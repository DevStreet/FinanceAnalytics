/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.IZYBacking;

/**
 * Interface to the IZY library.
 * The IZY library provides a vectorised interface to many common mathematical functions.
 * 
 * Note on complex number representation:
 * 
 * 
 * Methods declared to take Double Precision Complex have their double precision complex valued arrays passed as double[] due to the lack
 * of a native complex number representation in java. Classes implementing from this interface expect double precision complex values
 * to be packed into a double[] as alternating double precision real values and double precision complex values.
 * 
 *  
 * For example take complex numbers X and Y, and let the notation rX indicate the real part of X and iX the imaginary part, the valid packing 
 * would then take the form: 
 * double[] foo = {rX, iX, rY, iY}
 * The reason for this packing is down to internal representation of complex data structures in native libraries.
 */
//CSOFF // to match C
public interface IZYAPIInterface {

  /**
   * Double Precision Vectorised y:=acos(a).
   * Computes the inverse cosine of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_acos(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=asin(a).
   * Computes the inverse sine of {@code a} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_asin(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=atan(a).
   * Computes the inverse tangent of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_atan(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=atan2(a, b).
   * Computes the principal values of the inverse tangent of a/b and further uses the signs given in the two arguments to determine the quandrant in which the result lies.
   * See also {@link Math}.atan2()
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place 
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_atan2(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=cos(a).
   * Computes the cosine of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_cos(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=sin(a).
   * Computes the sine of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_sin(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised: {y,x}:=sincos(a)
   * Computes the sine and cosine of {@code a} simultaneously, c.f.  {y,x}={sin(a),cos(a)}.
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result {@code sin(a)} shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   * @param x the double precision vector in which the result {@code cos(a)} shall be placed
   * @param offsetx the first element in this array denotes the position in {@code x} at which the result shall be placed
   */
  void vd_sincos(int[] n, double[] a, int[] offseta, double[] y, int[] offsety, double[] x, int[] offsetx);

  /**
   * Double Precision Vectorised y:=tan(a).
   * Computes the tangent of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_tan(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=acosh(a).
   * Computes the inverse hyperbolic cosine (acosh) of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_acosh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=asinh(a).
   * Computes the inverse hyperbolic sine (asinh) of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_asinh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=atanh(a).
   * Computes the inverse hyperbolic tangent (atanh) of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_atanh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=cosh(a).
   * Computes the hyperbolic cosine (cosh) of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_cosh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=sinh(a).
   * Computes the hyperbolic sine (sinh) of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_sinh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=tanh(a).
   * Computes the hyperbolic tangent (tanh) of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_tanh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=crbt(a).
   * Computes the cube root of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_cbrt(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=hypot(a, b).
   * Computes the Eucliedean distance between {@code a} and {@code b}, this is the equivalent to y:=sqrt(a*a+b*b)
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_hypot(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=inv(a).
   * Computes the reciprocal of {@code a}, the equivalent of {@code 1/a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_inv(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=invcbrt(a).
   * Computes the reciprocal of the cube root of {@code a}, the equivalent of {@code 1/cbrt(a)}.
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_invcbrt(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=invsqrt(a).
   * Computes the reciprocal of the square root of {@code a}, the equivalent of {@code 1/sqrt(a)}.
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_invsqrt(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=pow(a,b).
   * Computes {@code a} raised to the power of {@code b}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_pow(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=powx(a, constant b).
   * Computes {@code a} raised to the power of a constant {@code b}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector from which the constant will be taken
   * @param offsetb the first element in this array denotes position in {@code b} from which the constant shall be taken
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_powx(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=pow2o3(a).
   * Computes the {@code a} raised to the power 2/3
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_pow2o3(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=pow3o2(a).
   * Computes the {@code a} raised to the power 3/2
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_pow3o2(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=sqrt(a).
   * Computes the square root of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_sqrt(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=exp(a).
   * Computes the base 'e' (natural base) exponential of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_exp(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=expm1(a).
   * Computes the base 'e' (natural base) exponential of {@code a} minus 1.
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_expm1(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=ln(a).
   * Computes the base 'e' (natural base) logarithm of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_ln(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=log10(a).
   * Computes the base 10 logarithm of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_log10(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=log1p(a).
   * Computes the base 'e' (natural) logarithm of ({@code a} + 1), this is equivalent to ln(a+1)
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_log1p(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=abs(a).
   * Computes the absolute value of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_abs(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=a+b.
   * Computes the arithmetic sum of {@code a} and {@code b} such that y:=a+b
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_add(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=a+(constant)b.
   * Computes the arithmetic sum of {@code a} and a constant {@code b} such that y:=a+(constant)b
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector from which the constant will be taken
   * @param offsetb the first element in this array denotes position in {@code b} from which the constant shall be taken
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_addx(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=a/b.
   * Computes the arithmetic division of {@code a} by {@code b} such that y:=a/b
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_div(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=a/(constant)b.
   * Computes the arithmetic division of {@code a} by a constant {@code b} such that y:=a/(constant) b
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector from which the constant will be taken
   * @param offsetb the first element in this array denotes position in {@code b} from which the constant shall be taken
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_divx(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=(constant)a/b.
   * Computes the arithmetic division of a constant {@code a} by a vector {@code b} such that y:=(constant)a/b
   * @param n number of elements of {@code b} to process
   * @param a the double precision vector from which the constant will be taken
   * @param offseta the first element in this array denotes position in {@code a} from which the constant shall be taken
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_xdiv(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  
  /**
   * Double Precision Vectorised y:=a*b.
   * Computes the arithmetic multiplication of {@code a} by {@code b} such that y:=a*b
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_mul(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=a*(constant)b.
   * Computes the arithmetic multiplication of {@code a} by a constant {@code b} such that y:=a*(constant) b
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector from which the constant will be taken
   * @param offsetb the first element in this array denotes position in {@code b} from which the constant shall be taken
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_mulx(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=sqr(a).
   * Computes the square of {@code a}, this is the equivalent of y:=a*a
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_sqr(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=a-b.
   * Computes the arithmetic subtraction of {@code b} from {@code a} such that y:=a-b
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_sub(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=a-(constant)b.
   * Computes the arithmetic subtraction of a constant {@code b} from {@code a} such that y:=a-(constant)b
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector from which the constant will be taken
   * @param offsetb the first element in this array denotes position in {@code b} from which the constant shall be taken
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_subx(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=(constant)a-b.
   * Computes the arithmetic subtraction of {@code b} from a constant {@code a} such that y:=(constant)a-b
   * @param n number of elements of {@code b} to process
   * @param a the double precision vector from which the constant will be taken
   * @param offseta the first element in this array denotes position in {@code a} from which the constant shall be taken
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_xsub(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);  

  /**
   * Double Precision Vectorised y:=-a.
   * Computes the negation of {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_negate(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);
  
  /**
   * Double Precision Vectorised y:=ceil(a).
   * Computes the ceiling of {@code a} (the smallest integer value more positive than a)
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_ceil(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=floor(a).
   * Computes the ceiling of {@code a} (the largest integer value less positive than a)
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_floor(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised: {y,x}:=modf(a).
   * Computes the signed integral and fractional values of {@code a} returning the integral part in {@code y} and the fractional part in {@code x} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the integral part of {@code a} shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   * @param x the double precision vector in which the fractional part of {@code a} shall be placed
   * @param offsetx the first element in this array denotes the position in {@code x} at which the result shall be placed
   */
  void vd_modf(int[] n, double[] a, int[] offseta, double[] y, int[] offsety, double[] x, int[] offsetx);

  /**
   * Double Precision Vectorised: y:=nearbyint(a).
   * Computes the nearest integer to {@code a} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_nearbyint(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised: y:=rint(a).
   * Computes the rounded integer value of {@code a}. In java this does the same as nearbyint() as there is no capacity for handling the signalled exception.
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_rint(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised: y:=round(a).
   * Computes the rounded integer value of {@code a} with tied cases rounding away from zero.
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_round(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised: y:=trunc(a).
   * Computes the rounded integer value of {@code a} such that the rounded value is not larger in magnitude than {@code a} (essentially round towards zero)
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_trunc(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * NOT IMPLEMENTED YET.
   */
  void vd_cdfnorm(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * NOT IMPLEMENTED YET.
   */
  void vd_cdfnorminv(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised: y:=erf(a).
   * Computes the value of the error function at {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_erf(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised: y:=erfc(a).
   * Computes the value of the complimentary error function at {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_erfc(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * NOT IMPLEMENTED YET.
   */
  void vd_erfinv(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * NOT IMPLEMENTED YET.
   */
  void vd_erfcinv(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised: y:=lgamma(a).
   * Computes the value of the base 'e' (natural) logarithm of the gamma function at {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_lgamma(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised: y:=tgamma(a).
   * Computes the value of the gamma function at {@code a}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vd_tgamma(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=abs(a).
   * Computes the absolute value of the complex number {@code a}, this is the equivalent of y:=sqrt(real(a)*real(a)+imag(a)*imag(a))
   * See note on complex number representation {@link IZYAPIInterface}
   * See also {@code vd_hypot}
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_abs(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=acos(a).
   * Computes the inverse cosine of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_acos(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=acosh(a).
   * Computes the inverse hyperbolic cosine of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_acosh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=a+b.
   * Computes the complex arithmetic sum of {@code a} and {@code b} such that y:=a+b
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision complex representation vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_add(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=a+(constant)b.
   * Computes the complex arithmetic sum of {@code a} and a constant {@code b} such that y:=a+(constant)b
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector from which the constant will be taken
   * @param offsetb the first element in this array denotes position in {@code b} from which the constant shall be taken
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_addx(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=arg(a).
   * Computes the argument (phase angle) of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_arg(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=asin(a).
   * Computes the inverse sine of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_asin(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=asinh(a).
   * Computes the inverse hyperbolic sine of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_asinh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=atan(a).
   * Computes the inverse tangent of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_atan(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=atanh(a).
   * Computes the inverse hyperbolic tangent of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_atanh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=conj(a).
   * Computes the complex conjugate of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_conj(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=cos(a).
   * Computes the cosine of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_cos(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=cosh(a).
   * Computes the hyperbolic cosine of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_cosh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=a/b.
   * Computes the complex arithmetic division of {@code a} by {@code b} such that y:=a/b
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision complex representation vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_div(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=a/(constant)b.
   * Computes the complex arithmetic division of {@code a} by a constant {@code b} such that y:=a/(constant)b
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector from which the constant will be taken
   * @param offsetb the first element in this array denotes position in {@code b} from which the constant shall be taken
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_divx(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=(constant)a/b.
   * Computes the complex arithmetic division of a constant {@code a} by {@code b} such that y:=(constant)a/b
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code b} to process
   * @param a the double precision vector from which the constant will be taken
   * @param offseta the first element in this array denotes position in {@code a} from which the constant shall be taken
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place 
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_xdiv(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);  
  
  /**
   * Double Precision Complex Vectorised y:=exp(a).
   * Computes the complex base 'e' (natural base) exponential of {@code a}
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_exp(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=ln(a).
   * Computes the complex base 'e' (natural base) logarithm of {@code a}
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_ln(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=log10(a).
   * Computes the complex base 10 logarithm of {@code a}
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_log10(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=a*b.
   * Computes the complex arithmetic multiplication of {@code a} by {@code b} such that y:=a*b
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision complex representation vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_mul(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=a*(constant)b.
   * Computes the complex arithmetic multiplication of {@code a} by a constant {@code b} such that y:=a*(constant)b
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector from which the constant will be taken
   * @param offsetb the first element in this array denotes position in {@code b} from which the constant shall be taken
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_mulx(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=a*conj(b).
   * Computes the complex arithmetic multiplication of {@code a} by the complex conjugate of {@code b} such that y:=a*conj(b)
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector from which the constant will be taken
   * @param offsetb the first element in this array denotes position in {@code b} from which the constant shall be taken
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_mulbyconj(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  
  /**
   * Double Precision Complex Vectorised y:=-a.
   * Computes complex negation of {@code a}. Both real and imaginary parts of {@code a} are negated
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_negate(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);
  
  /**
   * Double Precision Complex Vectorised y:=-real(a)+imag(a).
   * Computes complex negation of the real part of {@code a}, the imaginary part is ignored.
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_negatereal(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);
  
  /**
   * Double Precision Complex Vectorised y:=pow(a,b).
   * Computes complex {@code a} raised to the power of complex {@code b}
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_pow(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Vectorised y:=powx(a, constant b).
   * Computes complex {@code a} raised to the power of a constant complex {@code b}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector from which the constant will be taken
   * @param offsetb the first element in this array denotes position in {@code b} from which the constant shall be taken
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_powx(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=sin(a).
   * Computes the sine of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_sin(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=sinh(a).
   * Computes the hyperbolic sine of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_sinh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=sqrt(a).
   * Computes the square root of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_sqrt(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=a-b.
   * Computes the complex arithmetic subtraction of {@code b} from {@code a} such that y:=a-b
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision complex representation vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_sub(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=a-(constant)b.
   * Computes the complex arithmetic subtraction of a constant {@code b} from {@code a} such that y:=a-(constant)b
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code a} to process
   * @param a the double precision vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param b the double precision vector from which the constant will be taken
   * @param offsetb the first element in this array denotes position in {@code b} from which the constant shall be taken
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_subx(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised y:=(constant)a-b.
   * Computes the complex arithmetic subtraction of {@code b} from a constant {@code a} such that y:=(constant)a-b
   * See note on complex number representation {@link IZYAPIInterface}
   * @param n number of elements of {@code b} to process
   * @param a the double precision vector from which the constant will be taken
   * @param offseta the first element in this array denotes position in {@code a} from which the constant shall be taken
   * @param b the double precision vector on which the operation shall be performed
   * @param offsetb the first element in this array denotes position in {@code b} from which the operations shall take place 
   * @param y the double precision vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_xsub(int[] n, double[] a, int[] offseta, double[] b, int[] offsetb, double[] y, int[] offsety);  
  
  /**
   * Double Precision Complex Vectorised: y:=tan(a).
   * Computes the tangent of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_tan(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);

  /**
   * Double Precision Complex Vectorised: y:=tanh(a).
   * Computes the hyperbolic tangent of the complex number {@code a}
   * See note on complex number representation {@link IZYAPIInterface} 
   * @param n number of elements of {@code a} to process
   * @param a the double precision complex representation vector on which the operation shall be performed
   * @param offseta the first element in this array denotes position in {@code a} from which the operations shall take place
   * @param y the double precision complex representation vector in which the result shall be placed
   * @param offsety the first element in this array denotes the position in {@code y} at which the result shall be placed
   */
  void vz_tanh(int[] n, double[] a, int[] offseta, double[] y, int[] offsety);
}
