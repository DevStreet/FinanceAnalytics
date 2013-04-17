/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.language.snapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opengamma.core.marketdatasnapshot.ValueSnapshot;
import com.opengamma.core.marketdatasnapshot.VolatilityPoint;
import com.opengamma.core.marketdatasnapshot.impl.ManageableVolatilityCubeSnapshot;
import com.opengamma.language.Value;
import com.opengamma.language.ValueUtils;
import com.opengamma.language.context.SessionContext;
import com.opengamma.language.definition.Categories;
import com.opengamma.language.definition.DefinitionAnnotater;
import com.opengamma.language.definition.JavaTypeInfo;
import com.opengamma.language.definition.MetaParameter;
import com.opengamma.language.error.InvokeInvalidArgumentException;
import com.opengamma.language.function.AbstractFunctionInvoker;
import com.opengamma.language.function.MetaFunction;
import com.opengamma.language.function.PublishedFunction;

/**
 * Fetches the data from a volatility surface as a 3D matrix tensor.
 */
public class GetVolatilityCubeTensorFunction extends AbstractFunctionInvoker implements PublishedFunction {

  /**
   * Default instance.
   */
  public static final GetVolatilityCubeTensorFunction INSTANCE = new GetVolatilityCubeTensorFunction();

  private final MetaFunction _meta;

  private static List<MetaParameter> parameters() {
    return Arrays.asList(
        new MetaParameter("snapshot", JavaTypeInfo.builder(ManageableVolatilityCubeSnapshot.class).get()),
        new MetaParameter("marketValue", JavaTypeInfo.builder(Boolean.class).defaultValue(Boolean.TRUE).get()),
        new MetaParameter("overrideValue", JavaTypeInfo.builder(Boolean.class).defaultValue(Boolean.FALSE).get()));
  }

  private GetVolatilityCubeTensorFunction(final DefinitionAnnotater info) {
    super(info.annotate(parameters()));
    _meta = info.annotate(new MetaFunction(Categories.MARKET_DATA, "GetVolatilityCubeTensor", getParameters(), this));
  }

  protected GetVolatilityCubeTensorFunction() {
    this(new DefinitionAnnotater(GetVolatilityCubeTensorFunction.class));
  }

  @SuppressWarnings({"rawtypes", "unchecked" })
  public static Value[][][] invoke(final ManageableVolatilityCubeSnapshot snapshot, final Boolean marketValue, final Boolean overrideValue) {
    final Set<Comparable<Object>> keyXSet = new HashSet<>();
    final Set<Comparable<Object>> keyYSet = new HashSet<>();
    final Set<Comparable<Object>> keyZSet = new HashSet<>();
    for (final VolatilityPoint<Object, Object, Object> key : snapshot.getValues().keySet()) {
      if (key.getXAxis() instanceof Comparable) {
        keyXSet.add((Comparable<Object>) key.getXAxis());
      } else {
        throw new InvokeInvalidArgumentException(0, "cube X key '" + key.getXAxis() + "' is not comparable");
      }
      if (key.getYAxis() instanceof Comparable) {
        keyYSet.add((Comparable<Object>) key.getYAxis());
      } else {
        throw new InvokeInvalidArgumentException(0, "cube Y key '" + key.getYAxis() + "' is not comparable");
      }
      if (key.getZAxis() instanceof Comparable) {
        keyZSet.add((Comparable<Object>) key.getZAxis());
      } else {
        throw new InvokeInvalidArgumentException(0, "cube Z key '" + key.getZAxis() + "' is not comparable");
      }
    }
    final List<Comparable<Object>> keyX = new ArrayList<>(keyXSet);
    final List<Comparable<Object>> keyY = new ArrayList<>(keyYSet);
    final List<Comparable<Object>> keyZ = new ArrayList<>(keyZSet);
    Collections.sort(keyX);
    Collections.sort(keyY);
    Collections.sort(keyZ);
    final Value[][][] values = new Value[keyZ.size()][keyY.size()][keyX.size()];
    for (int i = 0; i < keyZ.size(); i++) {
      final Comparable<Object> z = keyZ.get(i);
      for (int j = 0; j < keyY.size(); j++) {
        final Comparable<Object> y = keyY.get(j);
        for (int k = 0; k < keyX.size(); k++) {
          final ValueSnapshot value = snapshot.getValues().get(new VolatilityPoint(keyX.get(k), y, z));
          if (value == null) {
            values[i][j][k] = new Value();
          } else if (Boolean.TRUE.equals(overrideValue) && (value.getOverrideValue() != null)) {
            values[i][j][k] = ValueUtils.of(value.getOverrideValue());
          } else if (Boolean.TRUE.equals(marketValue) && (value.getMarketValue() != null)) {
            values[i][j][k] = ValueUtils.of(value.getMarketValue());
          } else {
            values[i][j][k] = new Value();
          }
        }
      }
    }
    return values;
  }

  // AbstractFunctionInvoker

  @Override
  protected Object invokeImpl(final SessionContext sessionContext, final Object[] parameters) {
    return invoke((ManageableVolatilityCubeSnapshot) parameters[0], (Boolean) parameters[1], (Boolean) parameters[2]);
  }

  // PublishedFunction

  @Override
  public MetaFunction getMetaFunction() {
    return _meta;
  }

}
