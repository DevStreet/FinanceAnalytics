title: Excel Functions - O
shortcut: DOC:Excel Functions - O
---
OpenGammaAbout

..............
OpenGammaAbout
..............


Returns copyright information, version and diagnostic status of the OpenGamma add-in..

This function takes no parameters.

This function is available on a worksheet only and cannot be used from Visual Basic.

OpenGammaErase

..............
OpenGammaErase
..............


Erases the binary storage area used to hold the complex results (Fudge messages) returned by the OpenGamma platform that are referenced by object identifiers in the worksheet.

This function takes no parameters.

This function is available on a worksheet only and cannot be used from Visual Basic.

OpenGammaFromBase64

...................
OpenGammaFromBase64
...................


Converts a base-64 version of a Fudge encoding of a complex entity to an identifier that can be used in this worksheet and passed to the OpenGamma platform.



+-----------+-----------------------------------+
| Parameter | Description                       |
+===========+===================================+
| data      | The base-64 data string to import |
+-----------+-----------------------------------+



This function is available on a worksheet only and cannot be used from Visual Basic.

OpenGammaObject

...............
OpenGammaObject
...............


Rewrites an OpenGamma object reference for use on a different worksheet from its declared sheet. If no rewrite is necessary the original reference is returned..



+-----------+---------------------------------+
| Parameter | Description                     |
+===========+=================================+
| object    | The object reference to rewrite |
+-----------+---------------------------------+



This function is available on a worksheet only and cannot be used from Visual Basic.

OpenGammaToBase64

.................
OpenGammaToBase64
.................


Returns a base-64 version of a Fudge encoded complex entity referenced by an object identifier.



+------------+---------------------------------+
| Parameter  | Description                     |
+============+=================================+
| identifier | The object identifier to export |
+------------+---------------------------------+



This function is available on a worksheet only and cannot be used from Visual Basic.

