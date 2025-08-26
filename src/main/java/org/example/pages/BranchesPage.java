package org.example.pages;

import org.example.BasePage;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

 // Represents the Branches page and related actions on the website. This class contains methods
public class BranchesPage extends BasePage {

    private final ExtentTest test;
    private final WebDriverWait wait;

    // Locators for different page elements
    private final By footerBranchesLink = By.xpath("//footer//a[contains(normalize-space(),'ფილიალ')]");
    private final By branchesHeader = By.xpath("//h1[contains(normalize-space(),'ფილიალ')] | //h2[contains(normalize-space(),'ფილიალ')]");
    private final By footerPaymentMethodsLink = By.xpath("//li[a[text()='გადახდის მეთოდები']]/a");
    private final By paymentMethodsHeader = By.xpath("//h1[text()='გადახდის მეთოდები'] | //h2[text()='გადახდის მეთოდები']");


    // Constructor for BranchesPage.
    // Initializes the WebDriver and ExtentTest objects.
    public BranchesPage(WebDriver driver, ExtentTest test) {
        super(driver);
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // This ensures all elements on the page are loaded and scripts have finished executing.
    public void waitForPageReady() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(d ->
                ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete")
        );
        test.info("Page readyState is COMPLETE");
    }

    // Scrolls to the bottom of the page.
    public void scrollToBottomStable() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long lastHeight = ((Number) js.executeScript("return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);")).longValue();

        for (int i = 0; i < 20; i++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            sleep(500);
            long newHeight = ((Number) js.executeScript("return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);")).longValue();
            if (newHeight == lastHeight) {
                test.info("Reached page bottom (height stabilized at " + newHeight + ")");
                break;
            }
            lastHeight = newHeight;
        }
    }

    // Scrolls the page to bring a specific element into the center of the viewport.
    // This ensures the element is visible and can be interacted with.
    private void scrollToElement(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", el);
        test.info("Scrolled into view: " + locator);
    }

    // Clicks an element. It first tries a standard click and if it fails due to an interception
    // it retries using JavaScript to force the click.
    private void safeClick(By locator) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            el.click();
            test.info("Clicked element: " + locator);
        } catch (ElementClickInterceptedException | TimeoutException e) {
            test.warning("Normal click failed, trying JS click: " + e.getClass().getSimpleName());
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            test.info("JS-click performed: " + locator);
        }
    }

     // Navigates to the Branches page by clicking the link in the footer.
     // It scrolls to the bottom of the page to ensure the footer is visible.
    public void goToBranchesFromFooter() {
        scrollToBottomStable();
        scrollToElement(footerBranchesLink);
        safeClick(footerBranchesLink);
    }

     // Waits for the Branches page to open by checking for a specific URL and header.
     // @return true if the URL and header are found, otherwise returns false.
    public boolean waitForBranchesPageOpen() {
        boolean urlOk = wait.until(ExpectedConditions.urlContains("/page/branches"));
        test.info("URL contains /page/branches: " + urlOk);

        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(branchesHeader));
            test.pass("Branches header visible: " + header.getText());
            return urlOk && header.isDisplayed();
        } catch (TimeoutException e) {
            test.warning("Branches header was not found, relying on URL only.");
            return urlOk;
        }
    }

     // Helper method to pause test execution for a specified duration in milliseconds.
    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}