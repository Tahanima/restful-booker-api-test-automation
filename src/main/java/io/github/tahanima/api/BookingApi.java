package io.github.tahanima.api;

import io.github.tahanima.api.payload.BookingRequestPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.github.tahanima.util.ApiEndpoints.BASE_URL;
import static io.github.tahanima.util.ApiEndpoints.BOOKING_ENDPOINT;
import static io.restassured.RestAssured.given;

/**
 * @author tahanima
 */
public final class BookingApi {
    private BookingApi() {}

    private static final String ENDPOINT = BASE_URL + BOOKING_ENDPOINT;

    public static Response getAllBookingIds() {
        return given()
                .when()
                .get(ENDPOINT);
    }

    public static Response getBookingIdsByName(String firstName, String lastName) {
        return given()
                .param("firstname", firstName)
                .param("lastname", lastName)
                .when()
                .get(ENDPOINT);
    }

    public static Response getBookingIdsByDate(String checkin, String checkout) {
        return given()
                .param("checkin", checkin)
                .param("checkout", checkout)
                .when()
                .get(ENDPOINT);
    }

    public static Response getBookingById(String id) {
        return given()
                .accept("application/json")
                .when()
                .get(ENDPOINT + id);
    }

    public static Response createBooking(BookingRequestPayload bookingRequestPayload) {
        return given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body(bookingRequestPayload)
                .when()
                .get(ENDPOINT);
    }
}
