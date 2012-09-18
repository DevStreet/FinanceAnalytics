/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.tool;


import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.ReaderWriterUtils;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.external.PrettyWriter;
import com.opengamma.integration.copiernew.external.XmlWriter;
import com.opengamma.util.generate.scripts.Scriptable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Scriptable
public class ExportTool {

  public static void main(String[] args) {

    // need options for different output formats: csv, xml, fudge, etc.
    if (args.length != 1 && args.length != 2) {
      throw new OpenGammaRuntimeException("Usage: ExportTool <uri> [<filename>]");
    }

    Iterable reader = ReaderWriterUtils.getMasterReader(args[0]);

    OutputStream outputStream = System.out;
    if (args.length == 2) {
      try {
        outputStream = new FileOutputStream(args[1]);
      } catch (FileNotFoundException e) {
        throw new OpenGammaRuntimeException("Could not open output file: " + e.getMessage());
      }
    }
    XmlWriter writer = new XmlWriter(outputStream);

    writer.addOrUpdate(reader);

    try {
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();  // TODO
    }

  }
}
