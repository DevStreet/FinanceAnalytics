title: R Misc Functions - I
shortcut: DOC:R Misc Functions - I
---
fromFudgeMsg.Integer

....................
fromFudgeMsg.Integer
....................


Converts a Fudge message representation to an R object instance.



+-----------+----------+-------------------+
| Parameter | Required | Description       |
+===========+==========+===================+
| msg       | Yes      | The Fudge message |
+-----------+----------+-------------------+



The 'fromFudgeMsg' functions should seldom need to be called directly unless fields are being manually extracted from Fudge representations of analytic or OpenGamma system objects. The accessor methods provided for the class wrappers will automatically apply the conversions and return structured R object. Alternatively, if the Fudge message contains type information the 'toObject.FudgeMsg' function can be used to select the correct conversion function.

Init

....
Init
....


Connects to the OpenGamma installation and imports the OG namespace containing the wrapper functions. The OG package is constructed dynamically using the definitions published by the Java components.

Note that the OG package containing the Java based definitions is installed as a source package. A minimal R installation may not be able to install this and you may see errors from the install.packages function. If there is not a writable directory to install the package into you may see errors and the namespace will not be loaded (or be prompted to create a directory if you are running R interactively).



+-------------+----------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Parameter   | Required | Description                                                                                                                                                                                                                                                                                                                                                          |
+=============+==========+======================================================================================================================================================================================================================================================================================================================================================================+
| cached.stub |          | If set to TRUE will use an existing OG namespace. If set to FALSE will always rebuild it. It is necessary to rebuild it if the Java definitions have changed, but carries a performance penalty when running many short scripts. If omitted, the opengamma.cache.stub option is used - if this is not set the default behavior is to use automatic cache management. |
+-------------+----------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



