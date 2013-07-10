/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Holds a single dimension of index items 
 */
public class OneDIndex extends Index {

  private List<IndexItem> _items;

  /**
   * A 1D index constructed from an IndexItem
   * @param item the item in this index
   */
  public OneDIndex(IndexItem item) {
    Catchers.catchNull(item);
    _items = new ArrayList<IndexItem>();
    _items.add(item);
  }

  /**
   * A 1D index constructed from IndexItems
   * @param items the items in this index
   */
  public OneDIndex(List<IndexItem> items) {
    Catchers.catchNull(items);
    Iterator<IndexItem> it = items.iterator();
    while (it.hasNext()) {
      Catchers.catchNull(it.next());
    }
    _items = new ArrayList<IndexItem>(items);
  }

  /**
   * Gets the items.
   * @return the items
   */
  public final List<IndexItem> getItems() {
    return _items;
  }

  @Override
  public String toString() {
    String items = _items.toString();
    return "ITEMS: " + items;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_items == null) ? 0 : _items.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    OneDIndex other = (OneDIndex) obj;
    if (!_items.equals(other._items)) {
      return false;
    }
    return true;
  }

  @Override
  public OGArray<? extends Number> accept(IndexVisitor visitor) {
    return visitor.visit(this);
  }

}
