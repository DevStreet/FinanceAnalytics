/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.annuity;

/**
 * Compounding methods for Ibor-like coupons in swaps.
 */
public enum CompoundingMethod {
  
  /**
   * None.
   */
  NONE,
  
  /**
   * Flat. Corresponds to "Flat Compounding" in the ISDA definitions.
   */
  FLAT,
  
  /**
   * Straight. Corresponds to "Compounding" in the ISDA definitions.
   */
  STRAIGHT,
  
  /**
   * Spread exclusive. Corresponds to "Compounding treating Spread as simple interest" in the ISDA definitions.
   */
  SPREAD_EXCLUSIVE
}
