/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd.compute;
import com.opengamma.maths.highlevelapi.functions.DOGMA;

/**
 * Simple svd test
 */
public class SvdSimpleTest {

  private final static int m = 4;
  private final static int n = 3;
  private static double[] s_data = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  private static OGMatrix A = new OGMatrix(s_data, m, n);

  Svd mySVD = new Svd();
  DOGMA DOGMA = new DOGMA();

  @Test
  public void svdCallTest() {
    OGSvdResult result = mySVD.svd(A, compute.USV);
    System.out.println(result.getU().toString());
    System.out.println(result.getS().toString());
    System.out.println(result.getV().toString());
    OGArray<? extends Number> tmp = DOGMA.mtimes(result.getU(),result.getS(),DOGMA.transpose(result.getV()));
    System.out.println(tmp.toString());
  }

}
