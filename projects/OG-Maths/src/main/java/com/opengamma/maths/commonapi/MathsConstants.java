/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.commonapi;

/**
 * Contains commonly used numbers
 */
public class MathsConstants {

  /**
   * eps is the smallest representable difference between two double precision numbers.
   * In ULP terms it is 2ULP. 
   */
  public static final double eps = 2 * Math.pow(2, -53); //CSIGNORE

  // divided constants generated in __float128, java can truncate as it likes
  /**
   * The mathematical constant pi
   */
  public static final double pi =  3.14159265358979323846264338327950d; //CSIGNORE  
  
  /**
   * The mathematical constant one half of pi 
   */
  public static final double halfpi =  1.57079632679489661923132169163975d; //CSIGNORE

  /**
   * The mathematical constant one quarter of pi 
   */
  public static final double quaterpi =  0.78539816339744830961566084581988d; //CSIGNORE
  
  
}
