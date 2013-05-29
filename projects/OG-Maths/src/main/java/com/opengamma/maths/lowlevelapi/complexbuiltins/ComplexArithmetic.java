/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.complexbuiltins;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.commonapi.MathsConstants;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.functions.MathBits;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.ZABS;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.ZACOS;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.ZASIN;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.ZATAN;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.ZSQRT;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.ZTAN;

/**
 * These are equivalent to the built in functions in other languages for handling complex arithmetic
 * There is no safety, the onus is on the user to ensure inputs are correctly handled (typical uses are for inlined calls)
 */
public class ComplexArithmetic {
  private static final double s_sqeps = Math.sqrt(D1mach.four());
  private static Logger s_log = LoggerFactory.getLogger(ComplexArithmetic.class);

  /**
  * Complex addition 
  * @param arg0 the first complex number
  * @param arg1 the second complex number
  * @return the sum arg0+arg1 
  */
  public static double[] cadd(double[] arg0, double[] arg1) {
    double[] tmp = new double[2];
    caddInline(arg0, 0, arg1, 0, tmp, 0);
    return tmp;
  }

  /**
   * Complex inlined addition, use to prevent memcpy
   * @param arg0 the first complex number
   * @param offsetarg0 the offset into the 'arg0' vector at which the value shall be accessed, this value assumes 128 bit wide strides
   * @param arg1 the second complex number
   * @param offsetarg1 the offset into the 'arg1' vector at which the value shall be accessed, this value assumes 128 bit wide strides
   * @param result the sum arg0+arg1 
   * @param offsetresult the offset into the 'result' vector at which the result shall be placed, this value assumes 128 bit wide strides
   */
  public static void caddInline(double[] arg0, int offsetarg0, double[] arg1, int offsetarg1, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE 
    final int offsetarg0128_0 = 2 * offsetarg0; //CSIGNORE
    final int offsetarg0128_1 = 2 * offsetarg0 + 1; //CSIGNORE 
    final int offsetarg1128_0 = 2 * offsetarg1; //CSIGNORE
    final int offsetarg1128_1 = 2 * offsetarg1 + 1; //CSIGNORE
    result[offsetresult128_0] = arg0[offsetarg0128_0] + arg1[offsetarg1128_0];
    result[offsetresult128_1] = arg0[offsetarg0128_1] + arg1[offsetarg1128_1];
  }

  /**
  * Complex subtraction 
  * @param arg0 the first complex number
  * @param arg1 the second complex number
  * @return the sum arg0+arg1 
  */
  public static double[] csubtract(double[] arg0, double[] arg1) {
    double[] tmp = new double[2];
    csubtractInline(arg0, 0, arg1, 0, tmp, 0);
    return tmp;
  }

  /**
   * Complex inlined subtraction, use to prevent memcpy
   * @param arg0 the first complex number
   * @param offsetarg0 the offset into the 'arg0' vector at which the value shall be accessed, this value assumes 128 bit wide strides
   * @param arg1 the second complex number
   * @param offsetarg1 the offset into the 'arg1' vector at which the value shall be accessed, this value assumes 128 bit wide strides
   * @param result the sum arg0+arg1 
   * @param offsetresult the offset into the 'result' vector at which the result shall be placed, this value assumes 128 bit wide strides
   */
  public static void csubtractInline(double[] arg0, int offsetarg0, double[] arg1, int offsetarg1, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE 
    final int offsetarg0128_0 = 2 * offsetarg0; //CSIGNORE
    final int offsetarg0128_1 = 2 * offsetarg0 + 1; //CSIGNORE 
    final int offsetarg1128_0 = 2 * offsetarg1; //CSIGNORE
    final int offsetarg1128_1 = 2 * offsetarg1 + 1; //CSIGNORE
    result[offsetresult128_0] = arg0[offsetarg0128_0] - arg1[offsetarg1128_0];
    result[offsetresult128_1] = arg0[offsetarg0128_1] - arg1[offsetarg1128_1];
  }

  /**
  * Complex division 
  * @param arg0 the numerator
  * @param arg1 the denominator
  * @return arg0/arg1 the quotient
  */
  public static double[] cdivide(double[] arg0, double[] arg1) {
    double[] tmp = new double[2];
    cdivideInline(arg0, 0, arg1, 0, tmp, 0);
    return tmp;
  }

  /**
   * Complex inlined division, use to prevent memcpy
   * @param arg0 the numerator
   * @param offsetarg0 the offset into the 'arg0' vector at which the value shall be accessed, this value assumes 128 bit wide strides
   * @param arg1 the denominator
   * @param offsetarg1 the offset into the 'arg1' vector at which the value shall be accessed, this value assumes 128 bit wide strides
   * @param result arg0/arg1 the quotient
   * @param offsetresult the offset into the 'result' vector at which the result shall be placed, this value assumes 128 bit wide strides
   */
  // The obvious a+bi/c+di = (ac+bd)/(cc+dd) + ((bc-ad)/(cc+dd))i
  // is fine so long as cc+dd isn't large.
  // We employ the formulation suggested by R. L. Smith to offer some protection against
  // overflow, note that improved variants are available if more is known about the hardware.
  // See  R.L. Smith. Algorithm 116: Complex division. Communications of the ACM,
  // 5(8):435, 1962.
  public static void cdivideInline(double[] arg0, int offsetarg0, double[] arg1, int offsetarg1, double[] result, int offsetresult) {
    double a, b, c, d, btm, div;
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE 
    final int offsetarg0128_0 = 2 * offsetarg0; //CSIGNORE
    final int offsetarg0128_1 = 2 * offsetarg0 + 1; //CSIGNORE 
    final int offsetarg1128_0 = 2 * offsetarg1; //CSIGNORE
    final int offsetarg1128_1 = 2 * offsetarg1 + 1; //CSIGNORE
    a = arg0[offsetarg0128_0];
    b = arg0[offsetarg0128_1];
    c = arg1[offsetarg1128_0];
    d = arg1[offsetarg1128_1];

    if (Math.abs(d) < Math.abs(c)) {
      div = d / c;
      btm = c + d * div;
      result[offsetresult128_0] = (a + b * div) / btm;
      result[offsetresult128_1] = (b - a * div) / btm;
    } else {
      div = c / d;
      btm = d + c * div;
      result[offsetresult128_0] = (b + a * div) / btm;
      result[offsetresult128_1] = (-a + b * div) / btm;
    }
  }

  /**
   * Complex multiplication
   * @param arg0 the first arg to multiply
   * @param arg1 the second arg to multiply
   * @return the product of (arg0, arg1)
   */
  public static double[] cmultiply(double[] arg0, double[] arg1) {
    double[] ret = new double[2];
    cmultiplyInline(arg0, 0, arg1, 0, ret, 0);
    return ret;
  }

  /**
   * Complex inlined multiply, use to prevent memcpy
   * @param arg0 the first arg to multiply
   * @param offsetarg0 the offset into the 'arg0' vector at which the value shall be accessed, this value assumes 128 bit wide strides
   * @param arg1 the second arg to multiply
   * @param offsetarg1 the offset into the 'arg1' vector at which the value shall be accessed, this value assumes 128 bit wide strides
   * @param result the product(arg0, arg1) 
   * @param offsetresult the offset into the 'result' vector at which the result shall be placed, this value assumes 128 bit wide strides
   */
  public static void cmultiplyInline(double[] arg0, int offsetarg0, double[] arg1, int offsetarg1, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetarg0128_0 = 2 * offsetarg0; //CSIGNORE
    final int offsetarg0128_1 = 2 * offsetarg0 + 1; //CSIGNORE
    final int offsetarg1128_0 = 2 * offsetarg1; //CSIGNORE
    final int offsetarg1128_1 = 2 * offsetarg1 + 1; //CSIGNORE
    double a, b, c, d;
    a = arg0[offsetarg0128_0];
    b = arg0[offsetarg0128_1];
    c = arg1[offsetarg1128_0];
    d = arg1[offsetarg1128_1];
    result[offsetresult128_0] = a * c - b * d;
    result[offsetresult128_1] = b * c + a * d;
  }

  /**
   * Complex inlined multiply, use to prevent memcpy
   * This prototype allows the swapping/messing with the inputs on the way in which is useful for some internal things.
   * Essentially does (a + bi)*(c + di)
   * @param a see above
   * @param b see above
   * @param c see above
   * @param d see above
   * @param result the product (a + bi)*(c + di)
   * @param offsetresult the offset into the 'result' vector at which the result shall be placed, this value assumes 128 bit wide strides
   */
  public static void cmultiplyInline(double a, double b, double c, double d, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    result[offsetresult128_0] = a * c - b * d;
    result[offsetresult128_1] = b * c + a * d;
  }

  /**
   * Computes the hyperbolic cosine of a complex value
   * @param x the value of which to compute the hyperbolic cosine, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the hyperbolic cosine, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the hyperbolic cosine of 'x', this value assumes 128 bit wide strides
   */
  public static void ccoshInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetx2 = 2 * offsetx;
    double realpart = x[offsetx2];
    double imagpart = x[1 + offsetx2];
    double sinimag, cosimag;
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    boolean infreal = Double.isInfinite(realpart);
    boolean infimag = Double.isInfinite(imagpart);

    if (infreal || infimag) {
      if (infreal && !infimag) { // {inf,finite}
        result[offsetresult128_0] = realpart;
        result[offsetresult128_1] = Math.copySign(realpart, imagpart);
      } else {
        result[offsetresult128_0] = MathBits.getSignallingNaN();
        result[offsetresult128_1] = MathBits.getSignallingNaN();
      }
      return;
    }

    if (Math.abs(imagpart) < MathBits.getMinimumPositiveNormal()) {
      cosimag = 1.d;
      sinimag = imagpart;
    } else {
      cosimag = Math.cos(imagpart);
      sinimag = Math.sin(imagpart);
    }
    result[offsetresult128_0] = Math.cosh(realpart) * cosimag;
    result[offsetresult128_1] = Math.sinh(realpart) * sinimag;
  }

  /**
   * Computes the hyperbolic cosine of a complex value
   * @param x the value of which to compute the hyperbolic cosine, packed as x[0] = real part, x[1] = complex part.
   * @return the value of the hyperbolic cosine of {@code x} packed as return[0] = real part, return[1] = complex part.
   */
  public static double[] ccosh(double[] x) {
    double[] res = new double[2];
    ccoshInline(x, 0, res, 0);
    return res;
  }

  /**
   * Computes the hyperbolic sine of a complex value
   * @param x the value of which to compute the hyperbolic sine, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the hyperbolic sine, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the hyperbolic sine of 'x', this value assumes 128 bit wide strides
   */
  public static void csinhInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetx2 = 2 * offsetx;
    double realpart = x[offsetx2];
    double imagpart = x[1 + offsetx2];
    double sinimag, cosimag;
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    boolean infreal = Double.isInfinite(realpart);
    boolean infimag = Double.isInfinite(imagpart);

    if (infreal || infimag) {
      if (infreal && !infimag) { // {inf,finite}
        result[offsetresult128_0] = realpart;
        result[offsetresult128_1] = Math.copySign(realpart, imagpart);
      } else {
        result[offsetresult128_0] = MathBits.getSignallingNaN();
        result[offsetresult128_1] = MathBits.getSignallingNaN();
      }
      return;
    }

    if (Math.abs(imagpart) < MathBits.getMinimumPositiveNormal()) {
      cosimag = 1.d;
      sinimag = imagpart;
    } else {
      cosimag = Math.cos(imagpart);
      sinimag = Math.sin(imagpart);
    }
    result[offsetresult128_0] = Math.sinh(realpart) * cosimag;
    result[offsetresult128_1] = Math.cosh(realpart) * sinimag;
  }

  /**
   * Computes the hyperbolic sine of a complex value
   * @param x the value of which to compute the hyperbolic sine, packed as x[0] = real part, x[1] = complex part.
   * @return the value of the hyperbolic sine of {@code x} packed as return[0] = real part, return[1] = complex part.
   */
  public static double[] csinh(double[] x) {
    double[] res = new double[2];
    csinhInline(x, 0, res, 0);
    return res;
  }

  /**
   * Computes the hyperbolic tangent of a complex value
   * @param x the value of which to compute the hyperbolic tangent, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the hyperbolic tangent, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the hyperbolic tangent of 'x', this value assumes 128 bit wide strides
   */
  public static void ctanhInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetx2 = 2 * offsetx;
    double x2 = 2. * x[offsetx2];
    double y2 = 2. * x[offsetx2 + 1];
    double btm = Math.cos(y2) + Math.cosh(x2);

    if (Math.abs(btm) <= Math.max(Math.abs(y2), 1) * s_sqeps) {
      s_log.warn("complex tanh: Answer is to less than half precision, abs(imaginary part) is too big or imaginar part is too near pi or 0");
    }
    result[offsetresult128_0] = Math.sinh(x2) / btm;
    result[offsetresult128_1] = Math.sin(y2) / btm;
  }

  /**
   * Computes the hyperbolic tangent of a complex value
   * @param x the value of which to compute the hyperbolic tangent, packed as x[0] = real part, x[1] = complex part.
   * @return the value of the hyperbolic tangent of {@code x} packed as return[0] = real part, return[1] = complex part.
   */
  public static double[] ctanh(double[] x) {
    double[] res = new double[2];
    ctanhInline(x, 0, res, 0);
    return res;
  }

  /**
   * Computes the cosine of a complex value
   * @param x the value of which to compute the cosine packed, as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the cosine, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the cosine of 'x', this value assumes 128 bit wide strides
   */
  public static void ccosInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetx2 = 2 * offsetx;
    double[] rewrite = {-x[offsetx2 + 1], x[offsetx2 + 0] };
    ccoshInline(rewrite, 0, result, offsetresult);
  }

  /**
   * Computes the complex cosine of a complex value
   * @param x the value of which to compute the complex cosine, packed as x[0] = real part, x[1] = complex part.
   * @return the  complex cosine of a value
   */
  public static double[] ccos(double[] x) {
    double[] rewrite = {-x[1], x[0] };
    return ccosh(rewrite);
  }

  /**
   * Computes the sine of a complex value
   * @param x the value of which to compute the sine, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the sine, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the sine of 'x', this value assumes 128 bit wide strides
   */
  // csin = csinh(-imag,real)/i = sin(real)cosh(imag) + i * cos(real)sinh(imag)
  // writing in terms of a sinh with swapped args and then cmplx divide is probably slower
  public static void csinInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetx2 = 2 * offsetx;
    double realpart = x[offsetx2];
    double imagpart = x[1 + offsetx2];
    double sinreal, cosreal;
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    boolean infreal = Double.isInfinite(realpart);
    boolean infimag = Double.isInfinite(imagpart);

    if (infreal || infimag) {
      if (infreal && !infimag) { // {inf,finite}
        result[offsetresult128_0] = realpart;
        result[offsetresult128_1] = Math.copySign(realpart, imagpart);
      } else {
        result[offsetresult128_0] = MathBits.getSignallingNaN();
        result[offsetresult128_1] = MathBits.getSignallingNaN();
      }
      return;
    }

    if (Math.abs(imagpart) < MathBits.getMinimumPositiveNormal()) {
      cosreal = 1.d;
      sinreal = imagpart;
    } else {
      cosreal = Math.cos(realpart);
      sinreal = Math.sin(realpart);
    }
    result[offsetresult128_0] = Math.cosh(imagpart) * sinreal;
    result[offsetresult128_1] = Math.sinh(imagpart) * cosreal;
  }

  /**
   * Computes the sine of a complex value
   * @param x the value of which to compute the sine, packed as x[0] = real part, x[1] = complex part.
   * @return the  sine of a complex value
   */
  public static double[] csin(double[] x) {
    double[] res = new double[2];
    csinInline(x, 0, res, 0);
    return res;
  }

  /**
   * Computes the tangent of a complex value
   * @param x the value of which to compute the tangent, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the tangent, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the tangent of 'x', this value assumes 128 bit wide strides
   * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
   * http://www.netlib.org/slatec/src/ctan.f
   */
  public static void ctanInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetx2 = 2 * offsetx;
    double x2 = 2. * x[offsetx2];
    double y2 = 2. * x[offsetx2 + 1];
    double sn2x = Math.sin(x2);
    double den = Math.cos(x2) + Math.cosh(y2);
    if (den == 0) {
      throw new MathsExceptionIllegalArgument("Tan() is singular (x is pi/2 or 3*pi/2 and y is 0");
    }
    if (Math.abs(den) <= Math.max(Math.abs(x2), 1) * s_sqeps) {
      s_log.warn("ctanInline: Answer is to less than half precision, abs(x) is too big or x is too near pi/2 or 3*pi/2");
    }
    result[offsetresult128_0] = sn2x / den;
    result[offsetresult128_1] = Math.sinh(y2) / den;
  }

  /**
   * Computes the tangent of a complex value
   * @param x the value of which to compute the tangent, packed as x[0] = real part, x[1] = complex part.
   * @return the  tangent of a complex value
   */
  public static double[] ctan(double[] x) {
    return ZTAN.ztan(x);
  }

  /**
   * Computes the inverse tangent of a complex value
   * @param x the value of which to compute the inverse tangent, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the inverse tangent, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the inverse tangent of 'x', this value assumes 128 bit wide strides
   */
  //TODO: inline this properly
  public static void catanInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetx2 = 2 * offsetx;
    double[] ret;
    double[] inpt = new double[] {x[offsetx2], x[offsetx2 + 1] };
    try {
      ret = ZATAN.zatan(inpt);
    } catch (MathsExceptionIllegalArgument e) { // goes to inf
      ret = new double[] {MathBits.getSignallingNaN(), Math.copySign(MathBits.getPositiveInfinity(), inpt[1]) };
    }
    result[offsetresult128_0] = ret[0];
    result[offsetresult128_1] = ret[1];
  }

  /**
   * Computes the inverse tangent of a complex value
   * @param x the value of which to compute the inverse tangent, packed as x[0] = real part, x[1] = complex part.
   * @return the inverse tangent of a complex value
   */
  public static double[] catan(double[] x) {
    return ZATAN.zatan(x);
  }

  /**
   * Computes the inverse sine of a complex value
   * @param x the value of which to compute the inverse sine, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the inverse sine, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the inverse sine of 'x', this value assumes 128 bit wide strides
   */
  public static void casinInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetx2 = 2 * offsetx;
    double[] ret;
    double[] inpt = new double[] {x[offsetx2], x[offsetx2 + 1] };
    ret = ZASIN.zasin(inpt);
    result[offsetresult128_0] = ret[0];
    result[offsetresult128_1] = ret[1];
  }

  /**
   * Computes the inverse sine of a complex value
   * @param x the value of which to compute the inverse sine, packed as x[0] = real part, x[1] = complex part.
   * @return the inverse sine of a complex value
   */
  public static double[] casin(double[] x) {
    return ZASIN.zasin(x);
  }

  /**
   * Computes the inverse cosine of a complex value
   * @param x the value of which to compute the inverse cosine, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the inverse cosine, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the inverse cosine of 'x', this value assumes 128 bit wide strides
   */
  public static void cacosInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetx2 = 2 * offsetx;
    double[] ret;
    double[] inpt = new double[] {x[offsetx2], x[offsetx2 + 1] };
    ret = ZACOS.zacos(inpt);
    result[offsetresult128_0] = ret[0];
    result[offsetresult128_1] = ret[1];
  }

  /**
   * Computes the inverse cosine of a complex value
   * @param x the value of which to compute the inverse cosine, packed as x[0] = real part, x[1] = complex part.
   * @return the inverse cosine of a complex value
   */
  public static double[] cacos(double[] x) {
    return ZACOS.zacos(x);
  }

  /**
   * Computes the square root of a complex value
   * @param x the value of which to compute the square root, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the square root, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the square root of 'x', this value assumes 128 bit wide strides
   * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
   * See http://www.netlib.org/slatec/fnlib/zsqrt.f
   */
  public static void csqrtInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetx2 = 2 * offsetx;
    double drt = 7.071067811865475244008443621e-1;

    double ar = x[offsetx2];
    double ai = x[offsetx2 + 1];
    double br = 0;
    double bi = 0;
    double zm = ZABS.zabs(ar, ai);
    double dtheta = 0;
    zm = Math.sqrt(zm);
    if (ar == 0.d) {
      if (ai > 0.d) {
        br = zm * drt;
        bi = zm * drt;
      } else if (ai < 0.d) {
        br = zm * drt;
        bi = -zm * drt;
      }
      result[offsetresult128_0] = br;
      result[offsetresult128_1] = bi;
      return;
    } else if (ai == 0.d) {
      if (ar > 0.d) {
        br = Math.sqrt(ar);
      } else {
        bi = Math.sqrt(Math.abs(ar));
      }
      result[offsetresult128_0] = br;
      result[offsetresult128_1] = bi;
      return;
    } else {
      dtheta = Math.atan(ai / ar);
      if (dtheta < 0.d) {
        if (ar < 0.d) {
          dtheta += MathsConstants.pi;
        }
      } else {
        if (ar < 0.d) {
          dtheta -= MathsConstants.pi;
        }
      }
    }
    dtheta /= 2d;
    br = zm * Math.cos(dtheta);
    bi = zm * Math.sin(dtheta);
    result[offsetresult128_0] = br;
    result[offsetresult128_1] = bi;
    return;
  }

  /**
   * Computes the square root of a complex value
   * @param x the value of which to compute the square root, packed as x[0] = real part, x[1] = complex part.
   * @return the square root of a complex value
   */
  public static double[] csqrt(double[] x) {
    return ZSQRT.zsqrt(x);
  }

  /**
   * Computes the base 'e' (natural) logarithm of a complex value
   * @param x the value of which to compute the base 'e' (natural) logarithm, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the base 'e' (natural) logarithm, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the base 'e' (natural) logarithm of 'x', this value assumes 128 bit wide strides
   */
  public static void clogInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetx2 = 2 * offsetx;
    double[] ret;
    double[] inpt = new double[] {x[offsetx2], x[offsetx2 + 1] };
    ret = clog(inpt);
    result[offsetresult128_0] = ret[0];
    result[offsetresult128_1] = ret[1];
  }

  /**
   * Computes the base 'e' (natural) logarithm of a complex value
   * @param x the value of which to compute the base 'e' (natural) logarithm, packed as x[0] = real part, x[1] = complex part.
   * @return the base 'e' (natural) logarithm of a complex value
   * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
   * http://www.netlib.org/slatec/src/zlog.f
   */
  public static double[] clog(double[] x) {
    double zm;
    double dhpi = MathsConstants.halfpi;
    double dpi = MathsConstants.pi;
    double dtheta;
    double ar = x[0];
    double ai = x[1];
    double[] ret = new double[2];
    if (ar == 0) {
      if (ai == 0) {
        ret[0] = MathBits.getNegativeInfinity(); // variation from SLATEC
        return ret;
      } else {
        ret[1] = dhpi;
        ret[0] = Math.log(Math.abs(ai));
        if (ai < 0) {
          ret[1] = -ret[1];
        }
        return ret;
      }
    } else if (ai == 0) {
      if (ar > 0.0) {
        ret[0] = Math.log(ar);
        ret[1] = 0.d;
        return ret;
      } else {
        ret[0] = Math.log(Math.abs(ar));
        ret[1] = dpi;
        return ret;
      }
    } else {
      dtheta = Math.atan(ai / ar);
      if (dtheta <= 0d) { // this double branch is right, just looks odd from flow indirection produced by GOTO
        if (ar < 0d) {
          dtheta = dtheta + dpi;
        }
      } else {
        if (ar < 0d) {
          dtheta = dtheta - dpi;
        }
      }
      zm = ZABS.zabs(ar, ai);
      ret[0] = Math.log(zm);
      ret[1] = dtheta;
      return ret;
    }
  }

  /**
   * Computes the base 'e' natural exponential of a complex value
   * @param x the value of which to compute the base 'e' (natural) exponential, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the base 'e' (natural) exponential, this value assumes 128 bit wide strides
   * @param result the result
   * @param offsetresult the offset into the 'result' vector at which to place the base 'e' (natural) exponential of 'x', this value assumes 128 bit wide strides
   * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
   * http://www.netlib.org/slatec/src/zexp.f
   */
  public static void cexpInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetx2 = 2 * offsetx;
    double zm = Math.exp(x[offsetx2]);
    result[offsetresult128_0] = zm * Math.cos(x[offsetx2 + 1]);
    result[offsetresult128_1] = zm * Math.sin(x[offsetx2 + 1]);
  }

  /**
   * Computes the base 'e' natural exponential of a complex value
   * @param x the value of which to compute the base 'e' (natural) exponential, packed as x[0] = real part, x[1] = complex part.
   * @return the base 'e' natural exponential of a complex value
   */
  public static double[] cexp(double[] x) {
    double[] ret = new double[2];
    cexpInline(x, 0, ret, 0);
    return ret;
  }

  /**
   * Computes the inverse hyperbolic cosine of a complex value
   * @param x the value of which to compute the inverse hyperbolic cosine, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the inverse hyperbolic cosine, this value assumes 128 bit wide strides
   * @param result the inverse hyperbolic cosine of 'x'
   * @param offsetresult the offset into the 'result' vector at which to place the inverse hyperbolic cosine of 'x', this value assumes 128 bit wide strides
   */
  private static double[] s_twoAsCplx = new double[] {2, 0 };

  public static void cacoshInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetx2 = 2 * offsetx;
    double[] lhs = new double[2];
    result[offsetresult128_0] = x[offsetx2] + 1;
    result[offsetresult128_1] = x[offsetx2 + 1];
    lhs[0] = x[offsetx2] - 1;
    lhs[1] = x[offsetx2 + 1];
    ComplexArithmetic.cdivideInline(result, offsetresult, s_twoAsCplx, 0, result, offsetresult);
    ComplexArithmetic.cdivideInline(lhs, 0, s_twoAsCplx, 0, lhs, 0);
    ComplexArithmetic.csqrtInline(result, offsetresult, result, offsetresult);
    ComplexArithmetic.csqrtInline(lhs, 0, lhs, 0);
    result[offsetresult128_0] += lhs[0];
    result[offsetresult128_1] += lhs[1];
    ComplexArithmetic.clogInline(result, offsetresult, result, offsetresult);
    result[offsetresult128_0] *= 2;
    result[offsetresult128_1] *= 2;
  }

  /**
   * Computes the inverse hyperbolic cosine of a complex value
   * @param x the value of which to compute the inverse hyperbolic cosine, packed as x[0] = real part, x[1] = complex part.
   * @return result the inverse hyperbolic cosine of 'x'
   */
  public static double[] cacosh(double[] x) {
    double[] ret = new double[2];
    cacoshInline(x, 0, ret, 0);
    return ret;
  }

  /**
   * Computes the inverse hyperbolic sine of a complex value
   * @param x the value of which to compute the inverse hyperbolic sine, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the inverse hyperbolic sine, this value assumes 128 bit wide strides
   * @param result the inverse hyperbolic sine of 'x'
   * @param offsetresult the offset into the 'result' vector at which to place the inverse hyperbolic sine of 'x', this value assumes 128 bit wide strides
   * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
   * See http://www.netlib.org/slatec/fnlib/casinh.f
   */
  public static void casinhInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetx2 = 2 * offsetx;
    result[offsetresult128_0] = -x[offsetx2 + 1];
    result[offsetresult128_1] = x[offsetx2];
    csinInline(result, offsetresult, result, offsetresult);
    double tmp = result[offsetresult128_0];
    result[offsetresult128_0] = result[offsetresult128_1];
    result[offsetresult128_1] = -tmp;
  }

  /**
   * Computes the inverse hyperbolic sine of a complex value
   * @param x the value of which to compute the inverse hyperbolic sine, packed as x[0] = real part, x[1] = complex part.
   * @return result the inverse hyperbolic sine of 'x'
   */
  public static double[] casinh(double[] x) {
    double[] ret = new double[2];
    casinhInline(x, 0, ret, 0);
    return ret;
  }

  /**
   * Computes the inverse hyperbolic tangent of a complex value
   * @param x the value of which to compute the inverse hyperbolic tangent, packed as x[0] = real part, x[1] = complex part.
   * @param offsetx the offset into the 'x' vector at which to compute the inverse hyperbolic tangent, this value assumes 128 bit wide strides
   * @param result the inverse hyperbolic tangent of 'x'
   * @param offsetresult the offset into the 'result' vector at which to place the inverse hyperbolic tangent of 'x', this value assumes 128 bit wide strides
   * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
   * See http://www.netlib.org/slatec/fnlib/catanh.f
   */
  public static void catanhInline(double[] x, int offsetx, double[] result, int offsetresult) {
    final int offsetresult128_0 = 2 * offsetresult; //CSIGNORE
    final int offsetresult128_1 = 2 * offsetresult + 1; //CSIGNORE
    final int offsetx2 = 2 * offsetx;
    result[offsetresult128_0] = -x[offsetx2 + 1];
    result[offsetresult128_1] = x[offsetx2];
    ctanInline(result, offsetresult, result, offsetresult);
    double tmp = result[offsetresult128_0];
    result[offsetresult128_0] = result[offsetresult128_1];
    result[offsetresult128_1] = -tmp;
  }

  /**
   * Computes the inverse hyperbolic tangent of a complex value
   * @param x the value of which to compute the inverse hyperbolic tangent, packed as x[0] = real part, x[1] = complex part.
   * @return result the inverse hyperbolic tangent of 'x'
   */
  public static double[] catanh(double[] x) {
    double[] ret = new double[2];
    catanhInline(x, 0, ret, 0);
    return ret;
  }

} // class end

