title: Commodity Future Option Value Requirements
shortcut: DOC:Commodity Future Option Value Requirements
---
This page lists the value requirements that can be requested at the position level for this asset class. The properties listed may not be produced by all functions. Where multiple functions are available for a given value requirement (for example the alternative calculation methods available in the analytics library) each might only produce a subset of the properties given here.



+---------------------------+------------------------------------+
| Value Requirement Name    | Properties                         |
+===========================+====================================+
|  `LastMarketCap`_         |                                    |
+---------------------------+------------------------------------+
|  `LastPrice`_             |                                    |
+---------------------------+------------------------------------+
|  `LastRawBeta`_           |                                    |
+---------------------------+------------------------------------+
|  `LastVolume`_            |                                    |
+---------------------------+------------------------------------+
|  `SecurityMarketPrice`_   |  `SecurityMarketPrice.Currency`_   |
+---------------------------+------------------------------------+



LastMarketCap

...............
Last Market Cap
...............


The market cap as of the previous close

This value requirement has no additional properties.

LastPrice

..........
Last Price
..........


The market value as of the previous close

This value requirement has no additional properties.

LastRawBeta

.............
Last Raw Beta
.............


The beta of a stock as of the previous close

This value requirement has no additional properties.

LastVolume

...........
Last Volume
...........


The daily volume as of the previous close

This value requirement has no additional properties.

SecurityMarketPrice

.....................
Security Market Price
.....................


The market price of the security underlying a trade or position.



+-----------------------------------------+----------------------------------------------------------------------------------------------------------------+
| Property                                | Description                                                                                                    |
+=========================================+================================================================================================================+
|  SecurityMarketPrice.Currency Currency  | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_, _GBP_. |
+-----------------------------------------+----------------------------------------------------------------------------------------------------------------+



