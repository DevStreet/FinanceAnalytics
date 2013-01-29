/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.AssertJUnit.assertEquals;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.core.change.ChangeManager;
import com.opengamma.id.ObjectIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.id.VersionCorrection;

import net.sf.ehcache.CacheManager;

/**
 * Test EHCaching master behaviour that is common across all masters, using dummy TestDocument and TestMaster classes
 */
public class EHCachingMasterTest extends AbstractEHCachingMasterTest<EHCachingMasterTest.TestMaster, EHCachingMasterTest.TestDocument> {

  {
    // Initialise security documents

    // Document A
    docA100_V1999to2010_Cto2011 = new TestDocument(A100_UID);
    docA200_V2010to = new TestDocument(A200_UID);
    docA300_V1999to2010_C2011to = new TestDocument(A300_UID);

    // Document B
    docB200_V2000to2009 = new TestDocument(B200_UID);
    docB400_V2009to2011 = new TestDocument(B400_UID);
    docB500_V2011to = new TestDocument(B500_UID);

    // Document C
    docC100_Vto2011 = new TestDocument(C100_UID);
    docC300_V2011to = new TestDocument(C300_UID);

    // Document to add
    DOC_TO_ADD = new TestDocument(null);
    DOC_ADDED = new TestDocument(ADDED_UID);
  }

  class TestDocument extends AbstractDocument {

    UniqueId _uniqueId;

    TestDocument(UniqueId uniqueId) {
      _uniqueId = uniqueId;
    }

    @Override
    public UniqueIdentifiable getValue() {
      return _uniqueId;
    }

    @Override
    public UniqueId getUniqueId() {
      return _uniqueId;
    }

    @Override
    public void setUniqueId(UniqueId uniqueId) {
      _uniqueId = uniqueId;
    }
  }

  class TestMaster implements AbstractChangeProvidingMaster<TestDocument> {

    @Override
    public TestDocument get(UniqueId uniqueId) {
      return null;  // TODO
    }

    @Override
    public TestDocument get(ObjectIdentifiable objectId, VersionCorrection versionCorrection) {
      return null;  // TODO
    }

    @Override
    public Map<UniqueId, TestDocument> get(Collection<UniqueId> uniqueIds) {
      return null;  // TODO
    }

    @Override
    public TestDocument add(TestDocument document) {
      return null;  // TODO
    }

    @Override
    public TestDocument update(TestDocument document) {
      return null;  // TODO
    }

    @Override
    public void remove(ObjectIdentifiable oid) {
      // TODO
    }

    @Override
    public TestDocument correct(TestDocument document) {
      return null;  // TODO
    }

    @Override
    public List<UniqueId> replaceVersion(UniqueId uniqueId, List<TestDocument> replacementDocuments) {
      return null;  // TODO
    }

    @Override
    public List<UniqueId> replaceAllVersions(ObjectIdentifiable objectId, List<TestDocument> replacementDocuments) {
      return null;  // TODO
    }

    @Override
    public List<UniqueId> replaceVersions(ObjectIdentifiable objectId, List<TestDocument> replacementDocuments) {
      return null;  // TODO
    }

    @Override
    public UniqueId replaceVersion(TestDocument replacementDocument) {
      return null;  // TODO
    }

    @Override
    public void removeVersion(UniqueId uniqueId) {
      // TODO
    }

    @Override
    public UniqueId addVersion(ObjectIdentifiable objectId, TestDocument documentToAdd) {
      return null;  // TODO
    }

    @Override
    public ChangeManager changeManager() {
      return null;  // TODO
    }
  }

  class EHCachingTestMaster extends AbstractEHCachingMaster<TestDocument> {
    public EHCachingTestMaster(AbstractChangeProvidingMaster<TestDocument> underlying, CacheManager cacheManager) {
      super(underlying, cacheManager);
    }
  }

  //-------------------------------------------------------------------------

  @Test
  public void testGetUidVersioned() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    // Assert returned documents
    assertEquals(docB200_V2000to2009, cachingMaster.get(docB200_V2000to2009.getUniqueId()));
    assertEquals(docA100_V1999to2010_Cto2011, cachingMaster.get(docA100_V1999to2010_Cto2011.getUniqueId()));
    assertEquals(docA100_V1999to2010_Cto2011, cachingMaster.get(docA100_V1999to2010_Cto2011.getUniqueId()));
    assertEquals(docA100_V1999to2010_Cto2011, cachingMaster.get(docA100_V1999to2010_Cto2011.getUniqueId()));
    assertEquals(docB200_V2000to2009, cachingMaster.get(docB200_V2000to2009.getUniqueId()));
    assertEquals(docB200_V2000to2009, cachingMaster.get(docB200_V2000to2009.getUniqueId()));
    assertEquals(docA100_V1999to2010_Cto2011, cachingMaster.get(docA100_V1999to2010_Cto2011.getUniqueId()));
    assertEquals(docB200_V2000to2009, cachingMaster.get(docB200_V2000to2009.getUniqueId()));

    // Assert invocation counts
    verify(mockUnderlyingMaster, times(1)).get(docA100_V1999to2010_Cto2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docA200_V2010to.getUniqueId());
    verify(mockUnderlyingMaster, times(1)).get(docB200_V2000to2009.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB400_V2009to2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB500_V2011to.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC100_Vto2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC300_V2011to.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docA200_V2010to.getObjectId(), VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(docB500_V2011to.getObjectId(), VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(docC300_V2011to.getObjectId(), VersionCorrection.LATEST);

    cachingMaster.shutdown();
  }

  @Test
  public void testGetUidUnversioned() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    // Assert returned documents
    assertEquals(docB500_V2011to, cachingMaster.get(docB200_V2000to2009.getUniqueId().toLatest()));
    assertEquals(docA200_V2010to, cachingMaster.get(docA100_V1999to2010_Cto2011.getUniqueId().toLatest()));
    assertEquals(docA200_V2010to, cachingMaster.get(docA100_V1999to2010_Cto2011.getUniqueId().toLatest()));
    assertEquals(docA200_V2010to, cachingMaster.get(docA100_V1999to2010_Cto2011.getUniqueId().toLatest()));
    assertEquals(docB500_V2011to, cachingMaster.get(docB200_V2000to2009.getUniqueId().toLatest()));
    assertEquals(docB500_V2011to, cachingMaster.get(docB200_V2000to2009.getUniqueId().toLatest()));
    assertEquals(docA200_V2010to, cachingMaster.get(docA100_V1999to2010_Cto2011.getUniqueId().toLatest()));
    assertEquals(docB500_V2011to, cachingMaster.get(docB200_V2000to2009.getUniqueId().toLatest()));

    // Assert invocation counts
    verify(mockUnderlyingMaster, times(1)).get(A_OID, VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(1)).get(B_OID, VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(C_OID, VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(docA100_V1999to2010_Cto2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docA200_V2010to.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB200_V2000to2009.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB400_V2009to2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB500_V2011to.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC100_Vto2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC300_V2011to.getUniqueId());

    cachingMaster.shutdown();
  }

  @Test
  public void testGetOidLatestVersionCorrection() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    // Assert returned documents
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID, VersionCorrection.LATEST));
    assertEquals(docA200_V2010to, cachingMaster.get(A_OID, VersionCorrection.LATEST));
    assertEquals(docA200_V2010to, cachingMaster.get(A_OID, VersionCorrection.LATEST));
    assertEquals(docA200_V2010to, cachingMaster.get(A_OID, VersionCorrection.LATEST));
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID, VersionCorrection.LATEST));
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID, VersionCorrection.LATEST));
    assertEquals(docA200_V2010to, cachingMaster.get(A_OID, VersionCorrection.LATEST));
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID, VersionCorrection.LATEST));

    // Assert invocation counts
    verify(mockUnderlyingMaster, times(1)).get(A_OID, VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(1)).get(B_OID, VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(C_OID, VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(docA100_V1999to2010_Cto2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docA200_V2010to.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB200_V2000to2009.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB400_V2009to2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB500_V2011to.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC100_Vto2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC300_V2011to.getUniqueId());

    cachingMaster.shutdown();
  }

  @Test
  public void testGetOidMixedVersionCorrection() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO enhance testing of v/c range border cases

    // Assert returned documents
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID, VersionCorrection.LATEST));
    assertEquals(docA100_V1999to2010_Cto2011, cachingMaster.get(A_OID,
        VersionCorrection.of(ZonedDateTime.of(LocalDateTime.of(2009, 1, 1, 12, 0, 0, 0), ZoneOffset.UTC).toInstant(),
                             ZonedDateTime.of(LocalDateTime.of(2010, 1, 1, 12, 0, 0, 0), ZoneOffset.UTC).toInstant())));
    assertEquals(docA200_V2010to, cachingMaster.get(A_OID, VersionCorrection.LATEST));
    assertEquals(docA300_V1999to2010_C2011to, cachingMaster.get(A_OID,
        VersionCorrection.of(ZonedDateTime.of(LocalDateTime.of(2009, 6, 6, 12, 0, 0, 0), ZoneOffset.UTC).toInstant(), now)));
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID, VersionCorrection.of(now, now)));
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID, VersionCorrection.LATEST));
    assertEquals(docA200_V2010to, cachingMaster.get(A_OID, VersionCorrection.LATEST));
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID,
        VersionCorrection.of(ZonedDateTime.of(LocalDateTime.of(2011, 6, 6, 12, 0, 0, 0), ZoneOffset.UTC).toInstant(), now)));

    // Assert invocation counts
    verify(mockUnderlyingMaster, times(1)).get(B_OID, VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(1)).get(A_OID,
        VersionCorrection.of(ZonedDateTime.of(LocalDateTime.of(2009, 1, 1, 12, 0, 0, 0), ZoneOffset.UTC).toInstant(),
                             ZonedDateTime.of(LocalDateTime.of(2010, 1, 1, 12, 0, 0, 0), ZoneOffset.UTC).toInstant()));
    verify(mockUnderlyingMaster, times(1)).get(A_OID, VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(1)).get(A_OID,
        VersionCorrection.of(ZonedDateTime.of(LocalDateTime.of(2009, 6, 6, 12, 0, 0, 0), ZoneOffset.UTC).toInstant(), now));
    verify(mockUnderlyingMaster, times(0)).get(B_OID, VersionCorrection.of(now, now));
    verify(mockUnderlyingMaster, times(0)).get(B_OID,
        VersionCorrection.of(ZonedDateTime.of(LocalDateTime.of(2011, 6, 6, 12, 0, 0, 0), ZoneOffset.UTC).toInstant(), now));
    verify(mockUnderlyingMaster, times(0)).get(C_OID, VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(0)).get(docA100_V1999to2010_Cto2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docA200_V2010to.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docA300_V1999to2010_C2011to.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB200_V2000to2009.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB400_V2009to2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docB500_V2011to.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC100_Vto2011.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(docC300_V2011to.getUniqueId());

    cachingMaster.shutdown();
  }

  @Test
  public void testCachedMiss() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  //-------------------------------------------------------------------------

  @Test
  public void testUpdate() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  public void testAdd() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    // Assert returned documents
    assertEquals(DOC_ADDED, cachingMaster.add(DOC_TO_ADD));

    // Assert cache contents
    assertEquals(DOC_ADDED, cachingMaster.get(DOC_ADDED.getUniqueId()));
    assertEquals(DOC_ADDED, cachingMaster.get(DOC_ADDED.getObjectId(), VersionCorrection.LATEST));
    assertEquals(DOC_ADDED, cachingMaster.get(DOC_ADDED.getObjectId(), VersionCorrection.of(now, now)));

    // Assert invocation counts
    verify(mockUnderlyingMaster, times(1)).add(DOC_TO_ADD);
    verify(mockUnderlyingMaster, times(0)).add(DOC_ADDED);
    verify(mockUnderlyingMaster, times(0)).get(DOC_ADDED.getUniqueId());
    verify(mockUnderlyingMaster, times(0)).get(DOC_TO_ADD.getUniqueId());

    cachingMaster.shutdown();
  }

  @Test
  public void testRemove() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  public void testCorrect() { // same as replaceVersion()
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  public void testReplaceVersion() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  public void testReplaceAllVersions() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  public void testReplaceVersions() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  public void testRemoveVersion() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  public void testAddVersion() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

  @Test
  public void testChangeProvider() {
    TestMaster mockUnderlyingMaster = (TestMaster) populateMockMaster(mock(TestMaster.class));
    AbstractEHCachingMaster<TestDocument> cachingMaster = new EHCachingTestMaster(mockUnderlyingMaster, getCleanCacheManager());

    //TODO

    cachingMaster.shutdown();
  }

}
