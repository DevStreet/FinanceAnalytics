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
import com.opengamma.util.paging.Paging;
import com.opengamma.util.paging.PagingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ConfigMasterReader<T> implements Iterable<ConfigEntry<T>> {

  private static final Logger s_logger = LoggerFactory.getLogger(ConfigMasterReader.class);

  private ConfigMaster _configMaster;
  private ConfigSearchRequest<T> _configSearchRequestTemplate;

  public ConfigMasterReader(ConfigMaster configMaster) {
    this(configMaster, null);
  }

  public ConfigMasterReader(ConfigMaster configMaster, ConfigSearchRequest<T> configSearchRequest) {
    this(configMaster, configSearchRequest, PagingRequest.DEFAULT_PAGING_SIZE);
  }

  public ConfigMasterReader(ConfigMaster configMaster, ConfigSearchRequest<T> configSearchRequest, int bufferSize) {
    ArgumentChecker.notNull(configMaster, "configMaster");
    ArgumentChecker.notNegativeOrZero(bufferSize, "bufferSize");
    if (configSearchRequest == null) {
      configSearchRequest = new ConfigSearchRequest<T>();
      configSearchRequest.setPagingRequest(PagingRequest.ofIndex(0, bufferSize));
    }
    _configSearchRequestTemplate = configSearchRequest;
    _configMaster = configMaster;
  }

  @Override
  public Iterator<ConfigEntry<T>> iterator() {
    return new Iterator<ConfigEntry<T>>() {

      private int _nextPageIndex;
      private ConfigSearchResult<T> _configSearchResult;
      private Iterator<ConfigDocument<T>> _iterator;

      {
        _nextPageIndex = 0;
        turnPage();
      }

      @Override
      public boolean hasNext() {
        if (!_iterator.hasNext()) {
          return !_configSearchResult.getPaging().isLastPage();
        } else {
          return true;
        }
      }

      @Override
      public ConfigEntry<T> next() {
        while (true) {
          try {
            ConfigDocument<T> doc = _iterator.next();
            return new ConfigEntry<T>(doc.getName(), doc.getValue(), doc.getUniqueId());
          } catch (NoSuchElementException e) {
            if (!_configSearchResult.getPaging().isLastPage()) {
              turnPage();
            } else {
              throw new NoSuchElementException();
            }
          }
        }
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove is not supported on this iterator");
      }

      private void turnPage() {
        while (true) {
          ConfigSearchRequest<T> configSearchRequest = _configSearchRequestTemplate;
          configSearchRequest.setPagingRequest(PagingRequest.ofIndex(_nextPageIndex, _configSearchRequestTemplate.getPagingRequest().getPagingSize()));
          _nextPageIndex += _configSearchRequestTemplate.getPagingRequest().getPagingSize();
          try {
            _configSearchResult = _configMaster.search(configSearchRequest);
            _iterator = _configSearchResult.getDocuments().iterator();
            return;
          } catch (Throwable t) {
            s_logger.error("Error performing config master search: " + t.getMessage());
          }
        }
      }

    };
  }
}
