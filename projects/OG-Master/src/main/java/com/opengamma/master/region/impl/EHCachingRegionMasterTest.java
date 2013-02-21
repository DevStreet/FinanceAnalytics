/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.region.impl;

import static org.mockito.Mockito.mock;

import org.testng.annotations.Test;

import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractEHCachingMasterTest;
import com.opengamma.master.region.RegionDocument;
import com.opengamma.master.region.RegionMaster;

/**
 * Test {@link EHCachingRegionMaster}
 */
@Test
public class EHCachingRegionMasterTest extends AbstractEHCachingMasterTest<RegionMaster, RegionDocument> {

  {
    // Initialise region documents
  }

  @Test
  void testSearch() {
    RegionMaster mockUnderlyingMaster = (RegionMaster) populateMockMaster(mock(RegionMaster.class));
    AbstractEHCachingMaster<RegionDocument> cachingMaster = new EHCachingRegionMaster("region", mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  void testHistorySearch() {
    RegionMaster mockUnderlyingMaster = (RegionMaster) populateMockMaster(mock(RegionMaster.class));
    AbstractEHCachingMaster<RegionDocument> cachingMaster = new EHCachingRegionMaster("region", mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

}
