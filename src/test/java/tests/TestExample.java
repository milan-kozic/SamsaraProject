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

import java.util.ArrayList;

@Test (groups={Groups.REGRESSION})
public class TestExample extends BaseTestClass {


    @Test
    public void testSuccessfulLoginLogout() {

        log.info("[START TEST] testSuccessfulLoginLogout()");
        WebDriver driver = null;

        String sUsername = "user";
        String sPassword = "password";
        String sExpectedSuccessMessage = CommonStrings.LOGOUT_SUCCESS_MESSAGE;

        try {

            driver = setUpDriver();
            LoginPage loginPage = new LoginPage(driver).open();

            //DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typeUsername(sUsername);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

//            String sEnteredUserName = loginPage.getUsername();
//            log.info("USERNAME: " + sEnteredUserName);

            loginPage.typePassword(sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            //String sLoginButtonTitle = loginPage.getLoginButtonTitle();
            //System.out.println("Login Button Title: " + sLoginButtonTitle);

            WelcomePage welcomePage = loginPage.clickLoginButton(true);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            //String sWelcomePageTitle = welcomePage.getPageTitle();
            //System.out.println("Page Title: " + sWelcomePageTitle);

            welcomePage.clickLogOutLink();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            Assert.assertTrue(loginPage.isSuccessMessageDisplayed(), "Success Message is NOT displayed!");

            String sActualSuccessMessage = loginPage.getSuccessMessage();
            Assert.assertEquals(sActualSuccessMessage, sExpectedSuccessMessage, "Wrong Success Message!");

        } finally {
            quitDriver(driver);
        }
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {

        log.info("[START TEST] testUnsuccessfulLoginWrongPassword()");

        WebDriver driver = null;

        String sUsername = "user";
        String sPassword = "wrong_password";
        String sExpectedErrorMessage = CommonStrings.LOGIN_ERROR_MESSAGE;
        try {

            driver = setUpDriver();

            LoginPage loginPage = new LoginPage(driver).open();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            log.debug("Error Message: " + loginPage.isErrorMessageDisplayed());
            Assert.assertFalse(loginPage.isErrorMessageDisplayed());

            loginPage.typeUsername(sUsername);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typePassword(sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage = loginPage.clickLoginButton(false);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            Assert.assertTrue(loginPage.isErrorMessageDisplayed());

            String sActualErrorMessage = loginPage.getErrorMessage();
            Assert.assertEquals(sActualErrorMessage, sExpectedErrorMessage, "Wrong Error Message!");

        } finally {
            quitDriver(driver);
        }
    }
}
