/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAExponentsAndLogarithms.log;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * Tests log
 */
@Test(groups = TestGroup.UNIT)
public class DOGMA_Log_Test {
   
  @Test
  public void testcomplex() {
    double[][] data = new double[][] { {0, 2, 3 }, {0, 5, 0 }, {6, 0, 0 } };
    OGArray<? extends Number> foo, bar, baz;
    foo = new OGComplexSparseMatrix(data, data);
    bar = DOGMA.full(DOGMA.log(foo));
    foo = new OGComplexMatrix(data, data);
    baz = DOGMA.full(DOGMA.log(foo));
    assertTrue(baz.fuzzyequals(bar, 10 * D1mach.four()));
  }

  @Test
  public void testRealExpandToComplex() {
    double[][] data = new double[][] { {-1, 2, 3 }, {0, 5, 0 }, {6, 0, 0 } };
    OGArray<? extends Number> foo, bar, baz;
    double[][] rp = new double[][] { {0., 0.6931471805599453, 1.0986122886681098 }, {Double.NEGATIVE_INFINITY, 1.6094379124341003, Double.NEGATIVE_INFINITY },
        {1.7917594692280550, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY } };
    double[][] ip = new double[][] { {3.1415926535897931, 0., 0. }, {0., 0., 0. }, {0., 0., 0. } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    foo = new OGSparseMatrix(data);
    bar = DOGMA.full(DOGMA.log(foo));
    foo = new OGMatrix(data);
    baz = DOGMA.full(DOGMA.log(foo));
    assertTrue(baz.fuzzyequals(bar, 10 * D1mach.four()));
    baz = new OGComplexSparseMatrix(data);
    assertTrue(answer.fuzzyequals(DOGMA.full(DOGMA.log(baz)), 10 * D1mach.four()));
  }

  @Test
  public void testRealNoExpansion() {
    double[][] data = new double[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 } };
    OGArray<? extends Number> foo, bar, baz;
    foo = new OGSparseMatrix(data);
    bar = DOGMA.full(DOGMA.log(foo));
    foo = new OGMatrix(data);
    baz = DOGMA.full(DOGMA.log(foo));
    assertTrue(baz.fuzzyequals(bar, 10 * D1mach.four()));
  }

}
