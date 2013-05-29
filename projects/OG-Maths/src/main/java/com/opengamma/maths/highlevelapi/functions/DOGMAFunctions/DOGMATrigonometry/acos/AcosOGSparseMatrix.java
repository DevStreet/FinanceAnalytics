/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.acos;

import com.opengamma.maths.commonapi.MathsConstants;
import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Acos;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.ComplexConstants;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;
import com.opengamma.maths.lowlevelapi.functions.memory.SparseMemoryManipulation;

/**
 * Acos() on OGSparse
 */
@DOGMAMethodHook(provides = Acos.class)
public class AcosOGSparseMatrix implements Acos<OGArray<? extends Number>, OGSparseMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGSparseMatrix array1) {
    double[] data = array1.getData();
    int n = data.length;
    double[] tmp;
    // check bounds
    boolean complex = false;
    for (int i = 0; i < n; i++) {
      if (Math.abs(data[i]) > 1) {
        complex = true;
        break;
      }
    }
    OGArray<? extends Number> retarr;
    if (complex) {
      tmp = DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(data);
      EasyIZY.vz_acos(tmp, tmp);
      retarr = SparseMemoryManipulation.createFullComplexSparseMatrixWithNewFillValueInANDNewValuesBasedOnStructureOf(array1, tmp, ComplexConstants.half_pi_plus_zero_i());
    } else {
      tmp = new double[n];
      EasyIZY.vd_acos(data, tmp);
      retarr = SparseMemoryManipulation.createFullSparseMatrixWithNewFillValueInANDNewValuesBasedOnStructureOf(array1, tmp, MathsConstants.halfpi);
    }
    return retarr;
  }
}
