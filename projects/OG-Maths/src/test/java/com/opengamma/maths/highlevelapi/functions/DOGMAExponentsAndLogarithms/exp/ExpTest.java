/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAExponentsAndLogarithms.exp;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAExponentsAndLogarithms.exp.ExpOGComplexMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAExponentsAndLogarithms.exp.ExpOGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAExponentsAndLogarithms.exp.ExpOGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAExponentsAndLogarithms.exp.ExpOGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * Test exp
 */
@Test(groups=TestGroup.UNIT)
public class ExpTest {

  ExpOGMatrix ExpOGMatrix = new ExpOGMatrix();
  ExpOGSparseMatrix ExpOGSparseMatrix = new ExpOGSparseMatrix();
  ExpOGComplexMatrix ExpOGComplexMatrix = new ExpOGComplexMatrix();
  ExpOGComplexSparseMatrix ExpOGComplexSparseMatrix = new ExpOGComplexSparseMatrix();

  double[][] real_data = new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } };
  double[][] real_data_suffle = new double[][] { {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. }, {1., 0., 3. } };
  double[][] imag_data = new double[][] { {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. }, {1., 0., 3. } };
  double[][] real_answer = new double[][] { {2.7182818284590451, 1., 20.0855369231876679 }, {1., 148.4131591025765999, 1. }, {1., 1., 8103.0839275753842230 }, {22026.4657948067178950, 1., 1. } };

  double[][] complex_answer_rp = new double[][] { {2.7182818284590451, 0.2836621854632262, 20.0855369231876679 }, {1., 148.4131591025765999, -0.9111302618846769 },
      {-0.8390715290764524, 1., 8103.0839275753842230 }, {11900.9502590597767266, 1., -0.9899924966004454 } };
  double[][] complex_answer_ip = new double[][] { {0., -0.9589242746631385, 0. }, {0., 0., 0.4121184852417566 }, {-0.5440211108893698, 0., 0. }, {18534.6318641934558400, 0., 0.1411200080598672 } };

  OGMatrix realAnswer = new OGMatrix(real_answer);
  OGMatrix ogmatrix = new OGMatrix(real_data);
  OGSparseMatrix ogsparsematrix = new OGSparseMatrix(real_data);

  OGComplexMatrix complexAnswer = new OGComplexMatrix(complex_answer_rp, complex_answer_ip);
  OGComplexMatrix ogcomplexmatrix = new OGComplexMatrix(real_data, real_data_suffle);
  OGComplexSparseMatrix ogcomplexsparsematrix = new OGComplexSparseMatrix(real_data, real_data_suffle);

  @Test
  public void testReal() {
    OGArray<? extends Number> tmp1 = ExpOGMatrix.eval(ogmatrix);
    OGArray<? extends Number> tmp2 = ExpOGSparseMatrix.eval(ogsparsematrix);
    assertTrue(tmp1.fuzzyequals(DOGMA.full(tmp2), 10 * D1mach.four()));
    assertTrue(realAnswer.fuzzyequals(DOGMA.full(tmp2), 10 * D1mach.four()));
  };

  @Test
  public void testComplex() {
    OGArray<? extends Number> tmp1 = ExpOGComplexMatrix.eval(ogcomplexmatrix);
    OGArray<? extends Number> tmp2 = ExpOGComplexSparseMatrix.eval(ogcomplexsparsematrix);
    assertTrue(tmp1.fuzzyequals(DOGMA.full(tmp2), 10 * D1mach.four()));
    assertTrue(complexAnswer.fuzzyequals(DOGMA.full(tmp2), 10 * D1mach.four()));
  };

}
