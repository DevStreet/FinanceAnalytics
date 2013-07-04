/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAInquiry.issymmetric;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests issymmetric on OGMatrix
 */
@Test
public class OGMatrixIsSymmetricTest {

  OGMatrix True = new OGMatrix(1);
  OGMatrix False = new OGMatrix(0);

  IsSymmetricOGMatrix sym = new IsSymmetricOGMatrix();

  OGMatrix symmetrixMatrix = new OGMatrix(new double[][] { {1., 2., 3., 4. },
      {2., 6., 7., 8. }, {3., 7., 11., 12. },
      {4., 8., 12., 16. } });
  OGMatrix asymmetrixMatrix = new OGMatrix(new double[][] { {1., 20., 3., 4. },
      {2., 6., 7., 8. }, {3., 7., 11., 12. },
      {4., 8., 12., 16. } });

  OGMatrix notSquare = new OGMatrix(new double[][] { {1., 2., 3. }, {4., 5., 6. },
 {7., 8., 9. }, {10., 11., 12. } });

  @Test
  public void symmetricMatrixTrueTest() {
    assertTrue(sym.eval(symmetrixMatrix).fuzzyequals(True, 10 * D1mach.four()));
  }

  @Test
  public void symmetricMatrixFalseTest() {
    assertTrue(sym.eval(asymmetrixMatrix).fuzzyequals(False, 10 * D1mach.four()));
  }

  @Test
  public void notSquareMatrixFalseTest() {
    assertTrue(sym.eval(notSquare).fuzzyequals(False, 10 * D1mach.four()));
  }
}
