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

  /**
   * ZABS(X) Computes the absolute value or magnitude of a double precision complex number (stored as a two element double [])
   * @param zr the real part of the complex number of which the absolute value shall be computed
   * @param zi the imaginary part of the complex number of which the absolute value shall be computed
   * @return the absolute value of the complex number
   */
  double zabs(double zr, double zi);

  /**
   * Computes the logarithm of a complex number, wrapping the argument appropriately.
   * @param ar the real part of the complex number of which the logarithm shall be computed
   * @param ai the imaginary part of the complex number of which the logarithm shall be computed
   * @param br br[0] the real part of the complex number resulting from taking the logarithm of ar+ai*i
   * @param bi bi[0] the imaginary part of the complex number resulting from taking the logarithm of ar+ai*i
   * @param ierr ierr[0] contains 0 if successful, 1 otherwise.
   */
  void zlog(double ar, double ai, double[] br, double[] bi, int[] ierr);

}
