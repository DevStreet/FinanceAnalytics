/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator.generators;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.languagegenerator.DogmaLangTokenToCodeGenerator;
import com.opengamma.maths.dogma.languagegenerator.FullToken;
import com.opengamma.maths.highlevelapi.datatypes.OGArrayInterface;

/**
 * Generates "arbitrary" function code
 */
// TODO: This needs a refactor, too much repetition and unnecessary repeated reflection
public class ArbitraryFunctionGenerator implements DogmaLangTokenToCodeGenerator {
  ArbitraryFunctionGenerator() {
  }

  private static ArbitraryFunctionGenerator s_instance = new ArbitraryFunctionGenerator();

  public static ArbitraryFunctionGenerator getInstance() {
    return s_instance;
  }

  private static String s_indent = "  ";
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
      methodList.clear();
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
      Type genericReturnType;
      String mname;
      String fname = f.getSimpleName();
      String fnamel = fname.toLowerCase();
      Class<?>[] parameterTypes;
      String returnTypeString;
      while (mit.hasNext()) {
        StringBuffer argbuf = new StringBuffer(), argnamesbuf = new StringBuffer();
        StringBuffer nullcheckbuf = new StringBuffer();
        next = mit.next();
        parameterTypes = next.getParameterTypes();
        returnType = next.getReturnType();
        genericReturnType = next.getGenericReturnType();
        if (!returnType.toString().equalsIgnoreCase(genericReturnType.toString())) {
          returnTypeString = genericReturnType.toString();
        } else {
          returnTypeString = returnType.getCanonicalName();
        }
        mname = next.getName();
        tmp.append(s_indent + "public static " + returnTypeString + " " + fnamel + "(");
        int plen = parameterTypes.length;
        if (plen > 0) {
          for (int i = 0; i < plen - 1; i++) {
            //            argbuf.append(parameterTypes[i].getSimpleName() + " arg" + i + ", ");
            argbuf.append(superizeIfaceType(parameterTypes[i]) + " arg" + i + ", ");
            argnamesbuf.append(" arg" + i + ", ");
            nullcheckbuf.append("Catchers.catchNull(arg" + i + ");\n");
          }
          //          argbuf.append(parameterTypes[plen - 1].getSimpleName() + " arg" + (plen - 1));
          argbuf.append(superizeIfaceType(parameterTypes[plen - 1]) + " arg" + (plen - 1));
          argnamesbuf.append(" arg" + (plen - 1));
          nullcheckbuf.append("Catchers.catchNull(arg" + (plen - 1) + ");\n");
        }
        tmp.append(argbuf);
        tmp.append("){\n");
        tmp.append(nullcheckbuf);
        if (!returnType.getSimpleName().equalsIgnoreCase("void")) {
          tmp.append(s_indent + s_indent + "return ");
        }
        tmp.append(s_indent + "s_" + nextImplementingClass.getClass().getSimpleName().toLowerCase() + "." + mname + "(" + argnamesbuf.toString() + ");\n");
        tmp.append(s_indent + "};\n");
      }
    }
    return tmp.toString();
  }

  private static Map<String, String> s_numberClassNames = new HashMap<String, String>();
  static {
    s_numberClassNames.put("short", "Short");
    s_numberClassNames.put("int", "Integer");
    s_numberClassNames.put("long", "Long");
    s_numberClassNames.put("ComplexType", "ComplexType");
    s_numberClassNames.put("double", "Double");
    s_numberClassNames.put("float", "Float");
  }

  private String superizeIfaceType(Class<?> clazz) {

    // looks through the class interfaces to see if its an OGArray type
    Class<?> superClass = clazz.getSuperclass();
    if (superClass != null) {
      Class<?>[] implementedIfaces = superClass.getInterfaces();
      for (int iWalkIfacePtr = 0; iWalkIfacePtr < implementedIfaces.length; iWalkIfacePtr++) {
        if (implementedIfaces[iWalkIfacePtr] == OGArrayInterface.class) {
          return clazz.getSimpleName();
        }
      }
    }

    // sees if the class is one of the primitive "Number" types, shame number is so broken, should really be called "numeric" 
    if (s_numberClassNames.containsKey(clazz.getSimpleName())) {
      return s_numberClassNames.get(clazz.getSimpleName());
    }

    return clazz.getCanonicalName();
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
      tmp.append(s_indent + "private static " + nextImplementingClass.getClass().getCanonicalName() + " s_" + nextImplementingClass.getClass().getSimpleName().toString().toLowerCase() + " = new " +
          nextImplementingClass.getClass().getCanonicalName() + "();\n");
    }
    return tmp.toString();
  }

  @Override
  public String generateEntryPointsCode(FullToken f) {
    Set<?> classesWithFunctions = f.getImplementingFunctions();

    // for each class, reflect, look for labelled functions, add in;

//    System.out.println("Generating entry point code for " + f.getSimpleName());
//    System.out.println("Token used is:" + f.toString());

    StringBuffer tmp = new StringBuffer();

    Iterator<?> it = classesWithFunctions.iterator();
    Object nextImplementingClass;
    Class<?> localClass;
    Method[] localMethods;
    boolean methodFound;
    List<Method> methodList = new ArrayList<Method>();
    while (it.hasNext()) {
      methodList.clear();
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

    //            System.out.println(f.toString());
      //      System.out.println("METHOD LIST:" + methodList.toString());
      //            System.out.println();

      Iterator<Method> mit = methodList.iterator();
      Method next;
      Class<?> returnType;
      Type genericReturnType;
      String mname;
      String fname = f.getSimpleName();
      String fnamel = fname.toLowerCase();
      Class<?>[] parameterTypes;
      String returnTypeString;
      while (mit.hasNext()) {
        StringBuffer argbuf = new StringBuffer(), argnamesbuf = new StringBuffer();
        next = mit.next();
        parameterTypes = next.getParameterTypes();
        returnType = next.getReturnType();

        genericReturnType = next.getGenericReturnType();
        if (!returnType.toString().equalsIgnoreCase(genericReturnType.toString())) {
          returnTypeString = genericReturnType.toString();
        } else {
          returnTypeString = returnType.getCanonicalName();
        }
        mname = next.getName();
        tmp.append(s_indent + "public static " + returnTypeString + " " + fnamel + "(");
        int plen = parameterTypes.length;
        if (plen > 0) {
          for (int i = 0; i < plen - 1; i++) {
            argbuf.append(parameterTypes[i].getCanonicalName() + " arg" + i + ", ");
            argnamesbuf.append("arg" + i + ", ");
          }
          argbuf.append(parameterTypes[plen - 1].getCanonicalName() + " arg" + (plen - 1));
          argnamesbuf.append("arg" + (plen - 1));
        }
        tmp.append(argbuf);
        tmp.append(") {\n");
        if (!returnType.getSimpleName().equalsIgnoreCase("void")) {
          tmp.append(s_indent + s_indent + "return ");
        }
        tmp.append(s_indent + s_indent + s_autogenPath + "DOGMA" + f.getSimpleName() + "." + mname + "(" + argnamesbuf.toString() + ");\n");
        tmp.append(s_indent + "};\n\n");
      }
    }
    return tmp.toString();
  }

}
