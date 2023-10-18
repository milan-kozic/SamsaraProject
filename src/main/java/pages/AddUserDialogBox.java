package pages;

import data.Time;
import objects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.DateTimeUtils;

public class AddUserDialogBox extends BasePageClass {

    private final By addUserDialogBoxLocator = By.id("addUserModal");

    private final String addUserDialogBoxLocatorString = "//div[@id='addUserModal']";
    private final By addUserDialogBoxTitleLocator = By.xpath(addUserDialogBoxLocatorString + "//button[contains(@class, 'btn-default')]");
    private final By cancelButtonLocator = By.xpath(addUserDialogBoxLocatorString + "//button[contains(@class, 'btn-default')]");

    @FindBy(id = "username")
    private WebElement usernameTextField;

    @FindBy(id = "firstName")
    private WebElement firstNameTextField;

    @FindBy(id = "lastName")
    private WebElement lastNameTextField;

    @FindBy(id = "email")
    private WebElement emailTextField;

    @FindBy(id = "about")
    private WebElement aboutTextField;

    @FindBy(id = "secretQuestion")
    private WebElement secretQuestionTextField;

    @FindBy(id = "secretAnswer")
    private WebElement secretAnswerTextField;

    @FindBy(id = "password")
    private WebElement passwordTextField;

    @FindBy(id = "repassword")
    private WebElement repasswordTextField;

    @FindBy(id = "submitButton")
    private WebElement saveButton;

    public AddUserDialogBox(WebDriver driver) {
        super(driver);
    }

    public AddUserDialogBox verifyAddUserDialogBox() {
        log.debug("verifyAddUserDialogBox()");
        Assert.assertTrue(isAddUserDialogBoxOpened(Time.TIME_SHORT), "'Add User' DialogBox is NOT opened!");
        waitUntilPageIsReady(Time.TIME_SHORTER);
        return this;
    }

    public boolean isAddUserDialogBoxOpened(int timeout) {
        return isWebElementVisible(addUserDialogBoxLocator, timeout);
    }

    public boolean isAddUserDialogBoxClosed(int timeout) {
        return isWebElementInvisible(addUserDialogBoxLocator, timeout);
    }

    public boolean isDialogBoxTitleDisplayed() {
        log.debug("isDialogBoxTitleDisplayed()");
        return isWebElementDisplayed(addUserDialogBoxTitleLocator);
    }

    public String getDialogBoxTitle() {
        log.debug("getDialogBoxTitle()");
        Assert.assertTrue(isDialogBoxTitleDisplayed(), "DialogBox Title is NOT displayed on 'Add User' DialogBox");
        WebElement addUserDialogBoxTitle = getWebElement(addUserDialogBoxTitleLocator);
        return getTextFromWebElement(addUserDialogBoxTitle);
    }

    public AddUserDialogBox typeUsername(String sUsername) {
        log.debug("typeUsername(" + sUsername + ")");
        clearAndTypeTextToWebElement(usernameTextField, sUsername);
        return this;
    }

    public AddUserDialogBox typeFirstName(String sFirstName) {
        log.debug("typeFirstName(" + sFirstName + ")");
        clearAndTypeTextToWebElement(firstNameTextField, sFirstName);
        return this;
    }

    public AddUserDialogBox typeLastName(String sLastName) {
        log.debug("typeLastName(" + sLastName + ")");
        clearAndTypeTextToWebElement(lastNameTextField, sLastName);
        return this;
    }

    public AddUserDialogBox typeEmail(String sEmail) {
        log.debug("typeEmail(" + sEmail + ")");
        clearAndTypeTextToWebElement(emailTextField, sEmail);
        return this;
    }

    public AddUserDialogBox typeAbout(String sAbout) {
        log.debug("typeAbout(" + sAbout + ")");
        clearAndTypeTextToWebElement(aboutTextField, sAbout);
        return this;
    }

    public AddUserDialogBox typeSecretQuestion(String sSecretQuestion) {
        log.debug("typeSecretQuestion(" + sSecretQuestion + ")");
        clearAndTypeTextToWebElement(secretQuestionTextField, sSecretQuestion);
        return this;
    }

    public AddUserDialogBox typeSecretAnswer(String sSecretAnswer) {
        log.debug("typeSecretAnswer(" + sSecretAnswer + ")");
        clearAndTypeTextToWebElement(secretAnswerTextField, sSecretAnswer);
        return this;
    }

    public AddUserDialogBox typePassword(String sPassword) {
        log.debug("typePassword(" + sPassword + ")");
        clearAndTypeTextToWebElement(passwordTextField, sPassword);
        return this;
    }

    public AddUserDialogBox typeConfirmPassword(String sPassword) {
        log.debug("typeConfirmPassword(" + sPassword + ")");
        clearAndTypeTextToWebElement(repasswordTextField, sPassword);
        return this;
    }

    public boolean isCancelButtonDisplayed() {
        log.debug("isCancelButtonDisplayed()");
        return isWebElementDisplayed(cancelButtonLocator);
    }

    public boolean isCancelButtonEnabled() {
        log.debug("isCancelButtonEnabled()");
        Assert.assertTrue(isCancelButtonDisplayed(), "Cancel Button is NOT displayed on 'Add User' DialogBox!");
        WebElement cancelButton = getWebElement(cancelButtonLocator);
        return isWebElementEnabled(cancelButton);
    }

    public UsersPage clickCancelButton() {
        log.debug("clickCancelButton()");
        Assert.assertTrue(isCancelButtonEnabled(), "Cancel Button is NOT enabled on 'Add User' DialogBox'!");
        WebElement cancelButton = getWebElement(cancelButtonLocator);
        clickOnWebElement(cancelButton);
        Assert.assertTrue(isAddUserDialogBoxClosed(Time.TIME_SHORTER), "'Add User' DialogBox should NOT be opened!");
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }

    public boolean isSaveButtonDisplayed() {
        log.debug("isSaveButtonDisplayed()");
        return isWebElementDisplayed(saveButton);
    }

    public boolean isSaveButtonEnabled() {
        log.debug("isSaveButtonEnabled()");
        Assert.assertTrue(isSaveButtonDisplayed(), "Save Button is NOT displayed on 'Add User' DialogBox!");
        return isWebElementEnabled(saveButton);
    }

    public UsersPage clickSaveButton() {
        log.debug("clickSaveButton()");
        Assert.assertTrue(isSaveButtonEnabled(), "Save Button is NOT enabled on 'Add User' DialogBox!");
        clickOnWebElement(saveButton, Time.TIME_SHORTEST);
        Assert.assertTrue(isAddUserDialogBoxClosed(Time.TIME_SHORTER), "'Add User' DialogBox is NOT closed!");
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }

    public UsersPage addNewUser(User newUser) {
        typeUsername(newUser.getUsername());
        typeFirstName(newUser.getFirstName());
        typeLastName(newUser.getLastName());
        typeEmail(newUser.getEmail());
        typeAbout(newUser.getAbout());
        typeSecretQuestion(newUser.getSecretQuestion());
        typeSecretAnswer(newUser.getSecretAnswer());
        typePassword(newUser.getPassword());
        typeConfirmPassword(newUser.getPassword());
        return clickSaveButton();
    }
}
