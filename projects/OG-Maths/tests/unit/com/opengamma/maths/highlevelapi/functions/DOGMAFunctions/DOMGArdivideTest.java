/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMAArithmetic;

/**
 * Tests the rdivide() function in DOGMA 
 */
public class DOMGArdivideTest {
  DOGMAArithmetic DA = new DOGMAArithmetic();

  // OGDoubles
  OGDoubleArray OGD1x1 = new OGDoubleArray(new double[][] {{10 } });
  OGDoubleArray OGD3x5A = new OGDoubleArray(new double[][] { {1, -2, 3, -4, 5 }, {-6, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 } });
  OGDoubleArray OGD3x5B = new OGDoubleArray(new double[][] { {10, -20, 30, -40, 50 }, {-60, 70, -80, 90, -100 }, {110, -120, 130, -140, 150 } });
  OGDoubleArray OGD5x2 = new OGDoubleArray(new double[][] { {1, 2 }, {3, 4 }, {5, 6 }, {7, 8 }, {9, 10 } });

  // OGSparse
  OGSparseArray OGS1x1 = new OGSparseArray(new double[][] {{10 } });
  OGSparseArray OGS3x5A = new OGSparseArray(new double[][] { {1, -2, 0, -4, 0 }, {0, 7, 0, 9, -10 }, {0, 0, 13, 0, 0 } });
  OGSparseArray OGS3x5B = new OGSparseArray(new double[][] { {10, -20, 0, -40, 0 }, {0, 70, -80, 90, -100 }, {0, 0, 130, 0, 0 } });
  OGSparseArray OGS5x2 = new OGSparseArray(new double[][] { {1, 0 }, {0, 4 }, {0, 0 }, {0, 8 }, {0, 0 } });

  // answers
  OGDoubleArray OGD3x5ArdivOGD1x1 = new OGDoubleArray(new double[][] { {0.1, -0.2, .3, -0.4, 0.5 }, {-0.6, 0.7, -0.8, 0.9, -1.0 }, {1.1, -1.2, 1.3, -1.4, 1.5 } });
  OGDoubleArray OGD1x1rdivOGD3x5A = new OGDoubleArray(new double[][] { {10., -5., 3.3333333333333335, -2.5, 2. },
      {-1.6666666666666667, 1.4285714285714286, -1.25, 1.1111111111111112, -1. },
      {0.9090909090909091, -0.8333333333333334, 0.7692307692307693, -0.7142857142857143, 0.6666666666666666 } }
      );
  OGDoubleArray OGD3x5Ardiv3x5A = new OGDoubleArray(new double[][] { {1., 1., 1., 1., 1. }, {1., 1., 1., 1., 1. }, {1., 1., 1., 1., 1. } });
  OGDoubleArray OGD3x5Ardiv3x5B = new OGDoubleArray(new double[][] { {0.1, 0.1, 0.1, 0.1, 0.1 }, {0.1, 0.1, 0.1, 0.1, 0.1 }, {0.1, 0.1, 0.1, 0.1, 0.1 } });
  OGDoubleArray OGD1x1rdivOGS3x5A = new OGDoubleArray(new double[][] { {10., -5., Double.POSITIVE_INFINITY, -2.5, Double.POSITIVE_INFINITY },
      {Double.POSITIVE_INFINITY, 1.4285714285714286, Double.POSITIVE_INFINITY, 1.1111111111111112, -1. },
      {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.7692307692307693, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY } });

  OGDoubleArray OGD3x5ArdivOGS3x5A = new OGDoubleArray(new double[][] { {1., 1., Double.POSITIVE_INFINITY, 1., Double.POSITIVE_INFINITY },
      {Double.NEGATIVE_INFINITY, 1., Double.NEGATIVE_INFINITY, 1., 1. },
      {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 1., Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY } }
      );

  OGDoubleArray OGDAllNaN = new OGDoubleArray(new double[][] { {Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN }, {Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN },
      {Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN } });

  OGSparseArray OGS3x5ArdivOGD1x1 = new OGSparseArray(new double[][] { {0.1, -0.2, 0.0, -0.4, 0.0 }, {0.0, 0.7, 0.0, 0.9, -1.0 }, {0.0, 0.0, 1.3, 0.0, 0.0 } });
  OGDoubleArray OGS1x1rdivOGD3x5 = new OGDoubleArray(new double[][] { {10., -5., 3.3333333333333335, -2.50, 2. }, {-1.6666666666666667, 1.4285714285714286, -1.25, 1.1111111111111112, -1. },
      {0.9090909090909091, -0.8333333333333334, 0.7692307692307693, -0.7142857142857143, 0.6666666666666666 } });

  OGSparseArray OGS3x5ArdivOGD3x5A = new OGSparseArray(new double[][] { {1., 1., 0., 1., 0. }, {0., 1., 0., 1., 1. }, {0., 0., 1., 0., 0. } });
  OGDoubleArray OGS3x5ArdivOGS3x5B = new OGDoubleArray(new double[][] { {0.10, 0.10, Double.NaN, 0.10, Double.NaN }, {Double.NaN, 0.10, -0.00, 0.10, 0.10 },
      {Double.NaN, Double.NaN, 0.10, Double.NaN, Double.NaN } });

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testNullinput1okinput2() {
    DA.rdivide(null, new OGDoubleArray(1));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testNullinput2okinput1() {
    DA.rdivide(new OGDoubleArray(1), null);
  }

  // Operations on classes
  // OGDoubleArray and OGDoubleArray
  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void testOGDandOGDNonConformantArgsTest() {
    DA.rdivide(OGD5x2, OGD3x5A);
  }

  @Test
  public void testDouble3x5ArdivDouble1x1Test() {
    OGArraySuper<Number> tmp = DA.rdivide(OGD3x5A, OGD1x1);
    assertTrue(tmp.equals(OGD3x5ArdivOGD1x1));
  }

  @Test
  public void testDouble1x1rdivDouble3x5ATest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGD1x1, OGD3x5A);
    assertTrue(tmp.equals(OGD1x1rdivOGD3x5A));
  }

  @Test
  public void testDouble3x5ArdivDouble3x5ATest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGD3x5A, OGD3x5A);
    assertTrue(tmp.equals(OGD3x5Ardiv3x5A));
  }

  @Test
  public void testDouble3x5ArdivDouble3x5BTest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGD3x5A, OGD3x5B);
    assertTrue(tmp.equals(OGD3x5Ardiv3x5B));
  }

  // OGDoubleArray and OGSparseArray
  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void testOGDandOGSNonConformantArgsTest() {
    DA.rdivide(OGD5x2, OGS3x5A);
  }

  @Test
  public void testDouble3x5ArdivSparse1x1Test() {
    OGArraySuper<Number> tmp = DA.rdivide(OGD3x5A, OGS1x1);
    assertTrue(tmp.equals(OGD3x5ArdivOGD1x1));
  }

  @Test
  public void testDouble1x1rdivSparse3x5ATest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGD1x1, OGS3x5A);
    assertTrue(tmp.equals(OGD1x1rdivOGS3x5A));
  }

  @Test
  public void testDouble3x5ArdivSparse3x5ATest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGD3x5A, OGS3x5A);
    assertTrue(tmp.equals(OGD3x5ArdivOGS3x5A));
  }

  @Test
  public void testDoublerdivSparseIEEENaNdivisionTest() {
    OGArraySuper<Number> tmp = DA.rdivide(new OGDoubleArray(Double.NaN), OGS3x5A);
    assertTrue(tmp.equals(OGDAllNaN));
  }

  // OGSparseArray and OGDoubleArray
  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void testOGSandOGDNonConformantArgsTest() {
    DA.rdivide(OGS3x5A, OGD5x2);
  }

  @Test
  public void testSparse3x5ArdivDouble1x1Test() {
    OGArraySuper<Number> tmp = DA.rdivide(OGS3x5A, OGD1x1);
    assertTrue(tmp.equals(OGS3x5ArdivOGD1x1));
  }

  @Test
  public void testSparse1x1rdivDouble3x5ATest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGS1x1, OGD3x5A);
    assertTrue(tmp.equals(OGS1x1rdivOGD3x5));
  }

  @Test
  public void testSparse3x5ArdivDouble3x5ATest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGS3x5A, OGD3x5A);
    assertTrue(tmp.equals(OGS3x5ArdivOGD3x5A));
  }

  // OGSparseArray and OGSparseArray
  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void testOGSandOGSNonConformantArgsTest() {
    DA.rdivide(OGS3x5A, OGD5x2);
  }

  @Test
  public void testSparse3x5ArdivSparse1x1Test() {
    OGArraySuper<Number> tmp = DA.rdivide(OGS3x5A, OGD1x1);
    assertTrue(tmp.equals(OGS3x5ArdivOGD1x1));
  }

  @Test
  public void testSparse1x1rdivSparse3x5ATest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGS1x1, OGD3x5A);
    assertTrue(tmp.equals(OGS1x1rdivOGD3x5));
  }

  @Test
  public void testSparse3x5ArdivSparse3x5BTest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGS3x5A, OGS3x5B);
    assertTrue(tmp.equals(OGS3x5ArdivOGS3x5B));
  }

  // Operations on Natives
  // OGDoubleArray operate on natives
  @Test
  public void testDouble3x5ArdividedoubleTest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGD3x5A, 10.e0);
    assertTrue(tmp.equals(OGD3x5ArdivOGD1x1));
  }

  @Test
  public void testDouble3x5ArdivideintTest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGD3x5A, 10);
    assertTrue(tmp.equals(OGD3x5ArdivOGD1x1));
  }

  @Test
  public void testdoublerdivideDouble3x5ATest() {
    OGArraySuper<Number> tmp = DA.rdivide(10.e0, OGD3x5A);
    assertTrue(tmp.equals(OGD1x1rdivOGD3x5A));
  }

  @Test
  public void testintrdivideDouble3x5ATest() {
    OGArraySuper<Number> tmp = DA.rdivide(10, OGD3x5A);
    assertTrue(tmp.equals(OGD1x1rdivOGD3x5A));
  }
  // OGSparseArray operate on natives
  @Test
  public void testSparse3x5ArdividedoubleTest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGS3x5A, 10.e0);
    assertTrue(tmp.equals(OGS3x5ArdivOGD1x1));
  }

  @Test
  public void testSparse3x5ArdivideintTest() {
    OGArraySuper<Number> tmp = DA.rdivide(OGS3x5A, 10);
    assertTrue(tmp.equals(OGS3x5ArdivOGD1x1));
  }

  @Test
  public void testdoublerdivideSparse3x5ATest() {
    OGArraySuper<Number> tmp = DA.rdivide(10.e0, OGS3x5A); 
    assertTrue(tmp.equals(OGD1x1rdivOGS3x5A));
  }

  @Test
  public void testintrdivideSparse3x5ATest() {
    OGArraySuper<Number> tmp = DA.rdivide(10, OGS3x5A);
    assertTrue(tmp.equals(OGD1x1rdivOGS3x5A));
  }  
  
}
