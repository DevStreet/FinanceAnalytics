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
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * Tests the OGIndexArray Class
 */
public class OGIndexMatrixTest {

  int[][] data4x3 = new int[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }, {10, 11, 12 } };
  int[] data4x3unwound = new int[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  OGIndexMatrix getRow = new OGIndexMatrix(new int[] {7, 8, 9 }, 1, 3);
  OGIndexMatrix getCol = new OGIndexMatrix(new int[] {2, 5, 8, 11 }, 4, 1);

  // sending in null ptr int[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testintPtrPtrConstructorNullPtrTest() {
    int[][] tmp = null;
    new OGIndexMatrix(tmp);
  }

  // sending in embedded null ptr int[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testintPtrPtrConstructorOKThenNullPtrTest() {
    int[][] tmp = new int[2][1];
    tmp[0] = new int[] {1 };
    tmp[1] = null;
    new OGIndexMatrix(tmp);
  }

  // sending in null ptr int[] constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testintPtrConstructorNullPtrTest() {
    int[] tmp = null;
    new OGIndexMatrix(tmp, 1, 1);
  }

  // sending in ragged[][] int[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testintPtrPtrConstructorRaggedTest() {
    int[][] tmp = new int[2][];
    tmp[0] = new int[] {1 };
    tmp[1] = new int[] {1, 2, 3 };
    new OGIndexMatrix(tmp);
  }

  // sending in ok int[][] constructor
  @Test
  public void testintPtrPtrConstructorInternalDataTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3);
    assertTrue(D.getClass() == OGIndexMatrix.class);
    assertTrue(Arrays.equals(D.getIntData(), data4x3unwound));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  //sending in bad rows int[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testintPtrConstructorBadRowsDataTest() {
    new OGIndexMatrix(data4x3unwound, -1, 3);
  }

  //sending in bad rows int[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testintPtrConstructorBadColsDataTest() {
    new OGIndexMatrix(data4x3unwound, 3, -1);
  }

  //sending in bad data count int[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testintPtrConstructorDataCountDataTest() {
    new OGIndexMatrix(data4x3unwound, 3, 17);
  }

  // sending in ok int[] constructor
  @Test
  public void testintPtrConstructorInternalDataTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    assertTrue(D.getClass() == OGIndexMatrix.class);
    assertTrue(Arrays.equals(D.getIntData(), data4x3unwound));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // sending in single int for a 1x1 element array
  public void testintConstructorInternalDataTest() {
    OGIndexMatrix D = new OGIndexMatrix(7);
    assertTrue(D.getClass() == OGIndexMatrix.class);
    assertTrue(Arrays.equals(D.getIntData(), new int[] {7 }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);
  }

  // test get rows
  @Test
  public void testGetRowsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfRows() == 4);
  }

  // test get cols
  @Test
  public void testGetColumnsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // test get col neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColNegIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getColumn(-1);
  }

  // test get col bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnBadIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getColumn(23);
  }

  // test get col ok
  @Test
  public void testGetColumnOkIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> col = D.getColumn(1);
    assertTrue(col.equals(getCol));
  }

  // test get cols null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetColumnsNullTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getColumns(null);
  }

  // test get cols neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsNegIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getColumns(new int[] {-1 });
  }

  // test get cols bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsBadIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getColumns(23);
  }

  // test get cols ok
  @Test
  public void testGetConsecutiveColumnsOkIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGIndexMatrix expected = new OGIndexMatrix(new int[][] { {2, 3 }, {5, 6 }, {8, 9 }, {11, 12 } });
    OGArray<? extends Number> col = D.getColumns(new int[] {1, 2 });
    assertTrue(col.equals(expected));
  }

  // test get cols ok
  @Test
  public void testGetRandomColumnsOkIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> col = D.getColumns(new int[] {2, 0 });
    OGIndexMatrix expected = new OGIndexMatrix(new int[][] { {3, 1 }, {6, 4 }, {9, 7 }, {12, 10 } });
    assertTrue(col.equals(expected));
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadRowIndicesTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getEntry(23, 1);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadColumnIndicesTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getEntry(1, 23);
  }

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        assertTrue(D.getEntry(i, j) == data4x3[i][j]);
      }
    }
  }

  // test get full row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowNegIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getFullRow(-1);
  }

  // test get full row bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowBadIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getFullRow(23);
  }

  // test get full row ok
  @Test
  public void testGetFullRowOkIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGIndexMatrix row = D.getFullRow(2);
    assertTrue(row.equals(getRow));
  }

  // test get row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowNegIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getRow(-1);
  }

  // test get row bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowBadIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getRow(23);
  }

  // test get row ok
  @Test
  public void testGetRowOkIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGIndexMatrix row = D.getRow(2);
    assertTrue(row.equals(getRow));
  }

  // test get Rows null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetRowsNullTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getRows(null);
  }

  // test get Rows neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsNegIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getRows(new int[] {-1 });
  }

  // test get Rows bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsBadIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getRows(23);
  }

  // test get Rows ok
  @Test
  public void testGetConsecutiveRowsOkIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGIndexMatrix expected = new OGIndexMatrix(new int[][] { {4, 5, 6 }, {7, 8, 9 } });
    OGArray<? extends Number> row = D.getRows(new int[] {1, 2 });
    assertTrue(row.equals(expected));
  }

  // test get Rows ok
  @Test
  public void testGetRandomRowsOkIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> row = D.getRows(new int[] {2, 0 });
    OGIndexMatrix expected = new OGIndexMatrix(new int[][] { {7, 8, 9 }, {1, 2, 3 } });
    assertTrue(row.equals(expected));
  }

  // test get full col neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColNegIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getFullColumn(-1);
  }

  // test get full col bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColumnBadIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.getFullColumn(23);
  }

  // test get full col ok
  @Test
  public void testGetFullColumnOkIndexTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGIndexMatrix col = D.getFullColumn(1);
    assertTrue(col.equals(getCol));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullRowsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.get(null, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullColsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.get(new int[] {1, 2, 3 }, null);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegRowsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.get(new int[] {-1 }, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegColsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.get(new int[] {1, 2, 3 }, new int[] {-1 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBRowsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.get(new int[] {23 }, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBColsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.get(new int[] {1, 2, 3 }, new int[] {23 });
  }

  @Test
  public void testGetSeqRowsSeqColsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {1, 2, 3 }, new int[] {1, 2 });
    OGIndexMatrix expected = new OGIndexMatrix(new int[] {5, 8, 11, 6, 9, 12 }, 3, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetSeqRowsRandomColsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {1, 2, 3 }, new int[] {2, 0 });
    OGIndexMatrix expected = new OGIndexMatrix(new int[] {6, 9, 12, 4, 7, 10 }, 3, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetRandomRowsRandomColsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {0, 3, 2 }, new int[] {2, 0 });
    OGIndexMatrix expected = new OGIndexMatrix(new int[] {3, 12, 9, 1, 10, 7 }, 3, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  // test get No elements
  @Test
  public void testGetNoElementsTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfElements() == 12);
  }

  // test get data
  @Test
  public void testGetDataTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    assertTrue(Arrays.equals(D.getIntData(), data4x3unwound));
  }

  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObj() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNull() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    assertFalse(D.equals(null));
  }

  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClass() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    assertFalse(D.equals(new OGMatrix(1)));
  }

  // test equals obj class ok, diff cols
  @Test
  public void testEqualsObjDifferentCols() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGIndexMatrix Diff = new OGIndexMatrix(7);
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols diff rows
  @Test
  public void testEqualsObjDifferentRows() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGIndexMatrix Diff = new OGIndexMatrix(new int[][] {{1, 2, 3 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testEqualsObjDifferentData() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGIndexMatrix Diff = new OGIndexMatrix(new int[][] { {999, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }, {10, 11, 12 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdentical() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    OGIndexMatrix Diff = new OGIndexMatrix(new int[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }, {10, 11, 12 } });
    assertTrue(D.equals(Diff));
  }

  // test hash code
  @Test
  public void testHashCodeTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.hashCode();
  }

  // test toString code
  @Test
  public void testToStringTest() {
    OGIndexMatrix D = new OGIndexMatrix(data4x3unwound, 4, 3);
    D.toString();
  }

}
