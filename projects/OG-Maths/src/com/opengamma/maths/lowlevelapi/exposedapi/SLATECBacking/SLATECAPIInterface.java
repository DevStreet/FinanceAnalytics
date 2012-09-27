/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking;

import com.opengamma.maths.lowlevelapi.slatec.fnlib.DERF;

/**
 * Required behaviours of the SLATEC interface, cut down version, may be expanded.
 * API is as close as possible to the SLATEC interface
 */
public interface SLATECAPIInterface {

  /**
   * DERF(X) provides the ability to calculate the error function at position 'x'. 
   * It does this using Chebychev approximation mixed with calls to erfc() provided by {@link DERF}.
   * @param x the position at which to evaluate the error function.
   * @return the value of the error function at position 'x'
   */
  double derf(double x);

  /**
   * DERFC(X) provides the ability to calculate the complementary error function at position 'x'. 
   * It does this using a set of Chebychev approximations for various parts of the function.
   * @param x the position at which to evaluate the complimentary error function.
   * @return the value of the complimentary error function at position 'x'
   */
  double derfc(double x);

}
