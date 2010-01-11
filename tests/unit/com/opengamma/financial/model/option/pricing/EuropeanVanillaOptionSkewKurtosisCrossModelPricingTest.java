/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.model.option.pricing;

import java.util.Arrays;
import java.util.List;

import javax.time.calendar.ZonedDateTime;

import org.junit.Test;

import com.opengamma.financial.greeks.Greek;
import com.opengamma.financial.model.interestrate.curve.ConstantInterestRateDiscountCurve;
import com.opengamma.financial.model.option.definition.EdgeworthSkewKurtosisBinomialOptionModelDefinition;
import com.opengamma.financial.model.option.definition.EuropeanVanillaOptionDefinition;
import com.opengamma.financial.model.option.definition.OptionDefinition;
import com.opengamma.financial.model.option.definition.SkewKurtosisOptionDataBundle;
import com.opengamma.financial.model.option.pricing.analytic.ModifiedCorradoSuSkewnessKurtosisModel;
import com.opengamma.financial.model.option.pricing.tree.BinomialOptionModel;
import com.opengamma.financial.model.volatility.surface.ConstantVolatilitySurface;
import com.opengamma.util.time.DateUtil;
import com.opengamma.util.time.Expiry;

/**
 * 
 * @author emcleod
 */
public class EuropeanVanillaOptionSkewKurtosisCrossModelPricingTest {
  private static final Double STRIKE = 9.5;
  private static final ZonedDateTime DATE = DateUtil.getUTCDate(2009, 1, 1);
  private static final Expiry EXPIRY = new Expiry(DateUtil.getDateOffsetWithYearFraction(DATE, 0.5));
  private static final SkewKurtosisOptionDataBundle NORMAL_DATA = new SkewKurtosisOptionDataBundle(new ConstantInterestRateDiscountCurve(0.08), 0.08,
      new ConstantVolatilitySurface(0.3), 10., DATE, 0., 3.);
  private static final SkewKurtosisOptionDataBundle DATA = new SkewKurtosisOptionDataBundle(new ConstantInterestRateDiscountCurve(0.08), 0.08, new ConstantVolatilitySurface(0.3),
      10., DATE, 1., 3.);
  private static final List<Greek> REQUIRED_GREEKS = Arrays.asList(Greek.PRICE);

  @Test
  public void testNormal() {
    final OptionDefinition call = new EuropeanVanillaOptionDefinition(STRIKE, EXPIRY, true);
    final OptionModel<OptionDefinition, SkewKurtosisOptionDataBundle> model1 = new ModifiedCorradoSuSkewnessKurtosisModel();
    final OptionModel<OptionDefinition, SkewKurtosisOptionDataBundle> model2 = new BinomialOptionModel<SkewKurtosisOptionDataBundle>(10,
        new EdgeworthSkewKurtosisBinomialOptionModelDefinition());
    // System.out.println(model1.getGreeks(call, DATA, REQUIRED_GREEKS));
    // System.out.println(model2.getGreeks(call, DATA, REQUIRED_GREEKS));
    // System.out.println(new BlackScholesMertonModel().getGreeks(call, DATA,
    // REQUIRED_GREEKS));
  }
}
