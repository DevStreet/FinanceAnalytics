/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.portfolio;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.portfolio.PortfolioSearchRequest;
import com.opengamma.master.portfolio.PortfolioSearchResult;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.paging.PagingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PortfolioMasterReader implements Iterable<ManageablePortfolio> {

  private static final Logger s_logger = LoggerFactory.getLogger(PortfolioMasterReader.class);

  private PortfolioMaster _portfolioMaster;
  private PortfolioSearchRequest _portfolioSearchRequestTemplate;

  public PortfolioMasterReader(PortfolioMaster portfolioMaster) {
    this(portfolioMaster, null);
  }

  public PortfolioMasterReader(PortfolioMaster portfolioMaster, PortfolioSearchRequest portfolioSearchRequest) {
    this(portfolioMaster, portfolioSearchRequest, PagingRequest.DEFAULT_PAGING_SIZE);
  }

  public PortfolioMasterReader(PortfolioMaster portfolioMaster, PortfolioSearchRequest portfolioSearchRequest, int bufferSize) {
    ArgumentChecker.notNull(portfolioMaster, "portfolioMaster");
    ArgumentChecker.notNegativeOrZero(bufferSize, "bufferSize");
    if (portfolioSearchRequest == null) {
      portfolioSearchRequest = new PortfolioSearchRequest();
      portfolioSearchRequest.setPagingRequest(PagingRequest.ofIndex(0, bufferSize));
    }
    _portfolioSearchRequestTemplate = portfolioSearchRequest;
    _portfolioMaster = portfolioMaster;

  }

  @Override
  public Iterator<ManageablePortfolio> iterator() {
    return new Iterator<ManageablePortfolio>() {
  
      private int _nextPageIndex;
      private PortfolioSearchResult _portfolioSearchResult;
      private Iterator<ManageablePortfolio> _iterator;
  
      {
        _nextPageIndex = 0;
        turnPage();
      }
  
      @Override
      public boolean hasNext() {
        if (!_iterator.hasNext()) {
          return (_portfolioSearchResult != null) && (!_portfolioSearchResult.getPaging().isLastPage());
        } else {
          return true;
        }
      }
  
      @Override
      public ManageablePortfolio next() {
        while (true) {
          try {
            return _iterator.next();
          } catch (NoSuchElementException e) {
            if ((_portfolioSearchResult != null) && (!_portfolioSearchResult.getPaging().isLastPage())) {
              turnPage();
            } else {
              throw new NoSuchElementException();
            }
          }
        }
      }
  
      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove is not supported on this iterator");
      }
  
      private void turnPage() {
        PortfolioSearchRequest portfolioSearchRequest = _portfolioSearchRequestTemplate;
        portfolioSearchRequest.setPagingRequest(PagingRequest.ofIndex(_nextPageIndex, _portfolioSearchRequestTemplate.getPagingRequest().getPagingSize()));
        _nextPageIndex += _portfolioSearchRequestTemplate.getPagingRequest().getPagingSize();
        try {
          _portfolioSearchResult = _portfolioMaster.search(portfolioSearchRequest);
          _iterator = _portfolioSearchResult.getPortfolios().iterator();
        } catch (Throwable t) {
          _iterator = new ArrayList<ManageablePortfolio>().iterator();
          s_logger.error("Error performing portfolio master search: " + t.getMessage());
        }
      }
    };
  }

}
