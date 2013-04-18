title: Using Ant from the Command Line
shortcut: DOC:Using Ant from the Command Line
---
...........
Quick Start
...........




.. code::

    ant install




Will clean the source tree, build all the modules and publish them to your local ivy repository (in folder kept under your home directory), prepare the deployment structures and then install them in **OpenGamma/**.

The first time you run `publish-all-local` all of the necessary dependencies will be downloaded from our Ivy repositories.  This can take some time the first time.  Subsequently it will be much faster, although if you use `ant clean-ivy-cache` then the dependencies will be downloaded anew.




.. code::

    ant tests




Will run *all* ~11,000 of the unit tests (takes 10-30 minutes depending on whether Postgres tests are switched on).

See `Running the Example Engine using Ant </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/Working-with-Ant/Running-the-Example-Engine-using-Ant/index.rst>`_  for how to run the example engine using ant.

...........................................................
More detailed description of the command line build targets
...........................................................


You can list all the available command line ant targets by running



.. code::

    ant -p




Ant targets can be invoked in two places

#  In the top level `OG-Platform/` directory


#  In one of the project directories `OG-Platform/projects/XXXX` where `XXXX` is the project name


~~~~~~~~~~~~~~~~~
Top level targets
~~~~~~~~~~~~~~~~~




+------------------------------+-------------------------------------------------------------------------------+
| Ant command                  | Description                                                                   |
+==============================+===============================================================================+
|  `ant build`                 | compile & publish all projects to the local repo                              |
+------------------------------+-------------------------------------------------------------------------------+
|  `ant checkout`              | checkout branch in each project (prompts for branch name if no \-Dgit.branch) |
+------------------------------+-------------------------------------------------------------------------------+
|  `clean`                     | clean all projects, delete repository & reload ivy                            |
+------------------------------+-------------------------------------------------------------------------------+
|  `clean-all`                 | clean all projects, delete repository, reload ivy & clean the ivy cache       |
+------------------------------+-------------------------------------------------------------------------------+
|  `clone-or-pull`             | git sync all projects using clone/pull                                        |
+------------------------------+-------------------------------------------------------------------------------+
|  `dist-binary`               | builds a single binary distribution zipfile                                   |
+------------------------------+-------------------------------------------------------------------------------+
|  `dist-source`               | builds a single source distribution zipfile                                   |
+------------------------------+-------------------------------------------------------------------------------+
|  `docs`                      | javadoc & archive for the whole system (requires > 2GB RAM and LaTeX)         |
+------------------------------+-------------------------------------------------------------------------------+
|  `init`                      | initialises the system config (user/pw required)                              |
+------------------------------+-------------------------------------------------------------------------------+
|  `install`                   | install artifacts to directory specified by property (install.dir)            |
+------------------------------+-------------------------------------------------------------------------------+
|  `ivy-report`                | generate ivy dependency report                                                |
+------------------------------+-------------------------------------------------------------------------------+
|  `projects.clone-or-pull`    | clone or pull all projects from git                                           |
+------------------------------+-------------------------------------------------------------------------------+
|  `projects.clover`           | run all unit tests with Clover enabled and produce one merged report          |
+------------------------------+-------------------------------------------------------------------------------+
|  `projects.pull`             | pull origin master all projects from git                                      |
+------------------------------+-------------------------------------------------------------------------------+
|  `projects.status`           | display the status of all projects from git                                   |
+------------------------------+-------------------------------------------------------------------------------+
|  `pull`                      | git sync all projects using pull                                              |
+------------------------------+-------------------------------------------------------------------------------+
|  `resolve`                   | resolve and retrieve dependencies with ivy                                    |
+------------------------------+-------------------------------------------------------------------------------+
|  `resolve-all`               | resolve all projects via ivy in the right order                               |
+------------------------------+-------------------------------------------------------------------------------+
|  `status`                    | git status check                                                              |
+------------------------------+-------------------------------------------------------------------------------+
|  `tests`                     | run tests for all projects with combined report                               |
+------------------------------+-------------------------------------------------------------------------------+
|  `tests-individual-reports`  | run tests for all projects with separate reports                              |
+------------------------------+-------------------------------------------------------------------------------+



~~~~~~~~~~~~~~~~~~~~~~~~
Project-specific targets
~~~~~~~~~~~~~~~~~~~~~~~~


In the local case `ant build` will publish just the sub-project to the local ivy repository.  Running `ant tests` locally to a project will just test that sub-project and the resulting JUnitReport will be placed in `tests/unit/output/html`.  There are also some special targets in OG-Examples, and OG-BloombergExamples for creating example databases and populating them with demo data.

~~~~~~~~~
About Ivy
~~~~~~~~~


Publishing artifacts to your local repository does not affect anyone else and it's important not to run `ant build` instead, which publishes your artifacts to the shared repository.  Ivy is organised in a series of tiers from it's cache, followed by it's local repository, which is kept in your home directory under `.ivy/`, through shared, which would normally be configured to be on filesystem shared between a group of collaborating developers (by default it's configured to look in `OG-Platform/repository` (which is the bundled repository on the downloads which include dependencies) up to the public repositories that are found on the internet (at http://ivyrepos.opengamma.com/OG-PrivateIvy and http://ivyrepos.opengamma.com/roundup, both are password protected using the username/password you enter during the `ant init` setup).  Ivy follows a series of 'resolvers' up a chain through each of these levels, only going higher if it can't find the artifact it is looking for.
