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
 * A list of generators for swaps Fixed/ON available for tests.
 */
public final class GeneratorSwapFixedONMaster {

  /**
   * The method unique instance.
   */
  private static final GeneratorSwapFixedONMaster INSTANCE = new GeneratorSwapFixedONMaster();

  /**
   * Return the unique instance of the class.
   * @return The instance.
   */
  public static GeneratorSwapFixedONMaster getInstance() {
    return INSTANCE;
  }

  /**
   * The map with the list of names and the swap generators.
   */
  private final Map<String, GeneratorSwapFixedONCompounding> _generatorSwap;

  /**
   * Private constructor.
   */
  private GeneratorSwapFixedONMaster() {

    final DayCount DCACT360 = DayCounts.ACT_360;
    final DayCount DCACT365 = DayCounts.ACT_365;
    final BusinessDayConvention MOD_FOL = BusinessDayConventions.MODIFIED_FOLLOWING;
    final StubType STUB = StubType.SHORT_START;
    final boolean IS_EOM = true;
    final IndexONMaster indexONMaster = IndexONMaster.getInstance();
    // AUD
    // TODO: AUD
    final int SPOT_LAG_AUD = 2;
    final int PAY_LAG_AUD = 1;
    final Calendar SYD = new MondayToFridayCalendar("SYD");
    final Currency AUD = Currency.AUD;
    final  String NAME_AU1YFixed_PayLag = "AUD1YFixed_PayLag";
    final GeneratorLegFixed AUD1YFixed_PayLag = new GeneratorLegFixed(NAME_AU1YFixed_PayLag, Tenor.SIX_MONTHS, DCACT365, MOD_FOL, AUD, IS_EOM, 
        SPOT_LAG_AUD, SYD, STUB, false, PAY_LAG_AUD);
    final IndexON rbaon = indexONMaster.getIndex("RBA ON");
    final  String NAME_AUD1YRBAONCmp = "AUD1YRBAONCmp";
    final GeneratorLegONCompounding AUD1YRBAONCmp = new GeneratorLegONCompounding(NAME_AUD1YRBAONCmp, rbaon, SYD, Tenor.ONE_YEAR, DCACT365, MOD_FOL, 
        AUD, IS_EOM, SPOT_LAG_AUD, SYD, STUB, IS_EOM, PAY_LAG_AUD);
    // EUR
    final int SPOT_LAG_EUR = 2;
    final int PAY_LAG_EUR = 1;
    final Calendar TARGET = new CalendarTarget("TARGET");
    final Currency EUR = Currency.EUR;
    final  String NAME_EUR1YFixed = "EUR1YFixed_PayLag";
    final GeneratorLegFixed EUR1YFixed_PayLag = new GeneratorLegFixed(NAME_EUR1YFixed, Tenor.ONE_YEAR, DCACT360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG_EUR, TARGET, STUB, false, PAY_LAG_EUR);
    final IndexON eonia = indexONMaster.getIndex("EONIA");
    final  String NAME_EUR1YEONIACmp = "EUR1YEONIACmp";
    final GeneratorLegONCompounding EUR1YEONIACmp = new GeneratorLegONCompounding(NAME_EUR1YEONIACmp, eonia, TARGET, Tenor.ONE_YEAR, DCACT360, MOD_FOL, 
        EUR, IS_EOM, SPOT_LAG_EUR, TARGET, STUB, IS_EOM, PAY_LAG_EUR);
    // JPY
    final int SPOT_LAG_JPY = 2;
    final int PAY_LAG_JPY = 1;
    final Calendar TOK = new MondayToFridayCalendar("TOK");
    final Currency JPY = Currency.JPY;
    final  String NAME_JPY1YFixed_PayLag = "JPY1YFixed_PayLag";
    final GeneratorLegFixed JPY1YFixed_PayLag = new GeneratorLegFixed(NAME_JPY1YFixed_PayLag, Tenor.ONE_YEAR, DCACT360, MOD_FOL, JPY, IS_EOM, 
        SPOT_LAG_JPY, TOK, STUB, false, PAY_LAG_JPY);
    final IndexON tonar = indexONMaster.getIndex("TONAR");
    final  String NAME_JPT1YTONARCmp = "JPT1YTONARCmp";
    final GeneratorLegONCompounding JPT1YTONARCmp = new GeneratorLegONCompounding(NAME_JPT1YTONARCmp, tonar, TOK, Tenor.ONE_YEAR, DCACT360, MOD_FOL, 
        JPY, IS_EOM, SPOT_LAG_JPY, TOK, STUB, IS_EOM, PAY_LAG_JPY);
    // USD
    final int SPOT_LAG_USD = 2;
    final int PAY_LAG_USD = 2;
    final Calendar NYC = new CalendarUSD("NYC");
    final Currency USD = Currency.USD;
    final  String NAME_USD1YFixed = "USD1MFixed_PayLag";
    final GeneratorLegFixed USD1YFixed_PayLag = new GeneratorLegFixed(NAME_USD1YFixed, Tenor.ONE_YEAR, DCACT360, MOD_FOL, USD, IS_EOM, 
        SPOT_LAG_USD, NYC, STUB, false, PAY_LAG_EUR);
    final IndexON fedFund = indexONMaster.getIndex("FED FUND");
    final  String NAME_USD1YFEDFUNDCmp = "USD1YFEDFUNDCmp";
    final GeneratorLegONCompounding USD1YFEDFUNDCmp = new GeneratorLegONCompounding(NAME_USD1YFEDFUNDCmp, fedFund, NYC, Tenor.ONE_YEAR, DCACT360, MOD_FOL, 
        USD, IS_EOM, SPOT_LAG_USD, NYC, STUB, IS_EOM, PAY_LAG_USD);
    
    _generatorSwap = new HashMap<>();
    _generatorSwap.put("USD1YFEDFUNDCmp", new GeneratorSwapFixedONCompounding("USD1YFEDFUNDCmp", USD1YFixed_PayLag, USD1YFEDFUNDCmp));
    _generatorSwap.put("EUR1YEONIACmp", new GeneratorSwapFixedONCompounding("EUR1YEONIACmp", EUR1YFixed_PayLag, EUR1YEONIACmp));
    _generatorSwap.put("AUD1YRBAONCmp", new GeneratorSwapFixedONCompounding("AUD1YRBAONCmp", AUD1YFixed_PayLag, AUD1YRBAONCmp));
    _generatorSwap.put("JPY1YTONARCmp", new GeneratorSwapFixedONCompounding("JPY1YTONARCmp", JPY1YFixed_PayLag, JPT1YTONARCmp));
  }

  public GeneratorSwapFixedONCompounding getGenerator(final String name, final Calendar cal) {
    final GeneratorSwapFixedONCompounding generator = _generatorSwap.get(name);
    if (generator == null) {
      throw new OpenGammaRuntimeException("Could not get Swap Fixed/ON generator for " + name);
    }
    return generator;
  }

}
