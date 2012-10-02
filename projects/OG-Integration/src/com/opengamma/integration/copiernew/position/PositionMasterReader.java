/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.position;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.position.PositionMaster;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.master.position.PositionSearchResult;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.paging.PagingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PositionMasterReader implements Iterable<ManageablePosition> {

  private static final Logger s_logger = LoggerFactory.getLogger(PositionMasterReader.class);

  private PositionMaster _positionMaster;
  private PositionSearchRequest _positionSearchRequestTemplate;

  public PositionMasterReader(PositionMaster positionMaster) {
    this(positionMaster, null);
  }

  public PositionMasterReader(PositionMaster positionMaster, PositionSearchRequest positionSearchRequest) {
    this(positionMaster, positionSearchRequest, PagingRequest.DEFAULT_PAGING_SIZE);
  }

  public PositionMasterReader(PositionMaster positionMaster, PositionSearchRequest positionSearchRequest, int bufferSize) {
    ArgumentChecker.notNull(positionMaster, "positionMaster");
    ArgumentChecker.notNegativeOrZero(bufferSize, "bufferSize");
    if (positionSearchRequest == null) {
      positionSearchRequest = new PositionSearchRequest();
      positionSearchRequest.setPagingRequest(PagingRequest.ofIndex(0, bufferSize));
    }
    _positionSearchRequestTemplate = positionSearchRequest;
    _positionMaster = positionMaster;
  }

  @Override
  public Iterator<ManageablePosition> iterator() {
    return new Iterator<ManageablePosition>() {

      private int _nextPageIndex;
      private PositionSearchResult _positionSearchResult;
      private Iterator<ManageablePosition> _iterator;

      {
        _nextPageIndex = 0;
        turnPage();
      }

      @Override
      public boolean hasNext() {
        if (!_iterator.hasNext()) {
          return (_positionSearchResult != null) && (!_positionSearchResult.getPaging().isLastPage());
        } else {
          return true;
        }
      }

      @Override
      public ManageablePosition next() {
        while (true) {
          try {
            return _iterator.next();
          } catch (NoSuchElementException e) {
            if ((_positionSearchResult != null) && (!_positionSearchResult.getPaging().isLastPage())) {
              turnPage();
            } else {
              throw new NoSuchElementException();
            }
          }
        }
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove is not supported on this iterator");
      }

      private void turnPage() {
        PositionSearchRequest positionSearchRequest = _positionSearchRequestTemplate;
        positionSearchRequest.setPagingRequest(PagingRequest.ofIndex(_nextPageIndex, _positionSearchRequestTemplate.getPagingRequest().getPagingSize()));
        _nextPageIndex += _positionSearchRequestTemplate.getPagingRequest().getPagingSize();
        try {
          _positionSearchResult = _positionMaster.search(positionSearchRequest);
          _iterator = _positionSearchResult.getPositions().iterator();
        } catch (Throwable t) {
          _iterator = new ArrayList<ManageablePosition>().iterator();
          s_logger.error("Error performing position master search: " + t.getMessage());
        }
    }

    };
  }
  
}
