/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionConfigProblem;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexScalar;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGRealScalar;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Provides a map between Matrix Classes and their address in jump tables
 */
public class MatrixTypeToIndexMap {

  private static MatrixTypeToIndexMap s_instance;

  MatrixTypeToIndexMap() {
  }

  public static MatrixTypeToIndexMap getInstance() {
    return s_instance;
  }

  private static Map<Class<?>, Integer> s_classToIntMap = new HashMap<Class<?>, Integer>();
  static {
    s_classToIntMap.put(OGRealScalar.class, 0);
    s_classToIntMap.put(OGComplexScalar.class, 1); // this is bad Complex should be a superset of Number then this mangle wouldn't have to happen
    s_classToIntMap.put(OGDiagonalMatrix.class, 2);
    s_classToIntMap.put(OGComplexDiagonalMatrix.class, 3);
    s_classToIntMap.put(OGPermutationMatrix.class, 4);
    s_classToIntMap.put(OGIndexMatrix.class, 5);
    s_classToIntMap.put(OGSparseMatrix.class, 6);
    s_classToIntMap.put(OGComplexSparseMatrix.class, 7);
    s_classToIntMap.put(OGMatrix.class, 8);
    s_classToIntMap.put(OGComplexMatrix.class, 9);
  }

  public static int getIndexFromClass(Class<?> array) {
    Integer ret = s_classToIntMap.get(array);
    if (ret != null) {
      return ret;
    } else {
      throw new MathsExceptionConfigProblem("Unknown maths type encountered");
    }
  }
}
