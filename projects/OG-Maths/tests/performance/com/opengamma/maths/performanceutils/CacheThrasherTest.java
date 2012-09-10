/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.performanceutils;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Tests the cache thrasher, rather a hard thing to do!
 */
public class CacheThrasherTest {

  @Test
  public void defaultConstructorTest() {
    new CacheThrasher();
  }
  
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void badL1Test(){
    new CacheThrasher(-1,1,1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void badL2Test(){
    new CacheThrasher(1,-1,1);
  }  

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void badL3Test(){
    new CacheThrasher(1,1,-1);
  }    

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void badHierarchyTest(){
    new CacheThrasher(3,2,1);
  }      
  
  @Test
  public void getPointlessVarTest()
  {
    assert (new CacheThrasher().getpointlessVarToStopJIT() == 0);
  }
  
  @Test
  // this is an interesting test, we time the thrashes and check, odd thing to be doing in a virtual world
  public void thrashHierarchyTest() {
    CacheThrasher ct = new CacheThrasher();
    Timer t = new Timer();
    t.startTimer();
    ct.thrashL1();
    long L1time=t.elapsedTime();
    ct.thrashL2();
    long L2time=t.elapsedTime();
    ct.thrashL3(); 
    long L3time=t.elapsedTime();
    t.stopTimer();
    assert(L1time < L2time && L2time < L3time);
  }
}
