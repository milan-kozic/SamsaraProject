package pages;

import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class EditUserDialogBox extends BasePageClass {

    private final By editUserDialogBoxLocator = By.id("editUserModal");

    private final String editUserDialogBoxLocatorString = "//div[@id='editUserModal']";
    private final By cancelButtonLocator = By.xpath(editUserDialogBoxLocatorString + "//button[contains(@class, 'btn-default')]");

    protected EditUserDialogBox(WebDriver driver) {
        super(driver);
    }

    public EditUserDialogBox verifyEditUserDialogBox() {
        log.debug("verifyEditUserDialogBox()");
        Assert.assertTrue(isEditUserDialogBoxOpened(Time.TIME_SHORT), "'Edit User' DialogBox is NOT opened!");
        waitUntilPageIsReady(Time.TIME_SHORTER);
        return this;
    }

    public boolean isEditUserDialogBoxOpened(int timeout) {
        return isWebElementVisible(editUserDialogBoxLocator, timeout);
    }

    public boolean isEditUserDialogBoxClosed(int timeout) {
        return isWebElementInvisible(editUserDialogBoxLocator, timeout);
    }

    public boolean isCancelButtonDisplayed() {
        log.debug("isCancelButtonDisplayed()");
        return isWebElementDisplayed(cancelButtonLocator);
    }

    public boolean isCancelButtonEnabled() {
        log.debug("isCancelButtonEnabled()");
        Assert.assertTrue(isCancelButtonDisplayed(), "Cancel Button is NOT displayed on 'Edit User' DialogBox!");
        WebElement cancelButton = getWebElement(cancelButtonLocator);
        return isWebElementEnabled(cancelButton);
    }

    public UsersPage clickCancelButton() {
        log.debug("clickCancelButton()");
        Assert.assertTrue(isCancelButtonEnabled(), "Cancel Button is NOT enabled on 'Edit User' DialogBox'!");
        WebElement cancelButton = getWebElement(cancelButtonLocator);
        clickOnWebElement(cancelButton);
        Assert.assertTrue(isEditUserDialogBoxClosed(Time.TIME_SHORTER), "'Edit User' DialogBox should NOT be opened!");
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }
}
