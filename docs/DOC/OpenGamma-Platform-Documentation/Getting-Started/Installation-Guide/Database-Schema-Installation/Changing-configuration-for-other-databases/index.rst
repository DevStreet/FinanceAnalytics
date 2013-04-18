title: Changing configuration for other databases
shortcut: DOC:Changing configuration for other databases
---
.............
Configuration
.............


OpenGamma can run against different databases. This document explains how the configuration works.

In general servers will be started using the `Component-based Configuration </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/index.rst>`_ . With these servers, there is one directory for each server which contains one or more properties files. For example, the file is curently called `example.properties` in OG-Examples. (The properties files in each configuration directory form chains, so it is important to check each file and understand the chains that exist.)

These properties files contain sets of keys like this for HSQL:



.. code::

    db.standard.dialect = com.opengamma.util.db.HSQLDbDialect
    db.standard.driver = org.hsqldb.jdbcDriver
    db.standard.url = jdbc:hsqldb:file:install/db/hsqldb/example-db
    db.standard.username = OpenGamma
    db.standard.password = OpenGamma




The equivalent settings for Postgres would be:



.. code::

    db.standard.dialect = com.opengamma.util.db.PostgresDbDialect
    db.standard.driver = org.postgresql.Driver
    db.standard.url = jdbc:postgresql://db.server.com/databaselocation
    db.standard.username = foo
    db.standard.password = bar




The older properties files may also be referenced:

*  `common/db.properties`


*  `common/tests.properties`


