/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;


/**
 * tests double ton sparse type
 */
public class DOGMAOGDoubleArraySparseTest {

  private static SparseOGSparseArray s_d2s = SparseOGSparseArray.getInstance();
  private static double[][] denseData = {{      0.0000000000000000,      0.0000000000000000,      0.0000000000000000,      0.0000000000000000,      3.0000000000000000},{      0.0000000000000000,      0.0000000000000000,     10.0000000000000000,      0.0000000000000000,      2.0000000000000000},{     10.0000000000000000,      0.0000000000000000,      4.0000000000000000,      1.0000000000000000,      0.0000000000000000},{      5.0000000000000000,      1.0000000000000000,      8.0000000000000000,      0.0000000000000000,      5.0000000000000000},{     10.0000000000000000,      0.0000000000000000,      0.0000000000000000,      0.0000000000000000,      0.0000000000000000}};

  private static OGSparseMatrix sparseAnswer = new OGSparseMatrix(denseData);

  @Test
  public static void denseToSparseTest() {
    OGSparseMatrix p = new OGSparseMatrix(denseData);
    assertTrue(sparseAnswer.equals(s_d2s.sparse(p)));
  }

}
