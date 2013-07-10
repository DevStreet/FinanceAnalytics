/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAInquiry.issymmetric;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAInquiry.issymmetric.IsSymmetricOGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests issymmetric on OGMatrix
 */
@Test(groups = TestGroup.UNIT)
public class OGComplexMatrixIsSymmetricTest {

  OGMatrix True = new OGMatrix(1);
  OGMatrix False = new OGMatrix(0);

  IsSymmetricOGComplexMatrix sym = new IsSymmetricOGComplexMatrix();

  double[][] data0 = new double[][] { {1., 2., 3., 4. }, {2., 6., 7., 8. }, {3., 7., 11., 12. }, {4., 8., 12., 16. } };
  double[][] data1 = new double[][] { {10., 20., 30., 40. }, {20., 60., 70., 80. }, {30., 70., 110., 120. }, {40., 80., 120., 160. } };
  double[][] data2 = new double[][] { {1., 20., 3., 4. }, {2., 6., 7., 8. }, {3., 7., 11., 12. }, {4., 8., 12., 16. } };
  double[][] data3 = new double[][] { {10., 200., 30., 40. }, {20., 60., 70., 80. }, {30., 70., 110., 120. }, {40., 80., 120., 160. } };

  OGComplexMatrix symmetricMatrix = new OGComplexMatrix(data0, data1);
  OGComplexMatrix symmetricRealPartMatrix = new OGComplexMatrix(data0, data2);
  OGComplexMatrix symmetricComplexPartMatrix = new OGComplexMatrix(data1, data2);
  OGComplexMatrix asymmetricMatrix = new OGComplexMatrix(data1, data3);

  OGComplexMatrix notSquare = new OGComplexMatrix(new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } });

  @Test
  public void symmetricMatrixTrueTest() {
    assertTrue(sym.eval(symmetricMatrix).fuzzyequals(True, 10 * D1mach.four()));
  }

  @Test
  public void symmetricRealPartMatrixTrueTest() {
    assertTrue(sym.eval(symmetricRealPartMatrix).fuzzyequals(False, 10 * D1mach.four()));
  }

  @Test
  public void symmetricComplexPartMatrixTrueTest() {
    assertTrue(sym.eval(symmetricComplexPartMatrix).fuzzyequals(False, 10 * D1mach.four()));
  }

  @Test
  public void symmetricMatrixFalseTest() {
    assertTrue(sym.eval(asymmetricMatrix).fuzzyequals(False, 10 * D1mach.four()));
  }

  @Test
  public void notSquareMatrixFalseTest() {
    assertTrue(sym.eval(notSquare).fuzzyequals(False, 10 * D1mach.four()));
  }
}
