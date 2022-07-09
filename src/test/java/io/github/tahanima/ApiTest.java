package io.github.tahanima;

import io.github.tahanima.api.AuthApi;
import io.github.tahanima.api.BookingApi;
import io.github.tahanima.api.PingApi;
import io.github.tahanima.api.payload.AuthRequestPayload;
import io.github.tahanima.api.payload.AuthResponsePayload;
import io.github.tahanima.api.payload.BookingDates;
import io.github.tahanima.api.payload.BookingRequestPayload;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author tahanima
 */
class ApiTest {
    @Test
    void testCreateTokenReturns200() {
        AuthRequestPayload authRequestPayload
                = AuthRequestPayload.builder()
                                    .username("admin")
                                    .password("password123")
                                    .build();

        Response response = AuthApi.createToken(authRequestPayload);

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testCreateTokenReturnsNonEmptyToken() {
        AuthRequestPayload authRequestPayload
                = AuthRequestPayload.builder()
                                    .username("admin")
                                    .password("password123")
                                    .build();

        AuthResponsePayload authResponsePayload
                = AuthApi.createToken(authRequestPayload).as(AuthResponsePayload.class);

        assertThat(authResponsePayload.getToken(), is(not(emptyString())));
    }

    @Test
    void testGetAllBookingIdsReturns200() {
        Response response = BookingApi.getAllBookingIds();

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testGetBookingIdsByNameReturns200() {
        Response response = BookingApi.getBookingIdsByName("sally", "brown");

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testGetBookingIdsByDateReturns200() {
        Response response = BookingApi.getBookingIdsByDate("2014-03-13", "2014-05-21");

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testGetBookingByIdReturns200() {
        Response response = BookingApi.getBookingById("1");

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testCreateBookingReturns200() {
        BookingDates bookingDates = BookingDates.builder()
                                                .checkin(new Date())
                                                .checkout(new Date())
                                                .build();

        BookingRequestPayload bookingRequestPayload
                = BookingRequestPayload.builder()
                                       .firstName("Mary")
                                       .lastName("Active")
                                       .totalPrice(200)
                                       .depositPaid(true)
                                       .bookingDates(bookingDates)
                                       .additionalNeeds("None")
                                       .build();

        Response response = BookingApi.createBooking(bookingRequestPayload);

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testHealthCheckReturns201() {
        Response response = PingApi.healthCheck();

        assertThat(response.statusCode(), equalTo(SC_CREATED));
    }
}
