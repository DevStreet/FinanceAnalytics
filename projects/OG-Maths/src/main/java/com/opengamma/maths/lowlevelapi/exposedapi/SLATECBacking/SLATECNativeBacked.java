/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
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
    return OGSLATECRawWrapper.dlngam(new double[] {x});
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

  // TODO: Add in ZATAN in a SLATEC_extn lib, or perhaps just use the native vector variant
  @Override
  public double[] zatan(double[] z) {
    throw new MathsExceptionNotImplemented("ZATAN is not implemented in SLATEC as Fortran has complex double precision support built in.");
  }

}
