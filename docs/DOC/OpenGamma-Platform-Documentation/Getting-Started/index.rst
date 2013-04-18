title: Getting Started
shortcut: DOC:Getting Started
---
This page tells you how to get started with an OpenGamma installation for developers. This assumes that you have `downloaded <http://developers.opengamma.com/>`_  the binary distribution or alternatively that the open source engine has already been built, and that the build is located in **OpenGamma/**.  You will also need to install the `Java JDK or JRE <http://java.oracle.com>`_  and `set the JAVA_HOME variable <https://www.google.com/search?q=setting%20JAVA_HOME>`_ .

..................
Bloomberg Examples
..................


If you have a Bloomberg Professional or Bloomberg Anywhere terminal, you can get out-of-the-box integration with the live, reference and historical data available via your Bloomberg terminal.

Before starting the engine, if you want to use a Managed B-PIPE/SAPI instance, then the network address of your Bloomberg terminal needs to be set in the following configuration files.  If you have a Bloomberg Anywhere or Professional Terminal installed locally, you can skip these steps.



.. code::

    OpenGamma/config/toolcontext/bloombergexample-bin.properties
    OpenGamma/config/fullstack/bloombergexample-bin.properties




In each case the following line needs to be updated so that it specifies your server's network location rather than localhost:



.. code::

    opengamma.bloomberg.sapi.host=localhost




To initialise the Bloomberg examples and run the engine in the foreground type:

On Linux:



.. code::

    cd OpenGamma
    scripts/init-og-bloombergexample-db.sh
    scripts/og-bloombergexample.sh debug




On Windows:



.. code::

    cd OpenGamma
    scripts\init-og-bloombergexample-db.bat
    scripts\og-bloombergexample.bat debug




Finally, fire up your browser and navigate to http://localhost:8080. More information about the scripts that are available with this release can be found `Scripts available with the Open Source Distribution </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Scripts-available-with-the-Open-Source-Distribution/index.rst>`_ .

.........................................................
Examples for users without access to a Bloomberg terminal
.........................................................


If you don't have access to a Bloomberg Professional, you can still get a taste of how OpenGamma operates using our synthetic data generator that produces simulated market data for some example portfolios.

To initialise the examples and run the engine in the foreground, type:

On Linux:



.. code::

    cd OpenGamma
    scripts/init-og-examples-db.sh
    scripts/og-examples.sh debug




On Windows:



.. code::

    cd OpenGamma
    scripts\init-og-examples-db.bat
    scripts\og-examples.bat debug




Finally, fire up your browser and navigate to http://localhost:8080. More information about the scripts that are available with this release can be found `Scripts available with the Open Source Distribution </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Scripts-available-with-the-Open-Source-Distribution/index.rst>`_ .

........................
Setting up R Integration
........................


`R <http://www.r-project.org/>`_  is a free software environment for statistical computing and graphics. The default installation excludes the RStats module, which provides the source code to the `OpenGamma Tools for R </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Tools-for-R/index.rst>`_ . Please refer to `Obtaining and building the source code </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Obtaining-and-building-the-source-code/index.rst>`_  for more details.
