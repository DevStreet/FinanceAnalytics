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
import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;

/**
 * Subtracts an {@link OGDiagonalMatrix} from an {@link OGMatrix} 
 */
@DOGMAMethodHook(provides = Minus.class)
public final class MinusOGDiagonalMatrixOGMatrix implements Minus<OGArray<? extends Number>, OGDiagonalMatrix, OGMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGDiagonalMatrix array1, OGMatrix array2) {
    MinusOGMatrixOGDiagonalMatrix reverse = new MinusOGMatrixOGDiagonalMatrix();
    return DOGMA.uminus(reverse.eval(array2, array1));
  }

}
