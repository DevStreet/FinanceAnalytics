/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

/**
 * Main method to generate the parser and lexer classes for midx
 */
public class GenerateMissileXParser {
  private static org.antlr.Tool s_antlrTool = new org.antlr.Tool();

  //CSOFF we need a main method!
  public static void main(String[] str) {
    boolean success = true;
    s_antlrTool.addGrammarFile("src/main/java/com/opengamma/maths/lowlevelapi/indexing/MissileX.g");
    try {
      s_antlrTool.process();
    } catch (Exception ex) {
      ex.printStackTrace();
      success = false;
    }
    if (success) {
      System.out.print("Operation completed successfully.");
    } else {
      System.out.print("Operation failed.");
    }
  }

}
