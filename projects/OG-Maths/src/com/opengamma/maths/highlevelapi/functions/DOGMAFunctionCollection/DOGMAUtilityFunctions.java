/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAUtilityFunctions.dot.Dot;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMAUtilityFunctionsAPI;

/**
 * 
 */
public class DOGMAUtilityFunctions implements DOGMAUtilityFunctionsAPI {
  private Dot _dot = new Dot();
  
  @Override
  public OGArraySuper<? extends Number> dot(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return _dot.dot(array1, array2);
  }

}
