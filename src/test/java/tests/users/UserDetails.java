package tests.users;

import data.Groups;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserDetailsDialogBox;
import pages.UsersPage;
import pages.WelcomePage;
import tests.BaseTestClass;
import utils.DateTimeUtils;

import java.util.Date;

@Test(groups={Groups.REGRESSION, Groups.USERS})
public class UserDetails extends BaseTestClass {

    private final String sTestName = this.getClass().getName();

    private WebDriver driver;

    private String sAdminUsername;
    private String sAdminPassword;

    private String sUserUsername;

    @BeforeMethod
    public void setUpTest(ITestContext testContext) {
        log.debug("[SETUP TEST] " + sTestName);

        driver = setUpDriver();

        sAdminUsername = "admin";
        sAdminPassword = "password";

        sUserUsername = "dedoje";
    }

    @Test
    public void testUserDetails() {

        log.info("[START TEST] " + sTestName);

        LoginPage loginPage = new LoginPage(driver).open();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        WelcomePage welcomePage = loginPage.login(sAdminUsername, sAdminPassword);
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        UsersPage usersPage = welcomePage.clickUsersTab();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        usersPage = usersPage.searchUser(sUserUsername);
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        UserDetailsDialogBox userDetailsDialogBox = usersPage.clickUserDetailsIconInUsersTable(sUserUsername);
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        log.info("USERNAME: " + userDetailsDialogBox.getUsername());
        Date createdDate = userDetailsDialogBox.getCreatedAt();
        log.info("CREATED AT: " + createdDate);

        usersPage = userDetailsDialogBox.clickCloseButton();
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
