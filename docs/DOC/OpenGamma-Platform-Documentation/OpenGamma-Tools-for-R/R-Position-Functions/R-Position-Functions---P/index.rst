title: R Position Functions - P
shortcut: DOC:R Position Functions - P
---
ExpandPortfolio

...............
ExpandPortfolio
...............


Expand the contents of a portfolio.



+-----------+----------+----------------------+
| Parameter | Required | Description          |
+===========+==========+======================+
| portfolio | Yes      | A portfolio to query |
+-----------+----------+----------------------+




ExpandPortfolioNode

...................
ExpandPortfolioNode
...................


Expand the contents of a portfolio node.



+---------------+----------+---------------------------+
| Parameter     | Required | Description               |
+===============+==========+===========================+
| portfolioNode | Yes      | A portfolio node to query |
+---------------+----------+---------------------------+




ExpandPosition

..............
ExpandPosition
..............


Expand the contents of a position.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| position  | Yes      | A position to query |
+-----------+----------+---------------------+




FetchPortfolio

..............
FetchPortfolio
..............


Fetches a portfolio from the position master.



+------------+----------+----------------------------------------------------+
| Parameter  | Required | Description                                        |
+============+==========+====================================================+
| identifier | Yes      | The unique identifier of the portfolio to retrieve |
+------------+----------+----------------------------------------------------+




FetchPosition

.............
FetchPosition
.............


Fetches a position from the position master.



+------------+----------+---------------------------------------------------+
| Parameter  | Required | Description                                       |
+============+==========+===================================================+
| identifier | Yes      | The unique identifier of the position to retrieve |
+------------+----------+---------------------------------------------------+




GetPortfolioName

................
GetPortfolioName
................


Returns the display name from a portfolio.



+-----------+----------+----------------------+
| Parameter | Required | Description          |
+===========+==========+======================+
| portfolio | Yes      | A portfolio to query |
+-----------+----------+----------------------+




GetPortfolioNodeChildNodes

..........................
GetPortfolioNodeChildNodes
..........................


Returns the child portfolio nodes from a portfolio node.



+---------------+----------+---------------------------+
| Parameter     | Required | Description               |
+===============+==========+===========================+
| portfolioNode | Yes      | A portfolio node to query |
+---------------+----------+---------------------------+




GetPortfolioNodeName

....................
GetPortfolioNodeName
....................


Returns the display name from a portfolio node.



+---------------+----------+---------------------------+
| Parameter     | Required | Description               |
+===============+==========+===========================+
| portfolioNode | Yes      | A portfolio node to query |
+---------------+----------+---------------------------+




GetPortfolioNodeParentNodeId

............................
GetPortfolioNodeParentNodeId
............................


Returns the parent node unique identifier from a portfolio node.



+---------------+----------+---------------------------+
| Parameter     | Required | Description               |
+===============+==========+===========================+
| portfolioNode | Yes      | A portfolio node to query |
+---------------+----------+---------------------------+




GetPortfolioNodePositions

.........................
GetPortfolioNodePositions
.........................


Returns the immediate child positions from a portfolio node.



+---------------+----------+---------------------------+
| Parameter     | Required | Description               |
+===============+==========+===========================+
| portfolioNode | Yes      | A portfolio node to query |
+---------------+----------+---------------------------+




GetPortfolioNodeUniqueId

........................
GetPortfolioNodeUniqueId
........................


Returns the unique identifier from a portfolio node.



+---------------+----------+---------------------------+
| Parameter     | Required | Description               |
+===============+==========+===========================+
| portfolioNode | Yes      | A portfolio node to query |
+---------------+----------+---------------------------+




GetPortfolioRootNode

....................
GetPortfolioRootNode
....................


Returns the root node from a portfolio.



+-----------+----------+----------------------+
| Parameter | Required | Description          |
+===========+==========+======================+
| portfolio | Yes      | A portfolio to query |
+-----------+----------+----------------------+




GetPositionAttribute

....................
GetPositionAttribute
....................


Queries an attribute on a position.



+-----------+----------+------------------------+
| Parameter | Required | Description            |
+===========+==========+========================+
| position  | Yes      | The position to query  |
+-----------+----------+------------------------+
| attribute | Yes      | The attribute to query |
+-----------+----------+------------------------+




GetPositionAttributes

.....................
GetPositionAttributes
.....................


Returns the aggregation attributes from a position.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| position  | Yes      | A position to query |
+-----------+----------+---------------------+




GetPositionParentNodeId

.......................
GetPositionParentNodeId
.......................


Returns the parent node unique identifier from a position.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| position  | Yes      | A position to query |
+-----------+----------+---------------------+




GetPositionSecurity

...................
GetPositionSecurity
...................


Queries the security associated with a position.



+-----------+----------+-----------------------+
| Parameter | Required | Description           |
+===========+==========+=======================+
| position  | Yes      | The position to query |
+-----------+----------+-----------------------+




GetPositionTrades

.................
GetPositionTrades
.................


Returns the immediate child trades from a position.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| position  | Yes      | A position to query |
+-----------+----------+---------------------+




Portfolio

.........
Portfolio
.........


Creates a portfolio object from a root node.



+-----------+----------+--------------------------------+
| Parameter | Required | Description                    |
+===========+==========+================================+
| name      | Yes      | The name of the new portfolio  |
+-----------+----------+--------------------------------+
| rootNode  | Yes      | The root node of the portfolio |
+-----------+----------+--------------------------------+




PortfolioId

...........
PortfolioId
...........


Returns the identifier of the most recent best match of a portfolio with a given name.



+-----------+----------+---------------------------+
| Parameter | Required | Description               |
+===========+==========+===========================+
| name      | Yes      | The name of the portfolio |
+-----------+----------+---------------------------+




PortfolioNode

.............
PortfolioNode
.............


Creates a portfolio node object from one or more positions or other nodes.



+-----------+----------+---------------------------------------------------------+
| Parameter | Required | Description                                             |
+===========+==========+=========================================================+
| name      |          | The name of the node, omit for none                     |
+-----------+----------+---------------------------------------------------------+
| nodes     |          | The immediate child nodes to include, omit for none     |
+-----------+----------+---------------------------------------------------------+
| positions |          | The immediate child positions to include, omit for none |
+-----------+----------+---------------------------------------------------------+




PortfolioPosition

.................
PortfolioPosition
.................


Creates a position object from a security and quantity.



+-----------+----------+---------------------------------------------------+
| Parameter | Required | Description                                       |
+===========+==========+===================================================+
| security  | Yes      | Identifier of the security to use in the position |
+-----------+----------+---------------------------------------------------+
| quantity  | Yes      | Quantity held of the security                     |
+-----------+----------+---------------------------------------------------+




Portfolios

..........
Portfolios
..........


Returns the set of portfolios.



+-----------+----------+-------------------------------------------------------------+
| Parameter | Required | Description                                                 |
+===========+==========+=============================================================+
| name      |          | Optional search string to match only a subset of portfolios |
+-----------+----------+-------------------------------------------------------------+




SetPositionAttribute

....................
SetPositionAttribute
....................


Sets or removes an attribute on a position, returning the updated position object.



+-----------+----------+----------------------------------------------------------+
| Parameter | Required | Description                                              |
+===========+==========+==========================================================+
| position  | Yes      | The position to update                                   |
+-----------+----------+----------------------------------------------------------+
| attribute | Yes      | The attribute to set, update or remove                   |
+-----------+----------+----------------------------------------------------------+
| value     |          | The new attribute value, or omit to remove the attribute |
+-----------+----------+----------------------------------------------------------+




StorePortfolio

..............
StorePortfolio
..............


Writes a portfolio to the position and portfolio master databases.



+------------+----------+------------------------------------------------------------------------------------------+
| Parameter  | Required | Description                                                                              |
+============+==========+==========================================================================================+
| portfolio  | Yes      | The portfolio object to write                                                            |
+------------+----------+------------------------------------------------------------------------------------------+
| identifier |          | The unique identifier of the portfolio to update, omit to write a new portfolio instance |
+------------+----------+------------------------------------------------------------------------------------------+
| master     |          | The master database to write to, omit for the session default                            |
+------------+----------+------------------------------------------------------------------------------------------+



This function does not return a value.

