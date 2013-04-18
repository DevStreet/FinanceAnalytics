title: Which branch do I use?
shortcut: DOC:Which branch do I use?
---
........
Branches
........


Each OpenGamma project has a range of branches available.  Switching between them can be achieved with the usual git syntax of


.. code::

    git checkout develop



alternatively if you have multiple git projects using OpenGamma at once (e.g. the OG-RStats project or the commercial components) you can use the command


.. code::

    ant checkout -Dgit.branch=develop



to switch branch in every project in turn.  If you omit the `\-D` flag, you will be prompted for the branch name.  This can be a real time-saver if you have many projects.

We recommend most users stick to the `master` branch, but here is a list of the function of some of the other branches available.



+------------------------------+--------------------------------------------------------------------------------------------------+
| Branch name                  | Function                                                                                         |
+==============================+==================================================================================================+
| master (default branch)      | Stable branch containing last major release                                                      |
+------------------------------+--------------------------------------------------------------------------------------------------+
| develop                      | Development branch for internal development, may be unstable                                     |
+------------------------------+--------------------------------------------------------------------------------------------------+
| dev/stable                   | old stable branch, before we decided to make master contain a stable branch                      |
+------------------------------+--------------------------------------------------------------------------------------------------+
| dev/v<major>.<minor>.x       | tag for latest minor release within each major release                                           |
+------------------------------+--------------------------------------------------------------------------------------------------+
| rel/v<major>.<minor>.<patch> | tag for actual releases                                                                          |
+------------------------------+--------------------------------------------------------------------------------------------------+
| ui/dev                       | internal semi-stable release for UI development team                                             |
+------------------------------+--------------------------------------------------------------------------------------------------+
| [ui/]topic/*                 | branches used to work on issues that allow for easy cherry\-picking into multiple other branches |
+------------------------------+--------------------------------------------------------------------------------------------------+


