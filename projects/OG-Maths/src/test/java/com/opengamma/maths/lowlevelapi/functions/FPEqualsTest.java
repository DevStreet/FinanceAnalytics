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
 * Checks FPEquals works
 */
@Test
public class FPEqualsTest {

  public void TwoArraysSimpleTest() {
    double[] a = {1, 2, 3 };
    double[] b = {1, 2, 3 };
    double[] c = {1, 2 };
    assertTrue(FPEquals.equals(a, b));
    assertFalse(FPEquals.equals(a, c));
  }

  public void TwoArraysSimpleFuzzyTest() {
    double[] a = {1, 2, 3 };
    double[] b = {1, 2, 3 + 1e-16 };
    double[] c = {1, 2, 3 + 1e-13 };
    assertTrue(FPEquals.fuzzyEquals(a, b));
    assertFalse(FPEquals.fuzzyEquals(a, c));
  }

  public void TwoArraysSimpleFuzzyWTolTest() {
    double[] a = {1, 2, 3 };
    double[] b = {1, 2, 3 + 1e-13 };
    double[] c = {1, 2, 3 + 1e-12 };
    assertTrue(FPEquals.fuzzyEquals(a, b, 1e-12));
    assertFalse(FPEquals.fuzzyEquals(a, c, 1e-13));
  }

  public void TwoValuesSimpleFuzzyTest() {
    double a = 1 + 1e-15;
    double b = 1 + 2e-15;
    assertTrue(FPEquals.fuzzyEquals(a, b));
  }

  public void TwoValuesWtolSimpleFuzzyTest() {
    double a = 3;
    double b = 3 + 1e-13;
    double c = 3 + 1e-12;
    assertTrue(FPEquals.fuzzyEquals(a, b, 1e-12));
    assertFalse(FPEquals.fuzzyEquals(a, c, 1e-13));
  }

  public void TwoValuesSimpleFuzzyDynamicTolTest() {
    double a = 3e10;
    double b = 3.000000000000001e10;
    double c = 3.0000000000001e10;
    assertTrue(FPEquals.fuzzyEqualsDynamicTol(a, b));
    assertFalse(FPEquals.fuzzyEqualsDynamicTol(a, c));
  }

  public void TwoArraysSimpleFuzzyWDynamicTolTest() {
    double[] a = {1e10, 2e10, 3e10 };
    double[] b = {1e10, 2e10, 3.000000000000001e10 };
    double[] c = {1e10, 2e10, 3.0000000000001e10 };
    assertTrue(FPEquals.fuzzyEqualsDynamicTol(a, b));
    assertFalse(FPEquals.fuzzyEqualsDynamicTol(a, c));
  }
  
}
