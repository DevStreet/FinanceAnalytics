title: Using the Portfolio Loader Tool
shortcut: DOC:Using the Portfolio Loader Tool
---
Usage: scripts/load-portfolio.sh \-n <arg> -f <arg> [-s <arg>] [-o] [-v] [-w] [-c <resource>] [-l <resource>] [-h]

\-n,--name <arg>
The name of the destination OpenGamma portfolio

\-f,--filename <arg>
The path to the file containing data to import (CSV, XLS or ZIP)

\-s,--security <arg>
The security type expected in the input CSV/XLS file (ignored if ZIP file is specified)

\-w,--write
Actually persists the portfolio to the database if specified, otherwise pretty-prints without persisting

\-o,--overwrite
Deletes any existing matching securities, positions and portfolios and recreates them from input data

\-v,--verbose
Displays progress messages on the terminal

\-c,--config <resource>
The toolcontext configuration resource

\-l,--logback <resource>
The logback configuration resource

\-h,--help
Prints this message

For example, the following command line loads a multi-asset type portfolio from the file multiassetportfolio.zip into a portfolio named "Multi-Asset Portfolio", using the default toolcontext configuration resource:

scripts/load-portfolio.sh \-f examples/multisecuritytypeportfolio.zip \-n “Multi Security Type Portfolio” \-w

The following command line loads positions in a single security type (equities) from the file equityportfolio.csv into a portfolio named "Equity Portfolio", using the default toolcontext configuration resource to locate the OpenGamma masters:

scripts/load-portfolio \-f examples/equityportfolio.csv \-s Equity \-n “Equity Portfolio” \-w

equityportfolio.csv sample:




::

    "companyName","currency","exchange","exchangeCode","externalIdBundle","name","position:quantity","securityType","shortName","trade:counterpartyExternalId","trade:deal","trade:premium","trade:premiumCurrency","trade:premiumDate","trade:premiumTime","trade:quantity","trade:tradeDate","trade:tradeTime"
    "EXXON MOBIL CORP","USD","NEW YORK STOCK EXCHANGE INC.","XNYS","BLOOMBERG_BUID~EQ0010054600001000, BLOOMBERG_TICKER~XOM US Equity, CUSIP~30231G102, ISIN~US30231G1022, SEDOL1~2326618","EXXON MOBIL CORP","1264","EQUITY","XOM",,,,,,,,,
    "APPLE INC","USD","NASDAQ/NGS (GLOBAL SELECT MARKET)","XNGS","BLOOMBERG_BUID~EQ0010169500001000, BLOOMBERG_TICKER~AAPL US Equity, CUSIP~037833100, ISIN~US0378331005, SEDOL1~2046251","APPLE INC","257","EQUITY","AAPL",,,,,,,,,
    "MICROSOFT CORP","USD","NASDAQ/NGS (GLOBAL SELECT MARKET)","XNGS","BLOOMBERG_BUID~EQ0010174300001000, BLOOMBERG_TICKER~MSFT US Equity, CUSIP~594918104, ISIN~US5949181045, SEDOL1~2588173","MICROSOFT CORP","3740","EQUITY","MSFT",,,,,,,,,



