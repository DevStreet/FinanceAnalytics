/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.holiday.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.id.UniqueId;
import com.opengamma.master.cache.AbstractEHCachingMaster;
import com.opengamma.master.AbstractSearchRequest;
import com.opengamma.master.cache.EHCachingDocumentSearchCache;
import com.opengamma.master.cache.SearchCache;
import com.opengamma.master.holiday.HolidayDocument;
import com.opengamma.master.holiday.HolidayHistoryRequest;
import com.opengamma.master.holiday.HolidayHistoryResult;
import com.opengamma.master.holiday.HolidayMaster;
import com.opengamma.master.holiday.HolidayMetaDataRequest;
import com.opengamma.master.holiday.HolidayMetaDataResult;
import com.opengamma.master.holiday.HolidaySearchRequest;
import com.opengamma.master.holiday.HolidaySearchResult;
import com.opengamma.master.holiday.HolidaySearchSortOrder;
import com.opengamma.util.paging.Paging;
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.tuple.ObjectsPair;

import net.sf.ehcache.CacheManager;

/**
 * A cache decorating a {@code HolidayMaster}, mainly intended to reduce the frequency and repetition of queries
 * from the management UI to a {@code DbHolidayMaster}. In particular, prefetching is employed in paged queries,
 * which tend to scale poorly.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingHolidayMaster extends AbstractEHCachingMaster<HolidayDocument> implements HolidayMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingHolidayMaster.class);

  /** The search cache */
  private EHCachingDocumentSearchCache _documentSearchCache;
  
  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param name          the cache name, not empty
   * @param underlying    the underlying holiday source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingHolidayMaster(final String name, final HolidayMaster underlying, final CacheManager cacheManager) {
    super(name, underlying, cacheManager);

    // Create the doc search cache and register a holiday master searcher
    _documentSearchCache = new EHCachingDocumentSearchCache(name, new SearchCache.Searcher() {
      @Override
      public ObjectsPair<Integer, List<UniqueId>> search(AbstractSearchRequest request) {
        // Fetch search results from underlying master
        HolidaySearchResult result = ((HolidayMaster) getUnderlying()).search((HolidaySearchRequest) request);

        // Cache the result documents
        EHCachingDocumentSearchCache.cacheDocuments(result.getDocuments(), getUidToDocumentCache());

        // Return the list of result UniqueIds
        return new ObjectsPair<>(result.getPaging().getTotalItems(),
                                 EHCachingDocumentSearchCache.extractUniqueIds(result.getDocuments()));
      }
    }, cacheManager);

    // Prime search cache
    HolidaySearchRequest defaultSearch = new HolidaySearchRequest();
    defaultSearch.setSortOrder(HolidaySearchSortOrder.NAME_ASC);
    defaultSearch.setPagingRequest(PagingRequest.FIRST_PAGE);
    _documentSearchCache.backgroundPrefetch(defaultSearch);
  }

  @Override
  public HolidayMetaDataResult metaData(HolidayMetaDataRequest request) {
    return ((HolidayMaster) getUnderlying()).metaData(request);  // TODO
  }

  @Override
  public HolidaySearchResult search(HolidaySearchRequest request) {
    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    _documentSearchCache.backgroundPrefetch(request);

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<UniqueId>> pair = _documentSearchCache.search(request, false); // don't block until cached

    List<HolidayDocument> documents = new ArrayList<>();
    for (UniqueId uniqueId : pair.getSecond()) {
      documents.add(get(uniqueId));
    }

    HolidaySearchResult result = new HolidaySearchResult(documents);
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));
    return result;
  }

  @Override
  public HolidayHistoryResult history(HolidayHistoryRequest request) {
    return ((HolidayMaster) getUnderlying()).history(request); //TODO
  }

}
