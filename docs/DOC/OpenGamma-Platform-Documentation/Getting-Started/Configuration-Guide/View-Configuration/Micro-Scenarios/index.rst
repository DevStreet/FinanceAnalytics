title: Micro Scenarios
shortcut: DOC:Micro Scenarios
---
Column sets (known as calculation configurations in the source code) are isolated sandboxes that can have their parameters/constraints (e.g. use different curves) and their market data varied.

...................
Market Data Control
...................


The simplest case is to specify a global market data manipulation (e.g. \+10%).  This can be done by adding a constraint under the **Default Properties** section of the ViewDefinition editor called 'MARKET*DATA*SHIFT'.  The value of this constraint is an expression controlling how to modify the market data.  At it's simplest, it could be as simple as


.. code::

    x * 0.9



which would perform a 10% downshift.  However, several pseudo variables are available:


+----------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------+
| Name           | Meaning                                                                                                                          | Example                                                                                           |
+================+==================================================================================================================================+===================================================================================================+
|  `x`           | the current value of the market data                                                                                             |  `x * 0.9`                                                                                        |
+----------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------+
|  `security`    | the fully resolved security linked to the identifier used to request the data                                                    |  `if (security.securityType \!= "FX_OPTION") x * 1.1`                                             |
+----------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------+
|  `value`       | the name of the Market value requirement name used to request the data                                                           |  `if (value == "Market_Value") x * 1.1`                                                           |
+----------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------+
|  `externalId`  |  in cases where a security is not resolvable, (e.g. a rate), the externalId is available (e.g. `BLOOMBERG_TICKER~USSW1 Curncy`)  |  `if ((externalId.scheme == "BLOOMBERG_TICKER") && (externalId.value == "USSW1 Curncy)) x * 0.9`  |
+----------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------+
|  `uniqueId`    | sometimes the uniqueId can be useful                                                                                             |                                                                                                   |
+----------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------------------------------------------------------------------------------------+



Complex expressions can be built up to handle multiple possible shifts based, for example on asset class.

There are also some functions available to perform more specific market data manipulations:


+-------------------------+------------------------+-------------------------------------------+------------------------------------------------------------+------------------------------------------------+
| Name                    | Alternative            | Description                               | Example                                                    | Notes                                          |
+=========================+========================+===========================================+============================================================+================================================+
|  `Curve:parallelShift`  |  `parallelShiftCurve`  | parallel shift of all curves              |  `Curve:parallelShift(1.1)`                                | parallel shift curves by \+10%                 |
+-------------------------+------------------------+-------------------------------------------+------------------------------------------------------------+------------------------------------------------+
|  `Curve:pointShift`     |  `pointShiftCurve`     | shift a single point on all curves        |  `Curve:poingShift(0.9, 2.5)`                              | bump the 2.5 year point on the curves by \-10% |
+-------------------------+------------------------+-------------------------------------------+------------------------------------------------------------+------------------------------------------------+
|  `Security:get`         |  `getSecurity`         | fetches the security object               |  `if (getSecurity().securityType == "IR_FUTURE") x * 0.9`  | rather redundant because of security variable  |
+-------------------------+------------------------+-------------------------------------------+------------------------------------------------------------+------------------------------------------------+
|  `FX:isRate`            |  `isRateFX`            | returns true when data item in an FX rate |  `if (isRateFX()) x * 1.1`                                 | shift only FX rates by 10%                     |
+-------------------------+------------------------+-------------------------------------------+------------------------------------------------------------+------------------------------------------------+
|  `FX:multiplier`        |  `multiplierFX`        | returns the rate if it's an FX ratre      |  `if ((isRateFX() && (multiplierFX() > 0.9) x * 1.1`       | only shift FX rates > 0.9 by 10%               |
+-------------------------+------------------------+-------------------------------------------+------------------------------------------------------------+------------------------------------------------+



Volatility surfaces can be parallel shifted by a legacy mechanism by defining a Property/Constraint in the Default Properties section of your column set called **VOLATILITY_SURFACE_SHIFT** and a value to add or delete, so 0.1 = 10%, \-0.2 = \-20% and so on.

We'll be adding more functionality and power to this expression language over time and improving the way these are built in the user interface.
