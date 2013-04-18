title: Setting Up Infrastructure For Tests
shortcut: DOC:Setting Up Infrastructure For Tests
---
Many OpenGamma components are infrastructure-heavy (in that they are designed to be used with external infrastructure like databases and middleware components). If you wish to develop those components, or run those tests, you'll need to setup infrastructure similar to the OpenGamma corporate development environment. This guide will explain how.

.......................................
How OpenGamma Tests Database Components
.......................................


Our database tests all start with a clean database, build up test data as required and perform the required series of unit tests.  

When you run 


.. code::

    ant init



we download the files `db.properties` and `tests.properties` into the `common/` directory.  By default, the Open Source release only tests against the HyperSQL embedded database so it's easier for you to get started.  If you want to test and run against both HyperSQL and PostgreSQL, you need to edit `db.properties` and `tests.properties` and edit the entries that refer to *postgres.yourdomain.com* to point at `Database Schema Installation </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Installation-Guide/Database-Schema-Installation/index.rst>`_ .  You also need to change line 3 of `common/tests.properties` to 


.. code::

    test.database.type=all



~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
How database test conflicts are avoided in a development team
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

To avoid multiple users running tests concurrently on the same database, each test takes place in a database named 'test_*<user-name>*' where *<user-name>* is substituted for the user's local user account name.

