/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.holiday.impl;

import com.opengamma.id.UniqueId;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.holiday.HolidayDocument;
import com.opengamma.master.holiday.HolidayHistoryRequest;
import com.opengamma.master.holiday.HolidayHistoryResult;
import com.opengamma.master.holiday.HolidayMaster;
import com.opengamma.master.holiday.HolidayMetaDataRequest;
import com.opengamma.master.holiday.HolidayMetaDataResult;
import com.opengamma.master.holiday.HolidaySearchRequest;
import com.opengamma.master.holiday.HolidaySearchResult;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying holiday source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingHolidayMaster(final HolidayMaster underlying, final CacheManager cacheManager) {
    super(underlying, cacheManager);
  }

  @Override
  public HolidayMetaDataResult metaData(HolidayMetaDataRequest request) {
    return ((HolidayMaster) getUnderlying()).metaData(request);  // TODO
  }

  @Override
  public HolidaySearchResult search(HolidaySearchRequest request) {
    return ((HolidayMaster) getUnderlying()).search(request); //TODO
  }

  @Override
  public HolidayHistoryResult history(HolidayHistoryRequest request) {
    return ((HolidayMaster) getUnderlying()).history(request); //TODO
  }

}
