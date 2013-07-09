/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIndexing.assign;

import static com.opengamma.maths.dogma.DOGMA.assign;
import static com.opengamma.maths.dogma.DOGMA.colon;
import static com.opengamma.maths.dogma.DOGMA.transpose;
import static com.opengamma.maths.dogma.DOGMA.reshape;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Assign test
 */
public class AssignTest {

  @Test
  public void assignTest2D() {
    OGArray<? extends Number> expected;
    OGArray<? extends Number> tmp1 = transpose(reshape(colon(1, 20), 5, 4));
    OGArray<? extends Number> tmp2;
    OGArray<? extends Number> small = transpose(reshape(colon(1000, 1000, 4000), 2, 2));
    tmp2 = assign(tmp1, "2:3,2:3", small);
    assertTrue(tmp1.fuzzyequals(transpose(reshape(colon(1, 20), 5, 4)), 1e-14));
    double[][] data = new double[][] { {1, 2, 3, 4, 5 }, {6, 7, 8, 9, 10 }, {11, 12, 1000, 2000, 15 }, {16, 17, 3000, 4000, 20 } };
    expected = new OGMatrix(data);
    assertTrue(expected.fuzzyequals(tmp2, 1e-14));
    OGComplexMatrix smallcmplx = new OGComplexMatrix(new double[] {10, 100, 30, 300, 20, 200, 40, 400 }, 2, 2);
    OGArray<? extends Number> answer = assign(tmp1, "2:3,2:3", smallcmplx);
    assertTrue(tmp1.fuzzyequals(transpose(reshape(colon(1, 20), 5, 4)), 1e-14));
    expected = new OGComplexMatrix(new double[][] { {1, 2, 3, 4, 5 }, {6, 7, 8, 9, 10 }, {11, 12, 10, 20, 15 }, {16, 17, 30, 40, 20 } },
        new double[][] { {0, 0, 0, 0, 0 }, {0, 0, 0, 0, 0 }, {0, 0, 100, 200, 0 }, {0, 0, 300, 400, 0 } });
    assertTrue(expected.fuzzyequals(answer, 1e-14));
    answer = assign(answer, "0,0", 3);
    expected = new OGComplexMatrix(new double[][] { {3, 2, 3, 4, 5 }, {6, 7, 8, 9, 10 }, {11, 12, 10, 20, 15 }, {16, 17, 30, 40, 20 } },
        new double[][] { {0, 0, 0, 0, 0 }, {0, 0, 0, 0, 0 }, {0, 0, 100, 200, 0 }, {0, 0, 300, 400, 0 } });
    assertTrue(expected.fuzzyequals(answer, 1e-14));
  }

  @Test
  public void assignTest1D() {
    OGArray<? extends Number> expected;
    OGArray<? extends Number> tmp1 = transpose(reshape(colon(1, 20), 5, 4));
    OGArray<? extends Number> tmp2;
    OGArray<? extends Number> small = transpose(reshape(colon(1000, 1000, 4000), 2, 2));
    tmp2 = assign(tmp1, "1:4", small);
    assertTrue(tmp1.fuzzyequals(transpose(reshape(colon(1, 20), 5, 4)), 1e-14));
    expected = new OGMatrix(new double[][] { {1, 4000, 3, 4, 5 }, {1000, 7, 8, 9, 10 }, {3000, 12, 13, 14, 15 }, {2000, 17, 18, 19, 20 } });
    assertTrue(expected.fuzzyequals(tmp2, 1e-14));

    OGComplexMatrix smallcmplx = new OGComplexMatrix(new double[] {10, 100, 30, 300, 20, 200, 40, 400 }, 2, 2);
    OGArray<? extends Number> answer = assign(tmp1, "2:5", smallcmplx);
    assertTrue(tmp1.fuzzyequals(transpose(reshape(colon(1, 20), 5, 4)), 1e-14));
    expected = new OGComplexMatrix(new double[][] { {1, 20, 3, 4, 5 }, {6, 40, 8, 9, 10 }, {10, 12, 13, 14, 15 }, {30, 17, 18, 19, 20 } },
        new double[][] { {0, 200, 0, 0, 0 }, {0, 400, 0, 0, 0 }, {100, 0, 0, 0, 0 }, {300, 0, 0, 0, 0 } });
    assertTrue(expected.fuzzyequals(answer, 1e-14));
    answer = assign(answer, "0", 3);
    expected = new OGComplexMatrix(new double[][] { {3, 20, 3, 4, 5 }, {6, 40, 8, 9, 10 }, {10, 12, 13, 14, 15 }, {30, 17, 18, 19, 20 } },
        new double[][] { {0, 200, 0, 0, 0 }, {0, 400, 0, 0, 0 }, {100, 0, 0, 0, 0 }, {300, 0, 0, 0, 0 } });
    assertTrue(expected.fuzzyequals(answer, 1e-14));

  }
}
