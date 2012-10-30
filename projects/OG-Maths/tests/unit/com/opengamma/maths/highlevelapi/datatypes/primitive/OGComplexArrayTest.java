/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.ogblas.auxiliary.D1MACH;

/**
 * Tests the OGComlpexArray class 
 */
public class OGComplexArrayTest {

  double[][] realdata4x3 = new double[][] { {1.00, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } };
  double[] realdata4x3unwound = new double[] {1.00, 4.00, 7.00, 10.00, 2.00, 5.00, 8.00, 11.00, 3.00, 6.00, 9.00, 12.00 };

  double[][] imagdata4x3 = new double[][] { {10.00, 20.00, 30.00 }, {40.00, 50.00, 60.00 }, {70.00, 80.00, 90.00 }, {100.00, 110.00, 120.00 } };
  double[] imagdata4x3unwound = new double[] {10.00, 40.00, 70.00, 100.00, 20.00, 50.00, 80.00, 110.00, 30.00, 60.00, 90.00, 120.00 };

  double[] interleaved4x3 = new double[] {1.00, 10.00, 4.00, 40.00, 7.00, 70.00, 10.00, 100.00, 2.00, 20.00, 5.00, 50.00, 8.00, 80.00, 11.00, 110.00, 3.00, 30.00, 6.00, 60.00, 9.00, 90.00, 12.00,
      120.00 };

  ComplexType[][] cmplx4x3 = new ComplexType[][] { {new ComplexType(1, 10), new ComplexType(2, 20), new ComplexType(3, 30) },
      {new ComplexType(4, 40), new ComplexType(5, 50), new ComplexType(6, 60) }, {new ComplexType(7, 70), new ComplexType(8, 80), new ComplexType(9, 90) },
      {new ComplexType(10, 100), new ComplexType(11, 110), new ComplexType(12, 120) } };

  OGComplexMatrix getRow = new OGComplexMatrix(new double[] {7, 70, 8, 80, 9, 90 }, 1, 3);
  OGComplexMatrix getCol = new OGComplexMatrix(new double[] {2, 20, 5, 50, 8, 80, 11, 110 }, 4, 1);

  // sending in null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorNullPtrTest() {
    double[][] tmp = null;
    new OGComplexMatrix(tmp);
  }

  // sending in embedded null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorOKThenNullPtrTest() {
    double[][] tmp = new double[2][1];
    tmp[0] = new double[] {1 };
    tmp[1] = null;
    new OGComplexMatrix(tmp);
  }

  // sending in null ptr double[][] double[][] constructor 
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorNullPtrFirstTest() {
    double[][] tmp1 = null;
    double[][] tmp2 = new double[2][3];
    new OGComplexMatrix(tmp1, tmp2);
  }

  // sending in null ptr double[][] double[][] constructor 
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorNullPtrSecondTest() {
    double[][] tmp1 = new double[2][3];
    double[][] tmp2 = null;
    new OGComplexMatrix(tmp1, tmp2);
  }

  // sending in embedded null ptr double[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorOKThenNullPtrInFirstTest() {
    double[][] tmp1 = new double[2][];
    tmp1[0] = new double[] {1 };
    tmp1[1] = null;
    double[][] tmp2 = new double[2][1];
    new OGComplexMatrix(tmp1, tmp2);
  }

  // sending in embedded null ptr double[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorOKThenNullPtrInSecondTest() {
    double[][] tmp2 = new double[2][];
    double[][] tmp1 = new double[2][];
    tmp1[0] = new double[] {1 };
    tmp1[1] = null;
    new OGComplexMatrix(tmp1, tmp2);
  }

  // sending in null ptr double[] constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrConstructorNullPtrTest() {
    double[] tmp = null;
    new OGComplexMatrix(tmp, 1, 1);
  }

  // sending in null ptr double[] double [] int int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrDoublePtrConstructorNullPtrFirstTest() {
    double[] tmp1 = null;
    double[] tmp2 = new double[1];
    new OGComplexMatrix(tmp1, tmp2, 1, 1);
  }

  // sending in null ptr double[] double [] int int constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrDoublePtrConstructorNullPtrSecondTest() {
    double[] tmp1 = new double[1];
    double[] tmp2 = null;
    new OGComplexMatrix(tmp1, tmp2, 1, 1);
  }

  // sending in null ptr ComplexType[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testComplexTypePtrPtrConstructorNullPtrTest() {
    ComplexType[][] tmp = null;
    new OGComplexMatrix(tmp);
  }

  // sending in embedded null ptr ComplexType[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testComplexTypePtrPtrConstructorOKThenNullPtrTest() {
    ComplexType[][] tmp = new ComplexType[2][1];
    tmp[0] = new ComplexType[] {new ComplexType(1) };
    tmp[1] = null;
    new OGComplexMatrix(tmp);
  }

  // sending in null ptr ComplexType constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testComplexTypeConstructorNullPtrTest() {
    ComplexType tmp = new ComplexType(0);
    tmp = null;
    new OGComplexMatrix((ComplexType) tmp);
  }

  // sending in ragged[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrConstructorRaggedTest() {
    double[][] tmp = new double[2][];
    tmp[0] = new double[] {1 };
    tmp[1] = new double[] {1, 2, 3 };
    new OGComplexMatrix(tmp);
  }

  // sending in ragged[][] first double[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorRaggedFirstTest() {
    double[][] tmp1 = new double[2][];
    double[][] tmp2 = new double[2][2];
    tmp1[0] = new double[] {1 };
    tmp1[1] = new double[] {1, 2, 3 };
    new OGComplexMatrix(tmp1, tmp2);
  }

  // sending in ragged[][] second double[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrDoublePtrPtrConstructorRaggedSecondTest() {
    double[][] tmp1 = new double[2][2];
    double[][] tmp2 = new double[2][];
    tmp2[0] = new double[] {1 };
    tmp2[1] = new double[] {1, 2, 3 };
    new OGComplexMatrix(tmp1, tmp2);
  }
  
  // sending in ragged ComplexType[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testComplexTypePtrPtrConstructorRaggedTest() {
    ComplexType[][] tmp = new ComplexType[2][1];
    tmp[0] = new ComplexType[] {new ComplexType(1), new ComplexType(2) };
    tmp[1] = new ComplexType[] {new ComplexType(1) };
    new OGComplexMatrix(tmp);
  }  

  // sending in ok double[][] constructor
  @Test
  public void testDoublePtrPtrConstructorInternalDataTest() {
    OGComplexMatrix D = new OGComplexMatrix(realdata4x3);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertRowMajorDoublePointerToColumnMajorZeroInterleavedSinglePointer(realdata4x3)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // sending in ok double[][] double[][] constructor
  @Test
  public void testDoublePtrPtrDoublePtrPtrConstructorInternalDataTest() {
    OGComplexMatrix D = new OGComplexMatrix(realdata4x3, imagdata4x3);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertTwoRowMajorDoublePointerToColumnMajorInterleavedSinglePointer(realdata4x3, imagdata4x3)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  //sending in bad rows double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadRowsDataTest() {
    new OGComplexMatrix(realdata4x3unwound, -1, 3);
  }

  //sending in bad rows double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadColsDataTest() {
    new OGComplexMatrix(realdata4x3unwound, 3, -1);
  }

  //sending in bad data count double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorDataCountDataTest() {
    new OGComplexMatrix(realdata4x3unwound, 3, 17);
  }

  //sending in ok REAL double[] constructor
  @Test
  public void testDoublePtrConstructorRealDataOKTest() {
    OGComplexMatrix D = new OGComplexMatrix(realdata4x3unwound, 4, 3);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(realdata4x3unwound)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  //sending in ok REAL double[] constructor, interleaved data branch
  @Test
  public void testDoublePtrConstructorInterleavedDataOKTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertTwoSinglePointersToInterleavedSinglePointer(realdata4x3unwound, imagdata4x3unwound)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  @Test
  // sending in single double for a 1x1 element array
  public void testDoubleConstructorInternalDataTest() {
    OGComplexMatrix D = new OGComplexMatrix((double) 7.);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {7., 0 }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);
  }
  
  // sending in null ptr ComplexType constructor
  @Test
  public void testComplexTypeConstructorInternalDataTest() {
    ComplexType tmp = new ComplexType(7.0);
    OGComplexMatrix D = new OGComplexMatrix(tmp);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {7., 0 }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);

  }

  //sending in ok ComlpexType[][] constructor, interleaved data branch
  @Test
  public void testComplexTypePtrPtrConstructorDataOKTest() {
    OGComplexMatrix D = new OGComplexMatrix(cmplx4x3);
    assertTrue(D.getClass() == OGComplexMatrix.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertTwoSinglePointersToInterleavedSinglePointer(realdata4x3unwound, imagdata4x3unwound)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // test get rows
  @Test
  public void testGetRowsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.getNumberOfRows() == 4);
  }

  // test get cols
  @Test
  public void testGetColumnsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadRowIndicesTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getEntry(23, 1);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadColumnIndicesTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getEntry(1, 23);
  }

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        assertTrue(D.getEntry(i, j).getReal()==realdata4x3[i][j]);
        assertTrue(D.getEntry(i, j).getImag()==imagdata4x3[i][j]);
      }
    }
  }
 
  // test get full row neg index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullRowNegIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getFullRow(-1);
  }    
  
  // test get full row bad index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullRowBadIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getFullRow(23);
  }  
  
  // test get full row ok
  @Test
  public void testGetFullRowOkIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix row = D.getFullRow(2);
    assertTrue(row.equals(getRow));
  }    

  // test get full col neg index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullColNegIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getFullColumn(-1);
  }    
    
  
  // test get full col bad index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullColumnBadIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.getFullColumn(23);
  }  

  // test get full col ok
  @Test
  public void testGetFullColumnOkIndexTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix col = D.getFullColumn(1);
    assertTrue(col.equals(getCol));
  }    
  
  
  // test get No elements
  @Test
  public void testGetNoElementsTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.getNumberOfElements()==12);
  }  

  // test get data
  @Test
  public void testGetDataTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(Arrays.equals(D.getData(), interleaved4x3));
  }
  
  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObj() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNull() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertFalse(D.equals(null));
  }  
  
  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClass() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertFalse(D.equals(new Double(1.)));
  }  

  // test equals obj class ok, diff cols
  @Test
  public void testEqualsObjDifferentCols() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(7);    
    assertFalse(D.equals(Diff));
  }    
  
  // test equals obj class ok, same cols diff rows
  @Test
  public void testEqualsObjDifferentRows() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[][] {{1,2,3}});    
    assertFalse(D.equals(Diff));
  }  

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testEqualsObjDifferentData() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[]{999.00, 10.00, 4.00, 40.00, 7.00, 70.00, 10.00, 100.00, 2.00, 20.00, 5.00, 50.00, 8.00, 80.00, 11.00, 110.00, 3.00, 30.00, 6.00, 60.00, 9.00, 90.00, 12.00,
      120.00 },4,3);    
    assertFalse(D.equals(Diff));
  }    

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdentical() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[]{1.00, 10.00, 4.00, 40.00, 7.00, 70.00, 10.00, 100.00, 2.00, 20.00, 5.00, 50.00, 8.00, 80.00, 11.00, 110.00, 3.00, 30.00, 6.00, 60.00, 9.00, 90.00, 12.00,
        120.00 },4,3);    
    assertTrue(D.equals(Diff));
  }      
    
  // test equals obj points to obj
  @Test
  public void testFuzzyEqualsObjeqObj() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertTrue(D.fuzzyequals(D,10*D1MACH.four()));
  }

  // test equals obj not = null
  @Test
  public void testFuzzyEqualsObjNull() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertFalse(D.fuzzyequals(null,10*D1MACH.four()));
  }  
  
  // test equals obj class different
  @Test
  public void testFuzzyEqualsObjDifferentClass() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    assertFalse(D.fuzzyequals(new Double(1.),10*D1MACH.four()));
  }  

  // test equals obj class ok, diff cols
  @Test
  public void testFuzzyEqualsObjDifferentCols() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(7);    
    assertFalse(D.fuzzyequals(Diff,10*D1MACH.four()));
  }    
  
  // test equals obj class ok, same cols diff rows
  @Test
  public void testFuzzyEqualsObjDifferentRows() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[][] {{1,2,3}});    
    assertFalse(D.fuzzyequals(Diff,10*D1MACH.four()));
  }  

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testFuzzyEqualsObjDifferentData() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[]{999.00, 10.00, 4.00, 40.00, 7.00, 70.00, 10.00, 100.00, 2.00, 20.00, 5.00, 50.00, 8.00, 80.00, 11.00, 110.00, 3.00, 30.00, 6.00, 60.00, 9.00, 90.00, 12.00,
      120.00 },4,3);    
    assertFalse(D.fuzzyequals(Diff,10*D1MACH.four()));
  }    

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testFuzzyEqualsObjStructurallyIdentical() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    OGComplexMatrix Diff = new OGComplexMatrix(new double[]{1.00+9*D1MACH.four(), 10.00, 4.00, 40.00, 7.00, 70.00, 10.00, 100.00, 2.00, 20.00, 5.00, 50.00, 8.00, 80.00, 11.00, 110.00, 3.00, 30.00, 6.00, 60.00, 9.00, 90.00, 12.00,
        120.00 },4,3);    
    assertTrue(D.fuzzyequals(Diff,10*D1MACH.four()));
  }     
  
  // test hash code
  @Test
  public void testHashCodeTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.hashCode();
  }
  
  // test toString code
  @Test
  public void testToStringTest() {
    OGComplexMatrix D = new OGComplexMatrix(interleaved4x3, 4, 3);
    D.toString();
  }
  
}
