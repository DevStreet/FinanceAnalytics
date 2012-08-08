/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.integration.copiernew;

import java.io.Closeable;
import java.io.Flushable;

/**
 * Interface for a writer
 */
public interface Writeable<E> extends Flushable {

  E addOrUpdate(E datum);
        
}
