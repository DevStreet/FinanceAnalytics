/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

/**
 * Test methods in MathE 
 */
@Test
public class MathETest {

  // smoke test
  public void truncSmokeTest() {
    double[] input = new double[] {-6.25, -5.00, -3.75, -2.50, -1.25, 0.00, 1.25, 2.50, 3.75, 5.00, 6.25, 12345678.9e0, 1234567891234.56789, 1e300, 1e-20, -0.24 };
    double[] expected = new double[] {-6.0, -5.0, -3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 5.0, 6.0,12345678.0, 1234567891234.0, 1e300, 0.d, -0.d  };
    for (int i = 0; i < input.length; i++) {
      assertTrue(FPEquals.fuzzyEqualsDynamicTol(MathE.trunc(input[i]), expected[i]));
    }
  }

  public void truncNaNHandle() {
    assertTrue(Double.isNaN(MathE.trunc(MathBits.getSignallingNaN())));
  }

  public void truncInfHandle() {
    assertTrue(Double.isInfinite(MathE.trunc(MathBits.getPositiveInfinity())));
    assertTrue(MathE.trunc(MathBits.getPositiveInfinity()) > 0);
    assertTrue(Double.isInfinite(MathE.trunc(MathBits.getNegativeInfinity())));
    assertTrue(MathE.trunc(MathBits.getNegativeInfinity()) < 0);
  }

  public void truncCuspPoints() {
    assertTrue(MathE.trunc(MathBits.getLargestPositiveNormalMoreNegativeThanOne()) == 0);
    assertTrue(MathE.trunc(MathBits.getSmallestPositiveNormalMorePositiveThanOne()) == 1);
    assertTrue(MathE.trunc(MathBits.getSmallestNegativeNormalMoreNegativeThanMinusOne()) == -1);
    assertTrue(MathE.trunc(MathBits.getLargestNegativeNormalMorePositiveThanMinusOne()) == -0);
  }

  public void modfSmokeTest() {
    double[] a = new double[] {-6.250000000000000, -5.000000000000000, -3.750000000000000, -2.500000000000000, -1.250000000000000, 0.000000000000000, 1.250000000000000, 2.500000000000000,
        3.750000000000000, 5.000000000000000, 6.250000000000000 };
    double[] y1 = new double[a.length];
    double[] y2 = new double[a.length];
    double[] y_expected1 = new double[] {-6.000000000000000, -5.000000000000000, -3.000000000000000, -2.000000000000000, -1.000000000000000, 0.000000000000000, 1.000000000000000, 2.000000000000000,
        3.000000000000000, 5.000000000000000, 6.000000000000000 };
    double[] y_expected2 = new double[] {-0.250000000000000, 0.000000000000000, -0.750000000000000, -0.500000000000000, -0.250000000000000, 0.000000000000000, 0.250000000000000, 0.500000000000000,
        0.750000000000000, 0.000000000000000, 0.250000000000000 };
    double[] tmp = new double[1];
    for (int i = 0; i < a.length; i++) {
      y2[i] = MathE.modf(a[i], tmp);
      y1[i] = tmp[0];
    }
    assertTrue(FPEquals.fuzzyEquals(y1, y_expected1));
    assertTrue(FPEquals.fuzzyEquals(y2, y_expected2));
  }

  public void modfNaNHandle() {
    double[] tmp = new double[1];
    assertTrue(Double.isNaN(MathE.modf(MathBits.getSignallingNaN(), tmp)));
    assertTrue(Double.isNaN(tmp[0]));
  }

  public void modfInfHandle() {
    double[] tmp = new double[1];
    assertTrue(MathE.modf(MathBits.getPositiveInfinity(), tmp) == 0);
    assertTrue(tmp[0] > 0);
    assertTrue(Double.isInfinite(tmp[0]));
    assertTrue(MathE.modf(MathBits.getNegativeInfinity(), tmp) == 0);
    assertTrue(Double.isInfinite(tmp[0]));
    assertTrue(tmp[0] < 0);
  }

}
