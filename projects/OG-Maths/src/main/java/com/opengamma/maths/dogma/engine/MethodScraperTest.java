/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine;

import org.testng.annotations.Test;

import com.opengamma.maths.dogma.engine.language.InfixOperator;
import com.opengamma.maths.dogma.engine.matrixinfo.ConversionCostAdjacencyMatrixStore;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Plus;
import com.opengamma.maths.dogma.engine.operationstack.InfixOpChain;
import com.opengamma.maths.dogma.engine.operationstack.MethodScraperForInfixOperators;
import com.opengamma.maths.dogma.engine.operationstack.OperatorDictionaryPopulator;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * 
 */
public class MethodScraperTest {

  @Test
  public void filterTest() {
    MethodScraperForInfixOperators filter = new MethodScraperForInfixOperators();
    OperatorDictionaryPopulator<InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>> dict = new OperatorDictionaryPopulator<InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>>();
    InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>[][] functionTable = filter.availableMethodsForInfixOp(dict.getOperationsMap(), Plus.class);

    // hacky approx costs of conversion
    final double[][] FunctionEvalCosts = new double[][] {//
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },//
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },//
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },//
        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },//
        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },//
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },//
        {0, 0, 0, 0, 0, 0, 25, 0, 50, 0 },//
        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },//
        {0, 0, 0, 0, 0, 0, 50, 0, 100, 200 },//
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 200 } };
    //CSON
    InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>[][] foo;
    OGMatrix FunctionEvalCostsMatrix = new OGMatrix(FunctionEvalCosts);
    InfixOpChain[][] instructions = filter.computeFunctions(ConversionCostAdjacencyMatrixStore.getWeightedAdjacencyMatrix(), functionTable, FunctionEvalCostsMatrix);
  }

}
