title: Installation Guide
shortcut: DOC:Installation Guide
---
............
Installation
............


This guide is split into evaluation and production sections. For evaluation, you can either download our dedicated examples binary or work with the source code.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Evaluation using the binary release
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


This evaluation option is the simplest choice for those new to OpenGamma. It provides a working system "out of the box".


*  Download the `OpenGamma examples binary <http://developers.opengamma.com/downloads/>`_ 


*  Unpack it using tar or zip


*  Install the database (only need to do this once)


   *  In Linux/MacOS X



.. code::

    ./scripts/init-example-db.sh




   *  In Windows



.. code::

    scripts\init-example-db.bat




*  Run the system


   *  In Linux/MacOS X



.. code::

    ./scripts/og-examples.sh start




   *  In Windows



.. code::

    scripts\og-examples.bat start




*  Point your browser at `http://localhost:8080 <http://localhost:8080>`_ .  The server may take a minute or so to start up.


These batch files are only designed to work with the binary release. See the next section if you are evaluating the source code.



~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Evaluation using the source code
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


This evaluation option is best for more detailed investigation. It allows you to "get your hands dirty" with the code.

See all the details in `Developing with the OpenGamma Source Code </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/index.rst>`_ , especially the `Setting up IntelliJ IDEA </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Setting-up-IntelliJ-IDEA/index.rst>`_  section.


~~~~~~~~~~
Production
~~~~~~~~~~


This document provides guidelines for performing an installation of all or part of the OpenGamma Platform. Custom applications based on the OpenGamma Platform will need to perform the specific tasks necessary for their use of the different components available in the Platform.



