/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.external;

import java.util.Map;

/**
 * An abstract row builder class, builds a row from a datum
 */
public interface RowWriter<E> {

  /**
   * Constructs a row from the supplied datum.
   * @param datum The datum to convert
   * @return      The mapping from column names to contents of the current row
  */
  public Map<String, String> writeRow(E datum);

  public String[] getColumns();
}
