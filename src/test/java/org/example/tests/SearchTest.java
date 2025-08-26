package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.BaseTest;
import org.example.pages.LoginPage;
import org.example.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    private SearchPage searchPage;

    @BeforeMethod
    public void setupPageAndLogin() {
        test = extent.createTest("Search and Cart Verification Test");
        searchPage = new SearchPage(driver, test);

        // Authorization
        LoginPage loginPage = new LoginPage(driver, test);
        loginPage.login("liziaxvlediani@gmail.com", "Elisabedi123");
        test.info("Logged in successfully.");
    }

    @Test(priority = 1)
    public void searchAddNarcisoAndVerifyCart() {

        // Search from My Iciparis page
        test.info("Searching 'Narciso' from My Iciparis page.");
        searchPage.searchFromMyIciparis("Narciso");

        // Click on the first product
        searchPage.clickFirstResult();

        // Add to cart
        searchPage.clickAddToCartButton();
        test.pass("'Narciso' successfully added to cart.");

    }
}
