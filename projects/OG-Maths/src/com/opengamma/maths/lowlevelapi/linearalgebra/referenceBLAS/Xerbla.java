/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
/**
* This code is a translation of the BLAS code provided by netlib.org.
* It has been manually edited based on the results of the f2j project.
* 
*/
package com.opengamma.maths.lowlevelapi.linearalgebra.referenceBLAS;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking.BLASAPIInterface;

/**
 * Does XERBLA, See {@linkplain BLASAPIInterface}
 */
public class Xerbla {

  public static void xerbla(String srname, int info) {
    String breakage = "On entry to " + srname + " parameter " + info + " had an illegal value";
    throw new MathsExceptionIllegalArgument(breakage);
  }

}
