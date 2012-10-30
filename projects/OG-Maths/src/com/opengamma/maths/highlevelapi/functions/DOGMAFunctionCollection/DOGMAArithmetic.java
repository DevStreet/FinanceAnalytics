/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.commonapi.properties.DOGMAconfig;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.Mtimes;
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
  private Mtimes _mtimes = new Mtimes();
  private Transpose _transpose = new Transpose();

  /* ADD */
  @Override
  public OGArraySuper<? extends Number> plus(OGArraySuper<? extends Number>... array) {
    if (DOGMAconfig.getHaltOnNaNOnFunctionEntry()) {
      Catchers.catchNaN(array[0]);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionEntry()) {
      Catchers.catchInf(array[0]);
    }
    OGArraySuper<? extends Number> tmp = _copy.copy(array[0]);
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
  public OGArraySuper<? extends Number> plus(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    OGArraySuper<? extends Number> ret = null;
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
  public OGArraySuper<? extends Number> plus(OGArraySuper<? extends Number> array1, double aNumber) {
    OGArraySuper<? extends Number> ret = null;
    if (DOGMAconfig.getHaltOnNaNOnFunctionEntry()) {
      Catchers.catchNaN(array1);
      Catchers.catchNaN(aNumber);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionEntry()) {
      Catchers.catchInf(array1);
      Catchers.catchInf(aNumber);
    }
    ret = plus(array1, new OGDoubleArray(aNumber));
    if (DOGMAconfig.getHaltOnNaNOnFunctionExit()) {
      Catchers.catchNaN(ret);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionExit()) {
      Catchers.catchInf(ret);
    }
    return ret;
  }

  @Override
  public OGArraySuper<? extends Number> plus(OGArraySuper<? extends Number> array1, int aNumber) {
    OGArraySuper<? extends Number> ret = null;
    if (DOGMAconfig.getHaltOnNaNOnFunctionEntry()) {
      Catchers.catchNaN(array1);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionEntry()) {
      Catchers.catchInf(array1);
    }
    ret = plus(array1, new OGDoubleArray(aNumber));
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
  public OGArraySuper<? extends Number> minus(OGArraySuper<? extends Number>... array) {
    OGArraySuper<? extends Number> tmp = _copy.copy(array[0]);
    for (int i = 1; i < array.length; i++) {
      tmp = _plusMinus.minus(tmp, array[i]);
    }
    return tmp;
  }

  @Override
  public OGArraySuper<? extends Number> minus(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return _plusMinus.minus(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> minus(OGArraySuper<? extends Number> array1, double aNumber) {
    return minus(array1, new OGDoubleArray(aNumber));
  }

  @Override
  public OGArraySuper<? extends Number> minus(OGArraySuper<? extends Number> array1, int aNumber) {
    return minus(array1, new OGDoubleArray(aNumber));
  }

  /* LDIVIDE */

  @Override
  public OGArraySuper<? extends Number> ldivide(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    throw new MathsExceptionNotImplemented("ldivide not implemented yet");
  }

  /* MLDIVIDE */

  @Override
  public OGArraySuper<? extends Number> mldivide(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    throw new MathsExceptionNotImplemented("mldivide not implemented yet");
  }

  /* RDIVIDE */

  @Override
  public OGArraySuper<? extends Number> rdivide(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return _rdivide.rdivide(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> rdivide(OGArraySuper<? extends Number> array1, double number) {
    return _rdivide.rdivide(array1, new OGDoubleArray(number));
  }

  @Override
  public OGArraySuper<? extends Number> rdivide(OGArraySuper<? extends Number> array1, int number) {
    return _rdivide.rdivide(array1, new OGDoubleArray(number));
  }

  @Override
  public OGArraySuper<? extends Number> rdivide(double number, OGArraySuper<? extends Number> array1) {
    return _rdivide.rdivide(new OGDoubleArray(number), array1);
  }

  @Override
  public OGArraySuper<? extends Number> rdivide(int number, OGArraySuper<? extends Number> array1) {
    return _rdivide.rdivide(new OGDoubleArray(number), array1);
  }

  /* MRDIVIDE */

  @Override
  public OGArraySuper<? extends Number> mrdivide(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    throw new MathsExceptionNotImplemented("mrdivide not implemented yet");
  }

  /* TIMES */
  @Override
  public OGArraySuper<? extends Number> times(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return _times.times(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> times(OGArraySuper<? extends Number>... array) {
    OGArraySuper<? extends Number> tmp = _copy.copy(array[0]);
    for (int i = 1; i < array.length; i++) {
      tmp = _times.times(tmp, array[i]);
    }
    return tmp;
  }

  @Override
  public OGArraySuper<? extends Number> times(OGArraySuper<? extends Number> array1, double number) {
    return _times.times(array1, new OGDoubleArray(number));
  }

  @Override
  public OGArraySuper<? extends Number> times(OGArraySuper<? extends Number> array1, int number) {
    return _times.times(array1, new OGDoubleArray(number));
  }

  @Override
  public OGArraySuper<? extends Number> times(double number, OGArraySuper<? extends Number> array1) {
    return _times.times(array1, new OGDoubleArray(number));
  }

  @Override
  public OGArraySuper<? extends Number> times(int number, OGArraySuper<? extends Number> array1) {
    return _times.times(array1, new OGDoubleArray(number));
  }

  /* MTIMES */
  @Override
  public OGArraySuper<? extends Number> mtimes(OGArraySuper<? extends Number>... array) {
    OGArraySuper<? extends Number> tmp = _copy.copy(array[0]);
    for (int i = 1; i < array.length; i++) {
      tmp = _mtimes.mtimes(tmp, array[i]);
    }
    return tmp;
  }

  @Override
  public OGArraySuper<? extends Number> mtimes(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return _mtimes.mtimes(array1, array2);
  }

  /* POWER */
  @Override
  public OGArraySuper<? extends Number> power(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    throw new MathsExceptionNotImplemented("power not implemented yet");
  }

  /* MPOWER */
  @Override
  public OGArraySuper<? extends Number> mpower(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    throw new MathsExceptionNotImplemented("mpower not implemented yet");
  }

  /* TRANSPOSE */
  @Override
  public OGArraySuper<? extends Number> transpose(OGArraySuper<? extends Number> array) {
    return _transpose.transpose(array);
  }

}
