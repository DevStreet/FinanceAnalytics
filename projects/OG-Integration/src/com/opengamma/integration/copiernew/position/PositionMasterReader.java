/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.position;

import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.master.position.PositionSearchResult;
import com.opengamma.util.ArgumentChecker;

import java.util.Iterator;

public class PositionMasterReader implements Iterable<ManageablePosition> {

  private PositionSearchResult _positionSearchResult;

  public PositionMasterReader(PositionMaster positionMaster) {
    this(positionMaster, null);
  }

  public PositionMasterReader(PositionMaster positionMaster, PositionSearchRequest positionSearchRequest) {
    ArgumentChecker.notNull(positionMaster, "positionMaster");
    if (positionSearchRequest == null) {
      positionSearchRequest = new PositionSearchRequest();
    }
    _positionSearchResult = positionMaster.search(positionSearchRequest);
  }

  @Override
  public Iterator<ManageablePosition> iterator() {
    return _positionSearchResult.getPositions().iterator();
  }
}
