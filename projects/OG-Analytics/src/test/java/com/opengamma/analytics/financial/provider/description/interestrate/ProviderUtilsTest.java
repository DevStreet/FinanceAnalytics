/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.provider.description.interestrate;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.Set;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.forex.datasets.StandardDataSetsEURUSDForex;
import com.opengamma.analytics.financial.forex.method.FXMatrixUtils;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.interestrate.datasets.StandardDataSetsMulticurveUSD;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldAndDiscountCurve;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldCurve;
import com.opengamma.analytics.financial.provider.curve.CurveBuildingBlockBundle;
import com.opengamma.analytics.math.curve.InterpolatedDoublesCurve;
import com.opengamma.analytics.math.interpolation.CombinedInterpolatorExtrapolatorFactory;
import com.opengamma.analytics.math.interpolation.Interpolator1D;
import com.opengamma.analytics.math.interpolation.Interpolator1DFactory;
import com.opengamma.util.money.Currency;
import com.opengamma.util.tuple.Pair;

/**
 * Tests the merge tools between different providers.
 */
public class ProviderUtilsTest {

  private static final Pair<MulticurveProviderDiscount, CurveBuildingBlockBundle> MULTICURVE_PAIR_USD136 = 
      StandardDataSetsMulticurveUSD.getCurvesUSDOisL1L3L6();
  private static final MulticurveProviderDiscount MULTICURVE_USD136 = MULTICURVE_PAIR_USD136.getFirst();
  private static final Pair<MulticurveProviderDiscount, CurveBuildingBlockBundle> MULTICURVE_PAIR_USDEUR_FX = 
      StandardDataSetsEURUSDForex.getCurvesUSDOisEURFx();
  private static final MulticurveProviderDiscount MULTICURVE_USDEUR_FX = MULTICURVE_PAIR_USDEUR_FX.getFirst();
  private static final Currency USD = Currency.USD;
  private static final Currency EUR = Currency.EUR;
  private static final IborIndex[] INDEX_USD136 = StandardDataSetsMulticurveUSD.indexIborArrayUSDOisL1L3L6();

  private static final double TOLERANCE_FX_RATE = 1.0E-10;

  @Test
  /**
   * Test merge of a single provider.
   */
  public void mergeUSD136() {
    final ArrayList<MulticurveProviderDiscount> multicurveList = new ArrayList<>();
    multicurveList.add(MULTICURVE_USD136);
    final MulticurveProviderDiscount merged1 = ProviderUtils.mergeDiscountingProviders(multicurveList);
    assertEquals("ProviderUtils - Merge multi-curve provider", MULTICURVE_USD136.getCurve(USD), merged1.getCurve(USD));
    for (int loopi = 0; loopi < INDEX_USD136.length; loopi++) {
      assertEquals("ProviderUtils - Merge multi-curve provider", MULTICURVE_USD136.getCurve(INDEX_USD136[loopi]), merged1.getCurve(INDEX_USD136[loopi]));
    }
    assertTrue("ProviderUtils - Merge multi-curve provider", FXMatrixUtils.compare(MULTICURVE_USD136.getFxRates(), merged1.getFxRates(), TOLERANCE_FX_RATE));
  }

  @Test
  /**
   * Test merge of a single provider.
   */
  public void mergeUSDEURForex() {
    final ArrayList<MulticurveProviderDiscount> multicurveList = new ArrayList<>();
    multicurveList.add(MULTICURVE_USDEUR_FX);
    final MulticurveProviderDiscount merged1 = ProviderUtils.mergeDiscountingProviders(multicurveList);
    assertEquals("ProviderUtils - Merge multi-curve provider", MULTICURVE_USDEUR_FX.getCurve(USD), merged1.getCurve(USD));
    assertEquals("ProviderUtils - Merge multi-curve provider", MULTICURVE_USDEUR_FX.getCurve(EUR), merged1.getCurve(EUR));
    assertTrue("ProviderUtils - Merge multi-curve provider", FXMatrixUtils.compare(MULTICURVE_USDEUR_FX.getFxRates(), merged1.getFxRates(), TOLERANCE_FX_RATE));
  }
  
  private static final Interpolator1D INTERPOLATOR_LINEAR = 
      CombinedInterpolatorExtrapolatorFactory.getInterpolator(Interpolator1DFactory.LINEAR, 
          Interpolator1DFactory.FLAT_EXTRAPOLATOR, Interpolator1DFactory.FLAT_EXTRAPOLATOR);
  private static final double TOLERANCE_DF = 5.0E-4;
  private static final double TOLERANCE_FWD = 1.0E-3;
  
  @Test
  /** Tests that a provider build as the forward curves of an existing provider at a future time returns the correct
   * discount factors and forward rates, up to the interpolation. The choice of nodes has a large impact on 
   * the results due to interpolation. */
  public void futureTimeProvider() {
    double futureTime = 1.25;
    double[] nodes = {0.25, 0.50, 0.70, 1.00, 2.00, 3.00, 5.00, 5.50, 6.00, 7.00, 8.00, 9.00, 10.00};
    MulticurveProviderDiscount forward = 
        ProviderUtils.multicurveFutureTime(MULTICURVE_USD136, futureTime, nodes, INTERPOLATOR_LINEAR);
    Set<String> names = MULTICURVE_USD136.getAllCurveNames();
    for(String name: names) {
     YieldAndDiscountCurve curveYD = forward.getCurve(name);
     assertTrue("correct Y&D curve type", curveYD instanceof YieldCurve);
     YieldCurve curveY = (YieldCurve) curveYD;
     assertTrue("correct Y curve underlying", curveY.getCurve() instanceof InterpolatedDoublesCurve);
     InterpolatedDoublesCurve interpolated = (InterpolatedDoublesCurve) curveY.getCurve();
     assertEquals("correct number of nodes", interpolated.getXData().length, nodes.length);
    }
    double[] testTimes = {0.25, 0.75, 1.25, 5.00, 7.53};
    int nbTests = testTimes.length;
    double dfFutureTime = MULTICURVE_USD136.getDiscountFactor(USD, futureTime);
    for(int i=0; i<nbTests; i++ ) {
      double dfComputed = forward.getDiscountFactor(USD, testTimes[i]);
      double dfExpected = MULTICURVE_USD136.getDiscountFactor(USD, futureTime + testTimes[i]) / dfFutureTime;
      assertEquals("Currency - discount factor " + i + ": ", dfExpected, dfComputed, TOLERANCE_DF);
    }
    double[] indexTenors = {1.0d / 12.0d, 0.25d, 0.51d };
    for (int j = 0; j < indexTenors.length; j++) {
      for (int i = 0; i < nbTests; i++) {
        double fwd3Computed = forward.getSimplyCompoundForwardRate(INDEX_USD136[j], testTimes[i],
            testTimes[i] + indexTenors[j], indexTenors[j]);
        double fwd3Expected = MULTICURVE_USD136.getSimplyCompoundForwardRate(INDEX_USD136[j],  futureTime + testTimes[i],
            futureTime + testTimes[i] + indexTenors[j], indexTenors[j]);
        assertEquals("Currency - forward " + INDEX_USD136[j].toString() + " - " + i + ": ", 
            fwd3Expected, fwd3Computed, TOLERANCE_FWD);
      }
    } 
  }

}
