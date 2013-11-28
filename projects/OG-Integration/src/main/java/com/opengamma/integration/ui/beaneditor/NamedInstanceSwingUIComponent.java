/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.integration.ui.beaneditor;

import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComboBox;

import org.joda.beans.Bean;
import org.joda.beans.ui.form.MetaUIComponent;
import org.joda.beans.ui.swing.SwingUIComponent;
import org.joda.beans.ui.swing.component.JValidatedFields;

import com.opengamma.financial.convention.NamedInstance;
import com.opengamma.financial.convention.NamedInstanceFactory;
import com.opengamma.util.ClassUtils;

/**
 * An instantiated Swing component for a {@code NamedInstance}.
 */
public class NamedInstanceSwingUIComponent extends SwingUIComponent<JComboBox<NamedInstance>> {

  /**
   * Creates an instance.
   * 
   * @param metaComponent  the meta-component, not null
   */
  public NamedInstanceSwingUIComponent(MetaUIComponent metaComponent) {
    super(metaComponent);
    Class<?> type = metaComponent.getPropertyType();
    @SuppressWarnings("rawtypes")
    Class<? extends NamedInstanceFactory> factoryType = ClassUtils.loadClassRuntime(type.getName() + "Factory").asSubclass(NamedInstanceFactory.class);
    NamedInstanceFactory<?> factory = ClassUtils.singletonInstance(factoryType);
    Map<String, NamedInstance> map = new TreeMap<>(factory.instanceMap());
    JComboBox<NamedInstance> component = JValidatedFields.createCombobox(map, metaComponent.isMandatory(), true);
    setComponent(component);
  }

  //-------------------------------------------------------------------------
  @Override
  public void updateUI(Bean bean) {
    NamedInstance value = (NamedInstance) getMetaProperty().get(bean);
    getComponent().getModel().setSelectedItem(value);
  }

}
