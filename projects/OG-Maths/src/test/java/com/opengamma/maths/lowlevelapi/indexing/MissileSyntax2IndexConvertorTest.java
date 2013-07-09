/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionParseError;

/**
 * Tests MissileSyntax2IndexConvertor
 */
@Test
public class MissileSyntax2IndexConvertorTest {

  MissileSyntax2IndexConvertor conv = new MissileSyntax2IndexConvertor();

  @Test
  public void testDebug() {
    assertFalse(conv.isDebugOn());
  }

  @Test
  public void testwithDebug() {
    MissileSyntax2IndexConvertor lconv = new MissileSyntax2IndexConvertor(true);
    assertTrue(lconv.isDebugOn());
    Index idx;
    idx = lconv.eval("1:10");
    OneDIndex expected1d = new OneDIndex(new LinearIndex(1, 10));
    assertTrue(expected1d.equals(idx));
    idx = lconv.eval("1:10,4");
    TwoDIndex expected2d = new TwoDIndex(new LinearIndex(1, 10), new GeneralIndex(4));
    assertTrue(expected2d.equals(idx));
  }

  @Test
  public void testEval1D() {
    Index idx = conv.eval("1:10");
    OneDIndex expected = new OneDIndex(new LinearIndex(1, 10));
    assertTrue(expected.equals(idx));
  }

  @Test
  public void testEval2D() {
    Index idx = conv.eval("1:10,4");
    TwoDIndex expected = new TwoDIndex(new LinearIndex(1, 10), new GeneralIndex(4));
    assertTrue(expected.equals(idx));
  }

  @Test(expectedExceptions = MathsExceptionParseError.class)
  public void testBadSyntax() {
    conv.eval("1:10banana");
  }
  
}
