package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

 // The base class for all test classes.
public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

     // Sets up the ExtentReports instance and attaches the HTML reporter before the entire test suite runs.
     // This method ensures that the report is initialized only once for the entire suite.
    @BeforeSuite
    public void setupSuite() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
        extent.attachReporter(spark);
    }

     // Sets up the WebDriver for each test method.
     // This method is executed before every @Test method. It gets the WebDriver instance,
     // maximizes the browser window, and navigates to the base URL of the application.
    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.iciparis.ge/ge/");
    }

    // Closes the WebDriver instance after each test method is completed.
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    // Flushes the ExtentReports instance to finalize and save the report after the entire test suite is completed.
    @AfterSuite
    public void tearDownSuite() {
        extent.flush();
    }
}