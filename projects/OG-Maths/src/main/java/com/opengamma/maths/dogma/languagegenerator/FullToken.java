/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator;

import java.util.Iterator;
import java.util.Set;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;
import com.opengamma.maths.dogma.engine.language.FunctionInterfaceRegister;

/**
 * A function token description for the dogma language
 */
public class FullToken {
  private String _canonicalName, _simpleName;
  private Class<?> _interfaceClass;
  private Class<?> _interfaceClassType;
  private Set<?> _impls;

  FullToken(String simpleName, String canonicalName, Class<?> interfaceclazz, Set<?> implementingClasses) {
    FunctionInterfaceRegister.getInstance();
    Set<Class<?>> registeredInterface = FunctionInterfaceRegister.getRegisteredFunctionInterfaces();
    _simpleName = new String(simpleName);
    _canonicalName = new String(canonicalName);
    _interfaceClass = interfaceclazz;
    // see if this class is actually amenable to doing something useful
    Class<?>[] interfacesDeclared = interfaceclazz.getInterfaces();
    if (interfacesDeclared.length == 0) {
      throw new MathsExceptionGeneric("Class " + _canonicalName + " is annotated as a DOGMA function and yet it does not implement a Function interface.");
    }
    int implementsAnRecognisedInterface = 0;
    Class<?> interfaceFoundPtr = null;
    for (int i = 0; i < interfacesDeclared.length; i++) {
//      System.out.println("Checking for " + interfacesDeclared[i].toString());
      if (registeredInterface.contains(interfacesDeclared[i])) {
        implementsAnRecognisedInterface++;
        interfaceFoundPtr = interfacesDeclared[i];
      }
    }
    if (implementsAnRecognisedInterface != 1) { // yes 1, this is not a bool done wrong, its testing if exactly one sane interface is found 
      throw new MathsExceptionGeneric("Class " + _canonicalName + " is annotated as a DOGMA function and has multiple registerable interfaces, can't currently handle this!");
    }

    _interfaceClassType = interfaceFoundPtr;
    _impls = implementingClasses;
  }

  /**
   * Gets the canonical name.
   * @return the canonical name
   */
  public String getCanonicalName() {
    return _canonicalName;
  }

  /**
   * Gets the implementing functions
   * @return the implementing functions
   */
  public Set<?> getImplementingFunctions() {
    return _impls;
  }

  /**
   * Gets the simple name.
   * @return the simple name
   */
  public String getSimpleName() {
    return _simpleName;
  }

  /**
   * Gets the class of the interface.
   * @return the class of the interface
   */
  public Class<?> getInterfaceClass() {
    return _interfaceClass;
  }

  /**
   * Gets the interface Class Type from the {@link FunctionInterfaceRegister}.
   * @return the interfaceClassType
   */
  public Class<?> getInterfaceClassType() {
    return _interfaceClassType;
  }

  @Override
  public String toString() {
    String tmp = "";
    tmp = tmp + "Name: " + _simpleName + "\n";
    tmp = tmp + "Canonical: " + _canonicalName + "\n";
    tmp = tmp + "Interface: " + _interfaceClass + "\n";
    tmp = tmp + "Implementing Classes:\n";
    Iterator<?> it = _impls.iterator();
    while (it.hasNext()) {
      tmp = tmp + "       " + it.next().toString() + " \n";
    }
    return tmp;
  }

}
