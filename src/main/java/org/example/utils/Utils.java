package org.example.utils;

public class Utils {


     // Logs an informational message to both the console and the current ExtentReports test.
     // This is useful for providing context about the test execution flow.
    public static void logInfo(String message){
        if(ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().info(message);
        }
        System.out.println(message);
    }

     // Logs a failure message to both the console and the current ExtentReports test.
     // This method should be used when a test step fails to indicate a negative outcome.
    public static void logFail(String message){
        if(ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().fail(message);
        }
        System.out.println(message);
    }

     // Logs a success message to both the console and the current ExtentReports test.
     // This method should be used when a test step passes successfully.
    public static void logPass(String message){
        if(ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().pass(message);
        }
        System.out.println(message);
    }

     // A simple logging method that prints a message to the console only.
     // It does not interact with the ExtentReports instance.
    public static void log(String message) {
        System.out.println(message);
    }
}