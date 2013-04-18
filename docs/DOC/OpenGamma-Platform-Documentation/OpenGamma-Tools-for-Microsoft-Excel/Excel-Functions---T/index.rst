title: Excel Functions - T
shortcut: DOC:Excel Functions - T
---
TakeSnapshot

............
TakeSnapshot
............


Takes a snapshot of the market data required to run the next engine cycle.



+--------------+---------------------------------------------------------------------+
| Parameter    | Description                                                         |
+==============+=====================================================================+
| viewClientId | The identifier of the view client from which to obtain the snapshot |
+--------------+---------------------------------------------------------------------+



This function is capable of live operation. If automatic sheet calculation is enabled it may return new values pushed from the OpenGamma Platform even if the input parameters have not changed.

This function is available on a worksheet only and cannot be used from Visual Basic.

TakeSnapshotNow

...............
TakeSnapshotNow
...............


Takes a values from the next cycle of a view client, automatically triggering a cycle.



+----------------+-----------------------------------+
| Parameter      | Description                       |
+================+===================================+
| view_client_id | The identifier of the view client |
+----------------+-----------------------------------+



TickingMarketDataViewClient

...........................
TickingMarketDataViewClient
...........................


Creates a view client descriptor for sequential cycles of a view from ticking (live) market data.



+------------+----------------------------------------------------+
| Parameter  | Description                                        |
+============+====================================================+
| view       | Identifier of the referenced view                  |
+------------+----------------------------------------------------+
| dataSource | The source of live data, omit for platform default |
+------------+----------------------------------------------------+



TickingSnapshotViewClient

.........................
TickingSnapshotViewClient
.........................


Creates a view client descriptor for sequential cycles of a view from a market data snapshot.



+---------------+-------------------------------------------------------------------------------------------+
| Parameter     | Description                                                                               |
+===============+===========================================================================================+
| view          | Identifier of the referenced view                                                         |
+---------------+-------------------------------------------------------------------------------------------+
| snapshot      | Identifier of the snapshot to source market data from                                     |
+---------------+-------------------------------------------------------------------------------------------+
| valuationTime | Valuation time of the cycles to run, omit to use the system time when the view cycle runs |
+---------------+-------------------------------------------------------------------------------------------+



TimeSeries

..........
TimeSeries
..........


Retrieves a time series. Deprecated in favor of FetchTimeSeries.



+------------+----------------------------------------------+
| Parameter  | Description                                  |
+============+==============================================+
| type       | The type of the time series                  |
+------------+----------------------------------------------+
| identifier | An identifier of the target                  |
+------------+----------------------------------------------+
| resolver   | A key defining how to obtain the time series |
+------------+----------------------------------------------+



Trade

.....
Trade
.....


Defines a trade.



+-----------------+----------------------------------------------------------------------+
| Parameter       | Description                                                          |
+=================+======================================================================+
| security        | The identifier(s) of the security                                    |
+-----------------+----------------------------------------------------------------------+
| quantity        | The amount of the position                                           |
+-----------------+----------------------------------------------------------------------+
| counterparty    | The counterparty                                                     |
+-----------------+----------------------------------------------------------------------+
| tradeDate       | The trade date                                                       |
+-----------------+----------------------------------------------------------------------+
| tradeTime       | The trade time                                                       |
+-----------------+----------------------------------------------------------------------+
| premium         | The amount paid for the trade at the time of purchase                |
+-----------------+----------------------------------------------------------------------+
| premiumCurrency | The currency of payment at time of purchase                          |
+-----------------+----------------------------------------------------------------------+
| premiumDate     | The date of premium payment which may be different to the trade date |
+-----------------+----------------------------------------------------------------------+
| premiumTime     | The time of premium payment which may be different to the trade time |
+-----------------+----------------------------------------------------------------------+



TradeSetAttribute

.................
TradeSetAttribute
.................


Sets (or clears) an attribute on a trade. The original object is unchanged - a new object is returned with the updated value.



+-----------+---------------------------------------------------------------------------+
| Parameter | Description                                                               |
+===========+===========================================================================+
| trade     | The trade to update                                                       |
+-----------+---------------------------------------------------------------------------+
| attribute | The attribute(s) to set                                                   |
+-----------+---------------------------------------------------------------------------+
| value     | The value(s) to set, omit or use the empty string to remove the attribute |
+-----------+---------------------------------------------------------------------------+



TradeSetDealAttributes

......................
TradeSetDealAttributes
......................


Sets one or more deal attributes on a trade.



+------------+-------------------------------------------------+
| Parameter  | Description                                     |
+============+=================================================+
| trade      | The trade to update                             |
+------------+-------------------------------------------------+
| attributes | A comma-separated list of attribute=value pairs |
+------------+-------------------------------------------------+



TriggerCycle

............
TriggerCycle
............


Triggers a cycle on the given view client.



+-------------+----------------------------------------------------------------------+
| Parameter   | Description                                                          |
+=============+======================================================================+
| view_client | The identifier of the view client on which a cycle will be triggered |
+-------------+----------------------------------------------------------------------+



