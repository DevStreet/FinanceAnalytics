/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.portfoliopositionsecurity;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.core.security.Security;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ObjectId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.master.portfolio.ManageablePortfolio;
import com.opengamma.master.portfolio.ManageablePortfolioNode;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.ManageableSecurityLink;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.impl.MasterSecuritySource;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Triple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class PortfolioPositionSecurityMasterReader implements
    Iterable<Triple<String[], ManageablePosition, Iterable<ManageableSecurity>>> {

  private PositionMaster _positionMaster;
  private SecuritySource _securitySource;
  private ManageablePortfolio _portfolio;

  private ObjectId _nextPositionId;

  private ManageablePortfolioNode _currentNode;
  private Stack<Iterator<ManageablePortfolioNode>> _nodeIteratorStack;
  private Iterator<ManageablePortfolioNode> _nodeIterator;
  private Iterator<ObjectId> _positionIdIterator;


  public PortfolioPositionSecurityMasterReader(
      PositionMaster positionMaster, SecurityMaster securityMaster, ManageablePortfolio portfolio) {
    ArgumentChecker.notNull(positionMaster, "positionMaster");
    ArgumentChecker.notNull(securityMaster, "securityMaster");
    ArgumentChecker.notNull(portfolio, "portfolio");

    _positionMaster = positionMaster;
    _securitySource = new MasterSecuritySource(securityMaster);
    _portfolio = portfolio;
    _currentNode = _portfolio.getRootNode();

    List<ManageablePortfolioNode> rootNodeList = new ArrayList<ManageablePortfolioNode>();
    rootNodeList.add(_portfolio.getRootNode());

    _nodeIterator = rootNodeList.iterator();
    _nodeIteratorStack = new Stack<Iterator<ManageablePortfolioNode>>();
    _positionIdIterator = _nodeIterator.next().getPositionIds().iterator();

    _nextPositionId = getNextPositionId();
  }

  @Override
  public Iterator<Triple<String[], ManageablePosition, Iterable<ManageableSecurity>>> iterator() {
    return new Iterator<Triple<String[], ManageablePosition, Iterable<ManageableSecurity>>>() {

      @Override
      public boolean hasNext() {
        return (_nextPositionId == null);
      }

      @Override
      public Triple<String[], ManageablePosition, Iterable<ManageableSecurity>> next() {
        ObjectId positionId = _nextPositionId;
        _nextPositionId = getNextPositionId();
        if (positionId == null) {
          return null;
        } else {
          ManageablePosition position = _positionMaster.get(positionId, VersionCorrection.LATEST).getPosition();

          return new Triple<String[], ManageablePosition, Iterable<ManageableSecurity>>(
              getCurrentPath(),
              position,
              getSecurities(position)
          );
        }
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove not supported");
      }
    };
  }

  private String[] getCurrentPath() {
    Stack<ManageablePortfolioNode> stack =
        _portfolio.getRootNode().findNodeStackByObjectId(_currentNode.getUniqueId());
    stack.remove(0);
    String[] result = new String[stack.size()];
    int i = stack.size();
    while (!stack.isEmpty()) {
      result[--i] = stack.pop().getName();
    }
    return result;
  }

  /**
   * Walks the tree, depth-first, and returns the next position's id. Uses _positionIdIterator,
   * _nodeIterator and _nodeIteratorStack to maintain location state across calls.
   * @return
   */
  private ObjectId getNextPositionId() {

    while (true) {
      // Return the next position in the current portfolio node's list, if any there
      if (_positionIdIterator.hasNext()) {
        return _positionIdIterator.next();

      // Current node's positions exhausted, find another node
      } else {
        // Go down to current node's child nodes to find more positions (depth-first)
        _nodeIteratorStack.push(_nodeIterator);
        _nodeIterator = _currentNode.getChildNodes().iterator();

        // If there are no more nodes here pop back up until a node is available
        while (!_nodeIterator.hasNext()) {
          if (!_nodeIteratorStack.isEmpty()) {
            _nodeIterator = _nodeIteratorStack.pop();
          } else {
            return null;
          }
        }

        // Go to the next node and start fetching positions there
        _currentNode = _nodeIterator.next();
        _positionIdIterator = _currentNode.getPositionIds().iterator();
      }
    }
  }

  private Iterable<ManageableSecurity> getSecurities(ManageablePosition position) {

    Collection<ManageableSecurity> result = new ArrayList<ManageableSecurity>();

    // Write the related security(ies)
    ManageableSecurityLink sLink = position.getSecurityLink();
    Security security = sLink.resolveQuiet(_securitySource);
    if ((security != null) && (security instanceof ManageableSecurity)) {
      result.add((ManageableSecurity) security);

      // Find underlying security
      // TODO support multiple underlyings; unfortunately the system does not provide a standard way to retrieve underlyings
      if (((ManageableSecurity) security).propertyNames().contains("underlyingId")) {
        ExternalId id = (ExternalId) ((ManageableSecurity) security).property("underlyingId").get();

        Security underlying;
        try {
          underlying = _securitySource.getSecurity(id.toBundle());
          if (underlying != null && (underlying instanceof ManageableSecurity)) {
            result.add((ManageableSecurity) underlying);
          } else {
            //s_logger.warn("Could not resolve underlying " + id + " for security " + security.getName());
          }
        } catch (Throwable e) {
          // Underlying not found
          //s_logger.warn("Error trying to resolve underlying " + id + " for security " + security.getName());
        }
      }
    } else {
      //s_logger.warn("Could not resolve security relating to position " + position.getName());
    }
    return result;
  }
}
