/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.derived;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd;

/**
 * Holds the result of an SVD computation
 */
public class OGSvdResult {

  private OGDoubleArray _U; //CSIGNORE
  private OGDoubleArray _S; //CSIGNORE
  private OGDoubleArray _VT; //CSIGNORE

/**
 * Constructs an SVD result, data is not copied just repointed as results are assumed to come from somewhere sane like {@link Svd}
 * @param U
 * @param S
 * @param VT
 */
  public OGSvdResult(OGDoubleArray U, OGDoubleArray S, OGDoubleArray VT) { //CSIGNORE
    _U = U;
    _S = S;
    _VT = VT;
  }

  /**
   * Gets the matrix U.
   * @return the matrix U.
   */
  public OGDoubleArray getU() {
    return _U;
  }

  /**
   * Gets the matrix S.
   * @return the matrix S
   */
  public OGDoubleArray getS() {
    return _S;
  }

  /**
   * Gets the matrix VT.
   * @return the matrix VT
   */
  public OGDoubleArray getVT() {
    return _VT;
  }

}
