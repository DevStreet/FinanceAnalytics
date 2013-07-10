/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMALinearAlgebra.lu;


/**
 * Overloaded LU decomposition
 */
public enum LUCompute {

  /**
  * L compute L
  */
  L,
  /**
  * U compute U
  */
  U,
  /**
  * LU compute L and U such that L*U=A
  */
  LU,
  /**
  * LUP compute L, U and P such that L*U=P*A
  */
  LUP

}
