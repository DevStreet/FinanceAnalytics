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

public class WriteTool {

  public static void main(String[] args) {

    if (args.length != 1) {
      throw new OpenGammaRuntimeException("Usage: WriteTool <uri>");
    }

    throw new UnsupportedOperationException("Not yet implemented");
  }
}
