/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMADimensions.length;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Size;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does size()
 */
@DOGMAMethodHook(provides = Size.class)
public final class SizeFunction implements Size {

  /**
   * Returns the "size" of a matrix, this is the dimension of the matrix
   * @param array1 the array from which the size shall be derived
   * @return the size of array1
   */
  @DOGMAMethodLiteral
  public OGArray<? extends Number> size(OGArray<? extends Number> array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();

    return new OGMatrix(new double[] {rowsArray1, columnsArray1 });
  }

  /**
   * Returns the "size" of a matrix, this is the dimension of the matrix
   * @param array1 the array from which the size shall be derived
   * @return the size of array1
   */
  @DOGMAMethodLiteral
  public OGArray<? extends Number> size(Number array1) {
    Catchers.catchNullFromArgList(array1, 1);
    return new OGMatrix(new double[] {1, 1});
  }
}
