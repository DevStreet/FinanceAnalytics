/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.sqrt.Sqrt;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMAExponentsAndLogarithmsAPI;

/**
 * 
 */
public class DOGMAExponentsAndLogarithms implements DOGMAExponentsAndLogarithmsAPI {
  private Sqrt _sqrt = new Sqrt();
  
  @Override
  public OGArraySuper<Number> sqrt(OGArraySuper<Number> array1) {
    return _sqrt.sqrt(array1);
  }

}
