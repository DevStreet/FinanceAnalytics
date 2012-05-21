/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.io.mat;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

/**
 *  tests ReadMat
 */
public class TestReadMat {

  
  @Test
  public void TestReadMatFile() {
    //TODO: ask about an OG or at least sensible way of doing this
    String topPath = System.getProperty("user.dir");
    String relPath = "/tests/unit/com/opengamma/maths/lowlevelapi/io/mat/";
    String filename = topPath + relPath + "A.mat";
    try {
      ReadMAT.read(filename);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void TestParseMatFile() {
    //TODO: ask about an OG or at least sensible way of doing this
    String topPath = System.getProperty("user.dir");
    String relPath = "/tests/unit/com/opengamma/maths/lowlevelapi/io/mat/";
    String filename = topPath + relPath + "ABC.mat";
    MATFileStruct mfs = null;
    try {
      mfs = ReadMAT.parseMATFile(filename);
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("\n\nStringified MFS\n" + mfs.toString());

  }

}
