/**
 * /**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.varianceswap.demo;

import static com.opengamma.financial.convention.businessday.BusinessDayDateUtils.getWorkingDaysInclusive;
import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.engine.MersenneTwister64;
import cern.jet.random.engine.RandomEngine;

import com.opengamma.analytics.financial.datasets.CalendarTarget;
import com.opengamma.analytics.financial.equity.StaticReplicationDataBundle;
import com.opengamma.analytics.financial.equity.variance.EquityVarianceSwap;
import com.opengamma.analytics.financial.equity.variance.EquityVarianceSwapDefinition;
import com.opengamma.analytics.financial.equity.variance.pricing.RealizedVariance;
import com.opengamma.analytics.financial.equity.variance.pricing.VarianceSwapStaticReplication;
import com.opengamma.analytics.financial.instrument.varianceswap.VarianceSwapDefinition;
import com.opengamma.analytics.financial.model.interestrate.curve.ForwardCurve;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldAndDiscountCurve;
import com.opengamma.analytics.financial.model.interestrate.curve.YieldCurve;
import com.opengamma.analytics.financial.model.volatility.surface.BlackVolatilitySurfaceStrike;
import com.opengamma.analytics.financial.varianceswap.VarianceSwap;
import com.opengamma.analytics.math.curve.ConstantDoublesCurve;
import com.opengamma.analytics.math.statistics.distribution.NormalDistribution;
import com.opengamma.analytics.math.surface.ConstantDoublesSurface;
import com.opengamma.financial.convention.businessday.BusinessDayDateUtils;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.timeseries.date.localdate.ImmutableLocalDateDoubleTimeSeries;
import com.opengamma.timeseries.date.localdate.LocalDateDoubleTimeSeries;
import com.opengamma.util.money.Currency;

/**
 * 
 */
public class EquityVarianceSwapDemo {
  private static final RealizedVariance REALIZED_VOL_CAL = new RealizedVariance();
  private static final VarianceSwapStaticReplication PRICER = new VarianceSwapStaticReplication();
  private static final RandomEngine RANDOM = new MersenneTwister64(MersenneTwister.DEFAULT_SEED);
  private static final NormalDistribution NORMAL = new NormalDistribution(0, 1, RANDOM);
  private static final ZoneId UTC = ZoneId.of("UTC");

  private static final double s_Spot = 80;
  private static final double s_Drift = 0.05;
  private static final double s_Vol = 0.3;
  private static final ForwardCurve s_FwdCurve = new ForwardCurve(s_Spot, s_Drift);
  private static final YieldAndDiscountCurve s_DiscountCurve = new YieldCurve("Discount", ConstantDoublesCurve.from(s_Drift));
  private static final BlackVolatilitySurfaceStrike s_FlatVolSurf = new BlackVolatilitySurfaceStrike(ConstantDoublesSurface.from(s_Vol));

  private static final ZonedDateTime s_ObsStartTime = ZonedDateTime.of(2013, 12, 16, 12, 0, 0, 0, UTC);// ZonedDateTime.of(2013, 7, 27, 12, 0, 0, 0, UTC); // Saturday
  private static final ZonedDateTime s_ObsEndTime = ZonedDateTime.of(2015, 7, 30, 12, 0, 0, 0, UTC); // Thursday
  private static final ZonedDateTime s_SettlementTime = ZonedDateTime.of(2015, 8, 3, 12, 0, 0, 0, UTC);// Monday
  private static final Currency s_Ccy = Currency.EUR;
  private static final Calendar s_Calendar = new CalendarTarget("Eur");
  private static final double s_AnnualizationFactor = 252.0;
  private static final double s_VolStrike = 0.3;
  private static final double s_VolNotional = 1e6;

  /**
   * Demonstrate building an equity variance swap and adding time series of observations. Check that the realized variance
   * and present value of the swap are as expected when all the observations are known
   */
  @Test
  public void buildSwap() {
    EquityVarianceSwapDefinition def = new EquityVarianceSwapDefinition(s_ObsStartTime, s_ObsEndTime, s_SettlementTime, s_Ccy, s_Calendar, s_AnnualizationFactor, s_VolStrike, s_VolNotional, false);

    ZonedDateTime obsDate = ZonedDateTime.of(2014, 8, 11, 12, 0, 0, 0, UTC);
    EquityVarianceSwap varSwap = def.toDerivative(obsDate);
    System.out.println("time to observation start: " + varSwap.getTimeToObsStart());
    System.out.println("time to observation end: " + varSwap.getTimeToObsEnd());
    System.out.println("time to settlement: " + varSwap.getTimeToSettlement());
    System.out.println("Var Notional: " + varSwap.getVarNotional());
    System.out.println("Vol Notional: " + varSwap.getVolNotional());
    System.out.println("Annualization Factor: " + varSwap.getAnnualizationFactor());

    // we haven't added any observations, so all historical observations are treated as disrupted
    System.out.println("Observations disrupted: " + varSwap.getObsDisrupted());
    System.out.println("Observations expected: " + varSwap.getObsExpected());
    double[] obs = varSwap.getObservations();
    System.out.println("Observations: " + obs.length);

    // now add some randomly generated observations, and compute some values on the settlement date (i.e. all observations
    // are in the past)
    int observationDays = getWorkingDaysInclusive(s_ObsStartTime, s_ObsEndTime, s_Calendar);

    System.out.println("\nObsevations added: " + observationDays);
    LocalDate[] dates = new LocalDate[observationDays];
    double[] Prices = new double[observationDays];
    double[] logPrices = new double[observationDays];

    double dailyDrift = (s_Drift - 0.5 * s_Vol * s_Vol) / s_AnnualizationFactor;
    double dailySD = s_Vol / Math.sqrt(s_AnnualizationFactor);

    dates[0] = s_ObsStartTime.toLocalDate();
    Prices[0] = 100.0;
    logPrices[0] = Math.log(100.0);
    double sum2 = 0;
    for (int i = 1; i < observationDays; i++) {
      dates[i] = BusinessDayDateUtils.addWorkDays(dates[i - 1], 1, s_Calendar);
      logPrices[i] = logPrices[i - 1] + dailyDrift + dailySD * NORMAL.nextRandom();
      Prices[i] = Math.exp(logPrices[i]);
      double rtn = logPrices[i] - logPrices[i - 1];
      sum2 += rtn * rtn;
    }

    LocalDateDoubleTimeSeries ts = ImmutableLocalDateDoubleTimeSeries.of(dates, Prices);
    varSwap = def.toDerivative(s_SettlementTime, ts);

    System.out.println("Observations disrupted: " + varSwap.getObsDisrupted());
    System.out.println("Observations expected: " + varSwap.getObsExpected());
    obs = varSwap.getObservations();
    System.out.println("Observations: " + obs.length);

    double relVar = REALIZED_VOL_CAL.evaluate(varSwap);
    // even with B-S dynamics, the realized variance will differ from the expected variance
    System.out.println("Expected variance: " + s_Vol * s_Vol + ", realized variance: " + relVar);
    double calRelVar = s_AnnualizationFactor / (observationDays - 1) * sum2;
    assertEquals(calRelVar, relVar, 1e-15); // check the calculation inside RealizedVariance is correct

    // check the price computed by VarianceSwapStaticReplication is as expected when all the observations are known
    double calPV = s_VolNotional / 2 / s_VolStrike * (calRelVar - s_VolStrike * s_VolStrike);
    StaticReplicationDataBundle market = new StaticReplicationDataBundle(s_FlatVolSurf, s_DiscountCurve, s_FwdCurve);
    double pv = PRICER.presentValue(varSwap, market);
    System.out.println("Variance swap value at settlement: " + pv);
    assertEquals(calPV, pv, 1e-9);
  }

  /**
   * The expected variance is computed by static replication - integration over vanilla option prices. These prices are
   * derived from a volatility surface which is flat at 30% - hence we should recover (up to some numerical tolerance)
   * 0.3^2 for the expected variance.
   */
  @Test
  public void flatVolPrice() {
    VarianceSwapDefinition def = new VarianceSwapDefinition(s_ObsStartTime, s_ObsEndTime, s_SettlementTime, s_Ccy, s_Calendar, s_AnnualizationFactor, s_VolStrike, s_VolNotional);
    ZonedDateTime valueDate = ZonedDateTime.of(2013, 7, 25, 12, 0, 0, 0, UTC); // before first observation
    VarianceSwap varSwap = def.toDerivative(valueDate);

    assertEquals(0.0, REALIZED_VOL_CAL.evaluate(varSwap)); // No observations yet made, so zero realized volatility

    StaticReplicationDataBundle market = new StaticReplicationDataBundle(s_FlatVolSurf, s_DiscountCurve, s_FwdCurve);
    assertEquals(s_Vol * s_Vol, PRICER.expectedVariance(varSwap, market), 1e-10);

    // now look at a seasoned swap
    valueDate = ZonedDateTime.of(2014, 1, 28, 12, 0, 0, 0, UTC); // Tue

    // Don't include the valueDate in the observations
    int observationDays = BusinessDayDateUtils.getDaysBetween(s_ObsStartTime, valueDate, s_Calendar);

    System.out.println("\nObsevations added: " + observationDays);
    LocalDate[] dates = new LocalDate[observationDays];
    double[] Prices = new double[observationDays];
    double[] logPrices = new double[observationDays];

    double dailyDrift = (s_Drift - 0.5 * s_Vol * s_Vol) / s_AnnualizationFactor;
    double dailySD = s_Vol / Math.sqrt(s_AnnualizationFactor);

    dates[0] = s_ObsStartTime.toLocalDate();
    Prices[0] = 100.0;
    logPrices[0] = Math.log(100.0);
    double sum2 = 0;
    for (int i = 1; i < observationDays; i++) {
      dates[i] = BusinessDayDateUtils.addWorkDays(dates[i - 1], 1, s_Calendar);
      logPrices[i] = logPrices[i - 1] + dailyDrift + dailySD * NORMAL.nextRandom();
      Prices[i] = Math.exp(logPrices[i]);
      double rtn = logPrices[i] - logPrices[i - 1];
      sum2 += rtn * rtn;
    }

    LocalDateDoubleTimeSeries ts = ImmutableLocalDateDoubleTimeSeries.of(dates, Prices);
    varSwap = def.toDerivative(valueDate, ts);

    double relVar = (observationDays - 1) / s_AnnualizationFactor * REALIZED_VOL_CAL.evaluate(varSwap);
    assertEquals(relVar, sum2, 1e-14);

    //Compute the price using the observations we have and the knowledge that volatility surface is flat (at 30%), and
    //compare this with the result of the calculator (which integrates over the vanilla option prices)
    double df = market.getDiscountCurve().getDiscountFactor(varSwap.getTimeToSettlement());
    double expVar = (s_AnnualizationFactor * sum2 + s_Vol * s_Vol * (varSwap.getObsExpected() - observationDays)) / (varSwap.getObsExpected() - 1);
    double expPV = df * s_VolNotional / 2 / s_VolStrike * (expVar - s_VolStrike * s_VolStrike);
    double pv = PRICER.presentValue(varSwap, market);
    assertEquals(expPV, pv, 1e-5);
  }

}
