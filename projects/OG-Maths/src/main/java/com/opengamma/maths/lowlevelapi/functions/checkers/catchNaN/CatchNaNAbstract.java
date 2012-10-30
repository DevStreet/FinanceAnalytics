/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.checkers.catchNaN;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Catches NaNs
 * @param <T> An OGArray type
 */
public abstract class CatchNaNAbstract<T extends OGArray<? extends Number>> {
  public abstract void catchnan(T array1);
}
