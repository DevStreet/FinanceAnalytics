title: Obtaining and building the source code
shortcut: DOC:Obtaining and building the source code
---
.....................................
Options for obtaining the source code
.....................................



*  Download the source tarball or zip file from http://developers.opengamma.com.  This has the advantage of being slightly easier to initially set up as you don't require git to be installed or the initial 'ant init' or 'ant clone-or-pull' to be run.  The normal download also includes the dependencies, which will speed up building.


*  Fork a clone of the `OpenGamma git repositories on github <https://github.com/OpenGamma>`_ .  This has the advantage that it is easier to keep up to date. See below for more details.


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Prerequisites for either case
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



*  `Install the latest Java JDK <http://java.oracle.com>`_  if you don't have it installed. The OpenGamma Platform requires a 1.6.0 JDK for development, and a 1.6.0 JRE for runtime.


*  `Install Apache Ant <http://ant.apache.org/manual/install.html>`_  if you don't have it installed. `On Windows, installing Ant is more complex </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Setting-up-an-Eclipse-Workspace/On-Windows,-installing-Ant-is-more-complex/index.rst>`_ 


For a full overview of the prerequisites for developing with the OpenGamma source code using Eclipse, watch the [Prerequisites screencast|^OpenGamma_Prerequisites.mp4].&nbsp;It covers installing Java, Ant, Git, and Eclipse.




~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Download source code as tarball or zip file
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


If you want the open source release, just go to http://developers.opengamma.com and download the source tarball (.tar.gz) or zip file.  If you're a commercial customer, make sure you log into the http://developer.opengamma.com site using the supplied username and password and you'll be able to get a download including your subscribed commercial components.

Choose either `opengamma-public-platform-nodep-src-1.1.0` or `opengamma-public-platform-src-1.1.0` depending on whether you want to download all the dependencies in one go with the source (recommended) or download them only on-demand at build time.  One you have your package, unzip/untar it using your favorite extraction tool (we'd recommend 7Zip for Windows or the standard command line `tar` for Linux/Unix/Mac).  For example on Linux/Unix/Mac you'd type


.. code::

    tar zxf opengamma-public-platform-src-1.1.0.tar.gz



Then change directory into the `OG-Platform/` directory.

You can now move on to either the `Setting up an Eclipse Workspace </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Setting-up-an-Eclipse-Workspace/index.rst>`_  page or the `Using Ant from the Command Line </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Working-with-Ant/Using-Ant-from-the-Command-Line/index.rst>`_  page, or both.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Obtain the source code from GitHub
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


``````````````````````````````````````````````````````
Prerequisites for using the git repositories on GitHub
``````````````````````````````````````````````````````


`Install Git <http://git-scm.com/>`_  if you don't have it installed (we'd recommend the msysgit client for Windows, default settings although we'd recommend allowing integration with the Windows command line, which is not selected by default.
Although you don't actually need one unless you're contributing, you might consider [Setting up a github account|http://help.github.com] if you don't already have one.


``````````````````````
Cloning the repository
``````````````````````


To clone a copy of the OG-Platform git repository, run


.. code::

    git clone git://github.com/OpenGamma/OG-Platform.git



If you have firewall problems, try:


.. code::

    git clone http://github.com/OpenGamma/OG-Platform.git




^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Quick start for Open Source users and evaluators
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


To build the platform and install it in the **OpenGamma/** folder in OG-Platform type:


.. code::

    cd OG-Platform
    ant install



To create and populate the example database and start the example engine in the foreground, try:

On Linux:



.. code::

    cd opengamma
    scripts/init-og-examples-db.sh
    scripts/og-examples.sh debug




On Windows:



.. code::

    cd opengamma
    scripts\init-og-examples-db.bat
    scripts\og-examples.bat debug




and point your browser at `http://localhost:8080 <http://localhost:8080>`_ 
This currently excludes the RStats module, which provides the source code to the OpenGamma Tools for R integration.  If you want it, add a `ant clone-or-pull`` step before the `ant install` step.


If the Windows build fails with an ant I/O exception, this is normally due to file paths exceeding the Windows path length limit. Try moving the entire source tree to the root directory in order to shorten path lengths, and rebuilding.


^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
Procedure for customers using proprietary components
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


There are other projects that are not part of the "core" OpenGamma Platform release that are necessary for a successful development installation, so we need to run a couple more steps.  When your initial clone is complete (which may take a few minutes), if you're a commercial customer then run (you can skip this step if you're using the Open Source release).


.. code::

    ant init



This will prompt you for a username and password.  If you are a commercial OpenGamma customer, enter the username and password you've been given.  This procedure should only need to be done the first time you clone the repository or if you move from using open source only modules to use the subscription-based modules.

Now run


.. code::

    ant clone-or-pull



to clone all the additional git projects necessary for development.

If we have provided instructions to work from a custom branch then run


.. code::

    ant checkout



and enter the branch name when prompted. By default the branch `master` is used.

Finally, run


.. code::

    ant install



to build and install the the system in the **opengamma/** folder in OG-Platform.

You can now move on to either the `Setting up an Eclipse Workspace </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Setting-up-an-Eclipse-Workspace/index.rst>`_  page or the `Using Ant from the Command Line </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Working-with-Ant/Using-Ant-from-the-Command-Line/index.rst>`_  page, or both.
