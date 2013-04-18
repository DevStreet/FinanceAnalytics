title: Deploying all schemas in a single PostgreSQL database
shortcut: DOC:Deploying all schemas in a single PostgreSQL database
---
You *do not* need to install PostgreSQL to evaluate OpenGamma - the examples use a HyperSQL embedded database, and indeed the default set up that you download is to run the unit tests and example engine against *only* HyperSQL.  However, we recommend the installation and configuration of PostgreSQL for serious work.


.............
Prerequisites
.............


The following instructions assume you have completed the installation of PostgreSQL 9.0, created the default database and have started the database server process.  We're assuming here that databases are being created by the default `postgres` user (although you would obviously be advised against that configuration).  We also assume that the postgres `bin/` directory is in your path.

.........
Procedure
.........



~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Databases for MasterDB (Securities, Positions, Portfolios, Regions, Holidays, Exchanges, Configs)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Start with:



.. code::

    createdb -U postgres og_financial




Will create the initial empty database.  The actual SQL scripts to create the schema are stored in `OG-Platform/projects/OG-MasterDB/install/db/create/postgres/<db>/V_<patch-number>__<name>.sql` where `<db>` should be the specific database module you're installing (one of cfg_db, cmn_db, eng_db, exg_db, hts_db, pos_db, rsk_db, sec_db, snp_db), `<patch-number>` is the patch version you want (choose the highest version usually) and <name> is a descriptive name.

So, change directory to that location and then run:



.. code::

    psql -U postgres -d og_financial -f <your-chosen-db-script>.sql




Then update the appropriate `common/tests.properties` files to point the unit tests at your new databases and update your database info in the top level .properties file you're passing to the top level Component Server to launch the system (e.g. your equivalent of OG-Examples/config/fullstack/example.properties).

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Databases for OpenGamma LiveData (Security/Audit)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

You only need this if you actually have a data provider to install that requires authorisation/authentication services.


Start with:



.. code::

    createdb -U postgres og_DDDD




where `DDDD` is the name of your data provider (e.g. bloomberg, reuters, activ, internal).  This will create the initial empty database.  The actual SQL scripts to create the schema are stored in `OG-Platform/projects/OG-Security/install/db/create/postgres/auth_db/V_<patch-level>__<name>` where `<patch-level>` should be the patch version you want (choose the highest version usually) and `<name>` is an arbitrary descriptive name.  So, change directory to that location and then run:



.. code::

    psql -U postgres -d og_DDDD -f V_1__create-auth.sql




again, substitute `DDDD` with the name of your data provider.

`````````````````````````````
Referencing the new databases
`````````````````````````````


The database connection details are specified in `Changing configuration for other databases </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Installation-Guide/Database-Schema-Installation/Changing-configuration-for-other-databases/index.rst>`_ .
