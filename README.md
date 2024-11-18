# Automated Mobile Testing Framework

## Overview
This project is a Java-based automated testing framework designed for mobile application testing using Cucumber, JUnit, and Appium. It integrates various tools and libraries to facilitate robust and efficient mobile test automation.

## Project Structure
Below is the organized project structure:

```
/project
|-- /app                         # Contains the application files
|-- /src/test/java               # Root directory for automation code
    |-- /core                    # Contains configuration hooks, setup, reporting, and capability definitions
    |-- /map                     # Locator map using PageFactory for element definitions
    |-- /pages                   # Page classes that encapsulate interactions with the app
    |-- /steps                   # Cucumber step definitions calling methods from page classes
    |-- /utils                   # Auxiliary utility functions
```

### Directory Descriptions

- **/core**: This directory houses all core configurations and setup files for initializing Appium, setting up test capabilities, and generating reports.
- **/map**: Contains classes that map UI elements using `PageFactory.initElements` for easy locator management.
- **/pages**: Contains classes that define interactions with different pages of the app, encapsulating user actions and flows.
- **/steps**: Includes step definition files where Cucumber steps are mapped to actions within the page classes.
- **/utils**: Houses utility classes and functions that provide reusable logic across the framework.

## Key Dependencies
The project uses the following key dependencies as defined in the `pom.xml`:

- **JUnit**: For test execution.
- **Cucumber-Java & Cucumber-JUnit**: To write and run Cucumber feature files.
- **Selenium Java**: For WebDriver support and element interactions.
- **Appium Java Client**: To interact with mobile devices.
- **ExtentReports**: For generating comprehensive test reports.
- **Apache POI**: For handling Microsoft Office files (if needed for data-driven tests).
- **Log4j**: For logging and debugging.

### Full `pom.xml` Configuration:
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.saucelabs</groupId>
    <artifactId>saucelabs-mobile-automation-framework-java</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>automated-tests</name>
    <url>https://saucelabs.com/</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <cucumber.version>7.18.0</cucumber.version>
    </properties>

    <build>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.6.1</version>
                <configuration>
                    <encoding>UTF-8</encoding>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
                <configuration>
                    <runOrder>alphabetical</runOrder>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>2.22.2</version>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency>
            <groupId>org.junit.vintage</groupId>
            <artifactId>junit-vintage-engine</artifactId>
            <version>5.11.3</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.141.59</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.8.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.17.1</version>
        </dependency>
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20240303</version>
        </dependency>
        <dependency>
            <groupId>com.jayway.jsonpath</groupId>
            <artifactId>json-path</artifactId>
            <version>2.9.0</version>
        </dependency>
        <dependency>
            <groupId>com.sikulix</groupId>
            <artifactId>sikulixapi</artifactId>
            <version>2.0.5</version>
            <exclusions>
                <exclusion>
                    <groupId>com.github.vidstige</groupId>
                    <artifactId>jadb</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-nop</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>io.github.marcoslimaqa</groupId>
            <artifactId>sikulifactory</artifactId>
            <version>1.1.1</version>
            <exclusions>
                <exclusion>
                    <groupId>com.github.vidstige</groupId>
                    <artifactId>jadb</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>5.0.8</version>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>4.1.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>4.1.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-scratchpad</artifactId>
            <version>4.1.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml-schemas</artifactId>
            <version>4.1.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.11</version>
        </dependency>
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-all</artifactId>
            <version>1.3</version>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-path</artifactId>
            <version>4.0.0</version>
        </dependency>
        <dependency>


<dependency>
            <groupId>io.appium</groupId>
            <artifactId>java-client</artifactId>
            <version>8.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.22</version>
        </dependency>
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>5.3.1</version>
        </dependency>
    </dependencies>
</project>
```

## Setup and Configuration

### Prerequisites
Ensure that you have the following installed:
- **Java Development Kit (JDK)** 8 or later.
- **Maven** 3.6+.
- **Appium Server**.
- **Android/iOS SDKs** as needed.

### Environment Variables
Set the following environment variables for proper Appium operation:
- `JAVA_HOME`: Path to the JDK.
- `ANDROID_HOME`: Path to the Android SDK.
- Add the path to `platform-tools` and `tools` to your `PATH` variable.

### Installation
1. Clone the repository:
   ```bash
   git clone  https://github.com/raphaelpalhano/mobile_automation_saucelabs.git
   cd /mobile_automation_saucelab
   ```
2. Install Maven dependencies:
   ```bash
   mvn clean install
   ```

### Running Tests
- To execute tests using JUnit:
  ```bash
  mvn test -Dtest=Runner OR

   mvn clean test -Dtest=Runner -Dtest".environment=automation"

  ```
- To execute specific feature files or tags:
  ```bash
  mvn test -Dcucumber.options="--tags @tagName"
  ```


## Writing Tests
### Creating Feature Files
Place feature files in `src/test/resources/features`. Use the Gherkin syntax:
```gherkin
Feature: Example feature
  Scenario: Sample test scenario
    Given I launch the app
    When I perform an action
    Then I verify the result
```
### Adding Step Definitions
Map feature steps in `src/test/java/steps`. For example:
```java
@When("^I perform an action$")
public void performAction() {
    pageObject.actionMethod();
}
```

## Reporting
After test execution, reports are generated in `/target`. Access `extent-reports.html` for detailed test results.

