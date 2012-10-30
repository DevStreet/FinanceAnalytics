/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full.Full;

/**
 * tests sparse on diag type
 */
public class DOGMAOGPermutationArraySparseTest {

  private static SparseOGPermutationMatrix s_d2s = SparseOGPermutationMatrix.getInstance();
  static int[] _data = new int[] {2, 3, 1, 4, 0 };
  private static double[][] denseAnswer = { {0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 1.0000000000000000 },
      {0.0000000000000000, 0.0000000000000000, 1.0000000000000000, 0.0000000000000000, 0.0000000000000000 },
      {1.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000 },
      {0.0000000000000000, 1.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000 },
      {0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 1.0000000000000000, 0.0000000000000000 } };

  private static OGSparseMatrix sparseAnswer = new OGSparseMatrix(denseAnswer);

  static Full f = new Full();
  
  @Test
  public static void permutationToSparseTest() {
    OGPermutationMatrix p = new OGPermutationMatrix(_data);
    assertTrue(sparseAnswer.equals(s_d2s.sparse(p)));
  }

}
