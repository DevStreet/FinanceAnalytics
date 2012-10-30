/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMA;

/**
 * tests diag mtimes full
 */
public class MtimesOGDiagonalArrayOGDoubleArrayTest {
  double NaN = Double.NaN;
  double single = 7.d;
  double[] _dataC = new double[] {1., 2., 3. };
  double[][] _dataD6x3 = new double[][] { {1.00, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 }, {13.00, 14.00, 15.00 }, {16.00, 17.00, 18.00 } };
  double[][] _dataD6x4 = new double[][] { {1.00, 2.00, 3.00, 4.00 }, {5.00, 6.00, 7.00, 8.00 }, {9.00, 10.00, 11.00, 12.00 }, {13.00, 14.00, 15.00, 16.00 }, {17.00, 18.00, 19.00, 20.00 },
      {21.00, 22.00, 23.00, 24.00 } };
  double[][] _dataC3x6timesD = new double[][] { {1.00, 2.00, 3.00 }, {8.00, 10.00, 12.00 }, {21.00, 24.00, 27.00 } };
  double[][] _dataC4x6timesD = { {1.00, 2.00, 3.00 }, {8.00, 10.00, 12.00 }, {21.00, 24.00, 27.00 }, {0.00, 0.00, 0.00 } };
  double[][] _dataSingleTimesD = new double[][] { {7.00, 14.00, 21.00 }, {28.00, 35.00, 42.00 }, {49.00, 56.00, 63.00 }, {70.00, 77.00, 84.00 }, {91.00, 98.00, 105.00 }, {112.00, 119.00, 126.00 } };
  double[][] _dataSingleNaNTimesD = new double[][] { {NaN, NaN, NaN }, {NaN, NaN, NaN }, {NaN, NaN, NaN }, {NaN, NaN, NaN }, {NaN, NaN, NaN }, {NaN, NaN, NaN } };
  double[][] _dataC4x6TimesSingleNaN = new double[][] {{NaN,NaN,NaN,NaN,NaN,NaN},{NaN,NaN,NaN,NaN,NaN,NaN},{NaN,NaN,NaN,NaN,NaN,NaN},{NaN,NaN,NaN,NaN,NaN,NaN}};
  double[] _dataVector6x1 = new double[] {1,2,3,4,5,6};
  double[] _dataC4x6TimesVector6x1 = new double[] {1,4,9,0};
  
  double[] _dataC4x6TimesSingle = new double[] {7, 14, 21 };
  OGDoubleArray OGD1x1 = new OGDoubleArray(single);
  OGDoubleArray OGD1x1NaN = new OGDoubleArray(NaN);
  OGDoubleArray OGDA6x3 = new OGDoubleArray(_dataD6x3);
  OGDoubleArray OGDA6x4 = new OGDoubleArray(_dataD6x4);
  OGDoubleArray OGC3x6timesD = new OGDoubleArray(_dataC3x6timesD);
  OGDoubleArray OGC4x6timesD = new OGDoubleArray(_dataC4x6timesD);
  OGDoubleArray OGSingleTimesD = new OGDoubleArray(_dataSingleTimesD);
  OGDoubleArray OGSingleNaNTimesD = new OGDoubleArray(_dataSingleNaNTimesD);
  OGDoubleArray OGVector6x1 = new OGDoubleArray(_dataVector6x1,6,1);
  OGDoubleArray OGC4x6TimesSingleNaN = new OGDoubleArray(_dataC4x6TimesSingleNaN);
  OGDoubleArray OGC4x6TimesVector6x1 = new OGDoubleArray(_dataC4x6TimesVector6x1,4,1);
  
  OGDiagonalArray OGDiag1x1 = new OGDiagonalArray(single);
  OGDiagonalArray OGDiag1x1NaN = new OGDiagonalArray(NaN);
  OGDiagonalArray OGDiag3x6 = new OGDiagonalArray(_dataC, 3, 6);
  OGDiagonalArray OGDiag4x6 = new OGDiagonalArray(_dataC, 4, 6);
  OGDiagonalArray OGDiag6x4 = new OGDiagonalArray(_dataC, 6, 4);
  OGDiagonalArray OGDiag6x2 = new OGDiagonalArray(_dataC, 6, 2);
  OGDiagonalArray OGDiag4x6TimesSingle = new OGDiagonalArray(_dataC4x6TimesSingle, 4, 6);

  MtimesOGDiagonalArrayOGDoubleArray mtimes = MtimesOGDiagonalArrayOGDoubleArray.getInstance();

  DOGMA dogma = new DOGMA();
  OGArraySuper<? extends Number> tmp;

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void null1Test() {
    mtimes.mtimes(null, OGDA6x3);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void null2Test() {
    mtimes.mtimes(OGDiag3x6, null);
  }

  // branches in turn
  
  // colsArray1 = 1, rowsArray1 = 1, data = NaN
  @Test
  public void singleNaNTimesMatrixTest() {
    tmp = mtimes.mtimes(OGDiag1x1NaN, OGDA6x3);
    assertTrue(OGSingleNaNTimesD.equals(tmp));
  }

  // colsArray1 = 1, rowsArray1 = 1, data = double  
  @Test
  public void singleTimesMatrixTest() {
    tmp = mtimes.mtimes(OGDiag1x1, OGDA6x3);
    assertTrue(OGSingleTimesD.equals(tmp));
  }

  // colsArray2 = 1, rowsArray1 = 2, data = NaN
  @Test
  public void diagTimesSingleNaNTest() {
    tmp = mtimes.mtimes(OGDiag4x6, OGD1x1NaN);
    assertTrue(OGC4x6TimesSingleNaN.equals(tmp));
  }  
  
  // colsArray2 = 1, rowsArray1 = 2, data = double
  @Test
  public void diagTimesSingleTest() {
    tmp = mtimes.mtimes(OGDiag4x6, OGD1x1);
    assertTrue(OGDiag4x6TimesSingle.equals(tmp));
  }

  // colsArray1 = random, rowsArray1 = random, colsArray2 = 1, rowsArray2 = random. Diag * vector test
  @Test
  public void diagTimesVectorTest() {
    tmp = mtimes.mtimes(OGDiag4x6, OGVector6x1);
    assertTrue(OGC4x6TimesVector6x1.equals(tmp));
  }
  
  // colsArray1 = random, rowsArray1 = random, colsArray2 = random, rowsArray2 = random
  @Test
  public void matrixTimesMatrixNoExtraZerosTest() {
    tmp = mtimes.mtimes(OGDiag3x6, OGDA6x3);
    assertTrue(OGC3x6timesD.equals(tmp));
  }

  // colsArray1 = random, rowsArray1 = random, Array1 has a zero block as it is not square. colsArray2 = random, rowsArray2 = random  
  @Test
  public void matrixTimesMatrixExtraZerosBelowTest() {
    tmp = mtimes.mtimes(OGDiag4x6, OGDA6x3);
    assertTrue(OGC4x6timesD.equals(tmp));
  }

}
