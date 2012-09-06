/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.config;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.ConfigSearchRequest;
import com.opengamma.master.config.ConfigSearchResult;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.ObjectsPair;

import java.util.Iterator;

public class ConfigMasterReader<T> implements Iterable<ObjectsPair<String, T>> {

  private ConfigSearchResult<T> _configSearchResult;

  public ConfigMasterReader(ConfigMaster configMaster) {
    this(configMaster, null);
  }

  public ConfigMasterReader(ConfigMaster configMaster, ConfigSearchRequest<T> configSearchRequest) {
    ArgumentChecker.notNull(configMaster, "configMaster");
    if (configSearchRequest == null) {
      configSearchRequest = new ConfigSearchRequest<T>();
    }
    _configSearchResult = configMaster.search(configSearchRequest);
  }

  @Override
  public Iterator<ObjectsPair<String, T>> iterator() {
    return new Iterator<ObjectsPair<String, T>>() {

      Iterator<ConfigDocument<T>> iterator = _configSearchResult.getDocuments().iterator();

      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      public ObjectsPair<String, T> next() {
        ConfigDocument<T> doc = iterator.next();
        return new ObjectsPair<String, T>(doc.getName(), doc.getValue());
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove is not supported on this iterator");
      }
    };
  }
}
