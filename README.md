**DOUGLAS QA Challenge**

`Automation suite for validation Login feature`

### **Getting Started**

`This is a Maven project with JUnit as the test runner. I have used Cucumber framework for writing BDD scenarios. Also I have used IntelliJ as the IDE as it has built in support for writing Java Cucumber features.`

###**Prerequisites**

`IntelliJ, Maven and Google Chrome (To run the tests)`

###**Running Test**

`Open the project in IntelliJ or your desired IDE and run the following command to execute the tests.`

`mvn clean verify` - This command will run entire test suite and generate the cucumber report with all the test results

`mvn verify -Dcucumber.options="-t @login"` - This command will run only the scenarios which has a tag @login. 

###**Test Results**

`Test results will be available in the folder \target\cucumber-report-html\cucumber-html-reports`
