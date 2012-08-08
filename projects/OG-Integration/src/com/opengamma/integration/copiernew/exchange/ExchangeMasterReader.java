/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.exchange;

import com.opengamma.master.exchange.ExchangeMaster;
import com.opengamma.master.exchange.ExchangeSearchRequest;
import com.opengamma.master.exchange.ExchangeSearchResult;
import com.opengamma.master.exchange.ManageableExchange;
import com.opengamma.util.ArgumentChecker;

import java.util.Iterator;

public class ExchangeMasterReader implements Iterable<ManageableExchange> {

  private ExchangeSearchResult _exchangeSearchResult;

  public ExchangeMasterReader(ExchangeMaster exchangeMaster) {
    this(exchangeMaster, null);
  }

  public ExchangeMasterReader(ExchangeMaster exchangeMaster, ExchangeSearchRequest exchangeSearchRequest) {
    ArgumentChecker.notNull(exchangeMaster, "exchangeMaster");
    if (exchangeSearchRequest == null) {
      exchangeSearchRequest = new ExchangeSearchRequest();
    }
    _exchangeSearchResult = exchangeMaster.search(exchangeSearchRequest);
  }

  @Override
  public Iterator<ManageableExchange> iterator() {
    return _exchangeSearchResult.getExchanges().iterator();
  }
}
