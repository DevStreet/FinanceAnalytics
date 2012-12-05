/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator.docs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.fudgemsg.types.ClasspathUtilities;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionConfigProblem;
import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;

/**
 * Scans classpath for classes tagged with @DOGMADocumentationHook
 * @param <T> the set in the map
 */
public class DocsDictionaryPopulator<T> {
  
  public DocsDictionaryPopulator() {
    populate();
  }

  private final Map<Class<?>, Set<T>> _opMap = new HashMap<Class<?>, Set<T>>();

  private AtomicBoolean _haveScanned = new AtomicBoolean();

  /** 
   * Populates the function set
   */
  public void populate() {
    if (!_haveScanned.getAndSet(true)) {
      Set<String> classNamesWithAnnotation = ClasspathUtilities.getClassNamesWithAnnotation(DOGMADocumentationHook.class);
      if (classNamesWithAnnotation == null) {
        return;
      }
      for (String className : classNamesWithAnnotation) {
        addAnnotatedDOGMAMethodHookClass(className);
      }
    }
  }

  private void addAnnotatedDOGMAMethodHookClass(String className) {
    // try and instantiate a version of the class
    Class<?> clazz = null;
    try {
      clazz = Class.forName(className);
    } catch (Exception e) {
      // something broke, whine... can't do much else
      e.printStackTrace();
    }
    if (clazz == null) {
      throw new MathsExceptionConfigProblem("No class could be found with forName " + className);
    }
    Object registerInstance = null;
    try {
      registerInstance = clazz.newInstance();
    } catch (Exception e) {
      // something broke, whine... can't do much else
      e.printStackTrace();
    }
    if (registerInstance == null) {
      throw new MathsExceptionConfigProblem("Class" + className + " could not be instantiated");
    }

    // see what it is supposed to do
    Class<?> theClass = clazz.getAnnotation(DOGMADocumentationHook.class).providesDocsFor();

//    Method[] m = registerInstance.getClass().getDeclaredMethods();
//    for (int i = 0; i < m.length; i++) {
//      System.out.println(m[i].toString());
//    }

    // add the thing to the map
    if (_opMap.get(theClass) == null) {
      Set<T> tmp = new HashSet<T>();
      @SuppressWarnings("unchecked")
      T castRef = (T) registerInstance;
      tmp.add(castRef);
      _opMap.put(theClass, tmp);
    } else {
      @SuppressWarnings("unchecked")
      T tmp = (T) registerInstance;
      _opMap.get(theClass).add(tmp);
    }
  }

  /**
   * Once instantiated a map containing all the implementations of a given operation is available via this call
   * @return a map containing all the implementations of a given operation in a list keyed against operation class
   */
  public Map<Class<?>, Set<T>> getOperationsMap() {
    return _opMap;
  }
}
