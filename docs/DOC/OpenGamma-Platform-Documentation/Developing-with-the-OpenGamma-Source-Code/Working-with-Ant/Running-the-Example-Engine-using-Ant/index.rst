title: Running the Example Engine using Ant
shortcut: DOC:Running the Example Engine using Ant
---
This article assumes you're running a *64-bit JVM* with *2GB heap available*.  32-bit JVMs are not supported, due to a lack of memory although you may have some success getting the minimal server running if you change the heap size in `OG-Platform/projects/OG-Examples/build.xml:25`.  We've had success with even just 1GB.  If you use the scripts in `OG-Platform/projects/OG-Examples/scripts/` they will need changing as will the tests ant target.  Experiment at your own risk.


......................
Build all sub projects
......................


See the **Quick Start** section in `Using Ant from the Command Line </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Working-with-Ant/Using-Ant-from-the-Command-Line/index.rst>`_  for how to build the source.

....................................................................
Create the required embedded database schema (Postgres not required)
....................................................................


Provided in the examples project, OG-Platform/projects/OG-Examples is an ant target that creates an empty schema for the *HSQL database*


.. code::

    ant new-hsqldb



Running the ant command creates the database in OG-Examples/install/db/hsqldb/example-db
The file 'db.properties' in the OG-Platform/common directory has all the database connection properties.
To create the PostgreSQL version, follow the instruction in [Deploying all schemas in a single PostgreSQL database]


...................................
Populate the database with examples
...................................


We've put together a set of example securities, portfolios and view definitions so you can try running an engine and actually see some output.  You can do this by running


.. code::

    ant init-database




...............................
Start the Example Engine Server
...............................


To view the console output while the engine server is running, start the server with the following command


.. code::

    ant jetty-debug




wait for few minutes until you see a message


.. code::

    [java] ======== OPENGAMMA STARTED in 47210ms ========



(of course the time taken will vary\!) and open a browser to http://localhost:8080

To run the example server from Eclipse, run `com.opengamma.examples.ExampleComponentServerDev`, by right clicking \-> Run As \-> Java Application

Refer to the `OpenGamma Web Interface </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Web-Interface/index.rst>`_  for further information.

..................
Forking the engine
..................


To fork the engine rather than run it in interactive mode.  Output will go to the `jetty.log` file.


.. code::

    ant jetty




..............................
Stop the Example Engine Server
..............................


To stop a forked server, run


.. code::

    ant jetty-stop



