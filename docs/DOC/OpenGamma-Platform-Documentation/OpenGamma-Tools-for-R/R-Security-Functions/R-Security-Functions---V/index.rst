title: R Security Functions - V
shortcut: DOC:R Security Functions - V
---
ExpandVarianceSwapNotional

..........................
ExpandVarianceSwapNotional
..........................


Expand the contents of a notional value of the leg of a variance swap.



+----------------------+----------+---------------------------------------------------------+
| Parameter            | Required | Description                                             |
+======================+==========+=========================================================+
| varianceSwapNotional | Yes      | A notional value of the leg of a variance swap to query |
+----------------------+----------+---------------------------------------------------------+




GetVarianceSwapNotionalAmount

.............................
GetVarianceSwapNotionalAmount
.............................


Returns the the notional amount from a notional value of the leg of a variance swap.



+----------------------+----------+---------------------------------------------------------+
| Parameter            | Required | Description                                             |
+======================+==========+=========================================================+
| varianceSwapNotional | Yes      | A notional value of the leg of a variance swap to query |
+----------------------+----------+---------------------------------------------------------+




GetVarianceSwapNotionalCurrency

...............................
GetVarianceSwapNotionalCurrency
...............................


Returns the the notional currency from a notional value of the leg of a variance swap.



+----------------------+----------+---------------------------------------------------------+
| Parameter            | Required | Description                                             |
+======================+==========+=========================================================+
| varianceSwapNotional | Yes      | A notional value of the leg of a variance swap to query |
+----------------------+----------+---------------------------------------------------------+




SetVarianceSwapNotionalAmount

.............................
SetVarianceSwapNotionalAmount
.............................


Updates the the notional amount of a notional value of the leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+----------------------------------------------------------+
| Parameter            | Required | Description                                              |
+======================+==========+==========================================================+
| varianceSwapNotional | Yes      | A notional value of the leg of a variance swap to update |
+----------------------+----------+----------------------------------------------------------+
| amount               | Yes      | The the notional amount                                  |
+----------------------+----------+----------------------------------------------------------+




SetVarianceSwapNotionalCurrency

...............................
SetVarianceSwapNotionalCurrency
...............................


Updates the the notional currency of a notional value of the leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+----------------------------------------------------------+
| Parameter            | Required | Description                                              |
+======================+==========+==========================================================+
| varianceSwapNotional | Yes      | A notional value of the leg of a variance swap to update |
+----------------------+----------+----------------------------------------------------------+
| currency             |          | The the notional currency                                |
+----------------------+----------+----------------------------------------------------------+




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



+-----------+----------+---------------------------+
| Parameter | Required | Description               |
+===========+==========+===========================+
| currency  | Yes      | The the notional currency |
+-----------+----------+---------------------------+
| amount    | Yes      | The the notional amount   |
+-----------+----------+---------------------------+



