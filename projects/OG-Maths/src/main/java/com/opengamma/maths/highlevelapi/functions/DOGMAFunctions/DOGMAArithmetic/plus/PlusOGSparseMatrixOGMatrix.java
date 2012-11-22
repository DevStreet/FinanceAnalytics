/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Plus;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Adds {@link OGSparseMatrix} to {@link OGMatrix}    
 */
@DOGMAMethodHook(provides = Plus.class)
public final class PlusOGSparseMatrixOGMatrix implements Plus<OGArray<? extends Number>, OGSparseMatrix, OGMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGSparseMatrix array1, OGMatrix array2) {
    PlusOGMatrixOGSparseMatrix reverse = new PlusOGMatrixOGSparseMatrix();
    return reverse.eval(array2, array1);
  }

}
