/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAHelpers.genericconverters;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * 
 */
public final class OGDoubleArrayUpcast implements GenericUpcast<OGDoubleArray, OGDoubleArray> {
  private static OGDoubleArrayUpcast s_instance = new OGDoubleArrayUpcast();

  public static OGDoubleArrayUpcast getInstance() {
    return s_instance;
  }

  private OGDoubleArrayUpcast() {
  }

  @Override
  public OGDoubleArray from(OGDoubleArray array) {
    return array;
  }
}
