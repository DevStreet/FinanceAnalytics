title: Using the Historical Time Series Loader Tool
shortcut: DOC:Using the Historical Time Series Loader Tool
---
A simple historical time series loading tool is provided with OpenGamma. It provides evaluators and users with the ability to populate new or existing historical time series with data points that are laid out in a simple CSV or Excel sheet, without needing access to a suitable data feed. Each sheet can contain data points from several historical time series, since an ID column is used to demultiplex the data points, while the remaining two columns are titled Date and Value. Other parameters such as the data field to populate, the data provider, the data source and the ID scheme to use are supplied through the tool command line.

A script is provided with the Bloomberg examples. Alternatively, the tool class may be accessed from within OG-Integration as&nbsp;com.opengamma.integration.tool.timeseries.TimeSeriesLoaderTool.


Usage: load-timeseries.sh&nbsp;
\-f <arg>&nbsp;
\-d <arg> \-i&nbsp;
<arg> \-o <arg> \-p <arg> \-s <arg>  \[-t <arg>\]&nbsp;
\[-w\]&nbsp;
\[
\-c <resource>\]
&nbsp;
\[-l <resource>\]&nbsp;
&nbsp;
\[-h\]


\-f,--filename <arg>

The path to the file containing data to import (CSV or ZIP)


\-d,--field <arg>

The name of the time series data field


\-i,--scheme <arg>

The time series ID scheme (e.g. RIC)


\-o,--time <arg>

The time series observation time


\-p,--provider <arg>

The name of the time series data provider


\-s,--source <arg>

The name of the time series data source


\-t,--date <arg>

The JodaTime date format (e.g. yyyyMMdd)


\-w,--write

Actually persists the time series to the database if specified, otherwise pretty-prints&nbsp;
without persisting


\-c,--config <resource>

the toolcontext configuration resource


\-l,--logback <resource>

The logback configuration resource


\-h,--help

prints this message


For example, the following command line loads data points from multiple time series stored in the file multitimeseries.csv into the historical time series master with data field PX_LAST, data source BLOOMBERG, data provider CMPL, observation time LONDON_CLOSE and ID scheme BLOOMBERG_BUID, using the default toolcontext configuration resource:


scripts/load-timeseries.sh \-f multitimeseries.csv \-d PX_LAST \-s BLOOMBERG \-p CMPL \-o LONDON_CLOSE \-i BLOOMBERG_BUID \-w


multitimeseries.csv sample:



::

    "Id","Date","Value"
    "BBSW3M Index",20120106,4.415
    "BBSW3M Index",20120105,4.425
    "BBSW3M Index",20120104,4.44



