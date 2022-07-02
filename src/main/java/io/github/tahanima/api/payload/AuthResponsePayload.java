package io.github.tahanima.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author tahanima
 */
public class AuthResponsePayload {
    @JsonProperty
    private String token;

    public String getToken() {
        return token;
    }
}
