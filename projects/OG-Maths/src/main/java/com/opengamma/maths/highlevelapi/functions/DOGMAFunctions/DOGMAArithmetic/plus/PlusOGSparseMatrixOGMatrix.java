/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Adds OGDoubleArrays to OGSparseArrays   
 */
public final class PlusOGSparseMatrixOGMatrix implements PlusMinusAbstract<OGSparseMatrix, OGMatrix> {
  private static PlusOGSparseMatrixOGMatrix s_instance = new PlusOGSparseMatrixOGMatrix();

  public static PlusOGSparseMatrixOGMatrix getInstance() {
    return s_instance;
  }

  private PlusOGSparseMatrixOGMatrix() {
  }

  private static PlusOGMatrixOGSparseMatrix s_reverse = PlusOGMatrixOGSparseMatrix.getInstance();
  
  @Override
  public OGArray<? extends Number> plusminus(OGSparseMatrix array1, OGMatrix array2, final int op) {
    return s_reverse.plusminus(array2, array1, op);
  }

}
