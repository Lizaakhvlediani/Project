package org.example.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.BaseTest;
import org.example.pages.LoginPage;
import org.example.pages.LogoutPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogoutTest extends BaseTest {

    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setupExtent() {
        ExtentSparkReporter spark = new ExtentSparkReporter("LogoutReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Test
    public void testLogout() {
        test = extent.createTest("Logout Test");

        // Login
        String email = "Liziaxvlediani@gmail.com";
        String password = "Elisabedi123";

        LoginPage loginPage = new LoginPage(driver, test);
        loginPage.login(email, password);

        // Waiting for the URL to change
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://www.iciparis.ge/ge/my-iciparis"));

        // Logout
        LogoutPage logoutPage = new LogoutPage(driver, test);
        logoutPage.logout();

        // Waiting to return to the home page
        wait.until(ExpectedConditions.urlToBe("https://www.iciparis.ge/ge/"));

        // Check if it has returned to the homepage
        String actualUrl = driver.getCurrentUrl();
        if (actualUrl.equals("https://www.iciparis.ge/ge/")) {
            test.pass("Logout successful. Current URL: " + actualUrl);
        } else {
            test.fail("Logout failed. Current URL: " + actualUrl);
        }

        Assert.assertEquals(actualUrl, "https://www.iciparis.ge/ge/", "Logout did not return to home page");
    }

    // Flushes the ExtentReports instance to finalize and save the report after all tests are completed.
    @AfterClass
    public void tearDownExtent() {
        if (extent != null) {
            extent.flush();
        }
    }
}
