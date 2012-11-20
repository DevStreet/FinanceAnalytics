/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 */
public class TypeToken {
  private String _retType;
  private List<String> _argTypes = new ArrayList<String>();

  TypeToken(String returnTypeString, Class<?>[] argTypeClasses) {
    _retType = new String(returnTypeString);
    for (int i = 0; i < argTypeClasses.length; i++) {
      _argTypes.add(argTypeClasses[i].getName());
    }
  }

  /**
   * Gets the retType.
   * @return the retType
   */
  public String getReturnType() {
    return _retType;
  }

  /**
   * Gets the argTypeNames.
   * @return the FunctionName
   */
  public List<String> getArgTypeNames() {
    return _argTypes;
  }

  @Override
  public String toString() {
    String tmp = "";
    tmp = tmp + "Return Type = " + _retType + "\n";
    Iterator<String> it = _argTypes.iterator();
    tmp = tmp + "Argument Types =\n";
    while (it.hasNext()) {
      tmp = tmp + it.next().toString() + "\n";
    }
    return tmp;
  }

}

