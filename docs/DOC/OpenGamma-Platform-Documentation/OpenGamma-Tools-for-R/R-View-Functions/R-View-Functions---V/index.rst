title: R View Functions - V
shortcut: DOC:R View Functions - V
---
calculationDuration.ViewComputationResultModel

..............................................
calculationDuration.ViewComputationResultModel
..............................................


Accesses the calculationDuration field of a ViewComputationResultModel object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




calculationTime.ViewComputationResultModel

..........................................
calculationTime.ViewComputationResultModel
..........................................


Accesses the calculationTime field of a ViewComputationResultModel object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




column.ViewComputationResultModel

.................................
column.ViewComputationResultModel
.................................


Finds a column with a given value specification from a named configuration within a result object. A list is returned with the column values and labels corresponding to the computation target identifiers from each value.



+-----------+----------+-----------------------------------------------------------------------------------------------+
| Parameter | Required | Description                                                                                   |
+===========+==========+===============================================================================================+
| data      | Yes      | The result object to process                                                                  |
+-----------+----------+-----------------------------------------------------------------------------------------------+
| config    | Yes      | The name of the configuration to process                                                      |
+-----------+----------+-----------------------------------------------------------------------------------------------+
| col       | Yes      | The value specification to extract - e.g. as returned from columns.ViewComputationResultModel |
+-----------+----------+-----------------------------------------------------------------------------------------------+




columns.ViewComputationResultModel

..................................
columns.ViewComputationResultModel
..................................


Finds the column names from a data frame that match the named value and can satisfy any constraints on the requirement.



+------------------+----------+-----------------------------------------+
| Parameter        | Required | Description                             |
+==================+==========+=========================================+
| data             | Yes      | The data frame to search the columns of |
+------------------+----------+-----------------------------------------+
| valueRequirement | Yes      | The value requirement string to match   |
+------------------+----------+-----------------------------------------+




FetchViewDefinition

...................
FetchViewDefinition
...................


Fetches a stored view definition from the database.



+-----------+----------+----------------------------------------------+
| Parameter | Required | Description                                  |
+===========+==========+==============================================+
| id        | Yes      | The unique identifier of the view definition |
+-----------+----------+----------------------------------------------+




firstValue.ViewComputationResultModel

.....................................
firstValue.ViewComputationResultModel
.....................................


Returns the first non-NA value from the row. Typically the columns requested are a subset that can satisfy a given value requirement. This will then return the first usable value found. Values appear in multiple columns because a column is created in the data frame for each value name/properties pair. Differences in, for example, the function identifier may mean that there is not a single column containing all of the desired values requested in a view definition.



+-----------+----------+-----------------------------------+
| Parameter | Required | Description                       |
+===========+==========+===================================+
| row       | Yes      | The data frame row                |
+-----------+----------+-----------------------------------+
| columns   | Yes      | Vector of column names to look in |
+-----------+----------+-----------------------------------+




fromFudgeMsg.ViewComputationResultModel

.......................................
fromFudgeMsg.ViewComputationResultModel
.......................................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

GetViewPortfolio

................
GetViewPortfolio
................


Returns the identifier of the portfolio associated with the view definition.



+-----------+----------+----------------------------------------------+
| Parameter | Required | Description                                  |
+===========+==========+==============================================+
| id        | Yes      | The unique identifier of the view definition |
+-----------+----------+----------------------------------------------+




GetViewResult

.............
GetViewResult
.............


Returns the latest result from a view.



+-----------------+----------+------------------------------------------------------------------------------------------------------------+
| Parameter       | Required | Description                                                                                                |
+=================+==========+============================================================================================================+
| viewClient      | Yes      | A view client connected to the executing view                                                              |
+-----------------+----------+------------------------------------------------------------------------------------------------------------+
| waitForResult   |          | Timeout period to wait for the result to appear, 0 to poll, or -1 to wait indefinitely                     |
+-----------------+----------+------------------------------------------------------------------------------------------------------------+
| lastViewCycleId |          | Identifier of the previous view cycle returned, to block until the next result appears from a ticking view |
+-----------------+----------+------------------------------------------------------------------------------------------------------------+




is.ViewClient

.............
is.ViewClient
.............


Tests whether a value is an instance of the ViewClient class.



+-----------+----------+--------------------+
| Parameter | Required | Description        |
+===========+==========+====================+
| x         | Yes      | The object to test |
+-----------+----------+--------------------+




is.ViewComputationResultModel

.............................
is.ViewComputationResultModel
.............................


Tests whether a value is an instance of the ViewComputationResultModel class.



+-----------+----------+--------------------+
| Parameter | Required | Description        |
+===========+==========+====================+
| x         | Yes      | The object to test |
+-----------+----------+--------------------+




liveData.ViewComputationResultModel

...................................
liveData.ViewComputationResultModel
...................................


Accesses the liveData field of a ViewComputationResultModel object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




results.ViewComputationResultModel

..................................
results.ViewComputationResultModel
..................................


Accesses the results field of a ViewComputationResultModel object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




SetViewClientExecutionFlag

..........................
SetViewClientExecutionFlag
..........................


Sets or clears an execution flag on a view client descriptor, the updated descriptor is returned.



+------------+----------+---------------------------------------------------------------------------+
| Parameter  | Required | Description                                                               |
+============+==========+===========================================================================+
| viewClient | Yes      | The view client descriptor to update                                      |
+------------+----------+---------------------------------------------------------------------------+
| flag       | Yes      | The flag to add or remove                                                 |
+------------+----------+---------------------------------------------------------------------------+
| include    |          | Omit to set the flag, set to FALSE to remove the flag from the descriptor |
+------------+----------+---------------------------------------------------------------------------+




StoreViewDefinition

...................
StoreViewDefinition
...................


Writes a view definition to the repository.



+----------------+----------+------------------------------------------------------------------------------------------------------+
| Parameter      | Required | Description                                                                                          |
+================+==========+======================================================================================================+
| viewDefinition | Yes      | The view definition object to write                                                                  |
+----------------+----------+------------------------------------------------------------------------------------------------------+
| identifier     |          | The unique identifier of the view definition to update, omit to write a new view definition instance |
+----------------+----------+------------------------------------------------------------------------------------------------------+
| master         |          | The master database to write to, omit for the session default                                        |
+----------------+----------+------------------------------------------------------------------------------------------------------+



This function does not return a value.


valuationTime.ViewComputationResultModel

........................................
valuationTime.ViewComputationResultModel
........................................


Accesses the valuationTime field of a ViewComputationResultModel object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




versionCorrection.ViewComputationResultModel

............................................
versionCorrection.ViewComputationResultModel
............................................


Accesses the versionCorrection field of a ViewComputationResultModel object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




ViewCalculationRate

...................
ViewCalculationRate
...................


Creates a view calculation rate configuration item.



+-----------+----------+---------------------------------------------------------------+
| Parameter | Required | Description                                                   |
+===========+==========+===============================================================+
| maxDelta  |          | Maximum time between delta calculation cycles in milliseconds |
+-----------+----------+---------------------------------------------------------------+
| minDelta  |          | Minimum time between delta calculation cycles in milliseconds |
+-----------+----------+---------------------------------------------------------------+
| maxFull   |          | Maximum time between full calculation cycles in milliseconds  |
+-----------+----------+---------------------------------------------------------------+
| minFull   |          | Minimum time between full calculation cycles in milliseconds  |
+-----------+----------+---------------------------------------------------------------+




ViewClient

..........
ViewClient
..........


Creates (or returns an existing) a view client object.



+------------------+----------+--------------------------------------------------------------------------------------------------------------------------+
| Parameter        | Required | Description                                                                                                              |
+==================+==========+==========================================================================================================================+
| viewDescriptor   | Yes      | The view descriptor; can be a view definition identifier or a descriptor that includes view client execution options     |
+------------------+----------+--------------------------------------------------------------------------------------------------------------------------+
| useSharedProcess |          | True to use an existing process for the view that is shared by other view clients, false to create a new private process |
+------------------+----------+--------------------------------------------------------------------------------------------------------------------------+
| clientName       |          | The optional name of the client to allow multiple clients to be created on the same view within a session                |
+------------------+----------+--------------------------------------------------------------------------------------------------------------------------+




viewCycleId.ViewComputationResultModel

......................................
viewCycleId.ViewComputationResultModel
......................................


Accesses the viewCycleId field of a ViewComputationResultModel object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




ViewDefinition

..............
ViewDefinition
..............


Creates a new view definition object on a portfolio.



+--------------+----------+------------------------------------------------------+
| Parameter    | Required | Description                                          |
+==============+==========+======================================================+
| name         | Yes      | The name of the view                                 |
+--------------+----------+------------------------------------------------------+
| portfolio    | Yes      | The identifier of the portfolio the view is based on |
+--------------+----------+------------------------------------------------------+
| requirements |          | The requirements on the portfolio                    |
+--------------+----------+------------------------------------------------------+




ViewId

......
ViewId
......


Returns the identifier of a view definition with a given name.



+-----------+----------+---------------------------------+
| Parameter | Required | Description                     |
+===========+==========+=================================+
| name      | Yes      | The name of the view definition |
+-----------+----------+---------------------------------+




viewProcessId.ViewComputationResultModel

........................................
viewProcessId.ViewComputationResultModel
........................................


Accesses the viewProcessId field of a ViewComputationResultModel object.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| x         | Yes      | The object to query |
+-----------+----------+---------------------+




Views

.....
Views
.....


Returns the set of current view definitions.



+-----------+----------+-------------------------------------------------------------------+
| Parameter | Required | Description                                                       |
+===========+==========+===================================================================+
| name      |          | Optional search string to match only a subset of view definitions |
+-----------+----------+-------------------------------------------------------------------+



