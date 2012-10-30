/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAHelpers.genericconverters;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * 
 */
public final class OGMatrixUpcast implements GenericUpcast<OGMatrix, OGMatrix> {
  private static OGMatrixUpcast s_instance = new OGMatrixUpcast();

  public static OGMatrixUpcast getInstance() {
    return s_instance;
  }

  private OGMatrixUpcast() {
  }

  @Override
  public OGMatrix from(OGMatrix array) {
    return array;
  }
}
