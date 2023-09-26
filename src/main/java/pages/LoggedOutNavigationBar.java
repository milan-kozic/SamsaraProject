package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

abstract public class LoggedOutNavigationBar extends BasePageClass {

    private final By samsaraLogoLocator = By.xpath("//header[@id='headContainer']//a[@class='navbar-brand']");

    protected LoggedOutNavigationBar(WebDriver driver) {
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

    public LoginPage clickSamsaraLogo() {
        log.debug("clickSamsaraLogo()");
        Assert.assertTrue(isSamsaraLogoEnabled(), "Samsara Logo is NOT enabled on Navigation Bar!");
        WebElement samsaraLogo = getWebElement(samsaraLogoLocator);
        clickOnWebElement(samsaraLogo);
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.verifyLoginPage();
    }

}
