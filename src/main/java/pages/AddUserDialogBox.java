package pages;

import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AddUserDialogBox extends BasePageClass {

    private final By addUserDialogBoxLocator = By.id("addUserModal");

    private final String addUserDialogBoxLocatorString = "//div[@id='addUserModal']";
    private final By addUserDialogBoxTitleLocator = By.xpath(addUserDialogBoxLocatorString + "//button[contains(@class, 'btn-default')]");
    private final By cancelButtonLocator = By.xpath(addUserDialogBoxLocatorString + "//button[contains(@class, 'btn-default')]");

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
}
