/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.sabrcube;

import java.util.List;

import com.opengamma.engine.function.config.AbstractFunctionConfigurationBean;
import com.opengamma.engine.function.config.FunctionConfiguration;
import com.opengamma.engine.function.config.FunctionConfigurationSource;

/**
 * Function repository configuration source for the functions contained in this package.
 * @deprecated The functions that this configuration adds are deprecated
 */
@Deprecated
public class SABRCubeFunctions extends AbstractFunctionConfigurationBean {

  /**
   * Default instance of a repository configuration source exposing the functions from this package.
   *
   * @return the configuration source exposing functions from this package
   */
  public static FunctionConfigurationSource instance() {
    return new SABRCubeFunctions().getObjectCreating();
  }

  @Override
  protected void addAllConfigurations(final List<FunctionConfiguration> functions) {
    functions.add(functionConfiguration(SABRCMSSpreadNoExtrapolationVegaFunction.class));
    functions.add(functionConfiguration(SABRCMSSpreadRightExtrapolationVegaFunction.class));
    functions.add(functionConfiguration(SABRNoExtrapolationVegaFunction.class));
    functions.add(functionConfiguration(SABRRightExtrapolationVegaFunction.class));
  }
}
