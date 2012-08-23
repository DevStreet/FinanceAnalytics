/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew;

import com.opengamma.component.tool.AbstractTool;
import com.opengamma.id.UniqueId;
import com.opengamma.integration.copiernew.nodepositionsecurity.NodePositionSecurity;
import com.opengamma.integration.copiernew.nodepositionsecurity.NodePositionSecurityMasterReader;
import com.opengamma.integration.copiernew.nodepositionsecurity.NodePositionSecurityMasterWriter;
import com.opengamma.integration.copiernew.portfolio.PortfolioMasterReader;
import com.opengamma.integration.copiernew.portfolio.PortfolioMasterWriter;
import com.opengamma.integration.copiernew.portfoliocontent.PortfolioContent;
import com.opengamma.integration.copiernew.portfoliocontent.PortfolioContentMasterReader;
import com.opengamma.integration.copiernew.portfoliocontent.PortfolioContentMasterWriter;
import com.opengamma.integration.copiernew.position.PositionMasterWriter;
import com.opengamma.integration.copiernew.security.SecurityMasterWriter;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.portfolio.ManageablePortfolioNode;
import com.opengamma.master.portfolio.PortfolioDocument;
import com.opengamma.master.portfolio.PortfolioSearchRequest;

public class TestCopierTool extends AbstractTool {

  /**
   * Main method to run the tool.
   *
   * @param args  the arguments, not null
   */
  public static void main(String[] args) { //CSIGNORE
    new TestCopierTool().initAndRun(args);
    System.exit(0);
  }

  @Override
  protected void doRun() throws Exception {

    PortfolioSearchRequest portfolioSearchRequest = new PortfolioSearchRequest();
    portfolioSearchRequest.setName("Example Equity Portfolio*");
    Iterable<PortfolioContent> portfolioContentReader = new PortfolioContentMasterReader(
        new PortfolioMasterReader(getToolContext().getPortfolioMaster(), portfolioSearchRequest),
        getToolContext().getPositionMaster(),
        getToolContext().getSecuritySource()
    );

    Writeable<PortfolioContent> portfolioContentWriter = new PortfolioContentMasterWriter(
        getToolContext().getPortfolioMaster(),
        getToolContext().getPositionMaster(),
        new SecurityMasterWriter(getToolContext().getSecurityMaster()),
        "Ex-"
    );

    portfolioContentWriter.addOrUpdate(portfolioContentReader);

    portfolioContentWriter.flush();

/*
    PortfolioDocument sourcePortfolioDocument = getToolContext().getPortfolioMaster().get(UniqueId.of("DbPrt", "122711"));
    ManageablePortfolio destPortfolio;
      destPortfolio = getToolContext().getPortfolioMaster().get(UniqueId.of("DbPrt", "123001")).getPortfolio();
//      destPortfolio = new ManageablePortfolio("KVKVKVKVKV Test");

    Iterable<NodePositionSecurity> nodePositionSecurityReader = new NodePositionSecurityMasterReader(
        getToolContext().getPositionMaster(),
        getToolContext().getSecuritySource(),
        sourcePortfolioDocument.getPortfolio().getRootNode()
    );

    ManageablePortfolioNode root = new ManageablePortfolioNode();

    Writeable<NodePositionSecurity> nodePositionSecurityWriter = new NodePositionSecurityMasterWriter(
        getToolContext().getPositionMaster(),
        new SecurityMasterWriter(getToolContext().getSecurityMaster()),
        root, destPortfolio.getRootNode()
    );

    nodePositionSecurityWriter.addOrUpdate(nodePositionSecurityReader);
    new PortfolioMasterWriter(getToolContext().getPortfolioMaster()).addOrUpdate(
        new ManageablePortfolio("KVKVKVKVKV Test", root)
    );
*/

/*
    // Export securities
    SecuritySearchRequest searchRequest = new SecuritySearchRequest();
    //searchRequest.setSecurityType("EQUITY");
    //searchRequest.setExternalIdScheme("GLOBEOP_SECID");
    searchRequest.addObjectId(ObjectId.parse("DbSec~23239"));
    searchRequest.addObjectId(ObjectId.parse("DbSec~1406"));
    Iterable<ManageableSecurity> reader =
        new SecurityMasterReader(getToolContext().getSecurityMaster(), searchRequest);

    RowWriter<ManageableSecurity> securityRowWriter =
        new SecurityRowWriter(EquitySecurity.class, SwapSecurity.class);
    Writeable<ManageableSecurity> writer =
        new SheetWriter<ManageableSecurity>(new CsvRawSheetWriter("test-mixed 3.csv", securityRowWriter.getColumns()),
            securityRowWriter);
*/

/*
    // Import securities
    Iterable<ManageableSecurity> reader =
        new SheetReader<ManageableSecurity>(new CsvRawSheetReader("test-mixed 3.csv"),
            new SecurityRowReader(EquitySecurity.class, SwapSecurity.class));
    Writeable<ManageableSecurity> writer =
        new SecurityMasterWriter(getToolContext().getSecurityMaster());

    new Copier<ManageableSecurity>().copy(reader, writer);
*/

/*
    // Works fine but does not export details
    ExchangeSearchRequest searchRequest = new ExchangeSearchRequest();
    Iterable<ManageableExchange> masterReader =
        new ExchangeMasterReader(getToolContext().getExchangeMaster(), searchRequest);
    RowWriter<ManageableExchange> rowWriter =
        new ExchangeRowWriter();
    Writeable<ManageableExchange> sheetWriter =
        new SheetWriter<ManageableExchange>(new CsvRawSheetWriter("test.csv", rowWriter.getColumns()), rowWriter);
    new Copier<ManageableExchange>().copy(masterReader, sheetWriter);
*/

/*
    // Does not function correctly since details are omitted and beancompare detects details in existing exchanges
    Iterable<ManageableExchange> reader =
        new SheetReader<ManageableExchange>(new CsvRawSheetReader("test.csv"), new ExchangeRowReader());
    Writeable<ManageableExchange> writer =
        new ExchangeMasterWriter(getToolContext().getExchangeMaster());
    new Copier<ManageableExchange>().copy(reader, writer);
*/

//    writer.flush();
  }

}
