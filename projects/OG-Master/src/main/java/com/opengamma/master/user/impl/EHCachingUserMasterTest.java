/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.user.impl;

import static org.mockito.Mockito.mock;

import org.testng.annotations.Test;

import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractEHCachingMasterTest;
import com.opengamma.master.user.UserDocument;
import com.opengamma.master.user.UserMaster;

/**
 * Test {@link EHCachingUserMaster}
 */
@Test
public class EHCachingUserMasterTest extends AbstractEHCachingMasterTest<UserMaster, UserDocument> {

  {
    // Initialise user documents
  }

  @Test
  void testSearch() {
    UserMaster mockUnderlyingMaster = (UserMaster) populateMockMaster(mock(UserMaster.class));
    AbstractEHCachingMaster<UserDocument> cachingMaster = new EHCachingUserMaster("user", mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  void testHistorySearch() {
    UserMaster mockUnderlyingMaster = (UserMaster) populateMockMaster(mock(UserMaster.class));
    AbstractEHCachingMaster<UserDocument> cachingMaster = new EHCachingUserMaster("user", mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  void testMetaDataSearch() {
    UserMaster mockUnderlyingMaster = (UserMaster) populateMockMaster(mock(UserMaster.class));
    AbstractEHCachingMaster<UserDocument> cachingMaster = new EHCachingUserMaster("user", mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

}
