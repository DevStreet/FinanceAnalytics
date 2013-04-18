title: Key OpenGamma Terminology
shortcut: DOC:Key OpenGamma Terminology
---
Working with the OpenGamma Platform requires understanding the terminology used throughout the system. While this terminology is as industry-standard as possible, the particular way in which OpenGamma uses the key terminology is important in understanding how these concepts express themselves on an architectural and end-user level.



+-----------------------------------------------------------------------------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Concept                                                                                 | Description In The OpenGamma Platform                                                                                                                                                                                                                            |
+=========================================================================================+==================================================================================================================================================================================================================================================================+
|  `Security </javadoc/index.html?com/opengamma/core/security/Security.html>`_            | Any asset that may be owned by someone. While some systems divide securities into exchange-traded versus over-the-counter/contract-based, OpenGamma uses the same terminology for all types of assets.                                                           |
+-----------------------------------------------------------------------------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Position </javadoc/index.html?com/opengamma/core/position/Position.html>`_            | A holding in a particular Security. In general, a Position is the quantity of a particular Security held by a trading group.                                                                                                                                     |
+-----------------------------------------------------------------------------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Trade </javadoc/index.html?com/opengamma/core/position/Trade.html>`_                  | A single transaction in which some quantity of a Security is bought or sold. Trades may be netted/aggregated into a Position, or may stand on their own.                                                                                                         |
+-----------------------------------------------------------------------------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Portfolio </javadoc/index.html?com/opengamma/core/position/Portfolio.html>`_          | A named collection of Positions (and thus Trades). Portfolios can be aggregated in a number of ways, or left flat.                                                                                                                                               |
+-----------------------------------------------------------------------------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PortfolioNode </javadoc/index.html?com/opengamma/core/position/PortfolioNode.html>`_  | A particular point in the aggregation structure of a Portfolio. Even flat Portfolios (without any additional aggregation done) have at least one Portfolio Node, the root one which holds all Positions.                                                         |
+-----------------------------------------------------------------------------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Market Data                                                                             | Any data that varies over time and is quoted by some market. These markets may be public (stock exchanges), private (swap quotes aggregated by market data vendors or brokers), or even user-defined (trader marked mean reversions parsed from private emails). |
+-----------------------------------------------------------------------------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



.......
Example
.......


A desk trades fixed income instruments as part of a proprietary trading strategy. Each instrument that they may have a long or short position in is represented as a Security (whether listed, like an interest rate future, or a contract like an ISDA swap). The desk might also wish to view a number of Securities that they have not traded, either because they use them to construct yield and funding curves, or because they want to monitor some universe of instruments that they might take a position in.

An Individual transaction is represented as a Trade. When it is natural, on exchanges say, to net one's trades in a particular asset, OpenGamma provides a View on the Position in that Security. Suppose a desk has hedged their € interest rate exposure by making infrequent trades in a single Bond future (500 long, 200 short, 50 long). This strategy will have 3 Trades, but only a single Position (of 350 contracts).

Now consider the same strategy in an OTC market. If the desk made similar trades in 10Y € swaps, the securities' spot dates would not line up and the positions would not truly offset. In this case, the desk has decided to aggregate at the strategy level, so each  strategy is represented as a Portfolio Node (which contains Positions  underneath it; note that position netting is typically only done at the  Portfolio Node level). These Portfolio Nodes (including one for the  root) combine to form the entire desk's Portfolio.

In the case where the desk is part of a larger organization, the desk itself could be represented as a Portfolio Node in a larger Portfolio that represents a whole department or division. Unlike systems that have a fixed hierarchy (such as Book/Ledger/Portfolio), the OpenGamma Platform allows for any type of hierarchy desired; it also allows the same Trades or Positions to show up in multiple Portfolios for different aggregation strategies.


When calculations on this Portfolio are run, the OpenGamma Engine will dynamically determine the Market Data requirements (either ticking or historical) and use those to perform necessary calculations.
