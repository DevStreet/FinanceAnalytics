/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.maths.commonapi.exceptions;

/**
 * Provides a manner in which maths exceptions can be thrown.
 */
public class MathsExceptionEncounteredNaN extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public MathsExceptionEncounteredNaN() {
    super();
  }

  public MathsExceptionEncounteredNaN(final String s) {
    super(s);
  }

  public MathsExceptionEncounteredNaN(final String s, final Throwable cause) {
    super(s, cause);
  }

  public MathsExceptionEncounteredNaN(final Throwable cause) {
    super(cause);
  }

  /**
   * Specify where in an array a NaN has been found
   * @param theRow the row in which a NaN has been found
   * @param theColumn  the column in which a NaN has been found
   */
  public MathsExceptionEncounteredNaN(int theRow, int theColumn) {
    super("NaN found first at position: row = " + theRow + ". column = " + theColumn);
  }
}
