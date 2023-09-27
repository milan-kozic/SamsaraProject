package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.PropertiesUtils;

import java.util.ArrayList;
import java.util.List;

public class UsersPage extends LoggedInNavigationBar {

    private final String USERS_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.USERS_PAGE;

    private final By addNewUserButtonLocator = By.xpath("//a[contains(@class, 'btn-info') and contains(@onclick,'openAddUserModal')]");

    private final By usersTableLocator = By.id("users-table");

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
        return addUserDialogBox.verifyAddUserDialogBox();
    }

    public List<String> getAllUsernames() {
        String xPath = "//table[@id='users-table']/tbody/tr/td[1]";
        List<WebElement> usernameWebElements = getWebElements(By.xpath(xPath));
        List<String> usernames = new ArrayList<>(usernameWebElements.size());
        for(int i = 0; i< usernameWebElements.size(); i++) {
            WebElement usernameWebElement = usernameWebElements.get(i);
            String sUsername = getTextFromWebElement(usernameWebElement);
            usernames.add(sUsername);
        }
        return usernames;
    }

    private String createXpathForUsernameInUsersTable(String sUsername) {
        return "./tbody/tr/td[1]/self::td[text()='" + sUsername + "']";
    }

    public boolean isUserPresentInUsersTable(String sUsername) {
        log.debug("isUserPresentInUsersTable(" + sUsername + ")");
        WebElement usersTable = getWebElement(usersTableLocator);
        String xPath = createXpathForUsernameInUsersTable(sUsername);
        return isNestedWebElementDisplayed(usersTable, By.xpath(xPath));
    }

    public int getUserRowNumber(String sUsername) {
        List<String> usernames = getAllUsernames();
        return usernames.indexOf(sUsername) + 1;
    }

    public String getUsersDisplayName(String sUsername) {
        int row = getUserRowNumber(sUsername);
        if (row == 0) {
            Assert.fail("User '" + sUsername + "' doesn't exist in Users Table!");
        }
        String xPath = "//table[@id='users-table']/tbody/tr[" + row + "]/td[2]";
        log.debug("XPATH: " + xPath);
        WebElement displayName = getWebElement(By.xpath(xPath));
        return getTextFromWebElement(displayName);
    }

    public String getUsersHeroCount(String sUsername) {
        int row = getUserRowNumber(sUsername);
        if (row == 0) {
            Assert.fail("User '" + sUsername + "' doesn't exist in Users Table!");
        }
        String xPath = "//table[@id='users-table']/tbody/tr[" + row + "]/td[3]";
        log.debug("XPATH: " + xPath);
        WebElement heroCount = getWebElement(By.xpath(xPath));
        return getTextFromWebElement(heroCount);
    }

}
