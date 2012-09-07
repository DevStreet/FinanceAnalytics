/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.tool;


import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.ReaderWriterUtils;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.sheet.PrettyWriter;

import java.io.IOException;

public class ReadTool {

  public static void main(String[] args) {

    if (args.length != 1) {
      throw new OpenGammaRuntimeException("Usage: ReadTool <uri>");
    }

    Iterable reader = ReaderWriterUtils.getMasterReader(args[0]);

    // need options for different output formats: csv, xml, fudge, etc.
    Writeable writer = new PrettyWriter(System.out);

    writer.addOrUpdate(reader);

    try {
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();  // TODO
    }

  }
}
