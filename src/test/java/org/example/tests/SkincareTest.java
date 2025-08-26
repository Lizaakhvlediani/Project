package org.example.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.BaseTest;
import org.example.pages.SkincarePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class SkincareTest extends BaseTest {

    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setupExtent() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("SkincareTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("Skincare Category Test");
    }

    @Test
    public void openSkincareCategory() {
        SkincarePage skincarePage = new SkincarePage(driver, test);

        // Click on "კანის მოვლა"
        skincarePage.clickSkincare();

        // We check that the page opened with the correct URL
        boolean isUrlCorrect = skincarePage.isCorrectUrlOpened();
        Assert.assertTrue(isUrlCorrect, "The URL is incorrect. The skincare page did not open.");

        // Additionally, you can check that the element is also visible
        boolean isOpened = skincarePage.isSkincarePageOpened();
        Assert.assertTrue(isOpened, "Skin care' page not opened!");

        test.pass("The 'Skin Care' page has been successfully opened with the correct URL.");
    }

    // Flushes the ExtentReports instance to finalize and save the report after all tests are completed.
    @AfterClass
    public void teardownExtent() throws IOException {
        extent.flush();
    }
}