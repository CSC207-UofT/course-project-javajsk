# Project JavaJSK - UofTruck

University of Toronto Food truck ordering system. 
For more information see our [design document](design_document.md).

# Problem specification
Our problem that we intend to solve and it's specification can be found [here](/specification.md).

RESTAPI information can be found in the [endpoints](endpoints.md) file. This contains important information on how to access the different endpoints and what they require. (Only backend information).

# Progress Report
We are constantly updating the progress we make on this program, to get the latest information
please view the document listed as [progress_report](progress_report.md).

# Running the System
In order to run the system a few things are required. 
This system uses Java, Javascript, React and MongoDB. The mongoDB settings can be changed in the config files.

### Backend
The backend is writen entirely on Java and requires maven, spring-boot, and JSONObject. More specific depednencies can be seen in the github
dependency graph. Please ensure Java 11 is used. The spring-boot dependencies can be found in the [pom.xml](pom.xml) file.

_Note: The JSONObject library has caused a few minor issues when running the project. Reinstalling the library usually fixes the issue._

### Front End
In order to setup the front end, Node package manager is required and react scripts are required. In addition, the following packages are required for the front end:
`axios`, `react-boostrap` and `jwt-decode`. More specifics can be found in [package_lock.json](/uoftruck-front-end/package-lock.json).

Simply call `npm start` on in the [uoftruck-front-end](uoftruck-front-end) folder.

# Unit Test
All of our backend java contains unit tests which cover around ~73% of the main methods and functionality used to run the program.
These tests can be found in the [/src/test](/src/test) folder.
