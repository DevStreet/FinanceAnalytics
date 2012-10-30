/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.maths.commonapi.exceptions;

/**
 * Provides a manner in which maths exceptions can be thrown.
 */
public class MathsExceptionNonConformance extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public MathsExceptionNonConformance() {
    super();
  }

  public MathsExceptionNonConformance(final String s) {
    super(s);
  }

  public MathsExceptionNonConformance(final int dim1, final int dim2, final int dim3, final int dim4) {
    super(new String("Operation requested on non-conformant arguments. Argument 1 is [" + dim1 + "," + dim2 + "], Argument 2 is  [" + dim3 + "," + dim4 + "]"));
  }

  public MathsExceptionNonConformance(final String s, final Throwable cause) {
    super(s, cause);
  }

  public MathsExceptionNonConformance(final Throwable cause) {
    super(cause);
  }
}
