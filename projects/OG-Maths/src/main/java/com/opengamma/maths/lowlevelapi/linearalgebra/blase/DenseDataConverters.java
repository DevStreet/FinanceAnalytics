/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.linearalgebra.blase;

import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Blind converters from one memory format to another for BLAS/LAPACK storage schemes "Packed" and "Banded"
 */
public class DenseDataConverters {

  /**
   * Extracts the upper triangle from some dense data, all other entries are zero'd
   * @param aMatrix the matrix from which to extract the upper triangle
   * @param m the number of rows
   * @param n the number of columns
   * @return the data with the upper triangle extracted and all other entries set to zero
   */
  public static double[] fromDenseExtractUpperTriangle(double[] aMatrix, int m, int n) {
    Catchers.catchNullFromArgList(aMatrix, 1);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(m, 2);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 3);
    Catchers.catchInsufficientDataSizeFromArgList(aMatrix, m * n, 1);
    double[] tmp = new double[m * n];
    for (int j = 0; j < m; j++) {
      for (int i = j; i < n; i++) {
        tmp[i * m + j] = aMatrix[i * m + j];
      }
    }
    return tmp;
  }

  /**
   * Extracts the lower triangle from some dense data, all other entries are zero'd
   * @param aMatrix the matrix from which to extract the lower triangle
   * @param m the number of rows
   * @param n the number of columns
   * @return the data with the lower triangle extracted and all other entries set to zero
   */
  public static double[] fromDenseExtractLowerTriangle(double[] aMatrix, int m, int n) {
    Catchers.catchNullFromArgList(aMatrix, 1);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(m, 2);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 3);
    Catchers.catchInsufficientDataSizeFromArgList(aMatrix, m * n, 1);
    double[] tmp = new double[m * n];
    for (int j = 0; j < n; j++) {
      for (int i = j; i < m; i++) {
        tmp[j * m + i] = aMatrix[j * m + i];
      }
    }
    return tmp;
  }

  /**
   * Extracts the upper triangle from some dense data and packs it into columnar packed form
   * @param aMatrix the matrix from which to extract the upper triangle
   * @param n min(rows,columns)
   * @return the data with the upper triangle extracted and packed
   */
  public static double[] fromDenseExtractUpperTriangleToPackedStorage(double[] aMatrix, int n) {
    Catchers.catchNullFromArgList(aMatrix, 1);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 2);
    Catchers.catchInsufficientDataSizeFromArgList(aMatrix, n * n, 1);
    double[] tmp = new double[((n * (n + 1)) / 2)];
    int ptr = 0;
    for (int j = 0; j < n; j++) {
      for (int i = 0; i <= j; i++) {
        tmp[ptr++] = aMatrix[i + j * n];
      }
    }
    return tmp;
  }

  /**
   * Extracts the lower triangle from some dense data and packs it into columnar packed form
   * @param aMatrix the matrix from which to extract the upper triangle
   * @param n min(rows,columns)
   * @return the data with the upper triangle extracted and packed
   */
  public static double[] fromDenseExtractLowerTriangleToPackedStorage(double[] aMatrix, int n) {
    Catchers.catchNullFromArgList(aMatrix, 1);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 2);
    Catchers.catchInsufficientDataSizeFromArgList(aMatrix, n * n, 1);
    double[] tmp = new double[((n * (n + 1)) / 2)];
    int ptr = 0;
    for (int j = 0; j < n; j++) {
      for (int i = j; i < n; i++) {
        tmp[ptr++] = aMatrix[i + j * n];
      }
    }
    return tmp;
  }

  /**
   * Extracts from a dense m*n matrix the kl subdiagonals and ku superdiagonals into band storage of size kl+ku+1 rows and n columns.
   * 
   * @param aMatrix the data from which a banded matrix shall be extracted and put into banded storage
   * @param m number of rows
   * @param n number of columns
   * @param ku number of upper bands, must satisfy ku>0;
   * @param kl number of lower bands, must satisfy kl>0;
   * @return the band data in band storage format, dimension (ku+kl+1,n)
   */
  public static double[] fromDenseExtractBandMatrixToBandStorage(double[] aMatrix, int m, int n, int ku, int kl) {
    Catchers.catchNullFromArgList(aMatrix, 1);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(m, 2);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 3);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(ku, 4);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(kl, 5);
    Catchers.catchValueShouldBeLessThanXFromArgList(ku, n, 4);
    Catchers.catchValueShouldBeLessThanXFromArgList(kl, m, 3);    
    Catchers.catchInsufficientDataSizeFromArgList(aMatrix, m * n, 1);
    int rows = kl + ku + 1;
    double[] tmp = new double[rows * n];
    int k;
    int klp1 = kl + 1;
    int jrows, jm;
    for (int j = 0; j < n; j++) {
      k = ku - j;
      jm = j * m;
      jrows = j * rows;
      for (int i = Math.max(0, j - ku); i < Math.min(m, j + klp1); i++) {
        tmp[k + i + jrows] = aMatrix[i + jm];
      }
    }
    return tmp;
  }
}
