/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes;

import java.util.Arrays;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * Tests the OGMatrix Class
 */
public class OGMatrixTest {

  double[][] data4x3 = new double[][] { {1.00, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } };
  double[] data4x3unwound = new double[] {1.00, 4.00, 7.00, 10.00, 2.00, 5.00, 8.00, 11.00, 3.00, 6.00, 9.00, 12.00 };
  OGMatrix getRow = new OGMatrix(new double[] {7, 8, 9 }, 1, 3);
  OGMatrix getCol = new OGMatrix(new double[] {2, 5, 8, 11 }, 4, 1);

  // sending in null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorNullPtrTest() {
    double[][] tmp = null;
    new OGMatrix(tmp);
  }

  // sending in embedded null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorOKThenNullPtrTest() {
    double[][] tmp = new double[2][1];
    tmp[0] = new double[] {1 };
    tmp[1] = null;
    new OGMatrix(tmp);
  }

  // sending in null ptr double[] constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrConstructorNullPtrTest() {
    double[] tmp = null;
    new OGMatrix(tmp, 1, 1);
  }

  // sending in ragged[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrConstructorRaggedTest() {
    double[][] tmp = new double[2][];
    tmp[0] = new double[] {1 };
    tmp[1] = new double[] {1, 2, 3 };
    new OGMatrix(tmp);
  }

  // sending in ok double[][] constructor
  @Test
  public void testDoublePtrPtrConstructorInternalDataTest() {
    OGMatrix D = new OGMatrix(data4x3);
    assertTrue(D.getClass() == OGMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertRowMajorDoublePointerToColumnMajorSinglePointer(data4x3)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  //sending in bad rows double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadRowsDataTest() {
    new OGMatrix(data4x3unwound, -1, 3);
  }

  //sending in bad rows double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadColsDataTest() {
    new OGMatrix(data4x3unwound, 3, -1);
  }

  //sending in bad data count double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorDataCountDataTest() {
    new OGMatrix(data4x3unwound, 3, 17);
  }

  // sending in ok double[] constructor
  @Test
  public void testDoublePtrConstructorInternalDataTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    assertTrue(D.getClass() == OGMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertRowMajorDoublePointerToColumnMajorSinglePointer(data4x3)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // sending in single double for a 1x1 element array
  public void testDoubleConstructorInternalDataTest() {
    OGMatrix D = new OGMatrix(7.);
    assertTrue(D.getClass() == OGMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {7. }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);
  }

  // test get rows
  @Test
  public void testGetRowsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfRows() == 4);
  }

  // test get cols
  @Test
  public void testGetColumnsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadRowIndicesTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getEntry(23, 1);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadColumnIndicesTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getEntry(1, 23);
  }

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        assertTrue(D.getEntry(i, j) == data4x3[i][j]);
      }
    }
  }

  @Test
  public void testGetEntryLinearIndicesTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    int ptr = 0;
    for (int j = 0; j < D.getNumberOfColumns(); j++) {
      for (int i = 0; i < D.getNumberOfRows(); i++) {
        assertTrue(D.getEntry(ptr++) == data4x3[i][j]);
      }
    }
  }

  // test set entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testSetEntryBadRowIndicesTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.setEntry(23, 1, 1);
  }

  // test set entry bad col index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testSetEntryBadColumnIndicesTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.setEntry(1, 23, 1);
  }

  // test set null Number
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testSetEntryNullNumberTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.setEntry(1, 1, null);
  }

  // test set entry ok
  @Test
  public void testSetEntryOKIndicesTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    Number val = 1337;
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        assertTrue(D.setEntry(i, j, val).getEntry(i, j).doubleValue() == val.doubleValue());
      }
    }
    // make sure the underlying data is now all val
    double[] dataFinal = D.getData();
    for (int i = 0; i < dataFinal.length; i++) {
      assertTrue(dataFinal[i] == val.doubleValue());
    }
  }

  // test type promotion
  @Test
  public void testSetEntryTypePromotionTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    Number val = new ComplexType(13, 37);
    OGArray<? extends Number> answer = D.setEntry(2, 2, val);

    // make original is unchanged
    assertTrue(D.fuzzyequals(new OGMatrix(data4x3unwound, 4, 3), 1e-14));

    // make sure promotion has occurred
    assertTrue(answer instanceof OGComplexMatrix);

    // make sure data is ok
    double[] cd = new double[] {1.00, 0, 4.00, 0, 7.00, 0, 10.00, 0, 2.00, 0, 5.00, 0, 8.00, 0, 11.00, 0, 3.00, 0, 6.00, 0, 13.00, 37, 12.00, 0 };
    OGComplexMatrix cmplx = new OGComplexMatrix(cd, 4, 3);
    assertTrue(answer.fuzzyequals(cmplx, 1e-14));
  }

  // test get full row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowNegIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getFullRow(-1);
  }

  // test get full row bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowBadIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getFullRow(23);
  }

  // test get full row ok
  @Test
  public void testGetFullRowOkIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix row = D.getFullRow(2);
    assertTrue(row.equals(getRow));
  }

  // test get row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowNegIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getRow(-1);
  }

  // test get row bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowBadIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getRow(23);
  }

  // test get row ok
  @Test
  public void testGetRowOkIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix row = D.getRow(2);
    assertTrue(row.equals(getRow));
  }

  // test get full col neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColNegIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getFullColumn(-1);
  }

  // test get full col bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColumnBadIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getFullColumn(23);
  }

  // test get full col ok
  @Test
  public void testGetFullColumnOkIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix col = D.getFullColumn(1);
    assertTrue(col.equals(getCol));
  }

  // test get col neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColNegIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getColumn(-1);
  }

  // test get col bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnBadIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getColumn(23);
  }

  // test get col ok
  @Test
  public void testGetColumnOkIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> col = D.getColumn(1);
    assertTrue(col.equals(getCol));
  }

  // test get cols null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetColumnsNullTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getColumns(null);
  }

  // test get cols neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsNegIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getColumns(new int[] {-1 });
  }

  // test get cols bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsBadIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getColumns(23);
  }

  // test get cols ok
  @Test
  public void testGetConsecutiveColumnsOkIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix expected = new OGMatrix(new double[][] { {2., 3. }, {5., 6. }, {8., 9. }, {11., 12. } });
    OGArray<? extends Number> col = D.getColumns(new int[] {1, 2 });
    assertTrue(col.equals(expected));
  }

  // test get cols ok
  @Test
  public void testGetRandomColumnsOkIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> col = D.getColumns(new int[] {2, 0 });
    OGMatrix expected = new OGMatrix(new double[][] { {3., 1. }, {6., 4. }, {9., 7. }, {12., 10. } });
    assertTrue(col.equals(expected));
  }

  // test get Rows null
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetRowsNullTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getRows(null);
  }

  // test get Rows neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsNegIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getRows(new int[] {-1 });
  }

  // test get Rows bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsBadIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.getRows(23);
  }

  // test get Rows ok
  @Test
  public void testGetConsecutiveRowsOkIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix expected = new OGMatrix(new double[][] { {4., 5., 6. }, {7., 8., 9. } });
    OGArray<? extends Number> row = D.getRows(new int[] {1, 2 });
    assertTrue(row.equals(expected));
  }

  // test get Rows ok
  @Test
  public void testGetRandomRowsOkIndexTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> row = D.getRows(new int[] {2, 0 });
    OGMatrix expected = new OGMatrix(new double[][] { {7., 8., 9. }, {1., 2., 3. } });
    assertTrue(row.equals(expected));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullRowsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.get(null, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullColsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.get(new int[] {1, 2, 3 }, null);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegRowsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.get(new int[] {-1 }, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegColsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.get(new int[] {1, 2, 3 }, new int[] {-1 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBRowsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.get(new int[] {23 }, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBColsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.get(new int[] {1, 2, 3 }, new int[] {23 });
  }

  @Test
  public void testGetSeqRowsSeqColsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {1, 2, 3 }, new int[] {1, 2 });
    OGMatrix expected = new OGMatrix(new double[] {5, 8, 11, 6, 9, 12 }, 3, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetSeqRowsRandomColsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {1, 2, 3 }, new int[] {2, 0 });
    OGMatrix expected = new OGMatrix(new double[] {6, 9, 12, 4, 7, 10 }, 3, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetRandomRowsRandomColsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {0, 3, 2 }, new int[] {2, 0 });
    OGMatrix expected = new OGMatrix(new double[] {3, 12, 9, 1, 10, 7 }, 3, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  // test get No elements
  @Test
  public void testGetNoElementsTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfElements() == 12);
  }

  // test get data
  @Test
  public void testGetDataTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    assertTrue(Arrays.equals(D.getData(), data4x3unwound));
  }

  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObj() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNull() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    assertFalse(D.equals(null));
  }

  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClass() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    assertFalse(D.equals(new Double(1.)));
  }

  // test equals obj class ok, diff cols
  @Test
  public void testEqualsObjDifferentCols() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix Diff = new OGMatrix(7);
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols diff rows
  @Test
  public void testEqualsObjDifferentRows() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix Diff = new OGMatrix(new double[][] {{1, 2, 3 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testEqualsObjDifferentData() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix Diff = new OGMatrix(new double[][] { {999, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdentical() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix Diff = new OGMatrix(new double[][] { {1.00, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } });
    assertTrue(D.equals(Diff));
  }

  // test fuzzy equals obj points to obj
  @Test
  public void testFuzzyEqualsObjeqObj() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    assertTrue(D.fuzzyequals(D, 10 * D1mach.four()));
  }

  // test fuzzy equals obj not = null
  @Test
  public void testFuzzyEqualsObjNull() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    assertFalse(D.fuzzyequals(null, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class different
  @Test
  public void testFuzzyEqualsObjDifferentClass() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    assertFalse(D.fuzzyequals(new Double(1.), 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, diff cols
  @Test
  public void testFuzzyEqualsObjDifferentCols() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix Diff = new OGMatrix(7);
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, same cols diff rows
  @Test
  public void testFuzzyEqualsObjDifferentRows() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix Diff = new OGMatrix(new double[][] {{1, 2, 3 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows different data
  @Test
  public void testFuzzyEqualsObjDifferentData() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix Diff = new OGMatrix(new double[][] { {999, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows same data
  @Test
  public void testFuzzyEqualsObjStructurallyIdentical() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix Same = new OGMatrix(new double[][] { {1.00 + 9 * D1mach.four(), 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } });
    assertTrue(D.fuzzyequals(Same, 10 * D1mach.four()));
  }

  // test hash code
  @Test
  public void testHashCodeTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.hashCode();
  }

  // test toString code
  @Test
  public void testToStringTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    D.toString();
  }

  @Test
  public void parserLinearModeTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix expected;
    double[][] re = new double[][] {{4., 7., 10. } };
    expected = new OGMatrix(re);
    assertTrue(expected.fuzzyequals(D.get("1:3"), 10 * D1mach.four()));

    expected = new OGMatrix(data4x3unwound, 1, 12);
    assertTrue(expected.fuzzyequals(D.get(":"), 10 * D1mach.four()));
  }

  @Test
  public void parser2DModeTest() {
    OGMatrix D = new OGMatrix(data4x3unwound, 4, 3);
    OGMatrix expected;
    double[][] re = new double[][] { {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
    expected = new OGMatrix(re);
    assertTrue(expected.fuzzyequals(D.get("1:3,:"), 10 * D1mach.four()));

    re = new double[][] { {2., 3. }, {5., 6. }, {8., 9. }, {11., 12. } };

    expected = new OGMatrix(re);
    assertTrue(expected.fuzzyequals(D.get(":,1:2"), 10 * D1mach.four()));

    expected = new OGMatrix(data4x3unwound, 4, 3);
    assertTrue(expected.fuzzyequals(D.get(":,:"), 10 * D1mach.four()));
  }

}
