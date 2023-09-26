package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.PropertiesUtils;

public class UsersPage extends LoggedInNavigationBar {

    private final String USERS_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.USERS_PAGE;

    private final By addNewUserButtonLocator = By.xpath("//a[contains(@class, 'btn-info') and contains(@onclick,'openAddUserModal')]");

    public UsersPage(WebDriver driver) {
        super(driver);
    }

    public UsersPage open(boolean bVerify) {
        log.debug("openUsersPage(" + USERS_PAGE_URL + ")");
        openUrl(USERS_PAGE_URL);
        if(bVerify) {
            verifyUsersPage();
        }
        return this;
    }

    public UsersPage open() {
        return open(true);
    }

    public UsersPage verifyUsersPage() {
        log.debug("verifyUsersPage()");
        waitForUrlChange(USERS_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }

    public boolean isAddNewUserButtonDisplayed() {
        log.debug("isAddNewUserButtonDisplayed()");
        return isWebElementDisplayed(addNewUserButtonLocator);
    }

    public boolean isAddNewUserButtonEnabled() {
        log.debug("isAddNewUserButtonEnabled()");
        Assert.assertTrue(isAddNewUserButtonDisplayed(), "'Add New User' Button is NOT displayed on Users Page!");
        WebElement addNewUserButton = getWebElement(addNewUserButtonLocator);
        return isWebElementEnabled(addNewUserButton);
    }

    public AddUserDialogBox clickAddNewUserButton() {
        log.debug("clickAddNewUserButton()");
        Assert.assertTrue(isAddNewUserButtonEnabled(), "'Add New User' Button is NOT enabled on Users Page!");
        WebElement addNewUserButton = getWebElement(addNewUserButtonLocator);
        clickOnWebElement(addNewUserButton);
        AddUserDialogBox addUserDialogBox = new AddUserDialogBox(driver);
        return addUserDialogBox;
    }
}
