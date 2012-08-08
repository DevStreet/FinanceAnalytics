/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.exchange;

import com.opengamma.integration.copiernew.sheet.JodaBeanRowUtils;
import com.opengamma.integration.copiernew.sheet.RowWriter;
import com.opengamma.master.exchange.ManageableExchange;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRowWriter implements RowWriter<ManageableExchange> {

  private JodaBeanRowUtils _utils = new JodaBeanRowUtils(
    ManageableExchange.class,
    new String[] {
      "uniqueid",
      "detail"
    },
    new String[] {},
    new HashMap<Class<?>, Class<?>[]>()
  );

  @Override
  public Map<String, String> writeRow(ManageableExchange exchange) {
    return _utils.constructRow(exchange);

    // TODO: Iterate through details, writing a 'partial' row for each
  }

  @Override
  public String[] getColumns() {
    return _utils.getColumns();
  }

}
