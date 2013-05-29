/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.minus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * Test minus on OGSparseMatrix/OGSparseMatrix combo
 */
@Test(groups = TestGroup.UNIT)
public class MinusOGSparseMatrixOGSparseMatrixTest {
  static double[][] _data4x3Scale1 = new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } };
  static double[][] _data4x3Scale2 = new double[][] { {0., 50., 0. }, {0., 0., 90. }, {100., 0., 0. }, {10., 0., 30. } };
  static OGSparseMatrix F4x3Scale1 = new OGSparseMatrix(_data4x3Scale1);
  static OGSparseMatrix F4x3Scale2 = new OGSparseMatrix(_data4x3Scale2);
  static OGSparseMatrix F1x1Scale1 = new OGSparseMatrix(new double[][] {{1. } });
  static OGSparseMatrix F1x1Scale2 = new OGSparseMatrix(new double[][] {{10. } });

  // null ptr etc is caught by the function pointer code

  private static MinusOGSparseMatrixOGSparseMatrix minus = new MinusOGSparseMatrixOGSparseMatrix();

  @Test
  public static void ScalarMinusSparse() {
    OGMatrix answer = new OGMatrix(new double[][] { {9., 10., 7. }, {10., 5., 10. }, {10., 10., 1. }, {0., 10., 10. } });
    assertTrue(answer.fuzzyequals(minus.eval(F1x1Scale2, F4x3Scale1), 10 * D1mach.four()));
  }

  @Test
  public static void SparseMinusScalar() {
    OGMatrix answer = new OGMatrix(new double[][] { {-9., -10., -7. }, {-10., -5., -10. }, {-10., -10., -1. }, {0., -10., -10. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(minus.eval(F4x3Scale1, F1x1Scale2)), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteRows() {
    minus.eval(new OGSparseMatrix(new double[][] { {1, 2, 0 }, {3, 4, 0 }, {5, 6, 1 }, {7, 8, 0 }, {9, 10, 0 } }), F4x3Scale1);
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteCols() {
    minus.eval(new OGSparseMatrix(new double[][] { {1, 2 }, {3, 4 }, {5, 6 }, {7, 8 } }), F4x3Scale1);
  }

  @Test
  public static void FullMinusFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {1., -50., 3. }, {0., 5., -90. }, {-100., 0., 9. }, {0., 0., -30. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(minus.eval(F4x3Scale1, F4x3Scale2)), 10 * D1mach.four()));
  }

}
