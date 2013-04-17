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
 * Modifies a volatility cube to take values from the updated 2D matrix tensor.
 */
public class SetVolatilityCubeTensorFunction extends AbstractFunctionInvoker implements PublishedFunction {

  /**
   * Default instance.
   */
  public static final SetVolatilityCubeTensorFunction INSTANCE = new SetVolatilityCubeTensorFunction();

  private final MetaFunction _meta;

  private static List<MetaParameter> parameters() {
    return Arrays.asList(
        new MetaParameter("snapshot", JavaTypeInfo.builder(ManageableVolatilityCubeSnapshot.class).get()),
        new MetaParameter("overrideValue", JavaTypeInfo.builder(Value.class).arrayOf().arrayOf().arrayOf().allowNull().get()),
        new MetaParameter("marketValue", JavaTypeInfo.builder(Value.class).arrayOf().arrayOf().arrayOf().allowNull().get()));
  }

  private SetVolatilityCubeTensorFunction(final DefinitionAnnotater info) {
    super(info.annotate(parameters()));
    _meta = info.annotate(new MetaFunction(Categories.MARKET_DATA, "SetVolatilityCubeTensor", getParameters(), this));
  }

  protected SetVolatilityCubeTensorFunction() {
    this(new DefinitionAnnotater(SetVolatilityCubeTensorFunction.class));
  }

  @SuppressWarnings({"rawtypes", "unchecked" })
  public static ManageableVolatilityCubeSnapshot invoke(final ManageableVolatilityCubeSnapshot snapshot, final Value[][][] overrideValue, final Value[][][] marketValue) {
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
    if ((overrideValue != null) && (overrideValue.length < keyZ.size())) {
      throw new InvokeInvalidArgumentException(1, "Not enough planes in cube");
    }
    if ((marketValue != null) && (marketValue.length < keyZ.size())) {
      throw new InvokeInvalidArgumentException(2, "Not enough planes in cube");
    }
    for (int i = 0; i < keyZ.size(); i++) {
      final Comparable<Object> z = keyZ.get(i);
      if ((overrideValue != null) && (overrideValue[i].length < keyY.size())) {
        throw new InvokeInvalidArgumentException(1, "Not enough rows in cube");
      }
      if ((marketValue != null) && (marketValue[i].length < keyY.size())) {
        throw new InvokeInvalidArgumentException(2, "Not enough rows in cube");
      }
      for (int j = 0; j < keyY.size(); j++) {
        final Comparable<Object> y = keyY.get(j);
        if ((overrideValue != null) && (overrideValue[i][j].length < keyX.size())) {
          throw new InvokeInvalidArgumentException(1, "Not enough columns in cube");
        }
        if ((marketValue != null) && (marketValue[i][j].length < keyX.size())) {
          throw new InvokeInvalidArgumentException(2, "Not enough columns in cube");
        }
        for (int k = 0; k < keyX.size(); k++) {
          final VolatilityPoint key = new VolatilityPoint(keyX.get(k), y, z);
          final ValueSnapshot value = snapshot.getValues().get(key);
          if (marketValue != null) {
            final Double override;
            if (overrideValue != null) {
              override = overrideValue[i][j][k].getDoubleValue();
            } else {
              if (value != null) {
                override = value.getOverrideValue();
              } else {
                override = null;
              }
            }
            snapshot.getValues().put(key, new ValueSnapshot(marketValue[i][j][k].getDoubleValue(), override));
          } else if (overrideValue != null) {
            if (value != null) {
              value.setOverrideValue(overrideValue[i][j][k].getDoubleValue());
            } else {
              snapshot.getValues().put(key, new ValueSnapshot(null, overrideValue[i][j][k].getDoubleValue()));
            }
          }
        }
      }
    }
    return snapshot;
  }

  // AbstractFunctionInvoker

  @Override
  protected Object invokeImpl(final SessionContext sessionContext, final Object[] parameters) {
    return invoke((ManageableVolatilityCubeSnapshot) parameters[0], (Value[][][]) parameters[1], (Value[][][]) parameters[2]);
  }

  // PublishedFunction

  @Override
  public MetaFunction getMetaFunction() {
    return _meta;
  }

}
