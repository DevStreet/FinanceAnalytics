title: Using the Portfolio Template Creation Tool
shortcut: DOC:Using the Portfolio Template Creation Tool
---
Usage: scripts/create-portfolio-template.sh \-f <arg> -s <arg> [-l <resource>] [-c <resource>] [-h] 

\-f,--filename <arg>
The path to the file to create and export to (CSV, XLS or ZIP)

\-s,--securitytype <arg>
The security type to export (ignored if ZIP output file is specified)

\-c,--config <resource>
The toolcontext configuration resource

\-l,--logback <resource>
The logback configuration resource

\-h,--help
Prints this message

For example, the following command line creates a CSV file that contains the column headings for Bond securities:

scripts/create-portfolio-template.sh \-f bondportfolio.csv \-s Bond

The resulting CSV file can be populated with bond positions using a spreadsheet or a text editor and subsequently imported using the portfolio loader tool:




::

    "announcementDate","businessDayConvention","couponFrequency","couponRate","couponType","currency","dayCount","externalIdBundle","firstCouponDate","guaranteeType","interestAccrualDate","issuancePrice","issuerDomicile","issuerName","issuerType","lastTradeDate","market","minimumAmount","minimumIncrement","name","parAmount","position:quantity","redemptionValue","securityType","settlementDate","totalAmountIssued","trade:counterpartyExternalId","trade:deal","trade:premium","trade:premiumCurrency","trade:premiumDate","trade:premiumTime","trade:quantity","trade:tradeDate","trade:tradeTime","yieldConvention"



