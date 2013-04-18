title: 0.9.0 Release Notes
shortcut: DOC:0.9.0 Release Notes
---
............
Key Features
............


There are lots of highlights in this release:


*  A binary release of our examples package that actually shows a lot of functionality


*  Massive increase in end-to-end asset class coverage


*  New web UI skin, and hugely improved editing for View Definitions and Yield Curve Definitions


*  View definition changes instantly reflected in analytics view


*  Support for market data snapshots (our .NET manager app for capturing and editing them is coming soon)


~~~~~~~~~
Fake data
~~~~~~~~~

We've made a effort to produce a set of fake data to allow those evaluating the platform to get a better idea of the functionality.  Using our own identifier scheme, we've added

*  Fake sample ticking and historical data and code to generate them


*  Example portfolios and securities and code to generate them


*  Example equity and swap views and code to generate them


*  Curve definitions for a range of currencies


*  Curve specifications for a range of currencies (maps to our identifier scheme `OG_SYNTHETIC_TICKER`)


~~~~~~~~~~~~~
Asset classes
~~~~~~~~~~~~~


*  Interest Rate Swaptions, Futures, Future Options, and CMS products


*  FX Spot, Forward, and Vanilla and Single Barrier options


*  FRAs and numerous new types of bonds


~~~~~~~~~~~~~~~~~~~~~~~~
New Web GUI Improvements
~~~~~~~~~~~~~~~~~~~~~~~~

The biggest additions are:

*  A new improved skin


*  Hugely improved View Definition editing, with lots of nice, fast, client-side structure manipulation


   *  Changes are dynamically reflected in the web view! (previously required an engine restart)


*  Hugely improved Yield Curve Definition editing


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
New and improved documentation
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


*  Improvements to documentation for getting started, including a `Quick-start guide </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Quick-start-guide/index.rst>`_ 


*  Description of the process of building `Yield Curves </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Yield-Curves/index.rst>`_ 


*  Instructions for NO LINK FOUND FOR 'Setting Up IntelliJ IDEA'


*  Updates for the instructions on how to run the examples


*  A new `System Data Flow Diagram and Explanation </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/System-Data-Flow-Diagram-and-Explanation/index.rst>`_  page showing and explaining the compute engine in detail


*  New FAQ items


*  Incorporation of feedback of common problems into the instructions


*  Updated `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  section to reflect our renaming of several key identifier classes


.........
Upgrading
.........


For our 0.x lines, unless you're a commercial customer (for whom we're doing upgrades ourselves as part of our support policies), you'll need to either upgrade your databases, or recreate them from scratch from your original source data.



+---------+----------------------+
| Version | MasterDB Patch Level |
+=========+======================+
| 0.7.x   | 6                    |
+---------+----------------------+
| 0.8.0   | 13                   |
+---------+----------------------+
| 0.9.0   | 20                   |
+---------+----------------------+



To upgrade, find the patch level for your current release, and then roll forward from each patch, applying the appropriate `upgrade-db.sql` file for each patch level and database version until you get to the target patch.

..........................................
Full List of Jira Issues Resolved in 0.9.0
..........................................


In addition to the usability issues highlighted above, there are as usual a list of issues resolved throughout the system.

.. raw:: html

    <iframe class="jira" src="http://jira.opengamma.com/sr/jira.issueviews:searchrequest-printable/temp/SearchRequest.xml?jqlQuery=project+%3D+PLAT+AND+fixVersion+%3D+%220.9.0%22&tempMax=1000"></iframe>



