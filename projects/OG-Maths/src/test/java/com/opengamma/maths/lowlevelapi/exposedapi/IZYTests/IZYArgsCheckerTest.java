/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.IZYTests;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.lowlevelapi.exposedapi.IZY;
import com.opengamma.maths.lowlevelapi.exposedapi.IZY.backing;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;


/**
 * Args Checker Test for IZY
 */
@Test
public class IZYArgsCheckerTest {

  static IZY izy = new IZY(backing.Referencejava); // java one is fine, nothing is really "run"

  // these are used to smell out the signatures of the types 
  static Class<?> intPtrClass;
  static Class<?> dblePtrClass;
  static Class<?> dblePtrPtrClass;
  static Class<?> stringClass;
  static Class<?> classPtrClass;
  static {
    try {
      intPtrClass = Class.forName("[I");
      dblePtrClass = Class.forName("[D");
      stringClass = Class.forName("java.lang.String");
      classPtrClass = Class.forName("[Ljava.lang.Class;");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // checks that exceptions thrown are from IZY or "Catchers" called by IZY
  static Throwable checkExceptionCameFromIZY(String methodName, Exception e) throws Throwable {
    StackTraceElement[] st = e.getCause().getStackTrace();
    if (st.length == 0) {
      throw new MathsExceptionGeneric("Stack has zero length, something has gone very wrong, aborting");
    }
    if (st.length < 2) {
      throw new MathsExceptionGeneric("Stack is bizarrely short, something has gone very wrong, aborting");
    }
    if (st[0].getClassName().equals(IZY.class.getCanonicalName()) || (st[0].getClassName().equals(Catchers.class.getCanonicalName()) && st[1].getClassName().equals(IZY.class.getCanonicalName()))) {
      throw e.getCause();
    } else {
      throw new MathsExceptionGeneric("Exception thrown was not thrown in IZY when it should have been. IZY catch mechanism is failing for function " + methodName);
    }
  }

  // the functions
  String[] rvrv_names = new String[] {"vd_abs", "vd_acos", "vd_acosh", "vd_asin", "vd_asinh", "vd_atan", "vd_atanh", "vd_cbrt", "vd_cdfnorm", "vd_cdfnorminv", "vd_ceil", "vd_cos", "vd_cosh",
      "vd_erf", "vd_erfc", "vd_erfcinv", "vd_erfinv", "vd_exp", "vd_expm1", "vd_floor", "vd_inv", "vd_invcbrt", "vd_invsqrt", "vd_lgamma", "vd_ln", "vd_log10", "vd_log1p", "vd_nearbyint",
      "vd_negate", "vd_pow2o3", "vd_pow3o2", "vd_rint", "vd_round", "vd_sin", "vd_sinh", "vd_sqr", "vd_sqrt", "vd_tan", "vd_tanh", "vd_tgamma", "vd_trunc" };
  String[] rvrvrv_names = new String[] {"vd_add", "vd_atan2", "vd_div", "vd_hypot", "vd_modf", "vd_mul", "vd_pow", "vd_sincos", "vd_sub" };
  String[] rvrsrv_names = new String[] {"vd_addx", "vd_divx", "vd_mulx", "vd_powx", "vd_subx" };
  String[] rsrvrv_names = new String[] {"vd_xsub", "vd_xdiv" };
  String[] cvcv_names = new String[] {"vz_acos", "vz_acosh", "vz_asin", "vz_asinh", "vz_atan", "vz_atanh", "vz_conj", "vz_cos", "vz_cosh", "vz_exp", "vz_ln", "vz_log10", "vz_negate", "vz_negatereal",
      "vz_sin", "vz_sinh", "vz_sqrt", "vz_tan", "vz_tanh" };
  String[] cvrv_names = new String[] {"vz_abs", "vz_arg" };
  String[] cvcvcv_names = new String[] {"vz_add", "vz_div", "vz_mul", "vz_mulbyconj", "vz_pow", "vz_sub", };
  String[] cvcscv_names = new String[] {"vz_addx", "vz_divx", "vz_mulx", "vz_powx", "vz_subx", };
  String[] cscvcv_names = new String[] {"vz_xdiv", "vz_xsub" };

  // arg lists
  Class<?>[] argListIDIDI = new Class[] {intPtrClass, dblePtrClass, intPtrClass, dblePtrClass, intPtrClass };
  Class<?>[] argListIDIDIDI = new Class[] {intPtrClass, dblePtrClass, intPtrClass, dblePtrClass, intPtrClass, dblePtrClass, intPtrClass };

  // consts
  static int[] intNull = null;
  static double[] doubleNull = null;
  static int[] intZero = new int[] {0 };
  static int[] intOne = new int[] {1 };
  static int[] intNegOne = new int[] {-1 };
  static int[] intTwo = new int[] {2 };
  static int[] intFive = new int[] {5 };
  static int[] intTen = new int[] {10 };

  // send in NaNs, methods don't check for NaNs and won't do things like whine about range errors on NaN
  static double[] realData0 = new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN };
  static double[] realData1 = new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN };
  static double[] complexData0 = new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN };
  static double[] complexData1 = new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN };
  static double[] complexDataInvalid = new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN };
  static double[] realAns = new double[4];
  static double[] complexAns = new double[8];

  // these are the blobs to pass to the function that runs the test
  @DataProvider
  private Object[][] fGroupNamesDataProvider() {
    Object[][] obj = new Object[9][];
    obj[0] = new Object[] {rvrv_names, test_rvrv.class, argListIDIDI };
    obj[1] = new Object[] {rvrvrv_names, test_rvrvrv.class, argListIDIDIDI };
    obj[2] = new Object[] {rvrsrv_names, test_rvrvrv.class, argListIDIDIDI };
    obj[3] = new Object[] {rsrvrv_names, test_rvrvrv.class, argListIDIDIDI };
    obj[4] = new Object[] {cvcv_names, test_cvcv.class, argListIDIDI };
    obj[5] = new Object[] {cvrv_names, test_cvrv.class, argListIDIDI };
    obj[6] = new Object[] {cvcvcv_names, test_cvcvcv.class, argListIDIDIDI };
    obj[7] = new Object[] {cvcscv_names, test_cvcvcv.class, argListIDIDIDI };
    obj[8] = new Object[] {cscvcv_names, test_cvcvcv.class, argListIDIDIDI };
    return obj;
  }

  
  // this runs the test classes based on the structs coming from the above funcion
  @Factory(dataProvider = "fGroupNamesDataProvider")
  public Object[] run_test_classes(String[] methodNames, Class<?> testClass, Class<?>[] argList) {
    IZYArgsCheckerTest enclosingClass = new IZYArgsCheckerTest();
    Object[] obj = new Object[methodNames.length];
    Constructor<?> constr = null;
    try { // find the constuctor from the passed in "testClass" that matches the signature to test
      constr = testClass.getConstructor(new Class[] {IZYArgsCheckerTest.class, stringClass, classPtrClass });
    } catch (NoSuchMethodException | SecurityException ex) {
      ex.printStackTrace();
    }
    int ptr = 0;
    for (int k = 0; k < methodNames.length; k++) { // walk the method names passed in "methodNames" creating an instance of the inner test class via the constructor found above.
      try { 
        obj[ptr] = constr.newInstance(enclosingClass, methodNames[k], argList);
                                    //      ^--- this arg is needed because we're instantiating inner classes via reflecion
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        ex.printStackTrace();
      }
      ptr++;
    }
    return obj;
  }

  // the test harness class, stores the common components of a test
  public class test_harness {
    Method _method_to_test;
    String methodName;

    public test_harness(String methname, Class<?>[] argList) {
      methodName = methname;
      System.out.println("Attempting to test " + methname);
      try {
        _method_to_test = izy.getClass().getMethod(methname, argList);
      } catch (NoSuchMethodException | SecurityException ex) {
        System.err.println("Failed to get pointer to method: " + methname);
        ex.printStackTrace();
      }
    }

    @Override
    public String toString() {
      return methodName;
    }

  }

  // tests real, real
  public class test_rvrv extends test_harness {
    public test_rvrv(String methname, Class<?>[] argList) {
      super(methname, argList);
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg1Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intNull, realData0, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, doubleNull, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg3Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intNull, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, doubleNull, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg5Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realAns, intNull);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessCountTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intTen, realData0, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeCountTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intNegOne, realData0, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnCountLengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, new int[3], realData0, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intFive, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intNegOne, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test()
    public void warnOffsetArg2LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, new int[3], realAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realAns, intFive);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realAns, intNegOne);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnOffsetArg4LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realAns, new int[3]);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }
  } // end inner class

  // tests  complex, complex
  public class test_cvcv extends test_harness {
    public test_cvcv(String methname, Class<?>[] argList) {
      super(methname, argList);
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg1Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intNull, complexData0, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, doubleNull, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg3Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intNull, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, doubleNull, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg5Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexAns, intNull);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessCountTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intTen, complexData0, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeCountTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intNegOne, complexData0, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnCountLengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, new int[3], complexData0, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intFive, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intNegOne, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void invalidArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexDataInvalid, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test()
    public void warnOffsetArg2LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, new int[3], complexAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexAns, intFive);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexAns, intNegOne);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void invalidArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexDataInvalid, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnOffsetArg4LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexAns, new int[3]);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }
  } // end inner class  

  // tests complex vector, real vector
  public class test_cvrv extends test_harness {
    public test_cvrv(String methname, Class<?>[] argList) {
      super(methname, argList);
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg1Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intNull, complexData0, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, doubleNull, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg3Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intNull, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, doubleNull, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg5Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, realAns, intNull);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessCountTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intTen, complexData0, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeCountTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intNegOne, complexData0, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnCountLengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, new int[3], complexData0, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intFive, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intNegOne, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void invalidArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexDataInvalid, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test()
    public void warnOffsetArg2LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, new int[3], realAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, realAns, intFive);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, realAns, intNegOne);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnOffsetArg4LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, realAns, new int[3]);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }
  } // end inner class  

  // tests real, real, real
  public class test_rvrvrv extends test_harness {
    public test_rvrvrv(String methname, Class<?>[] argList) {
      super(methname, argList);
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg1Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intNull, realData0, intZero, realData1, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, doubleNull, intZero, realData1, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg3Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intNull, realData1, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, doubleNull, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg5Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realData1, intNull, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg6Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realData1, intZero, doubleNull, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg7Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realData1, intZero, realAns, intNull);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessCountTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intTen, realData0, intZero, realData1, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeCountTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intNegOne, realData0, intZero, realData1, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnCountLengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, new int[3], realData0, intZero, realData1, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intFive, realData1, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intNegOne, realData1, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnOffsetArg2LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, new int[3], realData1, intZero, realAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realData1, intFive, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realData1, intNegOne, realAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnOffsetArg4LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realData1, new int[3], realAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg6Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realData1, intZero, realAns, intFive);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg6Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realData1, intZero, realAns, intNegOne);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnOffsetArg6LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, realData0, intZero, realData1, intZero, realAns, new int[3]);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }
  } // end inner class

  // tests complex, complex, complex!
  public class test_cvcvcv extends test_harness {
    public test_cvcvcv(String methname, Class<?>[] argList) {
      super(methname, argList);
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg1Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intNull, complexData0, intZero, complexData1, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, doubleNull, intZero, complexData1, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg3Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intNull, complexData1, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, doubleNull, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg5Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexData1, intNull, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg6Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexData1, intZero, doubleNull, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionNullPointer.class, MathsExceptionNotImplemented.class })
    public void nullArg7Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexData1, intZero, complexAns, intNull);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessCountTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intTen, complexData0, intZero, complexData1, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeCountTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intNegOne, complexData0, intZero, complexData1, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnCountLengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, new int[3], complexData0, intZero, complexData1, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intFive, complexData1, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intNegOne, complexData1, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void invalidArg2Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexDataInvalid, intZero, complexData1, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnOffsetArg2LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, new int[3], complexData1, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexData1, intFive, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexData1, intNegOne, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void invalidArg4Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexDataInvalid, intZero, complexAns, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnOffsetArg4LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexData1, new int[3], complexAns, intZero);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void excessOffsetArg6Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexData1, intZero, complexAns, intFive);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void negativeOffsetArg6Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexData1, intZero, complexAns, intNegOne);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test(expectedExceptions = {MathsExceptionIllegalArgument.class, MathsExceptionNotImplemented.class })
    public void invalidArg6Test() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexData1, intZero, complexDataInvalid, intZero);
      } catch (InvocationTargetException e) {
        throw checkExceptionCameFromIZY(methodName, e);
      }
    }

    @Test
    public void warnOffsetArg6LengthTest() throws Throwable {
      try {
        _method_to_test.invoke(izy, intOne, complexData0, intZero, complexData1, intZero, complexAns, new int[3]);
      } catch (InvocationTargetException e) {
        if (!(e.getCause() instanceof MathsExceptionNotImplemented)) {
          throw checkExceptionCameFromIZY(methodName, e);
        }
      }
    }
  } // end inner class

}
