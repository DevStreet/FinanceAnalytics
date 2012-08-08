package com.opengamma.integration.copiernew.sheet;

import com.opengamma.integration.copiernew.sheet.RowReader;
import com.opengamma.util.ArgumentChecker;

import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 6/25/12
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class SheetReader<E> implements Iterable<E> {

  Iterator<Map<String, String>> _iterator;

  private RowReader<E> _rowReader;

  public SheetReader(Iterable<Map<String, String>> iterable, RowReader<E> rowReader) {
    ArgumentChecker.notNull(iterable, "iterator");
    _iterator = iterable.iterator();
    _rowReader = rowReader;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {

      @Override
      public boolean hasNext() {
        return _iterator.hasNext();
      }

      @Override
      public E next() {
        E datum = null;
        do {
          if (!_iterator.hasNext()) {
            return null;
          }
          Map<String, String> row = _iterator.next();
          if (row != null) {
            datum = _rowReader.readRow(row);
          }
        } while (datum == null);

        return datum;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

}
