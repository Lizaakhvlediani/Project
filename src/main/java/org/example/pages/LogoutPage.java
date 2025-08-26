package org.example.pages;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends BasePage {

    private ExtentTest test;

    public LogoutPage(WebDriver driver, ExtentTest test) {
        super(driver);
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    // Profile icon
    @FindBy(css = "i.mdi-account")
    private WebElement accountMenu;

    // "გამოსვლა" Button
    @FindBy(xpath = "//div[@class='v-list-item__title' and text()='გამოსვლა']")
    private WebElement logoutButton;

    // Clicks the account menu button to open the user's account dropdown.
    public void openAccountMenu() {
        clickToElement(accountMenu);
        test.log(Status.INFO, "Opened account menu.");
    }

    // Clicks the "Logout" button to sign the user out of their account.
    public void clickLogout() {
        clickToElement(logoutButton);
        test.log(Status.INFO, "Clicked on 'გამოსვლა' button.");
    }

    // Performs a complete logout action.
    // This method combines the steps of opening the account menu and then clicking the logout button.
    public void logout() {
        openAccountMenu();
        clickLogout();
    }
}
