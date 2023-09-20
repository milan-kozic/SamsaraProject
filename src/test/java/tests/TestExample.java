package tests;

import data.CommonStrings;
import data.Groups;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.WelcomePage;
import utils.DateTimeUtils;
import utils.WebDriverUtils;

@Test (groups={Groups.REGRESSION})
public class TestExample {

    @Test
    public void testSuccessfulLoginLogout() {

        WebDriver driver = null;

        String sUsername = "user";
        String sPassword = "password";
        String sExpectedSuccessMessage = CommonStrings.LOGOUT_SUCCESS_MESSAGE;

        try {

            driver = WebDriverUtils.setUpDriver();

            // Implicit Wait, Explicit Wait

            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typeUsername(sUsername);;
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typePassword(sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            //String sLoginButtonTitle = loginPage.getLoginButtonTitle();
            //System.out.println("Login Button Title: " + sLoginButtonTitle);

            loginPage.clickLoginButton();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            WelcomePage welcomePage = new WelcomePage(driver);
            //String sWelcomePageTitle = welcomePage.getPageTitle();
            //System.out.println("Page Title: " + sWelcomePageTitle);

            welcomePage.clickLogOutLink();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            Assert.assertTrue(loginPage.isSuccessMessageDisplayed(), "Success Message is NOT displayed!");

            String sActualSuccessMessage = loginPage.getSuccessMessage();
            Assert.assertEquals(sActualSuccessMessage, sExpectedSuccessMessage, "Wrong Success Message!");


        } finally {
            WebDriverUtils.quitDriver(driver);
        }
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {

        WebDriver driver = null;

        String sUsername = "user";
        String sPassword = "wrong_password";
        String sExpectedErrorMessage = CommonStrings.LOGIN_ERROR_MESSAGE;
        try {

            driver = WebDriverUtils.setUpDriver();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typeUsername(sUsername);;
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typePassword(sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.clickLoginButton();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            String sActualErrorMessage = loginPage.getErrorMessage();
            Assert.assertEquals(sActualErrorMessage, sExpectedErrorMessage, "Wrong Error Message!");

        } finally {
            WebDriverUtils.quitDriver(driver);
        }
    }
}
