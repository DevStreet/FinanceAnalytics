/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.holiday.impl;

import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractEHCachingMasterTest;
import com.opengamma.master.holiday.ManageableHoliday;
import com.opengamma.master.holiday.HolidayDocument;
import com.opengamma.master.holiday.HolidayMaster;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

/**
 * Test {@link EHCachingHolidayMaster}
 */
@Test
public class EHCachingHolidayMasterTest extends AbstractEHCachingMasterTest<HolidayMaster, HolidayDocument> {

  {

  }

  @Test
  void testSearch() {
    HolidayMaster mockUnderlyingMaster = (HolidayMaster) populateMockMaster(mock(HolidayMaster.class));
    AbstractEHCachingMaster<HolidayDocument> cachingMaster = new EHCachingHolidayMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  void testHistorySearch() {
    HolidayMaster mockUnderlyingMaster = (HolidayMaster) populateMockMaster(mock(HolidayMaster.class));
    AbstractEHCachingMaster<HolidayDocument> cachingMaster = new EHCachingHolidayMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  void testMetaDataSearch() {
    HolidayMaster mockUnderlyingMaster = (HolidayMaster) populateMockMaster(mock(HolidayMaster.class));
    AbstractEHCachingMaster<HolidayDocument> cachingMaster = new EHCachingHolidayMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

}
