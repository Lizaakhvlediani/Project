package org.example.pages;

import com.aventstack.extentreports.ExtentTest;
import org.example.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

// This class contains methods to interact with the search bar, search results, and add products to the cart.
public class SearchPage extends BasePage {

    private ExtentTest test;

    // Constructor for the SearchPage class.
    // Initializes the WebDriver and ExtentTest objects and loads the page elements.
    public SearchPage(WebDriver driver, ExtentTest test) {
        super(driver);
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    // Locators for different page elements
    private By searchInput = By.id("search"); // "მოძებნე პროდუქტი" field on My Iciparis page
    private By firstProductTitle = By.cssSelector("div.row-stream-title div.v-card__title.break-word");
    private By addToCartButton = By.cssSelector("button.add-to-cart.v-btn");
    private By cartButton = By.cssSelector("a[title='კალათა']");
    private By cartItems = By.cssSelector("div.cart-item-name"); // Product names in the cart


    // Navigates to the My Iciparis page and performs a product search.
    // It waits for the search input field to be clickable before entering the product name and submitting the search.
    public void searchFromMyIciparis(String productName) {
        driver.get("https://iciparis.ge/ge/my-iciparis");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        input.click();
        input.clear();
        input.sendKeys(productName);
        input.sendKeys("\n");

        if (test != null) test.info("Searched for product from My Iciparis: " + productName);
    }


     // Clicks on the first product displayed in the search results.
     // It uses a JavaScript click to handle potential overlay issues.
    public void clickFirstResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductTitle));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);
        if (test != null) test.pass("Clicked on first product result.");
    }


     // Clicks the "Add to Cart" button for a product.
     // It waits for the button to be clickable before performing the click using JavaScript.
    public void clickAddToCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
        if (test != null) test.pass("Clicked on 'Add to Cart' button.");
    }
}