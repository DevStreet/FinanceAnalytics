/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;

/**
 * Test general index
 */
@Test
public class GeneralIndexTest {

  @Test
  public void testArrayConstruct() {
    int[] data = new int[] {1, 2, 3, 4, 5 };
    GeneralIndex tmp = new GeneralIndex(data);
    assertTrue(Arrays.equals(tmp.getValues(), data));
  }

  @Test
  public void testSingleConstruct() {
    GeneralIndex tmp = new GeneralIndex(10);
    assertTrue(Arrays.equals(tmp.getValues(), new int[] {10 }));
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void testNullArrayConstruct() {
    new GeneralIndex(null);
  }

  @Test(expectedExceptions = MathsExceptionGeneric.class)
  public void testBadArrayConstruct() {
    new GeneralIndex(new int[] {1, -10, 3, 4, 5 });
  }
  
  @Test
  public void testLinearise() {
    int[] data = new int[] {1, 20, 3, 40, 5 };
    GeneralIndex tmp = new GeneralIndex(data);
    tmp.linearise(10);
    for (int i = 0; i < data.length; i ++) {
      assertTrue(data[i] == tmp.linearise(10)[i]);
    }
  }

  @Test
  public void testEquals() {
    int[] data = new int[] {1, 2, 3, 4, 5 };
    GeneralIndex expected = new GeneralIndex(data);
    assertTrue(expected.equals(expected));
    assertFalse(expected.equals(null));
    assertFalse(expected.equals(new Object()));
    GeneralIndex tmp;
    tmp = new GeneralIndex(new int[] {1, 10, 3, 4, 5 });
    assertFalse(expected.equals(tmp));
    tmp = new GeneralIndex(data);
    assertTrue(expected.equals(tmp));
  }

}
