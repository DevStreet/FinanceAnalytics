/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opengamma.util.ParallelArrayBinarySort;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Sort;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.transpose.TransposeOGMatrix;

/**
 * Sorts OGMatrixs
 */
@DOGMAMethodHook(provides = Sort.class)
public class SortOGMatrix {

  private static TransposeOGMatrix s_transpose = new TransposeOGMatrix();

  @DOGMAMethodLiteral
  public List<OGArray<? extends Number>> sort(OGMatrix array1, SortCompute compute, String direction) {
    return sortWorker(array1, compute, 0, false, direction);
  }

  @DOGMAMethodLiteral
  public List<OGArray<? extends Number>> sort(OGMatrix array1, SortCompute compute, int dimension) {
    return sortWorker(array1, compute, dimension, true, "ascend");
  }

  @DOGMAMethodLiteral
  public List<OGArray<? extends Number>> sort(OGMatrix array1, SortCompute compute) {
    return sortWorker(array1, compute, 0, false, "ascend");
  }

  @DOGMAMethodLiteral
  public List<OGArray<? extends Number>> sort(OGMatrix array1, SortCompute compute, int dimension, String direction) {
    return sortWorker(array1, compute, dimension, true, direction);
  }

  /*
   * Actually does the lifting
   */
  private List<OGArray<? extends Number>> sortWorker(OGMatrix array1, SortCompute compute, int dimension, boolean dimensionSetByUser, String direction) {
    double[] data = array1.getData();
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    double[] values;
    double[] sortedData, idxValues;
    int[] idx, idxOrig = null, subsort;
    int rowredef, colredef, useDimension;
    boolean descend = false;
    List<OGArray<? extends Number>> ret = new ArrayList<>(2);

    // which dimension, should be [0,1]
    if (dimension < 0 || dimension > 1) {
      throw new MathsExceptionIllegalArgument();
    }

    // default behavior of vectors is to be sorted along their leading dimension, hence this twiddle
    if (dimensionSetByUser) {
      useDimension = dimension;
    } else { // do the default thing if the user didn't say what they wanted
      if (rows == 1) {
        useDimension = 1;
      } else if (cols == 1) {
        useDimension = 0;
      } else {
        useDimension = 0;
      }
    }

    if (useDimension == 0) {
      rowredef = rows;
      colredef = cols;
    } else {
      OGMatrix matptr = s_transpose.eval(array1);
      data = matptr.getData();
      rowredef = cols;
      colredef = rows;
    }

    // sort desc or asc?
    if (direction.equalsIgnoreCase("descend")) {
      descend = true;
    } else if (!direction.equalsIgnoreCase("ascend")) {
      throw new MathsExceptionIllegalArgument("Unrecognised option to sort(). String must be \"ascend\" or \"descend\", got \"" + direction + "\"");
    }

    if (compute != SortCompute.values) {
      // set up values
      idxOrig = new int[rowredef];
      for (int i = 0; i < rowredef; i++) {
        idxOrig[i] = i;
      }
    }

    sortedData = new double[data.length];
    idxValues = new double[data.length];
    values = new double[rowredef];
    idx = new int[rowredef];

    for (int i = 0; i < colredef; i++) {
      // copy in a column of data
      System.arraycopy(data, i * rowredef, values, 0, rowredef);

      if (compute != SortCompute.values) {
        // copy in a corresponding set of indices to mangle
        System.arraycopy(idxOrig, 0, idx, 0, rowredef);

        // sort them
        ParallelArrayBinarySort.parallelBinarySort(values, idx);
      } else { // just use system sort
        Arrays.sort(values);
      }

      // glue them back in
      if (descend) { // values
        for (int j = 0; j < rowredef; j++) {
          sortedData[i * rowredef + j] = values[rowredef - 1 - j];
        }
      } else {
        for (int j = 0; j < rowredef; j++) {
          sortedData[i * rowredef + j] = values[j];
        }
      }

      if (compute != SortCompute.values) { // only do this if we care for keys
        if (descend) {
          for (int j = 0; j < rowredef; j++) {
            idxValues[i * rowredef + j] = idx[rowredef - 1 - j];
          }
        } else {
          for (int j = 0; j < rowredef; j++) { // indexes
            idxValues[i * rowredef + j] = idx[j];
          }
        }

        // parse the sorted data, if there's repeats then the indices need sorting too, sort is not necessarily order-preserving
        double vbase;
        int ptr = 0, count = 0;
        for (int j = 0; j < rowredef - 1; j++) {
          // look forward, see if value matches
          if (values[j] == values[j + 1]) {
            vbase = values[j];
            ptr = j + 1;
            // continue looking forward for further matches
            while (ptr < rowredef && vbase == values[ptr]) {
              ptr++;
            }
            subsort = Arrays.copyOfRange(idx, j, ptr);
            Arrays.sort(subsort);
            // indexes
            if (descend) {
              for (int k = j; k < ptr; k++) {
                count++;
                // vars ptr and j refer to values in the ascending order so we have to work backwards in both assigner and assignee
                idxValues[(i + 1) * rowredef - 1 - k] = subsort[subsort.length - count];
              }
            } else {
              for (int k = j; k < ptr; k++) {
                idxValues[i * rowredef + k] = subsort[count++];
              }
            }
            j += ptr - 1;
            ptr = 0;
            count = 0;
          }
        }

      }
    }

    if (useDimension == 0) {
      if (compute != SortCompute.keys) {
        ret.add(new OGMatrix(sortedData, rowredef, colredef));
      }
      if (compute != SortCompute.values) {
        ret.add(new OGMatrix(idxValues, rowredef, colredef));
      }
    } else {
      if (compute != SortCompute.keys) {
        ret.add(s_transpose.eval(new OGMatrix(sortedData, rowredef, colredef)));
      }
      if (compute != SortCompute.values) {
        ret.add(s_transpose.eval(new OGMatrix(idxValues, rowredef, colredef)));
      }
    }

    return ret;
  }
}
