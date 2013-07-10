/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIndexing.select;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;

/**
 * test the select mechanism
 */
@Test
public class SelectTest {

  DOGMA D = new DOGMA();

  OGMatrix densedata = new OGMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }, 4, 3);
  OGSparseMatrix sparsedata = new OGSparseMatrix(new double[][] { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 }, {0, 0, 7, 0 } });
  OGArray<? extends Number> got, expected;

  @Test
  public void linearIndexTest() {
    got = DOGMA.select(densedata, "0:2");
    expected = new OGMatrix(new double[] {1, 2, 3 }, 1, 3);
    assertTrue(expected.fuzzyequals(got, 10 * D1mach.four()));
    got = DOGMA.select(densedata, "0:5");
    expected = new OGMatrix(new double[] {1, 2, 3, 4, 5, 6 }, 1, 6);
    assertTrue(expected.fuzzyequals(got, 10 * D1mach.four()));

    got = DOGMA.select(sparsedata, "0:2");
    expected = new OGMatrix(new double[] {1, 3, 0 }, 1, 3);
    assertTrue(expected.fuzzyequals(got, 10 * D1mach.four()));
    got = DOGMA.select(sparsedata, "0:5");
    expected = new OGMatrix(new double[] {1, 3, 0, 0, 2, 0 }, 1, 6);
    assertTrue(expected.fuzzyequals(got, 10 * D1mach.four()));
  }

  @Test
  public void twoDIndexTest() {
    got = DOGMA.select(densedata, "0:2,:");
    expected = new OGMatrix(new double[][] { {1, 5, 9 }, {2, 6, 10 },
        {3, 7, 11 } });
    assertTrue(expected.fuzzyequals(got, 10 * D1mach.four()));
    got = DOGMA.select(densedata, ":,2");
    expected = new OGMatrix(new double[][] { {9 }, {10 }, {11 }, {12 } });
    assertTrue(expected.fuzzyequals(got, 10 * D1mach.four()));
    got = DOGMA.select(sparsedata, "0:2,:");
    expected = new OGMatrix(new double[][] { {1, 2, 0, 0 }, {3, 0, 4, 0 }, {0, 5, 6, 0 } });
    assertTrue(expected.fuzzyequals(DOGMA.full(got), 10 * D1mach.four()));
    got = DOGMA.select(sparsedata, ":,2");
    expected = new OGMatrix(new double[][] { {0 }, {4 }, {6 }, {7 } });
    assertTrue(expected.fuzzyequals(DOGMA.full(got), 10 * D1mach.four()));
  }
}
