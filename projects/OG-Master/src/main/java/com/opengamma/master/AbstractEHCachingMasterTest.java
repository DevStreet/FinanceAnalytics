/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master;

import com.opengamma.DataNotFoundException;
import com.opengamma.core.change.BasicChangeManager;
import com.opengamma.core.change.ChangeManager;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.ObjectId;
import com.opengamma.id.ObjectIdSupplier;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.SecuritySearchResult;
import com.opengamma.master.security.impl.EHCachingSecurityMaster;
import com.opengamma.master.security.impl.InMemorySecurityMaster;
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
 * Test {@link com.opengamma.master.security.impl.EHCachingSecurityMaster} using an underlying {@link com.opengamma.master.security.impl.InMemorySecurityMaster}.
 */
public abstract class AbstractEHCachingMasterTest<M extends AbstractChangeProvidingMaster<D>, D extends AbstractDocument> {

  protected static final String ID_SCHEME = "Test";

  protected static final Instant now = Instant.now();

  // Document A (100, 200, 300)
  protected static final ObjectId A_OID = ObjectId.of(ID_SCHEME, "A");
  protected static final UniqueId A100_UID = UniqueId.of(A_OID, "100");
  protected static final UniqueId A200_UID = UniqueId.of(A_OID, "200");
  protected static final UniqueId A300_UID = UniqueId.of(A_OID, "300");  
  protected D docA100_V1999to2010_Cto2011;
  protected D docA200_V2010to;
  protected D docA300_V1999to2010_C2011to;

  // Document B (200, 400, 500)
  protected static final ObjectId B_OID = ObjectId.of(ID_SCHEME, "B");
  protected static final UniqueId B200_UID = UniqueId.of(B_OID, "200");
  protected static final UniqueId B400_UID = UniqueId.of(B_OID, "400");
  protected static final UniqueId B500_UID = UniqueId.of(B_OID, "500");
  protected D docB200_V2000to2009;
  protected D docB400_V2009to2011;
  protected D docB500_V2011to;

  // Document C (100, 300)
  protected static final ObjectId C_OID = ObjectId.of(ID_SCHEME, "C");
  protected static final UniqueId C100_UID = UniqueId.of(C_OID, "100");
  protected static final UniqueId C300_UID = UniqueId.of(C_OID, "300");
  protected D docC100_Vto2011;
  protected D docC300_V2011to;

  protected abstract M getCleanMockMaster();
  protected abstract AbstractEHCachingMaster<D> getCleanEHCachingMaster(M underlyingMaster, CacheManager cacheManager);

  /**
   * Creates a fresh mock master and configures it to respond as though it contains the above documents
   * @return the mock master
   */
  protected AbstractChangeProvidingMaster populateMockMaster(M mockUnderlyingMaster) {

    // Set up VersionFrom, VersionTo, CorrectionFrom, CorrectionTo

    // Document A 100: v 1999 to 2010, c to 2011
    docA100_V1999to2010_Cto2011.setVersionFromInstant(ZonedDateTime.of(1999, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docA100_V1999to2010_Cto2011.setVersionToInstant(ZonedDateTime.of(2010, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docA100_V1999to2010_Cto2011.setCorrectionToInstant(ZonedDateTime.of(2011, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());

    // Document A 200: v 2010 to
    docA200_V2010to.setVersionFromInstant(ZonedDateTime.of(2010, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());

    // Document A 300 (corrects A100): v 1999 to 2010, c 2011 to
    docA300_V1999to2010_C2011to.setVersionFromInstant(ZonedDateTime.of(1999, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docA300_V1999to2010_C2011to.setVersionToInstant(ZonedDateTime.of(2010, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docA300_V1999to2010_C2011to.setCorrectionFromInstant(ZonedDateTime.of(2011, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());

    // Document B 200: v 2000 to 2009
    docB200_V2000to2009.setVersionFromInstant(ZonedDateTime.of(2000, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docB200_V2000to2009.setVersionToInstant(ZonedDateTime.of(2009, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());

    // Document B 400: v 2009 to 2011
    docB400_V2009to2011.setVersionFromInstant(ZonedDateTime.of(2009, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());
    docB400_V2009to2011.setVersionToInstant(ZonedDateTime.of(2011, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());

    // Document B 500: v 2011 to
    docB500_V2011to.setVersionFromInstant(ZonedDateTime.of(2011, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());

    // Document C 100: v to 2011
    docC100_Vto2011.setVersionToInstant(ZonedDateTime.of(2011, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());

    // Document C 300: v 2011 to
    docC300_V2011to.setVersionFromInstant(ZonedDateTime.of(2011, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant());

    ChangeManager changeManager = new BasicChangeManager();
    when(mockUnderlyingMaster.changeManager()).thenReturn(changeManager);

    // Configure mock master to respond to versioned unique ID gets
    when(mockUnderlyingMaster.get(docA100_V1999to2010_Cto2011.getUniqueId())).thenReturn(docA100_V1999to2010_Cto2011);
    when(mockUnderlyingMaster.get(docA200_V2010to.getUniqueId())).thenReturn(docA200_V2010to);
    when(mockUnderlyingMaster.get(docA300_V1999to2010_C2011to.getUniqueId())).thenReturn(docA300_V1999to2010_C2011to);
    when(mockUnderlyingMaster.get(docB200_V2000to2009.getUniqueId())).thenReturn(docB200_V2000to2009);
    when(mockUnderlyingMaster.get(docB400_V2009to2011.getUniqueId())).thenReturn(docB400_V2009to2011);
    when(mockUnderlyingMaster.get(docB500_V2011to.getUniqueId())).thenReturn(docB500_V2011to);
    when(mockUnderlyingMaster.get(docC100_Vto2011.getUniqueId())).thenReturn(docC100_Vto2011);
    when(mockUnderlyingMaster.get(docC300_V2011to.getUniqueId())).thenReturn(docC300_V2011to);

    // Configure mock master to respond to unversioned unique ID gets (should return latest version)
    when(mockUnderlyingMaster.get(docA100_V1999to2010_Cto2011.getUniqueId().toLatest())).thenReturn(docA200_V2010to);
    when(mockUnderlyingMaster.get(docB200_V2000to2009.getUniqueId().toLatest())).thenReturn(docB500_V2011to);
    when(mockUnderlyingMaster.get(docC100_Vto2011.getUniqueId().toLatest())).thenReturn(docC300_V2011to);

    // Configure mock master to respond to object ID/Version-Correction gets
    when(mockUnderlyingMaster.get(
        eq(A_OID), argThat(new IsValidFor(docA100_V1999to2010_Cto2011)))).thenReturn(docA100_V1999to2010_Cto2011);
    when(mockUnderlyingMaster.get(
        eq(A_OID), argThat(new IsValidFor(docA200_V2010to)))).thenReturn(docA200_V2010to);
    when(mockUnderlyingMaster.get(
        eq(A_OID), argThat(new IsValidFor(docA300_V1999to2010_C2011to)))).thenReturn(docA300_V1999to2010_C2011to);
    when(mockUnderlyingMaster.get(
        eq(B_OID), argThat(new IsValidFor(docB200_V2000to2009)))).thenReturn(docB200_V2000to2009);
    when(mockUnderlyingMaster.get(
        eq(B_OID), argThat(new IsValidFor(docB400_V2009to2011)))).thenReturn(docB400_V2009to2011);
    when(mockUnderlyingMaster.get(
        eq(B_OID), argThat(new IsValidFor(docB500_V2011to)))).thenReturn(docB500_V2011to);
    when(mockUnderlyingMaster.get(
        eq(C_OID), argThat(new IsValidFor(docC100_Vto2011)))).thenReturn(docC100_Vto2011);
    when(mockUnderlyingMaster.get(
        eq(C_OID), argThat(new IsValidFor(docC300_V2011to)))).thenReturn(docC300_V2011to);

    return mockUnderlyingMaster;
  }

  /********************************************************************************************************************/

  @Test
  public void testGetUidVersioned() {
    M mockUnderlyingMaster = getCleanMockMaster();
    AbstractEHCachingMaster<D> cachingMaster = getCleanEHCachingMaster(mockUnderlyingMaster, getCleanCacheManager());

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
    M mockUnderlyingMaster = getCleanMockMaster();
    AbstractEHCachingMaster<D> cachingMaster = getCleanEHCachingMaster(mockUnderlyingMaster, getCleanCacheManager());

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
    M mockUnderlyingMaster = getCleanMockMaster();
    AbstractEHCachingMaster<D> cachingMaster = getCleanEHCachingMaster(mockUnderlyingMaster, getCleanCacheManager());

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
    M mockUnderlyingMaster = getCleanMockMaster();
    AbstractEHCachingMaster<D> cachingMaster = getCleanEHCachingMaster(mockUnderlyingMaster, getCleanCacheManager());

    // Assert returned documents
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID, VersionCorrection.LATEST));
    assertEquals(docA100_V1999to2010_Cto2011, cachingMaster.get(A_OID,
        VersionCorrection.of(ZonedDateTime.of(2009, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant(),
                             ZonedDateTime.of(2010, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant())));
    assertEquals(docA200_V2010to, cachingMaster.get(A_OID, VersionCorrection.LATEST));
    assertEquals(docA300_V1999to2010_C2011to, cachingMaster.get(A_OID,
        VersionCorrection.of(ZonedDateTime.of(2009, 6, 6, 12, 0, 0, 0, TimeZone.UTC).toInstant(), now)));
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID, VersionCorrection.of(now, now)));
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID, VersionCorrection.LATEST));
    assertEquals(docA200_V2010to, cachingMaster.get(A_OID, VersionCorrection.LATEST));
    assertEquals(docB500_V2011to, cachingMaster.get(B_OID,
        VersionCorrection.of(ZonedDateTime.of(2011, 6, 6, 12, 0, 0, 0, TimeZone.UTC).toInstant(), now)));

    // Assert invocation counts
    verify(mockUnderlyingMaster, times(1)).get(B_OID, VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(1)).get(A_OID,
        VersionCorrection.of(ZonedDateTime.of(2009, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant(),
                             ZonedDateTime.of(2010, 1, 1, 12, 0, 0, 0, TimeZone.UTC).toInstant()));
    verify(mockUnderlyingMaster, times(1)).get(A_OID, VersionCorrection.LATEST);
    verify(mockUnderlyingMaster, times(1)).get(A_OID,
        VersionCorrection.of(ZonedDateTime.of(2009, 6, 6, 12, 0, 0, 0, TimeZone.UTC).toInstant(), now));
    verify(mockUnderlyingMaster, times(0)).get(B_OID, VersionCorrection.of(now, now));
    verify(mockUnderlyingMaster, times(0)).get(B_OID,
        VersionCorrection.of(ZonedDateTime.of(2011, 6, 6, 12, 0, 0, 0, TimeZone.UTC).toInstant(), now));
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

  }

  /********************************************************************************************************************/

  @Test
  public void testUpdate() {

  }

  @Test
  public void testAdd() {

  }

  @Test
  public void testCorrect() {

  }

  @Test
  public void testChangeProvider() {

  }

  /********************************************************************************************************************/


  /**
   * Mockito argument matcher that checks whether a VersionCorrection is within a document's v/c range
   */
  class IsValidFor extends ArgumentMatcher<VersionCorrection> {
    private Instant _fromVersion, _fromCorrection;
    private Instant _toVersion, _toCorrection;

    public IsValidFor(AbstractDocument document) {
      _fromVersion = document.getVersionFromInstant();
      _toVersion = document.getVersionToInstant();
      _fromCorrection = document.getCorrectionFromInstant();
      _toCorrection = document.getCorrectionToInstant();
    }

    public boolean matches(Object o) {
      VersionCorrection vc = (VersionCorrection) o;
      return  (_fromVersion == null || vc.getVersionAsOf() == null || vc.getVersionAsOf().isAfter(_fromVersion)) &&
              (_toVersion == null || vc.getVersionAsOf() != null && vc.getVersionAsOf().isBefore(_toVersion)) &&
              (_fromCorrection == null || vc.getCorrectedTo() == null || vc.getCorrectedTo().isAfter(_fromCorrection)) &&
              (_toCorrection == null || vc.getCorrectedTo() != null && vc.getCorrectedTo().isBefore(_toCorrection));
    }
  }

  /**
   * Returns an empty cache manager
   * @return the cache manager
   */
  private CacheManager getCleanCacheManager() {
    CacheManager cacheManager = CacheManager.getInstance();
    cacheManager.clearAll();
    cacheManager.removalAll();
    return cacheManager;
  }

}
