/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.position.impl;

import com.opengamma.id.ExternalIdBundle;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractEHCachingMasterTest;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.PositionDocument;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.impl.EHCachingSecurityMaster;
import net.sf.ehcache.CacheManager;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;

/**
 * Test {@link com.opengamma.master.position.impl.EHCachingPositionMaster}
 */
@Test
public class EHCachingPositionMasterTest extends AbstractEHCachingMasterTest<PositionMaster, PositionDocument> {

  {
    // Initialise security documents

    // Document A
    docA100_V1999to2010_Cto2011 = new PositionDocument(
        new ManageablePosition(A100_UID, BigDecimal.ONE, ExternalIdBundle.EMPTY));
    docA200_V2010to = new PositionDocument(
        new ManageablePosition(A200_UID, BigDecimal.ONE, ExternalIdBundle.EMPTY));
    docA300_V1999to2010_C2011to = new PositionDocument(
        new ManageablePosition(A300_UID, BigDecimal.ONE, ExternalIdBundle.EMPTY));

    // Document B
    docB200_V2000to2009 = new PositionDocument(
        new ManageablePosition(B200_UID, BigDecimal.ONE, ExternalIdBundle.EMPTY));
    docB400_V2009to2011 = new PositionDocument(
        new ManageablePosition(B400_UID, BigDecimal.ONE, ExternalIdBundle.EMPTY));
    docB500_V2011to = new PositionDocument(
        new ManageablePosition(B500_UID, BigDecimal.ONE, ExternalIdBundle.EMPTY));

    // Document C
    docC100_Vto2011 = new PositionDocument(
        new ManageablePosition(C100_UID, BigDecimal.ONE, ExternalIdBundle.EMPTY));
    docC300_V2011to = new PositionDocument(
        new ManageablePosition(C300_UID, BigDecimal.ONE, ExternalIdBundle.EMPTY));

   }

  @Test
  void testSearch() {
    PositionMaster mockUnderlyingMaster = (PositionMaster) populateMockMaster(mock(PositionMaster.class));
    AbstractEHCachingMaster<PositionDocument> cachingMaster = new EHCachingPositionMaster(mockUnderlyingMaster, getCleanCacheManager());
    
    //TODO
    
    cachingMaster.shutdown();
  }

  @Test
  void testHistorySearch() {
    PositionMaster mockUnderlyingMaster = (PositionMaster) populateMockMaster(mock(PositionMaster.class));
    AbstractEHCachingMaster<PositionDocument> cachingMaster = new EHCachingPositionMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

}
