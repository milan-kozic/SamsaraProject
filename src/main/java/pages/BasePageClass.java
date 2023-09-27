package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerUtils;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

abstract class BasePageClass extends LoggerUtils {

    protected WebDriver driver;

    protected BasePageClass(WebDriver driver) {
        this.driver = driver;
    }

    protected void openUrl(String url) {
        log.trace("openUrl(" + url + ")");
        driver.get(url);
    }

    protected String getCurrentUrl() {
        log.trace("getCurrentUrl()");
        return driver.getCurrentUrl();
    }

    private WebDriverWait getWebDriverWait(int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    protected boolean waitForUrlChange(String url, int timeout) {
        log.trace("waitForUrlChange(" + url + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.urlContains(url));
    }

    protected boolean waitUntilPageIsReady(int timeout) {
        log.trace("waitUntilPageIsReady(" + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply (WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                String sResult = (String) js.executeScript("return document.readyState");
                return sResult.equals("complete");
            }
        });
    }

    protected boolean waitForUrlChangeToExactUrl(String url, int timeout) {
        log.trace("waitForUrlChangeToExactUrl(" + url + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.urlToBe(url));
    }

    protected WebElement getWebElement(By locator) {
        log.trace("getWebElement(" + locator + ")");
        return driver.findElement(locator);
    }

    protected WebElement getWebElement(By locator, int timeout) {
        log.trace("getWebElement(" + locator + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement getWebElement(By locator, int timeout, int pollingTime) {
        log.trace("getWebElement(" + locator + ", " + timeout + ", " + pollingTime + ")");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return element;
    }

    protected WebElement getNestedWebElement(WebElement element, By locator) {
        log.trace("getNestedWebElement(" + element + ", " + locator + ")");
        return element.findElement(locator);
    }

    protected WebElement getNestedWebElement(WebElement element, By locator, int timeout) {
        log.trace("getWebElement(" + element + ", " + locator + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, locator));
    }

    protected List<WebElement> getWebElements(By locator) {
        log.trace("getWebElements(" + locator + ")");
        return driver.findElements(locator);
    }

    protected WebElement waitForElementToBeClickable(WebElement element, int timeout) {
        log.trace("waitForElementToBeClickable(" + element + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForElementToBeVisible(By locator, int timeout) {
        log.trace("waitForElementToBeVisible(" + locator + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementToBeVisible(WebElement element, int timeout) {
        log.trace("waitForElementToBeVisible(" + element + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean waitForElementToBeInvisible(By locator, int timeout) {
        log.trace("waitForElementToBeVisible(" + locator + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected boolean waitForElementToBeInvisible(WebElement element, int timeout) {
        log.trace("waitForElementToBeVisible(" + element + ", " + timeout + ")");
        WebDriverWait wait = getWebDriverWait(timeout);
        return wait.until(ExpectedConditions.invisibilityOf(element));
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

    protected boolean isNestedWebElementDisplayed(WebElement element, By locator) {
        log.trace("isWebElementDisplayed(" + element + ", " + locator + ")");
        try {
            WebElement webElement = getNestedWebElement(element, locator);
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

    protected boolean isWebElementEnabled(WebElement element) {
        log.trace("isWebElementEnabled(" + element + ")");
        try {
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isWebElementEnabled(WebElement element, int timeout) {
        log.trace("isWebElementEnabled(" + element + ", " + timeout + ")");
        try {
            WebElement webElement = waitForElementToBeClickable(element, timeout);
            return webElement != null;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isWebElementVisible(By locator, int timeout) {
        log.trace("isWebElementVisible(" + locator + ", " + timeout + ")");
        try {
            WebElement webElement = waitForElementToBeVisible(locator, timeout);
            return webElement != null;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isWebElementVisible(WebElement element, int timeout) {
        log.trace("isWebElementVisible(" + element + ", " + timeout + ")");
        try {
            WebElement webElement = waitForElementToBeVisible(element, timeout);
            return webElement != null;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isWebElementInvisible(By locator, int timeout) {
        log.trace("isWebElementInvisible(" + locator + ", " + timeout + ")");
        try {
            return waitForElementToBeInvisible(locator, timeout);
        } catch (Exception e) {
            return true;
        }
    }

    protected boolean isWebElementInvisible(WebElement element, int timeout) {
        log.trace("isWebElementInvisible(" + element + ", " + timeout + ")");
        try {
            return waitForElementToBeInvisible(element, timeout);
        } catch (Exception e) {
            return true;
        }
    }

    protected void typeTextToWebElement(WebElement element, String text) {
        log.trace("typeTextToWebElement(" + element + ", " + text + ")");
        element.sendKeys(text);
    }

    protected void typeTextToWebElement(WebElement element, String text, boolean clear) {
        log.trace("typeTextToWebElement(" + element + ", " + text + ", " + clear + ")");
        if(clear) {
            element.clear();
        }
        element.sendKeys(text);
    }

    protected void clearAndTypeTextToWebElement(WebElement element, String text) {
        log.trace("clearAndTypeTextToWebElement(" + element + ", " + text + ")");
        element.clear();
        element.sendKeys(text);
    }

    protected String getTextFromWebElement(WebElement element) {
        log.trace("getTextFromWebElement(" + element + ")");
        return element.getText();
    }

    protected String getAttributeFromWebElement(WebElement element, String attribute) {
        log.trace("getAttributeFromWebElement(" + element + ", " + attribute + ")");
        return element.getAttribute(attribute);
    }

    protected String getPlaceholderFromWebElement(WebElement element) {
        return getAttributeFromWebElement(element, "placeholder");
    }

    protected String getValueFromWebElement(WebElement element) {
        return getAttributeFromWebElement(element, "value");
    }

    protected String getValueFromWebElementJS(WebElement element) {
        log.trace("getValueFromWebElementJS(" + element + ")");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].value", element);
    }

    protected void clickOnWebElement(WebElement element) {
        log.trace("clickOnWebElement(" + element + ")");
        element.click();
    }

    protected void clickOnWebElement(WebElement element, int timeout) {
        log.trace("clickOnWebElement(" + element + ", " + timeout + ")");
        WebElement webElement = waitForElementToBeClickable(element, timeout);
        webElement.click();
    }

    protected void clickOnWebElementJS(WebElement element) {
        log.trace("clickOnWebElementJS(" + element + ")");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
}
