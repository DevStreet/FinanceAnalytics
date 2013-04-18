title: Portfolio File Formats
shortcut: DOC:Portfolio File Formats
---
...................................................
Simple Portfolio File Format (Single Security Type)
...................................................


Positions in a single security type can be stored externally in a CSV (comma-separated value) or XLS (Excel) file. The first row contains the field names which are used to map data in the subsequent rows onto the properties of the relevant OpenGamma data structures. Subsequent row data consists of properties of the relevant security type, as well as position/trade fields. Column order is irrelevant and any extra columns are ignored, as are quotes, header case and leading/trailing spaces. Each row normally represents a single position, although trade rows are expected to be supported in future. For instance, this example represents a list of positions in equities:



::

    "companyName","currency","exchange","exchangeCode","externalIdBundle","name","position:quantity","securityType","shortName","trade:counterpartyExternalId","trade:deal","trade:premium","trade:premiumCurrency","trade:premiumDate","trade:premiumTime","trade:quantity","trade:tradeDate","trade:tradeTime"
    "OCCIDENTAL PETROLEUM CORP","USD","NEW YORK STOCK EXCHANGE INC.","XNYS","CUSIP~674599105, ISIN~US6745991058, OG_SYNTHETIC_TICKER~OXY","OCCIDENTAL PETROLEUM CORP","320","EQUITY","","CPARTY~BACS","","","","","","320","2010-12-03",""
    "EXXON MOBIL CORP","USD","NEW YORK STOCK EXCHANGE INC.","XNYS","BLOOMBERG_BUID~EQ0010054600001000, BLOOMBERG_TICKER~XOM US Equity, CUSIP~30231G102, ISIN~US30231G1022, SEDOL1~2326618","EXXON MOBIL CORP","4330","EQUITY","XOM","CPARTY~BACS","","","","","","2000","2010-12-01",""
    "CONOCOPHILLIPS","USD","NEW YORK STOCK EXCHANGE INC.","XNYS","CUSIP~20825C104, ISIN~US20825C1045, OG_SYNTHETIC_TICKER~COP","CONOCOPHILLIPS","2740","EQUITY","","CPARTY~BACS","","","","","","2000","2010-12-01",""
    "MURPHY OIL CORP","USD","NEW YORK STOCK EXCHANGE INC.","XNYS","CUSIP~626717102, ISIN~US6267171022, OG_SYNTHETIC_TICKER~MUR","MURPHY OIL CORP","430","EQUITY","","CPARTY~BACS","","","","","","430","2010-12-03",""
    "CHEVRON CORP","USD","NEW YORK STOCK EXCHANGE INC.","XNYS","BLOOMBERG_BUID~EQ0010031500001000, BLOOMBERG_TICKER~CVX US Equity, CUSIP~166764100, ISIN~US1667641005, SEDOL1~2838555","CHEVRON CORP","260","EQUITY","CVX","CPARTY~BACS","","","","","","260","2010-12-03",""
    "HESS CORP","USD","NEW YORK STOCK EXCHANGE INC.","XNYS","CUSIP~42809H107, ISIN~US42809H1077, OG_SYNTHETIC_TICKER~HES","HESS CORP","100","EQUITY","","CPARTY~BACS","","","","","","100","2010-12-03",""
    "MARATHON OIL CORP","USD","NEW YORK STOCK EXCHANGE INC.","XNYS","CUSIP~565849106, ISIN~US5658491064, OG_SYNTHETIC_TICKER~MRO","MARATHON OIL CORP","3000","EQUITY","","CPARTY~BACS","","","","","","2000","2010-12-01",""




..........................................................
Structured Portfolio File Format (Multiple Security Types)
..........................................................


This file format represents positions in multiple security types within a hierarchical portfolio structure, and is useful for backing up and moving portfolios across different instances of the OpenGamma platform. The actual position entries are held in CSV files as described above, encapsulated within a ZIP file. Positions in each security type are represented by a separate CSV file, where the case-sensitive name of the CSV file denotes the security type within.



::

    $ unzip -l MultiAsset.zip 
    Archive:  MultiAsset.zip
      Length      Date    Time    Name
    ---------  ---------- -----   ----
          125  11-24-2011 10:57   Cash.csv
          673  01-24-2012 18:49   EquityFuture.csv
          306  11-24-2011 10:57   FRA.csv
          166  01-25-2012 17:49   FX.csv
          195  11-24-2011 10:57   FXForward.csv
          534  11-24-2011 10:57   IRFuture.csv
          381  11-24-2011 10:57   IRFutureOption.csv
        15970  11-24-2011 10:57   Swap.csv
          439  11-24-2011 10:57   Swaption.csv
          224  11-24-2011 10:57   VanillaFXOption.csv
           14  01-27-2012 12:03   METADATA.INI
    ---------                     -------
        19027                     11 files




In the example above, MultiAsset.zip contains position data for ten security types. In addition, the mandatory METADATA.INI file holds archive meta-data such as version hashes for the security types present in the file. While in this instance the described portfolio is flat, the structured portfolio file format can also represent hierarchically structured portfolios by locating the CSV files in specific folders in the ZIP file.

........................
Supported Security Types
........................


The following is a list of currently supported security types, together with the list of fields that are represented for that security type in the portfolio file format. Not all the fields are actually required to construct the OpenGamma data structures, and some fields are actually left empty when exporting to a file. For instance, swap fields are included for all possible leg types, but only a subset are actually used for a particular leg.


**AgricultureFuture**: "commodityType", "currency", "expiry", "externalIdBundle", "name", "position:quantity", "securityType", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "unitAmount", "unitName", "unitNumber"

**Bond**: "announcementDate", "businessDayConvention", "couponFrequency", "couponRate", "couponType", "currency", "dayCount", "externalIdBundle", "firstCouponDate", "guaranteeType", "interestAccrualDate", "issuancePrice", "issuerDomicile", "issuerName", "issuerType", "lastTradeDate", "market", "minimumAmount", "minimumIncrement", "name", "parAmount", "position:quantity", "redemptionValue", "securityType", "settlementDate", "totalAmountIssued", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "yieldConvention"

**BondFuture**: "basket", "bondType", "currency", "expiry", "externalIdBundle", "firstDeliveryDate", "lastDeliveryDate", "name", "position:quantity", "securityType", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "unitAmount"

**CapFloorCMSSpread**: "cap", "currency", "dayCount", "externalIdBundle", "frequency", "longId", "maturityDate", "name", "notional", "payer", "position:quantity", "securityType", "shortId", "startDate", "strike", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime"

**CapFloor**: "cap", "currency", "dayCount", "externalIdBundle", "frequency", "ibor", "maturityDate", "name", "notional", "payer", "position:quantity", "securityType", "startDate", "strike", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "underlyingId"

**Cash**: "amount", "currency", "dayCount", "externalIdBundle", "maturity", "name", "position:quantity", "rate", "regionId", "securityType", "start", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime"

**CommodityFuture**: "commodityType", "currency", "expiry", "externalIdBundle", "name", "position:quantity", "securityType", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "unitAmount", "unitName", "unitNumber"

**CorporateBond**: "announcementDate", "businessDayConvention", "couponFrequency", "couponRate", "couponType", "currency", "dayCount", "externalIdBundle", "firstCouponDate", "guaranteeType", "interestAccrualDate", "issuancePrice", "issuerDomicile", "issuerName", "issuerType", "lastTradeDate", "market", "minimumAmount", "minimumIncrement", "name", "parAmount", "position:quantity", "redemptionValue", "securityType", "settlementDate", "totalAmountIssued", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "yieldConvention"

**EnergyFuture**: "commodityType", "currency", "expiry", "externalIdBundle", "name", "position:quantity", "securityType", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "underlyingId", "unitAmount", "unitName", "unitNumber"

**EquityBarrierOption**: "barrierDirection", "barrierLevel", "barrierType", "currency", "exchange", "exerciseType", "expiry", "externalIdBundle", "monitoringType", "name", "optionType", "pointValue", "position:quantity", "samplingFrequency", "securityType", "strike", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "underlyingId"

**Equity**: "companyName", "currency", "exchange", "exchangeCode", "externalIdBundle", "name", "position:quantity", "securityType", "shortName", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime"

**EquityFuture**: "currency", "expiry", "externalIdBundle", "name", "position:quantity", "securityType", "settlementDate", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "underlyingId", "unitAmount"

**EquityIndexDividendFuture**: "currency", "expiry", "externalIdBundle", "name", "position:quantity", "securityType", "settlementDate", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "underlyingId", "unitAmount"

**EquityIndexDividendFutureOption**: "currency", "exchange", "exerciseType", "expiry", "externalIdBundle", "margined", "name", "optionType", "pointValue", "position:quantity", "securityType", "strike", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "underlyingId"

**EquityIndexOption**: "currency", "exchange", "exerciseType", "expiry", "externalIdBundle", "name", "optionType", "pointValue", "position:quantity", "securityType", "strike", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "underlyingId"

**EquityOption**: "currency", "exchange", "exerciseType", "expiry", "externalIdBundle", "name", "optionType", "pointValue", "position:quantity", "securityType", "strike", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "underlyingId"

**EquityVarianceSwap**: "annualizationFactor", "currency", "externalIdBundle", "firstObservationDate", "lastObservationDate", "name", "notional", "observationFrequency", "parameterizedAsVariance", "position:quantity", "regionId", "securityType", "settlementDate", "spotUnderlyingId", "strike", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime"

**ForwardSwap**: "counterparty", "effectiveDate", "externalIdBundle", "forwardStartDate", "maturityDate", "name", "payLeg", "payLeg:annualizationFactor", "payLeg:businessDayConvention", "payLeg:dayCount", "payLeg:eom", "payLeg:floatingRateType", "payLeg:floatingReferenceRateId", "payLeg:frequency", "payLeg:gearing", "payLeg:initialFloatingRate", "payLeg:monitoringFrequency", "payLeg:notional", "payLeg:offsetFixing", "payLeg:rate", "payLeg:regionId", "payLeg:settlementDays", "payLeg:spread", "payLeg:strike", "payLeg:type", "payLeg:underlyingId", "position:quantity", "receiveLeg", "receiveLeg:annualizationFactor", "receiveLeg:businessDayConvention", "receiveLeg:dayCount", "receiveLeg:eom", "receiveLeg:floatingRateType", "receiveLeg:floatingReferenceRateId", "receiveLeg:frequency", "receiveLeg:gearing", "receiveLeg:initialFloatingRate", "receiveLeg:monitoringFrequency", "receiveLeg:notional", "receiveLeg:offsetFixing", "receiveLeg:rate", "receiveLeg:regionId", "receiveLeg:settlementDays", "receiveLeg:spread", "receiveLeg:strike", "receiveLeg:type", "receiveLeg:underlyingId", "securityType", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradeDate"

**FRA**: "amount", "currency", "endDate", "externalIdBundle", "fixingDate", "name", "position:quantity", "rate", "regionId", "securityType", "startDate", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "underlyingId"

**Future**: "currency", "expiry", "externalIdBundle", "name", "position:quantity", "securityType", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "unitAmount"

**FXBarrierOption**: "barrierDirection", "barrierLevel", "barrierType", "callAmount", "callCurrency", "expiry", "externalIdBundle", "longShort", "monitoringType", "name", "position:quantity", "putAmount", "putCurrency", "samplingFrequency", "securityType", "settlementDate", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime"

**FXForward**: "externalIdBundle", "forwardDate", "name", "payAmount", "payCurrency", "position:quantity", "receiveAmount", "receiveCurrency", "regionId", "securityType", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime"

**FXFuture**: "currency", "denominator", "expiry", "externalIdBundle", "multiplicationFactor", "name", "numerator", "position:quantity", "securityType", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "unitAmount"

**FXOption**: "callAmount", "callCurrency", "exerciseType", "expiry", "externalIdBundle", "longShort", "name", "position:quantity", "putAmount", "putCurrency", "securityType", "settlementDate", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime"

**GovernmentBond**: "announcementDate", "businessDayConvention", "couponFrequency", "couponRate", "couponType", "currency", "dayCount", "externalIdBundle", "firstCouponDate", "guaranteeType", "interestAccrualDate", "issuancePrice", "issuerDomicile", "issuerName", "issuerType", "lastTradeDate", "market", "minimumAmount", "minimumIncrement", "name", "parAmount", "position:quantity", "redemptionValue", "securityType", "settlementDate", "totalAmountIssued", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "yieldConvention"

**IndexFuture**: "currency", "expiry", "externalIdBundle", "name", "position:quantity", "securityType", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "underlyingId", "unitAmount"

**InterestRateFuture**: "currency", "expiry", "externalIdBundle", "name", "position:quantity", "securityType", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "underlyingId", "unitAmount"

**IRFutureOption**: "currency", "exchange", "exerciseType", "expiry", "externalIdBundle", "margined", "name", "optionType", "pointValue", "position:quantity", "securityType", "strike", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "underlyingId"

**MetalFuture**: "commodityType", "currency", "expiry", "externalIdBundle", "name", "position:quantity", "securityType", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "underlyingId", "unitAmount", "unitName", "unitNumber"

**MunicipalBond**: "announcementDate", "businessDayConvention", "couponFrequency", "couponRate", "couponType", "currency", "dayCount", "externalIdBundle", "firstCouponDate", "guaranteeType", "interestAccrualDate", "issuancePrice", "issuerDomicile", "issuerName", "issuerType", "lastTradeDate", "market", "minimumAmount", "minimumIncrement", "name", "parAmount", "position:quantity", "redemptionValue", "securityType", "settlementDate", "totalAmountIssued", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "yieldConvention"

**NonDeliverableFXDigitalOption**: "callAmount", "callCurrency", "deliverInCallCurrency", "expiry", "externalIdBundle", "longShort", "name", "paymentCurrency", "position:quantity", "putAmount", "putCurrency", "securityType", "settlementDate", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime"

**NonDeliverableFXForward**: "deliverInReceiveCurrency", "externalIdBundle", "forwardDate", "name", "payAmount", "payCurrency", "position:quantity", "receiveAmount", "receiveCurrency", "regionId", "securityType", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime"

**NonDeliverableFXOption**: "callAmount", "callCurrency", "deliveryInCallCurrency", "exerciseType", "expiry", "externalIdBundle", "longShort", "name", "position:quantity", "putAmount", "putCurrency", "securityType", "settlementDate", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime"

**StockFuture**: "currency", "expiry", "externalIdBundle", "name", "position:quantity", "securityType", "settlementExchange", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradingExchange", "underlyingId", "unitAmount"

**Swap**: "counterparty", "effectiveDate", "externalIdBundle", "maturityDate", "name", "payLeg", "payLeg:annualizationFactor", "payLeg:businessDayConvention", "payLeg:dayCount", "payLeg:eom", "payLeg:floatingRateType", "payLeg:floatingReferenceRateId", "payLeg:frequency", "payLeg:gearing", "payLeg:initialFloatingRate", "payLeg:monitoringFrequency", "payLeg:notional", "payLeg:offsetFixing", "payLeg:rate", "payLeg:regionId", "payLeg:settlementDays", "payLeg:spread", "payLeg:strike", "payLeg:type", "payLeg:underlyingId", "position:quantity", "receiveLeg", "receiveLeg:annualizationFactor", "receiveLeg:businessDayConvention", "receiveLeg:dayCount", "receiveLeg:eom", "receiveLeg:floatingRateType", "receiveLeg:floatingReferenceRateId", "receiveLeg:frequency", "receiveLeg:gearing", "receiveLeg:initialFloatingRate", "receiveLeg:monitoringFrequency", "receiveLeg:notional", "receiveLeg:offsetFixing", "receiveLeg:rate", "receiveLeg:regionId", "receiveLeg:settlementDays", "receiveLeg:spread", "receiveLeg:strike", "receiveLeg:type", "receiveLeg:underlyingId", "securityType", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "tradeDate"

**Swaption**: "cashSettled", "currency", "exerciseType", "expiry", "externalIdBundle", "longShort", "name", "notional", "payer", "position:quantity", "securityType", "settlementDate", "trade:counterpartyExternalId", "trade:deal", "trade:premium", "trade:premiumCurrency", "trade:premiumDate", "trade:premiumTime", "trade:quantity", "trade:tradeDate", "trade:tradeTime", "underlyingId"
