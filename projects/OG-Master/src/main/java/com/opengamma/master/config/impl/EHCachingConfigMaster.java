/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.config.impl;

import com.opengamma.master.AbstractEHCachingMaster;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.config.ConfigHistoryRequest;
import com.opengamma.master.config.ConfigHistoryResult;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.ConfigMetaDataRequest;
import com.opengamma.master.config.ConfigMetaDataResult;
import com.opengamma.master.config.ConfigSearchRequest;
import com.opengamma.master.config.ConfigSearchResult;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A cache decorating a {@code ConfigMaster}, mainly intended to reduce the frequency and repetition of queries
 * from the management UI to a {@code DbConfigMaster}. In particular, prefetching is employed in paged queries,
 * which tend to scale poorly.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingConfigMaster extends AbstractEHCachingMaster<ConfigDocument> implements ConfigMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingConfigMaster.class);

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param underlying  the underlying config source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingConfigMaster(final ConfigMaster underlying, final CacheManager cacheManager) {
    super(underlying, cacheManager);
  }

  @Override
  public ConfigMetaDataResult metaData(ConfigMetaDataRequest request) {
    return ((ConfigMaster) getUnderlying()).metaData(request); //TODO
  }

  @Override
  public <T> ConfigSearchResult<T> search(ConfigSearchRequest<T> request) {
    return ((ConfigMaster) getUnderlying()).search(request); //TODO
  }

  @Override
  public <T> ConfigHistoryResult<T> history(ConfigHistoryRequest<T> request) {
    return ((ConfigMaster) getUnderlying()).history(request); //TODO
  }

}
