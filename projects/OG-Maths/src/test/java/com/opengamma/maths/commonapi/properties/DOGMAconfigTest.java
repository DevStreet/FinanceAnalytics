/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.commonapi.properties;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionConfigProblem;

/**
 * Checks we can load the default properties and the sanity therein
 */
public class DOGMAconfigTest {

  private String pwd = "projects/OG-Maths/tests/unit/com/opengamma/maths/commonapi/properties/";

  @Test
  public void DefaultLoaderTest() {
    new DOGMAconfig();
  }

  @Test
  public void DefaultLoaderWithPathTest() {
    new DOGMAconfig("config/OG-Maths.properties");
  }

  @Test(expectedExceptions = MathsExceptionConfigProblem.class)
  public void DefaultLoaderBadPathTest() {
    new DOGMAconfig("badpath");
  }

  @Test(expectedExceptions = MathsExceptionConfigProblem.class)
  public void DefaultLoaderBadMaxValueConfigTest() {
    new DOGMAconfig(pwd + "badconfigMaxValueOfMissingexample.properties");
  }

  @Test(expectedExceptions = MathsExceptionConfigProblem.class)
  public void DefaultLoaderBadMinValueConfigTest() {
    new DOGMAconfig(pwd + "badconfigMinValueOfMissingexample.properties");
  }

  public void CheckOverrideOn_s_haltOnNaNOnFunctionEntryTest() {
    new DOGMAconfig();
    assertTrue(DOGMAconfig.getHaltOnNaNOnFunctionEntry());
  }

  @Test
  public void CheckOverrideOn_s_haltOnNaNOnFunctionExitTest() {
    new DOGMAconfig();
    assertTrue(DOGMAconfig.getHaltOnNaNOnFunctionExit());
  }

  @Test
  public void CheckOverrideOn_s_haltOnInfOnFunctionEntryTest() {
    new DOGMAconfig();
    assertTrue(DOGMAconfig.getHaltOnInfOnFunctionEntry());
  }

  @Test
  public void CheckOverrideOn_s_haltOnInfOnFunctionExitTest() {
    new DOGMAconfig();
    assertTrue(DOGMAconfig.getHaltOnInfOnFunctionExit());
  }

  @Test
  public void CheckOverrideOn_s_haltOnMaxValueTest() {
    new DOGMAconfig();
    assertTrue(DOGMAconfig.getHaltOnMaxValue());
    assertTrue(DOGMAconfig.getHaltOnMaxValueOf() == 1e20);
  }

  @Test
  public void CheckOverrideOn_s_haltOnMinValueTest() {
    new DOGMAconfig();
    assertTrue(DOGMAconfig.getHaltOnMinValue());
    assertTrue(DOGMAconfig.getHaltOnMinValueOf() == -1e20);
  }

}
