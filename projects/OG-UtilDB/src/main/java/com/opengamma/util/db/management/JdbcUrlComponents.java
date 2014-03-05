/**
 * Copyright (C) 2014 - Daniel Kwiecinski daniel.kwiecinski@gmail.com
 * <p/>
 * All rights reserved.
 */
package com.opengamma.util.db.management;

import java.util.Map;

/**
 * The container class for storing jdbc url components.
 */
public class JdbcUrlComponents {
  private final String _host;
  private final int _port;
  private final String _catalog;
  private final String _user;
  private final String _password;
  private final Map<String, String> _properties;

  public JdbcUrlComponents(String host,
      int port,
      String catalog,
      String user,
      String password,
      Map<String, String> properties) {
    _host = host;
    _port = port;
    _catalog = catalog;
    _user = user;
    _password = password;
    _properties = properties;
  }

  public String getHost() {
    return _host;
  }

  public int getPort() {
    return _port;
  }

  public String getCatalog() {
    return _catalog;
  }

  public Map<String, String> getProperties() {
    return _properties;
  }

  public String getUser() {
    return _user;
  }

  public String getPassword() {
    return _password;
  }
}
