/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking;

import com.opengamma.maths.nativewrappers.OGSLATECRawWrapper;

/**
 * Native library backed SLATEC
 */
public class SLATECNativeBacked extends SLATECAbstractSuper implements SLATECAPIInterface {

  @Override
  public double derf(double x) {
    return OGSLATECRawWrapper.derf(new double[] {x });
  }

  @Override
  public double derfc(double x) {
    return OGSLATECRawWrapper.derfc(new double[] {x });
  }

  @Override
  public double dgamma(double x) {
    return OGSLATECRawWrapper.dgamma(new double[] {x });
  }

  @Override
  public double dlngam(double x) {
    return OGSLATECRawWrapper.dlngam(new double[] {x });
  }

  @Override
  public double dacosh(double x) {
    return OGSLATECRawWrapper.dacosh(new double[] {x });
  }

  @Override
  public double dasinh(double x) {
    return OGSLATECRawWrapper.dasinh(new double[] {x });
  }

  @Override
  public double datanh(double x) {
    return OGSLATECRawWrapper.datanh(new double[] {x });
  }

  @Override
  public double zabs(double zr, double zi) {
    return OGSLATECRawWrapper.zabs(new double[] {zr }, new double[] {zi });
  }

  @Override
  public void zlog(double ar, double ai, double[] br, double[] bi, int[] ierr) {
    OGSLATECRawWrapper.zlog(new double[] {ar }, new double[] {ai }, br, bi, ierr);
  }

  @Override
  public double dbinom(int n, int m) {
    return OGSLATECRawWrapper.dbinom(new int[] {n }, new int[] {m });
  }

  @Override
  public double d9lgmc(double x) {
    return OGSLATECRawWrapper.d9lgmc(new double[] {x });
  }

  @Override
  public double dcsevl(double x, double[] cs, int n) {
    return OGSLATECRawWrapper.dcsevl(new double[] {x }, cs, new int[] {n });
  }

}
