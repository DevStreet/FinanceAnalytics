title: R Market Data Functions - Y
shortcut: DOC:R Market Data Functions - Y
---
as.data.frame.YieldCurveSnapshot

................................
as.data.frame.YieldCurveSnapshot
................................


Returns a data frame representation of a YieldCurveSnapshot object.



+-----------+----------+--------------------------------------+
| Parameter | Required | Description                          |
+===========+==========+======================================+
| x         | Yes      | The object to convert                |
+-----------+----------+--------------------------------------+
| row.names | Yes      | See as.data.frame method for details |
+-----------+----------+--------------------------------------+
| optional  | Yes      | See as.data.frame method for details |
+-----------+----------+--------------------------------------+
| ...       | Yes      | Ignored                              |
+-----------+----------+--------------------------------------+




fromFudgeMsg.YieldCurveSnapshot

...............................
fromFudgeMsg.YieldCurveSnapshot
...............................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

GetYieldCurveTensor

...................
GetYieldCurveTensor
...................


Fetches a 1D matrix describing only the curve values.



+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+
| Parameter     | Required | Description                                                                                                           |
+===============+==========+=======================================================================================================================+
| snapshot      | Yes      | The yield curve snapshot object to query                                                                              |
+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+
| marketValue   |          | True to query the market data values, false to ignore the market data (true if omitted)                               |
+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+
| overrideValue |          | True to query override data values (in preference to market data), false to ignore override values (false if omitted) |
+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+




is.YieldCurveSnapshot

.....................
is.YieldCurveSnapshot
.....................


Tests whether a value is an instance of the YieldCurveSnapshot class.



+-----------+----------+--------------------+
| Parameter | Required | Description        |
+===========+==========+====================+
| x         | Yes      | The object to test |
+-----------+----------+--------------------+




SetYieldCurvePoint

..................
SetYieldCurvePoint
..................


Updates a point on a yield curve values, returning the updated values.



+---------------+----------+--------------------------------------------------------------------------------+
| Parameter     | Required | Description                                                                    |
+===============+==========+================================================================================+
| snapshot      | Yes      | null                                                                           |
+---------------+----------+--------------------------------------------------------------------------------+
| valueName     | Yes      | The name of the market data line value                                         |
+---------------+----------+--------------------------------------------------------------------------------+
| identifier    | Yes      | The identifier of the underlying instrument                                    |
+---------------+----------+--------------------------------------------------------------------------------+
| overrideValue |          | The new override value, omit to remove the override value                      |
+---------------+----------+--------------------------------------------------------------------------------+
| marketValue   |          | The new "original" market data value, omit to leave the market value unchanged |
+---------------+----------+--------------------------------------------------------------------------------+




SetYieldCurveTensor

...................
SetYieldCurveTensor
...................


Updates the values used for a yield curve, returning the updated yield curve.



+---------------+----------+---------------------------------------------------------------------------------------------------+
| Parameter     | Required | Description                                                                                       |
+===============+==========+===================================================================================================+
| snapshot      | Yes      | The yield curve snapshot to update                                                                |
+---------------+----------+---------------------------------------------------------------------------------------------------+
| overrideValue |          | The modified tensor to apply to the override values, omit to leave override values unchanged      |
+---------------+----------+---------------------------------------------------------------------------------------------------+
| marketValue   |          | The modified tensor to apply to the market data values, omit to leave market data value unchanged |
+---------------+----------+---------------------------------------------------------------------------------------------------+




valuationTime.YieldCurveSnapshot

................................
valuationTime.YieldCurveSnapshot
................................


Accesses the valuationTime field of a YieldCurveSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




values.YieldCurveSnapshot

.........................
values.YieldCurveSnapshot
.........................


Accesses the values field of a YieldCurveSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+



