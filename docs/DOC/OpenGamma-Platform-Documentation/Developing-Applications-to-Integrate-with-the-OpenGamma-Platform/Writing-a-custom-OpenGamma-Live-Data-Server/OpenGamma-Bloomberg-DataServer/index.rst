title: OpenGamma Bloomberg DataServer
shortcut: DOC:OpenGamma Bloomberg DataServer
---
............
Introduction
............


There are two main services provided to get financial data from Bloomberg, namely LiveBloombergDataServer and StaticBloombergDataServer. LiveDataServer produces real-time financial data while StaticDataServer is used for financial data that are not real-time in nature, for example security data, historical timeseries and security reference fields. 

`AbstractStaticBloombergDataServer` uses the request/response paradigm (see Bloomberg API developer guide 2.3) to request for static financial data. There are three classes that extend it to provide reference, historical and entitlement services:


*  `BloombergReferenceDataProvider`


*  `BloombergHistoricalTimeSeriesSource`


*  `BloombergEntitlementChecker`


If these classes do not meet your needs, you can extend `AbstractBloombergStaticDataProvider` to call `submitBloombergRequest` with a request object, get a correlationID for the request and then call `getResultElement` and process the result. For example, to make Intraday Tick Request:


.. code::

    ...
    Request request = getRefDataService().createRequest("IntradayTickRequest");
    request.set("security", "VOD LN Equity");
    request.append("eventTypes", "TRADE");
    request.append("eventTypes", "AT_TRADE");
    request.set("startDateTime", new Datetime(2010, 07, 26, 10, 30, 0, 0));
    request.set("endDateTime", new Datetime(2010, 07, 26, 14, 30, 0, 0));
    ...
    CorrelationID cid = submitBloombergRequest(request);
    BlockingQueue<Element> resultElements = getResultElement(cid);




Process the result elements accordingly.


..............................
BloombergReferenceDataProvider
..............................


`BloombergReferenceDataProvider` is a service for reading securities fields from Bloomberg. Create an instance with `SessionOptions` and call to `getFields` returns the requested reference data in a `ReferenceDataResult` object. `getFields` arguments are a collection of security and valid Bloomberg fileds. The security format is:

::

    /[Topic Prefix]/SYMBOLOGY[@Pricing Source][Exchange]

where `Topic Prefix` is one of the following: `ticker`, `cusip`, `wpk`, `isin`, `buid`, `sedol1`, `sedol2`, `sicovam`, `common`, `bsid`, `svm`, `cins`, `cats`, `bbgid`. (See Bloomberg API developer guide section 6.1.1 for more details.)


.. code::

    SessionOptions sessionOptions = new SessionOptions();
    sessionOptions.setServerHost("localhost");
    sessionOptions.setServerPort(8194);
    BloombergReferenceDataProvider dataProvider = new BloombergReferenceDataProvider(sessionOptions);
    dataProvider.start();
    Set<String> securities = ImmutableSet.of("AAPL US Equity", "/buid/EQ0082335400001000");
    Set<String> fields = ImmutableSet.of("PX_LAST", "VWAP_VOLUME");
    ReferenceDataResult results = dataProvider.getFields(securities, fields);
    dataProvider.stop();



`ReferenceDataResult` is a map of securityID to ReferenceData per security. `AAPL US Equity` PX*LAST and VWAP*VOLUME fields result can be obtained by:


.. code::

    PerSecurityReferenceDataResult aaplResult = results.getResult("AAPL US Equity");



The `PerSecurityReferenceDataResult` contains the result `Element` returned by Bloomberg, collections of security exception and fields exception and fields value written as a `FudgeMessage`. To obtain the requested `PX_LAST` and `VWAP_VOLUME`:


.. code::

    FudgeMsg fieldData = aaplResult.getFieldData();
    Long pxLast = fieldData.getLong("PX_LAST");
    Long vwapVolume = fieldData.getLong("VWAP_VOLUME");




...................................
BloombergHistoricalTimeSeriesSource
...................................


`BloombergHistoricalTimeSeriesSource` provides methods to load historical **End-of-Day** reference data from Bloomberg. It is an implementation of HistoricalTimeSeriesSource against the Bloomberg API and some of the methods are not applicable.


.. code::

    BloombergHistoricalTimeSeriesSource bbgHtsSource = new BloombergHistoricalTimeSeriesSource(sessionOptions);
    bbgHtsSource.start();
    HistoricalTimeSeries timeSeries = bbgHtsSource.getHistoricalTimeSeries(ExternalIdBundle.of(SecurityUtils.bloombergTickerSecurityId("MSFT US Equity")),
            BloombergConstants.BLOOMBERG_DATA_SOURCE_NAME, "ETPX", "PX_LAST");
    bbgHtsSource.stop();



The `dataProvider` argument is the same as `Pricing Source` in Bloomberg terminology (see Bloomberg developer guide section 6.1.2). To find what pricing sources are available for a security, load the security then type PCS<GO> on your Bloomberg Terminal. If `Pricing Source` is not available or is unknown, use `BloombergConstants.DATA_PROVIDER_UNKNOWN`.
