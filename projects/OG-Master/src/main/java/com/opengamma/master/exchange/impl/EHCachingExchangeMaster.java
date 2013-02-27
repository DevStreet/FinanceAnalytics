/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.exchange.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.id.UniqueId;
import com.opengamma.master.cache.AbstractEHCachingMaster;
import com.opengamma.master.AbstractSearchRequest;
import com.opengamma.master.cache.EHCachingDocumentSearchCache;
import com.opengamma.master.cache.SearchCache;
import com.opengamma.master.exchange.ExchangeDocument;
import com.opengamma.master.exchange.ExchangeHistoryRequest;
import com.opengamma.master.exchange.ExchangeHistoryResult;
import com.opengamma.master.exchange.ExchangeMaster;
import com.opengamma.master.exchange.ExchangeSearchRequest;
import com.opengamma.master.exchange.ExchangeSearchResult;
import com.opengamma.master.exchange.ExchangeSearchSortOrder;
import com.opengamma.util.paging.Paging;
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.tuple.ObjectsPair;

import net.sf.ehcache.CacheManager;

/**
 * A cache decorating a {@code ExchangeMaster}, mainly intended to reduce the frequency and repetition of queries
 * from the management UI to a {@code DbExchangeMaster}. In particular, prefetching is employed in paged queries,
 * which tend to scale poorly.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingExchangeMaster extends AbstractEHCachingMaster<ExchangeDocument> implements ExchangeMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingExchangeMaster.class);

  /** The search cache */
  private EHCachingDocumentSearchCache _documentSearchCache;
  
  /**
   * Creates an instance over an underlying master specifying the cache manager.
   *
   * @param name          the cache name, not empty
   * @param underlying    the underlying Exchange source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingExchangeMaster(final String name, final ExchangeMaster underlying, final CacheManager cacheManager) {
    super(name, underlying, cacheManager);
    
    // Create the doc search cache and register a exchange master searcher
    _documentSearchCache = new EHCachingDocumentSearchCache(name, new SearchCache.Searcher() {
      @Override
      public ObjectsPair<Integer, List<UniqueId>> search(AbstractSearchRequest request) {
        // Fetch search results from underlying master
        ExchangeSearchResult result = ((ExchangeMaster) getUnderlying()).search((ExchangeSearchRequest) request);

        // Cache the result documents
        EHCachingDocumentSearchCache.cacheDocuments(result.getDocuments(), getUidToDocumentCache());

        // Return the list of result UniqueIds
        return new ObjectsPair<>(result.getPaging().getTotalItems(),
                                 EHCachingDocumentSearchCache.extractUniqueIds(result.getDocuments()));
      }
    }, cacheManager);

    // Prime search cache
    ExchangeSearchRequest defaultSearch = new ExchangeSearchRequest();
    defaultSearch.setSortOrder(ExchangeSearchSortOrder.NAME_ASC);
    defaultSearch.setPagingRequest(PagingRequest.FIRST_PAGE);
    _documentSearchCache.backgroundPrefetch(defaultSearch);
    
  }

  @Override
  public ExchangeSearchResult search(ExchangeSearchRequest request) {
    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    _documentSearchCache.backgroundPrefetch(request);

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<UniqueId>> pair = _documentSearchCache.search(request, false); // don't block until cached

    List<ExchangeDocument> documents = new ArrayList<>();
    for (UniqueId uniqueId : pair.getSecond()) {
      documents.add(get(uniqueId));
    }

    ExchangeSearchResult result = new ExchangeSearchResult(documents);
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));
    return result;
  }

  @Override
  public ExchangeHistoryResult history(ExchangeHistoryRequest request) {
    return ((ExchangeMaster) getUnderlying()).history(request); //TODO
  }

}
