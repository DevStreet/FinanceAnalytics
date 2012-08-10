package com.opengamma.integration.copiernew.portfoliocontent;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Copier;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.portfoliopositionsecurity.PortfolioPositionSecurityMasterWriter;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.portfolio.PortfolioDocument;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.portfolio.PortfolioSearchRequest;
import com.opengamma.master.portfolio.PortfolioSearchResult;
import com.opengamma.master.portfolio.PortfolioSearchSortOrder;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.beancompare.BeanCompare;
import com.opengamma.util.beancompare.BeanDifference;
import com.opengamma.util.tuple.ObjectsPair;

import javax.time.calendar.ZonedDateTime;
import java.io.IOException;
import java.util.List;

public class PortfolioContentMasterWriter implements Writeable<ObjectsPair<ManageablePortfolio, Iterable<ObjectsPair<String[], ManageablePosition>>>> {

  PortfolioMaster _portfolioMaster;
  PositionMaster _positionMaster;
  private BeanCompare _beanCompare;

  public PortfolioContentMasterWriter(PortfolioMaster portfolioMaster, PositionMaster positionMaster) {
    ArgumentChecker.notNull(portfolioMaster, "portfolioMaster");
    ArgumentChecker.notNull(positionMaster, "positionMaster");

    _portfolioMaster = portfolioMaster;
    _positionMaster = positionMaster;
    _beanCompare = new BeanCompare();
  }

  @Override
  public ObjectsPair<ManageablePortfolio, Iterable<ObjectsPair<String[], ManageablePosition>>>
      addOrUpdate(ObjectsPair<ManageablePortfolio, Iterable<ObjectsPair<String[], ManageablePosition>>> pair) {

    ArgumentChecker.notNull(pair, "pair");

    ManageablePortfolio portfolio = pair.getFirst();
    Iterable<ObjectsPair<String[], ManageablePosition>> positionReader = pair.getSecond();

    PortfolioSearchRequest searchReq = new PortfolioSearchRequest();
    searchReq.setName(portfolio.getName());
    searchReq.setVersionCorrection(VersionCorrection.ofVersionAsOf(ZonedDateTime.now())); // valid now
    searchReq.setSortOrder(PortfolioSearchSortOrder.VERSION_FROM_INSTANT_DESC);
    PortfolioSearchResult searchResult = _portfolioMaster.search(searchReq);
    ManageablePortfolio foundPortfolio = searchResult.getFirstPortfolio();
    if (foundPortfolio != null) {
      List<BeanDifference<?>> differences;
      try {
        differences = _beanCompare.compare(foundPortfolio, portfolio);
      } catch (Exception e) {
        throw new OpenGammaRuntimeException("Error comparing portfolios named " + portfolio.getName(), e);
      }
      if (differences.size() == 1 && differences.get(0).getProperty().propertyType() == UniqueId.class) {
        // It's already there, don't update or add it
        portfolio = foundPortfolio;
      } else {
        PortfolioDocument updateDoc = new PortfolioDocument(portfolio);
        updateDoc.setUniqueId(foundPortfolio.getUniqueId());
        PortfolioDocument result = _portfolioMaster.update(updateDoc);
        portfolio = result.getPortfolio();
      }
    } else {
      // Not found, so add it
      PortfolioDocument addDoc = new PortfolioDocument(portfolio);
      PortfolioDocument result = _portfolioMaster.add(addDoc);
      portfolio = result.getPortfolio();
    }

    Writeable<ObjectsPair<String[], ManageablePosition>> positionWriter =
        new PortfolioPositionSecurityMasterWriter(_positionMaster, portfolio, false);

    new Copier<ObjectsPair<String[], ManageablePosition>>().copy(positionReader, positionWriter);

    return pair;
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
