/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew;

/**
 * Abstract visitor for portfolio copier
 */
public interface LogBack<E> {

  void info(String message, E datum);
  
  void info(String message);
  
  void error(String message);
}
