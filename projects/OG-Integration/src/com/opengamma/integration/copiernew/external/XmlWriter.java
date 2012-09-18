/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.external;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.Writeable;

import com.opengamma.util.ArgumentChecker;
import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.StaxWriter;
import org.joda.beans.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class XmlWriter<T extends Bean> implements Writeable<T> {

  private static final Logger s_logger = LoggerFactory.getLogger(XmlWriter.class);

  XStream _xStream;
  ObjectOutputStream _objectOutputStream;

  public XmlWriter(OutputStream outputStream) {
    ArgumentChecker.notNull(outputStream, "outputStream");
    // http://old.nabble.com/PrettyPrintWriter-and-StaxDriver--td4079982.html (Jörg Schaible Apr 26, 2006)
    _xStream = new XStream(new StaxDriver() {
      public HierarchicalStreamWriter createWriter(OutputStream out) {
        try {
          return new StaxWriter(
            getQnameMap(),
            new IndentingXMLStreamWriter(getOutputFactory().createXMLStreamWriter(out)),
            true,
            isRepairingNamespace()
          );
        } catch (XMLStreamException e) {
          throw new StreamException(e);
        }
      }
    });
    try {
      _objectOutputStream = _xStream.createObjectOutputStream(outputStream);
    } catch (IOException e) {
      throw new OpenGammaRuntimeException("Could not create an ObjectOutputStream");
    }
  }

  @Override
  public void addOrUpdate(T datum) {
    try {
      _objectOutputStream.writeObject(datum);
    } catch (IOException e) {
      s_logger.error("Could not addOrUpdate: " + e.getMessage());
    }
  }

  @Override
  public void addOrUpdate(Iterable<T> data) {
    for (T datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    _objectOutputStream.flush();
  }

  public void close() throws IOException {
    _objectOutputStream.close();
  }
}
