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
import com.opengamma.financial.convention.StubType;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConventions;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCounts;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Tenor;

/**
 * A list of swap generators that can be used in the tests.
 */
public final class GeneratorSwapIborIborMaster {

  /**
   * The method unique instance.
   */
  private static final GeneratorSwapIborIborMaster INSTANCE = new GeneratorSwapIborIborMaster();

  /**
   * Return the unique instance of the class.
   * @return The instance.
   */
  public static GeneratorSwapIborIborMaster getInstance() {
    return INSTANCE;
  }

  /**
   * The map with the list of names and the swap generators.
   */
  private final Map<String, GeneratorSwapIborIbor> _generatorSwap;

  /**
   * Private constructor.
   */
  private GeneratorSwapIborIborMaster() {

    /** The list of Ibor indexes for test purposes. */
    final IndexIborMaster MASTER_IBOR = IndexIborMaster.getInstance();
    final DayCount DCACT365 = DayCounts.ACT_365;
    final BusinessDayConvention MOD_FOL = BusinessDayConventions.MODIFIED_FOLLOWING;
    final StubType STUB = StubType.SHORT_START;
    final int PAY_LAG = 0;
    final boolean IS_EOM = true;
    // AUD
    final int SPOT_LAG_AUD = 0;
    final Calendar SYD = new CalendarTarget("SYD");
    final Currency AUD = Currency.AUD;
    final IborIndex AUDBBSW3M = MASTER_IBOR.getIndex("AUDBBSW3M");
    final IborIndex AUDBBSW6M = MASTER_IBOR.getIndex("AUDBBSW6M");
    final  String NAME_AUDBBSW3MLeg = "AUDBBSW3M";
    final GeneratorLegIbor AUDBBSW3MLeg = new GeneratorLegIbor(NAME_AUDBBSW3MLeg, AUDBBSW3M, SYD, SYD, Tenor.THREE_MONTHS, DCACT365, 
        MOD_FOL, AUD, IS_EOM, SPOT_LAG_AUD, SYD, STUB, false, PAY_LAG);
    final  String NAME_AUDBBSW6MLeg = "AUDBBSW6M";
     final GeneratorLegIbor AUDBBSW6MLeg = new GeneratorLegIbor(NAME_AUDBBSW6MLeg, AUDBBSW6M, SYD, SYD, Tenor.SIX_MONTHS, DCACT365, 
        MOD_FOL, AUD, IS_EOM, SPOT_LAG_AUD, SYD, STUB, false, PAY_LAG);
     
    _generatorSwap = new HashMap<>();
    _generatorSwap.put("AUDBBSW3MBBSW6M", new GeneratorSwapIborIbor("AUDBBSW3MBBSW6M", AUDBBSW3MLeg, AUDBBSW6MLeg));
  }

  public GeneratorSwapIborIbor getGenerator(final String name) {
    final GeneratorSwapIborIbor generator = _generatorSwap.get(name);
    if (generator == null) {
      throw new OpenGammaRuntimeException("Could not get Ibor index for " + name);
    }
    return generator;
  }

}
