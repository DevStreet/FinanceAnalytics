/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

/**
 * 
 */
@Test
public class FPEqualsTest {

  FPEquals fpq = FPEquals.getInstance();

  public void TwoArraysSimpleTest() {
    double[] a = {1, 2, 3 };
    double[] b = {1, 2, 3 };
    double[] c = {1, 2 };
    assertTrue(fpq.equals(a, b));
    assertFalse(fpq.equals(a, c));
  }

  public void TwoArraysSimpleFuzzyTest() {
    double[] a = {1, 2, 3 };
    double[] b = {1, 2, 3 + 1e-16 };
    double[] c = {1, 2, 3 + 1e-13 };
    assertTrue(fpq.fuzzyEquals(a, b));
    assertFalse(fpq.fuzzyEquals(a, c));
  }

  public void TwoArraysSimpleFuzzyWTolTest() {
    double[] a = {1, 2, 3 };
    double[] b = {1, 2, 3 + 1e-13 };
    double[] c = {1, 2, 3 + 1e-12 };
    assertTrue(fpq.fuzzyEquals(a, b, 1e-12));
    assertFalse(fpq.fuzzyEquals(a, c, 1e-13));
  }

  public void TwoValuesSimpleFuzzyTest() {
    double a = 1 + 1e-15;
    double b = 1 + 2e-15;
    assertTrue(fpq.fuzzyEquals(a, b));
  }

  public void TwoValuesWtolSimpleFuzzyTest() {
    double a = 3;
    double b = 3 + 1e-13;
    double c = 3 + 1e-12;
    assertTrue(fpq.fuzzyEquals(a, b, 1e-12));
    assertFalse(fpq.fuzzyEquals(a, c, 1e-13));
  }

  public void TwoValuesSimpleFuzzyDynamicTolTest() {
    double a = 3e10;
    double b = 3.000000000000001e10;
    double c = 3.0000000000001e10;
    assertTrue(fpq.fuzzyEqualsDynamicTol(a, b));
    assertFalse(fpq.fuzzyEqualsDynamicTol(a, c));
  }

  public void TwoArraysSimpleFuzzyWDynamicTolTest() {
    double[] a = {1e10, 2e10, 3e10 };
    double[] b = {1e10, 2e10, 3.000000000000001e10 };
    double[] c = {1e10, 2e10, 3.0000000000001e10 };
    assertTrue(fpq.fuzzyEqualsDynamicTol(a, b));
    assertFalse(fpq.fuzzyEqualsDynamicTol(a, c));
  }

}
