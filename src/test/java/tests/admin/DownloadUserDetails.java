package tests.admin;

import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;
import pages.WelcomePage;
import tests.BaseTestClass;
import utils.DateTimeUtils;

public class DownloadUserDetails extends BaseTestClass {

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
    public void testDownloadUserDetails() {

        log.info("[START TEST] " + sTestName);

        LoginPage loginPage = new LoginPage(driver).open();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        WelcomePage welcomePage = loginPage.login(sUsername, sPassword);
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        AdminPage adminPage = welcomePage.clickAdminTab();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        String sFileContent = adminPage.downloadUserDetailsFile();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
        log.info("FILE: \n" + sFileContent);

        adminPage.clickLogOutLink();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.debug("[END TEST] " + sTestName);
        tearDown(driver, testResult);
    }
}
