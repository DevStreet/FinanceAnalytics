title: R View Functions - S
shortcut: DOC:R View Functions - S
---
StaticMarketDataViewClient

..........................
StaticMarketDataViewClient
..........................


Creates a view client descriptor for a single cycle (or manually triggered cycles) of a view from (live) market data.



+------------+----------+----------------------------------------------------+
| Parameter  | Required | Description                                        |
+============+==========+====================================================+
| view       | Yes      | Identifier of the referenced view                  |
+------------+----------+----------------------------------------------------+
| dataSource |          | The source of live data, omit for platform default |
+------------+----------+----------------------------------------------------+




StaticSnapshotViewClient

........................
StaticSnapshotViewClient
........................


Creates a view client descriptor for a single cycle (or manually triggered cycles) of a view from a market data snapshot.



+---------------+----------+-------------------------------------------------------------------------------------------+
| Parameter     | Required | Description                                                                               |
+===============+==========+===========================================================================================+
| view          | Yes      | Identifier of the referenced view                                                         |
+---------------+----------+-------------------------------------------------------------------------------------------+
| snapshot      | Yes      | Identifier of the snapshot to source market data from                                     |
+---------------+----------+-------------------------------------------------------------------------------------------+
| valuationTime |          | Valuation time of the cycles to run, omit to use the system time when the view cycle runs |
+---------------+----------+-------------------------------------------------------------------------------------------+



