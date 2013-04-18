title: Excel Functions - V
shortcut: DOC:Excel Functions - V
---
ValueProperty

.............
ValueProperty
.............


Creates a default property value configuration item.



+---------------+------------------------------------------------------------------------+
| Parameter     | Description                                                            |
+===============+========================================================================+
| name          | The property name                                                      |
+---------------+------------------------------------------------------------------------+
| value         | The property value(s), omit to create a wild-card "any" property       |
+---------------+------------------------------------------------------------------------+
| configuration | The view configurations to apply to, omit for any configuration        |
+---------------+------------------------------------------------------------------------+
| optional      | The property's optional flag, typically omitted for default properties |
+---------------+------------------------------------------------------------------------+



ValueRequirementNames

.....................
ValueRequirementNames
.....................


Returns the set of standard Value Requirement Names defined within the system. Note that the Value Requirements available from the current function repository may differ.

This function takes no parameters.

This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

Values

......
Values
......


Retrieves the values from a map structure corresponding to the given keys.



+-----------+-------------------------------------------------+
| Parameter | Description                                     |
+===========+=================================================+
| map       | The map structure                               |
+-----------+-------------------------------------------------+
| keys      | The range of keys for which values are required |
+-----------+-------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

VanillaPayoffStyle

..................
VanillaPayoffStyle
..................


Returns an object representing a 'vanilla' option payoff style.

This function takes no parameters.

VarianceSwapNotional

....................
VarianceSwapNotional
....................


Defines a notional value of the leg of a variance swap.



+-----------+---------------------------+
| Parameter | Description               |
+===========+===========================+
| currency  | The the notional currency |
+-----------+---------------------------+
| amount    | The the notional amount   |
+-----------+---------------------------+



View

....
View
....


Injects a view definition.



+------------------------+------------------------------------------------------------+
| Parameter              | Description                                                |
+========================+============================================================+
| name                   | The name of the view                                       |
+------------------------+------------------------------------------------------------+
| portfolio_id           | The unique identifier of the view's underlying portfolio   |
+------------------------+------------------------------------------------------------+
| portfolio_requirements | A range containing the portfolio-level output requirements |
+------------------------+------------------------------------------------------------+
| specific_requirements  | A range containing specific output requirements            |
+------------------------+------------------------------------------------------------+
| configuration_items    | A range containing view configuration items                |
+------------------------+------------------------------------------------------------+



ViewCalculationRate

...................
ViewCalculationRate
...................


Creates a view calculation rate configuration item.



+-----------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Parameter | Description                                                                                                                                                                           |
+===========+=======================================================================================================================================================================================+
| maxDelta  | The maximum time, in milliseconds, that can elapse since any previous cycle before a delta cycle will be forced to execute, to ensure a minimum execution rate                        |
+-----------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| minDelta  | The minimum time, in milliseconds, that must elapse since any previous cycle before a delta cycle may be executed, to throttle the overall execution rate                             |
+-----------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| maxFull   | The maximum time, in milliseconds, that can elapse since the previous full cycle before a further full cycle will be forced to execute, to ensure a minimum full cycle execution rate |
+-----------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| minFull   | The minimum time, in milliseconds, that must elapse since the previous full cycle before a further full cycle may be executed, to throttle the full cycle execution rate              |
+-----------+---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



ViewClient

..........
ViewClient
..........


Obtains a view client attached to a view process.



+----------------------+---------------------------------------------------------------------------------------+
| Parameter            | Description                                                                           |
+======================+=======================================================================================+
| viewClientDescriptor | The view client descriptor                                                            |
+----------------------+---------------------------------------------------------------------------------------+
| useSharedProcess     | Indicates whether the client should be attached to a shared process, defaults to TRUE |
+----------------------+---------------------------------------------------------------------------------------+



This function is capable of live operation. If automatic sheet calculation is enabled it may return new values pushed from the OpenGamma Platform even if the input parameters have not changed.

This function is available on a worksheet only and cannot be used from Visual Basic.

ViewClientResultState

.....................
ViewClientResultState
.....................


Subscribes to the results state of a view client.



+--------------+-----------------------------------+
| Parameter    | Description                       |
+==============+===================================+
| viewClientId | The identifier of the view client |
+--------------+-----------------------------------+



This function is capable of live operation. If automatic sheet calculation is enabled it may return new values pushed from the OpenGamma Platform even if the input parameters have not changed.

This function is available on a worksheet only and cannot be used from Visual Basic.

ViewCycleJob

............
ViewCycleJob
............


Produces a sequence of view cycle results.



+-------------------+---------------------------------------------------+
| Parameter         | Description                                       |
+===================+===================================================+
| viewDefinitionId  | The identifier of the view definition             |
+-------------------+---------------------------------------------------+
| executionSequence | The execution sequence defining the cycles to run |
+-------------------+---------------------------------------------------+



This function is capable of live operation. If automatic sheet calculation is enabled it may return new values pushed from the OpenGamma Platform even if the input parameters have not changed.

This function is available on a worksheet only and cannot be used from Visual Basic.

ViewDefinition

..............
ViewDefinition
..............


Creates a new view definition object on a portfolio.



+--------------+------------------------------------------------------+
| Parameter    | Description                                          |
+==============+======================================================+
| name         | The name of the view                                 |
+--------------+------------------------------------------------------+
| portfolio    | The identifier of the portfolio the view is based on |
+--------------+------------------------------------------------------+
| requirements | The requirements on the portfolio                    |
+--------------+------------------------------------------------------+



ViewDepGraphComputedValue

.........................
ViewDepGraphComputedValue
.........................


View a computed value for a given value specification in a dependency graph.



+------------------------+----------------------------------------------+
| Parameter              | Description                                  |
+========================+==============================================+
| depGraphGrid           | No description available                     |
+------------------------+----------------------------------------------+
| depGraphComputedValues | The computed values for the dependency graph |
+------------------------+----------------------------------------------+
| row_index              | The row index                                |
+------------------------+----------------------------------------------+



ViewDepGraphValue

.................
ViewDepGraphValue
.................


Expands a given dependency graph.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| depGraphGrid | No description available |
+--------------+--------------------------+
| row_index    | The row index            |
+--------------+--------------------------+
| columnLabel  | No description available |
+--------------+--------------------------+



ViewId

......
ViewId
......


Returns the identifier of a view definition with a given name.



+-----------+---------------------------------+
| Parameter | Description                     |
+===========+=================================+
| name      | The name of the view definition |
+-----------+---------------------------------+



ViewLatestResult

................
ViewLatestResult
................


Fetches the latest result of a calculating view.



+------------+-----------------------------------------------------------+
| Parameter  | Description                                               |
+============+===========================================================+
| viewClient | Identifier of the view client which will provide the data |
+------------+-----------------------------------------------------------+



This function is available from Visual Basic only and cannot be used on a worksheet.

ViewPortfolio

.............
ViewPortfolio
.............


Gets the portfolio a view is defined on.



+-----------+------------------------+
| Parameter | Description            |
+===========+========================+
| view      | Identifier of the view |
+-----------+------------------------+



ViewPositionValue

.................
ViewPositionValue
.................


Subscribes to a portfolio-specific value from a view.



+-------------------+---------------------------------------------------------------------------+
| Parameter         | Description                                                               |
+===================+===========================================================================+
| viewClientId      | The identifier of the view client which will provide the data             |
+-------------------+---------------------------------------------------------------------------+
| portfolioIndex    | The index of the target within the flattened portfolio                    |
+-------------------+---------------------------------------------------------------------------+
| valueRequirement  | The position-level value requirement                                      |
+-------------------+---------------------------------------------------------------------------+
| notAvailableValue | The text to return if the value is not available, defaults to an error    |
+-------------------+---------------------------------------------------------------------------+
| flattenValue      | Whether to flatten complex objects into a simple value, defaults to FALSE |
+-------------------+---------------------------------------------------------------------------+



This function is capable of live operation. If automatic sheet calculation is enabled it may return new values pushed from the OpenGamma Platform even if the input parameters have not changed.

This function is available on a worksheet only and cannot be used from Visual Basic.

ViewPrimitiveValue

..................
ViewPrimitiveValue
..................


Subscribes to a primitive value from a view.



+-------------------+---------------------------------------------------------------------------+
| Parameter         | Description                                                               |
+===================+===========================================================================+
| viewClientId      | The identifier of the view client which will provide the data             |
+-------------------+---------------------------------------------------------------------------+
| targetId          | The identifier of the target                                              |
+-------------------+---------------------------------------------------------------------------+
| valueRequirement  | The primitive value requirement                                           |
+-------------------+---------------------------------------------------------------------------+
| notAvailableValue | The text to return if the value is not available, defaults to an error    |
+-------------------+---------------------------------------------------------------------------+
| flattenValue      | Whether to flatten complex objects into a simple value, defaults to FALSE |
+-------------------+---------------------------------------------------------------------------+



This function is capable of live operation. If automatic sheet calculation is enabled it may return new values pushed from the OpenGamma Platform even if the input parameters have not changed.

This function is available on a worksheet only and cannot be used from Visual Basic.

ViewRequirements

................
ViewRequirements
................


Gets the value requirements defined for a view.



+-----------+------------------------+
| Parameter | Description            |
+===========+========================+
| view      | Identifier of the view |
+-----------+------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ViewValue

.........
ViewValue
.........


Subscribes to a value from a view.



+-------------------+---------------------------------------------------------------------------+
| Parameter         | Description                                                               |
+===================+===========================================================================+
| viewClientId      | The identifier of the view client which will provide the data             |
+-------------------+---------------------------------------------------------------------------+
| targetType        | The type of the target                                                    |
+-------------------+---------------------------------------------------------------------------+
| targetId          | The identifier of the target                                              |
+-------------------+---------------------------------------------------------------------------+
| valueRequirement  | The specific value requirement                                            |
+-------------------+---------------------------------------------------------------------------+
| notAvailableValue | The text to return if the value is not available, defaults to an error    |
+-------------------+---------------------------------------------------------------------------+
| flattenValue      | Whether to flatten complex objects into a simple value, defaults to FALSE |
+-------------------+---------------------------------------------------------------------------+



This function is capable of live operation. If automatic sheet calculation is enabled it may return new values pushed from the OpenGamma Platform even if the input parameters have not changed.

This function is available on a worksheet only and cannot be used from Visual Basic.

Views

.....
Views
.....


Returns the set of current view definitions.



+-----------+-------------------------------------------------------------------+
| Parameter | Description                                                       |
+===========+===================================================================+
| name      | Optional search string to match only a subset of view definitions |
+-----------+-------------------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

