/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.exposedapi.IZYBacking.IZYAPIInterface;
import com.opengamma.maths.lowlevelapi.exposedapi.IZYBacking.IZYAbstractSuper;
import com.opengamma.maths.lowlevelapi.exposedapi.IZYBacking.IZYNativeBacked;
import com.opengamma.maths.lowlevelapi.exposedapi.IZYBacking.IZYReferenceJavaBacked;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Provides a unified interface to IZY
 */
public class IZY implements IZYAPIInterface {

  private static Logger s_log = LoggerFactory.getLogger(IZY.class);

  private IZYAbstractSuper _localIZY;

  private static IZY s_instance = new IZY();

  /**
   * Get the default IZY instance
   * @return the default IZY instance
   */
  public static IZY getInstance() {
    return s_instance;
  }

  /**
   * Backing type
   */
  public enum backing {
    /**
     * Reference Java backed BLAS
     */
    Referencejava,
    /**
     * OG Native backed BLAS
     */
    OGnative,
  }

  public IZY() {
    _localIZY = new IZYNativeBacked();
  }

  public IZY(backing backedby) {
    if (backedby == backing.Referencejava) {
      _localIZY = new IZYReferenceJavaBacked();
    } else if (backedby == backing.OGnative) {
      _localIZY = new IZYNativeBacked();
    }
  }
  
  /**
   * Checks the args to real domain izy calls of form f(vector,vector), before staging a call to the referenced izy implementation
   * @param count an int[], dereferencing the first entry yields the number of elements over which the computation shall take place
   * @param arg0 the first arg
   * @param offsetarg0 an int[], dereferencing the first entry yields the offset into arg0 at which the computation shall take place
   * @param out0 the pointer to the output
   * @param offsetout0 an int[], dereferencing the first entry yields the offset into out0 at which the computation shall take place
   */
  // catches buffer overflow and null pointer so that they don't end up in JNI land, yay branches :s
  private static void check_real_izy_rvrv(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    Catchers.catchNullFromArgList(count, 1);
    Catchers.catchNullFromArgList(arg0, 2);
    Catchers.catchNullFromArgList(offsetarg0, 3);
    Catchers.catchNullFromArgList(out0, 4);
    Catchers.catchNullFromArgList(offsetout0, 5);
    int n = count[0];
    int arg0len = arg0.length;
    int out0len = out0.length;
    helper_check_validity_of_count(n, count);
    helper_check_real_arg_is_valid(n, arg0len, offsetarg0, "arg0");
    helper_check_real_arg_is_valid(n, out0len, offsetout0, "out0");
  };

  /**
   * Checks the args to complex domain izy calls of form f(vector,vector), before staging a call to the referenced izy implementation
   * @param count an int[], dereferencing the first entry yields the number of elements over which the computation shall take place
   * @param arg0 the first arg
   * @param offsetarg0 an int[], dereferencing the first entry yields the offset into arg0 at which the computation shall take place
   * @param out0 the pointer to the output
   * @param offsetout0 an int[], dereferencing the first entry yields the offset into out0 at which the computation shall take place
   */
  // catches buffer overflow and null pointer so that they don't end up in JNI land, yay branches :s
  private static void check_complex_izy_cvcv(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    Catchers.catchNullFromArgList(count, 1);
    Catchers.catchNullFromArgList(arg0, 2);
    Catchers.catchNullFromArgList(offsetarg0, 3);
    Catchers.catchNullFromArgList(out0, 4);
    Catchers.catchNullFromArgList(offsetout0, 5);
    int n = count[0];
    int arg0len = arg0.length;
    int out0len = out0.length;
    helper_check_validity_of_count(n, count);
    helper_check_complex_arg_is_valid(n, arg0len, offsetarg0, "arg0");
    helper_check_complex_arg_is_valid(n, out0len, offsetout0, "out0");
  };

  /**
   * Checks the args to complex domain izy calls of form f(vector,vector), before staging a call to the referenced izy implementation
   * @param count an int[], dereferencing the first entry yields the number of elements over which the computation shall take place
   * @param arg0 the first arg
   * @param offsetarg0 an int[], dereferencing the first entry yields the offset into arg0 at which the computation shall take place
   * @param out0 the pointer to the output
   * @param offsetout0 an int[], dereferencing the first entry yields the offset into out0 at which the computation shall take place
   */
  // catches buffer overflow and null pointer so that they don't end up in JNI land, yay branches :s
  private static void check_complex_izy_cvrv(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    Catchers.catchNullFromArgList(count, 1);
    Catchers.catchNullFromArgList(arg0, 2);
    Catchers.catchNullFromArgList(offsetarg0, 3);
    Catchers.catchNullFromArgList(out0, 4);
    Catchers.catchNullFromArgList(offsetout0, 5);
    int n = count[0];
    int arg0len = arg0.length;
    int out0len = out0.length;
    helper_check_validity_of_count(n, count);
    helper_check_complex_arg_is_valid(n, arg0len, offsetarg0, "arg0");
    helper_check_real_arg_is_valid(n, out0len, offsetout0, "out0");
  };

  /**
   * Checks the args to real domain izy calls of f(vector,vector,vector), before staging a call to the referenced izy implementation
   * @param count an int[], dereferencing the first entry yields the number of elements over which the computation shall take place
   * @param arg0 the first arg
   * @param offsetarg0 an int[], dereferencing the first entry yields the offset into arg0 at which the computation shall take place
   * @param arg1 the second arg
   * @param offsetarg1 an int[], dereferencing the first entry yields the offset into arg1 at which the computation shall take place 
   * @param out0 the pointer to the output
   * @param offsetout0 an int[], dereferencing the first entry yields the offset into out0 at which the computation shall take place
   */
  // catches buffer overflow and null pointer so that they don't end up in JNI land, yay branches :s
  private static void check_real_izy_rvrvrv(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    Catchers.catchNullFromArgList(count, 1);
    Catchers.catchNullFromArgList(arg0, 2);
    Catchers.catchNullFromArgList(offsetarg0, 3);
    Catchers.catchNullFromArgList(arg1, 4);
    Catchers.catchNullFromArgList(offsetarg1, 5);
    Catchers.catchNullFromArgList(out0, 6);
    Catchers.catchNullFromArgList(offsetout0, 7);
    int n = count[0];
    int arg0len = arg0.length;
    int arg1len = arg1.length;
    int out0len = out0.length;
    helper_check_validity_of_count(n, count);
    helper_check_real_arg_is_valid(n, arg0len, offsetarg0, "arg0");
    helper_check_real_arg_is_valid(n, arg1len, offsetarg1, "arg1");
    helper_check_real_arg_is_valid(n, out0len, offsetout0, "out0");
  };

  /**
   * Checks the args to complex domain izy calls of f(vector,vector,vector), before staging a call to the referenced izy implementation
   * @param count an int[], dereferencing the first entry yields the number of elements over which the computation shall take place
   * @param arg0 the first arg
   * @param offsetarg0 an int[], dereferencing the first entry yields the offset into arg0 at which the computation shall take place
   * @param arg1 the second arg
   * @param offsetarg1 an int[], dereferencing the first entry yields the offset into arg1 at which the computation shall take place 
   * @param out0 the pointer to the output
   * @param offsetout0 an int[], dereferencing the first entry yields the offset into out0 at which the computation shall take place
   */
  // catches buffer overflow and null pointer so that they don't end up in JNI land, yay branches :s
  private static void check_complex_izy_cvcvcv(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    Catchers.catchNullFromArgList(count, 1);
    Catchers.catchNullFromArgList(arg0, 2);
    Catchers.catchNullFromArgList(offsetarg0, 3);
    Catchers.catchNullFromArgList(arg1, 4);
    Catchers.catchNullFromArgList(offsetarg1, 5);
    Catchers.catchNullFromArgList(out0, 6);
    Catchers.catchNullFromArgList(offsetout0, 7);
    int n = count[0];
    int arg0len = arg0.length;
    int arg1len = arg1.length;
    int out0len = out0.length;
    helper_check_validity_of_count(n, count);
    helper_check_complex_arg_is_valid(n, arg0len, offsetarg0, "arg0");
    helper_check_complex_arg_is_valid(n, arg1len, offsetarg1, "arg1");
    helper_check_complex_arg_is_valid(n, out0len, offsetout0, "out0");
  };

  /**
   * Checks the args to real domain izy calls of form f(vector,scalar,vector), before staging a call to the referenced izy implementation
   * @param count an int[], dereferencing the first entry yields the number of elements over which the computation shall take place
   * @param arg0 the first arg
   * @param offsetarg0 an int[], dereferencing the first entry yields the offset into arg0 at which the computation shall take place
   * @param arg1 the second arg
   * @param offsetarg1 an int[], dereferencing the first entry yields the offset into arg1 at which the computation shall take place 
   * @param out0 the pointer to the output
   * @param offsetout0 an int[], dereferencing the first entry yields the offset into out0 at which the computation shall take place
   */
  // catches buffer overflow and null pointer so that they don't end up in JNI land, yay branches :s
  private static void check_real_izy_rvrsrv(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    Catchers.catchNullFromArgList(count, 1);
    Catchers.catchNullFromArgList(arg0, 2);
    Catchers.catchNullFromArgList(offsetarg0, 3);
    Catchers.catchNullFromArgList(arg1, 4);
    Catchers.catchNullFromArgList(offsetarg1, 5);
    Catchers.catchNullFromArgList(out0, 6);
    Catchers.catchNullFromArgList(offsetout0, 7);
    int n = count[0];
    int arg0len = arg0.length;
    int arg1len = arg1.length;
    int out0len = out0.length;
    helper_check_validity_of_count(n, count);
    helper_check_real_arg_is_valid(n, arg0len, offsetarg0, "arg0");
    helper_check_real_const_arg_is_valid(n, arg1len, offsetarg1, "arg1");
    helper_check_real_arg_is_valid(n, out0len, offsetout0, "out0");
  };

  /**
   * Checks the args to complex domain izy calls of form f(vector,scalar,vector), before staging a call to the referenced izy implementation
   * @param count an int[], dereferencing the first entry yields the number of elements over which the computation shall take place
   * @param arg0 the first arg
   * @param offsetarg0 an int[], dereferencing the first entry yields the offset into arg0 at which the computation shall take place
   * @param arg1 the second arg
   * @param offsetarg1 an int[], dereferencing the first entry yields the offset into arg1 at which the computation shall take place 
   * @param out0 the pointer to the output
   * @param offsetout0 an int[], dereferencing the first entry yields the offset into out0 at which the computation shall take place
   */
  // catches buffer overflow and null pointer so that they don't end up in JNI land, yay branches :s
  private static void check_complex_izy_cvcscv(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    Catchers.catchNullFromArgList(count, 1);
    Catchers.catchNullFromArgList(arg0, 2);
    Catchers.catchNullFromArgList(offsetarg0, 3);
    Catchers.catchNullFromArgList(arg1, 4);
    Catchers.catchNullFromArgList(offsetarg1, 5);
    Catchers.catchNullFromArgList(out0, 6);
    Catchers.catchNullFromArgList(offsetout0, 7);
    int n = count[0];
    int arg0len = arg0.length;
    int arg1len = arg1.length;
    int out0len = out0.length;
    helper_check_validity_of_count(n, count);
    helper_check_complex_arg_is_valid(n, arg0len, offsetarg0, "arg0");
    helper_check_complex_const_arg_is_valid(n, arg1len, offsetarg1, "arg1");
    helper_check_complex_arg_is_valid(n, out0len, offsetout0, "out0");
  };

  /**
   * Checks the args to real domain izy calls of form f(scalar,vector,vector), before staging a call to the referenced izy implementation
   * @param count an int[], dereferencing the first entry yields the number of elements over which the computation shall take place
   * @param arg0 the first arg
   * @param offsetarg0 an int[], dereferencing the first entry yields the offset into arg0 at which the computation shall take place
   * @param arg1 the second arg
   * @param offsetarg1 an int[], dereferencing the first entry yields the offset into arg1 at which the computation shall take place 
   * @param out0 the pointer to the output
   * @param offsetout0 an int[], dereferencing the first entry yields the offset into out0 at which the computation shall take place
   */
  // catches buffer overflow and null pointer so that they don't end up in JNI land, yay branches :s
  private static void check_real_izy_rsrvrv(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    Catchers.catchNullFromArgList(count, 1);
    Catchers.catchNullFromArgList(arg0, 2);
    Catchers.catchNullFromArgList(offsetarg0, 3);
    Catchers.catchNullFromArgList(arg1, 4);
    Catchers.catchNullFromArgList(offsetarg1, 5);
    Catchers.catchNullFromArgList(out0, 6);
    Catchers.catchNullFromArgList(offsetout0, 7);
    int n = count[0];
    int arg0len = arg0.length;
    int arg1len = arg1.length;
    int out0len = out0.length;
    helper_check_validity_of_count(n, count);
    helper_check_real_const_arg_is_valid(n, arg0len, offsetarg0, "arg0");
    helper_check_real_arg_is_valid(n, arg1len, offsetarg1, "arg");
    helper_check_real_arg_is_valid(n, out0len, offsetout0, "out0");
  };

  /**
   * Checks the args to complex domain izy calls of form f(scalar,vector,vector), before staging a call to the referenced izy implementation
   * @param count an int[], dereferencing the first entry yields the number of elements over which the computation shall take place
   * @param arg0 the first arg
   * @param offsetarg0 an int[], dereferencing the first entry yields the offset into arg0 at which the computation shall take place
   * @param arg1 the second arg
   * @param offsetarg1 an int[], dereferencing the first entry yields the offset into arg1 at which the computation shall take place 
   * @param out0 the pointer to the output
   * @param offsetout0 an int[], dereferencing the first entry yields the offset into out0 at which the computation shall take place
   */
  // catches buffer overflow and null pointer so that they don't end up in JNI land, yay branches :s
  private static void check_complex_izy_cscvcv(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    Catchers.catchNullFromArgList(count, 1);
    Catchers.catchNullFromArgList(arg0, 2);
    Catchers.catchNullFromArgList(offsetarg0, 3);
    Catchers.catchNullFromArgList(arg1, 4);
    Catchers.catchNullFromArgList(offsetarg1, 5);
    Catchers.catchNullFromArgList(out0, 6);
    Catchers.catchNullFromArgList(offsetout0, 7);
    int n = count[0];
    int arg0len = arg0.length;
    int arg1len = arg1.length;
    int out0len = out0.length;
    helper_check_validity_of_count(n, count);
    helper_check_complex_const_arg_is_valid(n, arg0len, offsetarg0, "arg0");
    helper_check_complex_arg_is_valid(n, arg1len, offsetarg1, "arg");
    helper_check_complex_arg_is_valid(n, out0len, offsetout0, "out0");
  };

  // helper functions for the checkers, hopefully will be inlined
  /**
   * Checks the validity of the variable "count"
   * @param n the value from the dereferenced pointer
   * @param count the pointer to count
   */
  private static void helper_check_validity_of_count(int n, int[] count) {
    if (n < 0) {
      throw new MathsExceptionIllegalArgument("Illegal count, n=" + n + ". Cannot perform a negative number of computations");
    }
    if (count.length > 1) {
      s_log.warn("The variable \"count\" has length greater than 1, whilst this is perfectly valid, it may not have been intended!");
    }
  }

  /**
   * Checks a real domain argument is valid
   * @param count the number of elements to compute over
   * @param arglen the length of the arg over which the computation will take place
   * @param offset the offset into the arg
   * @param varname the name of the arg in codified form
   */
  private static void helper_check_real_arg_is_valid(int count, int arglen, int[] offset, String varname) {
    int os = offset[0];
    if (os < 0) {
      throw new MathsExceptionIllegalArgument("Illegal offset for " + varname + ", offset=" + offset + ". Cannot perform a negative number of computations");
    }
    if (os + count > arglen) {
      throw new MathsExceptionIllegalArgument("Insufficient data in " + varname + " for the given count and offset. Offending values are: count=" + count + " offset=" + os + " length(arg)=" + arglen);
    }
    if (offset.length > 1) {
      s_log.warn("The offset associated with variable \"" + varname + "\" has length greater than 1, whilst this is perfectly valid, it may not have been intended!");
    }
  }

  /**
   * Checks a complex domain argument is valid, java doesn't have complex as built in so we have to ensure the double[] arrays are valid as interleaved complex
   * @param count the number of elements to compute over
   * @param arglen the length of the arg over which the computation will take place
   * @param offset the offset into the arg
   * @param varname the name of the arg in codified form
   */
  private static void helper_check_complex_arg_is_valid(int count, int arglen, int[] offset, String varname) {
    int os = offset[0];
    if (os < 0) {
      throw new MathsExceptionIllegalArgument("Illegal offset for " + varname + ", offset=" + offset + ". Cannot perform a negative number of computations");
    }
    if (arglen % 2 != 0) {
      throw new MathsExceptionIllegalArgument(
          "Invalid interleaved complex representation encountered, this data representation should have an even number of elements as a double pointer, offending variable is " + varname);
    }
    if (os + count > arglen / 2) {
      throw new MathsExceptionIllegalArgument("Insufficient data in " + varname + " for the given count and offset. Offending values are: count=" + count + " offset=" + os + " complex length(arg)=" +
          arglen / 2);
    }
    if (offset.length > 1) {
      s_log.warn("The offset associated with variable \"" + varname + "\" has length greater than 1, whilst this is perfectly valid, it may not have been intended!");
    }
  }

  /**
   * Checks a real domain const argument is valid
   * @param count the number of elements to compute over
   * @param arglen the length of the arg over which the computation will take place
   * @param offset the offset into the arg
   * @param varname the name of the arg in codified form
   */
  private static void helper_check_real_const_arg_is_valid(int count, int arglen, int[] offset, String varname) {
    int os = offset[0];
    if (os < 0) {
      throw new MathsExceptionIllegalArgument("Illegal offset for " + varname + ", offset=" + offset + ". Cannot perform a negative number of computations");
    }
    if (os > arglen) {
      throw new MathsExceptionIllegalArgument("Insufficient data in " + varname + " for the given offset. Offending values are:s offset=" + os + " length(arg)=" + arglen);
    }
    if (offset.length > 1) {
      s_log.warn("The offset associated with variable \"" + varname + "\" has length greater than 1, whilst this is perfectly valid, it may not have been intended!");
    }
  }

  /**
   * Checks a complex domain const argument is valid
   * @param count the number of elements to compute over
   * @param arglen the length of the arg over which the computation will take place
   * @param offset the offset into the arg
   * @param varname the name of the arg in codified form
   */
  private static void helper_check_complex_const_arg_is_valid(int count, int arglen, int[] offset, String varname) {
    int os = offset[0];
    if (os < 0) {
      throw new MathsExceptionIllegalArgument("Illegal offset for " + varname + ", offset=" + offset + ". Cannot perform a negative number of computations");
    }
    if (arglen % 2 != 0) {
      throw new MathsExceptionIllegalArgument(
          "Invalid interleaved complex representation encountered, this data representation should have an even number of elements as a double pointer, offending variable is " + varname);
    }
    if (os > arglen / 2) {
      throw new MathsExceptionIllegalArgument("Insufficient data in " + varname + " for the given offset. Offending values are:s offset=" + os + " length(arg)=" + arglen);
    }
    if (offset.length > 1) {
      s_log.warn("The offset associated with variable \"" + varname + "\" has length greater than 1, whilst this is perfectly valid, it may not have been intended!");
    }
  }

  @Override
  public void vd_acos(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_acos(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_asin(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_asin(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_atan(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_atan(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_atan2(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrvrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_atan2(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_cos(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_cos(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_sin(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_sin(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_sincos(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0, double[] out1, int[] offsetout1) {
    check_real_izy_rvrvrv(count, arg0, offsetarg0, out0, offsetout0, out1, offsetout1);
    _localIZY.vd_sincos(count, arg0, offsetarg0, out0, offsetout0, out1, offsetout1);
  }

  @Override
  public void vd_tan(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_tan(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_acosh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_acosh(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_asinh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_asinh(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_atanh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_atanh(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_cosh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_cosh(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_sinh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_sinh(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_tanh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_tanh(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_cbrt(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_cbrt(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_hypot(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrvrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_hypot(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_inv(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_inv(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_invcbrt(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_invcbrt(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_invsqrt(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_invsqrt(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_pow(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrvrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_pow(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_powx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrsrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_powx(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_pow2o3(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_pow2o3(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_pow3o2(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_pow3o2(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_sqrt(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_sqrt(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_exp(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_exp(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_expm1(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_expm1(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_ln(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_ln(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_log10(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_log10(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_log1p(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_log1p(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_abs(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_abs(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_add(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrvrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_add(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_addx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrsrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_addx(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_div(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrvrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_div(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_divx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrsrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_divx(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_xdiv(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rsrvrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_xdiv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_mul(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrvrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_mul(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_mulx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrsrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_mulx(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_sqr(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_sqr(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_sub(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrvrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_sub(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_subx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rvrsrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_subx(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_xsub(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_real_izy_rsrvrv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vd_xsub(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vd_negate(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_negate(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_ceil(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_ceil(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_floor(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_floor(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_modf(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0, double[] out1, int[] offsetout1) {
    check_real_izy_rvrvrv(count, arg0, offsetarg0, out0, offsetout0, out1, offsetout1);
    _localIZY.vd_modf(count, arg0, offsetarg0, out0, offsetout0, out1, offsetout1);
  }

  @Override
  public void vd_nearbyint(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_nearbyint(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_rint(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_rint(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_round(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_round(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_trunc(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_trunc(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_cdfnorm(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_cdfnorm(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_cdfnorminv(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_cdfnorminv(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_erf(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_erf(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_erfc(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_erfc(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_erfinv(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_erfinv(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_erfcinv(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_erfcinv(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_lgamma(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_lgamma(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vd_tgamma(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_real_izy_rvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vd_tgamma(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_abs(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_abs(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_acos(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_acos(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_acosh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_acosh(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_add(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcvcv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_add(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_addx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcscv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_addx(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_arg(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvrv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_arg(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_asin(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_asin(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_asinh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_asinh(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_atan(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_atan(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_atanh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_atanh(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_conj(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_conj(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_cos(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_cos(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_cosh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_cosh(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_div(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcvcv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_div(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_divx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcscv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_divx(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_xdiv(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cscvcv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_xdiv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_exp(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_exp(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_ln(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_ln(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_log10(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_log10(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_mul(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcvcv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_mul(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_mulx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcscv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_mulx(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_mulbyconj(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcvcv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_mulbyconj(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_pow(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcvcv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_pow(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_powx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcscv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_powx(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_negate(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_negate(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_negatereal(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_negatereal(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_sin(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_sin(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_sinh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_sinh(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_sqrt(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_sqrt(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_sub(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcvcv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_sub(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_subx(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcscv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_subx(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_xsub(int[] count, double[] arg0, int[] offsetarg0, double[] arg1, int[] offsetarg1, double[] out0, int[] offsetout0) {
    check_complex_izy_cscvcv(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
    _localIZY.vz_xsub(count, arg0, offsetarg0, arg1, offsetarg1, out0, offsetout0);
  }

  @Override
  public void vz_tan(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_tan(count, arg0, offsetarg0, out0, offsetout0);
  }

  @Override
  public void vz_tanh(int[] count, double[] arg0, int[] offsetarg0, double[] out0, int[] offsetout0) {
    check_complex_izy_cvcv(count, arg0, offsetarg0, out0, offsetout0);
    _localIZY.vz_tanh(count, arg0, offsetarg0, out0, offsetout0);
  }
}
