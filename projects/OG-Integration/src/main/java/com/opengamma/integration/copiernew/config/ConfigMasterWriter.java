package com.opengamma.integration.copiernew.config;

import com.opengamma.core.config.impl.ConfigItem;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.config.ConfigSearchRequest;
import com.opengamma.master.config.ConfigSearchResult;
import com.opengamma.master.config.ConfigSearchSortOrder;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.beancompare.BeanCompare;
import com.opengamma.util.tuple.ObjectsPair;

import javax.time.calendar.ZonedDateTime;
import java.io.IOException;

public class ConfigMasterWriter implements Writeable<ConfigItem> {

  private static final String TEMPLATE_NAME = "<name>";

  ConfigMaster _configMaster;
  private BeanCompare _beanCompare;
  private String _nameTemplate;

  public ConfigMasterWriter(ConfigMaster configMaster) {
    this(configMaster, null);
  }

  public ConfigMasterWriter(ConfigMaster configMaster, String nameTemplate) {
    ArgumentChecker.notNull(configMaster, "configMaster");
    _configMaster = configMaster;
    _beanCompare = new BeanCompare();
    _nameTemplate = nameTemplate;
    if (_nameTemplate != null && !_nameTemplate.contains(TEMPLATE_NAME)) {
      _nameTemplate += TEMPLATE_NAME;
    }
  }

  @Override
  public void addOrUpdate(ConfigItem configItem) {
    ArgumentChecker.notNull(configItem, "configItem");

    ConfigSearchRequest searchReq = new ConfigSearchRequest();
    String name = configItem.getName();

    // Rename portfolio as per supplied template
    if (_nameTemplate != null) {
      name = _nameTemplate.replace(TEMPLATE_NAME, name);
    }

    searchReq.setName(name);
    searchReq.setType(configItem.getClass());

    searchReq.setVersionCorrection(VersionCorrection.ofVersionAsOf(ZonedDateTime.now())); // valid now
    searchReq.setSortOrder(ConfigSearchSortOrder.VERSION_FROM_INSTANT_DESC);
    ConfigSearchResult searchResult = _configMaster.search(searchReq);
    Object foundConfig = searchResult.getFirstValue();
    if (foundConfig != null) {
//      if (!foundConfig.equals(config)) {
        ConfigDocument updateDoc = new ConfigDocument(configItem);
        updateDoc.setName(name);
        updateDoc.setConfig(configItem);
        updateDoc.setUniqueId(searchResult.getFirstDocument().getUniqueId());
        ConfigDocument result = _configMaster.update(updateDoc);
//      }
    } else {
      // Not found, so add it
      ConfigDocument addDoc = new ConfigDocument(configItem);
      addDoc.setConfig(configItem);
      addDoc.setName(name);
      ConfigDocument result = _configMaster.add(addDoc);
    }
  }

  @Override
  public void addOrUpdate(Iterable<ConfigItem> data) {
    for (ConfigItem datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
