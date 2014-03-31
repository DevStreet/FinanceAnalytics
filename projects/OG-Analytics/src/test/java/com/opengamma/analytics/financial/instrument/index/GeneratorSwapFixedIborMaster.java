/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.datasets.CalendarTarget;
import com.opengamma.analytics.financial.datasets.CalendarUSD;
import com.opengamma.financial.convention.StubType;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConventions;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.calendar.MondayToFridayCalendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCounts;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Tenor;

/**
 * A list of swap generators that can be used in the tests.
 */
public final class GeneratorSwapFixedIborMaster {

  /**
   * The method unique instance.
   */
  private static final GeneratorSwapFixedIborMaster INSTANCE = new GeneratorSwapFixedIborMaster();

  /**
   * Return the unique instance of the class.
   * @return The instance.
   */
  public static GeneratorSwapFixedIborMaster getInstance() {
    return INSTANCE;
  }

  /**
   * The map with the list of names and the swap generators.
   */
  private final Map<String, GeneratorSwapFixedIbor> _generatorSwap;

  /**
   * Private constructor.
   */
  private GeneratorSwapFixedIborMaster() {

    /** The list of Ibor indexes for test purposes. */
    final IndexIborMaster MASTER_IBOR = IndexIborMaster.getInstance();
    final DayCount DC30U_360 = DayCounts.THIRTY_U_360;
    final DayCount DCACT360 = DayCounts.ACT_360;
    final DayCount DCACT365 = DayCounts.ACT_365;
    final BusinessDayConvention MOD_FOL = BusinessDayConventions.MODIFIED_FOLLOWING;
    final StubType STUB = StubType.SHORT_START;
    final int PAY_LAG = 0;
    final boolean IS_EOM = true;
    // London
    final Calendar LON = new MondayToFridayCalendar("LON");
    // TODO: AUD
    final int SPOT_LAG_AUD = 0;
    final Calendar SYD = new CalendarTarget("SYD");
    final Currency AUD = Currency.AUD;
    final IborIndex AUDBBSW3M = MASTER_IBOR.getIndex("AUDBBSW3M");
    final IborIndex AUDBBSW6M = MASTER_IBOR.getIndex("AUDBBSW6M");
    final  String NAME_AUD3MFixed = "AUD3MFixed";
    final GeneratorLegFixed AUD3MFixed = new GeneratorLegFixed(NAME_AUD3MFixed, Tenor.THREE_MONTHS, DCACT365, MOD_FOL, AUD, IS_EOM, 
        SPOT_LAG_AUD, SYD, STUB, false, PAY_LAG);
    final  String NAME_AUD6MFixed = "AUD6MFixed";
    final GeneratorLegFixed AUD6MFixed = new GeneratorLegFixed(NAME_AUD6MFixed, Tenor.SIX_MONTHS, DCACT365, MOD_FOL, AUD, IS_EOM, 
        SPOT_LAG_AUD, SYD, STUB, false, PAY_LAG);
    final  String NAME_AUDBBSW3MLeg = "AUDBBSW3M";
    final GeneratorLegIbor AUDBBSW3MLeg = new GeneratorLegIbor(NAME_AUDBBSW3MLeg, AUDBBSW3M, SYD, SYD, Tenor.THREE_MONTHS, DCACT365, 
        MOD_FOL, AUD, IS_EOM, SPOT_LAG_AUD, SYD, STUB, false, PAY_LAG);
    final  String NAME_AUDBBSW6MLeg = "AUDBBSW6M";
     final GeneratorLegIbor AUDBBSW6MLeg = new GeneratorLegIbor(NAME_AUDBBSW6MLeg, AUDBBSW6M, SYD, SYD, Tenor.SIX_MONTHS, DCACT365, 
        MOD_FOL, AUD, IS_EOM, SPOT_LAG_AUD, SYD, STUB, false, PAY_LAG);
    // EUR
    final int SPOT_LAG_EUR = 2;
    final Calendar TARGET = new CalendarTarget("TARGET");
    final Currency EUR = Currency.EUR;
    final IborIndex EUREURIBOR1M = MASTER_IBOR.getIndex("EURIBOR1M");
    final IborIndex EUREURIBOR3M = MASTER_IBOR.getIndex("EURIBOR3M");
    final IborIndex EUREURIBOR6M = MASTER_IBOR.getIndex("EURIBOR6M");
    final  String NAME_EUR1YFixed = "EUR1YFixed";
    final GeneratorLegFixed EUR1YFixed = new GeneratorLegFixed(NAME_EUR1YFixed, Tenor.ONE_YEAR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG_EUR, TARGET, STUB, false, PAY_LAG);
    final  String NAME_EUREURIBOR1MLeg = "EUREURIBOR1M";
    final GeneratorLegIbor EUREURIBOR1MLeg = new GeneratorLegIbor(NAME_EUREURIBOR1MLeg, EUREURIBOR1M, TARGET, TARGET, Tenor.ONE_MONTH, DCACT360, 
        MOD_FOL, EUR, IS_EOM, SPOT_LAG_EUR, TARGET, STUB, false, PAY_LAG);
    final  String NAME_EUREURIBOR3MLeg = "EUREURIBOR3M";
    final GeneratorLegIbor EUREURIBOR3MLeg = new GeneratorLegIbor(NAME_EUREURIBOR3MLeg, EUREURIBOR3M, TARGET, TARGET, Tenor.THREE_MONTHS, DCACT360, 
        MOD_FOL, EUR, IS_EOM, SPOT_LAG_EUR, TARGET, STUB, false, PAY_LAG);
    final  String NAME_EUREURIBOR6MLeg = "EUREURIBOR6M";
    final GeneratorLegIbor EUREURIBOR6MLeg = new GeneratorLegIbor(NAME_EUREURIBOR6MLeg, EUREURIBOR6M, TARGET, TARGET, Tenor.SIX_MONTHS, DCACT360, 
        MOD_FOL, EUR, IS_EOM, SPOT_LAG_EUR, TARGET, STUB, false, PAY_LAG);
    // GBP
    final int SPOT_LAG_GBP = 0;
    final Currency GBP = Currency.GBP;
    final IborIndex GBPLIBOR1M = MASTER_IBOR.getIndex("GBPLIBOR1M");
    final IborIndex GBPLIBOR3M = MASTER_IBOR.getIndex("GBPLIBOR3M");
    final IborIndex GBPLIBOR6M = MASTER_IBOR.getIndex("GBPLIBOR6M");
    final  String NAME_GBP3MFixed = "GBP3MFixed";
    final GeneratorLegFixed GBP3MFixed = new GeneratorLegFixed(NAME_GBP3MFixed, Tenor.THREE_MONTHS, DCACT365, MOD_FOL, GBP, IS_EOM, 
        SPOT_LAG_GBP, LON, STUB, false, PAY_LAG);
    final  String NAME_GBP6MFixed = "GBP6MFixed";
    final GeneratorLegFixed GBP6MFixed = new GeneratorLegFixed(NAME_GBP6MFixed, Tenor.SIX_MONTHS, DCACT365, MOD_FOL, GBP, IS_EOM, 
        SPOT_LAG_GBP, LON, STUB, false, PAY_LAG);
    final  String NAME_GBPLIBOR1MLeg = "GBPLIBOR1M";
    final GeneratorLegIbor GBPLIBOR1MLeg = new GeneratorLegIbor(NAME_GBPLIBOR1MLeg, GBPLIBOR1M, LON, LON, Tenor.ONE_MONTH, DCACT365, 
        MOD_FOL, GBP, IS_EOM, SPOT_LAG_GBP, LON, STUB, false, PAY_LAG);
    final  String NAME_GBPLIBOR3MLeg = "GBPLIBOR3M";
    final GeneratorLegIbor GBPLIBOR3MLeg = new GeneratorLegIbor(NAME_GBPLIBOR3MLeg, GBPLIBOR3M, LON, LON, Tenor.THREE_MONTHS, DCACT365, 
        MOD_FOL, GBP, IS_EOM, SPOT_LAG_GBP, LON, STUB, false, PAY_LAG);
    final  String NAME_GBPLIBOR6MLeg = "GBPLIBOR6M";
    final GeneratorLegIbor GBPLIBOR6MLeg = new GeneratorLegIbor(NAME_GBPLIBOR6MLeg, GBPLIBOR6M, LON, LON, Tenor.SIX_MONTHS, DCACT365, 
        MOD_FOL, GBP, IS_EOM, SPOT_LAG_GBP, LON, STUB, false, PAY_LAG);
    // TODO: JPY
    // USD
    final int SPOT_LAG_USD = 2;
    final Calendar NYC = new CalendarUSD("NYC");
    final Calendar NYCLON = new CalendarUSD("NYCLON");
    final Currency USD = Currency.USD;
    final IborIndex USDLIBOR1M = MASTER_IBOR.getIndex("USDLIBOR1M");
    final IborIndex USDLIBOR3M = MASTER_IBOR.getIndex("USDLIBOR3M");
    final IborIndex USDLIBOR6M = MASTER_IBOR.getIndex("USDLIBOR6M");
    final  String NAME_USD6MFixed = "USD6MFixed";
    final GeneratorLegFixed USD6MFixed = new GeneratorLegFixed(NAME_USD6MFixed, Tenor.SIX_MONTHS, DC30U_360, MOD_FOL, USD, IS_EOM, 
        SPOT_LAG_USD, NYC, STUB, false, PAY_LAG);
    final  String NAME_USDLIBOR1M = "USDLIBOR1MLeg";
    final GeneratorLegIbor USDLIBOR1MLeg = new GeneratorLegIbor(NAME_USDLIBOR1M, USDLIBOR1M, NYC, LON, Tenor.ONE_MONTH, DCACT360, 
        MOD_FOL, USD, IS_EOM, SPOT_LAG_USD, NYCLON, STUB, false, PAY_LAG);
    final  String NAME_USDLIBOR3M = "USDLIBOR3MLeg";
    final GeneratorLegIbor USDLIBOR3MLeg = new GeneratorLegIbor(NAME_USDLIBOR3M, USDLIBOR3M, NYC, LON, Tenor.THREE_MONTHS, DCACT360, 
        MOD_FOL, USD, IS_EOM, SPOT_LAG_USD, NYCLON, STUB, false, PAY_LAG);
    final  String NAME_USDLIBOR6M = "USDLIBOR6MLeg";
    final GeneratorLegIbor USDLIBOR6MLeg = new GeneratorLegIbor(NAME_USDLIBOR6M, USDLIBOR6M, NYC, LON, Tenor.SIX_MONTHS, DCACT360, 
        MOD_FOL, USD, IS_EOM, SPOT_LAG_USD, NYCLON, STUB, false, PAY_LAG);
    
    _generatorSwap = new HashMap<>();
    _generatorSwap.put("EUR1YEURIBOR1M", new GeneratorSwapFixedIbor("EUR1YEURIBOR1M", EUR1YFixed, EUREURIBOR1MLeg));
    _generatorSwap.put("EUR1YEURIBOR3M", new GeneratorSwapFixedIbor("EUR1YEURIBOR3M", EUR1YFixed, EUREURIBOR3MLeg));
    _generatorSwap.put("EUR1YEURIBOR6M", new GeneratorSwapFixedIbor("EUR1YEURIBOR6M", EUR1YFixed, EUREURIBOR6MLeg));
    _generatorSwap.put("USD6MLIBOR1M", new GeneratorSwapFixedIbor("USD6MLIBOR1M", USD6MFixed, USDLIBOR1MLeg));
    _generatorSwap.put("USD6MLIBOR1M", new GeneratorSwapFixedIbor("USD6MLIBOR1M", USD6MFixed, USDLIBOR3MLeg));
    _generatorSwap.put("USD6MLIBOR3M", new GeneratorSwapFixedIbor("USD6MLIBOR3M", USD6MFixed, USDLIBOR6MLeg));
    _generatorSwap.put("GBP6MLIBOR1M", new GeneratorSwapFixedIbor("GBP6MLIBOR1M", GBP6MFixed, GBPLIBOR1MLeg));
    _generatorSwap.put("GBP3MLIBOR3M", new GeneratorSwapFixedIbor("GBP3MLIBOR3M", GBP3MFixed, GBPLIBOR3MLeg));
    _generatorSwap.put("GBP6MLIBOR6M", new GeneratorSwapFixedIbor("GBP6MLIBOR6M", GBP6MFixed, GBPLIBOR6MLeg));
    _generatorSwap.put("AUD3MBBSW3M", new GeneratorSwapFixedIbor("AUD3MBBSW3M", AUD3MFixed, AUDBBSW3MLeg));
    _generatorSwap.put("AUD6MBBSW6M", new GeneratorSwapFixedIbor("AUD6MBBSW6M", AUD6MFixed, AUDBBSW6MLeg));
//    _generatorSwap.put("DKK1YCIBOR6M",
//        new GeneratorSwapFixedIbor("DKK1YCIBOR6M", Period.ofMonths(12), DayCounts.THIRTY_U_360, _iborIndexMaster.getIndex("DKKCIBOR6M"), baseCalendar));
//    _generatorSwap.put("JPY6MLIBOR3M",
//        new GeneratorSwapFixedIbor("JPY6MLIBOR3M", Period.ofMonths(6), DayCounts.ACT_365, _iborIndexMaster.getIndex("JPYLIBOR3M"), baseCalendar));
//    _generatorSwap.put("JPY6MLIBOR6M",
//        new GeneratorSwapFixedIbor("JPY6MLIBOR6M", Period.ofMonths(6), DayCounts.ACT_365,
//            _iborIndexMaster.getIndex("JPYLIBOR6M"), baseCalendar));
  }

  public GeneratorSwapFixedIbor getGenerator(final String name) {
    final GeneratorSwapFixedIbor generator = _generatorSwap.get(name);
    if (generator == null) {
      throw new OpenGammaRuntimeException("Could not get swap generator for " + name);
    }
    return generator;
  }

}
