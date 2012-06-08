/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIO;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.lowlevelapi.io.smartImport.SnortDataProperties;

/**
 * Imports data from various formats and tries to do something data aware when it comes to array construction
 */
public class SmartImport {

  public OGArraySuper<Number> fromNativeDoubleMatrix(double[][] aMatrix) {
    SnortDataProperties snorter = new SnortDataProperties(aMatrix);

    OGArraySuper<Number> ret = null;

    switch (snorter.getMatrixType()) {
      case upperTriangular:
      case lowerTriangular:
      case dense:
        ret = new OGDoubleArray(aMatrix);
        break;
      case diagonal:
      case identity:
      case sparse:
        ret = new OGSparseArray(aMatrix);
        break;
      default:
    }

    return ret;

  }
}
