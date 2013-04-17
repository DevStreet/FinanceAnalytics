/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;

import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.ArgumentChecker;

/**
 * A key used to identify a volatility cube.
 * <p>
 * This class is immutable and thread-safe.
 */
public class VolatilityCubeKey extends StructuredMarketDataKey implements Comparable<VolatilityCubeKey> {

  /** Serialization version. */
  private static final long serialVersionUID = 3L;

  /**
   * The target.
   */
  private final UniqueId _target;
  /**
   * The cube name.
   */
  private final String _name;
  /**
   * The instrument type.
   */
  private final String _instrumentType;
  /**
   * The quote type.
   */
  private final String _quoteType;
  /**
   * The quote units.
   */
  private final String _quoteUnits;

  /**
   * Creates an instance.
   * 
   * @param currency  the currency
   * @param name  the name
   */
  public VolatilityCubeKey(final UniqueIdentifiable target, final String name, final String instrumentType, final String quoteType, final String quoteUnits) {
    ArgumentChecker.notNull(target, "target");
    ArgumentChecker.notNull(name, "name");
    ArgumentChecker.notNull(instrumentType, "instrument type");
    ArgumentChecker.notNull(quoteType, "quote type");
    ArgumentChecker.notNull(quoteUnits, "quote units");
    _target = target.getUniqueId();
    _name = name;
    _instrumentType = instrumentType;
    _quoteType = quoteType;
    _quoteUnits = quoteUnits;
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the target field
   * 
   * @return the target
   */
  public UniqueId getTarget() {
    return _target;
  }

  /**
   * Gets the name.
   * 
   * @return the name
   */
  public String getName() {
    return _name;
  }

  /**
   * Gets the instrument type.
   * @return the instrument type
   */
  public String getInstrumentType() {
    return _instrumentType;
  }

  /**
   * Gets the quote type.
   * @return the quote type
   */
  public String getQuoteType() {
    return _quoteType;
  }

  /**
   * Gets the quote units.
   * @return the quote units
   */
  public String getQuoteUnits() {
    return _quoteUnits;
  }

  //-------------------------------------------------------------------------
  /**
   * Compares this key to another, by currency then name.
   * 
   * @param other  the other key, not null
   * @return the comparison value
   */
  @Override
  public int compareTo(final VolatilityCubeKey other) {
    int i = _target.compareTo(other.getTarget());
    if (i != 0) {
      return i;
    }
    i = _name.compareTo(other.getName());
    if (i != 0) {
      return i;
    }
    i = _instrumentType.compareTo(other._instrumentType);
    if (i != 0) {
      return i;
    }
    i = _quoteType.compareTo(other._quoteType);
    if (i != 0) {
      return i;
    }
    return _quoteUnits.compareTo(other._quoteUnits);
  }

  /**
   * Checks if this key equals another.
   * <p>
   * This checks the currency and name.
   * 
   * @param object  the object to compare to, null returns false
   * @return true if equal
   */
  @Override
  public boolean equals(final Object object) {
    if (object == this) {
      return true;
    }
    if (object instanceof VolatilityCubeKey) {
      final VolatilityCubeKey other = (VolatilityCubeKey) object;
      return _target.equals(other._target)
          && _name.equals(other._name)
          && _instrumentType.equals(other._instrumentType)
          && _quoteType.equals(other._quoteType)
          && _quoteUnits.equals(other._quoteUnits);
    }
    return false;
  }

  /**
   * Returns a suitable hash code.
   * 
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return _target.hashCode() ^ _name.hashCode() ^ _instrumentType.hashCode() ^ _quoteType.hashCode() ^ _quoteUnits.hashCode();
  }

  @Override
  public <T> T accept(final Visitor<T> visitor) {
    return visitor.visitVolatilityCubeKey(this);
  }

  public MutableFudgeMsg toFudgeMsg(final FudgeSerializer serializer) {
    final MutableFudgeMsg msg = serializer.newMessage();
    msg.add("target", _target.toString());
    msg.add("name", _name);
    msg.add("instrumentType", _instrumentType);
    msg.add("quoteType", _quoteType);
    msg.add("quoteUnits", _quoteUnits);
    return msg;
  }

  public static VolatilityCubeKey fromFudgeMsg(final FudgeDeserializer deserializer, final FudgeMsg msg) {
    final UniqueId target = UniqueId.parse(msg.getString("target"));
    final String name = msg.getString("name");
    final String instrumentType = msg.getString("instrumentType");
    final String quoteType = msg.getString("quoteType");
    final String quoteUnits = msg.getString("quoteUnits");
    return new VolatilityCubeKey(target, name, instrumentType, quoteType, quoteUnits);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
