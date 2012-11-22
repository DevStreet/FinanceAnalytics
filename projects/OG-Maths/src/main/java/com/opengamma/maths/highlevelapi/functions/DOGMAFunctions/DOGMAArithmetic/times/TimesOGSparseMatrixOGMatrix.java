/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Times;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Does elementwise OGSparse * OGDouble
 */
@DOGMAMethodHook(provides = Times.class)
public final class TimesOGSparseMatrixOGMatrix implements Times<OGArray<? extends Number>, OGSparseMatrix, OGMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGSparseMatrix array1, OGMatrix array2) {
    TimesOGMatrixOGSparseMatrix s_reverse = new TimesOGMatrixOGSparseMatrix();
    return s_reverse.eval(array2, array1);
  }

}
