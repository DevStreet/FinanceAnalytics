/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import java.util.Arrays;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.ogblas.auxiliary.D1MACH;

/**
 * Tests the {@link OGComplexDiagonalMatrix} Class
 */
public class OGComplexDiagonalMatrixTest {

  double[] data4x3diagdreal = new double[] {1, 2, 3 };
  double[] data4x3diagdimag = new double[] {10, 20, 30 };
  double[] interleavedfull4x3 = DenseMemoryManipulation.convertTwoSinglePointersToInterleavedSinglePointer(data4x3diagdreal, data4x3diagdimag);
  double[] interleavedreal4x3 = DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(data4x3diagdreal);
  ComplexType[][] data4x3full = new ComplexType[][] {//
  {new ComplexType(1, 10), new ComplexType(0.00), new ComplexType(0.00) },//
      {new ComplexType(0.00), new ComplexType(2, 20), new ComplexType(0.00) }, //
      {new ComplexType(0.00), new ComplexType(0.00), new ComplexType(3, 30) },//
      {new ComplexType(0.00), new ComplexType(0.00), new ComplexType(0.00) } };

  // sending in null ptr double[] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrConstructorNullPtrTest() {
    double[] tmp = null;
    new OGComplexDiagonalMatrix(tmp);
  }

  // sending in null ptr double[], int, int constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrIntIntConstructorNullPtrTest() {
    double[] tmp = null;
    new OGComplexDiagonalMatrix(tmp, 1, 1);
  }

  // sending in null ptr double[] double[] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrDoublePtrConstructorNullPtrFirstArgTest() {
    double[] tmp = null;
    new OGComplexDiagonalMatrix(tmp, data4x3diagdimag);
  }

  // sending in null ptr double[] double[] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrDoublePtrConstructorNullPtrSecondArgTest() {
    double[] tmp = null;
    new OGComplexDiagonalMatrix(data4x3diagdreal, tmp);
  }

  // sending in null ptr ComplexType[] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testComplexTypePtrConstructorNullPtrTest() {
    ComplexType[] tmp = null;
    new OGComplexDiagonalMatrix(tmp);
  }

  // sending in null ptr double[] double[] int int  constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrDoublePtrIntIntConstructorNullPtrFirstArgTest() {
    double[] tmp = null;
    new OGComplexDiagonalMatrix(tmp, data4x3diagdimag, 4, 3);
  }

  // sending in null ptr double[] double[] int int  constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrDoublePtrIntIntConstructorNullPtrSecondArgTest() {
    double[] tmp = null;
    new OGComplexDiagonalMatrix(data4x3diagdreal, tmp, 4, 3);
  }

  // sending in non commuting args double[] double[]  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrConstructorNonConformantArgTest() {
    new OGComplexDiagonalMatrix(data4x3diagdreal, new double[] {1, 2 });
  }

  // sending in non commuting args double[] double[] int int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrIntIntConstructorNonConformantArgTest() {
    new OGComplexDiagonalMatrix(data4x3diagdreal, new double[] {1, 2 }, 4, 3);
  }

  // sending in bad rows double[], int, int constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrIntIntConstructorBadRowsDataTest() {
    new OGComplexDiagonalMatrix(data4x3diagdreal, -1, 3);
  }

  // sending in bad rows double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrIntIntConstructorBadColsDataTest() {
    new OGComplexDiagonalMatrix(data4x3diagdreal, 3, -1);
  }

  // sending in bad rows double[], double[], int, int constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrIntIntConstructorBadRowsDataTest() {
    new OGComplexDiagonalMatrix(data4x3diagdreal, data4x3diagdimag, -1, 3);
  }

  // sending in bad rows double[], double[], int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrDoublePtrIntIntConstructorBadColsDataTest() {
    new OGComplexDiagonalMatrix(data4x3diagdreal, data4x3diagdimag, 3, -1);
  }

  // sending in bad rows double, int, int constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoubleIntIntConstructorBadRowsDataTest() {
    new OGComplexDiagonalMatrix(1, -1, 3);
  }

  // sending in bad cols double, int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoubleIntIntConstructorBadColsDataTest() {
    new OGComplexDiagonalMatrix(1, 3, -1);
  }

  // sending in bad rows double, int, int constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoubleDoubleIntIntConstructorBadRowsDataTest() {
    new OGComplexDiagonalMatrix(1, 2, -1, 3);
  }

  // sending in bad cols double, int, int  constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoubleDoubleIntIntConstructorBadColsDataTest() {
    new OGComplexDiagonalMatrix(1, 2, 3, -1);
  }

  // sending in ok ComplexType[] constructor
  @Test
  public void testComplexTypePtrConstructorInternalDataTest() {
    ComplexType[] tmp = new ComplexType[] {new ComplexType(1, 10), new ComplexType(2, 20), new ComplexType(3, 30) };
    new OGComplexDiagonalMatrix(tmp);
  }

  // sending in ok double[] constructor
  @Test
  public void testDoublePtrConstructorInternalDataTest() {
    OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal);
    assertTrue(D.getClass() == OGComplexDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), interleavedreal4x3));
    assertTrue(D.getNumberOfRows() == 3);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // sending in ok double[] double[] constructor
  @Test
  public void testDoublePtrDoublePtrConstructorInternalDataTest() {
    OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, data4x3diagdimag);
    assertTrue(D.getClass() == OGComplexDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), interleavedfull4x3));
    assertTrue(D.getNumberOfRows() == 3);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // sending in ok double[] double[] constructor
  @Test
  public void testDoublePtrDoublePtrIntIntConstructorInternalDataTest() {
    OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, data4x3diagdimag, 4, 3);
    assertTrue(D.getClass() == OGComplexDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), interleavedfull4x3));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // sending in ok double[], int, int constructor
  @Test
  public void testDoublePtrIntIntConstructorInternalDataTest() {
    OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 16, 32);
    assertTrue(D.getClass() == OGComplexDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), interleavedreal4x3));
    assertTrue(D.getNumberOfRows() == 16);
    assertTrue(D.getNumberOfColumns() == 32);
  }

  // sending in ok double, int, int  constructor
  @Test
  public void testDoubleIntIntConstructorInternalDataTest() {
    OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(3, 13, 37);
    assertTrue(D.getClass() == OGComplexDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {3 }));
    assertTrue(D.getNumberOfRows() == 13);
    assertTrue(D.getNumberOfColumns() == 37);
  }

  // sending in ok double constructor
  @Test
  public void testDoubleConstructorInternalDataTest() {
    OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(3.d);
    assertTrue(D.getClass() == OGComplexDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {3 }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);
  }

  // sending in ok int constructor
  @Test
  public void testIntConstructorInternalDataTest() {
    OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix((int) 3);
    assertTrue(D.getClass() == OGComplexDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {3 }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);
  }

  // sending in ok real imag constructor
  @Test
  public void testDoubleDoubleConstructorInternalDataTest() {
    OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix((double) 3, (double) 4);
    assertTrue(D.getClass() == OGComplexDiagonalMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {3, 4 }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);
  }

  // test get rows
    @Test
    public void testGetRowsTest() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      assertTrue(D.getNumberOfRows() == 4);
    }
  
    // test get cols
    @Test
    public void testGetColumnsTest() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      assertTrue(D.getNumberOfColumns() == 3);
    }
  
    // test get entry bad index count
    @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
    public void testGetEntryBadIndexCountTest() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      D.getEntry(1, 2, 3);
    }
  
    // test get entry bad row index
    @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
    public void testGetEntryBadRowIndicesTest() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      D.getEntry(23, 1);
    }
  
    // test get entry bad row index
    @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
    public void testGetEntryBadColumnIndicesTest() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      D.getEntry(1, 23);
    }
    
    // test get entry neg row index
    @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
    public void testGetEntryNegRowIndicesTest() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      D.getEntry(-1, 1);
    }
  
    // test get entry neg row index
    @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
    public void testGetEntryNegColumnIndicesTest() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      D.getEntry(1, -1);
    }
    

  // test get entry ok
    @Test
    public void testGetEntryOKIndicesTest() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal,data4x3diagdimag, 4, 3);
      for (int i = 0; i < D.getNumberOfRows(); i++) {
        for (int j = 0; j < D.getNumberOfColumns(); j++) {
          assertTrue(D.getEntry(i, j).equals(data4x3full[i][j]));
        }
      }
    }

   //test get data
    @Test
    public void testGetDataTest() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal,data4x3diagdimag, 4, 3);
      assertTrue(Arrays.equals(D.getData(),interleavedfull4x3));
    }
  
    // test equals obj points to obj
    @Test
    public void testEqualsObjeqObj() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      assertTrue(D.equals(D));
    }
  
    // test equals obj not = null
    @Test
    public void testEqualsObjNull() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      assertFalse(D.equals(null));
    }
  
    // test equals obj class different
    @Test
    public void testEqualsObjDifferentClass() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      assertFalse(D.equals(new Double(1.)));
    }
  
    // test equals obj class ok, diff cols
    @Test
    public void testEqualsObjDifferentCols() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      OGComplexDiagonalMatrix Diff = new OGComplexDiagonalMatrix(7);
      assertFalse(D.equals(Diff));
    }
  
    // test equals obj class ok, same cols diff rows
    @Test
    public void testEqualsObjDifferentRows() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      OGComplexDiagonalMatrix Diff = new OGComplexDiagonalMatrix(new double[] {1, 2, 3 }, 3, 3);
      assertFalse(D.equals(Diff));
    }
  
    // test equals obj class ok, same cols same rows different data
    @Test
    public void testEqualsObjDifferentData() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      OGComplexDiagonalMatrix Diff = new OGComplexDiagonalMatrix(new double[] {999, 2, 3 }, 4, 3);
      assertFalse(D.equals(Diff));
    }
  
    // test equals obj class ok, same cols same rows same data
    @Test
    public void testEqualsObjStructurallyIdentical() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      OGComplexDiagonalMatrix Diff = new OGComplexDiagonalMatrix(new double[] {1.00, 2.00, 3.00 }, 4, 3);
      assertTrue(D.equals(Diff));
    }
  
    // test fuzzy equals obj points to obj
    @Test
    public void testFuzzyEqualsObjeqObj() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      assertTrue(D.fuzzyequals(D, 10 * D1MACH.four()));
    }
  
    // test fuzzy equals obj not = null
    @Test
    public void testFuzzyEqualsObjNull() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      assertFalse(D.fuzzyequals(null, 10 * D1MACH.four()));
    }
  
    // test fuzzy equals obj class different
    @Test
    public void testFuzzyEqualsObjDifferentClass() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      assertFalse(D.fuzzyequals(new Double(1.), 10 * D1MACH.four()));
    }
  
    // test fuzzy equals obj class ok, diff cols
    @Test
    public void testFuzzyEqualsObjDifferentCols() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      OGComplexDiagonalMatrix Diff = new OGComplexDiagonalMatrix(7);
      assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
    }
  
    // test fuzzy equals obj class ok, same cols diff rows
    @Test
    public void testFuzzyEqualsObjDifferentRows() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      OGComplexDiagonalMatrix Diff = new OGComplexDiagonalMatrix(new double[] {1, 2, 3 }, 4, 13);
      assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
    }
  
    // test fuzzy equals obj class ok, same cols same rows different data
    @Test
    public void testFuzzyEqualsObjDifferentData() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      OGComplexDiagonalMatrix Diff = new OGComplexDiagonalMatrix(new double[] {999.00, 2.00, 3.00 }, 4, 3);
      assertFalse(D.fuzzyequals(Diff, 10 * D1MACH.four()));
    }
  
    // test fuzzy equals obj class ok, same cols same rows same data
    @Test
    public void testFuzzyEqualsObjStructurallyIdentical() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      OGComplexDiagonalMatrix Same = new OGComplexDiagonalMatrix(new double[] {1.00 + 9 * D1MACH.four(), 2.00, 3.00 }, 4, 3);
      assertTrue(D.fuzzyequals(Same, 10 * D1MACH.four()));
    }
  
    // test hash code
    @Test
    public void testHashCodeTest() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      D.hashCode();
    }
  
    // test hash code
    @Test
    public void testToStringTest() {
      OGComplexDiagonalMatrix D = new OGComplexDiagonalMatrix(data4x3diagdreal, 4, 3);
      D.toString();
    }

}
