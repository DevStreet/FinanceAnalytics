/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import javax.time.calendar.LocalDate;
import javax.time.calendar.MonthOfYear;

import org.apache.commons.lang.ObjectUtils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.financial.analytics.volatility.surface.BloombergFutureUtils;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalScheme;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class NewBloombergMonthlyFutureCurveInstrumentProvider {
  private static final ExternalScheme SCHEME = ExternalSchemes.BLOOMBERG_TICKER;
  private static BiMap<MonthOfYear, Character> s_monthCode;
  private final String _futurePrefix;
  private final String _marketSector;

  static {
    s_monthCode = HashBiMap.create();
    s_monthCode.put(MonthOfYear.JANUARY, 'F');
    s_monthCode.put(MonthOfYear.FEBRUARY, 'G');
    s_monthCode.put(MonthOfYear.MARCH, 'H');
    s_monthCode.put(MonthOfYear.APRIL, 'J');
    s_monthCode.put(MonthOfYear.MAY, 'K');
    s_monthCode.put(MonthOfYear.JUNE, 'M');
    s_monthCode.put(MonthOfYear.JULY, 'N');
    s_monthCode.put(MonthOfYear.AUGUST, 'Q');
    s_monthCode.put(MonthOfYear.SEPTEMBER, 'U');
    s_monthCode.put(MonthOfYear.OCTOBER, 'V');
    s_monthCode.put(MonthOfYear.NOVEMBER, 'X');
    s_monthCode.put(MonthOfYear.DECEMBER, 'Z');
  }

  public NewBloombergMonthlyFutureCurveInstrumentProvider(final String futurePrefix, final String marketSector) {
    ArgumentChecker.notNull(futurePrefix, "future prefix");
    ArgumentChecker.notNull(marketSector, "market sector");
    _futurePrefix = futurePrefix;
    _marketSector = marketSector;
  }

  public String getFuturePrefix() {
    return _futurePrefix;
  }

  public String getMarketSector() {
    return _marketSector;
  }

  public ExternalId getInstrument(final LocalDate curveDate, final Tenor tenor, final int nFuturesFromTenor) {
    ArgumentChecker.notNull(curveDate, "curve date");
    ArgumentChecker.notNull(tenor, "tenor");
    ArgumentChecker.isTrue(nFuturesFromTenor > 0, "number of futures must be > 0");
    return createMonthlyIRFutureStrips(curveDate, tenor, nFuturesFromTenor, _futurePrefix, _marketSector);
  }

  private ExternalId createMonthlyIRFutureStrips(final LocalDate curveDate, final Tenor tenor, final int numQuartlyFuturesFromTenor, final String prefix, final String postfix) {
    final StringBuilder futureCode = new StringBuilder();
    futureCode.append(prefix);
    final LocalDate curveFutureStartDate = curveDate.plus(tenor.getPeriod());
    final String expiryCode = BloombergFutureUtils.getQuarterlyExpiryCodeForFutures(prefix, numQuartlyFuturesFromTenor, curveFutureStartDate);
    futureCode.append(expiryCode);
    futureCode.append(" ");
    futureCode.append(postfix);
    return ExternalId.of(SCHEME, futureCode.toString());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _futurePrefix.hashCode();
    result = prime * result + _marketSector.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final NewBloombergMonthlyFutureCurveInstrumentProvider other = (NewBloombergMonthlyFutureCurveInstrumentProvider) obj;
    if (!ObjectUtils.equals(_futurePrefix, other._futurePrefix)) {
      return false;
    }
    if (!ObjectUtils.equals(_marketSector, other._marketSector)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "BloombergMonthlyFutureCurveInstrumentProvider[" + _futurePrefix + ", " + _marketSector + "]";
  }
}
