title: FAQ
shortcut: DOC:FAQ
---



...............
Getting Started
...............


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
How can I build the OpenGamma Platform?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


You'll need to get started by either downloading the OpenGamma Platform source code from http://developers.opengamma.com, or `forking our repositories on GitHub <https://github.com/OpenGamma>`_ . Then simply follow the instructions on `Developing with the OpenGamma Source Code </confluence/DOC/OpenGamma-Platform-Documentation/Developing-with-the-OpenGamma-Source-Code/index.rst>`_ .
licensing


~~~~~~~~~
Licensing
~~~~~~~~~


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
What is the license for the OpenGamma Platform?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


The OpenGamma Platform is available under two different types of licenses:

*  The `Apache License v.2.0 <http://www.apache.org/licenses/LICENSE-2.0.html>`_ , which is our primary Open Source license, or


*  A commercial license, only available to commercial customers of OpenGamma


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Can I roll into production on the Open Source license?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Of course you can. The Apache License allows you to make free use of the platform, in binary or source code format. You also can make your own changes to the source code and distribute them inside your organization, or outside your organization, without permission or fees.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Do I need to release my proprietary code?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


No. The Apache License doesn't have any "viral" or "copy-left" features. Any code which you write, either integrating with the core OpenGamma Platform, or modifying it, is owned by you and you can do whatever you want with it. There is absolutely no requirement that **any** proprietary code you write be released under any specific license.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Can I incorporate the OpenGamma Platform into a commercial product or service?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Yes. You can incorporate all or part of the OpenGamma Platform in your own commercial product or service without paying any licensing fee to the authors.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
What do I get from a commercial license?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Aside from access to OpenGamma `Commercial Support </confluence/DOC/OpenGamma-Platform-Documentation/Support/Commercial-Support/index.rst>`_ , and access to OpenGamma proprietary components (like our Bloomberg module and `OpenGamma Tools for Microsoft Excel </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Tools-for-Microsoft-Excel/index.rst>`_ ), you get a license which contains traditional features such as:

*  Explicit representations and warranties about the software;


*  Indemnification against potential intellectual property violations in the software (of which there are none known); and


*  A confidentiality clause covering any communications between your organization and OpenGamma.


.................
Contributing Code
.................


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Can I contribute to the OpenGamma Platform?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Yes\! We welcome contributions. See our `Contributing back to OpenGamma </confluence/DOC/OpenGamma-Platform-Documentation/Contributing-back-to-OpenGamma/index.rst>`_ .

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Why do you insist on an IP assignment agreement?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Because commercial customers receive a license to the OpenGamma Platform with certain additional features above and beyond the Apache License, we have to have the legal authority to release the code under that license. However, our IP assignment agreement has an embedded license back to you to allow you to do whatever you like with that contribution.

.................
Community Process
.................


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
How do I get an account on Jira and Documentation sites?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


In order to get an account on `OpenGamma Jira <http://jira.opengamma.com/>`_  and `OpenGamma Documentation <http://docs.opengamma.com>`_  you need to first sign up for an account on the `main OpenGamma developers site <http://developers.opengamma.com/>`_ . When you do this, it will create an account in our `Atlassian Crowd <http://www.atlassian.com/software/crowd/>`_  instance, and enable your accounts on Jira and Documentation.

We've had reports from some people that their logins don't work properly on Jira and Docs first time. If that happens, log out of Jira and/or Documentation, wait about half an hour, and try again. We're actively working on this, and should have a fix for it shortly.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Why do I have to sign up for a separate Forums account?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


You'll notice that you need to create a separate account for `OpenGamma Forums <http://forums.opengamma.com/>`_ . This is because we're using a new forum software system called Vanilla, which doesn't currently have Crowd integration. We're working with the Vanilla team to do Crowd integration, and should eliminate this separate login at some point in the future.

..........................................
Problems building and running the examples
..........................................


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
When I run `ant jetty` my browser flashes to a page and then gives me a 503 error
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


You need tun the `ant new-hsqldb` task in `OG-Examples` to create a blank database before starting the engine.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
When I run `ant publish-all-local` I get a message about unresolved dependencies
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


If you're seeing something like this:


.. code::

    [ivy:resolve]           ::::::::::::::::::::::::::::::::::::::::::::::
    [ivy:resolve]           ::          UNRESOLVED DEPENDENCIES         ::
    [ivy:resolve]           ::::::::::::::::::::::::::::::::::::::::::::::
    [ivy:resolve]           :: org.fudgemsg#fudge-java;latest.integration: not found
    [ivy:resolve]           :: org.fudgemsg#fudge-proto;latest.integration: not found
    [ivy:resolve]           ::::::::::::::::::::::::::::::::::::::::::::::
    [ivy:resolve]



then you haven't pulled down the Fudge-Java and Fudge-Proto projects.  This can happen for two reasons:

#  You forgot to run `ant clone-or-pull`


#  You ran `ant clone-or-pull` and it printed an error, but you didn't notice.

If you're not sure, run `ant clone-or-pull` again (in the top level directory) and see if it reports any errors.
If you see an error mentioning `libcurl`, it means you need to upgrade your version of `git` as GitHub have
recently stopped supporting the older HTTP protocol and older versions of git don't have https support compiled in.
Similarly if you get a 503 error back, it's probably the same issue.  Upgrade git, restart your terminal session
and run `ant clone-or-pull` again.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
When I download the examples binary package for Windows a window flashes up when I double-click on the batch files
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Unfortunately, with the current release, you can't just double-click on the batch files.  You need to open a command window (run cmd.exe), change directory to the root of the unzipped distribution (**not** the `scripts` directory, the one above), and run the batch files from there:


.. code::

    scripts\init-example-db.bat
    scripts\start-jetty.bat



This obviously isn't ideal behaviour, and we're planning to change it for the next release.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
When I run the Windows examples batch files or the Linux bash script files, I get a ClassNotFoundException for DBTool or other class
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


This is the same as the above problem: in Windows you need to open a command window (run cmd.exe), change directory to the root of the unzipped distribution (**not** the `scripts` directory, the one above), and run the batch files from there:


.. code::

    scripts\init-example-db.bat
    scripts\start-jetty.bat



In Linux you need to do similar, change directory to the root of the untarred distribution (**not** the `scripts` directory, the one above), and run the scripts from there:


.. code::

    ./scripts/init-example-db.sh
    ./scripts/start-jetty.sh



This obviously isn't ideal behaviour, and we're planning to change it for the next release.
