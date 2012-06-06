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
    return _copy.copy(array);
  }

}
