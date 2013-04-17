/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fudgemsg.FudgeField;
import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeBuilder;
import org.fudgemsg.mapping.FudgeBuilderFor;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;

import com.opengamma.id.UniqueIdentifiable;

/**
 * Fudge builder for {@code VolatilityCubeData}.
 */
@FudgeBuilderFor(VolatilityCubeData.class)
public class VolatilityCubeDataBuilder implements FudgeBuilder<VolatilityCubeData<Object, Object, Object>> {
  /** The definition name field */
  private static final String DEFINITION_NAME_FIELD = "definitionName";
  /** The specification name field */
  private static final String SPECIFICATION_NAME_FIELD = "specificationName";
  /** The target field */
  private static final String TARGET_FIELD = "target";
  /** The x field */
  private static final String X_FIELD = "x";
  /** The y field */
  private static final String Y_FIELD = "y";
  /** The z field */
  private static final String Z_FIELD = "z";
  /** The xs field */
  private static final String XS_FIELD = "xs";
  /** The ys field */
  private static final String YS_FIELD = "ys";
  /** The zs field */
  private static final String ZS_FIELD = "zs";
  /** The volatility data field */
  private static final String VOLATILITY_FIELD = "volatility";
  /** The volatilities data field */
  private static final String VOLATILITIES_FIELD = "volatilities";
  /** The x label field */
  private static final String X_LABEL_FIELD = "xLabel";
  /** The y label field */
  private static final String Y_LABEL_FIELD = "yLabel";
  /** The z label field */
  private static final String Z_LABEL_FIELD = "zLabel";

  @Override
  public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final VolatilityCubeData<Object, Object, Object> object) {
    final MutableFudgeMsg message = serializer.newMessage();
    message.add(TARGET_FIELD, FudgeSerializer.addClassHeader(serializer.objectToFudgeMsg(object.getTarget()), object.getTarget().getClass()));
    serializer.addToMessage(message, TARGET_FIELD, null, object.getTarget());
    message.add(DEFINITION_NAME_FIELD, object.getDefinitionName());
    message.add(SPECIFICATION_NAME_FIELD, object.getSpecificationName());
    message.add(X_LABEL_FIELD, object.getXLabel());
    message.add(Y_LABEL_FIELD, object.getYLabel());
    message.add(Z_LABEL_FIELD, object.getZLabel());
    for (final Object x : object.getXs()) {
      if (x != null) {
        message.add(XS_FIELD, null, FudgeSerializer.addClassHeader(serializer.objectToFudgeMsg(x), x.getClass()));
      }
    }
    for (final Object y : object.getYs()) {
      if (y != null) {
        message.add(YS_FIELD, null, FudgeSerializer.addClassHeader(serializer.objectToFudgeMsg(y), y.getClass()));
      }
    }
    for (final Object z : object.getZs()) {
      if (z != null) {
        message.add(ZS_FIELD, null, FudgeSerializer.addClassHeader(serializer.objectToFudgeMsg(z), z.getClass()));
      }
    }
    for (final Map.Entry<VolatilityPoint<Object, Object, Object>, Double> entry : object.getData().entrySet()) {
      final MutableFudgeMsg subMessage = serializer.newMessage();
      final VolatilityPoint<Object, Object, Object> volatilityPoint = entry.getKey();
      final Object xAxis = volatilityPoint.getXAxis();
      final Object yAxis = volatilityPoint.getYAxis();
      final Object zAxis = volatilityPoint.getZAxis();
      subMessage.add(X_FIELD, null, serializer.objectToFudgeMsg(xAxis));
      subMessage.add(Y_FIELD, null, serializer.objectToFudgeMsg(yAxis));
      subMessage.add(Z_FIELD, null, serializer.objectToFudgeMsg(zAxis));
      subMessage.add(VOLATILITY_FIELD, entry.getValue());
      message.add(VOLATILITIES_FIELD, null, subMessage);
    }
    return message;
  }

  @Override
  public VolatilityCubeData<Object, Object, Object> buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
    final UniqueIdentifiable target = deserializer.fieldValueToObject(UniqueIdentifiable.class, message.getByName(TARGET_FIELD));
    final String definitionName = message.getString(DEFINITION_NAME_FIELD);
    final String specificationName = message.getString(SPECIFICATION_NAME_FIELD);
    final String xLabel = message.getString(X_LABEL_FIELD);
    final String yLabel = message.getString(Y_LABEL_FIELD);
    final String zLabel = message.getString(Z_LABEL_FIELD);
    final List<FudgeField> volatilitiesField = message.getAllByName(VOLATILITIES_FIELD);
    final List<FudgeField> xsFields = message.getAllByName(XS_FIELD);
    final List<Object> xs = new ArrayList<>();
    Object[] xsArray = null;
    for (final FudgeField xField : xsFields) {
      final Object x = deserializer.fieldValueToObject(xField);
      xs.add(x);
      if (xsArray == null) {
        xsArray = (Object[]) Array.newInstance(x.getClass(), 0);
      }
    }
    final List<FudgeField> ysFields = message.getAllByName(YS_FIELD);
    final List<Object> ys = new ArrayList<>();
    Object[] ysArray = null;
    for (final FudgeField yField : ysFields) {
      final Object y = deserializer.fieldValueToObject(yField);
      ys.add(y);
      if (ysArray == null) {
        ysArray = (Object[]) Array.newInstance(y.getClass(), 0);
      }
    }
    final List<FudgeField> zsFields = message.getAllByName(ZS_FIELD);
    final List<Object> zs = new ArrayList<>();
    Object[] zsArray = null;
    for (final FudgeField zField : zsFields) {
      final Object z = deserializer.fieldValueToObject(zField);
      zs.add(z);
      if (zsArray == null) {
        zsArray = (Object[]) Array.newInstance(z.getClass(), 0);
      }
    }
    final Map<VolatilityPoint<Object, Object, Object>, Double> data = new HashMap<>();
    final Class<?> xClazz = xs.get(0).getClass();
    final Class<?> yClazz = ys.get(0).getClass();
    final Class<?> zClazz = zs.get(0).getClass();
    for (final FudgeField field : volatilitiesField) {
      final FudgeMsg subMessage = (FudgeMsg) field.getValue();
      final Object x = deserializer.fieldValueToObject(xClazz, subMessage.getByName(X_FIELD));
      final Object y = deserializer.fieldValueToObject(yClazz, subMessage.getByName(Y_FIELD));
      final Object z = deserializer.fieldValueToObject(zClazz, subMessage.getByName(Z_FIELD));
      final double value = subMessage.getDouble(VOLATILITY_FIELD);
      data.put(new VolatilityPoint<>(x, y, z), value);
    }
    return new VolatilityCubeData<>(definitionName, specificationName, target, xs.toArray(xsArray), xLabel,
        ys.toArray(ysArray), yLabel, zs.toArray(zsArray), zLabel, data);
  }
}
