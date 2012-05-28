/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.individualfunctions.concrete.mtimes;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;

/**
 * 
 */
public class MtimesOGDoubleArrayOGDoubleArray {

  public OGDoubleArray times(OGDoubleArray... array) {
    int n = array[0].getData().length;
    double[] tmp = new double[n];
    int cols = array[0].getNumberOfColumns();
    int rows = array[0].getNumberOfRows();
    System.arraycopy(array[0].getData(), 0, tmp, 0, n);
    double[] arrayptr;
    for (int i = 0; i < array.length; i++) {
      arrayptr = array[i].getData();
      for (int j = 0; j < n; j++) {
        tmp[j] *= arrayptr[j];
      }

    }
    return new OGDoubleArray(tmp, rows, cols);
  }

  public OGDoubleArray mtimes(OGDoubleArray... array) {
    catchNull(array);

    BLAS blas = new BLAS();

    // check they commute.
    final int colsArray1 = thisArray.getNumberOfColumns();
    final int colsArray2 = thatArray.getNumberOfColumns();
    final int rowsArray1 = thisArray.getNumberOfRows();
    final int rowsArray2 = thatArray.getNumberOfRows();

    if (colsArray1 != rowsArray2) {
      throw new MathsExceptionIllegalArgument("Arguments do not conform: thisArray is " + rowsArray1 + "x" + colsArray1 + ", thatArray is " + rowsArray2 + "x" + colsArray2 + ".");
    }

    //    System.out.println("this array="+thisArray.toString());
    //    System.out.println("that array="+thatArray.toString());

    double[] answer = null;
    int answerRows = 0;
    int answerCols = 0;
    if (colsArray2 == 1) { // A*x
      final int rows = thisArray.getNumberOfRows();
      double[] tmp = new double[rows];
      blas.dgemv('N', thisArray.getNumberOfRows(), thisArray.getNumberOfColumns(), 1.0, thisArray.getData(), thisArray.getNumberOfRows(), thatArray.getData(), 1, 0, tmp, 1);
      answer = tmp;
      answerRows = thisArray.getNumberOfRows();
      answerCols = 1;
    } else if (rowsArray1 == 1) { // x'*A
      double[] tmp = new double[thatArray.getNumberOfColumns()];
      blas.dgemv('T', thatArray.getNumberOfRows(), thatArray.getNumberOfColumns(), 1.0, thatArray.getData(), thisArray.getNumberOfColumns(), thisArray.getData(), 1, 0, tmp, 1);
      answer = tmp;
      answerRows = 1;
      answerCols = thatArray.getNumberOfColumns();
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
      int m = thisArray.getNumberOfRows();
      int n = thatArray.getNumberOfColumns();
      int k = thisArray.getNumberOfColumns();
      double alpha = 1.e0;
      int lda = m;
      int ldb = k;
      double beta = 0.e0;
      double[] cMatrix = new double[m * n];
      int ldc = m;
      blas.dgemm('N', 'N', m, n, k, alpha, thisArray.getData(), lda, thatArray.getData(), ldb, beta, cMatrix, ldc);
      answer = cMatrix;
      answerRows = m;
      answerCols = n;
    }
    return new OGDoubleArray(answer, answerRows, answerCols);
  }
}
