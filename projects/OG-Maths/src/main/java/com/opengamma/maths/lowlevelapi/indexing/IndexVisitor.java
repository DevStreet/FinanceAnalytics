/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Visitor for indexes
 */
public interface IndexVisitor {

  OGArray<? extends Number> visit(TwoDIndex idx);

  OGArray<? extends Number> visit(OneDIndex idx);

}
