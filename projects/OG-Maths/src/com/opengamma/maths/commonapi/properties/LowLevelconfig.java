/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.commonapi.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionConfigProblem;

/**
 * Hoists in the config for the low level libraries
 */
public class LowLevelconfig {

  /* default place for properties */
  private static String s_propertiesFilename = "config/OG-Maths.properties";

  /* SETTINGS */
  private static boolean s_haltOnNaNOnFunctionEntry;
  private static boolean s_haltOnNaNOnFunctionExit;
  private static boolean s_haltOnInfOnFunctionEntry;
  private static boolean s_haltOnInfOnFunctionExit;
  private static boolean s_haltOnMaxValue;
  private static boolean s_haltOnMinValue;
  private static double s_haltOnMaxValueOf = Double.MAX_VALUE;
  private static double s_haltOnMinValueOf = Double.MIN_VALUE;

  private static HashMap<String, String> s_properties = new HashMap<String, String>();

  /** 
   * Default settings from default file
   */
  public LowLevelconfig() {
    getPropertiesFromConfig();
  }

  /** 
   * Settings from file with path given by string
   * @param propertiesFileName path and name of properties file
   */
  public LowLevelconfig(String propertiesFileName) {
    getPropertiesFromConfig(propertiesFileName);
  }

  private static void getPropertiesFromConfig() {
    getPropertiesFromConfig(s_propertiesFilename);
  }

  private static void getPropertiesFromConfig(String configFileName) {

    FileInputStream propsFile = null;
    Properties ogMathsProperties = new Properties();
    try {
      propsFile = new FileInputStream(configFileName);
    } catch (IOException e) {
      throw new MathsExceptionConfigProblem("Cannot find default properties file. Was looking for file: " + configFileName);
    }
    try {
      ogMathsProperties.load(propsFile);
    } catch (IOException e) {
      throw new MathsExceptionConfigProblem("Cannot load in default properties. Load attempted from file: " + configFileName);
    }
    try {
      propsFile.close();
    } catch (IOException e) {
      throw new MathsExceptionConfigProblem("Failed to close properties file stream. Clise attempted on file: " + configFileName);
    }

    // look for DOGMA specific entries
    Pattern dogmaPattern = Pattern.compile("DOGMA.*", Pattern.CASE_INSENSITIVE);
    for (Entry<Object, Object> entry : ogMathsProperties.entrySet()) {
      String key = entry.getKey().toString();
      String value = entry.getValue().toString();
      Matcher dogmaMatch = dogmaPattern.matcher(key);
      if (dogmaMatch.matches()) {
        s_properties.put(key, value);
      }
    }

    // walk settings and set what we know   
    if (s_properties.containsKey("DOGMA.HaltOnNaNOnFunctionEntry")) {
      s_haltOnNaNOnFunctionEntry = Str2Bool.str2bool(s_properties.get("DOGMA.HaltOnNaNOnFunctionEntry").toString());
    }

    if (s_properties.containsKey("DOGMA.HaltOnNaNOnFunctionExit")) {
      s_haltOnNaNOnFunctionExit = Str2Bool.str2bool(s_properties.get("DOGMA.HaltOnNaNOnFunctionExit").toString());
    }

    if (s_properties.containsKey("DOGMA.HaltOnInfOnFunctionEntry")) {
      s_haltOnInfOnFunctionEntry = Str2Bool.str2bool(s_properties.get("DOGMA.HaltOnInfOnFunctionEntry").toString());
    }

    if (s_properties.containsKey("DOGMA.HaltOnInfOnFunctionExit")) {
      s_haltOnInfOnFunctionExit = Str2Bool.str2bool(s_properties.get("DOGMA.HaltOnInfOnFunctionExit").toString());
    }

    if (s_properties.containsKey("DOGMA.HaltOnMaxValue")) {
      s_haltOnMaxValue = Str2Bool.str2bool(s_properties.get("DOGMA.HaltOnMaxValue").toString());
      if (s_properties.get("DOGMA.HaltOnMaxValueOf") != null) {
        s_haltOnMaxValueOf = Double.valueOf(s_properties.get("DOGMA.HaltOnMaxValueOf")).doubleValue();
      } else {
        throw new MathsExceptionConfigProblem("DOGMA.HaltOnMaxValue specified with no value in DOGMA.HaltOnMaxValueOf set");
      }
    }

    if (s_properties.containsKey("DOGMA.HaltOnMinValue")) {
      s_haltOnMinValue = Str2Bool.str2bool(s_properties.get("DOGMA.HaltOnMinValue").toString());
      if (s_properties.get("DOGMA.HaltOnMinValueOf") != null) {
        s_haltOnMinValueOf = Double.valueOf(s_properties.get("DOGMA.HaltOnMinValueOf")).doubleValue();
      } else {
        throw new MathsExceptionConfigProblem("DOGMA.HaltOnMinValue specified with no value in DOGMA.HaltOnMinValueOf set");
      }
    }

  }

  /**
   * Returns the hash map of the key value pairs of DOGMA globbed settings
   * @return the settings
   */
  public HashMap<String, String> getHashedSettings() {
    return s_properties;
  }

  /**
   * Gets the s_HaltOnNaNOnFunctionEntry.
   * @return the s_HaltOnNaNOnFunctionEntry
   */
  public static boolean getHaltOnNaNOnFunctionEntry() {
    return s_haltOnNaNOnFunctionEntry;
  }

  /**
   * Gets the s_HaltOnNaNOnFunctionExit.
   * @return the s_HaltOnNaNOnFunctionExit
   */
  public static boolean getHaltOnNaNOnFunctionExit() {
    return s_haltOnNaNOnFunctionExit;
  }

  /**
   * Gets the s_HaltOnInfOnFunctionEntry.
   * @return the s_HaltOnInfOnFunctionEntry
   */
  public static boolean getHaltOnInfOnFunctionEntry() {
    return s_haltOnInfOnFunctionEntry;
  }

  /**
   * Gets the s_HaltOnInfOnFunctionEntry.
   * @return the s_HaltOnInfOnFunctionEntry
   */
  public static boolean getHaltOnInfOnFunctionExit() {
    return s_haltOnInfOnFunctionExit;
  }

  /**
   * Gets the s_HaltOnMaxValue
   * @return the s_HaltOnMaxValue
   */
  public static boolean getHaltOnMaxValue() {
    return s_haltOnMaxValue;
  }

  /**
   * Gets the s_HaltOnMaxValueOf
   * @return the s_HaltOnMaxValueOf
   */
  public static double getHaltOnMaxValueOf() {
    return s_haltOnMaxValueOf;
  }

  /**
   * Gets the s_HaltOnMinValue
   * @return the s_HaltOnMinValue
   */
  public static boolean getHaltOnMinValue() {
    return s_haltOnMinValue;
  }

  /**
   * Gets the s_HaltOnMinValueOf
   * @return the s_HaltOnMinValueOf
   */
  public static double getHaltOnMinValueOf() {
    return s_haltOnMinValueOf;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Formatter strbool = new Formatter(sb);
    for (Entry<String, String> entry : s_properties.entrySet()) {
      strbool.format("%-35.30s: %s\n", entry.getKey(), entry.getValue());
    }
    return sb.toString();
  }

}
