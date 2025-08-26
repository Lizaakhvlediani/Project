# Project: ICI Paris Automation Testing

## Description
This project is an automation testing framework for the [ICI Paris Georgia website](https://www.iciparis.ge/ge/).  
It covers both **UI tests** using Selenium WebDriver and **API tests** using Rest Assured.  
The goal of this project is to verify key functionalities of the site, such as login, logout, searching products, navigating branches, and testing skincare-related pages.

## Technologies Used
- **Java**
- **Selenium WebDriver** (UI automation)
- **Rest Assured** (API testing)
- **TestNG** (test management & execution)
- **Maven** (build and dependency management)

## Project Structure
Project
├─ main
│ ├─ java
│ │ └─ org.example
│ │ ├─ pages
│ │ │ ├─ BranchesPage.java
│ │ │ ├─ LoginPage.java
│ │ │ ├─ LogoutPage.java
│ │ │ ├─ SearchPage.java
│ │ │ └─ SkincarePage.java
│ │ ├─ utils
│ │ │ ├─ ApiMethod.java
│ │ │ ├─ ApiMethods.java
│ │ │ ├─ DriverManager.java
│ │ │ ├─ ExtentReportManager.java
│ │ │ ├─ TestListener.java
│ │ │ └─ Utils.java
│ │ ├─ BasePage.java
│ │ └─ BaseTest.java
├─ test
│ └─ java
│ └─ org.example.tests
│ ├─ ApiTest.java
│ ├─ BranchesTest.java
│ ├─ IciParisApiTest.java
│ ├─ LoginTest.java
│ ├─ LogoutTest.java
│ ├─ SearchTest.java
│ └─ SkincareTest.java
└─ resources
## Prerequisites
Make sure you have the following installed on your system:
- Java JDK 8 or higher
- Maven 3.6+
- A browser (Chrome/Firefox) and WebDriver for Selenium


## Installation & Running Tests
bash
Clone the repository:
git clone <repository-url>

Navigate to the project folder:
cd Project

Install dependencies using Maven:
mvn clean install

Running Tests
UI Tests (Selenium + TestNG)

Run all tests:
mvn test

Run a specific test class (e.g., LoginTest):
mvn -Dtest=LoginTest test

API Tests (Rest Assured)

Run all API tests:
mvn test

Reporting
Test execution reports are generated using ExtentReports and can be found in the test-output folder.

Author - Lizi Axvlediani
