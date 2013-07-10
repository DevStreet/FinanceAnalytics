/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.mtimes;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Mtimes;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;
import com.opengamma.maths.lowlevelapi.exposedapi.ComplexConstants;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does matrix * matrix in a mathematical sense
 */
@DOGMAMethodHook(provides = Mtimes.class)
public final class MtimesOGComplexMatrixOGComplexMatrix implements Mtimes<OGComplexMatrix, OGComplexMatrix, OGComplexMatrix> {
  private BLAS _localblas = BLAS.getInstance();

  @Override
  public OGComplexMatrix eval(OGComplexMatrix array1, OGComplexMatrix array2) {
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
    OGComplexMatrix ret = null;
    final double[] alpha = ComplexConstants.one();
    final double[] beta = ComplexConstants.zero();
    
    if (colsArray1 == 1 && rowsArray1 == 1) { // We have scalar * matrix
      n = data2.length;
      tmp = new double[n];
      EasyIZY.vz_mulx(data2, data1, tmp);
      ret = new OGComplexMatrix(tmp, rowsArray2, colsArray2);
    } else if (colsArray2 == 1 && rowsArray2 == 1) { // We have matrix * scalar
      n = data1.length;
      tmp = new double[n];
      EasyIZY.vz_mulx(data1, data2, tmp);
      ret = new OGComplexMatrix(tmp, rowsArray1, colsArray1);
    } else {
      Catchers.catchBadCommute(colsArray1, "Columns in first array", rowsArray2, "Rows in second array");
      if (colsArray2 == 1) { // A*x
        tmp = new double[2 * rowsArray1];
        // dgemv(trans, m, n, alpha, aMatrix, lda, x, incx, beta, y, incy);
        _localblas.zgemv('N', rowsArray1, colsArray1, alpha, data1, rowsArray1, data2, 1, beta, tmp, 1);
        ret = new OGComplexMatrix(tmp, rowsArray1, 1);
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
        final int lda = fm;
        final int ldb = fk;
        double[] cMatrix = new double[2 * fm * fn];
        final int ldc = fm;
        _localblas.zgemm('N', 'N', fm, fn, fk, alpha, data1, lda, data2, ldb, beta, cMatrix, ldc);
        ret = new OGComplexMatrix(cMatrix, fm, fn);
      }
    }
    return ret;
  }
}
