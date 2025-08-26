package org.example.tests;

import org.example.utils.ApiMethod;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;

public class ApiTest {

    @Test
    public void testGetLimitedProductsStatusCode() {
        // Call the ApiHelper class method that uses the limit=10 parameter
        Response response = ApiMethod.getLimitedProducts(10);

        // Check the status code
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200, "სტატუს კოდი არ არის 200");

        // We can check the Content-Type of the Response
        String contentType = response.getHeader("Content-Type");
        assertEquals(contentType, "application/json", "Content-Type არ არის application/json");

        // We can check the body of the Response (if needed)
        // For example, let's check that there is some data in the response
        String responseBody = response.getBody().asString();
    }
}