title: Sources, Masters, and Databases
shortcut: DOC:Sources, Masters, and Databases
---
OpenGamma Platform installations are extremely data intensive. Modern computational finance requires an extreme amount of data:

*  Reference data on companies, securities, customers, and markets


*  Market data (both live and historical)


*  Portfolio data indicating what a firm has traded


*  Configuration data storing the types of calculations that users need performed


*  Result data for past calculations


This document describes the general way in which the OpenGamma Platform handles these data requirements.

...........
Conventions
...........


In order to store and provide access to each type of data in a consistent way, the OpenGamma Platform adopts naming and functionality conventions for the interfaces used to satisfy any data requirements.

~~~~~~~
Sources
~~~~~~~


All types of data required by a running OpenGamma calculation process are obtained from a *source* of that data; these are represented by interfaces with the `Source` suffix. For example, when a running calculation needs to obtain a `Security </javadoc/index.html?com/opengamma/core/security/Security.html>`_, it does so through a `SecuritySource </javadoc/index.html?com/opengamma/core/security/SecuritySource.html>`_; when it needs a `Position </javadoc/index.html?com/opengamma/core/position/Position.html>`_, it gets it from a `PositionSource </javadoc/index.html?com/opengamma/core/position/PositionSource.html>`_.

These interfaces, by design, are simple and usually have only a few operations for the data type they represent:

*  load an instance of the data type by a `UniqueId </javadoc/index.html?com/opengamma/id/UniqueId.html>`_;


*  resolve one or more instances of the data type by an `ExternalId </javadoc/index.html?com/opengamma/id/ExternalId.html>`_ or `ExternalIdBundle </javadoc/index.html?com/opengamma/id/ExternalIdBundle.html>`_;


*  load one or more versions of the same item using time parameters for historical lookups.


Relying on such simplistic interface operations makes it extremely easy to support connections to different sources of data without having to run through an expensive ETL process where that data is already available.

`Source` interfaces usually provide a caching implementation that wraps another *source*; therefore, we do not recommend that custom `Source` implementations do their own caching.

```````````````````
Compositing Sources
```````````````````


A powerful consequence of providing data access through such simple `Source` interfaces is the ability to present a single composite view of the data required, even though the actual data may be stored in a number of underlying systems. This may be done for a variety of reasons, including:

*  to build a single risk application on top of different underlying trading systems;


*  to source exchange traded securities from a firm-wide golden source security master, but OTC contract terms from a desk-specific system;


*  to source fully booked trades from a database, but hypothetical trades from memory.


The OpenGamma Platform contains a number of `Source` implementations that decorate other `Source` instances specifically for compositing purposes. For example, see `DelegatingSecuritySource </javadoc/index.html?com/opengamma/core/security/impl/DelegatingSecuritySource.html>`_, which can composite any number of other `SecuritySource </javadoc/index.html?com/opengamma/core/security/SecuritySource.html>`_ instances together to provide a single source of *security* data to a calculation process.

~~~~~~~
Masters
~~~~~~~


While the *sources* provide the minimum amount of functionality necessary for inclusion of data into the OpenGamma Platform, their functionality is intentionally very limited. Common operations, like searching or adding new data, are not part of the `Source` interfaces, so they are not appropriate for many use cases. For this additional layer of functionality, each data entity has an additional and corresponding *master*; these are represented by interfaces with the `Master` suffix, such as `SecurityMaster </javadoc/index.html?com/opengamma/master/security/SecurityMaster.html>`_.

The `Master` interfaces expand the *source* functionality to the full set that end users would expect:

*  searching by common search terms for elements of interest;


*  changing individual data elements (add, update, correct, delete);


*  loading the full history for individual data elements.


............
Data Storage
............


~~~~~~~~~
Databases
~~~~~~~~~


OpenGamma has at least one relational database implementation of each `Source` and `Master` interface. These databases, though fully optional, will usually be the default way that users feed data into an OpenGamma Platform installation.

The underlying OpenGamma database schemas were designed to provide the ideal, complete set of features for the interfaces:

*  Where data is a representation of an *opinion* (such as a calculation configuration), it is stored as a time-series. This allows the rest of the system to load any item of data as of any particular point of time in the past.


*  Where data is a representation of a *fact* (such as a trade that either did or did not take place, or an end of day price), it is stored as a bi-temporal structure that allows looking up history both as of a particular point in time in the past, but also as of any correction that might have been made.


*  They are designed to be fast for the operations the rest of the system are likely to require.


*  They are designed to precisely match the way in which the OpenGamma Platform handles `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_ .


*  They are easy to port to different database vendors, allowing more flexibility in optimizing your run time and development time architecture.


Moving data into and out of an OpenGamma database schema is best done though our code-level or remote API systems, as all logic relating to the time-series implementations is done in code (to assist in database portability).

~~~~~~~~~~~~
Alternatives
~~~~~~~~~~~~


There are a number of different ways to feed data into the OpenGamma Platform *without* using our database schemas.

Where data doesn't have strict long-term storage requirements (such as hypothetical positions and trades), OpenGamma has in many cases in-memory forms of all our `Source` and `Master` interfaces.

We have produced experimental implementations in "NoSQL" databases such as MongoDB, although we have not maintained a MongoDB Security database for some time because we could not find an efficient way to support bi-temporal versioning in MongoDB given its lack of transactions.

Where loading from files is common, we have implemented flat-file loading mechanisms through the `Source` system.

~~~~~~~~~~~~~~~~~
Source or Master?
~~~~~~~~~~~~~~~~~


A common question when attempting to produce support for a custom data store (such as an existing database or legacy application) is whether to implement a `Source` or a `Master`.

Here are some guidelines to help you decide:

*  Do you only need to load data elements into the system without end-user capabilities?


   *  Write a `Source`


*  Do you need the ability to load data elements with particular histories?


   *  Write a `Master`


*  Do you need to combine data from different data sources?


   *  Use a Compositing `Source`


*  Do you want the rich history capabilities the OpenGamma Platform supports on a data source that doesn't support them?


   *  Use an ETL process to move the data to an OpenGamma Database instance


.................
Data Type Listing
.................


Below is a summary of the specific data types, and the names of the corresponding `Source` and `Master` interfaces which are part of the OpenGamma Platform. Each of these has a fully operational implementation.



+-----------------------------------------+---------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Type of Data                            | Source Interface                                                                                                          | Master Interface                                                                                                          | Anything Else?                                                                                                                                                                                                    |
+=========================================+===========================================================================================================================+===========================================================================================================================+===================================================================================================================================================================================================================+
|  securities Security Data               |  `SecuritySource </javadoc/index.html?com/opengamma/core/security/SecuritySource.html>`_                                  |  `SecurityMaster </javadoc/index.html?com/opengamma/master/security/SecurityMaster.html>`_                                | Any semi-static security specific data, including OTC securities                                                                                                                                                  |
+-----------------------------------------+---------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  portfolios Portfolio Structures        |  `PositionSource </javadoc/index.html?com/opengamma/core/position/PositionSource.html>`_                                  |  `PortfolioMaster </javadoc/index.html?com/opengamma/master/portfolio/PortfolioMaster.html>`_                             | Hierarchical Portfolio structure supports aggregation points for results.  Note: Source unified with Positions and Trades                                                                                         |
+-----------------------------------------+---------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  positions-trades Positions and Trades  |  `PositionSource </javadoc/index.html?com/opengamma/core/position/PositionSource.html>`_                                  |  `PositionMaster </javadoc/index.html?com/opengamma/master/position/PositionMaster.html>`_                                | Portfolio structure is separated from positions so structure can be layered over flat external sources of positions/trades                                                                                        |
+-----------------------------------------+---------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Configuration                           |  `ConfigSource </javadoc/index.html?com/opengamma/core/config/ConfigSource.html>`_                                        |  `ConfigMaster </javadoc/index.html?com/opengamma/master/config/ConfigMaster.html>`_                                      | Arbitrary fudge-encoded, versioned, configuration objects identifier by a simple name key.  Examples are yield/surface definitions and view definitions                                                           |
+-----------------------------------------+---------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Exchanges                               |  `ExchangeSource </javadoc/index.html?com/opengamma/core/exchange/ExchangeSource.html>`_                                  |  `ExchangeMaster </javadoc/index.html?com/opengamma/master/exchange/ExchangeMaster.html>`_                                | Information about exchanges such as name, ISO MIC code, region etc                                                                                                                                                |
+-----------------------------------------+---------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Regions                                 |  `RegionSource </javadoc/index.html?com/opengamma/core/region/RegionSource.html>`_                                        |  `RegionMaster </javadoc/index.html?com/opengamma/master/region/RegionMaster.html>`_                                      | Support for multiple overlapping hierarchies of region information e.g. countries, unions (e.g EU), with ISO codes, Currency codes, etc.  Can also support detailed municipalities and so on for credit purposes. |
+-----------------------------------------+---------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Holiday Calendars                       |  `HolidaySource </javadoc/index.html?com/opengamma/core/holiday/HolidaySource.html>`_                                     |  `HolidayMaster </javadoc/index.html?com/opengamma/master/holiday/HolidayMaster.html>`_                                   | Stores bank holiday (keyed by region), currency holidays (keyed by currency), settlement holidays (keyed by exchange) and trading holidays (keyed by exchange)                                                    |
+-----------------------------------------+---------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Market Data Snapshots                   |  `MarketDataSnapshotSource </javadoc/index.html?com/opengamma/master/marketdatasnapshot/MarketDataSnapshotSource.html>`_  |  `MarketDataSnapshotMaster </javadoc/index.html?com/opengamma/master/marketdatasnapshot/MarketDataSnapshotMaster.html>`_  | Structured market data snapshots (point data, yield curves, surfaces, cubes), with overrides capability.                                                                                                          |
+-----------------------------------------+---------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Historical Data                         |  `HistoricalDataSource </javadoc/index.html?com/opengamma/core/historicaldata/HistoricalDataSource.html>`_                |  `HistoricalTimeSeriesMaster </javadoc/index.html?com/opengamma/master/timeseries/HistoricalTimeSeriesMaster.html>`_      | Supports multiple named snapshots per day, fields (e.g. LAST_PRICE, VOLUME, LAST_VOLATILITY), data providers (e.g. internal composites), data sources (e.g. Bloomberg, Reuters, Activ, etc)                       |
+-----------------------------------------+---------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+


