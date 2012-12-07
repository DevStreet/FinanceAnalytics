/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.methodhookinstances.unary;

import com.opengamma.maths.dogma.engine.language.UnaryFunction;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * @param <R> ret
 * @param <S> arg1
 */
public interface Log<R extends OGArray<? extends Number>, S extends OGArray<? extends Number>> extends UnaryFunction<R, S> {


}
