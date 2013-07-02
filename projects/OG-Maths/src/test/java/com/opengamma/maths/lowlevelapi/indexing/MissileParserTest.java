/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.testng.annotations.Test;

/**
 * Tests the missile parser
 */
@Test
public class MissileParserTest {

  static GeneralIndex _3 = new GeneralIndex(3);
  static GeneralIndex _4 = new GeneralIndex(4);
  static LinearIndex _1_10 = new LinearIndex(1, 10);
  static LinearIndex _1_2_10 = new LinearIndex(1, 2, 10);
  static EndIndex _4_end = new EndIndex(4);
  static EndIndex _4_2_end = new EndIndex(4, 2);
  static EndIndex _4_end_m1 = new EndIndex(4, 1, -1);
  static EndIndex _4_2_end_m1 = new EndIndex(4, 2, -1);
  static ColonIndex colon = ColonIndex.getInstance();

  private static Map<String, IndexItem> str2idxmap = new HashMap<>();
  static {
    str2idxmap.put("3", _3);
    str2idxmap.put("4", _4);
    str2idxmap.put("1:10", _1_10);
    str2idxmap.put("1:2:10", _1_2_10);
    str2idxmap.put("4:end", _4_end);
    str2idxmap.put("4:2:end", _4_2_end);
    str2idxmap.put("4:end-1", _4_end_m1);
    str2idxmap.put("4:2:end-1", _4_2_end_m1);
    str2idxmap.put(":", colon);
  }

  @Test
  public void testOneD() {
    Iterator<Entry<String, IndexItem>> i1 = str2idxmap.entrySet().iterator();
    Entry<String, IndexItem> idx;
    while (i1.hasNext()) {
      idx = i1.next();
      check(idx.getKey(), new OneDIndex(idx.getValue()));
      check("[" + idx.getKey() + "]", new OneDIndex(idx.getValue()));
    }
  }

  @Test(dependsOnMethods = {"testOneD" })
  public void testTwoD() {
    Iterator<Entry<String, IndexItem>> i1 = str2idxmap.entrySet().iterator();
    Entry<String, IndexItem> row, col;
    while (i1.hasNext()) {
      row = i1.next();
      Iterator<Entry<String, IndexItem>> i2 = str2idxmap.entrySet().iterator();
      while (i2.hasNext()) {
        col = i2.next();
        check(row.getKey() + "," + col.getKey(), new TwoDIndex(row.getValue(), col.getValue()));
        check("[" + row.getKey() + "],[" + col.getKey() + "]", new TwoDIndex(row.getValue(), col.getValue()));
      }
    }
  }

  //FIX ME: this test currently fails because the AST and the walker don't match
  @Test(dependsOnMethods = {"testTwoD" }, enabled = false)
  public void randomOneDSelectionTest() {
    Random rand = new Random(0L);
    int maxItemsInIndex = 4;
    int nTestCycles = 100;
    Map<String, IndexItem> localstr2idxMap = new HashMap<>(str2idxmap);
    localstr2idxMap.remove(":");
    Object[] arr = localstr2idxMap.entrySet().toArray();

    for (int cycle = 0; cycle < nTestCycles; cycle++) {
      int thisManyItemsInIndex = rand.nextInt(maxItemsInIndex) + 1;
      String vector = "[";
      List<IndexItem> items = new ArrayList<>();
      for (int i = 0; i < thisManyItemsInIndex; i++) {
        @SuppressWarnings("unchecked")
        Entry<String, IndexItem> use = (Entry<String, IndexItem>) arr[rand.nextInt(localstr2idxMap.size())];
        boolean addBrackets = rand.nextInt(2) == 1 ? true : false;
        if (addBrackets) {
          vector += "[";
        }
        vector += use.getKey();
        items.add(use.getValue());
        if (addBrackets) {
          vector += "]";
        }
        if (i < thisManyItemsInIndex - 1) {
          vector += ",";
        } else {
          vector += "]";
        }
      }
      check(vector, new OneDIndex(items));
    }
  }
  
  //TODO: 2d random selection test

  private void check(String arg, Index expected)
  {
    MissileSyntax2IndexConvertor p = new MissileSyntax2IndexConvertor();
    Index got = p.eval(arg);
    assertTrue(expected.equals(got));
  }

}
