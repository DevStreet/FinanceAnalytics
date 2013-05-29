/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi;

import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * A less scary version of IZY. Default offsets of zero are assumed. 
 * Performing the operation across the entire vector inputs is also assumed.
 */
public class EasyIZY {

  private static EasyIZY s_instance = new EasyIZY();

  /**
   * Get the current EasyIZY instance
   * @return the current EasyIZY instance
   */
  public static EasyIZY getInstance() {
    return s_instance;
  }

  private static IZY s_localIZY = IZY.getInstance();

  private static final int[] s_zero = PointerConstants.zero();

  //CSOFF
  public static void vd_acos(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_acos(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_asin(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_asin(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_atan(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_atan(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_atan2(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector arg0 is not the same length as output vector");
    Catchers.catchCondition(arg1.length != out0.length, "Input vector arg1 is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_atan2(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vd_cos(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_cos(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_sin(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_sin(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_sincos(double[] arg0, double[] out0, double[] out1) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(out1.length != out0.length, "Output vectors are of different lengths");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_sincos(count, arg0, s_zero, out0, s_zero, out1, s_zero);
  }

  public static void vd_tan(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_tan(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_acosh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_acosh(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_asinh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_asinh(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_atanh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_atanh(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_cosh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_cosh(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_sinh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_sinh(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_tanh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_tanh(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_cbrt(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_cbrt(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_hypot(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_hypot(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vd_inv(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_inv(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_invcbrt(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_invcbrt(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_invsqrt(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_invsqrt(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_pow(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_pow(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vd_powx(double[] arg0, double arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_powx(count, arg0, s_zero, new double[] {arg1 }, s_zero, out0, s_zero);
  }

  public static void vd_pow2o3(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_pow2o3(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_pow3o2(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_pow3o2(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_sqrt(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_sqrt(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_exp(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_exp(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_expm1(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_expm1(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_ln(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_ln(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_log10(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_log10(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_log1p(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_log1p(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_abs(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_abs(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_add(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_add(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vd_addx(double[] arg0, double arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_addx(count, arg0, s_zero, new double[] {arg1 }, s_zero, out0, s_zero);
  }

  public static void vd_div(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_div(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vd_divx(double[] arg0, double arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_divx(count, arg0, s_zero, new double[] {arg1 }, s_zero, out0, s_zero);
  }

  public static void vd_xdiv(double arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg1.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg1.length };
    s_localIZY.vd_xdiv(count, new double[] {arg0 }, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vd_mul(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_mul(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vd_mulx(double[] arg0, double arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_mulx(count, arg0, s_zero, new double[] {arg1 }, s_zero, out0, s_zero);
  }

  public static void vd_sqr(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_sqr(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_sub(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_sub(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vd_subx(double[] arg0, double arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_subx(count, arg0, s_zero, new double[] {arg1 }, s_zero, out0, s_zero);
  }

  public static void vd_xsub(double arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg1.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg1.length };
    s_localIZY.vd_xsub(count, new double[] {arg0 }, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vd_negate(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_negate(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_ceil(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_ceil(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_floor(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_floor(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_modf(double[] arg0, double[] out0, double[] out1) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(out0.length != out1.length, "Output vectors are not the same length");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_modf(count, arg0, s_zero, out0, s_zero, out1, s_zero);
  }

  public static void vd_nearbyint(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_nearbyint(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_rint(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_rint(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_round(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_round(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_trunc(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_trunc(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_cdfnorm(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_cdfnorm(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_cdfnorminv(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_cdfnorminv(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_erf(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_erf(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_erfc(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_erfc(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_erfinv(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_erfinv(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_erfcinv(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_erfcinv(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_lgamma(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_lgamma(count, arg0, s_zero, out0, s_zero);
  }

  public static void vd_tgamma(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length };
    s_localIZY.vd_tgamma(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_abs(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_abs(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_acos(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_acos(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_acosh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_acosh(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_add(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_add(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_addx(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg1.length != 2, "arg1 has a length > 2. This is incorrect for a complex number representation");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_addx(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_arg(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_arg(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_asin(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_asin(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_asinh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_asinh(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_atan(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_atan(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_atanh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_atanh(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_conj(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_conj(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_cos(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_cos(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_cosh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_cosh(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_div(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_div(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_divx(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg1.length != 2, "arg1 has a length > 2. This is incorrect for a complex number representation");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_divx(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_xdiv(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg1.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != 2, "arg1 has a length > 2. This is incorrect for a complex number representation");
    int[] count = new int[] {arg1.length / 2 };
    s_localIZY.vz_xdiv(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_exp(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_exp(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_ln(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_ln(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_log10(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_log10(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_mul(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_mul(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_mulx(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg1.length != 2, "arg1 has a length > 2. This is incorrect for a complex number representation");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_mulx(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_mulbyconj(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_mulbyconj(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_pow(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_pow(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_powx(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg1.length != 2, "arg1 has a length > 2. This is incorrect for a complex number representation");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_powx(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_negate(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_negate(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_negatereal(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_negatereal(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_sin(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_sin(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_sinh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_sinh(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_sqrt(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_sqrt(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_sub(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != arg1.length, "Input vectors are not the same length");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_sub(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_subx(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg1.length != 2, "arg1 has a length > 2. This is incorrect for a complex number representation");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_subx(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_xsub(double[] arg0, double[] arg1, double[] out0) {
    Catchers.catchCondition(arg1.length != out0.length, "Input vector is not the same length as output vector");
    Catchers.catchCondition(arg0.length != 2, "arg1 has a length > 2. This is incorrect for a complex number representation");
    int[] count = new int[] {arg1.length / 2 };
    s_localIZY.vz_xsub(count, arg0, s_zero, arg1, s_zero, out0, s_zero);
  }

  public static void vz_tan(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_tan(count, arg0, s_zero, out0, s_zero);
  }

  public static void vz_tanh(double[] arg0, double[] out0) {
    Catchers.catchCondition(arg0.length != out0.length, "Input vector is not the same length as output vector");
    int[] count = new int[] {arg0.length / 2 };
    s_localIZY.vz_tanh(count, arg0, s_zero, out0, s_zero);
  }
}
