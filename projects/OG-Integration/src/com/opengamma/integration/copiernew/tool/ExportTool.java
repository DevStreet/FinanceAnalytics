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

import java.io.IOException;

@Scriptable
public class ExportTool {

  public static void main(String[] args) {

    if (args.length != 1) {
      throw new OpenGammaRuntimeException("Usage: ExportTool <uri>");
    }

    Iterable reader = ReaderWriterUtils.getMasterReader(args[0]);

    // need options for different output formats: csv, xml, fudge, etc.
    XmlWriter writer = new XmlWriter(System.out);

    writer.addOrUpdate(reader);

    try {
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();  // TODO
    }

  }
}
