/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGRealScalar;

/**
 * test copy OGRealScalar
 */
public class OGRealScalarCopyTest {

  @Test
  public void copyTest() {
    OGRealScalar foo = new OGRealScalar(1234);
    CopyOGRealScalar copy = new CopyOGRealScalar();
    assertTrue(foo.fuzzyequals(copy.eval(foo), 1e-14));

  }

}
