title: Windows Installation Packages
shortcut: DOC:Windows Installation Packages
---
The `downloads page <http://developers.opengamma.com/downloads/platform-1.0.0>`_  includes MSI installers for Windows to simplify the installation and evaluation process of the Open Source components. Please note that the installers only contain the binary distribution. If you require source code, please refer to our `public GIT repository <https://github.com/OpenGamma/>`_  or download one of the source distributions from the downloads page.


*  Use the `Servers`_  package if you just want to see the main OpenGamma system accessed via a browser based interface.


*  Use the `Tools`_  or `Full`_  packages if you want to use OpenGamma with `OpenGamma Tools for R </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Tools-for-R/index.rst>`_  as well.


Servers

.................
OpenGamma Servers
.................


The servers package (also included as part of the full installation bundle) contains both an example OpenGamma engine with mock data sources and one that can run against data from a Bloomberg terminal. If the installer is run on a workstation with Bloomberg installed, the Bloomberg version will be started, otherwise the example server will be started.

Both servers are installed as Windows services. The example server is named *OpenGammaExample* and the Bloomberg connected server named *BloombergExample*. One of these will be started by the installer after it completes but they are not set to start automatically after a workstation restart.

When the service starts it can take a couple of minutes to initialise and respond to requests. During this time, launching the *OpenGamma Web Interface* link from the Start Menu may not work and show a "Page not Found" error. Please wait a moment and try refreshing the page if this occurs.

~~~~~~~~~~~~~~~~~~~~~~~~
Bloomberg Example Server
~~~~~~~~~~~~~~~~~~~~~~~~


```````````
Desktop API
```````````


When installed on a workstation which also has Bloomberg installed the example server will connect to it to populate the databases with real data. This will use part of your Bloomberg data allowance as information about the exchange traded securities that form our sample portfolios and time-series data are loaded to initialise the database. After the installer has completed, the Bloomberg connection will only be used while you are running a view to subscribe to the ticking market data needed to calculate that view or to fetch additional data when you import portfolios or additional historical time series.

```````````````````````````````````
Managed B-PIPE (or SAPI/Server API)
```````````````````````````````````


During installation, select a *Complete* install, or choose *Custom* and enable the *Bloomberg Example Server* which is disabled by default unless you are installing onto a workstation that has the desktop API installed. After installation you can configure the server to connect to your Managed B-PIPE instance by doing the following:


*  Stop the *OpenGammaExample* and/or the *BloombergExample* services if either are running


*  Go to the configuration files for the example server, normally installed at `C:\Program Files\OpenGamma Ltd\OpenGamma\config\fullstack\bloombergexample-run.properties` and `C:\Program Files\OpenGamma Ltd\OpenGamma\config\toolcontext\bloombergexample-run.properties`


*  Edit the lines in each which read `bloomberg.host = localhost` to `bloomberg.host = _address of your SAPI instance_`


*  If your SAPI installation is not using the default port number (8194) then this should also be changed


*  As the Bloomberg connection was not available during installation the example database will not have been populated. This can be done by going to `C:\Program Files\OpenGamma Ltd\OpenGamma\bin` and running the `Populate example Bloomberg database` short cut. As this will update files in the installation folder you will need to launch it as an administrator (right click and choose `Run as administrator ...`.


*  Finally restart the *OpenGammaBloombergExample* service and it should connect to your Bloomberg connection


If you have a developer licensed connection with a low daily data limit then this may be hit if you run the installer more than a few times, load a large number of portfolios or import a large number of additional historical time series.

Tools

...............
OpenGamma Tools
...............


The tools package (also included as part of the full installation bundle) contains the `OpenGamma Tools for R </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Tools-for-R/index.rst>`_  component. This will only be installed if a supported installation of R is found. We have been testing with R version 2.13.1 although any version above 2.11.1 should work. The package contains two smaller installers - one for our Integration API and another for R support. These will be launched in turn.

During the installation of the Integration API that our R module uses to communicate with the OpenGamma system you may be prompted for the configuration address of your OpenGamma installation. If you have already installed the evaluation package on the same workstation, or are installing the client tools as part of the full installation bundle, the default value should be correct.

If you have not already installed the OpenGamma server or have installed on a different workstation to the client tools then it may be easiest to leave the default configuration address and run the *Configure OpenGamma Integration Service* link from the Start Menu after installation.

Also during the installation you may be prompted to download, and accept license agreements for, the Microsoft C/C++ Runtime Libraries if they are not already installed on your system. If you do not have internet access from the workstation you are installing onto these prerequisites may be downloaded from the Microsoft website (`64-bit <http://www.microsoft.com/download/en/details.aspx?id=14632>`_  and `32-bit <http://www.microsoft.com/download/en/details.aspx?id=5555>`_ )

Please note that the 32-bit tools may not work on some 64-bit versions of Windows and R. This is due to the location that a default R installation uses for 32-bit code. The 64-bit version of our tool installers contains both versions of our library so that it can be used with either 32-bit or 64-bit versions of R, alternatively we have `Installing 32-bit R modules on 64-bit Windows </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Installation-Guide/Windows-Installation-Packages/Installing-32-bit-R-modules-on-64-bit-Windows/index.rst>`_ .

Full

...........................
OpenGamma Full Distribution
...........................


The full distribution bundle contains both the `Servers`_  and `Tools`_  installation packages to simplify the evaluation process when using OpenGamma on a single workstation.
