/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.security;

import com.opengamma.integration.copiernew.sheet.JodaBeanRowUtils;
import com.opengamma.integration.copiernew.sheet.RowReader;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.util.ArgumentChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SecurityRowReader implements RowReader<ManageableSecurity> {

  private Map<Class<? extends ManageableSecurity>, SecurityRowUtils> _rowUtils =
      new HashMap<Class<? extends ManageableSecurity>, SecurityRowUtils>();

  public SecurityRowReader(Class<? extends ManageableSecurity> ... clazzes) {
    ArgumentChecker.notEmpty(clazzes, "clazzes");
    ArgumentChecker.noNulls(clazzes, "clazzes");
    for (Class<? extends ManageableSecurity> clazz : clazzes) {
      _rowUtils.put(clazz, new SecurityRowUtils(clazz));
    }
  }

  @Override
  public ManageableSecurity readRow(Map<String, String> row) {
    if (_rowUtils.size() == 1) {
      return (ManageableSecurity) _rowUtils.values().iterator().next().constructBean(row);
    } else {
      // get sec type of current row

      String secType = row.get(SecurityRowUtils.SECTYPE_COLUMN_NAME);

      if (secType != null) {
        Class<?> clazz;
        try {
          clazz = Class.forName(secType.trim());
        } catch (ClassNotFoundException e) {
          return null;
        }
        return (ManageableSecurity) _rowUtils.get(clazz).constructBean(row);
      } else {
        return null;
      }
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
