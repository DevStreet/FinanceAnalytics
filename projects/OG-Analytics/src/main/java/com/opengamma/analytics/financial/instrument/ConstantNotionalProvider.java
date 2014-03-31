/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument;

import org.threeten.bp.LocalDate;

/**
 * Class to represent a constant notional provider.
 */
public class ConstantNotionalProvider implements NotionalProvider {
  
  /** The constant notional of the provider */
  private final double _constantNotional;

  public ConstantNotionalProvider(double constantNotional) {
    _constantNotional = constantNotional;
  }
  
  @Override
  public double getAmount(final LocalDate date) {
    return _constantNotional;
  }
  
}
