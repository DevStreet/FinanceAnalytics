/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.config.impl;

import com.opengamma.core.config.impl.ConfigItem;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractEHCachingMasterTest;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.config.ConfigMaster;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

/**
 * Test {@link EHCachingConfigMaster}
 */
@Test
public class EHCachingConfigMasterTest extends AbstractEHCachingMasterTest<ConfigMaster, ConfigDocument> {

  {
    // Initialise config documents

    // Document A
    docA100_V1999to2010_Cto2011 = new ConfigDocument("A100", String.class, "A", A100_UID, null, null, null, null);
    docA200_V2010to = new ConfigDocument("A200", String.class, "A", A200_UID, null, null, null, null);
    docA300_V1999to2010_C2011to = new ConfigDocument("A300", String.class, "A", A300_UID, null, null, null, null);

    // Document B
    docB200_V2000to2009 = new ConfigDocument("B200", String.class, "B", B200_UID, null, null, null, null);
    docB400_V2009to2011 = new ConfigDocument("B400", String.class, "B", B400_UID, null, null, null, null);
    docB500_V2011to = new ConfigDocument("B500", String.class, "B", B500_UID, null, null, null, null);

    // Document C
    docC100_Vto2011 = new ConfigDocument("C100", String.class, "C", C100_UID, null, null, null, null);
    docC300_V2011to = new ConfigDocument("C300", String.class, "C", C300_UID, null, null, null, null);

    // Document to add
    DOC_TO_ADD = new ConfigDocument("D", String.class, "D", null, null, null, null, null);
    DOC_ADDED = new ConfigDocument("D", String.class, "D", ADDED_UID, null, null, null, null);
  }

  class TestItem {
    
  }
  
  @Test
  void testSearch() {
    ConfigMaster mockUnderlyingMaster = (ConfigMaster) populateMockMaster(mock(ConfigMaster.class));
    AbstractEHCachingMaster<ConfigDocument> cachingMaster = new EHCachingConfigMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  void testHistorySearch() {
    ConfigMaster mockUnderlyingMaster = (ConfigMaster) populateMockMaster(mock(ConfigMaster.class));
    AbstractEHCachingMaster<ConfigDocument> cachingMaster = new EHCachingConfigMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  void testMetaDataSearch() {
    ConfigMaster mockUnderlyingMaster = (ConfigMaster) populateMockMaster(mock(ConfigMaster.class));
    AbstractEHCachingMaster<ConfigDocument> cachingMaster = new EHCachingConfigMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

}
