/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMAArithmetic;
import static org.testng.Assert.assertTrue;

/**
 * Tests the times() function in DOGMA
 */
public class DOGMAtimesTest {

  DOGMAArithmetic DA = new DOGMAArithmetic();

  OGDoubleArray OGD1x1 = new OGDoubleArray(new double[][] {{10 } });
  OGDoubleArray OGD3x5A = new OGDoubleArray(new double[][] { {1, -2, 3, -4, 5 }, {-6, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 } });
  OGDoubleArray OGD3x5B = new OGDoubleArray(new double[][] { {10, -20, 30, -40, 50 }, {-60, 70, -80, 90, -100 }, {110, -120, 130, -140, 150 } });
  OGDoubleArray OGD5x2 = new OGDoubleArray(new double[][] { {1, 2 }, {3, 4 }, {5, 6 }, {7, 8 }, {9, 10 } });

  // answers
  OGDoubleArray OGD3x5timesOGD1x1 = new OGDoubleArray(new double[][] { {10, -20, 30, -40, 50 }, {-60, 70, -80, 90, -100 }, {110, -120, 130, -140, 150 } });
  OGDoubleArray OGD1x1timesOGD3x5 = new OGDoubleArray(new double[][] { {10, -20, 30, -40, 50 }, {-60, 70, -80, 90, -100 }, {110, -120, 130, -140, 150 } });
  OGDoubleArray OGD3x5Atimes3x5A = new OGDoubleArray(new double[][] { {1, 4, 9, 16, 25 }, {36, 49, 64, 81, 100 }, {121, 144, 169, 196, 225 } });
  OGDoubleArray OGD3x5Atimes3x5B = new OGDoubleArray(new double[][] { {10, 40, 90, 160, 250 }, {360, 490, 640, 810, 1000 }, {1210, 1440, 1690, 1960, 2250 } });
  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testNullinput1okinput2() {
    DA.times(null, new OGDoubleArray(1));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testNullinput2okinput1() {
    DA.times(new OGDoubleArray(1), null);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testNullinput3okinput1and2() {
    DA.times(new OGDoubleArray(1), new OGDoubleArray(1), null);
  }

  // OGDoubleArray and OGDoubleArray
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testNonConformantArgsTest() {
    DA.times(OGD5x2, OGD3x5A);
  }

  @Test
  public void testDouble3x5AtimesDouble1x1Test() {
    OGArraySuper<Number> tmp = DA.times(OGD3x5A, OGD1x1);
    assertTrue(tmp.equals(OGD3x5timesOGD1x1));
  }

  @Test
  public void testDouble1x1timesDouble3x5ATest() {
    OGArraySuper<Number> tmp = DA.times(OGD1x1, OGD3x5A);
    assertTrue(tmp.equals(OGD3x5timesOGD1x1));
  }

  @Test
  public void testDouble3x5AtimesDouble3x5ATest() {
    OGArraySuper<Number> tmp = DA.times(OGD3x5A, OGD3x5A);
    assertTrue(tmp.equals(OGD3x5Atimes3x5A));
    
  }

  @Test
  public void testDouble3x5AtimesDouble3x5BTest() {
    OGArraySuper<Number> tmp = DA.times(OGD3x5A, OGD3x5B);
    assertTrue(tmp.equals(OGD3x5Atimes3x5B));
  }

  // OGDoubleArray operate on natives
  @Test
  public void testDouble3x5AtimesdoubleTest() {
    OGArraySuper<Number> tmp = DA.times(OGD3x5A, 10.e0);
    assertTrue(tmp.equals(OGD3x5timesOGD1x1));
  }

  @Test
  public void testDouble3x5AtimesintTest() {
    OGArraySuper<Number> tmp = DA.times(OGD3x5A, 10);
    assertTrue(tmp.equals(OGD3x5timesOGD1x1));
  }  
  
}
