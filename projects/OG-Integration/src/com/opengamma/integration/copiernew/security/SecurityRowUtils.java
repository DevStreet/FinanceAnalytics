/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.security;

import com.opengamma.financial.security.equity.EquitySecurity;
import com.opengamma.financial.security.swap.FixedInterestRateLeg;
import com.opengamma.financial.security.swap.FixedVarianceSwapLeg;
import com.opengamma.financial.security.swap.FloatingGearingIRLeg;
import com.opengamma.financial.security.swap.FloatingInterestRateLeg;
import com.opengamma.financial.security.swap.FloatingSpreadIRLeg;
import com.opengamma.financial.security.swap.FloatingVarianceSwapLeg;
import com.opengamma.financial.security.swap.InterestRateLeg;
import com.opengamma.financial.security.swap.Notional;
import com.opengamma.financial.security.swap.SwapLeg;
import com.opengamma.financial.security.swap.SwapSecurity;
import com.opengamma.financial.security.swap.VarianceSwapLeg;
import com.opengamma.integration.copiernew.sheet.JodaBeanRowUtils;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.ManageableTrade;
import com.opengamma.master.security.ManageableSecurity;

import java.util.HashMap;
import java.util.Map;

public class SecurityRowUtils extends JodaBeanRowUtils {

  public SecurityRowUtils(Class<? extends ManageableSecurity> clazz) {
    super(clazz, getIgnoredMetaProperties(), getClassPackages(), getSubClasses());
    registerMetaBeans();
  }

  public static final String SECTYPE_COLUMN_NAME = "type";

  /**
  * Types of swap leg that might be encountered, and for which additional fields are generated
  */
  private static Map<Class<?>, Class<?>[]> getSubClasses() {
    Map<Class<?>, Class<?>[]> subClasses = new HashMap<Class<?>, Class<?>[]>();
    subClasses.put(SwapLeg.class, new Class<?>[] {
      SwapLeg.class,
      InterestRateLeg.class,
      FixedInterestRateLeg.class,
      FloatingInterestRateLeg.class,
      FloatingGearingIRLeg.class,
      FloatingSpreadIRLeg.class,
      VarianceSwapLeg.class,
      FixedVarianceSwapLeg.class,
      FloatingVarianceSwapLeg.class
    });
    return subClasses;
  }

  /**
  * The packages where security classes are to be found
  */
  //  @Override
  private static String[] getClassPackages() {
   return new String[] {
     "com.opengamma.financial.security.bond",
     "com.opengamma.financial.security.capfloor",
     "com.opengamma.financial.security.cash",
     "com.opengamma.financial.security.deposit",
     "com.opengamma.financial.security.equity",
     "com.opengamma.financial.security.forward",
     "com.opengamma.financial.security.fra",
     "com.opengamma.financial.security.future",
     "com.opengamma.financial.security.fx",
     "com.opengamma.financial.security.option",
     "com.opengamma.financial.security.swap"
   };
  }

  /**
  * Security properties to ignore when scanning
  */
  //  @Override
  private static String[] getIgnoredMetaProperties() {
   return new String[] {
     "attributes",
     "uniqueid",
     "objectid",
     "securitylink",
     "trades",
     "attributes",
     "gicscode",
     "parentpositionid",
     "providerid",
     "deal"
   };
  }

  private static void registerMetaBeans() {
    // Force registration of various meta beans that might not have been loaded yet
    ManageablePosition.meta();
    ManageableTrade.meta();
    Notional.meta();
    SwapLeg.meta();
    InterestRateLeg.meta();
    FixedInterestRateLeg.meta();
    FloatingInterestRateLeg.meta();
    FloatingGearingIRLeg.meta();
    FloatingSpreadIRLeg.meta();
    VarianceSwapLeg.meta();
    FixedVarianceSwapLeg.meta();
    FloatingVarianceSwapLeg.meta();
    EquitySecurity.meta();
    SwapSecurity.meta();
  }

}
