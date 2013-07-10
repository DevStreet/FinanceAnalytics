/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.reshape;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Reshape;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Reshape an array
 */
@DOGMAMethodHook(provides = Reshape.class)
public final class ReshapeOGMatrix implements Reshape<OGMatrix> {

  @DOGMAMethodLiteral
  public OGMatrix reshape(OGMatrix array1, int newRows, int newCols) {
    Catchers.catchNullFromArgList(array1, 1);
    int rows = array1.getNumberOfRows();
    int cols = array1.getNumberOfColumns();

    if (array1.getNumberOfElements() != newRows * newCols) {
      throw new MathsExceptionIllegalArgument("Cannot reshape array, number of elements are not conformant.\n  thisArray is " + rows + "x" + cols + ". Requested reshape is: " + newRows + " x " +
          newCols);
    }
    double[] data = array1.getData();
    return new OGMatrix(data, newRows, newCols);
  }

}
