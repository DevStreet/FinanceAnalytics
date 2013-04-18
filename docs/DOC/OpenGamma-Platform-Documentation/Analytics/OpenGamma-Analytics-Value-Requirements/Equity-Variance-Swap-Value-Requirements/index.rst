title: Equity Variance Swap Value Requirements
shortcut: DOC:Equity Variance Swap Value Requirements
---
This page lists the value requirements that can be requested at the position level for this asset class. The properties listed may not be produced by all functions. Where multiple functions are available for a given value requirement (for example the alternative calculation methods available in the analytics library) each might only produce a subset of the properties given here.



+-----------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Value Requirement Name            | Properties                                                                                                                                                                                                                                                                           |
+===================================+======================================================================================================================================================================================================================================================================================+
|  `Forward`_                       |  `Forward.Currency`_ , `Forward.Curve`_ , `Forward.CurveCalculationConfig`_ , `Forward.ForwardCalculationMethod`_                                                                                                                                                                    |
+-----------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `ForwardPrice`_                  |  `ForwardPrice.Currency`_ , `ForwardPrice.Curve`_ , `ForwardPrice.CurveCalculationConfig`_ , `ForwardPrice.ForwardCalculationMethod`_                                                                                                                                                |
+-----------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastMarketCap`_                 |                                                                                                                                                                                                                                                                                      |
+-----------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastPrice`_                     |                                                                                                                                                                                                                                                                                      |
+-----------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastRawBeta`_                   |                                                                                                                                                                                                                                                                                      |
+-----------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastVolume`_                    |                                                                                                                                                                                                                                                                                      |
+-----------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PresentValue`_                  |  `PresentValue.CalculationMethod`_ , `PresentValue.Currency`_ , `PresentValue.Curve`_ , `PresentValue.CurveCalculationConfig`_ , `PresentValue.Surface`_                                                                                                                             |
+-----------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Value`_                         |  `Value.CalculationMethod`_ , `Value.Currency`_ , `Value.Curve`_ , `Value.CurveCalculationConfig`_ , `Value.Surface`_                                                                                                                                                                |
+-----------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `VegaQuoteMatrix`_               |  `VegaQuoteMatrix.CalculationMethod`_ , `VegaQuoteMatrix.Currency`_ , `VegaQuoteMatrix.Curve`_ , `VegaQuoteMatrix.CurveCalculationConfig`_ , `VegaQuoteMatrix.InstrumentType`_ , `VegaQuoteMatrix.Surface`_                                                                          |
+-----------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `YieldCurveNodeSensitivities`_   |  `YieldCurveNodeSensitivities.CalculationMethod`_ , `YieldCurveNodeSensitivities.Currency`_ , `YieldCurveNodeSensitivities.Curve`_ , `YieldCurveNodeSensitivities.CurveCalculationConfig`_ , `YieldCurveNodeSensitivities.CurveCurrency`_ , `YieldCurveNodeSensitivities.Surface`_   |
+-----------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



Forward

.......
Forward
.......


The forward value of a security



+-------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                    | Description                                                                                                                                |
+=============================================================+============================================================================================================================================+
|  Forward.Currency Currency                                  | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_, _GBP_.                             |
+-------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  Forward.Curve Curve                                        | The symbolic name of the curve used. Example value: _Discounting_.                                                                         |
+-------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  Forward.CurveCalculationConfig CurveCalculationConfig      | The symbolic name of the configuration used for the curve. Example value: _DefaultTwoCurveUSDConfig_.                                      |
+-------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  Forward.ForwardCalculationMethod ForwardCalculationMethod  | The symbolic name of the method used to calculate the forward value of an equity spot rate. Example value: _ForwardFromSpotAndYieldCurve_. |
+-------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+



ForwardPrice

.............
Forward Price
.............


The forward price of a security



+------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                         | Description                                                                                                                                |
+==================================================================+============================================================================================================================================+
|  ForwardPrice.Currency Currency                                  | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_, _GBP_.                             |
+------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  ForwardPrice.Curve Curve                                        | The symbolic name of the curve used. Example value: _Discounting_.                                                                         |
+------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  ForwardPrice.CurveCalculationConfig CurveCalculationConfig      | The symbolic name of the configuration used for the curve. Example value: _DefaultTwoCurveUSDConfig_.                                      |
+------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  ForwardPrice.ForwardCalculationMethod ForwardCalculationMethod  | The symbolic name of the method used to calculate the forward value of an equity spot rate. Example value: _ForwardFromSpotAndYieldCurve_. |
+------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+



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

PresentValue

.............
Present Value
.............


The present value of a cash-flow based fixed-income instrument.



+--------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
| Property                                                     | Description                                                                                                    |
+==============================================================+================================================================================================================+
|  PresentValue.CalculationMethod CalculationMethod            | The symbolic name of the general calculation method used. Example value: _StaticReplication_.                  |
+--------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  PresentValue.Currency Currency                              | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_, _GBP_. |
+--------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  PresentValue.Curve Curve                                    | The symbolic name of the curve used. Example value: _Discounting_.                                             |
+--------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  PresentValue.CurveCalculationConfig CurveCalculationConfig  | The symbolic name of the configuration used for the curve. Example value: _DefaultTwoCurveUSDConfig_.          |
+--------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  PresentValue.Surface Surface                                | The symbolic name of a surface used. Example value: _BBG_.                                                     |
+--------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+



Value

.....
Value
.....


Generic valuation of a security, for example it might be FAIR*VALUE or PRESENT*VALUE depending on the asset class.



+-------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
| Property                                              | Description                                                                                                    |
+=======================================================+================================================================================================================+
|  Value.CalculationMethod CalculationMethod            | The symbolic name of the general calculation method used. Example value: _StaticReplication_.                  |
+-------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  Value.Currency Currency                              | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_, _GBP_. |
+-------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  Value.Curve Curve                                    | The symbolic name of the curve used. Example value: _Discounting_.                                             |
+-------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  Value.CurveCalculationConfig CurveCalculationConfig  | The symbolic name of the configuration used for the curve. Example value: _DefaultTwoCurveUSDConfig_.          |
+-------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  Value.Surface Surface                                | The symbolic name of a surface used. Example value: _BBG_.                                                     |
+-------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+



VegaQuoteMatrix

.................
Vega Quote Matrix
.................


The bucketed vega of a security to the market data volatility surface.



+-----------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
| Property                                                        | Description                                                                                                    |
+=================================================================+================================================================================================================+
|  VegaQuoteMatrix.CalculationMethod CalculationMethod            | The symbolic name of the general calculation method used. Example value: _StaticReplication_.                  |
+-----------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.Currency Currency                              | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_, _GBP_. |
+-----------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.Curve Curve                                    | The symbolic name of the curve used. Example value: _Discounting_.                                             |
+-----------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.CurveCalculationConfig CurveCalculationConfig  | The symbolic name of the configuration used for the curve. Example value: _DefaultTwoCurveUSDConfig_.          |
+-----------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.InstrumentType InstrumentType                  | The type of instrument used to construct the surface(s) used. Example value: _EQUITY_OPTION_.                  |
+-----------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.Surface Surface                                | The symbolic name of a surface used. Example value: _BBG_.                                                     |
+-----------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------+



YieldCurveNodeSensitivities

..............................
Yield Curve Node Sensitivities
..............................


The sensitivities of a cash-flow based fixed-income instrument to each of the nodal points in a yield curve.



+-----------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                    | Description                                                                                                                                             |
+=============================================================================+=========================================================================================================================================================+
|  YieldCurveNodeSensitivities.CalculationMethod CalculationMethod            | The symbolic name of the general calculation method used. Example value: _StaticReplication_.                                                           |
+-----------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.Currency Currency                              | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_, _GBP_.                                          |
+-----------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.Curve Curve                                    | The symbolic name of the curve used. Example value: _Discounting_.                                                                                      |
+-----------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.CurveCalculationConfig CurveCalculationConfig  | The symbolic name of the configuration used for the curve. Example value: _DefaultTwoCurveUSDConfig_.                                                   |
+-----------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.CurveCurrency CurveCurrency                    | The currency of the curve used. This does not imply anything about the currency of the output value. Example values: _JPY_, _CHF_, _EUR_, _USD_, _GBP_. |
+-----------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.Surface Surface                                | The symbolic name of a surface used. Example value: _BBG_.                                                                                              |
+-----------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------------------------------------------------------------+



