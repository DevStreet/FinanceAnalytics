/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.interfaces.DOGMAArithmeticAPI;
import com.opengamma.maths.highlevelapi.functions.interfaces.individialfunctions.PlusAndMinus;

/**
 * Basic Arithmetic 
 */
public class DOGMAArithmetic implements DOGMAArithmeticAPI {
  private static PlusAndMinus s_localplusandminus = new PlusAndMinus();
  
  public OGDoubleArray plus(OGDoubleArray... array) {
    return s_localplusandminus.plus(array);
  }

  @Override
  public OGDoubleArray plus(OGDoubleArray array1, OGDoubleArray array2) {
    return s_localplusandminus.plus(array1, array2);
  }
  
  @Override
  public OGDoubleArray plus(OGDoubleArray array1, OGSparseArray array2) {
    return s_localplusandminus.plus(array1, array2);
  }

  @Override
  public OGDoubleArray plus(OGSparseArray array1, OGDoubleArray array2) {
    return s_localplusandminus.plus(array1, array2);
  }

  @Override
  public OGSparseArray plus(OGSparseArray array1, OGSparseArray array2) {
    return s_localplusandminus.plus(array1, array2);
  }

  @Override
  public OGDoubleArray minus(OGDoubleArray array1, OGDoubleArray array2) {
    return s_localplusandminus.plus(array1, array2);
  }

  @Override
  public OGDoubleArray minus(OGDoubleArray array1, OGSparseArray array2) {
    return s_localplusandminus.plus(array1, array2);
  }

  @Override
  public OGDoubleArray minus(OGSparseArray array1, OGDoubleArray array2) {
    return s_localplusandminus.plus(array1, array2);
  }

  @Override
  public OGSparseArray minus(OGSparseArray array1, OGSparseArray array2) {
    return s_localplusandminus.plus(array1, array2);
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

}
