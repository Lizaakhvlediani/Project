package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


 // This class uses a Singleton design pattern
 // ensure that only one instance of the WebDriver is created and used throughout the test execution.
public class DriverManager {
    private static WebDriver driver;

  //This method ensures that all test classes share the same browser session.
    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }

    // Closes the WebDriver instance and sets it to null.
    // This method should be called after a test is completed to properly shut down the browser and free up resources.
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}