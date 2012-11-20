/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * Converts an OGMatrix to an OGComplexMatrix
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGMatrixToOGComplexMatrix implements Converter<OGMatrix> {
  @Override
  public OGArray<? extends Number> convert(OGMatrix array1) {
    double[] data = array1.getData();
    double[] tmp = DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(data);
    return new OGComplexMatrix(tmp, array1.getNumberOfRows(), array1.getNumberOfColumns());
  }
}
