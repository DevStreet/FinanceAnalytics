/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIndexing.assign;

import java.util.Iterator;
import java.util.List;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Assign;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexScalar;
import com.opengamma.maths.highlevelapi.datatypes.OGRealScalar;
import com.opengamma.maths.lowlevelapi.functions.utilities.Unique;
import com.opengamma.maths.lowlevelapi.indexing.ColonIndex;
import com.opengamma.maths.lowlevelapi.indexing.Index;
import com.opengamma.maths.lowlevelapi.indexing.IndexItem;
import com.opengamma.maths.lowlevelapi.indexing.MissileSyntax2IndexConvertor;
import com.opengamma.maths.lowlevelapi.indexing.OneDIndex;
import com.opengamma.maths.lowlevelapi.indexing.TwoDIndex;

/**
 * Holds the DOGMA assignment functions
 */
@DOGMAMethodHook(provides = Assign.class)
public class AssignFunction {

  // this is an annoying problem
  // basically need another layer of abstraction for type promotion to work cleanly
  // at the minute, assign is stateless:
  // 1) the assignTo object is copied
  // 2) the setEntry() method is called on the copy for the indicies specified
  //  a) if the assignTo and the assignFrom objects are both the same in maths number land e.g. real or complex inplace updates are done
  //  b) if the assignTo and the assignFrom objects are different in maths number land e.g. real/complex complex/real, type promotion occurs and the assignTo type is suitably converted

  @DOGMAMethodLiteral
  public OGArray<? extends Number> assign(OGArray<? extends Number> assignTo, String index, OGArray<? extends Number> assignFrom) {
    MissileSyntax2IndexConvertor conv = new MissileSyntax2IndexConvertor();
    Index idx = conv.eval(index);
    OGArray<? extends Number> ret = DOGMA.copy(assignTo);
    if (idx instanceof OneDIndex) {
      OneDIndex tmpidx = (OneDIndex) idx;
      int rows = assignTo.getNumberOfRows();
      int cols = assignTo.getNumberOfColumns();
      int[] indexEntries = process1Dindex(tmpidx, rows * cols);
      int ptr = 0;
      int indexEntriesLen = indexEntries.length;
      for (int i = 0; i < indexEntriesLen; i++) {
        int col = indexEntries[i] / rows;
        int row = indexEntries[i] - col * rows;
        ret = ret.setEntry(row, col, assignFrom.getEntry(ptr++));
      }
    } else {
      TwoDIndex tmpidx = (TwoDIndex) idx;
      int[][] tmp = process2DIndex(new OneDIndex(tmpidx.getRowItems()), new OneDIndex(tmpidx.getColItems()), assignTo.getNumberOfRows(), assignTo.getNumberOfColumns());
      int[] rowidx = tmp[0];
      int[] colidx = tmp[1];

      int rowidxlen = rowidx.length;
      int colidxlen = colidx.length;
      int ptr = 0;
      for (int j = 0; j < colidxlen; j++) {
        for (int i = 0; i < rowidxlen; i++) {
          ret = ret.setEntry(rowidx[i], colidx[j], assignFrom.getEntry(ptr++));
        }
      }
    }
    return ret;
  };

  @DOGMAMethodLiteral
  public OGArray<? extends Number> assign(OGArray<? extends Number> assignTo, String index, Number assignFrom) {
    if (assignFrom instanceof ComplexType) {
      return assign(assignTo, index, new OGComplexScalar(assignFrom));
    } else {
      return assign(assignTo, index, new OGRealScalar(assignFrom));
    }
  };

  private int[] process1Dindex(OneDIndex indexes, int dim) {
    Iterator<IndexItem> it;
    int[] primitiveIndices = null;
    //  process rows
    List<IndexItem> items = indexes.getItems();
    it = items.iterator();
    if (items.contains(ColonIndex.getInstance())) {
      if (items.size() != 1) {
        throw new MathsExceptionIllegalArgument("Colon must be specified by itself.");
      }
    } else {
      // resolve end objects into linear indices
      // fold
      it = items.iterator();
      int[][] store = new int[items.size()][];
      int ptr = 0;
      int total = 0;
      while (it.hasNext()) {
        store[ptr] = it.next().linearise(dim);
        total += store[ptr].length;
        ptr++;
      }
      primitiveIndices = new int[total];
      int offset = 0;
      for (int i = 0; i < store.length; i++) {
        System.arraycopy(store[i], 0, primitiveIndices, offset, store[i].length);
        offset += store[i].length;
      }
      primitiveIndices = Unique.bitwise(primitiveIndices);
    }
    return primitiveIndices;

  }

  private int[][] process2DIndex(OneDIndex rows, OneDIndex cols, int rowDim, int colDim) {
    int[] rowIndiciesPrimitive = process1Dindex(rows, rowDim);
    int[] colIndiciesPrimitive = process1Dindex(cols, colDim);
    return new int[][] {rowIndiciesPrimitive, colIndiciesPrimitive };
  }

}
