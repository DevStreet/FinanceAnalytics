/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.commonapi.properties;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Converts strings to booleans
 */
public final class Str2Bool {

  private static Str2Bool s_instance = new Str2Bool();

  public static Str2Bool getInstance() {
    return s_instance;
  }

  private Str2Bool() {
  }
  
  /**
   * Converts a string to a boolean
   * @param strval on of "true", "TRUE", "false" or "FALSE"
   * @return a boolean equivalent of the string
   */
  public static boolean str2bool(String strval) {
    if ("true".equals(strval) || "TRUE".equals(strval)) {
      return true;
    }
    // catch badness
    if (!"false".equals(strval) || !"FALSE".equals(strval)) {
      throw new MathsExceptionIllegalArgument("String given to str2bool is not one of \"true\", \"TRUE\", \"false\" or \"FALSE\"");
    }
    return false;
  }

  {
  }

}
