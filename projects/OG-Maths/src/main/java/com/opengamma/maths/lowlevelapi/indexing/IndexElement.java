/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * An element of an indexing operation
 */
public interface IndexElement {
  OGArray<? extends Number> accept(IndexVisitor visitor);
}
