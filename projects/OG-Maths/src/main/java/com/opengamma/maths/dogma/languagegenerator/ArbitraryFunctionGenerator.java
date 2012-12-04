/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;

/**
 * 
 * TODO: This needs a refactor, too much repetition and unnecessary repeated reflection
 */
public class ArbitraryFunctionGenerator implements DogmaLangTokenToCodeGenerator {
  ArbitraryFunctionGenerator() {
  }

  private static ArbitraryFunctionGenerator s_instance = new ArbitraryFunctionGenerator();

  public static ArbitraryFunctionGenerator getInstance() {
    return s_instance;
  }

  private static String s_autogenPath = "com.opengamma.maths.dogma.autogen.";

  @Override
  public String generateMethodCode(FullToken f) {
    Set<?> classesWithFunctions = f.getImplementingFunctions();

    // for each class, reflect, look for labelled functions, add in;

    StringBuffer tmp = new StringBuffer();

    Iterator<?> it = classesWithFunctions.iterator();
    Object nextImplementingClass;
    Class<?> localClass;
    Method[] localMethods;
    boolean methodFound;
    List<Method> methodList = new ArrayList<Method>();
    while (it.hasNext()) {
      methodFound = false;
      nextImplementingClass = it.next();
      localClass = nextImplementingClass.getClass();
      localMethods = localClass.getDeclaredMethods();
      for (int i = 0; i < localMethods.length; i++) {
        if (localMethods[i].getAnnotation(DOGMAMethodLiteral.class) != null) {
          methodFound = true;
          methodList.add(localMethods[i]);
        }
      }
      if (!methodFound) {
        throw new MathsExceptionGeneric("Class: " + nextImplementingClass.getClass() + " is annotated to have a DOGMAMethodHook and is declared to provide the " +
            "ArbitraryFunction interface, however, the instantiated class has no methods annotated with DOGMAMethodLiteral.");
      }
      //      System.out.println(methodList.toString());

      Iterator<Method> mit = methodList.iterator();
      Method next;
      Class<?> returnType;
      String mname;
      String fname = f.getSimpleName();
      String fnamel = fname.toLowerCase();
      Class<?>[] parameterTypes;
      while (mit.hasNext()) {
        StringBuffer argbuf = new StringBuffer(), argnamesbuf = new StringBuffer();
        next = mit.next();
        parameterTypes = next.getParameterTypes();
        returnType = next.getReturnType();
        mname = next.getName();
        tmp.append("public static " + returnType.getSimpleName() + " " + fnamel + "(");
        int plen = parameterTypes.length;
        if (plen > 0) {
          for (int i = 0; i < plen - 1; i++) {
            argbuf.append(parameterTypes[i].getSimpleName() + " arg" + i + ", ");
            argnamesbuf.append(" arg" + i + ", ");
          }
          argbuf.append(parameterTypes[plen - 1].getSimpleName() + " arg" + (plen - 1));
          argnamesbuf.append(" arg" + (plen - 1));
        }
        tmp.append(argbuf);
        tmp.append(" ){\n");
        tmp.append("// " + mname + "\n");
        tmp.append("return s_" + nextImplementingClass.getClass().getSimpleName().toLowerCase() + "." + mname + "(" + argnamesbuf.toString() + ");\n");
        tmp.append("};\n");
      }
    }
    return tmp.toString();
  }

  @Override
  public String generateTableCode(FullToken f) {
    StringBuffer tmp = new StringBuffer();

    return tmp.toString();
  }

  @Override
  public String generateTableCodeVariables(FullToken f) {
    StringBuffer tmp = new StringBuffer();
    Set<?> classesWithFunctions = f.getImplementingFunctions();
    // for each class, reflect, look for labelled functions, add in;
    Iterator<?> it = classesWithFunctions.iterator();
    Object nextImplementingClass;
    Class<?> localClass;
    Method[] localMethods;
    boolean methodFound;
    while (it.hasNext()) {
      methodFound = false;
      nextImplementingClass = it.next();
      localClass = nextImplementingClass.getClass();
      localMethods = localClass.getDeclaredMethods();
      for (int i = 0; i < localMethods.length; i++) {
        if (localMethods[i].getAnnotation(DOGMAMethodLiteral.class) != null) {
          methodFound = true;
        }
      }
      if (!methodFound) {
        throw new MathsExceptionGeneric("Class: " + nextImplementingClass.getClass() + " is annotated to have a DOGMAMethodHook and is declared to provide the " +
            "ArbitraryFunction interface, however, the instantiated class has no methods annotated with DOGMAMethodLiteral.");
      }
      tmp.append("private static " + nextImplementingClass.getClass().getCanonicalName() + " s_" + nextImplementingClass.getClass().getSimpleName().toString().toLowerCase() + " = new " +
          nextImplementingClass.getClass().getCanonicalName() + "();\n");
    }
    return tmp.toString();
  }

  @Override
  public String generateEntryPointsCode(FullToken f) {
    Set<?> classesWithFunctions = f.getImplementingFunctions();

    // for each class, reflect, look for labelled functions, add in;

    StringBuffer tmp = new StringBuffer();

    Iterator<?> it = classesWithFunctions.iterator();
    Object nextImplementingClass;
    Class<?> localClass;
    Method[] localMethods;
    boolean methodFound;
    List<Method> methodList = new ArrayList<Method>();
    while (it.hasNext()) {
      methodFound = false;
      nextImplementingClass = it.next();
      localClass = nextImplementingClass.getClass();
      localMethods = localClass.getDeclaredMethods();
      for (int i = 0; i < localMethods.length; i++) {
        if (localMethods[i].getAnnotation(DOGMAMethodLiteral.class) != null) {
          methodFound = true;
          methodList.add(localMethods[i]);
        }
      }
      if (!methodFound) {
        throw new MathsExceptionGeneric("Class: " + nextImplementingClass.getClass() + " is annotated to have a DOGMAMethodHook and is declared to provide the " +
            "ArbitraryFunction interface, however, the instantiated class has no methods annotated with DOGMAMethodLiteral.");
      }
//      System.out.println(methodList.toString());

      Iterator<Method> mit = methodList.iterator();
      Method next;
      Class<?> returnType;
      String mname;
      String fname = f.getSimpleName();
      String fnamel = fname.toLowerCase();
      Class<?>[] parameterTypes;
      while (mit.hasNext()) {
        StringBuffer argbuf = new StringBuffer(), argnamesbuf = new StringBuffer();
        next = mit.next();
        parameterTypes = next.getParameterTypes();
        returnType = next.getReturnType();
        mname = next.getName();
        tmp.append("public static " + returnType.getSimpleName() + " " + fnamel + "(");
        int plen = parameterTypes.length;
        if (plen > 0) {
          for (int i = 0; i < plen - 1; i++) {
            argbuf.append(parameterTypes[i].getSimpleName() + " arg" + i + ", ");
            argnamesbuf.append(" arg" + i + ", ");
          }
          argbuf.append(parameterTypes[plen - 1].getSimpleName() + " arg" + (plen - 1));
          argnamesbuf.append(" arg" + (plen - 1));
        }
        tmp.append(argbuf);
        tmp.append(" ){\n");
        tmp.append("// " + mname + "\n");
        tmp.append("return " + s_autogenPath + "DOGMA" + f.getSimpleName() + "." + mname + "(" + argnamesbuf.toString() + ");\n");
        tmp.append("};\n");
      }
    }
    return tmp.toString();
  }

}
