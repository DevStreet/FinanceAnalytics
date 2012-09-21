/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.tool;


import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.component.tool.AbstractToolWithoutContext;
import com.opengamma.integration.copiernew.ReaderWriterUtils;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.external.PrettyWriter;
import com.opengamma.integration.copiernew.external.StreamWriter;
import com.opengamma.util.generate.scripts.Scriptable;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;

@Scriptable
public class ListTool extends AbstractToolWithoutContext {

  public static void main(String[] args) {
    new ListTool().initAndRun(args);
    System.exit(0);
  }

  @Override
  protected void doRun() throws Exception {

    if (getCommandLine().getArgs().length != 1) {
      usage(createOptions());
      return;
    }

    Writeable writer = new PrettyWriter(System.out);
    Iterable reader = ReaderWriterUtils.getMasterReader(getCommandLine().getArgs()[0]);
    writer.addOrUpdate(reader);

    try {
      writer.flush();
    } catch (IOException e) {
      throw new OpenGammaRuntimeException("Could not flush and close output file: " + e.getMessage());
    }
  }

  @Override
  protected void usage(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.setWidth(120);
    formatter.printHelp("java " + getEntryPointClass().getName() + " [OPTIONS] <Source URI>", options);
  }
}
