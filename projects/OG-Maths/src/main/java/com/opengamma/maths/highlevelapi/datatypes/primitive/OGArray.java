/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import java.util.Iterator;
import java.util.List;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.indexing.ColonIndex;
import com.opengamma.maths.lowlevelapi.indexing.Index;
import com.opengamma.maths.lowlevelapi.indexing.IndexItem;
import com.opengamma.maths.lowlevelapi.indexing.IndexVisitor;
import com.opengamma.maths.lowlevelapi.indexing.MissileSyntax2IndexConvertor;
import com.opengamma.maths.lowlevelapi.indexing.OneDIndex;
import com.opengamma.maths.lowlevelapi.indexing.TwoDIndex;

/**
 * Super class for all array types
 * @param <T>
 */
public abstract class OGArray<T extends Number> implements OGArrayInterface<T>, IndexVisitor {

  private MissileSyntax2IndexConvertor _conv = new MissileSyntax2IndexConvertor(true);

  @Override
  public OGArray<? extends Number> get(TwoDIndex index) {
    OneDIndex ridx = new OneDIndex(index.getRowItems());
    OneDIndex cidx = new OneDIndex(index.getColItems());
    return this.get(ridx, cidx);
  }

  @Override
  public OGArray<? extends Number> visit(TwoDIndex idx) {
    return get(idx);
  }

  @Override
  public OGArray<? extends Number> visit(OneDIndex idx) {
    return get(idx);
  }

  @Override
  public OGArray<? extends Number> get(String linearIndex) {
    Index idx = _conv.eval(linearIndex);
    return idx.accept(this);
  }

  @Override
  public OGArray<? extends Number> get(OneDIndex idx) {
    // walk the elements in the index look up and hoik them out
    List<IndexItem> items = idx.getItems();
    if (items.contains(ColonIndex.getInstance())) {
      if (items.size() != 1) {
        throw new MathsExceptionIllegalArgument("Colon must be specified by itself.");
      }
    }
    Iterator<IndexItem> it = items.iterator();
    // resolve end objects into linear indices
    // fold
    it = items.iterator();
    int[][] store = new int[items.size()][];
    int ptr = 0;
    int total = 0;
    while (it.hasNext()) {
      store[ptr] = it.next().linearise(this.getNumberOfColumns() * this.getNumberOfRows());
      total += store[ptr].length;
      ptr++;
    }
    int[] shuffle = new int[total];
    int offset = 0;
    for (int i = 0; i < store.length; i++) {
      System.arraycopy(store[i], 0, shuffle, offset, store[i].length);
      offset += store[i].length;
    }
    return this.get(shuffle);
  }

  public OGArray<? extends Number> get(OneDIndex rows, OneDIndex cols) {
    OGArray<? extends Number> ret = null;
    boolean rowsIsColon = false;
    boolean colsIsColon = false;
    Iterator<IndexItem> it;
    int[] colIndiciesPrimitive = null;
    int[] rowIndiciesPrimitive = null;

    //  process rows
    List<IndexItem> rowitems = rows.getItems();
    it = rowitems.iterator();
    if (rowitems.contains(ColonIndex.getInstance())) {
      if (rowitems.size() != 1) {
        throw new MathsExceptionIllegalArgument("Colon must be specified by itself.");
      }
      rowsIsColon = true;
    } else {
      // resolve end objects into linear indices
      // fold
      it = rowitems.iterator();
      int[][] store = new int[rowitems.size()][];
      int ptr = 0;
      int total = 0;
      while (it.hasNext()) {
        store[ptr] = it.next().linearise(this.getNumberOfRows());
        total += store[ptr].length;
        ptr++;
      }
      rowIndiciesPrimitive = new int[total];
      int offset = 0;
      for (int i = 0; i < store.length; i++) {
        System.arraycopy(store[i], 0, rowIndiciesPrimitive, offset, store[i].length);
        offset += store[i].length;
      }
    }

    //  process columns
    List<IndexItem> colitems = cols.getItems();
    it = colitems.iterator();
    if (colitems.contains(ColonIndex.getInstance())) {
      if (colitems.size() != 1) {
        throw new MathsExceptionIllegalArgument("Colon must be specified by itself.");
      }
      colsIsColon = true;
    } else {
      // resolve end objects into linear indices
      // fold
      it = colitems.iterator();
      int[][] store = new int[colitems.size()][];
      int ptr = 0;
      int total = 0;
      while (it.hasNext()) {
        store[ptr] = it.next().linearise(this.getNumberOfColumns());
        total += store[ptr].length;
        ptr++;
      }
      colIndiciesPrimitive = new int[total];
      int offset = 0;
      for (int i = 0; i < store.length; i++) {
        System.arraycopy(store[i], 0, colIndiciesPrimitive, offset, store[i].length);
        offset += store[i].length;
      }
    }

    if (rowsIsColon || colsIsColon) {
      if (rowsIsColon && colsIsColon) {
        ret = this;
      } else if (rowsIsColon) {
        ret = this.getColumns(colIndiciesPrimitive);
      } else if (colsIsColon) {
        ret = this.getRows(rowIndiciesPrimitive);
      }
    } else {
      ret = get(rowIndiciesPrimitive, colIndiciesPrimitive);
    }
    return ret;
  }

  @Override
  public OGArray<? extends Number> get(int[] linear) {
    Catchers.catchNull(linear);
    int len = linear.length;
    int idx;
    int rows = this.getNumberOfRows();
    int cols = this.getNumberOfColumns();
    int colNum = 0;
    int rowNum = 0;
    OGArray<? extends Number> ret;
    if (getEntry(0, 0) instanceof ComplexType) {
      ComplexType[][] tmp = new ComplexType[1][len];
      for (int i = 0; i < len; i++) {
        idx = linear[i];
        colNum = idx / cols;
        rowNum = idx - colNum * rows;
        tmp[0][i] = (ComplexType) getEntry(rowNum, colNum);
      }
      ret = new OGComplexMatrix(tmp);
    } else {
      double[][] tmp = new double[1][len];
      for (int i = 0; i < len; i++) {
        idx = linear[i];
        colNum = idx / cols;
        rowNum = idx - colNum * rows;
        tmp[0][i] = (Double) getEntry(rowNum, colNum);
      }
      ret = new OGMatrix(tmp);
    }
    return ret;
  }

}
