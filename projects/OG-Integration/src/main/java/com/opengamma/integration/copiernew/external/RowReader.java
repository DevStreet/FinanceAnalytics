/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.external;

import java.util.Map;

/**
 * An abstract row parser class, builds a datum from a row
 */
public interface RowReader<E> {

  /**
   * Constructs a datum from the supplied row.
   * @param row   The row to convert
   * @return      The constructed datum
  */
  public abstract E readRow(Map<String, String> row);

  public String[] getColumns();

}
