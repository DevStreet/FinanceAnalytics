title: Excel Functions - P
shortcut: DOC:Excel Functions - P
---
ParseNumber

...........
ParseNumber
...........


Converts a string to a number, inverting any formatting rules applied by FormatNumber.



+-----------+---------------------+
| Parameter | Description         |
+===========+=====================+
| value     | The value to decode |
+-----------+---------------------+



This function is available on a worksheet only and cannot be used from Visual Basic.

Portfolio

.........
Portfolio
.........


Creates a new portfolio.



+-----------+---------------------------------------------------------------------------------------------------------------------------------------------------+
| Parameter | Description                                                                                                                                       |
+===========+===================================================================================================================================================+
| name      | The name of the portfolio                                                                                                                         |
+-----------+---------------------------------------------------------------------------------------------------------------------------------------------------+
| positions | The set of positions, a 2-D range with the security identifiers and portfolio node labels in the first column and quantities/trades in the second |
+-----------+---------------------------------------------------------------------------------------------------------------------------------------------------+



PortfolioAggregators

....................
PortfolioAggregators
....................


Fetches the set of all currently defined portfolio aggregators.



+-----------+-------------------------------------------------------------------------------------------------------------------------+
| Parameter | Description                                                                                                             |
+===========+=========================================================================================================================+
| portfolio | The portfolio identifier to find additional aggregation options for, omit for the standard pre-defined aggregators only |
+-----------+-------------------------------------------------------------------------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

PortfolioComponentIdentifiers

.............................
PortfolioComponentIdentifiers
.............................


Retrieves the identifiers of components (nodes, positions, trades, securities) that make up a portfolio.



+----------------------+---------------------------------------------------------------------------------------------------------------------------------------+
| Parameter            | Description                                                                                                                           |
+======================+=======================================================================================================================================+
| portfolioIdentifier  | The identifier of the portfolio                                                                                                       |
+----------------------+---------------------------------------------------------------------------------------------------------------------------------------+
| preferredScheme      | When selecting identifiers from an external identifier bundle, the preferred scheme(s) to use. Omit for the local environment default |
+----------------------+---------------------------------------------------------------------------------------------------------------------------------------+
| includeSecurity      | Whether to include security identifiers, defaults to TRUE                                                                             |
+----------------------+---------------------------------------------------------------------------------------------------------------------------------------+
| includePosition      | Whether to include position identifiers, defaults to FALSE                                                                            |
+----------------------+---------------------------------------------------------------------------------------------------------------------------------------+
| includeTrade         | Whether to include trade identifiers, defaults to FALSE                                                                               |
+----------------------+---------------------------------------------------------------------------------------------------------------------------------------+
| includePortfolioNode | Whether to include node identifiers, defaults to FALSE                                                                                |
+----------------------+---------------------------------------------------------------------------------------------------------------------------------------+



PortfolioId

...........
PortfolioId
...........


Returns the identifier of the most recent best match of a portfolio with a given name.



+-----------+---------------------------+
| Parameter | Description               |
+===========+===========================+
| name      | The name of the portfolio |
+-----------+---------------------------+



PortfolioNode

.............
PortfolioNode
.............


Creates a portfolio node object from one or more positions or other nodes.



+-----------+---------------------------------------------------------+
| Parameter | Description                                             |
+===========+=========================================================+
| name      | The name of the node, omit for none                     |
+-----------+---------------------------------------------------------+
| nodes     | The immediate child nodes to include, omit for none     |
+-----------+---------------------------------------------------------+
| positions | The immediate child positions to include, omit for none |
+-----------+---------------------------------------------------------+



PortfolioRiskFactorsViewDefinition

..................................
PortfolioRiskFactorsViewDefinition
..................................


Generates a view definition of the known risk factors of a portfolio.



+-----------+---------------------------------+
| Parameter | Description                     |
+===========+=================================+
| portfolio | The identifier of the portfolio |
+-----------+---------------------------------+



PortfolioTransform

..................
PortfolioTransform
..................


Applies aggregators and/or filters to a portfolio.



+-------------+---------------------------------------------------+
| Parameter   | Description                                       |
+=============+===================================================+
| portfolio   | The identifier of the portfolio                   |
+-------------+---------------------------------------------------+
| aggregation | The aggregation order, omit for portfolio default |
+-------------+---------------------------------------------------+
| filter      | The portfolio filter expression, omit for none    |
+-------------+---------------------------------------------------+



A portfolio can be filtered by evaluating the filter expression. Each position is evaluated and only included in the resulting portfolio if the expression is TRUE. After filtering the positions, any nodes which do not have any positions remaining underneath them are pruned from the portfolio.

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



PortfolioValueRequirementNames

..............................
PortfolioValueRequirementNames
..............................


Lists the value requirements available on a portfolio.



+-----------+---------------------------------+
| Parameter | Description                     |
+===========+=================================+
| portfolio | The identifier of the portfolio |
+-----------+---------------------------------+



PortfolioVersions

.................
PortfolioVersions
.................


Fetches the available versions of a portfolio.



+----------------+--------------------------------------------------------------------------------------------+
| Parameter      | Description                                                                                |
+================+============================================================================================+
| portfolio      | The identifier of the portfolio to query                                                   |
+----------------+--------------------------------------------------------------------------------------------+
| correctionDate | The 'point of view' date to observe the portfolio at. Omit for the most recent information |
+----------------+--------------------------------------------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

Portfolios

..........
Portfolios
..........


Returns the set of portfolios.



+-----------+-------------------------------------------------------------+
| Parameter | Description                                                 |
+===========+=============================================================+
| name      | Optional search string to match only a subset of portfolios |
+-----------+-------------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

PortfoliosFudge

...............
PortfoliosFudge
...............


Fetches the available portfolios as a Fudge message.

This function takes no parameters.

This function is available from Visual Basic only and cannot be used on a worksheet.

Position

........
Position
........


Creates a position object from a security and quantity.



+-----------+---------------------------------------------------+
| Parameter | Description                                       |
+===========+===================================================+
| security  | Identifier of the security to use in the position |
+-----------+---------------------------------------------------+
| quantity  | Quantity held of the security                     |
+-----------+---------------------------------------------------+



PositionSecurity

................
PositionSecurity
................


Queries the security associated with a position.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| position  | The position to query |
+-----------+-----------------------+



PoweredPayoffStyle

..................
PoweredPayoffStyle
..................


Returns an object representing a 'powered' option payoff style.



+-----------+-------------+
| Parameter | Description |
+===========+=============+
| power     | The power   |
+-----------+-------------+



PrimitiveOutputs

................
PrimitiveOutputs
................


Retrieves the details of primitive terminal outputs available from a view.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| name                      | The name of the view definition           |
+---------------------------+-------------------------------------------+
| calculation_configuration | The name of the calculation configuration |
+---------------------------+-------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

PrimitiveRequirement

....................
PrimitiveRequirement
....................


Creates a primitive value requirement..



+------------+-------------------------------------+
| Parameter  | Description                         |
+============+=====================================+
| target     | The unique identifier of the target |
+------------+-------------------------------------+
| value_name | The name of the required value      |
+------------+-------------------------------------+



