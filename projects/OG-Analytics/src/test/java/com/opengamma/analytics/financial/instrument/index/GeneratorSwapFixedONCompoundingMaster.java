/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.financial.convention.calendar.Calendar;

/**
 * A list of generators for swaps Fixed/ON available for tests.
 */
public final class GeneratorSwapFixedONCompoundingMaster {

  /**
   * The method unique instance.
   */
  private static final GeneratorSwapFixedONCompoundingMaster INSTANCE = new GeneratorSwapFixedONCompoundingMaster();

  /**
   * Return the unique instance of the class.
   * @return The instance.
   */
  public static GeneratorSwapFixedONCompoundingMaster getInstance() {
    return INSTANCE;
  }

  /**
   * The map with the list of names and the swap generators.
   */
  private final Map<String, GeneratorSwapFixedONCompounding> _generatorSwap;

  /**
   * Private constructor.
   */
  private GeneratorSwapFixedONCompoundingMaster() {
    
    final GeneratorLegFixedMaster masterLegFixed = GeneratorLegFixedMaster.getInstance();
    final GeneratorLegONCompoundingMaster masterLegON = GeneratorLegONCompoundingMaster.getInstance();
    
    // AUD
    final  String NAME_AU1YFixed_PayLag = "AUD1YFixed_PayLag";
    final  String NAME_AUD1YRBAONCmpLeg = "AUD1YRBAONCmpLeg";
    final  String NAME_AUD1YRBAONCmp = "AUD1YRBAONCmp";
    // EUR
    final  String NAME_EUR1YFixed_PayLag = "EUR1YFixed_PayLag";
    final  String NAME_EUR1YEONIACmpLeg = "EUR1YEONIACmpLeg";
    final  String NAME_EUR1YEONIACmp = "EUR1YEONIACmp";
    // JPY
    final  String NAME_JPY1YFixed_PayLag = "JPY1YFixed_PayLag";
    final  String NAME_JPT1YTONARCmpLeg = "JPT1YTONARCmpLeg";
    final  String NAME_JPT1YTONARCmp = "JPT1YTONARCmp";
    // USD
    final  String NAME_USD1YFixed_PayLag = "USD1MFixed_PayLag";
    final  String NAME_USD1YFEDFUNDCmpLeg = "USD1YFEDFUNDCmpLeg";
    final  String NAME_USD1YFEDFUNDCmp = "USD1YFEDFUNDCmp";
    
    _generatorSwap = new HashMap<>();
    _generatorSwap.put(NAME_AUD1YRBAONCmp, new GeneratorSwapFixedONCompounding(NAME_AUD1YRBAONCmp, 
        masterLegFixed.getGenerator(NAME_AU1YFixed_PayLag), masterLegON.getGenerator(NAME_AUD1YRBAONCmpLeg)));
    _generatorSwap.put(NAME_EUR1YEONIACmp, new GeneratorSwapFixedONCompounding(NAME_EUR1YEONIACmp, 
        masterLegFixed.getGenerator(NAME_EUR1YFixed_PayLag), masterLegON.getGenerator(NAME_EUR1YEONIACmpLeg)));
    _generatorSwap.put(NAME_JPT1YTONARCmp, new GeneratorSwapFixedONCompounding(NAME_JPT1YTONARCmp, 
        masterLegFixed.getGenerator(NAME_JPY1YFixed_PayLag), masterLegON.getGenerator(NAME_JPT1YTONARCmpLeg)));
    _generatorSwap.put(NAME_USD1YFEDFUNDCmp, new GeneratorSwapFixedONCompounding(NAME_USD1YFEDFUNDCmp, 
        masterLegFixed.getGenerator(NAME_USD1YFixed_PayLag), masterLegON.getGenerator(NAME_USD1YFEDFUNDCmpLeg)));
  }

  public GeneratorSwapFixedONCompounding getGenerator(final String name, final Calendar cal) {
    final GeneratorSwapFixedONCompounding generator = _generatorSwap.get(name);
    if (generator == null) {
      throw new OpenGammaRuntimeException("Could not get Swap Fixed/ON generator for " + name);
    }
    return generator;
  }

}
