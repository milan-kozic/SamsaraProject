package pages;

import data.PageUrlPaths;
import data.Time;
import objects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.PropertiesUtils;

public class LoginPage extends LoggedOutNavigationBar {

    private final String LOGIN_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.LOGIN_PAGE;

    // Locators
    private final String loginBoxLocatorString = "//div[@id='loginbox']";
    private final By pageTitleLocator = By.xpath("//div[contains(@class,'panel-title')]");
    private final By usernameTextFieldLocator = By.id("username");
    private final By passwordTextFieldLocator = By.id("password");
    private final By loginButtonLocator = By.xpath(loginBoxLocatorString + "//input[contains(@class, 'btn-primary')]");
    private final By successMessageLocator = By.xpath(loginBoxLocatorString + "//div[contains(@class, 'alert-success')]");

    private final By errorMessageLocator = By.xpath(loginBoxLocatorString + "//div[contains(@class, 'alert-danger')]");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open(boolean bVerify) {
        log.debug("openLoginPage(" + LOGIN_PAGE_URL + ")");
        openUrl(LOGIN_PAGE_URL);
        if(bVerify) {
            verifyLoginPage();
        }
        return this;
    }

    public LoginPage open() {
        return open(true);
    }

    public LoginPage verifyLoginPage() {
        log.debug("verifyLoginPage()");
        waitForUrlChange(LOGIN_PAGE_URL, Time.TIME_SHORTER);
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

    public boolean isUsernameTextFieldDisplayed() {
        log.debug("isUsernameTextFieldDisplayed()");
        return isWebElementDisplayed(usernameTextFieldLocator);
    }

    public boolean isUsernameTextFieldEnabled() {
        log.debug("isUsernameTextFieldEnabled()");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username TextField is NOT displayed on LoginPage!");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        return isWebElementEnabled(usernameTextField);
    }

    public LoginPage typeUsername(String sUsername) {
        log.debug("typeUsername(" + sUsername + ")");
        Assert.assertTrue(isUsernameTextFieldEnabled(), "Username TextField is NOT enabled on LoginPage!");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        clearAndTypeTextToWebElement(usernameTextField, sUsername);
        return this;
    }

    public String getUsername() {
        log.debug("getUsername()");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username TextField is NOT displayed on LoginPage!");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        return getValueFromWebElement(usernameTextField);
    }

    public String getUsernamePlaceholder() {
        log.debug("getUsernamePlaceholder()");
        Assert.assertTrue(isUsernameTextFieldDisplayed(), "Username TextField is NOT displayed on LoginPage!");
        WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        return getPlaceholderFromWebElement(usernameTextField);
    }

    public boolean isPasswordTextFieldDisplayed() {
        log.debug("isPasswordTextFieldDisplayed()");
        return isWebElementDisplayed(passwordTextFieldLocator);
    }

    public boolean isPasswordTextFieldEnabled() {
        log.debug("isPasswordTextFieldEnabled()");
        Assert.assertTrue(isPasswordTextFieldDisplayed(), "Password TextField is NOT displayed on LoginPage!");
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        return isWebElementEnabled(passwordTextField);
    }

    public LoginPage typePassword(String sPassword) {
        log.debug("typePassword(" + sPassword + ")");
        Assert.assertTrue(isPasswordTextFieldEnabled(), "Password TextField is NOT enabled on LoginPage!");
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        clearAndTypeTextToWebElement(passwordTextField, sPassword);
        return this;
    }

    public String getPassword() {
        log.debug("getPassword()");
        Assert.assertTrue(isPasswordTextFieldDisplayed(), "Password TextField is NOT displayed on LoginPage!");
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        return getValueFromWebElement(passwordTextField);
    }

    public String getPasswordPlaceholder() {
        log.debug("getPasswordPlaceholder()");
        Assert.assertTrue(isPasswordTextFieldDisplayed(), "Password TextField is NOT displayed on LoginPage!");
        WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        return getPlaceholderFromWebElement(passwordTextField);
    }

    public boolean isLoginButtonDisplayed() {
        log.debug("isLoginButtonDisplayed()");
        return isWebElementDisplayed(loginButtonLocator);
    }

    public boolean isLoginButtonEnabled() {
        log.debug("isLoginButtonEnabled()");
        Assert.assertTrue(isLoginButtonDisplayed(), "Login Button is NOT displayed on LoginPage!");
        WebElement loginButton = getWebElement(loginButtonLocator);
        return isWebElementEnabled(loginButton);
    }

    public boolean isLoginButtonEnabled(int timeout) {
        log.debug("isLoginButtonEnabled(" + timeout + ")");
        Assert.assertTrue(isLoginButtonDisplayed(), "Login Button is NOT displayed on LoginPage!");
        WebElement loginButton = getWebElement(loginButtonLocator);
        return isWebElementEnabled(loginButton, timeout);
    }

    private void clickLoginButtonNoVerification() {
        Assert.assertTrue(isLoginButtonEnabled(), "Login Button is NOT enabled on LoginPage!");
        WebElement loginButton = getWebElement(loginButtonLocator);
        clickOnWebElement(loginButton);
    }

    public WelcomePage clickLoginButton() {
        log.debug("clickLoginButton()");
        clickLoginButtonNoVerification();
        WelcomePage welcomePage = new WelcomePage(driver);
        return welcomePage.verifyWelcomePage();
    }

    public LoginPage clickLoginButtonNoProgress() {
        log.debug("clickLoginButtonNoProgress()");
        clickLoginButtonNoVerification();
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.verifyLoginPage();
    }

    public <T> T clickLoginButton(boolean bProgress) {
        log.debug("clickLoginButton(" + bProgress + ")");
        clickLoginButtonNoVerification();
        if (bProgress) {
            WelcomePage welcomePage = new WelcomePage(driver);
            return (T) welcomePage.verifyWelcomePage();
        } else {
            LoginPage loginPage = new LoginPage(driver);
            return (T) loginPage.verifyLoginPage();
        }
    }

    public String getLoginButtonTitle() {
        log.debug("getLoginButtonTitle()");
        Assert.assertTrue(isLoginButtonDisplayed(), "Login Button is NOT displayed on LoginPage!");
        WebElement loginButton = getWebElement(loginButtonLocator);
        return getValueFromWebElement(loginButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return isWebElementDisplayed(successMessageLocator);
    }

    public String getSuccessMessage() {
        log.debug("getSuccessMessage()");
        WebElement successMessage = getWebElement(successMessageLocator);
        return getTextFromWebElement(successMessage);

    }

    public boolean isErrorMessageDisplayed() {
        return isWebElementDisplayed(errorMessageLocator);
    }

    public String getErrorMessage() {
        log.debug("getErrorMessage()");
        WebElement errorMessage = getWebElement(errorMessageLocator);
        return getTextFromWebElement(errorMessage);
    }

    /**
     * Login to Samsara
     * @param sUsername {String} - User's Username
     * @param sPassword {String} - User's Password
     * @return {WelcomePage}
     */
    public WelcomePage login(String sUsername, String sPassword) {
        log.info("login(" + sUsername + ", " + sPassword + ")");
        typeUsername(sUsername);
        typePassword(sPassword);
        return clickLoginButton();
    }

    public WelcomePage login(User user) {
        return login(user.getUsername(), user.getPassword());
    }

}
