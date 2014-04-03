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
 * A list of generators for leg overnight compounding available for tests.
 */
public class GeneratorLegFixedMaster {

  /**
   * The method unique instance.
   */
  private static final GeneratorLegFixedMaster INSTANCE = new GeneratorLegFixedMaster();

  /**
   * Return the unique instance of the class.
   * @return The instance.
   */
  public static GeneratorLegFixedMaster getInstance() {
    return INSTANCE;
  }

  /**
   * The map with the list of names and the swap generators.
   */
  private final Map<String, GeneratorLegFixed> _generatorSwap;

  /**
   * Private constructor.
   */
  private GeneratorLegFixedMaster() {

    final DayCount DC30U_360 = DayCounts.THIRTY_U_360;
    final DayCount DCACT360 = DayCounts.ACT_360;
    final DayCount DCACT365 = DayCounts.ACT_365;
    final BusinessDayConvention MOD_FOL = BusinessDayConventions.MODIFIED_FOLLOWING;
    final StubType STUB = StubType.SHORT_START;
    final boolean IS_EOM = true;
    // AUD
    final int SPOT_LAG_AUD = 2;
    final int PAY_LAG_AUD = 1;
    final Calendar SYD = new MondayToFridayCalendar("SYD");
    final Currency AUD = Currency.AUD;
    final  String NAME_AU1YFixed_PayLag = "AUD1YFixed_PayLag";
    final  String NAME_AUD3MFixed = "AUD3MFixed";
    final  String NAME_AUD6MFixed = "AUD6MFixed";
    // EUR
    final int SPOT_LAG_EUR = 2;
    final int PAY_LAG_EUR = 1;
    final Calendar TARGET = new CalendarTarget("TARGET");
    final Currency EUR = Currency.EUR;
    final  String NAME_EUR1YFixed_PayLag = "EUR1YFixed_PayLag";
    final  String NAME_EUR1YFixed = "EUR1YFixed";
    // GBP
    final int SPOT_LAG_GBP = 0;
    final Calendar LON = new MondayToFridayCalendar("LON");
    final Currency GBP = Currency.GBP;
    final  String NAME_GBP3MFixed = "GBP3MFixed";
    final  String NAME_GBP6MFixed = "GBP6MFixed";
    // JPY
    final int SPOT_LAG_JPY = 2;
    final int PAY_LAG_JPY = 1;
    final Calendar TOK = new MondayToFridayCalendar("TOK");
    final Currency JPY = Currency.JPY;
    final  String NAME_JPY1YFixed_PayLag = "JPY1YFixed_PayLag";
    // USD
    final int SPOT_LAG_USD = 2;
    final int PAY_LAG_USD = 2;
    final Calendar NYC = new CalendarUSD("NYC");
    final Currency USD = Currency.USD;
    final  String NAME_USD1YFixed_PayLag = "USD1MFixed_PayLag";
    final  String NAME_USD6MFixed = "USD6MFixed";
    
    _generatorSwap = new HashMap<>();
    _generatorSwap.put(NAME_AU1YFixed_PayLag, new GeneratorLegFixed(NAME_AU1YFixed_PayLag, Tenor.SIX_MONTHS, DCACT365, MOD_FOL, AUD, IS_EOM, 
        SPOT_LAG_AUD, SYD, STUB, false, PAY_LAG_AUD));
    _generatorSwap.put(NAME_AUD3MFixed, new GeneratorLegFixed(NAME_AUD3MFixed, Tenor.THREE_MONTHS, DCACT365, MOD_FOL, AUD, IS_EOM, 
        SPOT_LAG_AUD, SYD, STUB, false, 0));
    _generatorSwap.put(NAME_AUD6MFixed, new GeneratorLegFixed(NAME_AUD6MFixed, Tenor.SIX_MONTHS, DCACT365, MOD_FOL, AUD, IS_EOM, 
        SPOT_LAG_AUD, SYD, STUB, false, 0));
    _generatorSwap.put(NAME_EUR1YFixed_PayLag, new GeneratorLegFixed(NAME_EUR1YFixed_PayLag, Tenor.ONE_YEAR, DCACT360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG_EUR, TARGET, STUB, false, PAY_LAG_EUR));
    _generatorSwap.put(NAME_EUR1YFixed, new GeneratorLegFixed(NAME_EUR1YFixed, Tenor.ONE_YEAR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG_EUR, TARGET, STUB, false, 0));
    _generatorSwap.put(NAME_GBP3MFixed, new GeneratorLegFixed(NAME_GBP3MFixed, Tenor.THREE_MONTHS, DCACT365, MOD_FOL, GBP, IS_EOM, 
        SPOT_LAG_GBP, LON, STUB, false, 0));
    _generatorSwap.put(NAME_GBP6MFixed, new GeneratorLegFixed(NAME_GBP6MFixed, Tenor.SIX_MONTHS, DCACT365, MOD_FOL, GBP, IS_EOM, 
        SPOT_LAG_GBP, LON, STUB, false, 0));
    _generatorSwap.put(NAME_JPY1YFixed_PayLag, new GeneratorLegFixed(NAME_JPY1YFixed_PayLag, Tenor.ONE_YEAR, DCACT360, MOD_FOL, JPY, IS_EOM, 
        SPOT_LAG_JPY, TOK, STUB, false, PAY_LAG_JPY));
    _generatorSwap.put(NAME_USD1YFixed_PayLag, new GeneratorLegFixed(NAME_USD1YFixed_PayLag, Tenor.ONE_YEAR, DCACT360, MOD_FOL, USD, IS_EOM, 
        SPOT_LAG_USD, NYC, STUB, false, PAY_LAG_USD));
    _generatorSwap.put(NAME_USD6MFixed, new GeneratorLegFixed(NAME_USD6MFixed, Tenor.SIX_MONTHS, DC30U_360, MOD_FOL, USD, IS_EOM, 
        SPOT_LAG_USD, NYC, STUB, false, 0));
  }

  public GeneratorLegFixed getGenerator(final String name) {
    final GeneratorLegFixed generator = _generatorSwap.get(name);
    if (generator == null) {
      throw new OpenGammaRuntimeException("Could not get Swap Fixed/ON generator for " + name);
    }
    return generator;
  }

}
