package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertiesUtils;

public class WelcomePage extends LoggedInNavigationBar {

    private final String WELCOME_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.WELCOME_PAGE;

    private final By pageTitleLocator = By.xpath("//div[contains(@class,'panel-title')]");

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
}
