/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.memory;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.datatypes.primitive.MatrixPrimitiveUtils;

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

}
