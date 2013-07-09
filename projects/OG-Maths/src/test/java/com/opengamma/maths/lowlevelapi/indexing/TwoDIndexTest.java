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
 * tests the TwoDIndex class
 */
@Test
public class TwoDIndexTest {
  IndexItem item1 = new LinearIndex(1, 10);
  IndexItem item2 = new LinearIndex(1, 100);

  @Test
  public void singleItemConstructorTest() {
    TwoDIndex idx = new TwoDIndex(item1, item2);
    List<IndexItem> expected1 = new ArrayList<IndexItem>();
    List<IndexItem> expected2 = new ArrayList<IndexItem>();
    expected1.add(item1);
    expected2.add(item2);
    assertTrue(idx.getRowItems().equals(expected1));
    assertTrue(idx.getColItems().equals(expected2));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void singleNullItem1stConstructorTest() {
    IndexItem item = null;
    new TwoDIndex(item, item2);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void singleNullItem2ndConstructorTest() {
    IndexItem item = null;
    new TwoDIndex(item1, item);
  }

  @Test
  public void multiItemConstructorTest() {
    List<IndexItem> input1 = new ArrayList<IndexItem>();
    List<IndexItem> input2 = new ArrayList<IndexItem>();
    input1.add(item1);
    input1.add(item1);
    input1.add(item1);

    input2.add(item2);
    input2.add(item2);

    TwoDIndex idx = new TwoDIndex(input1, input2);
    List<IndexItem> expected1 = new ArrayList<IndexItem>();
    expected1.add(item1);
    expected1.add(item1);
    expected1.add(item1);

    List<IndexItem> expected2 = new ArrayList<IndexItem>();
    expected2.add(item2);
    expected2.add(item2);

    assertTrue(idx.getRowItems().equals(expected1));
    assertTrue(idx.getColItems().equals(expected2));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void multiItemNull1stConstructorTest() {
    List<IndexItem> input = null;
    List<IndexItem> input2 = new ArrayList<IndexItem>();
    input2.add(item2);
    new TwoDIndex(input, input2);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void multiItemNull2ndConstructorTest() {
    List<IndexItem> input = null;
    List<IndexItem> input1 = new ArrayList<IndexItem>();
    input1.add(item1);
    new TwoDIndex(input1, input);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void multiItemWithNull1stIntermediateConstructorTest() {
    List<IndexItem> input2 = new ArrayList<IndexItem>();
    input2.add(item2);
    List<IndexItem> input = new ArrayList<IndexItem>();
    input.add(item1);
    input.add(null);
    input.add(item1);
    new TwoDIndex(input, input2);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void multiItemWithNull2ndIntermediateConstructorTest() {
    List<IndexItem> input1 = new ArrayList<IndexItem>();
    input1.add(item1);
    List<IndexItem> input = new ArrayList<IndexItem>();
    input.add(item2);
    input.add(null);
    input.add(item2);
    new TwoDIndex(input1, input);
  }

  @Test
  public void equalsTest() {
    List<IndexItem> input1 = new ArrayList<IndexItem>();
    input1.add(item1);
    input1.add(item1);
    input1.add(item1);
    List<IndexItem> input2 = new ArrayList<IndexItem>();
    input2.add(item2);
    input2.add(item2);
    
    TwoDIndex idx = new TwoDIndex(input1, input2);
    assertTrue(idx.equals(idx));
    assertFalse(idx.equals(null));
    assertFalse(idx.equals(new Object()));
    
    List<IndexItem> different1 = new ArrayList<IndexItem>();
    different1.add(item1);
    different1.add(item1);
    List<IndexItem> different2 = new ArrayList<IndexItem>();
    different2.add(item2);
    assertFalse(idx.equals(new TwoDIndex(different1, different2)));
    
    different1.add(item1);
    assertFalse(idx.equals(new TwoDIndex(different1, different2)));
    
    different2.add(item2);
    assertTrue(idx.equals(new TwoDIndex(different1, different2)));
  }
}
