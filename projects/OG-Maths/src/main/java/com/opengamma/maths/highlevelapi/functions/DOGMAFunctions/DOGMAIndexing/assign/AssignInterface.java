/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIndexing.assign;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Interface for assignment operations
 */
public interface AssignInterface<S extends OGArray<? extends Number>, P extends OGArray<? extends Number>, Q extends OGArray<? extends Number>, T extends OGArray<? extends Number>> {

  OGArray<? extends Number> assign(S arr1, int[] rows, int[] columns, T arr2);

  OGArray<? extends Number> assign(S arr1, P rows, Q columns, T arr2);

  OGArray<? extends Number> assign(S arr1, Index idx, Q columns, T arr2);

  OGArray<? extends Number> assign(S arr1, P rows, Index idx, T arr2);
}
