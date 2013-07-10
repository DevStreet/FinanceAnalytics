/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.lowlevelapi.exposedapi.SLATEC;
import com.opengamma.maths.nativewrappers.exceptions.OGNativeMathsWrapperInvalidArgumentException;

/**
 * Tests the DCSEVL class
 */
public class DCSEVLTest {

  @Test(expectedExceptions = OGNativeMathsWrapperInvalidArgumentException.class)
  public void nullTest() {
    SLATEC.getInstance().dcsevl(1, null, 5);
  }

  @Test(expectedExceptions = OGNativeMathsWrapperInvalidArgumentException.class)
  public void negTermsTest() {
    double[] cs = {1, 2, 3 };
    SLATEC.getInstance().dcsevl(1, cs, -1);
  }

  @Test(expectedExceptions = OGNativeMathsWrapperInvalidArgumentException.class)
  public void tooManyTermsTest() {
    double[] cs = {1, 2, 3 };
    SLATEC.getInstance().dcsevl(1, cs, 1001);
  }

  @Test
  public void outOfBoundsTest() {
    double[] cs = {1, 2, 3 };
    SLATEC.getInstance().dcsevl(1.01, cs, 10);
  }

  @Test
  public void outMoreTermsTheCoeffs() {
    double[] cs = {1, 2, 3 };
    SLATEC.getInstance().dcsevl(0.9, cs, 10);
  }

  @Test
  public void checkCall() {
    double[] cs = {1, 2, 3 };
    assertTrue(SLATEC.getInstance().dcsevl(0.9, cs, 3) == 4.16);
  }

}
