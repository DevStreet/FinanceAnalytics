/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.commonapi.exceptions;

/**
 * For specificly stating something is not implemented
 */
public class MathsExceptionNotImplemented extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public MathsExceptionNotImplemented() {
    super();
  }

  public MathsExceptionNotImplemented(final String s) {
    super(s);
  }

  public MathsExceptionNotImplemented(final String s, final Throwable cause) {
    super(s, cause);
  }

  public MathsExceptionNotImplemented(final Throwable cause) {
    super(cause);
  }
}
