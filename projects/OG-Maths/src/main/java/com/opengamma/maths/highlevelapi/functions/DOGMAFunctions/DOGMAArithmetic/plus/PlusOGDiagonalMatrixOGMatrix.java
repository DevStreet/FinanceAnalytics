/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Plus;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Adds {@link OGDiagonalMatrix} to {@link OGMatrix} 
 */
@DOGMAMethodHook(provides = Plus.class)
public final class PlusOGDiagonalMatrixOGMatrix implements Plus<OGArray<? extends Number>, OGDiagonalMatrix, OGMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGDiagonalMatrix array1, OGMatrix array2) {
    PlusOGMatrixOGDiagonalMatrix reverse = new PlusOGMatrixOGDiagonalMatrix();
    return reverse.eval(array2, array1);
  }

}
