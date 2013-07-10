/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.log;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Log;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexSparseMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.ComplexConstants;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.memory.SparseMemoryManipulation;

/**
 * Log() of an OGComplexSparse matrix
 */
@DOGMAMethodHook(provides = Log.class)
public class LogOGComplexSparseMatrix implements Log<OGArray<? extends Number>, OGComplexSparseMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGComplexSparseMatrix array1) {
    double[] data = array1.getData();
    double[] tmp = new double[data.length];
    EasyIZY.vz_ln(data, tmp);
    return SparseMemoryManipulation.createFullComplexSparseMatrixWithNewFillValueInANDNewValuesBasedOnStructureOf(array1, tmp, ComplexConstants.negative_inf_plus_zero_i());

  }
}
