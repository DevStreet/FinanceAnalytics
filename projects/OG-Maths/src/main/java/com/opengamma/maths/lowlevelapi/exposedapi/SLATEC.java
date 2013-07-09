/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi;

import com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking.SLATECAPIInterface;
import com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking.SLATECAbstractSuper;
import com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking.SLATECNativeBacked;

/**
 * Provides a unified interface to SLATEC
 */
public final class SLATEC implements SLATECAPIInterface {

  private static class SLATECRef {
    public static final SLATEC s_instance = new SLATEC();
  }

  public static SLATEC getInstance() {
    return SLATECRef.s_instance;
  }

  private SLATECAbstractSuper _localSLATEC;

  private SLATEC() {
    _localSLATEC = new SLATECNativeBacked();
  }

  @Override
  public double derf(double x) {
    return _localSLATEC.derf(x);
  }

  @Override
  public double derfc(double x) {
    return _localSLATEC.derfc(x);
  }

  @Override
  public double dgamma(double x) {
    return _localSLATEC.dgamma(x);
  }

  @Override
  public double dlngam(double x) {
    return _localSLATEC.dlngam(x);
  }

  @Override
  public double dacosh(double x) {
    return _localSLATEC.dacosh(x);
  }

  @Override
  public double dasinh(double x) {
    return _localSLATEC.dasinh(x);
  }

  @Override
  public double datanh(double x) {
    return _localSLATEC.datanh(x);
  }

  @Override
  public double zabs(double zr, double zi) {
    return _localSLATEC.zabs(zr, zi);
  }

  @Override
  public void zlog(double ar, double ai, double[] br, double[] bi, int[] ierr) {
    _localSLATEC.zlog(ar, ai, br, bi, ierr);
  }

  @Override
  public double[] zatan(double[] z) {
    return _localSLATEC.zatan(z);
  }

}
