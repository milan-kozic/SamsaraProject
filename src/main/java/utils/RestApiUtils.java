package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.ApiCalls;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import objects.ApiError;
import objects.User;
import org.testng.Assert;

public class RestApiUtils extends LoggerUtils{

    private static final String sAdminUser = PropertiesUtils.getAdminUsername();
    private static final String sAdminPass = PropertiesUtils.getAdminPassword();

    private static Response checkIfUserExistsApiCall(String sUsername, String sAuthUser, String sAuthPass) {

        String sApiCall = ApiCalls.createCheckIfUserExistsApiCall(sUsername);
        log.debug("API CALL: " + sApiCall);

        Response response = null;
        try {
            response = RestAssured.given().auth().basic(sAuthUser, sAuthPass)
                    .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                    .when().get(sApiCall);
        } catch (Exception e) {
            Assert.fail("Exception in checkIfUserExists(" + sUsername + ") Api Call. Error Message: " + e.getMessage());
        }
        return response;
    }

    public static boolean checkIfUserExists(String sUsername, String sAuthUser, String sAuthPass) {
        log.debug("checkIfUserExists(" + sUsername + ")");
        Response response = checkIfUserExistsApiCall(sUsername, sAuthUser, sAuthPass);
        int status = response.getStatusCode();
        String sResponseBody = response.getBody().asString();
        Assert.assertEquals(status, 200, "Wrong Response Status Code in checkIfUserExists(" + sUsername + ") Api Call! Response Body: " + sResponseBody);
        return Boolean.parseBoolean(sResponseBody);
    }

    public static boolean checkIfUserExists(String sUsername) {
        return checkIfUserExists(sUsername, sAdminUser, sAdminPass);
    }

    private static Response getUserApiCall(String sUsername, String sAuthUser, String sAuthPass) {

        String sApiCall = ApiCalls.createGetUserApiCall(sUsername);
        log.debug("API CALL: " + sApiCall);

        Response response = null;
        try {
            response = RestAssured.given().auth().basic(sAuthUser, sAuthPass)
                    .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                    .when().get(sApiCall);
        } catch (Exception e) {
            Assert.fail("Exception in getUser(" + sUsername + ") Api Call. Error Message: " + e.getMessage());
        }
        return response;
    }

    public static User getUser(String sUsername, String sAuthUser, String sAuthPass) {
        log.debug("getUser(" + sUsername + ")");
        Assert.assertTrue(checkIfUserExists(sUsername, sAuthUser, sAuthPass), "User '" + sUsername + "' doesn't exist!");
        Response response = getUserApiCall(sUsername, sAuthUser, sAuthPass);
        int status = response.getStatusCode();
        String sResponseBody = response.getBody().asString();
        Assert.assertEquals(status, 200, "Wrong Response Status Code in getUser(" + sUsername + ") Api Call! Response Body: " + sResponseBody);
        Gson gson = new Gson();
        //Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.fromJson(sResponseBody, User.class);
    }

    public static User getUser(String sUsername) {
        return getUser(sUsername, sAdminUser, sAdminPass);
    }

    public static ApiError getUserError(String sUsername, String sAuthUser, String sAuthPass) {
        Response response = getUserApiCall(sUsername, sAuthUser, sAuthPass);
        String sResponseBody = response.getBody().asString();
        Gson gson = new Gson();
        return gson.fromJson(sResponseBody, ApiError.class);
    }

    public static ApiError getUserError(String sUsername) {
        return getUserError(sUsername, sAdminUser, sAdminPass);
    }

    private static Response postUserApiCall(User user, String sAuthUser, String sAuthPass) {

        String sApiCall = ApiCalls.createPostUserApiCall();
        log.debug("API CALL: " + sApiCall);

        Gson gson = new Gson();
        String json = gson.toJson(user, User.class);

        Response response = null;
        try {
            response = RestAssured.given().auth().basic(sAuthUser, sAuthPass)
                    .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                    .body(json)
                    .when().post(sApiCall);
        } catch (Exception e) {
            Assert.fail("Exception in postUser(" + user.getUsername() + ") Api Call. Error Message: " + e.getMessage());
        }
        return response;
    }

    public static void postUser(User user, String sAuthUser, String sAuthPass) {
        log.debug("postUser(" + user.getUsername() + ")");
        Assert.assertFalse(checkIfUserExists(user.getUsername(), sAuthUser, sAuthPass), "User '" + user.getUsername() + "' already exists!");
        Response response = postUserApiCall(user, sAuthUser, sAuthPass);
        int status = response.getStatusCode();
        String sResponseBody = response.getBody().asString();
        Assert.assertEquals(status, 200, "Wrong Response Status Code in getUser(" + user.getUsername() + ") Api Call! Response Body: " + sResponseBody);
        log.debug("User Created: " + checkIfUserExists(user.getUsername(), sAuthUser, sAuthPass));
    }

    public static void postUser(User user) {
        postUser(user, sAdminUser, sAdminPass);
    }

    public static ApiError postUserError(User user, String sAuthUser, String sAuthPass) {
        Response response = postUserApiCall(user, sAuthUser, sAuthPass);
        String sResponseBody = response.getBody().asString();
        Gson gson = new Gson();
        return gson.fromJson(sResponseBody, ApiError.class);
    }

    public static ApiError postUserError(User user) {
        return postUserError(user, sAdminUser, sAdminPass);
    }

    private static Response deleteUserApiCall(String sUsername, String sAuthUser, String sAuthPass) {

        String sApiCall = ApiCalls.createDeleteUserApiCall(sUsername);
        log.debug("API CALL: " + sApiCall);

        Response response = null;
        try {
            response = RestAssured.given().auth().basic(sAuthUser, sAuthPass)
                    .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                    .when().delete(sApiCall);
        } catch (Exception e) {
            Assert.fail("Exception in deleteUser(" + sUsername + ") Api Call. Error Message: " + e.getMessage());
        }
        return response;
    }

    public static void deleteUser(String sUsername, String sAuthUser, String sAuthPass) {
        log.debug("deleteUser(" + sUsername + ")");
        Assert.assertTrue(checkIfUserExists(sUsername, sAuthUser, sAuthPass), "User '" + sUsername + "' doesn't exist!");
        Response response = deleteUserApiCall(sUsername, sAuthUser, sAuthPass);
        int status = response.getStatusCode();
        String sResponseBody = response.getBody().asString();
        Assert.assertEquals(status, 200, "Wrong Response Status Code in deleteUser(" + sUsername + ") Api Call! Response Body: " + sResponseBody);
        log.debug("User Deleted: " + !checkIfUserExists(sUsername, sAuthUser, sAuthPass));
    }

    public static void deleteUser(String sUsername) {
        deleteUser(sUsername, sAdminUser, sAdminPass);
    }

    public static ApiError deleteUserError(String sUsername, String sAuthUser, String sAuthPass) {
        Response response = deleteUserApiCall(sUsername, sAuthUser, sAuthPass);
        String sResponseBody = response.getBody().asString();
        Gson gson = new Gson();
        return gson.fromJson(sResponseBody, ApiError.class);
    }

    public static ApiError deleteUserError(String sUsername) {
        return deleteUserError(sUsername, sAdminUser, sAdminPass);
    }
}
