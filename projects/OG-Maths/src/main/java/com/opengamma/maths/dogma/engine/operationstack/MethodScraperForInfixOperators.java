/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.operationstack;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionConfigProblem;
import com.opengamma.maths.dogma.engine.converters.ConversionPointers;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.dogma.engine.language.InfixOperator;
import com.opengamma.maths.dogma.engine.matrixinfo.MatrixTypeToIndexMap;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.util.tuple.Pair;

/**
 * Filters and processes way to convert from one type to another
 */
public class MethodScraperForInfixOperators {

  private static MethodScraperForInfixOperators s_instance;

  public MethodScraperForInfixOperators() {
  }

  public static MethodScraperForInfixOperators getInstance() {
    return s_instance;
  }

  // yes, object array
  private static Object[][] s_conversionFunctions = ConversionPointers.getConverters();

  /**
   * Computes the available methods for an infix operator, takes an operations dictionary getOperationsMap() type map which contains map of <operation,list of classes that do operation>
   * @param functionSet map from OperationsDictionary.getOperationsMap() based on the InfixOperator type
   * @param anInterface one of the keys in the functionSet map
   * @return InfixOperator table for the key provided in anInterface
   */
  public static InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>[][] availableMethodsForInfixOp(
      Map<Class<?>, Set<InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>>> functionSet, Class<?> anInterface) {
    @SuppressWarnings("unchecked")
    InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>[][] ret = new InfixOperator[10][10];
    // reflect on methods
    // lookup arg types
    // check arg types in map to ints
    // dump infix op code into array in right position
    Set<InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>> functions = functionSet.get(anInterface);
    if (functions != null) { // only run if there are actually some implementation of the inteface available
      Method[] m;
      Iterator<InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>> i = functions.iterator();
      while (i.hasNext()) {
        InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>> next = i.next();
        Class<?> current = next.getClass();
        m = current.getDeclaredMethods();
        // look for the eval method
        boolean found = false;
        for (int j = 0; j < m.length; j++) {
          if (m[j].getName().equals("eval")) {
            found = true;
            // arg types
            Class<?>[] argTypes = m[j].getParameterTypes();
            if (argTypes.length != 2) {
              throw new MathsExceptionConfigProblem("Eval() method has the incorrect number of arguments for an infix operator");
            }
            MatrixTypeToIndexMap.getInstance();
            int idx1 = MatrixTypeToIndexMap.getIndexFromClass(argTypes[0]);
            MatrixTypeToIndexMap.getInstance();
            int idx2 = MatrixTypeToIndexMap.getIndexFromClass(argTypes[1]);
            ret[idx1][idx2] = next;
            break;
          }
        }
        if (!found) {
          throw new MathsExceptionConfigProblem("Could not find eval() method");
        }
      }
    }
    return ret;
  }

  // Works out OpChains based on a function table and reachable pairs
  /**
   * @param conversionTableEvalCost the cost of converting between types
   * @param functionTable the available functions
   * @param functionTableEvalCost the relative cost of invoking a function
   * @return an array of InfixOpChains, one per admissible type pairing.
   */
  @SuppressWarnings("unchecked")
  public static InfixOpChain[][] computeFunctions(OGMatrix conversionTableEvalCost, InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>[][] functionTable,
      OGMatrix functionTableEvalCost) {

    // first element wise multiply the non null entries in the functionTable with their cost
    int m = functionTable.length;
    int n = functionTable[0].length;
    double[][] availabilityAndCostMatrix = new double[m][n];

    // this is hideous
    List<Pair<Integer, Integer>> functionArgPairings = new ArrayList<Pair<Integer, Integer>>();

    // walk, ew times, accumulate pairings
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (functionTable[i][j] != null) {
          availabilityAndCostMatrix[i][j] = functionTableEvalCost.getEntry(i, j);
          functionArgPairings.add(Pair.of(Integer.valueOf(i), Integer.valueOf(j)));
        }
      }
    }

    OGMatrix implexists = new OGMatrix(availabilityAndCostMatrix);
    System.out.println(implexists.toString());
    int typeArg1, typeArg2;
    double mincost, cost;
    Pair<Integer, Integer> bestPair = null, next;
    InfixOpChain[][] ret = new InfixOpChain[10][10];

    // for each type pair that has a nonzero weight (i.e. conversion is possible)...
    // if there is a function that has a prototype that matches, bung it in with null as NOP on the conversion lists
    // else compute the cost of doing the op as conv1+conv2+invoke and write out an OpChain
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (availabilityAndCostMatrix[i][j] != 0) {
          InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>> clazz = functionTable[i][j];
          ret[i][j] = new InfixOpChain(clazz);
        } else {
          Iterator<Pair<Integer, Integer>> it = functionArgPairings.iterator();
          mincost = Double.MAX_VALUE;
          while (it.hasNext()) { // walk list of
            next = it.next();
            typeArg1 = next.getFirst();
            typeArg2 = next.getSecond();
            if (conversionTableEvalCost.getEntry(i, typeArg1) != 0 && conversionTableEvalCost.getEntry(j, typeArg2) != 0) { // it's possible to convert
              cost = availabilityAndCostMatrix[i][j] + conversionTableEvalCost.getEntry(i, typeArg1) + conversionTableEvalCost.getEntry(j, typeArg2);
              if (cost < mincost) {
                mincost = cost;
                bestPair = next;
              }
            }
            if (bestPair != null) {
              // create InfixOpChain based on best found, they are lists opposed to class pointers because at some point we might want to be able to run chains of conversions
              InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>> clazz = functionTable[bestPair.getFirst()][bestPair.getSecond()];
              List<Converter<? super OGArray<? extends Number>>> chain1 = new ArrayList<Converter<? super OGArray<? extends Number>>>();
              List<Converter<? super OGArray<? extends Number>>> chain2 = new ArrayList<Converter<? super OGArray<? extends Number>>>();
              chain1.add((Converter<? super OGArray<? extends Number>>) s_conversionFunctions[i][bestPair.getFirst()]);
              chain2.add((Converter<? super OGArray<? extends Number>>) s_conversionFunctions[j][bestPair.getSecond()]);
              ret[i][j] = new InfixOpChain(chain1, chain2, clazz);
            }
          }
        }
      }
    }
    return ret;
  }
}
