title: Platform Overview
shortcut: DOC:Platform Overview
---
This is a high-level summary of the functionality included in the OpenGamma Platform. The components listed below are all available under an Open Source license unless explicitly stated.

..................
What is OpenGamma?
..................


OpenGamma is a platform - a set of components that can be assembled into applications providing analytics and risk services.  These can scale down to the point where the whole system is running on a laptop, or up to a multi-tier distributed architecture.

As a commercial customer we would help provide a customised configuration of the platform, integrated with any existing systems you may have.  With the open source release, we provide out-of-the-box, two example application assemblies that incorporate all of the core open source components to form a working risk/analytics system.

*  **Examples** \- A completely standalone version of the system that uses synthetically created market data to give someone an idea of how the system functions, but doesn't have access to a real live market data source.


*  **Bloomberg Examples** \- An example version of the system that can connect to a local Bloomberg terminal (or potentially a SAPI server) to get real time market data, static security data and historical time series data.


OpenGamma provides several different ways to manage the source data and analyse its results:

*  **Under an Open Source (Apache 2) license**


   *  `OpenGamma Web Interface </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Web-Interface/index.rst>`_  that provides


      *  Viewing and management of securities data, portfolio, position and trade data, historical time series data, holiday data, region data and configuration data.


      *  Ability to run and view live calculating analytics results: things like Historical VaR, Present Value, Greeks and a huge range of other analytics.


   *  `OpenGamma Tools for R </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Tools-for-R/index.rst>`_  that allows data to be injected into our computation engine (market data, portfolio data etc.) and results to flow back into the engine, making it an ideal way of running complex scenarios.


   *  Java, .NET and REST interfaces to enable integration into custom applications and user interfaces


*  **And under a commercial license**


   *  `OpenGamma Tools for Microsoft Excel </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Tools-for-Microsoft-Excel/index.rst>`_  allowing real-time results streaming into Excel, and data injected or modified via Excel


   *  A PnL Explain application to show how and why profits vary


   *  A Market Data Snapshot Manager application to allow curation of market data (surfaces, curves, cubes, etc)


........................................................
Breakdown of services provided by the OpenGamma Platform
........................................................


~~~~~~~~~~~~~~~~~~~~~~
Financial Data Storage
~~~~~~~~~~~~~~~~~~~~~~


These all have both an in-memory implementation and one based on at least two RDBMSs -- currently Hypersonic and PostgreSQL.

*  Security definitions


   *  Static and semi-static data, e.g. strike, expiry, underlying of an equity option


*  Portfolio structures


   *  Hierarchical structures allowing for aggregation points for positions


*  Positions and Trades


   *  Positions and trades themselves, either use ours as-is, implement the interfaces to your own data or ETL from your trading system


*  Historical market data


   *  Time Series of prices, volumes, etc with support for multiple snapshots per day and multiple data providers per data source


*  Holiday schedules


   *  Market opening and closing calendars by region/country, currency or exchange


*  Region structures


   *  Super-national structures like EU, countries, sub-national structures like states, municipalities, etc.


*  Exchanges


   *  Opening hours, MIC code, country code, etc.


*  Configuration


   *  Curve definitions, Cross currency conversion matrices, Surface definitions, View definitions etc.


All data stores, known generally as *Masters* have bi-temporal versioning.

See `Sources, Masters, and Databases </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Sources,-Masters,-and-Databases/index.rst>`_  for more information about these databases, and see `Bi-temporal versioning using versions and corrections </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Bi-temporal-versioning-using-versions-and-corrections/index.rst>`_  for more information about versioning.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Live Market Data Abstraction Layer
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


This provides a common interface to data providers and services such as data normalisation.

*  Bloomberg support is now available as part of the open source release as Bloomberg have open sourced their base API.


*  Thomson Reuters, Activ Financial and Tullett Prebon are available as fully-supported commercial components with source code.


*  Custom implementations for proprietary sources of market data are easy to write and full examples are available in the source code distribution.


See `Major Components and Subsystems </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Major-Components-and-Subsystems/index.rst>`_  for more information.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Fully-Distributed Computation Engine
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


The computation engine uses dependency analysis to remove duplicate calculations and automatically parallelise and distribute the calculation path. It operates in a requirements-driven manner, where users specify the analytics they wish to see and the system figures out how to produce them from the available functions. This ensures that it is easily reconfigurable without additional programming.

Calculations are fully distributed, and intelligent caching improves performance by grouping related calculations onto the same calculation nodes.

See `Views and Results </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Views-and-Results/index.rst>`_  for more information.

`Analytics </confluence/DOC/OpenGamma-Platform-Documentation/Analytics/index.rst>`_ 

The Platform includes our clean room, from-scratch implementation of an analytics library from first principles.

*  Cutting-edge `Asset Class Support </confluence/DOC/OpenGamma-Platform-Documentation/Analytics/Asset-Class-Support/index.rst>`_ 


*  Pure Java, functional style keeps semantics clean


*  Currently has most depth on fixed income, but will increase in breadth in the future


*  Heavily unit tested


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
So what can OpenGamma calculate?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


For a list of supported analytic outputs, see `OpenGamma Analytics Value Requirements </confluence/DOC/OpenGamma-Platform-Documentation/Analytics/OpenGamma-Analytics-Value-Requirements/index.rst>`_ .

For a list of supported asset classes, see `Asset Class Support </confluence/DOC/OpenGamma-Platform-Documentation/Analytics/Asset-Class-Support/index.rst>`_ .
