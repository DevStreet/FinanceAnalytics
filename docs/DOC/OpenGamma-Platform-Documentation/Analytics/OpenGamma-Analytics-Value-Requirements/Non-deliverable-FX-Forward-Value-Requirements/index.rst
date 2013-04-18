title: Non-deliverable FX Forward Value Requirements
shortcut: DOC:Non-deliverable FX Forward Value Requirements
---
This page lists the value requirements that can be requested at the position level for this asset class. The properties listed may not be produced by all functions. Where multiple functions are available for a given value requirement (for example the alternative calculation methods available in the analytics library) each might only produce a subset of the properties given here.



+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Value Requirement Name          | Properties                                                                                                                                                                             |
+=================================+========================================================================================================================================================================================+
|  `FXCurrencyExposure`_          |  `FXCurrencyExposure.PayCurve`_ , `FXCurrencyExposure.PayCurveCalculationConfig`_ , `FXCurrencyExposure.ReceiveCurve`_ , `FXCurrencyExposure.ReceiveCurveCalculationConfig`_           |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `FXCurveSensitivities`_        |  `FXCurveSensitivities.PayCurve`_ , `FXCurveSensitivities.PayCurveCalculationConfig`_ , `FXCurveSensitivities.ReceiveCurve`_ , `FXCurveSensitivities.ReceiveCurveCalculationConfig`_   |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `FXPresentValue`_              |  `FXPresentValue.PayCurve`_ , `FXPresentValue.PayCurveCalculationConfig`_ , `FXPresentValue.ReceiveCurve`_ , `FXPresentValue.ReceiveCurveCalculationConfig`_                           |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastMarketCap`_               |                                                                                                                                                                                        |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastPrice`_                   |                                                                                                                                                                                        |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastRawBeta`_                 |                                                                                                                                                                                        |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastVolume`_                  |                                                                                                                                                                                        |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PayFixedCash-Flows`_          |                                                                                                                                                                                        |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `ReceiveFixedCash-Flows`_      |                                                                                                                                                                                        |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `ReceiveFloatingCash-Flows`_   |                                                                                                                                                                                        |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



FXCurrencyExposure

....................
FX Currency Exposure
....................


The currency exposure of a FX instrument



+----------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                         | Description                                                                                                                                |
+==================================================================================+============================================================================================================================================+
|  FXCurrencyExposure.PayCurve PayCurve                                            | The symbolic name of a curve used. Example value: _Discounting_.                                                                           |
+----------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurrencyExposure.PayCurveCalculationConfig PayCurveCalculationConfig          | The symbolic name of the configuration used for the pay curve. Example values: _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveEURConfig_.     |
+----------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurrencyExposure.ReceiveCurve ReceiveCurve                                    | The symbolic name of a curve used. Example value: _Discounting_.                                                                           |
+----------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurrencyExposure.ReceiveCurveCalculationConfig ReceiveCurveCalculationConfig  | The symbolic name of the configuration used for the receive curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_. |
+----------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+



FXCurveSensitivities

......................
FX Curve Sensitivities
......................


The sensitivities of the present value of a FX instrument to the curves to which it is sensitive.



+------------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                           | Description                                                                                                                                |
+====================================================================================+============================================================================================================================================+
|  FXCurveSensitivities.PayCurve PayCurve                                            | The symbolic name of a curve used. Example value: _Discounting_.                                                                           |
+------------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurveSensitivities.PayCurveCalculationConfig PayCurveCalculationConfig          | The symbolic name of the configuration used for the pay curve. Example values: _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveEURConfig_.     |
+------------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurveSensitivities.ReceiveCurve ReceiveCurve                                    | The symbolic name of a curve used. Example value: _Discounting_.                                                                           |
+------------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurveSensitivities.ReceiveCurveCalculationConfig ReceiveCurveCalculationConfig  | The symbolic name of the configuration used for the receive curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_. |
+------------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+



FXPresentValue

................
FX Present Value
................


The present value in both currencies of a FX instrument.



+------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                     | Description                                                                                                                                |
+==============================================================================+============================================================================================================================================+
|  FXPresentValue.PayCurve PayCurve                                            | The symbolic name of a curve used. Example value: _Discounting_.                                                                           |
+------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  FXPresentValue.PayCurveCalculationConfig PayCurveCalculationConfig          | The symbolic name of the configuration used for the pay curve. Example values: _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveEURConfig_.     |
+------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  FXPresentValue.ReceiveCurve ReceiveCurve                                    | The symbolic name of a curve used. Example value: _Discounting_.                                                                           |
+------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+
|  FXPresentValue.ReceiveCurveCalculationConfig ReceiveCurveCalculationConfig  | The symbolic name of the configuration used for the receive curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_. |
+------------------------------------------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------+



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

PayFixedCash-Flows

....................
Pay Fixed Cash-Flows
....................


The dates and payment amounts to be paid of the cash-flows of a security or portfolio

This value requirement has no additional properties.

ReceiveFixedCash-Flows

........................
Receive Fixed Cash-Flows
........................


The dates and payment amounts to be received of the cash-flows of a security or portfolio

This value requirement has no additional properties.

ReceiveFloatingCash-Flows

...........................
Receive Floating Cash-Flows
...........................


The payment dates, amounts and indices of the receive cash-flows of a security or portfolio

This value requirement has no additional properties.

