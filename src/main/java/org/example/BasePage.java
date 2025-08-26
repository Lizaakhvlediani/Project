package org.example;

import org.example.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


 // The base class for all Page Object Model (POM) pages.
 // This class contains common WebDriver methods and utilities
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

     // Constructor for the BasePage class.
     // Initializes the WebDriver instance and a WebDriverWait object with a default timeout of 10 seconds.
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Waits for a specified WebElement to be visible and clickable on the page.
    public void waitForElementToBeClickable(WebElement locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        Utils.logInfo("Waiting " + locator + " if is it clickable");
    }

    // Clicks on a specified WebElement after waiting for it to be clickable.
    public void clickToElement(WebElement locator) {
        waitForElementToBeClickable(locator);
        locator.click();
        Utils.logInfo("Clicking on element " + locator);
    }

    // Clicks on a specified element identified by its By locator after waiting for it to be clickable.
    // This method is a convenient alternative to the WebElement-based click method.
    public void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        Utils.logInfo("Clicking on element with locator " + locator);
    }


    // Enters text into a specified WebElement after waiting for it to be visible.
    public void enterText(WebElement locator, String text) {
        waitForElementToBeVisible(locator);
        locator.sendKeys(text);
        Utils.logInfo("SendKey: [ " + text + " ] to element: " + locator);
    }

    // Waits for a specified WebElement to be visible on the page.
    public void waitForElementToBeVisible(WebElement locator) {
        wait.until(ExpectedConditions.visibilityOf(locator));
        Utils.logInfo("waiting element: " + locator);
    }

    // Waits for a specified element, identified by its By locator, to be visible on the page.
    public void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Utils.logInfo("waiting element: " + locator);
    }

    // Refreshes the current page.
    public void refreshPage() {
        driver.navigate().refresh();
        Utils.logInfo("Refreshing page: " + driver.getCurrentUrl());
    }


    // Gets the current URL of the page.
    public String getCurrentUrl() {
        Utils.logInfo("get current url:  " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    // Asserts that the actual URL is equal to the expected URL.
    public void assertEquals(String expectedUrl, String actualUrl) {
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    // Asserts that the actual URL is equal to the expected URL with a custom failure message.
    public void assertEquals(String expectedUrl, String actualUrl, String message) {
        Assert.assertEquals(actualUrl, expectedUrl, message);
    }

    // Gets the text from a specified WebElement after waiting for it to be visible.
    public String getTextFromElement(WebElement locator) {
        waitForElementToBeVisible(locator);
        Utils.logInfo("text found: " + locator.getText());
        return locator.getText();
    }

    // Gets the value of a specific CSS property from a WebElement after waiting for it to be visible.
    public String getCssValue(WebElement locator, String propertyName) {
        waitForElementToBeVisible(locator);
        Utils.logInfo("PropertyName is: " + propertyName);
        return locator.getCssValue(propertyName);
    }

     // Checks if an element identified by its By locator is displayed on the page.
     // This method is useful for verifying the presence of an element without causing a test failure if it's not found.
    public boolean isElementDisplayed(By locator) {
        try {
            waitForElementToBeVisible(locator);
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}