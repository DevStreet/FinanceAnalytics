/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMASpecialFunctions.normcdf;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.NormCDF;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMASparseUtilities.full.FullOGSparseMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.SparseMemoryManipulation;

/**
 * Does NormCDF on OGSparseMatrix
 */
@DOGMAMethodHook(provides = NormCDF.class)
public final class NormCDFOGSparseMatrix implements NormCDF<OGMatrix, OGSparseMatrix> {

  private FullOGSparseMatrix _f = new FullOGSparseMatrix();

  @Override
  public OGMatrix eval(OGSparseMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final double[] dataArray1 = array1.getData();
    final int n = dataArray1.length;
    double[] tmp = new double[n];
    EasyIZY.vd_cdfnorm(dataArray1, tmp);
    return _f.eval((SparseMemoryManipulation.createFullSparseMatrixWithNewFillValueInANDNewValuesBasedOnStructureOf(array1, tmp, 0.5d)));
  }
}
