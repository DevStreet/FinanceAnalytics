/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.security.impl;

import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecurityHistoryRequest;
import com.opengamma.master.security.SecurityHistoryResult;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecurityMetaDataRequest;
import com.opengamma.master.security.SecurityMetaDataResult;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.SecuritySearchResult;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A cache decorating a {@code SecurityMaster}, mainly intended to reduce the frequency and repetition of queries
 * from the management UI to a {@code DbSecurityMaster}. In particular, prefetching is employed in paged queries,
 * which tend to scale poorly.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingSecurityMaster extends AbstractEHCachingMaster<SecurityDocument> implements SecurityMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingSecurityMaster.class);

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying security source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingSecurityMaster(final SecurityMaster underlying, final CacheManager cacheManager) {
    super(underlying, cacheManager);
  }

  @Override
  public SecurityMetaDataResult metaData(SecurityMetaDataRequest request) {
    return ((SecurityMaster) getUnderlying()).metaData(request);
  }

  @Override
  public SecuritySearchResult search(SecuritySearchRequest request) {
    return ((SecurityMaster) getUnderlying()).search(request);
  }

  @Override
  public SecurityHistoryResult history(SecurityHistoryRequest request) {
    return ((SecurityMaster) getUnderlying()).history(request);
  }

}
