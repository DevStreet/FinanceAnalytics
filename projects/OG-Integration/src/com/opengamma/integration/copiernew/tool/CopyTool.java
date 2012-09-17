/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.tool;


import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.ReaderWriterUtils;
import com.opengamma.integration.copiernew.Writeable;

import java.io.IOException;

public class CopyTool {

  public static void main(String[] args) {

    if (args.length != 2) {
      throw new OpenGammaRuntimeException("Usage: CopyTool <source uri> <destination uri>");
    }

    Iterable reader = ReaderWriterUtils.getMasterReader(args[0]);
    Writeable writer = ReaderWriterUtils.getMasterWriter(args[1]);
    writer.addOrUpdate(reader);

    try {
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();  // TODO
    }

  }
}

