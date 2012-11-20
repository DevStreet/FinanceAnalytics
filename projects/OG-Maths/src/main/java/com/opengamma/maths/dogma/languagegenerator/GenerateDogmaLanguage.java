/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 
 */
public class GenerateDogmaLanguage {

  /**
   * Generates the DOGMA Language based on what's available from a scan of the ClassPath
   * @param args not needed
   */
  public static void main(String[] args) {
    System.out.println("DOGMA Language Generator Running\n");
    DogmaLanguageCodeGenerator codegen = new DogmaLanguageCodeGenerator();
    String code = codegen.generateCode();
    File outfile = new File("src/main/java/com/opengamma/maths/dogma/DogmaLanguage.java");
    FileWriter writer;
    BufferedWriter bufferedWrite;
    // write to file
    try {
      outfile.createNewFile();
      writer = new FileWriter(outfile);
      bufferedWrite = new BufferedWriter(writer);
      bufferedWrite.write(code);
      bufferedWrite.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("DOGMA Language Generator Finished\n");
  }
}
