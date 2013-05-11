/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot.impl;

import java.util.HashMap;
import java.util.Map;

import org.fudgemsg.FudgeField;
import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;
import org.fudgemsg.types.IndicatorType;

import com.opengamma.core.marketdatasnapshot.ValueSnapshot;
import com.opengamma.core.marketdatasnapshot.VolatilityCubeSnapshot;
import com.opengamma.core.marketdatasnapshot.VolatilityPoint;
<<<<<<< HEAD
=======
import com.opengamma.util.time.Tenor;
import com.opengamma.util.tuple.ObjectsPairFudgeBuilder;
import com.opengamma.lambdava.tuple.Pair;
>>>>>>> develop

/**
 * 
 */
public class ManageableVolatilityCubeSnapshot implements VolatilityCubeSnapshot {

  /**
   * The values in the snapshot.
   */
  private Map<VolatilityPoint<Object, Object, Object>, ValueSnapshot> _values;

  @Override
  public Map<VolatilityPoint<Object, Object, Object>, ValueSnapshot> getValues() {
    return _values;
  }

  public void setValues(final Map<VolatilityPoint<Object, Object, Object>, ValueSnapshot> values) {
    _values = values;
  }

  /**
   * Creates a Fudge representation of the snapshot:
   * <pre>
   *   message {
   *     message { // map
   *       repeated VolatilityPoint key = 1;
   *       repeated ValueSnapshot value = 2;
   *     } values;
   *   }
   * </pre>
   * 
   * @param serializer Fudge serialization context, not null
   * @return the message representation of this snapshot
   */
  public FudgeMsg toFudgeMsg(final FudgeSerializer serializer) {
    final MutableFudgeMsg ret = serializer.newMessage();
    // TODO: this should not be adding it's own class header; the caller should be doing that, or this be registered as a generic builder for VolatilitySurfaceSnapshot and that class name be added
    FudgeSerializer.addClassHeader(ret, ManageableVolatilityCubeSnapshot.class);
    final MutableFudgeMsg valuesMsg = serializer.newMessage();
    if (_values != null) {
      for (final Map.Entry<VolatilityPoint<Object, Object, Object>, ValueSnapshot> entry : _values.entrySet()) {
        serializer.addToMessage(valuesMsg, null, 1, entry.getKey());
        if (entry.getValue() == null) {
          valuesMsg.add(2, IndicatorType.INSTANCE);
        } else {
          serializer.addToMessage(valuesMsg, null, 2, entry.getValue());
        }
      }
    }
    ret.add("values", valuesMsg);
    return ret;
  }

  // TODO: externalize the message representation to a Fudge builder

  /**
   * Creates a snapshot object from a Fudge message representation. See {@link #toFudgeMsg}
   * for the message format.
   * 
   * @param deserializer the Fudge deserialization context, not null
   * @param msg message containing the snapshot representation, not null
   * @return a snapshot object
   */
  @SuppressWarnings("unchecked")
  public static ManageableVolatilityCubeSnapshot fromFudgeMsg(final FudgeDeserializer deserializer, final FudgeMsg msg) {
    final HashMap<VolatilityPoint<Object, Object, Object>, ValueSnapshot> values = new HashMap<>();
    VolatilityPoint<Object, Object, Object> key = null;
    for (final FudgeField fudgeField : msg.getMessage("values")) {
      final Integer ordinal = fudgeField.getOrdinal();
      if (ordinal == null) {
        continue;
      }
      final int intValue = ordinal.intValue();
      if (intValue == 1) {
        key = deserializer.fieldValueToObject(VolatilityPoint.class, fudgeField);
      } else if (intValue == 2) {
        final ValueSnapshot value = deserializer.fieldValueToObject(ValueSnapshot.class, fudgeField);
        values.put(key, value);
        key = null;
      }
    }
    final ManageableVolatilityCubeSnapshot ret = new ManageableVolatilityCubeSnapshot();
    ret.setValues(values);
    return ret;
  }
}
