/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Holder of a adjacency matrix to show what can be converted to what
 * also the cost of conversion
 */
public class ConversionCostAdjacencyMatrixStore {

  private static ConversionCostAdjacencyMatrixStore s_instance = new ConversionCostAdjacencyMatrixStore();

  ConversionCostAdjacencyMatrixStore() {
  }

  //CSOFF
  private static final double[][] SDataUnweighted = new double[][] {//
  {1, 1, 1, 1, 0, 1, 1, 1, 1, 1 }, //
      {0, 1, 0, 1, 0, 0, 0, 1, 0, 1 }, //
      {0, 0, 1, 1, 0, 0, 1, 1, 1, 1 }, //
      {0, 0, 0, 1, 0, 0, 0, 1, 0, 1 }, //
      {0, 0, 0, 0, 1, 1, 1, 1, 1, 1 }, //
      {0, 0, 0, 0, 0, 1, 1, 1, 1, 1 }, //
      {0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }, //
      {0, 0, 0, 0, 0, 0, 0, 1, 0, 1 }, //
      {0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }, //
      {0, 0, 0, 0, 0, 0, 0, 1, 0, 1 } };

  // hacky approx costs of conversion
  private static final double[][] SDataWeighted = new double[][] {//
  {1, 1, 1, 1, 0, 1, 1, 1, 1, 1 }, //
      {0, 1, 0, 1, 0, 0, 0, 1, 0, 1 }, //
      {0, 0, 1, 1, 0, 0, 1, 1, 1, 1 }, //
      {0, 0, 0, 1, 0, 0, 0, 1, 0, 1 }, //
      {0, 0, 0, 0, 1, 1, 1, 1, 1, 1 }, //
      {0, 0, 0, 0, 0, 1, 1, 1, 1, 1 }, //
      {0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }, //
      {0, 0, 0, 0, 0, 0, 0, 1, 0, 1 }, //
      {0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }, //
      {0, 0, 0, 0, 0, 0, 0, 1, 0, 1 } };
  //CSON

  // we store this a an OGMatrix cos we can 
  private static final OGMatrix s_mapunweighted = new OGMatrix(SDataUnweighted);
  private static final OGMatrix s_mapweighted = new OGMatrix(SDataWeighted);

  public static OGMatrix getAdjacencyMatrix() {
    return s_mapunweighted;
  }

  public static OGMatrix getWeightedAdjacencyMatrix() {
    return s_mapweighted;
  }

  public static ConversionCostAdjacencyMatrixStore getInstance() {
    return s_instance;
  }

}
