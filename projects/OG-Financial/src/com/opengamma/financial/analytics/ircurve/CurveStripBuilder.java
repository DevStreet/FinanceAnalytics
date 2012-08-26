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

import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
/* package */final class CurveStripBuilder {
  private static final String CURVE_NODE_FIELD = "curveNode";
  private static final String CONFIGURATION_NAME_FIELD = "configurationName";

  private CurveStripBuilder() {
  }

  @FudgeBuilderFor(SwapStrip.class)
  public static final class SwapStripBuilder implements FudgeBuilder<SwapStrip> {
    private static final String RESET_TENOR_FIELD = "resetTenor";
    private static final String FLOATING_INDEX_FIELD = "floatingIndex";

    @Override
    public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final SwapStrip object) {
      final MutableFudgeMsg message = serializer.newMessage();
      serializer.addToMessage(message, CURVE_NODE_FIELD, null, object.getCurveNodePointTime());
      message.add(CONFIGURATION_NAME_FIELD, object.getConfigurationName());
      serializer.addToMessage(message, RESET_TENOR_FIELD, null, object.getResetTenor());
      message.add(FLOATING_INDEX_FIELD, object.getFloatingIndexType().name());
      return message;
    }

    @Override
    public SwapStrip buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
      final Tenor resetTenor = deserializer.fieldValueToObject(Tenor.class, message.getByName(RESET_TENOR_FIELD));
      final RateType floatingIndexType = RateType.valueOf(message.getString(FLOATING_INDEX_FIELD));
      final Tenor curveNodePointTime = deserializer.fieldValueToObject(Tenor.class, message.getByName(CURVE_NODE_FIELD));
      final String configurationName = message.getString(CONFIGURATION_NAME_FIELD);
      return new SwapStrip(resetTenor, floatingIndexType, curveNodePointTime, configurationName);
    }
  }

  @FudgeBuilderFor(FRAStrip.class)
  public static final class FRAStripBuilder implements FudgeBuilder<FRAStrip> {
    private static final String RESET_TENOR_FIELD = "resetTenor";
    private static final String FLOATING_INDEX_FIELD = "floatingIndex";

    @Override
    public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final FRAStrip object) {
      final MutableFudgeMsg message = serializer.newMessage();
      serializer.addToMessage(message, CURVE_NODE_FIELD, null, object.getCurveNodePointTime());
      message.add(CONFIGURATION_NAME_FIELD, object.getConfigurationName());
      serializer.addToMessage(message, RESET_TENOR_FIELD, null, object.getResetTenor());
      message.add(FLOATING_INDEX_FIELD, object.getFloatingIndexType().name());
      return message;
    }

    @Override
    public FRAStrip buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
      final Tenor resetTenor = deserializer.fieldValueToObject(Tenor.class, message.getByName(RESET_TENOR_FIELD));
      final RateType floatingIndexType = RateType.valueOf(message.getString(FLOATING_INDEX_FIELD));
      final Tenor curveNodePointTime = deserializer.fieldValueToObject(Tenor.class, message.getByName(CURVE_NODE_FIELD));
      final String configurationName = message.getString(CONFIGURATION_NAME_FIELD);
      return new FRAStrip(resetTenor, floatingIndexType, curveNodePointTime, configurationName);
    }
  }

  @FudgeBuilderFor(FXForwardStrip.class)
  public static final class FXForwardStripBuilder implements FudgeBuilder<FXForwardStrip> {
    private static final String PAY_CURRENCY_FIELD = "payCurrency";
    private static final String RECEIVE_CURRENCY_FIELD = "receiveCurrency";

    @Override
    public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final FXForwardStrip object) {
      final MutableFudgeMsg message = serializer.newMessage();
      serializer.addToMessage(message, CURVE_NODE_FIELD, null, object.getCurveNodePointTime());
      message.add(CONFIGURATION_NAME_FIELD, object.getConfigurationName());
      serializer.addToMessage(message, PAY_CURRENCY_FIELD, null, object.getPayCurrency());
      serializer.addToMessage(message, RECEIVE_CURRENCY_FIELD, null, object.getReceiveCurrency());
      return message;
    }

    @Override
    public FXForwardStrip buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
      final Currency payCurrency = deserializer.fieldValueToObject(Currency.class, message.getByName(PAY_CURRENCY_FIELD));
      final Currency receiveCurrency = deserializer.fieldValueToObject(Currency.class, message.getByName(RECEIVE_CURRENCY_FIELD));
      final Tenor curveNodePointTime = deserializer.fieldValueToObject(Tenor.class, message.getByName(CURVE_NODE_FIELD));
      final String configurationName = message.getString(CONFIGURATION_NAME_FIELD);
      return new FXForwardStrip(payCurrency, receiveCurrency, curveNodePointTime, configurationName);
    }

  }

  @FudgeBuilderFor(RateStrip.class)
  public static final class RateStripBuilder implements FudgeBuilder<RateStrip> {
    private static final String RATE_TYPE_FIELD = "rateType";

    @Override
    public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final RateStrip object) {
      final MutableFudgeMsg message = serializer.newMessage();
      serializer.addToMessage(message, CURVE_NODE_FIELD, null, object.getCurveNodePointTime());
      message.add(CONFIGURATION_NAME_FIELD, object.getConfigurationName());
      message.add(RATE_TYPE_FIELD, object.getRateType().name());
      return message;
    }

    @Override
    public RateStrip buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
      final RateType rateType = RateType.valueOf(message.getString(RATE_TYPE_FIELD));
      final Tenor curveNodePointTime = deserializer.fieldValueToObject(Tenor.class, message.getByName(CURVE_NODE_FIELD));
      final String configurationName = message.getString(CONFIGURATION_NAME_FIELD);
      return new RateStrip(rateType, curveNodePointTime, configurationName);
    }

  }

  @FudgeBuilderFor(OISStrip.class)
  public static final class OISStripBuilder implements FudgeBuilder<OISStrip> {

    @Override
    public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final OISStrip object) {
      final MutableFudgeMsg message = serializer.newMessage();
      serializer.addToMessage(message, CURVE_NODE_FIELD, null, object.getCurveNodePointTime());
      message.add(CONFIGURATION_NAME_FIELD, object.getConfigurationName());
      return message;
    }

    @Override
    public OISStrip buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
      final Tenor curveNodePointTime = deserializer.fieldValueToObject(Tenor.class, message.getByName(CURVE_NODE_FIELD));
      final String configurationName = message.getString(CONFIGURATION_NAME_FIELD);
      return new OISStrip(curveNodePointTime, configurationName);
    }

  }
}
