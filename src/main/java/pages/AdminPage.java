package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.FileUtils;
import utils.PropertiesUtils;

public class AdminPage extends LoggedInNavigationBar {

    private final String ADMIN_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.ADMIN_PAGE;

    public static final String CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION = "Allow Users To Share Registration";
    public static final String CHECKBOX_ENABLE_SOMETHING = "Enable Something";

    private final By pageTitleLocator = By.xpath("//div[contains(@class,'panel-title')]");
    private final By allowUsersToShareRegistrationCodeCheckBoxLocator = By.id("usersAllowed");
    private final By enableSomethingCheckboxLocator = By.id("enableSomething");

    @FindBy(id = "userDetails")
    private WebElement downloadUserDetailsButton;

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public AdminPage open(boolean bVerify) {
        log.debug("openAdminPage(" + ADMIN_PAGE_URL + ")");
        openUrl(ADMIN_PAGE_URL);
        if(bVerify) {
            verifyAdminPage();
        }
        return this;
    }

    public AdminPage open() {
        return open(true);
    }

    public AdminPage verifyAdminPage() {
        log.debug("verifyAdminPage()");
        waitForUrlChange(ADMIN_PAGE_URL, Time.TIME_SHORTER);
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

    private By getCheckBoxLocator(String sCheckBoxName) {
        By checkboxLocator = null;

        switch(sCheckBoxName) {
            case CHECKBOX_ALLOW_USERS_TO_SHARE_REGISTRATION : {
                checkboxLocator = allowUsersToShareRegistrationCodeCheckBoxLocator;
                break;
            }
            case CHECKBOX_ENABLE_SOMETHING : {
                checkboxLocator = enableSomethingCheckboxLocator;
                break;
            }
            default : {
                Assert.fail("CheckBox '" + sCheckBoxName + "' doesn't exist on Admin Page!");
            }
        }
        return checkboxLocator;
    }

    public boolean isCheckBoxDisplayed(String sCheckBoxName) {
        log.debug("isCheckBoxDisplayed(" + sCheckBoxName + ")");
        return isWebElementDisplayed(getCheckBoxLocator(sCheckBoxName));
    }

    public boolean isCheckBoxEnabled(String sCheckBoxName) {
        log.debug("isCheckBoxEnabled(" + sCheckBoxName + ")");
        Assert.assertTrue(isCheckBoxDisplayed(sCheckBoxName), "'" + sCheckBoxName + "' CheckBox is NOT displayed on AdminPage!");
        WebElement checkbox = getWebElement(getCheckBoxLocator(sCheckBoxName));
        return isWebElementEnabled(checkbox);
    }

    public boolean isCheckBoxChecked(String sCheckBoxName) {
        log.debug("isCheckBoxChecked(" + sCheckBoxName + ")");
        Assert.assertTrue(isCheckBoxDisplayed(sCheckBoxName), "'"+ sCheckBoxName + "' CheckBox is NOT displayed on AdminPage!");
        WebElement checkbox = getWebElement(getCheckBoxLocator(sCheckBoxName));
        return isWebElementSelected(checkbox);
    }

    public AdminPage checkCheckBox(String sCheckBoxName) {
        log.debug("checkCheckBox(" + sCheckBoxName + ")");
        Assert.assertTrue(isCheckBoxEnabled(sCheckBoxName), "'"+ sCheckBoxName + "' CheckBox is NOT enabled on AdminPage!");
        if(!isCheckBoxChecked(sCheckBoxName)) {
            WebElement checkbox = getWebElement(getCheckBoxLocator(sCheckBoxName));
            clickOnWebElement(checkbox);
        }
        AdminPage adminPage = new AdminPage(driver);
        return adminPage.verifyAdminPage();
    }

    public AdminPage uncheckCheckBox(String sCheckBoxName) {
        log.debug("uncheckCheckBox(" + sCheckBoxName + ")");
        Assert.assertTrue(isCheckBoxEnabled(sCheckBoxName), "'"+ sCheckBoxName + "' CheckBox is NOT enabled on AdminPage!");
        if(isCheckBoxChecked(sCheckBoxName)) {
            WebElement checkbox = getWebElement(getCheckBoxLocator(sCheckBoxName));
            clickOnWebElement(checkbox);
        }
        AdminPage adminPage = new AdminPage(driver);
        return adminPage.verifyAdminPage();
    }

    public boolean isAllowUsersToShareRegistrationCodeCheckBoxDisplayed() {
        log.debug("isAllowUsersToShareRegistrationCodeCheckBoxDisplayed()");
        return isWebElementDisplayed(allowUsersToShareRegistrationCodeCheckBoxLocator);
    }

    public boolean isAllowUsersToShareRegistrationCodeCheckBoxEnabled() {
        log.debug("isAllowUsersToShareRegistrationCodeCheckBoxEnabled()");
        Assert.assertTrue(isAllowUsersToShareRegistrationCodeCheckBoxDisplayed(), "'Allow Users To Share Registration Code' CheckBox is NOT displayed on AdminPage!");
        WebElement allowUsersToShareRegistrationCodeCheckBox = getWebElement(allowUsersToShareRegistrationCodeCheckBoxLocator);
        return isWebElementEnabled(allowUsersToShareRegistrationCodeCheckBox);
    }

    public boolean isAllowUsersToShareRegistrationCodeCheckBoxChecked() {
        log.debug("isAllowUsersToShareRegistrationCodeCheckBoxChecked()");
        Assert.assertTrue(isAllowUsersToShareRegistrationCodeCheckBoxDisplayed(), "'Allow Users To Share Registration Code' CheckBox is NOT displayed on AdminPage!");
        WebElement allowUsersToShareRegistrationCodeCheckBox = getWebElement(allowUsersToShareRegistrationCodeCheckBoxLocator);
        return isWebElementSelected(allowUsersToShareRegistrationCodeCheckBox);
    }

    public AdminPage checkAllowUsersToShareRegistrationCodeCheckBox() {
        log.debug("checkAllowUsersToShareRegistrationCodeCheckBox()");
        Assert.assertTrue(isAllowUsersToShareRegistrationCodeCheckBoxEnabled(), "'Allow Users To Share Registration Code' CheckBox is NOT enabled on AdminPage!");
        if(!isAllowUsersToShareRegistrationCodeCheckBoxChecked()) {
            WebElement allowUsersToShareRegistrationCodeCheckBox = getWebElement(allowUsersToShareRegistrationCodeCheckBoxLocator);
            clickOnWebElement(allowUsersToShareRegistrationCodeCheckBox);
        }
        AdminPage adminPage = new AdminPage(driver);
        return adminPage.verifyAdminPage();
    }

    public AdminPage uncheckAllowUsersToShareRegistrationCodeCheckBox() {
        log.debug("uncheckAllowUsersToShareRegistrationCodeCheckBox()");
        Assert.assertTrue(isAllowUsersToShareRegistrationCodeCheckBoxEnabled(), "'Allow Users To Share Registration Code' CheckBox is NOT enabled on AdminPage!");
        if(isAllowUsersToShareRegistrationCodeCheckBoxChecked()) {
            WebElement allowUsersToShareRegistrationCodeCheckBox = getWebElement(allowUsersToShareRegistrationCodeCheckBoxLocator);
            clickOnWebElement(allowUsersToShareRegistrationCodeCheckBox);
        }
        AdminPage adminPage = new AdminPage(driver);
        return adminPage.verifyAdminPage();
    }

    public String downloadUserDetailsFile() {
        log.debug("downloadUserDetailsFile()");
        String href = getAttributeFromWebElement(downloadUserDetailsButton, "href");
        log.debug("HREF: " + href);
        return FileUtils.downloadTextFile(driver, href);
    }
}
