/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.IZYTests;

import org.testng.annotations.Factory;

import com.opengamma.maths.lowlevelapi.exposedapi.IZY.backing;

/**
 * Test all the IZY backings
 */
public class IzyBackingsTest {
  
  // this method spawns a IzyBasicTest()s with various backings
  @Factory
  public Object[] createInstancesOfIzy() {
    Object[] obj = new Object[backing.values().length];
    int ptr = 0;
    for (backing b : backing.values()) {
      obj[ptr] = new IzyBasicTest(b);
      ptr++;
    }
    return obj;
  }

}
