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
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIndexing.assign.Index;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Allows selection of indices from matrices
 */
@DOGMAMethodHook(provides = Select.class)
public class SelectOGMatrix {

  @DOGMAMethodLiteral
  public OGArray<? extends Number> select(OGArray<? extends Number> array1, int[] rows, int[] columns) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(rows, 2);
    Catchers.catchNullFromArgList(columns, 3);
    return array1.get(rows, columns);
  }

  @DOGMAMethodLiteral
  public OGArray<? extends Number> select(OGArray<? extends Number> array1, Index idx, int[] columns) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(idx, 2);
    Catchers.catchNullFromArgList(columns, 3);
    int nrows = array1.getNumberOfRows();
    int[] rows = new int[nrows];
    for (int i = 0; i < nrows; i++) {
      rows[i] = i;
    }
    return array1.get(rows, columns);
  }

  @DOGMAMethodLiteral
  public OGArray<? extends Number> select(OGArray<? extends Number> array1, int[] rows, Index idx) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(rows, 2);
    Catchers.catchNullFromArgList(idx, 3);
    int ncols = array1.getNumberOfColumns();
    int[] columns = new int[ncols];
    for (int i = 0; i < ncols; i++) {
      columns[i] = i;
    }
    return array1.get(rows, columns);
  }
}
