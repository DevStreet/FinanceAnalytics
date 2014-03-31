/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import org.threeten.bp.Period;

import com.opengamma.util.time.Tenor;

/**
 * Class with the attributed required to generate an interest rate (IR) instrument from the market quotes.
 * The attributes are composed of one or two tenors (the start period and the end period).
 */
public class GeneratorAttributeIROTC extends GeneratorAttribute {

  /**
   * The start period. 
   */
  private final Tenor _startTenor;
  /**
   * The end period. 
   */
  private final Tenor _endTenor;

  /**
   * Constructor.
   * @param startTenor The start period.
   * @param endTenor The end period.
   */
  public GeneratorAttributeIROTC(final Tenor startTenor, final Tenor endTenor) {
    super();
    _startTenor = startTenor;
    _endTenor = endTenor;
  }

  /**
   * Constructor. By default the start period is set to ZERO.
   * @param endPeriod The end period.
   */
  public GeneratorAttributeIROTC(final Tenor endPeriod) {
    _startTenor = Tenor.of(Period.ZERO);
    _endTenor = endPeriod;
  }

  /**
   * Returns the start tenor.
   * @return The start tenor.
   */
  public Tenor getStartTenor() {
    return _startTenor;
  }

  /**
   * Returns the end tenor.
   * @return The end tenor.
   */
  public Tenor getEndTenor() {
    return _endTenor;
  }

}
