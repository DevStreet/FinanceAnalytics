/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.derived;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotRequested;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationArray;

/**
 * Holds an LUP result 
 */
public class OGLuResult {
  private OGArraySuper<? extends Number> _L; //CSIGNORE
  private OGArraySuper<? extends Number> _U; //CSIGNORE
  private OGPermutationArray _P; //CSIGNORE

  /**
   * Constructs an LU result, data is not copied just repointed as results are assumed to come from somewhere sane like {@link Lu}
   * @param L
   * @param U
   * @param P
   */
  public OGLuResult(OGArraySuper<? extends Number> L, OGArraySuper<? extends Number> U, OGPermutationArray P) { //CSIGNORE
    _L = L;
    _U = U;
    _P = P;
  }

  /**
   * Gets the matrix L.
   * @return the matrix L
   */
  public OGArraySuper<? extends Number> getL() {
    if (_L == null) {
      throw new MathsExceptionNotRequested("The matrix L was not requested to be computed at execution time.");
    }
    return _L;
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
   * Gets the matrix P.
   * @return the matrix P
   */
  public OGPermutationArray getP() {
    if (_P == null) {
      throw new MathsExceptionNotRequested("The matrix P was not requested to be computed at execution time.");
    }    
    return _P;
  }  
}
