/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.security.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.beans.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.id.UniqueId;
import com.opengamma.master.cache.AbstractEHCachingMaster;
import com.opengamma.master.cache.EHCachingPagedSearchCache;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecurityHistoryRequest;
import com.opengamma.master.security.SecurityHistoryResult;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecurityMetaDataRequest;
import com.opengamma.master.security.SecurityMetaDataResult;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.SecuritySearchResult;
import com.opengamma.master.security.SecuritySearchSortOrder;
import com.opengamma.util.paging.Paging;
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.tuple.ObjectsPair;

import net.sf.ehcache.CacheManager;

/**
 * A cache decorating a {@code SecurityMaster}, mainly intended to reduce the frequency and repetition of queries to
 * the underlying master.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingSecurityMaster extends AbstractEHCachingMaster<SecurityDocument> implements SecurityMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingSecurityMaster.class);

  /** The document search cache */
  private EHCachingPagedSearchCache _documentSearchCache;

  /** The history search cache */
  private EHCachingPagedSearchCache _historySearchCache;

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param name          the cache name, not null
   * @param underlying    the underlying security source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingSecurityMaster(final String name, final SecurityMaster underlying, final CacheManager cacheManager) {
    super(name, underlying, cacheManager);

    // Create the document search cache and register a security master searcher
    _documentSearchCache = new EHCachingPagedSearchCache(name + "Document", new EHCachingPagedSearchCache.Searcher() {
      @Override
      public ObjectsPair<Integer, List<UniqueId>> search(Bean request, PagingRequest pagingRequest) {
        // Fetch search results from underlying master
        SecuritySearchResult result = ((SecurityMaster) getUnderlying()).search((SecuritySearchRequest)
            EHCachingPagedSearchCache.withPagingRequest((SecuritySearchRequest) request, pagingRequest));

        // Cache the result documents
        EHCachingPagedSearchCache.cacheDocuments(result.getDocuments(), getUidToDocumentCache());

        // Return the list of result UniqueIds
        return new ObjectsPair<>(result.getPaging().getTotalItems(),
                                 EHCachingPagedSearchCache.extractUniqueIds(result.getDocuments()));
      }
    }, cacheManager);

    // Create the history search cache and register a security master searcher
    _historySearchCache = new EHCachingPagedSearchCache(name + "History", new EHCachingPagedSearchCache.Searcher() {
      @Override
      public ObjectsPair<Integer, List<UniqueId>> search(Bean request, PagingRequest pagingRequest) {
        // Fetch search results from underlying master
        SecurityHistoryResult result = ((SecurityMaster) getUnderlying()).history((SecurityHistoryRequest)
            EHCachingPagedSearchCache.withPagingRequest((SecurityHistoryRequest) request, pagingRequest));

        // Cache the result documents
        EHCachingPagedSearchCache.cacheDocuments(result.getDocuments(), getUidToDocumentCache());

        // Return the list of result UniqueIds
        return new ObjectsPair<>(result.getPaging().getTotalItems(),
                                 EHCachingPagedSearchCache.extractUniqueIds(result.getDocuments()));
      }
    }, cacheManager);

    // Prime document search cache
    SecuritySearchRequest defaultSearch = new SecuritySearchRequest();
    defaultSearch.setSortOrder(SecuritySearchSortOrder.NAME_ASC);
    _documentSearchCache.prefetch(defaultSearch, PagingRequest.FIRST_PAGE);
  }

  @Override
  public SecuritySearchResult search(SecuritySearchRequest request) {
    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    _documentSearchCache.prefetch(EHCachingPagedSearchCache.withPagingRequest(request, null), request.getPagingRequest());

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<UniqueId>> pair = _documentSearchCache.search(
        EHCachingPagedSearchCache.withPagingRequest(request, null),
        request.getPagingRequest(), false); // don't block until cached

    List<SecurityDocument> documents = new ArrayList<>();
    for (UniqueId uniqueId : pair.getSecond()) {
      documents.add(get(uniqueId));
    }

    SecuritySearchResult result = new SecuritySearchResult(documents);
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));
    return result;
  }

  @Override
  public SecurityHistoryResult history(SecurityHistoryRequest request) {

    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    _historySearchCache.prefetch(EHCachingPagedSearchCache.withPagingRequest(request, null), request.getPagingRequest());

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<UniqueId>> pair = _historySearchCache.search(
        EHCachingPagedSearchCache.withPagingRequest(request, null),
        request.getPagingRequest(), false); // don't block until cached

    List<SecurityDocument> documents = new ArrayList<>();
    for (UniqueId uniqueId : pair.getSecond()) {
      documents.add(get(uniqueId));
    }

    SecurityHistoryResult result = new SecurityHistoryResult(documents);
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));
    return result;
  }

  @Override
  public SecurityMetaDataResult metaData(SecurityMetaDataRequest request) {
    return ((SecurityMaster) getUnderlying()).metaData(request);
  }

}
