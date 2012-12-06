/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.methodhookinstances.unaryvoid;

import com.opengamma.maths.dogma.engine.language.VoidUnaryFunction;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * for displaying results 
 * @param <S> arg1
 */
public interface Disp<S extends OGArray<? extends Number>> extends VoidUnaryFunction<S> {

}
