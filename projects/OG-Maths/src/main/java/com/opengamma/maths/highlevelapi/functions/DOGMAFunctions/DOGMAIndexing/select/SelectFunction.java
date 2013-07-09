/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIndexing.select;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Select;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * For generating vector data
 */
@DOGMAMethodHook(provides = Select.class)
public class SelectFunction {

  @DOGMAMethodLiteral
  public static OGArray<? extends Number> select(OGArray<? extends Number> matrix, String index) {
    Catchers.catchNullFromArgList(matrix, 1);
    return matrix.get(index);
  }
}
