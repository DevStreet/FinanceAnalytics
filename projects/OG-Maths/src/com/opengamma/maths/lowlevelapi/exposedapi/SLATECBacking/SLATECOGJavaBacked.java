/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking;

import com.opengamma.maths.lowlevelapi.slatec.fnlib.DERF;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.DERFC;

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

}
