/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.commonapi.exceptions;

/**
 * Provides a manner in which maths exceptions relating to parse errors can be thrown
 */
public class MathsExceptionParseError extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public MathsExceptionParseError() {
    super();
  }

  public MathsExceptionParseError(final String s) {
    super("Error parsing: " + s);
  }

  public MathsExceptionParseError(final String s, final Throwable cause) {
    super("Error parsing: " + s, cause);
  }

  public MathsExceptionParseError(final Throwable cause) {
    super(cause);
  }
}
