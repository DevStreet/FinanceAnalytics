title: Running Tests with Ant
shortcut: DOC:Running Tests with Ant
---
In order to run command-line tests, you must have already done a full [command-line Ant build|Building From The Command Line]. Incremental tests on particular projects can be done without an `ant publish-all-local`.


Running the unit tests can either be done within each sub-project in the `projects` directory of `OG-Platform` or can be done from the top level.  In either case simply type


.. code::

    ant tests



Note that a full set of tests (currently more than 11,000) take around 30 minutes to run on a fast workstation.  Output can be found in `tests/output/html/` relative to wherever you ran the `ant tests` task.
When running the top level tests, the `noframes` option is used to generate the test report.  This is because the `frames` output consumes a great deal of memory and can cause `OutOfMemoryException` errors.  If you want the `frames` option, you should increase the Java heap and possibly stack space  maximums in the `ANT_OPTS` environment variable.  This has to be done because the JUnitReportRunner Ant task doesn't support process forking, so has to run in the same memory footprint as Ant itself.  Something like `ANT_OPTS=-Xmx4096m \-Xss4m`, although the stack option (`\-Xss`) will only help if you get a `java.lang.StackOverflowException`.


...............
Test Frameworks
...............


The `Fudge-Java` and `Fudge-Proto` projects used by the OpenGamma Platform still currently use `JUnit4 <http://www.junit.org/>`_ , whereas the Platform itself now uses `TestNG <http://testng.org>`_ .  Reports from both test frameworks are unified by using the JUnitReportRunner Ant task. 
