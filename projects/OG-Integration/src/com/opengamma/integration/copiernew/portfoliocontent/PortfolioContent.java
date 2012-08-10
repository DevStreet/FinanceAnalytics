/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.portfoliocontent;

import com.opengamma.integration.copiernew.nodepositionsecurity.NodePositionSecurity;
import com.opengamma.master.portfolio.ManageablePortfolio;

public class PortfolioContent {

  private ManageablePortfolio _portfolio;
  private Iterable<NodePositionSecurity> _nodePositionSecurityReader;

  public PortfolioContent(ManageablePortfolio portfolio, Iterable<NodePositionSecurity> nodePositionSecurityReader) {
    _portfolio = portfolio;
    _nodePositionSecurityReader = nodePositionSecurityReader;
  }

  public ManageablePortfolio getPortfolio() {
    return _portfolio;
  }

  public Iterable<NodePositionSecurity> getNodePositionSecurityReader() {
    return _nodePositionSecurityReader;
  }

}
