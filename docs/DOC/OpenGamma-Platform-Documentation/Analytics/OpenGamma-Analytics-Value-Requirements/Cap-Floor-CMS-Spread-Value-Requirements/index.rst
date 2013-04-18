title: Cap-Floor CMS Spread Value Requirements
shortcut: DOC:Cap-Floor CMS Spread Value Requirements
---
This page lists the value requirements that can be requested at the position level for this asset class. The properties listed may not be produced by all functions. Where multiple functions are available for a given value requirement (for example the alternative calculation methods available in the analytics library) each might only produce a subset of the properties given here.



+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Value Requirement Name                     | Properties                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
+============================================+==============================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================+
|  `LastMarketCap`_                          |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastPrice`_                              |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastRawBeta`_                            |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `LastVolume`_                             |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PresentValue`_                           |  `PresentValue.CalculationMethod`_ , `PresentValue.Cube`_ , `PresentValue.Currency`_ , `PresentValue.CurveCalculationConfig`_ , `PresentValue.FittingMethod`_ , `PresentValue.SABRExtrapolationCutoffStrike`_ , `PresentValue.SABRTailThicknessParameter`_ , `PresentValue.VolatilityModel`_                                                                                                                                                                                                                 |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PresentValueCurveSensitivity`_           |  `PresentValueCurveSensitivity.CalculationMethod`_ , `PresentValueCurveSensitivity.Cube`_ , `PresentValueCurveSensitivity.Currency`_ , `PresentValueCurveSensitivity.CurveCalculationConfig`_ , `PresentValueCurveSensitivity.FittingMethod`_ , `PresentValueCurveSensitivity.SABRExtrapolationCutoffStrike`_ , `PresentValueCurveSensitivity.SABRTailThicknessParameter`_ , `PresentValueCurveSensitivity.VolatilityModel`_                                                                                 |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PresentValueSABRAlphaNodeSensitivity`_   |  `PresentValueSABRAlphaNodeSensitivity.CalculationMethod`_ , `PresentValueSABRAlphaNodeSensitivity.Cube`_ , `PresentValueSABRAlphaNodeSensitivity.Currency`_ , `PresentValueSABRAlphaNodeSensitivity.CurveCalculationConfig`_ , `PresentValueSABRAlphaNodeSensitivity.FittingMethod`_ , `PresentValueSABRAlphaNodeSensitivity.SABRExtrapolationCutoffStrike`_ , `PresentValueSABRAlphaNodeSensitivity.SABRTailThicknessParameter`_ , `PresentValueSABRAlphaNodeSensitivity.VolatilityModel`_                 |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PresentValueSABRAlphaSensitivity`_       |  `PresentValueSABRAlphaSensitivity.CalculationMethod`_ , `PresentValueSABRAlphaSensitivity.Cube`_ , `PresentValueSABRAlphaSensitivity.Currency`_ , `PresentValueSABRAlphaSensitivity.CurveCalculationConfig`_ , `PresentValueSABRAlphaSensitivity.FittingMethod`_ , `PresentValueSABRAlphaSensitivity.SABRExtrapolationCutoffStrike`_ , `PresentValueSABRAlphaSensitivity.SABRTailThicknessParameter`_ , `PresentValueSABRAlphaSensitivity.VolatilityModel`_                                                 |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PresentValueSABRNuNodeSensitivity`_      |  `PresentValueSABRNuNodeSensitivity.CalculationMethod`_ , `PresentValueSABRNuNodeSensitivity.Cube`_ , `PresentValueSABRNuNodeSensitivity.Currency`_ , `PresentValueSABRNuNodeSensitivity.CurveCalculationConfig`_ , `PresentValueSABRNuNodeSensitivity.FittingMethod`_ , `PresentValueSABRNuNodeSensitivity.SABRExtrapolationCutoffStrike`_ , `PresentValueSABRNuNodeSensitivity.SABRTailThicknessParameter`_ , `PresentValueSABRNuNodeSensitivity.VolatilityModel`_                                         |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PresentValueSABRNuSensitivity`_          |  `PresentValueSABRNuSensitivity.CalculationMethod`_ , `PresentValueSABRNuSensitivity.Cube`_ , `PresentValueSABRNuSensitivity.Currency`_ , `PresentValueSABRNuSensitivity.CurveCalculationConfig`_ , `PresentValueSABRNuSensitivity.FittingMethod`_ , `PresentValueSABRNuSensitivity.SABRExtrapolationCutoffStrike`_ , `PresentValueSABRNuSensitivity.SABRTailThicknessParameter`_ , `PresentValueSABRNuSensitivity.VolatilityModel`_                                                                         |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PresentValueSABRRhoNodeSensitivity`_     |  `PresentValueSABRRhoNodeSensitivity.CalculationMethod`_ , `PresentValueSABRRhoNodeSensitivity.Cube`_ , `PresentValueSABRRhoNodeSensitivity.Currency`_ , `PresentValueSABRRhoNodeSensitivity.CurveCalculationConfig`_ , `PresentValueSABRRhoNodeSensitivity.FittingMethod`_ , `PresentValueSABRRhoNodeSensitivity.SABRExtrapolationCutoffStrike`_ , `PresentValueSABRRhoNodeSensitivity.SABRTailThicknessParameter`_ , `PresentValueSABRRhoNodeSensitivity.VolatilityModel`_                                 |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `PresentValueSABRRhoSensitivity`_         |  `PresentValueSABRRhoSensitivity.CalculationMethod`_ , `PresentValueSABRRhoSensitivity.Cube`_ , `PresentValueSABRRhoSensitivity.Currency`_ , `PresentValueSABRRhoSensitivity.CurveCalculationConfig`_ , `PresentValueSABRRhoSensitivity.FittingMethod`_ , `PresentValueSABRRhoSensitivity.SABRExtrapolationCutoffStrike`_ , `PresentValueSABRRhoSensitivity.SABRTailThicknessParameter`_ , `PresentValueSABRRhoSensitivity.VolatilityModel`_                                                                 |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Value`_                                  |  `Value.CalculationMethod`_ , `Value.Cube`_ , `Value.Currency`_ , `Value.CurveCalculationConfig`_ , `Value.FittingMethod`_ , `Value.SABRExtrapolationCutoffStrike`_ , `Value.SABRTailThicknessParameter`_ , `Value.VolatilityModel`_                                                                                                                                                                                                                                                                         |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `VegaQuoteCube`_                          |  `VegaQuoteCube.CalculationMethod`_ , `VegaQuoteCube.Cube`_ , `VegaQuoteCube.CubeInstrumentType`_ , `VegaQuoteCube.Currency`_ , `VegaQuoteCube.CurveCalculationConfig`_ , `VegaQuoteCube.FittingMethod`_ , `VegaQuoteCube.LeftXExtrapolator`_ , `VegaQuoteCube.LeftYExtrapolator`_ , `VegaQuoteCube.RightXExtrapolator`_ , `VegaQuoteCube.RightYExtrapolator`_ , `VegaQuoteCube.VolatilityModel`_ , `VegaQuoteCube.XInterpolator`_ , `VegaQuoteCube.YInterpolator`_                                          |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `YieldCurveNodeSensitivities`_            |  `YieldCurveNodeSensitivities.CalculationMethod`_ , `YieldCurveNodeSensitivities.Cube`_ , `YieldCurveNodeSensitivities.Currency`_ , `YieldCurveNodeSensitivities.Curve`_ , `YieldCurveNodeSensitivities.CurveCalculationConfig`_ , `YieldCurveNodeSensitivities.CurveCurrency`_ , `YieldCurveNodeSensitivities.FittingMethod`_ , `YieldCurveNodeSensitivities.SABRExtrapolationCutoffStrike`_ , `YieldCurveNodeSensitivities.SABRTailThicknessParameter`_ , `YieldCurveNodeSensitivities.VolatilityModel`_   |
+--------------------------------------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



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



+----------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                   | Description                                                                                                                                                    |
+============================================================================+================================================================================================================================================================+
|  PresentValue.CalculationMethod CalculationMethod                          | The symbolic name of the general calculation method used. Example values: _SABRRightExtrapolation_, _SABRNoExtrapolation_.                                     |
+----------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.Cube Cube                                                    | The symbolic name of the cube used. Example value: _BLOOMBERG_.                                                                                                |
+----------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.Currency Currency                                            | The currency of the value, specified as a 3-digit ISO code. Example values: _EUR_, _USD_, _GBP_.                                                               |
+----------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.CurveCalculationConfig CurveCalculationConfig                | The symbolic name of the configuration used for the curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveGBPConfig_, _DefaultTwoCurveEURConfig_. |
+----------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.FittingMethod FittingMethod                                  | The symbolic name of the fitting method used. Example value: _NonLinearLeastSquares_.                                                                          |
+----------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.SABRExtrapolationCutoffStrike SABRExtrapolationCutoffStrike  | The cutoff strike after which extrapolation is used. Example value: _0.07_.                                                                                    |
+----------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.SABRTailThicknessParameter SABRTailThicknessParameter        | The tail thickness. Example value: _10.0_.                                                                                                                     |
+----------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValue.VolatilityModel VolatilityModel                              | The symbolic name of the volatility model used. Example value: _SABR_.                                                                                         |
+----------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+



PresentValueCurveSensitivity

...............................
Present Value Curve Sensitivity
...............................


The sensitivity of the present value to points on the yield curve at every point a cash-flow instrument has sensitivity.



+--------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                                   | Description                                                                                                                                                    |
+============================================================================================+================================================================================================================================================================+
|  PresentValueCurveSensitivity.CalculationMethod CalculationMethod                          | The symbolic name of the general calculation method used. Example values: _SABRRightExtrapolation_, _SABRNoExtrapolation_.                                     |
+--------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueCurveSensitivity.Cube Cube                                                    | The symbolic name of the cube used. Example value: _BLOOMBERG_.                                                                                                |
+--------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueCurveSensitivity.Currency Currency                                            | The currency of the value, specified as a 3-digit ISO code. Example values: _EUR_, _USD_, _GBP_.                                                               |
+--------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueCurveSensitivity.CurveCalculationConfig CurveCalculationConfig                | The symbolic name of the configuration used for the curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveGBPConfig_, _DefaultTwoCurveEURConfig_. |
+--------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueCurveSensitivity.FittingMethod FittingMethod                                  | The symbolic name of the fitting method used. Example value: _NonLinearLeastSquares_.                                                                          |
+--------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueCurveSensitivity.SABRExtrapolationCutoffStrike SABRExtrapolationCutoffStrike  | The cutoff strike after which extrapolation is used. Example value: _0.07_.                                                                                    |
+--------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueCurveSensitivity.SABRTailThicknessParameter SABRTailThicknessParameter        | The tail thickness. Example value: _10.0_.                                                                                                                     |
+--------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueCurveSensitivity.VolatilityModel VolatilityModel                              | The symbolic name of the volatility model used. Example value: _SABR_.                                                                                         |
+--------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+



PresentValueSABRAlphaNodeSensitivity

.........................................
Present Value SABR Alpha Node Sensitivity
.........................................


The sensitivity of the present value of an instrument to the alpha parameter of the SABR model. Sensitivity to the grid node points.



+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                                           | Description                                                                                                                                                    |
+====================================================================================================+================================================================================================================================================================+
|  PresentValueSABRAlphaNodeSensitivity.CalculationMethod CalculationMethod                          | The symbolic name of the general calculation method used. Example value: _SABRRightExtrapolation_.                                                             |
+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaNodeSensitivity.Cube Cube                                                    | The symbolic name of the cube used. Example value: _BLOOMBERG_.                                                                                                |
+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaNodeSensitivity.Currency Currency                                            | The currency of the value, specified as a 3-digit ISO code. Example values: _EUR_, _USD_, _GBP_.                                                               |
+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaNodeSensitivity.CurveCalculationConfig CurveCalculationConfig                | The symbolic name of the configuration used for the curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveGBPConfig_, _DefaultTwoCurveEURConfig_. |
+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaNodeSensitivity.FittingMethod FittingMethod                                  | The symbolic name of the fitting method used. Example value: _NonLinearLeastSquares_.                                                                          |
+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaNodeSensitivity.SABRExtrapolationCutoffStrike SABRExtrapolationCutoffStrike  | The cutoff strike after which extrapolation is used. Example value: _0.07_.                                                                                    |
+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaNodeSensitivity.SABRTailThicknessParameter SABRTailThicknessParameter        | The tail thickness. Example value: _10.0_.                                                                                                                     |
+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaNodeSensitivity.VolatilityModel VolatilityModel                              | The symbolic name of the volatility model used. Example value: _SABR_.                                                                                         |
+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+



PresentValueSABRAlphaSensitivity

....................................
Present Value SABR Alpha Sensitivity
....................................


The sensitivity of the present value of an instrument to the alpha parameter of the SABR model.



+------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                                       | Description                                                                                                                                                    |
+================================================================================================+================================================================================================================================================================+
|  PresentValueSABRAlphaSensitivity.CalculationMethod CalculationMethod                          | The symbolic name of the general calculation method used. Example values: _SABRRightExtrapolation_, _SABRNoExtrapolation_.                                     |
+------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaSensitivity.Cube Cube                                                    | The symbolic name of the cube used. Example value: _BLOOMBERG_.                                                                                                |
+------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaSensitivity.Currency Currency                                            | The currency of the value, specified as a 3-digit ISO code. Example values: _EUR_, _USD_, _GBP_.                                                               |
+------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaSensitivity.CurveCalculationConfig CurveCalculationConfig                | The symbolic name of the configuration used for the curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveGBPConfig_, _DefaultTwoCurveEURConfig_. |
+------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaSensitivity.FittingMethod FittingMethod                                  | The symbolic name of the fitting method used. Example value: _NonLinearLeastSquares_.                                                                          |
+------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaSensitivity.SABRExtrapolationCutoffStrike SABRExtrapolationCutoffStrike  | The cutoff strike after which extrapolation is used. Example value: _0.07_.                                                                                    |
+------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaSensitivity.SABRTailThicknessParameter SABRTailThicknessParameter        | The tail thickness. Example value: _10.0_.                                                                                                                     |
+------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRAlphaSensitivity.VolatilityModel VolatilityModel                              | The symbolic name of the volatility model used. Example value: _SABR_.                                                                                         |
+------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+



PresentValueSABRNuNodeSensitivity

......................................
Present Value SABR Nu Node Sensitivity
......................................


The sensitivity of the present value of an instrument to the nu parameter of the SABR model. Sensitivity to the grid node points.



+-------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                                        | Description                                                                                                                                                    |
+=================================================================================================+================================================================================================================================================================+
|  PresentValueSABRNuNodeSensitivity.CalculationMethod CalculationMethod                          | The symbolic name of the general calculation method used. Example value: _SABRRightExtrapolation_.                                                             |
+-------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuNodeSensitivity.Cube Cube                                                    | The symbolic name of the cube used. Example value: _BLOOMBERG_.                                                                                                |
+-------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuNodeSensitivity.Currency Currency                                            | The currency of the value, specified as a 3-digit ISO code. Example values: _EUR_, _USD_, _GBP_.                                                               |
+-------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuNodeSensitivity.CurveCalculationConfig CurveCalculationConfig                | The symbolic name of the configuration used for the curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveGBPConfig_, _DefaultTwoCurveEURConfig_. |
+-------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuNodeSensitivity.FittingMethod FittingMethod                                  | The symbolic name of the fitting method used. Example value: _NonLinearLeastSquares_.                                                                          |
+-------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuNodeSensitivity.SABRExtrapolationCutoffStrike SABRExtrapolationCutoffStrike  | The cutoff strike after which extrapolation is used. Example value: _0.07_.                                                                                    |
+-------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuNodeSensitivity.SABRTailThicknessParameter SABRTailThicknessParameter        | The tail thickness. Example value: _10.0_.                                                                                                                     |
+-------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuNodeSensitivity.VolatilityModel VolatilityModel                              | The symbolic name of the volatility model used. Example value: _SABR_.                                                                                         |
+-------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+



PresentValueSABRNuSensitivity

.................................
Present Value SABR Nu Sensitivity
.................................


The sensitivity of the present value of an instrument to the nu parameter of the SABR model.



+---------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                                    | Description                                                                                                                                                    |
+=============================================================================================+================================================================================================================================================================+
|  PresentValueSABRNuSensitivity.CalculationMethod CalculationMethod                          | The symbolic name of the general calculation method used. Example values: _SABRRightExtrapolation_, _SABRNoExtrapolation_.                                     |
+---------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuSensitivity.Cube Cube                                                    | The symbolic name of the cube used. Example value: _BLOOMBERG_.                                                                                                |
+---------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuSensitivity.Currency Currency                                            | The currency of the value, specified as a 3-digit ISO code. Example values: _EUR_, _USD_, _GBP_.                                                               |
+---------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuSensitivity.CurveCalculationConfig CurveCalculationConfig                | The symbolic name of the configuration used for the curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveGBPConfig_, _DefaultTwoCurveEURConfig_. |
+---------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuSensitivity.FittingMethod FittingMethod                                  | The symbolic name of the fitting method used. Example value: _NonLinearLeastSquares_.                                                                          |
+---------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuSensitivity.SABRExtrapolationCutoffStrike SABRExtrapolationCutoffStrike  | The cutoff strike after which extrapolation is used. Example value: _0.07_.                                                                                    |
+---------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuSensitivity.SABRTailThicknessParameter SABRTailThicknessParameter        | The tail thickness. Example value: _10.0_.                                                                                                                     |
+---------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRNuSensitivity.VolatilityModel VolatilityModel                              | The symbolic name of the volatility model used. Example value: _SABR_.                                                                                         |
+---------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+



PresentValueSABRRhoNodeSensitivity

.......................................
Present Value SABR Rho Node Sensitivity
.......................................


The sensitivity of the present value of an instrument to the rho parameter of the SABR model. Sensitivity to the grid node points.



+--------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                                         | Description                                                                                                                                                    |
+==================================================================================================+================================================================================================================================================================+
|  PresentValueSABRRhoNodeSensitivity.CalculationMethod CalculationMethod                          | The symbolic name of the general calculation method used. Example value: _SABRRightExtrapolation_.                                                             |
+--------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoNodeSensitivity.Cube Cube                                                    | The symbolic name of the cube used. Example value: _BLOOMBERG_.                                                                                                |
+--------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoNodeSensitivity.Currency Currency                                            | The currency of the value, specified as a 3-digit ISO code. Example values: _EUR_, _USD_, _GBP_.                                                               |
+--------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoNodeSensitivity.CurveCalculationConfig CurveCalculationConfig                | The symbolic name of the configuration used for the curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveGBPConfig_, _DefaultTwoCurveEURConfig_. |
+--------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoNodeSensitivity.FittingMethod FittingMethod                                  | The symbolic name of the fitting method used. Example value: _NonLinearLeastSquares_.                                                                          |
+--------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoNodeSensitivity.SABRExtrapolationCutoffStrike SABRExtrapolationCutoffStrike  | The cutoff strike after which extrapolation is used. Example value: _0.07_.                                                                                    |
+--------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoNodeSensitivity.SABRTailThicknessParameter SABRTailThicknessParameter        | The tail thickness. Example value: _10.0_.                                                                                                                     |
+--------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoNodeSensitivity.VolatilityModel VolatilityModel                              | The symbolic name of the volatility model used. Example value: _SABR_.                                                                                         |
+--------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+



PresentValueSABRRhoSensitivity

..................................
Present Value SABR Rho Sensitivity
..................................


The sensitivity of the present value of an instrument to the rho parameter of the SABR model.



+----------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                                     | Description                                                                                                                                                    |
+==============================================================================================+================================================================================================================================================================+
|  PresentValueSABRRhoSensitivity.CalculationMethod CalculationMethod                          | The symbolic name of the general calculation method used. Example values: _SABRRightExtrapolation_, _SABRNoExtrapolation_.                                     |
+----------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoSensitivity.Cube Cube                                                    | The symbolic name of the cube used. Example value: _BLOOMBERG_.                                                                                                |
+----------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoSensitivity.Currency Currency                                            | The currency of the value, specified as a 3-digit ISO code. Example values: _EUR_, _USD_, _GBP_.                                                               |
+----------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoSensitivity.CurveCalculationConfig CurveCalculationConfig                | The symbolic name of the configuration used for the curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveGBPConfig_, _DefaultTwoCurveEURConfig_. |
+----------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoSensitivity.FittingMethod FittingMethod                                  | The symbolic name of the fitting method used. Example value: _NonLinearLeastSquares_.                                                                          |
+----------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoSensitivity.SABRExtrapolationCutoffStrike SABRExtrapolationCutoffStrike  | The cutoff strike after which extrapolation is used. Example value: _0.07_.                                                                                    |
+----------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoSensitivity.SABRTailThicknessParameter SABRTailThicknessParameter        | The tail thickness. Example value: _10.0_.                                                                                                                     |
+----------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  PresentValueSABRRhoSensitivity.VolatilityModel VolatilityModel                              | The symbolic name of the volatility model used. Example value: _SABR_.                                                                                         |
+----------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+



Value

.....
Value
.....


Generic valuation of a security, for example it might be FAIR*VALUE or PRESENT*VALUE depending on the asset class.



+---------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                            | Description                                                                                                                                                    |
+=====================================================================+================================================================================================================================================================+
|  Value.CalculationMethod CalculationMethod                          | The symbolic name of the general calculation method used. Example values: _SABRRightExtrapolation_, _SABRNoExtrapolation_.                                     |
+---------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.Cube Cube                                                    | The symbolic name of the cube used. Example value: _BLOOMBERG_.                                                                                                |
+---------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.Currency Currency                                            | The currency of the value, specified as a 3-digit ISO code. Example values: _EUR_, _USD_, _GBP_.                                                               |
+---------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.CurveCalculationConfig CurveCalculationConfig                | The symbolic name of the configuration used for the curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveGBPConfig_, _DefaultTwoCurveEURConfig_. |
+---------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.FittingMethod FittingMethod                                  | The symbolic name of the fitting method used. Example value: _NonLinearLeastSquares_.                                                                          |
+---------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.SABRExtrapolationCutoffStrike SABRExtrapolationCutoffStrike  | The cutoff strike after which extrapolation is used. Example value: _0.07_.                                                                                    |
+---------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.SABRTailThicknessParameter SABRTailThicknessParameter        | The tail thickness. Example value: _10.0_.                                                                                                                     |
+---------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  Value.VolatilityModel VolatilityModel                              | The symbolic name of the volatility model used. Example value: _SABR_.                                                                                         |
+---------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+



VegaQuoteCube

...............
Vega Quote Cube
...............


The bucketed vega of a security to the market data volatility cube.



+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                      | Description                                                                                                                                                    |
+===============================================================+================================================================================================================================================================+
|  VegaQuoteCube.CalculationMethod CalculationMethod            | The symbolic name of the general calculation method used. Example value: _SABRNoExtrapolation_.                                                                |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.Cube Cube                                      | The symbolic name of the cube used. Example value: _BLOOMBERG_.                                                                                                |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.CubeInstrumentType CubeInstrumentType          | The instrument type the cube is constructed from. Example value: _SWAPTION_CUBE_.                                                                              |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.Currency Currency                              | The currency of the value, specified as a 3-digit ISO code. Example values: _EUR_, _USD_, _GBP_.                                                               |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.CurveCalculationConfig CurveCalculationConfig  | The symbolic name of the configuration used for the curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveGBPConfig_, _DefaultTwoCurveEURConfig_. |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.FittingMethod FittingMethod                    | The symbolic name of the fitting method used. Example value: _NonLinearLeastSquares_.                                                                          |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.LeftXExtrapolator LeftXExtrapolator            | The symbolic name of the left X extrapolator used. Example value: _FlatExtrapolator_.                                                                          |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.LeftYExtrapolator LeftYExtrapolator            | The symbolic name of the left Y extrapolator used. Example value: _FlatExtrapolator_.                                                                          |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.RightXExtrapolator RightXExtrapolator          | The symbolic name of the right X extrapolator used. Example value: _FlatExtrapolator_.                                                                         |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.RightYExtrapolator RightYExtrapolator          | The symbolic name of the right Y extrapolator used. Example value: _FlatExtrapolator_.                                                                         |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.VolatilityModel VolatilityModel                | The symbolic name of the volatility model used. Example value: _SABR_.                                                                                         |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.XInterpolator XInterpolator                    | The symbolic name of the X interpolator used. Example value: _Linear_.                                                                                         |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  VegaQuoteCube.YInterpolator YInterpolator                    | The symbolic name of the Y interpolator used. Example value: _Linear_.                                                                                         |
+---------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+



YieldCurveNodeSensitivities

..............................
Yield Curve Node Sensitivities
..............................


The sensitivities of a cash-flow based fixed-income instrument to each of the nodal points in a yield curve.



+-------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Property                                                                                  | Description                                                                                                                                                    |
+===========================================================================================+================================================================================================================================================================+
|  YieldCurveNodeSensitivities.CalculationMethod CalculationMethod                          | The symbolic name of the general calculation method used. Example values: _SABRRightExtrapolation_, _SABRNoExtrapolation_.                                     |
+-------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.Cube Cube                                                    | The symbolic name of the cube used. Example value: _BLOOMBERG_.                                                                                                |
+-------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.Currency Currency                                            | The currency of the value, specified as a 3-digit ISO code. Example values: _EUR_, _USD_, _GBP_.                                                               |
+-------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.Curve Curve                                                  | The symbolic name of the curve used. Example values: _Forward6M_, _Forward3M_.                                                                                 |
+-------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.CurveCalculationConfig CurveCalculationConfig                | The symbolic name of the configuration used for the curve. Example values: _DefaultTwoCurveUSDConfig_, _DefaultTwoCurveGBPConfig_, _DefaultTwoCurveEURConfig_. |
+-------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.CurveCurrency CurveCurrency                                  | The currency of the curve used. This does not imply anything about the currency of the output value. Example values: _EUR_, _USD_, _GBP_.                      |
+-------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.FittingMethod FittingMethod                                  | The symbolic name of the fitting method used. Example value: _NonLinearLeastSquares_.                                                                          |
+-------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.SABRExtrapolationCutoffStrike SABRExtrapolationCutoffStrike  | The cutoff strike after which extrapolation is used. Example value: _0.07_.                                                                                    |
+-------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.SABRTailThicknessParameter SABRTailThicknessParameter        | The tail thickness. Example value: _10.0_.                                                                                                                     |
+-------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  YieldCurveNodeSensitivities.VolatilityModel VolatilityModel                              | The symbolic name of the volatility model used. Example value: _SABR_.                                                                                         |
+-------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------+



