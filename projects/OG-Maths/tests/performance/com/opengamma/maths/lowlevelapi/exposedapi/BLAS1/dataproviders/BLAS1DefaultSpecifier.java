/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.BLAS1.dataproviders;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Holds default specifiers for running performance tests. 
 * Applicable to BLAS1 routines that need "n", "x", "y", "alpha", "incx", "incy"
 */
public class BLAS1DefaultSpecifier {
  private int[] _xlengthsAligned = {4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072 };
  private int[] _xlengthsUnaligned = {3, 5, 11, 17, 29, 61, 131, 253, 511, 1023, 2049, 4097, 8191, 16383, 32767, 65535, 131067 };
  private int[] _incxes = {1, 2, 3, -1, -2 };

  private static double s_alphaDefault = 0.3;
  private double _alpha = 0.3;

  /** 
   * default constructor gets defaults!
   */
  public BLAS1DefaultSpecifier() {
  }

  /**
   * Sets the lengths of data to try 
   * @param xlengthsAligned lengths in the forms 2^k, k \in Z+.
   * @param xlengthsUnaligned  lengths in the forms 2^k - P, k \in Z+, P is some int to make the lengths not align to cacheline boundaries.
   */
  public BLAS1DefaultSpecifier(int[] xlengthsAligned, int[] xlengthsUnaligned) {
    this(xlengthsAligned, xlengthsUnaligned, s_alphaDefault);
  }

  /**
   * Sets the data to try 
   * @param xlengthsAligned lengths in the forms 2^k, k \in Z+.
   * @param xlengthsUnaligned  lengths in the forms 2^k - P, k \in Z+, P is some int to make the lengths not align to cacheline boundaries.
   * @param alpha the scaling parameter 
   */
  public BLAS1DefaultSpecifier(int[] xlengthsAligned, int[] xlengthsUnaligned, double alpha) {
    if (xlengthsAligned == null) {
      throw new MathsExceptionIllegalArgument("xlengthsUnaligned should not be null");
    }
    if (xlengthsUnaligned == null) {
      throw new MathsExceptionIllegalArgument("xlengthsUnaligned should not be null");
    }
    int n = xlengthsAligned.length;
    _xlengthsAligned = new int[n];
    _xlengthsUnaligned = new int[n];    
    System.arraycopy(xlengthsAligned, 0, _xlengthsAligned, 0, n);
    System.arraycopy(xlengthsUnaligned, 0, _xlengthsUnaligned, 0, n);
    _alpha = alpha;
  }

  /**
   * Gets the lengthsAligned.
   * @return the lengthsAligned
   */
  public int[] getLengthsAligned() {
    return _xlengthsAligned;
  }

  /**
   * Gets the incxes.
   * @return the incxes
   */
  public int[] getIncxes() {
    return _incxes;
  }

  /**
   * Gets the lengthsUnaligned.
   * @return the lengthsUnaligned
   */
  public int[] getLengthsUnaligned() {
    return _xlengthsUnaligned;
  }

  /**
   * Gets the alpha.
   * @return the alpha
   */
  public double getAlpha() {
    return _alpha;
  }

}
