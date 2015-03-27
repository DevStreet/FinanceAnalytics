/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.provider.description.interestrate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opengamma.analytics.financial.forex.method.FXMatrix;
import com.opengamma.analytics.financial.forex.method.FXMatrixUtils;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.instrument.index.IndexON;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldAndDiscountCurve;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldCurve;
import com.opengamma.analytics.math.curve.InterpolatedDoublesCurve;
import com.opengamma.analytics.math.interpolation.Interpolator1D;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;

/**
 * Utility class for providers. 
 * This is a temporary class and will be removed when the providers have been refactored.
 */
public class ProviderUtils {

  /* The minimal node level below which a special treatment is used to avoid dividing by 0. */
  private static final double MIN_NODE = 1.0E-4;
  /* The small increment used to compute the time derivative of the curves. */
  private static final double DT = 1.0E-5;

  /**
   * Merges discounting curve providers. 
   * If a currency or index appears twice in the providers, an error is returned.
   * The FXMatrix are also merged.
   * @param providers The providers to merge, not null or empty
   * @return The merged providers
   */
  public static MulticurveProviderDiscount mergeDiscountingProviders(final Collection<MulticurveProviderDiscount> providers) {
    ArgumentChecker.notNull(providers, "providers");
    ArgumentChecker.notEmpty(providers, "providers");
    final MulticurveProviderDiscount result = new MulticurveProviderDiscount();
    FXMatrix matrix = new FXMatrix();
    int loop = 0;
    for (final MulticurveProviderDiscount provider : providers) {
      for (final Map.Entry<Currency, YieldAndDiscountCurve> entry : provider.getDiscountingCurves().entrySet()) {
        result.setCurve(entry.getKey(), entry.getValue());
      }
      for (final Map.Entry<IborIndex, YieldAndDiscountCurve> entry : provider.getForwardIborCurves().entrySet()) {
        result.setCurve(entry.getKey(), entry.getValue());
      }
      for (final Map.Entry<IndexON, YieldAndDiscountCurve> entry : provider.getForwardONCurves().entrySet()) {
        result.setCurve(entry.getKey(), entry.getValue());
      }
      if (loop == 0) {
        matrix = new FXMatrix(provider.getFxRates());
        loop++;
      } else {
        matrix = FXMatrixUtils.merge(matrix, provider.getFxRates());
      }
    }
    result.setForexMatrix(matrix);
    return result;
  }

  /**
   * Merges discounting curve providers. 
   * If a currency or index appears twice in the providers, the curve in the last instance is used.
   * The FXMatrix are also merged.
   * @param providers The providers to merge, not null or empty
   * @return The merged providers
   */
  public static MulticurveProviderDiscount mergeWithDuplicateDiscountingProviders(final Collection<MulticurveProviderDiscount> providers) {
    ArgumentChecker.notNull(providers, "providers");
    ArgumentChecker.notEmpty(providers, "providers");
    final MulticurveProviderDiscount result = new MulticurveProviderDiscount();
    FXMatrix matrix = new FXMatrix();
    int loop = 0;
    for (final MulticurveProviderDiscount provider : providers) {
      for (final Map.Entry<Currency, YieldAndDiscountCurve> entry : provider.getDiscountingCurves().entrySet()) {
        result.setOrReplaceCurve(entry.getKey(), entry.getValue());
      }
      for (final Map.Entry<IborIndex, YieldAndDiscountCurve> entry : provider.getForwardIborCurves().entrySet()) {
        result.setOrReplaceCurve(entry.getKey(), entry.getValue());
      }
      for (final Map.Entry<IndexON, YieldAndDiscountCurve> entry : provider.getForwardONCurves().entrySet()) {
        result.setOrReplaceCurve(entry.getKey(), entry.getValue());
      }
      if (loop == 0) {
        matrix = new FXMatrix(provider.getFxRates());
        loop++;
      } else {
        matrix = FXMatrixUtils.merge(matrix, provider.getFxRates());
      }
    }
    result.setForexMatrix(matrix);
    return result;
  }

  /**
   * Merges Hull-White one-factor providers.
   * @param providers The providers to merge, not null or empty
   * @return The merged providers
   */
  public static HullWhiteOneFactorProviderDiscount mergeHullWhiteProviders(final Collection<HullWhiteOneFactorProviderDiscount> providers) {
    ArgumentChecker.notNull(providers, "providers");
    ArgumentChecker.notEmpty(providers, "providers");
    final Iterator<HullWhiteOneFactorProviderDiscount> iter = providers.iterator();
    final HullWhiteOneFactorProviderDiscount result = iter.next().copy();
    while (iter.hasNext()) {
      final HullWhiteOneFactorProviderDiscount provider = iter.next().copy();
      final MulticurveProviderDiscount underlying = provider.getMulticurveProvider().copy();
      for (final Map.Entry<Currency, YieldAndDiscountCurve> entry : underlying.getDiscountingCurves().entrySet()) {
        result.setCurve(entry.getKey(), entry.getValue());
      }
      for (final Map.Entry<IborIndex, YieldAndDiscountCurve> entry : underlying.getForwardIborCurves().entrySet()) {
        result.setCurve(entry.getKey(), entry.getValue());
      }
      for (final Map.Entry<IndexON, YieldAndDiscountCurve> entry : underlying.getForwardONCurves().entrySet()) {
        result.setCurve(entry.getKey(), entry.getValue());
      }
      final FXMatrix matrix = underlying.getFxRates();
      final Collection<Currency> currencies = matrix.getCurrencies().keySet();
      final Iterator<Currency> iterator = currencies.iterator();
      if (currencies.size() > 0) {
        final Currency initialCurrency = iterator.next();
        while (iterator.hasNext()) {
          final Currency otherCurrency = iterator.next();
          underlying.getFxRates().addCurrency(initialCurrency, otherCurrency, matrix.getFxRate(initialCurrency, otherCurrency));
        }
      }
      //TODO actually merge.
    }
    return result;
  }

  /**
   * Merges a discounting curve provider and an FX matrix.
   * @param provider The provider, not null
   * @param matrix The FX matrix, not null
   * @return The merged provider
   */
  public static MulticurveProviderDiscount mergeDiscountingProviders(final MulticurveProviderDiscount provider, final FXMatrix matrix) {
    ArgumentChecker.notNull(provider, "provider");
    ArgumentChecker.notNull(matrix, "matrix");
    final MulticurveProviderDiscount result = provider.copy();
    final FXMatrix fxMatrix = FXMatrixUtils.merge(provider.getFxRates(), matrix);
    result.setForexMatrix(fxMatrix);
    return result;
  }

  /**
   * Merges a Hull-White curve provider and an FX matrix.
   * @param provider The provider, not null
   * @param matrix The FX matrix, not null
   * @return The merged provider
   */
  public static HullWhiteOneFactorProviderDiscount mergeHullWhiteProviders(final HullWhiteOneFactorProviderDiscount provider, final FXMatrix matrix) {
    ArgumentChecker.notNull(provider, "provider");
    ArgumentChecker.notNull(matrix, "matrix");
    final HullWhiteOneFactorProviderDiscount result = provider.copy();
    final FXMatrix fxMatrix = FXMatrixUtils.merge(provider.getMulticurveProvider().getFxRates(), matrix);
    result.getMulticurveProvider().setForexMatrix(fxMatrix);
    return result;
  }
  
  /**
   * Construct a multi-curve provider based on an existing one and a future time T. The new provider is, up to the 
   * interpolation, composed of the forward curves at the given time.
   * The (pseudo-)discount factors are given by the ratios 
   *   forward-multicurve.discountFactor(t) = base-multicurve.discountFactor(t+T) / base-multicurve.discountFactor(T).
   * @param base The base multi-curve provider. 
   * @param time The future time. Time should be positive.
   * @param nodes The nodes for the curves at the future date. The nodes should be positive and in increasing order.
   * @param interpolator The interpolator for the curves at the future date.
   * @return The multi-curve provider.
   */
  public static MulticurveProviderDiscount multicurveFutureTime(MulticurveProviderDiscount base, double time,
      double[] nodes, Interpolator1D interpolator) {
    ArgumentChecker.isTrue(nodes[0] >= 0, "nodes should be positive.");
    ArgumentChecker.isTrue(time >= 0, "future time should be positive.");
    int nbNodes = nodes.length;
    Set<String> names = base.getAllNames();
    MulticurveProviderDiscount multicurveForward = new MulticurveProviderDiscount(base.getFxRates());
    for (String name : names) {
      YieldAndDiscountCurve curve = base.getCurve(name);
      double[] zr = new double[nbNodes];
      for (int i = 0; i < nbNodes; i++) {
        if (nodes[i] < MIN_NODE) {
          double r0T = curve.getInterestRate(time);
          double drdT = (curve.getInterestRate(time + DT) - r0T) / DT;
          zr[i] = drdT * time + r0T;
        } else {
          zr[i] = (curve.getInterestRate(nodes[i] + time) * (nodes[i] + time) - curve.getInterestRate(time) * time)
              / nodes[i];
        }
      }
      YieldCurve curveForward = new YieldCurve(name, new InterpolatedDoublesCurve(nodes, zr, interpolator, true));
      List<Currency> nameCcys = base.getCurrencyForName(name);
      List<IborIndex> nameIbors = base.getIborIndexForName(name);
      List<IndexON> nameOns = base.getOvernightIndexForName(name);
      for (Currency ccy : nameCcys) {
        multicurveForward.setCurve(ccy, curveForward);
      }
      for (IborIndex index : nameIbors) {
        multicurveForward.setCurve(index, curveForward);
      }
      for (IndexON index : nameOns) {
        multicurveForward.setCurve(index, curveForward);
      }
    }
    return multicurveForward;
  }

}
