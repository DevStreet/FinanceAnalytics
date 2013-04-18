title: Configuration Overview
shortcut: DOC:Configuration Overview
---
There are really two type of configuration that are available

..............................
Module structure configuration
..............................


This level of configuration would tend to only be done either at installation time or during the installation of new modules.  These are the kinds of tasks that would be expected to be performed by support staff rather than end users.  For more information, see `Component-based Configuration </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/index.rst>`_ .

............................
Runtime system configuration
............................


This refers to the integrated configuration system, and is used to store configurations that are likely to be changed more often, and may be available to end users.  Some of the things currently supported by the configuration system are:

*  View Definitions - which portfolio the view is linked to, what the outputs required are (i.e. the columns and any 'primitive' values like yield curves) and some execution options (e.g execution frequency)


*  Yield Curve Definitions - a high level description of the components of curves


*  Yield Curve Specification Builder Configurations (sometimes referred to slightly incorrectly as Yield Curve Specifications) - descriptions of all possible nodal instruments available to build curves


*  Time Series Meta Data Configurations - descriptions of how to resolve requests for data from the historical time series subsystem


*  Currency Matrix Configurations - contains the required identifiers to look up data for performing cross currency conversions


*  Volatility Surface Specifications - descriptions of how to generate identifiers to retrieve nodal data for constructing volatility surfaces


The shared configuration system is one of the few subsystems that's actually *required* by most components in the system.
Each configuration object is keyed by name and versioned.  The actual configurations themselves are actually stored in an RDBMS as BLOB of data in the Fudge message encoded format.  Currently the objects themselves are editable through the web interface, but because the format they are stored in is binary, the Fudge messages are marshalled to and from an XML representation to allow user editing. Even so, the XML format is quite difficult to read and modify so in the near future, more friendly form-based configuration editors will be available through the web interface.
