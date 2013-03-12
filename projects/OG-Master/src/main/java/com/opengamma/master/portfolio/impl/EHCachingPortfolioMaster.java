/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.portfolio.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.beans.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.id.UniqueId;
import com.opengamma.master.AbstractSearchRequest;
import com.opengamma.master.cache.AbstractEHCachingMaster;
import com.opengamma.master.cache.EHCachingPagedSearchCache;
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

import net.sf.ehcache.CacheException;
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

  /** The document search cache */
  private EHCachingPagedSearchCache _documentSearchCache;
  
  /** The history search cache */
  private EHCachingPagedSearchCache _historySearchCache;

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
    _documentSearchCache = new EHCachingPagedSearchCache(name + "Document", cacheManager, new EHCachingPagedSearchCache.Searcher() {
      @Override
      public ObjectsPair<Integer, List<UniqueId>> search(Bean request, PagingRequest pagingRequest) {

        // Fetch search results from underlying master
        PortfolioSearchResult result = ((PortfolioMaster) getUnderlying()).search((PortfolioSearchRequest)
            EHCachingPagedSearchCache.withPagingRequest((AbstractSearchRequest) request, pagingRequest));

        // Don't cache search result documents as they might not contain the full node depth!!!
        //EHCachingPagedSearchCache.cacheDocuments(result.getDocuments(), getUidToDocumentCache());

        // Return the list of result UniqueIds
        return new ObjectsPair<>(result.getPaging().getTotalItems(),
                                 EHCachingPagedSearchCache.extractUniqueIds(result.getDocuments()));
      }
    });

    // Create the history search cache and register a security master searcher
    _historySearchCache = new EHCachingPagedSearchCache(name + "History", cacheManager, new EHCachingPagedSearchCache.Searcher() {
      @Override
      public ObjectsPair<Integer, List<UniqueId>> search(Bean request, PagingRequest pagingRequest) {
        // Fetch search results from underlying master
        PortfolioHistoryResult result = ((PortfolioMaster) getUnderlying()).history((PortfolioHistoryRequest)
            EHCachingPagedSearchCache.withPagingRequest((PortfolioHistoryRequest) request, pagingRequest));

        // Don't cache search result documents as they might not contain the full node depth!!!
        //EHCachingPagedSearchCache.cacheDocuments(result.getDocuments(), getUidToDocumentCache());

        // Return the list of result UniqueIds
        return new ObjectsPair<>(result.getPaging().getTotalItems(),
                                 EHCachingPagedSearchCache.extractUniqueIds(result.getDocuments()));
      }
    });
    
    // Prime search cache
    PortfolioSearchRequest defaultSearch = new PortfolioSearchRequest();
    defaultSearch.setSortOrder(PortfolioSearchSortOrder.NAME_ASC);
    _documentSearchCache.prefetch(defaultSearch, PagingRequest.FIRST_PAGE);
  }

  @Override public PortfolioDocument get(UniqueId uniqueId) {

    // Get portfolio from cache
    PortfolioDocument result = super.get(uniqueId);

    // Add each portfolio node in the portfolio to the node cache
    cacheAllNodes(result.getPortfolio().getRootNode());

    return result;
  }

  private void cacheAllNodes(ManageablePortfolioNode node) {
    // TODO don't cache entire node tree for each cached node!!!
//    ManageablePortfolioNode prunedNode = JodaBeanUtils.clone(node);
    getUidToDocumentCache().put(new Element(node.getUniqueId(), node));
    for (ManageablePortfolioNode childNode : node.getChildNodes()) {
      cacheAllNodes(childNode);
    }
  }

  @Override
  public PortfolioSearchResult search(PortfolioSearchRequest request) {
    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    _documentSearchCache.prefetch(EHCachingPagedSearchCache.withPagingRequest(request, null), request.getPagingRequest());

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<UniqueId>> pair =
        _documentSearchCache.search(EHCachingPagedSearchCache.withPagingRequest(request, null),
                                    request.getPagingRequest(), false); // don't block until cached

    List<PortfolioDocument> documents = new ArrayList<>();
    for (UniqueId uniqueId : pair.getSecond()) {
      documents.add(get(uniqueId));
    }

    PortfolioSearchResult result = new PortfolioSearchResult(documents);
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));

    // Debug: check result against underlying
    if (EHCachingPagedSearchCache.TEST_AGAINST_UNDERLYING) {
      PortfolioSearchResult check = ((PortfolioMaster) getUnderlying()).search(request);
      if (!result.getPaging().equals(check.getPaging())) {
        s_logger.error(_documentSearchCache.getCache().getName()
                           + "\n\tCache:\t" + result.getPaging()
                           + "\n\tUnderlying:\t" + check.getPaging());
      }
      if (!result.getDocuments().equals(check.getDocuments())) {
        System.out.println(_documentSearchCache.getCache().getName() + ": ");
        if (check.getDocuments().size() != result.getDocuments().size()) {
          System.out.println("\tSizes differ (Underlying " + check.getDocuments().size()
                             + "; Cache " + result.getDocuments().size() + ")");
        } else {
          for (int i = 0; i < check.getDocuments().size(); i++) {
            if (!check.getDocuments().get(i).equals(result.getDocuments().get(i))) {
              System.out.println("\tUnderlying\t" + i + ":\t" + check.getDocuments().get(i));
              System.out.println("\tCache     \t" + i + ":\t" + result.getDocuments().get(i));
            }
          }
        }
      }
    }

    return result;
  }

  @Override
  public PortfolioHistoryResult history(PortfolioHistoryRequest request) {

    // Ensure that the relevant prefetch range is cached, otherwise fetch and cache any missing sub-ranges in background
    _historySearchCache.prefetch(EHCachingPagedSearchCache.withPagingRequest(request, null), request.getPagingRequest());

    // Fetch the paged request range; if not entirely cached then fetch and cache it in foreground
    ObjectsPair<Integer, List<UniqueId>> pair = _historySearchCache.search(
        EHCachingPagedSearchCache.withPagingRequest(request, null),
        request.getPagingRequest(), false); // don't block until cached

    List<PortfolioDocument> documents = new ArrayList<>();
    for (UniqueId uniqueId : pair.getSecond()) {
      documents.add(get(uniqueId));
    }

    PortfolioHistoryResult result = new PortfolioHistoryResult(documents);
    result.setPaging(Paging.of(request.getPagingRequest(), pair.getFirst()));
    return result;    
  }

  @Override
  public ManageablePortfolioNode getNode(UniqueId nodeId) {

    Element element;
    try {
      element = getUidToDocumentCache().get(nodeId);
    } catch (CacheException e) {
      ManageablePortfolioNode node = ((PortfolioMaster) getUnderlying()).getNode(nodeId);
      cacheAllNodes(node);
      return node;
    }

    if (element != null) {
      ManageablePortfolioNode node = ((ManageablePortfolioNode) element.getObjectValue());
      return node;
    } else {
      return null;
    }
  }
}
