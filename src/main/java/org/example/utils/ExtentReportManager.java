package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Create Singleton ExtentReports
    public static ExtentReports getExtentReports() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/report/ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Your Name");
        }
        return extent;
    }

    // Manage Test in ThreadLocal
    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    // Get Test from ThreadLocal
    public static ExtentTest getTest() {
        return test.get();
    }

    // Creating and setting up ThreadLocal Test
    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = getExtentReports().createTest(testName);
        setTest(extentTest);
        return extentTest;
    }

    // Flush reports
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
