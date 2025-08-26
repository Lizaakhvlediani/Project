package org.example.pages;

import com.aventstack.extentreports.ExtentTest;
import org.example.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

// This class represents the "Skincare" page and its related functionalities.
// It contains locators and methods to interact with the page elements and verify page navigation.
public class SkincarePage extends BasePage {
    private ExtentTest test;

    // Selector for the Skincare link on the main page.
    private By skincareLink = By.xpath("//a[normalize-space()='კანის მოვლა']");

    // Selector to confirm opened Skincare
    private By skincareBreadcrumb = By.xpath("//a[@class='v-breadcrumbs__item' and normalize-space()='კანის მოვლა']");

    // Button for listing
    private By dropdownButton = By.cssSelector("div.sidebar-title i.mdi-chevron-down");

    // Select an item from a list by text
    private By dropdownOption(String optionText) {
        return By.xpath("//div[@class='listOption']/span[normalize-space()='" + optionText + "']");
    }

    //  Constructor for the SkincarePage class.
    //  Initializes the WebDriver and ExtentTest objects.
    public SkincarePage(WebDriver driver, ExtentTest test) {
        super(driver);
        this.test = test;
    }

    // Clicks on the "კანის მოვლა"
    public void clickSkincare() {
        click(skincareLink);
        test.info("Clicked on 'კანის მოვლა'");
    }

    // Checks if the "კანის მოვლა" page is successfully opened
    public boolean isSkincarePageOpened() {
        return isElementDisplayed(skincareBreadcrumb);
    }

    // To test the new URL method
    public boolean isCorrectUrlOpened() {
        String expectedUrl = "https://www.iciparis.ge/ge/products/36-kanis-movla";
        String actualUrl = driver.getCurrentUrl();
        test.info("Checking if URL is correct. Expected: " + expectedUrl + ", Actual: " + actualUrl);
        return actualUrl.equals(expectedUrl);
    }

    // Button for sidebar-title
    private By sidebarButton = By.cssSelector("div.sidebar-title i.v-icon.mdi-chevron-up");

    // Method for click a button
    public void clickSidebarButton() {
        WebElement button = driver.findElement(sidebarButton);
        // JavaScript click
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        test.info("Clicked on sidebar button using JS");
    }
}