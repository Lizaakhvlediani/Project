package org.example.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.BaseTest;
import org.example.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

// Test class for validating user login functionality on the iciparis.ge website.
// This class uses TestNG for test execution and ExtentReports for detailed reporting.
public class LoginTest extends BaseTest {

    private ExtentReports extent;
    private ExtentTest test;

    // Sets up the ExtentReports instance and attaches the HTML reporter before the test class runs.
    // The report will be saved as "ExtentReport.html" in the project's root directory.
    @BeforeClass
    public void setupExtent() {
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    // Test case to confirm successful login with correct authentication data.
    @Test
    public void testValidLogin() {
        test = extent.createTest("Valid Login Test");

        String email = "Liziaxvlediani@gmail.com";
        String password = "Elisabedi123";

        test.info("Starting login test for user: " + email);

        // LoginPage object using ExtentTest
        LoginPage loginPage = new LoginPage(driver, test);

        // Login
        loginPage.login(email, password);

        // Waiting for URL change
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://www.iciparis.ge/ge/my-iciparis"));

        // Assert URL
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.equals("https://www.iciparis.ge/ge/my-iciparis")) {
            test.pass("Login successful. Current URL: " + actualUrl);
        } else {
            test.fail("Login failed. Current URL: " + actualUrl);
        }

        Assert.assertEquals(actualUrl, "https://www.iciparis.ge/ge/my-iciparis", "URL did not match");
    }

    // Flushes the ExtentReports instance to finalize and save the report after all tests are completed.
    @AfterClass
    public void tearDownExtent() {
        if (extent != null) {
            extent.flush();
        }
    }
}
