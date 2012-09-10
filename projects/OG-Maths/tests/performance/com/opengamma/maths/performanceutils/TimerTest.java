/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.performanceutils;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;

/**
 * tests the simple timer class
 */
public class TimerTest {

  @Test
  public void testValidStart() {
    Timer t = new Timer();
    t.startTimer();    
  }

  @Test
  public void testValidStop() {
    Timer t = new Timer();
    t.startTimer();
    t.stopTimer();
  }
  
  @Test
  public void testValidElapsed() {
    Timer t = new Timer();
    t.startTimer();
    t.elapsedTime();
    t.stopTimer();
  }  

  @Test
  public void testValidSplits() {
    Timer t = new Timer();
    t.startTimer();
    t.splitTime();    
    t.elapsedTime();
    t.splitTime();    
    t.stopTimer();
  } 
  
  @Test
  public void testValidTotal() {
    Timer t = new Timer();
    t.startTimer();
    t.splitTime();    
    t.elapsedTime();
    t.splitTime();    
    t.stopTimer();
    t.totalTime();
  }  

  @Test(expectedExceptions = MathsExceptionGeneric.class)
  public void testInvalidStart() {
    Timer t = new Timer();
    t.startTimer();
    t.startTimer();        
  }

  @Test(expectedExceptions = MathsExceptionGeneric.class)
  public void testInvalidStop() {
    Timer t = new Timer();
    t.stopTimer();
  }
  
  @Test(expectedExceptions = MathsExceptionGeneric.class)
  public void testInvalidElapsed() {
    Timer t = new Timer();
    t.elapsedTime();
  }  

  @Test(expectedExceptions = MathsExceptionGeneric.class)
  public void testInvalidSplits() {
    Timer t = new Timer();
    t.splitTime();    
  } 
  
  @Test(expectedExceptions = MathsExceptionGeneric.class)
  public void testInvalidTotal() {
    Timer t = new Timer();
    t.startTimer();
    t.totalTime();
  }    
  
}
