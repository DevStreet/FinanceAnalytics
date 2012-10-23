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

/**
 * Tests the OGIndexArray Class
 */
public class OGIndexTest {

  int[][] data4x3 = new int[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }, {10, 11, 12 } };
  int[] data4x3unwound = new int[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  OGIndexArray getRow = new OGIndexArray(new int[] {7,8,9}, 1, 3);
  OGIndexArray getCol = new OGIndexArray(new int[] {2,5,8,11}, 4, 1);
  
  // sending in null ptr int[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testintPtrPtrConstructorNullPtrTest() {
    int[][] tmp = null;
    new OGIndexArray(tmp);
  }

  // sending in embedded null ptr int[][] constructor
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testintPtrPtrConstructorOKThenNullPtrTest() {
    int[][] tmp = new int[2][1];
    tmp[0] = new int[] {1 };
    tmp[1] = null;
    new OGIndexArray(tmp);
  }

  // sending in null ptr int[] constructor  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testintPtrConstructorNullPtrTest() {
    int[] tmp = null;
    new OGIndexArray(tmp, 1, 1);
  }

  // sending in ragged[][] int[][] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testintPtrPtrConstructorRaggedTest() {
    int[][] tmp = new int[2][];
    tmp[0] = new int[] {1 };
    tmp[1] = new int[] {1, 2, 3 };
    new OGIndexArray(tmp);
  }

  // sending in ok int[][] constructor
  @Test
  public void testintPtrPtrConstructorInternalDataTest() {
    OGIndexArray D = new OGIndexArray(data4x3);
    assertTrue(D.getClass() == OGIndexArray.class);
    assertTrue(Arrays.equals(D.getData(), data4x3unwound));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  //sending in bad rows int[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testintPtrConstructorBadRowsDataTest() {
    new OGIndexArray(data4x3unwound, -1, 3);
  }

  //sending in bad rows int[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testintPtrConstructorBadColsDataTest() {
    new OGIndexArray(data4x3unwound, 3, -1);
  }

  //sending in bad data count int[] constructor
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testintPtrConstructorDataCountDataTest() {
    new OGIndexArray(data4x3unwound, 3, 17);
  }

  // sending in ok int[] constructor
  @Test
  public void testintPtrConstructorInternalDataTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    assertTrue(D.getClass() == OGIndexArray.class);
    assertTrue(Arrays.equals(D.getData(), data4x3unwound));
    assertTrue(D.getNumberOfRows() == 4);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // sending in single int for a 1x1 element array
  public void testintConstructorInternalDataTest() {
    OGIndexArray D = new OGIndexArray(7);
    assertTrue(D.getClass() == OGIndexArray.class);
    assertTrue(Arrays.equals(D.getData(), new int[] {7}));
    assertTrue(D.getNumberOfRows() == 1);
    assertTrue(D.getNumberOfColumns() == 1);
  }

  // test get rows
  @Test
  public void testGetRowsTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfRows() == 4);
  }

  // test get cols
  @Test
  public void testGetColumnsTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfColumns() == 3);
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    D.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadRowIndicesTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    D.getEntry(23, 1);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadColumnIndicesTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    D.getEntry(1, 23);
  }

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    for (int i = 0; i < D.getNumberOfRows(); i++) {
      for (int j = 0; j < D.getNumberOfColumns(); j++) {
        assertTrue(D.getEntry(i, j) == data4x3[i][j]);
      }
    }
  }

  // test get full row neg index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullRowNegIndexTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    D.getFullRow(-1);
  }    
  
  // test get full row bad index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullRowBadIndexTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    D.getFullRow(23);
  }  
  
  // test get full row ok
  @Test
  public void testGetFullRowOkIndexTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    OGIndexArray row = D.getFullRow(2);
    assertTrue(row.equals(getRow));
  }    

  // test get full col neg index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullColNegIndexTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    D.getFullColumn(-1);
  }    
    
  
  // test get full col bad index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetFullColumnBadIndexTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    D.getFullColumn(23);
  }  

  // test get full col ok
  @Test
  public void testGetFullColumnOkIndexTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    OGIndexArray col = D.getFullColumn(1);
    assertTrue(col.equals(getCol));
  }  
  
  // test get No elements
  @Test
  public void testGetNoElementsTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    assertTrue(D.getNumberOfElements()==12);
  }  

  // test get data
  @Test
  public void testGetDataTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    assertTrue(Arrays.equals(D.getData(), data4x3unwound));
  }

  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObj() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNull() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    assertFalse(D.equals(null));
  }  
  
  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClass() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    assertFalse(D.equals(new OGDoubleArray(1)));
  }  

  // test equals obj class ok, diff cols
  @Test
  public void testEqualsObjDifferentCols() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    OGIndexArray Diff = new OGIndexArray(7);    
    assertFalse(D.equals(Diff));
  }    
  
  // test equals obj class ok, same cols diff rows
  @Test
  public void testEqualsObjDifferentRows() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    OGIndexArray Diff = new OGIndexArray(new int[][] {{1,2,3}});    
    assertFalse(D.equals(Diff));
  }  

  // test equals obj class ok, same cols same rows different data
  @Test
  public void testEqualsObjDifferentData() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    OGIndexArray Diff = new OGIndexArray(new int[][] { {999, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }, {10, 11, 12 } });    
    assertFalse(D.equals(Diff));
  }    

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdentical() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    OGIndexArray Diff = new OGIndexArray(new int[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }, {10, 11, 12 } });     
    assertTrue(D.equals(Diff));
  }      
  
  // test hash code
  @Test
  public void testHashCodeTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    D.hashCode();
  }
  
  // test toString code
  @Test
  public void testToStringTest() {
    OGIndexArray D = new OGIndexArray(data4x3unwound, 4, 3);
    D.toString();
  }
  
}

