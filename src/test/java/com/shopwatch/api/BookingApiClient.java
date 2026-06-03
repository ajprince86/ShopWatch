package com.shopwatch.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingApiClient {

    // Base URL for the Restful Booker API
    private static final String BASE_URL =
            "https://restful-booker.herokuapp.com";

    // Reusable request specification
    private static RequestSpecification requestSpec() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }

    // GET - retrieve all bookings
    public static Response getAllBookings() {
        return requestSpec()
                .when()
                .get("/booking");
    }

    // GET - retrieve a single booking by ID
    public static Response getBookingById(int id) {
        return requestSpec()
                .when()
                .get("/booking/" + id);
    }

    // POST - create a new booking
    public static Response createBooking(String body) {
        return requestSpec()
                .body(body)
                .when()
                .post("/booking");
    }

    // DELETE - delete a booking
    public static Response deleteBooking(int id, String token) {
        return requestSpec()
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + id);
    }
}