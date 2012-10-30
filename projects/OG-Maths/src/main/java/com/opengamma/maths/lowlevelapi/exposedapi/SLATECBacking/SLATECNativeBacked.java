/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.SLATECBacking;

import com.opengamma.maths.nativewrappers.OGSLATECRawWrapper;

//import com.opengamma.maths.nativewrappers.OGSLATECRawWrapper;

/**
 * Native library backed SLATEC
 */
public class SLATECNativeBacked extends SLATECAbstractSuper implements SLATECAPIInterface {

  @Override
  public double derf(double x) {
    return OGSLATECRawWrapper.derf(new double[] {x});
  }  
  
  @Override
  public double derfc(double x) {
    return OGSLATECRawWrapper.derfc(new double[] {x });
  }

}
