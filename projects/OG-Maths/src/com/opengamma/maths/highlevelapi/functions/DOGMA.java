/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions;

import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.highlevelapi.datatypes.derived.OGSortResult;
import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMAArithmetic;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMAExponentsAndLogarithms;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMALinearAlgebra;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMARearrangingMatrices;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMASparseUtilities;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMASpecialFunctions;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMATrigonometry;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMAUtilityFunctions;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd.compute;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMAArithmeticAPI;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMAExponentsAndLogarithmsAPI;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMALinearAlgebraAPI;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMARearrangingMatricesAPI;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMASparseUtilitiesAPI;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMASpecialFunctionsAPI;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMATrigonometaryAPI;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMAUtilityFunctionsAPI;

/**
 * The DOGMA language. The entry point for using all DOGMA functions from one place!
 */
public class DOGMA implements DOGMAArithmeticAPI, DOGMAExponentsAndLogarithmsAPI, DOGMARearrangingMatricesAPI, DOGMASparseUtilitiesAPI, DOGMASpecialFunctionsAPI, DOGMATrigonometaryAPI,
    DOGMAUtilityFunctionsAPI, DOGMALinearAlgebraAPI {
  /**
   * The square root of minus one, the complex number <i>i</i>
   */
  public static final ComplexType i = ComplexType.I;  //CSIGNORE

  /**
   * Negated square root of minus one, the complex number <i>-i</i>
   */
  public static final ComplexType negative_i = ComplexType.NEGATIVE_I;  //CSIGNORE  
  
  // impl blobs
  private static final DOGMAArithmetic DA = new DOGMAArithmetic();
  private static final DOGMAExponentsAndLogarithmsAPI DEAL = new DOGMAExponentsAndLogarithms();
  private static final DOGMARearrangingMatrices DRM = new DOGMARearrangingMatrices();
  private static final DOGMASparseUtilities DSU = new DOGMASparseUtilities();
  private static final DOGMASpecialFunctions DSF = new DOGMASpecialFunctions();
  private static final DOGMATrigonometry DT = new DOGMATrigonometry();
  private static final DOGMAUtilityFunctions DUF = new DOGMAUtilityFunctions();
  private static final DOGMALinearAlgebra DLA = new DOGMALinearAlgebra();

  // From Arithmetic...
  @Override
  public OGArraySuper<Number> plus(OGArraySuper<Number>... array) {
    return DA.plus(array);
  }

  @Override
  public OGArraySuper<Number> plus(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return DA.plus(array1, array2);
  }

  @Override
  public OGArraySuper<Number> plus(OGArraySuper<Number> array1, double number) {
    return DA.plus(array1, number);
  }

  @Override
  public OGArraySuper<Number> plus(OGArraySuper<Number> array1, int number) {
    return DA.plus(array1, number);
  }

  @Override
  public OGArraySuper<Number> minus(OGArraySuper<Number>... array) {
    return DA.minus(array);
  }

  @Override
  public OGArraySuper<Number> minus(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return DA.plus(array1, array2);
  }

  @Override
  public OGArraySuper<Number> minus(OGArraySuper<Number> array1, double number) {
    return DA.minus(array1, number);
  }

  @Override
  public OGArraySuper<Number> minus(OGArraySuper<Number> array1, int number) {
    return DA.minus(array1, number);
  }

  @Override
  public OGArraySuper<Number> ldivide(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return DA.ldivide(array1, array2);
  }

  @Override
  public OGArraySuper<Number> mldivide(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return DA.mldivide(array1, array2);
  }

  @Override
  public OGArraySuper<Number> rdivide(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return DA.rdivide(array1, array2);
  }

  @Override
  public OGArraySuper<Number> rdivide(OGArraySuper<Number> array1, double number) {
    return DA.rdivide(array1, number);
  }

  @Override
  public OGArraySuper<Number> rdivide(OGArraySuper<Number> array1, int number) {
    return DA.rdivide(array1, number);
  }

  @Override
  public OGArraySuper<Number> rdivide(double number, OGArraySuper<Number> array1) {
    return DA.rdivide(number, array1);
  }

  @Override
  public OGArraySuper<Number> rdivide(int number, OGArraySuper<Number> array1) {
    return DA.rdivide(number, array1);
  }

  @Override
  public OGArraySuper<Number> mrdivide(OGArraySuper<Number> matrixA, OGArraySuper<Number> vectorb) {
    return DA.mrdivide(matrixA, vectorb);
  }

  @Override
  public OGArraySuper<Number> times(OGArraySuper<Number>... array) {
    return DA.times(array);
  }

  @Override
  public OGArraySuper<Number> times(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return DA.times(array1, array2);
  }

  @Override
  public OGArraySuper<Number> times(OGArraySuper<Number> array1, double number) {
    return DA.times(array1, number);
  }

  @Override
  public OGArraySuper<Number> times(OGArraySuper<Number> array1, int number) {
    return DA.times(array1, number);
  }

  @Override
  public OGArraySuper<Number> times(double number, OGArraySuper<Number> array1) {
    return DA.times(number, array1);
  }

  @Override
  public OGArraySuper<Number> times(int number, OGArraySuper<Number> array1) {
    return DA.times(number, array1);
  }

  @Override
  public OGArraySuper<Number> mtimes(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return DA.mtimes(array1, array2);
  }

  @Override
  public OGArraySuper<Number> mtimes(OGArraySuper<Number>... array) {
    return DA.mtimes(array);
  }

  @Override
  public OGArraySuper<Number> power(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return DA.power(array1, array2);
  }

  @Override
  public OGArraySuper<Number> mpower(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return DA.mpower(array1, array2);
  }

  @Override
  public OGArraySuper<Number> transpose(OGArraySuper<Number> array) {
    return DA.transpose(array);
  }

  // The exponents and logs API

  @Override
  public OGArraySuper<Number> sqrt(OGArraySuper<Number> array1) {
    return DEAL.sqrt(array1);
  }

  // The rearranging matrices API

  @Override
  public OGArraySuper<Number> cat(OGIndexArray dim, OGArraySuper<Number>... array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> diag(OGArraySuper<Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> flipdim(OGArraySuper<Number> array, OGIndexArray dim) {
    return null;
  }

  @Override
  public OGArraySuper<Number> fliplr(OGArraySuper<Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> flipud(OGArraySuper<Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> horzcat(OGArraySuper<Number>... array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> ipermute(OGArraySuper<Number> array, OGPermutationArray perm) {
    return null;
  }

  @Override
  public OGArraySuper<Number> permute(OGArraySuper<Number> array, OGPermutationArray perm) {
    return null;
  }

  @Override
  public OGArraySuper<Number> postpad(OGArraySuper<Number> array, OGIndexArray l, OGArraySuper<Number> c) {
    return null;
  }

  @Override
  public OGArraySuper<Number> postpad(OGArraySuper<Number> array, OGIndexArray l, OGArraySuper<Number> c, OGIndexArray dim) {
    return null;
  }

  @Override
  public OGArraySuper<Number> prepad(OGArraySuper<Number> array, OGIndexArray l, OGArraySuper<Number> c) {
    return null;
  }

  @Override
  public OGArraySuper<Number> prepad(OGArraySuper<Number> array, OGIndexArray l, OGArraySuper<Number> c, OGIndexArray dim) {
    return null;
  }

  @Override
  public OGArraySuper<Number> reshape(OGArraySuper<Number> array, OGIndexArray rows, OGIndexArray columns) {
    return null;
  }

  @Override
  public OGArraySuper<Number> resize(OGArraySuper<Number> array, OGIndexArray rows, OGIndexArray columns) {
    return null;
  }

  @Override
  public OGArraySuper<Number> rot90(OGArraySuper<Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> rot90(OGArraySuper<Number> array, OGIndexArray increments) {
    return null;
  }

  @Override
  public OGArraySuper<Number> sort(OGArraySuper<Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> sort(OGArraySuper<Number> array, String order) {
    return null;
  }

  @Override
  public OGSortResult<OGArraySuper<Number>> sort(OGArraySuper<Number> array, OGIndexArray dim, String order) {
    return null;
  }

  @Override
  public OGArraySuper<Number> sortrows(OGArraySuper<Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> sortrows(OGArraySuper<Number> array, OGIndexArray columnIndex) {
    return null;
  }

  @Override
  public OGArraySuper<Number> triu(OGArraySuper<Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> tril(OGArraySuper<Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> vertcat(OGArraySuper<Number>... array) {
    return null;
  }

  @Override
  public OGArraySuper<Number> copy(OGArraySuper<Number> array) {
    return DRM.copy(array);
  }

  // The sparse utils API

  @Override
  public OGArraySuper<Number> full(OGArraySuper<Number> array) {
    return DSU.full(array);
  }

  // The special functions API

  @Override
  public OGArraySuper<Number> erf(OGArraySuper<Number> array1) {
    return DSF.erf(array1);
  }

  @Override
  public OGArraySuper<Number> erfc(OGArraySuper<Number> array1) {
    return DSF.erfc(array1);
  }

  // The trig API

  @Override
  public OGArraySuper<Number> sin(OGArraySuper<Number> array1) {
    return DT.sin(array1);
  }

  @Override
  public OGArraySuper<Number> cos(OGArraySuper<Number> array1) {
    return DT.cos(array1);
  }

  @Override
  public OGArraySuper<Number> tan(OGArraySuper<Number> array1) {
    return DT.tan(array1);
  }

  @Override
  public OGArraySuper<Number> asin(OGArraySuper<Number> array1) {
    return DT.asin(array1);
  }

  @Override
  public OGArraySuper<Number> acos(OGArraySuper<Number> array1) {
    return DT.acos(array1);
  }

  @Override
  public OGArraySuper<Number> atan(OGArraySuper<Number> array1) {
    return DT.atan(array1);
  }

  @Override
  public OGArraySuper<Number> sinh(OGArraySuper<Number> array1) {
    return DT.sinh(array1);
  }

  @Override
  public OGArraySuper<Number> cosh(OGArraySuper<Number> array1) {
    return DT.cosh(array1);
  }

  @Override
  public OGArraySuper<Number> tanh(OGArraySuper<Number> array1) {
    return DT.tanh(array1);
  }

  // the utlity functions api

  @Override
  public OGArraySuper<Number> dot(OGArraySuper<Number> array1, OGArraySuper<Number> array2) {
    return DUF.dot(array1, array2);
  }

  // the linear algebra functions api
  @Override
  public OGSvdResult svd(OGArraySuper<Number> array1, compute required) {
    return DLA.svd(array1, required);
  }
}
