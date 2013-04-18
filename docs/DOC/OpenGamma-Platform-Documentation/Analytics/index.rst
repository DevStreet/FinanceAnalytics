title: Analytics
shortcut: DOC:Analytics
---
The OpenGamma Platform comes with a completely independently-written analytics library.  It can be used either as part of the OpenGamma Platform, or separately in its own right.  There are no direct dependencies in the analytics library on the rest of the Platform, apart from the OG-Utils project and OG-Maths, when functionality is moved into this new project.

The general architecture of the analytics library is such that it is as divorced as possible from the data - it is the job of the computation engine functions in the OG-Financial project (that typically extend the AbstractFunction class) to marshall the data required by the analytics library, call into the library, and then package the results for the computation engine to use.

Parameters to engine functions are typically controlled via 'constraints' (also known as properties or value properties).  These constraints have sensible defaults, but it's usually a good idea to be as specific as possible when specifying a view.  Hovering over the column headings in the analytics view will show you any constraints applied to an existing column, and column definitions are edited using the Configuration tab in the Web UI and creating or editing the appropriate  'ViewDefinition'.

..........................
Available analytic outputs
..........................


More specific documentation, containing all the constraints/properties/parameters (with example values) is available below.

`OpenGamma Analytics Value Requirements </confluence/DOC/OpenGamma-Platform-Documentation/Analytics/OpenGamma-Analytics-Value-Requirements/index.rst>`_ 




`Quantitative Documentation </confluence/DOC/OpenGamma-Platform-Documentation/Analytics/Quantitative-Documentation/index.rst>`_ 

Various working papers that outline some of the modelling and pricing methodologies used in the OpenGamma analytics library.



`Quantitative Research </confluence/DOC/OpenGamma-Platform-Documentation/Analytics/Quantitative-Research/index.rst>`_ 

Various quantitative research papers written by the OpenGamma quantitative development team.
