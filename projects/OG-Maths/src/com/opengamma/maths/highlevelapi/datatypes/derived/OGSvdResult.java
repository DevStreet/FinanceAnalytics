/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.derived;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotRequested;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd;

/**
 * Holds the result of an SVD computation
 */
public class OGSvdResult {

  private OGArraySuper<? extends Number> _U; //CSIGNORE
  private OGArraySuper<? extends Number> _S; //CSIGNORE
  private OGArraySuper<? extends Number> _V; //CSIGNORE

  /**
   * Constructs an SVD result, data is not copied just repointed as results are assumed to come from somewhere sane like {@link Svd}
   * @param U
   * @param S
   * @param V
   */
  public OGSvdResult(OGArraySuper<? extends Number> U, OGArraySuper<? extends Number> S, OGArraySuper<? extends Number> V) { //CSIGNORE
    _U = U;
    _S = S;
    _V = V;
  }

  /**
   * Gets the matrix U.
   * @return the matrix U.
   */
  public OGArraySuper<? extends Number> getU() {
    if (_U == null) {
      throw new MathsExceptionNotRequested("The matrix U was not requested to be computed at execution time.");
    }
    return _U;
  }

  /**
   * Gets the matrix S.
   * @return the matrix S
   */
  public OGArraySuper<? extends Number> getS() {
    if (_S == null) {
      throw new MathsExceptionNotRequested("The matrix S was not requested to be computed at execution time.");
    }
    return _S;
  }

  /**
   * Gets the matrix V.
   * @return the matrix V
   */
  public OGArraySuper<? extends Number> getV() {
    if (_V == null) {
      throw new MathsExceptionNotRequested("The matrix V was not requested to be computed at execution time.");
    }    
    return _V;
  }

}
