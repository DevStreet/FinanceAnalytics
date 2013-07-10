/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMALinearAlgebra.svd;


/**
 * Overloaded Svd
 */
public enum SVDCompute {

  /**
   * U compute U
   */
  U,
  /**
  * S compute S
  */
  S,
  /**
  * V compute V
  */
  V,
  /**
  * US compute U and S
  */
  US,
  /**
  * UV compute U and V
  */
  UV,
  /**
  * V compute S and V
  */
  SV,
  /**
  *  compute U, S and V
  */
  USV

}
