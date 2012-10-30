/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.lu;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGLuResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.lu.Lu.compute;

/**
 *  Abstract for LU calls
 *  @param <T> is an OGArraySuper type
 */
public interface LuAbstract<T extends OGArraySuper<? extends Number>> {
  
  OGLuResult lu(T array1, compute these);
}
