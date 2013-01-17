/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master;

import com.opengamma.DataNotFoundException;
import com.opengamma.core.change.BasicChangeManager;
import com.opengamma.core.change.ChangeEvent;
import com.opengamma.core.change.ChangeListener;
import com.opengamma.core.change.ChangeManager;
import com.opengamma.id.ObjectId;
import com.opengamma.id.ObjectIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.ehcache.EHCacheUtils;
import com.opengamma.util.time.Expiry;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.time.Instant;
import javax.time.calendar.TimeZone;
import javax.time.calendar.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static com.google.common.collect.Maps.newHashMap;

/**
 * A cache decorating a master, mainly intended to reduce the frequency and repetition of queries from the management
 * UI to a database-backed master. In particular, prefetching is employed in paged queries, which tend to scale poorly.
 * <p>
 * The cache is implemented using {@code EHCache}.
 *
 * @param <D> the document type returned by the master
 */
public abstract class AbstractEHCachingMaster<D extends AbstractDocument> implements AbstractChangeProvidingMaster<D> {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(AbstractEHCachingMaster.class);
  /** The underlying cache. */
  private final AbstractChangeProvidingMaster<D> _underlying;
  /** The cache manager. */
  private final CacheManager _manager;
  /** Listens for changes in the underlying security source. */
  private final ChangeListener _changeListener;
  /** The local change manager. */
  private final ChangeManager _changeManager;
  /** The document cache indexed by ObjectId/version/correction. */
  private final Cache _documentByOidCache;
  /** The document cache indexed by UniqueId. */
  private final Cache _documentByUidCache;
  /** The document by oid cache's name. */
  private final String _documentByOidCacheName = getClass().getName() + "-documentByOidCache";
  /** The document by uid cache's name. */
  private final String _documentByUidCacheName = getClass().getName() + "-documentByUidCache";

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying source, not null
   * @param cacheManager  the cache manager, not null
   */
  public AbstractEHCachingMaster(final AbstractChangeProvidingMaster<D> underlying, final CacheManager cacheManager) {
    ArgumentChecker.notNull(underlying, "underlying");
    ArgumentChecker.notNull(cacheManager, "cacheManager");
    _underlying = underlying;
    EHCacheUtils.addCache(cacheManager, _documentByOidCacheName);
    _documentByOidCache = EHCacheUtils.getCacheFromManager(cacheManager, _documentByOidCacheName);
    EHCacheUtils.addCache(cacheManager, _documentByUidCacheName);
    _documentByUidCache = EHCacheUtils.getCacheFromManager(cacheManager, _documentByUidCacheName);
    _manager = cacheManager;
    _changeManager = new BasicChangeManager();
    _changeListener = new ChangeListener() {
      @Override
      public void entityChanged(ChangeEvent event) {
        final ObjectId oid = event.getObjectId();
        final Instant versionFrom = event.getVersionFrom();
        final Instant versionTo = event.getVersionTo();
        cleanCaches(oid, versionFrom, versionTo);
        _changeManager.entityChanged(event.getType(), event.getObjectId(),
            event.getVersionFrom(), event.getVersionTo(), event.getVersionInstant());
      }
    };
    underlying.changeManager().addChangeListener(_changeListener);
  }

  //-------------------------------------------------------------------------

  /**
   * Gets the underlying source of items.
   *
   * @return the underlying source of items, not null
   */
  protected AbstractChangeProvidingMaster<D> getUnderlying() {
    return _underlying;
  }

  /**
   * Gets the cache manager.
   *
   * @return the cache manager, not null
   */
  protected CacheManager getCacheManager() {
    return _manager;
  }

  /**
   * Gets the document by ObjectId cache.
   *
   * @return the cache, not null
   */
  protected Cache getDocumentByOidCache() {
    return _documentByOidCache;
  }

  /**
   * Gets the document by UniqueId cache.
   *
   * @return the cache, not null
   */
  protected Cache getDocumentByUidCache() {
    return _documentByUidCache;
  }

  /**
   * Gets the change manager.
   *
   * @return the change manager, not null
   */
  @Override
  public ChangeManager changeManager() {
    return _changeManager;
  }
  //-------------------------------------------------------------------------

  /**
   * Map from fromVersion instants to fromCorrection instants to documents
   */
  private class InstantMap {

    /** the version/correction/document map */
    private final NavigableMap<Instant, NavigableMap<Instant, D>> _fromVersionCorrectionInstantMap;

    public InstantMap() {
      _fromVersionCorrectionInstantMap = new TreeMap<>();
    }

    public InstantMap(NavigableMap<Instant, NavigableMap<Instant, D>> map) {
      _fromVersionCorrectionInstantMap = map;
    }

    public D get(VersionCorrection versionCorrection) {
      versionCorrection = versionCorrection.withLatestFixed(Instant.now());
      Instant fromVersionInstant = _fromVersionCorrectionInstantMap.floorKey(versionCorrection.getVersionAsOf());
      if (fromVersionInstant != null) {
        NavigableMap<Instant, D> fromCorrectionInstantMap = _fromVersionCorrectionInstantMap.get(fromVersionInstant);
        Instant fromCorrectionInstant = fromCorrectionInstantMap.floorKey(versionCorrection.getCorrectedTo());
        if (fromCorrectionInstant != null) {
          D document = fromCorrectionInstantMap.get(fromCorrectionInstant);
          if ((
               document.getVersionToInstant() == null ||
               document.getVersionToInstant().equals(versionCorrection.getVersionAsOf()) ||
               document.getVersionToInstant().isAfter(versionCorrection.getVersionAsOf())
             ) && (
               document.getCorrectionToInstant() == null ||
               document.getCorrectionToInstant().equals(versionCorrection.getCorrectedTo()) ||
               document.getCorrectionToInstant().isAfter(versionCorrection.getCorrectedTo())
             )) {
            return document;
          } // else one or both of the found version and correction expire too early
        } // else did not find a correction that's old enough
      } // else did not find a document that's old enough
      return null;
    }

    public InstantMap getRange(Instant fromVersion, Instant toVersion) {

      // get tail of map
      NavigableMap<Instant, NavigableMap<Instant, D>> tailMap =
          fromVersion != null && _fromVersionCorrectionInstantMap.floorKey(fromVersion) != null
          ? _fromVersionCorrectionInstantMap.tailMap(_fromVersionCorrectionInstantMap.floorKey(fromVersion), true)
          : _fromVersionCorrectionInstantMap;

      // get head of tail
      NavigableMap<Instant, NavigableMap<Instant, D>> headOfTailMap =
          toVersion != null && _fromVersionCorrectionInstantMap.floorKey(toVersion) != null
          ? tailMap.headMap(_fromVersionCorrectionInstantMap.floorKey(toVersion), false)
          : tailMap;

      return new InstantMap(headOfTailMap);
    }

    public void put(D document) { // assumes document has the same objectid as the others in this InstantMap
      Instant versionFromInstant = document.getVersionFromInstant() != null ? document.getVersionFromInstant() : Instant.EPOCH;
      Instant correctionFromInstant = document.getCorrectionFromInstant() != null ? document.getCorrectionFromInstant() : Instant.EPOCH;
      NavigableMap<Instant, D> fromCorrectionInstantMap = _fromVersionCorrectionInstantMap.get(versionFromInstant);
      if (fromCorrectionInstantMap == null) {
        fromCorrectionInstantMap = new TreeMap<>();
        _fromVersionCorrectionInstantMap.put(versionFromInstant, fromCorrectionInstantMap);
      }
      // TODO may need to invalidate previous latest by changing version/correction or reloading from underlying
      fromCorrectionInstantMap.put(correctionFromInstant, document);
    }

    public NavigableMap<Instant, NavigableMap<Instant, D>> getMap() {
      return _fromVersionCorrectionInstantMap;
    }
  } // InstantMap

  private InstantMap getOrCreateInstantMap(ObjectIdentifiable objectId, Cache cache) {
    Element e = cache.get(objectId);
    InstantMap instantMap;
    if (e != null) {
      instantMap = (InstantMap) (e.getObjectValue());
    } else {
      instantMap = new InstantMap();
      cache.put(new Element(objectId, instantMap));
    }
    return instantMap;
  }
  //-------------------------------------------------------------------------

  @Override
  public D get(ObjectIdentifiable objectId, VersionCorrection versionCorrection) {
    ArgumentChecker.notNull(objectId, "objectId");
    ArgumentChecker.notNull(versionCorrection, "versionCorrection");

    // Get/create instant map
    InstantMap instantMap = getOrCreateInstantMap(objectId, getDocumentByOidCache());

    // Get/create document in instant map
    D result = instantMap.get(versionCorrection);
    if (result != null) {
      s_logger.debug("retrieved object: {} from doc-cache", result);
    } else {
      // Get document from underlying master
      result = getUnderlying().get(objectId, versionCorrection);

      // Update uniqueid map
      if (result != null) { // TODO NOT CACHING MISSES :(
        getDocumentByUidCache().put(new Element(result.getUniqueId(), result));

        // Update objectid/version/correction map
        instantMap.put(result);
      }
    }
    return result;
  }

  @Override
  public D get(UniqueId uniqueId) {
    ArgumentChecker.notNull(uniqueId, "uniqueId");

    D result;
    if (!uniqueId.isVersioned()) {
      // Revert to ObjectId search for unversioned UniqueIds
      result = get(uniqueId.getObjectId(), VersionCorrection.LATEST);
    } else {
      // Locate UniqueId in cache
      Element e = getDocumentByUidCache().get(uniqueId);
      if (e != null) {
        result = (D) (e.getObjectValue());
        s_logger.debug("retrieved object: {} from doc-cache", result);
      } else {
        // Get document from underlying master
        result = getUnderlying().get(uniqueId);

        if (result != null) { // TODO NOT CACHING MISSES :(
          // Update uniqueid map
          getDocumentByUidCache().put(new Element(uniqueId, result));

          // Update objectid/version/correction map
          InstantMap instantMap = getOrCreateInstantMap(uniqueId.getObjectId(), getDocumentByOidCache());
          instantMap.put(result);
        }
      }
    }
    return result;
  }

  @Override
  public Map<UniqueId, D> get(Collection<UniqueId> uniqueIds) {
    Map<UniqueId, D> result = newHashMap();
    for (UniqueId uniqueId : uniqueIds) {
      try {
        D object = get(uniqueId);
        result.put(uniqueId, object);
      } catch (DataNotFoundException ex) {
        // do nothing
      }
    }
    return result;
  }

  //-------------------------------------------------------------------------

  @Override
  public D add(D document) {

    // Add document to underlying master
    D result = getUnderlying().add(document);

    // Store document in ObjectId/version/correction map
    InstantMap instantMap = getOrCreateInstantMap(result.getObjectId(), getDocumentByOidCache());
    instantMap.put(result);

    // Store document in UniqueId map
    getDocumentByUidCache().put(new Element(result.getUniqueId(), result));

    return result;
  }

  @Override
  public D update(D document) {
    // Update document in underlying master
    D result = getUnderlying().update(document);

    // Store document in ObjectId/version/correction map
    InstantMap instantMap = getOrCreateInstantMap(result.getObjectId(), getDocumentByOidCache());
    instantMap.put(result);

    // Store document in UniqueId map
    getDocumentByUidCache().put(new Element(result.getUniqueId(), result));

    // TODO adjust version/correction validity of previous version in Oid map
    // TODO do we need to fire entity changed events here?
    return result;
  }

  @Override
  public void remove(ObjectIdentifiable oid) {
    // Remove document from underlying master
    getUnderlying().remove(oid);

    // Adjust version/correction validity of latest version in Oid cache
    cleanCaches(oid.getObjectId(), Instant.now(), null);
  }

  @Override
  public D correct(D document) {
    // Correct document in underlying master
    D result = getUnderlying().correct(document);

    // Adjust version/correction validity of latest version in Oid cache
//    InstantMap instantMap = getOrCreateInstantMap(document.getObjectId(), getDocumentByOidCache());
//    D oldDocument = instantMap.get(VersionCorrection.of(document.getVersionToInstant(), document.getCorrectionToInstant()));
//    if (oldDocument != null) {
//      oldDocument.setVersionToInstant();   // hope this is accurate enough, might need to fetch times from underlyig master
//      oldDocument.setCorrectionToInstant();
//    }

    return result;
  }

  @Override
  public List<UniqueId> replaceVersion(UniqueId uniqueId, List<D> replacementDocuments) {
    return getUnderlying().replaceVersion(uniqueId, replacementDocuments);
  }

  @Override
  public List<UniqueId> replaceAllVersions(ObjectIdentifiable objectId, List<D> replacementDocuments) {
    return getUnderlying().replaceAllVersions(objectId, replacementDocuments);
  }

  @Override
  public List<UniqueId> replaceVersions(ObjectIdentifiable objectId, List<D> replacementDocuments) {
    return getUnderlying().replaceVersions(objectId, replacementDocuments);
  }

  @Override
  public UniqueId replaceVersion(D replacementDocument) {
    return getUnderlying().replaceVersion(replacementDocument);
  }

  @Override
  public void removeVersion(UniqueId uniqueId) {
    getUnderlying().removeVersion(uniqueId);
  }

  @Override
  public UniqueId addVersion(ObjectIdentifiable objectId, D documentToAdd) {
    return getUnderlying().addVersion(objectId, documentToAdd);
  }

  //-------------------------------------------------------------------------

  private void cleanCaches(ObjectId oid, Instant fromVersion, Instant toVersion) {

// Coarse grain removal from caches - very inefficient
//    getDocumentByOidCache().remove(oid);
//    for (UniqueId uniqueId : (Collection<UniqueId>) getDocumentByUidCache().getKeys()) {
//      if (uniqueId.getObjectId().equals(oid)) {
//        getDocumentByUidCache().remove(uniqueId);
//      }
//    }

    // Get the documents that match the version range
    InstantMap instantMap = getOrCreateInstantMap(oid, getDocumentByOidCache()).getRange(fromVersion, toVersion);

    // Remove all matching versions
    for (NavigableMap<Instant, D> correctionMap : instantMap.getMap().values()) {

      // Remove each correction from Uid map
      for (D document : correctionMap.values()) {
        getDocumentByUidCache().remove(document.getUniqueId());
      }

      // Remove all corrections from Oid map
      correctionMap.clear();
    }
  }

  /**
   * Call this at the end of a unit test run to clear the state of EHCache.
   * It should not be part of a generic lifecycle method.
   */
  public void shutdown() {
    getUnderlying().changeManager().removeChangeListener(_changeListener);
    getCacheManager().clearAllStartingWith(_documentByOidCacheName);
    getCacheManager().clearAllStartingWith(_documentByUidCacheName);
    getCacheManager().removeCache(_documentByOidCacheName);
    getCacheManager().removeCache(_documentByUidCacheName);
  }

  //-------------------------------------------------------------------------
  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + getUnderlying() + "]";
  }

}
