/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mldivide;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * quick eyeballed does it work test for mldivide(), will be updated to be thorough once happy with alg and dependencies
 */
public class MldivideOGDoubleArrayOGDoubleArrayTest {
   private double[][] A_square_singular = new double[][] { {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 } };
  private double[][] A_square_symmetric_positive_definite = new double[][] { {123.00, 23.00, 23.00 }, {23.00, 123.00, 23.00 }, {23.00, 23.00, 123.00 } };
  private double[][] A_square_non_symmetric_well_conditioned = new double[][] { {10.00, 2.00, 1.00 }, {2.00, 3.00, 10.00 }, {4.00, 10.00, 1.00 } };

  private double[][] A_rectangular = new double[][] { {1.00, 2.00, 3.00, 4.00 }, {5.00, 6.00, 7.00, 8.00 }, {9.00, 10.00, 11.00, 12.00 }, {13.00, 14.00, 15.00, 16.00 }, {17.00, 18.00, 19.00, 20.00 } };
  private double[][] B_rectangular = new double[][] { {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 }, {1.00, 2.00, 3.00 } };

  OGMatrix A1 = new OGMatrix(A_square_singular);
  OGMatrix A2 = new OGMatrix(A_square_symmetric_positive_definite);
  OGMatrix A3 = new OGMatrix(A_square_non_symmetric_well_conditioned);

  OGMatrix A4 = new OGMatrix(A_rectangular);
  OGMatrix B1 = new OGMatrix(B_rectangular);

  private MldivideOGDoubleArrayOGDoubleArray mldivide = new MldivideOGDoubleArrayOGDoubleArray();

  @Test
  public void aTest() {
    OGMatrix answer = mldivide.mldivide(A3, A1);
    System.out.println(A1.toString());
    System.out.println(A3.toString());
    System.out.println(answer.toString());
  }

  @Test
  public void a2Test() {
    OGMatrix answer = mldivide.mldivide(A4, B1);
    System.out.println(answer.toString());
  }

}
