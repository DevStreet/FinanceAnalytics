package com.opengamma.integration.copiernew.external;

import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.UniqueId;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.util.ArgumentChecker;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Formatter;

public class PrettyWriter<E> implements Writeable<E>, Closeable {

  private static final Logger s_logger = LoggerFactory.getLogger(PrettyWriter.class);

  private Formatter _formatter;
  private int _count = 0;

  public PrettyWriter(OutputStream outputStream) {
    ArgumentChecker.notNull(outputStream, "OutputStream");
    _formatter = new Formatter(outputStream);
  }

  @Override
  public void addOrUpdate(E datum) {
    try {
      String name = (String) datum.getClass().getMethod("getName").invoke(datum);
      UniqueId uniqueId = (UniqueId) datum.getClass().getMethod("getUniqueId").invoke(datum);
      ExternalIdBundle externalIds = (ExternalIdBundle) datum.getClass().getMethod("getExternalIdBundle").invoke(datum);
      _formatter.format("%-24s%-50s%s\n", uniqueId, name, externalIds);
      _formatter.flush();
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
    _formatter.close();
  }

  @Override
  public void flush() throws IOException {
    _formatter.flush();
  }
}
