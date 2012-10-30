/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.lu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGLuResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.lu.Lu.compute;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACK;

/**
 * Does LU/LUP on OGDoubleArrays 
 */
public final class LuOGDoubleArray implements LuAbstract<OGMatrix> {
  private static LuOGDoubleArray s_instance = new LuOGDoubleArray();
  private static Logger s_log = LoggerFactory.getLogger(LuOGDoubleArray.class);

  public static LuOGDoubleArray getInstance() {
    return s_instance;
  }

  private LuOGDoubleArray() {
  }

  private LAPACK _localLAPACK = new LAPACK();

  @Override
  public OGLuResult lu(OGMatrix array1, compute these) {
    final int m = array1.getNumberOfRows();
    final int n = array1.getNumberOfColumns();
    final int lda = Math.max(1, m);
    double[] A = new double[m * n];//CSIGNORE
    System.arraycopy(array1.getData(), 0, A, 0, m * n); // else the data in A is destroyed
    int[] ipiv = new int[Math.min(m, n)];
    int[] info = new int[1];

    // common var
    double[] L = null;//CSIGNORE
    double[] U = null;//CSIGNORE
    int[] P = null;//CSIGNORE

    // return stores
    OGMatrix retL = null;
    OGMatrix retU = null;
    OGPermutationMatrix retP = null;

    // the LAPACK call
    _localLAPACK.dgetrf(m, n, A, lda, ipiv, info);

    // smell info, if it's >0 LAPACK spotted that U[i][i] is exactly zero which will screw up back substitution and obviously indicates singular
    if (info[0] > 0) {
      s_log.warn("LAPACK: completed call to dgetrf() suggests that the inputted matrix is singular at U[" + info[0] + "][" + info[0] +
          "]. The use of U for back substitution will result in divide by 0.");
    }

    // jmp on request
    switch (these) {
      case L:
        L = extractL(A, m, n);
        retL = new OGMatrix(L, m, n);
        break;
      case U:
        U = extractU(A, n);
        retU = new OGMatrix(U, n, n);
        break;
      case LU:
        double[] localL;
        localL = new double[m * n];
        U = new double[n * n];
        processLU(A, m, n, localL, U);
        P = piv2perm(ipiv, m);
        // apply pivot to L so that L*U=A, i.e. we multiply transpose(P)*L, this is a stride hating row permutation, grrrr
        L = permuteL(localL, m, n, P);
        retL = new OGMatrix(L, m, n);
        retU = new OGMatrix(U, n, n);
        retP = new OGPermutationMatrix(P);
        break;
      case LUP:
        L = new double[m * n];
        U = new double[n * n];
        processLU(A, m, n, L, U);
        P = piv2perm(ipiv, m);
        // done, we want L*U=A*P so that transpose(P)*L*U=A
        retL = new OGMatrix(L, m, n);
        retU = new OGMatrix(U, n, n);
        retP = new OGPermutationMatrix(P);
        break;

    }
    return new OGLuResult(retL, retU, retP);
  }

  // "reads" a pivot matrix into permutation form 
  private static int[] piv2perm(int[] P, int m) {//CSIGNORE
    final int len = P.length;
    int[] perm = new int[m];
    int piv, swp; // current pivot and swap store
    // turn into 0 based indexing
    for (int i = 0; i < len; i++) {
      P[i] -= 1;
    }
    // 0:m-1 range vector, will be permuted in a tick
    for (int i = 0; i < m; i++) {
      perm[i] = i;
    }
    // apply permutation to range indexed vector, just walk through in order and apply the swaps
    for (int i = 0; i < len; i++) {
      piv = P[i]; // get pivot at index "i"
      if (piv != i) { // apply the pivot by swapping the corresponding "row" indices in the perm index vector
        swp = perm[piv];
        perm[piv] = perm[i];
        perm[i] = swp;
      }
    }
    return perm;
  }

  private static double[] extractL(double[] A, int m, int n) {//CSIGNORE
    double[] L = new double[m * n];//CSIGNORE
    int mi;
    for (int i = 0; i < n; i++) {
      mi = m * i;
      L[mi + i] = 1.d;
      for (int j = i + 1; j < m; j++) {
        L[mi + j] = A[mi + j];
      }
    }
    return L;
  }

  private static double[] extractU(double[] A, int n) {//CSIGNORE
    double[] U = new double[n * n];//CSIGNORE
    int ni;
    for (int i = 0; i < n; i++) {
      ni = n * i;
      for (int j = 0; j <= i; j++) {
        U[ni + j] = A[ni + j];
      }
    }
    return U;
  }

  // preserve some cache lines, apparently they appreciate it
  private static double[] processLU(double[] A, int m, int n, double[] L, double[] U) {//CSIGNORE
    int mi, ni;
    for (int i = 0; i < n; i++) {
      mi = m * i;
      ni = n * i;
      for (int j = 0; j <= i; j++) {
        U[ni + j] = A[mi + j];
      }
      L[mi + i] = 1.d;
      for (int j = i + 1; j < m; j++) {
        L[mi + j] = A[mi + j];
      }
    }
    return U;
  }

  // permute lower matrix as given by "perm", might hoik this out to a separate function at some point
  private static double[] permuteL(double[] L, int m, int n, int[] P) {//CSIGNORE
    // this is essentially a reorder of the rows which is the same as a broadcasted col perm
    double[] permL = new double[L.length];
    int im;
    for (int i = 0; i < n; i++) {
      im = i * m;
      for (int j = i; j < m; j++) { //wrecks the stride in permL :(. Walk lower half only to save moving zeros
        permL[im + P[j]] = L[im + j];
      }
    }
    return permL;
  }
}
