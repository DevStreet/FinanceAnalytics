/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;


/**
 * tests sparse on sparse type
 */
public class DOGMAOGSparseArraySparseTest {

  private static SparseOGSparseArray s_s2s = SparseOGSparseArray.getInstance();
  private static double[][] denseData = {{      0.0000000000000000,      0.0000000000000000,      0.0000000000000000,      0.0000000000000000,      3.0000000000000000},{      0.0000000000000000,      0.0000000000000000,     10.0000000000000000,      0.0000000000000000,      2.0000000000000000},{     10.0000000000000000,      0.0000000000000000,      4.0000000000000000,      1.0000000000000000,      0.0000000000000000},{      5.0000000000000000,      1.0000000000000000,      8.0000000000000000,      0.0000000000000000,      5.0000000000000000},{     10.0000000000000000,      0.0000000000000000,      0.0000000000000000,      0.0000000000000000,      0.0000000000000000}};

  private static OGSparseArray sparseAnswer = new OGSparseArray(denseData);

  @Test
  public static void diagToSparseTest() {
    OGSparseArray p = new OGSparseArray(denseData);
    assertTrue(sparseAnswer.equals(s_s2s.sparse(p)));
  }

}
