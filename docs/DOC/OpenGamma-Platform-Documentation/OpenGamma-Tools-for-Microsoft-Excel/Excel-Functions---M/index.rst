title: Excel Functions - M
shortcut: DOC:Excel Functions - M
---
MarketDataOverride

..................
MarketDataOverride
..................


Creates a market data override configuration item.



+------------------+---------------------------------------------------------------------------------------------------------------------------+
| Parameter        | Description                                                                                                               |
+==================+===========================================================================================================================+
| value            | The value to apply, or null for no value (missing market data)                                                            |
+------------------+---------------------------------------------------------------------------------------------------------------------------+
| valueRequirement | The value requirement identifying the market data line, omit if valueName and identifier are specified                    |
+------------------+---------------------------------------------------------------------------------------------------------------------------+
| valueName        | The value name. The value requirement must be omitted and an identifier also supplied                                     |
+------------------+---------------------------------------------------------------------------------------------------------------------------+
| identifier       | An identifier of the item the market data line is for. The value requirement must be omitted and a valueName specified    |
+------------------+---------------------------------------------------------------------------------------------------------------------------+
| type             | The computation target type that the identifier is a unique id for. If omitted, the identifier must be a weak external id |
+------------------+---------------------------------------------------------------------------------------------------------------------------+
| operation        | Optional operation to adjust the underlying market data with the given value. If omitted, the given value is used as-is   |
+------------------+---------------------------------------------------------------------------------------------------------------------------+



MarketDataRequirementNames

..........................
MarketDataRequirementNames
..........................


Returns the set of standard Market Data Requirement Names defined within the system. Note that the Market Data Requirements available from the current live data provider may differ.

This function takes no parameters.

This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

MatchPortfolioRows

..................
MatchPortfolioRows
..................


Fetches the row indices of nodes and positions that match the user expression.



+-----------+---------------------------------+
| Parameter | Description                     |
+===========+=================================+
| portfolio | The identifier of the portfolio |
+-----------+---------------------------------+
| filter    | The filter expression           |
+-----------+---------------------------------+



Rows in the flattened portfolio are selected by evaluating the filter expression.

~~~~~~~~~~~~~~~~~~~~~
Comparison Operations
~~~~~~~~~~~~~~~~~~~~~




+--------------+-----------------------------------------------------------+
| Operator     | Description                                               |
+==============+===========================================================+
|  _a_`<`_b_   | Tests if the value of _a_ is less than _b_                |
+--------------+-----------------------------------------------------------+
|  _a_`<=`_b_  | Tests if the value of _a_ is less than or equal to _b_    |
+--------------+-----------------------------------------------------------+
|  _a_`>`_b_   | Tests if the value of _a_ is greater than _b_             |
+--------------+-----------------------------------------------------------+
|  _a_`>=`_b_  | Tests if the value of _a_ is greater than or equal to _b_ |
+--------------+-----------------------------------------------------------+
|  _a_`=`_b_   | Tests if the value of _a_ is equal to _b_                 |
+--------------+-----------------------------------------------------------+
|  _a_`<>`_b_  | Tests if the value of _a_ is not equal to _b_             |
+--------------+-----------------------------------------------------------+



Numbers are compared using the natural ordering. Strings are compared alphabetically and case-sensitively.

~~~~~~~~~~~~~~~~~~
Logical Operations
~~~~~~~~~~~~~~~~~~




+---------------+------------------------------------+
| Operator      | Description                        |
+===============+====================================+
|  `Not` _a_    | Logical inverse of _a_             |
+---------------+------------------------------------+
|  _a_`And`_b_  | True if _a_ and _b_ are both true  |
+---------------+------------------------------------+
|  _a_`Or`_b_   | True if either _a_ or _b_ are true |
+---------------+------------------------------------+



~~~~~~~~~~~~~~~~~~~
Position Properties
~~~~~~~~~~~~~~~~~~~




+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Symbol               | Description                                                                                                                                                                                                            |
+======================+========================================================================================================================================================================================================================+
|  `Quantity`          | The total quantity of the position                                                                                                                                                                                     |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| _name_               | The value of the named attribute on the position. If no attribute is defined, a field on the associated security. If no matching field is defined on the security, the named property from one of the component trades |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Attribute`._name_  | The value of the named attribute on the position                                                                                                                                                                       |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Trade`._name_      | The value of a the named property on one of the component trades                                                                                                                                                       |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Security`._name_   | The value of the named property from the associated security                                                                                                                                                           |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



~~~~~~~~~~~~~~~~
Trade Properties
~~~~~~~~~~~~~~~~




+----------------------+-----------------------------------------------+
| Symbol               | Description                                   |
+======================+===============================================+
|  `Counterparty`      | The counterparty                              |
+----------------------+-----------------------------------------------+
|  `Premium`           | The premium                                   |
+----------------------+-----------------------------------------------+
|  `PremiumCurrency`   | The premium currency                          |
+----------------------+-----------------------------------------------+
|  `PremiumDate`       | The premium date                              |
+----------------------+-----------------------------------------------+
|  `PremiumTime`       | The premium time                              |
+----------------------+-----------------------------------------------+
| _name_               | The value of the named attribute on the trade |
+----------------------+-----------------------------------------------+
|  `Attribute`._name_  | The value of the named attribute on the trade |
+----------------------+-----------------------------------------------+


~~~~~~~~~~~~~~~~~~~
Security Properties
~~~~~~~~~~~~~~~~~~~




+----------+-------------------------------------------+
| Symbol   | Description                               |
+==========+===========================================+
|  `Name`  | The name of the security                  |
+----------+-------------------------------------------+
|  `Type`  | The top-level asset class of the security |
+----------+-------------------------------------------+


~~~~~~~~~~~~~~~~~~~~~~~~~
Portfolio Node Properties
~~~~~~~~~~~~~~~~~~~~~~~~~




+---------------+----------------------------------------------------------------------------------+
| Symbol        | Description                                                                      |
+===============+==================================================================================+
|  `Depth`      | The depth of the node in the hierarchy - the root node in a portfolio is depth 1 |
+---------------+----------------------------------------------------------------------------------+
|  `Name`       | The name of the node                                                             |
+---------------+----------------------------------------------------------------------------------+
|  `Positions`  | The number of positions immediately underneath this node                         |
+---------------+----------------------------------------------------------------------------------+
|  `Nodes`      | The number of nodes immediately underneath this node                             |
+---------------+----------------------------------------------------------------------------------+




~~~~~~~~~~~~~~~~~~~~~
Additional Properties
~~~~~~~~~~~~~~~~~~~~~




+----------------+--------------------------------------------------------------+
| Symbol         | Description                                                  |
+================+==============================================================+
|  `isNode`      | True if the current row is a portfolio node, false otherwise |
+----------------+--------------------------------------------------------------+
|  `isPosition`  | True if the current row is a position, false otherwise       |
+----------------+--------------------------------------------------------------+



The portfolio node properties can also be referenced with a `Node.` prefix. If the current row is a position, the immediate parent node can be referenced by `Node.`. For example, `isPosition And Node.Depth >= 3` would get all positions that are underneath nodes at at least depth 3.

This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

MatrixValues

............
MatrixValues
............


Retrieves the values from a matrix.



+-----------+-------------------+
| Parameter | Description       |
+===========+===================+
| matrix    | The matrix object |
+-----------+-------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

MetalFutureSecurity

...................
MetalFutureSecurity
...................


Defines a metal future security. The new security is added to the Security Master and an identifier to it returned.



+--------------------+-------------------------------------------+
| Parameter          | Description                               |
+====================+===========================================+
| name               | The display name or label of the security |
+--------------------+-------------------------------------------+
| expiry             | The expiry date                           |
+--------------------+-------------------------------------------+
| tradingExchange    | The trading exchange                      |
+--------------------+-------------------------------------------+
| settlementExchange | The settlement exchange                   |
+--------------------+-------------------------------------------+
| currency           | The currency                              |
+--------------------+-------------------------------------------+
| unitAmount         | The unit amount                           |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



MetalFutureSecurityObject

.........................
MetalFutureSecurityObject
.........................


Defines a metal future security.



+--------------------+-------------------------------------------+
| Parameter          | Description                               |
+====================+===========================================+
| name               | The display name or label of the security |
+--------------------+-------------------------------------------+
| expiry             | The expiry date                           |
+--------------------+-------------------------------------------+
| tradingExchange    | The trading exchange                      |
+--------------------+-------------------------------------------+
| settlementExchange | The settlement exchange                   |
+--------------------+-------------------------------------------+
| currency           | The currency                              |
+--------------------+-------------------------------------------+
| unitAmount         | The unit amount                           |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



MetalFutureSecuritySetUnderlyingId

..................................
MetalFutureSecuritySetUnderlyingId
..................................


Updates the underlying identifier  of a metal future security. The original object is unchanged - a new object is returned with the updated value.



+---------------------+-----------------------------------+
| Parameter           | Description                       |
+=====================+===================================+
| metalFutureSecurity | A metal future security to update |
+---------------------+-----------------------------------+
| underlyingId        | The underlying identifier         |
+---------------------+-----------------------------------+



MetalFutureSecurityUnderlyingId

...............................
MetalFutureSecurityUnderlyingId
...............................


Returns the underlying identifier  from a metal future security.



+---------------------+----------------------------------+
| Parameter           | Description                      |
+=====================+==================================+
| metalFutureSecurity | A metal future security to query |
+---------------------+----------------------------------+



