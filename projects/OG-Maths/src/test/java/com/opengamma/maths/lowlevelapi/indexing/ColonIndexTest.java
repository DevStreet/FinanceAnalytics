/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

/**
 * Test colon index
 */
@Test
public class ColonIndexTest {

  @Test
  public void testGetInstance() {
    ColonIndex.getInstance();
  }

  @Test
  public void testLinearise() {
    int n = 10;
    int[] idx = ColonIndex.getInstance().linearise(n);
    assertTrue(idx.length == n);
    for (int i = 0; i < n; i++) {
      assertTrue(idx[i] == i);
    }
  }

  @Test
  public void testEquals() {
    assertTrue(ColonIndex.getInstance().equals(ColonIndex.getInstance()));
    assertFalse(ColonIndex.getInstance().equals(new ColonIndex()));
  }

}
