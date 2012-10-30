/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Adds {@link OGDiagonalMatrix} to {@link OGMatrix} 
 */
public final class PlusOGDiagonalMatrixOGMatrix implements PlusMinusAbstract<OGDiagonalMatrix, OGMatrix> {
  private static PlusOGDiagonalMatrixOGMatrix s_instance = new PlusOGDiagonalMatrixOGMatrix();

  public static PlusOGDiagonalMatrixOGMatrix getInstance() {
    return s_instance;
  }

  private PlusOGDiagonalMatrixOGMatrix() {
  }

  private static PlusOGMatrixOGDiagonalMatrix s_reverse = PlusOGMatrixOGDiagonalMatrix.getInstance();

  @Override
  public OGArray<? extends Number> plusminus(OGDiagonalMatrix array1, OGMatrix array2, int op) {
    return s_reverse.plusminus(array2, array1, op);
  }

}
