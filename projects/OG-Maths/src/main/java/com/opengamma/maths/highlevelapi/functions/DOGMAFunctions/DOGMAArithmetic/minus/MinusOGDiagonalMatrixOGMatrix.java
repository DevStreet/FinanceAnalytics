/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.minus;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.Minus;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Subtracts an {@link OGDiagonalMatrix} from an {@link OGMatrix} 
 */
@DOGMAMethodHook(provides = Minus.class)
public final class MinusOGDiagonalMatrixOGMatrix implements Minus<OGArray<? extends Number>, OGDiagonalMatrix, OGMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGDiagonalMatrix array1, OGMatrix array2) {
    MinusOGMatrixOGDiagonalMatrix reverse = new MinusOGMatrixOGDiagonalMatrix();
    return reverse.eval(array2, array1);
  }

}
