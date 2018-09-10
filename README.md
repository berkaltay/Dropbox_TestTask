# Dropbox Test Task

Dropbox Test Automation for 5 Test Cases

## Getting Started

User may check-in all the available source code to an IDE for testing purposes.

### Prerequisites

```
JAVA JDK 1.8
an IDE (IntelliJ is strongly suggested for auto-import of "jars")

```

### Installing

JDK should be installed, JAVA_HOME should be configured properly.

## Running the tests

Use testng.xml to trigger the test cases
- it's prepared to run in parallel to reduce the test run times

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Author

* **Berk Altay** - *Initial work*

## Tech Stack
* Written in JAVA
* Used Selenium WebDriver to drive Chrome browser
* Used MAVEN for build management
* TestNG for test orchestration
* Page Object Pattern is followed

## Test Cases:
* Login/Logout functionality
  - user credentials parameterized in Constants.java class
* Upload file functionality
* Search file functionality
* Rename file functionality
* Create folder functionality

