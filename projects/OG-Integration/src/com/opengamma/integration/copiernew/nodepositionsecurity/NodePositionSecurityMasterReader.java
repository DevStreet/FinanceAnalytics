/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.nodepositionsecurity;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.core.position.Position;
import com.opengamma.core.position.PositionSource;
import com.opengamma.core.security.Security;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ObjectId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.master.portfolio.ManageablePortfolioNode;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.ManageableSecurityLink;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.impl.MasterSecuritySource;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.Triple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NodePositionSecurityMasterReader implements Iterable<NodePositionSecurity> {

  private PositionSource _positionSource;
  private SecuritySource _securitySource;

  private ObjectId _nextPositionId;

  private ManageablePortfolioNode _rootNode;
  private ManageablePortfolioNode _currentNode;
  private Stack<Iterator<ManageablePortfolioNode>> _nodeIteratorStack;
  private Iterator<ManageablePortfolioNode> _nodeIterator;
  private Iterator<ObjectId> _positionIdIterator;


  public NodePositionSecurityMasterReader(
      PositionSource positionSource, SecuritySource securitySource, ManageablePortfolioNode rootNode) {
    ArgumentChecker.notNull(positionSource, "positionSource");
    ArgumentChecker.notNull(securitySource, "securitySource");
    ArgumentChecker.notNull(rootNode, "rootNode");

    _positionSource = positionSource;
    _securitySource = securitySource;
    _currentNode = _rootNode = rootNode;

    List<ManageablePortfolioNode> rootNodeList = new ArrayList<ManageablePortfolioNode>();
    rootNodeList.add(rootNode);

    _nodeIterator = rootNodeList.iterator();
    _nodeIteratorStack = new Stack<Iterator<ManageablePortfolioNode>>();
    _positionIdIterator = _nodeIterator.next().getPositionIds().iterator();

    _nextPositionId = getNextPositionId();
  }

  @Override
  public Iterator<NodePositionSecurity> iterator() {
    return new Iterator<NodePositionSecurity>() {

      @Override
      public boolean hasNext() {
        return (_nextPositionId == null);
      }

      @Override
      public NodePositionSecurity next() {
        ObjectId positionId = _nextPositionId;
        _nextPositionId = getNextPositionId();
        if (positionId == null) {
          return null;
        } else {
          Position position = _positionSource.getPosition(positionId.atLatestVersion());
          if (position instanceof ManageablePosition) {
            return new NodePositionSecurity(
                getCurrentPath(),
                (ManageablePosition) position,
                getSecurities((ManageablePosition) position)
            );
          } else {
            return null;
          }
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
        _rootNode.findNodeStackByObjectId(_currentNode.getUniqueId());
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
