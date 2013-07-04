/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.uminus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * uminus on OGMatrix
 */
@Test
public class UminusOGComplexMatrixTest {

  double[][] d1r = new double[][] { {1., 5., 9., 13., 17. }, {-2., -6., -10., -14., -18. }, {3., 7., 11., 15., 19. }, {-4., -8., -12., -16., -20. } };
  double[][] d1i = new double[][] { {11., 15., 19., 23., 27. }, {8., 4., 0., -4., -8. }, {13., 17., 21., 25., 29. }, {6., 2., -2., -6., -10. } };
  double[][] d1rNegated = new double[][] { {-1., -5., -9., -13., -17. }, {2., 6., 10., 14., 18. }, {-3., -7., -11., -15., -19. }, {4., 8., 12., 16., 20. } };
  double[][] d1iNegated = new double[][] { {-11., -15., -19., -23., -27. }, {-8., -4., -0., 4., 8. }, {-13., -17., -21., -25., -29. }, {-6., -2., 2., 6., 10. } };
  OGComplexMatrix data = new OGComplexMatrix(d1r, d1i);
  OGComplexMatrix dataNeg = new OGComplexMatrix(d1rNegated, d1iNegated);

  UminusOGComplexMatrix m = new UminusOGComplexMatrix();

  @Test
  public void negationTest() {
    assertTrue(dataNeg.fuzzyequals(m.eval(data), 10 * D1mach.four()));
  }

}
