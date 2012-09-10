/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.performanceutils;

/**
 * Requirements for a performance runner
 */
public interface PerformanceRunner {
  double warmUpJIT();  
  TimerResultsSet runTest();
}
