package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.PropertiesUtils;

public class WelcomePage extends BasePageClass {

    private final String WELCOME_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.WELCOME_PAGE;

    private final By pageTitleLocator = By.xpath("//div[contains(@class,'panel-title')]");
    private final By logoutLinkLocator = By.xpath("//a[contains(@href, 'logoutForm')]");

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public WelcomePage open(boolean bVerify) {
        log.debug("openWelcomePage(" + WELCOME_PAGE_URL + ")");
        openUrl(WELCOME_PAGE_URL);
        if (bVerify) {
            verifyWelcomePage();
        }
        return this;
    }

    public WelcomePage open() {
        return open(true);
    }

    public WelcomePage verifyWelcomePage() {
        waitForUrlChangeToExactUrl(WELCOME_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }

    public boolean isPageTitleDisplayed() {
        log.debug("isPageTitleDisplayed()");
        return isWebElementDisplayed(pageTitleLocator);
    }

    public String getPageTitle() {
        log.debug("getPageTitle()");
        WebElement pageTitle = getWebElement(pageTitleLocator);
        return getTextFromWebElement(pageTitle);
    }

    public boolean isLogOutLinkDisplayed() {
        log.debug("isLogOutLinkDisplayed()");
        return isWebElementDisplayed(logoutLinkLocator);
    }

    public boolean isLogOutLinkEnabled() {
        log.debug("isLogOutLinkEnabled()");
        Assert.assertTrue(isLogOutLinkDisplayed(), "LogOut Link is NOT displayed on WelcomePage!");
        WebElement logoutLink = getWebElement(logoutLinkLocator);
        return isWebElementEnabled(logoutLink);
    }
    public void clickLogOutLink() {
        log.debug("clickLogOutLink()");
        Assert.assertTrue(isLogOutLinkEnabled(), "LogOut Link is NOT enabled on WelcomePage!");
        WebElement logoutLink = getWebElement(logoutLinkLocator);
        clickOnWebElement(logoutLink);
    }
}
