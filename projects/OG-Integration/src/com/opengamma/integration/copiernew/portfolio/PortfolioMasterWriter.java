package com.opengamma.integration.copiernew.portfolio;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.ExternalIdSearch;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.master.portfolio.PortfolioDocument;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.portfolio.PortfolioSearchRequest;
import com.opengamma.master.portfolio.PortfolioSearchResult;
import com.opengamma.master.portfolio.PortfolioSearchSortOrder;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.beancompare.BeanCompare;
import com.opengamma.util.beancompare.BeanDifference;

import javax.time.calendar.ZonedDateTime;
import java.io.IOException;
import java.util.List;

public class PortfolioMasterWriter implements Writeable<ManageablePortfolio> {

  PortfolioMaster _portfolioMaster;
  private BeanCompare _beanCompare;

  public PortfolioMasterWriter(PortfolioMaster portfolioMaster) {
    ArgumentChecker.notNull(portfolioMaster, "portfolioMaster");
    _portfolioMaster = portfolioMaster;
    _beanCompare = new BeanCompare();
  }

  @Override
  public void addOrUpdate(ManageablePortfolio portfolio) {
    ArgumentChecker.notNull(portfolio, "portfolio");

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
      } else {
        PortfolioDocument updateDoc = new PortfolioDocument(portfolio);
        updateDoc.setUniqueId(foundPortfolio.getUniqueId());
        PortfolioDocument result = _portfolioMaster.update(updateDoc);
      }
    } else {
      // Not found, so add it
      PortfolioDocument addDoc = new PortfolioDocument(portfolio);
      PortfolioDocument result = _portfolioMaster.add(addDoc);
    }
  }

  @Override
  public void addOrUpdate(Iterable<ManageablePortfolio> data) {
    for (ManageablePortfolio datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
