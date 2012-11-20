/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine;

import com.opengamma.maths.dogma.engine.language.UnaryFunction;
import com.opengamma.maths.dogma.engine.matrixinfo.ConversionCostAdjacencyMatrixStore;
import com.opengamma.maths.dogma.engine.matrixinfo.MatrixTypeToIndexMap;
import com.opengamma.maths.dogma.engine.methodhookinstances.Sin;
import com.opengamma.maths.dogma.engine.operationstack.MethodScraperForUnaryFunctions;
import com.opengamma.maths.dogma.engine.operationstack.OperatorDictionaryPopulator;
import com.opengamma.maths.dogma.engine.operationstack.RunUnaryFunctionChain;
import com.opengamma.maths.dogma.engine.operationstack.UnaryFunctionChain;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Maps unary functions to op chains
 */
public class DOGMAUnaryOps {

  // single
  private static DOGMAUnaryOps s_instance;

  DOGMAUnaryOps() {
  }

  public static DOGMAUnaryOps getInstance() {
    return s_instance;
  }

  private static UnaryFunctionChain[] s_sinInstructions;

  private static RunUnaryFunctionChain s_runner = new RunUnaryFunctionChain();
  static {
    // hacky approx costs of evaluation
    final double[][] FunctionEvalCosts = new double[][] {//
    {1 },//
        {1 },//
        {2 },//
        {3 },//
        {3 },//
        {5 },//
        {5 },//
        {5 },//
        {10 },//
        {20 } };
    OGMatrix functionEvalCostsMatrix = new OGMatrix(FunctionEvalCosts);
    OperatorDictionaryPopulator<UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>>> operatorDict = new OperatorDictionaryPopulator<UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>>>();
    UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>>[] sinFunctionTable = MethodScraperForUnaryFunctions.availableMethodsForUnaryFunctions(operatorDict.getOperationsMap(),
        Sin.class);

    s_sinInstructions = MethodScraperForUnaryFunctions.computeFunctions(ConversionCostAdjacencyMatrixStore.getWeightedAdjacencyMatrix(), sinFunctionTable, functionEvalCostsMatrix);

  }

  // generates all the functions  
  public static OGArray<? extends Number> sin(OGArray<? extends Number> arg1) {
    int type1 = MatrixTypeToIndexMap.getIndexFromClass(arg1.getClass());
    OGArray<? extends Number> tmp = s_runner.dispatch(s_sinInstructions[type1], arg1);
    return tmp;
  }

}
