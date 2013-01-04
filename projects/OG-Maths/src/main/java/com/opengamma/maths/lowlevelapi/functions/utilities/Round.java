/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.utilities;

/**
 * Rounds doubles
 */
public class Round {

  /**
   * Stateless rounding of data
   * @param data the data to round
   * @return the rounded data
   */
  public static double[] stateless(final double[] data) {
    final int len = data.length;
    double[] tmp = new double[len];
    for (int i = 0; i < len; i++) {
      if (Double.isNaN(data[i])) {
        tmp[i] = Double.NaN;
      }
      if (tmp[i] == Double.NEGATIVE_INFINITY) {
        tmp[i] = Double.NEGATIVE_INFINITY;
      }
      if (tmp[i] == Double.POSITIVE_INFINITY) {
        tmp[i] = Double.POSITIVE_INFINITY;
      }
      if (tmp[i] >= 0) {
        tmp[i] = Math.round(data[i]);
      } else {
        if (tmp[i] > -0.5d) { // geq needs to round down
          tmp[i] = 0d;
        } else {
          tmp[i] = (long) Math.floor(data[i] - 0.5d);
        }
      }
    }
    return tmp;
  }

  /**
   * In place rounding of data
   * @param data the data to round
   */
  public static void inPlace(final double[] data) {
    final int len = data.length;
    for (int i = 0; i < len; i++) {
      if (Double.isNaN(data[i])) {
        data[i] = Double.NaN;
      }
      if (data[i] == Double.NEGATIVE_INFINITY) {
        data[i] = Double.NEGATIVE_INFINITY;
      }
      if (data[i] == Double.POSITIVE_INFINITY) {
        data[i] = Double.POSITIVE_INFINITY;
      }
      if (data[i] >= 0) {
        data[i] = Math.round(data[i]);
      } else {
        if (data[i] > -0.5d) { // geq needs to round down
          data[i] = 0d;
        } else {
          data[i] = (long) Math.floor(data[i] - 0.5d);
        }
      }
    }
  }

}
