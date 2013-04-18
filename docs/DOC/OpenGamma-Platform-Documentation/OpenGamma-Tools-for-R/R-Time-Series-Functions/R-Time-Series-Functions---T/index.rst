title: R Time Series Functions - T
shortcut: DOC:R Time Series Functions - T
---
as.xts.TimeSeries

.................
as.xts.TimeSeries
.................


Converts a core R time-series object to a XTS time-series. Time-series returned from the Java stack use a start date which is the day offset from 1970-01-01. Using this conversion will remove any NA values and set the start date of the XTS time-series using a correct R date object.

Calling this function will import the XTS package if it is not already loaded. This function will fail and return NA if the XTS package is not available.



+-----------+----------+-------------------------------+
| Parameter | Required | Description                   |
+===========+==========+===============================+
| data      | Yes      | Time-series object to convert |
+-----------+----------+-------------------------------+




FetchTimeSeries

...............
FetchTimeSeries
...............


Retrieves a time-series from the system .



+------------------------+----------+------------------------------------------------------------------------+
| Parameter              | Required | Description                                                            |
+========================+==========+========================================================================+
| identifier             | Yes      | The identifier or identifier bundle of the time-series to load         |
+------------------------+----------+------------------------------------------------------------------------+
| start                  |          | The start date, omit to load from the earliest date available          |
+------------------------+----------+------------------------------------------------------------------------+
| end                    |          | The end date, omit to load until the latest date available             |
+------------------------+----------+------------------------------------------------------------------------+
| dataField              |          | The type of data required, e.g. PX_LAST                                |
+------------------------+----------+------------------------------------------------------------------------+
| resolutionKey          |          | The key to resolve the correct time-series                             |
+------------------------+----------+------------------------------------------------------------------------+
| inclusiveStart         |          | Whether to include the start date in the time-series, defaults to TRUE |
+------------------------+----------+------------------------------------------------------------------------+
| inclusiveEnd           |          | Whether to include the end date in the time-series, defaults to FALSE  |
+------------------------+----------+------------------------------------------------------------------------+
| dataSource             |          | The data source                                                        |
+------------------------+----------+------------------------------------------------------------------------+
| dataProvider           |          | The data provider                                                      |
+------------------------+----------+------------------------------------------------------------------------+
| identifierValidityDate |          | The date that the identifier must be valid on                          |
+------------------------+----------+------------------------------------------------------------------------+
| maxPoints              |          | The maximum number of points to retrieve, omit to retrieve all         |
+------------------------+----------+------------------------------------------------------------------------+




StoreTimeSeries

...............
StoreTimeSeries
...............


Persists a time-series to the system database, updating an existing one if the identifier and other key fields match.



+-----------------+----------+----------------------------------------------------------------------+
| Parameter       | Required | Description                                                          |
+=================+==========+======================================================================+
| timeseries      | Yes      | The time-series object to persist                                    |
+-----------------+----------+----------------------------------------------------------------------+
| name            | Yes      | The name of the time-series                                          |
+-----------------+----------+----------------------------------------------------------------------+
| identifier      | Yes      | The identifier or identifier bundle of the time-series               |
+-----------------+----------+----------------------------------------------------------------------+
| dataField       | Yes      | The type of data                                                     |
+-----------------+----------+----------------------------------------------------------------------+
| dataSource      | Yes      | The data source                                                      |
+-----------------+----------+----------------------------------------------------------------------+
| dataProvider    | Yes      | The data provider                                                    |
+-----------------+----------+----------------------------------------------------------------------+
| observationTime | Yes      | The observation time of each sample                                  |
+-----------------+----------+----------------------------------------------------------------------+
| master          |          | The master to store the time-series in, omit for the session default |
+-----------------+----------+----------------------------------------------------------------------+



This function does not return a value.

