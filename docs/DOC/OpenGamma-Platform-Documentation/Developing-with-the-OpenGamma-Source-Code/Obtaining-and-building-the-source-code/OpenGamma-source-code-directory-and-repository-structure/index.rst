title: OpenGamma source code directory and repository structure
shortcut: DOC:OpenGamma source code directory and repository structure
---
While OpenGamma is primarily an Open Source platform, we do have some proprietary components only available to commercial customers. In addition, our `Support </confluence/DOC/OpenGamma-Platform-Documentation/Support/index.rst>`_  methodology allows us to share code directly with customers to provide hands-on, proactive support. Unfortunately `Git is really not designed to support a mixed public/private model <http://www.opengamma.com/blog/2012/03/13/public-private-java-project-using-git>`_ , so we've partitioned the source code across a range of git repositories to allow us maximum flexibility in composing the system.

The basic unit of these partitions are the component projects that make up OpenGamma.  Projects form a moderately complex tree of dependencies between modules (primarily managed by Eclipse and Ivy). The way we manage the public/private split is to have a single repository for all the Open Source projects, and then separate repositories for each of our customer and proprietary component projects.

The single Open Source repository also acts as a top-level project, called OG-Platform.  OG-Platform contains the common top-level build files and configuration.

*  OG-Platform


   *  `common/` contains common build tasks, properties files and Eclipse setup files


   *  `projects/` contains all the sub-projects, including both public and private projects


   *  `install/` default location of deployment directory when `ant install` is run


   *  `build.xml` top-level Ant build file


   *  `lib/` where ivy artifacts are placed during a resolve


   *  `doc`


   *  various other text files


The idea is that you clone OG-Platform and then if required, run an ant task (called 'clone-or-pull') to pull each required sub-project into the projects directory underneath it.  This, however, leaves the question of how the ant task knows which repositories it has permission to access.  For commercial customers, using some of our extra git repositories, this is achieved by an initialisation phase where the user provides a username and password supplied by OpenGamma to access a custom version of the file `common/build-projects.xml`.  This custom version will then clone (or pull) the extra projects into the `projects/` sub-directory.

Note that the clone-or-pull step does not now require the use of any extra repositories as part of the Open Source release, and no initialisation phase is required any more.  This was primarily to simplify the experience for those evaluating the platform.
