/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi;

import com.opengamma.maths.commonapi.MathsConstants;

/**
 * double[] representations of complex constants
 */
public class ComplexConstants {
  /**
   * The value 0+0i
   */
  private static double[] s_zero = new double[] {0, 0 };

  /**
   * The value 1
   */
  private static double[] s_one = new double[] {1, 0 };

  /**
   * The value 1
   */
  private static double[] s_i = new double[] {0, 1 };

  /**
   * The value 0+(pi/2)*i
   */
  private static final double[] s_i_times_half_pi = new double[] {0.e0, MathsConstants.halfpi }; //CSIGNORE

  /**
   * The value pi/2+0*i
   */
  private static final double[] s_half_pi = new double[] {MathsConstants.halfpi, 0.e0 }; //CSIGNORE

  /**
   * The value -Inf + 0i
   */
  private static final double[] s_neg_inf = new double[] {Double.NEGATIVE_INFINITY, 0.e0 }; //CSIGNORE
  
  
  /**
   * The value 0
   * @return 0
   */
  public static double[] zero() {
    return s_zero;
  }

  /**
   * The value 1
   * @return 1
   */
  public static double[] one() {
    return s_one;
  }

  /**
   * The value i
   * @return i
   */
  public static double[] i() {
    return s_i;
  }

  /**
   * The value 0+(pi/2)*i
   * @return 0+(pi/2)*i
   */
  public static double[] zero_plus_i_times_half_pi() { //CSIGNORE
    return s_i_times_half_pi;
  }

  /**
   * The value (pi/2)+0*i
   * @return (pi/2)+0*i
   */
  public static double[] half_pi_plus_zero_i() { //CSIGNORE
    return s_half_pi;
  }

  /**
   * The value -Inf+0*i
   * @return -Inf+0*i
   */
  public static double[] negative_inf_plus_zero_i() { //CSIGNORE
    return s_neg_inf;
  }
  
}
