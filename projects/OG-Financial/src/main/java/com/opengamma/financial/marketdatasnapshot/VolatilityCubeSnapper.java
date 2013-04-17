/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.marketdatasnapshot;

import com.opengamma.core.marketdatasnapshot.VolatilityCubeData;
import com.opengamma.core.marketdatasnapshot.VolatilityCubeKey;
import com.opengamma.core.marketdatasnapshot.VolatilityCubeSnapshot;
import com.opengamma.engine.value.ValuePropertyNames;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.engine.view.ViewComputationResultModel;
import com.opengamma.financial.analytics.model.InstrumentTypeProperties;
import com.opengamma.financial.analytics.volatility.cube.VolatilityCubeDefinitionSource;
import com.opengamma.financial.analytics.volatility.surface.SurfaceAndCubePropertyNames;
import com.opengamma.id.UniqueId;

/**
 * 
 */
public class VolatilityCubeSnapper extends StructuredSnapper<VolatilityCubeKey, VolatilityCubeData, VolatilityCubeSnapshot> {


  public VolatilityCubeSnapper(final VolatilityCubeDefinitionSource cubeDefinitionSource) {
    super(ValueRequirementNames.VOLATILITY_CUBE_MARKET_DATA);
  }

  @Override
  VolatilityCubeKey getKey(final ValueSpecification spec) {
    final UniqueId uniqueId = spec.getTargetSpecification().getUniqueId();
    final String surface = getSingleProperty(spec, ValuePropertyNames.CUBE);
    final String instrumentType = getSingleProperty(spec, InstrumentTypeProperties.PROPERTY_INSTRUMENT_TYPE);
    final String quoteType = getSingleProperty(spec, SurfaceAndCubePropertyNames.PROPERTY_CUBE_QUOTE_TYPE);
    final String quoteUnits = getSingleProperty(spec, SurfaceAndCubePropertyNames.PROPERTY_CUBE_UNITS);
    return new VolatilityCubeKey(uniqueId, surface, instrumentType, quoteType, quoteUnits);
  }

  @Override
  VolatilityCubeSnapshot buildSnapshot(final ViewComputationResultModel resultModel, final VolatilityCubeKey key, final VolatilityCubeData volatilityCubeData) {
    //    final ManageableVolatilityCubeSnapshot ret = new ManageableVolatilityCubeSnapshot();
    //
    //    final ManageableUnstructuredMarketDataSnapshot otherValues = getUnstructured(volatilityCubeData.getOtherData());
    //
    //    final Map<VolatilityPoint, ValueSnapshot> values = new HashMap<>();
    //
    //    //fill with nulls
    //    final VolatilityCubeDefinition definition = _cubeDefinitionSource.getDefinition(key.getCurrency(), key.getName());
    //
    //    final Iterable<VolatilityPoint> allPoints = definition.getAllPoints();
    //    for (final VolatilityPoint point : allPoints) {
    //      values.put(point, new ValueSnapshot(null));
    //    }
    //
    //    for (final Entry<VolatilityPoint, Double> ycp : volatilityCubeData.getDataPoints().entrySet()) {
    //      values.put(ycp.getKey(), new ValueSnapshot(ycp.getValue()));
    //    }
    //
    //    final Map<Pair<Tenor, Tenor>, ValueSnapshot> strikes = new HashMap<>();
    //    for (final Entry<Pair<Tenor, Tenor>, Double> strike : volatilityCubeData.getATMStrikes().entrySet()) {
    //      strikes.put(strike.getKey(), new ValueSnapshot(strike.getValue()));
    //    }
    //
    //    ret.setOtherValues(otherValues);
    //    ret.setValues(values);
    //    ret.setStrikes(strikes);
    //    return ret;
    return null;
  }

}
