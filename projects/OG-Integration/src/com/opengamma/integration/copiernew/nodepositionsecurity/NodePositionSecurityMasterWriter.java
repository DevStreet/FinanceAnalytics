package com.opengamma.integration.copiernew.nodepositionsecurity;

import com.opengamma.id.ExternalIdSearch;
import com.opengamma.id.ExternalIdSearchType;
import com.opengamma.integration.copiernew.Copier;
import com.opengamma.integration.copiernew.Writeable;
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
import com.opengamma.util.tuple.Triple;
import org.apache.commons.lang.ArrayUtils;

import java.io.IOException;

public class NodePositionSecurityMasterWriter implements Writeable<NodePositionSecurity> {

  private PositionMaster _positionMaster;
  private Writeable<ManageableSecurity> _securityWriter;

  private ManageablePortfolioNode _rootNode;
  private ManageablePortfolioNode _currentNode;
  private ManageablePortfolioNode _originalNode;
  private ManageablePortfolioNode _originalRoot;

  private boolean _overwrite;


  public NodePositionSecurityMasterWriter(PositionMaster positionMaster,
                                          Writeable<ManageableSecurity> securityWriter,
                                          ManageablePortfolioNode rootNode, boolean overwrite) {
    ArgumentChecker.notNull(positionMaster, "positionMaster");
    ArgumentChecker.notNull(securityWriter, "securityWriter");
    ArgumentChecker.notNull(rootNode, "rootNode");

    _positionMaster = positionMaster;
    _securityWriter = securityWriter;
    _currentNode = _originalRoot = _rootNode = rootNode;
    _overwrite = overwrite;
  }

  @Override
  public NodePositionSecurity addOrUpdate(NodePositionSecurity nodePositionSecurity) {
    ArgumentChecker.notNull(nodePositionSecurity, "triple");

    String[] path = nodePositionSecurity.getPath();
    ManageablePosition position = nodePositionSecurity.getPosition();
    Iterable<ManageableSecurity> securities = nodePositionSecurity.getSecurities();

    setPath(path);
    position = writePosition(position);
    new Copier<ManageableSecurity>().copy(securities, _securityWriter);

    return new NodePositionSecurity(path, position, securities);
  }

  @Override
  public void flush() throws IOException {
    // No action
  }


  /**
   * WritePosition checks if the position exists in the previous version of the portfolio.
   * If so, the existing position is reused.
   * @param position    the position to be written
   * @param securities  the security(ies) related to the above position, also to be written; index 1 onwards are underlyings
   * @return            the positions/securities in the masters after writing
   */
  public ManageablePosition writePosition(ManageablePosition position) {
    ArgumentChecker.notNull(position, "position");

    // Write position
    if (_overwrite) {
      // Add the new position to the position master
      PositionDocument addedDoc = _positionMaster.add(new PositionDocument(position));

      // Add the new position to the portfolio
      _currentNode.addPosition(addedDoc.getUniqueId());

      // Return the new position
      return addedDoc.getPosition();

    } else {

      if (!(_originalNode == null) && !_originalNode.getPositionIds().isEmpty()) {
        PositionSearchRequest searchReq = new PositionSearchRequest();

        // Filter positions in current node of original portfolio
        searchReq.setPositionObjectIds(_originalNode.getPositionIds());

        // Filter positions with same external ids
        ExternalIdSearch externalIdSearch = new ExternalIdSearch();
        externalIdSearch.addExternalIds(position.getSecurityLink().getExternalIds());
        externalIdSearch.setSearchType(ExternalIdSearchType.ALL);
        searchReq.setSecurityIdSearch(externalIdSearch);

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

          // Return the existing position
          return existingPosition;
        }
      }

      // Add the new position to the position master
      PositionDocument addedDoc = _positionMaster.add(new PositionDocument(position));

      // Add the new position to the portfolio
      _currentNode.addPosition(addedDoc.getUniqueId());

      // Return the new position
      return addedDoc.getPosition();
    }
  }

  private void setPath(String[] newPath) {
    ArgumentChecker.notNull(newPath, "newPath");

    if (newPath.length == 0) {
      _currentNode = _rootNode;
      _originalNode = _originalRoot;
    } else {
      if (_originalRoot != null) {
        _originalNode = findNode(newPath, _originalRoot);
      }
      _currentNode = createNode(newPath, _rootNode);
    }
  }


  private ManageablePortfolioNode findNode(String[] path, ManageablePortfolioNode startNode) {

    // Degenerate case
    if (path.length == 1) {
      if (startNode.name().equals(path[0])) {
        return startNode;
      } else {
        return null;
      }

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
