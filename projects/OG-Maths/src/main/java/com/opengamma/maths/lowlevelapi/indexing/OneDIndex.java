/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a single dimension of index items 
 */
public class OneDIndex extends Index {

  private List<IndexItem> _items;

  /**
   * A 1D index constructed from IndexItems
   * @param items the items in this index
   */
  public OneDIndex(List<IndexItem> items) {
    _items = new ArrayList<IndexItem>(items);
  }
  
  /**
   * Gets the items.
   * @return the items
   */
  public final List<IndexItem> getItems() {
    return _items;
  }

}
