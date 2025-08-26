package org.example.utils;

import jdk.jshell.execution.Util;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    // This method is called when a test method starts.
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.createTest(testName);
        Utils.logInfo("this is test name " + testName);
    }

    // This method is called when a test method succeeds.
    @Override
    public void onTestSuccess(ITestResult result) {
         Utils.logPass("Test Passed");
    }

    // This method is called when a test method fails.
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
    }

    // This method is called when a test method is skipped.
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    // This method is called after all tests in a test suite have been executed.
    // It flushes the ExtentReports, which writes all test information to the report file.
    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        ExtentReportManager.flushReports();
    }
}

