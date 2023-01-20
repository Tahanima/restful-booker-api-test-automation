package io.github.tahanima.api.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;

/**
 * @author tahanima
 */
@Jacksonized
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDates {
    @JsonProperty private String checkin;

    @JsonProperty private String checkout;
}
