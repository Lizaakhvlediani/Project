package org.example.utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

 // A utility class for common API methods using RestAssured.
public class ApiMethods {

     // Sends a GET request to the specified URL.
     // This method simplifies sending a simple GET request and returning the full response object.
    public Response getRequest(String url) {
        return given().when().get(url);
    }
}