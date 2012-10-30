/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Does elementwise OGSparse * OGDouble
 */
public final class TimesOGSparseMatrixOGMatrix implements TimesAbstract<OGSparseMatrix, OGMatrix> {
  private static TimesOGSparseMatrixOGMatrix s_instance = new TimesOGSparseMatrixOGMatrix();

  public static TimesOGSparseMatrixOGMatrix getInstance() {
    return s_instance;
  }

  private TimesOGSparseMatrixOGMatrix() {
  }

  
  private static TimesOGMatrixOGSparseMatrix s_reverse = TimesOGMatrixOGSparseMatrix.getInstance();
  
  
  @Override
  public OGArray<? extends Number> times(OGSparseMatrix array1, OGMatrix array2) {
    return s_reverse.times(array2, array1);
  }

}
