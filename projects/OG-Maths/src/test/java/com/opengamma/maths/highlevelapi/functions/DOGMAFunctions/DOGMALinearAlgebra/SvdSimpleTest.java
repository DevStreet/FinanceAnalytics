/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra;

import java.util.List;

import org.testng.annotations.Test;

import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.SVDCompute;

/**
 * Simple svd test
 */
public class SvdSimpleTest {

  private final static int m = 4;
  private final static int n = 3;
  private static double[] s_data = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  private static OGMatrix A_real_matrix = new OGMatrix(s_data, m, n);
  double[][] data0 = new double[][] { {0.1, 0.2, 0.3 }, {0.4, 0.5, 0.6 }, {0.7, 0.8, 0.9 }, {1.0, 1.1, 1.2 } };
  double[][] data1 = new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
  OGComplexMatrix A_complex_matrix = new OGComplexMatrix(data0, data1);

  boolean debugMessages = false;

  @Test
  public void svdDOGMATest() {
    if (debugMessages) {
      System.out.println("Real Test");
    }
    List<OGArray<? extends Number>> result = DOGMA.svd(A_real_matrix, SVDCompute.USV);
    if (debugMessages) {
      DOGMA.disp(result.get(0));
      DOGMA.disp(result.get(1));
      DOGMA.disp(result.get(2));
    }
    OGArray<? extends Number> tmp = DOGMA.mtimes(DOGMA.mtimes(result.get(0), result.get(1)), DOGMA.transpose(result.get(2)));
    if (debugMessages) {
      DOGMA.disp(tmp);
    }
  }

  @Test
  public void svdComplexDOGMATest() {
    if (debugMessages) {
      System.out.println("Complex Test");
    }
    List<OGArray<? extends Number>> result = DOGMA.svd(A_complex_matrix, SVDCompute.USV);
    if (debugMessages) {
      DOGMA.disp(result.get(0));
      DOGMA.disp(result.get(1));
      DOGMA.disp(result.get(2));
    }
    OGArray<? extends Number> tmp = DOGMA.mtimes(DOGMA.mtimes(result.get(0), result.get(1)), DOGMA.transpose(result.get(2)));
    if (debugMessages) {
      DOGMA.disp(tmp);
    }
  }

}
