/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master;

import net.sf.ehcache.constructs.blocking.CacheEntryFactory;

public class OidToUidCacheEntryFactory implements CacheEntryFactory {

  @Override
  public Object createEntry(Object key) throws Exception {
    return null;  // TODO get from underlying
  }

}
