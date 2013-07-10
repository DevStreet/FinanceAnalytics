/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.uminus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.uminus.UminusOGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * uminus on OGSparseMatrix
 */
@Test(groups = TestGroup.UNIT)
public class UminusOGSparseMatrixTest {

  double[][] d1 = new double[][] {{1, -2, 3, -4, 5, -6, 7, -8, 9, -10, 11, -12, 13, -14, 15, -16, 17, -18, 19, -20 }};
  double[][] d1Negated = new double[][] {{-1, 2, -3, 4, -5, 6, -7, 8, -9, 10, -11, 12, -13, 14, -15, 16, -17, 18, -19, 20 }};

  OGSparseMatrix data = new OGSparseMatrix(d1);
  OGSparseMatrix dataNeg = new OGSparseMatrix(d1Negated);

  UminusOGSparseMatrix m = new UminusOGSparseMatrix();

  @Test
  public void negationTest() {
    assertTrue(dataNeg.fuzzyequals(m.eval(data), 10 * D1mach.four()));
  }

}
