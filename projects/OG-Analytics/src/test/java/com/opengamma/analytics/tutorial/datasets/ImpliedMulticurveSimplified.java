/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.tutorial.datasets;

import java.util.LinkedHashMap;

import org.threeten.bp.Period;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.curve.interestrate.generator.GeneratorYDCurve;
import com.opengamma.analytics.financial.forex.method.FXMatrix;
import com.opengamma.analytics.financial.instrument.InstrumentDefinition;
import com.opengamma.analytics.financial.instrument.index.GeneratorAttribute;
import com.opengamma.analytics.financial.instrument.index.GeneratorAttributeIR;
import com.opengamma.analytics.financial.instrument.index.GeneratorInstrument;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.instrument.index.IndexIborMaster;
import com.opengamma.analytics.financial.instrument.index.IndexON;
import com.opengamma.analytics.financial.instrument.index.IndexONMaster;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.provider.calculator.discounting.ParSpreadMarketQuoteCurveSensitivityDiscountingCalculator;
import com.opengamma.analytics.financial.provider.calculator.discounting.ParSpreadMarketQuoteDiscountingCalculator;
import com.opengamma.analytics.financial.provider.curve.CurveBuildingBlockBundle;
import com.opengamma.analytics.financial.provider.curve.CurveCalibrationConventionDataSets;
import com.opengamma.analytics.financial.provider.curve.CurveCalibrationTestsUtils;
import com.opengamma.analytics.financial.provider.curve.multicurve.MulticurveDiscountBuildingRepository;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderDiscount;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderInterface;
import com.opengamma.timeseries.precise.zdt.ImmutableZonedDateTimeDoubleTimeSeries;
import com.opengamma.timeseries.precise.zdt.ZonedDateTimeDoubleTimeSeries;
import com.opengamma.util.money.Currency;
import com.opengamma.util.tuple.Pair;

/**
 * Create very simplified curves. The data is provided as implied rate from another multi-curve framework.
 */
public class ImpliedMulticurveSimplified {

  private static final IndexIborMaster IBOR_MASTER = IndexIborMaster.getInstance();
  private static final IndexONMaster ON_MASTER = IndexONMaster.getInstance();
  private static final double NOTIONAL = 1.0;
  
  /** Calculators */
  private static final ParSpreadMarketQuoteDiscountingCalculator PSMQC = 
      ParSpreadMarketQuoteDiscountingCalculator.getInstance();
  private static final ParSpreadMarketQuoteCurveSensitivityDiscountingCalculator PSMQCSC = 
      ParSpreadMarketQuoteCurveSensitivityDiscountingCalculator.getInstance();
  private static final MulticurveDiscountBuildingRepository CURVE_BUILDING_REPOSITORY =
      CurveCalibrationConventionDataSets.curveBuildingRepositoryMulticurve();
  
  private static final ZonedDateTimeDoubleTimeSeries TS_EMPTY = ImmutableZonedDateTimeDoubleTimeSeries.ofEmptyUTC();
  private static final ZonedDateTimeDoubleTimeSeries[] TS_ARRAY_EMPTY = new ZonedDateTimeDoubleTimeSeries[] {TS_EMPTY};
  
  /** =====     USD     ===== */
  
  private static final Currency USD = Currency.USD;
  private static final IndexON USDFEDFUND = ON_MASTER.getIndex("FED FUND");
  private static final IborIndex USDLIBOR1M = IBOR_MASTER.getIndex(IndexIborMaster.USDLIBOR1M);
  private static final IborIndex USDLIBOR3M = IBOR_MASTER.getIndex(IndexIborMaster.USDLIBOR3M);
  private static final IborIndex USDLIBOR6M = IBOR_MASTER.getIndex(IndexIborMaster.USDLIBOR6M);  

  private static final String CURVE_NAME_USD = "USD-ALL";
  private static final FXMatrix FX_MATRIX_USD = new FXMatrix(USD);
  private static final GeneratorYDCurve GEN_INT_MAT = CurveCalibrationConventionDataSets.generatorYDMatLin();
  
  private static final GeneratorInstrument<? extends GeneratorAttribute>[] IRS_USD_GENERATORS =
      CurveCalibrationConventionDataSets.generatorUsdIbor3Fra3Irs3(0, 0, 4);
  private static final Period[] FWD3_USD_TENOR = new Period[] {
    Period.ofYears(2), Period.ofYears(5), Period.ofYears(10), Period.ofYears(30) };
  private static final GeneratorAttributeIR[] IRS_USD_ATTR = new GeneratorAttributeIR[FWD3_USD_TENOR.length];
  static {
    for (int loopins = 0; loopins < FWD3_USD_TENOR.length; loopins++) {
      IRS_USD_ATTR[loopins] = new GeneratorAttributeIR(FWD3_USD_TENOR[loopins]);
    }
  }

  /** Units of curves */
  private static final int NB_UNITS_USD = 1;
  private static final GeneratorYDCurve[][] GENERATORS_UNITS_USD = new GeneratorYDCurve[NB_UNITS_USD][];
  private static final String[][] NAMES_UNITS_USD = new String[NB_UNITS_USD][];
  private static final MulticurveProviderDiscount KNOWN_DATA = new MulticurveProviderDiscount(FX_MATRIX_USD);
  private static final LinkedHashMap<String, Currency> DSC_MAP = new LinkedHashMap<>();
  private static final LinkedHashMap<String, IndexON[]> FWD_ON_MAP = new LinkedHashMap<>();
  private static final LinkedHashMap<String, IborIndex[]> FWD_IBOR_MAP = new LinkedHashMap<>();

  static {
    GENERATORS_UNITS_USD[0] = new GeneratorYDCurve[] {GEN_INT_MAT };
    NAMES_UNITS_USD[0] = new String[] {CURVE_NAME_USD };
    DSC_MAP.put(CURVE_NAME_USD, USD);
    FWD_ON_MAP.put(CURVE_NAME_USD, new IndexON[] {USDFEDFUND });
    FWD_IBOR_MAP.put(CURVE_NAME_USD, new IborIndex[] {USDLIBOR1M, USDLIBOR3M, USDLIBOR6M });
  }
  
  /**
   * Calibrate curves with from data computed as quotes from another multi-curve provider.
   * The curve calibrated is a reduced curve with 4 IRS: 2Y, 5Y, 10Y and 30Y.
   * @param calibrationDate The calibration date.
   * @param multicurve The multi-curve provider from which the data for the calibration are computed.
   * @return The curves and the Jacobian matrices.
   */
  public static Pair<MulticurveProviderDiscount, CurveBuildingBlockBundle> getCurvesUsd(ZonedDateTime calibrationDate, 
      MulticurveProviderInterface multicurve) {
    int nbNode = FWD3_USD_TENOR.length;
    double[] marketQuotes0 = new double[nbNode];
    InstrumentDefinition<?>[] definitionsDsc0 = CurveCalibrationTestsUtils.getDefinitions(marketQuotes0, NOTIONAL, 
        IRS_USD_GENERATORS, IRS_USD_ATTR, calibrationDate);
    double[] marketQuoteComputed = new double[nbNode];
    for (int loopdsc = 0; loopdsc < nbNode; loopdsc++) {
      InstrumentDerivative derivative = CurveCalibrationTestsUtils.convert(definitionsDsc0[loopdsc], false, 
          calibrationDate, TS_ARRAY_EMPTY, TS_ARRAY_EMPTY, TS_ARRAY_EMPTY, TS_ARRAY_EMPTY);
      marketQuoteComputed[loopdsc] = derivative.accept(PSMQC, multicurve);
    }
    InstrumentDefinition<?>[] definitions = CurveCalibrationTestsUtils.getDefinitions(marketQuoteComputed, NOTIONAL, 
        IRS_USD_GENERATORS, IRS_USD_ATTR, calibrationDate);
    InstrumentDefinition<?>[][][] definitionsUnits = new InstrumentDefinition<?>[][][] {{definitions }};
    return CurveCalibrationTestsUtils.makeCurvesFromDefinitionsMulticurve(calibrationDate, definitionsUnits, 
        GENERATORS_UNITS_USD, NAMES_UNITS_USD,
        KNOWN_DATA, PSMQC, PSMQCSC, false, DSC_MAP, FWD_ON_MAP, FWD_IBOR_MAP, CURVE_BUILDING_REPOSITORY,
        TS_ARRAY_EMPTY, TS_ARRAY_EMPTY, TS_ARRAY_EMPTY, TS_ARRAY_EMPTY);
  }
  
  /** =====     EUR     ===== */

}
