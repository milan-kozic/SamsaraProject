package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.URL;
import java.time.Duration;

public class WebDriverUtils {

    public static WebDriver setUpDriver() {

        WebDriver driver = null;

        String sBrowser = PropertiesUtils.getBrowser();
        boolean bRemote = PropertiesUtils.getRemote();
        boolean bHeadless = PropertiesUtils.getHeadless();

        String sHubUrl = "http://localhost:4444";

        String sDriverFolder = "C:\\Selenium\\";

        String sPathDriverChrome = sDriverFolder + "chromedriver.exe";
        String sPathDriverFirefox = sDriverFolder + "geckodriver.exe";
        String sPathDriverEdge = sDriverFolder = "msedgedriver.exe";

        try {
            switch (sBrowser) {

                case "chrome": {
                    ChromeOptions options = new ChromeOptions();
                    if (bHeadless) {
                        options.addArguments("--headless");
                    }
                    options.addArguments("--window-size=1600x900");

                    if (bRemote) {
                        RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(sHubUrl), options);
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

                    if (bRemote) {
                        RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(sHubUrl), options);
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // Maximize Browser
        driver.manage().window().maximize();

        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        if(driver != null) {
            driver.quit();
        }
    }
}
