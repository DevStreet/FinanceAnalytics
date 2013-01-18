/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.position.impl;

import com.opengamma.id.UniqueId;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.position.ManageableTrade;
import com.opengamma.master.position.PositionDocument;
import com.opengamma.master.position.PositionHistoryRequest;
import com.opengamma.master.position.PositionHistoryResult;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.master.position.PositionSearchResult;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying Position source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingPositionMaster(final PositionMaster underlying, final CacheManager cacheManager) {
    super(underlying, cacheManager);
  }

  @Override
  public ManageableTrade getTrade(UniqueId tradeId) {
    return ((PositionMaster) getUnderlying()).getTrade(tradeId); //TODO
  }

  @Override
  public PositionSearchResult search(PositionSearchRequest request) {
    return ((PositionMaster) getUnderlying()).search(request); //TODO
  }

  @Override
  public PositionHistoryResult history(PositionHistoryRequest request) {
    return ((PositionMaster) getUnderlying()).history(request); //TODO
  }

}
