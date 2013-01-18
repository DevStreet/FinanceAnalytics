/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.user.impl;

import com.opengamma.id.ExternalIdBundle;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractEHCachingMasterTest;
import com.opengamma.master.user.ManageableOGUser;
import com.opengamma.master.user.UserDocument;
import com.opengamma.master.user.UserMaster;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

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
    AbstractEHCachingMaster<UserDocument> cachingMaster = new EHCachingUserMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  void testHistorySearch() {
    UserMaster mockUnderlyingMaster = (UserMaster) populateMockMaster(mock(UserMaster.class));
    AbstractEHCachingMaster<UserDocument> cachingMaster = new EHCachingUserMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  void testMetaDataSearch() {
    UserMaster mockUnderlyingMaster = (UserMaster) populateMockMaster(mock(UserMaster.class));
    AbstractEHCachingMaster<UserDocument> cachingMaster = new EHCachingUserMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

}
