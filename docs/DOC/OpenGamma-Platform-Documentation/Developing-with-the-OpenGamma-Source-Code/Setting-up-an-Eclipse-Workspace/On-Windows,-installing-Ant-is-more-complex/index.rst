title: On Windows, installing Ant is more complex
shortcut: DOC:On Windows, installing Ant is more complex
---
You windows you can either follow the `standard Apache Ant Installation guide <http://ant.apache.org/manual/install.html>`_  or you can use an installer like `WinAnt <http://code.google.com/p/WinAnt>`_  to make things easier.  We have discovered that unfortunately WinAnt has a bug when used with the more recent JDKs.  A simple fix for this is to

#  Open the Windows file explorer on your JDK installation directory.  It's usually in `C:\Program Files\Java\jdk1.7.0_03` or similar.  


#  Then open the `lib` folder, choose the file 'tools.jar', and copy it into the `lib` folder in the JRE folder (usually something like `C:\Program Files\Java\jre1.7.0_03`).

You should now be able to run ant from the Windows Command Prompt (cmd.exe).
