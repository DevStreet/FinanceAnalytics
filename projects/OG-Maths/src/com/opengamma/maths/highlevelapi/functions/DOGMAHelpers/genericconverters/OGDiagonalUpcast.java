/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAHelpers.genericconverters;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse.SparseOGDiagonalArray;

/**
 * 
 */
public final class OGDiagonalUpcast implements GenericUpcast<OGDiagonalMatrix, OGSparseMatrix> {
  private static SparseOGDiagonalArray s_sparse = SparseOGDiagonalArray.getInstance();
  private static OGDiagonalUpcast s_instance = new OGDiagonalUpcast();

  public static OGDiagonalUpcast getInstance() {
    return s_instance;
  }

  private OGDiagonalUpcast() {
  }

  @Override
  public OGSparseMatrix from(OGDiagonalMatrix array) {
    OGSparseMatrix ret = s_sparse.sparse(array);
    System.out.println("ret is " + ret.toString());
    return ret;
  }
}
