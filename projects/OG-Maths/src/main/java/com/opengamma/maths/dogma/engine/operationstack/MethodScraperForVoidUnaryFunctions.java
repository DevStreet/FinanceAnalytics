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
import com.opengamma.maths.dogma.engine.language.VoidUnaryFunction;
import com.opengamma.maths.dogma.engine.matrixinfo.MatrixTypeToIndexMap;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Filters and processes way to convert from one type to another
 */
public class MethodScraperForVoidUnaryFunctions {

  private static MethodScraperForVoidUnaryFunctions s_instance;

  public MethodScraperForVoidUnaryFunctions() {
  }

  public static MethodScraperForVoidUnaryFunctions getInstance() {
    return s_instance;
  }

  // yes, object array
  private static Object[][] s_conversionFunctions = ConversionPointers.getConverters();

  /**
   * Computes the available methods for an infix operator, takes an operations dictionary getOperationsMap() type map which contains map of <operation,list of classes that do operation>
   * @param functionSet map from OperationsDictionary.getOperationsMap() based on the UnaryFunction type
   * @param anInterface one of the keys in the functionSet map
   * @return UnaryFunction table for the key provided in anInterface
   */
  public static VoidUnaryFunction<OGArray<? extends Number>>[] availableMethodsForVoidUnaryFunctions(
      Map<Class<?>, Set<VoidUnaryFunction<OGArray<? extends Number>>>> functionSet, Class<?> anInterface) {
    @SuppressWarnings("unchecked")
    VoidUnaryFunction<OGArray<? extends Number>>[] ret = new VoidUnaryFunction[10];
    // reflect on methods
    // lookup arg types
    // check arg types in map to ints
    // dump infix op code into array in right position
    Set<VoidUnaryFunction<OGArray<? extends Number>>> functions = functionSet.get(anInterface);
    if (functions != null) { // only run if there are actually some implementation of the inteface available
      Method[] m;
      Iterator<VoidUnaryFunction<OGArray<? extends Number>>> i = functions.iterator();
      while (i.hasNext()) {
        VoidUnaryFunction<OGArray<? extends Number>> next = i.next();
        Class<?> current = next.getClass();
        m = current.getDeclaredMethods();
        // look for the eval method
        boolean found = false;
        for (int j = 0; j < m.length; j++) {
          if (m[j].getName().equals("eval")) {
            found = true;
            // arg types
            Class<?>[] argTypes = m[j].getParameterTypes();
            if (argTypes.length != 1) {
              throw new MathsExceptionConfigProblem("Eval() method has the incorrect number of arguments for an infix operator");
            }
            MatrixTypeToIndexMap.getInstance();
            int idx1 = MatrixTypeToIndexMap.getIndexFromClass(argTypes[0]);
            ret[idx1] = next;
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
  public static VoidUnaryFunctionChain[] computeFunctions(OGMatrix conversionTableEvalCost, VoidUnaryFunction<OGArray<? extends Number>>[] functionTable,
      OGMatrix functionTableEvalCost) {

    // first element wise multiply the non null entries in the functionTable with their cost
    int n = functionTable.length;
    double[] availabilityAndCostMatrix = new double[n];

    // this is hideous
    int[] functionArgHasMatch = new int[n];

    // walk, ew times, accumulate pairings
    for (int i = 0; i < n; i++) {
      functionArgHasMatch[i] = -1; // signal no pairing
      if (functionTable[i] != null) {
        //        System.out.println("Fn tab[" + i + "]=" + functionTable[i].toString());
        availabilityAndCostMatrix[i] = functionTableEvalCost.getEntry(i, 0);
        functionArgHasMatch[i] = i;
      }
    }

    int typeArg1;
    double mincost, cost;
    int best;
    VoidUnaryFunctionChain[] ret = new VoidUnaryFunctionChain[10];

    //    System.out.println("functionArgHasMatch=" + Arrays.toString(functionArgHasMatch));

    // for each type pair that has a nonzero weight (i.e. conversion is possible)...
    // if there is a function that has a prototype that matches, bung it in with null as NOP on the conversion lists
    // else compute the cost of doing the op as conv1+conv2+invoke and write out an OpChain
    for (int i = 0; i < n; i++) {
      if (availabilityAndCostMatrix[i] != 0) {
        VoidUnaryFunction<OGArray<? extends Number>> clazz = functionTable[i];
        ret[i] = new VoidUnaryFunctionChain(clazz);
      } else {
        best = -1;
        mincost = Double.MAX_VALUE;
        for (int j = 0; j < n; j++) {
          if (functionArgHasMatch[j] != -1) {
            typeArg1 = functionArgHasMatch[j];
            if (s_conversionFunctions[i][typeArg1] != null) { // it's possible to convert
              cost = availabilityAndCostMatrix[i] + conversionTableEvalCost.getEntry(i, typeArg1);
              if (cost < mincost) {
                mincost = cost;
                best = j;
              }
            }
          }
        }
        if (best != -1) {
          // create InfixOpChain based on best found, they are lists opposed to class pointers because at some point we might want to be able to run chains of conversions
          VoidUnaryFunction<OGArray<? extends Number>> clazz = functionTable[best];
          List<Converter<? super OGArray<? extends Number>>> chain1 = new ArrayList<Converter<? super OGArray<? extends Number>>>();
          chain1.add((Converter<? super OGArray<? extends Number>>) s_conversionFunctions[i][best]);
          ret[i] = new VoidUnaryFunctionChain(chain1, clazz);
        }
      }
    }
    return ret;
  }
}
