/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.exchange.impl;

import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.exchange.ExchangeDocument;
import com.opengamma.master.exchange.ExchangeHistoryRequest;
import com.opengamma.master.exchange.ExchangeHistoryResult;
import com.opengamma.master.exchange.ExchangeMaster;
import com.opengamma.master.exchange.ExchangeSearchRequest;
import com.opengamma.master.exchange.ExchangeSearchResult;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying Exchange source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingExchangeMaster(final ExchangeMaster underlying, final CacheManager cacheManager) {
    super(underlying, cacheManager);
  }

  @Override
  public ExchangeSearchResult search(ExchangeSearchRequest request) {
    return ((ExchangeMaster) getUnderlying()).search(request); //TODO
  }

  @Override
  public ExchangeHistoryResult history(ExchangeHistoryRequest request) {
    return ((ExchangeMaster) getUnderlying()).history(request); //TODO
  }

}
