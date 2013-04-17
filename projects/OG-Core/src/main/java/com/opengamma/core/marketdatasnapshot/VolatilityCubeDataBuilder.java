/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot;

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
public class VolatilityCubeDataBuilder implements FudgeBuilder<VolatilityCubeData<?, ?, ?>> {
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
  /** The x field */
  private static final String X_CLASS_FIELD = "xClass";
  /** The y field */
  private static final String Y_CLASS_FIELD = "yClass";
  /** The z field */
  private static final String Z_CLASS_FIELD = "zClass";
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
  public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final VolatilityCubeData<?, ?, ?> object) {
    final MutableFudgeMsg message = serializer.newMessage();
    message.add(TARGET_FIELD, FudgeSerializer.addClassHeader(serializer.objectToFudgeMsg(object.getTarget()), object.getTarget().getClass()));
    serializer.addToMessage(message, TARGET_FIELD, null, object.getTarget());
    message.add(DEFINITION_NAME_FIELD, object.getDefinitionName());
    message.add(SPECIFICATION_NAME_FIELD, object.getSpecificationName());
    message.add(X_LABEL_FIELD, object.getXLabel());
    message.add(Y_LABEL_FIELD, object.getYLabel());
    message.add(Z_LABEL_FIELD, object.getZLabel());
    int i = 0;
    for (final Map.Entry<?, Double> entry : object.getData().entrySet()) {
      final MutableFudgeMsg subMessage = serializer.newMessage();
      final VolatilityPoint volatilityPoint = (VolatilityPoint) entry.getKey();
      final Comparable<?> xAxis = volatilityPoint.getXAxis();
      final Comparable<?> yAxis = volatilityPoint.getYAxis();
      final Comparable<?> zAxis = volatilityPoint.getZAxis();
      subMessage.add(X_FIELD, null, serializer.objectToFudgeMsg(xAxis));
      subMessage.add(Y_FIELD, null, serializer.objectToFudgeMsg(yAxis));
      subMessage.add(Z_FIELD, null, serializer.objectToFudgeMsg(zAxis));
      subMessage.add(VOLATILITY_FIELD, entry.getValue());
      message.add(VOLATILITIES_FIELD, null, subMessage);
      if (i == 0) {
        subMessage.add(X_CLASS_FIELD, null, FudgeSerializer.addClassHeader(serializer.objectToFudgeMsg(xAxis), xAxis.getClass()));
        subMessage.add(Y_CLASS_FIELD, null, FudgeSerializer.addClassHeader(serializer.objectToFudgeMsg(yAxis), yAxis.getClass()));
        subMessage.add(Z_CLASS_FIELD, null, FudgeSerializer.addClassHeader(serializer.objectToFudgeMsg(zAxis), zAxis.getClass()));
      }
      i++;
    }
    return message;
  }

  @Override
  public VolatilityCubeData<?, ?, ?> buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
    final UniqueIdentifiable target = deserializer.fieldValueToObject(UniqueIdentifiable.class, message.getByName(TARGET_FIELD));
    final String definitionName = message.getString(DEFINITION_NAME_FIELD);
    final String specificationName = message.getString(SPECIFICATION_NAME_FIELD);
    final String xLabel = message.getString(X_LABEL_FIELD);
    final String yLabel = message.getString(Y_LABEL_FIELD);
    final String zLabel = message.getString(Z_LABEL_FIELD);
    final List<FudgeField> volatilitiesField = message.getAllByName(VOLATILITIES_FIELD);
    int i = 0;
    final Map<VolatilityPoint, Double> data = new HashMap<>();
    Class<?> xClazz = null;
    Class<?> yClazz = null;
    Class<?> zClazz = null;
    for (final FudgeField field : volatilitiesField) {
      final FudgeMsg subMessage = (FudgeMsg) field.getValue();
      if (i == 0) {
        xClazz = deserializer.fieldValueToObject(subMessage.getByName(X_CLASS_FIELD)).getClass();
        yClazz = deserializer.fieldValueToObject(subMessage.getByName(Y_CLASS_FIELD)).getClass();
        zClazz = deserializer.fieldValueToObject(subMessage.getByName(Z_CLASS_FIELD)).getClass();
      }
      final Comparable<?> x = (Comparable<?>) deserializer.fieldValueToObject(xClazz, subMessage.getByName(X_FIELD));
      final Comparable<?> y = (Comparable<?>) deserializer.fieldValueToObject(yClazz, subMessage.getByName(Y_FIELD));
      final Comparable<?> z = (Comparable<?>) deserializer.fieldValueToObject(zClazz, subMessage.getByName(Z_FIELD));
      final double value = subMessage.getDouble(VOLATILITY_FIELD);
      data.put(new VolatilityPoint(x, y, z), value);
      i++;
    }
    return new VolatilityCubeData(definitionName, specificationName, target, xLabel, yLabel, zLabel, data);
  }
}
