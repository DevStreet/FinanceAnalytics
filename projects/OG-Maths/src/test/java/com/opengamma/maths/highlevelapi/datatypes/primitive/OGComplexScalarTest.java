/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * Tests the real scalar class
 */
public class OGComplexScalarTest {
  OGComplexScalar defaultVal = new OGComplexScalar(1.2, 3.4);
  double dataForEqualsTests = 1.6;
  Double NULL = null;

  @Test
  public void constructFromNumberTest() {
    new OGComplexScalar(1.4);
    new OGComplexScalar(1);
  }

  @Test
  public void constructFromComplexNumberTest() {
    new OGComplexScalar(new ComplexType(1.4, 2.4));
    new OGComplexScalar(new ComplexType(1));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void constructFromNullNumberTest() {
    new OGComplexScalar(NULL);
  }

  @Test
  public void getNumberOfRowsTest() {
    assertTrue(defaultVal.getNumberOfRows() == 1);
  }

  @Test
  public void getNumberOfColumnsTest() {
    assertTrue(defaultVal.getNumberOfColumns() == 1);
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    defaultVal.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadRowIndicesTest() {
    defaultVal.getEntry(23, 0);
    defaultVal.getEntry(-1, 0);
  }

  // test get entry bad col index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadColumnIndicesTest() {
    defaultVal.getEntry(0, 23);
    defaultVal.getEntry(0, -1);
  }

  // test get col neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColNegIndexTest() {
    defaultVal.getColumn(-1);
  }

  // test get col bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnBadIndexTest() {
    defaultVal.getColumn(23);
  }

  // test get col ok
  @Test
  public void testGetColumnOkIndexTest() {
    OGComplexScalar getCol = new OGComplexScalar(1.2, 3.4);
    OGArray<? extends Number> col = defaultVal.getColumn(0);
    assertTrue(col.equals(getCol));
  }

  // test get cols null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetColumnsNullTest() {
    OGComplexScalar getCol = new OGComplexScalar(1.2, 3.4);
    getCol.getColumns(null);
  }

  // test get cols neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsNegIndexTest() {
    OGComplexScalar getCol = new OGComplexScalar(1.2, 3.4);
    getCol.getColumns(new int[] {-1 });
  }

  // test get cols bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsBadIndexTest() {
    OGComplexScalar getCol = new OGComplexScalar(1.2, 3.4);
    getCol.getColumns(23);
  }

  // test get cols ok
  @Test
  public void testGetSingleColumnsOkIndexTest() {
    OGComplexScalar getCol = new OGComplexScalar(1.2, 3.4);
    OGArray<? extends Number> col = defaultVal.getColumns(0);
    assertTrue(col.equals(getCol));
  }

  // test get cols ok
  @Test
  public void testGetSingleColumnsOkRepeatSelectIndexTest() {
    OGArray<? extends Number> col = defaultVal.getColumns(0, 0, 0, 0);
    OGComplexMatrix expected = new OGComplexMatrix(new double[] {1.2, 3.4, 1.2, 3.4, 1.2, 3.4, 1.2, 3.4 }, 1, 4);
    assertTrue(expected.equals(col));
  }

  // test get rows null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetRowsNullTest() {
    OGComplexScalar getrow = new OGComplexScalar(1.2, 3.4);
    getrow.getRows(null);
  }

  // test get rows neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsNegIndexTest() {
    OGComplexScalar getRow = new OGComplexScalar(1.2, 3.4);
    getRow.getRows(new int[] {-1 });
  }

  // test get rows bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsBadIndexTest() {
    OGComplexScalar getRow = new OGComplexScalar(1.2, 3.4);
    getRow.getRows(23);
  }

  // test get rows ok
  @Test
  public void testGetSingleRowsOkIndexTest() {
    OGComplexScalar getRow = new OGComplexScalar(1.2, 3.4);
    OGArray<? extends Number> row = defaultVal.getRows(0);
    assertTrue(row.equals(getRow));
  }

  // test get rows ok
  @Test
  public void testGetSingleRowsOkRepeatSelectIndexTest() {
    OGArray<? extends Number> row = defaultVal.getRows(0, 0, 0, 0);
    OGComplexMatrix expected = new OGComplexMatrix(new double[] {1.2, 3.4, 1.2, 3.4, 1.2, 3.4, 1.2, 3.4 }, 4, 1);
    assertTrue(expected.equals(row));
  }

  // test get row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testRowColNegIndexTest() {
    defaultVal.getRow(-1);
  }

  // test get row bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowBadIndexTest() {
    defaultVal.getRow(23);
  }

  // test get row ok
  @Test
  public void testGetRowOkIndexTest() {
    OGComplexScalar getRow = new OGComplexScalar(1.2, 3.4);
    OGArray<? extends Number> row = defaultVal.getRow(0);
    assertTrue(row.equals(getRow));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullRowsTest() {
    OGComplexScalar D = new OGComplexScalar(1.2, 3.4);
    D.get(null, new int[] {0 });
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullColsTest() {
    OGComplexScalar D = new OGComplexScalar(1.2, 3.4);
    D.get(new int[] {0 }, null);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegRowsTest() {
    OGComplexScalar D = new OGComplexScalar(1.2, 3.4);
    D.get(new int[] {-1 }, new int[] {0 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegColsTest() {
    OGComplexScalar D = new OGComplexScalar(1.2, 3.4);
    D.get(new int[] {0 }, new int[] {-1 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBRowsTest() {
    OGComplexScalar D = new OGComplexScalar(1.2, 3.4);
    D.get(new int[] {23 }, new int[] {0 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBColsTest() {
    OGComplexScalar D = new OGComplexScalar(1.2, 3.4);
    D.get(new int[] {0 }, new int[] {23 });
  }

  @Test
  public void testGetSeqRowsSeqColsTest() {
    OGComplexScalar D = new OGComplexScalar(1.2, 3.4);
    OGArray<? extends Number> answer = D.get(new int[] {0 }, new int[] {0 });
    OGComplexScalar expected = new OGComplexScalar(1.2, 3.4);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetRepeatedRowsTest() {
    OGComplexScalar D = new OGComplexScalar(1.2, 3.4);
    OGArray<? extends Number> answer = D.get(new int[] {0, 0, 0, 0 }, new int[] {0 });
    OGComplexMatrix expected = new OGComplexMatrix(new double[] {1.2, 3.4, 1.2, 3.4, 1.2, 3.4, 1.2, 3.4 }, 4, 1);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetRepeatedColsTest() {
    OGComplexScalar D = new OGComplexScalar(1.2, 3.4);
    OGArray<? extends Number> answer = D.get(new int[] {0 }, new int[] {0, 0, 0, 0 });
    OGComplexMatrix expected = new OGComplexMatrix(new double[] {1.2, 3.4, 1.2, 3.4, 1.2, 3.4, 1.2, 3.4 }, 1, 4);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    assertTrue(defaultVal.getEntry(0, 0).getReal() == 1.2d);
    assertTrue(defaultVal.getEntry(0, 0).getImag() == 3.4d);
  }

  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObjTest() {
    OGComplexScalar D = new OGComplexScalar(dataForEqualsTests);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNullTest() {
    OGComplexScalar D = new OGComplexScalar(dataForEqualsTests);
    assertFalse(D.equals(null));
  }

  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClassTest() {
    OGComplexScalar D = new OGComplexScalar(dataForEqualsTests);
    assertFalse(D.equals(new Double(1.)));
  }

  // test equals obj class ok, same cols same rows Different data
  @Test
  public void testEqualsObjDataDifferentTest() {
    OGComplexScalar D = new OGComplexScalar(dataForEqualsTests);
    OGComplexScalar Diff = new OGComplexScalar(1.7d);
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdenticalTest() {
    OGComplexScalar D = defaultVal;
    OGComplexScalar Diff = new OGComplexScalar(1.2d, 3.4d);
    assertTrue(D.equals(Diff));
  }

  @Test
  public void testHashCodeTest() {
    defaultVal.hashCode();
  }

  @Test
  public void testToStringTest() {
    defaultVal.toString();
  }

  @Test
  public void testGetDataTest() {
    assertTrue(defaultVal.getData()[0] == 1.2d);
    assertTrue(defaultVal.getData()[1] == 3.4d);
  }

}
