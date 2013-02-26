/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.portfolio.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.id.UniqueId;
import com.opengamma.master.AbstractSearchRequest;
import com.opengamma.master.cache.AbstractEHCachingMaster;
import com.opengamma.master.cache.DocumentSearchCache;
import com.opengamma.master.portfolio.ManageablePortfolioNode;
import com.opengamma.master.portfolio.PortfolioDocument;
import com.opengamma.master.portfolio.PortfolioHistoryRequest;
import com.opengamma.master.portfolio.PortfolioHistoryResult;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.portfolio.PortfolioSearchRequest;
import com.opengamma.master.portfolio.PortfolioSearchResult;
import com.opengamma.master.portfolio.PortfolioSearchSortOrder;
import com.opengamma.util.paging.Paging;
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.tuple.ObjectsPair;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * A cache decorating a {@code PortfolioMaster}, mainly intended to reduce the frequency and repetition of queries
 * from the management UI to a {@code DbPortfolioMaster}. In particular, prefetching is employed in paged queries,
 * which tend to scale poorly.
 * <p>
 * The cache is implemented using {@code EHCache}.
 */
public class EHCachingPortfolioMaster extends AbstractEHCachingMaster<PortfolioDocument> implements PortfolioMaster {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(EHCachingPortfolioMaster.class);

  /** The search cache */
  private DocumentSearchCache _documentSearchCache;

  /**
   * Creates an instance over an underlying source specifying the cache manager.
   *
   * @param name          the cache name, not empty
   * @param underlying    the underlying portfolio source, not null
   * @param cacheManager  the cache manager, not null
   */
  public EHCachingPortfolioMaster(final String name, final PortfolioMaster underlying, final CacheManager cacheManager) {
    super(name, underlying, cacheManager);

    // Create the doc search cache and register a security master searcher
    _documentSearchCache = new DocumentSearchCache(name, new DocumentSearchCache.CacheSearcher() {
      @Override
      public ObjectsPair<Integer, List<UniqueId>> search(AbstractSearchRequest request) {

        // Fetch search results from underlying master
        PortfolioSearchResult result = ((PortfolioMaster) getUnderlying()).search((PortfolioSearchRequest) request);

        // Don't cache search result documents as they might not contain the full node depth!!!
        // Cache the result documents
        //DocumentSearchCache.cacheDocuments(result.getDocuments(), getUidToDocumentCache());

        // Return the list of result UniqueIds
        return new ObjectsPair<>(result.getPaging().getTotalItems(),
                                 DocumentSearchCache.extractUniqueIds(result.getDocuments()));
      }
    }, cacheManager);

    // Prime search cache
    PortfolioSearchRequest defaultSearch = new PortfolioSearchRequest();
    defaultSearch.setSortOrder(PortfolioSearchSortOrder.NAME_ASC);
    defaultSearch.setPagingRequest(PagingRequest.FIRST_PAGE);
    _documentSearchCache.backgroundPrefetch(defaultSearch);
  }

  @Override public PortfolioDocument get(UniqueId uniqueId) {

    // Get portfolio from cache
    PortfolioDocument result = super.get(uniqueId);

    // Add each portfolio node in the portfolio to the node cache
    cacheAllNodes(result.getPortfolio().getRootNode());

    return result;
  }

  private void cacheAllNodes(ManageablePortfolioNode node) {
    getUidToDocumentCache().put(new Element(node.getUniqueId().toString(), node));
    for (ManageablePortfolioNode childNode : node.getChildNodes()) {
      cacheAllNodes(childNode);
    }
  }

  @Override
  public PortfolioSearchResult search(PortfolioSearchRequest request) {
    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    _documentSearchCache.backgroundPrefetch(request);

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<UniqueId>> pair = _documentSearchCache.doSearch(request, false); // don't block until cached

    List<PortfolioDocument> documents = new ArrayList<>();
    for (UniqueId uniqueId : pair.getSecond()) {
      documents.add(get(uniqueId));
    }

    PortfolioSearchResult result = new PortfolioSearchResult(documents);
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));
    return result;
  }

  @Override
  public PortfolioHistoryResult history(PortfolioHistoryRequest request) {
    return ((PortfolioMaster) getUnderlying()).history(request); //TODO
  }

  @Override
  public ManageablePortfolioNode getNode(UniqueId nodeId) {

    Element element = getUidToDocumentCache().get(nodeId);
    if (element != null) {
      ManageablePortfolioNode node = ((PortfolioDocument) element.getObjectValue()).getPortfolio().getRootNode();
      return node;
    } else {
      ManageablePortfolioNode node = ((PortfolioMaster) getUnderlying()).getNode(nodeId);
      cacheAllNodes(node);
      return node;
    }
  }

}
