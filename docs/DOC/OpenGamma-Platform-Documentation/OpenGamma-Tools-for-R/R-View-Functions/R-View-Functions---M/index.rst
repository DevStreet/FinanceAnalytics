title: R View Functions - M
shortcut: DOC:R View Functions - M
---
MarketDataOverride

..................
MarketDataOverride
..................


Creates a market data override configuration item.



+------------------+----------+---------------------------------------------------------------------------------------------------------------------------+
| Parameter        | Required | Description                                                                                                               |
+==================+==========+===========================================================================================================================+
| value            |          | The value to apply, or null for no value (missing market data)                                                            |
+------------------+----------+---------------------------------------------------------------------------------------------------------------------------+
| valueRequirement |          | The value requirement identifying the market data line, omit if valueName and identifier are specified                    |
+------------------+----------+---------------------------------------------------------------------------------------------------------------------------+
| valueName        |          | The value name. The value requirement must be omitted and an identifier also supplied                                     |
+------------------+----------+---------------------------------------------------------------------------------------------------------------------------+
| identifier       |          | An identifier of the item the market data line is for. The value requirement must be omitted and a valueName specified    |
+------------------+----------+---------------------------------------------------------------------------------------------------------------------------+
| type             |          | The computation target type that the identifier is a unique id for. If omitted, the identifier must be a weak external id |
+------------------+----------+---------------------------------------------------------------------------------------------------------------------------+
| operation        |          | Optional operation to adjust the underlying market data with the given value. If omitted, the given value is used as-is   |
+------------------+----------+---------------------------------------------------------------------------------------------------------------------------+



