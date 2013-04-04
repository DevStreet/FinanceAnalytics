/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.historicaltimeseries.impl;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;
import org.threeten.bp.LocalDate;

import com.opengamma.id.ObjectId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesGetFilter;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeries;
import com.opengamma.util.ehcache.EHCacheUtils;
import com.opengamma.util.test.TestGroup;

import net.sf.ehcache.CacheManager;

@Test(groups = TestGroup.UNIT)
public class EHCachingDataPointCacheTest {

  @Test
  public void testDataPointCacheGetLatestNoFilter() {
    EHCachingDataPointCache ehCachingDataPointCache = getCleanDataPointCache();

    ManageableHistoricalTimeSeries cachedResult =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST);
    ManageableHistoricalTimeSeries underlyingResult =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST);
    assertEquals(cachedResult, underlyingResult);

    // TODO test repeated gets, underlying get counts
  }

  @Test
  public void testDataPointCacheGetLatestWithFilter() {
    EHCachingDataPointCache ehCachingDataPointCache = getCleanDataPointCache();

    ManageableHistoricalTimeSeries cachedResult =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 2, 1), LocalDate.of(2012, 3, 1)));
    ManageableHistoricalTimeSeries underlyingResult =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 2, 1), LocalDate.of(2012, 3, 1)));
    assertEquals(cachedResult, underlyingResult);

    // TODO test repeated gets, underlying get counts
  }

  @Test
  public void testDataPointCacheMultipleGetsWithExpandingFilters() {
    EHCachingDataPointCache ehCachingDataPointCache = getCleanDataPointCache();

    ManageableHistoricalTimeSeries cachedResult1 =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 2, 1), LocalDate.of(2012, 3, 1)));
    ManageableHistoricalTimeSeries cachedResult2 =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 4, 1)));

    ManageableHistoricalTimeSeries underlyingResult1 =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 2, 1), LocalDate.of(2012, 3, 1)));
    ManageableHistoricalTimeSeries underlyingResult2 =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 4, 1)));

    assertEquals(cachedResult1, underlyingResult1);
    assertEquals(cachedResult2, underlyingResult2);
  }

  @Test
  public void testDataPointCacheGetWithWideFilter() {
    EHCachingDataPointCache ehCachingDataPointCache = getCleanDataPointCache();

    ManageableHistoricalTimeSeries cachedResult =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2011, 12, 1), LocalDate.of(2012, 3, 1)));
    ManageableHistoricalTimeSeries underlyingResult =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2011, 12, 1), LocalDate.of(2012, 3, 1)));
    assertEquals(cachedResult, underlyingResult);
  }

  /**
   * Returns an empty cache manager
   * @return the cache manager
   */
  private CacheManager getCleanCacheManager() {
    CacheManager cacheManager = EHCacheUtils.createTestCacheManager(getClass().getName() + System.currentTimeMillis());
    cacheManager.clearAll();
    cacheManager.removalAll();
    return cacheManager;
  }

  private EHCachingDataPointCache getCleanDataPointCache() {
    return new EHCachingDataPointCache("Test", new DataPointTestMaster(), getCleanCacheManager());
  }

}
