/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.tool;


import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.component.tool.AbstractToolWithoutContext;
import com.opengamma.financial.tool.ToolContext;
import com.opengamma.integration.copiernew.ReaderWriterUtils;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.external.PrettyWriter;
import com.opengamma.integration.copiernew.external.XmlWriter;
import com.opengamma.util.generate.scripts.Scriptable;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// TODO need options for different output formats: csv, xml, fudge, etc.

@Scriptable
public class ExportTool extends AbstractToolWithoutContext {

  /** File name option flag */
  private static final String FILE_NAME_OPT = "f";

  public static void main(String[] args) {
    new ExportTool().initAndRun(args);
    System.exit(0);
  }

  @Override
  protected void doRun() throws Exception {

    if (getCommandLine().getArgs().length != 1) {
      usage(createOptions());
      return;
    }

    OutputStream outputStream;
    if (getCommandLine().hasOption(FILE_NAME_OPT)) {
      try {
        outputStream = new FileOutputStream(getCommandLine().getOptionValue(FILE_NAME_OPT));
      } catch (FileNotFoundException e) {
        throw new OpenGammaRuntimeException("Could not open output file: " + e.getMessage());
      }
    } else {
      outputStream = System.out;
    }

    XmlWriter writer = new XmlWriter(outputStream);
    Iterable reader = ReaderWriterUtils.getMasterReader(getCommandLine().getArgs()[0]);
    writer.addOrUpdate(reader);

    try {
      writer.flush();
      writer.close();
    } catch (IOException e) {
      throw new OpenGammaRuntimeException("Could not flush and close output file: " + e.getMessage());
    }
  }

  @Override
  protected Options createOptions() {

    Options options = super.createOptions();

    Option filenameOption = new Option(
        FILE_NAME_OPT, "filename", true,
        "The path to the file containing data to export (standard input if not specified)"
    );
    filenameOption.setRequired(false);
    options.addOption(filenameOption);

    return options;
  }

  @Override
  protected void usage(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.setWidth(120);
    formatter.printHelp("java " + getEntryPointClass().getName() + " [OPTIONS] <Source URI>", options);
  }
}
