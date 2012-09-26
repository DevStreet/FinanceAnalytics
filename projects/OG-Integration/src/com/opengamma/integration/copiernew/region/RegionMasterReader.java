/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.region;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.region.RegionMaster;
import com.opengamma.master.region.RegionSearchRequest;
import com.opengamma.master.region.RegionSearchResult;
import com.opengamma.master.region.ManageableRegion;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.paging.PagingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RegionMasterReader implements Iterable<ManageableRegion> {

  private static final Logger s_logger = LoggerFactory.getLogger(RegionMasterReader.class);

  private RegionMaster _regionMaster;
  private RegionSearchRequest _regionSearchRequestTemplate;

  public RegionMasterReader(RegionMaster regionMaster) {
    this(regionMaster, null);
  }

  public RegionMasterReader(RegionMaster regionMaster, RegionSearchRequest regionSearchRequest) {
    this(regionMaster, regionSearchRequest, PagingRequest.DEFAULT_PAGING_SIZE);
  }

  public RegionMasterReader(RegionMaster regionMaster, RegionSearchRequest regionSearchRequest, int bufferSize) {
    ArgumentChecker.notNull(regionMaster, "regionMaster");
    ArgumentChecker.notNegativeOrZero(bufferSize, "bufferSize");
    if (regionSearchRequest == null) {
      regionSearchRequest = new RegionSearchRequest();
      regionSearchRequest.setPagingRequest(PagingRequest.ofIndex(0, bufferSize));
    }
    _regionSearchRequestTemplate = regionSearchRequest;
    _regionMaster = regionMaster;

  }

  @Override
  public Iterator<ManageableRegion> iterator() {
    return new Iterator<ManageableRegion>() {
  
      private int _nextPageIndex;
      private RegionSearchResult _regionSearchResult;
      private Iterator<ManageableRegion> _iterator;
  
      {
        _nextPageIndex = 0;
        turnPage();
      }
  
      @Override
      public boolean hasNext() {
        if (!_iterator.hasNext()) {
          return (_regionSearchResult != null) && (!_regionSearchResult.getPaging().isLastPage());
        } else {
          return true;
        }
      }
  
      @Override
      public ManageableRegion next() {
        while (true) {
          try {
            return _iterator.next();
          } catch (NoSuchElementException e) {
            if ((_regionSearchResult != null) && (!_regionSearchResult.getPaging().isLastPage())) {
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
        RegionSearchRequest regionSearchRequest = _regionSearchRequestTemplate;
        regionSearchRequest.setPagingRequest(PagingRequest.ofIndex(_nextPageIndex, _regionSearchRequestTemplate.getPagingRequest().getPagingSize()));
        _nextPageIndex += _regionSearchRequestTemplate.getPagingRequest().getPagingSize();
        try {
          _regionSearchResult = _regionMaster.search(regionSearchRequest);
          _iterator = _regionSearchResult.getRegions().iterator();
        } catch (Throwable t) {
          _iterator = new ArrayList<ManageableRegion>().iterator();
          s_logger.error("Error performing region master search: " + t.getMessage());
        }
      }
    };
  }
  
}
