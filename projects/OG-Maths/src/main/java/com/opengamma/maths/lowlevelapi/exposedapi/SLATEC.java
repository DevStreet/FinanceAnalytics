/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi;

import com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking.SLATECAPIInterface;
import com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking.SLATECAbstractSuper;
import com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking.SLATECNativeBacked;
import com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking.SLATECOGJavaBacked;

/**
 * Provides a unified interface to SLATEC
 */
public class SLATEC implements SLATECAPIInterface {

  private SLATECAbstractSuper _localSLATEC;

  /**
   * Backing type
   */
  public enum backing {
    /**
     * OG Java backed SLATEC
     */
    OGjava,
    /**
     * OG Native backed SLATEC
     */
    OGnative
  }

  public SLATEC() {
    _localSLATEC = new SLATECNativeBacked();
  }

  public SLATEC(backing backedby) {
    if (backedby == backing.OGjava) {
      _localSLATEC = new SLATECOGJavaBacked();
    } else if (backedby == backing.OGnative) {
      _localSLATEC = new SLATECNativeBacked();
    }

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
