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
    String name;
    try {
      name = (String) datum.getClass().getMethod("getName").invoke(datum);
    } catch (Throwable t) {
      name = "-";
    }
    UniqueId uniqueId;
    try {
      uniqueId = (UniqueId) datum.getClass().getMethod("getUniqueId").invoke(datum);
    } catch (Throwable t) {
      uniqueId = UniqueId.of("-", "-");
    }
    ExternalIdBundle externalIds;
    try {
      externalIds = (ExternalIdBundle) datum.getClass().getMethod("getExternalIdBundle").invoke(datum);
    } catch (Throwable t) {
      externalIds = ExternalIdBundle.of();
    }
    _formatter.format("%-24s%-50s%s\n", uniqueId, name, externalIds);
    _formatter.flush();
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
