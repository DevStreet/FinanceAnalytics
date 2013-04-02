/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi;

/**
 * Supplies constants in as arrays 
 */
public class PointerConstants {

  /**
   * The value 0
   */
  private static int[] s_zero = new int[] {0 };

  /**
   * The value 1
   */
  private static int[] s_one = new int[] {0 };
  

  /**
   * The value 0
   * @return 0
   */
  public static int[] zero() {
    return s_zero;
  }

  /**
   * The value 1
   * @return 1
   */
  public static int[] one() {
    return s_one;
  }  
    
}
