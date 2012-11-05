/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.exchange;

import com.opengamma.integration.copiernew.external.JodaBeanRowUtils;
import com.opengamma.integration.copiernew.external.RowReader;
import com.opengamma.master.exchange.ManageableExchange;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRowReader implements RowReader<ManageableExchange> {


  private JodaBeanRowUtils _utils;
  @Override
  public ManageableExchange readRow(Map<String, String> row) {
    ManageableExchange.meta();
    _utils = new JodaBeanRowUtils(
      ManageableExchange.class,
      new String[] {
        "uniqueid",
        "detail"
      },
      new String[] {},
      new HashMap<Class<?>, Class<?>[]>()
    );

    return (ManageableExchange) _utils.constructBean(row);
  }

  @Override
  public String[] getColumns() {
    return _utils.getColumns();
  }
}
