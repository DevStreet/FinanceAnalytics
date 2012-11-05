/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.ArgumentChecker;

import java.lang.reflect.Method;

public class Remover {

  Object _master;
  Method _removeMethod;

  public Remover(Object master) {
    ArgumentChecker.notNull(master, "master");
    _master = master;
    try {
      _removeMethod = _master.getClass().getMethod("remove", UniqueId.class);
    } catch (NoSuchMethodException e) {
      throw new OpenGammaRuntimeException("Could not locate a remove method for " + _master + ": " + e.getMessage());
    }
  }

  public void delete(UniqueIdentifiable item) {
    try {
      _removeMethod.invoke(_master, item.getUniqueId());
    } catch (Throwable t) {
      throw new OpenGammaRuntimeException("Could not invoke the remove method on " + _master + ": " + t.getMessage());
    }
  }

  public void delete(Iterable<UniqueIdentifiable> iterable) {
    for (UniqueIdentifiable item : iterable) {
      delete(item);
    }
  }

}
