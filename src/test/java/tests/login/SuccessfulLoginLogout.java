package tests.login;

import data.CommonStrings;
import data.Groups;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.WelcomePage;
import tests.BaseTestClass;
import utils.DateTimeUtils;

@Test (groups={Groups.REGRESSION, Groups.SANITY, Groups.LOGIN})
public class SuccessfulLoginLogout extends BaseTestClass {

    private final String sTestName = this.getClass().getName();

    private WebDriver driver;

    private String sUsername;
    private String sPassword;

    @BeforeMethod
    public void setUpTest(ITestContext testContext) {
        log.debug("[SETUP TEST] " + sTestName);

        driver = setUpDriver();

        sUsername = "admin";
        sPassword = "password";
    }

    @Test
    public void testSuccessfulLoginLogout() {

        log.info("[START TEST] " + sTestName);

        String sExpectedSuccessMessage = CommonStrings.getLogoutSuccessMessage();

            LoginPage loginPage = new LoginPage(driver).open();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typeUsername(sUsername);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage.typePassword(sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            WelcomePage welcomePage = loginPage.clickLoginButton();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            loginPage = welcomePage.clickLogOutLink();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            Assert.assertTrue(loginPage.isSuccessMessageDisplayed(), "Success Message is NOT displayed!");

            String sActualSuccessMessage = loginPage.getSuccessMessage();
            Assert.assertEquals(sActualSuccessMessage, sExpectedSuccessMessage, "Wrong Success Message!");

    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.debug("[END TEST] " + sTestName);
        tearDown(driver, testResult);
    }
}
