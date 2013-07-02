/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Test end index
 */
@Test
public class EndIndexTest {

  @Test
  public void test1argConstruct() {
    int start = 3;
    EndIndex tmp = new EndIndex(start);
    assertTrue(tmp.getStart() == start);
  }

  @Test
  public void test2argConstruct() {
    int start = 3;
    int step = 2;
    EndIndex tmp = new EndIndex(start, step);
    assertTrue(tmp.getStart() == start);
    assertTrue(tmp.getStep() == step);
  }

  @Test
  public void test3argConstruct() {
    int start = 3;
    int step = 2;
    int offset = -5;
    EndIndex tmp = new EndIndex(start, step, offset);
    assertTrue(tmp.getStart() == start);
    assertTrue(tmp.getStep() == step);
    assertTrue(tmp.getOffset() == offset);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testBadStartConstruct() {
    int start = -1;
    int step = 2;
    int offset = -5;
    new EndIndex(start, step, offset);
  }
  
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testBadStepConstruct() {
    int start = 3;
    int step = 0;
    int offset = -5;
    new EndIndex(start, step, offset);
  }
  
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testBadOffsetConstruct() {
    int start = 3;
    int step = 2;
    int offset = 5;
    new EndIndex(start, step, offset);
  }

  @Test
  public void testLinearise() {
    int start = 3;
    int step = 2;
    int offset = -5;
    int n = 20;
    EndIndex tmp = new EndIndex(start, step, offset);
    tmp.linearise(n);
    int[] idx = {3, 5, 7, 9, 11, 13 };
    int ptr = 0;
    for (int i = 3; i < n + offset; i += step) {
      assertTrue(idx[ptr++] == i);
    }
  }

  @Test
  public void testEquals() {
    int start = 3;
    int step = 2;
    int offset = -5;
    EndIndex expected = new EndIndex(start, step, offset);
    assertTrue(expected.equals(expected));
    assertFalse(expected.equals(null));
    assertFalse(expected.equals(new Object()));
    EndIndex tmp;
    tmp = new EndIndex(53, step, offset);
    assertFalse(expected.equals(tmp));
    tmp = new EndIndex(start, 53, offset);
    assertFalse(expected.equals(tmp));
    tmp = new EndIndex(start, step, -53);
    assertFalse(expected.equals(tmp));
    tmp = new EndIndex(start, step, offset);
    assertTrue(expected.equals(tmp));
  }

}
