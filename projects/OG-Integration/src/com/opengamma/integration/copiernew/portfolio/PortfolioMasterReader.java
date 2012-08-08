/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.portfolio;

import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.portfolio.PortfolioSearchRequest;
import com.opengamma.master.portfolio.PortfolioSearchResult;
import com.opengamma.util.ArgumentChecker;

import java.util.Iterator;

public class PortfolioMasterReader implements Iterable<ManageablePortfolio> {

  private PortfolioSearchResult _portfolioSearchResult;

  public PortfolioMasterReader(PortfolioMaster portfolioMaster) {
    this(portfolioMaster, null);
  }

  public PortfolioMasterReader(PortfolioMaster portfolioMaster, PortfolioSearchRequest portfolioSearchRequest) {
    ArgumentChecker.notNull(portfolioMaster, "portfolioMaster");
    if (portfolioSearchRequest == null) {
      portfolioSearchRequest = new PortfolioSearchRequest();
    }
    _portfolioSearchResult = portfolioMaster.search(portfolioSearchRequest);
  }

  @Override
  public Iterator<ManageablePortfolio> iterator() {
    return _portfolioSearchResult.getPortfolios().iterator();
  }
}
