/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAHelpers.genericconverters;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * 
 */
public final class GenericUpcastMapHolder {
  private static GenericUpcastMapHolder s_instance = new GenericUpcastMapHolder();

  public static GenericUpcastMapHolder getInstance() {
    return s_instance;
  }

  private GenericUpcastMapHolder() {
  }

  public OGArray<? extends Number> upcast(OGArray<? extends Number> array) {
    if (array instanceof OGDiagonalMatrix) {
      return OGDiagonalMatrixUpcast.getInstance().from((OGDiagonalMatrix) array);
    } else if (array instanceof OGMatrix) {
      return OGMatrixUpcast.getInstance().from((OGMatrix) array);
    }
    throw new IllegalStateException();
  }
}

