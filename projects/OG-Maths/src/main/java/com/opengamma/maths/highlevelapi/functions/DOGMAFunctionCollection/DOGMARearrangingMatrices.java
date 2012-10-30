/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGSortResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy.Copy;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMARearrangingMatricesAPI;


/**
 * Functions for rearranging matrices
 */
public class DOGMARearrangingMatrices implements DOGMARearrangingMatricesAPI {
  private final Copy _copy = new Copy();
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
    return _copy.copy(array);
  }

}
