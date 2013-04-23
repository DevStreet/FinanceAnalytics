/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.sort;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.opengamma.analytics.math.statistics.distribution.fnlib.D1MACH;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Tests sort on OGMatrices 
 */
@Test
public class OGMatrixSortTest {

  SortOGMatrix sort = new SortOGMatrix();
  double[] unsortedVector = new double[] {5, 2, 1, 3, 4 };
  double[][] wideMatrix = new double[][] { {3., 2., 2., 3., 3. }, {1., 3., 1., 2., 2. }, {2., 1., 3., 1., 1. } };
  double[][] tallMatrix = new double[][] { {17., 24., 1. }, {23., 5., 7. }, {4., 6., 13. }, {10., 12., 19. }, {11., 18., 25. } };
  double[][] tallMatrixWithRepeats = new double[][] { {3., 1., 2. }, {2., 3., 1. }, {2., 1., 3. }, {3., 2., 1. }, {3., 2., 1. } };
  OGMatrix MunsortedVVector = new OGMatrix(unsortedVector, 5, 1);
  OGMatrix MunsortedHVector = new OGMatrix(unsortedVector, 1, 5);
  OGMatrix MunsortedWideMatrixWithRepeats = new OGMatrix(wideMatrix);
  OGMatrix MunsortedTallMatrix = new OGMatrix(tallMatrix);
  OGMatrix MunsortedTallMatrixWithRepeats = new OGMatrix(tallMatrixWithRepeats);

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void illegalDimensionNeg() {
    sort.sort(MunsortedVVector, SortCompute.valuesAndKeys, -1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void illegalDimensionLarge() {
    sort.sort(MunsortedVVector, SortCompute.valuesAndKeys, 3);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void illegalSortString() {
    sort.sort(MunsortedVVector, SortCompute.valuesAndKeys, "banana");
  }

  @Test
  public void testSortVVect() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedVVector, SortCompute.valuesAndKeys);
    OGMatrix values = new OGMatrix(new double[][] { {1. }, {2. }, {3. }, {4. }, {5. } });
    OGMatrix indices = new OGMatrix(new double[][] { {2. }, {1. }, {3. }, {4. }, {0. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testSortVVectDim0() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedVVector, SortCompute.valuesAndKeys, 0);
    OGMatrix values = new OGMatrix(new double[][] { {1. }, {2. }, {3. }, {4. }, {5. } });
    OGMatrix indices = new OGMatrix(new double[][] { {2. }, {1. }, {3. }, {4. }, {0. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testSortVVectDim0Descend() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedVVector, SortCompute.valuesAndKeys, 0, "descend");
    OGMatrix values = new OGMatrix(new double[][] { {5. }, {4. }, {3. }, {2. }, {1. } });
    OGMatrix indices = new OGMatrix(new double[][] { {0. }, {4. }, {3. }, {1. }, {2. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testSortVVectDim1() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedVVector, SortCompute.valuesAndKeys, 1);
    OGMatrix values = new OGMatrix(new double[][] { {5. }, {2. }, {1. }, {3. }, {4. } });
    OGMatrix indices = new OGMatrix(new double[][] { {0. }, {0. }, {0. }, {0. }, {0. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testSortVVectDim1Descend() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedVVector, SortCompute.valuesAndKeys, 1, "descend");
    OGMatrix values = new OGMatrix(new double[][] { {5. }, {2. }, {1. }, {3. }, {4. } });
    OGMatrix indices = new OGMatrix(new double[][] { {0. }, {0. }, {0. }, {0. }, {0. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testSortHVect() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedHVector, SortCompute.valuesAndKeys);
    OGMatrix values = new OGMatrix(new double[][] {{1., 2., 3., 4., 5. } });
    OGMatrix indices = new OGMatrix(new double[][] {{2., 1., 3., 4., 0. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testSortHVectDim0() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedHVector, SortCompute.valuesAndKeys, 0);
    OGMatrix values = new OGMatrix(new double[][] {{5., 2., 1., 3., 4. } });
    OGMatrix indices = new OGMatrix(new double[][] {{0., 0., 0., 0., 0. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testSortHVectDim0Descend() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedHVector, SortCompute.valuesAndKeys, 0, "descend");
    OGMatrix values = new OGMatrix(new double[][] {{5., 2., 1., 3., 4. } });
    OGMatrix indices = new OGMatrix(new double[][] {{0., 0., 0., 0., 0. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testSortHVectDim1() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedHVector, SortCompute.valuesAndKeys, 1);
    OGMatrix values = new OGMatrix(new double[][] {{1., 2., 3., 4., 5. } });
    OGMatrix indices = new OGMatrix(new double[][] {{2., 1., 3., 4., 0. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testSortHVectDim1Descend() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedHVector, SortCompute.valuesAndKeys, 1, "descend");
    OGMatrix values = new OGMatrix(new double[][] {{5., 4., 3., 2., 1. } });
    OGMatrix indices = new OGMatrix(new double[][] {{0., 4., 3., 1., 2. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }
  
  @Test
  public void testSortWideMatrixWithRepeats() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedWideMatrixWithRepeats, SortCompute.valuesAndKeys);
    OGMatrix values = new OGMatrix(new double[][] { {1., 1., 1., 1., 1. }, {2., 2., 2., 2., 2. }, {3., 3., 3., 3., 3. } });
    OGMatrix indices = new OGMatrix(new double[][] { {1., 2., 1., 2., 2. }, {2., 0., 0., 1., 1. }, {0., 1., 2., 0., 0. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testSortWideMatrixWithRepeatsDescend() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedWideMatrixWithRepeats, SortCompute.valuesAndKeys, "descend");
    OGMatrix values = new OGMatrix(new double[][] { {3., 3., 3., 3., 3. }, {2., 2., 2., 2., 2. }, {1., 1., 1., 1., 1. } });
    OGMatrix indices = new OGMatrix(new double[][] { {0., 1., 2., 0., 0. }, {2., 0., 0., 1., 1. }, {1., 2., 1., 2., 2. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testSortWideMatrixWithRepeatsDescendDimension1() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedWideMatrixWithRepeats, SortCompute.valuesAndKeys, 1, "descend");
    OGMatrix values = new OGMatrix(new double[][] { {3., 3., 3., 2., 2. }, {3., 2., 2., 1., 1. }, {3., 2., 1., 1., 1. } });
    OGMatrix indices = new OGMatrix(new double[][] { {0., 3., 4., 1., 2. }, {1., 3., 4., 0., 2. }, {2., 0., 1., 3., 4. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testTallMatrix() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedTallMatrix, SortCompute.valuesAndKeys);
    OGMatrix values = new OGMatrix(new double[][] { {4., 5., 1. }, {10., 6., 7. }, {11., 12., 13. }, {17., 18., 19. }, {23., 24., 25. } });
    OGMatrix indices = new OGMatrix(new double[][] { {2., 1., 0. }, {3., 2., 1. }, {4., 3., 2. }, {0., 4., 3. }, {1., 0., 4. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testTallMatrixWithRepeats() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedTallMatrixWithRepeats, SortCompute.valuesAndKeys);
    OGMatrix values = new OGMatrix(new double[][] { {2., 1., 1. }, {2., 1., 1. }, {3., 2., 1. }, {3., 2., 2. }, {3., 3., 3. } });
    OGMatrix indices = new OGMatrix(new double[][] { {1., 0., 1. }, {2., 2., 3. }, {0., 3., 4.000000000000000 }, {3., 4., 0. }, {4., 1., 2. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testTallMatrixWithRepeatsDimension0() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedTallMatrixWithRepeats, SortCompute.valuesAndKeys, 0);
    OGMatrix values = new OGMatrix(new double[][] { {2., 1., 1. }, {2., 1., 1. }, {3., 2., 1. }, {3., 2., 2. }, {3., 3., 3. } });
    OGMatrix indices = new OGMatrix(new double[][] { {1., 0., 1. }, {2., 2., 3. }, {0., 3., 4. }, {3., 4., 0. }, {4., 1., 2. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testTallMatrixWithRepeatsDimension1() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedTallMatrixWithRepeats, SortCompute.valuesAndKeys, 1);
    OGMatrix values = new OGMatrix(new double[][] { {1., 2., 3. }, {1., 2., 3. }, {1., 2., 3. }, {1., 2., 3. }, {1., 2., 3. } });
    OGMatrix indices = new OGMatrix(new double[][] { {1., 2., 0. }, {2., 0., 1. }, {1., 0., 2. }, {2., 1., 0. }, {2., 1., 0. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }

  @Test
  public void testTallMatrixWithRepeatsDescend() {
    List<OGArray<? extends Number>> result = sort.sort(MunsortedTallMatrixWithRepeats, SortCompute.valuesAndKeys, "descend");
    OGMatrix values = new OGMatrix(new double[][] { {3., 3., 3. }, {3., 2., 2. }, {3., 2., 1. }, {2., 1., 1. }, {2., 1., 1. } });
    OGMatrix indices = new OGMatrix(new double[][] { {0., 1., 2. }, {3., 3., 0. }, {4., 4., 1. }, {1., 0., 3. }, {2., 2., 4. } });
    assertTrue(values.fuzzyequals(result.get(0), D1MACH.four()));
    assertTrue(indices.fuzzyequals(result.get(1), D1MACH.four()));
  }
}
