/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.IZYTests;

import org.testng.annotations.Factory;

/**
 * Test all the IZY backings
 */
public class IzyBackingsTest {

  // this method spawns a IzyBasicTest
  @Factory
  public Object[] createInstancesOfIzy() {
    return new Object[] { new IzyBasicTest()};
  }

}
