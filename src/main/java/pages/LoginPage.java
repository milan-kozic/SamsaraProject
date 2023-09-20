package pages;

import data.PageUrlPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertiesUtils;

public class LoginPage {

    private WebDriver driver;

    private final String LOGIN_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.LOGIN_PAGE;

    // Locators
    private final String loginBoxLocatorString = "//div[@id='loginbox']";
    private final By usernameTextFieldLocator = By.id("username");
    private final By passwordTextFieldLocator = By.id("password");
    private final By loginButtonLocator = By.xpath(loginBoxLocatorString + "//input[contains(@class, 'btn-primary')]");
    private final By successMessageLocator = By.xpath(loginBoxLocatorString + "//div[contains(@class, 'alert-success')]");

    private final By errorMessageLocator = By.xpath(loginBoxLocatorString + "//div[contains(@class, 'alert-danger')]");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(LOGIN_PAGE_URL);
    }

    // Basic Selenium Methods

    public boolean isUsernameTextFieldDisplayed() {
        WebElement usernameTextField = driver.findElement(usernameTextFieldLocator);
        return usernameTextField.isDisplayed();
    }

    public boolean isUsernameTextFieldEnabled() {
        WebElement usernameTextField = driver.findElement(usernameTextFieldLocator);
        return usernameTextField.isEnabled();
    }

    public void typeUsername(String sUsername) {
        WebElement usernameTextField = driver.findElement(usernameTextFieldLocator);
        usernameTextField.sendKeys(sUsername);
    }

    public String getUsernamePlaceholder() {
        WebElement usernameTextField = driver.findElement(usernameTextFieldLocator);
        return usernameTextField.getAttribute("placeholder");
    }

    public boolean isPasswordTextFieldDisplayed() {
        WebElement passwordTextField = driver.findElement(passwordTextFieldLocator);
        return passwordTextField.isDisplayed();
    }

    public boolean isPasswordTextFieldEnabled() {
        WebElement passwordTextField = driver.findElement(passwordTextFieldLocator);
        return passwordTextField.isEnabled();
    }

    public void typePassword(String sPassword) {
        WebElement passwordTextField = driver.findElement(passwordTextFieldLocator);
        passwordTextField.sendKeys(sPassword);
    }

    public String getPasswordPlaceholder() {
        WebElement passwordTextField = driver.findElement(passwordTextFieldLocator);
        return passwordTextField.getAttribute("placeholder");
    }

    public boolean isLoginButtonDisplayed() {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        return loginButton.isDisplayed();
    }

    public boolean isLoginButtonEnabled() {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        return loginButton.isEnabled();
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }

    public String getLoginButtonTitle() {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        return loginButton.getAttribute("value");
    }

    public boolean isSuccessMessageDisplayed() {
        WebElement successMessage = driver.findElement(successMessageLocator);
        return successMessage.isDisplayed();
    }

    public String getSuccessMessage() {
        WebElement successMessage = driver.findElement(successMessageLocator);
        return successMessage.getText();
    }

    public boolean isErrorMessageDisplayed() {
        WebElement errorMessage = driver.findElement(errorMessageLocator);
        return errorMessage.isDisplayed();
    }

    public String getErrorMessage() {
        WebElement errorMessage = driver.findElement(errorMessageLocator);
        return errorMessage.getText();
    }

    // getUsername()


    // Complex Methods
    public void login(String sUsername, String sPassword) {
        typeUsername(sUsername);
        typePassword(sPassword);
        clickLoginButton();
    }

}
