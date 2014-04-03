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
public class GeneratorLegONCompoundingMaster {

  /**
   * The method unique instance.
   */
  private static final GeneratorLegONCompoundingMaster INSTANCE = new GeneratorLegONCompoundingMaster();

  /**
   * Return the unique instance of the class.
   * @return The instance.
   */
  public static GeneratorLegONCompoundingMaster getInstance() {
    return INSTANCE;
  }

  /**
   * The map with the list of names and the swap generators.
   */
  private final Map<String, GeneratorLegONCompounding> _generatorSwap;

  /**
   * Private constructor.
   */
  private GeneratorLegONCompoundingMaster() {

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
    final IndexON rbaon = indexONMaster.getIndex("RBA ON");
    final  String NAME_AUD1YRBAONCmp = "AUD1YRBAONCmpLeg";
    // EUR
    final int SPOT_LAG_EUR = 2;
    final int PAY_LAG_EUR = 1;
    final Calendar TARGET = new CalendarTarget("TARGET");
    final Currency EUR = Currency.EUR;
    final IndexON eonia = indexONMaster.getIndex("EONIA");
    final  String NAME_EUR1YEONIACmp = "EUR1YEONIACmpLeg";
    // JPY
    final int SPOT_LAG_JPY = 2;
    final int PAY_LAG_JPY = 1;
    final Calendar TOK = new MondayToFridayCalendar("TOK");
    final Currency JPY = Currency.JPY;
    final IndexON tonar = indexONMaster.getIndex("TONAR");
    final  String NAME_JPT1YTONARCmp = "JPT1YTONARCmpLeg";
    // USD
    final int SPOT_LAG_USD = 2;
    final int PAY_LAG_USD = 2;
    final Calendar NYC = new CalendarUSD("NYC");
    final Currency USD = Currency.USD;
    final IndexON fedFund = indexONMaster.getIndex("FED FUND");
    final  String NAME_USD1YFEDFUNDCmp = "USD1YFEDFUNDCmpLeg";
    
    _generatorSwap = new HashMap<>();
    _generatorSwap.put(NAME_USD1YFEDFUNDCmp, new GeneratorLegONCompounding(NAME_USD1YFEDFUNDCmp, fedFund, NYC, Tenor.ONE_YEAR, DCACT360, MOD_FOL, 
        USD, IS_EOM, SPOT_LAG_USD, NYC, STUB, IS_EOM, PAY_LAG_USD));
    _generatorSwap.put(NAME_EUR1YEONIACmp, new GeneratorLegONCompounding(NAME_EUR1YEONIACmp, eonia, TARGET, Tenor.ONE_YEAR, DCACT360, MOD_FOL, 
        EUR, IS_EOM, SPOT_LAG_EUR, TARGET, STUB, IS_EOM, PAY_LAG_EUR));
    _generatorSwap.put(NAME_AUD1YRBAONCmp, new GeneratorLegONCompounding(NAME_AUD1YRBAONCmp, rbaon, SYD, Tenor.ONE_YEAR, DCACT365, MOD_FOL, 
        AUD, IS_EOM, SPOT_LAG_AUD, SYD, STUB, IS_EOM, PAY_LAG_AUD));
    _generatorSwap.put(NAME_JPT1YTONARCmp, new GeneratorLegONCompounding(NAME_JPT1YTONARCmp, tonar, TOK, Tenor.ONE_YEAR, DCACT360, MOD_FOL, 
        JPY, IS_EOM, SPOT_LAG_JPY, TOK, STUB, IS_EOM, PAY_LAG_JPY));
  }

  public GeneratorLegONCompounding getGenerator(final String name) {
    final GeneratorLegONCompounding generator = _generatorSwap.get(name);
    if (generator == null) {
      throw new OpenGammaRuntimeException("Could not get Swap Fixed/ON generator for " + name);
    }
    return generator;
  }

}
