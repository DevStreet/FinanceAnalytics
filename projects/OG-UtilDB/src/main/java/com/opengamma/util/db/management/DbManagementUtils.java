/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.util.db.management;

import static com.opengamma.util.db.management.AbstractDbManagement.extractJdbcScheme;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.util.ArgumentChecker;

/**
 * Utilities around {@link DbManagement} instances.
 */
public final class DbManagementUtils {

  private static final Map<String, DbManagement> s_jdbcVendorMap = new ConcurrentHashMap<String, DbManagement>();
  
  static {
    s_jdbcVendorMap.put("postgresql", PostgresDbManagement.getInstance());
    s_jdbcVendorMap.put("derby", DerbyDbManagement.getInstance());
    s_jdbcVendorMap.put("hsqldb", HSQLDbManagement.getInstance());
    s_jdbcVendorMap.put("sqlserver", SqlServer2008DbManagement.getInstance());
    s_jdbcVendorMap.put("oracle", Oracle11gDbManagement.getInstance());
  }
  
  /**
   * Hidden constructor
   */
  private DbManagementUtils() {
  }
  
  /**
   * Gets the {@link DbManagement} implementation for a JDBC vendor name.
   * 
   * @param jdbcUrl  the JDBC url, not null
   * @return the {@link DbManagement} implementation, not null
   * @throws IllegalArgumentException  if the given JDBC vendor name is unsupported
   */
  public static DbManagement getDbManagement(String jdbcUrl) {
    ArgumentChecker.notNull(jdbcUrl, "jdbcUrl");
    String jdbcScheme = extractJdbcScheme(jdbcUrl);
    DbManagement dbManagement = s_jdbcVendorMap.get(jdbcScheme);
    if (dbManagement == null) {
      throw new IllegalArgumentException("Unsupported JDBC vendor name '" + jdbcScheme + "'");
    }
    return dbManagement;
  }

}
