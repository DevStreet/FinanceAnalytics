/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import java.util.Arrays;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.ogblas.auxiliary.D1MACH;

/**
 * Tests the {@link OGDiagonalMatrix} Class
 */
public class OGDiagonalArrayTest {

  double[] data4x3diagd = new double[] {1, 2, 3 };
  double[][] data4x3full = new double[][] { {1.00, 0.00, 0.00 }, {0.00, 2.00, 0.00 }, {0.00, 0.00, 3.00 }, {0.00, 0.00, 0.00 } };

  // sending in null ptr double[] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrConstructorNullPtrTest() {
    double[] tmp = null;
    new OGDiagonalMatrix(tmp);
  }

  // sending in null ptr double[], int, int constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrIntIntConstructorNullPtrTest() {
    double[] tmp = null;
    new OGDiagonalMatrix(tmp, 1, 1);
  }

  // sending in ok double[] constructor
  @Test
  public void testDoublePtrConstructorInternalDataTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd);
    assertTrue(D.getClass() == OGDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), data4x3diagd));
    assertTrue(D.getNumberOfRows() == 3);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // sending in bad rows double[], int, int constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrIntIntConstructorBadRowsDataTest() {
    new OGDiagonalMatrix(data4x3diagd, -1, 3);
  }

  // sending in bad rows double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrIntIntConstructorBadColsDataTest() {
    new OGDiagonalMatrix(data4x3diagd, 3, -1);
  }

  // sending in ok double[], int, int constructor
  @Test
  public void testDoublePtrIntIntConstructorInternalDataTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 16, 32);
    assertTrue(D.getClass() == OGDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), data4x3diagd));
    assertTrue(D.getNumberOfRows() == 16);
    assertTrue(D.getNumberOfColumns() == 32);
  }

  // sending in bad rows double, int, int constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoubleIntIntConstructorBadRowsDataTest() {
    new OGDiagonalMatrix(1, -1, 3);
  }

  // sending in bad rows double, int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoubleIntIntConstructorBadColsDataTest() {
    new OGDiagonalMatrix(1, 3, -1);
  }

  // sending in ok double, int, int  constructor
  @Test
  public void testDoubleIntIntConstructorInternalDataTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(3, 13, 37);
    assertTrue(D.getClass() == OGDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {3 }));
    assertTrue(D.getNumberOfRows() == 13);
    assertTrue(D.getNumberOfColumns() == 37);
  }

  // sending in ok double constructor
  @Test
  public void testDoubleConstructorInternalDataTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(3.d);
    assertTrue(D.getClass() == OGDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {3 }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);
  }

  // sending in ok int constructor
  @Test
  public void testIntConstructorInternalDataTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix((int) 3);
    assertTrue(D.getClass() == OGDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {3 }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);
  }

  // test get rows
  @Test
  public void testGetRowsTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    assertTrue(D.getNumberOfRows() == 4);
  }

  // test get cols
  @Test
  public void testGetColumnsTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadRowIndicesTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getEntry(23, 1);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadColumnIndicesTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getEntry(1, 23);
  }

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        assertTrue(D.getEntry(i, j) == data4x3full[i][j]);
      }
    }
  }

  // test get data
  @Test
  public void testGetDataTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    assertTrue(Arrays.equals(D.getData(), data4x3diagd));
  }

  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObj() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNull() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    assertFalse(D.equals(null));
  }

  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClass() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    assertFalse(D.equals(new Double(1.)));
  }

  // test equals obj class ok, diff cols
  @Test
  public void testEqualsObjDifferentCols() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Diff = new OGDiagonalMatrix(7);
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols diff rows
  @Test
  public void testEqualsObjDifferentRows() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Diff = new OGDiagonalMatrix(new double[] {1, 2, 3 }, 3, 3);
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testEqualsObjDifferentData() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Diff = new OGDiagonalMatrix(new double[] {999, 2, 3 }, 4, 3);
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdentical() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Diff = new OGDiagonalMatrix(new double[] {1.00, 2.00, 3.00 }, 4, 3);
    assertTrue(D.equals(Diff));
  }

  // test fuzzy equals obj points to obj
  @Test
  public void testFuzzyEqualsObjeqObj() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    assertTrue(D.fuzzyequals(D, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj not = null
  @Test
  public void testFuzzyEqualsObjNull() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    assertFalse(D.fuzzyequals(null, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class different
  @Test
  public void testFuzzyEqualsObjDifferentClass() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    assertFalse(D.fuzzyequals(new Double(1.), 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, diff cols
  @Test
  public void testFuzzyEqualsObjDifferentCols() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Diff = new OGDiagonalMatrix(7);
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, same cols diff rows
  @Test
  public void testFuzzyEqualsObjDifferentRows() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Diff = new OGDiagonalMatrix(new double[]{1, 2, 3},4,13);
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows different data
  @Test
  public void testFuzzyEqualsObjDifferentData() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Diff = new OGDiagonalMatrix(new double[] {999.00, 2.00, 3.00 }, 4, 3);
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows same data
  @Test
  public void testFuzzyEqualsObjStructurallyIdentical() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Same = new OGDiagonalMatrix(new double[] {1.00+9 * D1MACH.four(), 2.00, 3.00 }, 4, 3);
    assertTrue(D.fuzzyequals(Same, 10 * D1MACH.four()));
  }

  // test hash code
  @Test
  public void testHashCodeTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.hashCode();
  }

  // test hash code
  @Test
  public void testToStringTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.toString();
  }

}
