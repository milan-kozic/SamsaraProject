package tests.login;

import data.CommonStrings;
import data.Groups;
import data.Time;
import objects.User;
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
import utils.RestApiUtils;

@Test (groups={Groups.REGRESSION, Groups.SANITY, Groups.LOGIN})
public class SuccessfulLoginLogout extends BaseTestClass {

    private final String sTestName = this.getClass().getName();

    private WebDriver driver;

    private User newUser;

    private boolean bCreated = false;


    @BeforeMethod
    public void setUpTest(ITestContext testContext) {
        log.debug("[SETUP TEST] " + sTestName);

        driver = setUpDriver();

        newUser = User.createNewUniqueUser("LoginLogout");
        log.info("NEW USER: " + newUser);

        RestApiUtils.postUser(newUser);
        bCreated = true;

        User createdUser = RestApiUtils.getUser(newUser.getUsername());
        log.info("CREATED USER: " + createdUser);

        //ApiError error1 = RestApiUtils.getUserError("jfndksjfnkj", "admin", "password");
        //log.info("ERROR1: " + error1);

        //ApiError error2 = RestApiUtils.getUserError(newUser.getUsername(), "user", "password");
        //log.info("ERROR2: " + error2);

        //ApiError error3 = RestApiUtils.getUserError(newUser.getUsername(), "admin", "password123");
        //log.info("ERROR3: " + error3);
    }

    @Test
    public void testSuccessfulLoginLogout() {

        log.info("[START TEST] " + sTestName);

        String sExpectedSuccessMessage = CommonStrings.getLogoutSuccessMessage();

        LoginPage loginPage = new LoginPage(driver).open();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        loginPage.typeUsername(newUser.getUsername());
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        loginPage.typePassword(newUser.getPassword());
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
        if(bCreated) {
            deleteUser(newUser.getUsername());
        }
    }
}
