/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

/**
 * Index item, a member of an index
 */
public interface IndexItem {

  /**
   * Linearise an index to an int[] for loop induction purposes
   * @param leadingDimension the leading dimension overwhich the index applies
   * @return the linearised index
   */
  int[] linearise(int leadingDimension);

}
