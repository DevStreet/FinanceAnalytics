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
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.ogblas.auxiliary.D1MACH;

/**
 * Tests the OGDoubleArray Class
 */
public class OGDoubleArrayTest {

  double[][] data4x3 = new double[][] { {1.00, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } };
  double[] data4x3unwound = new double[] {1.00, 4.00, 7.00, 10.00, 2.00, 5.00, 8.00, 11.00, 3.00, 6.00, 9.00, 12.00 };
  OGDoubleArray getRow = new OGDoubleArray(new double[] {7,8,9}, 1, 3);
  OGDoubleArray getCol = new OGDoubleArray(new double[] {2,5,8,11}, 4, 1);
  
  // sending in null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorNullPtrTest() {
    double[][] tmp = null;
    new OGDoubleArray(tmp);
  }

  // sending in embedded null ptr double[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrPtrConstructorOKThenNullPtrTest() {
    double[][] tmp = new double[2][1];
    tmp[0] = new double[] {1 };
    tmp[1] = null;
    new OGDoubleArray(tmp);
  }

  // sending in null ptr double[] constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testDoublePtrConstructorNullPtrTest() {
    double[] tmp = null;
    new OGDoubleArray(tmp, 1, 1);
  }

  // sending in ragged[][] double[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrPtrConstructorRaggedTest() {
    double[][] tmp = new double[2][];
    tmp[0] = new double[] {1 };
    tmp[1] = new double[] {1, 2, 3 };
    new OGDoubleArray(tmp);
  }

  // sending in ok double[][] constructor
  @Test
  public void testDoublePtrPtrConstructorInternalDataTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3);
    assertTrue(D.getClass() == OGDoubleArray.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertRowMajorDoublePointerToColumnMajorSinglePointer(data4x3)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  //sending in bad rows double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadRowsDataTest() {
    new OGDoubleArray(data4x3unwound, -1, 3);
  }

  //sending in bad rows double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorBadColsDataTest() {
    new OGDoubleArray(data4x3unwound, 3, -1);
  }

  //sending in bad data count double[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testDoublePtrConstructorDataCountDataTest() {
    new OGDoubleArray(data4x3unwound, 3, 17);
  }

  // sending in ok double[] constructor
  @Test
  public void testDoublePtrConstructorInternalDataTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    assertTrue(D.getClass() == OGDoubleArray.class);
    assertTrue(Arrays.equals(D.getData(), DenseMemoryManipulation.convertRowMajorDoublePointerToColumnMajorSinglePointer(data4x3)));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // sending in single double for a 1x1 element array
  public void testDoubleConstructorInternalDataTest() {
    OGDoubleArray D = new OGDoubleArray(7.);
    assertTrue(D.getClass() == OGDoubleArray.class);
    assertTrue(Arrays.equals(D.getData(), new double[] {7. }));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);
  }

  // test get rows
  @Test
  public void testGetRowsTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfRows() == 4);
  }

  // test get cols
  @Test
  public void testGetColumnsTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    D.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadRowIndicesTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    D.getEntry(23, 1);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadColumnIndicesTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    D.getEntry(1, 23);
  }

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        assertTrue(D.getEntry(i, j) == data4x3[i][j]);
      }
    }
  }

  // test get full row neg index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullRowNegIndexTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    D.getFullRow(-1);
  }    
  
  // test get full row bad index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullRowBadIndexTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    D.getFullRow(23);
  }  
  
  // test get full row ok
  @Test
  public void testGetFullRowOkIndexTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    OGDoubleArray row = D.getFullRow(2);
    assertTrue(row.equals(getRow));
  }    

  // test get full col neg index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullColNegIndexTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    D.getFullColumn(-1);
  }    
    
  
  // test get full col bad index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullColumnBadIndexTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    D.getFullColumn(23);
  }  

  // test get full col ok
  @Test
  public void testGetFullColumnOkIndexTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    OGDoubleArray col = D.getFullColumn(1);
    assertTrue(col.equals(getCol));
  }  
  
  // test get No elements
  @Test
  public void testGetNoElementsTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfElements()==12);
  }  

  // test get data
  @Test
  public void testGetDataTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    assertTrue(Arrays.equals(D.getData(), data4x3unwound));
  }

  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObj() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNull() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    assertFalse(D.equals(null));
  }  
  
  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClass() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    assertFalse(D.equals(new Double(1.)));
  }  

  // test equals obj class ok, diff cols
  @Test
  public void testEqualsObjDifferentCols() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    OGDoubleArray Diff = new OGDoubleArray(7);    
    assertFalse(D.equals(Diff));
  }    
  
  // test equals obj class ok, same cols diff rows
  @Test
  public void testEqualsObjDifferentRows() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    OGDoubleArray Diff = new OGDoubleArray(new double[][] {{1,2,3,4}});    
    assertFalse(D.equals(Diff));
  }  

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testEqualsObjDifferentData() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    OGDoubleArray Diff = new OGDoubleArray(new double[][] { {999, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } });    
    assertFalse(D.equals(Diff));
  }    

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdentical() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    OGDoubleArray Diff = new OGDoubleArray(new double[][] { {1.00, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } });    
    assertTrue(D.equals(Diff));
  }      
  
  // test fuzzy equals obj points to obj
  @Test
  public void testFuzzyEqualsObjeqObj() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    assertTrue(D.fuzzyequals(D,10*D1MACH.four()));
  }

  // test fuzzy equals obj not = null
  @Test
  public void testFuzzyEqualsObjNull() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    assertFalse(D.fuzzyequals(null,10*D1MACH.four()));
  }  
  
  // test fuzzy equals obj class different
  @Test
  public void testFuzzyEqualsObjDifferentClass() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    assertFalse(D.fuzzyequals(new Double(1.),10*D1MACH.four()));
  }  

  // test fuzzy equals obj class ok, diff cols
  @Test
  public void testFuzzyEqualsObjDifferentCols() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    OGDoubleArray Diff = new OGDoubleArray(7);    
    assertFalse(D.fuzzyequals(Diff,10*D1MACH.four()));
  }    
  
  // test fuzzy equals obj class ok, same cols diff rows
  @Test
  public void testFuzzyEqualsObjDifferentRows() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    OGDoubleArray Diff = new OGDoubleArray(new double[][] {{1,2,3,4}});    
    assertFalse(D.fuzzyequals(Diff,10*D1MACH.four()));
  }  

  // test fuzzy equals obj class ok, same cols same rows different data
  @Test
  public void testFuzzyEqualsObjDifferentData() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    OGDoubleArray Diff = new OGDoubleArray(new double[][] { {999, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } });    
    assertFalse(D.fuzzyequals(Diff,10*D1MACH.four()));
  }    

  // test fuzzy equals obj class ok, same cols same rows same data
  @Test
  public void testFuzzyEqualsObjStructurallyIdentical() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    OGDoubleArray Same = new OGDoubleArray(new double[][] { {1.00+9*D1MACH.four(), 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } });    
    assertTrue(D.fuzzyequals(Same,10*D1MACH.four()));
  }   
  
  // test hash code
  @Test
  public void testHashCodeTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    D.hashCode();
  }
  
  // test hash code
  @Test
  public void testToStringTest() {
    OGDoubleArray D = new OGDoubleArray(data4x3unwound, 4, 3);
    D.toString();
  }
  
}

