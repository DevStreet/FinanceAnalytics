/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.security.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.testng.annotations.Test;

import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.VersionCorrection;
import com.opengamma.master.cache.AbstractEHCachingMaster;
import com.opengamma.master.cache.AbstractEHCachingMasterTest;
import com.opengamma.master.cache.EHCachingPagedSearchCache;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.SecuritySearchResult;
import com.opengamma.master.security.SecuritySearchSortOrder;
import com.opengamma.util.paging.PagingRequest;

/**
 * Test {@link com.opengamma.master.security.impl.EHCachingSecurityMaster}
 */
@Test
public class EHCachingSecurityMasterTest extends AbstractEHCachingMasterTest<SecurityMaster, SecurityDocument> {

  {
    // Initialise security documents

    // Document A
    docA100_V1999to2010_Cto2011 = new SecurityDocument(
        new ManageableSecurity(A100_UID, "A", "MANAGEABLE", ExternalIdBundle.EMPTY));
    docA200_V2010to = new SecurityDocument(
        new ManageableSecurity(A200_UID, "A", "MANAGEABLE", ExternalIdBundle.EMPTY));
    docA300_V1999to2010_C2011to = new SecurityDocument(
        new ManageableSecurity(A300_UID, "A", "MANAGEABLE", ExternalIdBundle.EMPTY));

    // Document B
    docB200_V2000to2009 = new SecurityDocument(
        new ManageableSecurity(B200_UID, "B", "MANAGEABLE", ExternalIdBundle.EMPTY));
    docB400_V2009to2011 = new SecurityDocument(
        new ManageableSecurity(B400_UID, "B", "MANAGEABLE", ExternalIdBundle.EMPTY));
    docB500_V2011to = new SecurityDocument(
        new ManageableSecurity(B500_UID, "B", "MANAGEABLE", ExternalIdBundle.EMPTY));

    // Document C
    docC100_Vto2011 = new SecurityDocument(
        new ManageableSecurity(C100_UID, "C", "MANAGEABLE", ExternalIdBundle.EMPTY));
    docC300_V2011to = new SecurityDocument(
        new ManageableSecurity(C300_UID, "C", "MANAGEABLE", ExternalIdBundle.EMPTY));

    // Document to add
    DOC_TO_ADD = new SecurityDocument(
      new ManageableSecurity(null, "ADD", "MANAGEABLE", ExternalIdBundle.EMPTY));
    DOC_ADDED = new SecurityDocument(
      new ManageableSecurity(ADDED_UID, "ADD", "MANAGEABLE", ExternalIdBundle.EMPTY));
   }

  @Test
  void testSearch() {
    SecurityMaster mockUnderlyingMaster = (SecurityMaster) populateMockMaster(mock(SecurityMaster.class));
    EHCachingSecurityMaster cachingMaster = new EHCachingSecurityMaster("security", mockUnderlyingMaster, getCleanCacheManager());

    // Configure underlying mock search response
    SecuritySearchRequest securitySearchRequest = new SecuritySearchRequest();
    securitySearchRequest.setSortOrder(SecuritySearchSortOrder.OBJECT_ID_ASC);
    securitySearchRequest.setVersionCorrection(VersionCorrection.LATEST);

    Collection<SecurityDocument> resultDocs = new ArrayList<>();
    resultDocs.add(docA200_V2010to); resultDocs.add(docB500_V2011to); resultDocs.add(docC300_V2011to);
    SecuritySearchResult securitySearchResult = new SecuritySearchResult(resultDocs);
    when(mockUnderlyingMaster.search(new SecuritySearchRequest())).thenReturn(securitySearchResult);

    SecuritySearchResult result = cachingMaster.search(securitySearchRequest);
    SecuritySearchResult result1 = cachingMaster.search(securitySearchRequest);

    SecuritySearchRequest securitySearchRequest2 = new SecuritySearchRequest();
    securitySearchRequest2.setPagingRequest(PagingRequest.ofIndex(1, 1));
    SecuritySearchResult result2 = cachingMaster.search(securitySearchRequest2);

    SecuritySearchRequest securitySearchRequest3 = new SecuritySearchRequest();
    securitySearchRequest3.setPagingRequest(PagingRequest.ofIndex(1, 2));
    SecuritySearchResult result3 = cachingMaster.search(securitySearchRequest3);

    SecuritySearchRequest securitySearchRequest4 = new SecuritySearchRequest();
    securitySearchRequest4.setPagingRequest(PagingRequest.ofIndex(0, 2));
    SecuritySearchResult result4 = cachingMaster.search(securitySearchRequest4);

    // Assert returned documents
//    assertEquals(result, result1);
    assertEquals(result.getDocuments(), resultDocs);
    assertEquals(result1.getDocuments(), resultDocs);
    assertEquals(result2.getDocuments().size(), 1);
    assertEquals(result2.getDocuments().get(0), docB500_V2011to);
    assertEquals(result3.getDocuments().size(), 2);
    assertEquals(result3.getDocuments().get(0), docB500_V2011to);
    assertEquals(result3.getDocuments().get(1), docC300_V2011to);
    assertEquals(result4.getDocuments().size(), 2);
    assertEquals(result4.getDocuments().get(0), docA200_V2010to);
    assertEquals(result4.getDocuments().get(1), docB500_V2011to);

    // Assert invocation counts
    verify(mockUnderlyingMaster, times(0)).get(docA100_V1999to2010_Cto2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB200_V2000to2009.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB400_V2009to2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC100_Vto2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docA200_V2010to.getObjectId(), VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(docB500_V2011to.getObjectId(), VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(docC300_V2011to.getObjectId(), VersionCorrection.LATEST);

    // Switch off invocation counts for the following if run-time checks are enabled
    if (!AbstractEHCachingMaster.TEST_AGAINST_UNDERLYING) {
      verify(mockUnderlyingMaster, times(0)).get(docA200_V2010to.getUniqueId());
      verify(mockUnderlyingMaster, times(0)).get(docB500_V2011to.getUniqueId());
      verify(mockUnderlyingMaster, times(0)).get(docC300_V2011to.getUniqueId());
    }
    if (!EHCachingPagedSearchCache.TEST_AGAINST_UNDERLYING) {
      verify(mockUnderlyingMaster, times(1)).search(securitySearchRequest);
      verify(mockUnderlyingMaster, times(0)).search(securitySearchRequest2);
      verify(mockUnderlyingMaster, times(0)).search(securitySearchRequest3);
      verify(mockUnderlyingMaster, times(0)).search(securitySearchRequest4);
    }

    cachingMaster.shutdown();
  }

  @Test
  void testHistorySearch() {
    SecurityMaster mockUnderlyingMaster = (SecurityMaster) populateMockMaster(mock(SecurityMaster.class));
    AbstractEHCachingMaster<SecurityDocument> cachingMaster = new EHCachingSecurityMaster("security", mockUnderlyingMaster, getCleanCacheManager());


    cachingMaster.shutdown();
  }

  @Test
  void testMetaDataSearch() {
    SecurityMaster mockUnderlyingMaster = (SecurityMaster) populateMockMaster(mock(SecurityMaster.class));
    AbstractEHCachingMaster<SecurityDocument> cachingMaster = new EHCachingSecurityMaster("security", mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

}
