/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.curve;

import com.opengamma.analytics.financial.instrument.index.GeneratorLegFixed;
import com.opengamma.analytics.financial.instrument.index.GeneratorLegIbor;
import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedIbor;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.instrument.index.IndexON;
import com.opengamma.analytics.financial.instrument.index.IndexPrice;
import com.opengamma.analytics.financial.instrument.index.IndexSwap;
import com.opengamma.financial.convention.IborIndexConvention;
import com.opengamma.financial.convention.OvernightIndexConvention;
import com.opengamma.financial.convention.PriceIndexConvention;
import com.opengamma.financial.convention.SwapFixedLegConvention;
import com.opengamma.financial.convention.VanillaIborLegConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Tenor;

/**
 * Utilities for different converters (Node and securities)/
 */
public class ConverterUtils {
  
  /**
   * Create an IndexON from the index name and the overnight index convention.
   * @param name The name of the index.
   * @param indexConvention The overnight index convention.
   * @return The IndexON object.
   */
  public static IndexON indexON(final String name, final OvernightIndexConvention indexConvention) {
    final Currency currency = indexConvention.getCurrency();
    final DayCount dayCount = indexConvention.getDayCount();
    final int publicationLag = indexConvention.getPublicationLag();
    final IndexON indexON = new IndexON(name, currency, dayCount, publicationLag);
    return indexON;
  }
  
  /**
   * Create a IborIndex object from the convention and the tenor.
   * @param name The name of the index.
   * @param indexConvention The index convention.
   * @param indexTenor The index tenor.
   * @return The IborIndex object.
   */
  public static IborIndex indexIbor(final String name, final IborIndexConvention indexConvention, final Tenor indexTenor) {
    final Currency currency = indexConvention.getCurrency();
    final DayCount dayCount = indexConvention.getDayCount();
    final BusinessDayConvention businessDayConvention = indexConvention.getBusinessDayConvention();
    final boolean eomIndex = indexConvention.isIsEOM();
    final int spotLag = indexConvention.getSettlementDays();
    final IborIndex iborIndex = new IborIndex(currency, indexTenor.getPeriod(), spotLag, dayCount, businessDayConvention, eomIndex, name);
    return iborIndex;
  }
  
  /**
   * Create a IndexPrice object from the name and the convention.
   * @param name The name of the index.
   * @param indexConvention The index convention.
   * @return The IndexPrice object.
   */
  public static IndexPrice indexPrice(final String name, final PriceIndexConvention indexConvention) {
    final IndexPrice priceIndex = new IndexPrice(name, indexConvention.getCurrency());
    return priceIndex;
  }
  
  /**
   * Create a fixed leg generator from the equivalent convention and the relevant calendar.
   * @param convention The fixed leg convention.
   * @param calendar The calendar.
   * @return The generator.
   */
  public static GeneratorLegFixed generatorLegFixed(final SwapFixedLegConvention convention, final Calendar calendar) {
    return new GeneratorLegFixed(convention.getName(), convention.getPaymentTenor(), convention.getDayCount(), convention.getBusinessDayConvention(), 
        convention.getCurrency(), convention.isIsEOM(), convention.getSettlementDays(), calendar, convention.getStubType(), 
        convention.isIsExchangeNotional(), convention.getPaymentLag());
  }
  
  /**
   * Create a fixed leg generator from the equivalent convention and the relevant calendar.
   * @param convention The fixed leg convention.
   * @param index The ibor index.
   * @param calendar The calendar.
   * @return The generator.
   */
  public static GeneratorLegIbor generatorLegIbor(final VanillaIborLegConvention convention, final IborIndex index, final Calendar calendar) {
    return new GeneratorLegIbor(convention.getName(), index, calendar, calendar, convention.getResetTenor(), index.getDayCount(), index.getBusinessDayConvention(), 
        index.getCurrency(), convention.isIsEOM(), convention.getSettlementDays(), calendar, convention.getStubType(), convention.isIsExchangeNotional(), convention.getPaymentLag());
  }
  
  /**
   * Create a swap fixed/Ibor generator from the equivalent conventions and the relevant calendar.
   * @param name The generator name.
   * @param conventionFixed The fixed convention.
   * @param conventionIbor The Ibor convention.
   * @param index The Ibor index.
   * @param calendar The calendar.
   * @return The swap generator.
   */
  public static GeneratorSwapFixedIbor generatorSwapFixedIbor(final String name, final SwapFixedLegConvention conventionFixed, final VanillaIborLegConvention conventionIbor, 
      final IborIndex index, final Calendar calendar) {
    final GeneratorLegFixed genFixed = generatorLegFixed(conventionFixed, calendar);
    final GeneratorLegIbor genIbor = generatorLegIbor(conventionIbor, index, calendar);
    return new GeneratorSwapFixedIbor(name, genFixed, genIbor);
  }

}
