/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.PlusAndMinus;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy.Copy;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMAArithmeticAPI;

/**
 * Basic Arithmetic 
 */
public class DOGMAArithmetic implements DOGMAArithmeticAPI {
  private final PlusAndMinus _plusMinus = new PlusAndMinus();
  private final Copy _copy = new Copy();

  @Override
  public OGArraySuper<Number> plus(OGArraySuper<Number>... array) {
    OGArraySuper<Number> tmp = _copy.copy(array[0]);
    for (int i = 1; i < array.length; i++) {
      tmp = _plusMinus.plus(tmp, array[i]);
    }
    return tmp;
  }

  @Override
  public OGArraySuper<Number> plus(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return _plusMinus.plus(array1, array2);
  }

  @Override
  public OGArraySuper<Number> minus(OGArraySuper<Number>... array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> minus(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return null;
  }

  @Override
  public OGDoubleArray ldivide(OGDoubleArray array1, OGDoubleArray array2) {
    return null;
  }

  @Override
  public OGDoubleArray mldivide(OGDoubleArray array1, OGDoubleArray array2) {
    return null;
  }

  @Override
  public OGDoubleArray rdivide(OGDoubleArray array1, OGDoubleArray array2) {
    return null;
  }

  @Override
  public OGDoubleArray mrdivide(OGDoubleArray matrixA, OGDoubleArray vectorb) {
    return null;
  }

  @Override
  public OGDoubleArray times(OGDoubleArray... array) {
    return null;
  }

  @Override
  public OGDoubleArray mtimes(OGDoubleArray... array) {
    return null;
  }

  @Override
  public OGDoubleArray power(OGDoubleArray array1, OGDoubleArray array2) {
    return null;
  }

  @Override
  public OGDoubleArray mpower(OGDoubleArray array1, OGDoubleArray array2) {
    return null;
  }

  @Override
  public OGDoubleArray tranpose(OGDoubleArray array) {
    return null;
  }

  /**
   * Adds a double to an array
   * @param array1 the array to add to
   * @param aNumber the double
   * @return the array plus element-wise a double
   */
  public OGArraySuper<Number> plus(OGArraySuper<Number> array1, double aNumber) {
    return plus(array1, new OGDoubleArray(aNumber));
  }

}
