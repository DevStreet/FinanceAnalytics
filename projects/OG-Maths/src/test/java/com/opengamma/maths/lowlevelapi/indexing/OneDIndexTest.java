/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;

/**
 * tests the OneDIndex class
 */
@Test
public class OneDIndexTest {

  @Test
  public void singleItemConstructorTest() {
    IndexItem item = new LinearIndex(1, 10);
    OneDIndex idx = new OneDIndex(item);
    List<IndexItem> expected = new ArrayList<IndexItem>();
    expected.add(item);
    assertTrue(idx.getItems().equals(expected));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void singleNullItemConstructorTest() {
    IndexItem item = null;
    new OneDIndex(item);
  }

  @Test
  public void multiItemConstructorTest() {
    IndexItem item = new LinearIndex(1, 10);
    List<IndexItem> input = new ArrayList<IndexItem>();
    input.add(item);
    input.add(item);
    input.add(item);
    OneDIndex idx = new OneDIndex(input);
    List<IndexItem> expected = new ArrayList<IndexItem>();
    expected.add(item);
    expected.add(item);
    expected.add(item);
    assertTrue(idx.getItems().equals(expected));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void multiItemNullConstructorTest() {
    List<IndexItem> input = null;
    new OneDIndex(input);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void multiItemWithNullConstructorTest() {
    IndexItem item = new LinearIndex(1, 10);
    List<IndexItem> input = new ArrayList<IndexItem>();
    input.add(item);
    input.add(null);
    input.add(item);
    new OneDIndex(input);
  }

  @Test
  public void equalsTest() {
    IndexItem item = new LinearIndex(1, 10);
    List<IndexItem> input = new ArrayList<IndexItem>();
    input.add(item);
    input.add(item);
    input.add(item);
    OneDIndex idx = new OneDIndex(input);
    assertTrue(idx.equals(idx));
    assertFalse(idx.equals(null));
    assertFalse(idx.equals(new Object()));    
    List<IndexItem> different = new ArrayList<IndexItem>();
    different.add(item);
    different.add(item);
    assertFalse(idx.equals(new OneDIndex(different)));
    different.add(item);
    assertTrue(idx.equals(new OneDIndex(different)));
  }
}
