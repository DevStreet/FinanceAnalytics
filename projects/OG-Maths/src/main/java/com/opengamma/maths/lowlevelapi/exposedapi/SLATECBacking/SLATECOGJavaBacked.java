/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking;

import com.opengamma.maths.lowlevelapi.slatec.fnlib.DERF;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.DERFC;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.ZABS;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.ZATAN;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.ZLOG;

/**
 * Provides OG Java implementations of SLATEC functions 
 */
public class SLATECOGJavaBacked extends SLATECAbstractSuper implements SLATECAPIInterface {

  @Override
  public double derf(double x) {
    return DERF.derf(x);
  }

  @Override
  public double derfc(double x) {
    return DERFC.derfc(x);
  }

  @Override
  public double dgamma(double x) {
    return Double.NaN;
  }
  
  @Override
  public double dlngam(double x) {
    return Double.NaN;
  }
  
  @Override
  public double dacosh(double x) {
    return Double.NaN;
  }

  @Override
  public double dasinh(double x) {
    return Double.NaN;
  }

  @Override
  public double datanh(double x) {
    return Double.NaN;
  }

  @Override
  public double zabs(double zr, double zi) {
    return ZABS.zabs(zr, zi);
  }

  @Override
  public void zlog(double ar, double ai, double[] br, double[] bi, int[] ierr) {
    ZLOG.zlog(ar, ai, br, bi, ierr);
  }

  @Override
  public double[] zatan(double[] z) {
    return ZATAN.zatan(z);
  }



}
