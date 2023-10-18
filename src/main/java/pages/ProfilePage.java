package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.DateTimeUtils;
import utils.PropertiesUtils;

public class ProfilePage extends LoggedInNavigationBar {

    private final String PROFILE_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.PROFILE_PAGE;

    @FindBy(xpath = "//input[@type='file' and @name='image']")
    private WebElement profileImageFilePathField;

    @FindBy(xpath = "//form[@id='apply-avatar-form']//button[@type='submit']")
    private WebElement uploadImageButton;

    @FindBy(id="username")
    private WebElement usernameTextField;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage open(boolean bVerify) {
        log.debug("openProfilePage(" + PROFILE_PAGE_URL + ")");
        openUrl(PROFILE_PAGE_URL);
        if(bVerify) {
            verifyProfilePage();
        }
        return this;
    }

    public ProfilePage open() {
        return open(true);
    }

    public ProfilePage verifyProfilePage() {
        log.debug("verifyProfilePage()");
        waitForUrlChange(PROFILE_PAGE_URL, Time.TIME_SHORTER);
        waitUntilPageIsReady(Time.TIME_SHORT);
        return this;
    }

    public boolean isProfileImageFilePathFieldDisplayed() {
        log.debug("isProfileImageFilePathFieldDisplayed()");
        return isWebElementDisplayed(profileImageFilePathField);
    }

    public ProfilePage typeProfileImageFilePath(String sFilePath) {
        Assert.assertTrue(isProfileImageFilePathFieldDisplayed(), "Profile Image FilePath Field is NOT displayed on Profile Page!");
        clearAndTypeTextToWebElement(profileImageFilePathField, sFilePath);
        return this;
    }

    public boolean isUploadImageButtonDisplayed() {
        log.debug("isUploadImageButtonDisplayed()");
        return isWebElementDisplayed(uploadImageButton);
    }

    public boolean isUploadImageButtonEnabled() {
        log.debug("isUploadImageButtonEnabled()");
        Assert.assertTrue(isUploadImageButtonDisplayed(), "Upload Image Button is NOT displayed on Profile Page!");
        return isWebElementEnabled(uploadImageButton);
    }

    public ProfilePage clickUploadImageButton() {
        log.debug("clickUploadImageButton()");
        Assert.assertTrue(isUploadImageButtonEnabled(), "Upload Image Button is NOT enabled on Profile Page!");
        clickOnWebElement(uploadImageButton);
        return this;
    }

    public ProfilePage uploadProfileImage(String sFileName) {
        log.debug("uploadProfileImage(" + sFileName + ")");
        String sFilePath = System.getProperty("user.dir") + PropertiesUtils.getImagesFolder() + sFileName;
        typeProfileImageFilePath(sFilePath);
        DateTimeUtils.wait(Time.TIME_SHORTEST);
        return clickUploadImageButton();
    }

    public ProfilePage typeUsername(String sUsername) {
        log.debug("typeUsername(" + sUsername + ")");
        removeAttributeFromWebElement(usernameTextField, "readonly");
        DateTimeUtils.wait(Time.TIME_SHORTEST);
        clearAndTypeTextToWebElement(usernameTextField, sUsername);
        DateTimeUtils.wait(Time.TIME_SHORTEST);
        setAttributeToWebElement(usernameTextField, "readonly", "readonly");
        DateTimeUtils.wait(Time.TIME_SHORTEST);
        return this;
    }
}
