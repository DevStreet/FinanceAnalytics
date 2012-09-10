/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.performanceutils;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Tests the Timer Results blob
 */
public class TimerResultsSetTest {

  @Test
  public void TestConstructor() {
   new TimerResultsSet(new int[] {1, 2, 3 }, new long[] {1, 2, 3 });
  }
  
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void TestConstructorLengthMismatch() {
   new TimerResultsSet(new int[] {1, 2, 3 }, new long[] {1, 2 });
  }  

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void TestConstructorNullArg1() {
   new TimerResultsSet(null, new long[] {1, 2 });
  }
  
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void TestConstructorNullArg2() {
    new TimerResultsSet(new int[] {1, 2, 3 }, null);
  }

  @Test
  public void testGetters() {
    int[] ns =new int[] {1, 2, 3 };
    long[] ts =new long[] {4, 5, 6 };    
   TimerResultsSet trs = new TimerResultsSet(ns,ts);
   assert(Arrays.equals(trs.getSizes(),ns));
   assert(Arrays.equals(trs.getTimes(),ts));   
  }  
  
}
