/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking;

/**
 * Required behaviours of the BLAS interface, cut down version, may be expanded.
 * API is as close as possible to netlib.org's reference BLAS interface, comments are in part also from the reference BLAS.
 */
public interface BLASAPIInterface {

  /**
   * Provides BLAS LEVEL 1: DROTG.
   * DROTG generates plane (Givens) rotations as follows:
   * 
   * | c  s| * |a| = |r|
   * |-s  c|   |b|   |0|
   *
   * On input: 
   * @param a first parameter to be eliminated
   * @param b second parameter to be eliminated
   * @param c the cosine of the angle required for the elimination
   * @param s the sine of the angle required for the elimination
   * 
   * On return:
   * {@code a} a contains the value "{@code r}"
   * 
   * {@code b} contains what is commonly referred to as the z-value. It is essentially a single number that can be used to represent the
   * rotation from which {@code s} and {@code c} can be found. The encoding is typically:
   * {@code z}=1 when {@code c} = 0 and {@code s}=1
   * | {@code z} |<1, {@code c} = sqrt(1 - {@code z} ^ 2) and {@code s} = {@code z}
   * | {@code z} |>1, {@code c} = 1/{@code z} and {@code s} = sqrt(1 - {@code c} ^ 2)
   * This encoding is present as it forms an important time and space saving when computing succesive rotations.
   * 
   * {@code c} returns the cosine rotation angle as defined above
   * {@code s} returns the sine rotation angle as defined above
   * 
   * _Example_
   * Input:
   * a = 0.34e0
   * b = 0.79e0
   * c = 0
   * s = 0 
   * 
   * Call:
   * drotg(a,b,c,s)
   * 
   * Output:
   * a = 0.86005813756978089
   * b = 2.5295827575581789
   * c = 0.39532211271289097
   * s = 0.91854255600936430
   * 
   * then output:a  = r = c*(input:a) + s*(input:b) = 0.860058137569780... 
   * and -s*(input:a) + c*(input:b) = 0
   * 
   * output:b = z = 2.5295827575581789
   * such that
   * cosine rotation = 1/z = 0.39532...
   * sine rotation   = sqrt( 1 - (cosine rotation)^2 ) = 0.91854...
   * 
   */
  void drotg(double[] a, double[] b, double[] c, double[] s);

  /**
   * Provides BLAS LEVEL 1: DROTMG.
   * DROTMG generates the modified plane (Givens) rotations as follows:
   * |H as defined below|*|sqrt(dd1)*dx1| = |r|
   * |                  | |sqrt(dd2)*dy2|   |q|
   * 
   * Based on the FLAG returned in the the first element of array dPARAM, H is:
   * Case FLAG = -1.0e0
   * H = |DH11  DH12|
   *     |DH21  DH22|
   *     
   * Case FLAG = 0.0e0
   * H = |1.e0  DH12|
   *     |DH21  1.e0| 
   * 
   * Case FLAG = 1.d0
   * H = |DH11  1.e0|
   *     |-1.e0 DH22| 
   *
   * Case FLAG = -2.d0
   * H = |1.e0  0.e0|
   *     |0.e0  1.e0|
   *      
   * On input:
   * @param dd1 the scaling factor for element dx1 (see above)
   * @param dd2 the scaling factor for element dy2 (see above)
   * @param dx1 see above
   * @param dy2 see above
   * @param dPARAM a 5 element vector
   * 
   * On output:
   * {@code dd1} the updated scaling factor for dx1
   * {@code dd2} the updated scaling factor for dy2
   * {@code dx1} the 'x' coordinate of the rotated vector prior to scaling by the updated dd1 factor
   * {@code dy2} is unchanged 
   * {@code dPARAM[0]} contains the flag as described above.
   * {@code dPARAM[1:4]} contains parameters (if needed) [DH11, DH21, DH12, DH22], from the "H" matrix described above, in that order.
   * 
   * This routine is typically used when a sequence of rotations are required. A fairly standard case involves starting
   * with dd1 and dd2 set to 1.e0 and then as iterations progress they are subsequently fed back in as inputs for
   * future iterations. Thus the scaling factors are folded as the interations proceed which means the computational
   * cost is closer to that of the standard rotation (in opposition to standard rotations plus manual scaling). 
   * 
   * _Example_
   * Input:
   * dd1 = 1
   * dd2 = 2
   * dx1 = 3
   * dy2 = 4
   * dParam = {0,0,0,0,0}
   * 
   * Call:
   * drotmg(dd1,dd2,dx1,dy2,dPARAM);
   * 
   * Output:
   * dd1 = 1.5609756097560976
   * dd2 = 0.78048780487804881
   * dx1 = 5.125
   * dy2 = 4.000
   * dParam = {1e0, 0.375, 0e0 , 0e0, 0.75} 
   */
  void drotmg(double[] dd1, double[] dd2, double[] dx1, double[] dy2, double[] dPARAM);

  /**
   * Provides BLAS LEVEL 1: DROT.
   * DROT applies a plane (Givens) rotation (for example from calls to DROTG) to a set of coordinates as follows:
   * |x|<--| c  s| * |x|
   * |y|   |-s  c|   |y|
   * 
   * The call is vectorised such that a set of ordered coordinate pairs can be passed as two vectors 'x' and 'y'
   * and the operation will be carried out sequentially on the pairs in the vectors.
   * 
   * @param n number of coordinates to which the rotation shall be applied
   * @param x a vector containing the 'x' coordinates to which the rotation shall be applied 
   * @param incx the increment between successive elements of 'x'
   * @param y a vector containing the 'y' coordinates to which the rotation shall be applied
   * @param incy the increment between successive elements of 'y'
   * @param c the cosine of the rotation angle
   * @param s the sine of the rotation angle
   * 
   * Typical use: zeroing a subdiagonal element in an Upper Hessenberg matrix, rotations need to be applied to the
   * row space of the elements intersecting the zeroed coordinate.
   * 
   * _Example_
   * Input:
   * n=10
   * x={1,2,3,4,5,6,7,8,9,10}
   * incx = 1
   * y={10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200}
   * incy = 2
   * c = 0.866025403784439 = sqrt(3)/2
   * s = 0.5
   * 
   * Call:
   * drot(n,x,incx,y,incy,c,s)
   * 
   * Output:
   * n=10
   * x={5.866..., 16.73..., 27.59..., 38.46..., 49.33..., 60.19..., 71.06..., 81.92..., 92.79..., 103.66...}
   * incx = 1
   * y={20.00..., 24.98..., 40.00..., 41.80..., 60.00..., 58.62..., 80.00..., 75.44..., 100.00..., 92.26...,\
   *  120.00..., 109.08..., 140.00..., 125.90..., 160.00..., 142.72..., 180.00..., 159.54..., 200.00}
   * incy = 2 
   * c = 0.866025403784439 = sqrt(3)/2
   * s = 0.5
   *  
   */
  void drot(int n, double[] x, int incx, double[] y, int incy, double c, double s);

  /**
   * Provides BLAS LEVEL 1: DROTM.
   * DROTM applies a modified plane (Givens) rotation (for example from calls to DROTMG) to a set of coordinates as follows:
   * |x|<--| H | * |x|
   * |y|   |   |   |y|
   * 
   * The matrix H is derived from a 5 element vector dPARAM encoded as (NOTE: COLUMN MAJOR UNWIND!):
   * H = [FLAG, DH11, DH21, DH12, DH22]
   * 
   * Based on the value of FLAG in the the first element of array dPARAM, H is formed as:
   * Case FLAG = -1.0e0
   * H = |DH11  DH12|
   *     |DH21  DH22|
   *     
   * Case FLAG = 0.0e0
   * H = |1.e0  DH12|
   *     |DH21  1.e0| 
   * 
   * Case FLAG = 1.d0
   * H = |DH11  1.e0|
   *     |-1.e0 DH22| 
   *
   * Case FLAG = -2.d0
   * H = |1.e0  0.e0|
   *     |0.e0  1.e0|
   *
   * The call is vectorised such that a set of ordered coordinate pairs can be passed as two vectors 'x' and 'y'
   * and the operation will be carried out sequentially on the pairs in the vectors.
   *
   * @param n number of coordinates to which the rotation shall be applied
   * @param x a vector containing the 'x' coordinates to which the rotation shall be applied 
   * @param incx the increment between successive elements of 'x'
   * @param y a vector containing the 'y' coordinates to which the rotation shall be applied
   * @param incy the increment between successive elements of 'y'
   * @param dPARAM a 5 element vector, encoded as given above (elements are zeroed if not required by FLAG).
   *  
   * Typical use: Applying compounded (usually scaled) rotations to a subspace.
   * 
   * _Example_
   * Input:
   * n=10
   * x={1,2,3,4,5,6,7,8,9,10}
   * incx = 1
   * y={10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200}
   * incy = 2
   * dPARAM = {1e0, 0.375, 0e0 , 0e0, 0.75}
   * 
   * Call:
   * drotm(n,x,incx,y,incy,dPARAM)
   * 
   * Output:
   * n=10 
   * x={10.38..., 30.75..., 51.13..., 71.50..., 91.88..., 112.25..., 132.63..., 153.00..., 173.38..., 193.75...}
   * incx = 1
   * y={6.50..., 20.00..., 20.50..., 40.00..., 34.50..., 60.00..., 48.50..., 80.00..., 62.50..., 100.00..., 76.50...,\
   * 120.00..., 90.50..., 140.00..., 104.50..., 160.00..., 118.50..., 180.00..., 132.50..., 200.00...}
   * incy = 2
   * dPARAM = {1e0, 0.375, 0e0 , 0e0, 0.75}
   *   
   */
  void drotm(int n, double[] x, int incx, double[] y, int incy, double[] dPARAM);

  /**
   * Provides BLAS LEVEL 1: DSWAP 
   * DSWAP performs the following vector operation
   *
   *  x <--> y
   *
   *  x and y are vectors, variables incx and incy are used to indicated the indicies of the interchanges (see below).
   *
   * @param n the number of elements over which the operation is to be undertaken
   * @param x a vector of dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * @param y a vector of dimension (n-1) * |incy| + 1.
   * @param incy the increment between successive elements of 'y'
   * 
   * _Example_
   * Input:
   * n=10
   * x={1,2,3,4,5,6,7,8,9,10}
   * incx = 1
   * y={10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200}
   * incy = 2
   * 
   * Call:
   * dswap(n, x, incx, y, incy)
   * 
   * Output:
   * n=10
   * x={10,30,50,70,90,110,130,150,170,190}
   * incx = 1
   * y={1,20,2,40,3,60,4,80,5,100,6,120,7,140,8,160,9,180,10,200}
   * incy = 2
   * 
   */
  void dswap(int n, double[] x, int incx, double[] y, int incy);

  /**
   * Provides BLAS LEVEL 1: DSCAL
   * DSCAL performs the following vector operation
   *
   *  x <-- alpha*x
   *
   *  where alpha is scalar, x is a vector.
   *
   * @param n the number of elements over which the operation is to be undertaken
   * @param alpha the scaling factor
   * @param x a vector of dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * 
   * _Example_
   * Input:
   * n=5
   * alpha = 2e0;
   * x={1,2,3,4,5,6,7,8,9,10}
   * incx = 2
   * 
   * Call:
   * dscal(n, alpha, x, incx);
   * 
   * Output:
   * n=5
   * alpha = 2e0;
   * x={2,2,6,4,10,6,14,8,18,10}
   * incx = 2
   * 
   */
  void dscal(int n, double alpha, double[] x, int incx);

  /**
   * Provides BLAS LEVEL 1: DCOPY
   * DCOPY performs the following vector operation
   *
   *  x <-- y
   *
   *  x and y are vectors, variables incx and incy are used to indicated the indicies of the copy (see below).
   *  
   * DCOPY: x <-- y
   * @param n the number of elements over which the operation is to be undertaken
   * @param x a vector of minimum dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * @param y a vector of minimum dimension (n-1) * |incy| + 1.
   * @param incy the increment between successive elements of 'y'
   * 
   * _Example_
   * Input:
   * n=10
   * x={1,2,3,4,5,6,7,8,9,10}
   * incx = 1
   * y={10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200}
   * incy = 2
   * 
   * Call:
   * dswap(n, x, incx, y, incy)
   * 
   * Output:
   * n=10
   * x={1,2,3,4,5,6,7,8,9,10}
   * incx = 1
   * y={1,20,2,40,3,60,4,80,5,100,6,120,7,140,8,160,9,180,10,200}
   * incy = 2
   * 
   */
  void dcopy(int n, double[] x, int incx, double[] y, int incy);

  /**
   * Provides BLAS LEVEL 1: DAXPY.
   * DAXPY performs the following vector operation
   *
   *  y <-- alpha*x + y
   *
   *  where alpha is scalar, x and y are vectors.
   * 
   * @param n the number of elements over which the operation is to be undertaken
   * @param alpha the scaling factor
   * @param x a vector of minimum dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * @param y a vector of minimum dimension (n-1) * |incy| + 1.
   * @param incy the increment between successive elements of 'y'
   * 
   * _Example_
   * Input:
   * n=10
   * alpha=2e0
   * x={1,2,3,4,5,6,7,8,9,10}
   * incx = 1
   * y={10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200}
   * incy = 2
   * 
   * Call:
   * daxpy(n, alpha, x, incx, y, incy);
   * 
   * Output:
   * n=10
   * alpha=2e0
   * x={1,2,3,4,5,6,7,8,9,10}
   * incx = 1
   * y={12,20,34,40,56,60,78,80,100,100,122,120,144,140,166,160,188,180,210,200}
   * incy = 2
   * 
   */
  void daxpy(int n, double alpha, double[] x, int incx, double[] y, int incy);

  /**
   * Provides BLAS LEVEL 1: DAXPY. Call contains offset parameters.
   * See standard {@code daxpy} call for more information.
   * 
   * @param n the number of elements over which the operation is to be undertaken
   * @param alpha the scaling factor
   * @param x a vector of minimum dimension (n-1) * |incx| + 1.
   * @param xOffset offset into the array x at which the operation should take place
   * @param incx the increment between successive elements of 'x'
   * @param y a vector of minimum dimension (n-1) * |incy| + 1.
   * @param yOffset offset into the array y at which the operation should take place 
   * @param incy the increment between successive elements of 'y'
   */
  void daxpy(int n, double alpha, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy);

  /**
   * Provides BLAS LEVEL 1: DDOT.
   * DDOT performs the following vector operation
   *
   *  dot <-- (x^T)y
   *
   *  x and y are vectors. ^T denotes transpose.
   *  This is the classic dot (inner) product of two vectors.
   *
   * @param n the number of elements to be used in forming the dot product.
   * @param x first vector, minimum dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * @param y second vector, minimum dimension (n-1) * |incy| + 1.
   * @param incy the increment between successive elements of 'y'
   * @return dot product of x and y over the selected elements
   * 
   * _Example_
   * Input:
   * n=10
   * x={1,2,3,4,5,6,7,8,9,10}
   * incx = 1
   * y={10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200}
   * incy = 2
   * 
   * Call:
   * tmp = ddot(n,x,incx,y,incy);
   * 
   * Output:
   * tmp = 7150.
   * 
   */
  double ddot(int n, double[] x, int incx, double[] y, int incy);

  /**
   * Provides BLAS LEVEL 1: DDOT.Call contains offset parameters.
   * See standard {@code daxpy} call for more information.
   * @param n the number of elements to be used in forming the dot product.
   * @param x first vector, minimum dimension (n-1) * |incx| + 1.
   * @param xOffset offset into the array x at which the operation should take place 
   * @param incx the increment between successive elements of 'x'
   * @param y second vector, minimum dimension (n-1) * |incy| + 1.
   * @param incy the increment between successive elements of 'y'
   * @param yOffset offset into the array y at which the operation should take place
   * @return dot product of x and y over the selected elements
   *
   */
  double ddot(int n, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy);

  /**
   * Provides BLAS LEVEL 1: DNRM2
   * DNRM2 performs the following vector operation
   *
   *  nrm2 <-- ||x||_2
   *
   *  which is effectively
   *
   *  nrm2 <-- sqrt(x[0]*x[0]+x[1]*x[1]+...+x[n]*x[n])
   *
   *  where x is vector.
   *  This is the classic 2-norm (L2 norm, Euclidean norm) of a vector.
   *
   * DNRM2: nrm2 <-- ||x||_2
   * @param n the number of elements over which the operation is to be undertaken
   * @param x a vector of minimum dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * @return tmp the 2-norm of the selected elements in vector 'x'
   * 
   * _Example_
   * Input:
   * n=5
   * x={1,2,3,4,5,6,7,8,9,10}
   * incx = 2
   * 
   * Call:
   * double tmp = dnrm2(n,x,incx);
   * 
   * Output:
   * tmp = 12.845232578665131
   * 
   */
  double dnrm2(int n, double[] x, int incx);

  /**
   * Provides BLAS LEVEL 1: DASUM.
   * DASUM performs the following vector operation.
   *
   *  asum <-- ||x||_1
   *
   *  which is effectively
   *
   *  asum2 <-- x[0]+x[1]+x[2]+...+x[n]
   *
   *  Where x is vector. Essentially this operation is the reduction of a vector.
   *
   * @param n the number of elements over which the operation is to be undertaken
   * @param x a vector of minimum dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * @return tmp the vector reduction (sum) of x.
   * 
   * _Example_
   * Input:
   * n=5
   * x={1,2,3,4,5,6,7,8,9,10}
   * incx = 2
   * 
   * Call:
   * tmp = dasum(n, x, incx);
   * 
   * Output:
   * tmp = 25 
   */
  double dasum(int n, double[] x, int incx);

  /**
   * Provides BLAS LEVEL 1: IDAMAX 
   * IDMAX performs the following operation
   *
   *  amax <-- 1st k where |Re{x_k}| == max(Re{x_i})
   *  
   *  Basically looks through the vector and returns the index of the first value that equals the absolute maximum.
   *
   * @param n the number of elements over which the operation is to be undertaken
   * @param x a vector of minimum dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * @return idx the first index at which the maximum value occurs (this index is the index taking into account
   * the incx variable so to obtain the absolute index within the vector 'x' compute "(tmp-1)*incx").
   * 
   * _Example_
   * Input:
   * n=5
   * x={1,2,3,4,5,6,71,8,9,10}
   * incx=2
   * 
   * Call:
   * int tmp = idamax(n,x,incx)
   * 
   * Output:
   * tmp = 4
   * 
   */
  int idamax(int n, double[] x, int incx);

  /**
   *
   * Provides BLAS LEVEL 2: DGEMV
   * DGEMV  performs one of the following matrix vector operations
   *
   *  y := alpha*A*x + beta*y OR y := alpha*A^T*x + beta*y,
   *
   *  where alpha and beta are scalars, x and y are vectors and A is an
   *  m by n matrix stored in column major format. The ^T indicates transposition.
   *
   * The variable {@code char} denotes the operation to be undertaken.
   * If trans is one of 'N' or 'n', the operation is y := alpha*A*x + beta*y
   * If trans is one of 'T' or 't', or, 'C' or 'c', the operation is y := alpha*A^T*x + beta*y 
   *
   * @param trans one of 'N' or 'n', 'T' or 't', 'C' or 'c'. See above.
   * @param m number of rows in matrix {@code aMatrix}
   * @param n number of columns in matrix {@code aMatrix}
   * @param alpha scaling factor for the matrix vector product
   * @param aMatrix the leading part of the "A" matrix of at least m by n entries
   * @param lda the first dimension of aMatrix, max(1,{@code m})
   * @param x a vector of minimum dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * @param beta scaling factor for the variable {@code y}
   * @param y a vector of minimum dimension (m-1) * |incy| + 1. Overwritten on output. 
   * @param incy the increment between successive elements of 'x'
   * 
   * _Example_
   * Input:
   * trans = 'N'
   * m=4
   * n=3
   * alpha=2.e0
   * aMatrix = {1,4,7,10,2,5,8,11,3,6,9,12};
   * lda = m
   * x = {10,20,30}
   * incx = 1
   * beta = 3.e0
   * y = {1,2,3,4,5,6,7,8}
   * incy = 2
   * 
   * Call:
   * dgemv(trans, m, n, alpha, aMatrix, lda, x, incx, beta, y, incy);
   * 
   * Output:
   * trans = 'N'
   * m=4
   * n=3
   * alpha=2.e0
   * aMatrix = {1,4,7,10,2,5,8,11,3,6,9,12};
   * lda = m
   * x = {10,20,30}
   * incx = 1
   * beta = 3.e0
   * y = {283, 2, 649, 4, 1015, 6, 1381, 8}
   * incy = 2
   *  
   */
  void dgemv(char trans, int m, int n, double alpha, double[] aMatrix, int lda, double[] x, int incx, double beta, double[] y, int incy);

  /**
  *
  * Provides BLAS LEVEL 2: DGBMV
  * DGBMV  performs one of the following matrix vector operations
  *
  *  y := alpha*A*x + beta*y OR y := alpha*A^T*x + beta*y,
  *
  *  where alpha and beta are scalars, x and y are vectors and A is an
  *  m by n band matrix with kl sub-diagonals and ku super-diagonals.
  *  The ^T indicates transposition.
  *
  * The variable {@code char} denotes the operation to be undertaken.
  * If trans is one of 'N' or 'n', the operation is y := alpha*A*x + beta*y
  * If trans is one of 'T' or 't', or, 'C' or 'c', the operation is y := alpha*A^T*x + beta*y 
  *
  * For information on the band storage format see http://www.netlib.org/lapack/lug/node124.html
  *
  * @param trans one of 'N' or 'n', 'T' or 't', 'C' or 'c'. See above.
  * @param m number of rows in matrix {@code aMatrix}
  * @param n number of columns in matrix {@code aMatrix}
  * @param kl the number of sub-diagonals in the matrix A, must satisfy kl >= 0.
  * @param ku the number of sub-diagonals in the matrix A, must satisfy ku >= 0. 
  * @param alpha scaling factor for the matrix vector product
  * @param aMatrix the leading part of the "A" matrix of at least m by n entries
  * @param lda the first dimension of aMatrix, must be at least kl+ku+1 and for most purposes will be.
  * @param x a vector of minimum dimension (n-1) * |incx| + 1.
  * @param incx the increment between successive elements of 'x'
  * @param beta scaling factor for the variable {@code y}
  * @param y a vector of minimum dimension (m-1) * |incy| + 1. Overwritten on output. 
  * @param incy the increment between successive elements of 'x'
  * 
  * _Example_
  * Input:
  * trans = 'N'
  * m=6
  * n=5
  * kl=2
  * ku=1
  * alpha=2.e0
  * aMatrix = {-1,1,6,11,2,7,12,17,8,13,18,23,14,19,24,29,20,25,30,-1} // recall that certain values and be "undefined", we use the flag -1  
  * lda = kl+ku+1
  * x = {1,2,3,4,5}
  * incx = 1
  * beta = 5.e0
  * y = {20,40,60,80,100,120}
  * incy = 1
  * 
  * Call:
  * dgbmv(trans, m, n, kl, ku, alpha, aMatrix, lda, x, incx, beta, y, incy);
  * 
  * Output:
  * trans = 'N'
  * m=6
  * n=5
  * alpha=2.e0
  * aMatrix = {-1,1,6,11,2,7,12,17,8,13,18,23,14,19,24,29,20,25,30,-1}
  * lda = kl+ku+1
  * x = {1,2,3,4,5}
  * incx = 1
  * beta = 5.e0
  * y = {110.00, 288.00, 560.00, 928.00, 1080.00, 1132.00}
  * incy = 1
  *  
  */
  void dgbmv(char trans, int m, int n, int kl, int ku, double alpha, double[] aMatrix, int lda, double[] x, int incx, double beta, double[] y, int incy);

  /**
  *
  * Provides BLAS LEVEL 2: DSYMV
  * DSYMV  performs the following matrix vector operation
  *
  *  y := alpha*A*x + beta*y
  *
  *  where alpha and beta are scalars, x and y are vectors and A is an
  *  n by n symmetric matrix.
  *
  * The first parameter "uplo" specifies whether the upper or lower triangular part
  * of the array A is to be referenced as follows:
  * UPLO = 'U' or 'u'   Only the upper triangular part of A is to be referenced.
  * UPLO = 'L' or 'l'   Only the lower triangular part of A is to be referenced.
  *
  * @param uplo one of 'U' or 'u', 'L' or 'l'. See above.
  * @param n number of rows/columns in matrix {@code aMatrix}
  * @param alpha scaling factor for the matrix vector product
  * @param aMatrix the leading part of the "A" matrix of at least n by n entries
  * @param lda the first dimension of aMatrix, must be at least max(1,n).
  * @param x a vector of minimum dimension (n-1) * |incx| + 1.
  * @param incx the increment between successive elements of 'x'
  * @param beta scaling factor for the variable {@code y}
  * @param y a vector of minimum dimension (m-1) * |incy| + 1. Overwritten on output. 
  * @param incy the increment between successive elements of 'x'
  * 
  * _Example_
  * Input:
  * uplo = 'U'
  * n=5
  * alpha=2.e0
  * aMatrix = {1,-1,-1,-1,-1,2,7,-1,-1,-1,3,8,13,-1,-1,4,9,14,19,-1,5,10,15,20,25} // recall that certain values and be "undefined", we use the flag -1  
  * lda = n
  * x = {1,2,3,4,5}
  * incx = 1
  * beta = 5.e0
  * y = {20,40,60,80,100,120}
  * incy = 1
  * 
  * Call:
  * dsymv(uplo, n, alpha, aMatrix, lda, x, incx, beta, y, incy);
  * 
  * Output:
  * uplo = 'U'
  * n=5
  * alpha=2.e0
  * aMatrix = {1,-1,-1,-1,-1,2,7,-1,-1,-1,3,8,13,-1,-1,4,9,14,19,-1,5,10,15,20,25}
  * lda = n
  * x = {1,2,3,4,5}
  * incx = 1
  * beta = 5.e0
  * y = {210.00, 452.00, 678.00, 880.00, 1050.00}
  * incy = 1
  *  
  */
  void dsymv(char uplo, int n, double alpha, double[] aMatrix, int lda, double[] x, int incx, double beta, double[] y, int incy);

  /**
  *
  * Provides BLAS LEVEL 2: DSBMV
  * DSBMV  performs the following matrix vector operation
  *
  *  y := alpha*A*x + beta*y
  *
  *  where alpha and beta are scalars, x and y are vectors and A is an
  *  n by n symmetric band matrix with k super diagonals.
  *
  * The first parameter "uplo" specifies whether the upper or lower triangular part
  * of the band matrix A is being supposed as follows:
  * UPLO = 'U' or 'u'   Only the upper triangular part of A is supplied.
  * UPLO = 'L' or 'l'   Only the lower triangular part of A is supplied.
  *
  * @param uplo one of 'U' or 'u', 'L' or 'l'. See above.
  * @param n number of rows/columns in matrix {@code aMatrix}
  * @param k the number of super-diagonals of the matrix A, k must satisfy k>=0.
  * @param alpha scaling factor for the matrix vector product
  * @param aMatrix if uplo=='U' or 'u' the leading ( k + 1 ) by n part of the array A must contain the upper triangular
  * band part of the symmetric matrix, supplied column by column, with the leading diagonal of the matrix in row
  * ( k + 1 ) of the array, the first super-diagonal starting at position 2 in row k, and so on. The top left k by k triangle
  * of the array A is not referenced.
  * If uplo=='L' or 'l' the leading (k+1 by n) part of the "A" matrix must contain the lower triangular
  * band part of the symmetric matrix, supplied column by column, with the leading diagonal of the matrix in row 1 of
  * the array, the first sub-diagonal starting at position 1 in row 2, and so on. The bottom right k by k triangle of the
  * array A is not referenced.
  * @param lda the first dimension of aMatrix, must be at least (k+1).
  * @param x a vector of minimum dimension (n-1) * |incx| + 1.
  * @param incx the increment between successive elements of 'x'
  * @param beta scaling factor for the variable {@code y}
  * @param y a vector of minimum dimension (m-1) * |incy| + 1. Overwritten on output. 
  * @param incy the increment between successive elements of 'x'
  * 
  * _Example_
  * Input:
  * uplo = 'U'
  * n=5
  * k=2
  * alpha=2.e0
  * aMatrix = {-1,-1,1,-1,2,7,3,8,13,9,14,19,15,20,25} // recall that certain values and be "undefined", we use the flag -1  
  * lda = 3
  * x = {1,2,3,4,5}
  * incx = 1
  * beta = 5.e0
  * y = {20,40,60,80,100}
  * incy = 1
  * 
  * Call:
  * dsbmv(uplo, n, alpha, aMatrix, lda, x, incx, beta, y, incy);
  * 
  * Output:
  * uplo = 'U'
  * n=5
  * k=2
  * alpha=2.e0
  * aMatrix = {-1,-1,1,-1,2,7,3,8,13,9,14,19,15,20,25}  
  * lda = 3
  * x = {1,2,3,4,5}
  * incx = 1
  * beta = 5.e0
  * y = {128.00, 352.00, 678.00, 872.00, 1000.00}
  * incy = 1
  *  
  */
  void dsbmv(char uplo, int n, int k, double alpha, double[] aMatrix, int lda, double[] x, int incx, double beta, double[] y, int incy);

  /**
  *
  * Provides BLAS LEVEL 2: DSPMV
  * DSPMV  performs the following matrix vector operation
  *
  *  y := alpha*A*x + beta*y
  *
  * where alpha and beta are scalars, x and y are vectors and A is an
  * n by n symmetric matrix, supplied in packed form.
  *
  * The first parameter "uplo" specifies whether the upper or lower triangular part
  * of the array A is supplied as follows:
  * UPLO = 'U' or 'u'   Only the upper triangular part of A is supplied.
  * UPLO = 'L' or 'l'   Only the lower triangular part of A is supplied.
  *
  * @param uplo one of 'U' or 'u', 'L' or 'l'. See above.
  * @param n number of rows/columns in matrix {@code aMatrix}
  * @param alpha scaling factor for the matrix vector product
  * @param aMatrix of at least dimension  ( ( n*( n + 1 ) )/2 ). 
  * Before entry with UPLO = 'U' or 'u', the array aMatrix must contain the upper triangular part of the symmetric matrix
  * packed sequentially, column by column, so that aMatrix( 1 ) contains A( 1, 1 ), aMatrix( 2 ) and aMatrix( 3 ) contain A( 1, 2 )
  * and A( 2, 2 ) respectively, and so on.
  * Before entry with UPLO = 'L' or 'l', the array aMatrix must contain the lower triangular part of the symmetric matrix
  * packed sequentially, column by column, so that aMatrix( 1 ) contains A( 1, 1 ), aMatrix( 2 ) and aMatrix( 3 ) contain A( 2, 1 )
  * and A( 3, 1 ) respectively, and so on.
  * @param x a vector of minimum dimension (n-1) * |incx| + 1.
  * @param incx the increment between successive elements of 'x'
  * @param beta scaling factor for the variable {@code y}
  * @param y a vector of minimum dimension (m-1) * |incy| + 1. Overwritten on output. 
  * @param incy the increment between successive elements of 'x'
  * 
  * _Example_
  * Input:
  * uplo = 'U'
  * n=5
  * alpha=2.e0
  * aMatrix = {1,2,7,3,8,13,4,9,14,19,5,10,15,20,25}
  * x = {1,2,3,4,5}
  * incx = 1
  * beta = 5.e0
  * y = {20,40,60,80,100}
  * incy = 1
  * 
  * Call:
  * dspmv(uplo, n, alpha, aMatrix, lda, x, incx, beta, y, incy);
  * 
  * Output:
  * uplo = 'U'
  * n=5
  * alpha=2.e0
  * aMatrix = {1,2,7,3,8,13,4,9,14,19,5,10,15,20,25}
  * x = {1,2,3,4,5}
  * incx = 1
  * beta = 5.e0
  * y = {210.00, 452.00, 678.00, 880.00, 1050.00}
  * incy = 1
  *  
  */
  void dspmv(char uplo, int n, double alpha, double[] aMatrix, double[] x, int incx, double beta, double[] y, int incy);

  /**
   * 
   * Provides BLAS LEVEL 2: DTRMV
   * DTRMV  performs one of the following matrix vector operations
   * 
   * x := A*x  or  x := A^T*x
   *  where x is a vectors and A is an n by n unit or non-uni upper or lower triangular matrix.
   *  The ^T indicates transposition.
   *
   * The variable {@code uplo} specifies whether A is an upper or lower triangular 
   * triangular matrix:
   * UPLO = 'U' or 'u'   A is an upper triangular matrix.
   * UPLO = 'L' or 'l'   A is an lower triangular matrix.
   * 
   * The variable {@code trans} denotes the operation to be undertaken.
   * If trans is one of 'N' or 'n', the operation is x := A*x
   * If trans is one of 'T' or 't', or, 'C' or 'c', the operation is x := A^T*x 
   * 
   * The variable {@code diag} specifies whether or not A is unit triangular as follows:
   * diag = 'U' or 'u'   A is assumed to be unit triangular.
   * diag = 'N' or 'n'   A is not assumed to be unit triangular.
   * 
   * @param uplo one of 'U' or 'u', 'L' or 'l'. See above.
   * @param trans one of 'N' or 'n', 'T' or 't', or, 'C' or 'c'. See above.
   * @param diag one of 'U' or 'u', 'N' or 'n'. See above.
   * @param n the order of the matrix A. n>=0;
   * @param aMatrix array of dimension (lda, n). 
   * Before entry with  UPLO = 'U' or 'u', the leading n by n upper triangular part of the array A must contain the upper
   * triangular matrix and the strictly lower triangular part of A is not referenced.
   * Before entry with UPLO = 'L' or 'l', the leading n by n lower triangular part of the array A must contain the lower
   * triangular matrix and the strictly upper triangular part of A is not referenced.
   * Note that when  DIAG = 'U' or 'u', the diagonal elements of A are not referenced either, but are assumed to be unity.
   * @param lda the leading dimension of aMatrix (A), at least max(1,n)
   * @param x a vector of minimum dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * 
   * _Example_
   * Input:
   * uplo = 'U'
   * trans = 'N'
   * diag = 'N'
   * n = 5
   * aMatrix={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25}
   * lda = n
   * x = {1,2,3,4,5}
   * incx = 1;
   * 
   * Call:
   * dtrmv(uplo,trans,diag,n,aMatrix,lda,x,incx)
   * 
   * Output
   * uplo = 'U'
   * trans = 'N'
   * diag = 'N'
   * n = 5
   * aMatrix={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25}
   * lda = n
   * x = {55,124,170,176,125}
   * incx = 1;
   * 
   */
  void dtrmv(char uplo, char trans, char diag, int n, double[] aMatrix, int lda, double[] x, int incx);

  /**
   * 
   * Provides BLAS LEVEL 2: DTBMV
   * DTBMV  performs one of the following matrix vector operations
   * 
   * x := A*x  or  x := A^T*x
   *  where x is a vector and A is an n by n unit, or non-unit, upper or lower triangular band matrix, with ( k + 1 ) diagonals.
   *  The ^T indicates transposition.
   *
   * The variable {@code uplo} specifies whether A is an upper or lower triangular 
   * triangular matrix:
   * UPLO = 'U' or 'u'   A is an upper triangular matrix.
   * UPLO = 'L' or 'l'   A is an lower triangular matrix.
   * 
   * The variable {@code trans} denotes the operation to be undertaken.
   * If trans is one of 'N' or 'n', the operation is x := A*x
   * If trans is one of 'T' or 't', or, 'C' or 'c', the operation is x := A^T*x 
   * 
   * The variable {@code diag} specifies whether or not A is unit triangular as follows:
   * diag = 'U' or 'u'   A is assumed to be unit triangular.
   * diag = 'N' or 'n'   A is not assumed to be unit triangular.
   * 
   * @param uplo one of 'U' or 'u', 'L' or 'l'. See above.
   * @param trans one of 'N' or 'n', 'T' or 't', or, 'C' or 'c'. See above.
   * @param diag one of 'U' or 'u', 'N' or 'n'. See above.
   * @param n the order of the matrix A. n>=0;
   * @param k If UPLO = 'U' or 'u', K specifies the number of super-diagonals of the matrix A.
   * If UPLO = 'L' or 'l', K specifies the number of sub-diagonals of the matrix A.
   * k must satisfy  k>=0
   * @param aMatrix array of dimension (lda, n). 
   * Before entry with UPLO = 'U' or 'u', the leading ( k + 1 ) by n part of the array A must contain the upper triangular
   * band part of the matrix of coefficients, supplied column by column, with the leading diagonal of the matrix in row
   * ( k + 1 ) of the array, the first super-diagonal starting at position 2 in row k, and so on. The top left k by k triangle
   * of the array A is not referenced.
   * Before entry with UPLO = 'L' or 'l', the leading ( k + 1 ) by n part of the array A must contain the lower triangular
   * band part of the matrix of coefficients, supplied column by column, with the leading diagonal of the matrix in row 1 of
   * the array, the first sub-diagonal starting at position 1 in row 2, and so on. The bottom right k by k triangle of the
   * array A is not referenced.
   * Note that when  DIAG = 'U' or 'u', the diagonal elements of A are not referenced either, but are assumed to be unity.
   * @param lda the leading dimension of aMatrix (A), at least (k+1)
   * @param x a vector of minimum dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * 
   * _Example_
   * Input:
   * uplo = 'U'
   * trans = 'N'
   * diag = 'N'
   * n = 5
   * k = 2
   * aMatrix={-1,-1,1,-1,2,7,3,8,13,9,14,19,15,20,25} // recall that certain values and be "undefined", we use the flag -1
   * lda = 3
   * x = {1,2,3,4,5}
   * incx = 1;
   * 
   * Call:
   * dtbmv(uplo,trans,diag,n,k,aMatrix,lda,x,incx)
   * 
   * Output
   * uplo = 'U'
   * trans = 'N'
   * diag = 'N'
   * n = 5
   * k = 2
   * aMatrix={-1,-1,1,-1,2,7,3,8,13,9,14,19,15,20,25}
   * lda = 3
   * x = {14, 74, 170, 176, 125}
   * incx = 1;
   * 
   */
  void dtbmv(char uplo, char trans, char diag, int n, int k, double[] aMatrix, int lda, double[] x, int incx);

  
  /**
   * 
   * Provides BLAS LEVEL 2: DTPMV
   * DTPMV  performs one of the following matrix vector operations
   * 
   * x := A*x  or  x := A^T*x
   *  where x is a vector and A is an n by n unit, or non-unit, upper or lower triangular matrix supplied in packed form.
   *  The ^T indicates transposition.
   *
   * The variable {@code uplo} specifies whether A is an upper or lower triangular 
   * triangular matrix:
   * UPLO = 'U' or 'u'   A is an upper triangular matrix.
   * UPLO = 'L' or 'l'   A is an lower triangular matrix.
   * 
   * The variable {@code trans} denotes the operation to be undertaken.
   * If trans is one of 'N' or 'n', the operation is x := A*x
   * If trans is one of 'T' or 't', or, 'C' or 'c', the operation is x := A^T*x 
   * 
   * The variable {@code diag} specifies whether or not A is unit triangular as follows:
   * diag = 'U' or 'u'   A is assumed to be unit triangular.
   * diag = 'N' or 'n'   A is not assumed to be unit triangular.
   * 
   * @param uplo one of 'U' or 'u', 'L' or 'l'. See above.
   * @param trans one of 'N' or 'n', 'T' or 't', or, 'C' or 'c'. See above.
   * @param diag one of 'U' or 'u', 'N' or 'n'. See above.
   * @param n the order of the matrix A. n>=0;
   * @param aMatrix of at least dimension  ( ( n*( n + 1 ) )/2 ). 
   * Before entry with UPLO = 'U' or 'u', the array aMatrix must contain the upper triangular part of the symmetric matrix
   * packed sequentially, column by column, so that aMatrix( 1 ) contains A( 1, 1 ), aMatrix( 2 ) and aMatrix( 3 ) contain A( 1, 2 )
   * and A( 2, 2 ) respectively, and so on.
   * Before entry with UPLO = 'L' or 'l', the array aMatrix must contain the lower triangular part of the symmetric matrix
   * packed sequentially, column by column, so that aMatrix( 1 ) contains A( 1, 1 ), aMatrix( 2 ) and aMatrix( 3 ) contain A( 2, 1 )
   * and A( 3, 1 ) respectively, and so on.
   * Note that when  DIAG = 'U' or 'u', the diagonal elements of A are not referenced either, but are assumed to be unity.
   * @param x a vector of minimum dimension (n-1) * |incx| + 1.
   * @param incx the increment between successive elements of 'x'
   * 
   * _Example_
   * Input:
   * uplo = 'U'
   * trans = 'N'
   * diag = 'N'
   * n = 5
   * aMatrix={1,2,7,3,8,13,9,14,19,15,20,25}
   * x = {1,2,3,4,5}
   * incx = 1;
   * 
   * Call:
   * dtpmv(uplo,trans,diag,n,aMatrix,x,incx)
   * 
   * Output
   * uplo = 'U'
   * trans = 'N'
   * diag = 'N'
   * n = 5
   * aMatrix={1,2,7,3,8,13,9,14,19,15,20,25}
   * x = {55,124,170,176,125.00}
   * incx = 1;
   * 
   */
  void dtpmv(char uplo, char trans, char diag, int n,  double[] aMatrix, double[] x, int incx);

  
  
  
  
  /**
   * 
   * Provides BLAS LEVEL 3: DGEMM
   * DGEMM performs the following matrix operations
   * 
   * C := alpha*_OP_(A)*_OP_(B) + beta*C
   * 
   * Where _OP_(X) is one of X, X^T. Where ^T denotes transpose.
   * If trans is one of 'N' or 'n', _OP_(X) = X
   * If trans is one of 'T' or 't', _OP_(X) = X^T
   * If trans is one of 'C' or 'c', _OP_(X) = X^T
   * 
   * @param transa one of 'N' or 'n', 'T' or 't', 'C' or 'c'. See above.
   * @param transb one of 'N' or 'n', 'T' or 't', 'C' or 'c'. See above.
   * @param m number of rows in matrix {@code _OP_(aMatrix)} and number of rows in {@code cMatrix}
   * @param n number of columns in matrix {@code _OP_(bMatrix) and number of columns in {@code cMatrix}
   * @param k number of columns in matrix {@code _OP_(aMatrix)} and number of rows in matrix {@code _OP_(bMatrix)}
   * @param alpha scaling factor for the matrix-matrix product 
   * @param aMatrix the leading part of the "A" matrix of at least dimension (LDA, ka), where ka is {@code k} when {@code transa} is 'N' or 'n' and is {@code m} otherwise.
   * @param lda the first dimension of {@code aMatrix}, if {@code transa} is 'N' or 'n' it is max(1,m) else it is at least max(1,k)
   * @param bMatrix the leading part of the "B" matrix of at least dimension (LDB, kb), where kb is {@code n} when {@code transb} is 'N' or 'n' and is {@code k} otherwise.
   * @param ldb the first dimension of {@code bMatrix}, if {@code transb} is 'N' or 'n' it is max(1,k) else it is at least max(1,n)
   * @param beta the scaling factor for the matrix "C", {@code cMatrix}
   * @param cMatrix the leading part of the "C" matrix of at least dimension (LDC, n). Overwritten by the operation defined in the preamble on exit. 
   * @param ldc the first dimension of "C" at least max(1,m)
   */
  void dgemm(char transa, char transb, int m, int n, int k, double alpha, double[] aMatrix, int lda, double[] bMatrix, int ldb, double beta, double[] cMatrix, int ldc);

}
