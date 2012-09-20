/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.tool;


import com.opengamma.component.tool.AbstractToolWithoutContext;
import com.opengamma.integration.copiernew.ReaderWriterUtils;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.util.generate.scripts.Scriptable;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.IOException;

@Scriptable
public class CopyTool extends AbstractToolWithoutContext {

  public static void main(String[] args) {
    new CopyTool().initAndRun(args);
    System.exit(0);
  }

  @Override
  protected void doRun() throws Exception {
    if (getCommandLine().getArgs().length != 2) {
      usage(createOptions());
      return;
    }

    Iterable reader = ReaderWriterUtils.getMasterReader(getCommandLine().getArgs()[0]);
    Writeable writer = ReaderWriterUtils.getMasterWriter(getCommandLine().getArgs()[1]);
    writer.addOrUpdate(reader);

    try {
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();  // TODO
    }
  }

  @Override
  protected void usage(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.setWidth(120);
    formatter.printHelp("java " + getEntryPointClass().getName() + " [OPTIONS] <Source URI> <Destination URI>", options);
  }
}

