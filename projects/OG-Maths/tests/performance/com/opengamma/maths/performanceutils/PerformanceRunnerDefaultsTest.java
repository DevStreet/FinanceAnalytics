/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.performanceutils;

import org.testng.annotations.Test;

/**
 * Tests the performance runner defaults, basically code coverage, not a lot we can do
 */
public class PerformanceRunnerDefaultsTest {

  PerformanceRunnerDefaults pr = new PerformanceRunnerDefaults();

  @Test
  public void getWarmupRunsTest() {
    pr.getWarmupRuns();
  }

  @Test
  public void getTimedRunsTest() {
    pr.getTimedRuns();
  }
  
  @Test
  public void getCacheThrasherTest() {
    pr.getCacheThrasher();
  }  
  
}
