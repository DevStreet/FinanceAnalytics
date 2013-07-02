/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;


/**
 * Main method to generate the parser and lexer classes for midx
 */
public class GenerateMissileParser {
  
  // get the antlr tool
  private static org.antlr.Tool s_antlrTool = new org.antlr.Tool();

  //CSOFF we need a main method!
  public static void main(String[] str) {
    boolean success = true;
    
    String inbasedir = "src/main/java/com/opengamma/maths/lowlevelapi/indexing/";
    s_antlrTool.setInputDirectory(inbasedir);
    // bung in grammars
    // lexer
    s_antlrTool.addGrammarFile("MissileLexer.g");
    // parser
    s_antlrTool.addGrammarFile("MissileParser.g");
    // AST walker and emitter
    s_antlrTool.addGrammarFile("MissileTree.g");
    // set output dir
    s_antlrTool.setOutputDirectory(inbasedir);
    // force tokens etc to appear in the same dir as the generated
    s_antlrTool.setForceRelativeOutput(true);
    
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
