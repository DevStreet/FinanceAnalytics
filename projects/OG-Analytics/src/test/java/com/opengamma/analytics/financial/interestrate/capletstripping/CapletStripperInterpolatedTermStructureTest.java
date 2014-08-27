/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.capletstripping;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Arrays;

import org.testng.annotations.Test;

import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.engine.MersenneTwister64;
import cern.jet.random.engine.RandomEngine;

import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunctionProvider;
import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunctionProviderFromInterpolatedTermStructure;
import com.opengamma.analytics.math.differentiation.VectorFieldFirstOrderDifferentiator;
import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.analytics.math.interpolation.CombinedInterpolatorExtrapolatorFactory;
import com.opengamma.analytics.math.interpolation.Interpolator1DFactory;
import com.opengamma.analytics.math.interpolation.TransformedInterpolator1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.analytics.math.minimization.ParameterLimitsTransform;
import com.opengamma.analytics.math.minimization.ParameterLimitsTransform.LimitType;
import com.opengamma.analytics.math.minimization.SingleRangeLimitTransform;

/**
 * 
 */
public class CapletStripperInterpolatedTermStructureTest extends SingleStrikeSetup {
  private static final RandomEngine RANDOM = new MersenneTwister64(MersenneTwister.DEFAULT_SEED);
  private static final VectorFieldFirstOrderDifferentiator DIFF = new VectorFieldFirstOrderDifferentiator();
  private static final String DEFAULT_INTERPOLATOR = Interpolator1DFactory.DOUBLE_QUADRATIC;
  private static final String DEFAULT_EXTRAPOLATOR = Interpolator1DFactory.LINEAR_EXTRAPOLATOR;
  private static final ParameterLimitsTransform TRANSFORM = new SingleRangeLimitTransform(0.0, LimitType.GREATER_THAN);

  /**
   * Test fitting the ATM Caps.
   */
  @Test
  public void atmTest() {
    MultiCapFloorPricer pricer = new MultiCapFloorPricer(getATMCaps(), getYieldCurves());
    CapletStripper stripper = new CapletStripperInterpolatedTermStructure(pricer);
    testATMStripping(stripper, 0.0, null, 1e-15, true);

  }

  /**
   * Fit caps at each absolute strike in turn
   */
  @Test
  public void singleStrikeTest() {

    int n = getNumberOfStrikes();
    for (int i = 0; i < n; i++) {
      MultiCapFloorPricer pricer = new MultiCapFloorPricer(getCaps(i), getYieldCurves());
      CapletStripper stripper = new CapletStripperInterpolatedTermStructure(pricer);
      testSingleStrikeStripping(stripper, i, 0.0, null, 1e-15, false);
    }
  }

  /**
   * Try to fit all cap with a volatility surface that has no strike dependence.
   * Clearly the global fit in this case will not be good.
   */
  @Test
  public void globalFitTest() {
    double[] knots = new double[] {0.25, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    MultiCapFloorPricer pricer = new MultiCapFloorPricer(getAllCaps(), getYieldCurves());
    CapletStripper stripper = new CapletStripperInterpolatedTermStructure(pricer, knots);

    double[] vols = getAllCapVols();
    double[] prices = pricer.price(vols);
    double[] vega = pricer.vega(vols);
    int n = vols.length;
    double[] errors = new double[n];
    Arrays.fill(errors, 1e-3);// 10bps
    for (int i = 0; i < n; i++) {
      vega[i] *= errors[i];
    }

    CapletStrippingResult res1 = stripper.solve(prices, MarketDataType.PRICE, vega);
    assertEquals(287962.8189949142, res1.getChiSq(), 1e-15);
    // System.out.println(res1);
  }

  /**
   * check the Jacobian against the FD version
   */
  @Test
  public void jacobianTest() {
    double[] knots = new double[] {0.25, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    MultiCapFloorPricer pricer = new MultiCapFloorPricer(getAllCaps(), getYieldCurves());

    TransformedInterpolator1D interpolator = new TransformedInterpolator1D(
        CombinedInterpolatorExtrapolatorFactory.getInterpolator(DEFAULT_INTERPOLATOR, DEFAULT_EXTRAPOLATOR), TRANSFORM);
    DiscreteVolatilityFunctionProvider pro = new DiscreteVolatilityFunctionProviderFromInterpolatedTermStructure(knots,
        interpolator);
    CapletStrippingImp imp = new CapletStrippingImp(pricer, pro);

    Function1D<DoubleMatrix1D, DoubleMatrix1D> func = imp.getCapVolFunction();
    Function1D<DoubleMatrix1D, DoubleMatrix2D> jacFunc = imp.getCapVolJacobianFunction();
    Function1D<DoubleMatrix1D, DoubleMatrix2D> jacFuncFD = DIFF.differentiate(func);

    int n = knots.length;
    DoubleMatrix1D pos = new DoubleMatrix1D(n);

    int nSamples = 20;
    for (int run = 0; run < nSamples; run++) {
      for (int i = 0; i < n; i++) {
        pos.getData()[i] = 6 * RANDOM.nextDouble() - 3.0;
      }

      compareJacobianFunc(jacFunc, jacFuncFD, pos, 1e-5);
    }
  }
}
