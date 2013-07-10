/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAInquiry.issquare;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.IsSquare;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;

/**
 * Test if a matrix is square
 */
@DOGMAMethodHook(provides = IsSquare.class)
public class IsSquareFunction {

  @DOGMAMethodLiteral
  public OGMatrix issquare(OGArray<? extends Number> array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    if (rows == cols) {
      return new OGMatrix(1);
    } else {
      return new OGMatrix(0);
    }
  }

}
