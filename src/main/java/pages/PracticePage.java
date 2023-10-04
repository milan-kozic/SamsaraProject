package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.PropertiesUtils;

public class PracticePage extends LoggedInNavigationBar {

    private final String PRACTICE_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.PRACTICE_PAGE;

    @FindBy (xpath = "//div[@id='useless-tooltip']/p")
    private WebElement uselessTooltipTitle;

    @FindBy (id = "useless-tooltip-text")
    private WebElement uselessTooltip;

    public PracticePage(WebDriver driver) {
        super(driver);
    }

    public PracticePage open(boolean bVerify) {
        log.debug("openPracticePage(" + PRACTICE_PAGE_URL + ")");
        openUrl(PRACTICE_PAGE_URL);
        if(bVerify) {
            verifyPracticePage();
        }
        return this;
    }

    public PracticePage open() {
        return open(true);
    }

    public PracticePage verifyPracticePage() {
        log.debug("verifyPracticePage()");
        waitForUrlChange(PRACTICE_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }

    public boolean isUselessTooltipDisplayed() {
        log.debug("isUselessTooltipDisplayed()");
        return isWebElementDisplayed(uselessTooltip);
    }

    public boolean isUselessTooltipTitleDisplayed() {
        log.debug("isUselessTooltipTitleDisplayed()");
        return isWebElementDisplayed(uselessTooltipTitle);
    }

    public String getUselessTooltipTitle() {
        log.debug("getUselessTooltipTitle()");
        Assert.assertTrue(isUselessTooltipTitleDisplayed(), "Useless Tooltip Title is NOT displayed on Practice Page!");
        return getTextFromWebElement(uselessTooltipTitle);
    }

    public String getUselessTooltip() {
        log.debug("getUselessTooltip()");
        Assert.assertTrue(isUselessTooltipTitleDisplayed(), "Useless Tooltip Title is NOT displayed on Practice Page!");
        moveMouseToWebElement(uselessTooltipTitle);
        Assert.assertTrue(isUselessTooltipDisplayed(), "Useless Tooltip is NOT displayed on Practice Page!");
        return getTextFromWebElement(uselessTooltip);
    }




}
