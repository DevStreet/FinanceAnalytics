/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.forex.option.black;

import java.util.List;

import com.opengamma.engine.function.config.AbstractFunctionConfigurationBean;
import com.opengamma.engine.function.config.FunctionConfiguration;
import com.opengamma.engine.function.config.FunctionConfigurationSource;

/**
 * Function repository configuration source for the functions contained in this package.
 * @deprecated This class adds deprecated functions
 */
@Deprecated
public class BlackFunctions extends AbstractFunctionConfigurationBean {

  /**
   * Default instance of a repository configuration source exposing the functions from this package.
   *
   * @return the configuration source exposing functions from this package
   */
  public static FunctionConfigurationSource instance() {
    return new BlackFunctions().getObjectCreating();
  }

  /**
   * Function repository configuration source for the defaults functions contained in this package.
   */
  public static class Defaults extends AbstractFunctionConfigurationBean {

    private double _overhedge; /* = 0.0;*/
    private double _relativeStrikeSmoothing = 0.001;

    public void setOverhedge(final double overhedge) {
      _overhedge = overhedge;
    }

    public double getOverhedge() {
      return _overhedge;
    }

    public void setRelativeStrikeSmoothing(final double relativeStrikeSmoothing) {
      _relativeStrikeSmoothing = relativeStrikeSmoothing;
    }

    public double getRelativeStrikeSmoothing() {
      return _relativeStrikeSmoothing;
    }

    @Override
    protected void addAllConfigurations(final List<FunctionConfiguration> functions) {
      functions.add(functionConfiguration(FXOneLookBarrierOptionBlackDefaultPropertiesFunction.class, Double.toString(getOverhedge()), Double.toString(getRelativeStrikeSmoothing())));
    }

  }

  @Override
  protected void addAllConfigurations(final List<FunctionConfiguration> functions) {
    functions.add(functionConfiguration(FXOptionBlackValuePhiFunction.class));
    functions.add(functionConfiguration(FXOptionBlackValueRhoFunction.class));
  }

}
