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

  private Writeable<ManageableSecurity> _securityWriter;
  private Writeable<ManageablePosition> _positionWriter;

  private ManageablePortfolioNode _rootNode;
  private ManageablePortfolioNode _currentNode;
  private ManageablePortfolioNode _originalNode;
  private ManageablePortfolioNode _originalRoot;

  private boolean _overwrite;


  public NodePositionSecurityMasterWriter(Writeable<ManageablePosition> positionWriter,
                                          Writeable<ManageableSecurity> securityWriter,
                                          ManageablePortfolioNode rootNode, boolean overwrite) {
    ArgumentChecker.notNull(positionWriter, "positionWriter");
    ArgumentChecker.notNull(securityWriter, "securityWriter");
    ArgumentChecker.notNull(rootNode, "rootNode");

    _positionWriter = positionWriter;
    _securityWriter = securityWriter;
    _currentNode = _originalRoot = _rootNode = rootNode;
    _overwrite = overwrite;
  }

  @Override
  public void addOrUpdate(NodePositionSecurity nodePositionSecurity) {
    ArgumentChecker.notNull(nodePositionSecurity, "triple");

    String[] path = nodePositionSecurity.getPath();
    ManageablePosition position = nodePositionSecurity.getPosition();
    Iterable<ManageableSecurity> securities = nodePositionSecurity.getSecurities();

    setPath(path);
    _positionWriter.addOrUpdate(position);
    _securityWriter.addOrUpdate(securities);
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
