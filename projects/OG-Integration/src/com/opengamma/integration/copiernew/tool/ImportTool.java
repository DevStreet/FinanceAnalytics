/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.tool;


import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.ReaderWriterUtils;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.external.XmlReader;
import com.opengamma.util.generate.scripts.Scriptable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Scriptable
public class ImportTool {

  public static void main(String[] args) {

    if (args.length != 1) {
      throw new OpenGammaRuntimeException("Usage: ImportTool <uri>");
    }

//    InputStream input;
//    try {
//      input = new FileInputStream("test.xml");
//    } catch (FileNotFoundException e) {
//      throw new OpenGammaRuntimeException(e.getMessage(), e);
//    }
//    Iterable reader = new XmlReader(input);
    Iterable reader = new XmlReader(System.in);

    // need options for different output formats: csv, xml, fudge, etc.
    Writeable writer = ReaderWriterUtils.getMasterWriter(args[0]);

    writer.addOrUpdate(reader);

    try {
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();  // TODO
    }

  }
}
