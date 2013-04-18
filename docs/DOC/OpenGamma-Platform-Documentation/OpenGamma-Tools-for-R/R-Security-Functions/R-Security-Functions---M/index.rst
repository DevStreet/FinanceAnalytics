title: R Security Functions - M
shortcut: DOC:R Security Functions - M
---
ExpandMetalFutureSecurity

.........................
ExpandMetalFutureSecurity
.........................


Expand the contents of a metal future security.



+---------------------+----------+----------------------------------+
| Parameter           | Required | Description                      |
+=====================+==========+==================================+
| metalFutureSecurity | Yes      | A metal future security to query |
+---------------------+----------+----------------------------------+




GetMetalFutureSecurityUnderlyingId

..................................
GetMetalFutureSecurityUnderlyingId
..................................


Returns the underlying identifier  from a metal future security.



+---------------------+----------+----------------------------------+
| Parameter           | Required | Description                      |
+=====================+==========+==================================+
| metalFutureSecurity | Yes      | A metal future security to query |
+---------------------+----------+----------------------------------+




MetalFutureSecurity

...................
MetalFutureSecurity
...................


Defines a metal future security.



+--------------------+----------+-------------------------------------------+
| Parameter          | Required | Description                               |
+====================+==========+===========================================+
| name               | Yes      | The display name or label of the security |
+--------------------+----------+-------------------------------------------+
| expiry             | Yes      | The expiry date                           |
+--------------------+----------+-------------------------------------------+
| tradingExchange    | Yes      | The trading exchange                      |
+--------------------+----------+-------------------------------------------+
| settlementExchange | Yes      | The settlement exchange                   |
+--------------------+----------+-------------------------------------------+
| currency           | Yes      | The currency                              |
+--------------------+----------+-------------------------------------------+
| unitAmount         | Yes      | The unit amount                           |
+--------------------+----------+-------------------------------------------+
| contractCategory   | Yes      | The category                              |
+--------------------+----------+-------------------------------------------+




SetMetalFutureSecurityUnderlyingId

..................................
SetMetalFutureSecurityUnderlyingId
..................................


Updates the underlying identifier  of a metal future security. The original object is unchanged - a new object is returned with the updated value.



+---------------------+----------+-----------------------------------+
| Parameter           | Required | Description                       |
+=====================+==========+===================================+
| metalFutureSecurity | Yes      | A metal future security to update |
+---------------------+----------+-----------------------------------+
| underlyingId        |          | The underlying identifier         |
+---------------------+----------+-----------------------------------+



