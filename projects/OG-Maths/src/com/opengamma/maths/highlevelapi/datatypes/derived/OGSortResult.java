/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.derived;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexArray;

/**
 * Holds the result of applying a sort() function to some data
 * @param <T> the type of array being returned
 */
public class OGSortResult<T> {
  private T _sortedArray;
  private OGIndexArray _index;

  /**
   * Gets the sortedArray.
   * @return the sortedArray
   */
  public T getSortedArray() {
    return _sortedArray;
  }

  /**
   * Gets the sort order index.
   * @return the sort order index
   */
  public OGIndexArray getIndex() {
    return _index;
  }
  
}
