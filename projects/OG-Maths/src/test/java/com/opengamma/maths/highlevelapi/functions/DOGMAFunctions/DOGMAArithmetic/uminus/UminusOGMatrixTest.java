/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.uminus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * uminus on OGMatrix
 */
@Test(groups = TestGroup.UNIT)
public class UminusOGMatrixTest {

  double[] d1 = new double[] {1, -2, 3, -4, 5, -6, 7, -8, 9, -10, 11, -12, 13, -14, 15, -16, 17, -18, 19, -20 };
  double[] d1Negated = new double[] {-1, 2, -3, 4, -5, 6, -7, 8, -9, 10, -11, 12, -13, 14, -15, 16, -17, 18, -19, 20 };

  OGMatrix data = new OGMatrix(d1);
  OGMatrix dataNeg = new OGMatrix(d1Negated);

  UminusOGMatrix m = new UminusOGMatrix();

  @Test
  public void negationTest() {
    assertTrue(dataNeg.fuzzyequals(m.eval(data), 10 * D1mach.four()));
  }

}
