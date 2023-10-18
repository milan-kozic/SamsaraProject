package utils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUtils extends LoggerUtils {

    public static String downloadTextFile(WebDriver driver, String link) {
        StringBuilder sFile = new StringBuilder();
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            String sCookies = WebDriverUtils.getCookies(driver);
            httpURLConnection.addRequestProperty("Cookie", sCookies);
            log.info("COOKIES: " + sCookies);
            InputStream inputStream = httpURLConnection.getInputStream();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sFile.append(line).append("\n");
                }
            } catch (IOException e) {
                Assert.fail("File from link '" + link + "' cannot be downloaded. Message: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("File from link '" + link + "' cannot be downloaded. Message: " + e.getMessage());
        }
        return sFile.toString();
    }
}
