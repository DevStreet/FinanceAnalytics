title: R Value Functions - V
shortcut: DOC:R Value Functions - V
---
empty.ValueProperties

.....................
empty.ValueProperties
.....................


The empty value property set can be satisfied by any properties, and can satisfy none (other than the empty set).

This is a constant - do not invoke it as a function, refer to it directly by value.


fromFudgeMsg.ValueProperties

............................
fromFudgeMsg.ValueProperties
............................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

infinite.ValueProperties

........................
infinite.ValueProperties
........................


The infinite value property set can satisfy any property constraints, and can only be satisfied by the infinite set.

This is a constant - do not invoke it as a function, refer to it directly by value.


is.ValueProperties

..................
is.ValueProperties
..................


Tests whether a value is an instance of the ValueProperties class.



+-----------+----------+--------------------+
| Parameter | Required | Description        |
+===========+==========+====================+
| x         | Yes      | The object to test |
+-----------+----------+--------------------+




name.ValueRequirement

.....................
name.ValueRequirement
.....................


Parses a value requirement/specification string and returns the name element.



+-------------+----------+-----------------------------+
| Parameter   | Required | Description                 |
+=============+==========+=============================+
| requirement | Yes      | Requirement string to parse |
+-------------+----------+-----------------------------+




new.ValueRequirement

....................
new.ValueRequirement
....................


Creates a requirement/specification string from a name and properties pair. Note that the string representations of requirements and specifications are the same.



+------------+----------+------------------------+
| Parameter  | Required | Description            |
+============+==========+========================+
| name       | Yes      | Value requirement name |
+------------+----------+------------------------+
| properties | Yes      | Value properties       |
+------------+----------+------------------------+




parse.ValueProperties

.....................
parse.ValueProperties
.....................


Parses the string representation and returns the equivalent ValueProperties object.



+----------------+----------+---------------------+
| Parameter      | Required | Description         |
+================+==========+=====================+
| propertyString | Yes      | The string to parse |
+----------------+----------+---------------------+




properties.ValueRequirement

...........................
properties.ValueRequirement
...........................


Parses a value requirement/specification string and returns the properties element.



+-------------+----------+-----------------------------+
| Parameter   | Required | Description                 |
+=============+==========+=============================+
| requirement | Yes      | Requirement string to parse |
+-------------+----------+-----------------------------+




satisfiedBy.ValueProperties

...........................
satisfiedBy.ValueProperties
...........................


Tests if one set of value properties can satisfy the constraints described yb another. Returns TRUE if A is satisfied by B (or equally worded as if B satisfies A) and FALSE otherwise. A and B may be either strung representations of value properties or instances of the ValueProperties object type.



+-----------+----------+---------------------------------------------+
| Parameter | Required | Description                                 |
+===========+==========+=============================================+
| a         | Yes      | The constaints to test satisfaction of      |
+-----------+----------+---------------------------------------------+
| b         | Yes      | The properties satisfy the constraints with |
+-----------+----------+---------------------------------------------+




ValueProperty

.............
ValueProperty
.............


Creates a default property value configuration item.



+---------------+----------+------------------------------------------------------------------------+
| Parameter     | Required | Description                                                            |
+===============+==========+========================================================================+
| name          | Yes      | The property name                                                      |
+---------------+----------+------------------------------------------------------------------------+
| value         |          | The property value(s), omit to create a wild-card "any" property       |
+---------------+----------+------------------------------------------------------------------------+
| configuration |          | The view configurations to apply to, omit for any configuration        |
+---------------+----------+------------------------------------------------------------------------+
| optional      |          | The property's optional flag, typically omitted for default properties |
+---------------+----------+------------------------------------------------------------------------+




ValueRequirementNames

.....................
ValueRequirementNames
.....................


Returns the set of standard Value Requirement Names defined within the system. Note that the Value Requirements available from the current function repository may differ.

This function takes no parameters.


ValueRequirementNames.Actual.Repo

.................................
ValueRequirementNames.Actual.Repo
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Affine.Dividends

......................................
ValueRequirementNames.Affine.Dividends
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Black.Price

.................................
ValueRequirementNames.Black.Price
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.BlackVolatilitySurface

............................................
ValueRequirementNames.BlackVolatilitySurface
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.BlackVolatilitySurfaceInterpolator

........................................................
ValueRequirementNames.BlackVolatilitySurfaceInterpolator
........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Bond.Coupon.Payment.Times

...............................................
ValueRequirementNames.Bond.Coupon.Payment.Times
...............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Bond.Tenor

................................
ValueRequirementNames.Bond.Tenor
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Call.Spread.Value.Vega

............................................
ValueRequirementNames.Call.Spread.Value.Vega
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Beta

...............................
ValueRequirementNames.CAPM.Beta
...............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Adjusted.R.Squared

........................................................
ValueRequirementNames.CAPM.Regression.Adjusted.R.Squared
........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Alpha

...........................................
ValueRequirementNames.CAPM.Regression.Alpha
...........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Alpha.p.Values

....................................................
ValueRequirementNames.CAPM.Regression.Alpha.p.Values
....................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Alpha.Residual

....................................................
ValueRequirementNames.CAPM.Regression.Alpha.Residual
....................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Alpha.Standard.Error

..........................................................
ValueRequirementNames.CAPM.Regression.Alpha.Standard.Error
..........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Alpha.t.Stats

...................................................
ValueRequirementNames.CAPM.Regression.Alpha.t.Stats
...................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Beta

..........................................
ValueRequirementNames.CAPM.Regression.Beta
..........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Beta.p.Values

...................................................
ValueRequirementNames.CAPM.Regression.Beta.p.Values
...................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Beta.Residual

...................................................
ValueRequirementNames.CAPM.Regression.Beta.Residual
...................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Beta.Standard.Error

.........................................................
ValueRequirementNames.CAPM.Regression.Beta.Standard.Error
.........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Beta.t.Stats

..................................................
ValueRequirementNames.CAPM.Regression.Beta.t.Stats
..................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.Mean.Square.Error

.......................................................
ValueRequirementNames.CAPM.Regression.Mean.Square.Error
.......................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CAPM.Regression.R.Squared

...............................................
ValueRequirementNames.CAPM.Regression.R.Squared
...............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CarryRho

..............................
ValueRequirementNames.CarryRho
..............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Clean.Price

.................................
ValueRequirementNames.Clean.Price
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Convertion.Factor

.......................................
ValueRequirementNames.Convertion.Factor
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Convexity

...............................
ValueRequirementNames.Convexity
...............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Cost.Of.Carry

...................................
ValueRequirementNames.Cost.Of.Carry
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Credit.Sensitivities

..........................................
ValueRequirementNames.Credit.Sensitivities
..........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CS01

..........................
ValueRequirementNames.CS01
..........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CurrencyPairs

...................................
ValueRequirementNames.CurrencyPairs
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Current.Yield

...................................
ValueRequirementNames.Current.Yield
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.CurveCalculationConfig

............................................
ValueRequirementNames.CurveCalculationConfig
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Daily.PnL

...............................
ValueRequirementNames.Daily.PnL
...............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Delta

...........................
ValueRequirementNames.Delta
...........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.DeltaBleed

................................
ValueRequirementNames.DeltaBleed
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Dirty.Price

.................................
ValueRequirementNames.Dirty.Price
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.DiscountCurve

...................................
ValueRequirementNames.DiscountCurve
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Dividend.Yield

....................................
ValueRequirementNames.Dividend.Yield
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Domestic.Price.LV

.......................................
ValueRequirementNames.Domestic.Price.LV
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.DriftlessTheta

....................................
ValueRequirementNames.DriftlessTheta
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Dual.Delta

................................
ValueRequirementNames.Dual.Delta
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Dual.Delta.LV

...................................
ValueRequirementNames.Dual.Delta.LV
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Dual.Gamma

................................
ValueRequirementNames.Dual.Gamma
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Dual.Gamma.LV

...................................
ValueRequirementNames.Dual.Gamma.LV
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.DV01

..........................
ValueRequirementNames.DV01
..........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.DV01.ext

..............................
ValueRequirementNames.DV01.ext
..............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.dVanna.dVol

.................................
ValueRequirementNames.dVanna.dVol
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.dZeta.dVol

................................
ValueRequirementNames.dZeta.dVol
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Elasticity

................................
ValueRequirementNames.Elasticity
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.External.Sensitivities

............................................
ValueRequirementNames.External.Sensitivities
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.FairValue

...............................
ValueRequirementNames.FairValue
...............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Fisher.Kurtosis

.....................................
ValueRequirementNames.Fisher.Kurtosis
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forex.Domestic.Price

..........................................
ValueRequirementNames.Forex.Domestic.Price
..........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forex.PV.Quotes

.....................................
ValueRequirementNames.Forex.PV.Quotes
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward

.............................
ValueRequirementNames.Forward
.............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward.Delta

...................................
ValueRequirementNames.Forward.Delta
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward.Delta.LV

......................................
ValueRequirementNames.Forward.Delta.LV
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward.Gamma

...................................
ValueRequirementNames.Forward.Gamma
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward.Gamma.LV

......................................
ValueRequirementNames.Forward.Gamma.LV
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward.Price

...................................
ValueRequirementNames.Forward.Price
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward.Vanna

...................................
ValueRequirementNames.Forward.Vanna
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward.Vanna.LV

......................................
ValueRequirementNames.Forward.Vanna.LV
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward.Vega

..................................
ValueRequirementNames.Forward.Vega
..................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward.Vega.LV

.....................................
ValueRequirementNames.Forward.Vega.LV
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward.Vomma

...................................
ValueRequirementNames.Forward.Vomma
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Forward.Vomma.LV

......................................
ValueRequirementNames.Forward.Vomma.LV
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ForwardCurve

..................................
ValueRequirementNames.ForwardCurve
..................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ForwardSwapCurveMarketData

................................................
ValueRequirementNames.ForwardSwapCurveMarketData
................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Full.PDE.Grid.LV

......................................
ValueRequirementNames.Full.PDE.Grid.LV
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.FuturePriceCurveData

..........................................
ValueRequirementNames.FuturePriceCurveData
..........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.FX.Currency.Exposure

..........................................
ValueRequirementNames.FX.Currency.Exposure
..........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.FX.Curve.Sensitivities

............................................
ValueRequirementNames.FX.Curve.Sensitivities
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.FX.Present.Value

......................................
ValueRequirementNames.FX.Present.Value
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.FXForwardCurveMarketData

..............................................
ValueRequirementNames.FXForwardCurveMarketData
..............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.FXForwardCurveSpec

........................................
ValueRequirementNames.FXForwardCurveSpec
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Gamma

...........................
ValueRequirementNames.Gamma
...........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.GammaBleed

................................
ValueRequirementNames.GammaBleed
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.GammaP

............................
ValueRequirementNames.GammaP
............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.GammaPBleed

.................................
ValueRequirementNames.GammaPBleed
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Grid.Dual.Delta

.....................................
ValueRequirementNames.Grid.Dual.Delta
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Grid.Dual.Gamma

.....................................
ValueRequirementNames.Grid.Dual.Gamma
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Grid.Forward.Delta

........................................
ValueRequirementNames.Grid.Forward.Delta
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Grid.Forward.Gamma

........................................
ValueRequirementNames.Grid.Forward.Gamma
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Grid.Forward.Vanna

........................................
ValueRequirementNames.Grid.Forward.Vanna
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Grid.Forward.Vega

.......................................
ValueRequirementNames.Grid.Forward.Vega
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Grid.Forward.Vomma

........................................
ValueRequirementNames.Grid.Forward.Vomma
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Grid.Implied.Volatility

.............................................
ValueRequirementNames.Grid.Implied.Volatility
.............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Grid.Present.Value

........................................
ValueRequirementNames.Grid.Present.Value
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Gross.Basis

.................................
ValueRequirementNames.Gross.Basis
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.HazardRateCurve

.....................................
ValueRequirementNames.HazardRateCurve
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Heston.Surfaces

.....................................
ValueRequirementNames.Heston.Surfaces
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Historical.FX.Time.Series

...............................................
ValueRequirementNames.Historical.FX.Time.Series
...............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Historical.Time.Series

............................................
ValueRequirementNames.Historical.Time.Series
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Historical.Time.Series.latest.value

.........................................................
ValueRequirementNames.Historical.Time.Series.latest.value
.........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.HistoricalCVaR

....................................
ValueRequirementNames.HistoricalCVaR
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.HistoricalCVaR.ext

........................................
ValueRequirementNames.HistoricalCVaR.ext
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.HistoricalVaR

...................................
ValueRequirementNames.HistoricalVaR
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.HistoricalVaR.Standard.Deviation

......................................................
ValueRequirementNames.HistoricalVaR.Standard.Deviation
......................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.HistoricalVaR.Standard.Deviation.ext

..........................................................
ValueRequirementNames.HistoricalVaR.Standard.Deviation.ext
..........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Implied.Repo

..................................
ValueRequirementNames.Implied.Repo
..................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Implied.Vol.LV.Black.Equivalent

.....................................................
ValueRequirementNames.Implied.Vol.LV.Black.Equivalent
.....................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Implied.Volatility

........................................
ValueRequirementNames.Implied.Volatility
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.InterpolatedVolatilitySurfaceData

.......................................................
ValueRequirementNames.InterpolatedVolatilitySurfaceData
.......................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Jensen.s.Alpha

....................................
ValueRequirementNames.Jensen.s.Alpha
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Last.Market.Cap

.....................................
ValueRequirementNames.Last.Market.Cap
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Last.Price

................................
ValueRequirementNames.Last.Price
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Last.Raw.Beta

...................................
ValueRequirementNames.Last.Raw.Beta
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Last.Volume

.................................
ValueRequirementNames.Last.Volume
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.LocalVolatilitySurface

............................................
ValueRequirementNames.LocalVolatilitySurface
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Macaulay.Duration

.......................................
ValueRequirementNames.Macaulay.Duration
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Mark

..........................
ValueRequirementNames.Mark
..........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Market.Clean.Price

........................................
ValueRequirementNames.Market.Clean.Price
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Market.Dirty.Price

........................................
ValueRequirementNames.Market.Dirty.Price
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Market.Yield.To.Maturity

..............................................
ValueRequirementNames.Market.Yield.To.Maturity
..............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Median

............................
ValueRequirementNames.Median
............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Modified.Duration

.......................................
ValueRequirementNames.Modified.Duration
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Nelson.Siegel.Bond.Curve

..............................................
ValueRequirementNames.Nelson.Siegel.Bond.Curve
..............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Nelson.Siegel.Svennson.Bond.Curve

.......................................................
ValueRequirementNames.Nelson.Siegel.Svennson.Bond.Curve
.......................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Net.Basis

...............................
ValueRequirementNames.Net.Basis
...............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Netted.Fixed.Cash.Flows

.............................................
ValueRequirementNames.Netted.Fixed.Cash.Flows
.............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.P.L.Series

................................
ValueRequirementNames.P.L.Series
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Par.Rate

..............................
ValueRequirementNames.Par.Rate
..............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Par.Rate.Curve.Sensitivity

................................................
ValueRequirementNames.Par.Rate.Curve.Sensitivity
................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Par.Rate.Parallel.Shift.Sensitivity

.........................................................
ValueRequirementNames.Par.Rate.Parallel.Shift.Sensitivity
.........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ParametricVaR

...................................
ValueRequirementNames.ParametricVaR
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Pay.Fixed.Cash.Flows

..........................................
ValueRequirementNames.Pay.Fixed.Cash.Flows
..........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Pay.Floating.Cash.Flows

.............................................
ValueRequirementNames.Pay.Floating.Cash.Flows
.............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PDE.Bucketed.Vega.LV

..........................................
ValueRequirementNames.PDE.Bucketed.Vega.LV
..........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PDE.Greeks.LV

...................................
ValueRequirementNames.PDE.Greeks.LV
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Pearson.Kurtosis

......................................
ValueRequirementNames.Pearson.Kurtosis
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Phi

.........................
ValueRequirementNames.Phi
.........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Piecewise.SABR.fitted.surface

...................................................
ValueRequirementNames.Piecewise.SABR.fitted.surface
...................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PnL

.........................
ValueRequirementNames.PnL
.........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionCarryRho

......................................
ValueRequirementNames.PositionCarryRho
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionDelta

...................................
ValueRequirementNames.PositionDelta
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionDeltaBleed

........................................
ValueRequirementNames.PositionDeltaBleed
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionDriftlessTheta

............................................
ValueRequirementNames.PositionDriftlessTheta
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositiondVanna.dVol

.........................................
ValueRequirementNames.PositiondVanna.dVol
.........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositiondZeta.dVol

........................................
ValueRequirementNames.PositiondZeta.dVol
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionElasticity

........................................
ValueRequirementNames.PositionElasticity
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionFairValue

.......................................
ValueRequirementNames.PositionFairValue
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionGamma

...................................
ValueRequirementNames.PositionGamma
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionGammaBleed

........................................
ValueRequirementNames.PositionGammaBleed
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionGammaP

....................................
ValueRequirementNames.PositionGammaP
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionGammaPBleed

.........................................
ValueRequirementNames.PositionGammaPBleed
.........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionPhi

.................................
ValueRequirementNames.PositionPhi
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionRho

.................................
ValueRequirementNames.PositionRho
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionSpeed

...................................
ValueRequirementNames.PositionSpeed
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionSpeedP

....................................
ValueRequirementNames.PositionSpeedP
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionStrikeDelta

.........................................
ValueRequirementNames.PositionStrikeDelta
.........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionStrikeGamma

.........................................
ValueRequirementNames.PositionStrikeGamma
.........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionTheta

...................................
ValueRequirementNames.PositionTheta
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionUltima

....................................
ValueRequirementNames.PositionUltima
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionVanna

...................................
ValueRequirementNames.PositionVanna
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionVarianceUltima

............................................
ValueRequirementNames.PositionVarianceUltima
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionVarianceVanna

...........................................
ValueRequirementNames.PositionVarianceVanna
...........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionVarianceVega

..........................................
ValueRequirementNames.PositionVarianceVega
..........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionVarianceVomma

...........................................
ValueRequirementNames.PositionVarianceVomma
...........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionVega

..................................
ValueRequirementNames.PositionVega
..................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionVegaBleed

.......................................
ValueRequirementNames.PositionVegaBleed
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionVegaP

...................................
ValueRequirementNames.PositionVegaP
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionVomma

...................................
ValueRequirementNames.PositionVomma
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionVommaP

....................................
ValueRequirementNames.PositionVommaP
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionZeta

..................................
ValueRequirementNames.PositionZeta
..................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionZetaBleed

.......................................
ValueRequirementNames.PositionZetaBleed
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionZomma

...................................
ValueRequirementNames.PositionZomma
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PositionZommaP

....................................
ValueRequirementNames.PositionZommaP
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value

...................................
ValueRequirementNames.Present.Value
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value.Coupon.Sensitivity

......................................................
ValueRequirementNames.Present.Value.Coupon.Sensitivity
......................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value.Curve.Sensitivity

.....................................................
ValueRequirementNames.Present.Value.Curve.Sensitivity
.....................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value.ext

.......................................
ValueRequirementNames.Present.Value.ext
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value.SABR.Alpha.Node.Sensitivity

...............................................................
ValueRequirementNames.Present.Value.SABR.Alpha.Node.Sensitivity
...............................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value.SABR.Alpha.Sensitivity

..........................................................
ValueRequirementNames.Present.Value.SABR.Alpha.Sensitivity
..........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value.SABR.Beta.Node.Sensitivity

..............................................................
ValueRequirementNames.Present.Value.SABR.Beta.Node.Sensitivity
..............................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value.SABR.Beta.Sensitivity

.........................................................
ValueRequirementNames.Present.Value.SABR.Beta.Sensitivity
.........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value.SABR.Nu.Node.Sensitivity

............................................................
ValueRequirementNames.Present.Value.SABR.Nu.Node.Sensitivity
............................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value.SABR.Nu.Sensitivity

.......................................................
ValueRequirementNames.Present.Value.SABR.Nu.Sensitivity
.......................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value.SABR.Rho.Node.Sensitivity

.............................................................
ValueRequirementNames.Present.Value.SABR.Rho.Node.Sensitivity
.............................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Present.Value.SABR.Rho.Sensitivity

........................................................
ValueRequirementNames.Present.Value.SABR.Rho.Sensitivity
........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Price.LV

..............................
ValueRequirementNames.Price.LV
..............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Price.Series

..................................
ValueRequirementNames.Price.Series
..................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Projected.Floating.Pay.Cash.Flows

.......................................................
ValueRequirementNames.Projected.Floating.Pay.Cash.Flows
.......................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Projected.Floating.Receive.Cash.Flows

...........................................................
ValueRequirementNames.Projected.Floating.Receive.Cash.Flows
...........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PureVolatilitySurface

...........................................
ValueRequirementNames.PureVolatilitySurface
...........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PV.Z.Spread.Sensitivity

.............................................
ValueRequirementNames.PV.Z.Spread.Sensitivity
.............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.PV01

..........................
ValueRequirementNames.PV01
..........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Quantity.ext

..................................
ValueRequirementNames.Quantity.ext
..................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Receive.Fixed.Cash.Flows

..............................................
ValueRequirementNames.Receive.Fixed.Cash.Flows
..............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Receive.Floating.Cash.Flows

.................................................
ValueRequirementNames.Receive.Floating.Cash.Flows
.................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Return.Series

...................................
ValueRequirementNames.Return.Series
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Rho

.........................
ValueRequirementNames.Rho
.........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.SABR.Surfaces

...................................
ValueRequirementNames.SABR.Surfaces
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Security.Implied.Volatility

.................................................
ValueRequirementNames.Security.Implied.Volatility
.................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Security.Market.Price

...........................................
ValueRequirementNames.Security.Market.Price
...........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Security.Model.Price

..........................................
ValueRequirementNames.Security.Model.Price
..........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Sharpe.Ratio

..................................
ValueRequirementNames.Sharpe.Ratio
..................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Skew

..........................
ValueRequirementNames.Skew
..........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Speed

...........................
ValueRequirementNames.Speed
...........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.SpeedP

............................
ValueRequirementNames.SpeedP
............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Spot

..........................
ValueRequirementNames.Spot
..........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Spot.FX.Change

....................................
ValueRequirementNames.Spot.FX.Change
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Spot.Rate.For.Security

............................................
ValueRequirementNames.Spot.Rate.For.Security
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.SpotRate

..............................
ValueRequirementNames.SpotRate
..............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.StandardVolatilityCubeData

................................................
ValueRequirementNames.StandardVolatilityCubeData
................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.StandardVolatilitySurfaceData

...................................................
ValueRequirementNames.StandardVolatilitySurfaceData
...................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.StrikeDelta

.................................
ValueRequirementNames.StrikeDelta
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.StrikeGamma

.................................
ValueRequirementNames.StrikeGamma
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Sum

.........................
ValueRequirementNames.Sum
.........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Theta

...........................
ValueRequirementNames.Theta
...........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Total.Risk.Alpha

......................................
ValueRequirementNames.Total.Risk.Alpha
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Treynor.Ratio

...................................
ValueRequirementNames.Treynor.Ratio
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Ultima

............................
ValueRequirementNames.Ultima
............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Underlying.Market.Price

.............................................
ValueRequirementNames.Underlying.Market.Price
.............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Underlying.Model.Price

............................................
ValueRequirementNames.Underlying.Model.Price
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Underlying.Return.Series

..............................................
ValueRequirementNames.Underlying.Return.Series
..............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Value

...........................
ValueRequirementNames.Value
...........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueCarryRho

...................................
ValueRequirementNames.ValueCarryRho
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueDelta

................................
ValueRequirementNames.ValueDelta
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueDeltaBleed

.....................................
ValueRequirementNames.ValueDeltaBleed
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueDriftlessTheta

.........................................
ValueRequirementNames.ValueDriftlessTheta
.........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValuedVanna.dVol

......................................
ValueRequirementNames.ValuedVanna.dVol
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValuedZeta.dVol

.....................................
ValueRequirementNames.ValuedZeta.dVol
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueElasticity

.....................................
ValueRequirementNames.ValueElasticity
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueFairValue

....................................
ValueRequirementNames.ValueFairValue
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueGamma

................................
ValueRequirementNames.ValueGamma
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueGammaBleed

.....................................
ValueRequirementNames.ValueGammaBleed
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueGammaP

.................................
ValueRequirementNames.ValueGammaP
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueGammaPBleed

......................................
ValueRequirementNames.ValueGammaPBleed
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValuePhi

..............................
ValueRequirementNames.ValuePhi
..............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueRho

..............................
ValueRequirementNames.ValueRho
..............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueSpeed

................................
ValueRequirementNames.ValueSpeed
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueSpeedP

.................................
ValueRequirementNames.ValueSpeedP
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueStrikeDelta

......................................
ValueRequirementNames.ValueStrikeDelta
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueStrikeGamma

......................................
ValueRequirementNames.ValueStrikeGamma
......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueTheta

................................
ValueRequirementNames.ValueTheta
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueUltima

.................................
ValueRequirementNames.ValueUltima
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueVanna

................................
ValueRequirementNames.ValueVanna
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueVarianceUltima

.........................................
ValueRequirementNames.ValueVarianceUltima
.........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueVarianceVanna

........................................
ValueRequirementNames.ValueVarianceVanna
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueVarianceVega

.......................................
ValueRequirementNames.ValueVarianceVega
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueVarianceVomma

........................................
ValueRequirementNames.ValueVarianceVomma
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueVega

...............................
ValueRequirementNames.ValueVega
...............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueVegaBleed

....................................
ValueRequirementNames.ValueVegaBleed
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueVegaP

................................
ValueRequirementNames.ValueVegaP
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueVomma

................................
ValueRequirementNames.ValueVomma
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueVommaP

.................................
ValueRequirementNames.ValueVommaP
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueZeta

...............................
ValueRequirementNames.ValueZeta
...............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueZetaBleed

....................................
ValueRequirementNames.ValueZetaBleed
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueZomma

................................
ValueRequirementNames.ValueZomma
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ValueZommaP

.................................
ValueRequirementNames.ValueZommaP
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Vanna

...........................
ValueRequirementNames.Vanna
...........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VannaVolgaVolatilitySurfaceData

.....................................................
ValueRequirementNames.VannaVolgaVolatilitySurfaceData
.....................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VarianceUltima

....................................
ValueRequirementNames.VarianceUltima
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VarianceVanna

...................................
ValueRequirementNames.VarianceVanna
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VarianceVega

..................................
ValueRequirementNames.VarianceVega
..................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VarianceVomma

...................................
ValueRequirementNames.VarianceVomma
...................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Vega

..........................
ValueRequirementNames.Vega
..........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Vega.Matrix

.................................
ValueRequirementNames.Vega.Matrix
.................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Vega.Quote.Cube

.....................................
ValueRequirementNames.Vega.Quote.Cube
.....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Vega.Quote.Matrix

.......................................
ValueRequirementNames.Vega.Quote.Matrix
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VegaBleed

...............................
ValueRequirementNames.VegaBleed
...............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VegaP

...........................
ValueRequirementNames.VegaP
...........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Volatility.Surface.Fitted.Points

......................................................
ValueRequirementNames.Volatility.Surface.Fitted.Points
......................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Volatility.Surface.Historical.Time.Series

...............................................................
ValueRequirementNames.Volatility.Surface.Historical.Time.Series
...............................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VolatilityCube

....................................
ValueRequirementNames.VolatilityCube
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VolatilityCubeDefinition

..............................................
ValueRequirementNames.VolatilityCubeDefinition
..............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VolatilityCubeFittedPoints

................................................
ValueRequirementNames.VolatilityCubeFittedPoints
................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VolatilityCubeMarketData

..............................................
ValueRequirementNames.VolatilityCubeMarketData
..............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VolatilityCubeSpec

........................................
ValueRequirementNames.VolatilityCubeSpec
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VolatilitySurface

.......................................
ValueRequirementNames.VolatilitySurface
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VolatilitySurfaceData

...........................................
ValueRequirementNames.VolatilitySurfaceData
...........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VolatilitySurfaceSpecification

....................................................
ValueRequirementNames.VolatilitySurfaceSpecification
....................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Vomma

...........................
ValueRequirementNames.Vomma
...........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.VommaP

............................
ValueRequirementNames.VommaP
............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Weight

............................
ValueRequirementNames.Weight
............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Yield.Curve.Historical.Time.Series

........................................................
ValueRequirementNames.Yield.Curve.Historical.Time.Series
........................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Yield.Curve.Instrument.Conversion.Historical.Time.Series

..............................................................................
ValueRequirementNames.Yield.Curve.Instrument.Conversion.Historical.Time.Series
..............................................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Yield.Curve.Node.Sensitivities

....................................................
ValueRequirementNames.Yield.Curve.Node.Sensitivities
....................................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Yield.To.Maturity

.......................................
ValueRequirementNames.Yield.To.Maturity
.......................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.YieldCurve

................................
ValueRequirementNames.YieldCurve
................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.YieldCurveInterpolated

............................................
ValueRequirementNames.YieldCurveInterpolated
............................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.YieldCurveJacobian

........................................
ValueRequirementNames.YieldCurveJacobian
........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.YieldCurveMarketData

..........................................
ValueRequirementNames.YieldCurveMarketData
..........................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.YieldCurveSpec

....................................
ValueRequirementNames.YieldCurveSpec
....................................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Z.Spread

..............................
ValueRequirementNames.Z.Spread
..............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Zeta

..........................
ValueRequirementNames.Zeta
..........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ZetaBleed

...............................
ValueRequirementNames.ZetaBleed
...............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.Zomma

...........................
ValueRequirementNames.Zomma
...........................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.


ValueRequirementNames.ZommaP

............................
ValueRequirementNames.ZommaP
............................


The symbolic constant used within the analytics library to describe calculated values.

This is a constant - do not invoke it as a function, refer to it directly by value.

