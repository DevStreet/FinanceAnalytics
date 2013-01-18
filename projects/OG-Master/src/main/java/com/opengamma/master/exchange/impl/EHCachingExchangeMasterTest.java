/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.exchange.impl;

import com.opengamma.id.ExternalIdBundle;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractEHCachingMasterTest;
import com.opengamma.master.exchange.ManageableExchange;
import com.opengamma.master.exchange.ExchangeDocument;
import com.opengamma.master.exchange.ExchangeMaster;
import org.testng.annotations.Test;

import javax.time.calendar.TimeZone;
import java.math.BigDecimal;

import static org.mockito.Mockito.mock;

/**
 * Test {@link EHCachingExchangeMaster}
 */
@Test
public class EHCachingExchangeMasterTest extends AbstractEHCachingMasterTest<ExchangeMaster, ExchangeDocument> {

  {
    // Initialise security documents

    // Document A
    docA100_V1999to2010_Cto2011 = new ExchangeDocument(
        new ManageableExchange(ExternalIdBundle.EMPTY, "A100", ExternalIdBundle.EMPTY, TimeZone.UTC));
    docA100_V1999to2010_Cto2011.setUniqueId(A100_UID);
    docA200_V2010to = new ExchangeDocument(
        new ManageableExchange(ExternalIdBundle.EMPTY, "A200", ExternalIdBundle.EMPTY, TimeZone.UTC));
    docA200_V2010to.setUniqueId(A200_UID);
    docA300_V1999to2010_C2011to = new ExchangeDocument(
        new ManageableExchange(ExternalIdBundle.EMPTY, "A300", ExternalIdBundle.EMPTY, TimeZone.UTC));
    docA300_V1999to2010_C2011to.setUniqueId(A300_UID);

    // Document B
    docB200_V2000to2009 = new ExchangeDocument(
        new ManageableExchange(ExternalIdBundle.EMPTY, "B200", ExternalIdBundle.EMPTY, TimeZone.UTC));
    docB200_V2000to2009.setUniqueId(B200_UID);
    docB400_V2009to2011 = new ExchangeDocument(
        new ManageableExchange(ExternalIdBundle.EMPTY, "B400", ExternalIdBundle.EMPTY, TimeZone.UTC));
    docB200_V2000to2009.setUniqueId(B400_UID);
    docB500_V2011to = new ExchangeDocument(
        new ManageableExchange(ExternalIdBundle.EMPTY, "B500", ExternalIdBundle.EMPTY, TimeZone.UTC));
    docB200_V2000to2009.setUniqueId(B500_UID);

    // Document C
    docC100_Vto2011 = new ExchangeDocument(
        new ManageableExchange(ExternalIdBundle.EMPTY, "C100", ExternalIdBundle.EMPTY, TimeZone.UTC));
    docC100_Vto2011.setUniqueId(C100_UID);
    docC300_V2011to = new ExchangeDocument(
        new ManageableExchange(ExternalIdBundle.EMPTY, "C300", ExternalIdBundle.EMPTY, TimeZone.UTC));
    docC300_V2011to.setUniqueId(C300_UID);
   }

  @Test
  void testSearch() {
    ExchangeMaster mockUnderlyingMaster = (ExchangeMaster) populateMockMaster(mock(ExchangeMaster.class));
    AbstractEHCachingMaster<ExchangeDocument> cachingMaster = new EHCachingExchangeMaster(mockUnderlyingMaster, getCleanCacheManager());
    
    //TODO
    
    cachingMaster.shutdown();
  }

  @Test
  void testHistorySearch() {
    ExchangeMaster mockUnderlyingMaster = (ExchangeMaster) populateMockMaster(mock(ExchangeMaster.class));
    AbstractEHCachingMaster<ExchangeDocument> cachingMaster = new EHCachingExchangeMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

}
