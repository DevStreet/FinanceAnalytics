/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.interfaces.individialfunctions;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;

/**
 * Overloaded Plus
 */
public class PlusAndMinus {
  private BLAS _localblas = new BLAS();

  public OGDoubleArray plus(OGDoubleArray... array) {
    int n = array[0].getData().length;
    int rows = array[0].getNumberOfRows();
    int columns = array[0].getNumberOfColumns();
    double[] tmp = new double[n];
    for (int i = 0; i < array.length; i++) {
      _localblas.daxpy(n, 1e0, array[i].getData(), 1, tmp, 1);
    }
    return new OGDoubleArray(tmp, rows, columns);
  }

  public OGDoubleArray plus(OGDoubleArray array1, OGDoubleArray array2) {
    int n = array1.getData().length;
    int rows = array1.getNumberOfRows();
    int columns = array1.getNumberOfColumns();
    double[] tmp = new double[n];
    System.arraycopy(array1.getData(), 0, tmp, 0, n);
    _localblas.daxpy(n, 1e0, tmp, 1, array2.getData(), 1);
    return new OGDoubleArray(tmp, rows, columns);
  }

  public OGDoubleArray plus(OGDoubleArray array1, OGSparseArray array2) {
    throw new MathsExceptionNotImplemented("Function not implemented");
  }

  public OGDoubleArray plus(OGSparseArray array1, OGDoubleArray array2) {
    throw new MathsExceptionNotImplemented("Function not implemented");
  }

  public OGSparseArray plus(OGSparseArray array1, OGSparseArray array2) {
    throw new MathsExceptionNotImplemented("Function not implemented");
  }

  public OGDoubleArray minus(OGDoubleArray... array) {
    int n = array[0].getData().length;
    int rows = array[0].getNumberOfRows();
    int columns = array[0].getNumberOfColumns();
    double[] tmp = new double[n];
    for (int i = 0; i < array.length; i++) {
      _localblas.daxpy(n, -1e0, tmp, 1, array[i].getData(), 1);
    }
    return new OGDoubleArray(tmp, rows, columns);
  }

  public OGDoubleArray minus(OGDoubleArray array1, OGDoubleArray array2) {
    int n = array1.getData().length;
    int rows = array1.getNumberOfRows();
    int columns = array1.getNumberOfColumns();
    double[] tmp = new double[n];
    System.arraycopy(array1.getData(), 0, tmp, 0, n);
    _localblas.daxpy(n, -1e0, tmp, 1, array2.getData(), 1);
    return new OGDoubleArray(tmp, rows, columns);
  }

  public OGDoubleArray minus(OGDoubleArray array1, OGSparseArray array2) {
    throw new MathsExceptionNotImplemented("Function not implemented");
  }

  public OGDoubleArray minus(OGSparseArray array1, OGDoubleArray array2) {
    throw new MathsExceptionNotImplemented("Function not implemented");
  }

  public OGSparseArray minus(OGSparseArray array1, OGSparseArray array2) {
    throw new MathsExceptionNotImplemented("Function not implemented");
  }  
}
