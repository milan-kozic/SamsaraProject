package pages;

import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.DateTimeUtils;

import java.util.Date;

public class UserDetailsDialogBox extends BasePageClass {

    private final By userDetailsDialogBoxLocator = By.id("userModal");

    private final String userDetailsDialogBoxLocatorString = "//div[@id='userModal']";
    private final By usernameTextLocator = By.xpath(userDetailsDialogBoxLocatorString + "//span[@class='username']");
    private final By createdAtTextLocator = By.xpath(userDetailsDialogBoxLocatorString + "//span[@class='created']");
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

    public boolean isUsernameTextDisplayed() {
        log.debug("isUsernameTextDisplayed()");
        return isWebElementDisplayed(usernameTextLocator);
    }

    public String getUsername() {
        log.debug("getUsername()");
        Assert.assertTrue(isUsernameTextDisplayed(), "Username Text is NOT displayed on 'User Details' DialogBox!");
        WebElement usernameText = getWebElement(usernameTextLocator);
        return getTextFromWebElement(usernameText);
    }

    public boolean isCreatedAtTextDisplayed() {
        log.debug("isCreatedAtTextDisplayed()");
        return isWebElementDisplayed(createdAtTextLocator);
    }

    public Date getCreatedAt() {
        log.debug("getCreatedAt()");
        Assert.assertTrue(isCreatedAtTextDisplayed(), "CreatedAt Text is NOT displayed on 'User Details' DialogBox!");
        WebElement createdAtText = getWebElement(createdAtTextLocator);
        String sDateTime = getTextFromWebElement(createdAtText);
        String sBrowserTimeZone = DateTimeUtils.getBrowserTimeZone(driver);
        sDateTime = sDateTime + " " + sBrowserTimeZone;
        return DateTimeUtils.getParsedDateTime(sDateTime, "MM.dd.yyyy. HH:mm z");
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
