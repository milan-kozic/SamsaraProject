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

    private final By pageTitleLocator = By.xpath("//div[contains(@class,'panel-title')]");
    private final By searchTextBoxLocator = By.id("search");
    private final By searchButtonLocator = By.xpath("//div[@id='custom-search-input']//i[contains(@class, 'glyphicon-search')]");
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

    public boolean isPageTitleDisplayed() {
        log.debug("isPageTitleDisplayed()");
        return isWebElementDisplayed(pageTitleLocator);
    }

    public String getPageTitle() {
        log.debug("getPageTitle()");
        WebElement pageTitle = getWebElement(pageTitleLocator);
        return getTextFromWebElement(pageTitle);
    }

    public boolean isSearchTextBoxDisplayed() {
        log.debug("isSearchTextBoxDisplayed()");
        return isWebElementDisplayed(searchTextBoxLocator);
    }

    public boolean isSearchTextBoxEnabled() {
        log.debug("isSearchTextBoxEnabled()");
        Assert.assertTrue(isSearchTextBoxDisplayed(), "Search TextBox is NOT displayed on Users Page!");
        WebElement searchTextBox = getWebElement(searchTextBoxLocator);
        return isWebElementEnabled(searchTextBox);
    }

    public UsersPage typeSearchText(String sSearchText) {
        log.debug("typeSearchText(" + sSearchText + ")");
        Assert.assertTrue(isSearchTextBoxEnabled(), "Search TextBox is NOT enabled on Users Page!");
        WebElement searchTextBox = getWebElement(searchTextBoxLocator);
        clearAndTypeTextToWebElement(searchTextBox, sSearchText);
        return this;
    }

    public String getSearchText() {
        log.debug("getSearchText()");
        Assert.assertTrue(isSearchTextBoxDisplayed(), "Search TextBox is NOT displayed on Users Page!");
        WebElement searchTextBox = getWebElement(searchTextBoxLocator);
        return getValueFromWebElement(searchTextBox);
    }

    public boolean isSearchButtonDisplayed() {
        log.debug("isSearchButtonDisplayed()");
        return isWebElementDisplayed(searchButtonLocator);
    }

    public boolean isSearchButtonEnabled() {
        log.debug("isSearchButtonEnabled()");
        Assert.assertTrue(isSearchButtonDisplayed(), "Search Button is NOT displayed on Users Page!");
        WebElement searchButton = getWebElement(searchButtonLocator);
        return isWebElementEnabled(searchButton);
    }

    public UsersPage clickSearchButton() {
        log.debug("clickSearchButton()");
        Assert.assertTrue(isSearchButtonEnabled(), "Search Button is NOT enabled on Users Page!");
        WebElement searchButton = getWebElement(searchButtonLocator);
        clickOnWebElement(searchButton);
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.verifyUsersPage();
    }

    /**
     * Search for User by provided text
     * @param sSearchText {String} - Search Text
     * @return {UsersPage}
     */
    public UsersPage searchUser(String sSearchText) {
        log.info("searchUser(" + sSearchText + ")");
        typeSearchText(sSearchText);
        return clickSearchButton();
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
        log.debug("XPATH: " + xPath);
        return isNestedWebElementDisplayed(usersTable, By.xpath(xPath));
    }

    public int getUserRowNumber(String sUsername) {
        List<String> usernames = getAllUsernames();
        return usernames.indexOf(sUsername) + 1;
    }

    private String createXpathForDisplayNameInUsersTable(String sUsername) {
        //return createXpathForUsernameInUsersTable(sUsername) + "/../td[2]";
        return createXpathForUsernameInUsersTable(sUsername) + "/following-sibling::td[1]";
    }

    public String getDisplayNameInUsersTable(String sUsername) {
        log.debug("getDisplayNameInUsersTable(" + sUsername + ")");
        Assert.assertTrue(isUserPresentInUsersTable(sUsername), "User '" + sUsername + "' is NOT present in Users Table!");
        WebElement usersTable = getWebElement(usersTableLocator);
        String xPath = createXpathForDisplayNameInUsersTable(sUsername);
        log.debug("XPATH: " + xPath);
        WebElement displayName = getNestedWebElement(usersTable, By.xpath(xPath));
        return getTextFromWebElement(displayName);
    }

    private String createXpathForHeroCountInUsersTable(String sUsername) {
        //return createXpathForUsernameInUsersTable(sUsername) + "/../td[3]";
        return createXpathForUsernameInUsersTable(sUsername) + "/following-sibling::td[2]";
    }

    private WebElement getHeroCountLinkWebElementInUsersTable(String sUsername) {
        Assert.assertTrue(isUserPresentInUsersTable(sUsername), "User '" + sUsername + "' is NOT present in Users Table!");
        WebElement usersTable = getWebElement(usersTableLocator);
        String xPath = createXpathForHeroCountInUsersTable(sUsername);
        log.debug("XPATH: " + xPath);
        return getNestedWebElement(usersTable, By.xpath(xPath));
    }

    public int getHeroCountInUsersTable(String sUsername) {
        log.debug("getHeroCountInUsersTable(" + sUsername + ")");
        WebElement heroCount = getHeroCountLinkWebElementInUsersTable(sUsername);
        return Integer.parseInt(getTextFromWebElement(heroCount));
    }

    public UserHeroesDialogBox clickHeroCountLinkInUsersTable(String sUsername) {
        log.debug("clickHeroCountLinkInUsersTable(" + sUsername + ")");
        WebElement heroCount = getHeroCountLinkWebElementInUsersTable(sUsername);
        clickOnWebElement(heroCount);
        UserHeroesDialogBox userHeroesDialogBox = new UserHeroesDialogBox(driver);
        return userHeroesDialogBox.verifyUserHeroesDialogBox();
    }

    private String createXpathForUserIconsInUsersTable(String sUsername) {
        return createXpathForUsernameInUsersTable(sUsername) + "/following-sibling::td[3]";
    }

    private String createXpathForUserDetailsIconInUsersTable(String sUsername) {
        return createXpathForUserIconsInUsersTable(sUsername) + "/a[contains(@class, 'btn-info')]";
    }

    public boolean isUserDetailsIconDisplayedInUsersTable(String sUsername) {
        log.debug("isUserDetailsIconDisplayedInUsersTable(" + sUsername + ")");
        Assert.assertTrue(isUserPresentInUsersTable(sUsername), "User '" + sUsername + "' is NOT present in Users Table!");
        WebElement usersTable = getWebElement(usersTableLocator);
        String xPath = createXpathForUserDetailsIconInUsersTable(sUsername);
        return isNestedWebElementDisplayed(usersTable, By.xpath(xPath));
    }

    private WebElement getUserDetailsIconWebElementInUsersTable(String sUsername) {
        WebElement usersTable = getWebElement(usersTableLocator);
        String xPath = createXpathForUserDetailsIconInUsersTable(sUsername);
        return getNestedWebElement(usersTable, By.xpath(xPath));
    }

    public boolean isUserDetailsIconEnabledInUsersTable(String sUsername) {
        log.debug("isUserDetailsIconEnabledInUsersTable(" + sUsername + ")");
        Assert.assertTrue(isUserDetailsIconDisplayedInUsersTable(sUsername), "'User Details' Icon for User '" + sUsername + "' is NOT displayed in Users Table!");
        WebElement userDetailsIcon = getUserDetailsIconWebElementInUsersTable(sUsername);
        return isWebElementEnabled(userDetailsIcon);
    }

    public UserDetailsDialogBox clickUserDetailsIconInUsersTable(String sUsername) {
        log.debug("isUserDetailsIconEnabledInUsersTable(" + sUsername + ")");
        Assert.assertTrue(isUserDetailsIconEnabledInUsersTable(sUsername), "'User Details' Icon for User '" + sUsername + "' is NOT enabled in Users Table!");
        WebElement userDetailsIcon = getUserDetailsIconWebElementInUsersTable(sUsername);
        clickOnWebElement(userDetailsIcon);
        UserDetailsDialogBox userDetailsDialogBox = new UserDetailsDialogBox(driver);
        return userDetailsDialogBox.verifyUserDetailsDialogBox();
    }

    private String createXpathForEditUserIconInUsersTable(String sUsername) {
        return createXpathForUserIconsInUsersTable(sUsername) +  "/a[contains(@class, 'btn-success')]";
    }

    public boolean isEditUserIconDisplayedInUsersTable(String sUsername) {
        log.debug("isEditUserIconDisplayedInUsersTable(" + sUsername + ")");
        Assert.assertTrue(isUserPresentInUsersTable(sUsername), "User '" + sUsername + "' is NOT present in Users Table!");
        WebElement usersTable = getWebElement(usersTableLocator);
        String xPath = createXpathForEditUserIconInUsersTable(sUsername);
        return isNestedWebElementDisplayed(usersTable, By.xpath(xPath));
    }

    private WebElement getEditUserIconWebElementInUsersTable(String sUsername) {
        WebElement usersTable = getWebElement(usersTableLocator);
        String xPath = createXpathForEditUserIconInUsersTable(sUsername);
        return getNestedWebElement(usersTable, By.xpath(xPath));
    }

    public boolean isEditUserIconEnabledInUsersTable(String sUsername) {
        log.debug("isEditUserIconEnabledInUsersTable(" + sUsername + ")");
        Assert.assertTrue(isEditUserIconDisplayedInUsersTable(sUsername), "'Edit User' Icon for User '" + sUsername + "' is NOT displayed in Users Table!");
        WebElement editUserIcon = getEditUserIconWebElementInUsersTable(sUsername);
        return isWebElementEnabled(editUserIcon);
    }

    public EditUserDialogBox clickEditUserIconInUsersTable(String sUsername) {
        log.debug("clickEditUserIconInUsersTable(" + sUsername + ")");
        Assert.assertTrue(isEditUserIconEnabledInUsersTable(sUsername), "'Edit User' Icon for User '" + sUsername + "' is NOT enabled in Users Table!");
        WebElement editUserIcon = getEditUserIconWebElementInUsersTable(sUsername);
        clickOnWebElement(editUserIcon);
        EditUserDialogBox editUserDialogBox = new EditUserDialogBox(driver);
        return editUserDialogBox.verifyEditUserDialogBox();
    }

    private String createXpathForDeleteUserIconInUsersTable(String sUsername) {
        return createXpathForUserIconsInUsersTable(sUsername) + "/a[contains(@class, 'btn-danger')]";
    }

    public boolean isDeleteUserIconDisplayedInUsersTable(String sUsername) {
        log.debug("isDeleteUserIconDisplayedInUsersTable(" + sUsername + ")");
        Assert.assertTrue(isUserPresentInUsersTable(sUsername), "User '" + sUsername + "' is NOT present in Users Table!");
        WebElement usersTable = getWebElement(usersTableLocator);
        String xPath = createXpathForDeleteUserIconInUsersTable(sUsername);
        return isNestedWebElementDisplayed(usersTable, By.xpath(xPath));
    }

    private WebElement getDeleteUserIconWebElementInUsersTable(String sUsername) {
        WebElement usersTable = getWebElement(usersTableLocator);
        String xPath = createXpathForDeleteUserIconInUsersTable(sUsername);
        return getNestedWebElement(usersTable, By.xpath(xPath));
    }

    public boolean isDeleteUserIconEnabledInUsersTable(String sUsername) {
        log.debug("isDeleteUserIconEnabledInUsersTable(" + sUsername + ")");
        Assert.assertTrue(isDeleteUserIconDisplayedInUsersTable(sUsername), "'Delete User' Icon for User '" + sUsername + "' is NOT displayed in Users Table!");
        WebElement deleteUserIcon = getDeleteUserIconWebElementInUsersTable(sUsername);
        return isWebElementEnabled(deleteUserIcon);
    }

    public DeleteUserDialogBox clickDeleteUserIconInUsersTable(String sUsername) {
        log.debug("clickDeleteUserIconInUsersTable(" + sUsername + ")");
        Assert.assertTrue(isDeleteUserIconEnabledInUsersTable(sUsername), "'Delete User' Icon for User '" + sUsername + "' is NOT enabled in Users Table!");
        WebElement deleteUserIcon = getDeleteUserIconWebElementInUsersTable(sUsername);
        clickOnWebElement(deleteUserIcon);
        DeleteUserDialogBox deleteUserDialogBox = new DeleteUserDialogBox(driver);
        return deleteUserDialogBox.verifyDeleteUserDialogBox();
    }

    // 1a. //table/tbody/tr/td[1]/self::td[text()='dedoje']/../td[2]
    // 1b. //table/tbody/tr/td[1]/self::td[text()='dedoje']/parent::tr/td[2]
    //  2. //table/tbody/tr/td[1]/self::td[text()='dedoje']/following-sibling::td[1]

    //table/tbody/tr/td[1]/self::td[text()='dedoje']/following-sibling::td[3]/a[contains(@class, 'btn-info')]



//    public String getDisplayNameInUsersTable(String sUsername) {
//        int row = getUserRowNumber(sUsername);
//        if (row == 0) {
//            Assert.fail("User '" + sUsername + "' doesn't exist in Users Table!");
//        }
//        String xPath = "//table[@id='users-table']/tbody/tr[" + row + "]/td[2]";
//        log.debug("XPATH: " + xPath);
//        WebElement displayName = getWebElement(By.xpath(xPath));
//        return getTextFromWebElement(displayName);
//    }

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
