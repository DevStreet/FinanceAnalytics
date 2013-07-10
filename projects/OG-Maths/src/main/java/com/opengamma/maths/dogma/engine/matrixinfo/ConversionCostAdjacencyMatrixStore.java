/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.matrixinfo;

import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;

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
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, //
    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, //
    {0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }, //
    {0, 0, 0, 0, 0, 0, 0, 1, 0, 1 }, //
    {0, 0, 0, 0, 0, 0, 0, 0, 1, 1 }, //
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1 } };

  
  // _FROM_      Number  Complex  Diag  CplxDiag  Perm  Idx   Sparse  CplxSparse   Mat  CplxMat
  //Number     |  Yes   |   Yes  | Yes |   Yes   | No  | No  |  Yes  |    Yes    | Yes |  Yes  |
  //Complex    |   No   |   Yes  | No  |   Yes   | No  | No  |  No   |    Yes    | No  |  Yes  |
  //Diag       |   No   |   No   | Yes |   Yes   | No  | No  |  Yes  |    Yes    | Yes |  Yes  |
  //CplxDiag   |   No   |   No   | No  |   Yes   | No  | No  |  No   |    Yes    | No  |  Yes  |
  //Perm       |   No   |   No   | No  |   No    | Yes | No  |  Yes  |    Yes    | Yes |  Yes  |
  //Idx        |   No   |   No   | No  |   No    | No  | Yes |  Yes  |    Yes    | Yes |  Yes  |
  //Sparse     |   No   |   No   | No  |   No    | No  | No  |  Yes  |    Yes    | Yes |  Yes  |
  //CplxSparse |   No   |   No   | No  |   No    | No  | No  |  No   |    Yes    | No  |  Yes  |
  //Mat        |   No   |   No   | No  |   No    | No  | No  |  No   |    No     | Yes |  Yes  |
  //CplxMat    |   No   |   No   | No  |   No    | No  | No  |  No   |    No     | No  |  Yes  |

  // hacky approx costs of conversion
  private static final double[][] SDataWeighted = new double[][] {//
      {1, 1, 1, 1, 0, 1, 1, 1, 1,  1 }, //
      {0, 1, 0, 1, 0, 0, 0, 1, 0,  1 }, //
      {0, 0, 1, 5, 0, 0, 5, 5, 7,  10 }, //
      {0, 0, 0, 1, 0, 0, 0, 5, 0,  10 }, //
      {0, 0, 0, 0, 1, 0, 0, 0, 0,  0 }, //
      {0, 0, 0, 0, 0, 1, 0, 0, 0,  0 }, //
      {0, 0, 0, 0, 0, 0, 1, 5, 20, 25 }, //
      {0, 0, 0, 0, 0, 0, 0, 1, 0,  25 }, //
      {0, 0, 0, 0, 0, 0, 0, 0, 1,  10 }, //
      {0, 0, 0, 0, 0, 0, 0, 0, 0,  1 } };
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
