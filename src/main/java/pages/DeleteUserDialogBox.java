package pages;

import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class DeleteUserDialogBox extends BasePageClass {

    private final By deleteUserDialogBoxLocator = By.id("deleteUserModal");

    private final String deleteUserDialogBoxLocatorString = "//div[@id='deleteUserModal']";
    private final By cancelButtonLocator = By.xpath(deleteUserDialogBoxLocatorString + "//button[contains(@class, 'btn-default')]");

    private final By deleteUserMessageLocator = By.xpath(deleteUserDialogBoxLocatorString + "//div[@class='modal-body']/p");

    public DeleteUserDialogBox(WebDriver driver) {
        super(driver);
    }

    public DeleteUserDialogBox verifyDeleteUserDialogBox() {
        log.debug("verifyDeleteUserDialogBox()");
        Assert.assertTrue(isDeleteUserDialogBoxOpened(Time.TIME_SHORT), "'Delete User' DialogBox is NOT opened!");
        waitUntilPageIsReady(Time.TIME_SHORTER);
        return this;
    }

    public boolean isDeleteUserDialogBoxOpened(int timeout) {
        return isWebElementVisible(deleteUserDialogBoxLocator, timeout);
    }

    public boolean isDeleteUserDialogBoxClosed(int timeout) {
        return isWebElementInvisible(deleteUserDialogBoxLocator, timeout);
    }

    public boolean isCancelButtonDisplayed() {
        log.debug("isCancelButtonDisplayed()");
        return isWebElementDisplayed(cancelButtonLocator);
    }

    public boolean isCancelButtonEnabled() {
        log.debug("isCancelButtonEnabled()");
        Assert.assertTrue(isCancelButtonDisplayed(), "Cancel Button is NOT displayed on 'Delete User' DialogBox!");
        WebElement cancelButton = getWebElement(cancelButtonLocator);
        return isWebElementEnabled(cancelButton);
    }

    public UsersPage clickCancelButton() {
        log.debug("clickCancelButton()");
        Assert.assertTrue(isCancelButtonEnabled(), "Cancel Button is NOT enabled on 'Delete User' DialogBox'!");
        WebElement cancelButton = getWebElement(cancelButtonLocator);
        clickOnWebElement(cancelButton);
        Assert.assertTrue(isDeleteUserDialogBoxClosed(Time.TIME_SHORTER), "'Delete User' DialogBox should NOT be opened!");
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }

    public boolean isDeleteUserMessageDisplayed() {
        log.debug("isDeleteUserMessageDisplayed()");
        return isWebElementDisplayed(deleteUserMessageLocator);
    }

    public String getDeleteUserMessage() {
        log.debug("getDeleteUserMessage()");
        Assert.assertTrue(isDeleteUserMessageDisplayed(), "Delete User Message is NOT displayed on 'Delete User' DialogBox!");
        WebElement deleteUserMessage = getWebElement(deleteUserMessageLocator);
        return getTextFromWebElement(deleteUserMessage);
    }
}
