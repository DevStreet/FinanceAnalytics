package com.opengamma.integration.copiernew.external;

import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.util.ArgumentChecker;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public class PrettyWriter<E> implements Writeable<E>, Closeable {

  private static final Logger s_logger = LoggerFactory.getLogger(PrettyWriter.class);

  private OutputStream _outputStream;
  private int _count = 0;

  public PrettyWriter(OutputStream outputStream) {
    ArgumentChecker.notNull(outputStream, "OutputStream");
    _outputStream = outputStream;
  }

  @Override
  public void addOrUpdate(E datum) {
    try {
      _outputStream.write((Integer.toString(++_count) + ": " + ToStringBuilder.reflectionToString(datum) + "\n").getBytes());
    } catch (Throwable t) {
      s_logger.error("Could not write datum to output stream");
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
