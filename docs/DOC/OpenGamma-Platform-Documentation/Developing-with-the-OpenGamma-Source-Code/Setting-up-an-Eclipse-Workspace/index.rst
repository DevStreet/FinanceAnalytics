title: Setting up an Eclipse Workspace
shortcut: DOC:Setting up an Eclipse Workspace
---
.............
Prerequisites
.............



*  `Install the latest Java JDK <http://www.oracle.com/technetwork/java/javase/downloads/index.html>`_  if you don't have it installed. The OpenGamma Platform requires a 1.6.0 JDK for development, and a 1.6.0 JRE for runtime.


*  `Install Apache Ant <http://ant.apache.org/manual/install.html>`_  if you don't have it installed. `On Windows, installing Ant is more complex </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Setting-up-an-Eclipse-Workspace/On-Windows,-installing-Ant-is-more-complex/index.rst>`_ .


*  If you're using Git, you may need to install that.  See `the excellent GitHub help guide <http://help.github.com/>`_  or just download from http://git-scm.com.  We'd recommend using the msysgit client on Windows rather than Cygwin.


For a full overview of the prerequisites for developing with the OpenGamma source code, watch the [Prerequisites screencast|DOC:Obtaining and building the source code^OpenGamma_Prerequisites.mp4]. It covers installing Java, Ant, Git, and Eclipse.


This assumes you have cloned a copy of OG-Platform from Git (or obtained a source tarball/zip).

For more information, see `Obtaining and building the source code </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Obtaining-and-building-the-source-code/index.rst>`_  but the short version for Open Source users is:


.. code::

    git clone http://github.com/OpenGamma/OG-Platform.git




......................................................................................
Setting up Eclipse when running from a Git clone and using Ivy to resolve dependencies
......................................................................................


You may `Using Ant from the Command Line </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Working-with-Ant/Using-Ant-from-the-Command-Line/index.rst>`_  before setting up an Eclipse workspace, although this isn't required.
If you choose not to run a command line build, be aware it can take a significant amount of time the first time you start after you finish the procedure below, when Eclipse resolves the Ivy artifacts.  This will of course depend on your internet connection.  Once you've done it for the first time, your Ivy cache should make building much faster.


~~~~~~~~~
Procedure
~~~~~~~~~


This procedure is also available as a [screencast|^OpenGamma Eclipse Setup.mp4].


It's very important you follow the steps below exactly in the order specified, or the result will be incorrect.

#  Make sure you install, as a minimum: Eclipse itself, the IvyDE plugin and the Checkstyle plugin.


   #  `Install Eclipse <http://www.eclipse.org>`_ . We recommend the 'Classic' bundle. Then, create a fresh workspace created in a location separate from your source tree.The .project files we use only work with the *Indigo* (3.7.x) or latest *Juno* (4.2.x) stable releases of Eclipse.



#  Either


   #  Go to **File->Import**


      #  Under **Install** choose **Install Software Items from File**


      #  Click the **Browse...** button


      #  Navigate the file chooser to the `OG-Platform/eclipse` folder and choose the file **plugins.p2f**


      #  Click **Next**, click the 'Accept the license terms' button and click **Finish**.  You may see warnings about unsigned content and Eclipse may offer to restart.


   #  Or install the plug-ins manually one by one:


      #  `Install IvyDE <http://ant.apache.org/ivy/ivyde/download.cgi>`_  (required)


      #  `Install Eclipse-CS <http://eclipse-cs.sourceforge.net/downloads.html>`_  (optional, but recommended)


      #  `Install TestNG plug-in <http://testng.org/doc/download.html>`_  (optional, but recommended)


#  Import the default preferences


   #  Go to **File->Import**


   #  Under **General** choose **Preferences**


   #  Click **Next**


   #  Click the **Browse...** button


   #  Navigate the file chooser to the `OG-Platform/eclipse` folder and choose the file **OpenGamma-Eclipse-Preferences.epf**


   #  Click **Finish**


#  Turn off auto-builds until we've finished setting up


   #  Go to **Project->Build Automatically** to uncheck the **Build Automatically** menu entry


#  Import the sub-projects


   #  Go to **File->Import**


   #  Under **General** choose **Existing Projects into Workspace**


   #  Click the **Browse...** button


   #  Navigate the file chooser to the `OG-Platform/projects` folder and click **Open**. **IMPORTANT**: Pay special attention to the fact it's the `projects/` subfolder, not the root.


   #  A large list of projects should appear


   #  Click **Finish**


#  Import the top-level project


   #  Go to **File->Import**


   #  Under **General** choose **Existing Projects into Workspace**


   #  Click the **Browse...** button


   #  Navigate the file chooser to the `OG-Platform` folder and click **Open**. **IMPORTANT**: This time it is the root folder.


   #  **OG-Platform** should appear as a project on its own


   #  Click **Finish**


#  Turn on auto-builds


   #  Go to **Project->Build Automatically** to check the **Build Automatically** menu entry


#  Quit Eclipse and Restart (Do Not Just Use **File->Restart**) It will take several minutes of building, but eventually show all projects error free.  If you have not run a command line build it may take even longer to download all the artifacts.


   #  If there is an error shown in the **OG-Engine** project:


      #  Find the source files (PutRequest.java and GetRequest.java are the culprits) in the package explorer.


      #  Right-click on each one in turn in the package explorer and choose **Checkstyle->Clear Checkstyle violations**.  This is necessary because of a bug in the Checkstyle parser.


#  Get exploring\!


...................................
Recommended Extras for Contributors
...................................



#  Load the code templates


   #  Go to Global Preferences (on the Mac it's **Eclipse->Preferences..** and on Windows/Linux it's under **Window->Preferences...**)


   #  Under **Java->Code Style->Code Templates** click on **Import...**


   #  Browse to **OG-Platform/eclipse/** and choose **OpenGamma-Java-Eclipse-CodeTemplates.xml**


   #  Click **Open**


#  Load the code formatter


   #  Go to Global Preferences (on the Mac it's **Eclipse->Preferences..** and on Windows/Linux it's under **Window->Preferences...**)


   #  Under **Java->Code Style->Formatter** click on **Import...**


   #  Browse to **OG-Platform/eclipse/** and choose **OpenGamma-Java-Eclipse-Formatter.xml**


   #  Click **Open**


#  Install more Eclipse plugins


   #  `Install Spring IDE <http://download.springsource.com/release/STS/doc/STS-installation_instructions.pdf>`_ . Only "Core/Spring" IDE is needed. Consider disabling spring file validation as it may be slow.


   #  `Install Freemarker support <http://download.jboss.org/jbosstools/updates/stable/helios/>`_ . This is part of the JBoss suite, but can be installed on its own (find the single Freemarker entry, rather than selecting everything).


.........................
Tips if you have problems
.........................



*  Make sure you have the latest Eclipse (Indigo at the time of writing).  The settings files don't work with Galileo properly: specifically, if you get an error about 'path fragments', that is because you're trying to use Galileo.


*  Eclipse doesn't always refresh things when you think it will.  Try highlighting all projects, right-clicking and choose 'Refresh' on the menu.


*  Sometimes IvyDE needs a kick to resolve/refresh artifacts - try highlighting all projects, right clicking and choose Ivy->Resolve.  There's a button on the toolbar that does the same thing.


*  Running command line unit tests can occasionally conflict with a running copy of Eclipse, in which case a workaround is to shut down Eclipse while you run tests.


*  If you have the Spring plug-in installed, turn off spring file validation.


*  If you do a fresh pull from the git repository, you'll need to refresh all the projects.

