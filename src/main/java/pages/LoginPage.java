package pages;

import data.PageUrlPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertiesUtils;

public class LoginPage extends BasePageClass {

    private final String LOGIN_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.LOGIN_PAGE;

    // Locators
    private final String loginBoxLocatorString = "//div[@id='loginbox']";
    private final By usernameTextFieldLocator = By.id("username");
    private final By passwordTextFieldLocator = By.id("password");
    private final By loginButtonLocator = By.xpath(loginBoxLocatorString + "//input[contains(@class, 'btn-primary')]");
    private final By successMessageLocator = By.xpath(loginBoxLocatorString + "//div[contains(@class, 'alert-success')]");

    private final By errorMessageLocator = By.xpath(loginBoxLocatorString + "//div[contains(@class, 'alert-danger')]");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        log.info("Open LoginPage");
        driver.get(LOGIN_PAGE_URL);
    }

    // Basic Selenium Methods

    public boolean isUsernameTextFieldDisplayed() {
        log.debug("isUsernameTextFieldDisplayed()");
        return isWebElementDisplayed(usernameTextFieldLocator);
    }

    public boolean isUsernameTextFieldEnabled() {
        log.debug("isUsernameTextFieldEnabled()");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        return usernameTextField.isEnabled();
    }

    public void typeUsername(String sUsername) {
        log.debug("typeUsername(" + sUsername + ")");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        usernameTextField.sendKeys(sUsername);
    }

    public String getUsernamePlaceholder() {
        log.debug("getUsernamePlaceholder()");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        return usernameTextField.getAttribute("placeholder");
    }

    public boolean isPasswordTextFieldDisplayed() {
        log.debug("isPasswordTextFieldDisplayed()");
        return isWebElementDisplayed(passwordTextFieldLocator);
    }

    public boolean isPasswordTextFieldEnabled() {
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        return passwordTextField.isEnabled();
    }

    public void typePassword(String sPassword) {
        log.debug("typePassword(" + sPassword + ")");
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        passwordTextField.sendKeys(sPassword);
    }

    public String getPasswordPlaceholder() {
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        return passwordTextField.getAttribute("placeholder");
    }

    public boolean isLoginButtonDisplayed() {
        return isWebElementDisplayed(loginButtonLocator);
    }

    public boolean isLoginButtonEnabled() {
        WebElement loginButton = getWebElement(loginButtonLocator);
        return loginButton.isEnabled();
    }

    public void clickLoginButton() {
        log.debug("clickLoginButton()");
        WebElement loginButton = getWebElement(loginButtonLocator);
        loginButton.click();
    }

    public String getLoginButtonTitle() {
        WebElement loginButton = getWebElement(loginButtonLocator);
        return loginButton.getAttribute("value");
    }

    public boolean isSuccessMessageDisplayed() {
        return isWebElementDisplayed(successMessageLocator);
    }

    public String getSuccessMessage() {
        log.debug("getSuccessMessage()");
        WebElement successMessage = getWebElement(successMessageLocator);
        return successMessage.getText();

    }

    public boolean isErrorMessageDisplayed() {
        return isWebElementDisplayed(errorMessageLocator);
    }

    public String getErrorMessage() {
        log.debug("getErrorMessage()");
        WebElement errorMessage = getWebElement(errorMessageLocator);
        return errorMessage.getText();
    }

    // getUsername()

    public void login(String sUsername, String sPassword) {
        log.info("login(" + sUsername + ", " + sPassword + ")");
        typeUsername(sUsername);
        typePassword(sPassword);
        clickLoginButton();
    }

}
