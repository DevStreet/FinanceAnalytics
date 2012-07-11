/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions.Erf;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMASpecialFunctionsAPI;

/**
 * 
 */
public class DOGMASpecialFunctions implements DOGMASpecialFunctionsAPI {
  private static final Erf ERF = new Erf();

  @Override
  public OGArraySuper<Number> erf(OGArraySuper<Number> array1) {
    return ERF.erf(array1);
  }

  @Override
  public OGArraySuper<Number> erfc(OGArraySuper<Number> array1) {
    return null;
  }
}
