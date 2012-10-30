/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.checkers.catchInf;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Catches Infss
 * @param <T> An OGArray type
 */
public abstract class CatchInfAbstract<T extends OGArray<? extends Number>> {
  public abstract void catchinf(T array1);
}
