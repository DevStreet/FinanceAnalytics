/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * Tests the OGDoubleArray Class
 */
public class OGSparseMatrixTest {

  double[][] data = { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } };
  double[] compressedData = {1.0, 3.0, 2.0, 5.0, 4.0, 6.0, 7.0 };
  int[] compressedColPtr = {0, 2, 4, 7, 7 };
  int[] compressedRowIdx = {0, 1, 0, 2, 1, 2, 3 };

  OGMatrix getRow = new OGMatrix(new double[] {0, 5, 6, 0 }, 1, 4);
  OGMatrix getCol = new OGMatrix(new double[] {2, 0, 5, 0 }, 4, 1);

  // sending in null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorNullPtrTest() {
    double[][] tmp = null;
    new OGSparseMatrix(tmp);
  }

  // sending in embedded null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorOKThenNullPtrTest() {
    double[][] tmp = new double[2][1];
    tmp[0] = new double[] {1 };
    tmp[1] = null;
    new OGSparseMatrix(tmp);
  }

  // sending in null ptr pos1 int[], int[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCConstructorNullPtrPos1Test() {
    new OGSparseMatrix(null, new int[1], new double[1], 1, 1);
  }

  // sending in null ptr pos1 int[], int[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCConstructorNullPtrPos2Test() {
    new OGSparseMatrix(new int[1], null, new double[1], 1, 1);
  }

  // sending in null ptr pos1 int[], int[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCConstructorNullPtrPos3Test() {
    new OGSparseMatrix(new int[1], new int[1], null, 1, 1);
  }

  // sending in ragged[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrConstructorRaggedTest() {
    double[][] tmp = new double[2][];
    tmp[0] = new double[] {1 };
    tmp[1] = new double[] {1, 2, 3 };
    new OGSparseMatrix(tmp);
  }

  //sending in all zeros to double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrConstructorAllZerosTest() {
    new OGSparseMatrix(new double[][] { {0, 0, 0 }, {0, 0, 0 } });
  }

  // sending in ok double[][] constructor
  @Test
  public void testDoublePtrPtrConstructorInternalDataTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    assertTrue(D.getClass() == OGSparseMatrix.class);
    assertTrue(Arrays.equals(D.getData(), compressedData));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  //sending in bad rows int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadRowsDataTest() {
    new OGSparseMatrix(compressedColPtr, compressedRowIdx, compressedData, -1, 4);
  }

  //sending in bad cols int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadColsDataTest() {
    new OGSparseMatrix(compressedColPtr, compressedRowIdx, compressedData, 4, -1);
  }

  //sending in too many cols int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorDataCountDataTest() {
    new OGSparseMatrix(compressedColPtr, compressedRowIdx, compressedData, 4, 5);
  }

  // sending in bad ColumnPtr  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadColPtrTooLongTest() {
    new OGSparseMatrix(new int[] {0, 2, 4, 5, 6, 7 }, compressedRowIdx, compressedData, 4, 4);
  }

  // sending in bad ColumnPtr  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadColPtrValuesAreNotIncreasingTest() {
    new OGSparseMatrix(new int[] {0, 2, 0, 7, 7 }, compressedRowIdx, compressedData, 4, 4);
  }

  // sending in bad ColumnPtr  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadColPtrValuesIsNegativeTest() {
    new OGSparseMatrix(new int[] {-1, 2, 0, 7, 7 }, compressedRowIdx, compressedData, 4, 4);
  }

  // sending in bad rowIdx  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadRowIdxTooHighTest() {
    new OGSparseMatrix(compressedColPtr, new int[] {0, 1, 0, 2, 1, 6, 3 }, compressedData, 4, 4);
  }

  // sending in bad rowIdx  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadRowIdxTooLowTest() {
    new OGSparseMatrix(compressedColPtr, new int[] {0, 1, 0, 2, 1, -1, 3 }, compressedData, 4, 4);
  }

  // sending in bad rowIdx  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadRowIdxLengthTest() {
    new OGSparseMatrix(compressedColPtr, new int[] {0, 1, 0, 2, 1, 1 }, compressedData, 4, 4);
  }

  // sending in ok  int[], int[], double[], int, int  constructor
  @Test
  public void testDoublePtrConstructorInternalDataTest() {
    OGSparseMatrix D = new OGSparseMatrix(compressedColPtr, compressedRowIdx, compressedData, 4, 4);
    assertTrue(D.getClass() == OGSparseMatrix.class);
    assertTrue(Arrays.equals(D.getData(), compressedData));
    assertTrue(Arrays.equals(D.getColumnPtr(), compressedColPtr));
    assertTrue(Arrays.equals(D.getRowIndex(), compressedRowIdx));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  // test get rows
  @Test
  public void testGetRowsTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    assertTrue(D.getNumberOfRows() == 4);
  }

  // test get cols
  @Test
  public void testGetColumnsTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadRowIndicesTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getEntry(23, 1);
  }

  // test get entry bad col index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadColumnIndicesTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getEntry(1, 23);
  }

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        assertTrue(D.getEntry(i, j) == data[i][j]);
      }
    }
  }

  // test get full row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowNegIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getFullRow(-1);
  }

  // test get full row bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowBadIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getFullRow(23);
  }

  // test get full row ok
  @Test
  public void testGetFullRowOkIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGMatrix row = D.getFullRow(2);
    assertTrue(row.equals(getRow));
  }

  // test get row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowNegIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getRow(-1);
  }

  // test get row bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowBadIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getRow(23);
  }

  // test get row ok
  @Test
  public void testGetRowOkIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix expected = new OGSparseMatrix(new double[][] {{3, 0, 4, 0 } });
    OGArray<? extends Number> row = D.getRow(1);
    assertTrue(row.fuzzyequals(expected, 10 * D1mach.four()));
  }

  // test get rows
  @Test
  public void testGetConsecutiveRows() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    double[][] rp = new double[][] { {3., 0., 4., 0. }, {0., 5., 6., 0. } };
    OGSparseMatrix tmp = new OGSparseMatrix(rp);
    assertTrue(tmp.equals(D.getRows(1, 2)));
  }

  // test get rows
  @Test
  public void testGetRandomRows() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    double[][] rp = new double[][] { {0., 5., 6., 0. }, {1., 2., 0., 0. } };
    OGSparseMatrix tmp = new OGSparseMatrix(rp);
    assertTrue(tmp.equals(D.getRows(2, 0)));
  }

  // test get rows null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetRowsNull() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getRows(null);
  }

  // test get rows neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsBadIndexLow() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getRows(-1);
  }

  // test get rows index overflow
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsBadIndexHigh() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getRows(12);
  }

  // test get full col neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColNegIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getFullColumn(-1);
  }

  // test get full col bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColumnBadIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getFullColumn(23);
  }

  // test get full col ok
  @Test
  public void testGetFullColumnOkIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGMatrix col = D.getFullColumn(1);
    assertTrue(col.equals(getCol));
  }

  // test get col neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColNegIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getColumn(-1);
  }

  // test get col bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnBadIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getColumn(23);
  }

  // test get col ok
  @Test
  public void testGetColumnOkIndexTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGArray<? extends Number> col = D.getColumn(1);
    double[][] tmp = new double[4][1];
    for (int i = 0; i < 4; i++) {
      tmp[i][0] = getCol.getEntry(i, 0);
    }
    OGSparseMatrix answer = new OGSparseMatrix(tmp);
    assertTrue(col.equals(answer));
    answer = new OGSparseMatrix(new double[][] { {1 }, {0 }, {2 }, {0 }, {3 }, {0 } });
  }

  // test get cols
  @Test
  public void testGetConsecutiveColumns() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    double[][] rp = new double[][] { {2., 0. }, {0., 4. }, {5., 6. }, {0., 7. } };
    ;
    OGSparseMatrix tmp = new OGSparseMatrix(rp);
    assertTrue(tmp.equals(D.getColumns(1, 2)));
  }

  // test get cols
  @Test
  public void testGetRandomColumns() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    double[][] rp = new double[][] { {0., 1. }, {4., 3. }, {6., 0. }, {7., 0. } };
    OGSparseMatrix tmp = new OGSparseMatrix(rp);
    assertTrue(tmp.equals(D.getColumns(2, 0)));
  }

  // test get columns null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetColumnsNull() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getColumns(null);
  }

  // test get columns neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsBadIndexLow() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getColumns(-1);
  }

  // test get columns index overflow
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsBadIndexHigh() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.getColumns(12);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullRowsTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.get(null, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullColsTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.get(new int[] {1, 2, 3 }, null);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegRowsTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.get(new int[] {-1 }, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegColsTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.get(new int[] {1, 2, 3 }, new int[] {-1 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBRowsTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.get(new int[] {23 }, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBColsTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.get(new int[] {1, 2, 3 }, new int[] {23 });
  }

  @Test
  public void testGetRandomSelection() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGArray<? extends Number> answer = D.get(new int[] {3, 1, 2 }, new int[] {2, 0 });
    OGSparseMatrix expected = new OGSparseMatrix(new double[][] { {7, 0 }, {4, 3 }, {6, 0 } });
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  // test get No elements
  @Test
  public void testGetNoElementsTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    assertTrue(D.getNumberOfNonZeroElements() == 7);
  }

  // test get data
  @Test
  public void testGetDataTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    assertTrue(Arrays.equals(D.getData(), compressedData));
  }

  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObj() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNull() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    assertFalse(D.equals(null));
  }

  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClass() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    assertFalse(D.equals(new Double(1.)));
  }

  // test equals obj class ok, diff cols
  @Test
  public void testEqualsObjDifferentCols() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Diff = new OGSparseMatrix(new double[][] {{7 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols diff rows
  @Test
  public void testEqualsObjDifferentRows() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Diff = new OGSparseMatrix(new double[][] {{1, 2, 3, 4 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testEqualsObjDifferentData() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Diff = new OGSparseMatrix(new double[][] { {999, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows different col ptr
  @Test
  public void testEqualsObjDifferentColPtr() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Diff = new OGSparseMatrix(new double[][] { {1, 0, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data, should bork on column ptr
  @Test
  public void testEqualsObjDifferentRowIdx() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Diff = new OGSparseMatrix(new double[][] { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {5, 0, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdentical() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Diff = new OGSparseMatrix(new double[][] { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertTrue(D.equals(Diff));
  }

  // test fuzzy equals obj points to obj
  @Test
  public void testFuzzyEqualsObjeqObj() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    assertTrue(D.fuzzyequals(D, 10 * D1mach.four()));
  }

  // test fuzzy equals obj not = null
  @Test
  public void testFuzzyEqualsObjNull() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    assertFalse(D.fuzzyequals(null, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class different
  @Test
  public void testFuzzyEqualsObjDifferentClass() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    assertFalse(D.fuzzyequals(new Double(1.), 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, diff cols
  @Test
  public void testFuzzyEqualsObjDifferentCols() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Diff = new OGSparseMatrix(new double[][] {{7 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, same cols diff rows
  @Test
  public void testFuzzyEqualsObjDifferentRows() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Diff = new OGSparseMatrix(new double[][] {{1, 2, 3, 4 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows different data
  @Test
  public void testFuzzyEqualsObjDifferentData() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Diff = new OGSparseMatrix(new double[][] { {999, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test equals obj class ok, same cols same rows different col ptr
  @Test
  public void testFuzzyEqualsObjDifferentColPtr() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Diff = new OGSparseMatrix(new double[][] { {1, 0, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test equals obj class ok, same cols same rows same data, should bork on column ptr
  @Test
  public void testFuzzyEqualsObjDifferentRowIdx() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Diff = new OGSparseMatrix(new double[][] { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {5, 0, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows same data
  @Test
  public void testFuzzyEqualsObjStructurallyIdentical() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix Same = new OGSparseMatrix(new double[][] { {1 + 9 * D1mach.four(), 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertTrue(D.fuzzyequals(Same, 10 * D1mach.four()));
  }

  // test hash code
  @Test
  public void testHashCodeTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.hashCode();
  }

  // test toString code
  @Test
  public void testToStringTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    D.toString();
  }

  @Test
  public void parserLinearModeTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGMatrix expected;
    double[][] re = new double[][] {{3., 0., 0., 2. } };

    expected = new OGMatrix(re);
    assertTrue(expected.fuzzyequals(D.get("1:4"), 10 * D1mach.four()));

    re = new double[][] {{1., 3., 0., 0., 2., 0., 5., 0., 0., 4., 6., 7., 0., 0., 0., 0. } };

    expected = new OGMatrix(re);
    assertTrue(expected.fuzzyequals(D.get(":"), 10 * D1mach.four()));
  }

  @Test
  public void parser2DModeTest() {
    OGSparseMatrix D = new OGSparseMatrix(data);
    OGSparseMatrix expected;
    double[][] re;
    re = new double[][] { {3, 0, 4, 0 }, {0, 5, 6, 0 },
        {0, 0, 7, 0 } };
    expected = new OGSparseMatrix(re);
    assertTrue(expected.fuzzyequals(D.get("1:3,:"), 10 * D1mach.four()));

    re = new double[][] { {2., 0. }, {0., 4. }, {5., 6. }, {0., 7. } };

    expected = new OGSparseMatrix(re);
    assertTrue(expected.fuzzyequals(D.get(":,1:2"), 10 * D1mach.four()));  
    
    expected = new OGSparseMatrix(data);
    assertTrue(expected.fuzzyequals(D.get(":,:"), 10 * D1mach.four()));
  }

}
