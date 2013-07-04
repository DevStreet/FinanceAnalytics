/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.sqrt;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests sqrt of an OGComplexMatrix 
 */
@Test
public class SqrtOGComplexMatrixTest {

  SqrtOGComplexMatrix sqrt = new SqrtOGComplexMatrix();

  double[][] data0 = new double[][] { {0.1, 0.2, 0.3 }, {0.4, 0.5, 0.6 }, {0.7, 0.8, 0.9 }, {1.0, 1.1, 1.2 } };
  double[][] data1 = new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
  double[][] data2 = new double[][] { {0.1, 0.2, 0.3, 0.4 }, {0.5, 0.6, 0.7, 0.8 }, {0.9, 1.0, 1.1, 1.2 } };
  double[][] data3 = new double[][] { {1., 2., 3., 4. }, {5., 6., 7., 8. }, {9., 10., 11., 12. } };
  double[][] Vr = new double[][] { {1. }, {2. }, {3. } };
  double[][] Hr = new double[][] {{1., 2., 3. } };
  double[][] Vi = new double[][] { {10. }, {20. }, {30. } };
  double[][] Hi = new double[][] {{10., 20., 30. } };
  OGComplexMatrix Single = new OGComplexMatrix(new double[] {10, 20 }, 1, 1);
  OGComplexMatrix Amatrix = new OGComplexMatrix(data0, data1);
  OGComplexMatrix Bmatrix = new OGComplexMatrix(data2, data3);
  OGComplexMatrix Vmatrix = new OGComplexMatrix(Vr, Vi);
  OGComplexMatrix Hmatrix = new OGComplexMatrix(Hr, Hi);
  double[][] rp, ip;

  @Test
  public void complexTest() {
    rp = new double[][] { {0.7432992540397472, 1.0511838859648148, 1.2874320732248881 }, {1.4865985080794943, 1.6620676596577597, 1.8207038985887485 },
        {1.9665849758889931, 2.1023677719296296, 2.2298977621192413 }, {2.3505186258697131, 2.4652447326009010, 2.5748641464497761 } };
    ip = new double[][] { {0.6726765798331650, 0.9513083422908102, 1.1651100133327039 }, {1.3453531596663300, 1.5041505593790212, 1.6477143825118072 },
        {1.7797349430160412, 1.9026166845816204, 2.0180297394994953 }, {2.1271901209248893, 2.2310158205661588, 2.3302200266654078 } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    OGArray<? extends Number> tmp = sqrt.eval(Amatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

}
