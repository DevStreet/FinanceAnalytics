/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.operationstack;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Interface to the static optimisation of the operation chain
 */
public interface InfixOperatorOptimiser {

  InfixOpChain getOpChain(OGArray<? extends Number> arr1, OGArray<? extends Number> arr2);
  
}
