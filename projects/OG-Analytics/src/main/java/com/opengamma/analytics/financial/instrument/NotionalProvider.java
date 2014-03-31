/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.analytics.financial.instrument;

import org.threeten.bp.LocalDate;

/**
 * An interface that can return the notional for a given date.
 */
public interface NotionalProvider {

  /**
   * Returns the notional at a given date.
   * @param date The date.
   * @return The notional.
   */
  double getAmount(final LocalDate date);

}
