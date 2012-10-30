/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions;

import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.highlevelapi.datatypes.derived.OGSortResult;
import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;
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
  public OGArray<? extends Number> plus(OGArray<? extends Number>... array) {
    return DA.plus(array);
  }

  @Override
  public OGArray<? extends Number> plus(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return DA.plus(array1, array2);
  }

  @Override
  public OGArray<? extends Number> plus(OGArray<? extends Number> array1, double number) {
    return DA.plus(array1, number);
  }

  @Override
  public OGArray<? extends Number> plus(OGArray<? extends Number> array1, int number) {
    return DA.plus(array1, number);
  }

  @Override
  public OGArray<? extends Number> minus(OGArray<? extends Number>... array) {
    return DA.minus(array);
  }

  @Override
  public OGArray<? extends Number> minus(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return DA.plus(array1, array2);
  }

  @Override
  public OGArray<? extends Number> minus(OGArray<? extends Number> array1, double number) {
    return DA.minus(array1, number);
  }

  @Override
  public OGArray<? extends Number> minus(OGArray<? extends Number> array1, int number) {
    return DA.minus(array1, number);
  }

  @Override
  public OGArray<? extends Number> ldivide(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return DA.ldivide(array1, array2);
  }

  @Override
  public OGArray<? extends Number> mldivide(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return DA.mldivide(array1, array2);
  }

  @Override
  public OGArray<? extends Number> rdivide(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return DA.rdivide(array1, array2);
  }

  @Override
  public OGArray<? extends Number> rdivide(OGArray<? extends Number> array1, double number) {
    return DA.rdivide(array1, number);
  }

  @Override
  public OGArray<? extends Number> rdivide(OGArray<? extends Number> array1, int number) {
    return DA.rdivide(array1, number);
  }

  @Override
  public OGArray<? extends Number> rdivide(double number, OGArray<? extends Number> array1) {
    return DA.rdivide(number, array1);
  }

  @Override
  public OGArray<? extends Number> rdivide(int number, OGArray<? extends Number> array1) {
    return DA.rdivide(number, array1);
  }

  @Override
  public OGArray<? extends Number> mrdivide(OGArray<? extends Number> matrixA, OGArray<? extends Number> vectorb) {
    return DA.mrdivide(matrixA, vectorb);
  }

  @Override
  public OGArray<? extends Number> times(OGArray<? extends Number>... array) {
    return DA.times(array);
  }

  @Override
  public OGArray<? extends Number> times(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return DA.times(array1, array2);
  }

  @Override
  public OGArray<? extends Number> times(OGArray<? extends Number> array1, double number) {
    return DA.times(array1, number);
  }

  @Override
  public OGArray<? extends Number> times(OGArray<? extends Number> array1, int number) {
    return DA.times(array1, number);
  }

  @Override
  public OGArray<? extends Number> times(double number, OGArray<? extends Number> array1) {
    return DA.times(number, array1);
  }

  @Override
  public OGArray<? extends Number> times(int number, OGArray<? extends Number> array1) {
    return DA.times(number, array1);
  }

  @Override
  public OGArray<? extends Number> mtimes(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return DA.mtimes(array1, array2);
  }

  @Override
  public OGArray<? extends Number> mtimes(OGArray<? extends Number>... array) {
    return DA.mtimes(array);
  }

  @Override
  public OGArray<? extends Number> power(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return DA.power(array1, array2);
  }

  @Override
  public OGArray<? extends Number> mpower(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return DA.mpower(array1, array2);
  }

  @Override
  public OGArray<? extends Number> transpose(OGArray<? extends Number> array) {
    return DA.transpose(array);
  }

  // The exponents and logs API

  @Override
  public OGArray<? extends Number> sqrt(OGArray<? extends Number> array1) {
    return DEAL.sqrt(array1);
  }

  // The rearranging matrices API

  @Override
  public OGArray<? extends Number> cat(OGIndexMatrix dim, OGArray<? extends Number>... array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> diag(OGArray<? extends Number> array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> flipdim(OGArray<? extends Number> array, OGIndexMatrix dim) {
    return null;
  }

  @Override
  public OGArray<? extends Number> fliplr(OGArray<? extends Number> array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> flipud(OGArray<? extends Number> array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> horzcat(OGArray<? extends Number>... array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> ipermute(OGArray<? extends Number> array, OGPermutationMatrix perm) {
    return null;
  }

  @Override
  public OGArray<? extends Number> permute(OGArray<? extends Number> array, OGPermutationMatrix perm) {
    return null;
  }

  @Override
  public OGArray<? extends Number> postpad(OGArray<? extends Number> array, OGIndexMatrix l, OGArray<? extends Number> c) {
    return null;
  }

  @Override
  public OGArray<? extends Number> postpad(OGArray<? extends Number> array, OGIndexMatrix l, OGArray<? extends Number> c, OGIndexMatrix dim) {
    return null;
  }

  @Override
  public OGArray<? extends Number> prepad(OGArray<? extends Number> array, OGIndexMatrix l, OGArray<? extends Number> c) {
    return null;
  }

  @Override
  public OGArray<? extends Number> prepad(OGArray<? extends Number> array, OGIndexMatrix l, OGArray<? extends Number> c, OGIndexMatrix dim) {
    return null;
  }

  @Override
  public OGArray<? extends Number> reshape(OGArray<? extends Number> array, OGIndexMatrix rows, OGIndexMatrix columns) {
    return null;
  }

  @Override
  public OGArray<? extends Number> resize(OGArray<? extends Number> array, OGIndexMatrix rows, OGIndexMatrix columns) {
    return null;
  }

  @Override
  public OGArray<? extends Number> rot90(OGArray<? extends Number> array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> rot90(OGArray<? extends Number> array, OGIndexMatrix increments) {
    return null;
  }

  @Override
  public OGArray<? extends Number> sort(OGArray<? extends Number> array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> sort(OGArray<? extends Number> array, String order) {
    return null;
  }

  @Override
  public OGSortResult<OGArray<? extends Number>> sort(OGArray<? extends Number> array, OGIndexMatrix dim, String order) {
    return null;
  }

  @Override
  public OGArray<? extends Number> sortrows(OGArray<? extends Number> array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> sortrows(OGArray<? extends Number> array, OGIndexMatrix columnIndex) {
    return null;
  }

  @Override
  public OGArray<? extends Number> triu(OGArray<? extends Number> array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> tril(OGArray<? extends Number> array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> vertcat(OGArray<? extends Number>... array) {
    return null;
  }

  @Override
  public OGArray<? extends Number> copy(OGArray<? extends Number> array) {
    return DRM.copy(array);
  }

  // The sparse utils API

  @Override
  public OGArray<? extends Number> full(OGArray<? extends Number> array) {
    return DSU.full(array);
  }

  // The special functions API

  @Override
  public OGArray<? extends Number> erf(OGArray<? extends Number> array1) {
    return DSF.erf(array1);
  }

  @Override
  public OGArray<? extends Number> erfc(OGArray<? extends Number> array1) {
    return DSF.erfc(array1);
  }

  // The trig API

  @Override
  public OGArray<? extends Number> sin(OGArray<? extends Number> array1) {
    return DT.sin(array1);
  }

  @Override
  public OGArray<? extends Number> cos(OGArray<? extends Number> array1) {
    return DT.cos(array1);
  }

  @Override
  public OGArray<? extends Number> tan(OGArray<? extends Number> array1) {
    return DT.tan(array1);
  }

  @Override
  public OGArray<? extends Number> asin(OGArray<? extends Number> array1) {
    return DT.asin(array1);
  }

  @Override
  public OGArray<? extends Number> acos(OGArray<? extends Number> array1) {
    return DT.acos(array1);
  }

  @Override
  public OGArray<? extends Number> atan(OGArray<? extends Number> array1) {
    return DT.atan(array1);
  }

  @Override
  public OGArray<? extends Number> sinh(OGArray<? extends Number> array1) {
    return DT.sinh(array1);
  }

  @Override
  public OGArray<? extends Number> cosh(OGArray<? extends Number> array1) {
    return DT.cosh(array1);
  }

  @Override
  public OGArray<? extends Number> tanh(OGArray<? extends Number> array1) {
    return DT.tanh(array1);
  }

  // the utlity functions api

  @Override
  public OGArray<? extends Number> dot(OGArray<? extends Number> array1, OGArray<? extends Number> array2) {
    return DUF.dot(array1, array2);
  }

  // the linear algebra functions api
  @Override
  public OGSvdResult svd(OGArray<? extends Number> array1, compute required) {
    return DLA.svd(array1, required);
  }
}
