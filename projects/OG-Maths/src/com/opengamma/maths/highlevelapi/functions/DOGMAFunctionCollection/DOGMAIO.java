/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIO.SmartImport;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMAIOAPI;

/**
 * Does IO for DOGMA
 */
public class DOGMAIO implements DOGMAIOAPI {

  private SmartImport _si = new SmartImport();

  @Override
  public OGArraySuper<Number> smartImport(double[][] aMatrix) {
    return _si.fromNativeDoubleMatrix(aMatrix);
  }

}

