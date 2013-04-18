title: R Market Data Functions - V
shortcut: DOC:R Market Data Functions - V
---
as.data.frame.VolatilitySurfaceSnapshot

.......................................
as.data.frame.VolatilitySurfaceSnapshot
.......................................


Returns a data frame representation of a VolatilitySurfaceSnapshot object.



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




fromFudgeMsg.VolatilityCubeSnapshot

...................................
fromFudgeMsg.VolatilityCubeSnapshot
...................................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

fromFudgeMsg.VolatilitySurfaceSnapshot

......................................
fromFudgeMsg.VolatilitySurfaceSnapshot
......................................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

fromVectors.VolatilitySurfaceSnapshot

.....................................
fromVectors.VolatilitySurfaceSnapshot
.....................................


Creates a volatility surface from vectors containing the keys and values.



+---------------+----------+---------------------------------------------------------------------+
| Parameter     | Required | Description                                                         |
+===============+==========+=====================================================================+
| xc            | Yes      | The class/type of X key values (e.g. "TENOR")                       |
+---------------+----------+---------------------------------------------------------------------+
| x             | Yes      | The X key values                                                    |
+---------------+----------+---------------------------------------------------------------------+
| yc            | Yes      | The class/type of Y key values (e.g. "INTEGER_FXVOLQUOTETYPE_PAIR") |
+---------------+----------+---------------------------------------------------------------------+
| y             | Yes      | The Y key values                                                    |
+---------------+----------+---------------------------------------------------------------------+
| marketValue   | Yes      | The market data value points                                        |
+---------------+----------+---------------------------------------------------------------------+
| overrideValue |          | The override value points (omit to not set)                         |
+---------------+----------+---------------------------------------------------------------------+




GetVolatilityCubeTensor

.......................
GetVolatilityCubeTensor
.......................


Fetches a 3D matrix describing only the cube values.



+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+
| Parameter     | Required | Description                                                                                                           |
+===============+==========+=======================================================================================================================+
| snapshot      | Yes      | The volatility cube snapshot object to query                                                                          |
+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+
| marketValue   |          | True to query the market data values, false to ignore the market data (true if omitted)                               |
+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+
| overrideValue |          | True to query override data values (in preference to market data), false to ignore override values (false if omitted) |
+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+




GetVolatilitySurfaceTensor

..........................
GetVolatilitySurfaceTensor
..........................


Fetches a 2D matrix describing only the surface values.



+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+
| Parameter     | Required | Description                                                                                                           |
+===============+==========+=======================================================================================================================+
| snapshot      | Yes      | The volatility surface snapshot object to query                                                                       |
+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+
| marketValue   |          | True to query the market data values, false to ignore the market data (true if omitted)                               |
+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+
| overrideValue |          | True to query override data values (in preference to market data), false to ignore override values (false if omitted) |
+---------------+----------+-----------------------------------------------------------------------------------------------------------------------+




is.VolatilityCubeSnapshot

.........................
is.VolatilityCubeSnapshot
.........................


Tests whether a value is an instance of the VolatilityCubeSnapshot class.



+-----------+----------+--------------------+
| Parameter | Required | Description        |
+===========+==========+====================+
| x         | Yes      | The object to test |
+-----------+----------+--------------------+




is.VolatilitySurfaceSnapshot

............................
is.VolatilitySurfaceSnapshot
............................


Tests whether a value is an instance of the VolatilitySurfaceSnapshot class.



+-----------+----------+--------------------+
| Parameter | Required | Description        |
+===========+==========+====================+
| x         | Yes      | The object to test |
+-----------+----------+--------------------+




otherValues.VolatilityCubeSnapshot

..................................
otherValues.VolatilityCubeSnapshot
..................................


Accesses the otherValues field of a VolatilityCubeSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




SetVolatilityCubePoint

......................
SetVolatilityCubePoint
......................


Updates a point on a volatility cube values, returning the updated values.



+----------------+----------+-------------------------------------------------------------------------------------------------------------------+
| Parameter      | Required | Description                                                                                                       |
+================+==========+===================================================================================================================+
| snapshot       | Yes      | null                                                                                                              |
+----------------+----------+-------------------------------------------------------------------------------------------------------------------+
| swapTenor      | Yes      | The swap tenor coordinate into the cube                                                                           |
+----------------+----------+-------------------------------------------------------------------------------------------------------------------+
| optionExpiry   | Yes      | The option expiry coordinate into the cube                                                                        |
+----------------+----------+-------------------------------------------------------------------------------------------------------------------+
| relativeStrike | Yes      | The strike relative to at the money (in Bps) coordinate into the cube                                             |
+----------------+----------+-------------------------------------------------------------------------------------------------------------------+
| overrideValue  |          | The new "override" market value, omit both this and the original value to remove from the values                  |
+----------------+----------+-------------------------------------------------------------------------------------------------------------------+
| marketValue    |          | The new "original" market value, may be omitted if an override value is specified to leave the original unchanged |
+----------------+----------+-------------------------------------------------------------------------------------------------------------------+




SetVolatilityCubeTensor

.......................
SetVolatilityCubeTensor
.......................


Updates the values used for a volatility cube, returning the updated volatility surface.



+---------------+----------+---------------------------------------------------------------------------------------------------+
| Parameter     | Required | Description                                                                                       |
+===============+==========+===================================================================================================+
| snapshot      | Yes      | The volatility cube snapshot to update                                                            |
+---------------+----------+---------------------------------------------------------------------------------------------------+
| overrideValue |          | The modified tensor to apply to the override values, omit to leave override values unchanged      |
+---------------+----------+---------------------------------------------------------------------------------------------------+
| marketValue   |          | The modified tensor to apply to the market data values, omit to leave market data value unchanged |
+---------------+----------+---------------------------------------------------------------------------------------------------+




SetVolatilitySurfacePoint

.........................
SetVolatilitySurfacePoint
.........................


Updates a point on a volatility surface values, returning the updated values.



+---------------+----------+----------------------------------------------------------------------------------------------------------+
| Parameter     | Required | Description                                                                                              |
+===============+==========+==========================================================================================================+
| snapshot      | Yes      | null                                                                                                     |
+---------------+----------+----------------------------------------------------------------------------------------------------------+
| x             | Yes      | The X index onto the surface                                                                             |
+---------------+----------+----------------------------------------------------------------------------------------------------------+
| y             | Yes      | The Y index onto the surface                                                                             |
+---------------+----------+----------------------------------------------------------------------------------------------------------+
| overrideValue |          | The new override value, omit to remove the override value                                                |
+---------------+----------+----------------------------------------------------------------------------------------------------------+
| marketValue   |          | The new "original" market data value, omit to leave the market value unchanged                           |
+---------------+----------+----------------------------------------------------------------------------------------------------------+
| xc            |          | The type to coerce the X index value to (required to add new points, may omit to update existing points) |
+---------------+----------+----------------------------------------------------------------------------------------------------------+
| yc            |          | The type to coerce the Y index value to (required to add new points, may omit to update existing points) |
+---------------+----------+----------------------------------------------------------------------------------------------------------+




SetVolatilitySurfaceTensor

..........................
SetVolatilitySurfaceTensor
..........................


Updates the values used for a volatility surface, returning the updated volatility surface.



+---------------+----------+---------------------------------------------------------------------------------------------------+
| Parameter     | Required | Description                                                                                       |
+===============+==========+===================================================================================================+
| snapshot      | Yes      | The volatility surface snapshot to update                                                         |
+---------------+----------+---------------------------------------------------------------------------------------------------+
| overrideValue |          | The modified tensor to apply to the override values, omit to leave override values unchanged      |
+---------------+----------+---------------------------------------------------------------------------------------------------+
| marketValue   |          | The modified tensor to apply to the market data values, omit to leave market data value unchanged |
+---------------+----------+---------------------------------------------------------------------------------------------------+




strikes.VolatilityCubeSnapshot

..............................
strikes.VolatilityCubeSnapshot
..............................


Accesses the strikes field of a VolatilityCubeSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




values.VolatilityCubeSnapshot

.............................
values.VolatilityCubeSnapshot
.............................


Accesses the values field of a VolatilityCubeSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




values.VolatilitySurfaceSnapshot

................................
values.VolatilitySurfaceSnapshot
................................


Accesses the values field of a VolatilitySurfaceSnapshot object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+



