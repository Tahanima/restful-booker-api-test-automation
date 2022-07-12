package io.github.tahanima.api.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author tahanima
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponsePayload {
  @JsonProperty("bookingid")
  private int bookingId;

  @JsonProperty("booking")
  private BookingRequestPayload bookingRequestPayload;
}
