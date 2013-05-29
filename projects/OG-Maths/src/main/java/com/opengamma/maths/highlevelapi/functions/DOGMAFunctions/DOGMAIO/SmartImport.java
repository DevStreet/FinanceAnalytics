/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIO;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.io.smartImport.SnortDataProperties;

/**
 * Imports data from various formats and tries to do something data aware when it comes to array construction
 */
public class SmartImport {

  /**
   * describes the orientation of a vector on import
   */
  public enum orientation {
    /** row vector */
    row,
    /** column vector */
    column
  }

  /**
   * Imports data from non-ragged (each row much have the same length) array of arrays
   * @param aMatrix the matrix to convert
   * @return an appropriate OG-Array type
   */
  public OGArray<Double> fromNativeArrayOfArrays(double[][] aMatrix) {
    SnortDataProperties snorter = new SnortDataProperties(aMatrix);
    OGArray<Double> ret = null;

    switch (snorter.getMatrixType()) {
      case upperTriangular:
      case lowerTriangular:
      case dense:
        ret = new OGMatrix(aMatrix);
        break;
      case diagonal:
      case identity:
        int rows = aMatrix.length;
        int cols = aMatrix[0].length;
        int nd = Math.min(rows, cols);
        double[] tmp = new double[nd];
        for (int i = 0; i < nd; i++) {
          tmp[i] = aMatrix[i][i];
        }
        ret = new OGDiagonalMatrix(tmp, rows, cols);
        break;
      case sparse:
        ret = new OGSparseMatrix(aMatrix);
        break;
      default:
    }
    return ret;
  }

  /**
   * @param aVector a double[] vector
   * @param o the orientation the vector should be
   * @return an OGArray type 
   */
  public OGArray<Double> fromNativeArray(double[] aVector, orientation o) {
    OGArray<Double> ret = null;
    switch (o) {
      case row:
        ret = new OGMatrix(aVector, 1, aVector.length);
        break;
      case column:
        ret = new OGMatrix(aVector, aVector.length, 1);
        break;
    }
    return ret;
  }

}
