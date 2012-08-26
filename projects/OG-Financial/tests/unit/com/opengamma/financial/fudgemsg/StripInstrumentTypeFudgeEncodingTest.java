/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.fudgemsg;

import static org.testng.AssertJUnit.assertEquals;

import org.fudgemsg.UnmodifiableFudgeField;
import org.fudgemsg.wire.types.FudgeWireType;
import org.testng.annotations.Test;

import com.opengamma.financial.analytics.ircurve.StripInstrumentTypeDeprecated;

/**
 * Test StripInstrumentType Fudge support.
 */
public class StripInstrumentTypeFudgeEncodingTest extends FinancialTestBase {

  private static final StripInstrumentTypeDeprecated s_ref = StripInstrumentTypeDeprecated.FUTURE;

  @Test
  public void testCycle() {
    assertEquals(s_ref, cycleObject(StripInstrumentTypeDeprecated.class, s_ref));
  }

  @Test
  public void testFromString() {
    assertEquals(s_ref, getFudgeContext().getFieldValue(StripInstrumentTypeDeprecated.class,
        UnmodifiableFudgeField.of(FudgeWireType.STRING, s_ref.name())));
  }

}
