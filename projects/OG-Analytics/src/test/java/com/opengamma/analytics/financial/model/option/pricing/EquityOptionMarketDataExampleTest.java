/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.model.option.pricing;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.model.option.pricing.analytic.BjerksundStenslandModel;
import com.opengamma.util.test.TestGroup;

/**
 * Market data examples. See PLAT-6649 for detail.
 */
@Test(groups = TestGroup.UNIT)
public class EquityOptionMarketDataExampleTest {

  /**
   * Reproducing computation result of ListedEquityOptionRollGeskeWhaleyFunction, where put option is computed Bjerksund-Stensland model.
   * The objective option is -100 of IWM 2014-09-20 P 97.0
   * Print "shock(%)", "shocked spot", "PV", "positionDelta", "positionGamma", "positionTheta", "positionVega" in order 
   */
  @Test(enabled = false)
  public void IWM20140920P97Test() {
    final double nort = -100.0 * 100.;

    double spot = 114.155;
    final double strike = 97.0;
    final double expiry = 0.1643835616438356;
    final double rate = 9.631981674264262E-4;
    final double vol = 0.2710383459682947;
    final double cost = 0.0023205106480338926;

    final double[] shockSet = new double[] {-15., -12., -9., -6., -3., 0., 3., 6., 9., 12., 15., };

    final BjerksundStenslandModel bs = new BjerksundStenslandModel();

    for (int i = 0; i < shockSet.length; ++i) {
      final double shockedSpot = spot * (100. + shockSet[i]) * 0.01;
      final double[] res = bs.getPriceAdjoint(shockedSpot, strike, rate, cost, expiry, vol, false);
      final double[] res1 = bs.getPriceDeltaGamma(shockedSpot, strike, rate, cost, expiry, vol, false);
      System.out.println(shockSet[i] + "\t" + shockedSpot + "\t" + nort * res[0] + "\t" + nort * res[1] + "\t" + nort * res1[2] + "\t" + nort * (-res[5]) / 365. + "\t" + nort * res[6] / 100.);
    }
  }

  /**
   * Reproducing computation result of ListedEquityOptionRollGeskeWhaleyFunction, where put option is computed Bjerksund-Stensland model.
   * The objective option is 100 of IWM 2014-09-20 P 107.0
   * Print "shock(%)", "shocked spot", "PV", "positionDelta", "positionGamma", "positionTheta", "positionVega" in order 
   */
  @Test(enabled = false)
  public void IWM20140920P107Test() {
    final double nort = 100.0 * 100.;

    double spot = 114.155;
    final double strike = 107.0;
    final double expiry = 0.1643835616438356;
    final double rate = 9.631981674264262E-4;
    final double vol = 0.22560139161834528;
    final double cost = 0.0023205106480338926;

    final double[] shockSet = new double[] {-15., -12., -9., -6., -3., 0., 3., 6., 9., 12., 15., };

    final BjerksundStenslandModel bs = new BjerksundStenslandModel();

    for (int i = 0; i < shockSet.length; ++i) {
      final double shockedSpot = spot * (100. + shockSet[i]) * 0.01;
      final double[] res = bs.getPriceAdjoint(shockedSpot, strike, rate, cost, expiry, vol, false);
      final double[] res1 = bs.getPriceDeltaGamma(shockedSpot, strike, rate, cost, expiry, vol, false);
      System.out.println(shockSet[i] + "\t" + shockedSpot + "\t" + nort * res[0] + "\t" + nort * res[1] + "\t" + nort * res1[2] + "\t" + nort * (-res[5]) / 365. + "\t" + nort * res[6] / 100.);
    }
  }

}
