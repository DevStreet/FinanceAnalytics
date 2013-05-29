/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIndexing.assign;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Assign;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIndexing.colon.ColonFunction;
import com.opengamma.maths.lowlevelapi.functions.MathE;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Assign some data in an OGMatrix from another OGMatrix
 */
@DOGMAMethodHook(provides = Assign.class)
public class AssignOGMatrixOGMatrix implements AssignInterface<OGMatrix, OGMatrix, OGMatrix, OGMatrix> {

  @Override
  @DOGMAMethodLiteral
  public OGArray<? extends Number> assign(OGMatrix arr1, int[] rows, int[] columns, OGMatrix arr2) {
    Catchers.catchNullFromArgList(arr1, 1);
    Catchers.catchNullFromArgList(rows, 2);
    Catchers.catchNullFromArgList(columns, 3);
    Catchers.catchNullFromArgList(arr2, 4);

    // check arr2 is same size as rows[] and columns[]
    // check rows[] and columns[] are withn the bounds of arr1

    final int nrowsSubmatrix = rows.length;
    final int ncolsSubmatrix = columns.length;
    final int nrowsArr1 = arr1.getNumberOfRows();
    final int nrowsArr2 = arr2.getNumberOfRows();
    final int ncolsArr1 = arr1.getNumberOfColumns();
    final int ncolsArr2 = arr2.getNumberOfColumns();
    Catchers.catchBadCommute(nrowsArr2, "rows in arr2", nrowsSubmatrix, "selected rows");
    Catchers.catchBadCommute(ncolsArr2, "columns in arr2", ncolsSubmatrix, "selected columns");

    int index;
    for (int i = 0; i < nrowsSubmatrix; i++) {
      index = rows[i];
      if (index < 0 || index >= nrowsArr1) {
        throw new MathsExceptionIllegalArgument("Invalid row assignment index. Value given was " + index);
      }
    }

    for (int i = 0; i < ncolsSubmatrix; i++) {
      index = columns[i];
      if (index < 0 || index >= ncolsArr1) {
        throw new MathsExceptionIllegalArgument("Invalid column assignment index. Value given was " + index);
      }
    }

    // stateless update, involves copy, ewww
    double[] origData = arr1.getData();
    double[] assignData = arr2.getData();
    double[] tmpData = new double[origData.length];
    System.arraycopy(origData, 0, tmpData, 0, origData.length);
    int idxi, idxj;
    // apply update to memcpy
    for (int i = 0; i < ncolsSubmatrix; i++) {
      idxi = columns[i];
      for (int j = 0; j < nrowsSubmatrix; j++) {
        idxj = rows[j];
        tmpData[idxi * nrowsArr1 + idxj] = assignData[i * nrowsArr2 + j];
      }
    }
    return new OGMatrix(tmpData, nrowsArr1, ncolsArr1);
  }

  @Override
  @DOGMAMethodLiteral
  public OGArray<? extends Number> assign(OGMatrix arr1, OGMatrix rows, OGMatrix columns, OGMatrix arr2) {
    Catchers.catchNullFromArgList(arr1, 1);
    Catchers.catchNullFromArgList(rows, 2);
    Catchers.catchNullFromArgList(columns, 3);
    Catchers.catchNullFromArgList(arr2, 4);
    double[] dataRows = rows.getData();
    double[] dataCols = columns.getData();
    final int nrows = dataRows.length;
    final int ncols = dataCols.length;
    int[] irows = new int[nrows];
    for (int i = 0; i < nrows; i++) {
      if (dataRows[i] >= 0 && dataRows[i] == MathE.trunc(dataRows[i])) {
        irows[i] = (int) dataRows[i];
      } else {
        throw new MathsExceptionIllegalArgument("Indices must be positive integers (zero included), violation is: " + dataRows[i]);
      }
    }
    int[] icols = new int[ncols];
    for (int i = 0; i < ncols; i++) {
      if (dataCols[i] >= 0 && dataCols[i] == MathE.trunc(dataCols[i])) {
        icols[i] = (int) dataCols[i];
      } else {
        throw new MathsExceptionIllegalArgument("Indices must be positive integers (zero included), violation is: " + dataCols[i]);
      }
    }
    return assign(arr1, irows, icols, arr2);
  }

  @Override
  @DOGMAMethodLiteral
  public OGArray<? extends Number> assign(OGMatrix arr1, Index idx, OGMatrix columns, OGMatrix arr2) {
    return assign(arr1, ColonFunction.colon(0, arr1.getNumberOfRows()), columns, arr2);
  }

  @Override
  @DOGMAMethodLiteral
  public OGArray<? extends Number> assign(OGMatrix arr1, OGMatrix rows, Index idx, OGMatrix arr2) {
    return assign(arr1, rows, ColonFunction.colon(0, arr1.getNumberOfColumns()), arr2);
  }
}
