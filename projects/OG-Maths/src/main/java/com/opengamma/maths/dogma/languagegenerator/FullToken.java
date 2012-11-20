/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator;

/**
 * 
 */
/**
 * A function token description for the dogma lang  
 */
public class FullToken {
  private TypeToken _token;
  private String _canonicalName, _simpleName;
  private Class<?> _clazz;

  FullToken(String canonicalName, String simpleName, TypeToken type, Class<?> interfaceclazz) {
    _canonicalName = new String(canonicalName);
    _simpleName = new String(simpleName);
    _token = type;
    _clazz = interfaceclazz;
  }

  /**
   * Gets the canonical name.
   * @return the canonical name
   */
  public String getCanonicalName() {
    return _canonicalName;
  }

  /**
   * Gets the canonical name.
   * @return the canonical name
   */
  public String getSimpleName() {
    return _simpleName;
  }

  /**
   * Gets the token.
   * @return the token
   */
  public TypeToken getToken() {
    return _token;
  }

  /**
   * Gets the class of the interface.
   * @return the class of the interface
   */
  public Class<?> getClazz() {
    return _clazz;
  }

  @Override
  public String toString() {
    String tmp = "";
    tmp = tmp + "Name = " + _canonicalName + "\n";
    tmp = tmp + _token.toString();
    return tmp;
  }

}
