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
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.ogblas.auxiliary.D1MACH;

/**
 * 
 */
public class OGComplexSparseMatrixTest {
  double[][] realData = { {1, 2, 0, 0 }, {5, 0, 7, 0 }, {0, 10, 11, 0 }, {0, 0, 15, 0 } };
  double[][] imagData = { {10, 20, 30, 0 }, {0, 60, 70, 0 }, {90, 100, 0, 120 }, {0, 0, 0, 160 } };
  double[] compressedRealData = {1, 0, 5, 0, 2, 0, 10, 0, 7, 0, 11, 0, 15, 0 }; // compressed real part interleaved with zero as no imag given

  double[] compressedRealPartOfMixed = {1, 5, 0, 2, 0, 10, 0, 7, 11, 15, 0, 0 }; // compressed real part of the full data set, zeros added when imag exists and real doesn't
  double[] compressedImagPartOfMixed = {10, 0, 90, 20, 60, 100, 30, 70, 0, 0, 120, 160 }; // compressed real part of the full data set, zeros added when imag exists and real doesn't  
  double[] compressedMixedData = {1, 10, 5, 0, 0, 90, 2, 20, 0, 60, 10, 100, 0, 30, 7, 70, 11, 0, 15, 0, 0, 120, 0, 160 };
  int[] compressedColPtr = {0, 3, 6, 10, 12 };
  int[] compressedRowIdx = {0, 1, 2, 0, 1, 2, 0, 1, 2, 3, 2, 3 };
  double[][] dataAsFullTransposed = { {1, 10, 5, 0, 0, 90, 0, 0 }, {2, 20, 0, 60, 10, 100, 0, 0 }, {0, 30, 7, 70, 11, 0, 15, 0 }, {0, 0, 0, 0, 0, 120, 0, 160 } };
  double[][] dataForEqualsTests = { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } };
  
  ComplexType[][] complexTData = { //
  {new ComplexType(1, 10), new ComplexType(2, 20), new ComplexType(0, 30), new ComplexType(0, 0) }, //
      {new ComplexType(5, 0), new ComplexType(0, 60), new ComplexType(7, 70), new ComplexType(0, 0) }, //
      {new ComplexType(0, 90), new ComplexType(10, 100), new ComplexType(11, 0), new ComplexType(0, 120) }, //
      {new ComplexType(0, 0), new ComplexType(0, 0), new ComplexType(15, 0), new ComplexType(0, 160) }, };

  // sending in null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorNullPtrTest() {
    double[][] tmp = null;
    new OGComplexSparseMatrix(tmp);
  }

  // sending in embedded null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorOKThenNullPtrTest() {
    double[][] tmp = new double[2][1];
    tmp[0] = new double[] {1 };
    tmp[1] = null;
    new OGComplexSparseMatrix(tmp);
  }

  // sending in null ptr double[][],double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorNullPtrFirstArgTest() {
    double[][] tmp1 = null;
    double[][] tmp2 = imagData;
    new OGComplexSparseMatrix(tmp1, tmp2);
  }

  // sending in embedded null ptr double[][],double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorOKThenNullPtrFirstArgTest() {
    double[][] tmp1 = new double[2][1];
    tmp1[0] = new double[] {1 };
    tmp1[1] = null;
    double[][] tmp2 = imagData;
    new OGComplexSparseMatrix(tmp1, tmp2);
  }

  // sending in null ptr double[][],double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorNullPtrSecondArgTest() {
    double[][] tmp2 = null;
    double[][] tmp1 = realData;
    new OGComplexSparseMatrix(tmp1, tmp2);
  }

  // sending in embedded null ptr double[][],double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorOKThenNullPtrSecondArgTest() {
    double[][] tmp2 = new double[2][1];
    tmp2[0] = new double[] {1 };
    tmp2[1] = null;
    double[][] tmp1 = realData;
    new OGComplexSparseMatrix(tmp1, tmp2);
  }

  // sending in null ptr pos1 int[], int[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCSingleInterleavedBlockConstructorNullPtrPos1Test() {
    new OGComplexSparseMatrix(null, new int[1], new double[1], 1, 1);
  }

  // sending in null ptr pos2 int[], int[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCSingleInterleavedBlockConstructorNullPtrPos2Test() {
    new OGComplexSparseMatrix(new int[1], null, new double[1], 1, 1);
  }

  // sending in null ptr pos3 int[], int[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCSingleInterleavedBlockConstructorNullPtrPos3Test() {
    new OGComplexSparseMatrix(new int[1], new int[1], null, 1, 1);
  }

  // sending in null ptr pos1 int[], int[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCSeparateBlockConstructorNullPtrPos1Test() {
    new OGComplexSparseMatrix(null, new int[1], new double[1], new double[1], 1, 1);
  }

  // sending in null ptr pos2 int[], int[], double[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCSeparateBlockConstructorNullPtrPos2Test() {
    new OGComplexSparseMatrix(new int[1], null, new double[1], new double[1], 1, 1);
  }

  // sending in null ptr pos3 int[], int[], double[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCSeparateBlockConstructorNullPtrPos3Test() {
    new OGComplexSparseMatrix(new int[1], new int[1], null, new double[1], 1, 1);
  }

  // sending in null ptr pos4 int[], int[], double[], double[], int, int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testCSCSeparateBlockConstructorNullPtrPos4Test() {
    new OGComplexSparseMatrix(new int[1], new int[1], new double[1], null, 1, 1);
  }

  // Raggeds

  // sending in ragged[][], double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrConstructorRaggedTest() {
    double[][] tmp = new double[2][];
    tmp[0] = new double[] {1 };
    tmp[1] = new double[] {1, 2, 3 };
    new OGComplexSparseMatrix(tmp);
  }

  // sending in ragged[][] double[][],double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorRaggedFirstArgTest() {
    double[][] tmp1 = new double[2][];
    tmp1[0] = new double[] {1 };
    tmp1[1] = new double[] {1, 2, 3 };
    new OGComplexSparseMatrix(tmp1, imagData);
  }

  // sending in ragged[][] double[][],double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorRaggedSecondArgTest() {
    double[][] tmp2 = new double[2][];
    tmp2[0] = new double[] {1 };
    tmp2[1] = new double[] {1, 2, 3 };
    new OGComplexSparseMatrix(realData, tmp2);
  }

  // sending in ragged[][] ComplexType[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testComplexTypePtrPtrConstructorRaggedSecondArgTest() {
    ComplexType[][] tmp = new ComplexType[2][];
    tmp[0] = new ComplexType[] {new ComplexType(1) };
    tmp[1] = new ComplexType[] {new ComplexType(1), new ComplexType(2), new ComplexType(3) };
    new OGComplexSparseMatrix(tmp);
  }

  // Zeros
  //sending in all zeros to double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrConstructorAllZerosTest() {
    new OGComplexSparseMatrix(new double[][] { {0, 0, 0 }, {0, 0, 0 } });
  }

  //sending in all zeros first arg to double[][], double[][] constructor
  public void testDoublePtrPtrDoublePtrPtrConstructorAllZerosFirstArgTest() {
    new OGComplexSparseMatrix(new double[][] { {0, 0, 0 }, {0, 0, 0 } }, new double[][] { {1, 2, 3 }, {0, 0, 6 } });
  }

  //sending in all zeros second arg to double[][], double[][] constructor
  public void testDoublePtrPtrDoublePtrPtrConstructorAllZerosSecondArgTest() {
    new OGComplexSparseMatrix(new double[][] { {1, 2, 3 }, {0, 0, 6 } }, new double[][] { {0, 0, 0 }, {0, 0, 0 } });
  }

  //sending in all zeros both args to double[][], double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorAllZerosBothArgsTest() {
    new OGComplexSparseMatrix(new double[][] { {0, 0, 0 }, {0, 0, 0 } }, new double[][] { {0, 0, 0 }, {0, 0, 0 } });
  }

  //sending in all zeros data  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorAllZerosTest() {
    new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 4, 4);
  }

  //sending in all zeros data  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorAllZerosInBothPartsTest() {
    new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 4, 4);
  }

  //sending in all zeros data  int[], int[], double[], int, int  constructor
  // Is fine, just trips other branch for coverage purposes
  public void testDoublePtrDoublePtrConstructorAllZerosInRealPartTest() {
    new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, new double[] {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 4, 4);
  }

  // sending in zeros data ComplexType[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testComplexPtrPtrConstructorAllZerosTest() {
    ComplexType[][] tmp = new ComplexType[2][];
    tmp[0] = new ComplexType[] {new ComplexType(0), new ComplexType(0), new ComplexType(0) };
    tmp[1] = new ComplexType[] {new ComplexType(0), new ComplexType(0), new ComplexType(0) };
    new OGComplexSparseMatrix(tmp);
  }

  // diff rows and cols
  // sending in diff rows double[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorRowsAreDifferentTest() {
    double[][] tmp = new double[2][];
    tmp[0] = new double[] {1, 2, 3 };
    tmp[1] = new double[] {1, 2, 3 };
    new OGComplexSparseMatrix(realData, tmp);
  }

  // sending in diff cols double[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorColumnsAreDifferentTest() {
    double[][] tmp = new double[4][];
    tmp[0] = new double[] {1, 2 };
    tmp[1] = new double[] {1, 2 };
    tmp[2] = new double[] {1, 2 };
    tmp[3] = new double[] {1, 2 };
    new OGComplexSparseMatrix(realData, tmp);
  }

  // bad rows and cols ptrs
  //sending in bad rows int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadRowsDataTest() {
    new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, compressedMixedData, -1, 4);
  }

  //sending in bad cols int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadColsDataTest() {
    new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, compressedMixedData, 4, -1);
  }

  //sending in too many cols int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorDataCountDataTest() {
    new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, compressedMixedData, 4, 5);
  }

  // sending in bad ColumnPtr  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadColPtrTooLongTest() {
    new OGComplexSparseMatrix(new int[] {0, 2, 4, 5, 6, 7 }, compressedRowIdx, compressedMixedData, 4, 4);
  }

  // sending in bad ColumnPtr  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadColPtrValuesAreNotIncreasingTest() {
    new OGComplexSparseMatrix(new int[] {0, 2, 0, 7, 7 }, compressedRowIdx, compressedMixedData, 4, 4);
  }

  // sending in bad ColumnPtr  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadColPtrValuesIsNegativeTest() {
    new OGComplexSparseMatrix(new int[] {-1, 2, 0, 7, 7 }, compressedRowIdx, compressedMixedData, 4, 4);
  }

  // sending in bad rowIdx  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadRowIdxTooHighTest() {
    new OGComplexSparseMatrix(compressedColPtr, new int[] {0, 1, 0, 2, 1, 6, 3 }, compressedMixedData, 4, 4);
  }

  // sending in bad rowIdx  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadRowIdxTooLowTest() {
    new OGComplexSparseMatrix(compressedColPtr, new int[] {0, 1, 0, 2, 1, -1, 3 }, compressedMixedData, 4, 4);
  }

  // sending in bad rowIdx  int[], int[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorbadRowIdxLengthTest() {
    new OGComplexSparseMatrix(compressedColPtr, new int[] {0, 1, 0, 2, 1, 1 }, compressedMixedData, 4, 4);
  }

  //sending in bad rows int[], int[], double[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorBadRowsDataTest() {
    new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, compressedRealPartOfMixed, compressedImagPartOfMixed, -1, 4);
  }

  //sending in bad cols int[], int[], double[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorBadColsDataTest() {
    new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, compressedRealPartOfMixed, compressedImagPartOfMixed, 4, -1);
  }

  //sending in too many cols int[], int[], double[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorDataCountDataTest() {
    new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, compressedRealPartOfMixed, compressedImagPartOfMixed, 4, 5);
  }

  // sending in bad ColumnPtr  int[], int[], double[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorbadColPtrTooLongTest() {
    new OGComplexSparseMatrix(new int[] {0, 2, 4, 5, 6, 7 }, compressedRowIdx, compressedRealPartOfMixed, compressedImagPartOfMixed, 4, 4);
  }

  // sending in bad ColumnPtr  int[], int[], double[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorbadColPtrValuesAreNotIncreasingTest() {
    new OGComplexSparseMatrix(new int[] {0, 2, 0, 7, 7 }, compressedRowIdx, compressedRealPartOfMixed, compressedImagPartOfMixed, 4, 4);
  }

  // sending in bad ColumnPtr  int[], int[], double[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorbadColPtrValuesIsNegativeTest() {
    new OGComplexSparseMatrix(new int[] {-1, 2, 0, 7, 7 }, compressedRowIdx, compressedRealPartOfMixed, compressedImagPartOfMixed, 4, 4);
  }

  // sending in bad rowIdx  int[], int[], double[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorbadRowIdxTooHighTest() {
    new OGComplexSparseMatrix(compressedColPtr, new int[] {0, 1, 0, 2, 1, 6, 3 }, compressedRealPartOfMixed, compressedImagPartOfMixed, 4, 4);
  }

  // sending in bad rowIdx  int[], int[], double[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorbadRowIdxTooLowTest() {
    new OGComplexSparseMatrix(compressedColPtr, new int[] {0, 1, 0, 2, 1, -1, 3 }, compressedRealPartOfMixed, compressedImagPartOfMixed, 4, 4);
  }

  // sending in bad rowIdx  int[], int[], double[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorbadRowIdxLengthTest() {
    new OGComplexSparseMatrix(compressedColPtr, new int[] {0, 1, 0, 2, 1, 1 }, compressedRealPartOfMixed, compressedImagPartOfMixed, 4, 4);
  }

  // sending in lengths that don't commute, imag is 1 short. int[], int[], double[], double[], int, int  constructor 
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorRealAndImagDataDoNotCommuteTest() {
    new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, compressedRealPartOfMixed, new double[] {10, 0, 90, 20, 60, 100, 30, 70, 0, 0, 120 }, 4, 4);
  }

  // OKs  
  // sending in ok double[][] double[][] constructor
  @Test
  public void testDoublePtrPtrDoublePtrPtrConstructorInternalDataTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    assertTrue(D.getClass() == OGComplexSparseMatrix.class);
    assertTrue(Arrays.equals(D.getData(), compressedMixedData));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  // sending in ok double[][] constructor
  @Test
  public void testDoublePtrPtrConstructorInternalDataTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData);
    assertTrue(D.getClass() == OGComplexSparseMatrix.class);
    assertTrue(Arrays.equals(D.getData(), compressedRealData));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  @Test
  public void testDoublePtrConstructorInternalDataTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, compressedMixedData, 4, 4);
    assertTrue(D.getClass() == OGComplexSparseMatrix.class);
    assertTrue(Arrays.equals(D.getData(), compressedMixedData));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  @Test
  public void testDoublePtrDoublePtrConstructorInternalDataTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(compressedColPtr, compressedRowIdx, compressedRealPartOfMixed, compressedImagPartOfMixed, 4, 4);
    assertTrue(D.getClass() == OGComplexSparseMatrix.class);
    assertTrue(Arrays.equals(D.getData(), compressedMixedData));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  @Test
  public void testComplexTypePtrPtrConstructorInternalDataTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(complexTData);
    assertTrue(D.getClass() == OGComplexSparseMatrix.class);
    assertTrue(Arrays.equals(D.getData(), compressedMixedData));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  // functions
  // test get rows
  @Test
  public void testGetNumRowsTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    assertTrue(D.getNumberOfRows() == 4);
  }

  // test get cols
  @Test
  public void testGetNumColumnsTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    assertTrue(D.getNumberOfColumns() == 4);
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    D.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadLargeRowIndicesTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    D.getEntry(23, 1);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadNegRowIndicesTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    D.getEntry(-1, 1);
  }

  // test get entry bad col index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadLargeColumnIndicesTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    D.getEntry(1, 23);
  }

  // test get entry bad col index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadNegColumnIndicesTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    D.getEntry(1, -1);
  }

  // test get full row
  @Test
  public void testGetFullRow() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    ComplexType[][] tmprow = new ComplexType[1][];
    for (int i = 0; i < 4; i++) {
      tmprow[0] = complexTData[i];
      OGComplexMatrix tmp = new OGComplexMatrix(tmprow);
      assertTrue(tmp.equals(D.getFullRow(i)));
    }
  }

  // test get full row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowBadIndexLow() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    D.getFullRow(-1);
  }

  // test get full row index overflow
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullRowBadIndexHigh() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    D.getFullRow(12);
  }

  // test get full col
  @Test
  public void testGetFullColumn() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    for (int i = 0; i < 4; i++) {
      OGComplexMatrix tmp = new OGComplexMatrix(dataAsFullTransposed[i], 4, 1);
      assertTrue(tmp.equals(D.getFullColumn(i)));
    }
  }
  
  // test get full row neg index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColBadIndexLow() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    D.getFullColumn(-1);
  }

  // test get full row index overflow
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetFullColBadIndexHigh() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    D.getFullColumn(12);
  }  

  // test get nnz
  @Test
  public void testGetNumberOfNonZeroElementsTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    assertTrue(D.getNumberOfNonZeroElements() == compressedMixedData.length / 2);
  }

  // test get row idx
  @Test
  public void testGetRowIndexTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    assertTrue(Arrays.equals(D.getRowIndex(), compressedRowIdx));
  }

  // test get col ptr
  @Test
  public void testGetColPtrTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    assertTrue(Arrays.equals(D.getColumnPtr(), compressedColPtr));
  }

  // test get data
  @Test
  public void testGetDataTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    assertTrue(Arrays.equals(D.getData(), compressedMixedData));
  }

  // test hash code
  @Test
  public void testHashCodeTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    D.hashCode();
  }

  // test toString code
  @Test
  public void testToStringTest() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(realData, imagData);
    D.toString();
  }

  
  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObj() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNull() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    assertFalse(D.equals(null));
  }

  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClass() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    assertFalse(D.equals(new Double(1.)));
  }

  // test equals obj class ok, diff cols
  @Test
  public void testEqualsObjDifferentCols() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Diff = new OGComplexSparseMatrix(new double[][] {{7 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols diff rows
  @Test
  public void testEqualsObjDifferentRows() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Diff = new OGComplexSparseMatrix(new double[][] {{1, 2, 3, 4 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testEqualsObjDifferentData() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Diff = new OGComplexSparseMatrix(new double[][] { {999, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows different col ptr
  @Test
  public void testEqualsObjDifferentColPtr() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Diff = new OGComplexSparseMatrix(new double[][] { {1, 0, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data, should bork on column ptr
  @Test
  public void testEqualsObjDifferentRowIdx() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Diff = new OGComplexSparseMatrix(new double[][] { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {5, 0, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdentical() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Diff = new OGComplexSparseMatrix(new double[][] { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertTrue(D.equals(Diff));
  }

  // test fuzzy equals obj points to obj
  @Test
  public void testFuzzyEqualsObjeqObj() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    assertTrue(D.fuzzyequals(D, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj not = null
  @Test
  public void testFuzzyEqualsObjNull() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    assertFalse(D.fuzzyequals(null, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class different
  @Test
  public void testFuzzyEqualsObjDifferentClass() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    assertFalse(D.fuzzyequals(new Double(1.), 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, diff cols
  @Test
  public void testFuzzyEqualsObjDifferentCols() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Diff = new OGComplexSparseMatrix(new double[][] {{7 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, same cols diff rows
  @Test
  public void testFuzzyEqualsObjDifferentRows() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Diff = new OGComplexSparseMatrix(new double[][] {{1, 2, 3, 4 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows different data
  @Test
  public void testFuzzyEqualsObjDifferentData() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Diff = new OGComplexSparseMatrix(new double[][] { {999, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test equals obj class ok, same cols same rows different col ptr
  @Test
  public void testFuzzyEqualsObjDifferentColPtr() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Diff = new OGComplexSparseMatrix(new double[][] { {1, 0, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test equals obj class ok, same cols same rows same data, should bork on column ptr
  @Test
  public void testFuzzyEqualsObjDifferentRowIdx() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Diff = new OGComplexSparseMatrix(new double[][] { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {5, 0, 6, 0 }, {0, 0, 7, 0 } });
    assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
  }

  // test fuzzy equals obj class ok, same cols same rows same data
  @Test
  public void testFuzzyEqualsObjStructurallyIdentical() {
    OGComplexSparseMatrix D = new OGComplexSparseMatrix(dataForEqualsTests);
    OGComplexSparseMatrix Same = new OGComplexSparseMatrix(new double[][] { {1 + 9 * D1MACH.four(), 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
    assertTrue(D.fuzzyequals(Same, 10 * D1MACH.four()));
  }

  
}
