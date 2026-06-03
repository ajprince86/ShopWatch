package com.shopwatch.api;

import io.restassured.RestAssured;

public class TokenManager {

    // Store the token so we only fetch it once
    private static String token;

    public static String getToken() {
        if (token == null) {
            token = RestAssured.given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .header("Content-Type", "application/json")
                    .body("{ \"username\": \"admin\", " +
                            "\"password\": \"password123\" }")
                    .when()
                    .post("/auth")
                    .then()
                    .extract()
                    .path("token");
        }
        return token;
    }
}