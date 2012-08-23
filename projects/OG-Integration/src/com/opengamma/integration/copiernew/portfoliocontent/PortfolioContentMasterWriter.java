package com.opengamma.integration.copiernew.portfoliocontent;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Copier;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.nodepositionsecurity.NodePositionSecurity;
import com.opengamma.integration.copiernew.nodepositionsecurity.NodePositionSecurityMasterWriter;
import com.opengamma.integration.copiernew.portfolio.PortfolioMasterWriter;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.portfolio.ManageablePortfolioNode;
import com.opengamma.master.portfolio.PortfolioDocument;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.portfolio.PortfolioSearchRequest;
import com.opengamma.master.portfolio.PortfolioSearchResult;
import com.opengamma.master.portfolio.PortfolioSearchSortOrder;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.beancompare.BeanCompare;
import com.opengamma.util.beancompare.BeanDifference;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Triple;

import javax.time.calendar.ZonedDateTime;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.util.List;

public class PortfolioContentMasterWriter implements Writeable<PortfolioContent> {

  private PortfolioMaster _portfolioMaster;
  private PositionMaster _positionMaster;
  private Writeable<ManageableSecurity> _securityWriter;
  private BeanCompare _beanCompare;
  private String _namePrefix;


  public PortfolioContentMasterWriter(PortfolioMaster portfolioMaster, PositionMaster positionMaster,
                                      Writeable<ManageableSecurity> securityWriter) {
    this(portfolioMaster, positionMaster, securityWriter, null);
  }

  public PortfolioContentMasterWriter(PortfolioMaster portfolioMaster, PositionMaster positionMaster,
                                      Writeable<ManageableSecurity> securityWriter, String namePrefix) {
    ArgumentChecker.notNull(portfolioMaster, "portfolioMaster");
    ArgumentChecker.notNull(positionMaster, "positionMaster");
    ArgumentChecker.notNull(securityWriter, "securityWriter");

    _portfolioMaster = portfolioMaster;
    _positionMaster = positionMaster;
    _securityWriter = securityWriter;
    _beanCompare = new BeanCompare();
    _namePrefix = namePrefix;
  }

  @Override
  public void addOrUpdate(PortfolioContent portfolioContent) {

    ArgumentChecker.notNull(portfolioContent, "portfolioContent");

    Iterable<NodePositionSecurity> nodePositionSecurityReader = portfolioContent.getNodePositionSecurityReader();
    ManageablePortfolio portfolio = portfolioContent.getPortfolio();

    if (_namePrefix != null) {
      portfolio.setName(_namePrefix + portfolio.getName());
    }

    ManageablePortfolioNode newRoot = new ManageablePortfolioNode();
    ManageablePortfolioNode originalRoot = null;

    PortfolioSearchRequest portfolioSearchRequest = new PortfolioSearchRequest();
    portfolioSearchRequest.setName(portfolio.getName());
    PortfolioSearchResult portfolioSearchResult = _portfolioMaster.search(portfolioSearchRequest);
    ManageablePortfolio existingPortfolio = portfolioSearchResult.getFirstPortfolio();
    if (existingPortfolio != null) {
      originalRoot = existingPortfolio.getRootNode();
    }
    Writeable<NodePositionSecurity> nodePositionSecurityWriter =
        new NodePositionSecurityMasterWriter(_positionMaster, _securityWriter, newRoot, originalRoot);

    nodePositionSecurityWriter.addOrUpdate(nodePositionSecurityReader);
    portfolio.setRootNode(newRoot);
    if (existingPortfolio == null) {
      _portfolioMaster.add(new PortfolioDocument(portfolio));
    } else {
      portfolio.setUniqueId(existingPortfolio.getUniqueId());
      _portfolioMaster.update(new PortfolioDocument(portfolio));
    }
  }

  @Override
  public void addOrUpdate(Iterable<PortfolioContent> data) {
    for (PortfolioContent datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
