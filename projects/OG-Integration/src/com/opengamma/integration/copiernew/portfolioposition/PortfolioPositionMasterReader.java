/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.portfolioposition;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.master.position.PositionSearchResult;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.ObjectsPair;

import java.util.Iterator;

public class PortfolioPositionMasterReader implements Iterable<ObjectsPair<String[], ManageablePosition>> {

  private PositionMaster _positionMaster;
  private ManageablePortfolio _portfolio;

  public PortfolioPositionMasterReader(PositionMaster positionMaster, ManageablePortfolio portfolio) {
    ArgumentChecker.notNull(positionMaster, "positionMaster");
    ArgumentChecker.notNull(portfolio, "portfolio");
    _positionMaster = positionMaster;
    _portfolio = portfolio;
  }

  @Override
  public Iterator<ObjectsPair<String[], ManageablePosition>> iterator() {
    return new Iterator<ObjectsPair<String[], ManageablePosition>>() {

      @Override
      public boolean hasNext() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
      }

      @Override
      public ObjectsPair<String[], ManageablePosition> next() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove not supported");
      }
    };
  }
}
