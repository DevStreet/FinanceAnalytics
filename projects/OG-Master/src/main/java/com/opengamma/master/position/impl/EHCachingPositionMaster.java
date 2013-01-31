/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.position.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.id.UniqueId;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractSearchRequest;
import com.opengamma.master.AbstractSearchResult;
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
 * from the management UI to a {@code DbPositionMaster}. In particular, prefetching is employed in paged queries,
 * which tend to scale poorly.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingPositionMaster extends AbstractEHCachingMaster<PositionDocument> implements PositionMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingPositionMaster.class);

  /** The search cache */
  private DocumentSearchCache<PositionDocument> _documentSearchCache;

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying Position source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingPositionMaster(final PositionMaster underlying, final CacheManager cacheManager) {
    super(underlying, cacheManager);

    _documentSearchCache = new DocumentSearchCache<>(cacheManager, "position", new DocumentSearchCache.CacheSearcher<PositionDocument>() {
      @Override
      public AbstractSearchResult<PositionDocument> search(AbstractSearchRequest request) {
        return ((PositionMaster) getUnderlying()).search((PositionSearchRequest) request);
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
    ObjectsPair<Integer, List<PositionDocument>> pair = _documentSearchCache.doSearch(request, false); // don't block until cached

    PositionSearchResult result = new PositionSearchResult(pair.getSecond());
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));
    return result;
  }

  @Override
  public PositionHistoryResult history(PositionHistoryRequest request) {
    return ((PositionMaster) getUnderlying()).history(request); //TODO
  }

}
