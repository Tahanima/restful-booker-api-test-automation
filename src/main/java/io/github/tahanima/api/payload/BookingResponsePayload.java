package io.github.tahanima.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author tahanima
 */
public class BookingResponsePayload {
    @JsonProperty("bookingid")
    private int bookingId;

    @JsonProperty
    private BookingRequestPayload bookingRequestPayload;

    public int getBookingId() {
        return bookingId;
    }

    public BookingRequestPayload getBooking() {
        return bookingRequestPayload;
    }
}
