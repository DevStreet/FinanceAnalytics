/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.portfoliocontent;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.core.position.PositionSource;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.integration.copiernew.nodepositionsecurity.NodePositionSecurity;
import com.opengamma.integration.copiernew.nodepositionsecurity.NodePositionSecurityMasterReader;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Triple;

import java.util.Iterator;

public class PortfolioContentMasterReader implements
    Iterable<PortfolioContent> {

  private Iterator<ManageablePortfolio> _portfolioIterator;
  private PositionSource _positionSource;
  private SecuritySource _securitySource;

  public PortfolioContentMasterReader(Iterable<ManageablePortfolio> portfolioIterable, PositionSource positionSource,
                                      SecuritySource securitySource) {
    ArgumentChecker.notNull(portfolioIterable, "portfolioIterable");
    ArgumentChecker.notNull(positionSource, "positionSource");
    ArgumentChecker.notNull(securitySource, "securitySource");

    _portfolioIterator = portfolioIterable.iterator();
    _positionSource = positionSource;
    _securitySource = securitySource;
  }


  @Override
  public Iterator<PortfolioContent> iterator() {

    return new Iterator<PortfolioContent>() {

      @Override
      public boolean hasNext() {
        return _portfolioIterator.hasNext();
      }

      @Override
      public PortfolioContent next() {
        ManageablePortfolio portfolio = _portfolioIterator.next();
        return new PortfolioContent (
            portfolio,
            new NodePositionSecurityMasterReader(_positionSource, _securitySource, portfolio.getRootNode())
        );
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove not supported");
      }
    };
  }
}
