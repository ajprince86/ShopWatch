package com.shopwatch.tests;

import com.shopwatch.api.BookingApiClient;
import com.shopwatch.api.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingApiTest {

    @Test
    public void shouldGetAllBookings() {

        Response response = BookingApiClient.getAllBookings();

        int count = response.jsonPath().getList("bookingid").size();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200");

        Assert.assertTrue(count > 0, "Should have at least one booking");

        System.out.println("All Bookings: " + response.prettyPrint());
    }

    @Test
    public void shouldGetBookingById() {
        Response response = BookingApiClient.getBookingById(1);

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200");

        String firstName = response.jsonPath().getString("firstname");
        System.out.println("First Name: " + firstName);

        Assert.assertNotNull(firstName, "First name should not be null");
    }

    @Test
    public void shouldCreateBooking() {
        String requestBody = "{\n" +
                "    \"firstname\": \"John\",\n" +
                "    \"lastname\": \"Smith\",\n" +
                "    \"totalprice\": 150,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2026-01-01\",\n" +
                "        \"checkout\": \"2026-01-07\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        Response response = BookingApiClient.createBooking(requestBody);

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200");

        int bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Created Booking ID: " + bookingId);

        Assert.assertTrue(bookingId > 0,
                "Booking ID should be greater than 0");
    }

    @Test
    public void shouldDeleteBooking() {
        // First create a booking to delete
        String requestBody = "{\n" +
                "    \"firstname\": \"Jane\",\n" +
                "    \"lastname\": \"Doe\",\n" +
                "    \"totalprice\": 200,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2026-02-01\",\n" +
                "        \"checkout\": \"2026-02-07\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Lunch\"\n" +
                "}";

        Response createResponse = BookingApiClient.createBooking(requestBody);
        int bookingId = createResponse.jsonPath().getInt("bookingid");

        // Now delete it using the token
        String token = TokenManager.getToken();
        Response deleteResponse = BookingApiClient
                .deleteBooking(bookingId, token);

        Assert.assertEquals(deleteResponse.getStatusCode(), 201,
                "Expected status code 201 for deletion");

        System.out.println("Deleted Booking ID: " + bookingId);
    }
}