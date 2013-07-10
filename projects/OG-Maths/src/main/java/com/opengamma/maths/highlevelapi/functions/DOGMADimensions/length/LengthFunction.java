/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMADimensions.length;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Length;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does length()
 */
@DOGMAMethodHook(provides = Length.class)
public final class LengthFunction implements Length {

  /**
   * Returns the "length" of a matrix, this is the length of the largest dimension
   * @param array1 the array from which the length shall be computed
   * @return the length of array1
   */
  @DOGMAMethodLiteral
  public int length(OGArray<? extends Number> array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();

    return Math.max(rowsArray1, columnsArray1);
  }

  /**
   * Returns the "length" of a matrix, this is the length of the largest dimension
   * @param array1 the array from which the length shall be computed
   * @return the length of array1
   */
  @DOGMAMethodLiteral
  public int length(Number array1) {
    Catchers.catchNullFromArgList(array1, 1);
    return 1;
  }
}
