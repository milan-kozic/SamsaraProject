package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.PropertiesUtils;

import java.util.ArrayList;
import java.util.List;

public class HeroesPage extends LoggedInNavigationBar {

    private final String HEROES_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.HEROES_PAGE;

    @FindBy(xpath = "//div[contains(@class,'panel-title')]")
    private WebElement pageTitle;

    @FindBy(id = "search")
    private WebElement searchTextBox;

    @FindBy(xpath = "//div[@id='custom-search-input']//i[contains(@class, 'glyphicon-search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//a[contains(@class, 'btn-info') and contains(@onclick,'openAddHeroModal')]")
    private WebElement addNewHeroButton;

    @FindBy(xpath = "//table[@id='heroes-table']/tbody/tr/td[1]")
    private List<WebElement> heroNameWebElements;

    private final By heroesTableLocator = By.id("heroes-table");

    public HeroesPage(WebDriver driver) {
        super(driver);
    }

    public HeroesPage open(boolean bVerify) {
        log.debug("openUsersPage(" + HEROES_PAGE_URL + ")");
        openUrl(HEROES_PAGE_URL);
        if(bVerify) {
            verifyHeroesPage();
        }
        return this;
    }

    public HeroesPage open() {
        return open(true);
    }

    public HeroesPage verifyHeroesPage() {
        log.debug("verifyHeroesPage()");
        waitForUrlChange(HEROES_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }

    public boolean isPageTitleDisplayed() {
        log.debug("isPageTitleDisplayed()");
        return isWebElementDisplayed(pageTitle);
    }

    public String getPageTitle() {
        log.debug("getPageTitle()");
        return getTextFromWebElement(pageTitle);
    }

    public boolean isSearchTextBoxDisplayed() {
        log.debug("isSearchTextBoxDisplayed()");
        return isWebElementDisplayed(searchTextBox);
    }

    public boolean isSearchTextBoxEnabled() {
        log.debug("isSearchTextBoxEnabled()");
        Assert.assertTrue(isSearchTextBoxDisplayed(), "Search TextBox is NOT displayed on Heroes Page!");
        return isWebElementEnabled(searchTextBox);
    }

    public HeroesPage typeSearchText(String sSearchText) {
        log.debug("typeSearchText(" + sSearchText + ")");
        Assert.assertTrue(isSearchTextBoxEnabled(), "Search TextBox is NOT enabled on Heroes Page!");
        clearAndTypeTextToWebElement(searchTextBox, sSearchText);
        return this;
    }

    public String getSearchText() {
        log.debug("getSearchText()");
        Assert.assertTrue(isSearchTextBoxDisplayed(), "Search TextBox is NOT displayed on Heroes Page!");
        return getValueFromWebElement(searchTextBox);
    }

    public boolean isSearchButtonDisplayed() {
        log.debug("isSearchButtonDisplayed()");
        return isWebElementDisplayed(searchButton);
    }

    public boolean isSearchButtonEnabled() {
        log.debug("isSearchButtonEnabled()");
        Assert.assertTrue(isSearchButtonDisplayed(), "Search Button is NOT displayed on Heroes Page!");
        return isWebElementEnabled(searchButton);
    }

    public HeroesPage clickSearchButton() {
        log.debug("clickSearchButton()");
        Assert.assertTrue(isSearchButtonEnabled(), "Search Button is NOT enabled on Heroes Page!");
        clickOnWebElement(searchButton);
        HeroesPage heroesPage = new HeroesPage(driver);
        return heroesPage.verifyHeroesPage();
    }

    /**
     * Search for Hero by provided text
     * @param sSearchText {String} - Search Text
     * @return {HeroesPage}
     */
    public HeroesPage searchUser(String sSearchText) {
        log.info("searchUser(" + sSearchText + ")");
        typeSearchText(sSearchText);
        return clickSearchButton();
    }

    public boolean isAddNewHeroButtonDisplayed() {
        log.debug("isAddNewHeroButtonDisplayed()");
        return isWebElementDisplayed(addNewHeroButton);
    }

    public boolean isAddNewHeroButtonEnabled() {
        log.debug("isAddNewHeroButtonEnabled()");
        Assert.assertTrue(isAddNewHeroButtonDisplayed(), "'Add New Hero' Button is NOT displayed on Heroes Page!");
        return isWebElementEnabled(addNewHeroButton);
    }

    public AddHeroDialogBox clickAddNewHeroButton() {
        log.debug("clickAddNewHeroButton()");
        Assert.assertTrue(isAddNewHeroButtonEnabled(), "'Add New Hero' Button is NOT enabled on Heroes Page!");
        clickOnWebElement(addNewHeroButton);
        AddHeroDialogBox addHeroDialogBox = new AddHeroDialogBox(driver);
        return addHeroDialogBox.verifyAddHeroDialogBox();
    }

    public List<String> getAllHeroNames() {
        List<String> heroNames = new ArrayList<>(heroNameWebElements.size());
        for(int i = 0; i< heroNameWebElements.size(); i++) {
            WebElement heroNameWebElement = heroNameWebElements.get(i);
            String sHeroName = getTextFromWebElement(heroNameWebElement);
            heroNames.add(sHeroName);
        }
        return heroNames;
    }
}
