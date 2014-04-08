/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.examples.simulated.curve;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.opengamma.core.id.ExternalSchemes.OG_SYNTHETIC_TICKER;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.opengamma.core.config.impl.ConfigItem;
import com.opengamma.engine.ComputationTargetSpecification;
import com.opengamma.financial.analytics.curve.CurveConstructionConfiguration;
import com.opengamma.financial.analytics.curve.CurveGroupConfiguration;
import com.opengamma.financial.analytics.curve.CurveNodeIdMapper;
import com.opengamma.financial.analytics.curve.DiscountingCurveTypeConfiguration;
import com.opengamma.financial.analytics.curve.IborCurveTypeConfiguration;
import com.opengamma.financial.analytics.curve.InterpolatedCurveDefinition;
import com.opengamma.financial.analytics.curve.OvernightCurveTypeConfiguration;
import com.opengamma.financial.analytics.curve.exposure.ExposureFunctions;
import com.opengamma.financial.analytics.ircurve.BloombergFutureCurveInstrumentProvider;
import com.opengamma.financial.analytics.ircurve.CurveInstrumentProvider;
import com.opengamma.financial.analytics.ircurve.StaticCurveInstrumentProvider;
import com.opengamma.financial.analytics.ircurve.StripInstrumentType;
import com.opengamma.financial.analytics.ircurve.calcconfig.CurveInstrumentConfig;
import com.opengamma.financial.analytics.ircurve.calcconfig.MultiCurveCalculationConfig;
import com.opengamma.financial.analytics.ircurve.strips.CashNode;
import com.opengamma.financial.analytics.ircurve.strips.CurveNode;
import com.opengamma.financial.analytics.ircurve.strips.DataFieldType;
import com.opengamma.financial.analytics.ircurve.strips.FRANode;
import com.opengamma.financial.analytics.ircurve.strips.SwapNode;
import com.opengamma.financial.analytics.model.curve.interestrate.MultiYieldCurvePropertiesAndDefaults;
import com.opengamma.id.ExternalId;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.ConfigMasterUtils;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Tenor;

/**
 *
 */
public class MultiCurveCalculationConfigPopulator {

  public MultiCurveCalculationConfigPopulator(final ConfigMaster configMaster) {
    ArgumentChecker.notNull(configMaster, "config master");
    populateConfigMaster(configMaster);
  }

  private static void populateConfigMaster(final ConfigMaster configMaster) {
    final String discountingCurveName = "Discounting";
    final String forward3MCurveName = "Forward3M";
    final String forward6MCurveName = "Forward6M";
    final String forward3MFutCurveName = "Forward3MFut";
    final MultiCurveCalculationConfig defaultUSDConfig = new MultiCurveCalculationConfig("DefaultTwoCurveUSDConfig",
                                                                                         new String[]{discountingCurveName, forward3MCurveName},
                                                                                         ComputationTargetSpecification.of(
                                                                                             Currency.USD),
                                                                                         MultiYieldCurvePropertiesAndDefaults.PAR_RATE_STRING,
                                                                                         getTwoCurveUSDInstrumentConfig(
                                                                                             discountingCurveName,
                                                                                             forward3MCurveName)
    );
    final MultiCurveCalculationConfig defaultGBPConfig = new MultiCurveCalculationConfig("DefaultTwoCurveGBPConfig",
                                                                                         new String[]{discountingCurveName, forward6MCurveName},
                                                                                         ComputationTargetSpecification.of(
                                                                                             Currency.GBP),
                                                                                         MultiYieldCurvePropertiesAndDefaults.PAR_RATE_STRING,
                                                                                         getTwoCurveGBPInstrumentConfig(
                                                                                             discountingCurveName,
                                                                                             forward6MCurveName)
    );
    final MultiCurveCalculationConfig defaultEURConfig = new MultiCurveCalculationConfig("DefaultTwoCurveEURConfig",
                                                                                         new String[]{discountingCurveName, forward6MCurveName},
                                                                                         ComputationTargetSpecification.of(
                                                                                             Currency.EUR),
                                                                                         MultiYieldCurvePropertiesAndDefaults.PAR_RATE_STRING,
                                                                                         getTwoCurveEURInstrumentConfig(
                                                                                             discountingCurveName,
                                                                                             forward6MCurveName)
    );
    final MultiCurveCalculationConfig defaultJPYConfig = new MultiCurveCalculationConfig("DefaultTwoCurveJPYConfig",
                                                                                         new String[]{discountingCurveName, forward6MCurveName},
                                                                                         ComputationTargetSpecification.of(
                                                                                             Currency.JPY),
                                                                                         MultiYieldCurvePropertiesAndDefaults.PAR_RATE_STRING,
                                                                                         getTwoCurveJPYInstrumentConfig(
                                                                                             discountingCurveName,
                                                                                             forward6MCurveName)
    );
    final MultiCurveCalculationConfig defaultCHFConfig = new MultiCurveCalculationConfig("DefaultTwoCurveCHFConfig",
                                                                                         new String[]{discountingCurveName, forward6MCurveName},
                                                                                         ComputationTargetSpecification.of(
                                                                                             Currency.CHF),
                                                                                         MultiYieldCurvePropertiesAndDefaults.PAR_RATE_STRING,
                                                                                         getTwoCurveCHFInstrumentConfig(
                                                                                             discountingCurveName,
                                                                                             forward6MCurveName)
    );
    final MultiCurveCalculationConfig eurOIS3M6M = new MultiCurveCalculationConfig("EUR-OIS-3M-6M",
                                                                                   new String[]{discountingCurveName, forward3MCurveName, forward6MCurveName},
                                                                                   ComputationTargetSpecification.of(
                                                                                       Currency.EUR),
                                                                                   MultiYieldCurvePropertiesAndDefaults.PAR_RATE_STRING,
                                                                                   getEUROISInstrumentConfig1(
                                                                                       discountingCurveName,
                                                                                       forward3MCurveName,
                                                                                       forward6MCurveName)
    );
    final MultiCurveCalculationConfig eurOIS3MFut6M = new MultiCurveCalculationConfig("EUR-OIS-3MFut-6M",
                                                                                      new String[]{discountingCurveName, forward3MFutCurveName, forward6MCurveName},
                                                                                      ComputationTargetSpecification.of(
                                                                                          Currency.EUR),
                                                                                      MultiYieldCurvePropertiesAndDefaults.PAR_RATE_STRING,
                                                                                      getEUROISFutInstrumentConfig1(
                                                                                          discountingCurveName,
                                                                                          forward3MFutCurveName,
                                                                                          forward6MCurveName)
    );
    ConfigMasterUtils.storeByName(configMaster, makeConfig(defaultUSDConfig));
    ConfigMasterUtils.storeByName(configMaster, makeConfig(defaultGBPConfig));
    ConfigMasterUtils.storeByName(configMaster, makeConfig(defaultEURConfig));
    ConfigMasterUtils.storeByName(configMaster, makeConfig(defaultJPYConfig));
    ConfigMasterUtils.storeByName(configMaster, makeConfig(defaultCHFConfig));
    ConfigMasterUtils.storeByName(configMaster, makeConfig(getAUDThreeCurveConfig()));
    ConfigMasterUtils.storeByName(configMaster, makeConfig(getAUDDiscountingCurveConfig()));
    ConfigMasterUtils.storeByName(configMaster, makeConfig(getAUDForwardCurvesConfig()));
    ConfigMasterUtils.storeByName(configMaster, makeConfig(getSingleAUDCurveConfig()));
    ConfigMasterUtils.storeByName(configMaster, makeConfig(eurOIS3M6M));
    ConfigMasterUtils.storeByName(configMaster, makeConfig(eurOIS3MFut6M));

    ConfigMasterUtils.storeByName(configMaster, ConfigItem.of(getUsdOnOisLibor3M_FRAIRS()));
    ConfigMasterUtils.storeByName(configMaster, ConfigItem.of(getUsdOnOisLibor3M_FRAIRSCurveConstructionConfiguration()));

    //ConfigMasterUtils.storeByName(conv, ConfigItem.of(getUSDDepoONConvention()));
    ConfigMasterUtils.storeByName(configMaster, ConfigItem.of(getInterpolatedCurveDefinition()));
    ConfigMasterUtils.storeByName(configMaster, ConfigItem.of(getInterpolatedCurveDefinition_USD_ON_OIS()));
    ConfigMasterUtils.storeByName(configMaster, ConfigItem.of(getUSD_LIBOR3M_BBG_Mapper()));
    ConfigMasterUtils.storeByName(configMaster, ConfigItem.of(getUSDDepoT_plus_1_BBG_Mapper()));

    ConfigMasterUtils.storeByName(configMaster, ConfigItem.of(getExposureFunctions()));

  }

  private static ConfigItem<MultiCurveCalculationConfig> makeConfig(final MultiCurveCalculationConfig curveConfig) {
    final ConfigItem<MultiCurveCalculationConfig> config = ConfigItem.of(curveConfig);
    config.setName(curveConfig.getCalculationConfigName());
    return config;
  }

  private static LinkedHashMap<String, CurveInstrumentConfig> getTwoCurveUSDInstrumentConfig(final String discountingCurveName, final String forward3MCurveName) {
    final String[] discountingOnly = new String[] {discountingCurveName};
    final String[] forward3MOnly = new String[] {forward3MCurveName};
    final String[] discountingForward3M = new String[] {discountingCurveName, forward3MCurveName};
    final String[] discountingDiscounting = new String[] {discountingCurveName, discountingCurveName};
    final LinkedHashMap<String, CurveInstrumentConfig> result = new LinkedHashMap<>();
    final Map<StripInstrumentType, String[]> discountingConfig = new HashMap<>();
    discountingConfig.put(StripInstrumentType.CASH, discountingOnly);
    discountingConfig.put(StripInstrumentType.OIS_SWAP, discountingDiscounting);
    final Map<StripInstrumentType, String[]> forward3MConfig = new HashMap<>();
    forward3MConfig.put(StripInstrumentType.LIBOR, forward3MOnly);
    forward3MConfig.put(StripInstrumentType.FRA_3M, discountingForward3M);
    forward3MConfig.put(StripInstrumentType.SWAP_3M, discountingForward3M);
    result.put(discountingCurveName, new CurveInstrumentConfig(discountingConfig));
    result.put(forward3MCurveName, new CurveInstrumentConfig(forward3MConfig));
    return result;
  }

  private static LinkedHashMap<String, CurveInstrumentConfig> getEUROISInstrumentConfig1(final String discountingCurveName, final String forward3MCurveName,
      final String forward6MCurveName) {
    final String[] discountingOnly = new String[] {discountingCurveName};
    final String[] forward3MOnly = new String[] {forward3MCurveName};
    final String[] forward6MOnly = new String[] {forward6MCurveName};
    final String[] discountingForward3M = new String[] {discountingCurveName, forward3MCurveName};
    final String[] discountingForward6M = new String[] {discountingCurveName, forward6MCurveName};
    final String[] discountingDiscounting = new String[] {discountingCurveName, discountingCurveName};
    final LinkedHashMap<String, CurveInstrumentConfig> result = new LinkedHashMap<>();
    final Map<StripInstrumentType, String[]> discountingConfig = new HashMap<>();
    discountingConfig.put(StripInstrumentType.CASH, discountingOnly);
    discountingConfig.put(StripInstrumentType.OIS_SWAP, discountingDiscounting);
    final Map<StripInstrumentType, String[]> forward3MConfig = new HashMap<>();
    forward3MConfig.put(StripInstrumentType.EURIBOR, forward3MOnly);
    forward3MConfig.put(StripInstrumentType.FRA_3M, discountingForward3M);
    forward3MConfig.put(StripInstrumentType.SWAP_3M, discountingForward3M);
    final Map<StripInstrumentType, String[]> forward6MConfig = new HashMap<>();
    forward6MConfig.put(StripInstrumentType.EURIBOR, forward6MOnly);
    forward6MConfig.put(StripInstrumentType.FRA_6M, discountingForward6M);
    forward6MConfig.put(StripInstrumentType.SWAP_6M, discountingForward6M);
    result.put(discountingCurveName, new CurveInstrumentConfig(discountingConfig));
    result.put(forward3MCurveName, new CurveInstrumentConfig(forward3MConfig));
    result.put(forward6MCurveName, new CurveInstrumentConfig(forward6MConfig));
    return result;
  }

  private static LinkedHashMap<String, CurveInstrumentConfig> getEUROISFutInstrumentConfig1(final String discountingCurveName, final String forward3MCurveName,
      final String forward6MCurveName) {
    final String[] discountingOnly = new String[] {discountingCurveName};
    final String[] forward3MOnly = new String[] {forward3MCurveName};
    final String[] forward6MOnly = new String[] {forward6MCurveName};
    final String[] discountingForward3M = new String[] {discountingCurveName, forward3MCurveName};
    final String[] discountingForward6M = new String[] {discountingCurveName, forward6MCurveName};
    final String[] discountingDiscounting = new String[] {discountingCurveName, discountingCurveName};
    final LinkedHashMap<String, CurveInstrumentConfig> result = new LinkedHashMap<>();
    final Map<StripInstrumentType, String[]> discountingConfig = new HashMap<>();
    discountingConfig.put(StripInstrumentType.CASH, discountingOnly);
    discountingConfig.put(StripInstrumentType.OIS_SWAP, discountingDiscounting);
    final Map<StripInstrumentType, String[]> forward3MConfig = new HashMap<>();
    forward3MConfig.put(StripInstrumentType.EURIBOR, forward3MOnly);
    forward3MConfig.put(StripInstrumentType.FRA_3M, discountingForward3M);
    forward3MConfig.put(StripInstrumentType.FUTURE, discountingForward3M);
    forward3MConfig.put(StripInstrumentType.SWAP_3M, discountingForward3M);
    final Map<StripInstrumentType, String[]> forward6MConfig = new HashMap<>();
    forward6MConfig.put(StripInstrumentType.EURIBOR, forward6MOnly);
    forward6MConfig.put(StripInstrumentType.FRA_6M, discountingForward6M);
    forward6MConfig.put(StripInstrumentType.SWAP_6M, discountingForward6M);
    result.put(discountingCurveName, new CurveInstrumentConfig(discountingConfig));
    result.put(forward3MCurveName, new CurveInstrumentConfig(forward3MConfig));
    result.put(forward6MCurveName, new CurveInstrumentConfig(forward6MConfig));
    return result;
  }

  private static LinkedHashMap<String, CurveInstrumentConfig> getTwoCurveGBPInstrumentConfig(final String discountingCurveName, final String forward6MCurveName) {
    final String[] discountingOnly = new String[] {discountingCurveName};
    final String[] forward6MOnly = new String[] {forward6MCurveName};
    final String[] discountingForward6M = new String[] {discountingCurveName, forward6MCurveName};
    final String[] discountingDiscounting = new String[] {discountingCurveName, discountingCurveName};
    final LinkedHashMap<String, CurveInstrumentConfig> result = new LinkedHashMap<>();
    final Map<StripInstrumentType, String[]> discountingConfig = new HashMap<>();
    discountingConfig.put(StripInstrumentType.CASH, discountingOnly);
    discountingConfig.put(StripInstrumentType.OIS_SWAP, discountingDiscounting);
    final Map<StripInstrumentType, String[]> forward6MConfig = new HashMap<>();
    forward6MConfig.put(StripInstrumentType.LIBOR, forward6MOnly);
    forward6MConfig.put(StripInstrumentType.FRA_6M, discountingForward6M);
    forward6MConfig.put(StripInstrumentType.SWAP_6M, discountingForward6M);
    result.put(discountingCurveName, new CurveInstrumentConfig(discountingConfig));
    result.put(forward6MCurveName, new CurveInstrumentConfig(forward6MConfig));
    return result;
  }

  private static LinkedHashMap<String, CurveInstrumentConfig> getTwoCurveEURInstrumentConfig(final String discountingCurveName, final String forward6MCurveName) {
    final String[] discountingOnly = new String[] {discountingCurveName};
    final String[] forward6MOnly = new String[] {forward6MCurveName};
    final String[] discountingForward6M = new String[] {discountingCurveName, forward6MCurveName};
    final String[] discountingDiscounting = new String[] {discountingCurveName, discountingCurveName};
    final LinkedHashMap<String, CurveInstrumentConfig> result = new LinkedHashMap<>();
    final Map<StripInstrumentType, String[]> discountingConfig = new HashMap<>();
    discountingConfig.put(StripInstrumentType.CASH, discountingOnly);
    discountingConfig.put(StripInstrumentType.OIS_SWAP, discountingDiscounting);
    final Map<StripInstrumentType, String[]> forward6MConfig = new HashMap<>();
    forward6MConfig.put(StripInstrumentType.EURIBOR, forward6MOnly);
    forward6MConfig.put(StripInstrumentType.FRA_6M, discountingForward6M);
    forward6MConfig.put(StripInstrumentType.SWAP_6M, discountingForward6M);
    result.put(discountingCurveName, new CurveInstrumentConfig(discountingConfig));
    result.put(forward6MCurveName, new CurveInstrumentConfig(forward6MConfig));
    return result;
  }

  private static LinkedHashMap<String, CurveInstrumentConfig> getTwoCurveJPYInstrumentConfig(final String discountingCurveName, final String forward6MCurveName) {
    final String[] discountingOnly = new String[] {discountingCurveName};
    final String[] forward6MOnly = new String[] {forward6MCurveName};
    final String[] discountingForward6M = new String[] {discountingCurveName, forward6MCurveName};
    final String[] discountingDiscounting = new String[] {discountingCurveName, discountingCurveName};
    final LinkedHashMap<String, CurveInstrumentConfig> result = new LinkedHashMap<>();
    final Map<StripInstrumentType, String[]> discountingConfig = new HashMap<>();
    discountingConfig.put(StripInstrumentType.CASH, discountingOnly);
    discountingConfig.put(StripInstrumentType.OIS_SWAP, discountingDiscounting);
    final Map<StripInstrumentType, String[]> forward3MConfig = new HashMap<>();
    forward3MConfig.put(StripInstrumentType.LIBOR, forward6MOnly);
    forward3MConfig.put(StripInstrumentType.FRA_6M, discountingForward6M);
    forward3MConfig.put(StripInstrumentType.SWAP_6M, discountingForward6M);
    result.put(discountingCurveName, new CurveInstrumentConfig(discountingConfig));
    result.put(forward6MCurveName, new CurveInstrumentConfig(forward3MConfig));
    return result;
  }

  private static LinkedHashMap<String, CurveInstrumentConfig> getTwoCurveCHFInstrumentConfig(final String discountingCurveName, final String forward6MCurveName) {
    final String[] discountingOnly = new String[] {discountingCurveName};
    final String[] forward6MOnly = new String[] {forward6MCurveName};
    final String[] discountingForward6M = new String[] {discountingCurveName, forward6MCurveName};
    final String[] discountingDiscounting = new String[] {discountingCurveName, discountingCurveName};
    final LinkedHashMap<String, CurveInstrumentConfig> result = new LinkedHashMap<>();
    final Map<StripInstrumentType, String[]> discountingConfig = new HashMap<>();
    discountingConfig.put(StripInstrumentType.CASH, discountingOnly);
    discountingConfig.put(StripInstrumentType.OIS_SWAP, discountingDiscounting);
    final Map<StripInstrumentType, String[]> forward3MConfig = new HashMap<>();
    forward3MConfig.put(StripInstrumentType.LIBOR, forward6MOnly);
    forward3MConfig.put(StripInstrumentType.FRA_6M, discountingForward6M);
    forward3MConfig.put(StripInstrumentType.SWAP_6M, discountingForward6M);
    result.put(discountingCurveName, new CurveInstrumentConfig(discountingConfig));
    result.put(forward6MCurveName, new CurveInstrumentConfig(forward3MConfig));
    return result;
  }

  private static MultiCurveCalculationConfig getAUDThreeCurveConfig() {
    final String[] yieldCurveNames = new String[] {"Discounting", "ForwardBasis3M", "ForwardBasis6M"};
    final Currency target = Currency.AUD;
    final String calculationMethod = MultiYieldCurvePropertiesAndDefaults.PAR_RATE_STRING;
    final LinkedHashMap<String, CurveInstrumentConfig> curveExposuresForInstruments = new LinkedHashMap<>();
    final Map<StripInstrumentType, String[]> discountingConfig = new HashMap<>();
    discountingConfig.put(StripInstrumentType.CASH, new String[] {"Discounting"});
    discountingConfig.put(StripInstrumentType.OIS_SWAP, new String[] {"Discounting", "Discounting"});
    final Map<StripInstrumentType, String[]> forwardBasis3MConfig = new HashMap<>();
    forwardBasis3MConfig.put(StripInstrumentType.BASIS_SWAP, new String[] {"Discounting", "ForwardBasis3M", "ForwardBasis6M"});
    forwardBasis3MConfig.put(StripInstrumentType.SWAP_3M, new String[] {"Discounting", "ForwardBasis3M"});
    forwardBasis3MConfig.put(StripInstrumentType.LIBOR, new String[] {"ForwardBasis3M"});
    final Map<StripInstrumentType, String[]> forwardBasis6MConfig = new HashMap<>();
    forwardBasis6MConfig.put(StripInstrumentType.BASIS_SWAP, new String[] {"Discounting", "ForwardBasis3M", "ForwardBasis6M"});
    forwardBasis6MConfig.put(StripInstrumentType.SWAP_6M, new String[] {"Discounting", "ForwardBasis6M"});
    forwardBasis6MConfig.put(StripInstrumentType.LIBOR, new String[] {"ForwardBasis6M"});
    curveExposuresForInstruments.put("Discounting", new CurveInstrumentConfig(discountingConfig));
    curveExposuresForInstruments.put("ForwardBasis3M", new CurveInstrumentConfig(forwardBasis3MConfig));
    curveExposuresForInstruments.put("ForwardBasis6M", new CurveInstrumentConfig(forwardBasis6MConfig));
    return new MultiCurveCalculationConfig("DefaultThreeCurveAUDConfig", yieldCurveNames, ComputationTargetSpecification.of(target), calculationMethod, curveExposuresForInstruments);
  }

  private static MultiCurveCalculationConfig getAUDDiscountingCurveConfig() {
    final String[] yieldCurveNames = new String[] {"Discounting"};
    final Currency target = Currency.AUD;
    final String calculationMethod = MultiYieldCurvePropertiesAndDefaults.PAR_RATE_STRING;
    final LinkedHashMap<String, CurveInstrumentConfig> curveExposuresForInstruments = new LinkedHashMap<>();
    final Map<StripInstrumentType, String[]> discountingConfig = new HashMap<>();
    discountingConfig.put(StripInstrumentType.CASH, new String[] {"Discounting"});
    discountingConfig.put(StripInstrumentType.OIS_SWAP, new String[] {"Discounting", "Discounting"});
    curveExposuresForInstruments.put("Discounting", new CurveInstrumentConfig(discountingConfig));
    return new MultiCurveCalculationConfig("DiscountingAUDConfig", yieldCurveNames, ComputationTargetSpecification.of(target), calculationMethod, curveExposuresForInstruments);
  }

  private static MultiCurveCalculationConfig getAUDForwardCurvesConfig() {
    final String[] yieldCurveNames = new String[] {"ForwardBasis3M", "ForwardBasis6M"};
    final Currency target = Currency.AUD;
    final String calculationMethod = MultiYieldCurvePropertiesAndDefaults.PAR_RATE_STRING;
    final LinkedHashMap<String, CurveInstrumentConfig> curveExposuresForInstruments = new LinkedHashMap<>();
    final Map<StripInstrumentType, String[]> forwardBasis3MConfig = new HashMap<>();
    forwardBasis3MConfig.put(StripInstrumentType.BASIS_SWAP, new String[] {"Discounting", "ForwardBasis3M", "ForwardBasis6M"});
    forwardBasis3MConfig.put(StripInstrumentType.SWAP_3M, new String[] {"Discounting", "ForwardBasis3M"});
    forwardBasis3MConfig.put(StripInstrumentType.LIBOR, new String[] {"ForwardBasis3M"});
    final Map<StripInstrumentType, String[]> forwardBasis6MConfig = new HashMap<>();
    forwardBasis6MConfig.put(StripInstrumentType.BASIS_SWAP, new String[] {"Discounting", "ForwardBasis3M", "ForwardBasis6M"});
    forwardBasis6MConfig.put(StripInstrumentType.SWAP_6M, new String[] {"Discounting", "ForwardBasis6M"});
    forwardBasis6MConfig.put(StripInstrumentType.LIBOR, new String[] {"ForwardBasis6M"});
    curveExposuresForInstruments.put("ForwardBasis3M", new CurveInstrumentConfig(forwardBasis3MConfig));
    curveExposuresForInstruments.put("ForwardBasis6M", new CurveInstrumentConfig(forwardBasis6MConfig));
    final LinkedHashMap<String, String[]> exogenousConfigAndCurveNames = new LinkedHashMap<>();
    exogenousConfigAndCurveNames.put("DiscountingAUDConfig", new String[] {"Discounting"});
    return new MultiCurveCalculationConfig("ForwardFromDiscountingAUDConfig", yieldCurveNames, ComputationTargetSpecification.of(target), calculationMethod, curveExposuresForInstruments,
        exogenousConfigAndCurveNames);
  }

  private static MultiCurveCalculationConfig getSingleAUDCurveConfig() {
    final String[] yieldCurveNames = new String[] {"Single"};
    final String[] twoCurveNames = new String[] {"Single", "Single"};
    final Currency target = Currency.AUD;
    final String calculationMethod = MultiYieldCurvePropertiesAndDefaults.PAR_RATE_STRING;
    final LinkedHashMap<String, CurveInstrumentConfig> curveExposuresForInstruments = new LinkedHashMap<>();
    final Map<StripInstrumentType, String[]> singleConfig = new HashMap<>();
    singleConfig.put(StripInstrumentType.FUTURE, twoCurveNames);
    singleConfig.put(StripInstrumentType.CASH, yieldCurveNames);
    singleConfig.put(StripInstrumentType.SWAP_3M, twoCurveNames);
    singleConfig.put(StripInstrumentType.SWAP_6M, twoCurveNames);
    curveExposuresForInstruments.put("Single", new CurveInstrumentConfig(singleConfig));
    return new MultiCurveCalculationConfig("SingleAUDConfig", yieldCurveNames, ComputationTargetSpecification.of(target), calculationMethod, curveExposuresForInstruments);
  }

  private static ExposureFunctions getUsdOnOisLibor3M_FRAIRS() {
    List<String> exposureFunctions = newArrayList("Currency");
    Map<ExternalId, String> idsToNames = ImmutableMap.of(ExternalId.of("CurrencyISO", "USD"),
                                                         "USD_ON-OIS_LIBOR3M-FRAIRS");
    return new ExposureFunctions("USD_ON-OIS_LIBOR3M-FRAIRS", exposureFunctions, idsToNames);
  }

  private static CurveConstructionConfiguration getUsdOnOisLibor3M_FRAIRSCurveConstructionConfiguration() {

    List<CurveGroupConfiguration> curveGroups = newArrayList(
        new CurveGroupConfiguration(0,
                                    (Map) ImmutableMap.of("USD-ON-OIS",
                                                          newArrayList(new DiscountingCurveTypeConfiguration(
                                                                           "USD"),
                                                                       new OvernightCurveTypeConfiguration(ExternalId.of(
                                                                           OG_SYNTHETIC_TICKER,
                                                                           "FEDL01 Index"))
                                                          )
                                    )
        ),
        new CurveGroupConfiguration(1,
                                    (Map) ImmutableMap.of("USD-LIBOR3M-FRAIRS",
                                                          newArrayList(new IborCurveTypeConfiguration(ExternalId.of(
                                                              OG_SYNTHETIC_TICKER,
                                                              "USDLIBORP3M"), Tenor.parse("P3M")))
                                    )
        )
    );

    return new CurveConstructionConfiguration("USD_ON-OIS_LIBOR3M-FRAIRS", curveGroups, Collections.<String>emptyList());
  }

  private static InterpolatedCurveDefinition getInterpolatedCurveDefinition() {
    Set<CurveNode> nodes = newHashSet(
        new CashNode(Tenor.parse("P0D"), Tenor.parse("P3M"), ExternalId.of(OG_SYNTHETIC_TICKER, "USDLIBORP3M"), "USD LIBOR3M BBG Mapper"),
        new FRANode(Tenor.parse("P3M"), Tenor.parse("P6M"), ExternalId.of(OG_SYNTHETIC_TICKER, "USDLIBORP3M"), "USD LIBOR3M BBG Mapper"),
        new FRANode(Tenor.parse("P6M"), Tenor.parse("P9M"), ExternalId.of(OG_SYNTHETIC_TICKER, "USDLIBORP3M"), "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P1Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P2Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P3Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P4Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P5Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P7Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P10Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P12Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P15Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P20Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P25Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P30Y"), ExternalId.of("CONVENTION", "USD 6M Govt Fixed Leg"), ExternalId.of("CONVENTION", "USD 3M Govt Ibor Leg"), true, "USD LIBOR3M BBG Mapper")
    );
    return new InterpolatedCurveDefinition("USD-LIBOR3M-FRAIRS", nodes, "Linear", "FlatExtrapolator", "FlatExtrapolator");
  }

  private static InterpolatedCurveDefinition getInterpolatedCurveDefinition_USD_ON_OIS() {
    Set<CurveNode> nodes = newHashSet(
        new CashNode(Tenor.parse("P0D"), Tenor.parse("OVERNIGHT"), ExternalId.of("CONVENTION", "USDDepoON"), "USD LIBOR3M BBG Mapper"),
        new CashNode(Tenor.parse("OVERNIGHT"), Tenor.parse("OVERNIGHT"), ExternalId.of("CONVENTION", "USDDepoON"), "USD Depo T+1 BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P2M"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P3M"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P6M"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P9M"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P1Y"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P2Y"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P3Y"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P4Y"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P5Y"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P6Y"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P7Y"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P8Y"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P9Y"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper"),
        new SwapNode(Tenor.parse("P0D"), Tenor.parse("P10Y"), ExternalId.of("CONVENTION", "USDFixed1Y_PayLag"), ExternalId.of("CONVENTION", "USDFEDFUNDCmp1Y"), true, "USD OIS BBG Mapper")
    );
    return new InterpolatedCurveDefinition("USD-ON-OIS", nodes, "Linear", "FlatExtrapolator", "FlatExtrapolator");
  }


  private static CurveNodeIdMapper getUSDDepoT_plus_1_BBG_Mapper() {
    //USD LIBOR3M BBG Mapper
    return CurveNodeIdMapper.builder().name("USD Depo T+1 BBG Mapper")
        .cashNodeIds(
            ImmutableMap.<Tenor, CurveInstrumentProvider>of(
                Tenor.parse("OVERNIGHT"),
                new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USDR2T Curncy"),
                                                  "Market_Value",
                                                  DataFieldType.OUTRIGHT)
            )
        )
        .build();
  }


  private static CurveNodeIdMapper getUSD_LIBOR3M_BBG_Mapper() {
    //USD LIBOR3M BBG Mapper
    return CurveNodeIdMapper.builder().name("USD LIBOR3M BBG Mapper")
        .cashNodeIds(
            ImmutableMap.<Tenor, CurveInstrumentProvider>of(
                Tenor.parse("P3M"),
                new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USDLIBORP3M"),
                                                  "Market_Value",
                                                  DataFieldType.OUTRIGHT)
            )
        )
        .fraNodeIds(
            new HashMap<Tenor, CurveInstrumentProvider>() {{
              put(
                  Tenor.parse("P24M"),
                  new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USFR1I2 Curncy"),
                                                    "Market_Value",
                                                    DataFieldType.OUTRIGHT)
              );
              put(
                  Tenor.parse("P9M"),
                  new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "SFR0FI Curncy"),
                                                    "Market_Value",
                                                    DataFieldType.OUTRIGHT)
              );
              put(
                  Tenor.parse("P18M"),
                  new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USFR1C1F Curncy"),
                                                    "Market_Value",
                                                    DataFieldType.OUTRIGHT)
              );
              put(
                  Tenor.parse("P27M"),
                  new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USFR02C Curncy"),
                                                    "Market_Value",
                                                    DataFieldType.OUTRIGHT)
              );
              put(
                  Tenor.parse("P21M"),
                  new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USFR1F1I Curncy"),
                                                    "Market_Value",
                                                    DataFieldType.OUTRIGHT)
              );
              put(
                  Tenor.parse("P6M"),
                  new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USFR0CF Curncy"),
                                                    "Market_Value",
                                                    DataFieldType.OUTRIGHT)
              );
            }}
        )


        .rateFutureNodeIds(
            ImmutableMap.<Tenor, CurveInstrumentProvider>of(
                Tenor.parse("P0D"),
                new BloombergFutureCurveInstrumentProvider("PD",
                                                           "Comdty",
                                                           "Market_Value",
                                                           DataFieldType.OUTRIGHT),

                Tenor.parse("P1D"),
                new BloombergFutureCurveInstrumentProvider("PD",
                                                           "Comdty",
                                                           "Market_Value",
                                                           DataFieldType.OUTRIGHT),

                Tenor.parse("P3D"),
                new BloombergFutureCurveInstrumentProvider("PD",
                                                           "Comdty",
                                                           "Market_Value",
                                                           DataFieldType.OUTRIGHT),

                Tenor.parse("P3M"),
                new BloombergFutureCurveInstrumentProvider("PD",
                                                           "Comdty",
                                                           "Market_Value",
                                                           DataFieldType.OUTRIGHT)
            )
        )
        .swapNodeIds(
            new HashMap<Tenor, CurveInstrumentProvider>() {
              {
                put(
                    Tenor.parse("P1Y"),
                    new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW1 Curncy"),
                                                      "Market_Value",
                                                      DataFieldType.OUTRIGHT)
                );
                put(
                    Tenor.parse("P2Y"),
                    new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW2 Curncy"),
                                                      "Market_Value",
                                                      DataFieldType.OUTRIGHT)
                );
                put(
                    Tenor.parse("P3Y"),
                    new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW3 Curncy"),
                                                      "Market_Value",
                                                      DataFieldType.OUTRIGHT)
                );
                put(
                    Tenor.parse("P4Y"),
                    new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW4 Curncy"),
                                                      "Market_Value",
                                                      DataFieldType.OUTRIGHT)
                );
                put(
                    Tenor.parse("P5Y"),
                    new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW5 Curncy"),
                                                      "Market_Value",
                                                      DataFieldType.OUTRIGHT)
                );
                put(
                    Tenor.parse("P7Y"),
                    new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW7 Curncy"),
                                                      "Market_Value",
                                                      DataFieldType.OUTRIGHT)
                );
                put(
                    Tenor.parse("P10Y"),
                    new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW10 Curncy"),
                                                      "Market_Value",
                                                      DataFieldType.OUTRIGHT)
                );
                put(
                    Tenor.parse("P15Y"),
                    new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW12 Curncy"),
                                                      "Market_Value",
                                                      DataFieldType.OUTRIGHT)
                );
                put(
                    Tenor.parse("P12Y"),
                    new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW15 Curncy"),
                                                      "Market_Value",
                                                      DataFieldType.OUTRIGHT)
                );
                put(
                    Tenor.parse("P20Y"),
                    new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW20 Curncy"),
                                                      "Market_Value",
                                                      DataFieldType.OUTRIGHT)
                );
                put(
                  Tenor.parse("P25Y"),
                  new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW25 Curncy"),
                                                    "Market_Value",
                                                    DataFieldType.OUTRIGHT)
                );
                put(
                    Tenor.parse("P30Y"),
                    new StaticCurveInstrumentProvider(ExternalId.of(OG_SYNTHETIC_TICKER, "USSW30 Curncy"),
                                                      "Market_Value",
                                                      DataFieldType.OUTRIGHT)
                );
              }}
              ).build();
            }


  private static ExposureFunctions getExposureFunctions() {
    final List<String> exposureFunctions = newArrayList("Currency");
    final Map<ExternalId, String> idsToNames = ImmutableMap.of(ExternalId.of("CurrencyISO", "USD"),
                                                               "USD_ON-OIS_LIBOR3M-FRAIRS");
    return new ExposureFunctions("USD_ON-OIS_LIBOR3M-FRAIRS", exposureFunctions, idsToNames);
  }

}
