/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.aggregation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.core.position.Portfolio;
import com.opengamma.core.position.PortfolioNode;
import com.opengamma.core.position.Position;
import com.opengamma.core.position.impl.PortfolioImpl;
import com.opengamma.core.position.impl.PortfolioNodeImpl;
import com.opengamma.id.UniqueIdentifier;
import com.opengamma.id.UniqueIdentifierSupplier;

/**
 * An aggregator of portfolios.
 */
public class PortfolioAggregator {
  @SuppressWarnings("unused")
  private static final Logger s_logger = LoggerFactory.getLogger(PortfolioAggregator.class);

  private static final UniqueIdentifierSupplier s_syntheticIdentifiers = new UniqueIdentifierSupplier("PortfolioAggregator");

  private final List<AggregationFunction<?>> _aggregationFunctions;

  public PortfolioAggregator(AggregationFunction<?>... aggregationFunctions) {
    _aggregationFunctions = Arrays.asList(aggregationFunctions);
  }
  
  public PortfolioAggregator(Collection<AggregationFunction<?>> aggregationFunctions) {
    _aggregationFunctions = new ArrayList<AggregationFunction<?>>(aggregationFunctions);
  }

  private static UniqueIdentifier createSyntheticIdentifier() {
    return s_syntheticIdentifiers.get();
  }

  public Portfolio aggregate(Portfolio inputPortfolio) {
    String aggPortfolioId = buildPortfolioName(inputPortfolio.getUniqueId().getValue());
    String aggPortfolioName = buildPortfolioName(inputPortfolio.getName());
    List<Position> flattenedPortfolio = new ArrayList<Position>();
    flatten(inputPortfolio.getRootNode(), flattenedPortfolio);
    UniqueIdentifier aggId = UniqueIdentifier.of(inputPortfolio.getUniqueId().getScheme(), aggPortfolioId);
    final PortfolioNodeImpl root = new PortfolioNodeImpl(createSyntheticIdentifier(), buildPortfolioName("Portfolio"));
    PortfolioImpl aggPortfolio = new PortfolioImpl(aggId, aggPortfolioName, root);
    aggregate(root, flattenedPortfolio, new ArrayDeque<AggregationFunction<?>>(_aggregationFunctions));
    return aggPortfolio;
  }
  
  protected void flatten(PortfolioNode portfolioNode, List<Position> flattenedPortfolio) {
    flattenedPortfolio.addAll(portfolioNode.getPositions());    
    for (PortfolioNode subNode : portfolioNode.getChildNodes()) {
      flatten(subNode, flattenedPortfolio);
    }
  }
  
  protected void aggregate(PortfolioNodeImpl inputNode, List<Position> flattenedPortfolio, Queue<AggregationFunction<?>> functionList) {
    AggregationFunction<?> nextFunction = functionList.remove();
    Map<String, List<Position>> buckets = new TreeMap<String, List<Position>>();
    // drop into buckets - could drop straight into tree but this is easier because we can use faster lookups as we're going.
    for (Position position : flattenedPortfolio) {
      Object obj = nextFunction.classifyPosition(position);
      if (obj != null) {
        String name = obj.toString();
        if (buckets.containsKey(name)) {
          buckets.get(name).add(position);
        } else {
          ArrayList<Position> list = new ArrayList<Position>();
          list.add(position);
          buckets.put(name, list);
        }
      }
    }
    for (String bucketName : buckets.keySet()) {
      PortfolioNodeImpl newNode = new PortfolioNodeImpl();
      newNode.setUniqueId(createSyntheticIdentifier());
      newNode.setParentNodeId(inputNode.getUniqueId());
      newNode.setName(bucketName);
      inputNode.addChildNode(newNode);
      if (functionList.isEmpty()) {
        for (Position position : buckets.get(bucketName)) {
          newNode.addPosition(position);
        }
      } else {
        aggregate(newNode, buckets.get(bucketName), new ArrayDeque<AggregationFunction<?>>(functionList)); // make a copy for each bucket.
      }
    }
  }

  protected String buildPortfolioName(String existingName) {
    StringBuilder aggregatedPortfolioName = new StringBuilder();
    aggregatedPortfolioName.append(existingName);
    aggregatedPortfolioName.append(" aggregated by ");
    for (AggregationFunction<?> aggFunction : _aggregationFunctions) {
      aggregatedPortfolioName.append(aggFunction.getName());
      aggregatedPortfolioName.append(", ");
    }
    if (_aggregationFunctions.size() > 0) {
      aggregatedPortfolioName.delete(aggregatedPortfolioName.length() - 2, aggregatedPortfolioName.length()); // remember it's end index _exclusive_
    }
    return aggregatedPortfolioName.toString();
  }
}
