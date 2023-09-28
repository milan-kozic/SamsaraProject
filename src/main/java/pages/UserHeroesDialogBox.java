package pages;

import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class UserHeroesDialogBox extends BasePageClass {

    private final By userHeroesDialogBoxLocator = By.id("heroesModal");

    private final String userHeroesDialogBoxLocatorString = "//div[@id='heroesModal']";
    private final By closeButtonLocator = By.xpath(userHeroesDialogBoxLocatorString + "//button[contains(@class, 'btn-default')]");

    protected UserHeroesDialogBox(WebDriver driver) {
        super(driver);
    }

    public UserHeroesDialogBox verifyUserHeroesDialogBox() {
        log.debug("verifyUserHeroesDialogBox()");
        Assert.assertTrue(isUserHeroesDialogBoxOpened(Time.TIME_SHORT), "'User Heroes' DialogBox is NOT opened!");
        waitUntilPageIsReady(Time.TIME_SHORTER);
        return this;
    }

    public boolean isUserHeroesDialogBoxOpened(int timeout) {
        return isWebElementVisible(userHeroesDialogBoxLocator, timeout);
    }

    public boolean isUserHeroesDialogBoxClosed(int timeout) {
        return isWebElementInvisible(userHeroesDialogBoxLocator, timeout);
    }

    public boolean isCloseButtonDisplayed() {
        log.debug("isCloseButtonDisplayed()");
        return isWebElementDisplayed(closeButtonLocator);
    }

    public boolean isCloseButtonEnabled() {
        log.debug("isCloseButtonEnabled()");
        Assert.assertTrue(isCloseButtonDisplayed(), "Close Button is NOT displayed on 'User Heroes' DialogBox!");
        WebElement closeButton = getWebElement(closeButtonLocator);
        return isWebElementEnabled(closeButton);
    }

    public UsersPage clickCloseButton() {
        log.debug("clickCloseButton()");
        Assert.assertTrue(isCloseButtonEnabled(), "Close Button is NOT enabled on 'User Heroes' DialogBox'!");
        WebElement closeButton = getWebElement(closeButtonLocator);
        clickOnWebElement(closeButton);
        Assert.assertTrue(isUserHeroesDialogBoxClosed(Time.TIME_SHORTER), "'User Heroes' DialogBox should NOT be opened!");
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }
}
