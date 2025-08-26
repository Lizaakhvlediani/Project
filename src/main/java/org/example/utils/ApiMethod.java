package org.example.utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

 // A utility class for API methods using RestAssured.
 // This class contains common methods to interact with the API endpoints of iciparis.ge,
public class ApiMethod {

    private static final String BASE_URI = "https://www.iciparis.ge"; // The main address of the site
    private static final String GET_PATH = "/ge/common/pages/get"; // Request address


     // Sends a GET request to retrieve a limited number of products.
     //This method uses RestAssured to build and send a request with a 'limit' query parameter.
     public static Response getLimitedProducts(int limit) {
        return given()
                .baseUri(BASE_URI)
                .queryParam("limit", limit) // Adding a parameter with the queryParam method
                .when()
                .get(GET_PATH);
    }
}