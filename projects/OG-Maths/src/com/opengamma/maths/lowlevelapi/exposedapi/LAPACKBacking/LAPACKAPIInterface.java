/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking;

/**
 * Required behaviours of the LAPACK interface, cut down version, may be expanded.
 * API is as close as possible to the LAPACK interface, comments are in part from the reference LAPACK at
 * http://www.netlib.org/lapack/double/
 */
public interface LAPACKAPIInterface {

  /**
   * Provides DGESVD which computes the singular value decomposition of a real matrix of dimension "m" rows and "n" columns.
   * If requested it can also compute the left and/or right singular vectors.
   * The SVD can be written as:
   *    A = U * SIGMA * V^T
   * where ^T denotes transpose.
   * The matrix SIGMA is an m by n matrix of zeros except for its min(m,n) diagonal elements.
   * The matrix U is an m by m orthogonal matrix.
   * The matrix V is an n by n orthogonal matrix.
   * The diagonal elements of SIGMA are the singular values of A, they are real and non-negative and returned in descending order 
   * The first min(m,n) columns of U and V are the left and right singular vectors of A.
   * For those familiar with "Matrix Computations" by Golub and Van Loan this can give the "full" SVD or "thin" variants.
   * @param jobu specifies options from computing all or parts of the matrix U, should be one of {'A','S','O','N'} with the following meanings:
   * 'A': all "m" columns of U are returned in U.
   * 'S': the first min(m,n) columns of U are returned in U (thin SVD, see Golub and Van Loan).
   * 'O': the first min(m,n) columns of U are overwritten on A (thin SVD, see Golub and Van Loan).
   * 'N': no columns U are computed.
   * @param jobvt specifies options from computing all or parts of the matrix V^T, should be one of {'A','S','O','N'} with the following meanings:
   * 'A': all "n" rows of V^T are returned in VT.
   * 'S': the first min(m,n) rows of V^T are returned in VT.
   * 'O': the first min(m,n) rows of V^T are overwritten on A.
   * 'N': no rows of V^T are computed.
   * @param m the number of rows in A. m>=0.
   * @param n the number of columns in A. n>=0.
   * @param A the matrix A, dimension(lda,n). A *is* altered in the computation as follows:
   * if jobu == 'O', A is overwritten with the first min(m,n) columns of U.
   * if jobvt == 'O', A is overwritten with the first min(m,n) rows of V^T.
   * if jobu != 'O' && jobvt != 'O', the contents of A is destroyed in the computation.
   * @param lda the leading dimension of A. lda>=max(1,m)
   * @param S on output contains the singular values of A sorted in descending order. S has dimension min(m,n)
   * @param U has dimension and contains the following: 
   * standard: dimension(lda, ucol)
   * if jobu=='A': dimension(ldu,m), U on output contanis the m by m orthogonal matrix U
   * if jobu=='S': dimension(ldu,min(m,n)), U on output contains the first min(m,n) columns of U
   * if jobu=='N'||'O': U is not referenced
   * @param ldu the leading dimension of the array U. LDU>=0, if jobu=='S'||'A', LDU>=m.
   * @param VT has dimension(ldvt,n) and contains the following:
   * if jobvt=='A': VT on output contains the n by n orthogonal matrix V^T
   * if jobvt=='S': VT on output contains first min(m,n) rows of the orthogonal matrix V^T
   * if jobvt=='N'||'O': VT is not referenced
   * @param ldvt the leading dimension of the array VT. ldvt>=1, if jobvt=='A' ldvt>=n, if jobvt=='S' ldvt>=min(m,n)
   * @param WORK acts as scratchspace but can contain useful output if detected by nonzero return in info.
   * The WORK array must have dimension max(1,lwork).
   * On return WORK has the following:
   * if info=0: WORK[0] returns the optimal lwork value.
   * if info>0: something went wrong whilst trying to reduce the bidiagonals. WORK(1:min(m,n)) contains the unconverged
   * superdiagonal elements of an upper bidiagonal matrix B. The diagonal elements from B are stored in S (not necessarily sorted).
   * B (when assembled) satisfies A=U*B*VT and therefore has the same singular values as A, with singular vectors related by U and VT.
   * @param lwork the dimension of the array WORK.
   * lwork >= max(3*min(m, n)+max(m, n), 5*min(m,n)), for better performance lwork should be larger if possible.
   * If lwork = -1, then the routine does nothing apart from calculate the optimal size of the array WORK and returns it in WORK[0].
   * @param info is a 1 element array, on exit the following values indicate:
   * info[0]==0: success
   * info[0]<0: if info = -i the i^th argument had an illegal value
   * info[0]>0: if dbdsqr() did not converge, info specifies how many superdiagonals of the bidiagonal form B did not converge to zero (See notes for input WORK). 
   */
  void dgesvd(char jobu, char jobvt, int m, int n, double[] A, int lda, double[] S, double[] U, int ldu, double[] VT, int ldvt, double[] WORK, int lwork, int[] info); // CSIGNORE

  // Complex SVD
  void zgesvd(char jobu, char jobvt, int m, int n, double[] A, int lda, double[] S, double[] U, int ldu, double[] VT, int ldvt, double[] WORK, int lwork, double[] RWORK, int[] info); // CSIGNORE

  // LUP
  void dgetrf(int m, int n, double[] A, int lda, int[] IPIV, int[] INFO); // CSIGNORE

  // linear least squares
  void dgelsd(int m, int n, int nrhs, double[] A, int lda, double[] b, int ldb, double[] s, double rcond, int[] rank, double[] work, int lwork, int[] iwork, int[] info); //CSIGNORE

  // triangular solve
  void dtrtrs(char uplo, char trans, char diag, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] info);

}
