/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAMeshing.meshgrid;

import java.util.ArrayList;
import java.util.List;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Meshgrid;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does meshgrid
 */
@DOGMAMethodHook(provides = Meshgrid.class)
public class MeshgridFunction {

  @DOGMAMethodLiteral
  public List<OGArray<? extends Number>> meshgrid(OGArray<? extends Number> array1) {
    Catchers.catchNull(array1);
    int rows = array1.getNumberOfRows();
    int cols = array1.getNumberOfColumns();

    if (rows != 1 || cols != 1) {
      throw new MathsExceptionIllegalArgument("The input matrix must have at least one dimension of value 1 (i.e. it's a vector)");
    }
    double[] data = array1.getData();
    // spread the data around, row replication to length(array1)
    final int n = Math.max(rows, cols);
    double[] tmp = new double[n * n];
    double val;
    int offset;
    for (int i = 0; i < n; i++) {
      val = data[i];
      // Arrays.fill would be good here but doesn't take an offset
      offset = i * n;
      for (int j = 0; j < n; j++) {
        tmp[offset + j] = val;
      }
    }
    ArrayList<OGArray<? extends Number>> ret = new ArrayList<OGArray<? extends Number>>(1);
    ret.add(new OGMatrix(tmp, n, n));
    return ret;
  }

  @DOGMAMethodLiteral
  public List<OGArray<? extends Number>> meshgrid(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    Catchers.catchNull(array1);
    Catchers.catchNull(array2);
    int rowsArr1 = array1.getNumberOfRows();
    int colsArr1 = array1.getNumberOfColumns();
    int rowsArr2 = array2.getNumberOfRows();
    int colsArr2 = array2.getNumberOfColumns();
    if (!(rowsArr1 == 1 || colsArr1 == 1)) {
      System.out.println(rowsArr1 + " " + colsArr1);
      System.out.println(rowsArr2 + " " + colsArr2);
      throw new MathsExceptionIllegalArgument("The input matrix array1 must have at least one dimension of value 1 (i.e. it's a vector)");
    }
    if (!(rowsArr2 == 1 || colsArr2 == 1)) {
      throw new MathsExceptionIllegalArgument("The input matrix array2 must have at least one dimension of value 1 (i.e. it's a vector)");
    }

    double[] data1 = array1.getData();
    double[] data2 = array2.getData();

    // spread the data around, row replication to length(array1)
    final int n = Math.max(rowsArr1, colsArr1);
    final int m = Math.max(rowsArr2, colsArr2);

    double[] tmp1 = new double[m * n];
    double val;
    int offset;
    for (int i = 0; i < n; i++) {
      val = data1[i];
      // Arrays.fill would be good here but doesn't take an offset
      offset = i * m;
      for (int j = 0; j < m; j++) {
        tmp1[offset + j] = val;
      }
    }

    double[] tmp2 = new double[m * n];
    for (int i = 0; i < n; i++) {
      System.arraycopy(data2, 0, tmp2, i * m, m);
    }

    ArrayList<OGArray<? extends Number>> ret = new ArrayList<OGArray<? extends Number>>(2);
    ret.add(new OGMatrix(tmp1, m, n));
    ret.add(new OGMatrix(tmp2, m, n));
    return ret;
  }
}
