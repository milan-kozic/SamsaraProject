package tests;

import data.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DateTimeUtils;
import utils.WebDriverUtils;

@Test (groups={Groups.REGRESSION})
public class TestExample {

    @Test
    public void testSuccessfulLoginLogout() {

        String sDriverFolder = "C:\\Selenium\\";
        System.setProperty("webdriver.chrome.driver", sDriverFolder + "chromedriver.exe");

        WebDriver driver = null;
        try {

            driver = new ChromeDriver();
            DateTimeUtils.wait(3);

            driver.manage().window().maximize();

            driver.get("http://18.219.75.209:8080/login");
            DateTimeUtils.wait(1);

            driver.findElement(By.id("username")).sendKeys("user");
            DateTimeUtils.wait(1);
            driver.findElement(By.id("password")).sendKeys("password");
            DateTimeUtils.wait(1);
            driver.findElement(By.xpath("//input[contains(@class, 'btn-primary')]")).click();
            DateTimeUtils.wait(1);
            //driver.findElement(By.cssSelector("input.btn-primary")).click();

            String sActualUrl = driver.getCurrentUrl();
            String sExpectedUrl = "http://18.219.75.209:8080/";


            Assert.assertEquals(sActualUrl, sExpectedUrl, "Wrong Url!");

            String sActualText = driver.findElement(By.xpath("//div[contains(@class,'panel-title')]")).getText();
            String sExpectedText = "Hello, and welcome to our gamers page!";

            Assert.assertEquals(sActualText, sExpectedText, "Wrong WelcomePage Title!");

            driver.findElement(By.xpath("//a[contains(@href, 'logoutForm')]")).click();
            DateTimeUtils.wait(1);

            String sActualSuccessMessage = driver.findElement(By.xpath("//div[@id='loginbox']//div[contains(@class, 'alert-success')]")).getText();
            String sExpectedSuccessMessage = "You have been logged out.";

            Assert.assertEquals(sActualSuccessMessage, sExpectedSuccessMessage, "Wrong Success Message!");

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testUnsuccessfulLoginWrongPassword() {

        String sDriverFolder = "C:\\Selenium\\";
        System.setProperty("webdriver.chrome.driver", sDriverFolder + "chromedriver.exe");

        WebDriver driver = null;
        try {

            driver = new ChromeDriver();
            DateTimeUtils.wait(3);

            driver.manage().window().maximize();

            driver.get("http://18.219.75.209:8080/login");
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
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
