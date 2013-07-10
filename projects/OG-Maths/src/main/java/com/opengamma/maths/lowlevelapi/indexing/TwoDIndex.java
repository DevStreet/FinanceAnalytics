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
 * Holds two dimensions of index items 
 */
public class TwoDIndex extends Index {

  private List<IndexItem> _rowitems;
  private List<IndexItem> _colitems;

  /**
   * A 2D index constructed from IndexItems
   * @param rowitems the row items in this index 
   * @param colitems the column items in this index
   */
  public TwoDIndex(List<IndexItem> rowitems, List<IndexItem> colitems) {
    Catchers.catchNull(rowitems);
    Catchers.catchNull(colitems);
    Iterator<IndexItem> it;
    it = rowitems.iterator();
    while (it.hasNext()) {
      Catchers.catchNull(it.next());
    }
    _rowitems = new ArrayList<IndexItem>(rowitems);
    it = colitems.iterator();
    while (it.hasNext()) {
      Catchers.catchNull(it.next());
    }
    _colitems = new ArrayList<IndexItem>(colitems);
  }

  /**
   * A 2D index constructed from a row and a column based IndexItem
   * @param rowitem the row item in this index 
   * @param colitem the column item in this index
   */
  public TwoDIndex(IndexItem rowitem, IndexItem colitem) {
    Catchers.catchNull(rowitem);
    Catchers.catchNull(colitem);
    _rowitems = new ArrayList<IndexItem>();
    _rowitems.add(rowitem);
    _colitems = new ArrayList<IndexItem>();
    _colitems.add(colitem);
  }

  /**
   * Gets the row items.
   * @return the row items
   */
  public final List<IndexItem> getRowItems() {
    return _rowitems;
  }

  /**
   * Gets the col items.
   * @return the col items
   */
  public final List<IndexItem> getColItems() {
    return _colitems;
  }

  @Override
  public String toString() {
    String row = _rowitems.toString();
    String col = _colitems.toString();
    return "ROW ITEMS: " + row + "\n" + "COL ITEMS:" + col;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_colitems == null) ? 0 : _colitems.hashCode());
    result = prime * result + ((_rowitems == null) ? 0 : _rowitems.hashCode());
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
    TwoDIndex other = (TwoDIndex) obj;
    if (!_rowitems.equals(other._rowitems)) {
      return false;
    }
    if (!_colitems.equals(other._colitems)) {
      return false;
    }
    return true;
  }

  @Override
  public OGArray<? extends Number> accept(IndexVisitor visitor) {
    return visitor.visit(this);
  }

}
