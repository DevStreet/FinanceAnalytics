/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import javax.time.calendar.ZonedDateTime;

import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeBuilder;
import org.fudgemsg.mapping.FudgeBuilderFor;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;

import com.opengamma.core.security.Security;
import com.opengamma.id.ExternalId;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
@FudgeBuilderFor(CurveStripWithSecurity.class)
public class CurveStripWithSecurityBuilder implements FudgeBuilder<CurveStripWithSecurity> {
  private static final String STRIP_FIELD = "strip";
  private static final String RESOLVED_TENOR_FIELD = "resolvedTenor";
  private static final String MATURITY_FIELD = "maturity";
  private static final String IDENTIFIER_FIELD = "identifier";
  private static final String SECURITY_FIELD = "security";

  @Override
  public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final CurveStripWithSecurity object) {
    final MutableFudgeMsg message = serializer.newMessage();
    serializer.addToMessageWithClassHeaders(message, STRIP_FIELD, null, object.getStrip());
    serializer.addToMessage(message, RESOLVED_TENOR_FIELD, null, object.getResolvedTenor());
    serializer.addToMessage(message, MATURITY_FIELD, null, object.getMaturity());
    serializer.addToMessage(message, IDENTIFIER_FIELD, null, object.getSecurityIdentifier());
    serializer.addToMessageWithClassHeaders(message, SECURITY_FIELD, null, object.getSecurity());
    return message;
  }

  @Override
  public CurveStripWithSecurity buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
    final CurveStrip strip = deserializer.fieldValueToObject(CurveStrip.class, message.getByName(STRIP_FIELD));
    final Tenor resolvedTenor = deserializer.fieldValueToObject(Tenor.class, message.getByName(RESOLVED_TENOR_FIELD));
    final ZonedDateTime maturity = deserializer.fieldValueToObject(ZonedDateTime.class, message.getByName(MATURITY_FIELD));
    final ExternalId securityIdentifier = deserializer.fieldValueToObject(ExternalId.class, message.getByName(IDENTIFIER_FIELD));
    final Security security = deserializer.fieldValueToObject(Security.class, message.getByName(SECURITY_FIELD));
    return new CurveStripWithSecurity(strip, resolvedTenor, maturity, securityIdentifier, security);
  }

}
