/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew;

import com.opengamma.master.AbstractDocument;
import com.opengamma.master.AbstractMaster;

/**
 * Provides the ability to copy portfolios within a master, across masters, between streams/files and masters, and
 * between streams/files.
 */
public abstract interface Syncer<D extends AbstractDocument> {
 
  void sync(AbstractMaster<D> a, AbstractMaster<D> b);

  void sync(AbstractMaster<D> a, AbstractMaster<D> b, LogBack<D> logBack);

}
