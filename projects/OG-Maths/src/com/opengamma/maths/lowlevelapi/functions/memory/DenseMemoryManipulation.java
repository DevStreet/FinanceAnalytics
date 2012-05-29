/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.memory;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.datatypes.primitive.MatrixPrimitiveUtils;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Function for shipping data from one format to another
 */
public class DenseMemoryManipulation {

  /**
   * Converts a row major double pointer to a column major single pointer
   * @param dataIn a row major double[][] non ragged array
   * @return a column major double[] array
   */
  public static double[] convertRowMajorDoublePointerToColumnMajorSinglePointer(double[][] dataIn)
  {
    if (MatrixPrimitiveUtils.isRagged(dataIn)) {
      throw new MathsExceptionIllegalArgument("Backing array is ragged");
    }
    int rows = dataIn.length;
    int cols = dataIn[0].length;
    double[] tmp = new double[rows * cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        tmp[j * rows + i] = dataIn[i][j];
      }
    }
    return tmp;
  }

  /**
   * Converts a row major double pointer to a row major single pointer
   * @param dataIn a row major double[][] non ragged array
   * @return a row major double[] array
   */
  public static double[] convertRowMajorDoublePointerToRowMajorSinglePointer(double[][] dataIn)
  {
    if (MatrixPrimitiveUtils.isRagged(dataIn)) {
      throw new MathsExceptionIllegalArgument("Backing array is ragged");
    }
    int rows = dataIn.length;
    int cols = dataIn[0].length;
    double[] tmp = new double[rows * cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        tmp[i * cols + j] = dataIn[i][j];
      }
    }
    return tmp;
  }

  /**
   * Copies and array
   * @param dataIn the data to copy
   * @return a copy of dataIn
   */
  public static boolean[] memcpy(boolean[] dataIn) {
    Catchers.catchNull(dataIn);
    final int n = dataIn.length;
    boolean[] tmp = new boolean[n];
    System.arraycopy(dataIn, 0, tmp, 0, n);
    return tmp;
  }

  /**
   * Copies and array
   * @param dataIn the data to copy
   * @return a copy of dataIn
   */
  public static byte[] memcpy(byte[] dataIn) {
    Catchers.catchNull(dataIn);
    final int n = dataIn.length;
    byte[] tmp = new byte[n];
    System.arraycopy(dataIn, 0, tmp, 0, n);
    return tmp;
  }
  
  /**
   * Copies and array
   * @param dataIn the data to copy
   * @return a copy of dataIn
   */
  public static short[] memcpy(short[] dataIn) {
    Catchers.catchNull(dataIn);
    final int n = dataIn.length;
    short[] tmp = new short[n];
    System.arraycopy(dataIn, 0, tmp, 0, n);
    return tmp;
  }  
    
  /**
   * Copies and array
   * @param dataIn the data to copy
   * @return a copy of dataIn
   */
  public static int[] memcpy(int[] dataIn) {
    Catchers.catchNull(dataIn);
    final int n = dataIn.length;
    int[] tmp = new int[n];
    System.arraycopy(dataIn, 0, tmp, 0, n);
    return tmp;
  }
  
  /**
   * Copies and array
   * @param dataIn the data to copy
   * @return a copy of dataIn
   */
  public static long[] memcpy(long[] dataIn) {
    Catchers.catchNull(dataIn);
    final int n = dataIn.length;
    long[] tmp = new long[n];
    System.arraycopy(dataIn, 0, tmp, 0, n);
    return tmp;
  }  
  
  /**
   * Copies and array
   * @param dataIn the data to copy
   * @return a copy of dataIn
   */
  public static float[] memcpy(float[] dataIn) {
    Catchers.catchNull(dataIn);
    final int n = dataIn.length;
    float[] tmp = new float[n];
    System.arraycopy(dataIn, 0, tmp, 0, n);
    return tmp;
  }  
  
  /**
   * Copies and array
   * @param dataIn the data to copy
   * @return a copy of dataIn
   */
  public static double[] memcpy(double[] dataIn) {
    Catchers.catchNull(dataIn);
    final int n = dataIn.length;
    double[] tmp = new double[n];
    System.arraycopy(dataIn, 0, tmp, 0, n);
    return tmp;
  }  
  
}
