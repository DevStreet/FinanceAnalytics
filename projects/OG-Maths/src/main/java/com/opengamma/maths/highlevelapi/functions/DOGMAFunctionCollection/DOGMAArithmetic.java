/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.commonapi.properties.DOGMAconfig;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.PlusAndMinus;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.Rdivide;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.Times;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.Transpose;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy.Copy;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMAArithmeticAPI;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Basic Arithmetic 
 */
public class DOGMAArithmetic implements DOGMAArithmeticAPI {
  private final PlusAndMinus _plusMinus = new PlusAndMinus();
  private final Copy _copy = new Copy();
  private final Times _times = new Times();
  private Rdivide _rdivide = new Rdivide();
  private Transpose _transpose = new Transpose();

  /* ADD */
  @Override
  public OGArray<? extends Number> plus(OGArray<? extends Number>... array) {
    if (DOGMAconfig.getHaltOnNaNOnFunctionEntry()) {
      Catchers.catchNaN(array[0]);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionEntry()) {
      Catchers.catchInf(array[0]);
    }
    OGArray<? extends Number> tmp = _copy.copy(array[0]);
    for (int i = 1; i < array.length; i++) {
      if (DOGMAconfig.getHaltOnNaNOnFunctionEntry()) {
        Catchers.catchNaN(array[i]);
      }
      if (DOGMAconfig.getHaltOnInfOnFunctionEntry()) {
        Catchers.catchInf(array[i]);
      }
      tmp = _plusMinus.plus(tmp, array[i]);
      if (DOGMAconfig.getHaltOnNaNOnFunctionExit()) {
        Catchers.catchNaN(tmp);
      }
      if (DOGMAconfig.getHaltOnInfOnFunctionExit()) {
        Catchers.catchInf(tmp);
      }
    }
    return tmp;
  }

  @Override
  public OGArray<? extends Number> plus(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    OGArray<? extends Number> ret = null;
    if (DOGMAconfig.getHaltOnNaNOnFunctionEntry()) {
      Catchers.catchNaN(array1);
      Catchers.catchNaN(array2);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionEntry()) {
      Catchers.catchInf(array1);
      Catchers.catchInf(array2);
    }
    ret = _plusMinus.plus(array1, array2);
    if (DOGMAconfig.getHaltOnNaNOnFunctionExit()) {
      Catchers.catchNaN(ret);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionExit()) {
      Catchers.catchInf(ret);
    }
    return ret;
  }

  @Override
  public OGArray<? extends Number> plus(OGArray<? extends Number> array1, double aNumber) {
    OGArray<? extends Number> ret = null;
    if (DOGMAconfig.getHaltOnNaNOnFunctionEntry()) {
      Catchers.catchNaN(array1);
      Catchers.catchNaN(aNumber);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionEntry()) {
      Catchers.catchInf(array1);
      Catchers.catchInf(aNumber);
    }
    ret = plus(array1, new OGMatrix(aNumber));
    if (DOGMAconfig.getHaltOnNaNOnFunctionExit()) {
      Catchers.catchNaN(ret);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionExit()) {
      Catchers.catchInf(ret);
    }
    return ret;
  }

  @Override
  public OGArray<? extends Number> plus(OGArray<? extends Number> array1, int aNumber) {
    OGArray<? extends Number> ret = null;
    if (DOGMAconfig.getHaltOnNaNOnFunctionEntry()) {
      Catchers.catchNaN(array1);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionEntry()) {
      Catchers.catchInf(array1);
    }
    ret = plus(array1, new OGMatrix(aNumber));
    if (DOGMAconfig.getHaltOnNaNOnFunctionExit()) {
      Catchers.catchNaN(ret);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionExit()) {
      Catchers.catchInf(ret);
    }
    return ret;
  }

  /* SUBTRACT */
  @Override
  public OGArray<? extends Number> minus(OGArray<? extends Number>... array) {
    OGArray<? extends Number> tmp = _copy.copy(array[0]);
    for (int i = 1; i < array.length; i++) {
      tmp = _plusMinus.minus(tmp, array[i]);
    }
    return tmp;
  }

  @Override
  public OGArray<? extends Number> minus(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return _plusMinus.minus(array1, array2);
  }

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

  /* RDIVIDE */

  @Override
  public OGArray<? extends Number> rdivide(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return _rdivide.rdivide(array1, array2);
  }

  @Override
  public OGArray<? extends Number> rdivide(OGArray<? extends Number> array1, double number) {
    return _rdivide.rdivide(array1, new OGMatrix(number));
  }

  @Override
  public OGArray<? extends Number> rdivide(OGArray<? extends Number> array1, int number) {
    return _rdivide.rdivide(array1, new OGMatrix(number));
  }

  @Override
  public OGArray<? extends Number> rdivide(double number, OGArray<? extends Number> array1) {
    return _rdivide.rdivide(new OGMatrix(number), array1);
  }

  @Override
  public OGArray<? extends Number> rdivide(int number, OGArray<? extends Number> array1) {
    return _rdivide.rdivide(new OGMatrix(number), array1);
  }

  /* MRDIVIDE */

  @Override
  public OGArray<? extends Number> mrdivide(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    throw new MathsExceptionNotImplemented("mrdivide not implemented yet");
  }

  /* TIMES */
  @Override
  public OGArray<? extends Number> times(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return _times.times(array1, array2);
  }

  @Override
  public OGArray<? extends Number> times(OGArray<? extends Number>... array) {
    OGArray<? extends Number> tmp = _copy.copy(array[0]);
    for (int i = 1; i < array.length; i++) {
      tmp = _times.times(tmp, array[i]);
    }
    return tmp;
  }

  @Override
  public OGArray<? extends Number> times(OGArray<? extends Number> array1, double number) {
    return _times.times(array1, new OGMatrix(number));
  }

  @Override
  public OGArray<? extends Number> times(OGArray<? extends Number> array1, int number) {
    return _times.times(array1, new OGMatrix(number));
  }

  @Override
  public OGArray<? extends Number> times(double number, OGArray<? extends Number> array1) {
    return _times.times(array1, new OGMatrix(number));
  }

  @Override
  public OGArray<? extends Number> times(int number, OGArray<? extends Number> array1) {
    return _times.times(array1, new OGMatrix(number));
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

  /* TRANSPOSE */
  @Override
  public OGArray<? extends Number> transpose(OGArray<? extends Number> array) {
    return _transpose.transpose(array);
  }

}
