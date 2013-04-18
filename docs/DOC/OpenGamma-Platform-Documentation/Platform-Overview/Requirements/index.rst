title: Requirements
shortcut: DOC:Requirements
---
.....................
Back-End Requirements
.....................


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Platform and Operating System Requirements
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


The OpenGamma Platform is written in pure Java, and thus can in theory work on any system with a working JRE.

That being said, our preferred deployment platform is Linux, and we recommend that to new installations. As part of our ongoing development and testing, we regularly test the system on:

*  Fedora 14


*  RedHat Enterprise Linux (and CentOS) 5.4 and 5.5


*  Windows Server 2008


*  Windows 7


*  Mac OS X 10.6 (Snow Leopard), 10.7 (Lion) probably works once Java installed, not tested though


Hardware requirements vary depending on application but a reasonable absolute minimum for testing and evaluation is:


*  At least 4 physical 1.8GHz\+ cores


   *  These must be *physical* cores, not simulated ones using Hyper-Threading


   *  Development use requires fewer cores, though at least a dual core machine is necessary for realistic development


   *  The aggressively multi-threaded nature of all OpenGamma components benefits drastically from higher core machines


*  8GB RAM


*  10GB Disk


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Message-Oriented Middleware Requirements
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Our current reference implementation is built against ActiveMQ, although any other JMS provider should prove easy to swap in. The system is designed to not be tied specifically to JMS either, most of the messaging is via abstract classes in the OG-Util project so it should be easy to use most other messaging clients, e.g. AMQP systems such as RabbitMQ, SonicMQ and proprietary systems like TIBCO, 29West, etc.

~~~~~~~~~~~~~~~~~~~~~
Database Requirements
~~~~~~~~~~~~~~~~~~~~~


OpenGamma's reference Open Source database, and what we use for the majority of our testing, is PostgreSQL 8.4. Testing and the demo database installation is performed on the embedded Java database HSQL 2.2.5.

The following table shows a summary of support OpenGamma currently provides for major databases and versions.



+----------------------+----------------+---------+
| Component            | PostgreSQL 8.4 | Vertica |
+======================+================+=========+
| Security Master      | Yes            | No      |
+----------------------+----------------+---------+
| Portfolio Master     | Yes            | No      |
+----------------------+----------------+---------+
| Position Master      | Yes            | No      |
+----------------------+----------------+---------+
| Holiday Master       | Yes            | No      |
+----------------------+----------------+---------+
| Exchange Master      | Yes            | No      |
+----------------------+----------------+---------+
| Config Master        | Yes            | No      |
+----------------------+----------------+---------+
| Function cost Master | Yes            | No      |
+----------------------+----------------+---------+
| Time Series Storage  | Yes            | Planned |
+----------------------+----------------+---------+
| Batch Risk           | Yes            | Yes     |
+----------------------+----------------+---------+



...................
Client Requirements
...................


In general, OpenGamma client components are designed to be as light-weight as possible, to push as much processing as possible onto the back-end. Your custom applications may do significant processing on the desktop, but OpenGamma components as a rule do not.

More important than CPU, disk, or RAM for OpenGamma client components is bandwidth and latency to the back-end components when running many custom views or streaming real-time results. Operation over WAN and VPN links is possible, and the OpenGamma client management components will gracefully degrade in this situation, but it's not ideal. Full-time remote sites warrant a custom installation with some OpenGamma Platform components on-site at the remote location.

~~~~~~~~~~~~~~~~~~~~~~~
Web Client Requirements
~~~~~~~~~~~~~~~~~~~~~~~


The OpenGamma Web Client is a web front-end onto the OpenGamma Platform designed using modern technologies for fairly recent, standards-compliant browsers. While it may work on browsers not tested, performance may be seriously degraded. Based on our experience, we strongly recommend Google Chrome, Safari, or Firefox 4\+ for the best experience when using the most complex parts of the web client.

The following table shows a summary of our experience supporting different common browsers and versions:



+-------------------+------------+------------+--------------------------------------------------------------------------------------------------------------------------------------+
| Browser           | Version(s) | Supported? | Notes                                                                                                                                |
+===================+============+============+======================================================================================================================================+
| Google Chrome     | 8\+        | Yes        | Likely to work with older Chrome versions, but Chrome auto-updates rather quickly and <9.0 versions are rare                         |
+-------------------+------------+------------+--------------------------------------------------------------------------------------------------------------------------------------+
| Firefox           | 3.6\+      | Yes        | New development will not work with Firefox 3.5 and lower, but some existing developer-only screens will still support 3.5 and below. |
+-------------------+------------+------------+--------------------------------------------------------------------------------------------------------------------------------------+
| Safari            | 5\+        | Yes        |                                                                                                                                      |
+-------------------+------------+------------+--------------------------------------------------------------------------------------------------------------------------------------+
| Internet Explorer | 6          |  **No**    |                                                                                                                                      |
+-------------------+------------+------------+--------------------------------------------------------------------------------------------------------------------------------------+
| Internet Explorer | 7          |  **No**    |                                                                                                                                      |
+-------------------+------------+------------+--------------------------------------------------------------------------------------------------------------------------------------+
| Internet Explorer | 8          | Yes        | May be some performance issues if spark lines are turned on in the analytics view                                                    |
+-------------------+------------+------------+--------------------------------------------------------------------------------------------------------------------------------------+
| Internet Explorer | 9          | Yes        |                                                                                                                                      |
+-------------------+------------+------------+--------------------------------------------------------------------------------------------------------------------------------------+



````````````````````````````
Web Client on Mobile Devices
````````````````````````````


OpenGamma does support Mobile Safari (iPad and iPhone) with most of our UI, although when using the `The Analytics Viewer </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Web-Interface/The-Analytics-Viewer/index.rst>`_ , you may find performance poor unless you disable 'sparklines' on the toolbar. Also, because the analytics viewer uses an embedded scrollable area, it requires two-fingered dragging to scroll it. We hope to address this with Mobile Safari specific markup in the near future.

There is no reason that the client should not run correctly on other mobile browsers (e.g. Blackberry Playbook, Android 2.x and 3.x, etc) but they're not currently tested. We'd be interested to hear how the UI performs on those platforms.
