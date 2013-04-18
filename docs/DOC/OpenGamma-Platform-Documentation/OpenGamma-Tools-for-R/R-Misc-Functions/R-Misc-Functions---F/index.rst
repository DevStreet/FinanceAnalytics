title: R Misc Functions - F
shortcut: DOC:R Misc Functions - F
---
classNames.FudgeMsg

...................
classNames.FudgeMsg
...................


Returns the fields with ordinal 0. When an object is serialized to a Fudge encoding, class names may be written with field ordinal 0 to specify a given sub-class.



+-----------+----------+------------------------+
| Parameter | Required | Description            |
+===========+==========+========================+
| msg       | Yes      | A Fudge message object |
+-----------+----------+------------------------+




displayName.FudgeMsg

....................
displayName.FudgeMsg
....................


Returns a string that can be displayed to describe the type of the encoded object. The name is constructed from the first class name found. If the class name is delimited by '.' characters, the last fragment is used. If there are no class names, the string "FudgeMsg" is returned.



+-----------+----------+------------------------+
| Parameter | Required | Description            |
+===========+==========+========================+
| msg       | Yes      | A Fudge message object |
+-----------+----------+------------------------+




expand.FudgeMsg

...............
expand.FudgeMsg
...............


Expands a Fudge message into a pure-R representation. If any of the fields are a Fudge message, these are expanded recursively.



+-----------+----------+----------------------------------+
| Parameter | Required | Description                      |
+===========+==========+==================================+
| msg       | Yes      | A Fudge message object to expand |
+-----------+----------+----------------------------------+




fields.FudgeMsg

...............
fields.FudgeMsg
...............


Fetches the fields from a Fudge message.



+-----------+----------+------------------------+
| Parameter | Required | Description            |
+===========+==========+========================+
| x         | Yes      | A Fudge message object |
+-----------+----------+------------------------+




fromFudgeMsg.Float

..................
fromFudgeMsg.Float
..................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

fromFudgeMsg.ForexLocalVolatilityPDEPresentValueResultCollection

................................................................
fromFudgeMsg.ForexLocalVolatilityPDEPresentValueResultCollection
................................................................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

is.FudgeMsg

...........
is.FudgeMsg
...........


Returns true if the object is a Fudge message, false otherwise.



+-----------+----------+--------------------+
| Parameter | Required | Description        |
+===========+==========+====================+
| x         | Yes      | The object to test |
+-----------+----------+--------------------+




mapToDataFrame.FudgeMsg

.......................
mapToDataFrame.FudgeMsg
.......................


Returns a data frame containing the content of a "map" encoded with keys as field ordinal 1 and values as field ordinal 2. The data frame contains two columns; Key and Value containing the key/value pairs. Construction of the data frame will fail if either a key or value cannot be put into a suitable vector. If keys or values contain Fudge sub-messages or primitive arrays that cannot be converted automatically, conversion functions can be applied (e.g. toString).



+-----------+----------+-----------------------------------------------------+
| Parameter | Required | Description                                         |
+===========+==========+=====================================================+
| x         | Yes      | A Fudge message object                              |
+-----------+----------+-----------------------------------------------------+
| keyFun    | Yes      | Function to apply to keys, NULL to use each as-is   |
+-----------+----------+-----------------------------------------------------+
| valueFun  | Yes      | Function to apply to values, NULL to use each as-is |
+-----------+----------+-----------------------------------------------------+




object.FudgeMsg

...............
object.FudgeMsg
...............


Declares a class template that holds a fudge message and can return it through the toFudgeMsg function. Additional functions should be declared on the class so that it can be queried or manipulated in R.



+-----------+----------+------------------------------+
| Parameter | Required | Description                  |
+===========+==========+==============================+
| className | Yes      | Name of the class to declare |
+-----------+----------+------------------------------+




print.FudgeMsg

..............
print.FudgeMsg
..............


Prints out a Fudge message, using the toString of all member fields values except for embedded Fudge messages which are recursively printed with an indent.



+-----------+----------+------------------------+
| Parameter | Required | Description            |
+===========+==========+========================+
| x         | Yes      | A Fudge message object |
+-----------+----------+------------------------+
| ...       | Yes      | Ignored                |
+-----------+----------+------------------------+




toObject.FudgeMsg

.................
toObject.FudgeMsg
.................


Produces an R object based on the Fudge message. If the message contains a class name in field with ordinal 0, a method called fromFudgeMsg.class is used, where class is the value returned by displayName.FudgeMsg. If no such method exists, toString is used to produce a textual description of the Fudge message.



+-----------+----------+------------------------+
| Parameter | Required | Description            |
+===========+==========+========================+
| x         | Yes      | A Fudge message object |
+-----------+----------+------------------------+



