/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.interfaces.DOGMAArithmeticAPI;
import com.opengamma.maths.highlevelapi.functions.interfaces.individialfunctions.PlusAndMinus;

/**
 * Basic Arithmetic 
 */
public class DOGMAArithmetic implements DOGMAArithmeticAPI {
  private final PlusAndMinus plusMinus = new PlusAndMinus();

  @Override
  public OGArraySuper<Number> plus(OGArraySuper<Number>... array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> plus(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return plusMinus.plus(array1, array2);
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
   * @param answer2
   * @param d
   * @return
   */
  public OGArraySuper<Number> plus(OGArraySuper<Number> array1, double aNumber) {
    return plus(array1, new OGDoubleArray(aNumber));
  }  


}
