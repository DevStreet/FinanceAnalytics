/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.commonapi.numbers.ComplexType;

/**
 * Tests the real scalar class
 */
public class OGRealScalarTest {
  OGRealScalar defaultVal = new OGRealScalar(1.4);
  double dataForEqualsTests = 1.6;
  Double NULL = null;

  @Test
  public void constructFromNumberTest() {
    new OGRealScalar(1.4);
    new OGRealScalar(1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void constructFromIllegalNumberTest() {
    new OGRealScalar(new ComplexType(0));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void constructFromNullNumberTest() {
    new OGRealScalar(NULL);
  }

  @Test
  public void getNumberOfRowsTest() {
    assertTrue(defaultVal.getNumberOfRows() == 1);
  }

  @Test
  public void getNumberOfColumnsTest() {
    assertTrue(defaultVal.getNumberOfColumns() == 1);
  }

  // test get entry bad index count
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadIndexCountTest() {
    defaultVal.getEntry(1, 2, 3);
  }

  // test get entry bad row index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadRowIndicesTest() {
    defaultVal.getEntry(23, 0);
    defaultVal.getEntry(-1, 0);
  }

  // test get entry bad col index
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testGetEntryBadColumnIndicesTest() {
    defaultVal.getEntry(0, 23);
    defaultVal.getEntry(0, -1);
  }
  
  // test get col neg index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetColNegIndexTest() {
    defaultVal.getColumn(-1);
  }    
    
  
  // test get col bad index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetColumnBadIndexTest() {
    defaultVal.getColumn(23);
  }  

  // test get col ok
  @Test
  public void testGetColumnOkIndexTest() {
    OGRealScalar getCol = new OGRealScalar(1.4);
    OGArray<? extends Number> col = defaultVal.getColumn(0);
    assertTrue(col.equals(getCol));
  } 
  
  // test get row neg index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetRowNegIndexTest() {
    defaultVal.getRow(-1);
  }    
    
  
  // test get row bad index
  @Test(expectedExceptions=MathsExceptionIllegalArgument.class)
  public void testGetRowBadIndexTest() {
    defaultVal.getRow(23);
  }  

  // test get row ok
  @Test
  public void testGetRowOkIndexTest() {
    OGRealScalar getRow = new OGRealScalar(1.4);
    OGArray<? extends Number> row = defaultVal.getRow(0);
    assertTrue(row.equals(getRow));
  } 

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    assertTrue(defaultVal.getEntry(0, 0).doubleValue() == 1.4d);
  }

  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObjTest() {
    OGRealScalar D = new OGRealScalar(dataForEqualsTests);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNullTest() {
    OGRealScalar D = new OGRealScalar(dataForEqualsTests);
    assertFalse(D.equals(null));
  }

  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClassTest() {
    OGRealScalar D = new OGRealScalar(dataForEqualsTests);
    assertFalse(D.equals(new Double(1.)));
  }

  // test equals obj class ok, same cols same rows Different data
  @Test
  public void testEqualsObjDataDifferentTest() {
    OGRealScalar D = new OGRealScalar(dataForEqualsTests);
    OGRealScalar Diff = new OGRealScalar(1.7d);
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdenticalTest() {
    OGRealScalar D = new OGRealScalar(dataForEqualsTests);
    OGRealScalar Diff = new OGRealScalar(1.6d);
    assertTrue(D.equals(Diff));
  }

  @Test
  public void testHashCodeTest() {
    defaultVal.hashCode();
  }

  @Test
  public void testToStringTest() {
    defaultVal.toString();
  }

  @Test
  public void testGetDataTest() {
    assertTrue(defaultVal.getData()[0] == 1.4d);
  }

}
