/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.timeseries.returns;

import com.opengamma.timeseries.TimeSeriesException;
import com.opengamma.timeseries.date.localdate.LocalDateDoubleEntryIterator;
import com.opengamma.timeseries.date.localdate.LocalDateDoubleTimeSeries;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.CalculationMode;

/**
 * This class contains a function that calculates the net one-period simple
 * return of an asset that pays dividends periodically. This is defined at time
 * <i>t</i> as:<br>
 * <i>R<sub>t</sub> = (P<sub>t</sub>-D<sub>t</sub>)/P<sub>t-1</sub>-1</i><br>
 * where <i>P<sub>t</sub></i> is the price at time <i>t</i>,
 * <i>D<sub>t</sub></i> is the dividend at time <i>t</i> and
 * <i>P<sub>t-1</sub></i> is the price at time <i>t-1</i>.
 *
 */
public class SimpleNetTimeSeriesReturnCalculator extends TimeSeriesReturnCalculator {

  public SimpleNetTimeSeriesReturnCalculator(final CalculationMode mode) {
    super(mode);
  }

  /**
   * @param x
   *          An array of DoubleTimeSeries. If the array has only one element,
   *          then this is assumed to be the price series and the result is the
   *          simple return. The dividend series is assumed to be the second
   *          element. It does not have to be the same length as the price
   *          series (in which case, dates without dividends are treated like
   *          the dividend was zero), and the dividend data points do not have
   *          to correspond to any of the dates in the price series (in which
   *          case, the result is the simple net return).
   * @throws IllegalArgumentException
   *           If: the array is null; it has no elements;
   * @throws TimeSeriesException
   *           Throws an exception if:
   *           the time series has less than two entries; the calculation
   *           mode is strict and there are zeroes in the price series.
   * @return A DoubleTimeSeries containing the return series. This will always
   *         be one element shorter than the original price series.
   */
  @Override
  public LocalDateDoubleTimeSeries evaluate(final LocalDateDoubleTimeSeries... x) {
    ArgumentChecker.notEmpty(x, "x");
    ArgumentChecker.notNull(x[0], "first time series");
    final LocalDateDoubleTimeSeries ts = x[0];
    if (ts.size() < 2) {
      throw new TimeSeriesException("Need at least two data points to calculate return series");
    }
    LocalDateDoubleTimeSeries d = null;
    if (x.length > 1) {
      if (x[1] != null) {
        d = x[1];
      }
    }

    final int[] resultDates = new int[ts.size() - 1];
    final double[] resultValues = new double[ts.size() - 1];
    int resultIndex = 0;

    final LocalDateDoubleEntryIterator it = ts.iterator();
    it.nextTimeFast();
    double previousValue = it.currentValue();

    double dividend;
    Double dividendTSData;
    while (it.hasNext()) {
      final int date = it.nextTimeFast();
      final double value = it.currentValue();

      if (isValueNonZero(previousValue) && isValueNonZero(value)) {
        resultDates[resultIndex] = date;
        if (d == null) {
          dividend = 0;
        } else {
          dividendTSData = d.getValue(date);
          dividend = dividendTSData == null ? 0 : dividendTSData;
        }
        resultValues[resultIndex++] = (value + dividend) / previousValue - 1;
      }
      previousValue = value;
    }
    return getSeries(resultDates, resultValues, resultIndex);
  }

}
