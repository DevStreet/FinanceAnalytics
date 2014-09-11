/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.varianceswap.demo;

import org.testng.annotations.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.datasets.CalendarTarget;
import com.opengamma.analytics.financial.equity.variance.EquityVarianceSwap;
import com.opengamma.analytics.financial.equity.variance.EquityVarianceSwapDefinition;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.daycount.BusinessTwoFiveTwo;
import com.opengamma.financial.convention.frequency.PeriodFrequency;
import com.opengamma.timeseries.date.localdate.ImmutableLocalDateDoubleTimeSeries;
import com.opengamma.timeseries.date.localdate.LocalDateDoubleTimeSeries;
import com.opengamma.util.money.Currency;

/**
 * 
 */
public class EquityVarianceSwapDemo {
  private static final ZoneId UTC = ZoneId.of("UTC");
  private static final ZonedDateTime s_ObsStartTime = ZonedDateTime.of(2013, 7, 30, 12, 0, 0, 0, UTC);
  private static final ZonedDateTime s_ObsEndTime = ZonedDateTime.of(2015, 7, 30, 12, 0, 0, 0, UTC);
  private static final ZonedDateTime s_SettlementTime = ZonedDateTime.of(2015, 8, 1, 12, 0, 0, 0, UTC);
  private static final PeriodFrequency s_ObsFreq = PeriodFrequency.DAILY;
  private static final Currency s_Ccy = Currency.EUR;
  private static final Calendar s_Calendar = new CalendarTarget("Eur");
  private static final double s_AnnualizationFactor = 252.0;
  private static final double s_VolStrike = 0.3;
  private static final double s_VolNotional = 1e6;

  @Test
  public void buildSwap() {
    EquityVarianceSwapDefinition def = new EquityVarianceSwapDefinition(s_ObsStartTime, s_ObsEndTime, s_SettlementTime, s_ObsFreq, s_Ccy, s_Calendar, s_AnnualizationFactor, s_VolStrike,
        s_VolNotional, false);

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


    BusinessTwoFiveTwo b252 = new BusinessTwoFiveTwo();
    int observationDays =  (int) (b252.getDayCountFraction(s_ObsStartTime, obsDate, s_Calendar)*252);

    final LocalDateDoubleTimeSeries ts = ImmutableLocalDateDoubleTimeSeries.of(dates, vars);


    def.toDerivative(obsDate, null);
  }

}
