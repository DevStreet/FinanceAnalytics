/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
/**
* This code is a translation of the BLAS code provided by netlib.org.
* It has been manually edited based on the results of the f2j project.
* 
*/
package com.opengamma.maths.lowlevelapi.linearalgebra.referenceBLAS;

import com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking.BLASAPIInterface;

/**
 * Does DGEMM, See {@linkplain BLASAPIInterface}
 */
public class Dgemm {

  public static void dgemm(char transa, char transb, int m, int n, int k, double alpha, double[] aMatrix, int aMatrixOffset, int lda,
      double[] bMatrix, int bOffset, int ldb, double beta, double[] cMatrix, int cOffset, int ldc) {
    double temp = 0.0d;
    int i = 0;
    int info = 0;
    int j = 0;
    int l = 0;
    int nrowa = 0;
    int nrowb = 0;
    boolean nota = false;
    boolean notb = false;
    nota = (transa == 'N');
    notb = (transb == 'N');
    if (nota) {
      nrowa = m;
    } else {
      nrowa = k;
    }
    if (notb) {
      nrowb = k;
    } else {
      nrowb = n;
    }

    // *
    // *     Test the input parameters.
    // *
    info = 0;
    if (!nota && transa != 'C' && transa != 'T') {
      info = 1;
    } else if (!notb && transb != 'C' && transb != 'T') {
      info = 2;
    } else if ((m < 0)) {
      info = 3;
    } else if ((n < 0)) {
      info = 4;
    } else if ((k < 0)) {
      info = 5;
    } else if ((lda < Math.max(1, nrowa))) {
      info = 8;
    } else if ((ldb < Math.max(1, nrowb))) {
      info = 10;
    } else if ((ldc < Math.max(1, m))) {
      info = 13;
    }
    if (info != 0) {
      Xerbla.xerbla("DGEMM ", info);
    }
    // *     Quick return if possible.
    if ((m == 0) || (n == 0) || ((alpha == 0.0 || k == 0) && beta == 1.0)) {
      return;
    }
    // *     And if  alpha.eq.zero.
    if (alpha == 0.0) {
      if ((beta == 0.0)) {
        for (j = 0; j < n; j++) {
          for (i = 0; i < m; i++) {
            cMatrix[i + j * ldc + cOffset] = 0.0;
          }
        }
      } else {
        for (j = 0; j < n; j++) {
          for (i = 0; i < m; i++) {
            cMatrix[i + j * ldc + cOffset] = beta * cMatrix[i + j * ldc + cOffset];
          }
        }
      }
      return;
    }

    // *     Start the operations.
    if (notb) {
      if (nota) {
        // *           Form  C := alpha*A*B + beta*C.
        for (j = 0; j < n; j++) {
          if ((beta == 0.0)) {
            for (i = 0; i < m; i++) {
              cMatrix[i + j * ldc + cOffset] = 0.0;
            }

          } else if ((beta != 1.0)) {
            for (i = 0; i < m; i++) {
              cMatrix[i + j * ldc + cOffset] = beta * cMatrix[i + j * ldc + cOffset];
            }
          }
          for (l = 0; l < k; l++) {
            if (bMatrix[l + j * ldb + bOffset] != 0.0) {
              temp = alpha * bMatrix[l + j * ldb + bOffset];
              for (i = 0; i < m; i++) {
                cMatrix[i + j * ldc + cOffset] = cMatrix[i + j * ldc + cOffset] + temp * aMatrix[i + l * lda + aMatrixOffset];
              }
            }
          }
        }
      } else {
        // *           Form  C := alpha*A'*B + beta*C
        for (j = 0; j < n; j++) {
          for (i = 0; i < m; i++) {
            temp = 0.0;
            for (l = 0; l < k; l++) {
              temp = temp + aMatrix[l + i * lda + aMatrixOffset] * bMatrix[l + j * ldb + bOffset];
            }
            if ((beta == 0.0)) {
              cMatrix[i + j * ldc + cOffset] = alpha * temp;
            } else {
              cMatrix[i + j * ldc + cOffset] = (alpha * temp) + beta * cMatrix[i + j * ldc + cOffset];
            }
          }
        }
      }
    } else {
      if (nota) {
        // *           Form  C := alpha*A*B' + beta*C
        for (j = 0; j < n; j++) {
          if (beta == 0.0) {
            for (i = 0; i < m; i++) {
              cMatrix[i + j * ldc + cOffset] = 0.0;
            }
          } else if (beta != 1.0) {
            for (i = 0; i < m; i++) {
              cMatrix[i + j * ldc + cOffset] = beta * cMatrix[i + j * ldc + cOffset];
            }
          }
          for (l = 0; l < k; l++) {
            if (bMatrix[j + l * ldb + bOffset] != 0.0) {
              temp = alpha * bMatrix[j + l * ldb + bOffset];
              for (i = 0; i < m; i++) {
                cMatrix[i + j * ldc + cOffset] = cMatrix[i + j * ldc + cOffset] + temp * aMatrix[i + l * lda + aMatrixOffset];
              }
            }
          }
        }
      } else {
        // *           Form  C := alpha*A'*B' + beta*C
        for (j = 0; j < n; j++) {
          for (i = 0; i < m; i++) {
            temp = 0.0;
            for (l = 0; l < k; l++) {
              temp = temp + aMatrix[l + i * lda + aMatrixOffset] * bMatrix[j + l * ldb + bOffset];
            }
            if (beta == 0.0) {
              cMatrix[i + j * ldc + cOffset] = (alpha * temp);
            } else {
              cMatrix[i + j * ldc + cOffset] = (alpha * temp) + beta * cMatrix[i + j * ldc + cOffset];
            }
          }
        }
      }
    }
    return;
  }
} // End class.

