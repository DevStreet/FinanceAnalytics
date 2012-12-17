/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.view.calcnode;

import com.opengamma.engine.view.cache.ViewComputationCache;

/**
 * Interface for an engine calculation node, providing the facility to query the calculation node ID and to retrieve
 * the view computation cache for a specified calculation job
 */
public interface CalculationNode {

  /**
   * Retrieve the computation cache for the specified calculation job
   * @param spec the calculation job whose computation cache to retrieve
   * @return     the computation cache for spec
   */
  ViewComputationCache getCache(CalculationJobSpecification spec);

  /**
   * Get this calculation node's ID
   * @return  this calculation node's ID
   */
  String getNodeId();

}
