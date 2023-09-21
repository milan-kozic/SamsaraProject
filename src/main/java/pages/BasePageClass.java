package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerUtils;

import java.time.Duration;
import java.util.List;

abstract class BasePageClass extends LoggerUtils {

    protected WebDriver driver;

    protected BasePageClass(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement getWebElement(By locator) {
        log.trace("getWebElement(" + locator + ")");
        return driver.findElement(locator);
    }

    protected WebElement getWebElement(By locator, int timeout) {
        log.trace("getWebElement(" + locator + ", " + timeout + ")");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> getWebElements(By locator) {
        log.trace("getWebElements(" + locator + ")");
        return driver.findElements(locator);
    }

    protected boolean isWebElementDisplayed(By locator) {
        log.trace("isWebElementDisplayed(" + locator + ")");
        try {
            WebElement webElement = getWebElement(locator);
            return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

//    protected boolean isWebElementDisplayed(By locator) {
//        log.trace("isWebElementDisplayed(" + locator + ")");
//        List<WebElement> elements = getWebElements(locator);
//        return !elements.isEmpty();
//    }

    protected boolean isWebElementDisplayed(WebElement element) {
        log.trace("isWebElementDisplayed(" + element + ")");
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
