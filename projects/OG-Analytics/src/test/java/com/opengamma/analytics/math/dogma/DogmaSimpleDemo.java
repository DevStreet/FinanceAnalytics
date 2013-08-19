/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.dogma;

import static com.opengamma.maths.dogma.DOGMA.acosh;
import static com.opengamma.maths.dogma.DOGMA.assign;
import static com.opengamma.maths.dogma.DOGMA.colon;
import static com.opengamma.maths.dogma.DOGMA.disp;
import static com.opengamma.maths.dogma.DOGMA.full;
import static com.opengamma.maths.dogma.DOGMA.mldivide;
import static com.opengamma.maths.dogma.DOGMA.mtimes;
import static com.opengamma.maths.dogma.DOGMA.plus;
import static com.opengamma.maths.dogma.DOGMA.select;
import static com.opengamma.maths.dogma.DOGMA.sqrt;
import static com.opengamma.maths.dogma.DOGMA.svd;

import java.util.List;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMALinearAlgebra.svd.SVDCompute;

/**
 * Demonstrates DOGMA basics
 */
@Test
public class DogmaSimpleDemo {

  /**
   * Type promotion on built in Number class
   */
  public void example1() {
    disp(sqrt(9));
    disp(sqrt(-1));
  }

  /**
   * Type promotion aware functions (sqrt(9) + sqrt(-1)) = 3 + i
   */
  public void example2() {
    disp(plus(sqrt(9), sqrt(-1)));
  }

  /**
   * Vectors are the basic unit of computation, functions are vector aware
   * and do vector type promotion
   */
  public void example3() {
    OGArray<? extends Number> A;
    // real space unary function (vectorised arc cosh(A))
    A = colon(1, 4);
    disp(A);
    disp(acosh(A));
    
    // complex space unary function (vectorised arc cosh(A))
    A = colon(-4, 4);
    disp(A);
    disp(acosh(A));
    
    // complex space promoted binary function (vectorised A + i)
    disp(plus(A, sqrt(-1)));
  }

  /**
   * Multiply some vectors and matrices
   */
  public void example4() {
    OGArray<? extends Number> A, B, C, D;
    A = new OGMatrix(new double[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }, {10, 11, 12 } });
    B = new OGMatrix(new double[] {10, 20, 30 }, 3, 1);
    C = new OGMatrix(new double[][] { {10, 20, 30 }, {40, 50, 60}, {70, 80, 90 }});   
       
    // show matrix A and vector B
    disp(A);
    disp(B);
    System.out.println("Mat-Vec multiply");
    D = mtimes(A, B);
    disp(D);
    
    // show matrix A and matrix C
    disp(A);
    disp(C);
    System.out.println("Mat-Mat multiply");
    D = mtimes(A,C);
    disp(D);    
  }

  /**
   * Basic vector parser support on selection, "select all from column 1"
   */
  public void example5() {
    OGArray<? extends Number> A, B;
    A = new OGMatrix(new double[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }, {10, 11, 12 } });
    disp(A);
    B = select(A, ":,1");
    disp(B);
  }

  /**
   * Basic vector parser support on selection, "select rows 0 and 1, columns 0 in steps of 2 to the last column - 2"
   */
  public void example6() {
    OGArray<? extends Number> A, B;
    A = new OGMatrix(new double[][] { {1, 2, 3, 4, 5, 6 }, {7, 8, 9, 10, 11, 12 }, {13, 14, 15, 16, 17, 18 } });
    disp(A);
    B = select(A, "[0,1],[0:2:end-2]");
    disp(B);
  }

  /**
   * Vector assignment, assign to B matrix A with all entries in column 1 set to 1337
   * then assign to B matrix B with all entries in row 1 set to 1337
   */
  public void example7() {
    OGArray<? extends Number> A, B;
    A = new OGMatrix(new double[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }, {10, 11, 12 } });
    disp(A);
    B = assign(A, ":,1", 1337);
    B = assign(B, "1,:", 1337);
    disp(B);
  }

  /**
   * More complex vector assignment. Assign to B matrix A with rows 1 in steps of 2 to 3 and columns
   * 0 in steps of 2 to 2 the set as in matrix C.
   */
  public void example8() {
    OGArray<? extends Number> A, B, C;
    A = new OGMatrix(new double[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }, {10, 11, 12 } });
    C = new OGMatrix(new double[][] { {10, 20 }, {30, 40 } });
    disp(A);
    B = assign(A, "1:2:3,0:2:2", C);
    disp(B);
  }

  /**
   * Sparse matrix construction in A. Then assign all entries in column 1 the value "i"
   * Note sparse matrix type promotion and "full" representation
   */
  public void example9() {
    OGArray<? extends Number> A, B;
    Number C;
    A = new OGSparseMatrix(new double[][] { {1, 0, 3 }, {0, 0, 6 }, {7, 8, 0 } });
    C = ComplexType.I;
    disp(A);
    B = assign(A, ":,1", C);
    disp(B);
    disp(full(B));
  }

  /**
   * A function that returns multiple values (e.g. SVD), matrices return as list
   */
  public void example10() {
    OGMatrix A = new OGMatrix(new double[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }, {10, 11, 12 } });
    List<OGArray<? extends Number>> result;
    result = svd(A, SVDCompute.US); // left singular vectors and singular values
    disp(result.get(0));
    disp(result.get(1));
  }

  /**
   * Magic left division operator, the smart linear system solver.
   * mldivide() solves linear systems of the form AX=B.
   * Anice is a well conditioned matrix.
   * B is a matrix of right hand sides
   * Demo solution and reconstruction
   */
  public void example11() {
    OGMatrix Anice = new OGMatrix(new double[] {3, 2, 1, 2, 4, 3, 1, 3, 5 }, 3, 3);
    OGMatrix B = new OGMatrix(new double[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 } });
    disp(Anice);
    disp(B);
    OGArray<? extends Number> X = mldivide(Anice, B); // solve A against each B assign to X
    disp(X);
    disp(mtimes(Anice, X)); // demo it equals B
  }

  /**
   * Magic left division operator, the smart linear system solver.
   * Anasty is a poorly conditioned (actually singular in this case) matrix.
   * B is a matrix of right hand sides
   * Demo solution, code warns and logs that something bad happens and then reconstruct
   */
  public void example12() {
    OGMatrix Anasty = new OGMatrix(new double[] {1, 1, 1e3, 3, 3, 0, 100, 100, 1 }, 3, 3);
    OGMatrix B = new OGMatrix(new double[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 } });
    disp(Anasty);
    disp(B);
    OGArray<? extends Number> X = mldivide(Anasty, B); // solve A against each B assign to X
    disp(X);
    disp(mtimes(Anasty, X)); // demo it equals B
  }

  /**
   * Magic left division operator, the smart linear system solver.
   * Aoverdet is overdetermined.
   * B is a matrix of right hand sides
   * Least squares solve takes place
   */
  public void example13() {
    OGMatrix Aoverdet = new OGMatrix(new double[][] { {1, 2, 3 }, {1, 2, 3 }, {7, 8, 9 }, {10, 11, 12 } });
    OGMatrix B = new OGMatrix(new double[] {1, 2, 3, 4 }, 4, 1);
    disp(Aoverdet);
    disp(B);
    OGArray<? extends Number> X = mldivide(Aoverdet, B); // solve A against B (LSQ)
    disp(X);
    disp(mtimes(Aoverdet, X)); // demo it's "near" B
  }

  /**
   * Resettable rand() states (this is thread safe, state is thread local)
   */
  public void example14() {
    disp(DOGMA.rand(10)); // some random matrix from some random seed
    System.out.println("DOGMA: Random state: " + DOGMA.rand("state")); // show state
    DOGMA.rand("state", 10L); // set state to 10
    System.out.println("DOGMA: Fixed state: " + DOGMA.rand("state")); // show state
    disp(DOGMA.rand(10)); // some random matrix based on seed 10
    System.out.println("DOGMA: mutated from state 10: " + DOGMA.rand("state")); // show state
    DOGMA.rand("state", 10L); // set state to 10
    disp(DOGMA.rand(10)); // some random matrix based on seed 10 again, same as before
  }

}
