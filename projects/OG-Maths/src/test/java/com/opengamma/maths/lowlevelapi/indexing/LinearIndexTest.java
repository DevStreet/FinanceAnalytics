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
public class LinearIndexTest {

  @Test
  public void test2argConstruct() {
    int low = 3;
    int high = 10;
    LinearIndex tmp = new LinearIndex(low, high);
    assertTrue(tmp.getLow() == low);
    assertTrue(tmp.getHigh() == high);
  }

  @Test
  public void test3argConstruct() {
    int low = 3;
    int step = 2;
    int high = 15;
    LinearIndex tmp = new LinearIndex(low, step, high);
    assertTrue(tmp.getLow() == low);
    assertTrue(tmp.getStep() == step);
    assertTrue(tmp.getHigh() == high);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testBadlowConstruct() {
    int low = -1;
    int step = 2;
    int high = 5;
    new LinearIndex(low, step, high);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testBadStepConstruct() {
    int low = 3;
    int step = 0;
    int high = 5;
    new LinearIndex(low, step, high);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testBadhighConstruct() {
    int low = 3;
    int step = 2;
    int high = -5;
    new LinearIndex(low, step, high);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testRelationalConstruct() {
    int low = 3;
    int step = -1;
    int high = 5;
    new LinearIndex(low, step, high);
  }

  @Test
  public void testRelationalConstructOK() {
    int low = 10;
    int step = -1;
    int high = 5;
    new LinearIndex(low, step, high);
  }

  @Test
  public void testLinearise() {
    int low = 3;
    int step = 2;
    int high = 20;
    int n = 29;
    LinearIndex tmp = new LinearIndex(low, step, high);
    tmp.linearise(n);
    int[] idx = {3, 5, 7, 9, 11, 13, 15, 17, 19 };
    int ptr = 0;
    for (int i = low; i < high; i += step) {
      assertTrue(idx[ptr++] == i);
    }
  }

  @Test
  public void testLineariseBackwards() {
    int low = 20;
    int step = -2;
    int high = 3;
    int n = 29;
    LinearIndex tmp = new LinearIndex(low, step, high);
    tmp.linearise(n);
    int[] idx = {20, 18, 16, 14, 12, 10, 8, 6, 4 };
    int ptr = 0;
    for (int i = low; i < high; i += step) {
      assertTrue(idx[ptr++] == i);
    }
  }

  @Test
  public void testEquals() {
    int low = 3;
    int step = 2;
    int high = 20;
    LinearIndex expected = new LinearIndex(low, step, high);
    assertTrue(expected.equals(expected));
    assertFalse(expected.equals(null));
    assertFalse(expected.equals(new Object()));
    LinearIndex tmp;
    tmp = new LinearIndex(4, step, high);
    assertFalse(expected.equals(tmp));
    tmp = new LinearIndex(low, 3, high);
    assertFalse(expected.equals(tmp));
    tmp = new LinearIndex(low, step, 5);
    assertFalse(expected.equals(tmp));
    tmp = new LinearIndex(low, step, high);
    assertTrue(expected.equals(tmp));
  }

}
