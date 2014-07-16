/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master;

/**
 * TODO not sure where this should go so it's accessible to everything that needs it
 * TODO should this return a RemoteComponentInfo or something? a bean?
 */
public interface ComponentResource<T> {

  public RemoteComponentInfo<T> getComponentInfo();
}
