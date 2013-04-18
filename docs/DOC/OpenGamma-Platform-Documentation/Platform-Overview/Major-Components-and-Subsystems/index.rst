An error occurred while processing cwiki markup.
The OpenGamma Platform is a modern software stack at the centre of which is the OpenGamma Engine, a distributed computation engine designed specifically for financial calculations. The Platform consists of various components and subsystems which can all be customised or replaced to meet the needs of any particular installation.

This document explores the broad categories of components and subsystems which occur in the OpenGamma Platform.

!Platform Architecture 2 extracted (no names).png|align=center,border=0!

h2. Data Management

Data components form a large part of the OpenGamma Platform, reflecting the importance and variety of data required in modern computational finance. There are two distinct types of data requirements: live streaming data, and everything else.
{anchor:ogld}

h3. Live Data

Live data is anything that may change in real-time, which is often provided on a subscription basis from external systems. Furthermore, it is often the data for which changes must take effect as soon as possible in computations.

The OpenGamma Engine supports live data as the ultimate source of input data into the dependency graph, with changes able to trigger a computation cycle. This is normally satisfied by external market data. Market data sources may be based on the _OpenGamma Live Data_ subsystem for feed aggregation and processing, or developers may provide custom implementations directly by implementing the required interfaces.

h4. OpenGamma Live Data

{excerpt-include:Writing A Custom OpenGamma Live Data Server|nopanel=true}

Developers wishing to write their own _OpenGamma Live Data_ server around a custom feed of live data should refer to [Writing a custom OpenGamma Live Data Server].

{tip:title=Bloomberg|icon=false}
We are pleased to say we can now provide a Bloomberg OpenGamma Live Data Server as part of our open source release as a result of [Bloomberg's Open Market Data Initiative|http://open.bloomberg.com].  This provides support for integration with:
* Desktop API - this requires a subscription to a Bloomberg Terminal
* Managed B-PIPE (or SAPI/Server API) - this requires a Managed B-PIPE/SAPI server and subscription
{tip}
{info}
Commercial customers have access to three built-in market data feed handlers fully supported by OpenGamma, and implemented as _OpenGamma Live Data_ servers:
* Thomson Reuters (using the Thomson Reuters Enterprise Platform for Data Management/RMDS products)
* ACTIV Financial
* Tullett-Prebon

These components are available for evaluation use by [contacting OpenGamma|http://www.opengamma.com/contact].
{info}

Since _OpenGamma Live Data_ is a standalone library, the client portion may be used by other, arbitrary systems to access live data from an _OpenGamma Live Data_ server, without any dependencies on the rest of the OpenGamma Platform.

h3. Non-Live Data

Data which is not expected to change in real-time is that which is accessed on demand by querying, and is typically stored in a database. This includes reference data as well as trading data such as positions. This forms the majority of data which the OpenGamma Platform requires, and there is extensive discussion of its treatment in [Sources, Masters, and Databases].

Access to Bloomberg security reference data is available through the [Bloomberg Reference Data] implementation in the newly open sourced OpenGamma Bloomberg Module.

h2. Calculations

Calculations are performed by the OpenGamma Engine across the available computation resources. This sits at the heart of the Platform, and at its core is a dependency graph executor which transforms a declarative definition of the required outputs into the calculated results. The Engine is responsible for meeting client demand for particular computation results, and for ensuring that this occurs in the most efficient way possible.

For a complete overview, see [Views and Results].

h2. Clients

Clients are a key tool in allowing users to harness the power of the OpenGamma Platform. Several fully-supported clients are available, and it is also possible to develop custom clients against our language-specific APIs, or from any language against our REST API.

h3. Web Client

The web client is our standard interface to the OpenGamma Platform, which provides streaming computation results to the browser as well as the ability to view and modify all [Non-Live Data|#Non-Live Data] available to the platform.

For a complete overview, see [OpenGamma Web Interface].

h3. OpenGamma Tools for Microsoft Excel

{info}
This is a commercial component. It is available for evaluation use by [contacting OpenGamma|http://www.opengamma.com/contact].
{info}

[The OpenGamma Tools for Microsoft Excel|OpenGamma Tools for Microsoft Excel] are based around an XLL plugin with a unique feature set, allowing for powerful, natural interaction with the services available through the OpenGamma Platform. It allows, from within the Excel environment:
* the ad-hoc creation of securities, positions, portfolios and view definitions
* computations to be driven around any of this ad-hoc, or permanent, data
* computation results, whether driven from Excel or not, to be streamed to Excel or any other client

These tools use the core language services also used by [#The R Statistical Programming Language], which can be configured to run as a Windows service.

For a complete overview, see [OpenGamma Tools for Microsoft Excel].

h3. The R Statistical Programming Language

[The OpenGamma R Integration|OpenGamma Tools for R] is primarily intended to provide facilities for scenario analysis and, as such, allows R functions to be used to perturb input data and run batch jobs based on snapshots and/or historical data. R's powerful graphing and statistical analysis functions can then be used to post-process the results.

This integration uses the core language services also used by the [#OpenGamma Tools for Microsoft Excel].

h3. Developer APIs for Custom Clients

Developer APIs are provided for popular languages which provide full, remote client access to to the OpenGamma Platform.

h4. Java

The Java API is the same API used internally in the Platform and by many of the supported clients.

For a complete overview, see [Writing a Java Client].

h4. .NET

The .NET API allows the full power of the OpenGamma Platform to be harnessed from languages such as C#, for easy integration with existing applications or to access powerful graphical rendering capabilities.

For a complete overview, see [Writing a C Sharp Client].

h4. REST

The REST API is used by both the [#Java] and [#.NET] APIs to provide remote access to the OpenGamma Platform, and it is available for use directly from any language. It provides a natural, REST-oriented view of the Platform including full support for injecting ad-hoc data structures, driving computations and retrieving results.

