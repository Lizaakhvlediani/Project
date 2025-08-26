package org.example.pages;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import org.example.BasePage;
import org.example.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    private ExtentTest test;

    public LoginPage(WebDriver driver, ExtentTest test) {
        super(driver);
        this.test = test;
        PageFactory.initElements(driver, this);
    }

    // "ჩემი ისი პარი" Button
    @FindBy(css = "a[title='ჩემი ისი პარი']")
    private WebElement myIciParisButton;

    // Email input
    @FindBy(xpath = "//label[text()='ელ.ფოსტა']/following-sibling::input")
    private WebElement emailInput;

    // Password input
    @FindBy(css = "input[type='password']")
    private WebElement passwordInput;

    // "შესვლა" Button
    @FindBy(css = "button.standard-button")
    private WebElement loginButton;

    // Clicks the "ჩემი ისი პარი" button to open the login form popup.
    public void openLoginForm() {
        clickToElement(myIciParisButton);
        test.log(Status.INFO, "Clicked on 'ჩემი ისი პარი' button.");
    }

    // Enters the provided email address into the email input field.
    public void enterEmail(String email) {
        enterText(emailInput, email);
        test.log(Status.INFO, "Entered email: " + email);
    }

    // Enters the provided password into the password input field.
    public void enterPassword(String password) {
        enterText(passwordInput, password);
        test.log(Status.INFO, "Entered password.");
    }

    // Clicks the "Login" button to submit the login form.
    public void clickLogin() {
        clickToElement(loginButton);
        test.log(Status.INFO, "Clicked on 'შესვლა' button.");
    }

    // Performs a complete login action by entering the email and password and clicking the login button.
    public void login(String email, String password) {
        openLoginForm();
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
}
