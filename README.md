## Synopsis

This project is a Selenium based test driven automation framework which aims to automate test scenarios for "PetClinic :: a Spring Framework demonstration" web application.

## Motivation

This project is prepared as a sample project for learning purposes.

## Framework Overview

1. This is a simple test driven Selenium - Java based framework that uses Maven as dependency builder tool.
2. The project is using TestNG framework that uses Page object design pattern along with PageFactory.
3. VeterinariansAppTests.java is the test module while VeterinariansApp.java is its page object.
4. WebDriver is instantiated when object of VeterinariansApp is created in VeterinariansAppTests.
5. Browser used is Firefox.
6. Test execution results are displayed in TestNG output format displaying Pass/Fail/ skipped summary.

## Installation

Simply clone the project in IDE.

## Assignment

I have created a UI Automation framework that automates following scenarios:
1) Verify image on home page
2) Find all the Veterinarians which are added in the application
3) Find all the existing owners which are added in application
4) Add a new owner, add pet for that owner.
5) Check all the information added for the newly created owner and pet is correct

## Prerequisites

The following items should be installed in your system:
1) Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
2) git command line tool (https://help.github.com/articles/set-up-git)
3) Execute the below mention command to run the application on local environment:
git clone https://github.com/spring-projects/spring-petclinic.git cd spring-petclinic
./mvnw spring-boot:run
4) After this you can access the application on: http://localhost:8080/
 
## Running Test Cases

Export this project in eclipse and execute the testing.xml to run the suite.
Or open command prompt and navigate to this project folder location. Then run following command in terminal.
mvn clean test -DsuiteXmlFile=testng.xml

## Owner

This Project is solely created by Vibhor Gaur (vibhorgaur8@gmail.com)
