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
  public void testDataPointCacheGetLatest() {
    EHCachingDataPointCache ehCachingDataPointCache = getCleanDataPointCache();

    ManageableHistoricalTimeSeries cachedResult =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST);
    ManageableHistoricalTimeSeries underlyingResult =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST);
    assertEquals(underlyingResult, cachedResult);

    // TODO test repeated gets, underlying get counts
  }

  @Test
  public void testDataPointCacheGetLatestWithClosedDateRange() {
    EHCachingDataPointCache ehCachingDataPointCache = getCleanDataPointCache();

    ManageableHistoricalTimeSeries cachedResult =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 2, 1), LocalDate.of(2012, 3, 1)));
    ManageableHistoricalTimeSeries underlyingResult =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 2, 1), LocalDate.of(2012, 3, 1)));
    assertEquals(underlyingResult, cachedResult);

    // TODO test repeated gets, underlying get counts
  }


  @Test
  public void testDataPointCacheGetWithWideClosedDateRange() {
    EHCachingDataPointCache ehCachingDataPointCache = getCleanDataPointCache();

    ManageableHistoricalTimeSeries cachedResult =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2011, 12, 1), LocalDate.of(2012, 3, 1)));
    ManageableHistoricalTimeSeries underlyingResult =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2011, 12, 1), LocalDate.of(2012, 3, 1)));
    assertEquals(underlyingResult, cachedResult);
  }

  @Test
  public void testDataPointCacheGetLatestWithOpenDateRange() {
    EHCachingDataPointCache ehCachingDataPointCache = getCleanDataPointCache();

    ManageableHistoricalTimeSeries cachedResult =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofAll());
    ManageableHistoricalTimeSeries underlyingResult =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofAll());
    assertEquals(underlyingResult, cachedResult);
  }

  @Test
  public void testDataPointCacheTwoGetsWithExpandingDateRange() {
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

    assertEquals(underlyingResult1, cachedResult1);
    assertEquals(underlyingResult2, cachedResult2);
  }

  @Test
  public void testDataPointCacheTwoGetsWithOverlappingDateRange() {
    EHCachingDataPointCache ehCachingDataPointCache = getCleanDataPointCache();

    ManageableHistoricalTimeSeries cachedResult1 =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 3, 1)));
    ManageableHistoricalTimeSeries cachedResult2 =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 2, 1), LocalDate.of(2012, 4, 1)));

    ManageableHistoricalTimeSeries underlyingResult1 =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 3, 1)));
    ManageableHistoricalTimeSeries underlyingResult2 =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 2, 1), LocalDate.of(2012, 4, 1)));

    assertEquals(underlyingResult1, cachedResult1);
    assertEquals(underlyingResult2, cachedResult2);
  }

  @Test
  public void testDataPointCacheMultipleGetsWithGapBetweenDateRanges() {
    EHCachingDataPointCache ehCachingDataPointCache = getCleanDataPointCache();

    ManageableHistoricalTimeSeries cachedResult1 =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 2, 28)));
    ManageableHistoricalTimeSeries cachedResult2 =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 3, 1), LocalDate.of(2012, 4, 30)));
    ManageableHistoricalTimeSeries cachedResult3 =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 2, 1), LocalDate.of(2012, 3, 31)));
    ManageableHistoricalTimeSeries cachedResult4 =
        ehCachingDataPointCache.getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 5, 1)));

    ManageableHistoricalTimeSeries underlyingResult1 =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 2, 28)));
    ManageableHistoricalTimeSeries underlyingResult2 =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 3, 1), LocalDate.of(2012, 4, 30)));
    ManageableHistoricalTimeSeries underlyingResult3 =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 2, 1), LocalDate.of(2012, 3, 31)));
    ManageableHistoricalTimeSeries underlyingResult4 =
        ehCachingDataPointCache.getUnderlying().getTimeSeries(ObjectId.of("Hts", "1"), VersionCorrection.LATEST,
            HistoricalTimeSeriesGetFilter.ofRange(LocalDate.of(2012, 1, 1), LocalDate.of(2012, 5, 1)));

    assertEquals(underlyingResult1, cachedResult1);
    assertEquals(underlyingResult2, cachedResult2);
    assertEquals(underlyingResult3, cachedResult3);
    assertEquals(underlyingResult4, cachedResult4);
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
