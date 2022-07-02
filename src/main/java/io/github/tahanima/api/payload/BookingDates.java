package io.github.tahanima.api.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

/**
 * @author tahanima
 */
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDates {
    @JsonProperty
    private Date checkin;

    @JsonProperty
    private Date checkout;
}
