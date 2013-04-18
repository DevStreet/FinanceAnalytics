title: OpenGamma Tools for Microsoft Excel
shortcut: DOC:OpenGamma Tools for Microsoft Excel
---
The OpenGamma Tools for Microsoft Excel provide a large number of functions for use in crafting sheets, and for use programmatically such as in NO LINK FOUND FOR 'Calling OpenGamma Functions From VBA', exposing a wide range of platform functionality.

..............
Function Types
..............


These functions are broadly defined into three categories:


*  functions which pull data from the OpenGamma Platform;&nbsp;


*  functions which build or manipulate structures in the Excel worksheet area; and&nbsp;


*  functions which push data into the OpenGamma Platform.


Many functions are available which pull data from other components of the OpenGamma Platform installation. These can pull static or reference data (like Security definitions and Time Series) or pull streaming data (such as Market Data or live results from a View Processor). Where these functions are capable of operating in a streaming fashion, they are indicated in the reference table.

For performance purposes, rich data structures (like Time Series, Security definitions, matrices or maps) are not extracted directly into individual cells in the OpenGamma Excel Integration Module. Rather, they live in a memory heap backing the Excel sheet in use, and OpenGamma tracks their lifecycle and reclaims memory as they are used and disappear. There is a category of functions which exist to produce these structures, but also manipulate them (for example, extracting individual values from a map or matrix).

Finally, there are functions which publish data back into the rest of the OpenGamma Platform. These functions take data (which may be a single datum or a rich data structure) and publish them back into the appropriate other areas of the system. Examples of these are functions which publish a hypothetical Security (for pre-trade analysis) or perform a Market Data override (to hold a particular input datum constant during a calculation).

..................
Function Reference
..................


In custom installations, it is possible to preface all functions with a prefix to avoid namespace collisions with other functions (for example, prepending all function names with `og`). For brevity, these have been removed from the online function reference.


*  `All Excel Functions </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Tools-for-Microsoft-Excel/All-Excel-Functions/index.rst>`_ 

