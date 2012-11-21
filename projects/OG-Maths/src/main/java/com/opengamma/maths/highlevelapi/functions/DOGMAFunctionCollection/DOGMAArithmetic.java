/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy.Copy;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMAArithmeticAPI;

/**
 * Basic Arithmetic 
 */
public class DOGMAArithmetic implements DOGMAArithmeticAPI {
  private final Copy _copy = new Copy();

  //  @Override
  //  public OGArray<? extends Number> plus(OGArray<? extends Number> array1, int aNumber) {
  //    OGArray<? extends Number> ret = null;
  //    if (DOGMAconfig.getHaltOnNaNOnFunctionEntry()) {
  //      Catchers.catchNaN(array1);
  //    }
  //    if (DOGMAconfig.getHaltOnInfOnFunctionEntry()) {
  //      Catchers.catchInf(array1);
  //    }
  //    ret = plus(array1, new OGMatrix(aNumber));
  //    if (DOGMAconfig.getHaltOnNaNOnFunctionExit()) {
  //      Catchers.catchNaN(ret);
  //    }
  //    if (DOGMAconfig.getHaltOnInfOnFunctionExit()) {
  //      Catchers.catchInf(ret);
  //    }
  //    return ret;
  //  }

  /* SUBTRACT */

  @Override
  public OGArray<? extends Number> minus(OGArray<? extends Number> array1, double aNumber) {
    return minus(array1, new OGMatrix(aNumber));
  }

  @Override
  public OGArray<? extends Number> minus(OGArray<? extends Number> array1, int aNumber) {
    return minus(array1, new OGMatrix(aNumber));
  }

  /* LDIVIDE */

  @Override
  public OGArray<? extends Number> ldivide(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    throw new MathsExceptionNotImplemented("ldivide not implemented yet");
  }

  /* MLDIVIDE */

  @Override
  public OGArray<? extends Number> mldivide(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    throw new MathsExceptionNotImplemented("mldivide not implemented yet");
  }

 
  /* MRDIVIDE */

  @Override
  public OGArray<? extends Number> mrdivide(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    throw new MathsExceptionNotImplemented("mrdivide not implemented yet");
  }

  /* POWER */
  @Override
  public OGArray<? extends Number> power(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    throw new MathsExceptionNotImplemented("power not implemented yet");
  }

  /* MPOWER */
  @Override
  public OGArray<? extends Number> mpower(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    throw new MathsExceptionNotImplemented("mpower not implemented yet");
  }

  @Override
  public OGArray<? extends Number> minus(OGArray<? extends Number>... array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> minus(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return null;
  }

}
