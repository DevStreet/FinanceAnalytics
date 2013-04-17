/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot;

import java.util.Map;

import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeBuilder;
import org.fudgemsg.mapping.FudgeBuilderFor;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;

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
  /** The x field */
  private static final String Y_FIELD = "y";
  /** The x field */
  private static final String Z_FIELD = "z";
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
    for (final Map.Entry<?, Double> entry : object.getData().entrySet()) {
      final MutableFudgeMsg subMessage = serializer.newMessage();
      final VolatilityPoint volatilityPoint = (VolatilityPoint) entry.getKey();
      subMessage.add(X_FIELD, null, serializer.objectToFudgeMsg(volatilityPoint.getXAxis()));
      subMessage.add(Y_FIELD, null, serializer.objectToFudgeMsg(volatilityPoint.getYAxis()));
      subMessage.add(Z_FIELD, null, serializer.objectToFudgeMsg(volatilityPoint.getZAxis()));
      subMessage.add(VOLATILITY_FIELD, entry.getValue());
      message.add(VOLATILITIES_FIELD, null, subMessage);
    }
    return null;
  }

  @Override
  public VolatilityCubeData<?, ?, ?> buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
    return null;
  }

}
