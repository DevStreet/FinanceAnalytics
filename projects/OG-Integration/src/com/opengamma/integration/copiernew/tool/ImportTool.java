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
import com.opengamma.integration.copiernew.external.StreamReader;
import com.opengamma.integration.copiernew.external.StreamWriter;
import com.opengamma.util.generate.scripts.Scriptable;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

// TODO need options for different output formats: csv, xml, fudge, etc.

@Scriptable
public class ImportTool extends AbstractToolWithoutContext {

  /** File name option flag */
  private static final String FILE_NAME_OPT = "f";
  /** Import format option flag */
  private static final String FORMAT_OPT = "t";

  public static void main(String[] args) {
    new ImportTool().initAndRun(args);
    System.exit(0);
  }

  @Override
  protected void doRun() throws Exception {

    if (getCommandLine().getArgs().length != 1) {
      usage(createOptions());
      return;
    }

    InputStream inputStream;
    if (getCommandLine().hasOption(FILE_NAME_OPT)) {
      try {
        inputStream = new FileInputStream(getCommandLine().getOptionValue(FILE_NAME_OPT));
      } catch (FileNotFoundException e) {
        throw new OpenGammaRuntimeException("Could not open input file: " + e.getMessage());
      }
    } else {
      inputStream = System.in;
    }

    StreamReader reader;
    if (!getCommandLine().hasOption(FORMAT_OPT)) {
      reader = new StreamReader(inputStream, new StaxDriver());
    } else {
      String format = getCommandLine().getOptionValue(FORMAT_OPT).toLowerCase().trim();
      if (format.equals("xml")) {
        reader = new StreamReader(inputStream, new StaxDriver());
      } else if (format.equals("json")) {
        reader = new StreamReader(inputStream, new JettisonMappedXmlDriver());
      } else {
        throw new OpenGammaRuntimeException("Unable to generate the specified format (" + format + ")");
      }
    }
    Writeable writer = ReaderWriterUtils.getMasterWriter(getCommandLine().getArgs()[0]);
    writer.addOrUpdate(reader);

    writer.flush();
  }

  @Override
  protected Options createOptions() {

    Options options = super.createOptions();

    Option filenameOption = new Option(
        FILE_NAME_OPT, "filename", true,
        "The path to the file containing data to export (standard output if not specified)"
    );
    filenameOption.setRequired(false);
    options.addOption(filenameOption);

    Option formatOption = new Option(
        FORMAT_OPT, "format", true,
        "The export format to use (xml, json)"
    );
    formatOption.setRequired(false);
    options.addOption(formatOption);

    return options;
  }

  @Override
  protected void usage(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.setWidth(120);
    formatter.printHelp("java " + getEntryPointClass().getName() + " [OPTIONS] <Destination URI>", options);
  }

}
