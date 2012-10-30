/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGSortResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy.Copy;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMARearrangingMatricesAPI;


/**
 * Functions for rearranging matrices
 */
public class DOGMARearrangingMatrices implements DOGMARearrangingMatricesAPI {
  private final Copy _copy = new Copy();
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
    return _copy.copy(array);
  }

}
