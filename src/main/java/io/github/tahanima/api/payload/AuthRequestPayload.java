package io.github.tahanima.api.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

/**
 * @author tahanima
 */
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthRequestPayload {
  @JsonProperty private String username;

  @JsonProperty private String password;
}
