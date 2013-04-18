title: Calling OpenGamma functions from VBA
shortcut: DOC:Calling OpenGamma functions from VBA
---
Once the `OpenGamma Tools for Microsoft Excel </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Tools-for-Microsoft-Excel/index.rst>`_  add-in is installed you will be able to add it as a reference to a VBA project within a Workbook. From the Visual Basic Editor use the *Tools* menu and then *References ...*, check the box alongside *OpenGamma Tools for Microsoft Excel Type Library* and press *OK*.

Four functions are now available through which the OpenGamma environment can be accessed.

*  `BeginExecute`_ 


*  `Execute`_ 


*  `CalculationModeLocking`_ 


*  `CalculationModeLocking`_ 


Instead of calling these directly, it is often simpler to import the *OpenGammaVBA.bas* module found in the Examples\Full Demos folder of the distribution. This contains VBA wrappers for all of the main functions described in this User's Guide.

All parameters and return types from the wrappers (and the underlying functions) are declared as *Variant*. This is to allow the same level of flexibility as when using the functions from a worksheet - for example a function expecting a set of identifiers could be given either a string or a bundle created by calling a function such as `Excel Functions - E </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Tools-for-Microsoft-Excel/Excel-Functions---E/index.rst>`_ .

Rich data structures that are returned by worksheet functions as a string such as *FXSecurity 3-58ED0330* return an object of type *IFudgeMsg*.  From this the `Fudge <http://www.fudgemsg.org/>`_  representation of the rich object can be inspected (the *OpenGammaVBA.bas* module has an example function to produce a long string representation of the data). For most practical uses, the content of the Fudge representation can be ignored; it is sufficient to assign it to/from local variables and use it as an input to other OpenGamma functions. Note that in this version it is not possible to convert an *IFudgeMsg* VBA object to/from the string representation used by worksheet functions. This will be corrected in a future release.

Please refer to the example worksheets in the distribution for further information.

..................
Function Execution
..................


BeginExecute


~~~~~~~~~~~~
BeginExecute
~~~~~~~~~~~~


Starts asynchronous execution of an OpenGamma function.



+---------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Parameter     | Description                                                                                                                                                                          |
+===============+======================================================================================================================================================================================+
| bstrName      | Name of the function to execute. This must be the name of the function as described in this User's Guide and not the prefixed version used on worksheets by some local installations |
+---------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| pavParameters | Array of parameters to the function                                                                                                                                                  |
+---------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



The target function (if it exists) will begin to execute within the OpenGamma service, and the VBA function will return an *IOverlappedCall* handle. When the result of the underlying function is needed *WaitForResult* must be called on the handle. Asynchronous calls can be used when the underlying functions are time consuming and parallel execution is desired but the VBA environment lacks multi-threading capabilities to do so. For example, if your local Security Master is slow to respond to queries, code to fetch a number of security objects could be written using asynchronous functions as:



.. code::

    Dim c1 As IOverlappedCall
    Dim c2 As IOverlappedCall
    ...
    Dim cN As IOverlappedCall
    Set c1 = OpenGamma.BeginExecute("FetchSecurity", _identifier of first security_)
    Set c2 = OpenGamma.BeginExecute("FetchSecurity", _identifier of second security_)
    ...
    Set cN = OpenGamma.BeginExecute("FetchSecurity", _identifier of Nth security_)
    Dim security1, security2, ..., securityN
    Set security1 = c1.WaitForResult
    Set security2 = c2.WaitForResult
    ...
    Set securityN = cN.WaitForResult




For most applications, the increased complexity of asynchronous calls is unnecessary and either `Execute`_  or the wrappers from *OpenGammaVBA.bas* are preferable.

Execute


~~~~~~~
Execute
~~~~~~~


Executes an OpenGamma function.



+---------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Parmaeter     | Description                                                                                                                                                                          |
+===============+======================================================================================================================================================================================+
| bstrName      | Name of the function to execute. This must be the name of the function as described in this User's Guide and not the prefixed version used on worksheets by some local installations |
+---------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| pavParameters | Array of parameters to the function                                                                                                                                                  |
+---------------+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



The target function (if it exists) will execute within the OpenGamma service and the result returned.

For time consuming functions (e.g. querying a slow database backend), some applications may benefit from making asynchronous calls to execute the functions in parallel (see `BeginExecute`_ ).

CalculationModeLocking


........................
Calculation Mode Locking
........................


The add-in contains an internal lock that can be acquired to prevent parts of an application from disrupting each other as they run. For example, overwriting functions called from a worksheet may execute concurrently with VBA macros. This is most noticeable when a VBA handler is written for worksheet update events and an overwriting function executes. The VBA handler may be invoked before the full overwriting behaviour has completed.

Overwriting functions (and some function evaluation chains) sometimes change the calculation mode of the worksheet. Most users run in *automatic* calculation mode, however for the duration of certain functions this is changed to *manual* to avoid intermediate recalculations and then restored to the *automatic* mode (unless the sheet started in the *manual* mode). To avoid conflict with VBA macros that may also use this method to suppress intermediate recalculation the *Calculation Mode Lock* is held for the duration of the change.

A VBA macro that needs to adjust the calculation mode of the worksheet temporarily should be written to first acquire the lock. Note that VBA is a single-threaded language and cannot block execution until a lock is acquired. The locking function therefore polls the lock status, returning `True` if it was locked, or `False` otherwise. For example:



.. code::

    Sub Example()
      Dim calcMode As XlCalculation
      If Not OpenGamma.LockCalculationMode Then
        Debug.Print "Couldn't lock calculation mode"
        Exit Sub
      End If
      calcMode = Application.Calculation
      Application.Calculation = xlCalculationManual
      On Error GoTo endSub
      ... operations requiring manual calculation mode
    endSub:
      Application.Calculation = calcMode
      OpenGamma.UnlockCalculationMode
    End Sub




In this example, the procedure does nothing if the lock is not available. This may be acceptable for macros associated with user controls (possibly with a better user feedback message for nothing happening). An alternative, used in some of the example workbooks when handling worksheet change events, is to schedule a retry of the failed operation:



.. code::

    Private Sub ActionCalledByEventHandler()
      Dim calcMode As XlCalculation
      If Not OpenGamma.LockCalculationMode Then
        Debug.Print "Couldn't lock calculation mode"
        Application.OnTime Now() + TimeValue("00:00:01"), "Sheet1.ActionCalledByEventHandler"
        Exit Sub
      End If
      calcMode = Application.Calculation
      Application.Calculation = xlCalculationManual
      On Error GoTo endSub
      ... operations requiring manual calculation mode
    endSub:
      Application.Calculation = calcMode
      OpenGamma.UnlockCalculationMode
    End Sub




Note that the locks are re-entrant. Once the lock is held, *LockCalculationMode* may be called again and will return `True`. An internal lock counter will have been increased. Each successful claim on the lock must be balanced by a call to *UnlockCalculationMode* or the lock may never be released and the system will no longer be usable. For this reason, error handling patterns, such as those in the examples above, should make sure that the locks are always released.
