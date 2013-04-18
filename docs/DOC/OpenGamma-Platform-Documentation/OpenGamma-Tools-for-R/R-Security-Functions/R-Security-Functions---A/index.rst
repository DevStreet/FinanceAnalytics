title: R Security Functions - A
shortcut: DOC:R Security Functions - A
---
AgricultureFutureSecurity

.........................
AgricultureFutureSecurity
.........................


Defines an agriculture future security.



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




AmericanExerciseType

....................
AmericanExerciseType
....................


Returns an object representing an American option exercise type.

This function takes no parameters.


AsianExerciseType

.................
AsianExerciseType
.................


Returns an object representing an Asian option exercise type.

This function takes no parameters.


AssetOrNothingPayoffStyle

.........................
AssetOrNothingPayoffStyle
.........................


Returns an object representing an 'asset or nothing' option payoff style.

This function takes no parameters.


AsymmetricPoweredPayoffStyle

............................
AsymmetricPoweredPayoffStyle
............................


Returns an object representing an 'asymmetric powered' option payoff style.



+-----------+----------+-------------+
| Parameter | Required | Description |
+===========+==========+=============+
| power     | Yes      | The power   |
+-----------+----------+-------------+




ExpandAgricultureFutureSecurity

...............................
ExpandAgricultureFutureSecurity
...............................


Expand the contents of an agriculture future security.



+---------------------------+----------+-----------------------------------------+
| Parameter                 | Required | Description                             |
+===========================+==========+=========================================+
| agricultureFutureSecurity | Yes      | An agriculture future security to query |
+---------------------------+----------+-----------------------------------------+



