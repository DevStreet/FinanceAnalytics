/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.rootfinding.newton;

import org.apache.commons.lang.Validate;

import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.maths.DOGMA;
import com.opengamma.maths.datacontainers.OGTerminal;
import com.opengamma.maths.datacontainers.matrix.OGRealDenseMatrix;

/**
 * 
 */
public class JacobianDirectionFunction implements NewtonRootFinderDirectionFunction {

  @Override
  public DoubleMatrix1D getDirection(final DoubleMatrix2D estimate, final DoubleMatrix1D y) {
    Validate.notNull(estimate);
    Validate.notNull(y);
    OGTerminal A = new OGRealDenseMatrix(estimate.getData());
    OGTerminal b = new OGRealDenseMatrix(y.getData(), y.getNumberOfElements(), 1);
    return new DoubleMatrix1D(DOGMA.toOGTerminal(DOGMA.mldivide(A, b)).getData());
  }
}
