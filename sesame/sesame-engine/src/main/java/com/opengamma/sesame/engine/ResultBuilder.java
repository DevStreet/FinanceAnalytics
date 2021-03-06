/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.engine;

import java.util.List;
import java.util.Map;

import org.threeten.bp.Instant;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import com.opengamma.sesame.trace.CallGraph;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.result.Failure;
import com.opengamma.util.result.FailureStatus;
import com.opengamma.util.result.Result;

/**
 * Mutable builder for the immutable {@link Results} class.
 */
final class ResultBuilder {

  private final Table<Integer, Integer, ResultItem> _table = TreeBasedTable.create();
  private final List<?> _inputs;
  private final List<String> _columnNames;
  private final Map<String, ResultItem> _nonPortfolioResults = Maps.newHashMap();
  private boolean _isPendingMarketData;

  ResultBuilder(List<?> inputs, List<String> columnNames) {
    _inputs = inputs;
    _columnNames = columnNames;
  }

  void add(int rowIndex, int columnIndex, Result<?> result, CallGraph callGraph) {
    _table.put(rowIndex, columnIndex, new ResultItem(result, callGraph));
    checkForPendingData(result);
  }

  void add(String outputName, Result<?> result, CallGraph callGraph) {
    _nonPortfolioResults.put(ArgumentChecker.notEmpty(outputName, "outputName"), new ResultItem(result, callGraph));
    checkForPendingData(result);
  }

  private void checkForPendingData(Result<?> result) {

    if (!_isPendingMarketData && !result.isSuccess()) {
      for (Failure failure : result.getFailures()) {
        if (failure.getStatus() == FailureStatus.PENDING_DATA) {
          _isPendingMarketData = true;
          return;
        }
      }
    }
  }

  Results build(Instant start, long startExecution, long startInitialization, long startResultsBuild) {
    Map<Integer, Map<Integer, ResultItem>> rowMap = _table.rowMap();
    List<ResultRow> rows = Lists.newArrayListWithCapacity(rowMap.size());
    int index = 0;
    for (Map<Integer, ResultItem> row : rowMap.values()) {
      rows.add(new ResultRow(_inputs.get(index++), Lists.newArrayList(row.values())));
    }
    ViewTimer timer = new ViewTimer(start, startInitialization, startExecution, startResultsBuild, System.nanoTime());
    return new Results(_columnNames, rows, _nonPortfolioResults, _isPendingMarketData, timer);
  }
}
