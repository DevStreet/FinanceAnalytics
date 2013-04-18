title: Bi-temporal versioning using versions and corrections
shortcut: DOC:Bi-temporal versioning using versions and corrections
---
An important aspect of all the OpenGamma data stores is the extensive use of bi-temporal versioning.  Bi-temporal versioning means that not only can the user recover older versions of objects, but can also correct mistakes in both the current and older versions of objects.  Normal versioning is done with a simple date/time parameter to our queries, called a *version date*.  Queries just specifying this parameter simply return the latest version known at the date/time specified, but with all subsequent corrections applied up until that point.

It gets more complex though when we consider how the user might want to see the *original* version of an object rather than one with one or more *corrections* applied to it.  In OpenGamma we use a second date/time parameter to our queries called the *correction date*.  This tells the system to apply any subsequent corrections up to the specified date.  Is is the use of these two date/times that leads to the term *bi-temporal*.

...............................................
So what is the point of bi-temporal versioning?
...............................................

A key thing you can do with bi-temporal versioning is both exact reproduction of reports at future times, and *restatement* - a common reporting operation where you reproduce a report generated in the past, but applying any corrections to the input data you've since discovered.

..........
An Example
..........


#  On 1-Jan-2012 the US Dollar 1 Year Swap Rate is reported as closing at 0.411%.


#  On 2-Jan-2012 a daily VaR report is run on the portfolio as it was at the end of the previous day.


#  On 3-Jan-2012 a back-office reconciliation finds that a swap in the portfolio on the 1-Jan-2012 had the wrong notional and issued a portfolio correction.


#  On 5-Jan-2012 the data provider issues a correction to the 1 Year Swap Rate it gave on 1-Jan-2012 to 0.423%.


#  On 1-Feb-2012 The board of directors requires a restatement of the VaR on 1-Jan-2012.


Using bi-temporal versioning, the system is capable of producing both the restatement and the original report, depending on whether the *correction date* is set to 1-Jan-2012 or 1-Feb-2012 when querying the relevant databases.


