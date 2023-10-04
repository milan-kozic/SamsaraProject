package tests;

import data.CommonStrings;
import data.HeroClass;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.DateTimeUtils;
import utils.ScreenShotUtils;

import java.util.Date;
import java.util.List;

//@Test (groups={Groups.REGRESSION})
public class TestExample extends BaseTestClass {


    //private WebDriver driver;

    //@BeforeMethod
//    public void setUpTest(ITestContext testContext) {
//        log.debug("[SETUP TEST]");
//
//
//        driver = setUpDriver();
//        // setup driver
//        // pogfpdmgpdfmpgd
//    }

    //@Test
    public void testSuccessfulLoginLogout() {

        String sFileName = "testSuccessfulLoginLogout";
        log.info("[START TEST] testSuccessfulLoginLogout()");

        WebDriver driver = null;

        String sUsername = "user";
        String sPassword = "password";
        String sExpectedSuccessMessage = CommonStrings.getLogoutSuccessMessage();
        log.info("EXPECTED MESSAGE: " + sExpectedSuccessMessage);

        boolean bSuccess = false;

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

            //WelcomePage welcomePage = loginPage.clickLoginButton(true);
            WelcomePage welcomePage = loginPage.clickLoginButton();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            //String sWelcomePageTitle = welcomePage.getPageTitle();
            //System.out.println("Page Title: " + sWelcomePageTitle);
            loginPage = welcomePage.clickLogOutLink();


            Assert.assertTrue(loginPage.isSuccessMessageDisplayed(), "Success Message is NOT displayed!");

            String sActualSuccessMessage = loginPage.getSuccessMessage();
            Assert.assertEquals(sActualSuccessMessage, sExpectedSuccessMessage, "Wrong Success Message!");

            bSuccess = true;

        } finally {

            if (!bSuccess) {
                ScreenShotUtils.takeScreenShot(driver, sFileName);
            }
            quitDriver(driver);
        }
    }

    //@AfterMethod(alwaysRun = true)
//    public void tearDownTest(ITestResult testResult) {
//        log.debug("[TEAR DOWN TEST]");
//        log.info("STATUS: " + testResult.getStatus());
//        String sMethodName = testResult.getName();
//        String sClassName = testResult.getTestClass().getName();
//
//        // rollback
//        // delete user from database...
//        if (testResult.getStatus() == ITestResult.FAILURE) {
//            log.info("Taking screenshot...");
//            ScreenShotUtils.takeScreenShot(driver, sMethodName);
//        }
//        quitDriver(driver);
//    }

    //@Test
    public void testUnsuccessfulLoginWrongPassword() {

        String sFileName = "testUnsuccessfulLoginWrongPassword";
        log.info("[START TEST] testUnsuccessfulLoginWrongPassword()");

        WebDriver driver = null;

        String sUsername = "user";
        String sPassword = "wrong_password";
        String sExpectedErrorMessage = CommonStrings.getLoginErrorMessage();

        boolean bSuccess = false;
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

            //loginPage = loginPage.clickLoginButton(false);
            loginPage = loginPage.clickLoginButtonNoProgress();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);


            Assert.assertTrue(loginPage.isErrorMessageDisplayed());

            String sActualErrorMessage = loginPage.getErrorMessage();
            Assert.assertEquals(sActualErrorMessage, sExpectedErrorMessage, "Wrong Error Message!");

            bSuccess = true;

        } finally {
            if (!bSuccess) {
                ScreenShotUtils.takeScreenShot(driver, sFileName);
            }
            quitDriver(driver);
        }
    }

    //@Test
    public void testAddNewUser() {

        String sFileName = "testAddNewUser";
        log.info("[START TEST] testAddNewUser()");

        WebDriver driver = null;

        String sUsername = "admin";
        String sPassword = "password";

        try {
            driver = setUpDriver();

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

        } finally {
            ScreenShotUtils.takeScreenShot(driver, sFileName);
            quitDriver(driver);
        }
    }

    //@Test
    public void testUserDetails() {

        String sFileName = "testUserDetails";
        log.info("[START TEST] testUserDetails()");

        WebDriver driver = null;

        String sUsername = "admin";
        String sPassword = "password";

        try {
            driver = setUpDriver();

            LoginPage loginPage = new LoginPage(driver).open();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            WelcomePage welcomePage = loginPage.login(sUsername, sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            UsersPage usersPage = welcomePage.clickUsersTab();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            List<String> usernames = usersPage.getAllUsernames();
            log.info(usernames);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            log.info("Dedoje? " + usersPage.isUserPresentInUsersTable("dedoje"));
            log.info("Display Name: " + usersPage.getDisplayNameInUsersTable("dedoje"));
            log.info("Hero Count: " + usersPage.getHeroCountInUsersTable("dedoje"));

            UserHeroesDialogBox userHeroesDialogBox = usersPage.clickHeroCountLinkInUsersTable("dedoje");
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            usersPage = userHeroesDialogBox.clickCloseButton();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            UserDetailsDialogBox  userDetailsDialogBox = usersPage.clickUserDetailsIconInUsersTable("dedoje");
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            usersPage = userDetailsDialogBox.clickCloseButton();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            usersPage.clickLogOutLink();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            //log.info("Dedoje Index: " + usersPage.getUserRowNumber("dedoje"));
            //log.info("Dedoje DisplayName: " + usersPage.getUsersDisplayName("dedoje"));
            //log.info("Dedoje HeroCount: " + usersPage.getUsersHeroCount("dedoje"));

        } finally {
            ScreenShotUtils.takeScreenShot(driver, sFileName);
            quitDriver(driver);
        }
    }

    //@Test
    public void testAdminPage() {

        String sFileName = "testAdminPage";
        log.info("[START TEST] testAdminPage()");

        WebDriver driver = null;

        String sUsername = "admin";
        String sPassword = "password";

        try {
            driver = setUpDriver();

            LoginPage loginPage = new LoginPage(driver).open();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            WelcomePage welcomePage = loginPage.login(sUsername, sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            AdminPage adminPage = welcomePage.clickAdminTab();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            log.info("ENABLE SOMETHING EXISTS? " + adminPage.isCheckBoxDisplayed(AdminPage.CHECKBOX_ENABLE_SOMETHING));
            log.info("ALLOW USERS EXISTS? " + adminPage.isCheckBoxDisplayed(AdminPage.CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION));

            log.info("Is Checked? " + adminPage.isCheckBoxChecked(AdminPage.CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION));

            adminPage.checkCheckBox(AdminPage.CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION);
            log.info("Is Checked? " + adminPage.isCheckBoxChecked(AdminPage.CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION));

            adminPage.checkCheckBox(AdminPage.CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION);
            log.info("Is Checked? " + adminPage.isCheckBoxChecked(AdminPage.CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION));

            adminPage.uncheckCheckBox(AdminPage.CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION);
            log.info("Is Checked? " + adminPage.isCheckBoxChecked(AdminPage.CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION));

            adminPage.uncheckCheckBox(AdminPage.CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION);
            log.info("Is Checked? " + adminPage.isCheckBoxChecked(AdminPage.CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION));

//            log.info("Is Checked? : "  + adminPage.isAllowUsersToShareRegistrationCodeCheckBoxChecked());
//
//            adminPage.checkAllowUsersToShareRegistrationCodeCheckBox();
//            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//            log.info("Is Checked? : "  + adminPage.isAllowUsersToShareRegistrationCodeCheckBoxChecked());
//
//            adminPage.checkAllowUsersToShareRegistrationCodeCheckBox();
//            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//            log.info("Is Checked? : "  + adminPage.isAllowUsersToShareRegistrationCodeCheckBoxChecked());
//
//            adminPage.uncheckAllowUsersToShareRegistrationCodeCheckBox();
//            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//            log.info("Is Checked? : "  + adminPage.isAllowUsersToShareRegistrationCodeCheckBoxChecked());
//
//            adminPage.uncheckAllowUsersToShareRegistrationCodeCheckBox();
//            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//            log.info("Is Checked? : "  + adminPage.isAllowUsersToShareRegistrationCodeCheckBoxChecked());

            adminPage.clickLogOutLink();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        } finally {
            ScreenShotUtils.takeScreenShot(driver, sFileName);
            quitDriver(driver);
        }
    }

    //@Test
    public void testAddNewHero() {

        String sFileName = "testAddNewHero";
        log.info("[START TEST] testAddNewHero()");

        WebDriver driver = null;

        String sUsername = "admin";
        String sPassword = "password";

        try {
            driver = setUpDriver();

            LoginPage loginPage = new LoginPage(driver).open();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            WelcomePage welcomePage = loginPage.login(sUsername, sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            HeroesPage heroesPage = welcomePage.clickHeroesTab();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            log.info("HEROES: " + heroesPage.getAllHeroNames());

            AddHeroDialogBox addHeroDialogBox = heroesPage.clickAddNewHeroButton();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            addHeroDialogBox.typeHeroName("Heroj");
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            addHeroDialogBox.typeHeroLevel(45);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            log.info("HERO CLASS: " + addHeroDialogBox.getSelectedHeroClass());

            log.info("IDIOT? " + addHeroDialogBox.isOptionPresentInHeroClassDropDownList("Idiot"));
            log.info("RANGER? " + addHeroDialogBox.isOptionPresentInHeroClassDropDownList(HeroClass.RANGER));

            addHeroDialogBox.selectHeroClass(HeroClass.RANGER);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            log.info("HERO CLASS: " + addHeroDialogBox.getSelectedHeroClass());

            heroesPage = addHeroDialogBox.clickCancelButton();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            heroesPage.clickLogOutLink();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        } finally {
            ScreenShotUtils.takeScreenShot(driver, sFileName);
            quitDriver(driver);
        }
    }

    //@Test
    public void testDeleteUser() {

        String sFileName = "testDeleteUser";
        log.info("[START TEST] testDeleteUser()");

        WebDriver driver = null;

        String sUsername = "admin";
        String sPassword = "password";

        String sUserUsername = "bubble";
        String sUserFullName = "Princess Bubblegum";

        try {
            driver = setUpDriver();

            LoginPage loginPage = new LoginPage(driver).open();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            WelcomePage welcomePage = loginPage.login(sUsername, sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            UsersPage usersPage = welcomePage.clickUsersTab();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            usersPage.searchUser(sUserUsername);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            DeleteUserDialogBox deleteUserDialogBox = usersPage.clickDeleteUserIconInUsersTable(sUserUsername);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            String sActualDeleteUserMessage = deleteUserDialogBox.getDeleteUserMessage();
            log.info("ACTUAL MESSAGE: " + sActualDeleteUserMessage);

            String sExpectedDeleteUserMessage = CommonStrings.getDeleteUserMessage(sUserUsername, sUserFullName);
            log.info("EXPECTED MESSAGE: " + sExpectedDeleteUserMessage);
            Assert.assertEquals(sActualDeleteUserMessage, sExpectedDeleteUserMessage, "Wrong Delete User Message!");

            usersPage = deleteUserDialogBox.clickCancelButton();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            usersPage.clickLogOutLink();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        } finally {
            ScreenShotUtils.takeScreenShot(driver, sFileName);
            quitDriver(driver);
        }
    }

    //@Test
    public void testUselessTooltip() {

        String sFileName = "testUselessTooltip";
        log.info("[START TEST] testUselessTooltip()");

        WebDriver driver = null;

        String sUsername = "admin";
        String sPassword = "password";

        String sExpectedUselessTooltipText = CommonStrings.getUselessTooltip();
        String sExpectedUselessTooltipTitle = CommonStrings.getUselessTooltipTitle();

        try {
            driver = setUpDriver();

            LoginPage loginPage = new LoginPage(driver).open();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            WelcomePage welcomePage = loginPage.login(sUsername, sPassword);
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            PracticePage practicePage = welcomePage.clickPracticeTab();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

            Assert.assertFalse(practicePage.isUselessTooltipDisplayed(), "Useless Tooltip should NOT be displayed if mouse is not hovered over Useless Tooltip Title!");

            String sActualUselessTooltipTitle = practicePage.getUselessTooltipTitle();
            Assert.assertEquals(sActualUselessTooltipTitle, sExpectedUselessTooltipTitle, "Wrong Useless Tooltip Title!");

            String sActualUselessTooltipText = practicePage.getUselessTooltip();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
            Assert.assertEquals(sActualUselessTooltipText, sExpectedUselessTooltipText, "Wrong Useless Tooltip Text!");

            practicePage.clickLogOutLink();
            DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        } finally {
            ScreenShotUtils.takeScreenShot(driver, sFileName);
            quitDriver(driver);
        }
    }

    //@Test
    public void testDate() {


        Date currentDate = DateTimeUtils.getCurrentDateTime();
        log.info("DATE: " + currentDate);

        String pattern = "EEEE dd-MMMM-yyyy HH:mm:ss zzzz";
        String locale = "hr";
        String sFormattedDate = DateTimeUtils.getFormattedDateTime(currentDate, pattern);
        log.info("FORMATTED DATE: " + sFormattedDate);
        String sLocalizedDate = DateTimeUtils.getLocalizedDateTime(currentDate, pattern, locale);
        log.info("LOCALIZED DATE: " + sLocalizedDate);

        String sCreatedDate = "02.10.2023. 15:52";
        String parsePattern = "dd.MM.yyyy. HH:mm";

        Date parsedDate = DateTimeUtils.getParsedDateTime(sCreatedDate, parsePattern);
        log.info("PARSED DATE: " + parsedDate);

        log.info("COMPARE DATES: " + currentDate.compareTo(parsedDate));
    }
}
