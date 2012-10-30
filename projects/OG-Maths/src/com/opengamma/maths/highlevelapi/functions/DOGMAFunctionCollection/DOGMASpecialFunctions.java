/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions.Erf;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions.Erfc;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMASpecialFunctionsAPI;

/**
 * 
 */
public class DOGMASpecialFunctions implements DOGMASpecialFunctionsAPI {
  private static final Erf ERF = new Erf();
  private static final Erfc ERFC = new Erfc();

  @Override
  public OGArray<? extends Number> erf(OGArray<? extends Number> array1) {
    return ERF.erf(array1);
  }

  @Override
  public OGArray<? extends Number> erfc(OGArray<? extends Number> array1) {
    return ERFC.erfc(array1);
  }
}
