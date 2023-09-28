package pages;

import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UserDetailsDialogBox extends BasePageClass {

    private final By userDetailsDialogBoxLocator = By.id("userModal");

    private final String userDetailsDialogBoxLocatorString = "//div[@id='userModal']";
    private final By closeButtonLocator = By.xpath(userDetailsDialogBoxLocatorString + "//button[contains(@class, 'btn-default')]");

    protected UserDetailsDialogBox(WebDriver driver) {
        super(driver);
    }

    public UserDetailsDialogBox verifyUserDetailsDialogBox() {
        log.debug("verifyUserDetailsDialogBox()");
        Assert.assertTrue(isUserDetailsDialogBoxOpened(Time.TIME_SHORT), "'User Details' DialogBox is NOT opened!");
        waitUntilPageIsReady(Time.TIME_SHORTER);
        return this;
    }

    public boolean isUserDetailsDialogBoxOpened(int timeout) {
        return isWebElementVisible(userDetailsDialogBoxLocator, timeout);
    }

    public boolean isUserDetailsDialogBoxClosed(int timeout) {
        return isWebElementInvisible(userDetailsDialogBoxLocator, timeout);
    }

    public boolean isCloseButtonDisplayed() {
        log.debug("isCloseButtonDisplayed()");
        return isWebElementDisplayed(closeButtonLocator);
    }

    public boolean isCloseButtonEnabled() {
        log.debug("isCloseButtonEnabled()");
        Assert.assertTrue(isCloseButtonDisplayed(), "Close Button is NOT displayed on 'User Details' DialogBox!");
        WebElement closeButton = getWebElement(closeButtonLocator);
        return isWebElementEnabled(closeButton);
    }

    public UsersPage clickCloseButton() {
        log.debug("clickCloseButton()");
        Assert.assertTrue(isCloseButtonEnabled(), "Close Button is NOT enabled on 'User Details' DialogBox'!");
        WebElement closeButton = getWebElement(closeButtonLocator);
        clickOnWebElement(closeButton);
        Assert.assertTrue(isUserDetailsDialogBoxClosed(Time.TIME_SHORTER), "'User Details' DialogBox should NOT be opened!");
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }
}
