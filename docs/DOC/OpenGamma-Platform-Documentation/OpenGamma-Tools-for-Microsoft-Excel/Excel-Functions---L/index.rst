title: Excel Functions - L
shortcut: DOC:Excel Functions - L
---
LiveData

........
LiveData
........


Subscribes to live market data.



+-----------+-------------------------------------------------------------+
| Parameter | Description                                                 |
+===========+=============================================================+
| id        | The identifier of the entity whose market value is required |
+-----------+-------------------------------------------------------------+



This function is capable of live operation. If automatic sheet calculation is enabled it may return new values pushed from the OpenGamma Platform even if the input parameters have not changed.

This function is available on a worksheet only and cannot be used from Visual Basic.

LivePrimitiveDataOverride

.........................
LivePrimitiveDataOverride
.........................


Creates a live data override for a specific live data value.



+------------+--------------------------------------------------------------------+
| Parameter  | Description                                                        |
+============+====================================================================+
| target     | The unique identifier of the target to which the live data applies |
+------------+--------------------------------------------------------------------+
| value_name | The name of the live data value to override                        |
+------------+--------------------------------------------------------------------+
| value      | The override value to set                                          |
+------------+--------------------------------------------------------------------+



LiveSecurityDataOverride

........................
LiveSecurityDataOverride
........................


Creates a live data override for a specific live data value.



+------------+--------------------------------------------------------------------+
| Parameter  | Description                                                        |
+============+====================================================================+
| target     | The unique identifier of the target to which the live data applies |
+------------+--------------------------------------------------------------------+
| value_name | The name of the live data value to override                        |
+------------+--------------------------------------------------------------------+
| value      | The override value to set                                          |
+------------+--------------------------------------------------------------------+



