package pages;

import data.Time;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AddHeroDialogBox extends BasePageClass {

    @FindBy(id = "addHeroModal")
    private WebElement addHeroDialogBox;
    
    private final String addHeroDialogBoxLocatorString = "//div[@id='addHeroModal']";
    
    @FindBy (xpath = addHeroDialogBoxLocatorString + "//button[contains(@class, 'btn-default')]")
    private WebElement addHeroDialogBoxTitle;

    @FindBy (id = "name")
    private WebElement heroNameTextField;

    @FindBy (id="level")
    private WebElement heroLevelTextField;

    @FindBy (id = "type")
    private WebElement heroClassDropDownList;
    
    @FindBy (xpath = addHeroDialogBoxLocatorString + "//button[contains(@class, 'btn-default')]")
    private WebElement cancelButton;
    
    public AddHeroDialogBox(WebDriver driver) {
        super(driver);
    }
    
    public AddHeroDialogBox verifyAddHeroDialogBox() {
        log.debug("verifyAddHeroDialogBox()");
        Assert.assertTrue(isAddHeroDialogBoxOpened(Time.TIME_SHORT), "'Add Hero' DialogBox is NOT opened!");
        waitUntilPageIsReady(Time.TIME_SHORTER);
        return this;
    }

    public boolean isAddHeroDialogBoxOpened(int timeout) {
        return isWebElementVisible(addHeroDialogBox, timeout);
    }

    public boolean isAddHeroDialogBoxClosed(int timeout) {
        return isWebElementInvisible(addHeroDialogBox, timeout);
    }

    public boolean isDialogBoxTitleDisplayed() {
        log.debug("isDialogBoxTitleDisplayed()");
        return isWebElementDisplayed(addHeroDialogBoxTitle);
    }

    public String getDialogBoxTitle() {
        log.debug("getDialogBoxTitle()");
        Assert.assertTrue(isDialogBoxTitleDisplayed(), "DialogBox Title is NOT displayed on 'Add Hero' DialogBox");
        return getTextFromWebElement(addHeroDialogBoxTitle);
    }

    public boolean isHeroNameTextFieldDisplayed() {
        log.debug("isHeroNameTextFieldDisplayed()");
        return isWebElementDisplayed(heroNameTextField);
    }

    public boolean isHeroNameTextFieldEnabled() {
        log.debug("isHeroNameTextFieldEnabled()");
        Assert.assertTrue(isHeroNameTextFieldDisplayed(), "HeroName TextField is NOT displayed on 'Add Hero' DialogBox!");
        return isWebElementEnabled(heroNameTextField);
    }

    public AddHeroDialogBox typeHeroName(String sHeroName) {
        log.debug("typeHeroName(" + sHeroName + ")");
        Assert.assertTrue(isHeroNameTextFieldEnabled(), "HeroName TextField is NOT enabled on 'Add Hero' DialogBox!");
        clearAndTypeTextToWebElement(heroNameTextField, sHeroName);
        return this;
    }

    public String getHeroName() {
        log.debug("getHeroName()");
        Assert.assertTrue(isHeroNameTextFieldDisplayed(), "HeroName TextField is NOT displayed on 'Add Hero' DialogBox!");
        return getValueFromWebElement(heroNameTextField);
    }

    public boolean isHeroLevelTextFieldDisplayed() {
        log.debug("isHeroLevelTextFieldDisplayed()");
        return isWebElementDisplayed(heroLevelTextField);
    }

    public boolean isHeroLevelTextFieldEnabled() {
        log.debug("isHeroLevelTextFieldEnabled()");
        Assert.assertTrue(isHeroLevelTextFieldDisplayed(), "HeroLevel TextField is NOT displayed on 'Add Hero' DialogBox!");
        return isWebElementEnabled(heroLevelTextField);
    }

    public AddHeroDialogBox typeHeroLevel(String sHeroLevel) {
        log.debug("typeHeroLevel(" + sHeroLevel + ")");
        Assert.assertTrue(isHeroLevelTextFieldEnabled(), "HeroLevel TextField is NOT enabled on 'Add Hero' DialogBox!");
        clearAndTypeTextToWebElement(heroLevelTextField, sHeroLevel);
        return this;
    }

    public AddHeroDialogBox typeHeroLevel(int iHeroLevel) {
        log.debug("typeHeroLevel(" + iHeroLevel + ")");
        Assert.assertTrue(isHeroLevelTextFieldEnabled(), "HeroLevel TextField is NOT enabled on 'Add Hero' DialogBox!");
        String sHeroLevel = Integer.toString(iHeroLevel);
        clearAndTypeTextToWebElement(heroLevelTextField, sHeroLevel);
        return this;
    }

    public String getHeroLevel() {
        log.debug("getHeroLevel()");
        Assert.assertTrue(isHeroLevelTextFieldDisplayed(), "HeroLevel TextField is NOT displayed on 'Add Hero' DialogBox!");
        return getValueFromWebElement(heroLevelTextField);
    }

    public boolean isHeroClassDropDownListDisplayed() {
        log.debug("isHeroClassDropDownListDisplayed()");
        return isWebElementDisplayed(heroClassDropDownList);
    }

    public boolean isHeroClassDropDownListEnabled() {
        log.debug("isHeroClassDropDownListEnabled()");
        Assert.assertTrue(isHeroClassDropDownListDisplayed(), "HeroClass DropDownList is NOT displayed on 'Add Hero' DialogBox!");
        return isWebElementEnabled(heroClassDropDownList);
    }

    public String getSelectedHeroClass() {
        log.debug("getSelectedHeroClass()");
        Assert.assertTrue(isHeroClassDropDownListDisplayed(), "HeroClass DropDownList is NOT displayed on 'Add Hero' DialogBox!");
        return getFirstSelectedOptionOnWebElement(heroClassDropDownList);
    }

    public boolean isOptionPresentInHeroClassDropDownList(String sClass) {
        log.debug("isHeroClassOptionPresent(" + sClass + ")");
        Assert.assertTrue(isHeroClassDropDownListEnabled(), "HeroClass DropDownList is NOT enabled on 'Add Hero' DialogBox!");
        return isOptionPresentOnWebElement(heroClassDropDownList, sClass);
    }

    public AddHeroDialogBox selectHeroClass(String sClass) {
        log.debug("selectHeroClass(" + sClass + ")");
        Assert.assertTrue(isOptionPresentInHeroClassDropDownList(sClass), "Option '" + sClass + "' doesn't exist in HeroClass DropDownMenu!");
        selectOptionOnWebElement(heroClassDropDownList, sClass);
        return this;
    }

    public boolean isCancelButtonDisplayed() {
        log.debug("isCancelButtonDisplayed()");
        return isWebElementDisplayed(cancelButton);
    }

    public boolean isCancelButtonEnabled() {
        log.debug("isCancelButtonEnabled()");
        Assert.assertTrue(isCancelButtonDisplayed(), "Cancel Button is NOT displayed on 'Add Hero' DialogBox!");
        return isWebElementEnabled(cancelButton);
    }

    public HeroesPage clickCancelButton() {
        log.debug("clickCancelButton()");
        Assert.assertTrue(isCancelButtonEnabled(), "Cancel Button is NOT enabled on 'Add Hero' DialogBox'!");
        clickOnWebElement(cancelButton);
        Assert.assertTrue(isAddHeroDialogBoxClosed(Time.TIME_SHORTER), "'Add Hero' DialogBox should NOT be opened!");
        HeroesPage heroesPage = new HeroesPage(driver);
        return heroesPage.verifyHeroesPage();
    }
}
