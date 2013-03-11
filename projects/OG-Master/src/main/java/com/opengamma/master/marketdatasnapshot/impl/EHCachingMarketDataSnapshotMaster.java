/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.marketdatasnapshot.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.beans.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.Instant;

import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.master.cache.AbstractEHCachingMaster;
import com.opengamma.master.cache.EHCachingPagedSearchCache;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotDocument;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotHistoryRequest;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotHistoryResult;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotMaster;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotSearchRequest;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotSearchResult;
import com.opengamma.util.paging.Paging;
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.tuple.ObjectsPair;

import net.sf.ehcache.CacheManager;

/**
 * A cache decorating a {@code MarketDataSnapshotMaster}, mainly intended to reduce the frequency and repetition of queries to
 * the underlying master.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingMarketDataSnapshotMaster extends AbstractEHCachingMaster<MarketDataSnapshotDocument> implements MarketDataSnapshotMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingMarketDataSnapshotMaster.class);

  /** The document search cache */
  private EHCachingPagedSearchCache _documentSearchCache;

  /** The history search cache */
  private EHCachingPagedSearchCache _historySearchCache;

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param name          the cache name, not null
   * @param underlying    the underlying market data snapshot source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingMarketDataSnapshotMaster(final String name,
                                           final MarketDataSnapshotMaster underlying,
                                           final CacheManager cacheManager) {
    super(name, underlying, cacheManager);

    // Create the document search cache and register a marketDataSnapshot master searcher
    _documentSearchCache = new EHCachingPagedSearchCache(name + "Document", new EHCachingPagedSearchCache.Searcher() {
      @Override
      public ObjectsPair<Integer, List<UniqueId>> search(Bean request, PagingRequest pagingRequest) {
        // Fetch search results from underlying master
        MarketDataSnapshotSearchResult result = ((MarketDataSnapshotMaster) getUnderlying()).search((MarketDataSnapshotSearchRequest)
            EHCachingPagedSearchCache.withPagingRequest(request, pagingRequest));

        // Cache the result documents
        EHCachingPagedSearchCache.cacheDocuments(result.getDocuments(), getUidToDocumentCache());

        // Return the list of result UniqueIds
        return new ObjectsPair<>(result.getPaging().getTotalItems(),
                                 EHCachingPagedSearchCache.extractUniqueIds(result.getDocuments()));
      }
    }, cacheManager);

    // Create the history search cache and register a marketDataSnapshot master searcher
    _historySearchCache = new EHCachingPagedSearchCache(name + "History", new EHCachingPagedSearchCache.Searcher() {
      @Override
      public ObjectsPair<Integer, List<UniqueId>> search(Bean request, PagingRequest pagingRequest) {
        // Fetch search results from underlying master
        MarketDataSnapshotHistoryResult result = ((MarketDataSnapshotMaster) getUnderlying()).history((MarketDataSnapshotHistoryRequest)
            EHCachingPagedSearchCache.withPagingRequest((MarketDataSnapshotHistoryRequest) request, pagingRequest));

        // Cache the result documents
        EHCachingPagedSearchCache.cacheDocuments(result.getDocuments(), getUidToDocumentCache());

        // Return the list of result UniqueIds
        return new ObjectsPair<>(result.getPaging().getTotalItems(),
                                 EHCachingPagedSearchCache.extractUniqueIds(result.getDocuments()));
      }
    }, cacheManager);

    // Prime document search cache
    //MarketDataSnapshotSearchRequest defaultSearch = new MarketDataSnapshotSearchRequest();
    //defaultSearch.setSortOrder(MarketDataSnapshotSearchSortOrder.NAME_ASC);
    //_documentSearchCache.prefetch(defaultSearch, PagingRequest.FIRST_PAGE);
  }

  @Override
  public MarketDataSnapshotSearchResult search(MarketDataSnapshotSearchRequest request) {
    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
//    _documentSearchCache.prefetch(EHCachingPagedSearchCache.withPagingRequest(request, null), request.getPagingRequest());

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<UniqueId>> pair = _documentSearchCache.search(
        EHCachingPagedSearchCache.withPagingRequest(request, null),
        request.getPagingRequest(), false); // don't block until cached

    List<MarketDataSnapshotDocument> documents = new ArrayList<>();
    for (UniqueId uniqueId : pair.getSecond()) {
      documents.add(get(uniqueId));
    }

    MarketDataSnapshotSearchResult result = new MarketDataSnapshotSearchResult(documents);
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));

    final VersionCorrection vc = request.getVersionCorrection().withLatestFixed(Instant.now());
    result.setVersionCorrection(vc);

    // Debug: check result against underlying
    if (EHCachingPagedSearchCache.TEST_AGAINST_UNDERLYING) {
      MarketDataSnapshotSearchResult check = ((MarketDataSnapshotMaster) getUnderlying()).search(request);
      if (!result.getPaging().equals(check.getPaging())) {
        s_logger.error("_documentSearchCache.getCache().getName() + \" returned paging:\\n\"" + result.getPaging() +
                           "\nbut the underlying master returned paging:\n" + check.getPaging());
      }
      if (!result.getDocuments().equals(check.getDocuments())) {
        s_logger.error(_documentSearchCache.getCache().getName() + " returned documents:\n" + result.getDocuments() +
                           "\nbut the underlying master returned documents:\n" + check.getDocuments());
      }
    }

    return result;
  }

  @Override
  public MarketDataSnapshotHistoryResult history(MarketDataSnapshotHistoryRequest request) {

    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    _historySearchCache.prefetch(EHCachingPagedSearchCache.withPagingRequest(request, null), request.getPagingRequest());

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<UniqueId>> pair = _historySearchCache.search(
        EHCachingPagedSearchCache.withPagingRequest(request, null),
        request.getPagingRequest(), false); // don't block until cached

    List<MarketDataSnapshotDocument> documents = new ArrayList<>();
    for (UniqueId uniqueId : pair.getSecond()) {
      documents.add(get(uniqueId));
    }

    MarketDataSnapshotHistoryResult result = new MarketDataSnapshotHistoryResult(documents);
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));
    return result;
  }

}
