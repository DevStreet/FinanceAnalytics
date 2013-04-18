title: Installing 32-bit R modules on 64-bit Windows
shortcut: DOC:Installing 32-bit R modules on 64-bit Windows
---
Running 32-bit installers can sometimes fail when R is installed on 64-bit Windows because of automatic path translations intended to isolate 32 and 64 bit programs. This article outlines the cause of the problem and gives a possible workaround.

The default R installation goes to `%ProgramFiles%\R`, which includes both the 32-bit and 64-bit elements. The 32-bit registry value
`SOFTWARE\R-Core\R32\InstallPath` has the correct path to the R installation. When read by the Windows Installer this path gets translated to something like `%ProgramFiles(x86)%\R\R-_version_` which is not a valid path. It is not possible for us to override this behaviour of the installer.

For example running the 32-bit OpenGamma for R installation can fail with an error message stating that an R installation is required despite R being installed.

For the OpenGamma R tools, our 64-bit distribution includes both 32-bit and 64-bit binaries. This can be installed without issue. If you only have the 32-bit distribution (for example as part of a large package bundle) then the workaround below will work. This should also work for other 32-bit R packages supplied as MSI packaged binaries from other vendors.

After installing R, create a junction point in the 32-bit program folder. From an administrative command prompt go to `%ProgramFiles(x86)%` and type:



.. code::

    mklink /D R "%ProgramFiles%\R"




Any 32-bit windows installers will then be able to see the correct R installation folder despite the automatic path translation.

