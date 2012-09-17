/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.linearalgebra.blase;

import java.util.Arrays;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * tests the dense data converters for BLAS/LAPACK storage schemes "Packed" and "Banded"
 */
public class DenseDataConvertersTest {

  private double[] aMatrix = new double[] {1, 6, 11, 16, 2, 7, 12, 17, 3, 8, 13, 18, 4, 9, 14, 19, 5, 10, 15, 20 };
  private double[] aMatrix_toUT = new double[] {1, 0, 0, 0, 2, 7, 0, 0, 3, 8, 13, 0, 4, 9, 14, 19, 5, 10, 15, 20 };
  private double[] aMatrix_toLT = new double[] {1, 6, 11, 16, 0, 7, 12, 17, 0, 0, 13, 18, 0, 0, 0, 19, 0, 0, 0, 0 };
  private double[] aMatrix_triUpacked = new double[] {1.0, 2.0, 7.0, 3.0, 8.0, 13.0, 4.0, 9.0, 14.0, 19.0 };
  private double[] aMatrix_triLpacked = new double[] {1, 6, 11, 16, 7, 12, 17, 13, 18, 19 };
  private double[] aMatrix_bandedPackedku1kl2 = new double[] {0, 1, 6, 11, 2, 7, 12, 17, 8, 13, 18, 0, 14, 19, 0, 0, 20, 0, 0, 0 };
  private double[] aMatrix_bandedPackedku1kl1 = new double[] {0, 1, 6, 2, 7, 12, 8, 13, 18, 14, 19, 0, 20, 0, 0 };
  private double[] aMatrix_bandedPackedku2kl1 = new double[] {0, 0, 1, 6, 0, 2, 7, 12, 3, 8, 13, 18, 9, 14, 19, 0, 15, 20, 0, 0 };
  private int m = 4;
  private int n = 5;
  private int ku1 = 1;
  private int ku2 = 2;
  private int kl1 = 1;
  private int kl2 = 2;

  // get UT

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void fromDenseExtractUpperTriangleBadArg1Test() {
    DenseDataConverters.fromDenseExtractUpperTriangle(null, m, n);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractUpperTriangleBadArg2Test() {
    DenseDataConverters.fromDenseExtractUpperTriangle(aMatrix, -1, n);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractUpperTriangleBadArg3Test() {
    DenseDataConverters.fromDenseExtractUpperTriangle(aMatrix, m, -1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractUpperTriangleBadDataLengthTest() {
    DenseDataConverters.fromDenseExtractUpperTriangle(aMatrix, 1000, 1);
  }

  @Test
  public void fromDenseExtractUpperTriangleAlgTest() {
    double[] answer = DenseDataConverters.fromDenseExtractUpperTriangle(aMatrix, m, n);
    assertTrue(Arrays.equals(answer, aMatrix_toUT));
  }

  // get LT
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void fromDenseExtractLowerTriangleBadArg1Test() {
    DenseDataConverters.fromDenseExtractLowerTriangle(null, m, n);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractLowerTriangleBadArg2Test() {
    DenseDataConverters.fromDenseExtractLowerTriangle(aMatrix, -1, n);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractLowerTriangleBadArg3Test() {
    DenseDataConverters.fromDenseExtractLowerTriangle(aMatrix, m, -1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractLowerTriangleBadDataLengthTest() {
    DenseDataConverters.fromDenseExtractUpperTriangle(aMatrix, 1000, 1);
  }

  @Test
  public void fromDenseExtractLowerTriangleTest() {
    double[] answer = DenseDataConverters.fromDenseExtractLowerTriangle(aMatrix, m, n);
    assertTrue(Arrays.equals(answer, aMatrix_toLT));
  }

  // get packed UT 
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void fromDenseExtractUpperTriangleAndPackBadArg1Test() {
    DenseDataConverters.fromDenseExtractUpperTriangleToPackedStorage(null, n);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractUpperTriangleAndPackBadArg2Test() {
    DenseDataConverters.fromDenseExtractUpperTriangleToPackedStorage(aMatrix, -1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractUpperTriangleAndPackBadDataLengthTest() {
    DenseDataConverters.fromDenseExtractUpperTriangleToPackedStorage(aMatrix, 1000);
  }

  @Test
  public void fromDenseExtractUpperTriangleAndPackAlgTest() {
    double[] answer = DenseDataConverters.fromDenseExtractUpperTriangleToPackedStorage(aMatrix, Math.min(n, m));
    assertTrue(Arrays.equals(answer, aMatrix_triUpacked));
  }

  // get packed UT 
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void fromDenseExtractLowerTriangleAndPackBadArg1Test() {
    DenseDataConverters.fromDenseExtractLowerTriangleToPackedStorage(null, n);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractLowerTriangleAndPackBadArg2Test() {
    DenseDataConverters.fromDenseExtractLowerTriangleToPackedStorage(aMatrix, -1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractLowerTriangleAndPackBadDataLengthTest() {
    DenseDataConverters.fromDenseExtractLowerTriangleToPackedStorage(aMatrix, 1000);
  }

  @Test
  public void fromDenseExtractLowerTriangleAndPackAlgTest() {
    double[] answer = DenseDataConverters.fromDenseExtractLowerTriangleToPackedStorage(aMatrix, Math.min(n, m));
    assertTrue(Arrays.equals(answer, aMatrix_triLpacked));
  }

  // get packed banded
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void fromDenseExtractBandedMatrixToBandStorageBadArg1Test() {
    DenseDataConverters.fromDenseExtractBandMatrixToBandStorage(null, m, n, 1, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractBandedMatrixToBandStorageBadArg2Test() {
    DenseDataConverters.fromDenseExtractBandMatrixToBandStorage(aMatrix, -1, n, 1, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractBandedMatrixToBandStorageBadArg3Test() {
    DenseDataConverters.fromDenseExtractBandMatrixToBandStorage(aMatrix, m, -1, 1, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractBandedMatrixToBandStorageBadArg4Test() {
    DenseDataConverters.fromDenseExtractBandMatrixToBandStorage(aMatrix, m, n, -1, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractBandedMatrixToBandStorageBadArg5Test() {
    DenseDataConverters.fromDenseExtractBandMatrixToBandStorage(aMatrix, m, n, 1, -1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractBandedMatrixToBandStorageToLargeArg4Test() {
    DenseDataConverters.fromDenseExtractBandMatrixToBandStorage(aMatrix, m, n, n+1, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractBandedMatrixToBandStorageToLargeArg5Test() {
    DenseDataConverters.fromDenseExtractBandMatrixToBandStorage(aMatrix, m, n, 1, m+1);
  }
  
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void fromDenseExtractBandedMatrixToBandStorageBadDataLengthTest() {
    DenseDataConverters.fromDenseExtractBandMatrixToBandStorage(aMatrix, 1000, n, 1, 1);
  }

  @Test
  public void fromDenseExtractBandMatrixtoBandStorageAlgTest() {
    double[] answer = null;
    answer = DenseDataConverters.fromDenseExtractBandMatrixToBandStorage(aMatrix, m, n, ku1, kl2);
    assertTrue(Arrays.equals(answer, aMatrix_bandedPackedku1kl2));
    answer = DenseDataConverters.fromDenseExtractBandMatrixToBandStorage(aMatrix, m, n, ku1, kl1);
    assertTrue(Arrays.equals(answer, aMatrix_bandedPackedku1kl1));
    answer = DenseDataConverters.fromDenseExtractBandMatrixToBandStorage(aMatrix, m, n, ku2, kl1);
    assertTrue(Arrays.equals(answer, aMatrix_bandedPackedku2kl1));
  }

}
