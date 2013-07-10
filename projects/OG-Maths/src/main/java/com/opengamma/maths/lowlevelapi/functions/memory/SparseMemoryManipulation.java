/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.memory;

import java.util.Arrays;

import com.opengamma.maths.highlevelapi.datatypes.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Functions that manipulate sparse memory
 */
public class SparseMemoryManipulation {

  /**
   * Distributes the values of a sparse matrix into a "full" sparse matrix with the zero values replaced with a specified value
   * @param array1 the array from which the nonzero bits will be taken
   * @param theFillInValue the replacement value for the zeros
   * @return a "full" sparse
   */
  public static OGSparseMatrix createFullSparseMatrixWithNewFillValueInBasedOnStructureOf(OGSparseMatrix array1, double theFillInValue) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] data = array1.getData();
    double[] tmp = new double[rows * cols];
    int[] rowIdxTmp = new int[rows * cols];
    int[] colPtrTmp = new int[cols + 1];

    // fill tmp with value
    Arrays.fill(tmp, theFillInValue);

    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i <= colPtr[ir + 1] - 1; i++) {
        tmp[rowIdx[i] + ir * rows] = data[i];
      }
    }
    // new row idx for full matrix
    for (int i = 0; i < rows; i++) {
      rowIdxTmp[i] = i;
    }
    for (int i = 1; i < cols; i++) { // replicate
      System.arraycopy(rowIdxTmp, 0, rowIdxTmp, i * rows, rows);
    }

    // new col ptr for full matrix
    for (int i = 1; i < cols + 1; i++) {
      colPtrTmp[i] = colPtrTmp[i - 1] + rows;
    }
    colPtrTmp[cols] = cols * rows;
    return new OGSparseMatrix(colPtrTmp, rowIdxTmp, tmp, rows, cols);
  }

  public static OGSparseMatrix createFullSparseMatrixWithNewFillValueInANDNewValuesBasedOnStructureOf(OGSparseMatrix array1, double[] newValues, double theFillInValue) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] data = array1.getData();
    double[] tmp = new double[rows * cols];
    int[] rowIdxTmp = new int[rows * cols];
    int[] colPtrTmp = new int[cols + 1];

    Catchers.catchCondition(data.length != newValues.length, "Data length in sparse matrix is not the same as the length of the proposed new data.");

    // fill tmp with value
    Arrays.fill(tmp, theFillInValue);

    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i <= colPtr[ir + 1] - 1; i++) {
        tmp[rowIdx[i] + ir * rows] = newValues[i];
      }
    }
    // new row idx for full matrix
    for (int i = 0; i < rows; i++) {
      rowIdxTmp[i] = i;
    }
    for (int i = 1; i < cols; i++) { // replicate
      System.arraycopy(rowIdxTmp, 0, rowIdxTmp, i * rows, rows);
    }

    // new col ptr for full matrix
    for (int i = 1; i < cols + 1; i++) {
      colPtrTmp[i] = colPtrTmp[i - 1] + rows;
    }
    colPtrTmp[cols] = cols * rows;
    return new OGSparseMatrix(colPtrTmp, rowIdxTmp, tmp, rows, cols);
  }

  /**
   * Distributes the values of a complex sparse matrix into a "full" complex sparse matrix with the zero values replaced with a specified value
   * @param array1 the array from which the nonzero bits will be taken
   * @param theFillInValue the replacement interleaved complex value for the zeros
   * @return a "full" complex sparse
   */
  public static OGComplexSparseMatrix createFullComplexSparseMatrixWithNewFillValueInBasedOnStructureOf(OGComplexSparseMatrix array1, double[] theFillInValue) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] data = array1.getData();
    double[] tmp = new double[2 * rows * cols];
    int[] rowIdxTmp = new int[rows * cols];
    int[] colPtrTmp = new int[cols + 1];
    int idx, ptr = 0;
    // fill tmp with value
    DenseMemoryManipulation.fillArrayWithInterleavedComplexValue(tmp, theFillInValue);

    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
        idx = 2 * (rowIdx[i] + ir * rows);
        tmp[idx] = data[ptr++];
        tmp[idx + 1] = data[ptr++];
      }
    }
    // new row idx for full matrix
    for (int i = 0; i < rows; i++) {
      rowIdxTmp[i] = i;
    }
    for (int i = 1; i < cols; i++) { // replicate
      System.arraycopy(rowIdxTmp, 0, rowIdxTmp, i * rows, rows);
    }

    // new col ptr for full matrix
    for (int i = 1; i < cols + 1; i++) {
      colPtrTmp[i] = colPtrTmp[i - 1] + rows;
    }
    colPtrTmp[cols] = cols * rows;
    return new OGComplexSparseMatrix(colPtrTmp, rowIdxTmp, tmp, rows, cols);
  }

  /**
   * Distributes the values of a sparse matrix into a "full" complex sparse matrix with the zero values replaced with a specified value
   * @param array1 the array from which the nonzero bits will be taken
   * @param theFillInValue the replacement interleaved complex value for the zeros
   * @return a "full" complex sparse
   */
  public static OGComplexSparseMatrix createFullComplexSparseMatrixWithNewFillValueInBasedOnStructureOf(OGSparseMatrix array1, double[] theFillInValue) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] data = array1.getData();
    double[] tmp = new double[2 * rows * cols];
    int[] rowIdxTmp = new int[rows * cols];
    int[] colPtrTmp = new int[cols + 1];
    int idx, ptr = 0;
    // fill tmp with value
    DenseMemoryManipulation.fillArrayWithInterleavedComplexValue(tmp, theFillInValue);

    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
        idx = 2 * (rowIdx[i] + ir * rows);
        tmp[idx] = data[ptr++];
        tmp[idx + 1] = data[ptr++];
      }
    }
    // new row idx for full matrix
    for (int i = 0; i < rows; i++) {
      rowIdxTmp[i] = i;
    }
    for (int i = 1; i < cols; i++) { // replicate
      System.arraycopy(rowIdxTmp, 0, rowIdxTmp, i * rows, rows);
    }

    // new col ptr for full matrix
    for (int i = 1; i < cols + 1; i++) {
      colPtrTmp[i] = colPtrTmp[i - 1] + rows;
    }
    colPtrTmp[cols] = cols * rows;
    return new OGComplexSparseMatrix(colPtrTmp, rowIdxTmp, tmp, rows, cols);
  }

  /**
   * Distributes the values of a complex sparse matrix into a "full" complex sparse matrix with the zero values replaced with a specified value
   * @param array1 the array from which the nonzero bits will be taken
   * @param newValues the new values for the matrix
   * @param theFillInValue the replacement interleaved complex value for the zeros
   * @return a "full" complex sparse
   */
  public static OGComplexSparseMatrix createFullComplexSparseMatrixWithNewFillValueInANDNewValuesBasedOnStructureOf(OGComplexSparseMatrix array1, double[] newValues, double[] theFillInValue) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] data = array1.getData();
    double[] tmp = new double[2 * rows * cols];
    int[] rowIdxTmp = new int[rows * cols];
    int[] colPtrTmp = new int[cols + 1];
    int idx, ptr = 0;
    // fill tmp with value
    DenseMemoryManipulation.fillArrayWithInterleavedComplexValue(tmp, theFillInValue);

    Catchers.catchCondition(data.length != newValues.length, "Data length in sparse matrix is not the same as the length of the proposed new data.");

    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
        idx = 2 * (rowIdx[i] + ir * rows);
        tmp[idx] = newValues[ptr++];
        tmp[idx + 1] = newValues[ptr++];
      }
    }
    // new row idx for full matrix
    for (int i = 0; i < rows; i++) {
      rowIdxTmp[i] = i;
    }
    for (int i = 1; i < cols; i++) { // replicate
      System.arraycopy(rowIdxTmp, 0, rowIdxTmp, i * rows, rows);
    }

    // new col ptr for full matrix
    for (int i = 1; i < cols + 1; i++) {
      colPtrTmp[i] = colPtrTmp[i - 1] + rows;
    }
    colPtrTmp[cols] = cols * rows;
    return new OGComplexSparseMatrix(colPtrTmp, rowIdxTmp, tmp, rows, cols);
  }

  /**
   * Distributes the values of a sparse matrix into a "full" complex sparse matrix with the zero values replaced with a specified value
   * @param array1 the array from which the nonzero bits will be taken
   * @param newValues the new values for the matrix
   * @param theFillInValue the replacement interleaved complex value for the zeros
   * @return a "full" complex sparse
   */
  public static OGComplexSparseMatrix createFullComplexSparseMatrixWithNewFillValueInANDNewValuesBasedOnStructureOf(OGSparseMatrix array1, double[] newValues, double[] theFillInValue) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] data = array1.getData();
    double[] tmp = new double[2 * rows * cols];
    int[] rowIdxTmp = new int[rows * cols];
    int[] colPtrTmp = new int[cols + 1];
    int idx, ptr = 0;
    // fill tmp with value
    DenseMemoryManipulation.fillArrayWithInterleavedComplexValue(tmp, theFillInValue);

    Catchers.catchCondition(2 * data.length != newValues.length, "Data length in sparse matrix is not the same as the length of the proposed new data.");

    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
        idx = 2 * (rowIdx[i] + ir * rows);
        tmp[idx] = newValues[ptr++];
        tmp[idx + 1] = newValues[ptr++];
      }
    }
    // new row idx for full matrix
    for (int i = 0; i < rows; i++) {
      rowIdxTmp[i] = i;
    }
    for (int i = 1; i < cols; i++) { // replicate
      System.arraycopy(rowIdxTmp, 0, rowIdxTmp, i * rows, rows);
    }

    // new col ptr for full matrix
    for (int i = 1; i < cols + 1; i++) {
      colPtrTmp[i] = colPtrTmp[i - 1] + rows;
    }
    colPtrTmp[cols] = cols * rows;
    return new OGComplexSparseMatrix(colPtrTmp, rowIdxTmp, tmp, rows, cols);
  }
}
