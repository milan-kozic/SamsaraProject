package utils;

import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    private static final String sPropertiesFilePath = "test.properties";

    private static final Properties properties = loadPropertiesFile();

    public static Properties loadPropertiesFile(String sFilePath) {
        InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(sFilePath);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            Assert.fail("Cannot load '" + sFilePath + "' file! Message: " + e.getMessage());
        }
        return properties;
    }

    private static Properties loadPropertiesFile() {
        return loadPropertiesFile(sPropertiesFilePath);
    }

    private static String getProperty(String sProperty) {
        String sResult = properties.getProperty(sProperty);
        Assert.assertNotNull(sResult, "Cannot find property '" + sProperty + "' in " + sPropertiesFilePath + " file!");
        return sResult;
    }

    public static String getEnvironment() {
        String sEnvironment = getProperty("environment");
        return sEnvironment;
    }

    private static String getLocalBaseUrl() {
        return getProperty("localBaseUrl");
    }

    private static String getTestBaseUrl() {
        return getProperty("testBaseUrl");
    }

    private static String getStageBaseUrl() {
        return getProperty("stageBaseUrl");
    }

    private static String getProdBaseUrl() {
        return getProperty("prodBaseUrl");
    }

    public static String getBaseUrl() {
        String sEnvironment = getEnvironment().toLowerCase();
        String sBaseUrl = null;
        switch (sEnvironment) {
            case "local" : {
                sBaseUrl = getLocalBaseUrl();
                break;
            }
            case "test" : {
                sBaseUrl = getTestBaseUrl();
                break;
            }
            case "stage" : {
                sBaseUrl = getStageBaseUrl();
                break;
            }
            case "prod" : {
                sBaseUrl = getProdBaseUrl();
                break;
            }
            default : {
                Assert.fail("Cannot get BaseUrl! Environment '" + sEnvironment + "' is not recognized!");
            }
        }
        return sBaseUrl;
    }

    public static String getBrowser() {
        String sBrowser = getProperty("browser");
        return sBrowser;
    }

    public static boolean getHeadless() {
        String sHeadless = getProperty("headless").toLowerCase();
        if(!(sHeadless.equals("true") || sHeadless.equals("false"))) {
            Assert.fail("Cannot convert 'Headless' property value '" + sHeadless + "' to boolean value!");
        }
        boolean bHeadless = Boolean.parseBoolean(sHeadless);
        return bHeadless;
    }

    public static boolean getRemote() {
        String sRemote = getProperty("remote").toLowerCase();
        if(!(sRemote.equals("true") || sRemote.equals("false"))) {
            Assert.fail("Cannot convert 'Remote' property value '" + sRemote + "' to boolean value!");
        }
        boolean bRemote = Boolean.parseBoolean(sRemote);
        return bRemote;
    }






}
