/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.security.impl;

import com.opengamma.DataNotFoundException;
import com.opengamma.core.change.BasicChangeManager;
import com.opengamma.core.change.ChangeManager;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.ObjectId;
import com.opengamma.id.ObjectIdSupplier;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.master.AbstractChangeProvidingMaster;
import com.opengamma.master.AbstractDocument;
import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.AbstractEHCachingMasterTest;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.SecuritySearchResult;
import net.sf.ehcache.CacheManager;
import org.mockito.ArgumentMatcher;
import org.testng.annotations.Test;

import javax.time.Instant;
import javax.time.calendar.TimeZone;
import javax.time.calendar.ZonedDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.*;

/**
 * Test {@link com.opengamma.master.security.impl.EHCachingSecurityMaster}
 */
@Test
public class EHCachingSecurityMasterTest extends AbstractEHCachingMasterTest<SecurityMaster, SecurityDocument> {

  {
    // Initialise security documents

    // Document A
    docA100_V1999to2010_Cto2011 = new SecurityDocument(
        new ManageableSecurity(A100_UID, "A", "test", ExternalIdBundle.EMPTY));
    docA200_V2010to = new SecurityDocument(
        new ManageableSecurity(A200_UID, "A", "test", ExternalIdBundle.EMPTY));
    docA300_V1999to2010_C2011to = new SecurityDocument(
        new ManageableSecurity(A300_UID, "A", "test", ExternalIdBundle.EMPTY));

    // Document B
    docB200_V2000to2009 = new SecurityDocument(
        new ManageableSecurity(B200_UID, "B", "test", ExternalIdBundle.EMPTY));
    docB400_V2009to2011 = new SecurityDocument(
        new ManageableSecurity(B400_UID, "B", "test", ExternalIdBundle.EMPTY));
    docB500_V2011to = new SecurityDocument(
        new ManageableSecurity(B500_UID, "B", "test", ExternalIdBundle.EMPTY));

    // Document C
    docC100_Vto2011 = new SecurityDocument(
        new ManageableSecurity(C100_UID, "C", "test", ExternalIdBundle.EMPTY));
    docC300_V2011to = new SecurityDocument(
        new ManageableSecurity(C300_UID, "C", "test", ExternalIdBundle.EMPTY));

    // Document to add
    DOC_TO_ADD = new SecurityDocument(
      new ManageableSecurity(null, "ADD", "test", ExternalIdBundle.EMPTY));
    DOC_ADDED = new SecurityDocument(
      new ManageableSecurity(ADDED_UID, "ADD", "test", ExternalIdBundle.EMPTY));
   }

  @Test
  void testSearch() {
    SecurityMaster mockUnderlyingMaster = (SecurityMaster) populateMockMaster(mock(SecurityMaster.class));
    EHCachingSecurityMaster cachingMaster = new EHCachingSecurityMaster(mockUnderlyingMaster, getCleanCacheManager());
    
    //TODO
    SecuritySearchRequest request = new SecuritySearchRequest();
    SecuritySearchResult result = cachingMaster.search(request);

    assert result.getDocuments().size() == 3;
    
    cachingMaster.shutdown();
  }

  @Test
  void testHistorySearch() {
    SecurityMaster mockUnderlyingMaster = (SecurityMaster) populateMockMaster(mock(SecurityMaster.class));
    AbstractEHCachingMaster<SecurityDocument> cachingMaster = new EHCachingSecurityMaster(mockUnderlyingMaster, getCleanCacheManager());


    cachingMaster.shutdown();
  }

  @Test
  void testMetaDataSearch() {
    SecurityMaster mockUnderlyingMaster = (SecurityMaster) populateMockMaster(mock(SecurityMaster.class));
    AbstractEHCachingMaster<SecurityDocument> cachingMaster = new EHCachingSecurityMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

}
