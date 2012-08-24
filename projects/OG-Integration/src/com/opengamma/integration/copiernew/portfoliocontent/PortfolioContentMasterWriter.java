package com.opengamma.integration.copiernew.portfoliocontent;

import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.nodepositionsecurity.NodePositionSecurity;
import com.opengamma.integration.copiernew.nodepositionsecurity.NodePositionSecurityMasterWriter;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.portfolio.ManageablePortfolioNode;
import com.opengamma.master.portfolio.PortfolioDocument;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.portfolio.PortfolioSearchRequest;
import com.opengamma.master.portfolio.PortfolioSearchResult;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.util.ArgumentChecker;

import java.io.IOException;

/**
 * PortfolioContentMasterWriters persist multiple portfolios and their contents to the portofolio and position
 * masters and to a security writer (which might or might not be backed by a security master). It makes use of
 * NodePositionSecurityMasterWriters to transfer the actual contents (node tree, positions and securities) of each
 * portfolio.
 */
public class PortfolioContentMasterWriter implements Writeable<PortfolioContent> {

  private static final String TEMPLATE_NAME = "<name>";

  private PortfolioMaster _portfolioMaster;
  private PositionMaster _positionMaster;
  private Writeable<ManageableSecurity> _securityWriter;
  private String _nameTemplate;


  /**
   * Creates a new PortfolioContentMasterWriter that persists portfolios and their contents to the portfolio master,
   * position master and a security writer (not necessarily backed by a security master).
   * @param portfolioMaster   The portfolio master (needed to look up existing portfolios and add/update)
   * @param positionMaster    The position master (needed to look up existing positions and add/update)
   * @param securityWriter    The security writer for adding/updating securities
   */
  public PortfolioContentMasterWriter(PortfolioMaster portfolioMaster, PositionMaster positionMaster,
                                      Writeable<ManageableSecurity> securityWriter) {
    this(portfolioMaster, positionMaster, securityWriter, null);
  }

  /**
   * Creates a new PortfolioContentMasterWriter that persists portfolios and their contents to the portfolio master,
   * position master and a security writer (not necessarily backed by a security master), while renaming the
   * portfolios according to the supplied nameTemplate (where <name> is replaced with the source portfolio name)
   * @param portfolioMaster
   * @param positionMaster
   * @param securityWriter    The security writer for adding/updating securities
   * @param nameTemplate
   */
  public PortfolioContentMasterWriter(PortfolioMaster portfolioMaster, PositionMaster positionMaster,
                                      Writeable<ManageableSecurity> securityWriter, String nameTemplate) {
    ArgumentChecker.notNull(portfolioMaster, "portfolioMaster");
    ArgumentChecker.notNull(positionMaster, "positionMaster");
    ArgumentChecker.notNull(securityWriter, "securityWriter");

    _portfolioMaster = portfolioMaster;
    _positionMaster = positionMaster;
    _securityWriter = securityWriter;
    _nameTemplate = nameTemplate;
    if (_nameTemplate != null && !_nameTemplate.contains(TEMPLATE_NAME)) {
      _nameTemplate += TEMPLATE_NAME;
    }
  }

  @Override
  public void addOrUpdate(PortfolioContent portfolioContent) {

    ArgumentChecker.notNull(portfolioContent, "portfolioContent");

    Iterable<NodePositionSecurity> nodePositionSecurityReader = portfolioContent.getNodePositionSecurityReader();
    ManageablePortfolio portfolio = portfolioContent.getPortfolio();

    if (_nameTemplate != null) {
      portfolio.setName(_nameTemplate.replace(TEMPLATE_NAME, portfolio.getName()));
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
