/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mldivide;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * Test for mldivide()
 */
@Test(groups = TestGroup.UNIT)
public class MldivideOGMatrixOGMatrixTest {
  boolean debugStatements = false; // eyeballable output switch

  private double[][] A_square_singular = new double[][] { {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 } };
  private double[][] A_square_symmetric_positive_definite = new double[][] { {123.00, 23.00, 23.00 }, {23.00, 123.00, 23.00 }, {23.00, 23.00, 123.00 } };
  private double[][] A_square_non_symmetric_well_conditioned = new double[][] { {10.00, 2.00, 1.00 }, {2.00, 3.00, 10.00 }, {4.00, 10.00, 1.00 } };
  private double[][] A_rectangular = new double[][] { {1.00, 2.00, 3.00, 4.00 }, {5.00, 6.00, 7.00, 8.00 }, {9.00, 10.00, 11.00, 12.00 }, {13.00, 14.00, 15.00, 16.00 }, {17.00, 18.00, 19.00, 20.00 } };
  private double[][] A_square_non_symmetric_condition_bad_for_lu_ok_for_qr = new double[][] { {1.0000000000000009, 2., 20. }, {1., 2., 0. }, {1., 2., 40. } };
  private double[][] A_upper_triangular = new double[][] { {1., 2., 3. }, {0., 5., 6. }, {0., 0., 9. } };;
  private double[][] A_unit_upper_triangular = new double[][] { {1., 2., 3. }, {0., 1., 6. }, {0., 0., 1. } };
  private double[][] A_lower_triangular = new double[][] { {1., 0., 0. }, {4., 5., 0. }, {7., 8., 9. } };
  private double[][] A_unit_lower_triangular = new double[][] { {1., 0., 0. }, {4., 1., 0. }, {7., 8., 1. } };
  private double[][] A_lower_triangular_singular_zero_on_diag = new double[][] { {1., 0., 0. }, {4., 0., 0. }, {7., 8., 1. } };
  private double[][] A_lower_triangular_singular_near_zero_on_diag = new double[][] { {1., 0., 0. }, {4., 0.0000000000000001, 0. }, {7., 8., 1. } };
  private double[][] A_square_spd_near_singular = new double[][] { {2.0000e+00, 1.0000e+150, 2.0000e+00 }, {1.0000e+150, 1.0000e+300, 2.0000e+150 }, {2.0000e+00, 2.0000e+150, 5.0000e+00 } };
  private double[][] A_square_symmetric_not_spd = new double[][] { {54., 2., 3. }, {2., 10., 6. }, {3., 6., -120. } };
  private double[][] A_rectangular_with_inf = new double[][] { {Double.POSITIVE_INFINITY, 2.00, 3.00, 4.00 }, {5.00, 6.00, 7.00, 8.00 }, {9.00, 10.00, 11.00, 12.00 }, {13.00, 14.00, 15.00, 16.00 },
      {17.00, 18.00, 19.00, 20.00 } };
  private double[][] A_square_permuted_upper_triangular = new double[][] { {0., 0., 13., 14., 15. }, {1., 2., 3., 4., 5. }, {0., 7., 8., 9., 10. }, {0., 0., 0., 0., 25. }, {0., 0., 0., 19., 20. } };;
  private double[][] A_square_permuted_upper_triangular_zero_on_diag = new double[][] { {0., 6., 7., 8. }, {0., 0., 0., 16. }, {1., 2., 3., 4. }, {0., 0., 0., 12. } };
  private double[][] A_square_almost_permuted_upper_triangular = new double[][] { {0., 12., 13., 14., 15. }, {1., 2., 3., 4., 5. }, {0., 7., 8., 9., 10. }, {0., 0., 0., 0., 25. },
      {0., 0., 0., 19., 20. } };;
  private double[][] A_square_permuted_upper_triangular_singular_to_mach_prech = new double[][] { {0., 0., 1.e-300, 14., 15. }, {1., 2., 3., 4., 5. }, {0., 7., 8., 9., 10. }, {0., 0., 0., 0., 25. },
      {0., 0., 0., 19., 20. } };;

  private double[][] B_rectangular = new double[][] { {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 } };

  private double[] C_4len_col_vector = new double[] {1, 2, 3, 4 };
  private double[] C_5len__shuffled_col_matrix = new double[] {3, 1, 2, 5, 4, 10, 20, 30, 40, 50 };

  OGMatrix A1 = new OGMatrix(A_square_singular);
  OGMatrix A2 = new OGMatrix(A_square_symmetric_positive_definite);
  OGMatrix A3 = new OGMatrix(A_square_non_symmetric_well_conditioned);
  OGMatrix A4 = new OGMatrix(A_rectangular);
  OGMatrix A5 = new OGMatrix(A_square_non_symmetric_condition_bad_for_lu_ok_for_qr);
  OGMatrix A6 = new OGMatrix(A_upper_triangular);
  OGMatrix A7 = new OGMatrix(A_unit_upper_triangular);
  OGMatrix A8 = new OGMatrix(A_lower_triangular);
  OGMatrix A9 = new OGMatrix(A_unit_lower_triangular);
  OGMatrix A10 = new OGMatrix(A_lower_triangular_singular_zero_on_diag);
  OGMatrix A11 = new OGMatrix(A_lower_triangular_singular_near_zero_on_diag);
  OGMatrix A12 = new OGMatrix(A_square_spd_near_singular);
  OGMatrix A13 = new OGMatrix(A_square_symmetric_not_spd);
  OGMatrix A14 = new OGMatrix(A_rectangular_with_inf);
  OGMatrix A15 = new OGMatrix(A_square_permuted_upper_triangular);
  OGMatrix A16 = new OGMatrix(A_square_permuted_upper_triangular_zero_on_diag);
  OGMatrix A17 = new OGMatrix(A_square_almost_permuted_upper_triangular);
  OGMatrix A18 = new OGMatrix(A_square_permuted_upper_triangular_singular_to_mach_prech);

  OGMatrix B1 = new OGMatrix(B_rectangular);
  OGMatrix C1 = new OGMatrix(C_4len_col_vector, 4, 1);
  OGMatrix C2 = new OGMatrix(C_5len__shuffled_col_matrix, 5, 2);

  private MldivideOGMatrixOGMatrix mldivide = new MldivideOGMatrixOGMatrix();

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void testNonConformance() {
    mldivide.eval(A1, B1);
  }

  @Test
  public void testUpperTriBranch() {
    OGMatrix answer = mldivide.eval(A6, A1);
    OGMatrix expected = new OGMatrix(new double[][] { {0.5333333333333334, 1.0666666666666669, 1.6000000000000001 }, {0.0666666666666667, 0.1333333333333334, 0.2000000000000000 },
        {0.1111111111111111, 0.2222222222222222, 0.3333333333333333 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A6.toString());
      System.out.println(A1.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testUnitUpperTriBranch() {
    OGMatrix answer = mldivide.eval(A7, A1);
    OGMatrix expected = new OGMatrix(new double[][] { {8.0000000000000000, 16.0000000000000000, 24.0000000000000000 }, {-5.0000000000000000, -10.0000000000000000, -15.0000000000000000 },
        {1.0000000000000000, 2.0000000000000000, 3.0000000000000000 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A7.toString());
      System.out.println(A1.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testLowerTriBranch() {
    OGMatrix answer = mldivide.eval(A8, A1);
    OGMatrix expected = new OGMatrix(new double[][] { {1.0000000000000000, 2.0000000000000000, 3.0000000000000000 }, {-0.6000000000000000, -1.2000000000000000, -1.8000000000000000 },
        {-0.1333333333333334, -0.2666666666666667, -0.4000000000000000 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A8.toString());
      System.out.println(A1.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testUnitLowerTriBranch() {
    OGMatrix answer = mldivide.eval(A9, A1);
    OGMatrix expected = new OGMatrix(new double[][] { {1.0000000000000000, 2.0000000000000000, 3.0000000000000000 }, {-3.0000000000000000, -6.0000000000000000, -9.0000000000000000 },
        {18.0000000000000000, 36.0000000000000000, 54.0000000000000000 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A9.toString());
      System.out.println(A1.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testPermutedUpperTriangularBranch() {
    OGMatrix answer = mldivide.eval(A15, C2);
    OGMatrix expected = new OGMatrix(new double[][] { {0.0000000000000000, 8.1445922498554086 }, {0.0000000000000000, 3.1787160208212839 }, {0.0000000000000000, -2.0971659919028340 },
        {0.0000000000000000, 0.9473684210526315 }, {0.2000000000000000, 1.6000000000000001 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println("A15" + A15.toString());
      System.out.println("C2" + C2.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testAlmostPermutedUpperTriangularBranch() {
    OGMatrix answer = mldivide.eval(A17, C2);
    OGMatrix expected = new OGMatrix(new double[][] { {0.0000000000000002, -29.9999999999998650 }, {0.0000000000000004, -57.8526315789472108 }, {-0.0000000000000004, 51.3052631578945864 },
        {0.0000000000000000, 0.9473684210526315 }, {0.2000000000000000, 1.6000000000000001 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println("A17" + A17.toString());
      System.out.println("C2" + C2.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testPermutedUpperTriangularZeroOnDiagBranch() {
    OGMatrix answer = mldivide.eval(A16, C1);
    OGMatrix expected = new OGMatrix(new double[][] { {2.0475247524752480 }, {-0.7168316831683170 }, {0.5287128712871273 }, {0.2000000000000003 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A16.toString());
      System.out.println(C1.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testPermutedUpperTriangularSingularToMachPrecBranch() {
    OGMatrix answer = mldivide.eval(A18, C2);
    OGMatrix expected = new OGMatrix(new double[][] { {-0.0000000000000001, 5.7657712505229739 }, {-0.0000000000000001, -1.3196460795883791 }, {-0.0000000000000001, 2.6102410879868061 },
        {0.0000000000000000, 0.2699985638374270 }, {0.2000000000000001, 1.5925606778687353 } });
    assertTrue(answer.fuzzyequals(expected, 100 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A18.toString());
      System.out.println(C2.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testTriDefinedSingularBranch() {
    OGMatrix answer = mldivide.eval(A10, A1);
    OGMatrix expected = new OGMatrix(new double[][] { {0.2941176470588236, 0.5882352941176472, 0.8823529411764708 }, {-0.1303167420814479, -0.2606334841628958, -0.3909502262443438 },
        {-0.0162895927601810, -0.0325791855203620, -0.0488687782805430 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A10.toString());
      System.out.println(A1.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testTriMachPrecSingularBranch() {
    OGMatrix answer = mldivide.eval(A11, A1);
    OGMatrix expected = new OGMatrix(new double[][] { {0.2941176470588236, 0.5882352941176472, 0.8823529411764708 }, {-0.1303167420814479, -0.2606334841628958, -0.3909502262443438 },
        {-0.0162895927601810, -0.0325791855203620, -0.0488687782805430 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A11.toString());
      System.out.println(A1.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testLUPBranch() {
    OGMatrix answer = mldivide.eval(A3, A1);
    OGMatrix expected = new OGMatrix(new double[][] { {0.0812641083521445, 0.1625282167042890, 0.2437923250564334 }, {0.0609480812641084, 0.1218961625282167, 0.1828442437923251 },
        {0.0654627539503386, 0.1309255079006772, 0.1963882618510158 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A3.toString());
      System.out.println(A1.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testLUPBranchFallToLSQ() {
    OGMatrix answer = mldivide.eval(A5, A1);
    OGMatrix expected = new OGMatrix(new double[][] { {0.2000000000000003, 0.4000000000000006, 0.6000000000000013 }, {0.4000000000000002, 0.8000000000000004, 1.2000000000000017 },
        {-0.0000000000000001, -0.0000000000000001, 0.0000000000000000 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A5.toString());
      System.out.println(A1.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testCholBranch() {
    OGMatrix answer = mldivide.eval(A2, A1);
    OGMatrix expected = new OGMatrix(new double[][] { {0.0059171597633136, 0.0118343195266272, 0.0177514792899408 }, {0.0059171597633136, 0.0118343195266272, 0.0177514792899408 },
        {0.0059171597633136, 0.0118343195266272, 0.0177514792899408 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A1.toString());
      System.out.println(A1.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testCholNotSPDBranch() {
    OGMatrix answer = mldivide.eval(A13, A1);
    OGMatrix expected = new OGMatrix(new double[][] { {0.0150267040825563, 0.0300534081651127, 0.0450801122476691 }, {0.0988051054584955, 0.1976102109169910, 0.2964153163754866 },
        {-0.0030174104583446, -0.0060348209166893, -0.0090522313750339 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A13.toString());
      System.out.println(A1.toString());
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testCholSingularBranch() {
    OGMatrix answer = mldivide.eval(A12, A1);
    OGMatrix expected = new OGMatrix(new double[][] { {0, 0, 0 }, {1e-300, 2e-300, 3e-300 }, {0, 0, 0 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(A12.toString());
      System.out.println(A1.toString());
      System.out.println(answer.toString());
    }
  }

  // this is a right pain to test because its rank deficient the results are pulled about by floating point behaviour in deficient part of Q
  // we test by reconstruction opposed to absolute value
  @Test
  public void testQRBranch() {
    OGMatrix answer = mldivide.eval(A4, B1);
    OGArray<? extends Number> zeros = DOGMA.zeros(B1.getNumberOfRows(), B1.getNumberOfColumns());
    OGArray<? extends Number> reconstruct = DOGMA.minus(B1, DOGMA.mtimes(A4, answer));
    assertTrue(reconstruct.fuzzyequals(zeros, 100 * D1mach.four()));
    if (debugStatements) {
      System.out.println(answer.toString());
    }
  }

  @Test
  public void testSVDBranch() {
    OGMatrix answer = mldivide.eval(A1, A2);
    OGMatrix expected = new OGMatrix(new double[][] { {4.0238095238095219, 4.0238095238095228, 4.0238095238095219 }, {8.0476190476190457, 8.0476190476190474, 8.0476190476190457 },
        {12.0714285714285658, 12.0714285714285694, 12.0714285714285676 } });
    assertTrue(answer.fuzzyequals(expected, 10 * D1mach.four()));
    if (debugStatements) {
      System.out.println(answer.toString());
    }
  }

}
