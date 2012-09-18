/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeBuilder;
import org.fudgemsg.mapping.FudgeBuilderFor;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;

import com.opengamma.id.ExternalId;

/**
 * 
 */
@FudgeBuilderFor(CurveStripWithIdentifier.class)
public class CurveStripWithIdentifierBuilder implements FudgeBuilder<CurveStripWithIdentifier> {
  private static final String STRIP_FIELD = "strip";
  private static final String ID_FIELD = "id";

  @Override
  public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final CurveStripWithIdentifier object) {
    final MutableFudgeMsg message = serializer.newMessage();
    serializer.addToMessageWithClassHeaders(message, STRIP_FIELD, null, object.getStrip());
    serializer.addToMessage(message, ID_FIELD, null, object.getIdentifier());
    return message;
  }

  @Override
  public CurveStripWithIdentifier buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
    final CurveStrip strip = deserializer.fieldValueToObject(CurveStrip.class, message.getByName(STRIP_FIELD));
    final ExternalId id = deserializer.fieldValueToObject(ExternalId.class, message.getByName(ID_FIELD));
    return new CurveStripWithIdentifier(strip, id);
  }
}
