title: 1.0.0 Release Notes
shortcut: DOC:1.0.0 Release Notes
---
High Level changes from 0.9.0 to 1.0.0


*  First release of R integration module


   *  Allows execution of completely custom scenarios controlled from R


   *  Market data can be manipulated at level of individual 'ticker' or in tensor form (matrix/vector etc)


   *  Custom securities and portfolios can be created from R or existing portfolios referenced


*  Bloomberg module now open source


   *  Complete example system using data populated from a Bloomberg Terminal or Managed B-PIPE/SAPI instance


   *  Automatic reference data loading for exchange traded securities


   *  Time series loading and updating


   *  OpenGamma Live Data adapter for real-time streaming data


*  WebUI


   *  Market data snapshots supported as a data source


   *  Time series viewing through the UI (see PnL series in dep graph for Historical VaR for example)


   *  Position detailed information pop-up, with link to full security info in analytics view


   *  Multiple live data sources including combined data sources


   *  Fall-back to time series for missing data


   *  Single level re-aggregation of portfolio on-the-fly


   *  First uses of new 'push REST' support in notifying web client of updates asynchronously


   *  Quantity in separate column, other small formatting changes


   *  Portfolio breadcrumbs


   *  Configuration form improvements


   *  Integration of Security Time Series data into Security views


*  Packaging


   *  Windows installer (.msi) for Examples


      *  Automatic Bloomberg terminal detection with installation of appropriate example set


      *  OpenGamma server installed as a Windows service


   *  Windows installer (.msi) for R support


   *  Windows installer (.msi) for Examples & R together


   *  All installers are signed with a digital certificate


*  Database Masters


   *  New asset classes in security master


   *  Re-written batch database


   *  Arbitrary attributes on portfolios, positions, trade and securities allow for custom meta-data


   *  Hidden portfolios


   *  Performance enhancements (esp. time series)


   *  Support for PostgreSQL 10


   *  Aggregation


   *  Support for aggregating by currencies (currency + normalized currency pairs)


   *  Support for ordering/sorting of positions


   *  Multi-aggregator aggregates several ways into single portfolio


   *  Option to aggregate where possible when some values are missing


*  Complete overhaul of the configuration system


   *  Much easier deployment and maintenance using distributed component management system


*  Examples


   *  With access to Bloomberg terminal


      *  Arbitrary exchange traded positions can be loaded from a CSV


      *  Several example portfolios containing OTC instruments are generated during installation


   *  Without access to a Bloomberg terminal


      *  We still support a synthetic data simulator (live and historical)


      *  We show a wider range of asset classes (e.g. CMS/FX/Swaptions etc).


*  Data Import/Export


   *  Standard import format for security/portfolio data


   *  Framework for custom importers


*  Improved Build System


   *  Single-step build


   *  Easier deployment


   *  Better ant target names


*  Analytics


   *  Externally provided sensitivities


   *  Yield curve sensitivity mapping


   *  Separate Yield Curve/Credit/All sensitivities buckets


   *  DV01, CS01, Historical VaR


*  Documentation


   *  Improved documentation, especially for Analytics


.........
Upgrading
.........


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



To upgrade, find the patch level for your current release, and then roll forward from each patch, applying the appropriate `upgrade-db.sql` file for each patch level and database version until you get to the target patch.  For 1.0.0 we've changed the way database patches are done.  Patches are now versioned on a per-database basis, and a version table is now included and updated to ease identifying which schema version you currently have.  A side effect of this is that we haven't included the older schema updates in this release, so for 1.0.0 we recommend recreating the schema from scratch.  Please contact us via the forums or commercial support channel if you need to perform a schema upgrade from 0.9.0 and we'll provide instructions.

..........................................
Full List of Jira Issues Resolved in 1.0.0
..........................................


In addition to the usability issues highlighted above, there are as usual a list of issues resolved throughout the system.

.. raw:: html

    <iframe class="jira" src="http://jira.opengamma.com/sr/jira.issueviews:searchrequest-printable/temp/SearchRequest.xml?jqlQuery=project+%3D+PLAT+AND+fixVersion+%3D+%221.0.0%22&tempMax=1000"></iframe>



