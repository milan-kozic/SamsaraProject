package data;

import org.testng.Assert;
import utils.PropertiesUtils;

import java.util.Properties;

public final class CommonStrings {

    private static final String sLocaleFile = "locale_" + PropertiesUtils.getLocale() + ".loc";
    private static final String sLocalePath = "\\locale\\" + sLocaleFile;
    private static final Properties locale = PropertiesUtils.loadPropertiesFile(sLocalePath);

    private static String getLocaleString(String sTitle) {
        String sResult = locale.getProperty(sTitle);
        Assert.assertNotNull(sResult, "Cannot find string '" + sTitle + "' in " + sLocaleFile + " file!");
        return sResult;
    }

    public static String getLogoutSuccessMessage() {
        return getLocaleString("LOGOUT_SUCCESS_MESSAGE");
    }

    public static String getLoginErrorMessage() {
        return getLocaleString("LOGIN_ERROR_MESSAGE");
    }

    public static String getDeleteUserMessage(String sUsername, String sFullName) {
        return getLocaleString("DELETE_USER_MESSAGE").replace("%USERNAME%", sUsername).replace("%FULL_NAME%", sFullName);
    }

    public static String getUselessTooltip() {
        return getLocaleString("USELESS_TOOLTIP");
    }

    public static String getUselessTooltipTitle() {
        return getLocaleString("USELESS_TOOLTIP_TITLE");
    }
}
