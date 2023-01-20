package io.github.tahanima;

import io.github.tahanima.api.BookingApi;
import io.github.tahanima.api.payload.BookingDates;
import io.github.tahanima.api.payload.BookingRequestPayload;
import io.github.tahanima.api.payload.BookingResponsePayload;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author tahanima
 */
class ApiTest extends BaseTest {
    BookingRequestPayload createBookingRequestPayload() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdfDate.format(new Date());

        return BookingRequestPayload.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .totalPrice(faker.number().numberBetween(100, 500))
                .depositPaid(true)
                .bookingDates(
                        BookingDates.builder().checkin(currentDate).checkout(currentDate).build())
                .additionalNeeds("None")
                .build();
    }

    @Test
    void testGetAllBookingIdsReturns200() {
        Response response = BookingApi.getAllBookingIds();

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testGetAllBookingIdsReturnsNonEmptyArray() {
        BookingResponsePayload[] bookingResponsePayload =
                BookingApi.getAllBookingIds().as(BookingResponsePayload[].class);

        assertThat(bookingResponsePayload.length, greaterThan(0));
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
        BookingRequestPayload bookingRequestPayload = createBookingRequestPayload();
        int id =
                BookingApi.createBooking(bookingRequestPayload)
                        .as(BookingResponsePayload.class)
                        .getBookingId();
        Response response = BookingApi.getBookingById(id);

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testCreateBookingReturns200() {
        Response response = BookingApi.createBooking(createBookingRequestPayload());

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testCreateBookingReturnsCorrectDetails() {
        BookingRequestPayload bookingRequestPayload = createBookingRequestPayload();
        BookingResponsePayload bookingResponsePayload =
                BookingApi.createBooking(bookingRequestPayload).as(BookingResponsePayload.class);

        assertThat(
                bookingRequestPayload.equals(bookingResponsePayload.getBookingRequestPayload()),
                is(true));
    }

    @Test
    void testUpdateBookingReturns200() {
        BookingRequestPayload bookingRequestPayload = createBookingRequestPayload();
        int id =
                BookingApi.createBooking(bookingRequestPayload)
                        .as(BookingResponsePayload.class)
                        .getBookingId();
        bookingRequestPayload.setTotalPrice(faker.number().numberBetween(100, 500));

        Response response = BookingApi.updateBooking(bookingRequestPayload, id, token);

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testUpdateBookingReturnsCorrectDetails() {
        BookingRequestPayload bookingRequestPayload = createBookingRequestPayload();
        int id =
                BookingApi.createBooking(bookingRequestPayload)
                        .as(BookingResponsePayload.class)
                        .getBookingId();

        bookingRequestPayload.setFirstName(faker.name().firstName());
        bookingRequestPayload.setLastName(faker.name().lastName());
        bookingRequestPayload.setTotalPrice(faker.number().numberBetween(100, 500));

        BookingRequestPayload bookingResponsePayload =
                BookingApi.updateBooking(bookingRequestPayload, id, token)
                        .as(BookingRequestPayload.class);

        assertThat(bookingRequestPayload.equals(bookingResponsePayload), is(true));
    }

    @Test
    void testPartialUpdateBookingReturns200() {
        BookingRequestPayload bookingRequestPayload = createBookingRequestPayload();
        int id =
                BookingApi.createBooking(bookingRequestPayload)
                        .as(BookingResponsePayload.class)
                        .getBookingId();
        bookingRequestPayload.setTotalPrice(faker.number().numberBetween(100, 500));

        Response response = BookingApi.partialUpdateBooking(bookingRequestPayload, id, token);

        assertThat(response.statusCode(), equalTo(SC_OK));
    }

    @Test
    void testDeleteBookingReturns201() {
        int id =
                BookingApi.createBooking(createBookingRequestPayload())
                        .as(BookingResponsePayload.class)
                        .getBookingId();

        Response response = BookingApi.deleteBooking(id, token);

        assertThat(response.statusCode(), equalTo(SC_CREATED));
    }
}
