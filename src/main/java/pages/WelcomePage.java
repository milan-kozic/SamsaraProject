package pages;

import data.PageUrlPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertiesUtils;

public class WelcomePage {

    private WebDriver driver;

    private final String WELCOME_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.WELCOME_PAGE;

    private final By pageTitleLocator = By.xpath("//div[contains(@class,'panel-title')]");
    private final By logoutLinkLocator = By.xpath("//a[contains(@href, 'logoutForm')]");

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(WELCOME_PAGE_URL);
    }

    public String getPageTitle() {
        WebElement pageTitle = driver.findElement(pageTitleLocator);
        return pageTitle.getText();
    }

    public void clickLogOutLink() {
        WebElement logoutLink = driver.findElement(logoutLinkLocator);
        logoutLink.click();
    }
}
