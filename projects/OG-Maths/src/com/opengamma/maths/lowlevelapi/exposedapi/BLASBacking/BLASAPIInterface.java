/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking;

import com.opengamma.maths.lowlevelapi.datatypes.primitive.DenseMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.BLAS2;

/**
 * Required behaviours of the BLAS interface, cut down version, may be expanded.
 * API is as close as possible to netlib.org's reference BLAS interface 
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
  void drotg(double a, double b, double c, double s);

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
  void drotmg(double dd1, double dd2, double dx1, double dy2, double[] dPARAM);
  
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
  * Provides BLAS LEVEL 2: DGEMV
  * DGEMV  performs one of the following matrix vector operations
  *
  *  y := alpha*A*x + beta*y OR y := alpha*A^T*x + beta*y,
  *
  *  where alpha and beta are scalars, x and y are vectors and A is an
  *  m by n matrix. The ^T indicates transposition.
  *
  *
  * DGEMV FULL: returns:= alpha*A*x + beta*y OR returns:=alpha*A^T*x + beta*y  depending on the enum orientation.
  * @param alpha a double indicating the scaling of A*x OR A^T*x
  * @param aMatrix a Matrix implementing the MatrixPrimitive interface
  * @param x a double[] vector
  * @param beta a double indicating the scaling of y
  * @param y a double[] vector
  * @param o orientation "normal" performs A*x, "transpose" performs A^T*x
  * @return tmp a double[] vector
  */
  double[] dgemv(double alpha, DenseMatrix aMatrix, double[] x, double beta, double[] y, BLAS2.orientation o);

}
