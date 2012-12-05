/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.operationstack;

import java.util.Comparator;

/**
 * Compares classes lexographically on reflective .getSimpleName() 
 */
public class ClassComparer implements Comparator<Class<?>> {
  @Override
  public int compare(Class<?> o1, Class<?> o2) {
    return o1.getSimpleName().compareTo(o2.getSimpleName());
  }
}
