/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot;

import java.util.Map;

/**
 * A snapshot of volatility cube data.
 * <p>
 * This class is mutable and not thread-safe.
 */
public interface VolatilityCubeSnapshot {

  /**
   * Gets the value snapshots by point.
   * 
   * @return the values
   */
  Map<VolatilityPoint<Object, Object, Object>, ValueSnapshot> getValues();

}
