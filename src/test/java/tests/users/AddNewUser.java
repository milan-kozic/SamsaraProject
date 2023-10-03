package tests.users;

import data.Groups;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddUserDialogBox;
import pages.LoginPage;
import pages.UsersPage;
import pages.WelcomePage;
import tests.BaseTestClass;
import utils.DateTimeUtils;

@Test (groups={Groups.REGRESSION, Groups.SANITY, Groups.USERS})
public class AddNewUser extends BaseTestClass {

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
    public void testAddNewUser() {

        log.info("[START TEST] " + sTestName);

        LoginPage loginPage = new LoginPage(driver).open();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        WelcomePage welcomePage = loginPage.login(sUsername, sPassword);
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        UsersPage usersPage = welcomePage.clickUsersTab();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        AddUserDialogBox addUserDialogBox = usersPage.clickAddNewUserButton();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        usersPage = addUserDialogBox.clickCancelButton();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        usersPage.clickLogOutLink();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.debug("[END TEST] " + sTestName);
        tearDown(driver, testResult);
    }
}
