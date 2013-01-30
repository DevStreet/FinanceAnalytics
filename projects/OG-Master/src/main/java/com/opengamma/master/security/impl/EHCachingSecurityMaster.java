/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.security.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;

import org.joda.beans.JodaBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

/**
 * A cache decorating a {@code SecurityMaster}, mainly intended to reduce the frequency and repetition of queries to
 * the underlying master.
 * <p>
 * The cache is implemented using {@code EHCache}.
 * TODO add front cache maps to keep EHCache happy
 * TODO OPTIMIZE cache replacement policy
 * TODO ensure that docs are not duplicated in-cache
 */
public class EHCachingSecurityMaster extends AbstractEHCachingMaster<SecurityDocument> implements SecurityMaster {

  /** The number of documents to prefetch on each end of the current paging request */
  private static final int PREFETCH_RADIUS = 2;
  /** */
  private static final int PREFETCH_GRANULARITY = 100;
  /** The maximum number of concurrent prefetch operations */
  private final int MAX_PREFETCH_CONCURRENCY = 4;

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingSecurityMaster.class);
  /** The document cache indexed by search requests. */
  private final Cache _documentBySearchRequestCache;
  /** The document by search request cache's name. */
  private final String _documentBySearchRequestCacheName = getClass().getName() + "-documentBySearchRequestCache";
  // A cache entry contains unpaged (total) result size and a map from start indices to ranges of cached result documents

  /** The prefetch thread executor service */
  private final ExecutorService _executorService;


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
    execBean.setNumberOfThreads(MAX_PREFETCH_CONCURRENCY);
    execBean.setStyle(ExecutorServiceFactoryBean.Style.CACHED);
    _executorService = execBean.getObjectCreating();

    // Prime cache
    SecuritySearchRequest defaultSearch = new SecuritySearchRequest();
    defaultSearch.setSortOrder(SecuritySearchSortOrder.NAME_ASC);
    defaultSearch.setPagingRequest(PagingRequest.ofIndex(0, PREFETCH_GRANULARITY));
    backgroundPrefetch(defaultSearch);
  }

  @Override
  public SecuritySearchResult search(SecuritySearchRequest request) {
    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    backgroundPrefetch(request);

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    return doSearch(request);
  }

  @Override
   public SecurityMetaDataResult metaData(SecurityMetaDataRequest request) {
     return ((SecurityMaster) getUnderlying()).metaData(request); //TODO
   }

  @Override
  public SecurityHistoryResult history(SecurityHistoryRequest request) {
    return ((SecurityMaster) getUnderlying()).history(request); //TODO
  }

  /**
   * Return a clone of the supplied search request, but with its paging request nulled out
   * @param request the search request
   * @return        a clone of the supplied search request, with its paging request nulled out
   */
  protected SecuritySearchRequest withoutPagingRequest(final SecuritySearchRequest request) {
    final SecuritySearchRequest result = JodaBeanUtils.clone(request);
    result.setPagingRequest(null);
    return result;
  }

  /**
   * Calculate the range that should be prefetched for the supplied request and initiate the fetching of any uncached
   * ranges from the underlying master in the background, without blocking.
   * @param request the search request
   */
  protected void backgroundPrefetch(final SecuritySearchRequest originalRequest) {

    // Build larger range to prefetch
    final int start =
        (originalRequest.getPagingRequest().getFirstItem() / PREFETCH_GRANULARITY >= PREFETCH_RADIUS)
        ? ((originalRequest.getPagingRequest().getFirstItem() / PREFETCH_GRANULARITY) * PREFETCH_GRANULARITY)
              - (PREFETCH_RADIUS * PREFETCH_GRANULARITY)
        : 0;
    final int end =
          ((originalRequest.getPagingRequest().getLastItem() / PREFETCH_GRANULARITY) * PREFETCH_GRANULARITY)
              +  (PREFETCH_RADIUS * PREFETCH_GRANULARITY); //TODO what if last item beyond end?
    PagingRequest superPagingRequest = PagingRequest.ofIndex(start, end - start);

    // Build new security request with larger range
    final SecuritySearchRequest superRequest = withoutPagingRequest(originalRequest);
    superRequest.setPagingRequest(superPagingRequest);

    // Submit search task to background executor
    _executorService.submit(new Runnable() {
      @Override
      public void run() {
        doSearch(superRequest);
      }
    });
  }

  /**
   * If result is entirely cached return it immediately; otherwise, fetch any missing ranges from the underlying
   * master in the foreground, cache and return it.
   * @param request the search request
   * @return        the search result
   */
  protected SecuritySearchResult doSearch(final SecuritySearchRequest request) {

    // Fetch the total #documents and cached document ranges for the search request (without paging)
    final ObjectsPair<Integer, ConcurrentNavigableMap<Integer, List<SecurityDocument>>> info =
        getCachedRequestInfo(getDocumentBySearchRequestCache(), request);
    final int totalDocuments = info.getFirst();
    final ConcurrentNavigableMap<Integer, List<SecurityDocument>> rangeMap = info.getSecond();

    // Ensure that the required range is cached in its entirety
    ObjectsPair<Integer, List<SecurityDocument>> pair = cacheSuperRange(request, rangeMap);
    final int superIndex = pair.getFirst();
    final List<SecurityDocument> superRange = pair.getSecond();

    // Create and return the search result
    final Collection<SecurityDocument> resultDocuments = superRange.subList(
        request.getPagingRequest().getFirstItem() - superIndex,
        request.getPagingRequest().getLastItem()  - superIndex);
    final SecuritySearchResult result = new SecuritySearchResult(resultDocuments);
    result.setPaging(Paging.of(request.getPagingRequest(), totalDocuments));
    return result;
  }

  /**
   * Retrieve from cache the total #documents and the cached document ranges for the supplied search request (without
   * taking into account its paging request). If an cached entry is not found for the unpaged search request, then
   * create one, populate it with the results of the supplied paged search request, and return it.
   * @param cache   the search request ehcache
   * @param request the search request
   * @return        the total document count and a range map of cached documents for the supplied search request without
   *                paging
   */
  protected ObjectsPair<Integer, ConcurrentNavigableMap<Integer, List<SecurityDocument>>>
                      getCachedRequestInfo(final Cache cache, final SecuritySearchRequest originalRequest) {

    SecuritySearchRequest request = withoutPagingRequest(originalRequest);

    // Get cache entry for current request (or create and get a primed cache entry if not found)
    final Element element = getDocumentBySearchRequestCache().get(request);
    if (element != null) {
      return (ObjectsPair<Integer, ConcurrentNavigableMap<Integer, List<SecurityDocument>>>) element.getObjectValue();
    } else {
      // Build a new cached map entry and pre-fill it with the results of the supplied search request
      final SecuritySearchResult resultToCache = ((SecurityMaster) getUnderlying()).search(originalRequest);
      //SecuritySearchResult resultToCache = doSearch(originalRequest);
      final ConcurrentNavigableMap<Integer, List<SecurityDocument>> rangeMapToCache = new ConcurrentSkipListMap<>();
      rangeMapToCache.put(resultToCache.getPaging().getFirstItem(), resultToCache.getDocuments());
      final ObjectsPair<Integer, ConcurrentNavigableMap<Integer, List<SecurityDocument>>> newResult =
          new ObjectsPair<>(resultToCache.getPaging().getTotalItems(), rangeMapToCache);
      getDocumentBySearchRequestCache().put(new Element(request, newResult));
      return newResult;
    }
  }

  /**
   * Fill in any uncached gaps for the requested range from the underlying, creating a single cached 'super range' from
   * which the entire current search request can be satisfied. This method may be called concurrently in multiple
   * threads.
   * @param originalRequest the search request
   * @param rangeMap        the range map of cached documents for the supplied search request without paging
   * @return                a super-range of cached documents that contains at least the requested documents
   */
//  protected synchronized ObjectsPair<Integer, List<SecurityDocument>> cacheSuperRange(final SecuritySearchRequest originalRequest,
  protected ObjectsPair<Integer, List<SecurityDocument>> cacheSuperRange(final SecuritySearchRequest originalRequest,
                            final ConcurrentNavigableMap<Integer, List<SecurityDocument>> rangeMap) {


    final int startIndex = originalRequest.getPagingRequest().getFirstItem();
    final int endIndex = originalRequest.getPagingRequest().getLastItem();
    SecuritySearchRequest request = withoutPagingRequest(originalRequest);

    final List<SecurityDocument> superRange;
    final int superIndex;

    synchronized (rangeMap) {

      // Determine the super range's start index
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
      final Integer start = rangeMap.ceilingKey(startIndex + 1);
      final Integer end = rangeMap.floorKey(endIndex);
      final ConcurrentNavigableMap<Integer, List<SecurityDocument>> subMap = (ConcurrentNavigableMap<Integer, List<SecurityDocument>>) (
          ((start != null) && (end != null) && (start <= end))
              ? rangeMap.subMap(start, true, end, true)
              : new ConcurrentSkipListMap<>()
      );


      // Iterate through ranges within the paging request range, concatenating them into the super range while filling in any
      // gaps from the underlying master
      // TODO OPTIMISE coalesce underlying gets
      int currentIndex = superIndex + superRange.size();
      final List<Integer> toRemove = new LinkedList<>();
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
        final SecuritySearchResult missingResult = ((SecurityMaster) getUnderlying()).search(request);
        superRange.addAll(missingResult.getDocuments());
        currentIndex += missingResult.getDocuments().size();
      }

      // put expanded super range into range map
      rangeMap.put(superIndex, superRange);
    }

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
