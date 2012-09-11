/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.security;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.master.security.SecuritySearchResult;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.paging.PagingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SecurityMasterReader implements Iterable<ManageableSecurity> {

  private static final Logger s_logger = LoggerFactory.getLogger(SecurityMasterReader.class);

  private SecurityMaster _securityMaster;
  private SecuritySearchRequest _securitySearchRequestTemplate;

  public SecurityMasterReader(SecurityMaster securityMaster) {
    this(securityMaster, null);
  }

  public SecurityMasterReader(SecurityMaster securityMaster, SecuritySearchRequest securitySearchRequest) {
    this(securityMaster, securitySearchRequest, PagingRequest.DEFAULT_PAGING_SIZE);
  }

  public SecurityMasterReader(SecurityMaster securityMaster, SecuritySearchRequest securitySearchRequest, int bufferSize) {
    ArgumentChecker.notNull(securityMaster, "securityMaster");
    ArgumentChecker.notNegativeOrZero(bufferSize, "bufferSize");
    if (securitySearchRequest == null) {
      securitySearchRequest = new SecuritySearchRequest();
      securitySearchRequest.setPagingRequest(PagingRequest.ofIndex(0, bufferSize));
    }
    _securitySearchRequestTemplate = securitySearchRequest;
    _securityMaster = securityMaster;

  }

  @Override
  public Iterator<ManageableSecurity> iterator() {
    return new Iterator<ManageableSecurity>() {
  
      private int _nextPageIndex;
      private SecuritySearchResult _securitySearchResult;
      private Iterator<ManageableSecurity> _iterator;
  
      {
        _nextPageIndex = 0;
        turnPage();
      }
  
      @Override
      public boolean hasNext() {
        if (!_iterator.hasNext()) {
          return !_securitySearchResult.getPaging().isLastPage();
        } else {
          return true;
        }
      }
  
      @Override
      public ManageableSecurity next() {
        while (true) {
          try {
            return _iterator.next();
          } catch (NoSuchElementException e) {
            if (!_securitySearchResult.getPaging().isLastPage()) {
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
        while (true) {
          SecuritySearchRequest securitySearchRequest = _securitySearchRequestTemplate;
          securitySearchRequest.setPagingRequest(PagingRequest.ofIndex(_nextPageIndex, _securitySearchRequestTemplate.getPagingRequest().getPagingSize()));
          _nextPageIndex += _securitySearchRequestTemplate.getPagingRequest().getPagingSize();
          try {
            _securitySearchResult = _securityMaster.search(securitySearchRequest);
            _iterator = _securitySearchResult.getSecurities().iterator();
            return;
          } catch (Throwable t) {
            s_logger.error("Error performing security master search: " + t.getMessage());
          }
        }
      }
  
    };
  }

}
