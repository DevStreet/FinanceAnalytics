/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.security.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractSearchRequest;
import com.opengamma.master.AbstractSearchResult;
import com.opengamma.master.DocumentSearchCache;
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

  /** The search cache */
  private DocumentSearchCache<SecurityDocument> _documentSearchCache;

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying security source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingSecurityMaster(final SecurityMaster underlying, final CacheManager cacheManager) {
    super(underlying, cacheManager);

    _documentSearchCache = new DocumentSearchCache<>(cacheManager, "security", new DocumentSearchCache.CacheSearcher<SecurityDocument>() {
      @Override
      public AbstractSearchResult<SecurityDocument> search(AbstractSearchRequest request) {
        return ((SecurityMaster) getUnderlying()).search((SecuritySearchRequest) request);
      }
    });

    // Prime search cache
    SecuritySearchRequest defaultSearch = new SecuritySearchRequest();
    defaultSearch.setSortOrder(SecuritySearchSortOrder.NAME_ASC);
    defaultSearch.setPagingRequest(PagingRequest.ofIndex(0, PagingRequest.DEFAULT_PAGING_SIZE));
    _documentSearchCache.backgroundPrefetch(defaultSearch);
  }

  @Override
  public SecuritySearchResult search(SecuritySearchRequest request) {
    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    _documentSearchCache.backgroundPrefetch(request);

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<SecurityDocument>> pair = _documentSearchCache.doSearch(request, false); // don't block until cached

    SecuritySearchResult result = new SecuritySearchResult(pair.getSecond());
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));
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

}
