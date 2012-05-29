/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.checkers;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;

/**
 * Catch things that will cause problems
 */
public class Catchers {

  // general complaints about null

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param varName the literal name of the variable so that the error string makes sense 
   */
  public static void catchNull(boolean[] thisArray, String varName) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array " + varName + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   */
  public static void catchNull(boolean[] thisArray) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param varName the literal name of the variable so that the error string makes sense 
   */
  public static void catchNull(byte[] thisArray, String varName) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array " + varName + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   */
  public static void catchNull(byte[] thisArray) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param varName the literal name of the variable so that the error string makes sense 
   */
  public static void catchNull(short[] thisArray, String varName) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array " + varName + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   */
  public static void catchNull(short[] thisArray) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param varName the literal name of the variable so that the error string makes sense 
   */
  public static void catchNull(int[] thisArray, String varName) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array " + varName + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   */
  public static void catchNull(int[] thisArray) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param varName the literal name of the variable so that the error string makes sense 
   */
  public static void catchNull(long[] thisArray, String varName) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array " + varName + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   */
  public static void catchNull(long[] thisArray) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param varName the literal name of the variable so that the error string makes sense 
   */
  public static void catchNull(double[] thisArray, String varName) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array " + varName + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   */
  public static void catchNull(double[] thisArray) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param varName the literal name of the variable so that the error string makes sense 
   */
  public static void catchNull(float[] thisArray, String varName) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array " + varName + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   */
  public static void catchNull(float[] thisArray) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Array points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param someBlob an object to check
   * @param varName the literal name of the variable so that the error string makes sense 
   */
  public static void catchNull(Object someBlob, String varName) {
    if (someBlob == null) {
      throw new MathsExceptionNullPointer("Object " + someBlob + " points to NULL. STOPPING");
    }
  }

  ///////////// Null from arg list
  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param pos the position in the arg list
   */
  public static void catchNullFromArgList(boolean[] thisArray, int pos) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Argument passed in position " + pos + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param pos the position in the arg list
   */
  public static void catchNullFromArgList(short[] thisArray, int pos) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Argument passed in position " + pos + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param pos the position in the arg list
   */
  public static void catchNullFromArgList(int[] thisArray, int pos) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Argument passed in position " + pos + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param pos the position in the arg list
   */
  public static void catchNullFromArgList(long[] thisArray, int pos) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Argument passed in position " + pos + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param pos the position in the arg list
   */
  public static void catchNullFromArgList(float[] thisArray, int pos) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Argument passed in position " + pos + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param thisArray the array to check
   * @param pos the position in the arg list
   */
  public static void catchNullFromArgList(double[] thisArray, int pos) {
    if (thisArray == null) {
      throw new MathsExceptionNullPointer("Argument passed in position " + pos + " points to NULL. STOPPING");
    }
  }

  /**
   * Catches null pointer
   * @param someBlob the object to check
   * @param pos the position in the arg list
   */
  public static void catchNullFromArgList(Object someBlob, int pos) {
    if (someBlob == null) {
      throw new MathsExceptionNullPointer("Argument passed in position " + pos + " points to NULL. STOPPING");
    }
  }

  //////////////// commute catch

  /**
   * Catches bad commutes, given two dimensions, if they are not the same an exception is thrown 
   * @param dim1 the first dimension
   * @param varName1 the literal name of the first variable so that the error string makes sense
   * @param dim2 the second dimension
   * @param varName2 the literal name of the second variable so that the error string makes sense
   */
  public static void catchBadCommute(int dim1, String varName1, int dim2, String varName2) {
    if (dim1 != dim2) {
      throw new MathsExceptionIllegalArgument(varName1 + " and + " + varName2 + " do not commute. STOPPING");
    }
  }

}
