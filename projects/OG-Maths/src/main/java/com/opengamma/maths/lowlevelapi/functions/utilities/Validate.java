/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.utilities;

/**
 * Validates truth or non-null property of paramters.
 */
public class Validate {
  public static void notNull(Object obj) {
    notNull(obj, "Object must not be null");
  }

  public static void notNull(Object obj, String message) {
    if (obj == null) {
      throw new MathsExceptionNullPointer(message);
    }
  }

  public static void isTrue(boolean v) {
    isTrue(v, "Value must be true");
  }

  public static void isTrue(boolean v, String message) {
    if (!v) {
      throw new MathsExceptionGeneric(message);
    }
  }
}
