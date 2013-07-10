/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAExponentsAndLogarithms.exp;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Exp;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexSparseMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.ComplexConstants;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.SparseMemoryManipulation;

/**
 * does exp
 */
@DOGMAMethodHook(provides = Exp.class)
public final class ExpOGComplexSparseMatrix implements Exp<OGComplexSparseMatrix, OGComplexSparseMatrix> {

  @Override
  public OGComplexSparseMatrix eval(OGComplexSparseMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final double[] dataArray1 = array1.getData();
    final int n = dataArray1.length;

    double[] tmp = new double[n];
    EasyIZY.vz_exp(dataArray1, tmp);
    return SparseMemoryManipulation.createFullComplexSparseMatrixWithNewFillValueInANDNewValuesBasedOnStructureOf(array1, tmp, ComplexConstants.one());
  }
}
