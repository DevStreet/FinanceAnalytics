title: Excel Functions - H
shortcut: DOC:Excel Functions - H
---
HistoricalExecutionSequence

...........................
HistoricalExecutionSequence
...........................


Generates an execution sequence that causes historical market data to be used.



+--------------------+-------------------------------------------------------------------------------------------------------------------------+
| Parameter          | Description                                                                                                             |
+====================+=========================================================================================================================+
| from               | The market data time of the first cycle                                                                                 |
+--------------------+-------------------------------------------------------------------------------------------------------------------------+
| to                 | The market data time of the last cycle                                                                                  |
+--------------------+-------------------------------------------------------------------------------------------------------------------------+
| samplePeriod       | The period, in seconds, between the market data times of successive cycles, omit for the default (1 day between cycles) |
+--------------------+-------------------------------------------------------------------------------------------------------------------------+
| timeSeriesResolver | Thetime series resolver key, omit for the platform default                                                              |
+--------------------+-------------------------------------------------------------------------------------------------------------------------+



HistoricalMarketDataViewClient

..............................
HistoricalMarketDataViewClient
..............................


Creates a view client descriptor for a sequence of view cycles between two valuation times against the historical market data source.



+-------------------------+-------------------------------------------------------------------------------------+
| Parameter               | Description                                                                         |
+=========================+=====================================================================================+
| view                    | Identifier of the referenced view                                                   |
+-------------------------+-------------------------------------------------------------------------------------+
| firstValuationTime      | Valuation time of the first cycle to run                                            |
+-------------------------+-------------------------------------------------------------------------------------+
| lastValuationTime       | Valuation time of the last cycle to run                                             |
+-------------------------+-------------------------------------------------------------------------------------+
| samplePeriod            | Period (in seconds) between valuation cycles of the view                            |
+-------------------------+-------------------------------------------------------------------------------------+
| timeSeriesResolver      | Resolution strategy for the underlying time-series, omit for platform default       |
+-------------------------+-------------------------------------------------------------------------------------+
| timeSeriesFieldResolver | Resolution strategy for the underlying time-series field, omit for platform default |
+-------------------------+-------------------------------------------------------------------------------------+



