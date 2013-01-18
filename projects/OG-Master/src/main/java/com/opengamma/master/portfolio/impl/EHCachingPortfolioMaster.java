/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.portfolio.impl;

import com.opengamma.id.UniqueId;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.portfolio.ManageablePortfolioNode;
import com.opengamma.master.portfolio.PortfolioDocument;
import com.opengamma.master.portfolio.PortfolioHistoryRequest;
import com.opengamma.master.portfolio.PortfolioHistoryResult;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.portfolio.PortfolioSearchRequest;
import com.opengamma.master.portfolio.PortfolioSearchResult;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A cache decorating a {@code PortfolioMaster}, mainly intended to reduce the frequency and repetition of queries
 * from the management UI to a {@code DbPortfolioMaster}. In particular, prefetching is employed in paged queries,
 * which tend to scale poorly.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingPortfolioMaster extends AbstractEHCachingMaster<PortfolioDocument> implements PortfolioMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingPortfolioMaster.class);

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying portfolio source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingPortfolioMaster(final PortfolioMaster underlying, final CacheManager cacheManager) {
    super(underlying, cacheManager);
  }

  @Override
  public PortfolioSearchResult search(PortfolioSearchRequest request) {
    return ((PortfolioMaster) getUnderlying()).search(request); //TODO
  }

  @Override
  public PortfolioHistoryResult history(PortfolioHistoryRequest request) {
    return ((PortfolioMaster) getUnderlying()).history(request); //TODO
  }

  @Override
  public ManageablePortfolioNode getNode(UniqueId nodeId) {
    return ((PortfolioMaster) getUnderlying()).getNode(nodeId);  // TODO
  }

}
