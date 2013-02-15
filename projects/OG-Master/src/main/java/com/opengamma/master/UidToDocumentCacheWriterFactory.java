/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master;

import java.util.Collection;
import java.util.Properties;

import net.sf.ehcache.CacheEntry;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.CacheWriterFactory;
import net.sf.ehcache.writer.writebehind.operations.SingleOperationType;

public class UidToDocumentCacheWriterFactory  extends CacheWriterFactory {

  @Override
  public CacheWriter createCacheWriter(Ehcache ehcache, Properties properties) {
    return new UidToDocumentCacheWriter();
  }

  public class UidToDocumentCacheWriter implements CacheWriter {

    @Override
    public CacheWriter clone(Ehcache ehcache) throws CloneNotSupportedException {
      return null;  // TODO
    }

    @Override
    public void init() {
      // TODO
    }

    @Override
    public void dispose() throws CacheException {
      // TODO
    }

    @Override
    public void write(Element element) throws CacheException {
      // TODO
    }

    @Override
    public void writeAll(Collection<Element> elements) throws CacheException {
      // TODO
    }

    @Override
    public void delete(CacheEntry cacheEntry) throws CacheException {
      // TODO
    }

    @Override
    public void deleteAll(Collection<CacheEntry> cacheEntries) throws CacheException {
      // TODO
    }

    @Override
    public void throwAway(Element element, SingleOperationType singleOperationType, RuntimeException e) {
      // TODO
    }
  }

}
