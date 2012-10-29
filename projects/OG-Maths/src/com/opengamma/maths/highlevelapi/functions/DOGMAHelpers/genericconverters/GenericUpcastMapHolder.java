/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAHelpers.genericconverters;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

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

  public OGArraySuper<? extends Number> upcast(OGArraySuper<? extends Number> array) {
    if (array instanceof OGDiagonalArray) {
      return OGDiagonalUpcast.getInstance().from((OGDiagonalArray) array);
    } else if (array instanceof OGDoubleArray) {
      return OGDoubleArrayUpcast.getInstance().from((OGDoubleArray) array);
    }
    throw new IllegalStateException();
  }
}

