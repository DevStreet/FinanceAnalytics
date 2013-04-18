title: Using the Portfolio Saver Tool
shortcut: DOC:Using the Portfolio Saver Tool
---
Usage: scripts/save-portfolio.sh \-f <arg> -n <arg> [-s <arg>] [-v] [-w] [-c <resource>] [-l <resource>] [-h]

\-f,--filename <arg>
The path to the file to create and export to (CSV, XLS or ZIP)

\-n,--name <arg>
The name of the source OpenGamma portfolio

\-s,--securitytype <arg>
The security type to export (ignored if ZIP output file is specified)

\-w,--write
Actually persists the portfolio to the file if specified, otherwise pretty-prints without persisting

\-v,--verbose
Displays progress messages on the terminal

\-c,--config <resource>
The toolcontext configuration resource

\-l,--logback <resource>
The logback configuration resource

\-h,--help
Prints this message

For example, the following command line saves a multi-asset type portfolio named "Multi-Asset Portfolio" to the file multiassetportfolio.zip, using the default toolcontext configuration resource to locate the OpenGamma masters:

scripts/save-portfolio \-n “Multi Security Type Portfolio” \-f multisecuritytpeportfolio.zip \-w

The following command line saves positions in a single security type (equities) from portfolio named "Equity Portfolio" to the file equityportfolio.csv, using the default toolcontext configuration resource to locate the OpenGamma masters:

scripts/save-portfolio.sh \-n “Equity Portfolio” \-s Equity \-f equityportfolio.csv \-w
