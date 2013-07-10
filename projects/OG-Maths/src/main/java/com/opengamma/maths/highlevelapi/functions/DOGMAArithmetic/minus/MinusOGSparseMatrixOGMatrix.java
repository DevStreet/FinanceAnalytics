/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.minus;

import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Minus;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;

/**
 * Subtracts an {@link OGSparseMatrix} from an {@link OGMatrix}    
 */
@DOGMAMethodHook(provides = Minus.class)
public final class MinusOGSparseMatrixOGMatrix implements Minus<OGArray<? extends Number>, OGSparseMatrix, OGMatrix> {
  @Override
  public OGArray<? extends Number> eval(OGSparseMatrix array1, OGMatrix array2) {
    MinusOGMatrixOGSparseMatrix reverse = new MinusOGMatrixOGSparseMatrix();
    return DOGMA.uminus(reverse.eval(array2, array1));
  }

}
