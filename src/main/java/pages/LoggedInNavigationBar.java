package pages;


import data.PageUrlPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

abstract public class LoggedInNavigationBar extends BasePageClass {

    private final String headerLocatorString = "//header[@id='headContainer']";
    private final By samsaraLogoLocator = By.xpath(headerLocatorString + "//a[@class='navbar-brand']");
    private final By usersTabLocator = By.xpath(headerLocatorString + "//a[@href='" + PageUrlPaths.USERS_PAGE + "']");
    private final By heroesTabLocator = By.xpath(headerLocatorString + "//a[@href='" + PageUrlPaths.HEROES_PAGE + "']");
    private final By profileTabLocator = By.xpath(headerLocatorString + "//a[@href='" + PageUrlPaths.PROFILE_PAGE + "']");
    private final By logoutLinkLocator = By.xpath(headerLocatorString + "//a[contains(@href, 'logoutForm')]");

    protected LoggedInNavigationBar(WebDriver driver) {
        super(driver);
    }

    public boolean isSamsaraLogoDisplayed() {
        log.debug("isSamsaraLogoDisplayed()");
        return isWebElementDisplayed(samsaraLogoLocator);
    }

    public boolean isSamsaraLogoEnabled() {
        log.debug("isSamsaraLogoEnabled()");
        Assert.assertTrue(isSamsaraLogoDisplayed(), "Samsara Logo is NOT displayed on Navigation Bar!");
        WebElement samsaraLogo = getWebElement(samsaraLogoLocator);
        return isWebElementEnabled(samsaraLogo);
    }

    public WelcomePage clickSamsaraLogo() {
        log.debug("clickSamsaraLogo()");
        Assert.assertTrue(isSamsaraLogoEnabled(), "Samsara Logo is NOT enabled on Navigation Bar!");
        WebElement samsaraLogo = getWebElement(samsaraLogoLocator);
        clickOnWebElement(samsaraLogo);
        WelcomePage welcomePage = new WelcomePage(driver);
        return welcomePage.verifyWelcomePage();
    }

    public boolean isUsersTabDisplayed() {
        log.debug("isUsersTabDisplayed()");
        return isWebElementDisplayed(usersTabLocator);
    }

    public boolean isUsersTabEnabled() {
        log.debug("isUsersTabEnabled()");
        Assert.assertTrue(isUsersTabDisplayed(), "Users Tab is NOT displayed on Navigation Bar!");
        WebElement usersTab = getWebElement(usersTabLocator);
        return isWebElementEnabled(usersTab);
    }

    public String getUsersTabTitle() {
        log.debug("getUsersTabTitle()");
        Assert.assertTrue(isUsersTabDisplayed(), "Users Tab is NOT displayed on Navigation Bar!");
        WebElement usersTab = getWebElement(usersTabLocator);
        return getTextFromWebElement(usersTab);
    }

    public UsersPage clickUsersTab() {
        log.debug("clickUsersTab()");
        Assert.assertTrue(isUsersTabEnabled(), "Users Tab is NOT enabled on Navigation Bar!");
        WebElement usersTab = getWebElement(usersTabLocator);
        clickOnWebElement(usersTab);
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }

    public boolean isLogOutLinkDisplayed() {
        log.debug("isLogOutLinkDisplayed()");
        return isWebElementDisplayed(logoutLinkLocator);
    }

    public boolean isLogOutLinkEnabled() {
        log.debug("isLogOutLinkEnabled()");
        Assert.assertTrue(isLogOutLinkDisplayed(), "LogOut Link is NOT displayed on Navigation Bar!");
        WebElement logoutLink = getWebElement(logoutLinkLocator);
        return isWebElementEnabled(logoutLink);
    }
    public LoginPage clickLogOutLink() {
        log.debug("clickLogOutLink()");
        Assert.assertTrue(isLogOutLinkEnabled(), "LogOut Link is NOT enabled on Navigation Bar!");
        WebElement logoutLink = getWebElement(logoutLinkLocator);
        clickOnWebElement(logoutLink);
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.verifyLoginPage();
    }
}
