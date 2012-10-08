/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.maths.commonapi.exceptions;

/**
 * Provides a manner in which maths exceptions can be thrown when a result is dereferenced and it hasn't been requested at compute time.
 */
public class MathsExceptionNotRequested extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public MathsExceptionNotRequested() {
    super();
  }

  public MathsExceptionNotRequested(final String s) {
    super(s);
  }

  public MathsExceptionNotRequested(final String s, final Throwable cause) {
    super(s, cause);
  }

  public MathsExceptionNotRequested(final Throwable cause) {
    super(cause);
  }
}
