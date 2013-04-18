title: Quick-start guide
shortcut: DOC:Quick-start guide
---
These will only work if you have all the prerequisites in place.  If you have trouble with this sequence, or want to understand the steps, read the other pages in this section.

For a full overview of the prerequisites for developing with the OpenGamma source code, watch the [Prerequisites screencast|DOC:Obtaining and building the source code^OpenGamma_Prerequisites.mp4]. It covers installing Java, Ant, Git, and Eclipse.


.................................................
Building and running the latest build from GitHub
.................................................


If you have a github user account set up and you are free from a restrictive firewall you can clone the OpenGamma root project, build it, create and populate the example database, and start the example server using

On Linux:



.. code::

    git clone git://github.com/OpenGamma/OG-Platform.git
    cd OG-Platform
    ant install
    cd OpenGamma
    scripts/init-og-examples-db.sh
    scripts/og-examples.sh debug




On Windows:



.. code::

    git clone git://github.com/OpenGamma/OG-Platform.git
    cd OG-Platform
    ant install
    cd OpenGamma
    scripts\init-og-examples-db.bat
    scripts\og-examples.bat debug




Finally, point your browser at `http://localhost:8080 <http://localhost:8080>`_ .
if you have firewall restrictions on ssh connections and/or you don't have a GitHub account set up, try:
{code}
git clone https://github.com/OpenGamma/OG-Platform.git
{code}


.......................................................................................................................................
Building and running the latest release build with dependencies included (assuming you've downloaded and unzipped/untarred the release)
.......................................................................................................................................


On Linux:



.. code::

    cd OG-Platform
    ant install
    cd OpenGamma
    scripts/init-og-examples-db.sh
    scripts/og-examples.sh debug




On Windows:



.. code::

    cd OG-Platform
    ant install
    cd OpenGamma
    scripts\init-og-examples-db.bat
    scripts\og-examples.bat debug




Finally, point your browser at `http://localhost:8080 <http://localhost:8080>`_ . 
