/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.rootfinding.newton;

import org.apache.commons.lang.Validate;

import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.maths.DOGMA;
import com.opengamma.maths.datacontainers.matrix.OGRealDenseMatrix;

/**
 * 
 */
public class InverseJacobianEstimateInitializationFunction implements NewtonRootFinderMatrixInitializationFunction {

  @Override
  public DoubleMatrix2D getInitializedMatrix(final Function1D<DoubleMatrix1D, DoubleMatrix2D> jacobianFunction, final DoubleMatrix1D x) {
    Validate.notNull(jacobianFunction);
    Validate.notNull(x);
    final DoubleMatrix2D A = jacobianFunction.evaluate(x);
    return new DoubleMatrix2D(DOGMA.toDoubleArrayOfArrays(DOGMA.pinv(new OGRealDenseMatrix(A.getData()))));
  }

}
