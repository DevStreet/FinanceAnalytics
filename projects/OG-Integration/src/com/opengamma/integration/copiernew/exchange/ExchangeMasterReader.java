/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.exchange;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.exchange.ExchangeMaster;
import com.opengamma.master.exchange.ExchangeSearchRequest;
import com.opengamma.master.exchange.ExchangeSearchResult;
import com.opengamma.master.exchange.ManageableExchange;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.paging.PagingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExchangeMasterReader implements Iterable<ManageableExchange> {

  private static final Logger s_logger = LoggerFactory.getLogger(ExchangeMasterReader.class);

  private ExchangeMaster _exchangeMaster;
  private ExchangeSearchRequest _exchangeSearchRequestTemplate;

  public ExchangeMasterReader(ExchangeMaster exchangeMaster) {
    this(exchangeMaster, null);
  }

  public ExchangeMasterReader(ExchangeMaster exchangeMaster, ExchangeSearchRequest exchangeSearchRequest) {
    this(exchangeMaster, exchangeSearchRequest, PagingRequest.DEFAULT_PAGING_SIZE);
  }

  public ExchangeMasterReader(ExchangeMaster exchangeMaster, ExchangeSearchRequest exchangeSearchRequest, int bufferSize) {
    ArgumentChecker.notNull(exchangeMaster, "exchangeMaster");
    ArgumentChecker.notNegativeOrZero(bufferSize, "bufferSize");
    if (exchangeSearchRequest == null) {
      exchangeSearchRequest = new ExchangeSearchRequest();
      exchangeSearchRequest.setPagingRequest(PagingRequest.ofIndex(0, bufferSize));
    }
    _exchangeSearchRequestTemplate = exchangeSearchRequest;
    _exchangeMaster = exchangeMaster;
  }

  @Override
  public Iterator<ManageableExchange> iterator() {
    return new Iterator<ManageableExchange>() {

      private int _nextPageIndex;
      private ExchangeSearchResult _exchangeSearchResult;
      private Iterator<ManageableExchange> _iterator;

      {
        _nextPageIndex = 0;
        turnPage();
      }

      @Override
      public boolean hasNext() {
        if (!_iterator.hasNext()) {
          return !_exchangeSearchResult.getPaging().isLastPage();
        } else {
          return true;
        }
      }

      @Override
      public ManageableExchange next() {
        while (true) {
          try {
            return _iterator.next();
          } catch (NoSuchElementException e) {
            if (!_exchangeSearchResult.getPaging().isLastPage()) {
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
          ExchangeSearchRequest exchangeSearchRequest = _exchangeSearchRequestTemplate;
          exchangeSearchRequest.setPagingRequest(PagingRequest.ofIndex(_nextPageIndex, _exchangeSearchRequestTemplate.getPagingRequest().getPagingSize()));
          _nextPageIndex += _exchangeSearchRequestTemplate.getPagingRequest().getPagingSize();
          try {
            _exchangeSearchResult = _exchangeMaster.search(exchangeSearchRequest);
            _iterator = _exchangeSearchResult.getExchanges().iterator();
            return;
          } catch (Throwable t) {
            s_logger.error("Error performing exchange master search: " + t.getMessage());
          }
        }
      }

    };
  }

}
