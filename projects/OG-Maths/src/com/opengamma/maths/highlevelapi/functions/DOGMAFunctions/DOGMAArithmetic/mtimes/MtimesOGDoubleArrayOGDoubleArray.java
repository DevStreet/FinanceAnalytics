/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does matrix * matrix in a mathematical sense
 */
public final class MtimesOGDoubleArrayOGDoubleArray extends MtimesAbstract<OGDoubleArray, OGDoubleArray> {
  private static MtimesOGDoubleArrayOGDoubleArray s_instance = new MtimesOGDoubleArrayOGDoubleArray();

  public static MtimesOGDoubleArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private MtimesOGDoubleArrayOGDoubleArray() {
  }

  private BLAS _localblas = new BLAS();

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> mtimes(OGDoubleArray array1, OGDoubleArray array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);

    final int colsArray1 = array1.getNumberOfColumns();
    final int colsArray2 = array2.getNumberOfColumns();
    final int rowsArray1 = array1.getNumberOfRows();
    final int rowsArray2 = array2.getNumberOfRows();
    final double[] data1 = array1.getData();
    final double[] data2 = array2.getData();

    double[] tmp = null;
    int n = 0;
    OGArraySuper<Number> ret = null;

    if (colsArray1 == 1 && rowsArray1 == 1) { // We have scalar * matrix
      final double deref = data1[0];
      n = data2.length;
      tmp = new double[n];
      System.arraycopy(data2, 0, tmp, 0, n);
      _localblas.dscal(n, deref, tmp, 1);
      ret = new OGDoubleArray(tmp, rowsArray2, colsArray2);
    } else if (colsArray2 == 1 && rowsArray2 == 1) { // We have matrix * scalar
      final double deref = data2[0];
      n = data1.length;
      tmp = new double[n];
      System.arraycopy(data1, 0, tmp, 0, n);
      _localblas.dscal(n, deref, tmp, 1);
      ret = new OGDoubleArray(tmp, rowsArray1, colsArray1);
    } else {
      Catchers.catchBadCommute(colsArray1, "Columns in first array", rowsArray2, "Rows in second array");
      if (colsArray2 == 1) { // A*x
        tmp = new double[rowsArray1];
        // dgemv(trans, m, n, alpha, aMatrix, lda, x, incx, beta, y, incy);
        _localblas.dgemv('N', rowsArray1, colsArray1, 1.0, data1, rowsArray1, data2, 1, 0.e0, tmp, 1);
        ret = new OGDoubleArray(tmp, rowsArray1, 1);
      } else {
        //      * @param transa one of 'N' or 'n', 'T' or 't', 'C' or 'c'. See above.
        //      * @param transb one of 'N' or 'n', 'T' or 't', 'C' or 'c'. See above.
        //      * @param m number of rows in matrix {@code _OP_(aMatrix)} and number of rows in {@code cMatrix}
        //      * @param n number of columns in matrix {@code _OP_(bMatrix) and number of columns in {@code cMatrix}
        //      * @param k number of columns in matrix {@code _OP_(aMatrix)} and number of rows in matrix {@code _OP_(bMatrix)}
        //      * @param alpha scaling factor for the matrix-matrix product 
        //      * @param aMatrix the leading part of the "A" matrix of at least dimension (LDA, ka), where ka is {@code k} when {@code transa} is 'N' or 'n' and is {@code m} otherwise.
        //      * @param lda the first dimension of {@code aMatrix}, if {@code transa} is 'N' or 'n' it is max(1,m) else it is at least max(1,k)
        //      * @param bMatrix the leading part of the "B" matrix of at least dimension (LDB, kb), where kb is {@code n} when {@code transb} is 'N' or 'n' and is {@code k} otherwise.
        //      * @param ldb the first dimension of {@code bMatrix}, if {@code transb} is 'N' or 'n' it is max(1,k) else it is at least max(1,n)
        //      * @param beta the scaling factor for the matrix "C", {@code cMatrix}
        //      * @param cMatrix the leading part of the "C" matrix of at least dimension (LDC, n). Overwritten by the operation defined in the preamble on exit. 
        //      * @param ldc the first dimension of "C" at least max(1,m)
        final int fm = rowsArray1;
        final int fn = colsArray2;
        final int fk = colsArray1;
        final double alpha = 1.e0;
        final int lda = fm;
        final int ldb = fk;
        final double beta = 0.e0;
        double[] cMatrix = new double[fm * fn];
        final int ldc = fm;
        _localblas.dgemm('N', 'N', fm, fn, fk, alpha, data1, lda, data2, ldb, beta, cMatrix, ldc);
        ret = new OGDoubleArray(cMatrix, fm, fn);
      }
    }
    return ret;
  }
}
