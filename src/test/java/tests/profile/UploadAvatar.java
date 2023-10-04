package tests.profile;

import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;
import pages.WelcomePage;
import tests.BaseTestClass;
import utils.DateTimeUtils;

public class UploadAvatar extends BaseTestClass {

    private final String sTestName = this.getClass().getName();

    private WebDriver driver;

    private String sUsername;
    private String sPassword;

    @BeforeMethod
    public void setUpTest(ITestContext testContext) {
        log.debug("[SETUP TEST] " + sTestName);

        driver = setUpDriver();

        sUsername = "dedoje";
        sPassword = "Pass123";
    }

    @Test
    public void testUploadAvatar() {

        log.info("[START TEST] " + sTestName);

        String sFileName = "dedoje.png";

        LoginPage loginPage = new LoginPage(driver).open();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        WelcomePage welcomePage = loginPage.login(sUsername, sPassword);
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        ProfilePage profilePage = welcomePage.clickProfileTab();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        profilePage = profilePage.uploadProfileImage(sFileName);
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        profilePage.typeUsername("blablablabla");
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        profilePage.clickLogOutLink();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.debug("[END TEST] " + sTestName);
        tearDown(driver, testResult);
    }
}
