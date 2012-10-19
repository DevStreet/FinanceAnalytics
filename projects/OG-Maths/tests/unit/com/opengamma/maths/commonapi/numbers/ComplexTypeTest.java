/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.commonapi.numbers;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Tests the complex type
 */
public class ComplexTypeTest {

  ComplexType Complex = null;

  @Test
  public void testDoubleConstructorTest() {
    Complex = new ComplexType(3.0);
  }

  @Test
  public void testDoubleDoubleConstructorTest() {
    Complex = new ComplexType(3.0, 4.0);
  }
  
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testOrderedDoubleprecisionPtrTooShortConstructorTest() {
    Complex = new ComplexType(new double[] {3.0});
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void testOrderedDoubleprecisionPtrTooLongConstructorTest() {
    Complex = new ComplexType(new double[] {3.0, 4.0, 5.0});
  }    
  
  @Test
  public void testOrderedDoubleprecisionPtrConstructorTest() {
    Complex = new ComplexType(new double[] {3.0, 4.0});
  }
  
  @Test
  public void testGetRealTest() {
    Complex = new ComplexType(3.0, 4.0);
    assertTrue(Complex.getReal()==3.0);
  }

  @Test
  public void testGetImaginaryTest() {
    Complex = new ComplexType(3.0, 4.0);
    assertTrue(Complex.getImag()==4.0);
  }  
  
  
  // test complex values are as expected
  @Test
  public void testComplexIisITest() {
    Complex = ComplexType.I;
    assertTrue(Complex.getReal()==0.0);    
    assertTrue(Complex.getImag()==1.0);    
  }
  
  @Test
  public void testComplexnegIisnegITest() {
    Complex = ComplexType.NEGATIVE_I;
    assertTrue(Complex.getReal()==0.0);    
    assertTrue(Complex.getImag()==-1.0);    
  }
  
  @Test
  public void testComplexZeroisZeroTest() {
    Complex = ComplexType.ZERO;
    assertTrue(Complex.getReal()==0.0);    
    assertTrue(Complex.getImag()==0.0);    
  }

  
  @Test(expectedExceptions = MathsExceptionNotImplemented.class)
  public void testIntValueTest() {
    new ComplexType(3.0, 4.0).intValue();
  }
  
  @Test(expectedExceptions = MathsExceptionNotImplemented.class)
  public void testLongValueTest() {
    new ComplexType(3.0, 4.0).longValue();
  }
  
  @Test(expectedExceptions = MathsExceptionNotImplemented.class)
  public void testFloatValueTest() {
    new ComplexType(3.0, 4.0).floatValue();
  }
  
  @Test(expectedExceptions = MathsExceptionNotImplemented.class)
  public void testDoubleValueTest() {
    new ComplexType(3.0, 4.0).doubleValue();
  }  
  
  @Test
  public void testToStringTest() {
    new ComplexType(3.0, 4.0).toString();
    new ComplexType(3.0, -4.0).toString();
    new ComplexType(-3.0, 4.0).toString();
    new ComplexType(-3.0, -4.0).toString();
  }
  
  
  @Test
  public void testHashCodeTest() {
    new ComplexType(1).hashCode();    
  }
  
  // test equals obj points to obj
  @Test
  public void testEqualsObjeqObj() {
    ComplexType D = new ComplexType(4, 3);
    assertTrue(D.equals(D));
  }

  // test equals obj not = null
  @Test
  public void testEqualsObjNull() {
    ComplexType D = new ComplexType(4, 3);
    assertFalse(D.equals(null));
  }  
  
  // test equals obj class different
  @Test
  public void testEqualsObjDifferentClass() {
    ComplexType D = new ComplexType(4, 3);
    assertFalse(D.equals(new Double(1.)));
  }  

  // test equals obj class ok, different data
  @Test
  public void testEqualsObjDifferentData() {
    ComplexType D = new ComplexType(4, 3);
    ComplexType Diff = new ComplexType(1,2);    
    assertFalse(D.equals(Diff));
  }

  
  // test equals obj class ok, same data
  @Test
  public void testEqualsObjStructurallyIdentical() {
    ComplexType D = new ComplexType(4, 3);
    ComplexType Diff = new ComplexType(4,3);    
    assertTrue(D.equals(Diff));
  }
  

}
