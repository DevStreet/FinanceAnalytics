title: R View Functions - T
shortcut: DOC:R View Functions - T
---
TickingMarketDataViewClient

...........................
TickingMarketDataViewClient
...........................


Creates a view client descriptor for sequential cycles of a view from ticking (live) market data.



+------------+----------+----------------------------------------------------+
| Parameter  | Required | Description                                        |
+============+==========+====================================================+
| view       | Yes      | Identifier of the referenced view                  |
+------------+----------+----------------------------------------------------+
| dataSource |          | The source of live data, omit for platform default |
+------------+----------+----------------------------------------------------+




TickingSnapshotViewClient

.........................
TickingSnapshotViewClient
.........................


Creates a view client descriptor for sequential cycles of a view from a market data snapshot.



+---------------+----------+-------------------------------------------------------------------------------------------+
| Parameter     | Required | Description                                                                               |
+===============+==========+===========================================================================================+
| view          | Yes      | Identifier of the referenced view                                                         |
+---------------+----------+-------------------------------------------------------------------------------------------+
| snapshot      | Yes      | Identifier of the snapshot to source market data from                                     |
+---------------+----------+-------------------------------------------------------------------------------------------+
| valuationTime |          | Valuation time of the cycles to run, omit to use the system time when the view cycle runs |
+---------------+----------+-------------------------------------------------------------------------------------------+




TriggerViewCycle

................
TriggerViewCycle
................


Requests the next cycle of a view client be run, regardless of the view recalculation period or market data/snapshot updates.



+------------+----------+-------------------------------------+
| Parameter  | Required | Description                         |
+============+==========+=====================================+
| viewClient | Yes      | A view client connected to the view |
+------------+----------+-------------------------------------+



This function does not return a value.

