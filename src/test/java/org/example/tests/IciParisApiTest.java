package org.example.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.utils.ApiMethods;

 // Test class for validating the API endpoints of iciparis.ge.
 // This class uses RestAssured to send API requests and TestNG for assertions and test management.
public class IciParisApiTest {
    private final ApiMethods api = new ApiMethods();
    private static final String BASE_URL = "https://www.iciparis.ge";


    // Verifies that the brands API endpoint returns a successful 200 OK status code.
    // This test sends a GET request to the brands API and asserts that the status code is 200 and the content type is JSON.
    @Test(description = "Verify that the brands API returns a 200 OK status code.")
    public void getBrandsAndValidateStatusCode() {
        String endpoint = BASE_URL + "/ge/common/brands/get?limit=10";
        Response response = api.getRequest(endpoint);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200, but got " + statusCode);

        System.out.println("Test Passed: The API call returned a status code of " + statusCode);

        String contentType = response.getContentType();
        Assert.assertTrue(contentType.contains("application/json"), "Expected JSON content type, but got " + contentType);

    }
}