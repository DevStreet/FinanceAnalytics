package com.opengamma.integration.copiernew.sheet;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.util.ArgumentChecker;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class PrettyWriter<E> implements Writeable<E>, Closeable {

  private OutputStream _outputStream;

  public PrettyWriter(OutputStream outputStream) {
    ArgumentChecker.notNull(outputStream, "OutputStream");
    _outputStream = outputStream;
  }

  @Override
  public void addOrUpdate(E datum) {
    try {
      _outputStream.write((datum.toString() + "\n").getBytes());
    } catch (Throwable t) {
      throw new OpenGammaRuntimeException("Could not write datum to output stream");
    }
  }

  @Override
  public void addOrUpdate(Iterable<E> data) {
    for (E datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void close() throws IOException {
    _outputStream.close();
  }

  @Override
  public void flush() throws IOException {
    _outputStream.flush();
  }
}
