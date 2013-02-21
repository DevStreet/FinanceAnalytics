/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.position.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.id.UniqueId;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractSearchRequest;
import com.opengamma.master.DocumentSearchCache;
import com.opengamma.master.position.ManageableTrade;
import com.opengamma.master.position.PositionDocument;
import com.opengamma.master.position.PositionHistoryRequest;
import com.opengamma.master.position.PositionHistoryResult;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.master.position.PositionSearchResult;
import com.opengamma.util.paging.Paging;
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.tuple.ObjectsPair;

import net.sf.ehcache.CacheManager;

/**
 * A cache decorating a {@code PositionMaster}, mainly intended to reduce the frequency and repetition of queries
 * from the management UI to a {@code DbPositionMaster}.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingPositionMaster extends AbstractEHCachingMaster<PositionDocument> implements PositionMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingPositionMaster.class);

  /** The search cache */
  private DocumentSearchCache _documentSearchCache;

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying Position source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingPositionMaster(final PositionMaster underlying, final CacheManager cacheManager) {
    super(underlying, cacheManager);

    // Create the doc search cache and register a position master searcher
    _documentSearchCache = new DocumentSearchCache(cacheManager, "position", new DocumentSearchCache.CacheSearcher() {
      @Override
      public ObjectsPair<Integer, List<UniqueId>> search(AbstractSearchRequest request) {
        // Fetch search results from underlying master
        PositionSearchResult result = ((PositionMaster) getUnderlying()).search((PositionSearchRequest) request);

        // Cache the result documents
        DocumentSearchCache.cacheDocuments(result.getDocuments(), getUidToDocumentCache());

        // Return the list of result UniqueIds
        return new ObjectsPair<>(result.getPaging().getTotalItems(),
                                 DocumentSearchCache.extractUniqueIds(result.getDocuments()));
      }
    });

    // Prime search cache
    PositionSearchRequest defaultSearch = new PositionSearchRequest();
    defaultSearch.setPagingRequest(PagingRequest.FIRST_PAGE);
    _documentSearchCache.backgroundPrefetch(defaultSearch);
  }

  @Override
  public ManageableTrade getTrade(UniqueId tradeId) {
    return ((PositionMaster) getUnderlying()).getTrade(tradeId); //TODO
  }

  @Override
  public PositionSearchResult search(PositionSearchRequest request) {
    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    _documentSearchCache.backgroundPrefetch(request);

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<UniqueId>> pair = _documentSearchCache.doSearch(request, false); // don't block until cached

    List<PositionDocument> documents = new ArrayList<>();
    for (UniqueId uniqueId : pair.getSecond()) {
      documents.add(get(uniqueId));
    }

    PositionSearchResult result = new PositionSearchResult(documents);
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));
    return result;
  }

  @Override
  public PositionHistoryResult history(PositionHistoryRequest request) {
    return ((PositionMaster) getUnderlying()).history(request); //TODO
  }

}
