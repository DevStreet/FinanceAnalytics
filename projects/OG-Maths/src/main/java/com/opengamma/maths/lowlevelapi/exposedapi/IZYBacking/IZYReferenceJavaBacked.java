/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.IZYBacking;

import java.text.Format;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.lowlevelapi.exposedapi.SLATEC;
import com.opengamma.maths.lowlevelapi.functions.MathE;

/**
 * Implements the izy interface in java, best effort impl only.
 */
public class IZYReferenceJavaBacked extends IZYAbstractSuper implements IZYAPIInterface {

  private static SLATEC s_slatec = new SLATEC();

  @Override
  public void vd_acos(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.acos(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_asin(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.asin(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_atan(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.atan(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_atan2(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.atan2(arg0[i + jmparg0], arg1[i + jmparg1]);
    }
  }

  @Override
  public void vd_cos(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.cos(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_sin(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.sin(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_sincos(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0, double[] out1, int[] offsetout1) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    int jmpout1 = offsetout1[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.sin(arg0[i + jmparg0]);
      out1[i + jmpout1] = Math.cos(arg0[i + jmparg0]);
    }

  }

  @Override
  public void vd_tan(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.tan(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_acosh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = s_slatec.dacosh(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_asinh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = s_slatec.dasinh(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_atanh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = s_slatec.datanh(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_cosh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.cosh(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_sinh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.sinh(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_tanh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.tanh(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_cbrt(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.pow(arg0[i + jmparg0], 1. / 3.);
    }
  }

  @Override
  public void vd_hypot(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.hypot(arg0[i + jmparg0], arg1[i + jmparg1]);
    }
  }

  @Override
  public void vd_inv(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = 1. / (arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_invcbrt(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.pow(arg0[i + jmparg0], -1. / 3.);
    }
  }

  @Override
  public void vd_invsqrt(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.pow(arg0[i + jmparg0], -1. / 2.); // could 1/sqrt, this *might* be more accurate
    }
  }

  @Override
  public void vd_pow(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.pow(arg0[i + jmparg0], arg1[i + jmparg1]);
    }
  }

  @Override
  public void vd_powx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    double constant = arg1[jmparg1];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.pow(arg0[i + jmparg0], constant);
    }
  }

  @Override
  public void vd_pow2o3(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.pow(arg0[i + jmparg0], 2. / 3.);
    }
  }

  @Override
  public void vd_pow3o2(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.pow(arg0[i + jmparg0], 3. / 2.);
    }
  }

  @Override
  public void vd_sqrt(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.sqrt(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_exp(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.exp(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_expm1(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.expm1(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_ln(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.log(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_log10(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.log10(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_log1p(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.log1p(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_abs(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.abs(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_add(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = arg0[i + jmparg0] + arg1[i + jmparg1];
    }
  }

  @Override
  public void vd_addx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    double constant = arg1[jmparg1];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = arg0[i + jmparg0] + constant;
    }
  }

  @Override
  public void vd_div(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = arg0[i + jmparg0] / arg1[i + jmparg1];
    }
  }

  @Override
  public void vd_divx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    double constant = arg1[jmparg1];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = arg0[i + jmparg0] / constant;
    }
  }

  @Override
  public void vd_mul(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = arg0[i + jmparg0] * arg1[i + jmparg1];
    }
  }

  @Override
  public void vd_mulx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    double constant = arg1[jmparg1];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = arg0[i + jmparg0] * constant;
    }
  }

  @Override
  public void vd_sqr(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    int ptr;
    for (int i = 0; i < n; i++) {
      ptr = i + jmparg0;
      out0[i + jmpout0] = arg0[ptr] * arg0[ptr];
    }
  }

  @Override
  public void vd_sub(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = arg0[i + jmparg0] - arg1[i + jmparg1];
    }
  }

  @Override
  public void vd_subx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmparg1 = offsetarg1[0];
    int jmpout0 = offsetout0[0];
    double constant = arg1[jmparg1];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = arg0[i + jmparg0] - constant;
    }
  }

  @Override
  public void vd_ceil(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.ceil(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_floor(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.floor(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_modf(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0, double[] out1, int[] offsetout1) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    int jmpout1 = offsetout1[0];
    double[] tmp = new double[1];
    for (int i = 0; i < n; i++) {
      out1[i + jmpout1] = MathE.modf(arg0[i + jmparg0], tmp);
      out0[i + jmpout0] = tmp[0];
    }

  }

  @Override
  public void vd_nearbyint(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.rint(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_rint(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = Math.rint(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_round(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    double v;
    for (int i = 0; i < n; i++) {
      v = arg0[i + jmparg0];
      out0[i + jmpout0] = Math.copySign(Math.round(Math.abs(v)), v);
    }
  }

  @Override
  public void vd_trunc(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = MathE.trunc(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_cdfnorm(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    throw new MathsExceptionNotImplemented("cdfnorm is not implemented");
  }

  @Override
  public void vd_cdfnorminv(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    throw new MathsExceptionNotImplemented("cdfnorminv is not implemented");
  }

  @Override
  public void vd_erf(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = s_slatec.derf(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_erfc(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = s_slatec.derfc(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_erfinv(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    throw new MathsExceptionNotImplemented("erfinv is not implemented");
  }

  @Override
  public void vd_erfcinv(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    throw new MathsExceptionNotImplemented("erfcinv is not implemented");
  }

  @Override
  public void vd_lgamma(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = s_slatec.dlngam(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vd_tgamma(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    int n = count[0];
    int jmparg0 = offsetarg0[0];
    int jmpout0 = offsetout0[0];
    for (int i = 0; i < n; i++) {
      out0[i + jmpout0] = s_slatec.dgamma(arg0[i + jmparg0]);
    }
  }

  @Override
  public void vz_abs(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_acos(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_acosh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_add(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_addx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_arg(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_asin(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_asinh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_atan(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_atanh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_conj(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_cos(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_cosh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_div(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_divx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_exp(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_ln(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_log10(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_mul(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_mulx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_mulbyconj(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_pow(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_powx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_sin(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_sinh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_sqrt(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_sub(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_subx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_tan(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

  @Override
  public void vz_tanh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
  }

}
