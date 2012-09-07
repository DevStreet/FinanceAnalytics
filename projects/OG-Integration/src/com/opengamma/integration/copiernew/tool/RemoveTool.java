/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.tool;


import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.Remover;
import com.opengamma.integration.copiernew.ReaderWriterUtils;

public class RemoveTool {

  public static void main(String[] args) {

    if (args.length != 1) {
      throw new OpenGammaRuntimeException("Usage: RmTool <uri>");
    }

    Iterable reader = ReaderWriterUtils.getMasterReader(args[0]);
    String[] split = ReaderWriterUtils.getUriParts(args[0]);
    Remover remover = new Remover(ReaderWriterUtils.getRemoteMaster(split[0], split[1], split[2]));

    remover.delete(reader);
  }
}
