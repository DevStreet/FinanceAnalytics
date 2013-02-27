/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.config.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.id.UniqueId;
import com.opengamma.master.cache.AbstractEHCachingMaster;
import com.opengamma.master.AbstractSearchRequest;
import com.opengamma.master.cache.EHCachingDocumentSearchCache;
import com.opengamma.master.cache.SearchCache;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.config.ConfigHistoryRequest;
import com.opengamma.master.config.ConfigHistoryResult;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.ConfigMetaDataRequest;
import com.opengamma.master.config.ConfigMetaDataResult;
import com.opengamma.master.config.ConfigSearchRequest;
import com.opengamma.master.config.ConfigSearchResult;
import com.opengamma.master.config.ConfigSearchSortOrder;
import com.opengamma.util.paging.Paging;
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.tuple.ObjectsPair;

import net.sf.ehcache.CacheManager;

/**
 * A cache decorating a {@code ConfigMaster}, mainly intended to reduce the frequency and repetition of queries
 * from the management UI to a {@code DbConfigMaster}. In particular, prefetching is employed in paged queries,
 * which tend to scale poorly.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingConfigMaster extends AbstractEHCachingMaster<ConfigDocument> implements ConfigMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingConfigMaster.class);

  /** The search cache */
  private EHCachingDocumentSearchCache _documentSearchCache;

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param name          the cache name, not empty
   * @param underlying    the underlying config source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingConfigMaster(final String name, final ConfigMaster underlying, final CacheManager cacheManager) {
    super(name, underlying, cacheManager);

        // Create the doc search cache and register a config master searcher
    _documentSearchCache = new EHCachingDocumentSearchCache(name, new SearchCache.Searcher() {
      @Override
      public ObjectsPair<Integer, List<UniqueId>> search(AbstractSearchRequest request) {
        // Fetch search results from underlying master
        ConfigSearchResult<?> result = ((ConfigMaster) getUnderlying()).search((ConfigSearchRequest<?>) request);

        // Cache the result documents
        EHCachingDocumentSearchCache.cacheDocuments(result.getDocuments(), getUidToDocumentCache());

        // Return the list of result UniqueIds
        return new ObjectsPair<>(result.getPaging().getTotalItems(),
                                 EHCachingDocumentSearchCache.extractUniqueIds(result.getDocuments()));
      }
    }, cacheManager);

    // Prime search cache
    ConfigSearchRequest defaultSearch = new ConfigSearchRequest();
    defaultSearch.setSortOrder(ConfigSearchSortOrder.NAME_ASC);
    defaultSearch.setPagingRequest(PagingRequest.FIRST_PAGE);
    _documentSearchCache.backgroundPrefetch(defaultSearch);

  }

  @Override
  public ConfigMetaDataResult metaData(ConfigMetaDataRequest request) {
    return ((ConfigMaster) getUnderlying()).metaData(request); //TODO
  }

  @Override
  public <T> ConfigSearchResult<T> search(ConfigSearchRequest<T> request) {
    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    _documentSearchCache.backgroundPrefetch(request);

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<UniqueId>> pair = _documentSearchCache.search(request, false); // don't block until cached

    List<ConfigDocument> documents = new ArrayList<>();
    for (UniqueId uniqueId : pair.getSecond()) {
      documents.add(get(uniqueId));
    }

    ConfigSearchResult<T> result = new ConfigSearchResult<>(documents);
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));
    return result;
  }

  @Override
  public <T> ConfigHistoryResult<T> history(ConfigHistoryRequest<T> request) {
    return ((ConfigMaster) getUnderlying()).history(request); //TODO
  }

}
