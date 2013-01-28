/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.security.impl;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecurityHistoryRequest;
import com.opengamma.master.security.SecurityHistoryResult;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecurityMetaDataRequest;
import com.opengamma.master.security.SecurityMetaDataResult;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.SecuritySearchResult;
import com.opengamma.master.security.SecuritySearchSortOrder;
import com.opengamma.util.ExecutorServiceFactoryBean;
import com.opengamma.util.ehcache.EHCacheUtils;
import com.opengamma.util.paging.Paging;
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.tuple.ObjectsPair;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * A cache decorating a {@code SecurityMaster}, mainly intended to reduce the frequency and repetition of queries to
 * the underlying master.
 * <p>
 * The cache is implemented using {@code EHCache}.
 * TODO add front cache maps to keep EHCache happy
 * TODO OPTIMIZE cache replacement policy
 */
public class EHCachingSecurityMaster extends AbstractEHCachingMaster<SecurityDocument> implements SecurityMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingSecurityMaster.class);
  /** The number of default page sizes to prefetch */
  private final int PREFETCH_SIZE = 16 * 2;
  /** The document cache indexed by search requests. */
  private final Cache _documentBySearchRequestCache;
  /** The document by search request cache's name. */
  private final String _documentBySearchRequestCacheName = getClass().getName() + "-documentBySearchRequestCache";

  /** The prefetch thread executor service */
  private final ExecutorService _executorService;
  /** Future (completion) for current prefetch thread */
  Future<?> _completed;

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying security source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingSecurityMaster(final SecurityMaster underlying, final CacheManager cacheManager) {
    super(underlying, cacheManager);

    EHCacheUtils.addCache(cacheManager, _documentBySearchRequestCacheName);
    _documentBySearchRequestCache = EHCacheUtils.getCacheFromManager(cacheManager, _documentBySearchRequestCacheName);

    // Async prefetch executor service
    ExecutorServiceFactoryBean execBean = new ExecutorServiceFactoryBean();
    execBean.setNumberOfThreads(5);
    execBean.setStyle(ExecutorServiceFactoryBean.Style.CACHED);
    _executorService = execBean.getObjectCreating();

    // Prime cache
    SecuritySearchRequest defaultSearch = new SecuritySearchRequest();
    defaultSearch.setSortOrder(SecuritySearchSortOrder.NAME_ASC);
    defaultSearch.setPagingRequest(PagingRequest.ofIndex(0, PagingRequest.DEFAULT_PAGING_SIZE * PREFETCH_SIZE));
    search(defaultSearch);
  }

  @Override
  public SecuritySearchResult search(SecuritySearchRequest originalRequest) {

    // TODO OPTIMISE check for a cached superset of the current request and reuse matching documents from that cache entry

    // Create an unpaged search request from the original request
    final SecuritySearchRequest request = new SecuritySearchRequest();
    request.setPagingRequest(null);
    request.setExternalIdScheme(originalRequest.getExternalIdScheme());
    request.setExternalIdValue(originalRequest.getExternalIdValue());
    request.setExternalIdSearch(originalRequest.getExternalIdSearch());
    request.setFullDetail(originalRequest.isFullDetail());
    request.setName(originalRequest.getName());
    request.setObjectIds(originalRequest.getObjectIds());
    request.setSecurityType(originalRequest.getSecurityType());
    request.setSortOrder(originalRequest.getSortOrder());
    request.setVersionCorrection(originalRequest.getVersionCorrection());

    // Separate out paging request from rest of search request
    final PagingRequest pagingRequest = originalRequest.getPagingRequest();

    // TODO check for null paging request

    // A cache entry contains unpaged (total) result size and a map from start indices to ranges of cached result documents
    // Get cache entry for current request (or create and get a primed cache entry if not found)
    int unpagedResultSize;
    final NavigableMap<Integer, List<SecurityDocument>> rangeMap;
    if (getDocumentBySearchRequestCache().get(request) != null) {
      // Hit - use a cached result
      ObjectsPair<Integer, NavigableMap<Integer, List<SecurityDocument>>> cachedResult =
          (ObjectsPair<Integer, NavigableMap<Integer, List<SecurityDocument>>>)
              getDocumentBySearchRequestCache().get(request).getObjectValue();
      unpagedResultSize = cachedResult.getFirst();
      rangeMap = cachedResult.getSecond();
    } else {
      // Miss - create a new cached result and prime it by prefetching some documents
      // TODO reuse already cached documents rather than duplicating
      request.setPagingRequest(PagingRequest.ofIndex(
          pagingRequest.getFirstItem(),
          PagingRequest.DEFAULT_PAGING_SIZE * PREFETCH_SIZE
      ));
      SecuritySearchResult resultToCache = ((SecurityMaster) getUnderlying()).search(request);
      request.setPagingRequest(null);
      NavigableMap<Integer, List<SecurityDocument>> rangeMapToCache = new TreeMap<>();
      rangeMapToCache.put(resultToCache.getPaging().getFirstItem(), resultToCache.getDocuments());
      ObjectsPair<Integer, NavigableMap<Integer, List<SecurityDocument>>> newResult =
          new ObjectsPair<>(resultToCache.getPaging().getTotalItems(), rangeMapToCache);
      getDocumentBySearchRequestCache().put(new Element(request, newResult));
      unpagedResultSize = newResult.getFirst(); // the total number of documents (whether cached or not) in unpaged search result
      rangeMap = newResult.getSecond();         // the map of starting indices to cached ranges for the unpaged search result
    }

    ObjectsPair<Integer, List<SecurityDocument>> pair =
      cacheRange(request, rangeMap, pagingRequest.getFirstItem(), pagingRequest.getLastItem());
    int superIndex = pair.getFirst();
    List<SecurityDocument> superRange = pair.getSecond();

//    // Launch background worker thread for async prefetching
//    if (_completed == null || _completed.isDone()) {
//      _completed = _executorService.submit(
//          new Runnable() {
//            @Override
//            public void run() {
//              cacheRange(request, rangeMap, pagingRequest.getLastItem() + 1, pagingRequest.getLastItem() + 1 + PagingRequest.DEFAULT_PAGING_SIZE * PREFETCH_SIZE);
//            }
//          }
//      );
//    }

    // Now that a single super-range of cached result documents contains the result, retrieve and return the search result
    request.setPagingRequest(null);
    Collection<SecurityDocument> resultDocuments = superRange.subList(
        pagingRequest.getFirstItem() - superIndex,
        pagingRequest.getFirstItem() - superIndex + pagingRequest.getPagingSize()
    );
    SecuritySearchResult result = new SecuritySearchResult(resultDocuments);
    result.setPaging(Paging.of(pagingRequest, unpagedResultSize));
    return result;
  }

  public SecuritySearchResult search2(SecuritySearchRequest originalRequest) {

    // TODO OPTIMISE check for a cached superset of the current request and reuse matching documents from that cache entry

    // Create an unpaged search request from the original request
    final SecuritySearchRequest request = new SecuritySearchRequest();
    request.setPagingRequest(null);
    request.setExternalIdScheme(originalRequest.getExternalIdScheme());
    request.setExternalIdValue(originalRequest.getExternalIdValue());
    request.setExternalIdSearch(originalRequest.getExternalIdSearch());
    request.setFullDetail(originalRequest.isFullDetail());
    request.setName(originalRequest.getName());
    request.setObjectIds(originalRequest.getObjectIds());
    request.setSecurityType(originalRequest.getSecurityType());
    request.setSortOrder(originalRequest.getSortOrder());
    request.setVersionCorrection(originalRequest.getVersionCorrection());
    
    // Separate out paging request from rest of search request
    final PagingRequest pagingRequest = originalRequest.getPagingRequest();

    // TODO check for null paging request

    // A cache entry contains unpaged (total) result size and a map from start indices to ranges of cached result documents
    // Get cache entry for current request (or create and get a primed cache entry if not found)
    int unpagedResultSize;
    final NavigableMap<Integer, List<SecurityDocument>> rangeMap;
    if (getDocumentBySearchRequestCache().get(request) != null) {
      // Hit - use a cached result
      ObjectsPair<Integer, NavigableMap<Integer, List<SecurityDocument>>> cachedResult =
          (ObjectsPair<Integer, NavigableMap<Integer, List<SecurityDocument>>>)
              getDocumentBySearchRequestCache().get(request).getObjectValue();
      unpagedResultSize = cachedResult.getFirst();
      rangeMap = cachedResult.getSecond();
    } else {
      // Miss - create a new cached result and prime it by prefetching some documents
      // TODO reuse already cached documents rather than duplicating
      request.setPagingRequest(PagingRequest.ofIndex(
          pagingRequest.getFirstItem(),
          PagingRequest.DEFAULT_PAGING_SIZE * PREFETCH_SIZE
      ));
      SecuritySearchResult resultToCache = ((SecurityMaster) getUnderlying()).search(request);
      request.setPagingRequest(null);
      NavigableMap<Integer, List<SecurityDocument>> rangeMapToCache = new TreeMap<>();
      rangeMapToCache.put(resultToCache.getPaging().getFirstItem(), resultToCache.getDocuments());
      ObjectsPair<Integer, NavigableMap<Integer, List<SecurityDocument>>> newResult =
          new ObjectsPair<>(resultToCache.getPaging().getTotalItems(), rangeMapToCache);
      getDocumentBySearchRequestCache().put(new Element(request, newResult));
      unpagedResultSize = newResult.getFirst(); // the total number of documents (whether cached or not) in unpaged search result
      rangeMap = newResult.getSecond();         // the map of starting indices to cached ranges for the unpaged search result
    }

    // Now we have the cached ranges map and unpaged result size for this search request, fill in gaps for the required range
    // from the underlying, creating a single 'super range' from which the entire current search request can be satisfied

    // Determine the super range's start index
    final List<SecurityDocument> superRange;
    final int superIndex;
    if (rangeMap.floorKey(pagingRequest.getFirstItem()) != null &&
        rangeMap.floorKey(pagingRequest.getFirstItem()) +
            rangeMap.floorEntry(pagingRequest.getFirstItem()).getValue().size() >= pagingRequest.getFirstItem()) {
      superRange = rangeMap.floorEntry(pagingRequest.getFirstItem()).getValue();
      superIndex = rangeMap.floorKey(pagingRequest.getFirstItem());
    } else {
      //superRange = new LinkedList<>();
      superRange = Collections.synchronizedList(new LinkedList<SecurityDocument>());
      superIndex = pagingRequest.getFirstItem();
      rangeMap.put(superIndex, superRange);
    }

    // Get map subset from closest start index above requested start index, to closest start index below requested end index
    Integer start = rangeMap.ceilingKey(pagingRequest.getFirstItem());
    Integer end = rangeMap.floorKey(pagingRequest.getLastItem());
    NavigableMap<Integer, List<SecurityDocument>> subMap = (NavigableMap<Integer, List<SecurityDocument>>) (
        ((start != null) && (end != null) && (start <= end))
            ? rangeMap.subMap(start, true, end, true)
            : new TreeMap<>()
    );

    // Iterate through ranges within the paging request range, concatenating them into the super range while filling in any
    // gaps from the underlying master
    // TODO OPTIMISE coalesce underlying gets
    int currentIndex = superIndex + superRange.size();
    List<Integer> toRemove = new LinkedList<>();
    for (Map.Entry<Integer, List<SecurityDocument>> entry : subMap.entrySet()) {
      // If required, fill gap from underlying
      if (entry.getKey() > currentIndex) {
        request.setPagingRequest(PagingRequest.ofIndex(currentIndex, entry.getKey() - currentIndex));
        SecuritySearchResult missingResult = ((SecurityMaster) getUnderlying()).search(request);
        superRange.addAll(missingResult.getDocuments());
        currentIndex += missingResult.getDocuments().size();
      }

      // Add next cached range
      superRange.addAll(entry.getValue());
      currentIndex += entry.getValue().size();

      toRemove.add(entry.getKey());
    }

    // Remove original cached ranges (now part of the super range)
    //for (Map.Entry<Integer, List<SecurityDocument>> entry : subMap.entrySet()) {
    //  rangeMap.remove(entry.getKey());
    for (Integer i : toRemove) {
      rangeMap.remove(i);
    }

    // If required, fill in the final range from underlying
    if (currentIndex < pagingRequest.getLastItem()) {
      request.setPagingRequest(PagingRequest.ofIndex(
          currentIndex,
//          pagingRequest.getLastItem() + (PagingRequest.DEFAULT_PAGING_SIZE * PREFETCH_SIZE) - currentIndex
          pagingRequest.getLastItem() - currentIndex
      ));
      SecuritySearchResult missingResult = ((SecurityMaster) getUnderlying()).search(request);
      superRange.addAll(missingResult.getDocuments());
      currentIndex += missingResult.getDocuments().size();

      // Wait for the previous async prefetch request to complete before using the cache
      // TODO should go ahead anyway instead of waiting, then async prefetch should deal with it
//      if (_completed != null) {
//        try {
//          _completed.get();
//        } catch (Exception e) {
//          e.printStackTrace();  // TODO
//        }
//      }

      // Launch background worker thread for async prefetching
      if (_completed == null || _completed.isDone()) {
        _completed = _executorService.submit(
            new Runnable() {
              @Override
              public void run() {
                request.setPagingRequest(PagingRequest.ofIndex(
                    pagingRequest.getLastItem() + 1,
                    (PagingRequest.DEFAULT_PAGING_SIZE * PREFETCH_SIZE)
                ));
                SecuritySearchResult prefetchResult = ((SecurityMaster) getUnderlying()).search(request);
                //rangeMap.put(pagingRequest.getLastItem() + 1, prefetchResult.getDocuments()); //TODO share subroutine for this (for coalescing etc.)
                superRange.addAll(prefetchResult.getDocuments());
              }
            }
        );
      }
    }

    // put expanded super range into range map
    rangeMap.put(superIndex, superRange);

    // Now that a single super-range of cached result documents contains the result, retrieve and return the search result
    request.setPagingRequest(null);
    Collection<SecurityDocument> resultDocuments = superRange.subList(
        pagingRequest.getFirstItem() - superIndex,
        pagingRequest.getFirstItem() - superIndex + pagingRequest.getPagingSize()
    );
    SecuritySearchResult result = new SecuritySearchResult(resultDocuments);
    result.setPaging(Paging.of(pagingRequest, unpagedResultSize));
    return result;
  }

  @Override
  public SecurityMetaDataResult metaData(SecurityMetaDataRequest request) {
    return ((SecurityMaster) getUnderlying()).metaData(request); //TODO
  }

  @Override
  public SecurityHistoryResult history(SecurityHistoryRequest request) {
    return ((SecurityMaster) getUnderlying()).history(request); //TODO
  }

  // Now we have the cached ranges map and unpaged result size for this search request, fill in gaps for the required range
  // from the underlying, creating a single 'super range' from which the entire current search request can be satisfied
  protected ObjectsPair<Integer, List<SecurityDocument>> cacheRange(final SecuritySearchRequest request,
                            final NavigableMap<Integer, List<SecurityDocument>> rangeMap,
                            final int startIndex, final int endIndex) {

    // Determine the super range's start index
    final List<SecurityDocument> superRange;
    final int superIndex;
    if (rangeMap.floorKey(startIndex) != null &&
        rangeMap.floorKey(startIndex) +
            rangeMap.floorEntry(startIndex).getValue().size() >= startIndex) {
      superRange = rangeMap.floorEntry(startIndex).getValue();
      superIndex = rangeMap.floorKey(startIndex);
    } else {
      superRange = Collections.synchronizedList(new LinkedList<SecurityDocument>());
      superIndex = startIndex;
      rangeMap.put(superIndex, superRange);
    }

    // Get map subset from closest start index above requested start index, to closest start index below requested end index
    Integer start = rangeMap.ceilingKey(startIndex);
    Integer end = rangeMap.floorKey(endIndex);
    NavigableMap<Integer, List<SecurityDocument>> subMap = (NavigableMap<Integer, List<SecurityDocument>>) (
        ((start != null) && (end != null) && (start <= end))
            ? rangeMap.subMap(start, true, end, true)
            : new TreeMap<>()
    );

    // Iterate through ranges within the paging request range, concatenating them into the super range while filling in any
    // gaps from the underlying master
    // TODO OPTIMISE coalesce underlying gets
    int currentIndex = superIndex + superRange.size();
    List<Integer> toRemove = new LinkedList<>();
    for (Map.Entry<Integer, List<SecurityDocument>> entry : subMap.entrySet()) {
      // If required, fill gap from underlying
      if (entry.getKey() > currentIndex) {
        request.setPagingRequest(PagingRequest.ofIndex(currentIndex, entry.getKey() - currentIndex));
        SecuritySearchResult missingResult = ((SecurityMaster) getUnderlying()).search(request);
        superRange.addAll(missingResult.getDocuments());
        currentIndex += missingResult.getDocuments().size();
      }

      // Add next cached range
      superRange.addAll(entry.getValue());
      currentIndex += entry.getValue().size();

      toRemove.add(entry.getKey());
    }

    // Remove original cached ranges (now part of the super range)
    for (Integer i : toRemove) {
      rangeMap.remove(i);
    }

    // If required, fill in the final range from underlying
    if (currentIndex < endIndex) {
      request.setPagingRequest(PagingRequest.ofIndex(
          currentIndex,
          endIndex - currentIndex
      ));
      SecuritySearchResult missingResult = ((SecurityMaster) getUnderlying()).search(request);
      superRange.addAll(missingResult.getDocuments());
      currentIndex += missingResult.getDocuments().size();

      // Launch background worker thread for async prefetching
      if (_completed == null || _completed.isDone()) {
        _completed = _executorService.submit(
            new Runnable() {
              @Override
              public void run() {
                cacheRange(request, rangeMap, endIndex, endIndex + PagingRequest.DEFAULT_PAGING_SIZE * PREFETCH_SIZE);
              }
            }
        );
      }

    }

    // put expanded super range into range map
    rangeMap.put(superIndex, superRange);

    return new ObjectsPair<>(superIndex, superRange);
  }

  /**
   * Gets the documents by search request cache.
   *
   * @return the cache, not null
   */
  protected Cache getDocumentBySearchRequestCache() {
    return _documentBySearchRequestCache;
  }
}
