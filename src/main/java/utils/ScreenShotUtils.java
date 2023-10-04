package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtils extends LoggerUtils {

    private static final String screenShotPath = System.getProperty("user.dir") + PropertiesUtils.getScreenShotsFolder();

    public static void takeScreenShot(WebDriver driver, String sTestName) {
        log.trace("takeScreenShot(" + sTestName + ")");
        if (driver == null) {
            log.warn("ScreenShot for test '" + sTestName + "' could not be taken! Driver instance has quit!");
            return;
        }
        String sFilePath = screenShotPath + sTestName + DateTimeUtils.getDateTimeStamp() + ".png";
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File dstFile = new File(sFilePath);
        try {
            FileUtils.copyFile(srcFile, dstFile);
            log.info("ScreenShot for test '" + sTestName + "' is saved in file: " + sFilePath);
        } catch (IOException e) {
            log.warn("ScreenShot for test '" + sTestName + "' could not be taken! Error Message: " + e.getMessage());
        }
    }

}
