/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.region.impl;

import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.region.RegionDocument;
import com.opengamma.master.region.RegionHistoryRequest;
import com.opengamma.master.region.RegionHistoryResult;
import com.opengamma.master.region.RegionMaster;
import com.opengamma.master.region.RegionSearchRequest;
import com.opengamma.master.region.RegionSearchResult;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A cache decorating a {@code RegionMaster}, mainly intended to reduce the frequency and repetition of queries
 * from the management UI to a {@code DbRegionMaster}. In particular, prefetching is employed in paged queries,
 * which tend to scale poorly.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingRegionMaster extends AbstractEHCachingMaster<RegionDocument> implements RegionMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingRegionMaster.class);

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying region source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingRegionMaster(final RegionMaster underlying, final CacheManager cacheManager) {
    super(underlying, cacheManager);
  }

  @Override
  public RegionSearchResult search(RegionSearchRequest request) {
    return ((RegionMaster) getUnderlying()).search(request); //TODO
  }

  @Override
  public RegionHistoryResult history(RegionHistoryRequest request) {
    return ((RegionMaster) getUnderlying()).history(request); //TODO
  }

}
