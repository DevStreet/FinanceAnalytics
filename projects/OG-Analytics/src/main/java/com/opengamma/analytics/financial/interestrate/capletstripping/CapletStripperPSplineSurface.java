/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.capletstripping;

import java.util.Arrays;

import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunctionProvider;
import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunctionProviderFromVolSurface;
import com.opengamma.analytics.financial.model.volatility.surface.BasisSplineVolatilitySurfaceProvider;
import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.analytics.math.interpolation.PenaltyMatrixGenerator;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.analytics.math.minimization.PositiveOrZero;
import com.opengamma.util.ArgumentChecker;

/**
 * This represents the (caplet) volatility surface by 2D basis-splines, then applies a penalty on the curvature of this
 * surface (in both directions) and solves for the market cap values in a (penalised) least-square sense.  <p>
 * This currently does not work properly 
 */

public class CapletStripperPSplineSurface implements CapletStripper {
  private static final Function1D<DoubleMatrix1D, Boolean> POSITIVE = new PositiveOrZero();
  private final CapletStrippingCore _imp;
  private final DoubleMatrix2D _penalty;
  private final int _size;

  /**
   *
   * @param pricer The pricer (which contained the details of the market values of the caps/floors)
   * @param k1 The low strike value
   * @param k2 The upper strike value
   * @param nStrikeKnots Number of knots in strike direction
   * @param strikeDegree The degree of the basis-spline in the strike direction
   * @param t1 The low time
   * @param t2 The upper time
   * @param nTimeKnots Number of knots in time direction
   * @param timeDegree The degree of the basis-spline in the strike direction
   * @param lambda Strength of the penalty 
   */
  public CapletStripperPSplineSurface(MultiCapFloorPricer pricer, double k1, double k2, int nStrikeKnots, int strikeDegree, double t1, double t2,
      int nTimeKnots, int timeDegree, double lambda) {
    BasisSplineVolatilitySurfaceProvider vtsp = new BasisSplineVolatilitySurfaceProvider(k1, k2, nStrikeKnots, strikeDegree, t1, t2, nTimeKnots, timeDegree);
    DiscreteVolatilityFunctionProvider volFuncPro = new DiscreteVolatilityFunctionProviderFromVolSurface(vtsp);
    _imp = new CapletStrippingCore(pricer, volFuncPro);
    int tSize = nTimeKnots + timeDegree - 1;
    int kSize = nStrikeKnots + strikeDegree - 1;
    _size = vtsp.getNumModelParameters();
    ArgumentChecker.isTrue(_size == tSize * kSize, "something wrong");
    _penalty = PenaltyMatrixGenerator.getPenaltyMatrix(new int[] {kSize, tSize }, new int[] {strikeDegree, timeDegree },
        new double[] {lambda, lambda });
  }

  @Override
  public CapletStrippingResult solve(double[] marketValues, MarketDataType type) {
    ArgumentChecker.notNull(marketValues, "marketValues");
    int n = marketValues.length;
    double[] errors = new double[n];
    Arrays.fill(errors, 1.0);
    DoubleMatrix1D guess = new DoubleMatrix1D(_size, 0.4);
    return solve(marketValues, type, errors, guess);
  }

  @Override
  public CapletStrippingResult solve(double[] marketValues, MarketDataType type, double[] errors) {
    DoubleMatrix1D guess = new DoubleMatrix1D(_size, 0.4);
    return solve(marketValues, type, errors, guess);
  }

  @Override
  public CapletStrippingResult solve(double[] marketValues, MarketDataType type, DoubleMatrix1D guess) {
    ArgumentChecker.notNull(marketValues, "marketValues");
    int n = marketValues.length;
    double[] errors = new double[n];
    Arrays.fill(errors, 1.0);
    return solve(marketValues, type, errors, guess);
  }

  @Override
  public CapletStrippingResult solve(double[] marketValues, MarketDataType type, double[] errors, DoubleMatrix1D guess) {
    if (type == MarketDataType.PRICE) {
      return _imp.solveForCapPrices(marketValues, errors, guess, _penalty, POSITIVE);
    } else if (type == MarketDataType.VOL) {
      return _imp.solveForCapVols(marketValues, errors, guess, _penalty, POSITIVE);
    }
    throw new IllegalArgumentException("Unknown MarketDataType " + type.toString());
  }

}
