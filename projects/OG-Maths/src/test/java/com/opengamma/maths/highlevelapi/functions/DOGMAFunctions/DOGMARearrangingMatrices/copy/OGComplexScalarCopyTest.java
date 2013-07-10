/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGComplexScalar;

/**
 * test copy OGComplexScalar
 */
public class OGComplexScalarCopyTest {

  @Test
  public void copyTest() {
    OGComplexScalar foo = new OGComplexScalar(1234,5678);
    CopyOGComplexScalar copy = new CopyOGComplexScalar();
    assertTrue(foo.fuzzyequals(copy.eval(foo), 1e-14));

  }

}
