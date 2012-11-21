/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions;

import static org.testng.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.dogma.DogmaLanguage;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Tests the times() function in DOGMA
 */
public class DOGMAtimesTest {
  Logger foo = LoggerFactory.getLogger(getClass());

  DogmaLanguage DA = new DogmaLanguage(true);

  // OGDoubles
  OGMatrix OGD1x1 = new OGMatrix(new double[][] {{10 } });
  OGMatrix OGD3x5A = new OGMatrix(new double[][] { {1, -2, 3, -4, 5 }, {-6, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 } });
  OGMatrix OGD3x5B = new OGMatrix(new double[][] { {10, -20, 30, -40, 50 }, {-60, 70, -80, 90, -100 }, {110, -120, 130, -140, 150 } });
  OGMatrix OGD5x2 = new OGMatrix(new double[][] { {1, 2 }, {3, 4 }, {5, 6 }, {7, 8 }, {9, 10 } });

  // OGSparse
  OGSparseMatrix OGS1x1 = new OGSparseMatrix(new double[][] {{10 } });
  OGSparseMatrix OGS3x5A = new OGSparseMatrix(new double[][] { {1, -2, 0, -4, 0 }, {0, 7, 0, 9, -10 }, {0, 0, 13, 0, 0 } });
  OGSparseMatrix OGS3x5B = new OGSparseMatrix(new double[][] { {10, -20, 0, -40, 0 }, {0, 70, -80, 90, -100 }, {0, 0, 130, 0, 0 } });
  OGSparseMatrix OGS5x2 = new OGSparseMatrix(new double[][] { {1, 0 }, {0, 4 }, {0, 0 }, {0, 8 }, {0, 0 } });

  // answers
  OGMatrix OGD3x5timesOGD1x1 = new OGMatrix(new double[][] { {10, -20, 30, -40, 50 }, {-60, 70, -80, 90, -100 }, {110, -120, 130, -140, 150 } });
  OGMatrix OGD1x1timesOGD3x5 = new OGMatrix(new double[][] { {10, -20, 30, -40, 50 }, {-60, 70, -80, 90, -100 }, {110, -120, 130, -140, 150 } });
  OGMatrix OGD3x5Atimes3x5A = new OGMatrix(new double[][] { {1, 4, 9, 16, 25 }, {36, 49, 64, 81, 100 }, {121, 144, 169, 196, 225 } });
  OGMatrix OGD3x5Atimes3x5B = new OGMatrix(new double[][] { {10, 40, 90, 160, 250 }, {360, 490, 640, 810, 1000 }, {1210, 1440, 1690, 1960, 2250 } });

  OGMatrix OGD3x5timesOGS1x1 = new OGMatrix(new double[][] { {10, -20, 30, -40, 50 }, {-60, 70, -80, 90, -100 }, {110, -120, 130, -140, 150 } });
  OGSparseMatrix OGD1x1timesOGS3x5 = new OGSparseMatrix(new double[][] { {10, -20, 0, -40, 0 }, {0, 70, 0, 90, -100 }, {0, 0, 130, 0, 0 } });
  OGSparseMatrix OGD3x5AtimesOGS3x5A = new OGSparseMatrix(new double[][] { {1, 4, 0, 16, 0 }, {0, 49, 0, 81, 100 }, {0, 0, 169, 0, 0 } });

  OGSparseMatrix OGS3x5AtimesOGS3x5A = new OGSparseMatrix(new double[][] { {1, 4, 0, 16, 0 }, {0, 49, 0, 81, 100 }, {0, 0, 169, 0, 0 } });
  OGSparseMatrix OGS3x5AtimesOGD1x1 = new OGSparseMatrix(new double[][] { {10, -20, 0, -40, 0 }, {0, 70, 0, 90, -100 }, {0, 0, 130, 0, 0 } });

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testNullinput1okinput2() {
    OGArray<? extends Number> tmp = null;
    DA.times(tmp, new OGMatrix(1));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testNullinput2okinput1() {
    OGArray<? extends Number> tmp = null;
    DA.times(new OGMatrix(1), tmp);
  }

  //  // Operations on classes
  //  // OGDoubleArray and OGDoubleArray
  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void testOGDandOGDNonConformantArgsTest() {
    DA.times(OGD5x2, OGD3x5A);
  }

  @Test
  public void testDouble3x5AtimesDouble1x1Test() {
    OGArray<? extends Number> tmp = DA.times(OGD3x5A, OGD1x1);
    assertTrue(tmp.equals(OGD3x5timesOGD1x1));
  }

  @Test
  public void testDouble1x1timesDouble3x5ATest() {
    OGArray<? extends Number> tmp = DA.times(OGD1x1, OGD3x5A);
    assertTrue(tmp.equals(OGD3x5timesOGD1x1));
  }

  @Test
  public void testDouble3x5AtimesDouble3x5ATest() {
    OGArray<? extends Number> tmp = DA.times(OGD3x5A, OGD3x5A);
    assertTrue(tmp.equals(OGD3x5Atimes3x5A));

  }

  @Test
  public void testDouble3x5AtimesDouble3x5BTest() {
    OGArray<? extends Number> tmp = DA.times(OGD3x5A, OGD3x5B);
    assertTrue(tmp.equals(OGD3x5Atimes3x5B));
  }

  // OGDoubleArray and OGSparseArray
  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void testOGDandOGSNonConformantArgsTest() {
    DA.times(OGD5x2, OGS3x5A);
  }

  @Test
  public void testDouble3x5AtimesSparse1x1Test() {
    OGArray<? extends Number> tmp = DA.times(OGD3x5A, OGS1x1);
    assertTrue(tmp.equals(OGD3x5timesOGS1x1));
  }

  @Test
  public void testDouble1x1timesSparse3x5ATest() {
    OGArray<? extends Number> tmp = DA.times(OGD1x1, OGS3x5A);
    assertTrue(tmp.equals(OGD1x1timesOGS3x5));
  }

  @Test
  public void testDouble3x5AtimesSparse3x5ATest() {
    OGArray<? extends Number> tmp = DA.times(OGD3x5A, OGS3x5A);
    assertTrue(tmp.equals(OGD3x5AtimesOGS3x5A));
  }

  // OGSparseArray and OGDoubleArray
  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void testOGSandOGDNonConformantArgsTest() {
    DA.times(OGS3x5A, OGD5x2);
  }

  @Test
  public void testSparse3x5AtimesDouble1x1Test() {
    OGArray<? extends Number> tmp = DA.times(OGS3x5A, OGD1x1);
    assertTrue(tmp.equals(OGS3x5AtimesOGD1x1));
  }

  @Test
  public void testSparse1x1timesDouble3x5ATest() {
    OGArray<? extends Number> tmp = DA.times(OGS1x1, OGD3x5A);
    assertTrue(tmp.equals(OGD1x1timesOGD3x5));
  }

  @Test
  public void testSparse3x5AtimesDouble3x5ATest() {
    OGArray<? extends Number> tmp = DA.times(OGS3x5A, OGD3x5A);
    assertTrue(tmp.equals(OGD3x5AtimesOGS3x5A));
  }

  // OGSparseArray and OGSparseArray 
  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void testOGSandOSDNonConformantArgsTest() {
    DA.times(OGS3x5A, OGS5x2);
  }

  @Test
  public void testSparse3x5AtimesSparse1x1Test() {
    OGArray<? extends Number> tmp = DA.times(OGS3x5A, OGS1x1);
    assertTrue(tmp.equals(OGS3x5AtimesOGD1x1));
  }

  @Test
  public void testSparse1x1timesSparse3x5ATest() {
    OGArray<? extends Number> tmp = DA.times(OGS1x1, OGS3x5A);
    assertTrue(tmp.equals(OGD1x1timesOGS3x5));
  }

  @Test
  public void testSparse3x5AtimesSparse3x5ATest() {
    OGArray<? extends Number> tmp = DA.times(OGS3x5A, OGS3x5A);
    assertTrue(tmp.equals(OGS3x5AtimesOGS3x5A));
  }

  // Operations on Natives
  // OGDoubleArray operate on natives
  @Test
  public void testDouble3x5AtimesdoubleTest() {
    OGArray<? extends Number> tmp = DA.times(OGD3x5A, 10.e0);
    assertTrue(tmp.equals(OGD3x5timesOGD1x1));
  }

  @Test
  public void testDouble3x5AtimesintTest() {
    OGArray<? extends Number> tmp = DA.times(OGD3x5A, 10);
    assertTrue(tmp.equals(OGD3x5timesOGD1x1));
  }

  @Test
  public void testdoubletimesDouble3x5ATest() {
    OGArray<? extends Number> tmp = DA.times(10.e0, OGD3x5A);
    assertTrue(tmp.equals(OGD3x5timesOGD1x1));
  }

  @Test
  public void testinttimesDouble3x5ATest() {
    OGArray<? extends Number> tmp = DA.times(10, OGD3x5A);
    assertTrue(tmp.equals(OGD3x5timesOGD1x1));
  }

  // OGSparseArray operate on natives
  @Test
  public void testSparse3x5AtimesdoubleTest() {
    OGArray<? extends Number> tmp = DA.times(OGS3x5A, 10.e0);
    assertTrue(tmp.equals(OGS3x5AtimesOGD1x1));
  }

  @Test
  public void testSparse3x5AtimesintTest() {
    OGArray<? extends Number> tmp = DA.times(OGS3x5A, 10);
    assertTrue(tmp.equals(OGS3x5AtimesOGD1x1));
  }

  @Test
  public void testdoubletimesSparse3x5ATest() {
    OGArray<? extends Number> tmp = DA.times(10.e0, OGS3x5A);
    assertTrue(tmp.equals(OGS3x5AtimesOGD1x1));
  }

  @Test
  public void testinttimesSparse3x5ATest() {
    OGArray<? extends Number> tmp = DogmaLanguage.times(10, OGS3x5A);
    assertTrue(tmp.equals(OGS3x5AtimesOGD1x1));
  }

}
