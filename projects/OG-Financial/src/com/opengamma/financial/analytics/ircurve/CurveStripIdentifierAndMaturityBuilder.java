/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.time.calendar.LocalDate;
import javax.time.calendar.LocalTime;
import javax.time.calendar.TimeZone;
import javax.time.calendar.ZonedDateTime;

import com.opengamma.analytics.financial.schedule.ScheduleCalculator;
import com.opengamma.core.holiday.HolidaySource;
import com.opengamma.core.region.RegionSource;
import com.opengamma.core.security.Security;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.financial.analytics.conversion.CalendarUtils;
import com.opengamma.financial.convention.ConventionBundle;
import com.opengamma.financial.convention.ConventionBundleSource;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.security.cash.CashSecurity;
import com.opengamma.id.ExternalId;
import com.opengamma.util.ArgumentChecker;

/**
 * 
 */
public class CurveStripIdentifierAndMaturityBuilder {
  private static final LocalTime CASH_EXPIRY_TIME = LocalTime.of(11, 0);
  private final RegionSource _regionSource;
  private final ConventionBundleSource _conventionSource;
  private final SecuritySource _securitySource;
  private final HolidaySource _holidaySource;

  public CurveStripIdentifierAndMaturityBuilder(final RegionSource regionSource, final ConventionBundleSource conventionSource, final SecuritySource securitySource,
      final HolidaySource holidaySource) {
    ArgumentChecker.notNull(regionSource, "region source");
    ArgumentChecker.notNull(conventionSource, "convention source");
    ArgumentChecker.notNull(securitySource, "security source");
    ArgumentChecker.notNull(holidaySource, "holiday source");
    _regionSource = regionSource;
    _conventionSource = conventionSource;
    _securitySource = securitySource;
    _holidaySource = holidaySource;
  }

  public NewInterpolatedCurveSpecificationWithSecurities resolveToSecurity(final CurveStripInstrumentProvider stripProvider, final Map<ExternalId, Double> marketValues) {
    ArgumentChecker.notNull(stripProvider, "strip provider");
    ArgumentChecker.notNull(marketValues, "market values");
    final Collection<CurveStripWithSecurity> securityStrips = new ArrayList<CurveStripWithSecurity>();
    for (final CurveStripWithIdentifier strip : stripProvider.getStrips()) {
      final Security security;
      final ZonedDateTime maturity;
      switch (strip.getStrip().getInstrumentType()) {
        case FRA:
        case FUTURE:
        case SWAP:
        case BASIS_SWAP:
        case OIS:
        case PERIODIC_ZERO_DEPOSIT:
        case RATE:
        case FX_FORWARD:
        case FX_SWAP:
      }
    }
    return null;
  }

  private CashSecurity getRate(final LocalDate curveDate, final CurveStripWithIdentifier strip, final Map<ExternalId, Double> marketValues) {
    final ExternalId identifier = strip.getIdentifier();
    final ConventionBundle cashConvention = _conventionSource.getConventionBundle(identifier);
    final Calendar calendar = CalendarUtils.getCalendar(_regionSource, _holidaySource, region);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(curveDate.atStartOfDayInZone(TimeZone.UTC), cashConvention.getSettlementDays(), calendar);
    final ZonedDateTime endDate = ScheduleCalculator.getAdjustedDate(startDate, cashConvention.getPeriod(), cashConvention.getBusinessDayConvention(), calendar, cashConvention.isEOMConvention());
    final Double rate = marketValues.get(identifier);
    return new CashSecurity(currency, region, startDate, endDate, cashConvention.getDayCount(), rate, 1.0);
  }
}
