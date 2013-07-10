/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMALinearAlgebra.eig;


/**
 * Eigen-decomposition compute flags
 */
public enum EIGCompute {

  /**
  * V compute V, the eigenvectors
  */
  V,
  /**
  * LAMBDA compute LAMBDA the eigenvalues
  */
  LAMBDA,
  /**
  * V_LAMBDA compute the eigenvectors and eigenvalues 
  */
  V_LAMBDA

}
