/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialMatrices;

import static org.testng.Assert.assertTrue;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.ogblas.auxiliary.D1MACH;

/**
 * Tests the Rosser matrix generator
 */
public class RosserTest {

  private static double[] s_theRosserData = new double[] {611, 196, -192, 407, -8, -52, -49, 29, 196, 899, 113, -192, -71, -43, -8, -44, -192, 113, 899, 196, 61, 49, 8, 52, 407, -192, 196, 611, 8, 44,
    59, -23, -8, -71, 61, 8, 411, -599, 208, 208, -52, -43, 49, 44, -599, 411, 208, 208, -49, -8, 8, 59, 208, 208, 99, -911, 29, -44, 52, -23, 208, 208, -911, 99 };

  @Test
  public static void testRosser() {
    final int n = 8;
    OGMatrix tmp = Rosser.rosser();
    assertArrayEquals(tmp.getData(), s_theRosserData, D1MACH.four());
    assertTrue(tmp.getNumberOfColumns() == n);
    assertTrue(tmp.getNumberOfRows() == n);
  }
  
  
}
