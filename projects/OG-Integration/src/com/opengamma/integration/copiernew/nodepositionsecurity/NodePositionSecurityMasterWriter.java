package com.opengamma.integration.copiernew.nodepositionsecurity;

import com.opengamma.id.ExternalIdSearch;
import com.opengamma.id.ExternalIdSearchType;
import com.opengamma.id.ObjectId;
import com.opengamma.integration.copiernew.Copier;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.position.PositionMasterWriter;
import com.opengamma.integration.copiernew.security.SecurityMasterWriter;
import com.opengamma.master.portfolio.ManageablePortfolioNode;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.PositionDocument;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.master.position.PositionSearchResult;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Triple;
import org.apache.commons.lang.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;

public class NodePositionSecurityMasterWriter implements Writeable<NodePositionSecurity> {

  private Writeable<ManageableSecurity> _securityWriter;
  private PositionMaster _positionMaster;

  private ManageablePortfolioNode _newRoot;
  private ManageablePortfolioNode _currentNode;
  private ManageablePortfolioNode _originalNode;
  private ManageablePortfolioNode _originalRoot;

  /**
   * Creates a new NodePositionSecurityMasterWriter which does not try to reuse existing positions, but always
   * creates new ones.
   * @param positionMaster  The position master (needed to look up existing positions and add/update)
   * @param securityWriter  The security writer for adding/updating securities
   * @param newRoot         The root node from where the new portfolio tree is built
   */
  public NodePositionSecurityMasterWriter(PositionMaster positionMaster,
                                          Writeable<ManageableSecurity> securityWriter,
                                          ManageablePortfolioNode newRoot) {
    this(positionMaster, securityWriter, newRoot, null);
  }

  /**
   * Creates a new NodePositionSecurityMasterWriter that attempts to reuse positions referenced in the corresponding
   * portfolio tree node of originalRoot.
   * @param positionMaster  The position master (needed to look up existing positions and add/update)
   * @param securityWriter  The security writer for adding/updating securities
   * @param newRoot         The root node from where the new portfolio tree is built
   * @param originalRoot    The root node from where to search for existing positions to reuse
   */
  public NodePositionSecurityMasterWriter(PositionMaster positionMaster,
                                          Writeable<ManageableSecurity> securityWriter,
                                          ManageablePortfolioNode newRoot, ManageablePortfolioNode originalRoot) {
    ArgumentChecker.notNull(positionMaster, "positionMaster");
    ArgumentChecker.notNull(securityWriter, "securityWriter");
    ArgumentChecker.notNull(newRoot, "newRoot");

    _positionMaster = positionMaster;
    _securityWriter = securityWriter;
    _currentNode = _newRoot = newRoot;
    _originalNode = _originalRoot = originalRoot;
  }

  @Override
  public void addOrUpdate(NodePositionSecurity nodePositionSecurity) {
    if (nodePositionSecurity == null) {
      return;
    }

    String[] path = nodePositionSecurity.getPath();
    ManageablePosition position = nodePositionSecurity.getPosition();
    Iterable<ManageableSecurity> securities = nodePositionSecurity.getSecurities();

    // Write securities
    _securityWriter.addOrUpdate(securities);

    setPath(path);

    if (!(_originalNode == null) && !_originalNode.getPositionIds().isEmpty()) {
      PositionSearchRequest searchReq = new PositionSearchRequest();

      // Filter positions in current node of original portfolio
      searchReq.setPositionObjectIds(_originalNode.getPositionIds());

      // Filter positions with same external ids
      ExternalIdSearch externalIdSearch = new ExternalIdSearch();
      externalIdSearch.addExternalIds(position.getSecurityLink().getExternalIds());
      externalIdSearch.setSearchType(ExternalIdSearchType.ALL);
      searchReq.setSecurityIdSearch(externalIdSearch);

      // TODO Unfortunately this causes an entirely new position to be added when its quantity changes.
      // The alternative is to risk confusing different positions in the same security and node.
      // Filter positions with the same quantity
      searchReq.setMinQuantity(position.getQuantity());
      searchReq.setMaxQuantity(position.getQuantity());

      // Search
      PositionSearchResult searchResult = _positionMaster.search(searchReq);

      PositionDocument firstDocument = searchResult.getFirstDocument();
      if (firstDocument != null) {
        ManageablePosition existingPosition = firstDocument.getPosition();
        // Add the existing position to the portfolio
        _currentNode.addPosition(existingPosition.getUniqueId());
        return;
      }
    }

    // Add a new position
    _positionMaster.add(new PositionDocument(position));

    // Add position to the correct portfolio node
    _currentNode.addPosition(position.getUniqueId());
  }

  @Override
  public void addOrUpdate(Iterable<NodePositionSecurity> data) {
    for (NodePositionSecurity datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }

  private void setPath(String[] newPath) {
    ArgumentChecker.notNull(newPath, "newPath");

    if (newPath.length == 0) {
      _currentNode = _newRoot;
      _originalNode = _originalRoot;
    } else {
      if (_originalRoot != null) {
        _originalNode = findNode(newPath, _originalRoot);
      }
      _currentNode = createNode(newPath, _newRoot);
    }
  }

  private ManageablePortfolioNode findNode(String[] path, ManageablePortfolioNode startNode) {

    // Degenerate case
    if (path.length == 1) {
      for (ManageablePortfolioNode childNode : startNode.getChildNodes()) {
        if (childNode.getName().equals(path[0])) {
          return childNode;
        }
      }
      return null;
//      if (startNode.getName().equals(path[0])) {
//        return startNode;
//      } else {
//        return null;
//      }

    // Recursive case, traverse all child nodes
    } else {
      for (ManageablePortfolioNode childNode : startNode.getChildNodes()) {
        String[] newPath = (String[]) ArrayUtils.subarray(path, 1, path.length - 1);
        ManageablePortfolioNode result = findNode(newPath, childNode);
        if (result != null) {
          return result;
        }
      }
      return null;
    }
  }

  private ManageablePortfolioNode createNode(String[] path, ManageablePortfolioNode startNode) {
    ManageablePortfolioNode node = startNode;
    for (String p : path) {
      ManageablePortfolioNode foundNode = null;
      for (ManageablePortfolioNode n : node.getChildNodes()) {
        if (n.getName().equals(p)) {
          foundNode = n;
          break;
        }
      }
      if (foundNode == null) {
        ManageablePortfolioNode newNode = new ManageablePortfolioNode(p);
        node.addChildNode(newNode);
        node = newNode;
      } else {
        node = foundNode;
      }
    }
    return node;
  }

}
