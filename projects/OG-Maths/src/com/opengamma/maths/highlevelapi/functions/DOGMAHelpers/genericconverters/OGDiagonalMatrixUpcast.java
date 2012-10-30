/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAHelpers.genericconverters;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse.SparseOGDiagonalMatrix;

/**
 * 
 */
public final class OGDiagonalMatrixUpcast implements GenericUpcast<OGDiagonalMatrix, OGSparseMatrix> {
  private static SparseOGDiagonalMatrix s_sparse = SparseOGDiagonalMatrix.getInstance();
  private static OGDiagonalMatrixUpcast s_instance = new OGDiagonalMatrixUpcast();

  public static OGDiagonalMatrixUpcast getInstance() {
    return s_instance;
  }

  private OGDiagonalMatrixUpcast() {
  }

  @Override
  public OGSparseMatrix from(OGDiagonalMatrix array) {
    OGSparseMatrix ret = s_sparse.sparse(array);
    System.out.println("ret is " + ret.toString());
    return ret;
  }
}
