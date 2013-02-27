/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.cache;

import java.util.List;

import org.joda.beans.impl.direct.DirectBean;

import com.opengamma.id.UniqueId;
import com.opengamma.util.tuple.ObjectsPair;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

public interface SearchCache {

  ObjectsPair<Integer, List<UniqueId>> search(final DirectBean requestBean, boolean blockUntilCached);
  void backgroundPrefetch(final DirectBean requestBean);
  CacheManager getCacheManager();
  Ehcache getSearchRequestCache();
  Searcher getSearcher();

  /**
   * A cache searcher, used by the  search cache to pass search requests to an underlying master without
   * knowing its type and retrieve the result unique Ids without knowing the document type.
   */
  public interface Searcher {
    /** Searches an underlying master, casting search requests/results as required for a specific master
     * @param request   The search request (will be cast to a search request for a specific master)
     * @return          The search result
     */
    ObjectsPair<Integer, List<UniqueId>> search(DirectBean request);
  }

}
