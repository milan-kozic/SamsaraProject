package pages;

import data.PageUrlPaths;
import org.openqa.selenium.WebDriver;
import utils.PropertiesUtils;

public class RegisterPage extends LoggedOutNavigationBar {

    private final String REGISTER_PAGE_URL = PropertiesUtils.getBaseUrl() + PageUrlPaths.REGISTER_PAGE;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
}
