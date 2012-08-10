/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.nodepositionsecurity;

import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.security.ManageableSecurity;

public class NodePositionSecurity {

  private String[] _path;
  private ManageablePosition _position;
  private Iterable<ManageableSecurity> _securities;

  public NodePositionSecurity(String[] path, ManageablePosition position, Iterable<ManageableSecurity> securities) {
    _path = path;
    _position = position;
    _securities = securities;
  }

  public String[] getPath() {
    return _path;
  }

  public ManageablePosition getPosition() {
    return _position;
  }

  public Iterable<ManageableSecurity> getSecurities() {
    return _securities;
  }

}
