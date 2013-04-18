title: R Market Data Functions - M
shortcut: DOC:R Market Data Functions - M
---
basisViewName.MarketDataSnapshot

................................
basisViewName.MarketDataSnapshot
................................


Accesses the basisViewName field of a MarketDataSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




fromFudgeMsg.ManageableMarketDataSnapshot

.........................................
fromFudgeMsg.ManageableMarketDataSnapshot
.........................................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

fromFudgeMsg.ManageableVolatilityCubeSnapshot

.............................................
fromFudgeMsg.ManageableVolatilityCubeSnapshot
.............................................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

fromFudgeMsg.ManageableVolatilitySurfaceSnapshot

................................................
fromFudgeMsg.ManageableVolatilitySurfaceSnapshot
................................................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

fromFudgeMsg.ManageableYieldCurveSnapshot

.........................................
fromFudgeMsg.ManageableYieldCurveSnapshot
.........................................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

fromFudgeMsg.MarketDataSnapshot

...............................
fromFudgeMsg.MarketDataSnapshot
...............................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

globalValues.MarketDataSnapshot

...............................
globalValues.MarketDataSnapshot
...............................


Accesses the globalValues field of a MarketDataSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




is.MarketDataSnapshot

.....................
is.MarketDataSnapshot
.....................


Tests whether a value is an instance of the MarketDataSnapshot class.



+-----------+----------+--------------------+
| Parameter | Required | Description        |
+===========+==========+====================+
| x         | Yes      | The object to test |
+-----------+----------+--------------------+




MarketDataRequirementNames.Market.Ask

.....................................
MarketDataRequirementNames.Market.Ask
.....................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.Bid

.....................................
MarketDataRequirementNames.Market.Bid
.....................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.CostOfCarry

.............................................
MarketDataRequirementNames.Market.CostOfCarry
.............................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.DirtyPriceMid

...............................................
MarketDataRequirementNames.Market.DirtyPriceMid
...............................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.DividendYield

...............................................
MarketDataRequirementNames.Market.DividendYield
...............................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.High

......................................
MarketDataRequirementNames.Market.High
......................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.ImpliedVolatility

...................................................
MarketDataRequirementNames.Market.ImpliedVolatility
...................................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.Last

......................................
MarketDataRequirementNames.Market.Last
......................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.Low

.....................................
MarketDataRequirementNames.Market.Low
.....................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.Mid

.....................................
MarketDataRequirementNames.Market.Mid
.....................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.OptImpliedVolatilityAsk

.........................................................
MarketDataRequirementNames.Market.OptImpliedVolatilityAsk
.........................................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.OptImpliedVolatilityBest

..........................................................
MarketDataRequirementNames.Market.OptImpliedVolatilityBest
..........................................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.OptImpliedVolatilityBid

.........................................................
MarketDataRequirementNames.Market.OptImpliedVolatilityBid
.........................................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.OptImpliedVolatilityLast

..........................................................
MarketDataRequirementNames.Market.OptImpliedVolatilityLast
..........................................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.OptImpliedVolatilityMid

.........................................................
MarketDataRequirementNames.Market.OptImpliedVolatilityMid
.........................................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.SettlementPrice

.................................................
MarketDataRequirementNames.Market.SettlementPrice
.................................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.Value

.......................................
MarketDataRequirementNames.Market.Value
.......................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.Volume

........................................
MarketDataRequirementNames.Market.Volume
........................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.YieldConventionMid

....................................................
MarketDataRequirementNames.Market.YieldConventionMid
....................................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


MarketDataRequirementNames.Market.YieldYieldToMaturityMid

.........................................................
MarketDataRequirementNames.Market.YieldYieldToMaturityMid
.........................................................


The symbolic constant used within the analytics library to specify market data requirements.

This is a constant - do not invoke it as a function, refer to it directly by value.


name.MarketDataSnapshot

.......................
name.MarketDataSnapshot
.......................


Accesses the name field of a MarketDataSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




uniqueId.MarketDataSnapshot

...........................
uniqueId.MarketDataSnapshot
...........................


Accesses the uniqueId field of a MarketDataSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




volatilityCubes.MarketDataSnapshot

..................................
volatilityCubes.MarketDataSnapshot
..................................


Accesses the volatilityCubes field of a MarketDataSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




volatilitySurfaces.MarketDataSnapshot

.....................................
volatilitySurfaces.MarketDataSnapshot
.....................................


Accesses the volatilitySurfaces field of a MarketDataSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




yieldCurves.MarketDataSnapshot

..............................
yieldCurves.MarketDataSnapshot
..............................


Accesses the yieldCurves field of a MarketDataSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+



