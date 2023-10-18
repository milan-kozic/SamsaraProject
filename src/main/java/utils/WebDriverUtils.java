package utils;

import data.Time;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Set;

public class WebDriverUtils extends LoggerUtils {

    public static WebDriver setUpDriver() {

        WebDriver driver = null;

        String sBrowser = PropertiesUtils.getBrowser();
        boolean bRemote = PropertiesUtils.getRemote();
        boolean bHeadless = PropertiesUtils.getHeadless();

        log.info("setUpDriver( Browser: " + sBrowser + ", Remote: " + bRemote + ", Headless: " + bHeadless + ")");

        String sHubUrl = PropertiesUtils.getHubUrl();

        String sDriversFolder = PropertiesUtils.getDriversFolder();

        String sPathDriverChrome = sDriversFolder + "chromedriver.exe";
        String sPathDriverFirefox = sDriversFolder + "geckodriver.exe";
        String sPathDriverEdge = sDriversFolder + "msedgedriver.exe";

        try {
            switch (sBrowser) {

                case "chrome": {
                    ChromeOptions options = new ChromeOptions();
                    if (bHeadless) {
                        options.addArguments("--headless");
                    }
                    options.addArguments("--window-size=1600x900");

                    HashMap<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("download.default_directory", "/directory/path");
                    prefs.put("safebrowsing.enabled", "false");
                    options.setExperimentalOption("prefs", prefs);

                    if (bRemote) {
                        RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(sHubUrl), options);
                        remoteDriver.setFileDetector(new LocalFileDetector());
                        driver = remoteDriver;
                    } else {
                        System.setProperty("webdriver.chrome.driver", sPathDriverChrome);
                        driver = new ChromeDriver(options);
                    }
                    break;
                }

                case "firefox": {
                    FirefoxOptions options = new FirefoxOptions();
                    if (bHeadless) {
                        options.addArguments("--headless");
                    }
                    options.addArguments("--window-size=1600x900");

                    FirefoxProfile profile = new FirefoxProfile();

                    profile.setPreference("browser.download.folderList", 2);
                    profile.setPreference("browser.download.dir", "downloads/directory/path");
                    profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv,application/zip");

                    options.setProfile(profile);

                    if (bRemote) {
                        RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(sHubUrl), options);
                        remoteDriver.setFileDetector(new LocalFileDetector());
                        driver = remoteDriver;
                    } else {
                        System.setProperty("webdriver.gecko.driver", sPathDriverFirefox);
                        driver = new FirefoxDriver(options);
                    }
                    break;
                }

                case "edge": {
                    EdgeOptions options = new EdgeOptions();
                    if (bHeadless) {
                        options.addArguments("--headless");
                    }
                    options.addArguments("--window-size=1600x900");

                    if (bRemote) {
                        RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(sHubUrl), options);
                        remoteDriver.setFileDetector(new LocalFileDetector());
                        driver = remoteDriver;
                    } else {
                        System.setProperty("webdriver.msedge.driver", sPathDriverEdge);
                        driver = new EdgeDriver(options);
                    }
                    break;
                }
                default : {
                    Assert.fail("Cannot create driver! Browser '" + sBrowser + "' is not recognized!");
                }

            }
        } catch (Exception e) {
            Assert.fail("Cannot create driver! Error Message: " + e.getMessage());
        }

        DateTimeUtils.wait(3);

        // Setup Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Time.IMPLICIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Time.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Time.ASYNC_SCRIPT_TIMEOUT));

        // Maximize Browser
        driver.manage().window().maximize();

        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        log.debug("quitDriver()");
        if(driver != null) {
            driver.quit();
        }
    }

    public static void setImplicitWait(WebDriver driver, int timeout) {
        log.trace("setImplicitWait(" + driver + ")");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    public static String getCookies(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        StringBuilder sCookies = new StringBuilder();
        for(Cookie cookie : cookies) {
            sCookies.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
        }
        return sCookies.toString();
    }
}
