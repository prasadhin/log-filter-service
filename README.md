# Log Filter Service
Service to filter logs based on differnt criterias.

## Story / Summary of project
As a developer
I need to be able to filter log extracts
 - by country
 - by country where the response time was above a certain limit
 - where the response time is above average
so that I can debug issues effectively.

A log extract file contains a header line, followed by zero or more data lines, in comma-separated value format. The
first column is the Unix timestamp of the time the request was made, the second column is the country from which the
request originated, and the third column is the time in milliseconds which the request took to complete. The data lines
are not sorted.

####An example Input file is:

    REQUEST_TIMESTAMP,COUNTRY_CODE,RESPONSE_TIME
    1433190845,US,539
    1432917066,GB,37

We have included a sample log extract and the interface you are required to implement for the solution.

### Code Highlights
* 100% code coeverage with JUnit5 Tests

### Tech stack
* JDK 11
* Build Tool Used: Maven
* Testing: Junit5

## Implementation & Components Used
* Model - ProcessorLogs, used to represent data loaded from log file
* Interface - Method signatures for Filtering logs using various criteria
* Implementation - Method implementations for the abstract file filter methods
* Utility - File reader and incoming log files Loader.
* Junit5 - 100% code coverage unit test cases is available in test folder and can be run with mvn.

###Note
Package name is left undisturbed, as it is.
Project name or channel name is not included in package to keep the structure intact.

## Application test commands
* mvn clean test


