title: R Market Data Functions - S
shortcut: DOC:R Market Data Functions - S
---
ExpandSnapshot


..............
ExpandSnapshot
..............


Expand the contents of a market data snapshot.



+-----------+----------+---------------------------------+
| Parameter | Required | Description                     |
+===========+==========+=================================+
| snapshot  | Yes      | A market data snapshot to query |
+-----------+----------+---------------------------------+




ExpandSnapshotYieldCurve


........................
ExpandSnapshotYieldCurve
........................


Expand the contents of a market data snapshot yield curve.



+--------------------+----------+---------------------------------------------+
| Parameter          | Required | Description                                 |
+====================+==========+=============================================+
| snapshotYieldCurve | Yes      | A market data snapshot yield curve to query |
+--------------------+----------+---------------------------------------------+




FetchSnapshot


.............
FetchSnapshot
.............


Retrieves a values from the system .



+------------+----------+------------------------------+
| Parameter  | Required | Description                  |
+============+==========+==============================+
| identifier | Yes      | The identifier of the values |
+------------+----------+------------------------------+




GetSnapshotBasisViewName


........................
GetSnapshotBasisViewName
........................


Fetches the view name the snapshot was originally based on.



+-----------+----------+-----------------------+
| Parameter | Required | Description           |
+===========+==========+=======================+
| snapshot  | Yes      | The snapshot to query |
+-----------+----------+-----------------------+




GetSnapshotGlobalValue


......................
GetSnapshotGlobalValue
......................


Fetches a global value from a market data values.



+------------+----------+--------------------------------+
| Parameter  | Required | Description                    |
+============+==========+================================+
| snapshot   | Yes      | null                           |
+------------+----------+--------------------------------+
| valueName  | Yes      | The value name to fetch        |
+------------+----------+--------------------------------+
| identifier | Yes      | The target identifier to fetch |
+------------+----------+--------------------------------+




GetSnapshotName


...............
GetSnapshotName
...............


Fetches the name of a snapshot.



+-----------+----------+-----------------------+
| Parameter | Required | Description           |
+===========+==========+=======================+
| snapshot  | Yes      | The snapshot to query |
+-----------+----------+-----------------------+




GetSnapshotVolatilityCube


.........................
GetSnapshotVolatilityCube
.........................


Fetches a volatility cube values from a market data values.



+-----------+----------+-------------------------------+
| Parameter | Required | Description                   |
+===========+==========+===============================+
| snapshot  | Yes      | null                          |
+-----------+----------+-------------------------------+
| name      |          | The name of the cube to fetch |
+-----------+----------+-------------------------------+




GetSnapshotVolatilitySurface


............................
GetSnapshotVolatilitySurface
............................


Fetches a volatility surface values from a market data values object.



+-----------+----------+---------------------------------------------------------------------+
| Parameter | Required | Description                                                         |
+===========+==========+=====================================================================+
| snapshot  | Yes      | null                                                                |
+-----------+----------+---------------------------------------------------------------------+
| name      |          | The surface name to fetch, omit to fetch a list of all values names |
+-----------+----------+---------------------------------------------------------------------+




GetSnapshotYieldCurve


.....................
GetSnapshotYieldCurve
.....................


Fetches a yield curve values from a market data values.



+-----------+----------+-------------------------------------------------------------------------------------+
| Parameter | Required | Description                                                                         |
+===========+==========+=====================================================================================+
| snapshot  | Yes      | null                                                                                |
+-----------+----------+-------------------------------------------------------------------------------------+
| name      |          | The name of the yield curve to fetch, omit to fetch a list of available curve names |
+-----------+----------+-------------------------------------------------------------------------------------+




GetSnapshotYieldCurveValuationTime


..................................
GetSnapshotYieldCurveValuationTime
..................................


Returns the valuation time from a market data snapshot yield curve.



+--------------------+----------+---------------------------------------------+
| Parameter          | Required | Description                                 |
+====================+==========+=============================================+
| snapshotYieldCurve | Yes      | A market data snapshot yield curve to query |
+--------------------+----------+---------------------------------------------+




SetSnapshotBasisViewName


........................
SetSnapshotBasisViewName
........................


Updates the view name the snapshot was originally based on, returning the updated snapshot.



+-----------+----------+------------------------------------------+
| Parameter | Required | Description                              |
+===========+==========+==========================================+
| snapshot  | Yes      | The snapshot to update                   |
+-----------+----------+------------------------------------------+
| name      | Yes      | The new basis view name for the snapshot |
+-----------+----------+------------------------------------------+




SetSnapshotGlobalValue


......................
SetSnapshotGlobalValue
......................


Updates a global value within a market data values, returning the updated values.



+---------------+----------+-------------------------------------------------------------------------------------------------------------------+
| Parameter     | Required | Description                                                                                                       |
+===============+==========+===================================================================================================================+
| snapshot      | Yes      | null                                                                                                              |
+---------------+----------+-------------------------------------------------------------------------------------------------------------------+
| valueName     | Yes      | The name of the value to add/update/remove                                                                        |
+---------------+----------+-------------------------------------------------------------------------------------------------------------------+
| identifier    | Yes      | The target identifier of the value to add/update/remove                                                           |
+---------------+----------+-------------------------------------------------------------------------------------------------------------------+
| overrideValue |          | The new "override" market value, omit both this and the original value to remove from the snapshot                |
+---------------+----------+-------------------------------------------------------------------------------------------------------------------+
| marketValue   |          | The new "original" market value, may be omitted if an override value is specified to leave the original unchanged |
+---------------+----------+-------------------------------------------------------------------------------------------------------------------+
| type          |          | The type of the target identifier, e.g. SECURITY or PRIMITIVE (default is SECURITY)                               |
+---------------+----------+-------------------------------------------------------------------------------------------------------------------+




SetSnapshotName


...............
SetSnapshotName
...............


Updates the name of a snapshot, returning the updated snapshot.



+-----------+----------+-------------------------------+
| Parameter | Required | Description                   |
+===========+==========+===============================+
| snapshot  | Yes      | The snapshot to update        |
+-----------+----------+-------------------------------+
| name      | Yes      | The new name for the snapshot |
+-----------+----------+-------------------------------+




SetSnapshotVolatilityCube


.........................
SetSnapshotVolatilityCube
.........................


Updates a volatility cube values within a market data values object, returning the updated object.



+-----------+----------+------------------------------------------------------------------------+
| Parameter | Required | Description                                                            |
+===========+==========+========================================================================+
| snapshot  | Yes      | null                                                                   |
+-----------+----------+------------------------------------------------------------------------+
| name      | Yes      | The name of the cube to add/remove/update                              |
+-----------+----------+------------------------------------------------------------------------+
| cube      |          | The volatility cube values, or NULL to remove the cube from the values |
+-----------+----------+------------------------------------------------------------------------+




SetSnapshotVolatilitySurface


............................
SetSnapshotVolatilitySurface
............................


Updates a volatility surface values within a market data values object, returning the updated object.



+-----------+----------+------------------------------------------------------------------------------+
| Parameter | Required | Description                                                                  |
+===========+==========+==============================================================================+
| snapshot  | Yes      | null                                                                         |
+-----------+----------+------------------------------------------------------------------------------+
| name      | Yes      | The name of the surface to add/remove/update                                 |
+-----------+----------+------------------------------------------------------------------------------+
| surface   |          | The volatility surface values, or NULL to remove the surface from the values |
+-----------+----------+------------------------------------------------------------------------------+




SetSnapshotYieldCurve


.....................
SetSnapshotYieldCurve
.....................


Updates a yield curve values within a market data values object, returning the updated object.



+------------+----------+---------------------------------------------------------------------+
| Parameter  | Required | Description                                                         |
+============+==========+=====================================================================+
| snapshot   | Yes      | null                                                                |
+------------+----------+---------------------------------------------------------------------+
| name       | Yes      | The name of the yield curve to add/remove/update                    |
+------------+----------+---------------------------------------------------------------------+
| yieldCurve |          | The yield curve values, or NULL to remove the curve from the values |
+------------+----------+---------------------------------------------------------------------+




SetSnapshotYieldCurveValuationTime


..................................
SetSnapshotYieldCurveValuationTime
..................................


Updates the valuation time of a market data snapshot yield curve. The original object is unchanged - a new object is returned with the updated value.



+--------------------+----------+----------------------------------------------+
| Parameter          | Required | Description                                  |
+====================+==========+==============================================+
| snapshotYieldCurve | Yes      | A market data snapshot yield curve to update |
+--------------------+----------+----------------------------------------------+
| valuationTime      |          | The valuation time                           |
+--------------------+----------+----------------------------------------------+




Snapshot


........
Snapshot
........


Defines a market data snapshot.

This function takes no parameters.


Snapshots


.........
Snapshots
.........


Returns the set of current market data snapshots.



+-----------+----------+---------------------------------------------------------------+
| Parameter | Required | Description                                                   |
+===========+==========+===============================================================+
| name      |          | Search string to match snapshots by name, use * as a wildcard |
+-----------+----------+---------------------------------------------------------------+




SnapshotVersions


................
SnapshotVersions
................


Returns the available versions of a snapshot.



+------------+----------+-------------+
| Parameter  | Required | Description |
+============+==========+=============+
| snapshot   | Yes      | null        |
+------------+----------+-------------+
| correction |          | null        |
+------------+----------+-------------+




SnapshotVolatilityCube


......................
SnapshotVolatilityCube
......................


Defines a market data snapshot volatility cube.

This function takes no parameters.


SnapshotVolatilitySurface


.........................
SnapshotVolatilitySurface
.........................


Defines a market data snapshot volatility surface.

This function takes no parameters.


SnapshotYieldCurve


..................
SnapshotYieldCurve
..................


Defines a market data snapshot yield curve.

This function takes no parameters.


StoreSnapshot


.............
StoreSnapshot
.............


Writes a values to the market data master database.



+------------+----------+------------------------------------------------------------------------------------+
| Parameter  | Required | Description                                                                        |
+============+==========+====================================================================================+
| snapshot   | Yes      | null                                                                               |
+------------+----------+------------------------------------------------------------------------------------+
| identifier |          | The unique identifier of the values to update, omit to write a new values instance |
+------------+----------+------------------------------------------------------------------------------------+
| master     |          | The master database to write to, omit for the session default                      |
+------------+----------+------------------------------------------------------------------------------------+



This function does not return a value.
