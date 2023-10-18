package data;

import utils.PropertiesUtils;

public class ApiCalls {

    private static final String CHECK_IF_USER_EXISTS = "/api/users/exists/";
    private static final String GET_USER = "/api/users/findByUsername/";
    private static final String DELETE_USER = "/api/users/deleteByUsername/";
    private static final String POST_USER = "/api/users/add";

    public static String createCheckIfUserExistsApiCall(String sUsername) {
        return PropertiesUtils.getBaseUrl() + ApiCalls.CHECK_IF_USER_EXISTS + sUsername;
    }

    public static String createGetUserApiCall(String sUsername) {
        return PropertiesUtils.getBaseUrl() + ApiCalls.GET_USER + sUsername;
    }

    public static String createDeleteUserApiCall(String sUsername) {
        return PropertiesUtils.getBaseUrl() + ApiCalls.DELETE_USER + sUsername;
    }

    public static String createPostUserApiCall() {
        return PropertiesUtils.getBaseUrl() + ApiCalls.POST_USER;
    }

}
