title: Non-deliverable FX Digital Option Value Requirements
shortcut: DOC:Non-deliverable FX Digital Option Value Requirements
---
This page lists the value requirements that can be requested at the position level for this asset class. The properties listed may not be produced by all functions. Where multiple functions are available for a given value requirement (for example the alternative calculation methods available in the analytics library) each might only produce a subset of the properties given here.



+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Value Requirement Name          | Properties                                                                                                                                                                                                                                                                                                                                                                                                                                   |
+=================================+==============================================================================================================================================================================================================================================================================================================================================================================================================================================+
|  `FXCurrencyExposure`_          |  `FXCurrencyExposure.CalculationMethod`_ , `FXCurrencyExposure.CallCurve`_ , `FXCurrencyExposure.CallCurveCalculationConfig`_ , `FXCurrencyExposure.LeftXExtrapolator`_ , `FXCurrencyExposure.PutCurve`_ , `FXCurrencyExposure.PutCurveCalculationConfig`_ , `FXCurrencyExposure.RightXExtrapolator`_ , `FXCurrencyExposure.Surface`_ , `FXCurrencyExposure.XInterpolator`_                                                                  |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `FXCurveSensitivities`_        |  `FXCurveSensitivities.CalculationMethod`_ , `FXCurveSensitivities.CallCurve`_ , `FXCurveSensitivities.CallCurveCalculationConfig`_ , `FXCurveSensitivities.LeftXExtrapolator`_ , `FXCurveSensitivities.PutCurve`_ , `FXCurveSensitivities.PutCurveCalculationConfig`_ , `FXCurveSensitivities.RightXExtrapolator`_ , `FXCurveSensitivities.Surface`_ , `FXCurveSensitivities.XInterpolator`_                                                |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `FXPresentValue`_              |  `FXPresentValue.CalculationMethod`_ , `FXPresentValue.CallCurve`_ , `FXPresentValue.CallCurveCalculationConfig`_ , `FXPresentValue.LeftXExtrapolator`_ , `FXPresentValue.PutCurve`_ , `FXPresentValue.PutCurveCalculationConfig`_ , `FXPresentValue.RightXExtrapolator`_ , `FXPresentValue.Surface`_ , `FXPresentValue.XInterpolator`_                                                                                                      |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastMarketCap`_               |                                                                                                                                                                                                                                                                                                                                                                                                                                              |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastPrice`_                   |                                                                                                                                                                                                                                                                                                                                                                                                                                              |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastRawBeta`_                 |                                                                                                                                                                                                                                                                                                                                                                                                                                              |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastVolume`_                  |                                                                                                                                                                                                                                                                                                                                                                                                                                              |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PresentValue`_                |  `PresentValue.CalculationMethod`_ , `PresentValue.CallCurve`_ , `PresentValue.CallCurveCalculationConfig`_ , `PresentValue.Currency`_ , `PresentValue.LeftXExtrapolator`_ , `PresentValue.PutCurve`_ , `PresentValue.PutCurveCalculationConfig`_ , `PresentValue.RightXExtrapolator`_ , `PresentValue.Surface`_ , `PresentValue.XInterpolator`_                                                                                             |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `SecurityImpliedVolatility`_   |  `SecurityImpliedVolatility.CalculationMethod`_ , `SecurityImpliedVolatility.CallCurve`_ , `SecurityImpliedVolatility.CallCurveCalculationConfig`_ , `SecurityImpliedVolatility.LeftXExtrapolator`_ , `SecurityImpliedVolatility.PutCurve`_ , `SecurityImpliedVolatility.PutCurveCalculationConfig`_ , `SecurityImpliedVolatility.RightXExtrapolator`_ , `SecurityImpliedVolatility.Surface`_ , `SecurityImpliedVolatility.XInterpolator`_   |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Value`_                       |  `Value.CalculationMethod`_ , `Value.CallCurve`_ , `Value.CallCurveCalculationConfig`_ , `Value.Currency`_ , `Value.LeftXExtrapolator`_ , `Value.PutCurve`_ , `Value.PutCurveCalculationConfig`_ , `Value.RightXExtrapolator`_ , `Value.Surface`_ , `Value.XInterpolator`_                                                                                                                                                                   |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `ValueGamma`_                  |  `ValueGamma.CalculationMethod`_ , `ValueGamma.CallCurve`_ , `ValueGamma.CallCurveCalculationConfig`_ , `ValueGamma.Currency`_ , `ValueGamma.LeftXExtrapolator`_ , `ValueGamma.PutCurve`_ , `ValueGamma.PutCurveCalculationConfig`_ , `ValueGamma.RightXExtrapolator`_ , `ValueGamma.Surface`_ , `ValueGamma.XInterpolator`_                                                                                                                 |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `ValueTheta`_                  |  `ValueTheta.CalculationMethod`_ , `ValueTheta.CallCurve`_ , `ValueTheta.CallCurveCalculationConfig`_ , `ValueTheta.Currency`_ , `ValueTheta.LeftXExtrapolator`_ , `ValueTheta.PutCurve`_ , `ValueTheta.PutCurveCalculationConfig`_ , `ValueTheta.RightXExtrapolator`_ , `ValueTheta.Surface`_ , `ValueTheta.ThetaCalculationMethod`_ , `ValueTheta.XInterpolator`_                                                                          |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `ValueVanna`_                  |  `ValueVanna.CalculationMethod`_ , `ValueVanna.CallCurve`_ , `ValueVanna.CallCurveCalculationConfig`_ , `ValueVanna.Currency`_ , `ValueVanna.LeftXExtrapolator`_ , `ValueVanna.PutCurve`_ , `ValueVanna.PutCurveCalculationConfig`_ , `ValueVanna.RightXExtrapolator`_ , `ValueVanna.Surface`_ , `ValueVanna.XInterpolator`_                                                                                                                 |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `ValueVega`_                   |  `ValueVega.CalculationMethod`_ , `ValueVega.CallCurve`_ , `ValueVega.CallCurveCalculationConfig`_ , `ValueVega.Currency`_ , `ValueVega.LeftXExtrapolator`_ , `ValueVega.PutCurve`_ , `ValueVega.PutCurveCalculationConfig`_ , `ValueVega.RightXExtrapolator`_ , `ValueVega.Surface`_ , `ValueVega.XInterpolator`_                                                                                                                           |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `ValueVomma`_                  |  `ValueVomma.CalculationMethod`_ , `ValueVomma.CallCurve`_ , `ValueVomma.CallCurveCalculationConfig`_ , `ValueVomma.Currency`_ , `ValueVomma.LeftXExtrapolator`_ , `ValueVomma.PutCurve`_ , `ValueVomma.PutCurveCalculationConfig`_ , `ValueVomma.RightXExtrapolator`_ , `ValueVomma.Surface`_ , `ValueVomma.XInterpolator`_                                                                                                                 |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `VegaMatrix`_                  |  `VegaMatrix.CalculationMethod`_ , `VegaMatrix.CallCurve`_ , `VegaMatrix.CallCurveCalculationConfig`_ , `VegaMatrix.Currency`_ , `VegaMatrix.LeftXExtrapolator`_ , `VegaMatrix.PutCurve`_ , `VegaMatrix.PutCurveCalculationConfig`_ , `VegaMatrix.RightXExtrapolator`_ , `VegaMatrix.Surface`_ , `VegaMatrix.XInterpolator`_                                                                                                                 |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `VegaQuoteMatrix`_             |  `VegaQuoteMatrix.CalculationMethod`_ , `VegaQuoteMatrix.CallCurve`_ , `VegaQuoteMatrix.CallCurveCalculationConfig`_ , `VegaQuoteMatrix.Currency`_ , `VegaQuoteMatrix.InstrumentType`_ , `VegaQuoteMatrix.LeftXExtrapolator`_ , `VegaQuoteMatrix.PutCurve`_ , `VegaQuoteMatrix.PutCurveCalculationConfig`_ , `VegaQuoteMatrix.RightXExtrapolator`_ , `VegaQuoteMatrix.Surface`_ , `VegaQuoteMatrix.XInterpolator`_                           |
+---------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



FXCurrencyExposure

....................
FX Currency Exposure
....................


The currency exposure of a FX instrument



+----------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                   | Description                                                                                                                                                                                     |
+============================================================================+=================================================================================================================================================================================================+
|  FXCurrencyExposure.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+----------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurrencyExposure.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+----------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurrencyExposure.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+----------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurrencyExposure.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+----------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurrencyExposure.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+----------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurrencyExposure.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+----------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurrencyExposure.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+----------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurrencyExposure.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+----------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurrencyExposure.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+----------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



FXCurveSensitivities

......................
FX Curve Sensitivities
......................


The sensitivities of the present value of a FX instrument to the curves to which it is sensitive.



+------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                     | Description                                                                                                                                                                                     |
+==============================================================================+=================================================================================================================================================================================================+
|  FXCurveSensitivities.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurveSensitivities.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurveSensitivities.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurveSensitivities.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurveSensitivities.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurveSensitivities.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurveSensitivities.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurveSensitivities.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXCurveSensitivities.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



FXPresentValue

................
FX Present Value
................


The present value in both currencies of a FX instrument.



+------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                               | Description                                                                                                                                                                                     |
+========================================================================+=================================================================================================================================================================================================+
|  FXPresentValue.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXPresentValue.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXPresentValue.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXPresentValue.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXPresentValue.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXPresentValue.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXPresentValue.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXPresentValue.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  FXPresentValue.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



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



+----------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                             | Description                                                                                                                                                                                     |
+======================================================================+=================================================================================================================================================================================================+
|  PresentValue.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+----------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+----------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+----------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.Currency Currency                                      | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_.                                                                                         |
+----------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+----------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+----------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+----------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+----------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+----------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+----------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



SecurityImpliedVolatility

...........................
Security Implied Volatility
...........................


The implied volatility of a security.



+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                          | Description                                                                                                                                                                                     |
+===================================================================================+=================================================================================================================================================================================================+
|  SecurityImpliedVolatility.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  SecurityImpliedVolatility.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  SecurityImpliedVolatility.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  SecurityImpliedVolatility.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  SecurityImpliedVolatility.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  SecurityImpliedVolatility.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  SecurityImpliedVolatility.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  SecurityImpliedVolatility.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  SecurityImpliedVolatility.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+-----------------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



Value

.....
Value
.....


Generic valuation of a security, for example it might be FAIR*VALUE or PRESENT*VALUE depending on the asset class.



+---------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                      | Description                                                                                                                                                                                     |
+===============================================================+=================================================================================================================================================================================================+
|  Value.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+---------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+---------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+---------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.Currency Currency                                      | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_.                                                                                         |
+---------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+---------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+---------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+---------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+---------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+---------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+---------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



ValueGamma

..........
ValueGamma
..........


The amount by which the value of a portfolio would change due to gamma.



+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                           | Description                                                                                                                                                                                     |
+====================================================================+=================================================================================================================================================================================================+
|  ValueGamma.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueGamma.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueGamma.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueGamma.Currency Currency                                      | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_.                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueGamma.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueGamma.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueGamma.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueGamma.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueGamma.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueGamma.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



ValueTheta

..........
ValueTheta
..........


The amount by which the value of a portfolio would change due to theta.



+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                           | Description                                                                                                                                                                                     |
+====================================================================+=================================================================================================================================================================================================+
|  ValueTheta.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueTheta.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueTheta.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueTheta.Currency Currency                                      | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_.                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueTheta.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueTheta.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueTheta.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueTheta.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueTheta.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueTheta.ThetaCalculationMethod ThetaCalculationMethod          | The symbolic name of the theta calculation method used. Example value: _OptionTheta_.                                                                                                           |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueTheta.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



ValueVanna

..........
ValueVanna
..........


The amount by which the value of a portfolio would change due to vanna.



+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                           | Description                                                                                                                                                                                     |
+====================================================================+=================================================================================================================================================================================================+
|  ValueVanna.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVanna.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVanna.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVanna.Currency Currency                                      | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_.                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVanna.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVanna.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVanna.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVanna.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVanna.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVanna.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



ValueVega

.........
ValueVega
.........


The amount by which the value of a portfolio would change due to vega.



+-------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                          | Description                                                                                                                                                                                     |
+===================================================================+=================================================================================================================================================================================================+
|  ValueVega.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+-------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVega.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+-------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVega.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+-------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVega.Currency Currency                                      | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_.                                                                                         |
+-------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVega.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+-------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVega.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+-------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVega.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+-------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVega.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+-------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVega.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+-------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVega.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+-------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



ValueVomma

..........
ValueVomma
..........


The amount by which the value of a portfolio would change due to vomma.



+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                           | Description                                                                                                                                                                                     |
+====================================================================+=================================================================================================================================================================================================+
|  ValueVomma.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVomma.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVomma.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVomma.Currency Currency                                      | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_.                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVomma.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVomma.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVomma.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVomma.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVomma.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  ValueVomma.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



VegaMatrix

...........
Vega Matrix
...........


The bucketed vega of a security for a (expiry, delta) volatility surface.



+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                           | Description                                                                                                                                                                                     |
+====================================================================+=================================================================================================================================================================================================+
|  VegaMatrix.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaMatrix.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaMatrix.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaMatrix.Currency Currency                                      | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_.                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaMatrix.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaMatrix.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaMatrix.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaMatrix.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaMatrix.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaMatrix.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+--------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



VegaQuoteMatrix

.................
Vega Quote Matrix
.................


The bucketed vega of a security to the market data volatility surface.



+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                | Description                                                                                                                                                                                     |
+=========================================================================+=================================================================================================================================================================================================+
|  VegaQuoteMatrix.CalculationMethod CalculationMethod                    | The symbolic name of the general calculation method used. Example value: _BlackMethod_.                                                                                                         |
+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.CallCurve CallCurve                                    | The symbolic name of the call curve used. Example value: _Discounting_.                                                                                                                         |
+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.CallCurveCalculationConfig CallCurveCalculationConfig  | The symbolic name of the configuration used for the call curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_. |
+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.Currency Currency                                      | The currency of the value, specified as a 3-digit ISO code. Example values: _JPY_, _CHF_, _EUR_, _USD_.                                                                                         |
+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.InstrumentType InstrumentType                          | The type of instrument used to construct the surface(s) used. Example value: _FX_VANILLA_OPTION_.                                                                                               |
+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.LeftXExtrapolator LeftXExtrapolator                    | The symbolic name of the left X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                         |
+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.PutCurve PutCurve                                      | The symbolic name of the put curve used. Example value: _Discounting_.                                                                                                                          |
+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.PutCurveCalculationConfig PutCurveCalculationConfig    | The symbolic name of the configuration used for the put curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveCHFConfig_, _DefaultTwoCurveJPYConfig_, _DefaultTwoCurveEURConfig_.  |
+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.RightXExtrapolator RightXExtrapolator                  | The symbolic name of the right X extrapolator used. Example value: _LinearExtrapolator_.                                                                                                        |
+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.Surface Surface                                        | The symbolic name of a surface used. Example value: _TULLETT_.                                                                                                                                  |
+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteMatrix.XInterpolator XInterpolator                            | The symbolic name of the X interpolator used. Example value: _DoubleQuadratic_.                                                                                                                 |
+-------------------------------------------------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



