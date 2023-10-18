package tests.users;

import data.Groups;
import data.Time;
import objects.Hero;
import objects.User;
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
import utils.RestApiUtils;

@Test (groups={Groups.REGRESSION, Groups.SANITY, Groups.USERS})
public class AddNewUser extends BaseTestClass {

    private final String sTestName = this.getClass().getName();

    private WebDriver driver;

    private String sUsername;
    private String sPassword;

    private User newUser;
    private Hero newHero;

    private boolean bCreated = false;

    @BeforeMethod
    public void setUpTest(ITestContext testContext) {
        log.debug("[SETUP TEST] " + sTestName);

        driver = setUpDriver();

        sUsername = "admin";
        sPassword = "password";

        newUser = User.createNewUniqueUser("AddNewUser");
        newHero = Hero.createNewUniqueHero(newUser, "AddNewHero");

    }

    @Test
    public void testAddNewUser() {

        log.info("[START TEST] " + sTestName);

        log.info("USER: " + newUser);
        log.info("HERO: " + newHero);

        LoginPage loginPage = new LoginPage(driver).open();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        WelcomePage welcomePage = loginPage.login(sUsername, sPassword);
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        UsersPage usersPage = welcomePage.clickUsersTab();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        AddUserDialogBox addUserDialogBox = usersPage.clickAddNewUserButton();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

//        addUserDialogBox.typeUsername(newUser.getUsername());
//        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//
//        addUserDialogBox.typeFirstName(newUser.getFirstName());
//        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//
//        addUserDialogBox.typeLastName(newUser.getLastName());
//        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//
//        addUserDialogBox.typeEmail(newUser.getEmail());
//        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//
//        addUserDialogBox.typeAbout(newUser.getAbout());
//        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//
//        addUserDialogBox.typeSecretQuestion(newUser.getSecretQuestion());
//        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//
//        addUserDialogBox.typeSecretAnswer(newUser.getSecretAnswer());
//        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//
//        addUserDialogBox.typePassword(newUser.getPassword());
//        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//
//        addUserDialogBox.typeConfirmPassword(newUser.getPassword());
//        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);
//
//        usersPage = addUserDialogBox.clickSaveButton();
//        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        usersPage = addUserDialogBox.addNewUser(newUser);
        bCreated = true;
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

        User createdUser = RestApiUtils.getUser(newUser.getUsername());
        log.info("CREATED USER: " + createdUser);

        usersPage.clickLogOutLink();
        DateTimeUtils.wait(Time.TIME_DEMONSTRATION);

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
