/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.commonapi.numbers;

import java.util.Arrays;

import com.opengamma.analytics.math.number.ComplexNumber;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Complex number type
 */
public class ComplexType extends Number {
  /** 
   * Define complex <i>i</i>
   */
  public static final ComplexNumber I = new ComplexNumber(0, 1);
  /**
   * Define complex <i>-i</i>
   */
  public static final ComplexNumber NEGATIVE_I = new ComplexNumber(0, -1);
  /**
   * Define complex 0 + 0<i>i</i>
   */
  public static final ComplexNumber ZERO = new ComplexNumber(0);

  private double[] _data = new double[2];

  /**
   * Construct from real only, imaginary set to 0
   * @param real the real part
   */
  public ComplexType(double real) {
    _data[0] = real;
    _data[1] = 0.d;
  }

  /**
   * Construct from real and imaginary parts
   * @param real the real part
   * @param imag the imaginary part
   */
  public ComplexType(double real, double imag) {
    _data[0] = real;
    _data[1] = imag;
  }

  /**
   * Construct from ordered double[] of length 2, real is at address [0] and imaginary is at address[1]
   * @param complex real part is at address [0] and imaginary part is at address[1]
   */
  public ComplexType(double[] complex) {
    Catchers.catchNullFromArgList(complex, 1);
    if (complex.length != 2) {
      throw new MathsExceptionIllegalArgument("Assignment from a double[] to ComplexType can only be performed from a double[] of length 2");
    }
    _data[0] = complex[0];
    _data[1] = complex[1];
  }

  /**
   * Get the real component of the number
   * @return the real component of the number
   */
  double getReal() {
    return _data[0];
  }

  /**
   * Get the real component of the number
   * @return the real component of the number
   */
  double getImag() {
    return _data[1];
  }

  private static final long serialVersionUID = 4761964964780050540L;

  @Override
  public int intValue() {
    throw new MathsExceptionNotImplemented("int value of complex has no meaning");
  }

  @Override
  public long longValue() {
    throw new MathsExceptionNotImplemented("long value of complex has no meaning");
  }

  @Override
  public float floatValue() {
    throw new MathsExceptionNotImplemented("float value of complex has no meaning");
  }

  @Override
  public double doubleValue() {
    throw new MathsExceptionNotImplemented("double value of complex has no meaning");
  }

  @Override
  public String toString() {
    String str = "ComplexType [data=" + Arrays.toString(_data) + "]";
    str = str + "\n====Pretty Print====\n";
    str = str + String.format("%24.18f " + (_data[1] < 0 ? "-" : "+") + " %24.18fi\n", _data[0], _data[1]);
    return str;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(_data);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ComplexType other = (ComplexType) obj;
    if (!Arrays.equals(_data, other._data)) {
      return false;
    }
    return true;
  }
}
