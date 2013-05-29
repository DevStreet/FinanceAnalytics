/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.dogma;

import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIO.SmartImport;

/**
 * Imports data from various formats and tries to do something data aware when it comes to array construction
 */
public class DOGMAAPISmartImport extends SmartImport {

  /** 
   * Imports data from OG-Analytics DoubleMatrix2D type.
   * @param aMatrix the matrix to import 
   * @return an OGArray backing suited to the input data
   */
  OGArray<? extends Number> fromExternalLib(DoubleMatrix2D aMatrix) {
    return fromNativeArrayOfArrays(aMatrix.getData());
  }

  /** 
   * Imports data from Colt DoubleMatrix2D type.
   * @param aMatrix the matrix to import 
   * @return an OGArray backing suited to the input data
   */
  OGArray<? extends Number> fromExternalLib(cern.colt.matrix.DoubleMatrix2D aMatrix) {
    return fromNativeArrayOfArrays(aMatrix.toArray());
  }

  /** 
   * Imports data from Colt DoubleMatrix1D type.
   * @param aMatrix the matrix to import 
   * @return an OGArray backing suited to the input data
   */
  OGArray<? extends Number> fromExternalLib(cern.colt.matrix.DoubleMatrix1D aMatrix) {
    double[] tmp = aMatrix.toArray();
    double[][] arrtmp = new double[1][];
    arrtmp[0] = tmp;
    return fromNativeArrayOfArrays(arrtmp);
  }

  /** 
   * Imports data from Commons RealMatrix type.
   * @param aMatrix the matrix to import 
   * @return an OGArray backing suited to the input data
   */
  OGArray<? extends Number> fromExternalLib(org.apache.commons.math.linear.RealMatrix aMatrix) {
    return fromNativeArrayOfArrays(aMatrix.getData());
  }

}
