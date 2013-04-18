title: Deploying Schemas In Separate PostgreSQL Databases
shortcut: DOC:Deploying Schemas In Separate PostgreSQL Databases
---
.............
Prerequisites
.............


The following instructions assume you have completed the installation of PostgreSQL 8.4, created the default database and have started the database server process.  We're assuming here that databases are being created by the default `postgres` user.  We also assume that the postgres `bin/` directory is in your path.

.........
Procedure
.........


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Databases used by MasterDB (Securities, Positions, Portfolios, Regions, Holidays, Exchanges, Configs)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Start by creating each database (catalog) on each machine you want to use:



.. code::

    createdb -U postgres og_financial_DDDD



where `DDDD` is the name of your database.  In fact, you can call the database whatever you like, and it can be a pre-existing database, in which case, skip the last step.

The actual SQL scripts to create the schema are stored in `OG-Platform/projects/OG-MasterDB/install/db/postgres/patchXXXX` where `patchXXXX` should be the patch version you want (choose the highest version usually).  So, change directory to that location and then run:



.. code::

    psql -U postgres -d og_financial_DDDD -f create-db-SSSS.sql




where `SSSS` is one or more of


*  batch


*  config


*  marketdatasnapshot


*  portfolio


*  position


*  refdata


*  security


*  timeseries


depending on which components you want.  All components additionally require `common` within each database to operate properly:



.. code::

    psql -U postgres -d og_financial_blah -f create-db-common.sql




~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Databases used by implementations of OpenGamma LiveData (Security/Audit)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Start by creating each database (catalog) on each machine you want to use:



.. code::

    createdb -U postgres og_livedata_DDDD



where `DDDD` is the name of your database.  There is usually one of these for each data provider, so one for e.g. Bloomberg, Reuters, Activ, etc so name your database appropriately.  In fact, you can call the database whatever you like, and it can be a pre-existing database, in which case, skip the last step.

The actual SQL scripts to create the schema are stored in `OG-Platform/projects/OG-Security/install/db/postgres/patchXXXX` where `patchXXXX` should be the patch version you want (choose the highest version usually).  So, change directory to that location and then run:



.. code::

    psql -U postgres -d og_livedata_DDDD -f create-db.sql




~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Referencing the new databases
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


The database connection details are specified in `Changing configuration for other databases </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Installation-Guide/Database-Schema-Installation/Changing-configuration-for-other-databases/index.rst>`_ .

