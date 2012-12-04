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
    System.out.println("Creating monolithic language code...");
    createMonolithic();
    System.out.println("\nCreating class wise language code...");
    createClasswise();
    System.out.println("DOGMA Language Generator Finished\n");
  }

  /**
   * creates dogma language as a monolithic class
   */
  private static void createMonolithic() {
    DogmaLanguageCodeGeneratorMonolithic codegen = new DogmaLanguageCodeGeneratorMonolithic();
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
  }

  /**
   * creates dogma language as a set of classes pulled in lazily
   */
  private static void createClasswise() {
    new DogmaLanguageCodeGeneratorClassWise("src/main/java/com/opengamma/maths/dogma/", "autogen");
    DogmaLanguageCodeGeneratorClassWise.generateCode();
  }

}
