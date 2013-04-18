title: Historical Data Connectivity
shortcut: DOC:Historical Data Connectivity
---
OpenGamma provides a fully implemented historical time series storage and retrieval system.  It is separated into several parts:


*  The generic database implementation, accessed via the `TimeSeriesMaster` interface, which supports


   *  Intra-day tick data (date/time level of accuracy per data point) - this is not currently used by the OpenGamma infrastructure.


   *  Daily data samples (date level of accuracy per data point)


   *  Versioning, including corrections are supported efficiently.


*  The historical data system presented to the rest of the system via the HistoricalDataSource interface, which currently only supports daily sampled data.


*  Data loaders


   *  These are typically standalone programs that can be run as scheduled tasks (e.g. via cron/anacron or the Windows Scheduler)


   *  They are located in the underlying data source projects

