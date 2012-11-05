/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.tool;

import com.opengamma.component.tool.AbstractToolWithoutContext;
import com.opengamma.integration.copiernew.Remover;
import com.opengamma.integration.copiernew.ReaderWriterUtils;
import com.opengamma.util.generate.scripts.Scriptable;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

@Scriptable
public class RemoveTool extends AbstractToolWithoutContext {

  public static void main(String[] args) {
    new RemoveTool().initAndRun(args);
    System.exit(0);
  }

  @Override
  protected void doRun() throws Exception {
    if (getCommandLine().getArgs().length != 1) {
      usage(createOptions());
      return;
    }

    Iterable reader = ReaderWriterUtils.getMasterReader(getCommandLine().getArgs()[0]);
    String[] split = ReaderWriterUtils.getUriParts(getCommandLine().getArgs()[0]);
    Remover remover = new Remover(ReaderWriterUtils.getRemoteMaster(split[0], split[1], split[2]));
    remover.delete(reader);
  }

  @Override
  protected void usage(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.setWidth(120);
    formatter.printHelp("java " + getEntryPointClass().getName() + " [OPTIONS] <Destination URI>", options);
  }

}
