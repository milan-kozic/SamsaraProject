package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils extends LoggerUtils {

    public static void wait (int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException in Thread.sleep(). Message: " + e.getMessage());
        }
    }

    public static Date getCurrentDateTime() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    public static String getFormattedDateTime(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static String getFormattedCurrentDateTime(String pattern) {
        Date date = getCurrentDateTime();
        return getFormattedDateTime(date, pattern);
    }

    public static String getLocalizedDateTime(Date date, String pattern, String locale) {
        Locale loc = new Locale(locale);
        DateFormat dateFormat = new SimpleDateFormat(pattern, loc);
        return dateFormat.format(date);
    }

    public String getLocalizedCurrentDateTime(String pattern, String locale) {
        Date date = getCurrentDateTime();
        return getLocalizedDateTime(date, pattern, locale);
    }

    public static String getDateTimeStamp() {
        String pattern = "yyMMddHHmmss";
        return getFormattedCurrentDateTime(pattern);
    }

    public static Date getParsedDateTime(String sDateTime, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date  date = null;
        try {
            date = dateFormat.parse(sDateTime);
        } catch (ParseException e) {
            Assert.fail("Cannot parse date '" + sDateTime + "' using pattern '" + pattern + "'! Message: " + e.getMessage());
        }
        return date;
    }

    public static String getBrowserDateTimeString(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String sJavaScript = "var browserDateTime = new Date().getTime(); return Intl.DateTimeFormat('en-GB', {dateStyle: 'full', timeStyle: 'long' }).format(browserDateTime);";
        return (String) js.executeScript(sJavaScript);
    }

    public static Date getBrowserDateTime(WebDriver driver) {
        String sBrowserDateTime = getBrowserDateTimeString(driver);
        sBrowserDateTime = sBrowserDateTime.replace(" at ", " ");
        return getParsedDateTime(sBrowserDateTime, "EEEE, d MMMM yyyy HH:mm:ss z");
    }

    public static String getBrowserTimeZone(WebDriver driver) {
        String sBrowserDateTime = getBrowserDateTimeString(driver);
        int index = sBrowserDateTime.lastIndexOf(" ");
        String sTimeZone = sBrowserDateTime.substring(index + 1);
        return sTimeZone;
    }



}
