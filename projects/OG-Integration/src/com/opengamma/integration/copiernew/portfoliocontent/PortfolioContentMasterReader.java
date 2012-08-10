/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.portfoliocontent;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.portfoliopositionsecurity.PortfolioPositionSecurityMasterReader;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.util.tuple.ObjectsPair;

import java.util.Iterator;

public class PortfolioContentMasterReader implements
    Iterable<ObjectsPair<ManageablePortfolio, Iterable<ObjectsPair<String[], ManageablePosition>>>> {

  private PositionMaster _positionMaster;
  private Iterator<ManageablePortfolio> _portfolioIterator;

  public PortfolioContentMasterReader(Iterator<ManageablePortfolio> portfolioIterator, PositionMaster positionMaster) {
    _portfolioIterator = portfolioIterator;
    _positionMaster = positionMaster;
  }


  @Override
  public Iterator<ObjectsPair<ManageablePortfolio, Iterable<ObjectsPair<String[], ManageablePosition>>>> iterator() {
    return new Iterator<ObjectsPair<ManageablePortfolio, Iterable<ObjectsPair<String[], ManageablePosition>>>>() {
      @Override
      public boolean hasNext() {
        return _portfolioIterator.hasNext();
      }

      @Override
      public ObjectsPair<ManageablePortfolio, Iterable<ObjectsPair<String[], ManageablePosition>>> next() {
        ManageablePortfolio portfolio = _portfolioIterator.next();
        return new ObjectsPair<ManageablePortfolio, Iterable<ObjectsPair<String[], ManageablePosition>>>(
            portfolio,
            new PortfolioPositionSecurityMasterReader(_positionMaster, portfolio)
        );
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove not supported");
      }
    };
  }
}
