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
import com.opengamma.id.ObjectIdSupplier;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.SecuritySearchResult;
import net.sf.ehcache.CacheManager;
import org.testng.annotations.Test;


import javax.time.calendar.TimeZone;
import javax.time.calendar.ZonedDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Test {@link EHCachingSecurityMaster} using an underlying {@link InMemorySecurityMaster}.
 */
@Test
public class EHCachingSecurityMasterTest {

  // TODO clean up and move common properties/methods into AbstractEHCachingMasterTest
  // TODO in-depth testing of versions and corrections

  private static final SecurityDocument docA100 = new SecurityDocument(
      new ManageableSecurity(UniqueId.of("test", "first", "100"), "first", "test", ExternalIdBundle.EMPTY));
  private static final SecurityDocument docA200 = new SecurityDocument(
      new ManageableSecurity(UniqueId.of("test", "first", "200"), "first", "test", ExternalIdBundle.EMPTY));
  private static final SecurityDocument docB200 = new SecurityDocument(
      new ManageableSecurity(UniqueId.of("test", "second", "200"), "second", "test", ExternalIdBundle.EMPTY));
  private static final SecurityDocument docB400 = new SecurityDocument(
      new ManageableSecurity(UniqueId.of("test", "second", "400"), "second", "test", ExternalIdBundle.EMPTY));
  private static final SecurityDocument docB500 = new SecurityDocument(
      new ManageableSecurity(UniqueId.of("test", "second", "500"), "second", "test", ExternalIdBundle.EMPTY));
  private static final SecurityDocument docC100 = new SecurityDocument(
      new ManageableSecurity(UniqueId.of("test", "third", "100"), "third", "test", ExternalIdBundle.EMPTY));
  private static final SecurityDocument docC300 = new SecurityDocument(
      new ManageableSecurity(UniqueId.of("test", "third", "300"), "third", "test", ExternalIdBundle.EMPTY));

  static {
    docA100.setVersionToInstant(ZonedDateTime.of(2010, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docA200.setVersionFromInstant(ZonedDateTime.of(2010, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docB200.setVersionToInstant(ZonedDateTime.of(2009, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docB400.setVersionFromInstant(ZonedDateTime.of(2009, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docB400.setVersionToInstant(ZonedDateTime.of(2011, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docB500.setVersionFromInstant(ZonedDateTime.of(2011, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docC100.setVersionToInstant(ZonedDateTime.of(2011, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docC300.setVersionFromInstant(ZonedDateTime.of(2011, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
  }

  private SecurityMaster populateMockMaster() {
    SecurityMaster mockUnderlyingMaster = mock(SecurityMaster.class);
    reset(mockUnderlyingMaster);

    ChangeManager changeManager = new BasicChangeManager();
    when(mockUnderlyingMaster.changeManager()).thenReturn(changeManager);

    when(mockUnderlyingMaster.get(docA100.getUniqueId())).thenReturn(docA100);
    when(mockUnderlyingMaster.get(docA200.getUniqueId())).thenReturn(docA200);
    when(mockUnderlyingMaster.get(docB200.getUniqueId())).thenReturn(docB200);
    when(mockUnderlyingMaster.get(docB400.getUniqueId())).thenReturn(docB400);
    when(mockUnderlyingMaster.get(docB500.getUniqueId())).thenReturn(docB500);
    when(mockUnderlyingMaster.get(docC100.getUniqueId())).thenReturn(docC100);
    when(mockUnderlyingMaster.get(docC300.getUniqueId())).thenReturn(docC300);

    when(mockUnderlyingMaster.get(docA100.getUniqueId().toLatest())).thenReturn(docA200);
    when(mockUnderlyingMaster.get(docB200.getUniqueId().toLatest())).thenReturn(docB500);
    when(mockUnderlyingMaster.get(docC100.getUniqueId().toLatest())).thenReturn(docC300);

    when(mockUnderlyingMaster.get(docA100.getObjectId(), VersionCorrection.LATEST)).thenReturn(docA200);
    when(mockUnderlyingMaster.get(docB200.getObjectId(), VersionCorrection.LATEST)).thenReturn(docB500);
    when(mockUnderlyingMaster.get(docC100.getObjectId(), VersionCorrection.LATEST)).thenReturn(docC300);

    return mockUnderlyingMaster;
  }

  public void test_get_hit_miss_uid_latest() {
    SecurityMaster mockUnderlyingMaster = populateMockMaster();
    EHCachingSecurityMaster cachingMaster = new EHCachingSecurityMaster(mockUnderlyingMaster, CacheManager.getInstance());
    assertEquals(docB500, cachingMaster.get(docB200.getUniqueId().toLatest()));
    assertEquals(docA200, cachingMaster.get(docA100.getUniqueId().toLatest()));
    assertEquals(docA200, cachingMaster.get(docA100.getUniqueId().toLatest()));
    assertEquals(docA200, cachingMaster.get(docA100.getUniqueId().toLatest()));
    assertEquals(docB500, cachingMaster.get(docB200.getUniqueId().toLatest()));
    assertEquals(docB500, cachingMaster.get(docB200.getUniqueId().toLatest()));
    assertEquals(docA200, cachingMaster.get(docA100.getUniqueId().toLatest()));
    assertEquals(docB500, cachingMaster.get(docB200.getUniqueId().toLatest()));
    verify(mockUnderlyingMaster, times(1)).get(docA200.getObjectId(), VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(1)).get(docB500.getObjectId(), VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(docC300.getObjectId(), VersionCorrection.LATEST);

    verify(mockUnderlyingMaster, times(0)).get(docA100.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docA200.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB200.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB400.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB500.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC100.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC300.getUniqueId());

    cachingMaster.shutdown();
  }

  public void test_get_hit_miss_uid_versioned() {
    SecurityMaster mockUnderlyingMaster = populateMockMaster();
    EHCachingSecurityMaster cachingMaster = new EHCachingSecurityMaster(mockUnderlyingMaster, CacheManager.getInstance());
    assertEquals(docB200, cachingMaster.get(docB200.getUniqueId()));
    assertEquals(docA100, cachingMaster.get(docA100.getUniqueId()));
    assertEquals(docA100, cachingMaster.get(docA100.getUniqueId()));
    assertEquals(docA100, cachingMaster.get(docA100.getUniqueId()));
    assertEquals(docB200, cachingMaster.get(docB200.getUniqueId()));
    assertEquals(docB200, cachingMaster.get(docB200.getUniqueId()));
    assertEquals(docA100, cachingMaster.get(docA100.getUniqueId()));
    assertEquals(docB200, cachingMaster.get(docB200.getUniqueId()));
    verify(mockUnderlyingMaster, times(1)).get(docA100.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docA200.getUniqueId());
    verify(mockUnderlyingMaster, times(1)).get(docB200.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB400.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB500.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC100.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC300.getUniqueId());

    verify(mockUnderlyingMaster, times(0)).get(docA200.getObjectId(), VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(docB500.getObjectId(), VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(docC300.getObjectId(), VersionCorrection.LATEST);

    cachingMaster.shutdown();
  }

  public void test_get_hit_miss_oid_latest() {
    SecurityMaster mockUnderlyingMaster = populateMockMaster();
    EHCachingSecurityMaster cachingMaster = new EHCachingSecurityMaster(mockUnderlyingMaster, CacheManager.getInstance());
    assertEquals(docB500, cachingMaster.get(docB200.getObjectId(), VersionCorrection.LATEST));
    assertEquals(docA200, cachingMaster.get(docA100.getObjectId(), VersionCorrection.LATEST));
    assertEquals(docA200, cachingMaster.get(docA100.getObjectId(), VersionCorrection.LATEST));
    assertEquals(docA200, cachingMaster.get(docA100.getObjectId(), VersionCorrection.LATEST));
    assertEquals(docB500, cachingMaster.get(docB200.getObjectId(), VersionCorrection.LATEST));
    assertEquals(docB500, cachingMaster.get(docB200.getObjectId(), VersionCorrection.LATEST));
    assertEquals(docA200, cachingMaster.get(docA100.getObjectId(), VersionCorrection.LATEST));
    assertEquals(docB500, cachingMaster.get(docB200.getObjectId(), VersionCorrection.LATEST));
    verify(mockUnderlyingMaster, times(1)).get(docA200.getObjectId(), VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(1)).get(docB500.getObjectId(), VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(docC300.getObjectId(), VersionCorrection.LATEST);

    verify(mockUnderlyingMaster, times(0)).get(docA100.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docA200.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB200.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB400.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB500.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC100.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC300.getUniqueId());

    cachingMaster.shutdown();
  }


  ////////////////////////////////// taken from InMemory tests for now //////////////////////////////////

  private static final UniqueId OTHER_UID = UniqueId.of("U", "1");
  private static final ExternalId ID1 = ExternalId.of("A", "B");
  private static final ExternalId ID2 = ExternalId.of("A", "C");
  private static final ExternalIdBundle BUNDLE1 = ExternalIdBundle.of(ID1);
  private static final ExternalIdBundle BUNDLE2 = ExternalIdBundle.of(ID2);
  private static final ManageableSecurity SEC1 = new ManageableSecurity(UniqueId.of("Test", "sec1"), "Test 1", "TYPE1", BUNDLE1);
  private static final ManageableSecurity SEC2 = new ManageableSecurity(UniqueId.of("Test", "sec2"), "Test 2", "TYPE2", BUNDLE2);

  private SecurityDocument doc1;
  private SecurityDocument doc2;

  public EHCachingSecurityMaster populateInMemoryMaster() {
    EHCachingSecurityMaster testPopulated =
        new EHCachingSecurityMaster(new InMemorySecurityMaster(new ObjectIdSupplier("Test")), CacheManager.getInstance());
    doc1 = new SecurityDocument();
    doc1.setSecurity(SEC1);
    doc1 = testPopulated.add(doc1);
    doc2 = new SecurityDocument();
    doc2.setSecurity(SEC2);
    doc2 = testPopulated.add(doc2);
    return testPopulated;
  }

  //-------------------------------------------------------------------------
  public void test_search_emptyMaster() {
    EHCachingSecurityMaster testEmpty =
        new EHCachingSecurityMaster(new InMemorySecurityMaster(new ObjectIdSupplier("Test")), CacheManager.getInstance());
    SecuritySearchRequest request = new SecuritySearchRequest();
    SecuritySearchResult result = testEmpty.search(request);
    assertEquals(0, result.getPaging().getTotalItems());
    assertEquals(0, result.getDocuments().size());
  }

  public void test_search_populatedMaster_all() {
    EHCachingSecurityMaster testPopulated = populateInMemoryMaster();
    SecuritySearchRequest request = new SecuritySearchRequest();
    SecuritySearchResult result = testPopulated.search(request);
    assertEquals(2, result.getPaging().getTotalItems());
    List<SecurityDocument> docs = result.getDocuments();
    assertEquals(2, docs.size());
    assertEquals(true, docs.contains(doc1));
    assertEquals(true, docs.contains(doc2));
  }

  public void test_search_populatedMaster_filterByBundle() {
    EHCachingSecurityMaster testPopulated = populateInMemoryMaster();
    SecuritySearchRequest request = new SecuritySearchRequest(BUNDLE1);
    SecuritySearchResult result = testPopulated.search(request);
    assertEquals(1, result.getPaging().getTotalItems());
    assertEquals(1, result.getDocuments().size());
    assertEquals(true, result.getDocuments().contains(doc1));
  }

  public void test_search_populatedMaster_filterByBundle_both() {
    EHCachingSecurityMaster testPopulated = populateInMemoryMaster();
    SecuritySearchRequest request = new SecuritySearchRequest();
    request.addExternalIds(BUNDLE1);
    request.addExternalIds(BUNDLE2);
    SecuritySearchResult result = testPopulated.search(request);
    assertEquals(2, result.getPaging().getTotalItems());
    List<SecurityDocument> docs = result.getDocuments();
    assertEquals(2, docs.size());
    assertEquals(true, docs.contains(doc1));
    assertEquals(true, docs.contains(doc2));
  }

  public void test_search_populatedMaster_filterByName() {
    EHCachingSecurityMaster testPopulated = populateInMemoryMaster();
    SecuritySearchRequest request = new SecuritySearchRequest();
    request.setName("*est 2");
    SecuritySearchResult result = testPopulated.search(request);
    assertEquals(1, result.getPaging().getTotalItems());
    List<SecurityDocument> docs = result.getDocuments();
    assertEquals(1, docs.size());
    assertEquals(true, docs.contains(doc2));
  }

  public void test_search_populatedMaster_filterByType() {
    EHCachingSecurityMaster testPopulated = populateInMemoryMaster();
    SecuritySearchRequest request = new SecuritySearchRequest();
    request.setSecurityType("TYPE2");
    SecuritySearchResult result = testPopulated.search(request);
    assertEquals(1, result.getPaging().getTotalItems());
    List<SecurityDocument> docs = result.getDocuments();
    assertEquals(1, docs.size());
    assertEquals(true, docs.contains(doc2));
  }

  public void test_search_popluatedMaster_filterByExternalIdValue() {
    EHCachingSecurityMaster testPopulated = populateInMemoryMaster();
    SecuritySearchRequest request = new SecuritySearchRequest();
    request.setExternalIdValue("B");
    SecuritySearchResult result = testPopulated.search(request);
    assertEquals(1, result.getPaging().getTotalItems());
    List<SecurityDocument> docs = result.getDocuments();
    assertEquals(1, docs.size());
    assertEquals(true, docs.contains(doc1));
  }

  public void test_search_popluatedMaster_filterByExternalIdValue_case() {
    EHCachingSecurityMaster testPopulated = populateInMemoryMaster();
    SecuritySearchRequest request = new SecuritySearchRequest();
    request.setExternalIdValue("b");
    SecuritySearchResult result = testPopulated.search(request);
    assertEquals(1, result.getPaging().getTotalItems());
    List<SecurityDocument> docs = result.getDocuments();
    assertEquals(1, docs.size());
    assertEquals(true, docs.contains(doc1));
  }

  //-------------------------------------------------------------------------
  @Test(expectedExceptions = DataNotFoundException.class)
  public void test_get_emptyMaster() {
    EHCachingSecurityMaster testEmpty =
        new EHCachingSecurityMaster(new InMemorySecurityMaster(new ObjectIdSupplier("Test")), CacheManager.getInstance());
    assertNull(testEmpty.get(OTHER_UID));
  }

  public void test_get_populatedMaster() {
    EHCachingSecurityMaster testPopulated = populateInMemoryMaster();
    assertSame(doc1, testPopulated.get(doc1.getUniqueId()));
    assertSame(doc2, testPopulated.get(doc2.getUniqueId()));
  }

  //-------------------------------------------------------------------------
  public void test_add_emptyMaster() {
    EHCachingSecurityMaster testEmpty =
        new EHCachingSecurityMaster(new InMemorySecurityMaster(new ObjectIdSupplier("Test")), CacheManager.getInstance());
    SecurityDocument doc = new SecurityDocument();
    doc.setSecurity(SEC1);
    SecurityDocument added = testEmpty.add(doc);
    assertNotNull(added.getVersionFromInstant());
    assertNotNull(added.getCorrectionFromInstant());
    assertEquals(added.getVersionFromInstant(), added.getCorrectionFromInstant());
    assertEquals("Test", added.getUniqueId().getScheme());
    assertSame(SEC1, added.getSecurity());
  }

  //-------------------------------------------------------------------------
  @Test(expectedExceptions = DataNotFoundException.class)
  public void test_update_emptyMaster() {
    EHCachingSecurityMaster testEmpty =
        new EHCachingSecurityMaster(new InMemorySecurityMaster(new ObjectIdSupplier("Test")), CacheManager.getInstance());
    SecurityDocument doc = new SecurityDocument();
    doc.setSecurity(SEC1);
    doc.setUniqueId(OTHER_UID);
    testEmpty.update(doc);
  }

  public void test_update_populatedMaster() {
    EHCachingSecurityMaster testPopulated = populateInMemoryMaster();
    SecurityDocument doc = new SecurityDocument();
    doc.setSecurity(SEC1);
    doc.setUniqueId(doc1.getUniqueId());
    SecurityDocument updated = testPopulated.update(doc);
    assertEquals(doc1.getUniqueId(), updated.getUniqueId());
    assertNotNull(doc1.getVersionFromInstant());
    assertNotNull(updated.getVersionFromInstant());
  }

  @Test(expectedExceptions = DataNotFoundException.class)
  public void test_update_notFound() {
    EHCachingSecurityMaster testEmpty =
        new EHCachingSecurityMaster(new InMemorySecurityMaster(new ObjectIdSupplier("Test")), CacheManager.getInstance());
    UniqueId uniqueId = UniqueId.of("DbSec", "0", "0");
    ManageableSecurity security = new ManageableSecurity(uniqueId, "Name", "Type", ExternalIdBundle.of("A", "B"));
    SecurityDocument doc = new SecurityDocument(security);
    testEmpty.update(doc);
  }

  //-------------------------------------------------------------------------
  @Test(expectedExceptions = DataNotFoundException.class)
  public void test_remove_emptyMaster() {
    EHCachingSecurityMaster testEmpty =
        new EHCachingSecurityMaster(new InMemorySecurityMaster(new ObjectIdSupplier("Test")), CacheManager.getInstance());
    testEmpty.remove(OTHER_UID);
  }

  public void test_remove_populatedMaster() {
    EHCachingSecurityMaster testPopulated = populateInMemoryMaster();
    testPopulated.remove(doc1.getUniqueId());
    SecuritySearchRequest request = new SecuritySearchRequest();
    SecuritySearchResult result = testPopulated.search(request);
    assertEquals(1, result.getPaging().getTotalItems());
    List<SecurityDocument> docs = result.getDocuments();
    assertEquals(1, docs.size());
    assertEquals(true, docs.contains(doc2));
  }

}
