/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.security;

import com.opengamma.integration.copiernew.external.JodaBeanRowUtils;
import com.opengamma.integration.copiernew.external.RowWriter;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.util.ArgumentChecker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class SecurityRowWriter implements RowWriter<ManageableSecurity> {

  private Map<Class<? extends ManageableSecurity>, SecurityRowUtils> _rowUtils =
      new HashMap<Class<? extends ManageableSecurity>, SecurityRowUtils>();

  public SecurityRowWriter(Class<? extends ManageableSecurity> ... clazzes) {
    ArgumentChecker.notEmpty(clazzes, "clazzes");
    ArgumentChecker.noNulls(clazzes, "clazzes");
    for (Class<? extends ManageableSecurity> clazz : clazzes) {
      _rowUtils.put(clazz, new SecurityRowUtils(clazz));
    }
  }

  @Override
  public Map<String, String> writeRow(ManageableSecurity security) {
    if (_rowUtils.containsKey(security.getClass())) {
      Map<String, String> result = _rowUtils.get(security.getClass()).constructRow(security);
      if (_rowUtils.size() > 1) {
        result.put(SecurityRowUtils.SECTYPE_COLUMN_NAME, security.getClass().getName());
      }
      return result;
    } else {
      return null;
    }
  }

  @Override
  public String[] getColumns() {
    SortedSet<String> result = new TreeSet<String>();
    if (_rowUtils.size() > 1) {
      result.add(SecurityRowUtils.SECTYPE_COLUMN_NAME);
    }
    for (JodaBeanRowUtils rowUtils : _rowUtils.values()) {
      result.addAll(Arrays.asList(rowUtils.getColumns()));
    }
    return result.toArray(new String[result.size()]);
  }
}
