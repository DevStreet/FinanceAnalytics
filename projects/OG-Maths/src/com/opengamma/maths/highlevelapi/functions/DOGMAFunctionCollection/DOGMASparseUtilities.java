/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full.Full;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMASparseUtilitiesAPI;

/**
 * 
 */
public class DOGMASparseUtilities implements DOGMASparseUtilitiesAPI {
  private final Full _full = new Full();

  @Override
  public OGArray<? extends Number> full(OGArray<? extends Number> array) {
    return _full.full(array);
  }

}
