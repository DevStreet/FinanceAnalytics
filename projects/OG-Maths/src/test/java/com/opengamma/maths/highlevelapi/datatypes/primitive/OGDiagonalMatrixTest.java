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
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;

/**
 * Tests the {@link OGDiagonalMatrix} Class
 */
public class OGDiagonalMatrixTest {

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
  public void testGetNumColumnsTest() {
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

  // test get entry bad col index
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

  // test set entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testSetEntryBadRowIndicesTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.setEntry(23, 1, 1);
  }

  // test set entry bad col index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testSetEntryBadColumnIndicesTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.setEntry(1, 23, 1);
  }

  // test set null Number
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testSetEntryNullNumberTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.setEntry(1, 1, null);
  }

  // test set entry ok
  @Test
  public void testSetEntryOnDiagIndicesTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    Number val = 1337;
    for (int i = 0; i < D.getNumberOfColumns(); i++) {
      assertTrue(D.setEntry(i, i, val).getEntry(i, i).doubleValue() == val.doubleValue());
    }
    // make sure the underlying data is now all val
    double[] dataFinal = D.getData();
    for (int i = 0; i < dataFinal.length; i++) {
      assertTrue(dataFinal[i] == val.doubleValue());
    }
  }

  @Test
  public void testSetEntryOffDiagIndicesTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix orig = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGMatrix expected;
    double[] expectedData;
    Number val = 1337;
    OGArray<? extends Number> answer;
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        if (i != j) { // test off diags
          expectedData = new double[4 * 3];
          for (int k = 0; k < 3; k++) {
            expectedData[k + k * 4] = k + 1;
          }
          expectedData[j * 4 + i] = val.doubleValue();
          expected = new OGMatrix(expectedData, 4, 3);
          D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
          answer = D.setEntry(i, j, val);
          assertTrue(expected.fuzzyequals(answer, 1e-14));
          // make original is unchanged
          assertTrue(D.fuzzyequals(orig, 1e-14));
        }
      }
    }
  }

  // test type promotion
  @Test
  public void testSetEntryOnDiagIndiciesTypePromotionTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    Number val = new ComplexType(13, 37);
    OGArray<? extends Number> answer = D.setEntry(2, 2, val);

    // make original is unchanged
    assertTrue(D.fuzzyequals(new OGDiagonalMatrix(data4x3diagd, 4, 3), 1e-14));

    // make sure promotion has occurred
    assertTrue(answer instanceof OGComplexDiagonalMatrix);

    // make sure data is ok
    double[] cd = new double[] {1, 0, 2, 0, 13, 37 };
    OGComplexDiagonalMatrix cmplx = new OGComplexDiagonalMatrix(cd, 4, 3);
    assertTrue(answer.fuzzyequals(cmplx, 1e-14));
  }

  @Test
  public void testSetEntryOffDiagIndicesTypePromotionTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix orig = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGComplexMatrix expected;
    double[] expectedData;
    Number val = new ComplexType(13, 37);
    OGArray<? extends Number> answer;
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        if (i != j) { // test off diags
          expectedData = new double[4 * 3 * 2];
          for (int k = 0; k < 3; k++) {
            expectedData[2 * (k + k * 4)] = k + 1;
          }
          expectedData[2 * (j * 4 + i)] = ((ComplexType) val).getReal();
          expectedData[2 * (j * 4 + i) + 1] = ((ComplexType) val).getImag();
          expected = new OGComplexMatrix(expectedData, 4, 3);
          D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
          answer = D.setEntry(i, j, val);
          assertTrue(expected.fuzzyequals(answer, 1e-14));
          // make original is unchanged
          assertTrue(D.fuzzyequals(orig, 1e-14));
        }
      }
    }
  }

  // test get col neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColNegIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getColumn(-1);
  }

  // test get col bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnBadIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getColumn(23);
  }

  // test get col ok
  @Test
  public void testGetColumnOkIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGArray<? extends Number> col = D.getColumn(1);
    OGMatrix getCol = new OGMatrix(new double[] {0, 2, 0, 0 }, 4, 1);
    assertTrue(col.equals(getCol));
  }

  // test get cols null index
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetColumnsNullTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getColumns(null);
  }

  // test get cols neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsNegIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getColumns(-1);
  }

  // test get cols bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetColumnsBadIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getColumns(23);
  }

  // test get cols ok
  @Test
  public void testGetColumnsOkIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGArray<? extends Number> col = D.getColumns(2, 0);
    OGMatrix getCol = new OGMatrix(new double[][] { {0., 1. }, {0., 0. }, {3., 0. }, {0., 0. } });
    assertTrue(col.equals(getCol));
  }

  // test get row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowNegIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getRow(-1);
  }

  // test get row bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowBadIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getRow(23);
  }

  // test get row ok
  @Test
  public void testGetRowOkIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGArray<? extends Number> row = D.getRow(1);
    OGMatrix getRow = new OGMatrix(new double[] {0, 2, 0 }, 1, 3);
    assertTrue(row.equals(getRow));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullRowsTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.get(null, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetNullColsTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.get(new int[] {1, 2, 3 }, null);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegRowsTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.get(new int[] {-1 }, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetNegColsTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.get(new int[] {1, 2, 3 }, new int[] {-1 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBRowsTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.get(new int[] {23 }, new int[] {1, 2 });
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetOOBColsTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.get(new int[] {1, 2, 3 }, new int[] {23 });
  }

  @Test
  public void testGetSeqRowsSeqColsInRangeTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {1, 2, 3 }, new int[] {1, 2 });
    OGDiagonalMatrix expected = new OGDiagonalMatrix(new double[] {2, 3 }, 3, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetSeqRowsSeqColsOutOfRangeTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {3 }, new int[] {1, 2 });
    OGMatrix expected = new OGMatrix(new double[] {0, 0 }, 1, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  @Test
  public void testGetNonSeqTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGArray<? extends Number> answer = D.get(new int[] {2, 0, 3 }, new int[] {2, 0 });
    OGMatrix expected = new OGMatrix(new double[] {3.0, 0.0, 0.0, 0.0, 1.0, 0.0 }, 3, 2);
    assertTrue(expected.fuzzyequals(answer, 10 * D1mach.four()));
  }

  // test get rows null index
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testGetRowsNullTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getRows(null);
  }

  // test get rows neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsNegIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getRows(-1);
  }

  // test get rows bad index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetRowsBadIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    D.getRows(23);
  }

  // test get rows ok
  @Test
  public void testGetRowsOkIndexTest() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGArray<? extends Number> row = D.getRows(2, 0);
    OGMatrix getRows = new OGMatrix(new double[][] { {0., 0., 3. }, {1., 0., 0. } });
    assertTrue(row.equals(getRows));
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
    assertTrue(D.fuzzyequals(D, 10 * D1mach.four()));
  }

  // test fuzzy equals obj not = null
  @Test
  public void testFuzzyEqualsObjNull() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    assertFalse(D.fuzzyequals(null, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class different
  @Test
  public void testFuzzyEqualsObjDifferentClass() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    assertFalse(D.fuzzyequals(new Double(1.), 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, diff cols
  @Test
  public void testFuzzyEqualsObjDifferentCols() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Diff = new OGDiagonalMatrix(7);
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, same cols diff rows
  @Test
  public void testFuzzyEqualsObjDifferentRows() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Diff = new OGDiagonalMatrix(new double[] {1, 2, 3 }, 4, 13);
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows different data
  @Test
  public void testFuzzyEqualsObjDifferentData() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Diff = new OGDiagonalMatrix(new double[] {999.00, 2.00, 3.00 }, 4, 3);
    assertFalse(D.fuzzyequals(Diff, 10 * D1mach.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows same data
  @Test
  public void testFuzzyEqualsObjStructurallyIdentical() {
    OGDiagonalMatrix D = new OGDiagonalMatrix(data4x3diagd, 4, 3);
    OGDiagonalMatrix Same = new OGDiagonalMatrix(new double[] {1.00 + 9 * D1mach.four(), 2.00, 3.00 }, 4, 3);
    assertTrue(D.fuzzyequals(Same, 10 * D1mach.four()));
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
