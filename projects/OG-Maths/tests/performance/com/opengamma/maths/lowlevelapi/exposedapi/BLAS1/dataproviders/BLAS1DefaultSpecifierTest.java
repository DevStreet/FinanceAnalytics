/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.BLAS1.dataproviders;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Tests this data holder class
 */
public class BLAS1DefaultSpecifierTest {

  int[] _xlen1 = new int[] {1, 2, 3 };
  int[] _xlen2 = new int[] {4, 5, 6 };
  double _alpha = 0.4;

  @Test
  public void defaultConstructor() {
    new BLAS1DefaultSpecifier();
  }

  @Test
  public void constructorWithLengths() {
    BLAS1DefaultSpecifier d = new BLAS1DefaultSpecifier(_xlen1, _xlen2);
    assert (Arrays.equals(_xlen1, d.getLengthsAligned()));
    assert (Arrays.equals(_xlen2, d.getLengthsUnaligned()));
    assert (d.getAlpha() == 0.3);
  }

  @Test
  public void constructorWithLengthsAndAlpha() {
    BLAS1DefaultSpecifier d = new BLAS1DefaultSpecifier(_xlen1, _xlen2, _alpha);
    assert (Arrays.equals(_xlen1, d.getLengthsAligned()));
    assert (Arrays.equals(_xlen2, d.getLengthsUnaligned()));
    assert (d.getAlpha() == _alpha);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void constructor2lenWithNullLengths1() {
    new BLAS1DefaultSpecifier(null, _xlen2);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void constructor2lenWithNullLengths2() {
    new BLAS1DefaultSpecifier(_xlen1, null);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void constructor2lenAndAlphaWithNullLengths1() {
    new BLAS1DefaultSpecifier(null, _xlen2, _alpha);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void constructor2lenAndAlphaWithNullLengths2() {
    new BLAS1DefaultSpecifier(_xlen1, null, _alpha);
  }

}
