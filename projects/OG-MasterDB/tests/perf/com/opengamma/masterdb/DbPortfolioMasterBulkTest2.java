/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import static com.google.common.collect.Lists.newArrayList;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.time.Instant;
import javax.time.TimeSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.opengamma.elsql.ElSqlBundle;
import com.opengamma.id.ObjectId;
import com.opengamma.id.UniqueId;
import com.opengamma.master.DocumentVisibility;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.portfolio.ManageablePortfolioNode;
import com.opengamma.master.portfolio.PortfolioDocument;
import com.opengamma.master.portfolio.PortfolioSearchRequest;
import com.opengamma.masterdb.portfolio.DbPortfolioMaster;
import com.opengamma.util.db.DbMapSqlParameterSource;
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.test.DbTest;

/**
 * Base tests for DbSecurityMasterWorker via DbSecurityMaster.
 */
public class DbPortfolioMasterBulkTest2 extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbPortfolioMasterBulkTest2.class);

  protected DbPortfolioMaster _master;
  protected Instant _version1Instant;
  protected Instant _version2Instant;

  @Factory(dataProvider = "databases", dataProviderClass = DbTest.class)
  public DbPortfolioMasterBulkTest2(String databaseType, String databaseVersion) {
    super(databaseType, databaseVersion);
    s_logger.info("running testcases for {}", databaseType);
  }


  @BeforeMethod
  public void setUp() throws Exception {
    super.setUp();
    ConfigurableApplicationContext context = DbMasterTestUtils.getContext(getDatabaseType());
    _master = (DbPortfolioMaster) context.getBean(getDatabaseType() + "DbPortfolioMaster");
    Instant now = Instant.now();
    _master.setTimeSource(TimeSource.fixed(now));
    _version1Instant = now.minusSeconds(100);
    _version2Instant = now.minusSeconds(50);
  }

  @Operation(batchSize = 10)
  public void search() {
    PortfolioSearchRequest request = new PortfolioSearchRequest();
    request.setPagingRequest(PagingRequest.FIRST_PAGE);
    _master.search(request);
    request.setDepth(3);
    _master.search(request);
  }

  @Operation(batchSize = 10)
  public void insert() {  
    PortfolioDocument doc = new PortfolioDocument();
    ManageablePortfolioNode rootNode = createPortfolioTree(2, 3, 12);
    ManageablePortfolio portfolio = new ManageablePortfolio("Test");
    portfolio.setRootNode(rootNode);
    doc.setPortfolio(portfolio);
    _master.add(doc);    
  }

  @Test(groups = {"perftest"})
  public void testOperations() {
    testOperations(100, 1000, 0);
  }

  @AfterMethod
  public void tearDown() throws Exception {
    _master = null;
    super.tearDown();
  }


  @Override
  protected void seed(int count) {


    ElSqlBundle bundle = ElSqlBundle.of(getDbConnector().getDialect().getElSqlConfig(), DbPortfolioMaster.class);


    final List<DbMapSqlParameterSource> records = newArrayList();

    // the arguments for inserting into the node table
    final List<DbMapSqlParameterSource> nodeList = new ArrayList<DbMapSqlParameterSource>(256);
    final List<DbMapSqlParameterSource> posList = new ArrayList<DbMapSqlParameterSource>(256);
    // the arguments for inserting into the portifolio_attribute table
    final List<DbMapSqlParameterSource> prtAttrList = Lists.newArrayList();

    for (int i = 0; i < count; i++) {


      final Long portfolioId = nextId("prt_master_seq");
      final Long portfolioOid = portfolioId;
      final UniqueId portfolioUid = UniqueId.of("Scheme", Long.toString(portfolioOid), "0");

      ManageablePortfolioNode rootNode = createPortfolioTree(2, 2, 5);

      // the arguments for inserting into the portfolio table
      final DbMapSqlParameterSource docArgs = new DbMapSqlParameterSource()
        .addValue("portfolio_id", portfolioId)
        .addValue("portfolio_oid", portfolioOid)
        .addTimestamp("ver_from_instant", Instant.now())
        .addTimestampNullFuture("ver_to_instant", null)
        .addTimestamp("corr_from_instant", Instant.now())
        .addTimestampNullFuture("corr_to_instant", null)
        .addValue("name", StringUtils.defaultString(randomString(20)))
        .addValue("visibility", DocumentVisibility.VISIBLE.getVisibilityLevel());


      insertBuildArgs(portfolioUid, null, rootNode, portfolioId, portfolioOid, null, null, new AtomicInteger(1), 0, nodeList, posList);


      for (Map.Entry<String, String> entry : new HashMap<String, String>() {{
        put("attr1", "val1");
        put("attr2", "val2");
      }}.entrySet()) {
        final long prtAttrId = nextId("prt_portfolio_attr_seq");
        final DbMapSqlParameterSource posAttrArgs = new DbMapSqlParameterSource()
          .addValue("attr_id", prtAttrId)
          .addValue("portfolio_id", portfolioId)
          .addValue("portfolio_oid", portfolioOid)
          .addValue("key", entry.getKey())
          .addValue("value", entry.getValue());
        prtAttrList.add(posAttrArgs);
      }

      records.add(docArgs);

    }

    final String sqlDoc = bundle.getSql("Insert");
    final String sqlNode = bundle.getSql("InsertNode");
    final String sqlPosition = bundle.getSql("InsertPosition");
    final String sqlAttributes = bundle.getSql("InsertAttribute");

    getDbConnector().getJdbcTemplate().batchUpdate(sqlDoc, records.toArray(new DbMapSqlParameterSource[records.size()]));
    getDbConnector().getJdbcTemplate().batchUpdate(sqlNode, nodeList.toArray(new DbMapSqlParameterSource[nodeList.size()]));
    getDbConnector().getJdbcTemplate().batchUpdate(sqlPosition, posList.toArray(new DbMapSqlParameterSource[posList.size()]));
    getDbConnector().getJdbcTemplate().batchUpdate(sqlAttributes, prtAttrList.toArray(new DbMapSqlParameterSource[prtAttrList.size()]));


  }

  /**
   * Recursively create the arguments to insert into the tree existing nodes.
   *
   * @param portfolioUid  the portfolio unique identifier, not null
   * @param parentNodeUid  the parent node unique identifier, not null
   * @param node  the root node, not null
   * @param portfolioId  the portfolio id, not null
   * @param portfolioOid  the portfolio oid, not null
   * @param parentNodeId  the parent node id, null if root node
   * @param parentNodeOid  the parent node oid, null if root node
   * @param counter  the counter to create the node id, use {@code getAndIncrement}, not null
   * @param depth  the depth of the node in the portfolio
   * @param argsList  the list of arguments to build, not null
   * @param posList  the list of arguments to for inserting positions, not null
   */
  protected void insertBuildArgs(
    final UniqueId portfolioUid, final UniqueId parentNodeUid,
    final ManageablePortfolioNode node,
    final Long portfolioId, final Long portfolioOid, final Long parentNodeId, final Long parentNodeOid,
    final AtomicInteger counter, final int depth, final List<DbMapSqlParameterSource> argsList, final List<DbMapSqlParameterSource> posList) {
    // need to insert parent before children for referential integrity
    final Long nodeId = nextId("prt_master_seq");
    final Long nodeOid = nodeId;
    UniqueId nodeUid = UniqueId.of("Scheme", Long.toString(nodeOid), "0");
    node.setUniqueId(nodeUid);
    node.setParentNodeId(parentNodeUid);
    node.setPortfolioId(portfolioUid);
    final DbMapSqlParameterSource treeArgs = new DbMapSqlParameterSource()
      .addValue("node_id", nodeId)
      .addValue("node_oid", nodeOid)
      .addValue("portfolio_id", portfolioId)
      .addValue("portfolio_oid", portfolioOid)
      .addValue("parent_node_id", parentNodeId)
      .addValue("parent_node_oid", parentNodeOid)
      .addValue("depth", depth)
      .addValue("name", StringUtils.defaultString(node.getName()));
    argsList.add(treeArgs);

    // store position links
    Set<ObjectId> positionIds = new LinkedHashSet<ObjectId>(node.getPositionIds());
    node.getPositionIds().clear();
    node.getPositionIds().addAll(positionIds);
    for (ObjectId positionId : positionIds) {
      final DbMapSqlParameterSource posArgs = new DbMapSqlParameterSource()
        .addValue("node_id", nodeId)
        .addValue("key_scheme", positionId.getScheme())
        .addValue("key_value", positionId.getValue());
      posList.add(posArgs);
    }

    // store the left/right before/after the child loop and back fill into stored args row
    treeArgs.addValue("tree_left", counter.getAndIncrement());
    for (ManageablePortfolioNode childNode : node.getChildNodes()) {
      insertBuildArgs(portfolioUid, nodeUid, childNode, portfolioId, portfolioOid, nodeId, nodeOid, counter, depth + 1, argsList, posList);
    }
    treeArgs.addValue("tree_right", counter.getAndIncrement());
  }


  protected ManageablePortfolioNode createPortfolioTree(int fanout, final int level, int positionCount) {
    ManageablePortfolioNode parent = new ManageablePortfolioNode(randomString(30));
    if (level > 0) {
      for (int i = 0; i < fanout; i++) {
        parent.addChildNode(createPortfolioTree(fanout, level - 1, positionCount));
      }
    } else {
      for (int i = 0; i < positionCount; i++) {
        parent.addPosition(UniqueId.of("Pos", randomString(12)));
      }
    }
    return parent;
  }
}
