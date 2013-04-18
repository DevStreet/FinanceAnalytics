title: Release Notes
shortcut: DOC:Release Notes
---
High Level changes from 1.1.0 to 1.2.0

.............
Release Notes
.............


~~~~~~~~~~~~~~
User Interface
~~~~~~~~~~~~~~



*  Multi-pane analytics viewer


*  Drag-and-drop panels


*  In-line drop-downs


*  Independent pop-out panels


*  Intelligent tab handling


*  Multi-view data gadgets can switch between e.g. explain value, data table, graph


*  Multi-curve viewer


*  Surface viewer


*  Fixed columns for trade/position details


*  Super-fast hand-written virtual viewport grid gadget


*  Improved spark-line performance


*  Copy and paste, even off on-screen buffer using Excel-like selection model


*  Copy and paste of complex structures (curves, time series, matrices, etc) from single selected cells


*  Multi-level portfolio aggregation


*  Cascading market data sourcing (e.g. Live Bloomberg falling through to historical time series snapshot)


*  Portfolio/market data/valuation time selection


*  Server restart and bad data link recovery


*  Client state encoded in URL


*  Progress notification


*  Feedback survey link


~~~~~~~~~~~~~~~~~
Analytics Library
~~~~~~~~~~~~~~~~~



*  Credit default swap support. Analytics and database support for vanilla legacy and standard CDS. Integration work done but untested. Pricing and greeks available in OG-Analytics.


*  Extended futures support. Generalised futures pricing (mark-to-market and as forward) for equity, commodity, interest rate and bond (in addition to specific pricing e.g. bond future pricing using the underlying basket).


*  Commodity futures and options database support. Pricing is non-commodity-specific at the moment, but the infrastructure is in place to add this.


*  Support for dividend-corrected equity volatility surfaces and the ability to use these surfaces in equity variance swap pricing.


~~~~~~~~~~~~~~~
Data Management
~~~~~~~~~~~~~~~



*  Futures/Forwards restructured


*  Initial CDS security model (non-functional in engine in this release)


*  User database for use by future permissions module


*  Schema versions are checked by production systems


*  Auto-upgrade tool for database schemas


~~~~~~~~~~~~~~~~~~~~~~~~~
Market and Reference Data
~~~~~~~~~~~~~~~~~~~~~~~~~



*  Live data system now uses OpenGamma component system


*  Reference data loading decoupled from Bloomberg into SecurityProvider interface


~~~~~
Excel
~~~~~



*  Explain value access functions


*  Matrix column/row/all summing/addressing


*  Back port to Excel 2003


*  Bug fixes


~~~~~~~~
Examples
~~~~~~~~



*  Example custom security


*  Example JMS-based analytics service


~~~~~~~~~~~~
Installation
~~~~~~~~~~~~



*  Installer allows user choice of non-Bloomberg examples when run on machine with Bloomberg terminal


*  User given feedback during database population stage


*  No progress or dialog boxes during unattended installation


~~~~~
Build
~~~~~



*  Maven plug-ins to generate scripts and schema version files


*  Maven build support (not tests yet)


*  Artifacts published in OpenGamma Nexus


*  Bash scripts and batch files now auto-generated from @Scriptable annotation on command line tool classes


............
Known issues
............



*  The Mixed CM View has been removed from the examples because it causes JVM crashes on Java 6.  While this is fixed in Java 7, this was discovered too late to upgrade the Windows installers.


*  Installing the Windows installers on Windows XP is not supported as most versions of XP don't support the RegQueryValue Win32 API Call used in the installer.  Windows Vista, 7 or 8 should be used instead.


*  The 32-bit Windows installer does not open a browser window at the end of installation.  The solution is to open a browser manually a browse to http://localhost:8080


*  Building the release artifacts requires ant-contrib and apache-commons-codec to be in the ant classpath.


*  A small number of unit tests fail when built outside the OpenGamma build environment.


*  The `README` file in demo binary installations implies the user should run the `og-examples.[sh|bat]` script with no parameters.  In fact the script takes a parameter e.g. `start` (start in background), `debug` (start and wait for logging output), `restart`, `stop`, etc.  It does however display these options when run without a parameter.


.........
Upgrading
.........


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Upgrading for Git users with Eclipse
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



~~~~~~~~~~~~~~~~~
Database upgrades
~~~~~~~~~~~~~~~~~


For our 0.x lines, unless you are a commercial customer (for whom we are performing upgrades as part of our support policies), you will need either to upgrade your databases, or to recreate them from scratch from your original source data.



+---------+----------------------+
| Version | MasterDB Patch Level |
+=========+======================+
| 0.7.x   | 6                    |
+---------+----------------------+
| 0.8.0   | 13                   |
+---------+----------------------+
| 0.9.0   | 20                   |
+---------+----------------------+
| 1.0.0   | 46                   |
+---------+----------------------+



After version 1.1.0, we have split each master to have a different patch level.  Each database now has a schema_version table containing the latest version number.



+---------+--------------+--------------+--------------+----------------+-------------------------+----------------+-----------------+------------------+----------------+----------------+-------------+
| Version | cfg (config) | cmn (common) | eng (engine) | exg (exchange) | hts (hist. time series) | pos (position) | prt (portfolio) | rsk (risk/batch) | sec (security) | snp (snapshot) | usr (users) |
+=========+==============+==============+==============+================+=========================+================+=================+==================+================+================+=============+
| 1.1.0   | 45           | 45           | 46           | 45             | 45                      | 46             | 46              | 45               | 53             | 45             | NA          |
+---------+--------------+--------------+--------------+----------------+-------------------------+----------------+-----------------+------------------+----------------+----------------+-------------+
| 1.2.0   | 46           | 45           | 46           | 45             | 45                      | 46             | 46              | 45               | 54             | 45             | 47          |
+---------+--------------+--------------+--------------+----------------+-------------------------+----------------+-----------------+------------------+----------------+----------------+-------------+



To upgrade, find the patch level for your current release, and then roll forward from each patch, applying the appropriate `V_xx__migrate.sql` file for each patch level and database version until you get to the target patch.  Please contact us via the forums or commercial support channel if you need to perform a schema upgrade from 0.9.0 and we'll provide instructions.

..........................................
Full List of Jira Issues Resolved in 1.2.0
..........................................


In addition to the usability issues highlighted above, there are as usual a list of issues resolved throughout the system.

.. raw:: html

    <iframe class="jira" src="http://jira.opengamma.com/sr/jira.issueviews:searchrequest-printable/temp/SearchRequest.xml?jqlQuery=project+%3D+PLAT+AND+fixVersion+%3D+%221.2.0%22&tempMax=1000"></iframe>



