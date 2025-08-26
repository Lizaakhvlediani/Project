package org.example.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.BaseTest;
import org.example.pages.BranchesPage;
import org.testng.Assert;
import org.testng.annotations.*;

//Test class for validating the functionality of the Branches page on iciparis.ge.
public class BranchesTest extends BaseTest {

    private ExtentReports extent;
    private ExtentTest test;
    private String reportPath;


    //Initializes the ExtentReports instance and attaches the HTML reporter before any tests run.
    @BeforeClass
    public void setupExtent() {
        reportPath = System.getProperty("user.dir") + "/BranchesTestReport.html";
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

    }

    // Test case to verify that clicking the "Branches" link in the footer successfully navigates to the correct branches page.
    @Test

    public void goToBranchesFromFooter_opensBranchesPage() {
        test = extent.createTest("Branches page test");
        driver.get("https://www.iciparis.ge/ge/");
        test.info("Opened home page");

        BranchesPage page = new BranchesPage(driver, test);
        page.waitForPageReady();
        page.goToBranchesFromFooter();
        boolean opened = page.waitForBranchesPageOpen();
        Assert.assertTrue(opened, "Branches page did not open correctly.");
        test.pass("Branches page opened successfully");
    }

    // Flushes the ExtentReports instance to finalize and save the report after all tests are completed.
    @AfterClass
    public void teardownExtent() {
        extent.flush();
    }
}