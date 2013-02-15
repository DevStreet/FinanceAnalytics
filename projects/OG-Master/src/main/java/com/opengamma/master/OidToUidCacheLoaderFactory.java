package com.opengamma.master;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;
import net.sf.ehcache.loader.CacheLoader;
import net.sf.ehcache.loader.CacheLoaderFactory;

/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

public class OidToUidCacheLoaderFactory extends CacheLoaderFactory {

  @Override
  public CacheLoader createCacheLoader(Ehcache ehcache, Properties properties) {
    return new OidToUidCacheLoader();
  }

  public class OidToUidCacheLoader implements CacheLoader {

    @Override
    public Object load(Object o) throws CacheException {
      return null;  // TODO
    }

    @Override
    public Map loadAll(Collection collection) {
      return null;  // TODO
    }

    @Override
    public Object load(Object o, Object o2) {
      return null;  // TODO
    }

    @Override
    public Map loadAll(Collection collection, Object o) {
      return null;  // TODO
    }

    @Override
    public String getName() {
      return null;  // TODO
    }

    @Override
    public CacheLoader clone(Ehcache ehcache) throws CloneNotSupportedException {
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
    public Status getStatus() {
      return null;  // TODO
    }
  }

}
