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
public class DOGMA implements 
  DOGMAArithmeticAPI, 
  DOGMAExponentsAndLogarithmsAPI, 
  DOGMARearrangingMatricesAPI, 
  DOGMASparseUtilitiesAPI, 
  DOGMASpecialFunctionsAPI, 
  DOGMATrigonometaryAPI,
  DOGMAUtilityFunctionsAPI, 
  DOGMALinearAlgebraAPI {
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
  public OGArraySuper<? extends Number> plus(OGArraySuper<? extends Number>... array) {
    return DA.plus(array);
  }

  @Override
  public OGArraySuper<? extends Number> plus(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return DA.plus(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> plus(OGArraySuper<? extends Number> array1, double number) {
    return DA.plus(array1, number);
  }

  @Override
  public OGArraySuper<? extends Number> plus(OGArraySuper<? extends Number> array1, int number) {
    return DA.plus(array1, number);
  }

  @Override
  public OGArraySuper<? extends Number> minus(OGArraySuper<? extends Number>... array) {
    return DA.minus(array);
  }

  @Override
  public OGArraySuper<? extends Number> minus(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return DA.plus(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> minus(OGArraySuper<? extends Number> array1, double number) {
    return DA.minus(array1, number);
  }

  @Override
  public OGArraySuper<? extends Number> minus(OGArraySuper<? extends Number> array1, int number) {
    return DA.minus(array1, number);
  }

  @Override
  public OGArraySuper<? extends Number> ldivide(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return DA.ldivide(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> mldivide(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return DA.mldivide(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> rdivide(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return DA.rdivide(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> rdivide(OGArraySuper<? extends Number> array1, double number) {
    return DA.rdivide(array1, number);
  }

  @Override
  public OGArraySuper<? extends Number> rdivide(OGArraySuper<? extends Number> array1, int number) {
    return DA.rdivide(array1, number);
  }

  @Override
  public OGArraySuper<? extends Number> rdivide(double number, OGArraySuper<? extends Number> array1) {
    return DA.rdivide(number, array1);
  }

  @Override
  public OGArraySuper<? extends Number> rdivide(int number, OGArraySuper<? extends Number> array1) {
    return DA.rdivide(number, array1);
  }

  @Override
  public OGArraySuper<? extends Number> mrdivide(OGArraySuper<? extends Number> matrixA, OGArraySuper<? extends Number> vectorb) {
    return DA.mrdivide(matrixA, vectorb);
  }

  @Override
  public OGArraySuper<? extends Number> times(OGArraySuper<? extends Number>... array) {
    return DA.times(array);
  }

  @Override
  public OGArraySuper<? extends Number> times(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return DA.times(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> times(OGArraySuper<? extends Number> array1, double number) {
    return DA.times(array1, number);
  }

  @Override
  public OGArraySuper<? extends Number> times(OGArraySuper<? extends Number> array1, int number) {
    return DA.times(array1, number);
  }

  @Override
  public OGArraySuper<? extends Number> times(double number, OGArraySuper<? extends Number> array1) {
    return DA.times(number, array1);
  }

  @Override
  public OGArraySuper<? extends Number> times(int number, OGArraySuper<? extends Number> array1) {
    return DA.times(number, array1);
  }

  @Override
  public OGArraySuper<? extends Number> mtimes(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return DA.mtimes(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> mtimes(OGArraySuper<? extends Number>... array) {
    return DA.mtimes(array);
  }

  @Override
  public OGArraySuper<? extends Number> power(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return DA.power(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> mpower(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return DA.mpower(array1, array2);
  }

  @Override
  public OGArraySuper<? extends Number> transpose(OGArraySuper<? extends Number> array) {
    return DA.transpose(array);
  }

  // The exponents and logs API

  @Override
  public OGArraySuper<? extends Number> sqrt(OGArraySuper<? extends Number> array1) {
    return DEAL.sqrt(array1);
  }

  // The rearranging matrices API

  @Override
  public OGArraySuper<? extends Number> cat(OGIndexArray dim, OGArraySuper<? extends Number>... array) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> diag(OGArraySuper<? extends Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> flipdim(OGArraySuper<? extends Number> array, OGIndexArray dim) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> fliplr(OGArraySuper<? extends Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> flipud(OGArraySuper<? extends Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> horzcat(OGArraySuper<? extends Number>... array) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> ipermute(OGArraySuper<? extends Number> array, OGPermutationArray perm) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> permute(OGArraySuper<? extends Number> array, OGPermutationArray perm) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> postpad(OGArraySuper<? extends Number> array, OGIndexArray l, OGArraySuper<? extends Number> c) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> postpad(OGArraySuper<? extends Number> array, OGIndexArray l, OGArraySuper<? extends Number> c, OGIndexArray dim) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> prepad(OGArraySuper<? extends Number> array, OGIndexArray l, OGArraySuper<? extends Number> c) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> prepad(OGArraySuper<? extends Number> array, OGIndexArray l, OGArraySuper<? extends Number> c, OGIndexArray dim) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> reshape(OGArraySuper<? extends Number> array, OGIndexArray rows, OGIndexArray columns) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> resize(OGArraySuper<? extends Number> array, OGIndexArray rows, OGIndexArray columns) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> rot90(OGArraySuper<? extends Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> rot90(OGArraySuper<? extends Number> array, OGIndexArray increments) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> sort(OGArraySuper<? extends Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> sort(OGArraySuper<? extends Number> array, String order) {
    return null;
  }

  @Override
  public OGSortResult<OGArraySuper<? extends Number>> sort(OGArraySuper<? extends Number> array, OGIndexArray dim, String order) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> sortrows(OGArraySuper<? extends Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> sortrows(OGArraySuper<? extends Number> array, OGIndexArray columnIndex) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> triu(OGArraySuper<? extends Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> tril(OGArraySuper<? extends Number> array) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> vertcat(OGArraySuper<? extends Number>... array) {
    return null;
  }

  @Override
  public OGArraySuper<? extends Number> copy(OGArraySuper<? extends Number> array) {
    return DRM.copy(array);
  }

  // The sparse utils API

  @Override
  public OGArraySuper<? extends Number> full(OGArraySuper<? extends Number> array) {
    return DSU.full(array);
  }

  // The special functions API

  @Override
  public OGArraySuper<? extends Number> erf(OGArraySuper<? extends Number> array1) {
    return DSF.erf(array1);
  }

  @Override
  public OGArraySuper<? extends Number> erfc(OGArraySuper<? extends Number> array1) {
    return DSF.erfc(array1);
  }

  // The trig API

  @Override
  public OGArraySuper<? extends Number> sin(OGArraySuper<? extends Number> array1) {
    return DT.sin(array1);
  }

  @Override
  public OGArraySuper<? extends Number> cos(OGArraySuper<? extends Number> array1) {
    return DT.cos(array1);
  }

  @Override
  public OGArraySuper<? extends Number> tan(OGArraySuper<? extends Number> array1) {
    return DT.tan(array1);
  }

  @Override
  public OGArraySuper<? extends Number> asin(OGArraySuper<? extends Number> array1) {
    return DT.asin(array1);
  }

  @Override
  public OGArraySuper<? extends Number> acos(OGArraySuper<? extends Number> array1) {
    return DT.acos(array1);
  }

  @Override
  public OGArraySuper<? extends Number> atan(OGArraySuper<? extends Number> array1) {
    return DT.atan(array1);
  }

  @Override
  public OGArraySuper<? extends Number> sinh(OGArraySuper<? extends Number> array1) {
    return DT.sinh(array1);
  }

  @Override
  public OGArraySuper<? extends Number> cosh(OGArraySuper<? extends Number> array1) {
    return DT.cosh(array1);
  }

  @Override
  public OGArraySuper<? extends Number> tanh(OGArraySuper<? extends Number> array1) {
    return DT.tanh(array1);
  }

  // the utlity functions api

  @Override
  public OGArraySuper<? extends Number> dot(OGArraySuper<? extends Number> array1, OGArraySuper<? extends Number> array2) {
    return DUF.dot(array1, array2);
  }

  // the linear algebra functions api
  @Override
  public OGSvdResult svd(OGArraySuper<? extends Number> array1, compute required) {
    return DLA.svd(array1, required);
  }
}
