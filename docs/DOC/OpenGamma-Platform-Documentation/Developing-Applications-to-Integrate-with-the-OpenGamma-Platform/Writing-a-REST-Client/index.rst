title: Writing a REST Client
shortcut: DOC:Writing a REST Client
---
All OpenGamma clients ultimately use the same underlying REST API to access remote components of the OpenGamma Platform. In supported languages, such as Java and C#, the REST API has already been wrapped into a library for use by remote clients. However, it is easy to use OpenGamma components from any language by using the REST API directly.

..................
RESTful Components
..................


Components are specified and constructed through the `Component-based Configuration </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/index.rst>`_ , and any such component may be `Component RESTful publishing </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Component-RESTful-publishing/index.rst>`_ . The RESTful components are rooted at `/jax/components` on any OpenGamma instance, and this root URL exposes a list of the available components, making it easy to see what is available.

This is described in detail in the documentation referred to above.

........
Messages
........


When a response is provided from a REST call, or content is required when making REST call, the server expects to use Fudge messages. `Fudge <http://www.fudgemsg.org>`_  is the messaging system used across the OpenGamma Platform.

~~~~~~~~~~~
Media Types
~~~~~~~~~~~


The supported media types for Fudge messages are:


*  `application/vnd.fudgemsg` for `binary-encoded Fudge <http://fudgemsg.org/display/FDG/Encoding+Specification>`_ 


*  `application/xml` for `XML-encoded Fudge <http://fudgemsg.org/display/FDG/XML+Fudge+Messages>`_ 


*  `application/json` for `JSON-encoded Fudge <http://fudgemsg.org/display/FDG/JSON+Fudge+Messages>`_  (some messages only)


A media type should be specified in the `Accept` and `Content-Type` headers as applicable, to direct the encoding expected and/or produced by the REST call.

........
Examples
........


In these examples it is assumed that we have access to a remote instance of the OpenGamma platform where:

*  the view processor is running on the host `opengamma-server`


*  the REST API is configured to use port 8080


For clarity, all examples will use XML-encoded Fudge.

~~~~~~~~~~~~~~~~~~~~~
Looking up a Security
~~~~~~~~~~~~~~~~~~~~~


Suppose we wish to retrieve the security with the Bloomberg Ticker `AAPL US Equity`. Visiting the URL


.. code::

    http://opengamma-server:8080/jax/components



we are given a list of components including the 'shared' Security Source:


.. code::

    <fudgeField type="message">
      <type type="string">com.opengamma.core.security.SecuritySource</type>
      <classifier type="string">shared</classifier>
      <uri type="string">/jax/components/SecuritySource/shared</uri>
      <attributes type="message"></attributes>
    </fudgeField>



This provides a root URL for the Security Source:


.. code::

    http://opengamma-server:8080/jax/components/SecuritySource/shared



Visiting this URL, we are given an HTML summary of the available REST operations including one for finding a single security:


.. code::

    com.opengamma.financial.security.DataFinancialSecuritySourceResource
    ...
    GET /jax/components/SecuritySource/shared/securitySearches/single => "searchSingle"



This tells us to refer to the `searchSingle` method of `DataFinancialSecuritySourceResource`. To search for details about the security with the Bloomberg Ticker `AAPL US Equity` we perform a `GET` operation on


.. code::

    http://opengamma-server:8080/jax/components/SecuritySource/shared/securitySearches/single?id=BLOOMBERG_TICKER~AAPL%20US%20Equity



This returns the full security object - in this case an `EquitySecurity`:


.. code::

    <fudgeEnvelope>
      <uniqueId type="message">
        <Scheme type="string">DbSec</Scheme>
        <Value type="string">1002</Value>
        <Version type="string">983</Version>
      </uniqueId>
      <name type="string">APPLE INC</name>
      <securityType type="string">EQUITY</securityType>
      <identifiers type="message">
        <ID type="message">
          <Scheme type="string">BLOOMBERG_BUID</Scheme>
          <Value type="string">EQ0010169500001000</Value>
        </ID>
        <ID type="message">
          <Scheme type="string">BLOOMBERG_TICKER</Scheme>
          <Value type="string">AAPL US Equity</Value>
        </ID>
        <ID type="message">
          <Scheme type="string">CUSIP</Scheme>
          <Value type="string">037833100</Value>
        </ID>
        <ID type="message">
          <Scheme type="string">ISIN</Scheme>
          <Value type="string">US0378331005</Value>
        </ID>
        <ID type="message">
          <Scheme type="string">SEDOL1</Scheme>
          <Value type="string">2046251</Value>
        </ID>
      </identifiers>
      <attributes type="message">
        <fudgeField-1 ordinal="-1" type="string">java.lang.CharSequence</fudgeField-1>
        <fudgeField-2 ordinal="-2" type="string">java.lang.CharSequence</fudgeField-2>
        <fudgeField1 ordinal="1" type="string">preferred</fudgeField1>
        <fudgeField2 ordinal="2" type="string">false</fudgeField2>
      </attributes>
      <shortName type="string">AAPL</shortName>
      <exchange type="string">NASDAQ/NGS (GLOBAL SELECT MARKET)</exchange>
      <exchangeCode type="string">XNGS</exchangeCode>
      <companyName type="string">APPLE INC</companyName>
      <currency type="string">USD</currency>
      <gicsCode type="int">45202010</gicsCode>
      <fudgeField0 ordinal="0" type="string">com.opengamma.financial.security.equity.EquitySecurity</fudgeField0>
      <fudgeField0 ordinal="0" type="string">com.opengamma.financial.security.FinancialSecurity</fudgeField0>
      <fudgeField0 ordinal="0" type="string">com.opengamma.master.security.ManageableSecurity</fudgeField0>
      <fudgeField0 ordinal="0" type="string">org.joda.beans.impl.direct.DirectBean</fudgeField0>
    </fudgeEnvelope>




~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Searching the Historical Time-Series Master
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Suppose we wish to search for historical time-series related to the Bloomberg Ticker `AAPL US Equity`. Visiting the URL


.. code::

    http://opengamma-server:8080/jax/components



we are given a list of components including the 'central' Historical Time-Series Master:


.. code::

    <fudgeField type="message">
      <type type="string">com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster</type>
      <classifier type="string">central</classifier>
      <uri type="string">/jax/components/HistoricalTimeSeriesMaster/central</uri>
      <attributes type="message">
        <uniqueIdScheme type="string">DbHts</uniqueIdScheme>
      </attributes>
    </fudgeField>



This provides a root URL for the Historical Time-Series Master:


.. code::

    http://opengamma-server:8080/jax/components/HistoricalTimeSeriesMaster/central



Visiting this URL, we are given an HTML summary of the available REST operations including one for searching the time-series metadata:


.. code::

    com.opengamma.master.historicaltimeseries.impl.DataHistoricalTimeSeriesMasterResource
    ...
    POST /jax/components/HistoricalTimeSeriesMaster/central/infoSearches => "search"



This tells us to refer to the `search` method of `DataHistoricalTimeSeriesMasterResource`. To search for historical time-series related to the security with the Bloomberg Ticker `AAPL US Equity` we need to `POST` a `HistoricalTimeSeriesInfoSearchRequest` message. The simplest search request would be:


.. code::

    <fudgeEnvelope>
      <pagingRequest type="message">
        <first type="byte">0</first>
        <size type="int">2147483647</size>
      </pagingRequest>
      <versionCorrection type="message"></versionCorrection>
      <externalIdSearch type="message">
        <identifiers type="message">
          <fudgeField type="message">
            <Scheme type="string">BLOOMBERG_TICKER</Scheme>
            <Value type="string">AAPL US Equity</Value>
          </fudgeField>
        </identifiers>
        <searchType type="string">ANY</searchType>
      </externalIdSearch>
    </fudgeEnvelope>



POSTing this to the URL


.. code::

    http://opengamma-server:8080/jax/components/HistoricalTimeSeriesMaster/central/infoSearches



produces a response consisting of a `HistoricalTimeSeriesInfoSearchResult` containing the metadata for any matching time-series, for example:


.. code::

    <fudgeEnvelope>
      <paging type="message">
        <first type="byte">0</first>
        <size type="int">2147483647</size>
        <total type="byte">2</total>
      </paging>
      <documents type="message">
        <fudgeField type="message">
          <versionFromInstant type="datetime">2012-04-10T17:55:26.778404Z</versionFromInstant>
          <correctionFromInstant type="datetime">2012-04-10T17:55:26.778404Z</correctionFromInstant>
          <info type="message">
            <uniqueId type="string">DbHts~1000~0</uniqueId>
            <externalIdBundle type="message">
              <ID type="message">
                <Scheme type="string">BLOOMBERG_BUID</Scheme>
                <Value type="string">EQ0010169500001000</Value>
              </ID>
              <ID type="message">
                <Scheme type="string">BLOOMBERG_TICKER</Scheme>
                <Value type="string">AAPL US Equity</Value>
              </ID>
              <ID type="message">
                <Scheme type="string">CUSIP</Scheme>
                <Value type="string">037833100</Value>
              </ID>
              <ID type="message">
                <Scheme type="string">ISIN</Scheme>
                <Value type="string">US0378331005</Value>
              </ID>
              <ID type="message">
                <Scheme type="string">SEDOL1</Scheme>
                <Value type="string">2046251</Value>
              </ID>
            </externalIdBundle>
            <name type="string">PX_LAST AAPL US Equity</name>
            <dataField type="string">PX_LAST</dataField>
            <dataSource type="string">BLOOMBERG</dataSource>
            <dataProvider type="string">CMPL</dataProvider>
            <observationTime type="string">LONDON_CLOSE</observationTime>
            <timeSeriesObjectId type="string">DbHts~DP1000</timeSeriesObjectId>
          </info>
          <uniqueId type="string">DbHts~1000~0</uniqueId>
        </fudgeField>
        <fudgeField type="message">
          <versionFromInstant type="datetime">2012-04-10T17:55:30.354634Z</versionFromInstant>
          <correctionFromInstant type="datetime">2012-04-10T17:55:30.354634Z</correctionFromInstant>
          <info type="message">
            <uniqueId type="string">DbHts~1010~0</uniqueId>
            <externalIdBundle type="message">
              <ID type="message">
                <Scheme type="string">BLOOMBERG_BUID</Scheme>
                <Value type="string">EQ0010169500001000</Value>
              </ID>
              <ID type="message">
                <Scheme type="string">BLOOMBERG_TICKER</Scheme>
                <Value type="string">AAPL US Equity</Value>
              </ID>
              <ID type="message">
                <Scheme type="string">CUSIP</Scheme>
                <Value type="string">037833100</Value>
              </ID>
              <ID type="message">
                <Scheme type="string">ISIN</Scheme>
                <Value type="string">US0378331005</Value>
              </ID>
              <ID type="message">
                <Scheme type="string">SEDOL1</Scheme>
                <Value type="string">2046251</Value>
              </ID>
            </externalIdBundle>
            <name type="string">VOLUME AAPL US Equity</name>
            <dataField type="string">VOLUME</dataField>
            <dataSource type="string">BLOOMBERG</dataSource>
            <dataProvider type="string">CMPL</dataProvider>
            <observationTime type="string">LONDON_CLOSE</observationTime>
            <timeSeriesObjectId type="string">DbHts~DP1010</timeSeriesObjectId>
          </info>
          <uniqueId type="string">DbHts~1010~0</uniqueId>
        </fudgeField>
      </documents>
      <fudgeField0 ordinal="0" type="string">com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchResult</fudgeField0>
      <fudgeField0 ordinal="0" type="string">com.opengamma.master.AbstractSearchResult</fudgeField0>
      <fudgeField0 ordinal="0" type="string">com.opengamma.master.AbstractDocumentsResult</fudgeField0>
      <fudgeField0 ordinal="0" type="string">org.joda.beans.impl.direct.DirectBean</fudgeField0>
    </fudgeEnvelope>



The metadata contains the `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  of each time-series. This can be used to retrieve the time-series using the REST operation:


.. code::

    com.opengamma.master.historicaltimeseries.impl.DataHistoricalTimeSeriesMasterResource
    ...
    GET /jax/components/HistoricalTimeSeriesMaster/central/dataPoints/{dpId} => "get"



Performing a `GET` on the URL


.. code::

    http://opengamma-server:8080/jax/components/HistoricalTimeSeriesMaster/central/dataPoints/DbHts~DP1000



we are given the full time-series (shortened here for brevity):


.. code::

    <fudgeEnvelope>
      <uniqueId type="string">DbHts~DP1000~2012-06-28T04:30:41.031668ZPT0S</uniqueId>
      <versionInstant type="datetime">2012-06-28T04:30:41.031668Z</versionInstant>
      <correctionInstant type="datetime">2012-06-28T04:30:41.031668Z</correctionInstant>
      <timeSeries type="message">
        <fudgeField0 ordinal="0" type="string">com.opengamma.util.timeseries.localdate.ArrayLocalDateDoubleTimeSeries</fudgeField0>
        <fudgeField1 ordinal="1" type="message">
          <fudgeField0 ordinal="0" type="string">com.opengamma.util.timeseries.localdate.LocalDateEpochDaysConverter</fudgeField0>
          <fudgeField1 ordinal="1" type="string">UTC</fudgeField1>
        </fudgeField1>
        <fudgeField2 ordinal="2" type="message">
          <fudgeField0 ordinal="0" type="string">com.opengamma.util.timeseries.fast.integer.FastArrayIntDoubleTimeSeries</fudgeField0>
          <fudgeField1 ordinal="1" type="message">
            <fudgeField0 ordinal="0" type="string">com.opengamma.util.timeseries.fast.DateTimeNumericEncoding</fudgeField0>
            <fudgeField1 ordinal="1" type="string">DATE_EPOCH_DAYS</fudgeField1>
          </fudgeField1>
          <fudgeField2 ordinal="2" type="int[]">4698,4701,4702,......</fudgeField2>
          <fudgeField3 ordinal="3" type="double[]">4.047,3.953,3.75,......</fudgeField3>
        </fudgeField2>
      </timeSeries>
      <fudgeField0 ordinal="0" type="string">com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeries</fudgeField0>
      <fudgeField0 ordinal="0" type="string">org.joda.beans.impl.direct.DirectBean</fudgeField0>
    </fudgeEnvelope>



