/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

/**
 * Tests DLNREL
 */
public class DLNRELTest {

  double[] x = new double[] {-0.4e0, -0.35e0, -0.3e0, -0.25e0, -0.2e0, -0.15e0, -0.1e0, -0.05e0, 0e0, 0.05e0, 0.1e0, 0.15e0, 0.2e0, 0.25e0, 0.3e0, 0.35e0, 0.4e0 };

  double[] answer = new double[] {-0.51082562376599072, -0.43078291609245434, -0.35667494393873250, -0.28768207245178090, -0.22314355131420976, -0.16251892949777494, -0.10536051565782627,
      -5.12932943875505226e-002, 0.0000000000000000, 4.87901641694319932e-002, 9.53101798043248377e-002, 0.13976194237515871, 0.18232155679395470, 0.22314355131420976, 0.26236426446749106,
      0.30010459245033805, 0.33647223662121289 };

  private static double fp_limit = 1e-7; 
  
  @Test
  public void badInputTest() {

  }

  
  @Test
  public void numberRangeTest() {
    for (int i = 0; i < x.length; i++) {
      System.out.println("answer="+answer[i]+" . DLNREL.dlnrel(x[i])"+DLNREL.dlnrel(x[i]));
      assertTrue(Math.abs(DLNREL.dlnrel(x[i]) - answer[i]) < fp_limit);
    }
  }
}
