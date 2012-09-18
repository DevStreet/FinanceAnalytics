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

    if (args.length != 1 && args.length != 2) {
      throw new OpenGammaRuntimeException("Usage: ImportTool <uri> [<filename>] ");
    }

    InputStream input = System.in;
    if (args.length == 2) {
      try {
        input = new FileInputStream(args[1]);
      } catch (FileNotFoundException e) {
        throw new OpenGammaRuntimeException("Could not open input file: " + e.getMessage());
      }
    }
    Iterable reader = new XmlReader(input);

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
