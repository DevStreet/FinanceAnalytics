/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIO;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMAIO.orientation;
import com.opengamma.maths.lowlevelapi.io.smartImport.SnortDataProperties;

/**
 * Imports data from various formats and tries to do something data aware when it comes to array construction
 */
public class SmartImport {

  public OGArray<Double> fromNativeDoubleDouble(double[][] aMatrix) {
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
      case sparse:
        ret = new OGSparseMatrix(aMatrix);
        break;
      default:
    }
    return ret;
  }

  /**
   * @param aVector a vector
   * @param o the orientation the vector should be
   * @return an OGArraySuper type 
   */
  public OGArray<Double> fromNativeDouble(double[] aVector, orientation o) {
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
