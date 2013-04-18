title: R View Functions - I
shortcut: DOC:R View Functions - I
---
Interop.ViewClient

..................
Interop.ViewClient
..................


Converts a transport representation of the data to an R object instance.



+-----------+----------+----------------------+
| Parameter | Required | Description          |
+===========+==========+======================+
| data      | Yes      | The transport object |
+-----------+----------+----------------------+



The 'Interop' functions should seldom need to be called directly. They are called by the core library when a Data object returned from the Java components includes additional metadata describing an R type to convert it to. When adding a top-level data type this mechanism can be more efficient than returning the Fudge encoding to R.

