/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.external;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.util.ArgumentChecker;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxReader;
import com.thoughtworks.xstream.io.xml.XppReader;
import org.joda.beans.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.Iterator;

public class XmlReader<T extends Bean> implements Iterable<T> {

  private static final Logger s_logger = LoggerFactory.getLogger(XmlReader.class);

  XStream _xStream;
  ObjectInputStream _objectInputStream;

  public XmlReader(InputStream inputStream) {
    ArgumentChecker.notNull(inputStream, "inputStream");
    _xStream = new XStream(new DomDriver());

    try {
      _objectInputStream = _xStream.createObjectInputStream(inputStream);
    } catch (IOException e) {
      throw new OpenGammaRuntimeException("Could not create an ObjectInputStream");
    }
  }


  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {

      T _buffer;

      {
        try {
          _buffer = (T)_objectInputStream.readObject();
        } catch (Throwable t) {
          _buffer = null;
          s_logger.error("Could not deserialise from XML: " + t.getMessage());
        }
      }

      @Override
      public boolean hasNext() {
        return (_buffer != null);
      }

      @Override
      public T next() {
        T result = _buffer;
        if (_buffer != null) {
          try {
            _buffer = (T)_objectInputStream.readObject();
          } catch (Throwable t) {
            _buffer = null;
            s_logger.error("Could not deserialise from XML: " + t.getMessage());
          }
          return result;
        }
        return result;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("XML reader does not support the remove operation");
      }
    };
  }
}
