package io.github.tahanima.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author tahanima
 */
@Getter
public class AuthResponsePayload {
    @JsonProperty private String token;
}
