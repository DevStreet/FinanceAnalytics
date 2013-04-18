title: R Misc Functions - E
shortcut: DOC:R Misc Functions - E
---
as.ExternalRef

..............
as.ExternalRef
..............


Returns an externalptr that will call a procedure in the Java stack when it falls out of scope. The original value can be retrieved from the reference using the from.ExternalRef function.



+----------------+----------+--------------------------------------------------------------+
| Parameter      | Required | Description                                                  |
+================+==========+==============================================================+
| value          | Yes      | Value to form the reference from                             |
+----------------+----------+--------------------------------------------------------------+
| destructorName | Yes      | Name of the Java destructor procedure to call when finalized |
+----------------+----------+--------------------------------------------------------------+




from.ExternalRef

................
from.ExternalRef
................


Returns the original value used to create the tracked reference with as.ExternalRef.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| ref       | Yes      | Tracked reference |
+-----------+----------+-------------------+




object.ExternalRef

..................
object.ExternalRef
..................


Declares a class template that holds a tracked reference and can return it through the toString function.



+-----------+----------+------------------------------+
| Parameter | Required | Description                  |
+===========+==========+==============================+
| className | Yes      | Name of the class to declare |
+-----------+----------+------------------------------+



