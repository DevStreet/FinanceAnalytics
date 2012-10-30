/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.derived;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotRequested;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd;

/**
 * Holds the result of an SVD computation
 */
public class OGSvdResult {

  private OGArray<? extends Number> _U; //CSIGNORE
  private OGArray<? extends Number> _S; //CSIGNORE
  private OGArray<? extends Number> _V; //CSIGNORE

  /**
   * Constructs an SVD result, data is not copied just repointed as results are assumed to come from somewhere sane like {@link Svd}
   * @param U
   * @param S
   * @param V
   */
  public OGSvdResult(OGArray<? extends Number> U, OGArray<? extends Number> S, OGArray<? extends Number> V) { //CSIGNORE
    _U = U;
    _S = S;
    _V = V;
  }

  /**
   * Gets the matrix U.
   * @return the matrix U.
   */
  public OGArray<? extends Number> getU() {
    if (_U == null) {
      throw new MathsExceptionNotRequested("The matrix U was not requested to be computed at execution time.");
    }
    return _U;
  }

  /**
   * Gets the matrix S.
   * @return the matrix S
   */
  public OGArray<? extends Number> getS() {
    if (_S == null) {
      throw new MathsExceptionNotRequested("The matrix S was not requested to be computed at execution time.");
    }
    return _S;
  }

  /**
   * Gets the matrix V.
   * @return the matrix V
   */
  public OGArray<? extends Number> getV() {
    if (_V == null) {
      throw new MathsExceptionNotRequested("The matrix V was not requested to be computed at execution time.");
    }    
    return _V;
  }

}
