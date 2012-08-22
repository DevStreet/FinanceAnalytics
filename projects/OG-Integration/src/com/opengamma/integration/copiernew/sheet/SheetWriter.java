package com.opengamma.integration.copiernew.sheet;

import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.sheet.RawSheetWriter;
import com.opengamma.integration.copiernew.sheet.RowWriter;
import com.opengamma.util.ArgumentChecker;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.Map;

public class SheetWriter<E> implements Writeable<E>, Closeable {

  private RowWriter<E> _rowWriter;
  private RawSheetWriter _rawSheetWriter;

  public SheetWriter(RawSheetWriter rawSheetWriter, RowWriter<E> rowWriter) {
    ArgumentChecker.notNull(rawSheetWriter, "rawSheetWriter");
    ArgumentChecker.notNull(rowWriter, "rowWriter");
    _rowWriter = rowWriter;
    _rawSheetWriter = rawSheetWriter;
  }

  @Override
  public void addOrUpdate(E datum) {
    Map<String, String> row = _rowWriter.writeRow(datum);
    if (row != null) {
      _rawSheetWriter.addOrUpdate(row);
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
    _rawSheetWriter.close();
  }

  @Override
  public void flush() throws IOException {
    _rawSheetWriter.flush();
  }
}
