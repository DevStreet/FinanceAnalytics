/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMASpecialFunctions.normcdf;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.NormCDF;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does NormCDF on OGMatrix
 */
@DOGMAMethodHook(provides = NormCDF.class)
public final class NormCDFOGMatrix implements NormCDF<OGMatrix, OGMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int n = dataArray1.length;
    double[] tmp = new double[n];
    EasyIZY.vd_cdfnorm(dataArray1, tmp);
    return new OGMatrix(tmp, rowsArray1, columnsArray1);
  }
}
