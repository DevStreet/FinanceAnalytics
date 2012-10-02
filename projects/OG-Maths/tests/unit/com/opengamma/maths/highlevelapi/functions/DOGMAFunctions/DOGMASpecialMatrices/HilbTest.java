/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialMatrices;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialMatrices.hilb.Hilb;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.ogblas.auxiliary.D1MACH;

/**
 * Tests the Hilbert matrix formation
 */
public class HilbTest {

  double[] hilb10x10 = new double[] {1.e000, 0.5e00, 0.3333333333333333, 0.25e0, 0.2e00, 0.1666666666666667, 0.1428571428571428, 0.1250000000000000, 0.1111111111111111, 0.1e00, 0.5e00,
      0.3333333333333333, 0.25e0, 0.2e00, 0.1666666666666667, 0.1428571428571428, 0.1250000000000000, 0.1111111111111111, 0.1e00, 0.0909090909090909, 0.3333333333333333, 0.25e0, 0.2e00,
      0.1666666666666667, 0.1428571428571428, 0.1250000000000000, 0.1111111111111111, 0.1e00, 0.0909090909090909, 0.0833333333333333, 0.25e0, 0.2e00, 0.1666666666666667, 0.1428571428571428,
      0.1250000000000000, 0.1111111111111111, 0.1e00, 0.0909090909090909, 0.0833333333333333, 0.0769230769230769, 0.2e00, 0.1666666666666667, 0.1428571428571428, 0.1250000000000000,
      0.1111111111111111, 0.1e00, 0.0909090909090909, 0.0833333333333333, 0.0769230769230769, 0.0714285714285714, 0.1666666666666667, 0.1428571428571428, 0.1250000000000000, 0.1111111111111111,
      0.1e00, 0.0909090909090909, 0.0833333333333333, 0.0769230769230769, 0.0714285714285714, 0.0666666666666667, 0.1428571428571428, 0.1250000000000000, 0.1111111111111111, 0.1e00,
      0.0909090909090909, 0.0833333333333333, 0.0769230769230769, 0.0714285714285714, 0.0666666666666667, 0.0625000000000000, 0.1250000000000000, 0.1111111111111111, 0.1e00, 0.0909090909090909,
      0.0833333333333333, 0.0769230769230769, 0.0714285714285714, 0.0666666666666667, 0.0625000000000000, 0.0588235294117647, 0.1111111111111111, 0.1e00, 0.0909090909090909, 0.0833333333333333,
      0.0769230769230769, 0.0714285714285714, 0.0666666666666667, 0.0625000000000000, 0.0588235294117647, 0.0555555555555556, 0.1e00, 0.0909090909090909, 0.0833333333333333, 0.0769230769230769,
      0.0714285714285714, 0.0666666666666667, 0.0625000000000000, 0.0588235294117647, 0.0555555555555556, 0.0526315789473684 };

  @Test
  public void hilbSimpleTest() {
    OGDoubleArray tmp = Hilb.hilb(10);
    assertArrayEquals(tmp.getData(),hilb10x10,10*D1MACH.four());
    assertTrue(tmp.getNumberOfColumns()==10);
    assertTrue(tmp.getNumberOfRows()==10);
  }

  @Test
  public void hilbSimple1x1Test() {
    OGDoubleArray tmp = Hilb.hilb(1);
    assertArrayEquals(tmp.getData(),new double[] {1},10*D1MACH.four());
  }  
  
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void badNTest() {
    Hilb.hilb(-1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void zeroNTest() {
    Hilb.hilb(0);
  }  
  
}
