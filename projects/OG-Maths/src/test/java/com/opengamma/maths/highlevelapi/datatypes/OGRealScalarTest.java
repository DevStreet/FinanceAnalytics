/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGRealScalar;
import com.opengamma.maths.lowlevelapi.functions.D1mach;

/**
 * Tests the real scalar class
 */
public class OGRealScalarTest {
  OGRealScalar defaultVal = new OGRealScalar(1.4);
  double dataForEqualsTests = 1.6;
  Double NULL = null;

  @Test
  public void constructFromNumberTest() {
    new OGRealScalar(1.4);
    new OGRealScalar(1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void constructFromIllegalNumberTest() {
    new OGRealScalar(new ComplexType(0));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void constructFromNullNumberTest() {
    new OGRealScalar(NULL);
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
    OGRealScalar getCol = new OGRealScalar(1.4);
    OGArray<? extends Number> col = defaultVal.getColumn(0);
    assertTrue(col.equals(getCol));
  }

  // test get cols null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetColumnsNullTest() {
    OGRealScalar getCol = new OGRealScalar(1.4);
    getCol.getColumns(null);
  }

  // test get cols neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsNegIndexTest() {
    OGRealScalar getCol = new OGRealScalar(1.4);
    getCol.getColumns(new int[] {-1 });
  }

  // test get cols bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsBadIndexTest() {
    OGRealScalar getCol = new OGRealScalar(1.4);
    getCol.getColumns(23);
  }

  // test get cols ok
  @Test
  public void testGetSingleColumnsOkIndexTest() {
    OGRealScalar getCol = new OGRealScalar(1.4);
    OGArray<? extends Number> col = defaultVal.getColumns(0);
    assertTrue(col.equals(getCol));
  }

  // test get cols ok
  @Test
  public void testGetSingleColumnsOkRepeatSelectIndexTest() {
    OGArray<? extends Number> col = defaultVal.getColumns(0, 0, 0, 0);
    OGMatrix expected = new OGMatrix(new double[] {1.4, 1.4, 1.4, 1.4 }, 1, 4);
    assertTrue(expected.equals(col));
  }

  // test get row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowNegIndexTest() {
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
    OGRealScalar getRow = new OGRealScalar(1.4);
    OGArray<? extends Number> row = defaultVal.getRow(0);
    assertTrue(row.equals(getRow));
  }

  // test get rows null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetRowsNullTest() {
    OGRealScalar getCol = new OGRealScalar(1.4);
    getCol.getRows(null);
  }

  // test get rows neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsNegIndexTest() {
    OGRealScalar getCol = new OGRealScalar(1.4);
    getCol.getRows(new int[] {-1 });
  }

  // test get rows bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsBadIndexTest() {
    OGRealScalar getCol = new OGRealScalar(1.4);
    getCol.getRows(23);
  }

  // test get rows ok
  @Test
  public void testGetSingleRowsOkIndexTest() {
    OGRealScalar getCol = new OGRealScalar(1.4);
    OGArray<? extends Number> col = defaultVal.getRows(0);
    assertTrue(col.equals(getCol));
  }

  // test get rows ok
  @Test
  public void testGetSingleRowsOkRepeatSelectIndexTest() {
    OGArray<? extends Number> col = defaultVal.getRows(0, 0, 0, 0);
    OGMatrix expected = new OGMatrix(new double[] {1.4, 1.4, 1.4, 1.4 }, 4, 1);
    assertTrue(expected.equals(col));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullRowsTest() {
    OGRealScalar D = new OGRealScalar(1.4);
    D.get(null, new int[] {0 });
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullColsTest() {
    OGRealScalar D = new OGRealScalar(1.4);
    D.get(new int[] {0 }, null);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegRowsTest() {
    OGRealScalar D = new OGRealScalar(1.4);
    D.get(new int[] {-1 }, new int[] {0 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegColsTest() {
    OGRealScalar D = new OGRealScalar(1.4);
    D.get(new int[] {0 }, new int[] {-1 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBRowsTest() {
    OGRealScalar D = new OGRealScalar(1.4);
    D.get(new int[] {23 }, new int[] {0 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBColsTest() {
    OGRealScalar D = new OGRealScalar(1.4);
    D.get(new int[] {0 }, new int[] {23 });
  }

  @Test
  public void testGetSeqRowsSeqColsTest() {
    OGRealScalar D = new OGRealScalar(1.4);
    OGArray<? extends Number> answer = D.get(new int[] {0 }, new int[] {0 });
    OGRealScalar expected = new OGRealScalar(1.4);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetRepeatedRowsTest() {
    OGRealScalar D = new OGRealScalar(1.4);
    OGArray<? extends Number> answer = D.get(new int[] {0, 0, 0, 0 }, new int[] {0 });
    OGMatrix expected = new OGMatrix(new double[] {1.4, 1.4, 1.4, 1.4 }, 4, 1);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetRepeatedColsTest() {
    OGRealScalar D = new OGRealScalar(1.4);
    OGArray<? extends Number> answer = D.get(new int[] {0 }, new int[] {0, 0, 0, 0 });
    OGMatrix expected = new OGMatrix(new double[] {1.4, 1.4, 1.4, 1.4 }, 1, 4);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    assertTrue(defaultVal.getEntry(0, 0).doubleValue() == 1.4d);
  }
  
  // test set entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testSetEntryBadRowIndicesTest() {
    OGRealScalar D = new OGRealScalar(1.2);
    D.setEntry(23, 1, 1);
  }

  // test set entry bad col index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testSetEntryBadColumnIndicesTest() {
    OGRealScalar D = new OGRealScalar(1.2);
    D.setEntry(1, 23, 1);
  }

  // test set null Number
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testSetEntryNullNumberTest() {
    OGRealScalar D = new OGRealScalar(1.2);
    D.setEntry(1, 1, null);
  }


  // test set entry ok
  @Test
  public void testSetEntryOKIndiciesTest() {
    OGArray<? extends Number> answer;
    OGRealScalar D = new OGRealScalar(1.2);
    answer = D.setEntry(1, 1, 1337); // set real, should remote to OGRealScalar
    assertTrue(answer instanceof OGRealScalar);
    assertTrue(answer.getData()[0] == 1337);
  }
  
  // test type promo
  @Test
  public void testSetEntryTypePromotionTest() {
    OGArray<? extends Number> answer;
    OGRealScalar D = new OGRealScalar(1.2);
    answer = D.setEntry(1, 1, new ComplexType(12, 34));
    assertTrue(answer.getData()[0] == 12 && answer.getData()[1] == 34);
  }



  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObjTest() {
    OGRealScalar D = new OGRealScalar(dataForEqualsTests);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNullTest() {
    OGRealScalar D = new OGRealScalar(dataForEqualsTests);
    assertFalse(D.equals(null));
  }

  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClassTest() {
    OGRealScalar D = new OGRealScalar(dataForEqualsTests);
    assertFalse(D.equals(new Double(1.)));
  }

  // test equals obj class ok, same cols same rows Different data
  @Test
  public void testEqualsObjDataDifferentTest() {
    OGRealScalar D = new OGRealScalar(dataForEqualsTests);
    OGRealScalar Diff = new OGRealScalar(1.7d);
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdenticalTest() {
    OGRealScalar D = new OGRealScalar(dataForEqualsTests);
    OGRealScalar Diff = new OGRealScalar(1.6d);
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
    assertTrue(defaultVal.getData()[0] == 1.4d);
  }

}
