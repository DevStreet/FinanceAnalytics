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
public class OGComplexScalarTest {
  OGComplexScalar defaultVal = new OGComplexScalar(1.2, 3.4);
  double dataForEqualsTests = 1.6;
  Double NULL = null;

  @Test
  public void constructFromNumberTest() {
    new OGComplexScalar(1.4);
    new OGComplexScalar(1);
  }

  @Test
  public void constructFromComplexNumberTest() {
    new OGComplexScalar(new ComplexType(1.4, 2.4));
    new OGComplexScalar(new ComplexType(1));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void constructFromNullNumberTest() {
    new OGComplexScalar(NULL);
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

  // test get entry ok
  @Test
  public void testGetEntryOKIndicesTest() {
    assertTrue(defaultVal.getEntry(0, 0).getReal() == 1.2d);
    assertTrue(defaultVal.getEntry(0, 0).getImag() == 3.4d);    
  }

  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObjTest() {
    OGComplexScalar D = new OGComplexScalar(dataForEqualsTests);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNullTest() {
    OGComplexScalar D = new OGComplexScalar(dataForEqualsTests);
    assertFalse(D.equals(null));
  }

  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClassTest() {
    OGComplexScalar D = new OGComplexScalar(dataForEqualsTests);
    assertFalse(D.equals(new Double(1.)));
  }

  // test equals obj class ok, same cols same rows Different data
  @Test
  public void testEqualsObjDataDifferentTest() {
    OGComplexScalar D = new OGComplexScalar(dataForEqualsTests);
    OGComplexScalar Diff = new OGComplexScalar(1.7d);
    assertFalse(D.equals(Diff));
  }

  // test equals obj class ok, same cols same rows same data
  @Test
  public void testEqualsObjStructurallyIdenticalTest() {
    OGComplexScalar D = defaultVal;
    OGComplexScalar Diff = new OGComplexScalar(1.2d,3.4d);
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
    assertTrue(defaultVal.getData()[0] == 1.2d);
    assertTrue(defaultVal.getData()[1] == 3.4d);
  }

}
