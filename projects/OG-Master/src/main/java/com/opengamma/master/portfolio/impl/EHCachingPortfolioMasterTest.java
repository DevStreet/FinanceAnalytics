/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.portfolio.impl;

import com.opengamma.id.ExternalIdBundle;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractEHCachingMasterTest;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.portfolio.PortfolioDocument;
import com.opengamma.master.portfolio.PortfolioMaster;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

/**
 * Test {@link EHCachingPortfolioMaster}
 */
@Test
public class EHCachingPortfolioMasterTest extends AbstractEHCachingMasterTest<PortfolioMaster, PortfolioDocument> {

  {
    // Initialise portfolio documents

    // Document A
    docA100_V1999to2010_Cto2011 = new PortfolioDocument(new ManageablePortfolio("A100"));
    docA100_V1999to2010_Cto2011.setUniqueId(A100_UID);
    docA200_V2010to = new PortfolioDocument(new ManageablePortfolio("A200"));
    docA200_V2010to.setUniqueId(A200_UID);
    docA300_V1999to2010_C2011to = new PortfolioDocument(new ManageablePortfolio("A300"));
    docA300_V1999to2010_C2011to.setUniqueId(A300_UID);

    // Document B
    docB200_V2000to2009 = new PortfolioDocument(new ManageablePortfolio("B200"));
    docB400_V2009to2011 = new PortfolioDocument(new ManageablePortfolio("B400"));
    docB500_V2011to = new PortfolioDocument(new ManageablePortfolio("B500"));

    // Document C
    docC100_Vto2011 = new PortfolioDocument(new ManageablePortfolio("C100"));
    docC100_Vto2011.setUniqueId(C100_UID);
    docC300_V2011to = new PortfolioDocument(new ManageablePortfolio("C300"));
    docC300_V2011to.setUniqueId(C300_UID);

    // Document to add
    DOC_TO_ADD = new PortfolioDocument(new ManageablePortfolio("D"));
    DOC_ADDED = new PortfolioDocument(new ManageablePortfolio("D"));
    DOC_ADDED.setUniqueId(ADDED_UID);
   }

  @Test
  void testSearch() {
    PortfolioMaster mockUnderlyingMaster = (PortfolioMaster) populateMockMaster(mock(PortfolioMaster.class));
    AbstractEHCachingMaster<PortfolioDocument> cachingMaster = new EHCachingPortfolioMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  void testHistorySearch() {
    PortfolioMaster mockUnderlyingMaster = (PortfolioMaster) populateMockMaster(mock(PortfolioMaster.class));
    AbstractEHCachingMaster<PortfolioDocument> cachingMaster = new EHCachingPortfolioMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  void testGetNode() {
    PortfolioMaster mockUnderlyingMaster = (PortfolioMaster) populateMockMaster(mock(PortfolioMaster.class));
    AbstractEHCachingMaster<PortfolioDocument> cachingMaster = new EHCachingPortfolioMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

}
