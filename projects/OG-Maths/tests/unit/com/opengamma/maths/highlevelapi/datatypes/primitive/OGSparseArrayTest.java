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
 * Tests the OGDoubleArray Class
 */
public class OGSparseArrayTest {

  double[][] data = { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } };
  double[] compressedData = {1.0, 3.0, 2.0, 5.0, 4.0, 6.0, 7.0 };
  int[] compressedColPtr = {0, 2, 4, 7, 7 };
  int[] compressedRowIdx = {0, 1, 0, 2, 1, 2, 3 };

  OGDoubleArray getRow = new OGDoubleArray(new double[] {0, 5, 6, 0 }, 1, 4);
  OGDoubleArray getCol = new OGDoubleArray(new double[] {2, 0, 5, 0 }, 4, 1);

  // sending in null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorNullPtrTest() {
    double[][] tmp = null;
    new OGSparseArray(tmp);
  }

  // sending in embedded null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorOKThenNullPtrTest() {
    double[][] tmp = new double[2][1];
    tmp[0] = new double[] {1 };
    tmp[1] = null;
    new OGSparseArray(tmp);
  }

  // sending in null ptr pos1 int[], int[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCConstructorNullPtrPos1Test() {
    new OGSparseArray(null, new int[1], new double[1], 1, 1);
  }

  // sending in null ptr pos1 int[], int[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCConstructorNullPtrPos2Test() {
    new OGSparseArray(new int[1], null, new double[1], 1, 1);
  }

  // sending in null ptr pos1 int[], int[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCConstructorNullPtrPos3Test() {
    new OGSparseArray(new int[1], new int[1], null, 1, 1);
  }

  // sending in ragged[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrConstructorRaggedTest() {
    double[][] tmp = new double[2][];
    tmp[0] = new double[] {1 };
    tmp[1] = new double[] {1, 2, 3 };
    new OGSparseArray(tmp);
  }

  //sending in all zeros to double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrConstructorAllZerosTest() {
    new OGSparseArray(new double[][] { {0, 0, 0 }, {0, 0, 0 } });
  }

  // sending in ok double[][] constructor
  @Test
  public void testDoublePtrPtrConstructorInternalDataTest() {
    OGSparseArray D = new OGSparseArray(data);
    assertTrue(D.getClass() == OGSparseArray.class);
    assertTrue(Arrays.equals(D.getData(), compressedData));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  //sending in bad rows int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadRowsDataTest() {
    new OGSparseArray(compressedColPtr, compressedRowIdx, compressedData, -1, 4);
  }

  //sending in bad cols int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadColsDataTest() {
    new OGSparseArray(compressedColPtr, compressedRowIdx, compressedData, 4, -1);
  }

  //sending in too many cols int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorDataCountDataTest() {
    new OGSparseArray(compressedColPtr, compressedRowIdx, compressedData, 4, 5);
  }

  // sending in bad ColumnPtr  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadColPtrTooLongTest() {
    OGSparseArray D = new OGSparseArray(new int[] {0, 2, 4, 5, 6, 7 }, compressedRowIdx, compressedData, 4, 4);
  }

  // sending in bad ColumnPtr  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadColPtrValuesAreNotIncreasingTest() {
    OGSparseArray D = new OGSparseArray(new int[] {0, 2, 0, 7, 7 }, compressedRowIdx, compressedData, 4, 4);
  }

  // sending in bad ColumnPtr  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadColPtrValuesIsNegativeTest() {
    OGSparseArray D = new OGSparseArray(new int[] {-1, 2, 0, 7, 7 }, compressedRowIdx, compressedData, 4, 4);
  }  
  
  // sending in bad rowIdx  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadRowIdxTooHighTest() {
    OGSparseArray D = new OGSparseArray(compressedColPtr, new int[] {0, 1, 0, 2, 1, 6, 3 }, compressedData, 4, 4);
  }

  // sending in bad rowIdx  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadRowIdxTooLowTest() {
    OGSparseArray D = new OGSparseArray(compressedColPtr, new int[] {0, 1, 0, 2, 1, -1, 3 }, compressedData, 4, 4);
  }

  // sending in bad rowIdx  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadRowIdxLengthTest() {
    OGSparseArray D = new OGSparseArray(compressedColPtr, new int[] {0, 1, 0, 2, 1, 1 }, compressedData, 4, 4);
  }

  // sending in ok  int[], int[], double[], int, int  constructor
  @Test
  public void testDoublePtrConstructorInternalDataTest() {
    OGSparseArray D = new OGSparseArray(compressedColPtr, compressedRowIdx, compressedData, 4, 4);
    assertTrue(D.getClass() == OGSparseArray.class);
    assertTrue(Arrays.equals(D.getData(), compressedData));
    assertTrue(Arrays.equals(D.getColumnPtr(), compressedColPtr));
    assertTrue(Arrays.equals(D.getRowIndex(), compressedRowIdx));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  // test get rows
  @Test
  public void testGetRowsTest() {
    OGSparseArray D = new OGSparseArray(data);
    assertTrue(D.getNumberOfRows() == 4);
  }

  // test get cols
  @Test
  public void testGetColumnsTest() {
    OGSparseArray D = new OGSparseArray(data);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    OGSparseArray D = new OGSparseArray(data);
    D.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadRowIndicesTest() {
    OGSparseArray D = new OGSparseArray(data);
    D.getEntry(23, 1);
  }

  // test get entry bad col index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadColumnIndicesTest() {
    OGSparseArray D = new OGSparseArray(data);
    D.getEntry(1, 23);
  }

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    OGSparseArray D = new OGSparseArray(data);
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        assertTrue(D.getEntry(i, j) == data[i][j]);
      }
    }
  }

  // test get full row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowNegIndexTest() {
    OGSparseArray D = new OGSparseArray(data);
    D.getFullRow(-1);
  }

  // test get full row bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowBadIndexTest() {
    OGSparseArray D = new OGSparseArray(data);
    D.getFullRow(23);
  }

  // test get full row ok
  @Test
  public void testGetFullRowOkIndexTest() {
    OGSparseArray D = new OGSparseArray(data);
    OGDoubleArray row = D.getFullRow(2);
    assertTrue(row.equals(getRow));
  }

  // test get full col neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColNegIndexTest() {
    OGSparseArray D = new OGSparseArray(data);
    D.getFullColumn(-1);
  }

  // test get full col bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColumnBadIndexTest() {
    OGSparseArray D = new OGSparseArray(data);
    D.getFullColumn(23);
  }

  // test get full col ok
  @Test
  public void testGetFullColumnOkIndexTest() {
    OGSparseArray D = new OGSparseArray(data);
    OGDoubleArray col = D.getFullColumn(1);
    assertTrue(col.equals(getCol));
  }

  // test get No elements
  @Test
  public void testGetNoElementsTest() {
    OGSparseArray D = new OGSparseArray(data);
    assertTrue(D.getNumberOfNonZeroElements() == 7);
  }

  // test get data
  @Test
  public void testGetDataTest() {
    OGSparseArray D = new OGSparseArray(data);
    assertTrue(Arrays.equals(D.getData(), compressedData));
  }

  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObj() {
    OGSparseArray D = new OGSparseArray(data);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNull() {
    OGSparseArray D = new OGSparseArray(data);
    assertFalse(D.equals(null));
  }

  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClass() {
    OGSparseArray D = new OGSparseArray(data);
    assertFalse(D.equals(new Double(1.)));
  }

  // test equals obj class ok, diff cols
  @Test
  public void testEqualsObjDifferentCols() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Diff = new OGSparseArray(new double[][] {{7 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols diff rows
  @Test
  public void testEqualsObjDifferentRows() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Diff = new OGSparseArray(new double[][] {{1, 2, 3, 4 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testEqualsObjDifferentData() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Diff = new OGSparseArray(new double[][] { {999, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows different col ptr
  @Test
  public void testEqualsObjDifferentColPtr() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Diff = new OGSparseArray(new double[][] { {1, 0, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data, should bork on column ptr
  @Test
  public void testEqualsObjDifferentRowIdx() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Diff = new OGSparseArray(new double[][] { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {5, 0, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdentical() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Diff = new OGSparseArray(new double[][] { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertTrue(D.equals(Diff));
  }

  // test fuzzy equals obj points to obj
  @Test
  public void testFuzzyEqualsObjeqObj() {
    OGSparseArray D = new OGSparseArray(data);
    assertTrue(D.fuzzyequals(D, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj not = null
  @Test
  public void testFuzzyEqualsObjNull() {
    OGSparseArray D = new OGSparseArray(data);
    assertFalse(D.fuzzyequals(null, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class different
  @Test
  public void testFuzzyEqualsObjDifferentClass() {
    OGSparseArray D = new OGSparseArray(data);
    assertFalse(D.fuzzyequals(new Double(1.), 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, diff cols
  @Test
  public void testFuzzyEqualsObjDifferentCols() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Diff = new OGSparseArray(new double[][] {{7 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, same cols diff rows
  @Test
  public void testFuzzyEqualsObjDifferentRows() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Diff = new OGSparseArray(new double[][] {{1, 2, 3, 4 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows different data
  @Test
  public void testFuzzyEqualsObjDifferentData() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Diff = new OGSparseArray(new double[][] { {999, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test equals obj class ok, same cols same rows different col ptr
  @Test
  public void testFuzzyEqualsObjDifferentColPtr() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Diff = new OGSparseArray(new double[][] { {1, 0, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test equals obj class ok, same cols same rows same data, should bork on column ptr
  @Test
  public void testFuzzyEqualsObjDifferentRowIdx() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Diff = new OGSparseArray(new double[][] { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {5, 0, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows same data
  @Test
  public void testFuzzyEqualsObjStructurallyIdentical() {
    OGSparseArray D = new OGSparseArray(data);
    OGSparseArray Same = new OGSparseArray(new double[][] { {1 + 9 * D1MACH.four(), 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertTrue(D.fuzzyequals(Same, 10 * D1MACH.four()));
  }

  // test hash code
  @Test
  public void testHashCodeTest() {
    OGSparseArray D = new OGSparseArray(data);
    D.hashCode();
  }

  // test toString code
  @Test
  public void testToStringTest() {
    OGSparseArray D = new OGSparseArray(data);
    D.toString();
  }

}
