/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.config;

import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.ArgumentChecker;

public class ConfigEntry<T>  implements UniqueIdentifiable {

  private String _configName;
  private T _configEntry;
  private UniqueId _uniqueId;

  public ConfigEntry(String configName, T configEntry, UniqueId uniqueId) {
    ArgumentChecker.notNull(configName, "configName");
    ArgumentChecker.notNull(configEntry, "configEntry");
    ArgumentChecker.notNull(uniqueId, "uniqueId");

    _configName = configName;
    _configEntry = configEntry;
    _uniqueId = uniqueId;
  }

  public String getConfigName() {
    return _configName;
  }

  public T getConfigEntry() {
    return _configEntry;
  }

  @Override
  public UniqueId getUniqueId() {
    return _uniqueId;
  }
}
