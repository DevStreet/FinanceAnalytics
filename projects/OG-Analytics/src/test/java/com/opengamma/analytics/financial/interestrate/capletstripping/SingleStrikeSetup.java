/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.capletstripping;


/**
 * 
 */
public class SingleStrikeSetup extends CapletStrippingSetup {

  public void testATMStripping(CapletStripper stripper, double expChi2, double[] expModelParms, double tol,
      boolean print) {
    testStripping(stripper, getATMCaps(), getATMCapPrices(), MarketDataType.PRICE, expChi2, expModelParms, tol, print);
  }

  public void testSingleStrikeStripping(CapletStripper stripper, int strikeIndex, double expChi2,
      double[] expModelParms, double tol, boolean print) {
    testStripping(stripper, getCaps(strikeIndex), getCapPrices(strikeIndex), MarketDataType.PRICE, expChi2,
        expModelParms, tol, print);
  }
}
