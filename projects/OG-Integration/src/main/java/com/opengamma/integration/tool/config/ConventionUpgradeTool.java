/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.tool.config;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.opengamma.component.tool.AbstractTool;
import com.opengamma.core.config.ConfigSource;
import com.opengamma.core.convention.ConventionSource;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.financial.tool.ToolContext;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.convention.ConventionMaster;
import com.opengamma.master.security.SecurityLoader;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.scripts.Scriptable;

/**
 * Tool to upgrade conventions to new form
 */
@Scriptable
public class ConventionUpgradeTool extends AbstractTool<ToolContext> {
  
  private static final String WRITE_OPTION = "write";

  //-------------------------------------------------------------------------
  /**
   * Main method to run the tool.
   * 
   * @param args  the standard tool arguments, not null
   */
  public static void main(String[] args) { // CSIGNORE
    new ConventionUpgradeTool().invokeAndTerminate(args);
  }

  //-------------------------------------------------------------------------
  @Override
  protected void doRun() {
    ToolContext toolContext = getToolContext();
    ConfigMaster configMaster = toolContext.getConfigMaster();
    ConfigSource configSource = toolContext.getConfigSource();
    ConventionSource conventionSource = toolContext.getConventionSource();
    ConventionMaster conventionMaster = toolContext.getConventionMaster();
    SecuritySource securitySource = toolContext.getSecuritySource();
    SecurityMaster securityMaster = toolContext.getSecurityMaster();
    SecurityLoader securityLoader = toolContext.getSecurityLoader();
    System.out.println("Starting upgrade...");
    ConventionUpgrader upgrader = new ConventionUpgrader(securityMaster, securitySource,
                                                         configMaster, configSource, 
                                                         conventionMaster, conventionSource, 
                                                         securityLoader, getCommandLine().hasOption(WRITE_OPTION));
    upgrader.upgrade();
    System.out.println("Finished upgrade.");
  }
  
  @Override
  protected Options createOptions(boolean mandatoryConfig) {
    Options options = super.createOptions(mandatoryConfig);
    options.addOption(createWriteOption());
    return options;
  }

  @SuppressWarnings("static-access")
  protected Option createWriteOption() {
    return OptionBuilder.withDescription("Write upgrades to DB").withLongOpt(WRITE_OPTION).create("w");
  }

  @Override
  protected Class<?> getEntryPointClass() {
    return getClass();
  }

  @Override
  protected void usage(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.setWidth(120);
    formatter.printHelp("config-validation-tool.sh [file...]", options, true);
  }

}
