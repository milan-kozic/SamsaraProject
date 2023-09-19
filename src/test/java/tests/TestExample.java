package tests;

import data.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DateTimeUtils;
import utils.PropertiesUtils;
import utils.WebDriverUtils;

import java.time.Duration;

@Test (groups={Groups.REGRESSION})
public class TestExample {

    @Test
    public void testSuccessfulLoginLogout() {

        WebDriver driver = null;
        try {

            driver = WebDriverUtils.setUpDriver();

            String sLoginUrl = PropertiesUtils.getBaseUrl() + "/login";
            driver.get(sLoginUrl);
            DateTimeUtils.wait(1);

            // Implicit Wait, Explicit Wait

            WebElement usernameTextField = driver.findElement(By.id("username"));
            usernameTextField.sendKeys("user");
            DateTimeUtils.wait(1);

            WebElement passwordTextField = driver.findElement(By.id("password"));
            passwordTextField.sendKeys("password");
            DateTimeUtils.wait(1);

            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement loginButton = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@class, 'btn_primary')]")));
            //WebElement loginButton = driver.findElement(By.xpath("//input[contains(@class, 'btn-primary')]"));

            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
            loginButton = wait2.until(ExpectedConditions.elementToBeClickable(loginButton));

            loginButton.click();
            DateTimeUtils.wait(1);
            //driver.findElement(By.cssSelector("input.btn-primary")).click();

            //String sActualUrl = driver.getCurrentUrl();
            //String sExpectedUrl = "http://18.219.75.209:8080/";


            //Assert.assertEquals(sActualUrl, sExpectedUrl, "Wrong Url!");

            String sActualText = driver.findElement(By.xpath("//div[contains(@class,'panel-title')]")).getText();
            String sExpectedText = "Hello, and welcome to our gamers page!";

            Assert.assertEquals(sActualText, sExpectedText, "Wrong WelcomePage Title!");

            driver.findElement(By.xpath("//a[contains(@href, 'logoutForm')]")).click();
            DateTimeUtils.wait(1);

            String sActualSuccessMessage = driver.findElement(By.xpath("//div[@id='loginbox']//div[contains(@class, 'alert-success')]")).getText();
            String sExpectedSuccessMessage = "You have been logged out.";

            Assert.assertEquals(sActualSuccessMessage, sExpectedSuccessMessage, "Wrong Success Message!");

        } finally {
            WebDriverUtils.quitDriver(driver);
        }
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {

        String sDriverFolder = "C:\\Selenium\\";
        System.setProperty("webdriver.chrome.driver", sDriverFolder + "chromedriver.exe");

        WebDriver driver = null;
        try {

            driver = WebDriverUtils.setUpDriver();

            String sLoginUrl = PropertiesUtils.getBaseUrl() + "/login";
            driver.get(sLoginUrl);
            DateTimeUtils.wait(1);

            driver.findElement(By.id("username")).sendKeys("user");
            DateTimeUtils.wait(1);

            driver.findElement(By.id("password")).sendKeys("12345678");
            DateTimeUtils.wait(1);

            driver.findElement(By.xpath("//input[contains(@class, 'btn-primary')]")).click();
            DateTimeUtils.wait(1);
            //driver.findElement(By.cssSelector("input.btn-primary")).click();

            String sActualErrorMessage = driver.findElement(By.xpath("//div[@id='loginbox']//div[contains(@class, 'alert-danger')]")).getText();
            String sExpectedErrorMessage = "Invalid username and/or password.";

            Assert.assertEquals(sActualErrorMessage, sExpectedErrorMessage, "Wrong Error Message!");

        } finally {
            WebDriverUtils.quitDriver(driver);
        }
    }
}
