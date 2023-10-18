package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import utils.LoggerUtils;
import utils.RestApiUtils;
import utils.ScreenShotUtils;
import utils.WebDriverUtils;

public class BaseTestClass extends LoggerUtils {

    protected WebDriver setUpDriver() {
        return WebDriverUtils.setUpDriver();
    }

    protected void quitDriver(WebDriver driver) {
        WebDriverUtils.quitDriver(driver);
    }

    protected void tearDown(WebDriver driver, ITestResult testResult) {

        String sTestName = testResult.getTestClass().getName();

        try {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                ScreenShotUtils.takeScreenShot(driver, sTestName);
            }
        } catch (Exception | AssertionError e) {
            log.error("Exception occurred in tearDown(" + sTestName + "). Message: " + e.getMessage());
        }
        finally {
            quitDriver(driver);
        }
    }

    protected void deleteUser(String sUsername) {
        try {
            RestApiUtils.deleteUser(sUsername);
        } catch (AssertionError | Exception e) {
            log.error("Delete User '" + sUsername + "' Failed! Message: " + e.getMessage());
        }
    }
}
